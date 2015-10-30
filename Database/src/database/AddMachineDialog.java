/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
/**
 *
 * @author Jirka
 */
public class AddMachineDialog extends Stage{
    private List list;
    private final ObservableList<Label> errorLabels = FXCollections.observableArrayList();
    
    public Stage showDialog(Window parent, List list) {
        this.list = list;
        
        final Stage dialog = new Stage();
        dialog.initOwner(parent);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Přidat stroj");
        dialog.setWidth(550);
        dialog.setHeight(650);
        
        //layout
        VBox layoutVBox = new VBox();
        layoutVBox.setAlignment(Pos.CENTER);
        layoutVBox.setPadding(new Insets(10, 10, 10, 10));
        
        HBox hBox = new HBox();
        //vBox.setAlignment(Pos.CENTER);
        //vBox.setSpacing(20);
        
        VBox imagesVBox = new VBox();
        imagesVBox.setAlignment(Pos.CENTER);
        
        HBox imagesHBox = new HBox();

        // Mřížka s TextFieldy a Labely
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);

        // Komponenty mřížky
        ChoiceBox idChoiceBox = new ChoiceBox();
        idChoiceBox.setItems(FXCollections.observableArrayList("Pila", "Frézka"));
        idChoiceBox.setValue(idChoiceBox.getItems().get(0));
        idChoiceBox.setPrefWidth(150);
        
        TextField nameTextField = new TextField();
        TextField codeTextField = new TextField();
        TextField producerTextField = new TextField();
        TextField dateOfBuyingTextField = new TextField();
        TextField placeOfBuyingTextField = new TextField();
        TextField priceTextField = new TextField();
        TextField guarantyTextField = new TextField();
        TextField manualTextField = new TextField();
        TextField consumptionTextField = new TextField();

        Label idLabel = new Label("Stroj");
        Label nameLabel = new Label("Název");
        Label codeLabel = new Label("Kód");
        Label producerLabel = new Label("Výrobce");
        Label dateOfBuyingLabel = new Label("Datum nákupu");
        Label placeOfBuyingLabel = new Label("Místo nákupu");
        Label priceLabel = new Label("Cena");
        Label guarantyLabel = new Label("Záruka");
        Label manualLabel = new Label("Manuál");
        Label consumptionLabel = new Label("Spotřeba");
        
        Label idError = new Label("!");
        Label nameError = new Label("!");
        Label codeError = new Label("!");
        Label producerError = new Label("!");
        Label dateOfBuyingError = new Label("!");
        Label placeOfBuyingError = new Label("!");
        Label priceError = new Label("!");
        Label guarantyError = new Label("!");
        Label manualError = new Label("!");
        Label consumptionError = new Label("!");
        Label imagesError = new Label("!");
        
        errorLabels.addAll(idError, nameError, codeError, producerError, priceError, dateOfBuyingError, placeOfBuyingError, priceError, guarantyError, manualError, consumptionError, imagesError);
        
        for (Label l :errorLabels){
            l.setStyle("-fx-text-fill: red;-fx-font-size: 20; ");
            l.setVisible(false);
        }

        grid.add(idLabel, 0, 0);
        grid.add(idChoiceBox, 1, 0);
        grid.add(idError, 2, 0);
        
        grid.add(nameLabel, 0, 1);
        grid.add(nameTextField, 1, 1);
        grid.add(nameError, 2, 1);
        
        grid.add(codeLabel, 0, 2);
        grid.add(codeTextField, 1, 2);
        grid.add(codeError, 2, 2);
        
        grid.add(producerLabel, 0, 3);
        grid.add(producerTextField, 1, 3);
        grid.add(producerError, 2, 3);
        
        grid.add(dateOfBuyingLabel, 0, 4);
        grid.add(dateOfBuyingTextField, 1, 4);
        grid.add(dateOfBuyingError, 2, 4);
        
        grid.add(placeOfBuyingLabel, 0, 5);
        grid.add(placeOfBuyingTextField, 1, 5);
        grid.add(placeOfBuyingError, 2, 5);
        
        grid.add(priceLabel, 0, 6);
        grid.add(priceTextField, 1, 6);
        grid.add(priceError, 2, 6);
        
