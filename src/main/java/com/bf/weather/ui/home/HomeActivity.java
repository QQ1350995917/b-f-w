package com.bf.weather.ui.home;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.bf.weather.R;
import com.bf.weather.ui.BaseActivity;
import com.bf.weather.ui.home.DragGridView.OnChanageListener;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Mar 1, 2014 9:57:31 PM
 * @DateUpdate:Mar 1, 2014 9:57:31 PM
 * @Des:首页管理
 */
public class HomeActivity extends BaseActivity{
	private DragGridView gv_act_home_list = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_home);
		this.initView();
	}

	private void initView() {
		final List<String> list = Arrays.asList(new String[]{"a","b","c","d","e","f","g","h","i"});
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_grid_view_home, R.id.tv_item_grid_view_home_name, list);
		this.gv_act_home_list = (DragGridView) this.findViewById(R.id.gv_act_home_list);
		this.gv_act_home_list.setAdapter(arrayAdapter);
		
		this.gv_act_home_list.setOnChangeListener(new OnChanageListener() {
			
			@Override
			public void onChange(int from, int to) {
				String temp = list.get(from);
				if(from < to){
					for(int i=from; i<to; i++){
						Collections.swap(list, i, i+1);
					}
				}else if(from > to){
					for(int i=from; i>to; i--){
						Collections.swap(list, i, i-1);
					}
				}
				
				list.set(to, temp);
				arrayAdapter.notifyDataSetChanged();
				
				
			}
		});
	}
}
