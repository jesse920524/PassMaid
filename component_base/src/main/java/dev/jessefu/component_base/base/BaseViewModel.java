package dev.jessefu.component_base.base;

import android.arch.lifecycle.ViewModel;

public class BaseViewModel<T extends BaseModel> extends ViewModel {

    private T model;


}
