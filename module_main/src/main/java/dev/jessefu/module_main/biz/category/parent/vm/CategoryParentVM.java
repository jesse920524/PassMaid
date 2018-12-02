package dev.jessefu.module_main.biz.category.parent.vm;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import dev.jessefu.component_base.base.BaseViewModel;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.module_main.biz.category.parent.model.CategoryParentModel;
import io.reactivex.functions.Consumer;

public class CategoryParentVM extends BaseViewModel<CategoryParentModel> {
    private static final String TAG = "CategoryParentVM";

    private CategoryParentModel model;
    private MutableLiveData<List<CategoryEntity>> liveDataCategoryList;

    public CategoryParentVM(){
        liveDataCategoryList = new MutableLiveData<>();
        model = new CategoryParentModel();
    }

    @Override
    public void start() {
        model.getCategoryList()
                .subscribe(new Consumer<List<CategoryEntity>>() {
                    @Override
                    public void accept(List<CategoryEntity> categoryEntities) throws Exception {
                        liveDataCategoryList.setValue(categoryEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable);
//                        liveDataCategoryList.setValue(Collections.EMPTY_LIST);
                    }
                });
    }

    public MutableLiveData<List<CategoryEntity>> getLiveDataCategoryList() {
        return liveDataCategoryList;
    }
}
