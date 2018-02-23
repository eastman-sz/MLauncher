package com.sz.empty

import android.content.Context
import android.view.View
import com.common.base.BaseRelativeLayout
import com.sz.glide.GlideHelper
import com.sz.mLauncher.R
import kotlinx.android.synthetic.main.load_apps_empty_view.view.*

/**
 * Created by Administrator on 2018/2/23.
 */
class LoadappsEmptyView : BaseRelativeLayout {

    constructor(context: Context) : super(context){
        init()
    }

    override fun initViews() {
        View.inflate(context , R.layout.load_apps_empty_view , this)

        GlideHelper.display(context ,gif_imageView , R.drawable.loading_spinner)
    }



}