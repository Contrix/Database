/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author Jirka
 */
public class OpenImageDialog extends Stage{
    
    public Stage showDialog(Window parent, String URI) {
        
        final Stage dialog = new Stage();
        Image img = new Image(URI);
        ImageView imageView = new ImageView(img);
        dialog.initOwner(parent);
        dialog.initStyle(StageStyle.UTILITY);
        //dialog.setTitle("Přidat stroj");
        dialog.setWidth(img.getWidth());
        dialog.setHeight(img.getHeight());
        
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(imageView);
        
        Scene scene = new Scene(anchorPane);        
        dialog.setScene(scene);
        dialog.show();
        return dialog;
    }
    
}
