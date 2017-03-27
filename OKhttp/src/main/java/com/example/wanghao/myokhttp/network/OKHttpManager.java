package com.example.wanghao.myokhttp.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by WangHao
 *
 * @ 创建时间 2017/3/27  9:42
 */

public class OKHttpManager {

    private static OKHttpManager mInstance;

    private OkHttpClient mOkHttpClient;

    private Handler mHandler;

    private Gson mGson;

    private OKHttpManager() {

        initOKHttp();

        mHandler = new Handler(Looper.getMainLooper()){};
        mGson = new Gson();

    }

    public static synchronized OKHttpManager getmInstance() {

        if (mInstance == null) {
            mInstance = new OKHttpManager();
        }


        return mInstance;
    }


    private void initOKHttp() {

        mOkHttpClient = new OkHttpClient().newBuilder().readTimeout(30000, TimeUnit.SECONDS)
                .connectTimeout(30000, TimeUnit.SECONDS).build();
    }


    public void request(SimpleHttpClient client, final BaseCallback callback) {


        if (callback == null) {
            throw new NullPointerException("callback is null");
        }


        mOkHttpClient.newCall(client.buildRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                sendonFailureMessage(callback,call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                if (response.isSuccessful()){

                    String result = response.body().string();
                    Log.d("ManiActivity","成功"+ result);

                    if (callback.mType == null || callback.mType == String.class){

                        sendOnSuccessMessage(callback,result);

                    }else {
                        try {
                            sendOnSuccessMessage(callback,  mGson.fromJson(result,callback.mType));
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }


                    if (response.body() != null){
                        response.body().close();
                    }

                }else{

                    sendOnErrorMessage(callback,response.code());
                }
            }


        });
    }

    private void sendOnSuccessMessage(final BaseCallback callback, final Object result) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(result);
               // Log.d("ManiActivity","成功"+ result);
            }
        });
    }


    private void sendOnErrorMessage(final BaseCallback callback, final int code) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError( code);
            }
        });


    }

    private void sendonFailureMessage(final BaseCallback callback, final Call call, final IOException e) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(call, e);
            }
        });

    }


}
