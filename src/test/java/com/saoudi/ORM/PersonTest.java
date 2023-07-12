package com.saoudi.ORM;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PersonTest {

    @Test
    public void testEqualsAndHashCode() {
        Person person1 = new Person(1, "John", "Doe", "john@example.com", "123456789", Date.valueOf("1990-01-01"));
        Person person2 = new Person(2, "Jane", "Smith", "jane@example.com", "987654321", Date.valueOf("1995-05-05"));
        Person person3 = new Person(4, "John", "Doe", "john@example.com", "123456789", Date.valueOf("1990-01-01"));

        // Check equality between two persons with the same ID and name
        assertEquals(person1, person3);
        assertEquals(person1.hashCode(), person3.hashCode());

        // Check difference between two persons with different IDs and names
        assertNotEquals(person1, person2);
        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void testToString() {
        Person person1 = new Person(1, "John", "Doe", "john@example.com", "123456789", Date.valueOf("1990-01-01"));
        String expectedString = "Person{id=1, name='John', lastName='Doe', email='john@example.com', phone='123456789', birthdate='1990-01-01'}";

        assertEquals(expectedString, person1.toString());
    }
}