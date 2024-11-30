package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.dao.imp.ProductImp;
import ensa.model.commercial;
import ensa.model.product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class UpdateProduct {
    @FXML
    private TextField nom ;
    @FXML
    private TextField description ;
    @FXML
    private TextField prix ;
    @FXML
    private TextField commercial ;
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
        List<commercial> commercials = commercialDao.getAllCommercials();
        commercial selectedCommercial = null;
        for (commercial com : commercials) {
            if (com.getNom().equals(matriculevalue)) { // Comparez avec le nom
                selectedCommercial = com;
                break;
            }
        }
        pimp.UpdateProduct(selectedProduct,nomvalue,descriptionvalue,prixd,selectedCommercial);



    }
}
