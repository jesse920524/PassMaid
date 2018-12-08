package dev.jessefu.component_base.event;
/**
 * 添加分类事件
 * 由AddCategoryDialog -> CategoryChooseActivity
 * */
public class AddCategoryEvent extends BaseEvent {

    protected AddCategoryEvent(String message) {
        super(message);
    }

    /**@param name categoryName*/
    public static AddCategoryEvent newInstance(String name){
        return new AddCategoryEvent(name);
    }
}
