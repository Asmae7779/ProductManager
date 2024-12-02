package ensa.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class HomeController {

    @FXML
    void gotoallproducts(ActionEvent event) {
        openInterface("/ensa/tableproduct.fxml", "Products", event);
    }

    @FXML
    void gotoaddproduct(ActionEvent event) {
        openInterface("/ensa/addproduct.fxml", "Add Product", event);
    }

    @FXML
    void gotoallcommercials(ActionEvent event) {
        openInterface("/ensa/allcommercials.fxml", "All Commercials", event);
    }

    @FXML
    void gotoaddcommercial(ActionEvent event) {
        openInterface("/ensa/addcommercial.fxml", "Add Commercial", event);
    }

    @FXML
    void gotoupdateproduct(ActionEvent event) {
        openInterface("/ensa/updateproduct.fxml", "Update Product", event);
    }

    public void openInterface(String fxmlPath, String title, ActionEvent event) {
        try {
            // Charger la nouvelle interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Créer un nouveau Stage pour la nouvelle interface
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer l'ancienne fenêtre
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
