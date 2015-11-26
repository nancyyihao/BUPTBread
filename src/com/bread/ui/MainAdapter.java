package com.bread.ui;

import java.util.ArrayList;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bread.network.AsyncImageLoader;
import com.bread.network.AsyncImageLoader.ImageCallback;
import com.bread.ui.R;
import com.bread.utils.CartData;

public class MainAdapter extends BaseAdapter {

	private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
	private ArrayList<Map<String, Object>> mBreadInfo;
	private Context mContext;
	
	public MainAdapter(Context context , ArrayList<Map<String, Object>> breadInfo) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		this.mBreadInfo = breadInfo;
	}

	@Override
	public int getCount() {
		return mBreadInfo.size();// 返回数组的长度
	}

	@Override
	public Object getItem(int position) {
		return mBreadInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView,
			ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.itemlist_shop, null);
			holder.image = (ImageView) convertView
					.findViewById(R.id.itemlist_image);
			holder.name = (TextView) convertView
					.findViewById(R.id.itemlist_shopname);
			holder.price = (TextView) convertView
					.findViewById(R.id.waimai_shopmenu_adapter_item_price);
			holder.plusButton = (ImageButton) convertView
					.findViewById(R.id.plus_btn);
			holder.minusButton = (ImageButton) convertView
					.findViewById(R.id.minus_btn);
			holder.numTextView = (TextView) convertView
					.findViewById(R.id.count);
			convertView.setTag(holder);// 绑定ViewHolder对象
		} else {
			holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
		}
		
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final LinearLayout layout = (LinearLayout) mInflater.inflate(R.layout.dialog_detail, null);
				final Dialog dialog = new Dialog(mContext);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(layout);
				dialog.show();
				
				final ImageView imageView = (ImageView) layout.findViewById(R.id.dialog_detail_big_image);
				new AsyncImageLoader(mContext).downloadImage(mBreadInfo.get(position).get("image").toString(),true,
						new ImageCallback() {
							@Override
							public void onImageLoaded(Bitmap bitmap, String imageUrl) {
								imageView.setImageBitmap(bitmap);
							}
						});
				
				TextView textViewPrice = (TextView)layout.findViewById(R.id.dialog_detail_single_price);
				textViewPrice.setText(holder.price.getText().toString());
				
				TextView textViewNum = (TextView) layout.findViewById(R.id.count);
				textViewNum.setText(holder.numTextView.getText().toString());
				
				layout.findViewById(R.id.dialog_detail_close).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				layout.findViewById(R.id.dialog_detail_addcart).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				final TextView textViewin = (TextView) layout.findViewById(R.id.count);
				layout.findViewById(R.id.plus_btn).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						holder.plusButton.callOnClick();
						textViewin.setText(holder.numTextView.getText().toString());
					}
				});
				layout.findViewById(R.id.minus_btn).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						holder.minusButton.callOnClick();
						textViewin.setText(holder.numTextView.getText().toString());
					}
				});
				
			}
		});
		new AsyncImageLoader(mContext).downloadImage(mBreadInfo.get(position).get("image").toString(),true,
				new ImageCallback() {
					@Override
					public void onImageLoaded(Bitmap bitmap, String imageUrl) {
						holder.image.setImageBitmap(bitmap);
					}
				});
		
		holder.name.setText(mBreadInfo.get(position).get("name")
				.toString());
		holder.price.setText(mBreadInfo.get(position).get("price")
				.toString());
		holder.numTextView.setText(String.valueOf(CartData.findCart(position)));
		holder.plusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int num = Integer.parseInt(holder.numTextView.getText()
						.toString()) + 1;
				holder.numTextView.setText(String.valueOf(num));

				CartData.editCart(mBreadInfo.get(position).get("id").toString(),
						mBreadInfo.get(position).get("name").toString(),
						mBreadInfo.get(position).get("price").toString(),
						String.valueOf(num),
						mBreadInfo.get(position).get("image").toString());
			}
		});
		holder.minusButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int num = Integer.parseInt(holder.numTextView.getText()
						.toString()) - 1;
				if (num >= 0) {
					holder.numTextView.setText(String.valueOf(num));
					if (num == 0) {
						CartData.removeCart(mBreadInfo.get(position)
								.get("id").toString());
					} else {
						CartData.editCart(mBreadInfo.get(position).get("id").toString(),
								mBreadInfo.get(position).get("name").toString(),
								mBreadInfo.get(position).get("price").toString(),
								String.valueOf(num),
								mBreadInfo.get(position).get("image").toString());
					}

				}
			}
		});
		
		return convertView;
	}
}
