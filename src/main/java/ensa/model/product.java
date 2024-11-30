package ensa.model;

public class product {
    private int id;
    private String nom;
    private String description;
    private double prix;
    private commercial commercial;
    private static int nextId = 1;




    public product(String nom, String description, double prix, commercial com) {
        this.id = nextId++;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.commercial = com ;
    }
    public product(int id ,String nom, String description, double prix, commercial com) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.commercial = com ;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }
    public commercial getCommercial(){
        return commercial ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public String getCommercialMatricule() {
        return commercial != null ? commercial.getMatricule() : null; // Assurez-vous que getMatricule() existe
    }

    }


