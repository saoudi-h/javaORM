package org.saoudi.javaORM;

import java.sql.*;
import java.util.ArrayList;

public class PersonModel extends AbstractModel<Person> {

    /**
     * Constructeur de la classe PersonModel.
     * Initialise la connexion à la base de données.
     */
    public PersonModel(String tableName) {
        super(tableName);
    }


    /**
     * Recherche et renvoie un utilisateur correspondant à l'email donné.
     *
     * @param email l'adresse email de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */

    public Person find(String email) throws SQLException {
        return this.find("email", email);
    }

    @Override
    public Person find(String by, String value) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE " + by + " = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, value);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int personId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            Date birthdate = resultSet.getDate("birthdate");

            return new Person(personId, name, lastName, email, phone, birthdate);

        } else return null;
    }

    @Override
    public Person[] findAll() throws SQLException {
        ArrayList<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " where 1";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int personId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            Date birthdate = resultSet.getDate("birthdate");

            persons.add(new Person(personId, name, lastName, email, phone, birthdate));
        }
        return persons.toArray(new Person[0]);
    }

    @Override
    public Person create(Person person) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (name,lastname,email, phone, birthdate) VALUES (?,? ,?, ?,?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, person.getName());
        statement.setString(2, person.getLastName());
        statement.setString(3, person.getEmail());
        statement.setString(4, person.getPhone());
        statement.setDate(5, person.getBirthdate());
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            person.setId(generatedId);
        } else {
            throw new SQLException("La création de la personne a échoué");
        }
        return person;
    }

    @Override
    public void update(Person person) throws SQLException {
        String sql = "Update "+tableName+" set name=?, lastname=?, email=?, phone=?, birthdate=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getName());
        statement.setString(2, person.getLastName());
        statement.setString(3, person.getEmail());
        statement.setString(4, person.getPhone());
        statement.setDate(5, person.getBirthdate());
        statement.setInt(6, person.getId());
        statement.executeUpdate();
    }

    @Override
    public Person save(Person person) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (name,lastname,email, phone, birthdate)\n" +
                "VALUES (?,?,?,?,?)\n" +
                "ON DUPLICATE KEY UPDATE name=?, lastname=?, email=?, phone=?, birthdate=?";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, person.getName());
        statement.setString(2, person.getLastName());
        statement.setString(3, person.getEmail());
        statement.setString(4, person.getPhone());
        statement.setDate(5, person.getBirthdate());
        statement.setString(6, person.getName());
        statement.setString(7, person.getLastName());
        statement.setString(8, person.getEmail());
        statement.setString(9, person.getPhone());
        statement.setDate(10, person.getBirthdate());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            person.setId(generatedId);
        }
        return person;
    }


    /**
     * Vérifie si une personne avec l'email donné existe dans la base de données.
     *
     * @param email L'identifiant de l'utilisateur à vérifier.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public boolean exists(String email) throws SQLException {
        return this.exists("email",email);
    }
}

