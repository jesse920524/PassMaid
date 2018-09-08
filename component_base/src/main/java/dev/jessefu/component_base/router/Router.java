package dev.jessefu.component_base.router;

import com.alibaba.android.arouter.launcher.ARouter;

import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.base.BaseFragment;

public enum Router {

    INSTANCE;


    private ARouter mARouter = ARouter.getInstance();


    public void inject(BaseActivity activity){
        mARouter.inject(activity);
    }

    public void inject(BaseFragment fragment){
        mARouter.inject(fragment);
    }

    /**module about section*/
    public void toAboutActivity(){
        mARouter.build(RouterConstants.ModuleAbout.ACTIVITY_ABOUT).navigation();
    }

    /**module dtails section*/

    /**module entrance section*/

    /**module main section*/

    /**module modify section*/

    /**module search section*/
    public void toSearchActivity(){
        mARouter.build(RouterConstants.ModuleSearch.ACTIVITY_SEARCH).navigation();
    }
}
