package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.model.commercial;
import ensa.model.product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
}
