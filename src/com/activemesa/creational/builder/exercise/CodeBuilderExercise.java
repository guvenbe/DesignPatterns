package com.activemesa.creational.builder.exercise;

import java.util.ArrayList;
import java.util.List;

class CodeBuilder {

    private Class theClass = new Class();

    public CodeBuilder(String rootName) {

        theClass.name = rootName;

    }

    public CodeBuilder addFields(String name, String type) {
        theClass.fields.add(new Field(name, type));

        return this;
    }

    Class build() {
        return theClass;
    }

    @Override
    public String toString() {
        return theClass.toString();
    }
}


class Field {
    public String type, name;

    public Field(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("public %s %s;", name, type);
    }
}

class Class {
    public String name;
    public List<Field> fields = new ArrayList<>();

    public Class() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String nl = System.lineSeparator();

        sb.append("public class " + name).append(nl)
                .append("{").append(nl);

        fields.forEach(f -> sb.append("  " + f).append(nl));

        sb.append("}").append(nl);

        return sb.toString();

    }
}

public class CodeBuilderExercise {
    public static void main(String[] args) {

        Class classz = new CodeBuilder("Person").addFields("name", "String").addFields("age", "int").build();
        System.out.println(classz.toString());


    }
}


