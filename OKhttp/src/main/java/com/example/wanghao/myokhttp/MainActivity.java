package com.example.wanghao.myokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.wanghao.myokhttp.moudel.Happy_carModel;
import com.example.wanghao.myokhttp.network.BaseCallback;
import com.example.wanghao.myokhttp.network.SimpleHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public String fileName ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String url ="http://app.ujiacity.com/index.php?m=home&c=Api&a=carlist&isCar=1&page=1";


    }

    public void getRequest(View v) {

        // testOne();

        // testTwo();

        // testThree();

        // testFour();

        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        OkHttpClient client = new OkHttpClient();
        String url= "http://openbox.mobilem.360.cn/index/d/sid/3236875";



        RequestBody body = new FormBody.Builder().add("isCar", 1 + "").add("page", 1 + "").build();
        Request request = new Request.Builder().url(url).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    String json = response.body().string();
                    Log.d("ManiActivity", "成功" + json);
                }
            }
        });

    }

    private void testFour() {
        String url = "http://app.ujiacity.com/index.php?m=home&c=Api&a=carlist";
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("isCar", 1 + "");
            jsonObj.put("page", 1 + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonParams = jsonObj.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    String json = response.body().string();
                    Log.d("ManiActivity", "成功" + json);
                }
            }
        });
    }

    private void testThree() {
        String url = "http://app.ujiacity.com/index.php?m=home&c=Api&a=carlist";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder().add("isCar", 1 + "").add("page", 1 + "").build();
        Request request = new Request.Builder().url(url).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    String json = response.body().string();
                    Log.d("ManiActivity", "成功" + json);
                }
            }
        });
    }


    private void testOne() {
        String url = "http://app.ujiacity.com/index.php?m=home&c=Api&a=carlist&isCar=1&page=1";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("ManiActivity", "失败————" + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                Log.d("ManiActivity", "成功" + result);

                if (response.body() != null) {
                    response.body().close();
                }
            }
        });
    }

    private void testTwo() {
        String url = "http://app.ujiacity.com/index.php?m=home&c=Api&a=carlist";
        SimpleHttpClient.
                newBuilder().
                addParam("isCar", 1).
                addParam("page", 1).
                get().url(url).
                build()
                .enqueue(new BaseCallback<Happy_carModel>() {
                    @Override
                    public void onSuccess(Happy_carModel baseResult) {
                        Log.d("ManiActivity", "成功" + baseResult.getReturn_code());
                    }

                    @Override
                    public void onError(int codes) {
                        Log.d("ManiActivity", "错误" + codes + "");
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("ManiActivity", "失败" + e.getLocalizedMessage());
                    }
                });
    }

}
