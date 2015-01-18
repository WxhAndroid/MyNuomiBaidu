package com.wxh.project.baiduNuomi.extend;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class GridViewForScrollView extends GridView{

	/**
	 * 构造函数
	 * @param context
	 */
	public GridViewForScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public GridViewForScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GridViewForScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//重新定义控件的高度
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
