package com.bf.weather.ui.city;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bf.weather.R;
import com.bf.weather.entity.City;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 2014 7:07:08 PM
 * @DateUpdate:Feb 23, 2014 7:07:08 PM
 * @Des:当前城市列表填充器
 */
public class CityAdapter extends BaseAdapter{
	private LayoutInflater inflater = null;
	private List<City> cityCurrents = null;
	
	public CityAdapter(Context context,List<City> cityCurrents){
		this.inflater = LayoutInflater.from(context);
		this.cityCurrents = cityCurrents;
	}
	
	public void reset(List<City> cityCurrents){
		this.cityCurrents = cityCurrents;
	}

	@Override
	public int getCount() {
		return cityCurrents.size();
	}

	@Override
	public Object getItem(int position) {
		return cityCurrents.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = null;
		if(convertView == null){
			view = (TextView) this.inflater.inflate(R.layout.item_grid_view_city_current, null);
		}else{
			view = (TextView) convertView;
		}
		view.setText(cityCurrents.get(position).getCityName());
		view.setTag(cityCurrents.get(position).getCityId());
		return view;
	}
}
