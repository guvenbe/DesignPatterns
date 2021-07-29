package com.activemesa.creational.prototype;

import java.util.Arrays;

// Cloneable is a marker interface

/*
 * Clone Complicated obkects
 * An existing(partially or fuilly constructed) design is a prototype
 * We make a copy(clone) the prototype and customize
 *   -Required deep copy support
 * We make cloning convinient (eg via factory)
 * */

/********************************************
 *
 * Usimg clonable is not recomended
 *
 * */
class Address implements Cloneable {
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    // base class clone() is protected
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Address(streetName, houseNumber);
    }
}

class Person implements Cloneable {
    public String[] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Person(
                // clone() creates a shallow copy!
                /*names */ names.clone(),

                // fixes address but not names
                /*address */ // (Address) address.clone()
                address instanceof Cloneable ? (Address) address.clone() : address
        );
    }
}

class CloneableDemo {
    public static void main(String[] args)
            throws CloneNotSupportedException {
        Person john = new Person(new String[]{"John", "Smith"},
                new Address("London Road", 123));

        // shallow copy, not good:
        Person jane = john;
        jane.names[0] = "jane";
        //they both became jane not good.
        System.out.println("bad" + jane);
        System.out.println("bad" + john);

        // jane is the girl next door
        Person jane2 = (Person) john.clone();
        jane.names[0] = "Jane"; // clone is (originally) shallow copy
        jane.address.houseNumber = 124; // oops, also changed john

        System.out.println(john);
        System.out.println(jane2);
    }
}