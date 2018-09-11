package dev.jessefu.component_base.util;

import android.animation.ObjectAnimator;

import com.google.gson.Gson;

public class GsonUtil {
    private static final String TAG = "GsonUtil";

    private Gson mGson;

    private static GsonUtil mInstance;

    private GsonUtil(){
        mGson = new Gson();
    }

    public static GsonUtil getInstance(){
        if (mInstance == null){
            synchronized (GsonUtil.class){
                if (mInstance == null){
                    mInstance = new GsonUtil();
                }
            }
        }
        return mInstance;
    }

    public <T> String toJson(T obj){
        return mGson.toJson(obj);
    }

    public <T> T fromJson(String json, Class<T> clazz){
        T t = mGson.fromJson(json, clazz);
        return t;
    }

}
