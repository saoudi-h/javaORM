package org.saoudi.javaORM;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe responsable de la gestion de la connexion à la base de données.
 */
public class DatabaseManager {
    private static final String CONFIG_FILE = "ressources/config.properties";
    private static final Properties appProps = new Properties();
    private static Connection connection;

    /**
     * Constructeur privé pour empêcher l'instanciation directe de la classe.
     */
    private DatabaseManager() {
    }

    /**
     * Obtient une connexion à la base de données.
     *
     * @return L'objet Connection représentant la connexion à la base de données.
     */
    public static Connection getConnection() {
        if (connection == null) {
            try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")){
                appProps.load(input);
                String database = appProps.getProperty("mysql_database");
                String url = appProps.getProperty("mysql_url") + database;
                String user = appProps.getProperty("mysql_user");
                String password = appProps.getProperty("mysql_password");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}