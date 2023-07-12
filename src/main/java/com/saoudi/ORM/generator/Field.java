package com.saoudi.ORM.generator;

public class Field {
    private String name;
    private Class<?> type;
    private boolean unique;
    private boolean nullable;

    public Field(String name, Class<?> type, boolean unique, boolean nullable) {
        this.name = name;
        this.type = type;
        this.unique = unique;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isNullable() {
        return nullable;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", unique=" + unique +
                ", nullable=" + nullable +
                '}';
    }
}