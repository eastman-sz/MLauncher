package com.sz.mLauncher

import android.os.Bundle
import android.view.View
import com.common.base.BaseAppCompactActivitiy
import com.common.base.BasePagerAdapter
import com.sz.view.AppInfo
import com.sz.view.AppInfoAdapter
import com.sz.view.PageAppsDbHelper
import com.sz.view.SubLauncherView
import com.utils.lib.ss.common.PkgHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAppCompactActivitiy() {

    var appslist = ArrayList<AppInfo>()
    var adapter : AppInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivitys()
    }

    override fun initViews() {
        var list = listOf<View>().toMutableList()

        list.add(SubLauncherView(context , 0))
        list.add(SubLauncherView(context , 1))

        var pagerAdapter = BasePagerAdapter(context , list)
        viewPager.adapter = pagerAdapter

        //底部Gridview
        adapter = AppInfoAdapter(context , appslist)
        main_tab_gridview.adapter = adapter

        initBottomApps();
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

            adapter?.notifyDataSetChanged()
        }
    }

}
