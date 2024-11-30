package ensa.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    void gotoallproducts(){
        openInterface("/ensa/tableproduct.fxml", "products");
    }
    @FXML
    void gotoaddproduct(){
        openInterface("/ensa/addproduct.fxml", "ADD");

    }
    @FXML
    void gotoallcommercials(){
        openInterface("/ensa/allcommercials.fxml", "All Commercials");

    }
    @FXML
    void gotoaddcommercial(){
        openInterface("/ensa/addcommercial.fxml", "ADD com");
    }
    @FXML
    void gotoupdateproduct(){
        openInterface("/ensa/updateproduct.fxml", "ADD");

    }

    private void openInterface(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
