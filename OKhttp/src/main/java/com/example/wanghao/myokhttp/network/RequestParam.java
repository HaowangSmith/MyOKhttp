package com.example.wanghao.myokhttp.network;

/**
 * Created by wanghao on 2017/3/27.
 */

public class RequestParam {

    private String key;

    private Object obj;

    public RequestParam(String key, Object obj) {
        this.key = key;
        this.obj = obj;
    }

    public String getKey() {
        return key;
    }

    public Object getObj() {
        return obj;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
