package com.sz.setting

import android.os.Bundle
import com.common.base.BaseAppCompactActivitiy
import com.sz.mLauncher.R

class SettingActvity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_actvity)

        initActivitys()
    }

    override fun initTitle() {

    }

}
