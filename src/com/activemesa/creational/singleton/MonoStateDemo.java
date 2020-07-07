package com.activemesa.creational.singleton;

// Not a great approach
//


class CheifExecutiveOfficer{

    //For Mono state make fields static
    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        CheifExecutiveOfficer.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        CheifExecutiveOfficer.age = age;
    }

    @Override
    public String toString() {
        return "CheifExecutiveOfficer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class MonoStateDemo {
    public static void main(String[] args) {
        CheifExecutiveOfficer ceo1 = new CheifExecutiveOfficer();
        ceo1.setName("Adam Smith");
        ceo1.setAge(55);

        CheifExecutiveOfficer ceo2 = new CheifExecutiveOfficer();

        //But wait ceo2 is already initialized
        //Becaouse all intances are referencing
        //Same instance.
        //This is why it is called mono
        System.out.println(ceo2);
    }
}
