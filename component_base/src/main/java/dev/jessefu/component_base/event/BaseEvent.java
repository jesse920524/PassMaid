package dev.jessefu.component_base.event;

/**所有EventBus事件的基类.
 **/
public abstract class BaseEvent {

    private String msg;

    protected BaseEvent(String message){
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
