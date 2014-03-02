package com.bf.weather.ui.home;

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
 * @DateCrate:Mar 2, 2014 4:00:53 PM
 * @DateUpdate:Mar 2, 2014 4:00:53 PM
 * @Des:description
 */
public class HomeAdapter extends BaseAdapter{
	private LayoutInflater inflater = null;
	private List<City> homeCurrents = null;
	
	public HomeAdapter(Context context,List<City> homeCurrents){
		this.inflater = LayoutInflater.from(context);
		this.homeCurrents = homeCurrents;
	}
	
	public void reset(List<City> homeCurrents){
		this.homeCurrents = homeCurrents;
	}

	@Override
	public int getCount() {
		return this.homeCurrents.size();
	}

	@Override
	public Object getItem(int position) {
		return this.homeCurrents.get(position);
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
		view.setText(homeCurrents.get(position).getCityName());
		view.setTag(homeCurrents.get(position).getCityId());
		return view;
	}

}
