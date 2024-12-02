package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.model.commercial;
import ensa.model.product;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TableCommercial {
    @FXML
    private TableView<commercial> commercialTable;

    @FXML
    private TableColumn<commercial, String> nom;

    @FXML
    private TableColumn<commercial, String> prenom;

    @FXML
    private TableColumn<commercial, String> matricule;



    @FXML
    public void initialize() {
        // Lier les colonnes à leurs propriétés

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        commercialTable.setOnMouseClicked(event -> handleMouseClick(event));

        loadCommercials();

        // Charger les données depuis la base de données

    }
    private void loadCommercials() {
        try {
            // Exemple : utiliser une classe DAO pour récupérer les données
            CommercialDaoImp dao = new CommercialDaoImp();
            ObservableList<commercial> commercials = FXCollections.observableArrayList(dao.getAllCommercials());

            // Ajouter les données au tableau
            commercialTable.setItems(commercials);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement des commerciaux.");
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
            commercial selectedCommercial = commercialTable.getSelectionModel().getSelectedItem();
            if ( selectedCommercial != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ensa/updatecommercial.fxml"));
                    Parent root = loader.load();

                    // Obtenir le contrôleur de l'interface update
                    UpdateCommercial controller = loader.getController();
                    controller.SetCommercial(selectedCommercial); // Passer l'objet produit au contrôleur

                    // Créer une nouvelle scène et une nouvelle fenêtre
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Update Commercial");
                    stage.show();
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Aucun Commercial selectione !");
            }
        }
    }
}
