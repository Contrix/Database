/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.nio.file.Files;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.Locale;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    //500
    private final ObservableList<Label> errorLabels = FXCollections.observableArrayList();
    
    public Stage showDialog(Window parent, List list) {
        
        final Stage dialog = new Stage();
        dialog.initOwner(parent);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Přidat stroj");
        dialog.setWidth(600);
        dialog.setHeight(650);
        
        //layout
        VBox layoutVBox = new VBox();
        layoutVBox.setAlignment(Pos.CENTER);
        layoutVBox.setPadding(new Insets(10, 10, 10, 10));
        layoutVBox.setSpacing(10);
        
        HBox hBox = new HBox();
        //vBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        
        VBox imagesVBox = new VBox();
        imagesVBox.setSpacing(20);
        imagesVBox.setAlignment(Pos.CENTER);
        
        HBox imagesHBox = new HBox();
        imagesHBox.setSpacing(5);

        // Mřížka s TextFieldy a Labely
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);

        // Komponenty mřížky
        /*ChoiceBox idChoiceBox = new ChoiceBox();
        idChoiceBox.setItems(FXCollections.observableArrayList("Pila", "Frézka"));
        idChoiceBox.setValue(idChoiceBox.getItems().get(0));
        idChoiceBox.setPrefWidth(150);*/
        
        TextField nameTextField = new TextField();
        TextField codeTextField = new TextField();
        TextField producerTextField = new TextField();
        //TextField dateOfBuyingTextField = new TextField();
        TextField placeOfBuyingTextField = new TextField();
        TextField priceTextField = new TextField();
        TextField guarantyTextField = new TextField();
        TextField manualTextField = new TextField();
        TextField consumptionTextField = new TextField();

        Label nameLabel = new Label("Název");
        Label codeLabel = new Label("Kód");
        Label producerLabel = new Label("Výrobce");
        Label dateOfBuyingLabel = new Label("Datum nákupu");
        Label placeOfBuyingLabel = new Label("Místo nákupu");
        Label priceLabel = new Label("Cena");
        Label guarantyLabel = new Label("Záruka");
        Label manualLabel = new Label("Manuál");
        Label consumptionLabel = new Label("Spotřeba");
        
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
        
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("dd.MM.yyyy");
        //dataPicker.setEditable(false);
        
        errorLabels.addAll(nameError, codeError, producerError, priceError, dateOfBuyingError, placeOfBuyingError, priceError, guarantyError, manualError, consumptionError, imagesError);
        
        for (Label l :errorLabels){
            l.setStyle("-fx-text-fill: red;-fx-font-size: 20; ");
            l.setVisible(false);
        }

        grid.add(nameLabel, 0, 0);
        grid.add(nameTextField, 1, 0);
        grid.add(nameError, 2, 0);
        
        grid.add(codeLabel, 0, 1);
        grid.add(codeTextField, 1, 1);
        grid.add(codeError, 2, 1);
        
        grid.add(producerLabel, 0, 2);
        grid.add(producerTextField, 1, 2);
        grid.add(producerError, 2, 2);
        
        grid.add(dateOfBuyingLabel, 0, 3);
        grid.add(datePicker, 1, 3);
        grid.add(dateOfBuyingError, 2, 3);
        
        grid.add(placeOfBuyingLabel, 0, 4);
        grid.add(placeOfBuyingTextField, 1, 4);
        grid.add(placeOfBuyingError, 2, 4);
        
        grid.add(priceLabel, 0, 5);
        grid.add(priceTextField, 1, 5);
        grid.add(priceError, 2, 5);
        
        grid.add(guarantyLabel, 0, 6);
        grid.add(guarantyTextField, 1, 6);
        grid.add(guarantyError, 2, 6);
        
        grid.add(manualLabel, 0, 7);
        grid.add(manualTextField, 1, 7);
        grid.add(manualError, 2, 7);
        
        grid.add(consumptionLabel, 0, 8);
        grid.add(consumptionTextField, 1, 8);
        grid.add(consumptionError, 2, 8);
        
        manualTextField.setOnMousePressed((MouseEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(dialog);
                if (file != null) {
                    manualTextField.setText(file.getPath());

                }
        });
        
        /*dateOfBuyingTextField.setOnMouseClicked((MouseEvent)->{
            DatePicker datePicker = new DatePicker();
            datePicker.showWeekNumbersProperty();
        });*/
        
        //Komponenty pro imagesVBox
        ImageView imageView = new ImageView();
        try{
            imageView.setImage(new Image("file:Data/empty.png")); 
        }
        catch(Exception e){
            System.out.println("501" + e);
        }
        
        TextField imageTextField = new TextField(); 
        
        //Tlačítko výběr fotky
        Button choiceImagesButton = new Button("Vybrat fotku");
        choiceImagesButton.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(dialog);
                if (file != null) {
                    imageView.setImage(new Image(file.toURI().toString(), 200, 200, false, false));
                    imageTextField.setText(file.getPath());

                }
        });

        //poznámky textArea
        TextArea noteTextArea = new TextArea();
        noteTextArea.setPromptText("Poznámky");
        
        //parametry textArea
        TextArea parametrTextArea = new TextArea();
        parametrTextArea.setPromptText("Parametry");
        
        // Tlačítko přidat stroj
        Button addButton = new Button("Přidat stroj");
        addButton.setOnAction((ActionEvent event) -> {
            try{
                Files.copy(Paths.get(imageTextField.getText()),
                        Paths.get(("Data/img/") + nameTextField.getText() + imageTextField.getText().substring(imageTextField.getText().length()-4, imageTextField.getText().length())));
                imageTextField.setText("Data/img/" + nameTextField.getText() + imageTextField.getText().substring(imageTextField.getText().length()-4, imageTextField.getText().length()));
            }
            catch (Exception e){
                System.out.println("502 " + "Copy image failed" + e);
            }
            
            try{
                Files.copy(Paths.get(manualTextField.getText()),
                        Paths.get(("Data/manuals/") + nameTextField.getText() + manualTextField.getText().substring(manualTextField.getText().length()-4, manualTextField.getText().length())));
                manualTextField.setText("Data/manuals/" + nameTextField.getText() + manualTextField.getText().substring(manualTextField.getText().length()-4, manualTextField.getText().length()));
            }
            catch (Exception e){
                System.out.println("503 " + "Copy manual failed" + e);
            }
            
            list.addMachine(new Machine(nameTextField.getText(), 
                    codeTextField.getText(), 
                    producerTextField.getText(), 
                    datePicker.getValue(),
                    placeOfBuyingTextField.getText(), 
                    Integer.parseInt(priceTextField.getText()), 
                    Integer.parseInt(guarantyTextField.getText()), 
                    manualTextField.getText(), 
                    Integer.parseInt(consumptionTextField.getText()), 
                    imageTextField.getText(), 
                    noteTextArea.getText(), 
                    parametrTextArea.getText()));//,idChoiceBox.getValue().toString()
            
            dialog.close();
        });
        
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (nameTextField.getText().length() <= 4){
                nameError.setVisible(true);
                checkErrors(addButton);
            }
            else{
                nameError.setVisible(false);
                checkErrors(addButton);
            }
        });
        
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            try{
                LocalDate.parse(datePicker.getValue().toString());
                dateOfBuyingError.setVisible(false);
                checkErrors(addButton);
            }
            catch (Exception e){
                dateOfBuyingError.setVisible(true);
                checkErrors(addButton);
            }
        });

        datePicker.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            try{
                LocalDate.parse(datePicker.getValue().toString());
                dateOfBuyingError.setVisible(false);
                checkErrors(addButton);
            }
            catch (Exception e){
                dateOfBuyingError.setVisible(true);
                checkErrors(addButton);
            }
        });
        
        
        priceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
                priceError.setVisible(false);
                checkErrors(addButton);
            }
            catch (Exception e){
                priceError.setVisible(true);
                checkErrors(addButton);
            }
        });
        
        guarantyTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
                guarantyError.setVisible(false);
                checkErrors(addButton);
            }
            catch (Exception e){
                guarantyError.setVisible(true);       
                checkErrors(addButton);
            }
        });
        
        manualTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Files.exists(Paths.get(newValue))){
                manualError.setVisible(false);
                checkErrors(addButton);
            }
            else{
                manualError.setVisible(true);
                checkErrors(addButton);
            }
        });
        
        consumptionTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
                consumptionError.setVisible(false);
                checkErrors(addButton);
            }
            catch (Exception e){
                consumptionError.setVisible(true);
                checkErrors(addButton);
            }
        });
        
        imageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Files.exists(Paths.get(newValue))){
                imagesError.setVisible(false);
                checkErrors(addButton);
            }
            else{
                imagesError.setVisible(true);
                checkErrors(addButton);
            }
        });
        
        if (errorLabels.stream().allMatch(l -> !l.isVisible())){
            addButton.setDisable(false);
            checkErrors(addButton);
        }
        else{
            addButton.setDisable(true);
            checkErrors(addButton);
        }
        

        layoutVBox.getChildren().addAll(hBox, parametrTextArea, noteTextArea, addButton);
        hBox.getChildren().addAll(grid, imagesVBox);
        imagesVBox.getChildren().addAll(imageView, imagesHBox);
        imagesHBox.getChildren().addAll(choiceImagesButton, imageTextField, imagesError);
        
        Scene scene = new Scene(layoutVBox);        
        dialog.setScene(scene);
        dialog.show();
        return dialog;
    }
    
    private void checkErrors(Button button){
        if (errorLabels.stream().allMatch(l -> !l.isVisible())){
            button.setDisable(false);
        }
        else{
            button.setDisable(true);
        }
    }
}
