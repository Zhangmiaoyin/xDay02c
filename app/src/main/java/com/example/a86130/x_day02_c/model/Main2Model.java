package com.example.a86130.x_day02_c.model;

import com.example.a86130.x_day02_c.ApiService;
import com.example.a86130.x_day02_c.bean.Main2Bean;
import com.example.a86130.x_day02_c.bean.OneBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 86130 on 2019/9/22.
 */

public class Main2Model {
    public void getDatas(final Main2CallBack main2CallBack, int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<Main2Bean> observable = apiService.geturl1(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Main2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Main2Bean main2Bean) {
                        if(main2Bean!=null&&main2Bean.getBody()!=null&&main2Bean.getBody().getResult()!=null) {
                            ArrayList<Main2Bean.BodyBean.ResultBean> result = (ArrayList<Main2Bean.BodyBean.ResultBean>) main2Bean.getBody().getResult();
                            main2CallBack.onSuccess(result);
                        }else{
                            main2CallBack.onFali("没有数据");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        main2CallBack.onFali("数据请求异常");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface Main2CallBack{
        void onSuccess(ArrayList<Main2Bean.BodyBean.ResultBean> ex);
        void onFali(String  str);
    }
}
