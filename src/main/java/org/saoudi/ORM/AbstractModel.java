package org.saoudi.ORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractModel<T extends Identifiable> implements InterfaceModel<T> {




    public String tableName;
    public Connection connection;

    /**
     * Constructeur de la classe UserModel.
     * Initialise la connexion à la base de données.
     */
    public AbstractModel() {
        connection = DatabaseManager.getConnection();
    }
    public AbstractModel(String tableName) {

        this();
        this.tableName = tableName;
    }
    /**
     * Recherche et renvoie un objet correspondant à l'identifiant donné.
     *
     * @param id L'identifiant de l'objet à rechercher.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    @Override
    public T find(int id) throws SQLException {
        return this.find("id",Integer.toString(id));
    }

    @Override
    abstract public T find(String by, String value) throws SQLException;

    @Override
    abstract public T[] findAll() throws SQLException;

    @Override
    abstract public T create(T obj) throws SQLException;

    @Override
    abstract public void update(T obj) throws SQLException;


    /**
     * Supprime un objet de la table "user".
     *
     * @param obj L'utilisateur à supprimé.
     * @return true si la suppression a réussi, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    @Override
    public boolean delete(T obj) throws SQLException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, obj.getId());
        int res = statement.executeUpdate();
        return res==1;
    }


    /**
     * Enregistre un objet dans la table "tableName".
     * Effectue une opération d'insertion avec mise à jour si l'objet existe déjà.
     *
     * @param obj L'objet à enregistrer.
     * @return true si l'enregistrement a réussi, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    @Override
    abstract public T save(T obj) throws SQLException;


    /**
     * Vérifie si un objet avec l'identifiant 'by' existe dans la base de données.
     *
     * @param by le nom de l'attribut par lequel on identifiera l'objet à vérifier.
     * @param value la valeur de l'identifiant de l'utilisateur indiqué par by.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    @Override
    public boolean exists(String by, String value) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM "+tableName+" WHERE "+by+" = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, value);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count > 0;
        } else {
            return false;
        }
    }

    /**
     * Vérifie si un utilisateur avec l'identifiant donné existe dans la base de données.
     *
     * @param id L'identifiant de l'utilisateur à vérifier.
     * @return true si l'utilisateur existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    @Override
    public boolean exists(int id) throws SQLException {
        return this.exists("id",Integer.toString(id));
    }


    /**
     * Compte le nombre total d'objets dans la base de données.
     *
     * @return Le nombre total d'objets.
     * @throws SQLException Si une erreur SQL se produit.
     */
    @Override
    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM "+ tableName;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("count");
        } else {
            return 0;
        }
    }
}
