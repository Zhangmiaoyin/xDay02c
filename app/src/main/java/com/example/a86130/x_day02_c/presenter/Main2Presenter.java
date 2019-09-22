package com.example.a86130.x_day02_c.presenter;

import com.example.a86130.x_day02_c.bean.Main2Bean;
import com.example.a86130.x_day02_c.model.Main2Model;
import com.example.a86130.x_day02_c.view.Main2View;

import java.util.ArrayList;

/**
 * Created by 86130 on 2019/9/22.
 */

public class Main2Presenter implements Main2Model.Main2CallBack{
    private Main2View main2View;
   private Main2Model main2Model;
    public Main2Presenter(Main2View main2View) {
        this.main2View = main2View;
        main2Model=new Main2Model();
    }
    @Override
    public void onSuccess(ArrayList<Main2Bean.BodyBean.ResultBean> ex) {
        main2View.addDatas(ex);
    }

    @Override
    public void onFali(String str) {
        main2View.showToast(str);
    }

    public void  getDatas(int id) {
        main2Model.getDatas(this,id);
    }
}
