package dev.jessefu.module_main.biz.main;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import butterknife.BindView;
import butterknife.OnClick;
import dev.jessefu.component_base.base.BaseActivity;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.enums.Category;
import dev.jessefu.component_base.router.Router;
import dev.jessefu.component_base.router.RouterConstants;
import dev.jessefu.module_main.R;
import dev.jessefu.module_main.R2;
import dev.jessefu.module_main.adapter.AHViewPagerAdapter;

@Route(path = RouterConstants.ModuleMain.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R2.id.tb_main)
    Toolbar mToolbar;
    @BindView(R2.id.tv_main_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_main_search)
    ImageView mIvSearch;
    @BindView(R2.id.fab_main)
    FloatingActionButton mFab;
    @BindView(R2.id.bn_main)
    AHBottomNavigation mBottomNav;
    @BindView(R2.id.vp_main)
    AHBottomNavigationViewPager mViewPager;
    @BindView(R2.id.tab_layout_main)
    TabLayout mTabLayout;
    private AHViewPagerAdapter mViewPagerAdapter;
    private AHBottomNavigationAdapter mBottomNavAdapter;
    private Fragment mCurrentFragment;

    private int mLastSelectedPos = 0;//记录上次选中的fragment position

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void initViews() {
        initToolbar();
        initBottomNav();
        initViewPager();
        initTabLayout();
        showFabAnim(1);
        setTabLayoutVisibility(false);

    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("全部"));
        for (Category category :
                Category.values()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(category.getName()));
        }
    }

    @Override
    protected void initData() {

    }

    private void initViewPager() {
        mViewPager.setPagingEnabled(false);//禁止滑动
        mViewPager.setOffscreenPageLimit(4);
        mViewPagerAdapter = new AHViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mCurrentFragment = mViewPagerAdapter.getCurrentFragment();

    }

    private void initBottomNav() {
        mBottomNavAdapter = new AHBottomNavigationAdapter(this, R.menu.main_menu_bottom_nav);
        mBottomNavAdapter.setupWithBottomNavigation(mBottomNav);

        mBottomNav.setAccentColor(ActivityCompat.getColor(this, R.color.colorAccent));
        //manage titles
        mBottomNav.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        //translucent Navigation bar
        mBottomNav.setTranslucentNavigationEnabled(true);
        mBottomNav.setSelectedBackgroundVisible(true);
        mBottomNav.manageFloatingActionButtonBehavior(mFab);
        mBottomNav.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            private int posDifference = 0;
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Log.d(TAG, "onTabSelected: position: " + position + " wasselected: " + wasSelected);
                mViewPager.setCurrentItem(position, false);
                mCurrentFragment = mViewPagerAdapter.getCurrentFragment();
                posDifference = position - mLastSelectedPos;
                switch (position){
                    case 0://starFragment
                        setFabVisibility(true);
                        showFabAnim(posDifference);
                        setTabLayoutVisibility(false);
                        break;
                    case 1://categoryFragment
                        setFabVisibility(true);
                        showFabAnim(posDifference);
                        setTabLayoutVisibility(true);
                        break;
                    case 2://settingFragment
                        setFabVisibility(false);
                        setTabLayoutVisibility(false);
                        break;
                        default:
                            throw new IllegalStateException("invalid position");
                }
                mLastSelectedPos = position;
                return true;
            }
        });
    }

    /**根据当前显示的fragment,控制FloatingActionButton的show/hide status
     * @param visible */
    @SuppressLint("RestrictedApi")
    private void setFabVisibility(boolean visible) {
        mFab.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void initToolbar() {
        mTvTitle.setText(R.string.app_name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @OnClick(R2.id.iv_main_search)
    public void onClickSearch(View view){
        //navigate 2 searchActivity
        Router.INSTANCE.toSearchActivity();
    }

    @OnClick(R2.id.fab_main)
    public void onClickFab(View view){
        /**test data*/
        AccountEntity entity = new AccountEntity();
        entity.setAccount("qq帐号");
        entity.setCategory(Category.SOCIAL.getName());
        entity.setAccount("276883319");
        entity.setPassword("fantasy920524");
        entity.setDescription("qq帐号,2005注册");
        entity.setIsStar(true);

        Router.INSTANCE.toDetailsActivity(entity);
    }

    /**
     * @param posDifference 位置差异.
     *  >0时, 动画正方向播放
     *  <0时, 动画反方向播放
     *  等于0时,不播放动画
     * */
    private void showFabAnim(int posDifference){
        // TODO: 2018-09-10 这方法的api十分之不友好,将会重构
        float dstDegree = 0.0f;
        if (posDifference > 0){//正向播放
            dstDegree = 270f;
        }else if (posDifference < 0){
            dstDegree = -270f;
        }

         ObjectAnimator.ofFloat(mFab, "rotation", 0, dstDegree)
                .setDuration(500)
                .start();
    }

    private void setTabLayoutVisibility(boolean visible){
        mTabLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


}
