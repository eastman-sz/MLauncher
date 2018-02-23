package com.sz.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sz.dbs.PageBgDbHelper
import com.sz.view.PageAppsDbHelper

/**
 * Created by E on 2018/2/22.
 */
class IDbHelper : SQLiteOpenHelper{

    constructor(context: Context?) : super(context , "mlauncherDbinfo" , null , 1)

    override fun onCreate(db: SQLiteDatabase?) {
        PageAppsDbHelper.createTable(db)
        PageBgDbHelper.createTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        PageAppsDbHelper.dropTable(db)
        PageBgDbHelper.dropTable(db)
    }
}