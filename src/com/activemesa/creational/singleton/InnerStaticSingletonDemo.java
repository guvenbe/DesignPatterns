package com.activemesa.creational.singleton;

class InnerStaticSingleton {

    protected InnerStaticSingleton() {

    }

    //This avoinds the problem of syncronization

    private static class Impl {
        private static final InnerStaticSingleton
                INSTANCE = new InnerStaticSingleton();

    }

    public  static InnerStaticSingleton getInstance(){
        System.out.println("Initializing Innerstatic Singleton");
        return Impl.INSTANCE;
    }
}


public class InnerStaticSingletonDemo {

    public static void main(String[] args) {
        InnerStaticSingleton innerStaticSingleton = InnerStaticSingleton.getInstance();
    }
}


