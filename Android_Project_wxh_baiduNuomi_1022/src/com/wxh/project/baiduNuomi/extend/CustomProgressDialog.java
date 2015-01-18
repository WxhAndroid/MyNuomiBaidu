package com.wxh.project.baiduNuomi.extend;

import com.example.android_wxh_project_baidunuomi.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomProgressDialog extends Dialog{

	private Context context = null;
	private static CustomProgressDialog customProgressDialog = null;

	public CustomProgressDialog(Context context){
		super(context);
		this.context = context;
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	/**
	 * 创建对话框
	 * @param context
	 * @return
	 */
	public static CustomProgressDialog createDialog(Context context){
		customProgressDialog = new CustomProgressDialog(context,R.style.CustomProgressDialog);
//		设置成下面的就会覆盖背景主题显示对话框
		//		customProgressDialog = new CustomProgressDialog(context, R.layout.customprogressdialog);
		customProgressDialog.setContentView(R.layout.customprogressdialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		return customProgressDialog;
	}


	/**
	 * 设置标题
	 * @param strTitle
	 * @return
	 */
	public CustomProgressDialog setTitile(String strTitle){
		return customProgressDialog;
	}
	
	/**
	 * 创建对话框 设置显示图片
	 */
		public void onWindowFocusChanged(boolean hasFocus){

			if (customProgressDialog == null){
				return;
			}

			ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
			AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
			animationDrawable.start();
		}

	/**
	 * 设置文本内容
	 * @param strMessage
	 * @return
	 */
	public static CustomProgressDialog setMessage(String strMessage){
		TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.tx_progress_dialog_show);

		if (tvMsg != null){
			tvMsg.setText(strMessage);
		}

		return customProgressDialog;
	}
}
