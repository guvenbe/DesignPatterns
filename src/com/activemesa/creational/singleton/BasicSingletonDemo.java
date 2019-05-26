package com.activemesa.creational.singleton;


import java.io.*;


/*
* Eric Gamma: Design Smell H was in favor of droping it from G4 patterns
* When one instance makes snse
*   - Database repository
*   - Object Factory
* Eg The constructor call is expensive
* We do it once
* We provide everyone with same instance
* Want to prevent anyone creating additional copies
* Need to take care of lazy instantiation and thread safety
*
* */

class BasicSingleton implements Serializable{

    public BasicSingleton()  {
    }

    private  static  final  BasicSingleton INSTANCE=new BasicSingleton();

    public static  BasicSingleton getInstance(){
        return INSTANCE;

    }

    private int value=0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
//      Add this back in
//     FIXES: required for correct serialization
//     readResolve is used for _replacing_ the object read from the stream

//  protected Object readResolve()
//  {
//    return INSTANCE;
//  }

    // generated getter

}

public class BasicSingletonDemo {

  static void saveToFile(BasicSingleton singleton, String filename)
    throws Exception
  {
    try (FileOutputStream fileOut = new FileOutputStream(filename);
         ObjectOutputStream out = new ObjectOutputStream(fileOut))
    {
      out.writeObject(singleton);
    }
  }


    static BasicSingleton readFromFile(String filename)throws Exception{

        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
        ){
            return (BasicSingleton)in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {



        final BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(123);
        System.out.println(singleton.getValue());

        /*
        * Problems:
        * 1. you can use reflection to get to constructor
        *
        * 2. serialization (will make additional copies
        * */

        BasicSingleton singleton1 = BasicSingleton.getInstance();
        singleton1.setValue(111);
        final String filename = "singleton1.bin";
        saveToFile(singleton1,filename);

        singleton1.setValue(222);


        final BasicSingleton singleton2 = readFromFile(filename);
        System.out.println(singleton1==singleton2); //false
        System.out.println(singleton1.getValue());  //111
        System.out.println(singleton2.getValue());  //222

        //Serialization causes problems
        //Not singleton behaviour


    }
}
