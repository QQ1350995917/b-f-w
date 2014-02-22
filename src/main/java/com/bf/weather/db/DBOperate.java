package com.bf.weather.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bf.weather.R;
import com.bf.weather.common.Constant;
import com.bf.weather.entity.City;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 2014 4:41:39 PM
 * @DateUpdate:Feb 23, 2014 4:41:39 PM
 * @Des:数据库操作
 */
public class DBOperate {
	
	/**
	 * 导入数据库文件
	 * @author:DingPengwei
	 * @date:Feb 23, 20145:53:29 PM
	 * @param context
	 * @return 执行结果
	 */
	public static boolean importDBFile(Context context){
		boolean result = false;
        try {
        	//String dbDirPath = context.getFilesDir() + "/databases";
        	String dbDirPath = "/data/data/com.bf.weather/databases/";
        	File dbDir = new File(dbDirPath);
        	if(!dbDir.exists()){
        		dbDir.mkdir();
        	}
        	InputStream is = context.getResources().openRawResource(R.raw.bfw);
        	FileOutputStream os = new FileOutputStream(dbDirPath + Constant.DB_NAME);
        	byte[] buffer = new byte[1024];
        	int count = 0;
        	while ((count = is.read(buffer)) > 0) {
        		os.write(buffer, 0, count);
        	}
        	is.close();
        	os.close();
        	result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = true;
		}
        return result;
	}
	
	/**
	 * 查询所有的省份以及直辖市信息
	 * @author:DingPengwei
	 * @date:Feb 23, 20146:12:02 PM
	 * @param context
	 * @return 省份以及直辖市信息列表
	 */
	public static List<City> readAllProvinces(Context context){
		List<City> citys = new ArrayList<City>();
 		DBHelper chatDBHelper = new DBHelper(context,null,null,1);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = chatDBHelper.getWritableDatabase();
			db.beginTransaction();
			cursor = db.rawQuery("select * from city where citygrade = '1' order by cityid", null);
			while(cursor.moveToNext()){
				City citya = new City();
				citya.setId(cursor.getString(cursor.getColumnIndex("id")));
				citya.setCityId(cursor.getString(cursor.getColumnIndex("cityid")));
				citya.setCityGreade(cursor.getString(cursor.getColumnIndex("citygrade")));
				citya.setPearentId(cursor.getString(cursor.getColumnIndex("parentid")));
				citya.setCityName(cursor.getString(cursor.getColumnIndex("cityname")));
				citya.setCityCode(cursor.getString(cursor.getColumnIndex("citycode")));
				citya.setCreateTime(cursor.getString(cursor.getColumnIndex("createtime")));
				citya.setUpdateTime(cursor.getString(cursor.getColumnIndex("updatetime")));
				citys.add(citya);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
		} finally{
			if(cursor != null){
				cursor.close();
			}
			if(db != null){
				db.endTransaction();
				db.close();
			}
		}
		return citys;
	}
	
	/**
	 * 查询所有的省会以及直辖市信息
	 * @author:DingPengwei
	 * @date:Feb 23, 20146:37:36 PM
	 * @param context
	 * @return 省会以及直辖市信息
	 */
	public static List<City> readCapitals(Context context) {
		List<City> citys = new ArrayList<City>();
		DBHelper chatDBHelper = new DBHelper(context,null,null,1);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = chatDBHelper.getWritableDatabase();
			db.beginTransaction();
			cursor = db.rawQuery("select * from city where citygrade = '3' and cityid like '%0101'", null);
			while(cursor.moveToNext()){
				City citya = new City();
				citya.setId(cursor.getString(cursor.getColumnIndex("id")));
				citya.setCityId(cursor.getString(cursor.getColumnIndex("cityid")));
				citya.setCityGreade(cursor.getString(cursor.getColumnIndex("citygrade")));
				citya.setPearentId(cursor.getString(cursor.getColumnIndex("parentid")));
				citya.setCityName(cursor.getString(cursor.getColumnIndex("cityname")));
				citya.setCityCode(cursor.getString(cursor.getColumnIndex("citycode")));
				citya.setCreateTime(cursor.getString(cursor.getColumnIndex("createtime")));
				citya.setUpdateTime(cursor.getString(cursor.getColumnIndex("updatetime")));
				citys.add(citya);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
		} finally{
			if(cursor != null){
				cursor.close();
			}
			if(db != null){
				db.endTransaction();
				db.close();
			}
		}
		return citys;
	}
	
	/**
	 * 根据父节点查找字节点集合
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 6:40:22 PM
	 * @param context
	 * @param parentId
	 * @return 字节点信息集合
	 */
	public static List<City> readSubByParentId(Context context, String parentId) {
		List<City> citys = new ArrayList<City>();
		DBHelper chatDBHelper = new DBHelper(context,null,null,1);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = chatDBHelper.getWritableDatabase();
			db.beginTransaction();
			cursor = db.rawQuery("SELECT * FROM CITY WHERE PARENTID = '" + parentId + "'", null);
			while(cursor.moveToNext()){
				City citya = new City();
				citya.setId(cursor.getString(cursor.getColumnIndex("id")));
				citya.setCityId(cursor.getString(cursor.getColumnIndex("cityid")));
				citya.setCityGreade(cursor.getString(cursor.getColumnIndex("citygrade")));
				citya.setPearentId(cursor.getString(cursor.getColumnIndex("parentid")));
				citya.setCityName(cursor.getString(cursor.getColumnIndex("cityname")));
				citya.setCityCode(cursor.getString(cursor.getColumnIndex("citycode")));
				citya.setCreateTime(cursor.getString(cursor.getColumnIndex("createtime")));
				citya.setUpdateTime(cursor.getString(cursor.getColumnIndex("updatetime")));
				citys.add(citya);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
		} finally{
			if(cursor != null){
				cursor.close();
			}
			if(db != null){
				db.endTransaction();
				db.close();
			}
		}
		return citys;
	}
	
	/**
	 * 根据省份的ID读取其下的城市信息
	 * @author:DingPengwei
	 * @date:Feb 23, 20146:42:51 PM
	 * @param context
	 * @param cityId
	 * @return 城市信息
	 */
	public static List<City> readProvinceCapitals(Context context, String cityId) {
		List<City> citys = new ArrayList<City>();
		DBHelper chatDBHelper = new DBHelper(context,null,null,1);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = chatDBHelper.getWritableDatabase();
			db.beginTransaction();
			cursor = db.rawQuery("select * from city where citygrade = '3' and cityid like '" + cityId + "%01'", null);
			while(cursor.moveToNext()){
				City citya = new City();
				citya.setId(cursor.getString(cursor.getColumnIndex("id")));
				citya.setCityId(cursor.getString(cursor.getColumnIndex("cityid")));
				citya.setCityGreade(cursor.getString(cursor.getColumnIndex("citygrade")));
				citya.setPearentId(cursor.getString(cursor.getColumnIndex("parentid")));
				citya.setCityName(cursor.getString(cursor.getColumnIndex("cityname")));
				citya.setCityCode(cursor.getString(cursor.getColumnIndex("citycode")));
				citya.setCreateTime(cursor.getString(cursor.getColumnIndex("createtime")));
				citya.setUpdateTime(cursor.getString(cursor.getColumnIndex("updatetime")));
				citys.add(citya);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
		} finally{
			if(cursor != null){
				cursor.close();
			}
			if(db != null){
				db.endTransaction();
				db.close();
			}
		}
		return citys;
	}
	
	
}