        grid.add(guarantyLabel, 0, 7);
        grid.add(guarantyTextField, 1, 7);
        grid.add(guarantyError, 2, 7);
        
        grid.add(manualLabel, 0, 8);
        grid.add(manualTextField, 1, 8);
        grid.add(manualError, 2, 8);
        
        grid.add(consumptionLabel, 0, 9);
        grid.add(consumptionTextField, 1, 9);
        grid.add(consumptionError, 2, 9);
        
        //Komponenty pro imagesVBox
        ImageView imageView = new ImageView();
        try{
            //file:/D:/Programming/JavaFX/Database/Database/empty.png
            /*File file = new File("empty.png");
        Image image = new Image(file.toURI().toString());
        System.out.println(file.toURI().toString());
        imageView.setImage(image);*/
            
            
           imageView.setImage(new Image("file:empty.png")); 
        }
        catch(Exception e){
            System.out.println("ne" + e);
        }
        
        //Tlačítko výběr fotky
        Button choiceImagesButton = new Button("Vybrat fotky");
        choiceImagesButton.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            //fileChooser.showOpenDialog(stage);
        });
        
        TextField imagesTextField = new TextField();        
        
        //poznámky textArea
        TextArea noteTextArea = new TextArea();
        noteTextArea.setPromptText("Poznámky");
        
        //parametry textArea
        TextArea parametrTextArea = new TextArea();
        parametrTextArea.setPromptText("Parametry");
       
        

        // Tlačítko přidat stroj
        Button addButton = new Button("Přidat stroj");
        addButton.setOnAction((ActionEvent event) -> {
            // Obsluha tlačítka
            for (Label l :errorLabels){
                l.setVisible(false);
            }            
            try{
                list.addMachine(new Machine(nameTextField.getText(), 
                        codeTextField.getText(), 
                        producerTextField.getText(), 
                        dateOfBuyingTextField.getText(), //datum
                        placeOfBuyingTextField.getText(), 
                        Integer.parseInt(priceTextField.getText()), 
                        Integer.parseInt(guarantyTextField.getText()), 
                        manualTextField.getText(), 
                        Integer.parseInt(consumptionTextField.getText()), 
                        imagesTextField.getText(), 
                        noteTextArea.getText(), 
                        parametrTextArea.getText(), 
                        idChoiceBox.getValue().toString()));
            }
            catch (Exception e){
                try {
                    nameTextField.getText();
                } catch (Exception a) {
                    nameError.setVisible(true);
                }
                try {
                    codeTextField.getText();
                } catch (Exception a) {
                    codeError.setVisible(true);
                }
                try {
                    producerTextField.getText();
                } catch (Exception a) {
                    producerError.setVisible(true);
                }
                /*try {
                    Integer.parseInt(priceTextField.getText()); datum
                } catch (Exception a) {
                    priceError.setVisible(true);
                }*/
                try {
                    placeOfBuyingTextField.getText();
                } catch (Exception a) {
                    placeOfBuyingError.setVisible(true);
                }
                try {
                    Integer.parseInt(priceTextField.getText());
                } catch (Exception a) {
                    priceError.setVisible(true);
                }
                try {
                    Integer.parseInt(guarantyTextField.getText());
                } catch (Exception a) {
                    guarantyError.setVisible(true);
                }
                try {
                    manualTextField.getText();
                } catch (Exception a) {
                    manualError.setVisible(true);
                }
                try {
                    Integer.parseInt(consumptionTextField.getText());
                } catch (Exception a) {
                    consumptionError.setVisible(true);
                }
                try {
                    idChoiceBox.getValue().toString();
                } catch (Exception a) {
                    idError.setVisible(true);
                }
            }
            dialog.close();
        });           

        layoutVBox.getChildren().addAll(hBox, parametrTextArea, noteTextArea, addButton);
        hBox.getChildren().addAll(grid, imagesVBox);
        imagesVBox.getChildren().addAll(imageView, imagesHBox);
        imagesHBox.getChildren().addAll(choiceImagesButton, imagesTextField);
        
        Scene scene = new Scene(layoutVBox);        
        dialog.setScene(scene);
        dialog.show();
        return dialog;
    }
}
