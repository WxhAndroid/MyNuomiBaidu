package com.wxh.project.baiduNuomi.common;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.Button;
/**
 *  第一个方法：setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom)

api原文为：

Sets the Drawables (if any) to appear to the left of, above, to the right of, and below the text. Use null if you do not want a Drawable there. The Drawables' bounds will be set to their intrinsic bounds.

意思大概就是：可以在上、下、左、右设置图标，如果不想在某个地方显示，则设置为null。图标的宽高将会设置为固有宽高，既自动通过getIntrinsicWidth和getIntrinsicHeight获取。——笔者翻译
 * @author T400
 *
 */

public class CommonButtonHelper {
	/**
	 * 设置Button中的图片在文字前面
	 * @param button 按钮
	 * @param message  button上的文字信息
	 * @param d  图片信息（getResources().getDrawable 获得）
	 */
	public static void setImageforButton(Button button,String message ,Drawable d){
		//设置搜索按钮中的图标紧靠文字前面
		SpannableString spanText = new SpannableString(String.format("1%s", message));
		
		
		  int a = (int) button.getTextSize();
		//  设置图片的文字大小
		 d.setBounds(0, 0, a, a);
		
		//原始大小的设法
		//  d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
		
		ImageSpan imageSpan = new ImageSpan(d,ImageSpan.ALIGN_BASELINE);
		spanText.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		button.setText(spanText);
	}
	
	
	/**
	 * 改变button控件的在drawableTop位置的背景图片
	 * @param button  一个button控件
	 * @param drawable drawableTop的背景图片。
	 */
	public static void changButtonTopImage(Button button, Drawable drawable) {
		
		button.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
	}
	
	/**
	 * 改变button控件的在drawableRight位置的背景图片
	 * @param button  一个button控件
	 * @param drawable drawableTop的背景图片。
	 */
	public static void changButtonRightImage(Button button, Drawable drawable) {
		
		button.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
	}

}
