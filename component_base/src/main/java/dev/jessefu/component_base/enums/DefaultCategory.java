package dev.jessefu.component_base.enums;
/**
 * 代表帐号种类*/

public enum DefaultCategory {

//    ALL("全部"),
    SOCIAL("社交"),
    LIFE("生活"),
    SHOPPING("购物"),
    GAME("游戏"),
    WORK("工作"),
    OTHER("其他");

    private String name;

    DefaultCategory(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
