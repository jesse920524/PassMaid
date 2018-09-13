package dev.jessefu.component_base.base;

import android.arch.lifecycle.ViewModel;

public abstract class BaseViewModel<T extends BaseModel> extends ViewModel {

    protected T model;

    public abstract void start();

}
