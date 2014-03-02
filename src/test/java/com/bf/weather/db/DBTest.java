package com.bf.weather.db;
/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 201412:45:26 PM
 * @DateUpdate:Feb 23, 201412:45:26 PM
 * @Des:数据库接口测试
 */
public class DBTest {

	/*
	 * 查询关键字匹配省份后的省会城市
	 * select * from city where cityid like '%01' and parentid in (select cityid from city where citygrade = 1 and cityname like '%河%');
	 */
}
