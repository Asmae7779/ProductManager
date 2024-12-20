package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.dao.imp.ProductImp;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductController {
    @FXML
    private TextField nom;

    @FXML
    private TextField description;

    @FXML
    private TextField prix;

    @FXML
    private TextField commercial;


    @FXML
    public void handleAddProduct() {
        String nomvalue = nom.getText();
        String descriptionvalue = description.getText();
        String prixvalue = prix.getText();
        String commercialName = commercial.getText(); // Nom du commercial saisi

        ProductImp imp = new ProductImp();
        double prixDouble = Double.parseDouble(prixvalue);

        // Obtenez la liste des commerciaux
        CommercialDaoImp commercialDao = new CommercialDaoImp();
        List<commercial> commercials = commercialDao.getAllCommercials();

        // Trouver le commercial correspondant au nom saisi
        if(commercials == null){
            System.out.println("pas de commercials dans votre tableau");
        }else{
            System.out.println("recherche resussi");
        }
        commercial selectedCommercial = null;
        for (commercial com : commercials) {
            if (com.getNom().equals(commercialName)) { // Comparez avec le nom
                selectedCommercial = com;
                break;
            }
        }



        if (selectedCommercial != null) {
            try {
                product pr = new product(nomvalue, descriptionvalue, prixDouble, selectedCommercial);
                imp.Add(pr);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());

            }
        } else {
            System.out.println("Commercial non trouvé : " + commercialName);

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
