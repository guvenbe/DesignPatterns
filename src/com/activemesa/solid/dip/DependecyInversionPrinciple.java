package com.activemesa.solid.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;

    // dob etc.
    public Person(String name) {
        this.name = name;
    }
}

class Relationships {

    // Triplet class requires javatuples
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }
}


interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

class RelationshipsGoodOnDip implements RelationshipBrowser {

    // Triplet class requires javatuples
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.getValue0().name, name)
                        && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

class Research {
    public Research(Relationships relationships) {
        // high-level: find all of john's children
        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();

        relations.stream()
                .filter(x -> x.getValue0().name.equals("John")
                        && x.getValue1() == Relationship.PARENT)
                .forEach(p -> System.out.println(p.getValue0().name + " has a child " + p.getValue2().name));
    }

    protected Research(RelationshipBrowser relationshipBrowser) {
        List<Person> children = relationshipBrowser.findAllChildrenOf("John");
        for (Person child : children)
            System.out.println("John has a child called " + child.name);

    }

}


public class DependecyInversionPrinciple {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        //Low-level module
        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);

        System.out.println("Now the functionality of searching is low level module");

        RelationshipsGoodOnDip relationshipsGoodOnDip = new RelationshipsGoodOnDip();
        relationshipsGoodOnDip.addParentAndChild(parent, child1);
        relationshipsGoodOnDip.addParentAndChild(parent, child2);

        new Research(relationships);
    }

}
