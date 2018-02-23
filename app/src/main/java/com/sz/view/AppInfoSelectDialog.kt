package com.sz.view

import android.content.Context
import android.os.Bundle
import com.common.base.CommonTitleView
import com.common.dialog.BaseDialog
import com.sz.mLauncher.R
import com.sz.util.BroadcastUtil
import com.utils.lib.ss.common.PkgHelper
import com.utils.lib.ss.info.LocalAppInfo
import kotlinx.android.synthetic.main.appinfo_select_dialog_view.*
/**
 * Created by E on 2018/2/22.
 */
class AppInfoSelectDialog : BaseDialog{

    var pageIndex : Int = 0;

    constructor(context: Context , pageIndex : Int): super(context , R.style.lable_del_dialog){
        this.pageIndex = pageIndex
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appinfo_select_dialog_view)

        init()
    }

    override fun initTitle() {
        appinfo_commonTitleView.setCenterTitleText("请选择应用")
        appinfo_commonTitleView.setLeftBtnText("取消")
        appinfo_commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                dismiss()
            }
            override fun onRightBtnClick() {
            }
        })
    }

    override fun initViews() {
        val list = ArrayList<AppInfo>()
        val adapter = AppInfoAdapter(context , list)

        appInfo_gridview.adapter = adapter

        //已选择的应用
        val pageAppsList = PageAppsDbHelper.getPageApps(pageIndex)
        var map = HashMap<String , String>()
        for (i in pageAppsList){
            map.put(i.pkgName , i.pkgName)
        }

        //本机所有应用
        val appInfoList = PkgHelper.getAllInstalPackage(context)

        for (i in appInfoList){
            val appInfo = AppInfo(map.containsKey(i.pkgName) , i)

            list.add(appInfo)
        }

        adapter.notifyDataSetChanged()

        appInfo_gridview.setOnItemClickListener { parent, view, position, id ->

            val appInfo = list.get(position)
            val pkgName = appInfo.localAppInfo.pkgName
            var selected = appInfo.selected

            appInfo.selected = !selected

            adapter.notifyDataSetChanged(appInfo_gridview , position)

            when(appInfo.selected){
                false -> PageAppsDbHelper.delete(pageIndex , appInfo.localAppInfo.pkgName)
                true -> PageAppsDbHelper.addApps(pageIndex , appInfo.localAppInfo.pkgName)
            }

            //send broadcast
            BroadcastUtil.sendPkgSelectedChgBroadcast(pageIndex , pkgName , appInfo.selected)
        }
    }

    override fun show() {
        super.show()

        var attributes = window.attributes

        attributes.width = context.resources.displayMetrics.widthPixels
        attributes.height = context.resources.displayMetrics.heightPixels
        window.attributes = attributes

    }

}