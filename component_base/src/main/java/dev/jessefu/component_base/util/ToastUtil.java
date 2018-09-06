package dev.jessefu.component_base.util;

import android.content.Context;
import android.widget.Toast;

import dev.jessefu.component_base.base.BaseApplication;

public final class ToastUtil {
    private static final String TAG = "ToastUtil";

    private static Toast mToast;

    private static final Context mContext = BaseApplication.getContext();

    private ToastUtil(){
        throw new AssertionError("could not instantiate this class");
    }

    public static void showShort(String msg){
        if (mToast == null){
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLong(String msg){
        if (mToast == null){
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        }else{
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
    }

    public static void cancelToast(){
        if (mToast != null){
            mToast.cancel();
        }
    }




}
