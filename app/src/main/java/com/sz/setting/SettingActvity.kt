package com.sz.setting

import android.os.Bundle
import android.view.View
import com.common.base.BaseAppCompactActivitiy
import com.common.base.CommonTitleView
import com.sz.mLauncher.R
import com.sz.view.AppInfoSelectDialog
import kotlinx.android.synthetic.main.activity_setting_actvity.*
import android.content.Intent
import android.R.attr.data
import android.provider.MediaStore
import android.util.Log
import com.sz.dbs.PageBgDbHelper
import com.sz.util.BroadcastUtil


class SettingActvity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_actvity)

        initActivitys()
    }

    override fun initTitle() {
        setting_commonTitleView.setCenterTitleText("设置")
        setting_commonTitleView.setLeftBtnText("返回")
        setting_commonTitleView.setOnTitleClickListener(object : CommonTitleView.OnTitleClickListener(){
            override fun onLeftBtnClick() {
                onBackPressed()
            }
        })
    }

    fun onBtnClick(v : View){
        when(v){
            bottomAppSelectedBtn -> AppInfoSelectDialog(context , 2).show()
            page1AppSelectedBtn -> AppInfoSelectDialog(context , 0).show()
            page2AppSelectedBtn -> AppInfoSelectDialog(context , 1).show()
            mainBgImgSelectedBtn -> openGallery()
        }
    }

    fun openGallery(){
/*        val i = Intent()
        i.type = "image*//*"
        i.action = Intent.ACTION_PICK
        startActivityForResult(i, 11)*/

        val intent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 11)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode  != 11 || null == data){
            return
        }
        val selectedImage = data.data
        val filePathColumns = arrayOf(MediaStore.Images.Media.DATA)
        val c = contentResolver.query(selectedImage, filePathColumns, null, null, null)
        c!!.moveToFirst()
        val columnIndex = c.getColumnIndex(filePathColumns[0])
        val imagePath = c.getString(columnIndex)

        PageBgDbHelper.saveImgPath(imagePath)

        //发送广播
        BroadcastUtil.sendMainBgImgChgBroadcast()
    }

}
