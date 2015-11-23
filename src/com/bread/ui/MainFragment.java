package com.bread.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bread.widgets.UITableView;

public class MainFragment extends Fragment {

	/**
	 * 全部推荐按钮
	 */
	private Button mMoreRecommandButton;
	/**
	 * 推荐列表
	 */
	private ListView mRecommandList;
	/**
	 * 小编推荐
	 */
	private com.bread.widgets.UITableView mTodayRecommandTableView;
	/**
	 * 今日优惠
	 */
	private com.bread.widgets.UITableView mTodayDiscountTableView;
	private ImageView mLeftImg;
	private TextView mLeftNameTxt;
	private TextView mLeftDiscountPriceTxt;
	private TextView mLeftOriginalPriceTxt;
	private ImageView mCenterImg;
	private TextView mCenterNameTxt;
	private TextView mCenterDiscountPriceTxt;
	private TextView mCenterOriginalPriceTxt;
	private ImageView mRightImg;
	private TextView mRightNameTxt;
	private TextView mRightDiscountPriceTxt;
	private TextView mRightOriginalPriceTxt;
	private LinearLayout mDiscountLeft;
	private LinearLayout mDiscountCenter;
	private LinearLayout mDiscountRight;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
				
		mMoreRecommandButton = (Button) view.findViewById(R.id.more_recommand_btn) ;
		mRecommandList = (ListView) view.findViewById(R.id.recommand_list) ;
		mTodayRecommandTableView = (UITableView) view.findViewById(R.id.fragment_main_today_recommand) ;
		mTodayDiscountTableView = (UITableView) view.findViewById(R.id.fragment_main_today_discount) ;
		
		mLeftImg = (ImageView) view.findViewById(R.id.today_left_discount_img) ;
		mLeftNameTxt = (TextView) view.findViewById(R.id.today_left_discount_name) ;
		mLeftDiscountPriceTxt = (TextView) view.findViewById(R.id.today_left_discount_price) ;
		mLeftOriginalPriceTxt = (TextView) view.findViewById(R.id.today_left_original_price) ;
		mLeftImg.setBackgroundResource(R.drawable.logo) ;
		mLeftNameTxt.setText("披萨") ;
		mLeftDiscountPriceTxt.setText("19.9") ;
		mLeftOriginalPriceTxt.setText("38.8") ;
		
		mCenterImg = (ImageView) view.findViewById(R.id.today_center_discount_img) ;
		mCenterNameTxt = (TextView) view.findViewById(R.id.today_center_discount_name) ;
		mCenterDiscountPriceTxt = (TextView) view.findViewById(R.id.today_center_discount_price) ;
		mCenterOriginalPriceTxt = (TextView) view.findViewById(R.id.today_center_original_price) ;
		mCenterImg.setBackgroundResource(R.drawable.logo) ;
		mCenterNameTxt.setText("披萨") ;
		mCenterDiscountPriceTxt.setText("19.9") ;
		mCenterOriginalPriceTxt.setText("38.8") ;
		
		mRightImg = (ImageView) view.findViewById(R.id.today_right_discount_img) ;
		mRightNameTxt = (TextView) view.findViewById(R.id.today_right_discount_name) ;
		mRightDiscountPriceTxt = (TextView) view.findViewById(R.id.today_right_discount_price) ;
		mRightOriginalPriceTxt = (TextView) view.findViewById(R.id.today_right_original_price) ;
		mRightImg.setBackgroundResource(R.drawable.logo) ;
		mRightNameTxt.setText("披萨") ;
		mRightDiscountPriceTxt.setText("19.9") ;
		mRightOriginalPriceTxt.setText("38.8") ;
		
		mDiscountLeft = (LinearLayout) view.findViewById(R.id.discount_left_lin) ;
		mDiscountCenter = (LinearLayout) view.findViewById(R.id.discount_center_lin) ;
		mDiscountRight = (LinearLayout) view.findViewById(R.id.discount_right_lin) ;
		mDiscountLeft.setOnClickListener(mOnClick) ;
		mDiscountCenter.setOnClickListener(mOnClick) ;
		mDiscountRight.setOnClickListener(mOnClick) ;
		
		mTodayDiscountTableView.addBasicItem("今日优惠") ;
		mTodayDiscountTableView.commit() ;
		mTodayRecommandTableView.addBasicItem("小编推荐") ;
		mTodayRecommandTableView.commit() ;
	}
	
	/**
	 * 点击跳转到商品详情页 TODO 增加跳转逻辑
	 */
	private OnClickListener mOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.discount_left_lin:
				break;
			case R.id.discount_center_lin:
				break;
			case R.id.discount_right_lin:
				break;
			default:
				break;
			}
		}
	};
	
	/**
	 * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
	 * 
	 * @param listView
	 */
	public void setListViewHeight(ListView listView) {

		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
}
