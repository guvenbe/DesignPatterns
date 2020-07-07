package com.activemesa.creational.singleton;

class LazySingleton{

    private static LazySingleton instance;

    public LazySingleton() {
        System.out.printf("initiallizing a lazy sinleton");
    }

    // This could have thread issue with race condition of
    // Multple threads
    // Intialization can get called twice from 2 different threads
    // This could be problem if the initailization is sensitive
    // to that
    // You could make it synronized
    // public static syncronized LazySingleton getInstance()
    // This has obvious performance implication
    // Other option is "double checked locking"
    // Even though this a outdated approach
    // ***Don't use it if you can avoid it
    // Just be aware of it
    // Inner Static singleton maybea better approach
    public static LazySingleton getInstance() {

        if(instance==null){
            synchronized (LazySingleton.class){

                if(instance==null){
                    instance=new LazySingleton();
                }

            }

        }
        return instance;
    }

//    public static LazySingleton getInstance() {
//
//        if(instance==null){
//            instance=new LazySingleton();
//        }
//        return instance;
//    }
}

public class LazySingletonDemo {
      public static void main(String[] args) {

          final LazySingleton instance = LazySingleton.getInstance();

      }

}

