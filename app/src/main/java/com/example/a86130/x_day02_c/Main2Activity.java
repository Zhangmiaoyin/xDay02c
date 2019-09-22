package com.example.a86130.x_day02_c;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a86130.x_day02_c.bean.Main2Bean;
import com.example.a86130.x_day02_c.bean.OneBean;
import com.example.a86130.x_day02_c.presenter.Main2Presenter;
import com.example.a86130.x_day02_c.view.Main2View;

import java.io.Serializable;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements Main2View {

    private ArrayList<OneBean.BodyBean.ResultBean> extra;
    private ImageView mIv;
    /**
     * sdgdfgsdfgsdfgdsfg
     */
    private TextView mName;
    /**
     * sdfasdgfdgdsf
     */
    private TextView mTitle;
    /**
     * 关注
     */
    private Button mBt;
    private TabLayout mTab;
    private TextView mTv;
    private String TAG = "Main2Activity";
    private int position;
    private ArrayList<Main2Bean.BodyBean.ResultBean> main2Beans;
    private Main2Presenter main2Presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //接收intent传递过来的数据
        Intent intent = getIntent();
        position = intent.getIntExtra("222", 0);
        extra = (ArrayList<OneBean.BodyBean.ResultBean>) intent.getSerializableExtra("111");
        Log.i(TAG, "共有" + extra.size() + "條数据");
        //p层
        main2Presenter = new Main2Presenter(this);
        //解析数据 并将接口的id传递过去
        main2Presenter.getDatas(extra.get(position).getID());
        initView();

    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mName = (TextView) findViewById(R.id.name);
        mTitle = (TextView) findViewById(R.id.title);
        mTab = (TabLayout) findViewById(R.id.tab);
        mTv = (TextView) findViewById(R.id.tv);
        initData();
        main2Beans = new ArrayList<>();

    }

    private void initData() {
        //初始化控件后就将传递过来的数据  给控件赋值
        //圆形图片
        RequestOptions options = new RequestOptions().circleCrop();
        //设置数据
        Glide.with(this).load(extra.get(position).getTeacherPic()).apply(options).into(mIv);
        mName.setText(extra.get(position).getTeacherName());
        mTitle.setText(extra.get(position).getTitle());
    }

    @Override
    public void addDatas(ArrayList<Main2Bean.BodyBean.ResultBean> ex) {
        //循环接口 获取接口的长度
        for (int i = 0; i <ex.size() ; i++) {
            //给tab赋值
            mTab.addTab(mTab.newTab().setText(ex.get(i).getDescription()));
        }
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
