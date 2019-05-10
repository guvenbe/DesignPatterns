package com.activemesa.creational.builder;

// builder inheritance with recursive generics

class Person{
    public String firstName;
    public String lastName;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>>{

    protected Person person = new Person();


    // critical to return SELF here
    public SELF withLastName(String lastName){
        person.lastName=lastName;
        return self();
    }

    // critical to return SELF here
    public SELF withFirstName(String firstName){
        person.firstName=firstName;
        return self();
    }

    public SELF withGender(String gender){
        person.setGender(gender);
        return self();
    }

    public Person build(){
        return person;
    }

    protected SELF self(){
        // unchecked cast, but actually safe
        // proof: try sticking a non-PersonBuilder
        //        as SELF parameter; it won't work!
        return (SELF) this;
    }

}
class EmployeeBuilder extends  PersonBuilder<EmployeeBuilder>{

    public EmployeeBuilder worksAt(String position){
        person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self(){
        return this;
    }

}

public class RecursiveGenericsDemo {
    public static void main(String[] args) {

        PersonBuilder personBuilder = new PersonBuilder();
        Person person =personBuilder.withLastName("Guven").withFirstName("Bora").withGender("male").build();
        System.out.printf("%s" ,person);

        Person person1 = new EmployeeBuilder().worksAt("GM").withFirstName("Bora").withLastName("Guven").build();

        System.out.println(person1);

    }
}
