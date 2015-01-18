package com.wxh.project.baiduNuomi.activity;


import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.application.MyApplication;
import com.wxh.project.baiduNuomi.common.CommonCallApp;
import com.wxh.project.baiduNuomi.common.CommonConfig;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.db.DBHelper;
import com.wxh.project.baiduNuomi.entity.ProductDetail;
import com.wxh.project.baiduNuomi.task.ProductDetailTask;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends Activity implements OnClickListener{
	//控件相关
	private ImageView imgBack;
	private ImageView imgCollect;
	private TextView txPhoneCall;
	

	private ProductDetailTask detailTask;
	private ProductDetail productDetail = new ProductDetail();
	//交易id 
	private String dealID;
	//数据库
	private DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);
		// 初始化数据库
		db = new DBHelper(ProductDetailActivity.this);
		initUI();
		initData();
	}

	private void initUI() {
		imgBack = (ImageView) findViewById(R.id.img_detail_fanghui);
		imgBack.setOnClickListener(this);
		imgCollect = (ImageView) findViewById(R.id.img_detail_shoucang);
		imgCollect.setOnClickListener(this);
		txPhoneCall = (TextView) findViewById(R.id.tx_product_sales_phone);
		txPhoneCall.setOnClickListener(this);

	}
	private void initData() {
		ViewHolder viewHolder = getProductView();
//		dealID = MyApplication.getTuan().getDeal_id();//接收传过来的参数
		dealID = getIntent().getStringExtra(CommonConfig.EXTRA_DETAIL_ID);
		Log.i("传过来的交易ID", dealID);
		detailTask = new ProductDetailTask(this, productDetail, viewHolder);
		detailTask.execute(String.format(CommonConfig.URL_DETAIL, dealID));
		Cursor cursor = db.executeQuery(CommonConfig.SQL_QUERY_TUAN_BY_DEAL_ID, dealID);
		if (cursor.getCount() > 0) {
			imgCollect.setImageDrawable(getResources().getDrawable(R.drawable.img_shoucang_completed));

		} 
		//开启异步任务进行下载
		//		Intent intent = getIntent();
		//		dealID = intent.getStringExtra(AllTuanActivity.EXTRA_DETAIL_ID);
		//打印Log信息
		//		Log.i("传过来的交易id:", dealID);
		//		ProductTask task  = new ProductTask(this, product, imgTopShow);
		//		task.execute(String.format(CommonConfig.URL_DETAIL, dealID));
		//开启线程进行下载
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_detail_shoucang:
			CommonDialog.confirm(this, "是否收藏该记录？", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Cursor cursor = db.executeQuery(CommonConfig.SQL_QUERY_TUAN_BY_DEAL_ID, dealID);
					if (cursor.getCount() > 0) {
						Toast.makeText(ProductDetailActivity.this, "数据已存在", Toast.LENGTH_LONG).show();
					}else{
						db.executeSQL(CommonConfig.SQL_INSERT_TUAN, dealID, detailTask.getProductDetail().getTitleAbout().getMin_image(),detailTask.getProductDetail().getTitleAbout().getBusiness_title(),
								detailTask.getProductDetail().getTitleAbout().getSubtitle(), String.valueOf(detailTask.getProductDetail().getTitleAbout().getSell_count()), String.valueOf(detailTask.getProductDetail().getRushBuy().getCurrent_price()),
								String.valueOf(detailTask.getProductDetail().getRushBuy().getMarket_price()));
//						Log.i("businessTitle", productDetail.getTitleAbout().getBusiness_title());
						Log.i("异步任务id", detailTask.getDeal_id());
						Log.i("标题", detailTask.getProductDetail().getConsumerTips().getConsumer_tips());
						Toast.makeText(ProductDetailActivity.this, "收藏成功", Toast.LENGTH_LONG).show();
						imgCollect.setImageDrawable(getResources().getDrawable(R.drawable.img_shoucang_completed));
					}
				}
			});
			break;

		case R.id.img_detail_fanghui:
			finish();
		case R.id.tx_product_sales_phone:
			String number = detailTask.getProductDetail().getMerchantBaseinfo().getSellerLlist().getSeller_phone();
			CommonCallApp.callPhone(this, number);
			break;

		default:
			break;
		}
	}

	private ViewHolder getProductView() {
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.imgTopShow = (ImageView) findViewById(R.id.img_top_product);
		viewHolder.txCurrentPrice = (TextView) findViewById(R.id.tx_current_price);  
		viewHolder.txMarketPrice = (TextView) findViewById(R.id.tx_market_price);   
		viewHolder.txMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		viewHolder.txFavourInfo = (TextView) findViewById(R.id.tx_favour_info_text);
		viewHolder.txBusinessTitle = (TextView) findViewById(R.id.tx_business_title);
		viewHolder.txSubTitle = (TextView) findViewById(R.id.tx_subtitle);           
		viewHolder.txSellCount = (TextView) findViewById(R.id.tx_sell_count);   
		viewHolder.txRemainTime = (TextView) findViewById(R.id.tx_remain_time);
		viewHolder.rating=(RatingBar) findViewById(R.id.rating);
		viewHolder.txRatingNum=(TextView) findViewById(R.id.tx_rating_num);
		viewHolder.txUserNum = (TextView) findViewById(R.id.tx_user_num);
		viewHolder.txSafeguardName = (TextView) findViewById(R.id.tx_safeguard_name);
		viewHolder.txBuyContent = (TextView) findViewById(R.id.tx_buy_content);      
		viewHolder.txConsumerTips = (TextView) findViewById(R.id.tx_consumer_tips);  
		
		viewHolder.txProductShopNum = (TextView) findViewById(R.id.tx_product_shop_count);
		viewHolder.txProductSalesName = (TextView) findViewById(R.id.tx_product_sales_title);
		viewHolder.txProductSalesAddr = (TextView) findViewById(R.id.tx_product_sales_addr);
		viewHolder.txProductSalesPhone = (TextView) findViewById(R.id.tx_product_sales_phone);

		return viewHolder;
	}




	public class ViewHolder{
		public ImageView imgTopShow;
		public TextView txCurrentPrice;
		public TextView txMarketPrice;
		public TextView txFavourInfo;
		public TextView txBusinessTitle;
		public TextView txSubTitle;
		public TextView txSellCount;
		public TextView txRemainTime;
		public RatingBar rating;//星条
		public TextView txRatingNum;
		public TextView txUserNum;
		public TextView txSafeguardName;//随便退那个
		public TextView txBuyContent;
		public TextView txConsumerTips;
		
		public TextView txProductShopNum;
		public TextView txProductSalesName;
		public TextView txProductSalesAddr;
		public TextView txProductSalesPhone;
	}
}
