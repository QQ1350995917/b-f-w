package com.bf.weather.db;

import com.bf.weather.common.Constant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 2014 11:15:04 AM
 * @DateUpdate:Feb 23, 2014 11:15:04 AM
 * @Des:初始化数据库
 */
public class DBHelper extends SQLiteOpenHelper{
	public DBHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, Constant.DB_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table city(" +
				"id integer primary key autoincrement," +
				"cityid varchar(20)," +
				"citygrade varchar(20)," +
				"parentid varchar(20)," +
				"cityname varchar(20)," +
				"citycode varchar(20)," +
				"createtime varchar(20)," +
				"updatetime varchar(20))"
		);
		db.execSQL("create table home(" +
				"id integer primary key autoincrement," +
				"cityid varchar(20)," +
				"cityname varchar(20)," +
				"citycode varchar(20)," +
				"homeindex int(20)," +
				"weatherinfo varchar(3000)," +
				"createtime varchar(20)," +
				"updatetime varchar(20))"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
