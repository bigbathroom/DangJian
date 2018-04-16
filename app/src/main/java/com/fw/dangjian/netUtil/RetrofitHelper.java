package com.fw.dangjian.netUtil;

import android.util.Log;
import com.fw.dangjian.MyApplication;
import com.fw.dangjian.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/7.
 */

public class RetrofitHelper {
    public static RetrofitHelper retrofitHelper;
    public static Retrofit retrofit;

    public static final String key= "GLwIRjUyRHCbK8wtcOtZJ3flAfTgPkKEGXRI9v8wvcWXd//ZWzn1n3=H+4UAMsE==+9+pjuawFrCoqqRS4DmDC2I4Xy2+Oxqqw7VWb8fgaxXsv3a/AhPG1jEUDQHYYD7F5NrfnkIu+jloVRA03Hc50V8En4BbE/XxOgASVc+RJYqG0ySP/JdjYZouRYfJpS4luUDU1F42r21e5Ah5fiPhjqKL5EsfyT28v5NE0BYEWxC3vVSakmFQbZvvQM1P5HpoiEzg3WEtWB=k0OUQcFuHg0EPXPY0EyFYQQmF8AfEwepPtzk13XghQji8mqzqNCChNWb38c7VDes/cPBhMfzAFzmSg66";

    public  String timeString;
    private RetrofitHelper() {
        //进行retrofit的初始化
        initRetrofit();
    }

    private void initRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);//超时时间15S
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                timeString = StringUtils.getTimeString();

                Request request = chain.request()
                        .newBuilder()
                        .addHeader("timestamp", timeString)
                        .addHeader("assetionkey", StringUtils.getBase64(key+timeString))
                        .build();
                return chain.proceed(request);
            }
        });

        //应用程序拦截器
       /* builder.addInterceptor(new ApplicationCacheIntercept());
        builder.addNetworkInterceptor(netInterceptor);//网络拦截器
        builder.cache(setCache());//添加缓存*/

        OkHttpClient okHttpClient = builder.build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitHelper getRetrofitHelper() {
        if (retrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitHelper == null) {
                    retrofitHelper = new RetrofitHelper();
                }
            }
        }
        return retrofitHelper;
    }

    /**
     * 设置通用的返回方法
     *
     * @param reqServer
     * @param <T>
     * @return
     */
    public <T> T createReq(Class<T> reqServer) {
        return retrofit.create(reqServer);
    }

    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


    private Cache setCache() {
        //设置缓存
        File cachFile = new File(MyApplication.getContext().getCacheDir(), "cache_base");
        Cache cache = null;
        try {
            cache = new Cache(cachFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }
        return cache;
    }

    /**
     * 一、无论有无网路都添加缓存。
     * 目前的情况是我们这个要addNetworkInterceptor
     * 这样才有效。经过本人测试（chan）测试有效.
     * 60S后如果没有网络将获取不到数据，显示连接失败
     */
    static Interceptor netInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
          /*String cacheControl = request.header("Cache-Control");
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=60";
            }*/
            int maxAge = 60;
            return response.newBuilder()
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }
    };

}
