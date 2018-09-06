package dev.jessefu.component_base.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.greenrobot.greendao.database.Database;

import dev.jessefu.component_base.db.DaoMaster;
import dev.jessefu.component_base.db.DaoSession;

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";

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
        mContext = getApplicationContext();
        //MultiDex分包方法 必须最先初始化
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();

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
