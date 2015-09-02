/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author Jirka
 */
public class FXMLDocumentController implements Initializable {
    
    private ObservableList<Machine> machine = FXCollections.observableArrayList();
    
    @FXML
    private Label nameLabel;
    @FXML
    private Label codeLabel;
    @FXML
    private Label producerLabel;
    @FXML
    private Label dateOfBuyingLabel;
    @FXML
    private Label placeOfBuyingLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label guarantyLabel;
    @FXML
    private Label manualLabel;
    @FXML
    private Label consumptionLabel;
    
    
    @FXML
    private Label informationLabel;
    
    @FXML
    private Button addMachineButton;
    
    @FXML
    private TextField searchTextField;
    
    @FXML
    private TreeView treeTreeView;
    
    

    @FXML
    private void handleSearchTextFieldAction(ActionEvent event) {
        System.out.println("Hledám");

        /*TreeItem<String> root = new TreeItem<String>("Root Node");
        root.setExpanded(true);
        root.getChildren().addAll(
            new TreeItem<String>("Item 1"),
            new TreeItem<String>("Item 2"),
            new TreeItem<String>("Item 3")
        );

        treeTreeView.setRoot(root);*/
        
        
        
    }
    
    @FXML
    private void handleAddMachineButtonAction(ActionEvent event) {
        System.out.println("Přidej stroj");
        AddMachineDialog dialog = new AddMachineDialog(manualLabel.getScene().getWindow());
        dialog.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String[] pole = new String[]{"a", "b", "c", "d", "e", "f"};
        
        TreeItem<String> rootItem = new TreeItem<String>("Stroje");

        TreeItem<String> saw = new TreeItem<String>("Pily");
        TreeItem<String> miller = new TreeItem<String>("Frézky");
        
        String a = new String("Aaa");
        String b = new String("Bbb");
        String c = new String("Ccc");
        
        for (String s : pole){
            miller.getChildren().add(new TreeItem (s));
        }

        saw.getChildren().addAll(new TreeItem<String>(a), new TreeItem<String>(a),new TreeItem<String>(a));
        //miller.getChildren().addAll(new TreeItem<String>(a), new TreeItem<String>(a),new TreeItem<String>(a));


        rootItem.setExpanded(true);
        treeTreeView.setRoot(rootItem);
        rootItem.getChildren().addAll(saw, miller);
        //rootItem.getChildren().add(link2);
    }    
    
}
