package dev.jessefu.module_main.biz.category.child.vm;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import dev.jessefu.component_base.base.BaseViewModel;
import dev.jessefu.component_base.db.entity.AccountEntity;
import dev.jessefu.component_base.util.RxTransformer;
import dev.jessefu.module_main.biz.category.child.model.CategoryChildModel;
import io.reactivex.functions.Consumer;

public class CategoryChildVM extends BaseViewModel<CategoryChildModel> {
    private static final String TAG = "CategoryChildVM";

    private String category;//类别

    private MutableLiveData<List<AccountEntity>> liveDataAccountEntities;

    public CategoryChildVM(){
        model = new CategoryChildModel();
        liveDataAccountEntities = new MutableLiveData<>();
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void start() {
        queryDataFromDB(category);
    }

    public void queryDataFromDB(String category){
        model.queryDataFromDB(category)
                .compose(RxTransformer.switchSchedulers())
                .subscribe(new Consumer<List<AccountEntity>>() {
                    @Override
                    public void accept(List<AccountEntity> accountEntities) throws Exception {
                        liveDataAccountEntities.setValue(accountEntities);
                    }
                });
    }

    public MutableLiveData<List<AccountEntity>> getLiveDataAccountEntities() {
        return liveDataAccountEntities;
    }
}
