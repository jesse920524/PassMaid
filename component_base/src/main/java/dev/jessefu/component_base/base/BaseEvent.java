package dev.jessefu.component_base.base;

public abstract class BaseEvent {

    private String msg;

    public BaseEvent(String msg){
        this.msg = msg;
    }

    public BaseEvent(){

    }

    public String getMsg(){
        return this.msg;
    }


}
