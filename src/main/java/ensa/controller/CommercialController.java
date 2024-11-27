package ensa.controller;
import ensa.dao.imp.CommercialDaoImp;
import ensa.model.commercial ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CommercialController {

    @FXML
    private TextField nom ;
    @FXML
    private TextField prenom ;
    @FXML
    private TextField matricule ;



    public void handleAddCommercial(ActionEvent actionEvent) {
        String nomv = nom.getText();
        String prenomv = prenom.getText();
        String matriculev = matricule.getText();
        commercial com = new commercial(nomv,prenomv,matriculev);
        CommercialDaoImp imp = new CommercialDaoImp();
        boolean p = imp.addCommercial(com);
        if(p){
            System.out.println("Ajout reussi");
        }else{
            System.out.println("Ajout echoue");
        }




    }

}
