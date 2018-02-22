package com.sz.view

import android.content.Context
import android.view.LayoutInflater
import com.common.base.BaseRelativeLayout
import com.sz.mLauncher.R
/**
 * Created by E on 2018/2/22.
 */
class SubLauncherView : BaseRelativeLayout {

    constructor(context: Context?) : super(context){
        init()
    }

    override fun initViews() {
        LayoutInflater.from(context).inflate(R.layout.sub_launcher_view , this);
    }

}