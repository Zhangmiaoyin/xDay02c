package com.example.a86130.x_day02_c.model;

import android.util.Log;

import com.example.a86130.x_day02_c.ApiService;
import com.example.a86130.x_day02_c.bean.OneBean;
import com.example.a86130.x_day02_c.presenter.OnPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 86130 on 2019/9/22.
 */

public class OnModel {
    public void addDatas(final OnCallBack onCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.utl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<OneBean> geturl = apiService.geturl();
        geturl.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OneBean oneBean) {
                    if(oneBean!=null&&oneBean.getBody()!=null&&oneBean.getBody().getResult()!=null){
                        ArrayList<OneBean.BodyBean.ResultBean> result = (ArrayList<OneBean.BodyBean.ResultBean>) oneBean.getBody().getResult();
                        Log.i("result",result.toString());
                        onCallBack.onSuccess(result);
                    }else{
                        onCallBack.onFali("数据为空");
                    }
                    }

                    @Override
                    public void onError(Throwable e) {
                       onCallBack.onFali("请求数据异常");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface  OnCallBack{
        void onSuccess(ArrayList<OneBean.BodyBean.ResultBean> ob);
        void onFali(String str);
    }
}
