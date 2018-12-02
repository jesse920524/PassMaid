package dev.jessefu.component_base.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.greendao.database.Database;

import dev.jessefu.component_base.BuildConfig;
import dev.jessefu.component_base.db.DaoMaster;
import dev.jessefu.component_base.db.DaoSession;

public class BaseApp extends Application {
    private static final String TAG = "BaseApp";

    private static Context mContext;

    private static DaoSession mDaoSession;

    public static Context getContext(){
        return mContext;
    }

    public static DaoSession getDaoSession(){
        return mDaoSession;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex分包方法 必须最先初始化
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initArouter();
        initGreenDao();

    }

    private void initArouter() {
        if (BuildConfig.DEBUG){
            ARouter.openDebug();
            ARouter.openLog();
        }

        ARouter.init(this);
    }

    private void initGreenDao(){
        try {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "passmaid-db");
            Database database = devOpenHelper.getWritableDb();
            mDaoSession = new DaoMaster(database).newSession();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
