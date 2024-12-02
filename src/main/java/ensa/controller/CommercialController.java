package ensa.controller;
import ensa.dao.imp.CommercialDaoImp;
import ensa.model.commercial ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
