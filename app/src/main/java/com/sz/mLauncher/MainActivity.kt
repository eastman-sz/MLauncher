package com.sz.mLauncher

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.common.base.BaseAppCompactActivitiy
import com.common.base.BasePagerAdapter
import com.common.base.IonPageChangeListener
import com.sz.dbs.PageBgDbHelper
import com.sz.glide.GlideHelper
import com.sz.util.BroadCastAction
import com.sz.util.PermissionHelpler
import com.sz.view.*
import com.utils.lib.ss.common.PkgHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.runOnUiThread

class MainActivity : BaseAppCompactActivitiy() {

    var appslist = ArrayList<AppInfo>()
    var adapter : SubLauncherAppInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivitys()

        PermissionHelpler.requestPermissions(this)
    }

    override fun initViews() {
        var list = listOf<View>().toMutableList()

        list.add(SubLauncherView(context , 0))
        list.add(SubLauncherView(context , 1))

        var pagerAdapter = BasePagerAdapter(context , list)
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(object : IonPageChangeListener(){
            override fun onPageSelected(index: Int) {
                runOnUiThread {
                    dot0ImageView.alpha = if (0 == index){1f}else{0.5f}
                    dot1ImageView.alpha = if (1 == index){1f}else{0.5f}
                }
            }
        })

        //底部Gridview
        adapter = SubLauncherAppInfoAdapter(context , appslist)
        main_tab_gridview.adapter = adapter

        initBottomApps();

        main_tab_gridview.setOnItemClickListener { parent, view, position, id ->

            var appInfo = appslist[position]
            val pkgName = appInfo.localAppInfo.pkgName

            val openSuccess = PkgHelper.openAPKByPkgName(context , pkgName)

            if (openSuccess){
                return@setOnItemClickListener
            }

            val name = appInfo.localAppInfo.labelName

            if (name.contains("电话") || pkgName.contains("phone")){

                val hasCallPhonePermission = PermissionHelpler.checkHasPermission(context , Manifest.permission.CALL_PHONE)
                if (hasCallPhonePermission){
                    context.startActivity(Intent(Intent.ACTION_DIAL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            }
        }

        freshMainBgImg()
    }

    fun initBottomApps(){
        var pageApplist = PageAppsDbHelper.getPageApps(2)

        if (pageApplist.isEmpty()){
            return
        }

        for (pageAppInfo in pageApplist){
            var pkgName = pageAppInfo.pkgName

            var localAppInfo = PkgHelper.getLocalAppInfo(context , pkgName)

            var appInfo = AppInfo(false , localAppInfo)

            appslist.add(appInfo)
        }

        main_tab_gridview.numColumns = appslist.size

        adapter?.notifyDataSetChanged()
    }

    fun addAppInfo(pkgName : String){
        var localAppInfo = PkgHelper.getLocalAppInfo(context , pkgName)

        var appInfo = AppInfo(false , localAppInfo)

        appslist.add(appInfo)

        main_tab_gridview.numColumns = appslist.size

        adapter?.notifyDataSetChanged()
    }

    fun delAppInfo(pkgName : String){
        for (i in appslist){
            val mPkgName = i.localAppInfo.pkgName

            if (mPkgName.equals(pkgName)){
                appslist.remove(i)

                break
            }
        }

        main_tab_gridview.numColumns = appslist.size

        adapter?.notifyDataSetChanged()
    }

    override fun addBroadCastAction(): java.util.ArrayList<String> {
        val broadList = ArrayList<String>();
        broadList.add(BroadCastAction.PKG_SELECTED_CHG)
        broadList.add(BroadCastAction.MAIN_PAGE_BG_IMG_CHG)
        return broadList
    }

    fun freshMainBgImg(){
        var imgPath = PageBgDbHelper.getImgPath()

        if (imgPath.isNullOrEmpty()){
            return
        }
        GlideHelper.display(main_bg_imageView , imgPath)
    }

    override fun onBroadCastReceive(context: Context?, action: String?, intent: Intent?) {
        when(action){
            BroadCastAction.PKG_SELECTED_CHG -> {
                val pIndex = intent?.getIntExtra("pageIndex" , 0)
                if (pIndex != 2){
                    return
                }

                val pkgName = intent?.getStringExtra("pkgName");
                val added = intent?.getBooleanExtra("added" , false)

                when(added){
                    true -> addAppInfo(pkgName)
                    false -> delAppInfo(pkgName)
                }
            }

            BroadCastAction.MAIN_PAGE_BG_IMG_CHG ->{
                freshMainBgImg()
            }
        }
    }

}
