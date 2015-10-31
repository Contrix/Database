/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author Jirka
 */
public class OpenImageDialog extends Stage{
    //600
    
    public Stage showDialog(Window parent, String path) {
        
        final Stage dialog = new Stage();
        Image img = new Image("file:" + path);
        ImageView imageView = new ImageView(img);
        dialog.initOwner(parent);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setWidth(img.getWidth());
        dialog.setHeight(img.getHeight());
        
        imageView.setOnMouseClicked((MouseEvent)->{
            dialog.close();
        });
        
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(imageView);
        
        Scene scene = new Scene(anchorPane);        
        dialog.setScene(scene);
        dialog.show();
        return dialog;
    }
    
}
