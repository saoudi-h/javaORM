package com.saoudi.ORM.generator;

public abstract class AbstractEntity {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String toString();

    public abstract boolean equals(Object obj);


}