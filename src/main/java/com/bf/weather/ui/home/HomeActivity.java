package com.bf.weather.ui.home;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.bf.weather.R;
import com.bf.weather.entity.City;
import com.bf.weather.ui.BaseActivity;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Mar 1, 2014 9:57:31 PM
 * @DateUpdate:Mar 1, 2014 9:57:31 PM
 * @Des:首页管理
 */
public class HomeActivity extends BaseActivity implements OnTouchListener{
	
	private class HomeAdapterTemp extends BaseAdapter {
		private LayoutInflater inflater = null;
		private List<String> homeCurrents = null;
		
		public HomeAdapterTemp(Context context,List<String> homeCurrents){
			this.inflater = LayoutInflater.from(context);
			this.homeCurrents = homeCurrents;
		}
		
		public void reset(List<String> homeCurrents){
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
			RelativeLayout view = null;
			if(convertView == null){
				view = (RelativeLayout) this.inflater.inflate(R.layout.item_grid_view_home, null);
			}else{
				view = (RelativeLayout) convertView;
			}
			TextView textView = (TextView) view.findViewById(R.id.tv_item_grid_view_home_name);
			textView.setText(homeCurrents.get(position));
			textView.setTextColor(Color.RED);
			textView.setTextSize(20);
			view.setBackgroundResource(R.drawable.icon);
			return view;
		}
	}
	
	private GridView gv_act_home_list = null;
	private List<String> homes = Arrays.asList(new String[]{"a","b","c","d","e","f","g","h","i"}); 
	private HomeAdapterTemp adapter = null;
	private RelativeLayout rl_act_home_container = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_home);
		this.initView();
	}

	private void initView() {
		this.gv_act_home_list = (GridView) this.findViewById(R.id.gv_act_home_list);
		this.gv_act_home_list.setOnTouchListener(this);
		this.adapter = new HomeAdapterTemp(this,homes);
		this.gv_act_home_list.setAdapter(this.adapter);
		this.rl_act_home_container = (RelativeLayout) this.findViewById(R.id.rl_act_home_container);
	}

	
	private View dragView = null;
	private int startX = 0;
	private int startY = 0;
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		int position = this.gv_act_home_list.pointToPosition((int) x, (int) y);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			View targetView = this.gv_act_home_list.getChildAt(position - this.gv_act_home_list.getFirstVisiblePosition());
			if(targetView == null){
				return false;
			}
			targetView.setVisibility(View.INVISIBLE);
			this.dragView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.item_grid_view_home, null);
			String name = ((TextView)targetView.findViewById(R.id.tv_item_grid_view_home_name)).getText().toString();
			((TextView)this.dragView.findViewById(R.id.tv_item_grid_view_home_name)).setText(name);
			RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rllp.leftMargin = targetView.getLeft();
			rllp.topMargin = targetView.getTop();
			this.dragView.setBackgroundResource(R.drawable.icon);
			this.rl_act_home_container.addView(this.dragView,rllp);
			break;
		case MotionEvent.ACTION_MOVE:
			if(this.dragView == null){
				break;
			}
			RelativeLayout.LayoutParams lp = (LayoutParams) this.dragView.getLayoutParams();
			lp.leftMargin = (int) x - startX;
			lp.topMargin = (int) y - startY;
			this.dragView.setLayoutParams(lp);
			break;
		case MotionEvent.ACTION_UP:
			break;
			
		}
		
		
		return false;
	}

}
