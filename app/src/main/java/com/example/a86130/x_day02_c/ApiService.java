package com.example.a86130.x_day02_c;

import com.example.a86130.x_day02_c.bean.Main2Bean;
import com.example.a86130.x_day02_c.bean.OneBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 86130 on 2019/9/22.
 */

public interface ApiService {
    String utl="https://api.yunxuekeji.cn/yunxue_app_api/";
    @GET("content/getData/30/66597/1/10")
    Observable<OneBean> geturl();
    //ID替换成第一个接口中获取得到的ID
    String url1="https://api.yunxuekeji.cn/yunxue_app_api/";
    @GET("teacher/getTeacherPower/{ID}")
    Observable<Main2Bean> geturl1(@Path("ID") int id);

}
