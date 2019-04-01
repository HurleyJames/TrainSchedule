package com.example.trainschedule.core.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.trainschedule.R;
import com.example.trainschedule.base.BaseActivity;
import com.example.trainschedule.core.fragment.DefaultFragment;
import com.example.trainschedule.utils.ViewPagerUtils;
import com.example.trainschedule.core.fragment.StationFragment;
import com.example.trainschedule.core.fragment.TrainFragment;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.drawerLayout)
    public DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    public NavigationView navigationView;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.viewPager)
    public ViewPagerUtils viewPager;

    private long exitTime = 0;

    private int option = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //监听ViewPager
        viewPagerListener();
        //监听NavigationView
        navigationViewListener();
    }

    /**
     * 监听ViewPager
     */
    private void viewPagerListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动页面后的操作
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //添加Fragment
        final ArrayList<Fragment> fragments = new ArrayList<>();
        //添加默认Fragment
        fragments.add(new DefaultFragment());
        //添加站站Fragment
        fragments.add(new StationFragment());
        //添加车次Fragment
        fragments.add(new TrainFragment());

        //设置适配器用于装载Fragment
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //得到Fragment
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                //得到数量
                return fragments.size();
            }
        };

        //设置装配器
        viewPager.setAdapter(pagerAdapter);
        //预加载剩下两页
        viewPager.setOffscreenPageLimit(2);
    }

    /**
     * 监听NavigationView
     */
    private void navigationViewListener() {
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(
                    //设置当导航栏被点击时的回调
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                //根据item进行选择
                                case R.id.default_fragment:
                                    //改变item选中状态
                                    item.setChecked(true);
                                    //跳转到相应的ViewPager
                                    //首页默认对应0
                                    viewPager.setCurrentItem(0);
                                    viewPager.setSlide(false);
                                    //关闭导航栏菜单
                                    drawerLayout.closeDrawers();
                                    break;
                                case R.id.station_query:
                                    //改变item选中状态
                                    item.setChecked(true);
                                    //跳转到相应的ViewPager
                                    //站站查询对应1
                                    viewPager.setCurrentItem(1);
                                    //禁止ViewPager左右滑动
                                    viewPager.setSlide(false);
                                    toolbar.setTitle(getString(R.string.station_query));
                                    //关闭导航栏菜单
                                    drawerLayout.closeDrawers();
                                    break;
                                case R.id.train_query:
                                    //改变item选中状态
                                    item.setChecked(true);
                                    //跳转到相应的ViewPager
                                    //车次查询对应2
                                    viewPager.setCurrentItem(2);
                                    //禁止ViewPager左右滑动
                                    viewPager.setSlide(false);
                                    toolbar.setTitle(getString(R.string.train_query));
                                    //关闭导航栏菜单
                                    drawerLayout.closeDrawers();
                                    break;
                                /*case R.id.ticket_manage:
                                    //改变item选中状态
                                    item.setChecked(true);
                                    //跳转到相应的ViewPager
                                    //车票管理对应3
                                    viewPager.setCurrentItem(3);
                                    //禁止ViewPager左右滑动
                                    viewPager.setSlide(false);
                                    //关闭导航栏菜单
                                    drawerLayout.closeDrawers();
                                    break;

                                    //关闭导航栏菜单
                                    drawerLayout.closeDrawers();
                                    Intent intent1=new Intent();
                                    intent1.setClass(MainActivity.this,TicketActivity.class);
                                    MainActivity.this.startActivity(intent1);
                                    break;
                                */
                                case R.id.about:
                                    //关闭导航栏菜单
                                    drawerLayout.closeDrawers();
                                    new LibsBuilder()
                                            .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                                            .withActivityTitle(getString(R.string.about))
                                            .withAboutAppName(getString(R.string.app_name))
                                            .withAboutDescription(getString(R.string.app_func))
                                            .withAboutIconShown(true)
                                            .withAboutVersionShown(true)
                                            .start(getApplicationContext());
                                    break;
                                case R.id.support:
                                    //关闭导航栏菜单
                                    drawerLayout.closeDrawers();
                                    Intent intent4 = new Intent();
                                    intent4.setClass(MainActivity.this, SupportActivity.class);
                                    MainActivity.this.startActivity(intent4);
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    }
            );
        }
    }

    /**
     * 监听NavigationView
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit(this);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 按两次返回键退出程序
     * @param context
     */
    private void exit(Context context) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(context, getString(R.string.exit), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}





















