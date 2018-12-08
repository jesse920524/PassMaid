package dev.jessefu.component_base.event;

/**初始化tab事件。
 * 由CategoryChildFragment -》 MainActivity*/
public class InitTabEvent extends BaseEvent {
    protected InitTabEvent(String message) {
        super(message);
    }

    public static InitTabEvent newInstance(String msg){
        return new InitTabEvent(msg);
    }

}
