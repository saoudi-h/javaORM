package com.saoudi.ORM;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PersonSchemaTest {
    private PersonModel personModel = new PersonModel("person");
    private Person person;
    private Person person2;

    @AfterEach
    public void cleanup() throws SQLException {
        if (person != null)
            personModel.delete(person);
        if (person2 != null)
            personModel.delete(person2);
    }

    @Test
    public void testFind() throws SQLException {
        person = new Person(1, "John", "Doe", "john.doe@example.com", "123456789", Date.valueOf("1990-01-01"));
        personModel.create(person);
        person2 = personModel.find(person.getEmail());
        assertNotNull(person2);
        assertEquals(person, person2);
        person2 = personModel.find(2);
        assertNull(person2);
    }

    @Test
    public void testFindAll() throws SQLException {
        person = new Person(1, "John", "Doe", "john.doe@example.com", "123456789", Date.valueOf("1990-01-01"));
        person2 = new Person(2, "Jane", "Smith", "jane.smith@example.com", "987654321", Date.valueOf("1995-05-05"));
        personModel.create(person);
        personModel.create(person2);
        Person[] persons = personModel.findAll();
        assertEquals(2, persons.length);
        assertEquals(person, persons[0]);
        assertEquals(person2, persons[1]);
    }

    @Test
    public void testCreate() throws SQLException {
        person = new Person(1, "John", "Doe", "john.doe@example.com", "123456789", Date.valueOf("1990-01-01"));
        person2 = personModel.create(person);
        assertNotNull(person2);
        assertNotEquals(1, person2.getId());
        assertEquals(person.getName(), person2.getName());
        assertEquals(person.getLastName(), person2.getLastName());
        assertEquals(person.getEmail(), person2.getEmail());
        assertEquals(person.getPhone(), person2.getPhone());
        assertEquals(person.getBirthdate(), person2.getBirthdate());
    }

    @Test
    public void testUpdate() throws SQLException {
        person = new Person(1, "John", "Doe", "john.doe@example.com", "123456789", Date.valueOf("1990-01-01"));
        personModel.create(person);
        person.setName("John Smith");
        person.setLastName("Smith");
        person.setEmail("john.smith@example.com");
        person.setPhone("987654321");
        person.setBirthdate(Date.valueOf("1995-05-05"));
        personModel.update(person);
        person2 = personModel.find(person.getId());
        assertNotNull(person2);
        assertEquals(person, person2);
    }

    @Test
    public void testDelete() throws SQLException {
        person = new Person(1, "John", "Doe", "john.doe@example.com", "123456789", Date.valueOf("1990-01-01"));
        personModel.create(person);
        boolean deleted = personModel.delete(person);
        assertTrue(deleted);
        Person deletedPerson = personModel.find(1);
        assertNull(deletedPerson);
    }

    @Test
    public void testSave() throws SQLException {
        person = new Person(1, "John", "Doe", "john.doe@example.com", "123456789", Date.valueOf("1990-01-01"));
        person2 = personModel.save(person);
        assertNotNull(person2);
        assertNotEquals(1, person2.getId());
        assertEquals(person.getName(), person2.getName());
        assertEquals(person.getLastName(), person2.getLastName());
        assertEquals(person.getEmail(), person2.getEmail());
        assertEquals(person.getPhone(), person2.getPhone());
        assertEquals(person.getBirthdate(), person2.getBirthdate());
    }

    @Test
    public void testExists() throws SQLException {
        person = new Person(1, "John", "Doe", "john.doe@example.com", "123456789", Date.valueOf("1990-01-01"));
        personModel.create(person);
        boolean existsByEmail = personModel.exists(person.getEmail());
        assertTrue(existsByEmail);
        boolean existsById = personModel.exists(person.getId());
        assertTrue(existsById);
        boolean existsByInvalidId = personModel.exists(-6);
        assertFalse(existsByInvalidId);
    }

    @Test
    public void testCount() throws SQLException {
        int initialCount = personModel.count();
        person = new Person(1, "John", "Doe", "john.doe@example.com", "123456789", Date.valueOf("1990-01-01"));
        person2 = new Person(2, "Jane", "Smith", "jane.smith@example.com", "987654321", Date.valueOf("1995-05-05"));
        personModel.create(person);
        personModel.create(person2);
        int updatedCount = personModel.count();
        assertEquals(initialCount + 2, updatedCount);
    }
}