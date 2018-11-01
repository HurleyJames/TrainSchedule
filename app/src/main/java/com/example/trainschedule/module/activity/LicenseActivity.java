package com.example.trainschedule.module.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.trainschedule.widget.adapter.LicenseAdapter;
import com.example.trainschedule.bean.License;
import com.example.trainschedule.R;
import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *      author : Hurley
 *      e-mail : 1401682479@qq.com
 *      time   : 2018/08/16
 * </pre>
 */

public class LicenseActivity extends AppCompatActivity{
    private static final String TAG = "LicenseActivity";

    @BindView(R.id.drawerLayout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.license_info)
    public RecyclerView mRvLicenseInfo;

    private List<License> licenses=new ArrayList<>();
    private LicenseAdapter mLicenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //initViews();
        ButterKnife.bind(this);

        //Toolbar转化为ActionBar
        setToolbar();

        //添加RecyclerView的数据
        dealRecyclerView(this);
    }

    public int getLayoutId() {
        return R.layout.fragment_license;
    }

    //Toolbar转化为ActionBar
    public void setToolbar(){
        //将Toolbar转化为Actionbar
        setSupportActionBar(toolbar);
        //获取ActionBar
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            //隐藏actionBar
            //actionBar.hide();
            //设置左上角的按钮图标可以点击
            actionBar.setDisplayHomeAsUpEnabled(true);
            //是否显示标题
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    //对Toolbar的菜单选项进行监听回调
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                //点击返货箭头返回上一页面
                //返回操作方法
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dealRecyclerView(Context context){
        //添加所使用的开源项目的名称和地址
        licenses.add(new License(getString(R.string.Gson),getString(R.string.gson_addr)));
        licenses.add(new License(getString(R.string.Volley),getString(R.string.volley_addr)));
        licenses.add(new License(getString(R.string.Glide),getString(R.string.glide_addr)));
        licenses.add(new License(getString(R.string.Fresco),getString(R.string.fresco_addr)));
        licenses.add(new License(getString(R.string.BufferKnife),getString(R.string.butterknife_addr)));
        licenses.add(new License(getString(R.string.PinchImageView),getString(R.string.pinchimageview_addr)));
        licenses.add(new License(getString(R.string.FlyShapeImageView),getString(R.string.flyshapeimageview_addr)));
        licenses.add(new License(getString(R.string.Y_DividerItemDecoration),getString(R.string.y_divideritemdecoration_addr)));
        licenses.add(new License(getString(R.string.Zxing),getString(R.string.zxing_addr)));

        mLicenseAdapter=new LicenseAdapter(this,licenses);
        mRvLicenseInfo.setLayoutManager(new LinearLayoutManager(this));
        //为RecyclerView添加分割线
        mRvLicenseInfo.addItemDecoration(new DividerItemDecoration(this));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //默认添加分割线方式
        //recyclerView.addItemDecoration(new RecyclerViewDivider(context,LinearLayoutManager.VERTICAL,R.drawable.a_divider_bg));
        mRvLicenseInfo.setAdapter(mLicenseAdapter);
    }

    //RecyclerView的分割线
    //使用Y_DividerItemDecoration框架
    private class DividerItemDecoration extends Y_DividerItemDecoration{

        private DividerItemDecoration(Context context){
            super(context);
        }

        @Override
        public Y_Divider getDivider(int itemPosition){
            Y_Divider divider=null;
            switch(itemPosition%2){
                case 0:
                    //每一行第一个显示right和bottom
                    divider=new Y_DividerBuilder()
                            //.setRightSideLine(true,0xff666666,10,0,0)
                            .setBottomSideLine(true,getResources().getColor(R.color.y_divider),1,0,0)
                            .create();
                    break;
                case 1:
                    //每一行第二个显示left和bottom
                    divider=new Y_DividerBuilder()
                            //.setLeftSideLine(true,0xff666666,10,0,0)
                            .setBottomSideLine(true,getResources().getColor(R.color.y_divider),1,0,0)
                            .create();
                    break;
                default:
                    break;
            }
            //第一行上方显示分割线
            if(itemPosition==0){
                divider=new Y_DividerBuilder()
                        .setTopSideLine(true,getResources().getColor(R.color.y_divider),1,0,0)
                        .setBottomSideLine(true,getResources().getColor(R.color.y_divider),1,0,0)
                        .create();
            }
            return divider;
        }
    }
}



















