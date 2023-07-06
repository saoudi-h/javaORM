package org.saoudi.ORM.model;

import org.saoudi.ORM.utils.Nameable;

import java.util.ArrayList;

public class Model implements Nameable {
    private String className;
    private ArrayList<Field> fields;

    public Model(String className) {
        this.className = className;
        this.fields = new ArrayList<>();
    }

    public void add(Field field) {
        fields.add(field);
    }



    public ArrayList<Field> getFields() {
        return fields;
    }

    @Override
    public String getName() {
        return className;
    }
}