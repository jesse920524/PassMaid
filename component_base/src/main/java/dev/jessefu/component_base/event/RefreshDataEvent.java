package dev.jessefu.component_base.event;

/**刷新数据Event
 * ModifyActivity新增或修改数据后, 回到MainActivity#onNewIntent(),会发送此事件.
 * StarFragment, CategoryFragment及各子Fragment应该注册此事件.*/
public class RefreshDataEvent extends BaseEvent {


    private RefreshDataEvent(String message) {
        super(message);
    }

    public static RefreshDataEvent newInstance(String msg){
        return new RefreshDataEvent(msg);
    }
}
