package com.example.a86130.x_day02_c.view;

import com.example.a86130.x_day02_c.bean.OneBean;

import java.util.ArrayList;

/**
 * Created by 86130 on 2019/9/22.
 */

public interface OnView {
    void addData(ArrayList<OneBean.BodyBean.ResultBean> ob);
    void showToast(String str);
}
