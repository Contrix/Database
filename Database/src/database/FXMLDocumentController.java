/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Window;

/**
 *
 * @author Jirka
 */
public class FXMLDocumentController implements Initializable {
    
    private Machine actualView;
    private List list = new List();
    private AddMachineDialog dialog = new AddMachineDialog();
    
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
    private Label notesLabel;    
    @FXML
    private Button addMachineButton;
    @FXML
    private Button removeMachineButton;   
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
        System.out.println(list.getMachines().size());
        
        
    }
    
    @FXML
    private void handleAddMachineButtonAction(ActionEvent event) {
        System.out.println("Přidej stroj");
        dialog.showDialog(manualLabel.getScene().getWindow(), list);
        initialize(null, null);
    }
    
    @FXML
    private void handleRemoveMachineButtonAction(ActionEvent event) {
        System.out.println("Odebírám");
        list.removeMachine(actualView);
        
        initialize(null, null);
    }
    
    @FXML
    private void handleChoiceInTreeAction() {
        setInformation(treeTreeView.getFocusModel().getFocusedItem().toString());
    }
    
    @FXML
    private void handleContextMenuRequest() {
        setInformation(treeTreeView.getFocusModel().getFocusedItem().toString());
    }
    
    private void setInformation (String s){
        System.out.println(s.substring(18, s.length()-2));
        for (Machine m : list.getMachines()){
            if(m.getName().equals(s.substring(18, s.length()-2))){
                actualView = m;
                break;
            }
        }
        
        nameLabel.setText(actualView.getName());
        codeLabel.setText(actualView.getCode());
        producerLabel.setText(actualView.getProducer());
        dateOfBuyingLabel.setText(actualView.getDateOfBuying());
        placeOfBuyingLabel.setText(actualView.getPlaceOfBuying());
        priceLabel.setText(Integer.toString(actualView.getPrice()));
        guarantyLabel.setText(Integer.toString(actualView.getGuaranty()));
        manualLabel.setText(actualView.getManual());
        consumptionLabel.setText(Integer.toString(actualView.getConsumption()));
        informationLabel.setText(actualView.getParametr());
        notesLabel.setText(actualView.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        TreeItem<String> rootItem = new TreeItem<>("Stroje");
        /*TreeItem<String> saw = new TreeItem<>("Pily");
        TreeItem<String> miller = new TreeItem<>("Frézky");*/
        /*String[] pole = new String[]{"a", "b", "c", "d", "e", "f"};//
        String a = "Aaa";//
        String b = "Bbb";
        String c = "Ccc";*/        
        /*for (String s : pole){//
            miller.getChildren().add(new TreeItem (s));
        }*/
        for (Machine m : list.getMachines()){
            /*switch (m.getID()){
                case "Pila":
                    saw.getChildren().add(new TreeItem(m.getName()));
                    break;
                case "Frézka":
                    miller.getChildren().add(new TreeItem(m.getName()));
                    break;
                default:
                    break;
            }*/
            rootItem.getChildren().add(new TreeItem(m.getName()));
        }

        //saw.getChildren().addAll(new TreeItem<>(a), new TreeItem<>(a),new TreeItem<>(a));//
        rootItem.setExpanded(true);
        treeTreeView.setRoot(rootItem);
        //rootItem.getChildren().add(link2);
    }
}
