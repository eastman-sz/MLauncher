package com.sz.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.common.base.CustomFontTextView
import com.common.base.IBaseAdapter
import com.common.base.ViewHolder
import com.sz.mLauncher.R
/**
 * Created by E on 2018/2/22.
 */
class SubLauncherAppInfoAdapter : IBaseAdapter<AppInfo>{

    constructor(context: Context , list: ArrayList<AppInfo>) : super(context , list , R.layout.sub_launcher_app_info_adapter_view)

    override fun getConvertView(convertView: View?, lis: MutableList<AppInfo>?, position: Int) {
        val selectImageView : ImageView = ViewHolder.getView(convertView , R.id.select_imageView)
        val iconImageView : ImageView = ViewHolder.getView(convertView , R.id.appinfo_icon_iamgeView)
        val appNameTextView : CustomFontTextView = ViewHolder.getView(convertView , R.id.appName_textView)

        var appInfo = list.get(position)
        var selected = appInfo.selected
        var localAppInfo = appInfo.localAppInfo
        var name = localAppInfo.labelName
        val icon = localAppInfo.icon

        appNameTextView.text = name
        iconImageView.setImageDrawable(icon)

        selectImageView.visibility = if (selected){View.VISIBLE}else{View.GONE}
    }

}