package com.minminaya.bogemusic.finder.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;
import com.minminaya.bogemusic.finder.adapter.TabFragmentAdapter;
import com.minminaya.bogemusic.finder.fragment.FinderFragment;
import com.minminaya.bogemusic.finder.fragment.MineFragment;
import com.minminaya.bogemusic.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 主页Activity
 */
public class FinderActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.content_finder)
    RelativeLayout contentFinder;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.nav_view)
    NavigationView mNavgationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.btn_setting)
    Button btnSetting;
    @Bind(R.id.tablayout)
    TabLayout mTablayout;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public void initView(Bundle savedInstanceState) {
        PermissionUtils.checkPermission(this);


        mTablayout.addTab(mTablayout.newTab().setText("发现"));
        mTablayout.addTab(mTablayout.newTab().setText("我的"));


        //初始化TabLayout
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(FinderFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(tabFragmentAdapter);
    }

    @Override
    public void setListeners() {

//        mDrawerLayout.openDrawer(GravityCompat.START);
        mNavgationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void bind() {
        mTablayout.setupWithViewPager(viewpager);
    }

    @Override
    public void unBind() {

    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @OnClick({R.id.fab, R.id.nav_view, R.id.btn_setting, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.nav_view:
                break;
            case R.id.btn_setting:
                break;
            case R.id.btn_search:
                break;
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_finder;
    }

}
