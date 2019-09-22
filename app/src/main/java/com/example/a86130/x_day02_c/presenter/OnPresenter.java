package com.example.a86130.x_day02_c.presenter;

import com.example.a86130.x_day02_c.bean.OneBean;
import com.example.a86130.x_day02_c.model.OnModel;
import com.example.a86130.x_day02_c.view.OnView;

import java.util.ArrayList;

/**
 * Created by 86130 on 2019/9/22.
 */

public class OnPresenter implements OnModel.OnCallBack{
    private OnView onView;
    private OnModel onModel;
    public OnPresenter(OnView onView) {
        this.onView = onView;
        this.onModel=new OnModel();
    }

    @Override
    public void onSuccess(ArrayList<OneBean.BodyBean.ResultBean> ob) {
        onView.addData(ob);
    }

    @Override
    public void onFali(String str) {
    onView.showToast(str);
    }

    public void addDatas() {
        onModel.addDatas(this);
    }
}
