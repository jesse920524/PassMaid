package dev.jessefu.component_base.json;

import com.google.gson.Gson;

public class GsonFactory {

    private static volatile GsonFactory INSTANCE;

    private static Gson mGson;
    static {
        mGson = new Gson();
    }

    private GsonFactory(){

    }

    public static Gson get(){
        return mGson;
    }

}
