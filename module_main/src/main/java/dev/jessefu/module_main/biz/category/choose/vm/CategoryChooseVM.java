package dev.jessefu.module_main.biz.category.choose.vm;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import dev.jessefu.component_base.base.BaseApp;
import dev.jessefu.component_base.base.BaseViewModel;
import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.module_main.biz.category.choose.model.CategoryChooseModel;
import io.reactivex.functions.Consumer;

public class CategoryChooseVM extends BaseViewModel<CategoryChooseModel> {
    private static final String TAG = "CategoryChooseVM";

    private CategoryChooseModel model;

    private MutableLiveData<List<CategoryEntity>> liveDataCategoryList;

    public CategoryChooseVM(){
        model = new CategoryChooseModel();
        liveDataCategoryList = new MutableLiveData<>();

    }

    @Override
    public void start() {
        getCategoryList();
    }

    private void getCategoryList(){
        model.getCategoryList()
                .subscribe(new Consumer<List<CategoryEntity>>() {
                    @Override
                    public void accept(List<CategoryEntity> categoryEntities) throws Exception {
                        liveDataCategoryList.setValue(categoryEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable.getLocalizedMessage());
                    }
                });
    }

    public void addCategory(String categoryName){
        model.insert(categoryName)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        CategoryEntity categoryEntity = new CategoryEntity();
                        categoryEntity.setName(s);

                        List<CategoryEntity> list = liveDataCategoryList.getValue();
                        list.add(categoryEntity);
                        liveDataCategoryList.setValue(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(BaseApp.getContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void removeCategory(String categoryName){
        model.deleteCategory(categoryName)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        // TODO: 2018-12-11 handle delete success
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // TODO: 2018-12-11 handle delete fail
                    }
                });
    }

    public MutableLiveData<List<CategoryEntity>> getLiveDataCategoryList() {
        return liveDataCategoryList;
    }


}
