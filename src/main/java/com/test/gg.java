package com.test;

/**
 * Created by honor on 2018/3/7.
 */
public class gg {
    int i;
    public void doSomething(){
        System.out.println("i="+i);
    }

    public static void main(String[] args) {
        gg g = new gg();
        g.doSomething();
        System.out.println(g.i);
    }
}
