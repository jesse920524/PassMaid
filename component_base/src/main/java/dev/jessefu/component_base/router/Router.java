package dev.jessefu.component_base.router;

import com.alibaba.android.arouter.launcher.ARouter;

import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.base.BaseFragment;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.util.GsonUtil;

import static dev.jessefu.component_base.router.RouterConstants.ModuleDetails.ACTIVITY_DETAIL_BUNDLE_KEY;

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

    /**
     * @param accountEntity 帐号实体类*/
    public void toDetailsActivity(AccountEntity accountEntity){
        mARouter.build(RouterConstants.ModuleDetails.ACTIVITY_DETAILS)
                .withString(ACTIVITY_DETAIL_BUNDLE_KEY, GsonUtil.getInstance().toJson(accountEntity))
                .navigation();
    }

    /**module entrance section*/

    /**module main section*/
    public void toMainActivity() {
        mARouter.build(RouterConstants.ModuleMain.ACTIVITY_MAIN).navigation();
    }

    /**module modify section*/

    /**module search section*/
    public void toSearchActivity(){
        mARouter.build(RouterConstants.ModuleSearch.ACTIVITY_SEARCH).navigation();
    }
}
