package dev.jessefu.module_main.biz.category.choose.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import dev.jessefu.component_base.db.entity.CategoryEntity;
import dev.jessefu.module_main.R;

public class RvCategoryChooseAdapter extends BaseQuickAdapter<CategoryEntity, BaseViewHolder> {

    public RvCategoryChooseAdapter(){
        this(R.layout.main_item_rv_category_choose);
    }

    public RvCategoryChooseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryEntity item) {

    }
}
