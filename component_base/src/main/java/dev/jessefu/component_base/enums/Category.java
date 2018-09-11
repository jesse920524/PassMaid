package dev.jessefu.component_base.enums;
/**
 * 代表帐号种类*/
public enum Category {

//    ALL("全部"),
    SOCIAL("社交"),
    LIFE("生活"),
    SHOPPING("购物"),
    GAME("游戏"),
    WORK("工作");

    private String name;

    Category(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
