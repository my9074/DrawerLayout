package com.example.drawerlayout;


import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private LinearLayout layout;
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initView() {
		// TODO Auto-generated method stub
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		layout = (LinearLayout) findViewById(R.id.include_list);
		Button button = (Button) layout.findViewById(R.id.btn_cluster);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				transaction = fragmentManager.beginTransaction();
				FragmentCluster cluster = new FragmentCluster();
				transaction.replace(R.id.content, cluster);
				transaction.commit();
				drawerLayout.closeDrawer(layout);
			}
		});

		fragmentManager = getSupportFragmentManager();
	
		
		// 设置ActionBarDrawerToggle与DrawerLayout通过setDrawerListener相关联
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.action_settings,
				R.string.hello_world) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				// getActionBar().setTitle("已打开");
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				// getActionBar().setTitle("已关闭");
				invalidateOptionsMenu();
			}
		};

		drawerLayout.setDrawerListener(drawerToggle);

		// 为ActionBar左上角图标加上一个返回箭头的图标
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// 使左上角图标可以点击
		getActionBar().setHomeButtonEnabled(true);
		// true:显示左上角图标 false:不显示图标，只显示一个标题
		getActionBar().setDisplayShowHomeEnabled(false);
	}

	/*
	 * 动态显示ActionBar时候由invalidateOptionsMenu()方法调用
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = drawerLayout.isDrawerOpen(layout);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/*
	 * 不常用的Activity生命周期方法。在start()和onRestoreInstanceState()后执行。
	 * 用于Activity初始化完成后最后执行的方法
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
