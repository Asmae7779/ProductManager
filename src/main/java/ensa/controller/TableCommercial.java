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
}
