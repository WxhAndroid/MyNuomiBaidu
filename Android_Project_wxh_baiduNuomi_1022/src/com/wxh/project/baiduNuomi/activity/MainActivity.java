package com.wxh.project.baiduNuomi.activity;

import com.example.android_wxh_project_baidunuomi.R;
import com.wxh.project.baiduNuomi.update.CheckUpdate;
import com.wxh.project.baiduNuomi.broadcast.NetworkBroadcastReceiver;
import com.wxh.project.baiduNuomi.common.CommonButtonHelper;
import com.wxh.project.baiduNuomi.db.DBHelper;
import com.wxh.project.baiduNuomi.entity.Tuan;
import com.wxh.project.baiduNuomi.extend.ListViewForScrollView;
import com.wxh.project.baiduNuomi.fragment.FirstFragment;
import com.wxh.project.baiduNuomi.fragment.FourthFragment;
import com.wxh.project.baiduNuomi.fragment.SecondFragment;
import com.wxh.project.baiduNuomi.fragment.ThirdFragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;

public class MainActivity extends Activity implements OnClickListener{
	private static final String TAG = "MainActivity";
	//�ؼ����
	private Button btnHome;
	private Button btnNear;
	private Button btnMine;
	private Button btnMore; 
	//	private ListViewForScrollView lvLike;

	//Fragment���
	private FirstFragment firstFragment;
	private SecondFragment secondFragment;
	private ThirdFragment thirdFragment;
	private FourthFragment fourthFragment;
	private FragmentTransaction ft;
	private Fragment fragment;

	private long exitTime = 0;

	//	public static DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		checkNetWorkState();//�������״̬
		checkUpdate();//������
		initUI();

		//		Intent intent = getIntent();
		//		boolean IS_NET_WORKED = Boolean.parseBoolean(intent.getStringExtra(NetworkBroadcastReceiver.ACTION_NETWORK_CHECK_STATE));
		//		if(IS_NET_WORKED){
		//			checkUpdate();//������
		//			initUI();
		//		}else{
		//			Toast.makeText(this, "�뿪������", Toast.LENGTH_SHORT).show();
		//		}
	}

	/*--------------------��ʼ��--------------------*/
	private void initUI() {
		btnHome = (Button) findViewById(R.id.btn_home);
		btnHome.setOnClickListener(this);

		btnNear = (Button) findViewById(R.id.btn_near);
		btnNear.setOnClickListener(this);

		btnMine = (Button) findViewById(R.id.btn_mine);
		btnMine.setOnClickListener(this);

		btnMore = (Button) findViewById(R.id.btn_more);
		btnMore.setOnClickListener(this);

		//��ʼ��ʱ��������ʾFirstFragment
		showFirstFragment();
	}

	/*--------------------����¼�--------------------*/
	@Override
	public void onClick(View v) {
		Button[] btnArray = new Button[]{btnHome, btnNear, btnMine, btnMore};
		int home = 0, near = 1, mine = 2, more = 3;
		switch (v.getId()) {
		case R.id.btn_home:
			changeButtonState(btnArray, home);
			showFirstFragment();
			break;
		case R.id.btn_near:
			changeButtonState(btnArray, near);
			showSecondFragment();
			break;
		case R.id.btn_mine:
			changeButtonState(btnArray, mine);
			showThirdFragment();
			break;
		case R.id.btn_more:
			changeButtonState(btnArray, more);
			showFourthFragment();
			break;
		default:
			break;
		}
	}
	
	/**
	 * �ı䰴ť״̬�л�ʱ����ɫ
	 * @param buttons
	 * @param index
	 */
	private void changeButtonState(Button[] buttons, int index){
		Drawable[] highLightButton = new Drawable[]{
				getResources().getDrawable(R.drawable.tab_ic_home_highlight),
				getResources().getDrawable(R.drawable.tab_ic_nearby_highlight),
				getResources().getDrawable(R.drawable.tab_ic_mine_highlight),
				getResources().getDrawable(R.drawable.tab_ic_more_highlight)
		};
		Drawable[] normalButton = new Drawable[]{
				getResources().getDrawable(R.drawable.tab_ic_home_normal),
				getResources().getDrawable(R.drawable.tab_ic_nearby_normal),
				getResources().getDrawable(R.drawable.tab_ic_mine_normal),
				getResources().getDrawable(R.drawable.tab_ic_more_normal)
		};
		//ȫ����Ϊ����ɫ�ı�  ǳͼƬ
		for (int i = 0; i < buttons.length; i++) {
			CommonButtonHelper.changButtonTopImage(buttons[i], normalButton[i]);
//			buttons[i].setBackground(normalButton[i]);
			buttons[i].setTextColor(getResources().getColor(R.color.black2));
		}
		//���µĸ�Ϊ����
		CommonButtonHelper.changButtonTopImage(buttons[index], highLightButton[index]);
//		buttons[index].setBackground(highLightButton[index]);
		buttons[index].setTextColor(getResources().getColor(R.color.red2));
		
	}


	/*------------------------------Fragmentѡ��------------------------------*/

	/**
	 * ��ʾ��ҳ��Fragment
	 */
	public void showFirstFragment(){
		fragment = getFragmentManager().findFragmentById(R.id.fl_home);
		if(!(fragment instanceof FirstFragment)){
			if(firstFragment == null){
				firstFragment = new FirstFragment();
			}
			// ���������滻FrameLayout
			ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.fl_home, firstFragment);
			ft.commit();
		}
	}

	/**
	 * ��ʾ��2ҳ������Fragment
	 */
	public void showSecondFragment(){
		fragment = getFragmentManager().findFragmentById(R.id.fl_home);
		if(!(fragment instanceof SecondFragment)){
			if(secondFragment == null){
				secondFragment = new SecondFragment();
			}
			// ���������滻FrameLayout
			ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.fl_home, secondFragment);
			ft.commit();
		}
	}

	/**
	 * ��ʾ��3ҳ �ҵ�Fragment
	 */
	private void showThirdFragment() {
		fragment = getFragmentManager().findFragmentById(R.id.fl_home);
		if(!(fragment instanceof ThirdFragment)){
			if(thirdFragment == null){
				thirdFragment = new ThirdFragment();
			}
			ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.fl_home, thirdFragment);
			ft.commit();
		}

	}

	/**
	 * ��ʾ ����ҳ���Fragment  ��4ҳ
	 */
	private void showFourthFragment() {
		fragment = getFragmentManager().findFragmentById(R.id.fl_home);
		if(!(fragment instanceof FourthFragment)){
			if(fourthFragment == null){
				fourthFragment = new FourthFragment();
			}
			ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.fl_home, fourthFragment);
			ft.commit();
		}
	}

	/**
	 * ������
	 */
	private void checkUpdate(){
		CheckUpdate checkUpdate = new CheckUpdate(this);
		checkUpdate.check();
	}

	/**
	 * �����������״̬
	 */
	private void checkNetWorkState(){
		Intent intent = new Intent(NetworkBroadcastReceiver.ACTION_NETWORK_CHECK_STATE);
		sendBroadcast(intent);
	}

	/*------------------------------�˳�����ʱ ����һ��------------------------------*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}

	}

}
