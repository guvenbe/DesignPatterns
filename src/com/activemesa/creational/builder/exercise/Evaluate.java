
package com.activemesa.creational.builder.exercise;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;

import com.activemesa.creational.builder.exercise.CodeBuilder;
import org.junit.jupiter.api.Test;


class Evaluate {
    private String preprocess(String text) {
        return text.replace("\r\n", "\n").trim();
    }

    @Test
    public void emptyTest() {
        CodeBuilder cb = new CodeBuilder("Foo");
        junit.framework.Assert.assertEquals("public class Foo\n{\n}",
                preprocess(cb.toString()));
    }

    @Test
    public void personTest() {
        CodeBuilder cb = new CodeBuilder("Person")
                .addFields("name", "String")
                .addFields("age", "int");
        junit.framework.Assert.assertEquals("public class Person\n{\n" +
                        "  public String name;\n" +
                        "  public int age;\n}",
                preprocess(cb.toString()));
    }
}