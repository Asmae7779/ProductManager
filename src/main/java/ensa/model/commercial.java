package ensa.model;

import java.util.ArrayList;
import java.util.List;

public class commercial {
    private String nom;
    private String prenom;
    private String matricule;
    private List<product> produits;


    public commercial() {
    }
    @Override
    public String toString() {
        return nom; // Retourne le nom pour l'affichage
    }


    public commercial(String nom, String prenom, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getMatricule() {
        return matricule;
    }


    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    // Getter pour 'produits'
    public List<product> getProduits() {
        return produits;
    }


    public void setProduits(List<product> produits) {
        this.produits = produits;
    }
    public void addProduct(product product) {
        if (product == null) {
            throw new IllegalArgumentException("Le produit ne peut pas être null.");
        }
        if (this.produits == null) {
            this.produits = new ArrayList<>(); // Initialiser la liste si elle est null
        }
        this.produits.add(product);
    }

}

