package com.sz.mLauncher

import android.os.Bundle
import android.view.View
import com.common.base.BaseAppCompactActivitiy
import com.common.base.BasePagerAdapter
import com.sz.view.SubLauncherView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAppCompactActivitiy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivitys()
    }

    override fun initViews() {
        var list = listOf<View>().toMutableList()

        list.add(SubLauncherView(context , 0))
        list.add(SubLauncherView(context , 1))

        var pagerAdapter = BasePagerAdapter(context , list)
        viewPager.adapter = pagerAdapter

    }



}
