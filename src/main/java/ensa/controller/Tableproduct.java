package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.model.commercial;
import ensa.model.product;
import ensa.util.DbConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Tableproduct {

    @FXML
    private TableView<product> productTable;

    @FXML
    private TableColumn<product, String> id;

    @FXML
    private TableColumn<product, String> nom;

    @FXML
    private TableColumn<product, String> desc;

    @FXML
    private TableColumn<product, Double> prix;

    @FXML
    private TableColumn<product, String> commercial_matricule;

    // Liste observable pour stocker les produits
    private ObservableList<product> productList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Lier les colonnes à leurs propriétés
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        commercial_matricule.setCellValueFactory(new PropertyValueFactory<>("commercialMatricule"));

        // Charger les données depuis la base de données
        loadProducts();
    }

    private void loadProducts() {
        String query = "SELECT * FROM product";

        try ( DbConnexion db = new DbConnexion();
              Connection connection = db.getConnection();
              PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idvalue = resultSet.getInt("id");
                String nomvalue = resultSet.getString("nom");
                String descriptionvalue = resultSet.getString("description");
                double prixvalue = resultSet.getDouble("prix");
                String commercialMatriculevalue = resultSet.getString("commercial_matricule");
                CommercialDaoImp commercialDao = new CommercialDaoImp();
                List<commercial> commercials = commercialDao.getAllCommercials();
                commercial selectedCommercial = null;
                for (commercial com : commercials) {
                    if (com.getMatricule().equals(commercialMatriculevalue)) { // Comparez avec le nom
                        selectedCommercial = com;
                        break;
                    }
                }


                // Créer un objet Product et l'ajouter à la liste
                product product = new product(idvalue, nomvalue, descriptionvalue, prixvalue, selectedCommercial);
                productList.add(product);
            }

            // Ajouter les produits à la TableView
            productTable.setItems(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

