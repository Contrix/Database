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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author Jirka
 */
public class FXMLDocumentController implements Initializable {
    
    private ObservableList<Machine> machines = FXCollections.observableArrayList();
    private Machine actualView;
    
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
        System.out.println(machines.size());
        
        
    }
    
    @FXML
    private void handleAddMachineButtonAction(ActionEvent event) {
        System.out.println("Přidej stroj");
        AddMachineDialog dialog = new AddMachineDialog(manualLabel.getScene().getWindow());
        dialog.showAndWait();
    }
    
    @FXML
    private void handleChoiceInTreeAction() {
        setInformation(treeTreeView.getFocusModel().getFocusedItem().toString());
    }
    
    private void setInformation (String s){
        System.out.println(s.substring(18, s.length()-2));
        for (Machine m : machines){
            if(m.getName().equals(s.substring(18, s.length()-2))){
                actualView = m;
                break;
            }
        }
        
        nameLabel.setText(actualView.getName());
        codeLabel.setText(actualView.getCode());
        producerLabel.setText(actualView.getProducer());
        dateOfBuyingLabel.setText(actualView.getDateOfBuying().toString());
        placeOfBuyingLabel.setText(actualView.getPlaceOfBuying());
        priceLabel.setText(Integer.toString(actualView.getPrice()));
        guarantyLabel.setText(Integer.toString(actualView.getGuaranty()));
        manualLabel.setText(actualView.getManual());
        consumptionLabel.setText(Integer.toString(actualView.getConsumption()));
        informationLabel.setText(actualView.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        TreeItem<String> rootItem = new TreeItem<>("Stroje");

        TreeItem<String> saw = new TreeItem<>("Pily");
        TreeItem<String> miller = new TreeItem<>("Frézky");
        
        
        String[] pole = new String[]{"a", "b", "c", "d", "e", "f"};//
        String a = "Aaa";//
        String b = "Bbb";
        String c = "Ccc";
        
        for (String s : pole){//
            miller.getChildren().add(new TreeItem (s));
        }
        System.out.println("1");
        for (Machine m : machines){
            System.out.println("2");
            switch (m.getID()){
                case "Pila":
                    saw.getChildren().add(new TreeItem(m.getName()));
                    break;
                case "Frézka":
                    miller.getChildren().add(new TreeItem(m.getName()));
                    break;
                default:
                    break;
            }
        }

        saw.getChildren().addAll(new TreeItem<>(a), new TreeItem<>(a),new TreeItem<>(a));//
        


        rootItem.setExpanded(true);
        saw.setExpanded(true);
        miller.setExpanded(true);
        treeTreeView.setRoot(rootItem);
        rootItem.getChildren().addAll(saw, miller);
        //rootItem.getChildren().add(link2);
    }    
    
}
