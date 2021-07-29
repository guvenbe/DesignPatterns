package com.activemesa.creational.singleton;


import java.io.File;
import java.io.IOException;

class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", ".");

    }

    //Cant do final
    private static StaticBlockSingleton instance;

    //Handles initialization issues
    //This atc like static constructor
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            System.err.println("failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}


public class StaticBlockSingletoDemo {

    public static void main(String[] args) {

        //This will throw exception
        //and error
        //falied to create singleton
        final StaticBlockSingleton instance = StaticBlockSingleton.getInstance();

    }
}


