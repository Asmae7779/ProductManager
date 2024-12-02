package ensa;

import ensa.dao.imp.CommercialDaoImp;
import ensa.util.DbConnexion;

import java.io.IOException;
import java.sql.Connection;
import ensa.dao.imp.ProductImp;
import ensa.model.product;
import ensa.model.commercial;
import ensa.util.DbConnexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 618, 418);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {

        launch(args);
    }

}
