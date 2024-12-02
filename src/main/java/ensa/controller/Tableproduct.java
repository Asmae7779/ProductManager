package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.model.commercial;
import ensa.model.product;
import ensa.util.DbConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
        productTable.setOnMouseClicked(event -> handleMouseClick(event));


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

    public void Retour(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ensa/homepage.fxml"));
            Parent root = loader.load();


            Stage newStage = new Stage();
            newStage.setTitle("Home");
            newStage.setScene(new Scene(root));
            newStage.show();


            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void Close(ActionEvent event){
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

    }
    private void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Vérifie si la ligne a été double-cliquée
            product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ensa/updateproduct.fxml"));
                    Parent root = loader.load();

                    // Obtenir le contrôleur de l'interface update
                    UpdateProduct controller = loader.getController();
                    controller.setProduct(selectedProduct); // Passer l'objet produit au contrôleur

                    // Créer une nouvelle scène et une nouvelle fenêtre
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Mettre à jour le produit");
                    stage.show();
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Aucun produit sélectionné !");
            }
        }
    }
}


