package com.sz.application

import android.app.Application
import android.content.Context
/**
 * Created by E on 2018/2/22.
 */
open class IApplication : Application(){

    companion object {
        open fun getContext() : Context {
            return IApplication.getContext()
        }
    }

}