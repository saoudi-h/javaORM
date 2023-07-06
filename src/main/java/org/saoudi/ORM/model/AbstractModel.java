package org.saoudi.ORM.model;

public abstract class AbstractModel {
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