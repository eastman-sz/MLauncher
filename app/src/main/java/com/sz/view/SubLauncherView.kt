package com.sz.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.AdapterView
import com.common.base.BaseRelativeLayout
import com.sz.mLauncher.R
import com.sz.util.BroadCastAction
import com.utils.lib.ss.common.PkgHelper
import com.utils.lib.ss.info.LocalAppInfo
import kotlinx.android.synthetic.main.sub_launcher_view.view.*
/**
 * Created by E on 2018/2/22.
 */
class SubLauncherView : BaseRelativeLayout {

    var pageIndex : Int = 0
    var list = ArrayList<AppInfo>()
    var adapter : AppInfoAdapter? = null

    constructor(context: Context? , pageIndex : Int) : super(context){
        this.pageIndex = pageIndex;
        init()
    }

    override fun initViews() {
        LayoutInflater.from(context).inflate(R.layout.sub_launcher_view , this);

        adapter = AppInfoAdapter(context , list)
        gridview.adapter = adapter

        addPageApps()
        addDefSetting()

        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            var appInfo = list.get(position)
            var pkgName = appInfo.localAppInfo.pkgName

            if (pkgName.equals("com.sz.self.setting")){
                AppInfoSelectDialog(context , pageIndex).show()
            }else{

                PkgHelper.openAPKByPkgName(context , pkgName)
            }
        }
    }

    fun addDefSetting(){
        if (0 != pageIndex){
            return
        }
        var appInfo = AppInfo(false , LocalAppInfo("com.sz.self.setting" , "设置" , "" ,null))

        list.add(appInfo)

        adapter?.notifyDataSetChanged()
    }

    fun addPageApps(){
        var pageApplist = PageAppsDbHelper.getPageApps(pageIndex)

        if (pageApplist.isEmpty()){
            return
        }

        for (pageAppInfo in pageApplist){
            var pkgName = pageAppInfo.pkgName

            var localAppInfo = PkgHelper.getLocalAppInfo(context , pkgName)

            var appInfo = AppInfo(false , localAppInfo)

            list.add(appInfo)

            adapter?.notifyDataSetChanged()
        }
    }

    override fun addBroadCastAction(): java.util.ArrayList<String> {
        val broadList = ArrayList<String>();
        broadList.add(BroadCastAction.PKG_SELECTED_CHG)
        return broadList
    }

    override fun onBroadCastReceive(context: Context?, action: String?, intent: Intent?) {
        if (!action.equals(BroadCastAction.PKG_SELECTED_CHG)){
            return
        }
        val pIndex = intent?.getIntExtra("pageIndex" , 0)
        if (pIndex != pageIndex){
            return
        }






    }


}