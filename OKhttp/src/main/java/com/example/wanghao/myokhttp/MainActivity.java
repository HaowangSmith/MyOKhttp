package com.example.wanghao.myokhttp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wanghao.myokhttp.moudel.Happy_carModel;
import com.example.wanghao.myokhttp.network.BaseCallback;
import com.example.wanghao.myokhttp.network.SimpleHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public String fileName = "jiaCheng";
    private ProgressBar mMProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String url ="http://app.ujiacity.com/index.php?m=home&c=Api&a=carlist&isCar=1&page=1";
        mMProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        requestPermission();
    }
    public static final int EXTERNAL_STORAGE_REQ_CODE = 10;

    public  void requestPermission(){
        //判断当前Activity是否已经获得了该权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            //如果App的权限申请已经被用户拒绝过，就需要在这里跟用户做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this,"please give me the permission",Toast.LENGTH_SHORT);
            }else {
                //进行权限请求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        EXTERNAL_STORAGE_REQ_CODE);
            }
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){

        switch (requestCode){
            case EXTERNAL_STORAGE_REQ_CODE:{
                //如果请求被拒绝，那么通常grantResults数组为空
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //申请成功

                    Toast.makeText(MainActivity.this,"已获得权限",Toast.LENGTH_SHORT);
                }else {
                    //申请失败，可以继续向用户解释
                }
                return;
            }
        }
    }

    public void getRequest(View v) {

        // testOne();

        // testTwo();

        // testThree();

        // testFour();


        testFive();

    }

    private void testFive() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://openbox.mobilem.360.cn/index/d/sid/3236875";

        Request request = new Request.Builder().url(url).get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("ManiActivity", "请求文件出错");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                writeFile(response);
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {

                int progress = msg.arg1;
                //Log.d("ManiActivity", "progress" + progress);
                mMProgressBar.setProgress(progress);
            }
        }
    };

    private void writeFile(Response response) {

        InputStream is = null;
        FileOutputStream fos = null;

        is = response.body().byteStream();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        Log.d("ManiActivity", "path" + path);

        File file = new File(path, fileName);
        try {

            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            long totalSize = response.body().contentLength();
            long sum = 0;

            while ((len = is.read(bytes)) != -1) {

                fos.write(bytes);

                sum += len;
                int progress = (int) ((sum * 1.0f / totalSize) * 100);
               // Log.d("ManiActivity", "progress" + progress);

                Message msg = mHandler.obtainMessage(1);
                msg.arg1 = progress;
                mHandler.sendMessage(msg);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }

        }
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
