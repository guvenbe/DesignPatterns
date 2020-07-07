package com.activemesa.creational.builderfacade;

class Person{

    //Address
    public String streetAddress, postcode, city;

    //Employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", Position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

//builder facade
class PersonBuilder{

    protected Person person = new Person();

    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person);
    }
    public PersonJobBuilder works(){
        return new PersonJobBuilder(person);
    }

    public Person build(){
        return person;
    }
}

class PersonAddressBuilder extends  PersonBuilder{

    public PersonAddressBuilder(Person person) {
        this.person=person;
    }

    public PersonAddressBuilder at(String streetAddress){
        person.streetAddress=streetAddress;
        return this;
    }
    public PersonAddressBuilder withPostCode(String postCode){
        person.postcode=postCode;
        return this;
    }

    public PersonAddressBuilder in(String city){
        person.city=city;
        return this;
    }

}

class PersonJobBuilder extends PersonBuilder{

    public PersonJobBuilder(Person person) {
        this.person=person;
    }

    PersonJobBuilder at(String companyName){
        person.companyName=companyName;
        return this;
    }

    PersonJobBuilder asA(String position){
        person.position=position;
        return this;
    }

    PersonJobBuilder earning(int annualIncome){
        person.annualIncome=annualIncome;
        return this;
    }


}


public class BuilderFacetsDemo {
    public static void main(String[] args) {

        Person person = new PersonBuilder()
                .lives()
                    .at("123 Elm Street")
                    .in("London")
                    .withPostCode("12345")
                .works()
                    .asA("Enginner")
                    .at("GM")
                    .earning(50000)
                .build();
        System.out.println(person);





    }
}
