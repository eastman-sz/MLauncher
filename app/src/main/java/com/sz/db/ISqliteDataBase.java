package com.sz.db;

import android.database.sqlite.SQLiteDatabase;
import com.sz.application.IApplication;
/**
 * @author E
 */
public class ISqliteDataBase {

	private static SQLiteDatabase sqLiteDatabase = null;

	private final static Object object = new Object();
	
	public static SQLiteDatabase getSqLiteDatabase(){
		if (null == sqLiteDatabase) {
			synchronized (object.getClass()) {
				if (null == sqLiteDatabase) {
					sqLiteDatabase = new IDbHelper(IApplication.Companion.getContext()).getWritableDatabase();
				}
			}
		}
		return sqLiteDatabase;
	}

}
