package com.example.a86130.x_day02_c;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.a86130.x_day02_c.bean.OneBean;
import com.example.a86130.x_day02_c.model.OnModel;
import com.example.a86130.x_day02_c.presenter.OnPresenter;
import com.example.a86130.x_day02_c.view.OnView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnView {

    private Toolbar mToolbar;
    private RecyclerView mRecycler;
    private ArrayList<OneBean.BodyBean.ResultBean> resultBeans;
    private OneAdapter oneAdapter;
    private OnPresenter onPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onPresenter = new OnPresenter(this);
        onPresenter.addDatas();//请求数据的方法
        initView();
        initrecy();
    }
     //初始化Recycler
    private void initrecy() {
        //管理器
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        //数据源
        resultBeans = new ArrayList<>();
        //适配器
        oneAdapter = new OneAdapter(resultBeans,this);
        //设置适配器
        mRecycler.setAdapter(oneAdapter);
        //接口回调
        oneAdapter.setOnclick(new OneAdapter.Onclick() {
            @Override
            public void onclick(int position) {
                //intent传值
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                //将要传递的对象序列化
                bundle.putSerializable("111",resultBeans);
                intent.putExtras(bundle);
                //并将下标传递过去
                intent.putExtra("222",position);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        //toolbar 标题 支持   标题居中
        mToolbar.setTitle("名师推荐");
        setSupportActionBar(mToolbar);
        mToolbar.setTitleMarginStart(400);
    }

    @Override   //请求数据的方法
    public void addData(ArrayList<OneBean.BodyBean.ResultBean> ob) {
        resultBeans.addAll(ob);
        oneAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
