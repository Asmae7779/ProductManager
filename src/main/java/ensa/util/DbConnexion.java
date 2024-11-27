package ensa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnexion {

    // URL de la base de données MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ProductManager";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public static Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connexion réussie à la base de données MySQL !");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base : " + e.getMessage());
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public static void Close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }


}

