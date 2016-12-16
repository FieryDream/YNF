package com.bw.ynf.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GaoJun on 2016/12/16 0016.
 */

public class MySqlite extends SQLiteOpenHelper {
    public MySqlite(Context context) {
        super(context, "ynf.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建收藏表，用来存放收藏商品的url
        db.execSQL("create table collect(_id integer primary key autoincrement,scUrl text,id text)");
        //创建购物车表，用来存放被放进购物车的商品的url
        db.execSQL("create table shopping(_id integer primary key autoincrement,shoppingUrl text,id text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
