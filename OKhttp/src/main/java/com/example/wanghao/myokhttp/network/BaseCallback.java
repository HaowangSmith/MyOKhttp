package com.example.wanghao.myokhttp.network;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by wanghao on 2017/3/27.
 */

public class BaseCallback<T> {

    public Type mType;
    //GSon 工具类
    static  Type getSuperclassTypeParameter(Class<?> subclass){
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof  Class){

            return null;
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }



    public BaseCallback(){

        mType = getSuperclassTypeParameter(this.getClass());
    }

    public void onSuccess(T t){};

    public void onError(int codes){};

    public void onFailure(Call call, IOException e){};

}
