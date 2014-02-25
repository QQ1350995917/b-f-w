package com.bf.weather.ui.city;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.bf.weather.R;
import com.bf.weather.db.DBOperate;
import com.bf.weather.entity.City;
import com.bf.weather.ui.BaseActivity;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 20 14 12:12:11 AM
 * @DateUpdate:Feb 23, 20 14 12:12:11 AM
 * @Des:城市的检索
 */
public class CityActivity extends BaseActivity implements OnClickListener{
	private LinearLayout rl_act_city_container = null;
	private TextView tv_act_city_province = null;
	private TextView tv_act_city_sub_city_list = null;
	private EditText et_act_city_search = null;
	private Button bt_act_city_search = null;
	private Button bt_act_city_controller = null;
	private boolean reversion = true;//由下拉框向输入框翻转
	
	private LayoutInflater inflater = null;
	private GridView gv_act_city_list = null;
	private ListView lv_act_city_list = null;
	private CityAdapter currentCityAdtapter = null;
	private PopupWindow cityPopupwindow = null;
	private String currentProvinceId = null;
	private String currentSubProvinceId = null;
	
	private InputMethodManager inputManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_city);
		this.initView();
		this.currentCityAdtapter = new CityAdapter(this, DBOperate.readCapitals(this));
		this.gv_act_city_list.setAdapter(currentCityAdtapter);
		this.lv_act_city_list.setAdapter(currentCityAdtapter);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_act_city_controller:
			this.onReversionClickListener();
			break;
		case R.id.bt_act_city_search:
			this.onSearchClickListener();
			break;
		case R.id.tv_act_city_province:
			this.showPopupWindowView(v, DBOperate.readAllProvinces(this), new OnMenuListItemClickListener(v.getId()));
			break;
		case R.id.tv_act_city_sub_city_list:
			if(this.currentProvinceId == null){
				Toast.makeText(this, this.getString(R.string.string_tip_choose_province), Toast.LENGTH_SHORT).show();
			}else{
				this.showPopupWindowView(v, DBOperate.readSubByParentId(this, currentProvinceId), new OnMenuListItemClickListener(v.getId()));
			}
			break;
		default:
			break;
		}		
	}
	
	/**
	 * 反转控制的点击事件
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 5:23:48 PM
	 */
	private void onReversionClickListener(){
		if(this.reversion){
			//由列表反转到输入框
			applyRotation(1, 0, 90,this.reversion);
			this.reversion = false;
			this.gv_act_city_list.setVisibility(View.GONE);
			this.lv_act_city_list.setVisibility(View.VISIBLE);
		}else{
			//由输入框反转到列表
			applyRotation(-1, 180, 90,this.reversion);
			this.reversion = true;
			this.lv_act_city_list.setVisibility(View.GONE);
			this.gv_act_city_list.setVisibility(View.VISIBLE);
			this.inputManager.hideSoftInputFromWindow(this.et_act_city_search.getWindowToken(), 0);
		}
		this.currentProvinceId = null;
		this.currentSubProvinceId = null;
		this.tv_act_city_province.setText(this.getString(R.string.string_province_list));
		this.tv_act_city_sub_city_list.setText(this.getString(R.string.string_sub_city_list));
		this.et_act_city_search.setText(null);
		this.currentCityAdtapter.reset(DBOperate.readCapitals(this));
		this.currentCityAdtapter.notifyDataSetChanged();
	}
	
	/**
	 * 搜索的点击事件
	 * @author:DingPengwei
	 * @date:Feb 25, 20147:08:56 PM
	 */
	private void onSearchClickListener(){
		this.gv_act_city_list.setVisibility(View.GONE);
		this.lv_act_city_list.setVisibility(View.VISIBLE);
		this.inputManager.hideSoftInputFromWindow(this.et_act_city_search.getWindowToken(), 0); 
		Editable search = this.et_act_city_search.getText();
		if(search != null && !"".equals(search.toString().trim())){
			currentCityAdtapter.reset(DBOperate.readBySearch(getApplicationContext(),search.toString().trim()));
			currentCityAdtapter.notifyDataSetChanged();
		}
	}
	
    /**
     * Setup a new 3D rotation on the container view.
     * 
     * @param position the item that was clicked to show a picture, or -1 to show the list
     * @param start the start angle at which the rotation must begin
     * @param end the end angle of the rotation
     */
    private void applyRotation(int position, float start, float end,boolean flag) {
        // Find the center of the container
        final float centerX = rl_act_city_container.getWidth() / 2.0f;
        final float centerY = rl_act_city_container.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true,flag);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(position));
        rl_act_city_container.startAnimation(rotation);
    }
    
    /**
     * 初始化View
     * @author:DingPengwei
     * @date:Feb 23, 2014 3:51:52 PM
     */
	private void initView() {
		this.rl_act_city_container = (LinearLayout) this.findViewById(R.id.rl_act_city_container);
		this.tv_act_city_province = (TextView) this.findViewById(R.id.tv_act_city_province);
		this.tv_act_city_province.setOnClickListener(this);
		this.tv_act_city_sub_city_list = (TextView) this.findViewById(R.id.tv_act_city_sub_city_list);
		this.tv_act_city_sub_city_list.setOnClickListener(this);
		this.et_act_city_search = (EditText) this.findViewById(R.id.et_act_city_search);
		this.inputManager = (InputMethodManager)this.et_act_city_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		this.bt_act_city_search = (Button) this.findViewById(R.id.bt_act_city_search);
		this.bt_act_city_search.setOnClickListener(this);
		this.bt_act_city_controller = (Button) this.findViewById(R.id.bt_act_city_controller);
		this.bt_act_city_controller.setOnClickListener(this);
		
		this.inflater = LayoutInflater.from(this);
		this.gv_act_city_list = (GridView) this.findViewById(R.id.gv_act_city_list);
		this.gv_act_city_list.setOnItemClickListener(new OnGridItemClickListener());
		
		this.lv_act_city_list = (ListView) this.findViewById(R.id.lv_act_city_list);
		this.lv_act_city_list.setOnItemClickListener(new OnListItemClickListener());
	}
	
	/**
	 * 显示弹出框
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 7:16:54 PM
	 * @param view
	 * @param citys
	 * @param itemClickListener
	 */
	@SuppressWarnings("deprecation")
	private void showPopupWindowView(View view,List<City> citys,OnItemClickListener itemClickListener){
		this.dissPopupWindowView();
		RelativeLayout popup_city = (RelativeLayout) this.inflater.inflate(R.layout.popup_city,null);
		this.cityPopupwindow = new PopupWindow(view,view.getWidth(), LayoutParams.WRAP_CONTENT);
		this.cityPopupwindow.setContentView(popup_city); 
		ListView lv_popup_list_view_city = (ListView) popup_city.findViewById(R.id.lv_popop_city_menu);
		lv_popup_list_view_city.setOnItemClickListener(itemClickListener);
		CityAdapter adapter = new CityAdapter(this, citys);
		lv_popup_list_view_city.setAdapter(adapter);
		this.cityPopupwindow.setFocusable(true);  
		this.cityPopupwindow.setBackgroundDrawable(new BitmapDrawable());//传null点击其他位置无法消失popwindow
		this.cityPopupwindow.showAsDropDown(view);
	}
	
	/**
	 * 消失弹出框
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 7:17:15 PM
	 */
	private void dissPopupWindowView(){
		if(this.cityPopupwindow != null && this.cityPopupwindow.isShowing()){
			this.cityPopupwindow.dismiss();
			this.cityPopupwindow = null;
		}
	}

    /**
     * This class listens for the end of the first half of the animation.
     * It then posts a new action that effectively swaps the views when the container
     * is rotated 90 degrees and thus invisible.
     */
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;
        private DisplayNextView(int position) {
            mPosition = position;
        }
        public void onAnimationStart(Animation animation) {
        }
        public void onAnimationEnd(Animation animation) {
        	rl_act_city_container.post(new SwapViews(mPosition));
        }
        public void onAnimationRepeat(Animation animation) {
        }
    }
    
    /**
     * This class is responsible for swapping the views and start the second
     * half of the animation.
     */
    private final class SwapViews implements Runnable {
        private final int mPosition;
        public SwapViews(int position) {
            mPosition = position;
        }
        public void run() {
            final float centerX = rl_act_city_container.getWidth() / 2.0f;
            final float centerY = rl_act_city_container.getHeight() / 2.0f;
            Rotate3dAnimation rotation;
            if (mPosition > -1) {
            	tv_act_city_province.setVisibility(View.GONE);
            	tv_act_city_sub_city_list.setVisibility(View.GONE);
                et_act_city_search.setVisibility(View.VISIBLE);
                bt_act_city_search.setVisibility(View.VISIBLE);
                rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false,true);
            } else {
            	et_act_city_search.setVisibility(View.GONE);
            	bt_act_city_search.setVisibility(View.GONE);
            	tv_act_city_province.setVisibility(View.VISIBLE);
            	tv_act_city_sub_city_list.setVisibility(View.VISIBLE);
            	tv_act_city_province.requestFocus();
            	tv_act_city_sub_city_list.requestFocus();
                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false,false);
            }
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            rl_act_city_container.startAnimation(rotation);
        }
    }
    
    /**
     * @Author:DingPengwei
     * @Email:www.dingpengwei@gmail.com
     * @DateCrate:Feb 23, 2014 20:27:11 PM
     * @DateUpdate:Feb 23, 2014 20:27:11 PM
     * @Des:城市菜单的点击事件
     */
    private final class OnMenuListItemClickListener implements OnItemClickListener{
    	private int parentViewId = 0;
    	public OnMenuListItemClickListener(int parentViewId){
    		this.parentViewId = parentViewId;
    	}
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			dissPopupWindowView();
			if(this.parentViewId == R.id.tv_act_city_province){
				tv_act_city_sub_city_list.setText(getString(R.string.string_sub_city_list));
				tv_act_city_province.setText(((TextView)view).getText());
				currentProvinceId = view.getTag().toString();
				currentCityAdtapter.reset(DBOperate.readProvinceCapitals(getApplicationContext(),currentProvinceId));
				currentCityAdtapter.notifyDataSetChanged();
			}else if(this.parentViewId == R.id.tv_act_city_sub_city_list){
				tv_act_city_sub_city_list.setText(((TextView)view).getText());
				currentSubProvinceId = view.getTag().toString();
				currentCityAdtapter.reset(DBOperate.readSubByParentId(getApplicationContext(), view.getTag().toString()));
				currentCityAdtapter.notifyDataSetChanged();
			}
		}
    }
    

    
    /**
     * @Author:DingPengwei
     * @Email:www.dingpengwei@gmail.com
     * @DateCrate:Feb 23, 2014 20:50:24 PM
     * @DateUpdate:Feb 23, 2014 20:50:24 PM
     * @Des:GRIDVIEW当前待选城市的点击事件
     */
    private final class OnGridItemClickListener implements OnItemClickListener{
    	@Override
    	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
    		Toast.makeText(getApplicationContext(), getString(R.string.string_tip_city_choosed) + ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
    	}
    }
    
    /**
     * @Author:DingPengwei
     * @Email:www.dingpengwei@gmail.com
     * @DateCrate:Feb 25, 2014 17:20:24 PM
     * @DateUpdate:Feb 25, 2014 17:20:24 PM
     * @Des:LISTVIEW当前待选城市的点击事件
     */
    private final class OnListItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			Toast.makeText(getApplicationContext(), getString(R.string.string_tip_city_choosed) + ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
		}
    }
    
    /**
     * @Author:DingPengwei
     * @Email:www.dingpengwei@gmail.com
     * @DateCrate:Feb 25, 2014 20:20:24 PM
     * @DateUpdate:Feb 25, 2014 20:20:24 PM
     * @Des:异步线程判断一个城市是否已经被列为HOME
     */
    private final class HomedAsyncTask extends AsyncTask<String, Void, Boolean>{
		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
    }
    
    @Override
    public void onBackPressed() {
    	if(!this.reversion){
    		this.onReversionClickListener();
    		return;
    	}
    	if(this.currentSubProvinceId != null){
    		currentCityAdtapter.reset(DBOperate.readProvinceCapitals(getApplicationContext(),this.currentProvinceId));
			currentCityAdtapter.notifyDataSetChanged();
    		this.currentSubProvinceId = null;
    		this.tv_act_city_sub_city_list.setText(this.getString(R.string.string_sub_city_list));
    		return;
    	}
    	if(this.currentProvinceId != null){
    		currentCityAdtapter.reset(DBOperate.readCapitals(this));
			currentCityAdtapter.notifyDataSetChanged();
    		this.currentProvinceId = null;
    		this.tv_act_city_province.setText(this.getString(R.string.string_province_list));
    		return;
    	}
    	this.finish();
    }
}
