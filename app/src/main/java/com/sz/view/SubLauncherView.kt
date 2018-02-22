package com.sz.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.AdapterView
import com.common.base.BaseRelativeLayout
import com.sz.mLauncher.R
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

        addDefSetting()

        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            AppInfoSelectDialog(context).show()
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


}