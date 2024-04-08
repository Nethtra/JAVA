package com.Reflection;

/**
 * @author 王天一
 * @version 1.0
 */
public class Cat {
    private String name = "巧克力";
    public int age = 3;

    //无参构造器
    public Cat() {
    }



    //有参构造器
    public Cat(String name) {
        this.name = name;
    }

    public void hi() {
        System.out.println("你好世界");
    }

    public void cry() {
        System.out.println("你哭世界");
    }
}
