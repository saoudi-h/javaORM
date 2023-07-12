package com.saoudi.ORM.generator;

import java.util.ArrayList;
import java.util.Arrays;

public class Schema implements Nameable {
    private String className;
    private ArrayList<Field> fields;

    public Schema() {
        this.fields = new ArrayList<>();
    }
    public Schema(String className) {
        this();
        this.className = className;
    }


    public Schema add(Field field) {
        fields.add(field);
        return this;
    }

    public Schema addAll(Field[] fields) {
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    public Schema setFields(ArrayList<Field> fields) {
        this.fields = fields;
        return this;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public Schema setName(String className) {
        this.className = className;
        return this;
    }
    @Override
    public String getName() {
        return className;
    }

    @Override
    public String toString() {
        return "Schema{" +
                "className='" + className + '\'' +
                ", fields=" + fields +
                '}';
    }
}