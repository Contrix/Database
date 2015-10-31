/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Jirka
 */
public class Database extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        File backGroundImage = new File("Data/bg.png");
        root.setStyle("-fx-background-image: url('" + backGroundImage.toURI().toURL().toString() + "');"
                + "-fx-background-position: center center;"
                + "-fx-background-size: 2000 1200"); 
        
        Scene scene = new Scene(root);
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            switch(e.getCode()){
                case ESCAPE:
                    stage.close();
                    break;
                    
                default:
                    break;
            }
        });
        
        stage.setScene(scene);
        stage.setTitle("Databáze");
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
