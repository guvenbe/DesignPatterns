package com.activemesa.creational.singleton;

import org.junit.Test;

import java.util.function.Supplier;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

class SingletonTester {
    public static boolean isSingleton(Supplier<Object> func)
    {
        return func.get() == func.get();
    }
}



public class SingletonTesterDemo
{
    @Test
    public void test()
    {
        Object obj = new Object();
        assertTrue(SingletonTester.isSingleton(() -> obj));
        assertFalse(SingletonTester.isSingleton(Object::new));
    }
}