package com.wxh.project.baiduNuomi.task;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wxh.project.baiduNuomi.activity.ProductDetailActivity.ViewHolder;
import com.wxh.project.baiduNuomi.common.CommonDialog;
import com.wxh.project.baiduNuomi.common.CommonImageHelper;
import com.wxh.project.baiduNuomi.common.CommonJSONHelper;
import com.wxh.project.baiduNuomi.entity.BuyContent;
import com.wxh.project.baiduNuomi.entity.CommentInfo;
import com.wxh.project.baiduNuomi.entity.ConsumerTips;
import com.wxh.project.baiduNuomi.entity.MerchantBaseinfo;
import com.wxh.project.baiduNuomi.entity.ProductDetail;
import com.wxh.project.baiduNuomi.entity.RushBuy;
import com.wxh.project.baiduNuomi.entity.SafeguardInfo;
import com.wxh.project.baiduNuomi.entity.SellerLlist;
import com.wxh.project.baiduNuomi.entity.TitleAbout;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ProductDetailTask extends AsyncTask<String, Void, ProductDetail>{
	private Context context;
	private ProductDetail productDetail;
	private ViewHolder viewHolder;
	private String deal_id;
	private DecimalFormat df = new DecimalFormat("#.00");
	private DecimalFormat dfDouble = new DecimalFormat("#.0");
	//	private


	public ProductDetailTask(Context context, ProductDetail productDetail, ViewHolder viewHolder) {
		super();
		this.context = context;
		this.productDetail = productDetail;
		this.viewHolder = viewHolder;
	}

	@Override
	protected void onPreExecute() {
		CommonDialog.showCustomProgressDialog(context, "��Ʒ���顭��");
	}

	@Override
	protected ProductDetail doInBackground(String... params) {
		productDetail = new ProductDetail();
		String json = CommonJSONHelper.getJSON(params[0]);
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject jsonData = jsonObject.getJSONObject("data");
			if(jsonData != null && jsonData.length() > 0){
				deal_id = jsonData.getString("deal_id");
				//				Log.i("�����Ľ���id", deal_id);
			}
			//����RushBuy
			JSONObject jsonRushBuy = jsonData.getJSONObject("rush_buy");
			if(jsonRushBuy != null && jsonRushBuy.length() > 0){
				RushBuy rushBuy = new RushBuy();
				rushBuy.setCurrent_price(jsonRushBuy.getInt("current_price"));
				rushBuy.setMarket_price(jsonRushBuy.getInt("market_price"));
				rushBuy.setSell_status(jsonRushBuy.getInt("sell_status"));
				Log.i("�г���", String.valueOf(jsonRushBuy.getInt("current_price")));
				//				rushBuy.setFavour_info(parseFavourInfo(jsonRushBuy.getJSONObject("favour_info")));
				productDetail.setRushBuy(rushBuy);
			}

			//����TitleAbout
			JSONObject jsonTitleAbout = jsonData.getJSONObject("title_about");
			if(jsonTitleAbout != null && jsonTitleAbout.length() > 0){
				TitleAbout about = new TitleAbout();
				about.setImage(jsonTitleAbout.getJSONArray("image").getString(0));
				about.setMin_image(jsonTitleAbout.getString("min_image"));
				Log.i("ͼƬ��ַ", jsonTitleAbout.getString("min_image"));
				about.setBusiness_title(jsonTitleAbout.getString("business_title"));
				about.setSubtitle(jsonTitleAbout.getString("subtitle"));
				about.setSell_count(jsonTitleAbout.getInt("sell_count"));

				String str = jsonTitleAbout.getString("remain_time");

				int day=Integer.parseInt(str)/(24*3600);
				int hour=(Integer.parseInt(str)-(day*(24*3600)))/3600;
				int min=(Integer.parseInt(str)-((24*day+hour)*3600))/60;
				about.setRemain_time(String.format("ʣ��%s��%sСʱ%s��", String.valueOf(day), String.valueOf(hour), String.valueOf(min)));
				//����BitMap
				//				about.setBitmap(CommonImageHelper.getBitmap(about.getMin_image()));
				about.setBitmap(CommonImageHelper.getBitmap(about.getImage()));
				productDetail.setTitleAbout(about);
			}
			//����SafeguardInfo
			JSONArray safeguaArray = jsonData.getJSONArray("safeguard_info");
			if(safeguaArray!= null && safeguaArray.length() > 0){
				List<SafeguardInfo> infoList = new ArrayList<SafeguardInfo>();
				SafeguardInfo info = new SafeguardInfo();
				for (int i = 0; i < safeguaArray.length(); i++) {
					JSONObject jsonSafeInfo = safeguaArray.getJSONObject(i);
					info.setSafeguard_name(jsonSafeInfo.getString("safeguard_name"));
					infoList.add(info);

					productDetail.setSafeguardInfo(infoList);
					//						Log.i("SafeguardInfo����", jsonSafeInfo.getString("safeguard_name"));
				}
			}

			//����BuyContent
			JSONObject jsonBuyContent = jsonData.getJSONObject("buy_content");
			if(jsonBuyContent != null && jsonBuyContent.length() > 0){
				BuyContent buyContent = new BuyContent();
				buyContent.setBuy_content(jsonBuyContent.getString("buy_content"));

				productDetail.setBuyContent(buyContent);
				//					Log.i("BuyContent����", jsonBuyContent.getString("buy_content"));
			}

			//����ConsumerTips
			JSONObject jsonConsumerTips = jsonData.getJSONObject("consumer_tips");
			if(jsonConsumerTips != null && jsonConsumerTips.length() > 0){
				ConsumerTips tips = new ConsumerTips();
				tips.setConsumer_tips(jsonConsumerTips.getString("consumer_tips"));

				productDetail.setConsumerTips(tips);
				//					Log.i("ConsumerTips����", jsonConsumerTips.getString("consumer_tips"));
			}

			//����comment_info
			JSONObject jsonCommentInfo = jsonData.getJSONObject("comment_info");
			if(jsonCommentInfo != null && jsonCommentInfo.length() > 0){
				CommentInfo commentInfo = new CommentInfo();
				commentInfo.setAverage_score_display(jsonCommentInfo.getDouble("average_score_display"));
				commentInfo.setUser_num(jsonCommentInfo.getInt("user_num"));


				productDetail.setCommentInfo(commentInfo);
			}
			//����merchant_baseinfo
			JSONObject jsonMerchantBaseinfo = jsonData.getJSONObject("merchant_baseinfo");
			if(jsonMerchantBaseinfo != null && jsonMerchantBaseinfo.length() > 0){
				MerchantBaseinfo baseinfo = new MerchantBaseinfo();
				baseinfo.setShop_num(jsonMerchantBaseinfo.getInt("shop_num"));
//				Log.i("�ֵ�����", "" +jsonMerchantBaseinfo.getInt("shop_num"));
				baseinfo.setSellerLlist(parseSellerListObject(jsonMerchantBaseinfo.getJSONObject("seller_list")));
				
				productDetail.setMerchantBaseinfo(baseinfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return productDetail;
	}
	
	/**
	 * ��������SellerLlist
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 */
	private SellerLlist parseSellerListObject(JSONObject jsonObject) throws JSONException {
		SellerLlist sellerLlist = new SellerLlist();
		if(jsonObject != null){
			sellerLlist.setLat(jsonObject.getDouble("lat"));//γ��
			sellerLlist.setLng(jsonObject.getDouble("lng"));//����
			sellerLlist.setSeller_address(jsonObject.getString("seller_address"));
			sellerLlist.setSeller_name(jsonObject.getString("seller_name"));
			sellerLlist.setSeller_phone(jsonObject.getString("seller_phone"));

		}
		return sellerLlist;
	}

	@Override
	protected void onPostExecute(ProductDetail result) {
		viewHolder.imgTopShow.setImageBitmap(result.getTitleAbout().getBitmap());
		viewHolder.rating.setRating((float) result.getCommentInfo().getAverage_score_display());
		viewHolder.txBusinessTitle.setText(result.getTitleAbout().getBusiness_title());
		viewHolder.txBuyContent.setText(result.getBuyContent().getBuy_content());
		viewHolder.txConsumerTips.setText(result.getConsumerTips().getConsumer_tips());
		viewHolder.txCurrentPrice.setText(df.format(result.getRushBuy().getCurrent_price() / 100.0));
		//		viewHolder.txFavourInfo.setText((CharSequence) result.getRushBuy().getFavour_info().getText());
		viewHolder.txMarketPrice.setText(df.format(result.getRushBuy().getMarket_price() / 100.0));
		viewHolder.txRatingNum.setText(String.valueOf(dfDouble.format(result.getCommentInfo().getAverage_score_display())));
		viewHolder.txRemainTime.setText(result.getTitleAbout().getRemain_time());
		viewHolder.txSafeguardName.setText(result.getSafeguardInfo().get(0).getSafeguard_name());
		viewHolder.txSellCount.setText(String.format("���ۣ�%s", result.getTitleAbout().getSell_count()));
		viewHolder.txSubTitle.setText(result.getTitleAbout().getSubtitle());
		viewHolder.txUserNum.setText(String.format("%s������", result.getCommentInfo().getUser_num()));
		 
		viewHolder.txProductShopNum.setText(String.format("%s�ҷֵ�", result.getMerchantBaseinfo().getShop_num()));
		viewHolder.txProductSalesName.setText(result.getMerchantBaseinfo().getSellerLlist().getSeller_name());
		viewHolder.txProductSalesAddr.setText(result.getMerchantBaseinfo().getSellerLlist().getSeller_address());
		viewHolder.txProductSalesPhone.setText(result.getMerchantBaseinfo().getSellerLlist().getSeller_phone());
		CommonDialog.closeCustomProgressDialog();
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public String getDeal_id() {
		return deal_id;
	}

}
