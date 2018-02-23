package com.sz.dbs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.sz.db.CursorHelper;
import com.sz.db.DbTableHelper;
import com.sz.db.ISqliteDataBase;
/**
 * Created by E on 2018/2/23.
 */
public class PageBgDbHelper {

    public static void saveImgPath(String imgPath){
        delete();

        ContentValues values = new ContentValues();
        values.put("imgPath" , imgPath);

        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        db.insert(DBNAME , null , values);
    }

    public static String getImgPath(){
        String imgPath = null;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , null , null ,null ,null , null);
            if (null != cursor && cursor.moveToNext()){
                cursor.moveToLast();
                imgPath = CursorHelper.getString(cursor , "imgPath");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != cursor){
                cursor.close();
            }
        }
        return imgPath;
    }

    public static void delete(){
        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        db.delete(DBNAME , null , null);
    }

    private final static String DBNAME = "pagebgDbName";

    public static void createTable(SQLiteDatabase db){
        DbTableHelper.fromTableName(DBNAME)
                .addColumn_Varchar("imgPath" , 100)

                .buildTable(db);
    }

    public static void dropTable(SQLiteDatabase db){
        db.execSQL("drop table if exists " + DBNAME);
    }

}
