package com.bf.weather.entity;
/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 2014 6:05:07 PM
 * @DateUpdate:Feb 23, 2014 6:05:07 PM
 * @Des:对应数据库city表
 */
public class City {
	
	private String id;//数据库ID
	private String cityId;//城市ID
	private String cityGreade;//城市的级别
	private String pearentId;//上级城市ID
	private String cityName;//城市名称
	private String cityCode;//城市标码
	private String createTime;//创建时间
	private String updateTime;//修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityGreade() {
		return cityGreade;
	}
	public void setCityGreade(String cityGreade) {
		this.cityGreade = cityGreade;
	}
	public String getPearentId() {
		return pearentId;
	}
	public void setPearentId(String pearentId) {
		this.pearentId = pearentId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", cityId=" + cityId + ", cityGreade="
				+ cityGreade + ", pearentId=" + pearentId + ", cityName="
				+ cityName + ", cityCode=" + cityCode + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
