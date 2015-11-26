package com.duang.easyecard.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.duang.easyecard.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ThirdFragment extends Fragment implements OnItemClickListener{

	private View viewFragment;
	
	private GridView gridView;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	
	//private Button mWebViewButton;
	//private Button mCallPhone;
	
	// ItemImage图标封装为一个数组
	private int [] iconImage = {
			R.drawable.ic_menu_compass,
			R.drawable.app_icon
	};
	// ItemText封装数组
	private String[] iconText = {"校园一卡通网站", "拨打校园卡挂失电话"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		viewFragment=inflater.inflate(R.layout.third, null);
		
		// 实例化控件
		gridView = (GridView) viewFragment.findViewById(R.id.grid_view);
		
		// 新建List
		data_list = new ArrayList<Map<String, Object>>();
		// 获取数据
		getData();
		// 新建适配器
		String [] from = {"image", "text"};
		int [] to = {R.id.grid_view_item_img, R.id.grid_view_item_text};
		sim_adapter = new SimpleAdapter(this.getActivity(), data_list, R.layout.grid_view_item, from, to);
		// 配置适配器
		gridView.setAdapter(sim_adapter);
		// 设置监听器
		gridView.setOnItemClickListener(this);
		return viewFragment;
	}
	
	public List<Map<String, Object>> getData(){        
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i = 0; i < iconImage.length; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", iconImage[i]);
            map.put("text", iconText[i]);
            data_list.add(map);
        }
            
        return data_list;
    }

	// Item的点击事件
	@Override
	public void onItemClick(AdapterView<?> view, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		switch (iconImage[position]) {
		// 访问校园一卡通网站
		case R.drawable.ic_menu_compass:
			Intent intent = new Intent(this.getActivity(), WebViewActivity.class);
			startActivity(intent);
			break;
		// 拨打挂失电话
		case R.drawable.app_icon:
			AlertDialog.Builder dialog = new AlertDialog.Builder(this.getActivity());
			dialog.setTitle("提示");
			dialog.setMessage("您确定要拨打挂失电话\n(0532-6678-2221)吗？");
			dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// 通过Intent调用拨打电话程序
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "053266782221"));
					startActivity(intent);
				}
			});
			dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			dialog.show();
		default:
			break;
			
		}
	}
	
	/*
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.campus_open_webview_button:
			Intent intent = new Intent(this.getActivity(), WebViewActivity.class);
			startActivity(intent);
			break;
		case R.id.campus_call_phone_button:
			Log.d("ThirdFragment", "ready_to_call_phone");
			AlertDialog.Builder dialog = new AlertDialog.Builder(this.getActivity());
			dialog.setTitle("提示");
			dialog.setMessage("您确定要拨打挂失电话\n(0532-6678-2221)吗？");
			dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// 通过Intent调用拨打电话程序
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "053266782221"));
					startActivity(intent);
				}
			});
			dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			dialog.show();
		}
	}
	*/
}

