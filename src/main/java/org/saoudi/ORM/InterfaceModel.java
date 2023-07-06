package org.saoudi.ORM;

import java.sql.SQLException;

public interface InterfaceModel<T extends Identifiable> {
    /**
     * Recherche et renvoie un objet de type T correspondant à l'identifiant donné.
     *
     * @param id L'identifiant de l'objet à rechercher.
     * @return L'objet correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T find(int id) throws SQLException;


    /**
     * Recherche et renvoie un objet correspondant à l'identifiant donné par l'attribut spécifié.
     *
     * @param by    L'attribut qui identifie un objet unique.
     * @param value La valeur de l'attribut spécifié par 'by'.
     * @return L'objet correspondant à l'identifiant, ou null s'il n'existe pas.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T find(String by, String value) throws SQLException;


    /**
     * Renvoie tous les objets de la table.
     *
     * @return Un tableau d'objets.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T[] findAll() throws SQLException;

    /**
     * Crée un nouvel objet dans la table.
     *
     * @param obj L'objet à créer.
     * @return L'objet créé avec son identifiant mis à jour.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T create(T obj) throws SQLException;

    /**
     * Met à jour les informations d'un objet dans la table.
     *
     * @param obj L'objet à mettre à jour.
     * @throws SQLException Si une erreur SQL se produit.
     */
    void update(T obj) throws SQLException;

    /**
     * Supprime un objet de la table.
     *
     * @param obj L'objet à supprimer.
     * @return true si la suppression a réussi, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    boolean delete(T obj) throws SQLException;

    /**
     * Enregistre un objet dans la table.
     * Effectue une opération d'insertion avec mise à jour si l'objet existe déjà.
     *
     * @param obj L'objet à enregistrer.
     * @return L'objet enregistré.
     * @throws SQLException Si une erreur SQL se produit.
     */
    T save(T obj) throws SQLException;



/**
 * Vérifie si un objet avec l'identifiant donné existe dans la base de données.
 *
 * @param id L'identifiant de l'objet à vérifier.
 * @return true si l'objet existe, false sinon.
 * @throws SQLException Si une erreur SQL se produit.
 */
boolean exists(int id) throws SQLException;

    /**
     * Vérifie si un objet avec l'attribut spécifié existe dans la base de données.
     *
     * @param by    Le nom de l'attribut utilisé pour identifier l'objet.
     * @param value La valeur de l'attribut spécifié par 'by'.
     * @return true si l'objet existe, false sinon.
     * @throws SQLException Si une erreur SQL se produit.
     */
    boolean exists(String by, String value) throws SQLException;

    /**
     * Compte le nombre total d'objets dans la base de données.
     *
     * @return Le nombre total d'objets.
     * @throws SQLException Si une erreur SQL se produit.
     */
    int count() throws SQLException;
}