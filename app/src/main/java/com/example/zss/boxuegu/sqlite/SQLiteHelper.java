package com.example.zss.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    public static String DB_NAME="bxg.db";
    public static final String U_USRINFO="userinfo";//用户资料
    public SQLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
/**
 * 创建个人信息表
 */
          db.execSQL("CREATE TABLE IF NOT EXISTS "+U_USRINFO+"("
          +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
          +"userName VARCHAR,"
          + "nickName VARCHAR,"
          +"sex VARCHAR,"
          +"signature VARCHAR"
          +")"
          );
    }

    /**
     * 当数据库版本号增加时调用此方法
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+U_USRINFO);
    onCreate(db);
    }
}
