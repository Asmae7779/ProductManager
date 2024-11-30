package ensa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnexion implements AutoCloseable {

    // URL de la base de données MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ProductManager";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    // Méthode pour établir la connexion
    public DbConnexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connexion réussie à la base de données MySQL !");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base : " + e.getMessage());
        }
    }

    // Retourne la connexion active
    public Connection getConnection() {
        return this.connection;
    }

    // Méthode close() de l'interface AutoCloseable pour fermer la connexion
    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
