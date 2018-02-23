package com.sz.view;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.sz.db.CursorHelper;
import com.sz.db.DbTableHelper;
import com.sz.db.ISqliteDataBase;
import java.util.ArrayList;
/**
 * Created by E on 2018/2/22.
 */
public class PageAppsDbHelper {

    public static void addApps(int pageIndex , String pkgName){
        ContentValues values = new ContentValues();
        values.put("pageIndex" , pageIndex);
        values.put("pkgName" , pkgName);

        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        int count = db.update(DBNAME , values , "pageIndex = ? and pkgName = ? " , new String[]{String.valueOf(pageIndex) , pkgName});
        if (count < 1){
            db.insert(DBNAME , null , values);
        }
    }

    public static ArrayList<PageApps> getPageApps(int pageIndex){
        ArrayList<PageApps> apps = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , "pageIndex = ? " , new String[]{String.valueOf(pageIndex)} , null , null , null);
            while (null != cursor && cursor.moveToNext()){
                apps.add(fromCursor(cursor));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != cursor){
                cursor.close();
            }
        }
        return apps;
    }

    public static void delete(int pageIndex , String pkgName){
        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        db.delete(DBNAME , "pageIndex = ? and pkgName = ? " , new String[]{String.valueOf(pageIndex) , pkgName});
    }

    private static PageApps fromCursor(Cursor cursor){
        int pageIndex = CursorHelper.getInt(cursor , "pageIndex");
        String pkgName = CursorHelper.getString(cursor , "pkgName");

        PageApps apps = new PageApps(pageIndex , pkgName);
        return apps;
    }

    private final static String DBNAME = "pageAppsDbName";

    public static void createTable(SQLiteDatabase db){
        DbTableHelper.fromTableName(DBNAME)
                .addColumn_Integer("pageIndex")
                .addColumn_Varchar("pkgName" , 20)

                .buildTable(db);
    }

    public static void dropTable(SQLiteDatabase db){
        db.execSQL("drop table if exists " + DBNAME);
    }

}
