package com.sz.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
/**
 * Created by E on 2018/2/22.
 */
class IDbHelper : SQLiteOpenHelper{

    constructor(context: Context?) : super(context , "" , null , 0)

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}