package com.sz.mLauncher

import android.os.Bundle
import com.common.base.BaseAppCompactActivitiy

class MainActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivitys()
    }


}
