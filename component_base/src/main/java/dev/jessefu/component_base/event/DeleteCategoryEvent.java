package dev.jessefu.component_base.event;

/**delete category event.
 * from: LongClickCategoryDialog -> CategoryChooseActivity
 * */
public class DeleteCategoryEvent extends BaseEvent{
    private static final String TAG = "DeleteCategoryEvent";

    public static DeleteCategoryEvent newInstance(String name){
        return new DeleteCategoryEvent(name);
    }

    protected DeleteCategoryEvent(String message) {
        super(message);
    }
}
