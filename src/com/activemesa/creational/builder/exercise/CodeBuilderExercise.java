package com.activemesa.creational.builder.exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

class CodeBuilder{

    private Class theClass = new Class();

    public CodeBuilder(String rootName){

        theClass.name = rootName;

    }

    public CodeBuilder addFields(String name, String type){
        theClass.fields.add(new Field(name, type));

        return  this;
    }

    Class  build(){
        return theClass;
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
        return String.format("public %s %s;", type, name);
    }
}

class Class{
    public String name;
    public List<Field> fields = new ArrayList<>();

    public Class() {}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String nl = System.lineSeparator();

        sb.append("public class " + name).append(nl)
                .append("{").append(nl);

        fields.forEach(f -> sb.append(f).append(nl));

        sb.append("}").append(nl);

        return sb.toString();

    }
}

public class CodeBuilderExercise {
    public static void main(String[] args) {

        Class classz = new CodeBuilder("Person").addFields("name", "String").addFields("age", "int").build();
        System.out.println(classz);



    }
}

class Evaluate
{
    private String preprocess(String text)
    {
        return text.replace("\r\n", "\n").trim();
    }

    @Test
    public void emptyTest() {
        CodeBuilder cb = new CodeBuilder("Foo");
        assertEquals("public class Foo\n{\n}",
                preprocess(cb.toString()));
    }

    @Test
    public void personTest()
    {
        CodeBuilder cb = new CodeBuilder("Person")
                .addFields("name", "String")
                .addFields("age", "int");
        assertEquals("public class Person\n{\n" +
                        "  public String name;\n" +
                        "  public int age;\n}",
                preprocess(cb.toString()));
    }
}
