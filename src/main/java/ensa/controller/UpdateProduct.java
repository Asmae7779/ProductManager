package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.dao.imp.ProductImp;
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

public class UpdateProduct {
    @FXML
    private TextField nom ;
    @FXML
    private TextField id ;
    @FXML
    private TextField description ;
    @FXML
    private TextField prix ;
    @FXML
    private TextField commercial ;
    @FXML
    private product currentProduct;

    public void setProduct(product product) {
        this.currentProduct = product;  // Stocke le produit sélectionné dans la variable currentProduct


        id.setText(String.valueOf(product.getId()));           // Remplir le champ ID
        nom.setText(product.getNom());                          // Remplir le champ Nom
        description.setText(product.getDescription());                 // Remplir le champ Description
        prix.setText(String.valueOf(product.getPrix()));        // Remplir le champ Prix
        commercial.setText(product.getCommercial().getNom()); // Remplir le champ Commercial
        loadProductDetails(product);
    }
    private void loadProductDetails(product product) {
        System.out.println("Détails du produit : " + product.getNom());
        handleUpdateProduct();
        // Vous pouvez mettre à jour les éléments de l'interface utilisateur ici
    }



    public void handleDeleteProduct(){
        String nomvalue = nom.getText();
        String descriptionvalue = description.getText();
        String prixvalue = prix.getText();
        double prixd = Double.parseDouble(prixvalue);
        String matriculevalue = commercial.getText();
        if (nomvalue.isEmpty() || descriptionvalue.isEmpty() || prixvalue.isEmpty() || matriculevalue.isEmpty()) {
            System.out.println("Erreur : tous les champs doivent être remplis.");
            return;
        }
        product selectedProduct = null ;
        ProductImp pimp = new ProductImp();
        List<product> allproducts = new ArrayList<>();
        allproducts = pimp.getAllProducts();

        for(product pd:allproducts) {
            if(pd.getNom().equals(nomvalue)){
                selectedProduct=pd;
                break;
            }


        }
        pimp.DeleteProduct(selectedProduct);
    }
    public void handleUpdateProduct(){
        String nomvalue = nom.getText();
        String descriptionvalue = description.getText();
        String prixvalue = prix.getText();
        double prixd = Double.parseDouble(prixvalue);
        String matriculevalue = commercial.getText();

        if (nomvalue.isEmpty() || descriptionvalue.isEmpty() || prixvalue.isEmpty() || matriculevalue.isEmpty()) {
            System.out.println("Erreur : tous les champs doivent être remplis.");
            return;
        }


        ProductImp pimp = new ProductImp();
        List<product> allproducts = new ArrayList<>();
        allproducts = pimp.getAllProducts();
        product selectedProduct = null ;
        for(product pd:allproducts) {
            if(pd.getNom().equals(nomvalue)){
                selectedProduct=pd;
                break;
            }

        }
        if(selectedProduct==null){
            System.out.println("Erreur : produit introuvable ");
        }
        CommercialDaoImp commercialDao = new CommercialDaoImp();
        List<commercial> commercials = new ArrayList<>();
        commercials = commercialDao.getAllCommercials();
        commercial selectedCommercial = null;
        for (commercial com : commercials) {
            if (com.getNom().equals(matriculevalue)) { // Comparez avec le nom
                selectedCommercial = com;
                break;
            }
        }
        pimp.UpdateProduct(selectedProduct,nomvalue,descriptionvalue,prixd,selectedCommercial);



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
