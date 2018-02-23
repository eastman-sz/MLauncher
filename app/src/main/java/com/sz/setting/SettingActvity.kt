package com.sz.setting

import android.os.Bundle
import android.view.View
import com.common.base.BaseAppCompactActivitiy
import com.common.base.CommonTitleView
import com.sz.mLauncher.R
import com.sz.view.AppInfoSelectDialog
import kotlinx.android.synthetic.main.activity_setting_actvity.*

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
        }
    }

}
