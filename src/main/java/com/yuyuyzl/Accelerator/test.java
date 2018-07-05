package com.yuyuyzl.Accelerator;

class A{
    public A(){
        System.out.println(this.getClass().getSimpleName());
    }
}

class B extends A{

}

public class test {
    public static void main(String[] args) {
        new A();
        new B();
    }
}
