package com.bf.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.bf.weather.R;
import com.bf.weather.ui.city.CityActivity;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 2014 11:21:26 AM
 * @DateUpdate:Feb 23, 2014 11:21:26 AM
 * @Des:欢迎页面
 */
public class SplashActivity extends BaseActivity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_splash);
		Intent intent = new Intent();
		intent.setClass(this, CityActivity.class);
		this.startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_act_splash_logo:
			Intent intent = new Intent();
			intent.setClass(this, CityActivity.class);
			this.startActivity(intent);
			break;
		default:
			break;
		}
	}

}
