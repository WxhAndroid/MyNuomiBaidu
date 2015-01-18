package com.wxh.project.baiduNuomi.common;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.Button;
/**
 *  ��һ��������setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom)

apiԭ��Ϊ��

Sets the Drawables (if any) to appear to the left of, above, to the right of, and below the text. Use null if you do not want a Drawable there. The Drawables' bounds will be set to their intrinsic bounds.

��˼��ž��ǣ��������ϡ��¡���������ͼ�꣬���������ĳ���ط���ʾ��������Ϊnull��ͼ��Ŀ�߽�������Ϊ���п�ߣ����Զ�ͨ��getIntrinsicWidth��getIntrinsicHeight��ȡ���������߷���
 * @author T400
 *
 */

public class CommonButtonHelper {
	/**
	 * ����Button�е�ͼƬ������ǰ��
	 * @param button ��ť
	 * @param message  button�ϵ�������Ϣ
	 * @param d  ͼƬ��Ϣ��getResources().getDrawable ��ã�
	 */
	public static void setImageforButton(Button button,String message ,Drawable d){
		//����������ť�е�ͼ���������ǰ��
		SpannableString spanText = new SpannableString(String.format("1%s", message));
		
		
		  int a = (int) button.getTextSize();
		//  ����ͼƬ�����ִ�С
		 d.setBounds(0, 0, a, a);
		
		//ԭʼ��С���跨
		//  d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
		
		ImageSpan imageSpan = new ImageSpan(d,ImageSpan.ALIGN_BASELINE);
		spanText.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		button.setText(spanText);
	}
	
	
	/**
	 * �ı�button�ؼ�����drawableTopλ�õı���ͼƬ
	 * @param button  һ��button�ؼ�
	 * @param drawable drawableTop�ı���ͼƬ��
	 */
	public static void changButtonTopImage(Button button, Drawable drawable) {
		
		button.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
	}
	
	/**
	 * �ı�button�ؼ�����drawableRightλ�õı���ͼƬ
	 * @param button  һ��button�ؼ�
	 * @param drawable drawableTop�ı���ͼƬ��
	 */
	public static void changButtonRightImage(Button button, Drawable drawable) {
		
		button.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
	}

}
