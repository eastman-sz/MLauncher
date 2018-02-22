package com.sz.view

import android.content.Context
import android.os.Bundle
import com.common.base.CommonTitleView
import com.common.dialog.BaseDialog
import com.sz.mLauncher.R
import com.utils.lib.ss.info.LocalAppInfo
import kotlinx.android.synthetic.main.appinfo_select_dialog_view.*
/**
 * Created by E on 2018/2/22.
 */
class AppInfoSelectDialog : BaseDialog{

    constructor(context: Context): super(context , R.style.lable_del_dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appinfo_select_dialog_view)

        init()
    }

    override fun initTitle() {
        appinfo_commonTitleView.setCenterTitleText("请选择应用")
        appinfo_commonTitleView.setLeftBtnText("取消")
        appinfo_commonTitleView.setRightBtnText("确定")
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

        val appInfoList = LocalAppInfo.getLocalInstalPackage(context);

        for (i in appInfoList){
            val appInfo = AppInfo(false , i)

            list.add(appInfo)
        }

        adapter.notifyDataSetChanged()
    }


    override fun show() {
        super.show()

        var attributes = window.attributes

        attributes.width = context.resources.displayMetrics.widthPixels
        attributes.height = context.resources.displayMetrics.heightPixels
        window.attributes = attributes

    }

}