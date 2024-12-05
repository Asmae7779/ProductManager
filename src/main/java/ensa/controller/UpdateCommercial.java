package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.model.commercial;
import ensa.model.product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UpdateCommercial {

    @FXML
    private commercial currentCommercial;
    @FXML
    private TextField nom ;
    @FXML
    private TextField prenom ;
    @FXML
    private TextField matricule ;


    public void handleUpdateCommercial(){
        String nomvalue = nom.getText();
        String prenomvalue = prenom.getText();
        String matvalue = matricule.getText();
        if (currentCommercial == null) {
            System.out.println("No commercial is currently selected.");
            return;
        }


        try {
            CommercialDaoImp imp = new CommercialDaoImp();
            imp.UpdateCommercial(currentCommercial, nomvalue, prenomvalue, matvalue);
            System.out.println("Commercial updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to update the commercial.");
        }


    }
    public void handleDeleteCommercial(){
        String matvalue = matricule.getText();
        List<commercial> commercials = new ArrayList<>();
        CommercialDaoImp imp = new CommercialDaoImp();
        commercials = imp.getAllCommercials();
        commercial selectedcom = null;
        for(commercial com:commercials){
            if(com.getMatricule().equals(matvalue)){
                selectedcom = com;
                break;
            }else {
                System.out.println("Produit introuvable!");
            }

        }
        imp.deleteCommercial(selectedcom);

    }


    public void SetCommercial(commercial com) {
        this.currentCommercial = com;  // Stocke le produit sélectionné dans la variable currentProduct
        if (com == null) {
            System.out.println("Commercial is null, cannot set fields.");
            return;
        }

        nom.setText(com.getNom());
        prenom.setText(com.getPrenom());
        matricule.setText(com.getMatricule());

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
