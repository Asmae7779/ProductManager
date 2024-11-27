package ensa.controller;

import ensa.dao.imp.CommercialDaoImp;
import ensa.dao.imp.ProductImp;
import ensa.model.commercial;
import ensa.model.product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
            System.out.println("recherche reussi");
        }
        commercial selectedCommercial = null;
        for (commercial com : commercials) {
            if (com.getNom().equals(commercialName)) { // Comparez avec le nom
                selectedCommercial = com;
                break; // Si trouvé, sortez de la boucle
            }
        }

        // Vérifiez si le commercial a été trouvé
        if (selectedCommercial != null) {
            product pr = new product(nomvalue, descriptionvalue, prixDouble, selectedCommercial);
            imp.Add(pr);
        } else {
            // Gérer le cas où le commercial n'est pas trouvé
            System.out.println("Commercial non trouvé : " + commercialName);
            // Vous pouvez afficher un message d'erreur à l'utilisateur ici
        }
    }
}
