package org.saoudi.appORM.models;

public class Voiture extends AbstractModel {
    private String marque;
    private String modele;
    public Voiture(int id, String marque, String modele) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
    }

    public Voiture(String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
        this.id = 0; // Valeur par défaut pour l'ID
    }
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "marque='" + marque + '\'' +
                "modele='" + modele + '\'' +
                "id='" + id + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Voiture other = (Voiture) obj;
        if (marque != null ? !marque.equals(other.marque) : other.marque != null) return false;
        if (modele != null ? !modele.equals(other.modele) : other.modele != null) return false;
        return id == other.id;
    }
    public static void main(String[] args){
        Voiture v = new Voiture("volkswagen","golf");
        Voiture b = new Voiture("volkswagen","golf");
        System.out.println(v);
        System.out.println(v.equals(b));

    }
}