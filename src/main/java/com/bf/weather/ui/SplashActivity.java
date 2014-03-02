package com.bf.weather.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bf.weather.R;
import com.bf.weather.ui.home.HomeActivity;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 2014 11:21:26 AM
 * @DateUpdate:Feb 23, 2014 11:21:26 AM
 * @Des:启动页面
 */
public class SplashActivity extends BaseActivity implements OnClickListener{
	private ImageView iv_act_splash_logo = null;
	private TextView tv_act_splash_app_name_black_face = null;
	private TextView tv_act_splash_app_name_weather = null;
	private TextView tv_act_splash_app_name_version = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_splash);
		this.initView();
		this.initVersion();
		new Handler().postDelayed(new SplashHandler(), 3000);
	}
	
	/**
	 * 初始化VIEW
	 * @author:DingPengwei
	 * @date:Mar 2, 201410:16:25 AM
	 */
	private void initView(){
		this.iv_act_splash_logo = (ImageView) this.findViewById(R.id.iv_act_splash_logo);
		this.tv_act_splash_app_name_black_face = (TextView) this.findViewById(R.id.tv_act_splash_app_name_black_face);
		this.tv_act_splash_app_name_black_face.setTypeface(Typeface.createFromAsset(this.getAssets(),"fonts/samplefont.ttf"));
		this.tv_act_splash_app_name_weather = (TextView) this.findViewById(R.id.tv_act_splash_app_name_weather);
		this.tv_act_splash_app_name_weather.setTypeface(Typeface.createFromAsset(this.getAssets(),"fonts/samplefont.ttf"));
		this.tv_act_splash_app_name_version = (TextView) this.findViewById(R.id.tv_act_splash_app_name_version);
		Animation animation = new TranslateAnimation(0.0f,0.0f, -200.0f, 0.0f);
		animation.setDuration(1000);
		animation.setStartOffset(300);
		animation.setRepeatMode(Animation.RESTART);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.bounce_interpolator));
        this.iv_act_splash_logo.startAnimation(animation);
	}
	
	/**
	 * 读取版本信息
	 * @author:DingPengwei
	 * @date:Mar 2, 201410:17:10 AM
	 */
	private void initVersion(){
		try {
			PackageManager packageManager = getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
			String version = packInfo.versionName;
			this.tv_act_splash_app_name_version.setText(this.getString(R.string.string_version) + version);
		} catch (Exception e) {
			this.tv_act_splash_app_name_version.setText(this.getString(R.string.string_version) + "alpha0.1");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @Author:DingPengwei
     * @Email:www.dingpengwei@gmail.com
     * @DateCrate:Mar 2, 2014 11:21:26 AM
     * @DateUpdate:Mar 2, 2014 11:21:26 AM
     * @Des:延迟启动线程
	 */
	class SplashHandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), HomeActivity.class));
            SplashActivity.this.finish();
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        }
    }
}
