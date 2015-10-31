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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

/**
 *
 * @author Jirka
 */
public class FXMLDocumentController implements Initializable {
    
    private Machine actualView;
    private List list = new List();
    private AddMachineDialog addMachineDialog = new AddMachineDialog();
    private OpenImageDialog openImageDialog = new OpenImageDialog();
    
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
    private Button goHomeButton;   
    @FXML
    private TextField searchTextField;    
    @FXML
    private TreeView treeTreeView;
    @FXML
    private ImageView imageView;

    
    

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
        System.out.println("Add machine");
        addMachineDialog.showDialog(manualLabel.getScene().getWindow(), list);
        initialize(null, null);
    }
    
    @FXML
    private void handleGoHomeButtonAction(ActionEvent event) {
        System.out.println("Go home");
    }
    
    @FXML
    private void handleChoiceInTreeAction() {
        setInformation(treeTreeView.getFocusModel().getFocusedItem().toString());
    }
    
    @FXML
    private void handleContextMenuRemoveRequest() {
        for (Machine m : list.getMachines()){
            if(m.getName().equals(treeTreeView.getFocusModel().getFocusedItem().toString().substring(18, treeTreeView.getFocusModel().getFocusedItem().toString().length()-2))){
                System.out.println("Deleting " + m.toString());
                list.removeMachine(m);
                break;
            }
        }
        initialize(null, null);
    }
    
    @FXML
    private void handleImageOnMouceClicked() {      
        openImageDialog.showDialog(manualLabel.getScene().getWindow(), actualView.getImages());
    }
    
    private void setInformation (String s){
        //System.out.println(s.substring(18, s.length()-2));
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
        try{
            imageView.setImage(new Image(actualView.getImages()));
        }
        catch (Exception e){
            imageView.setImage(new Image("file:Data/empty.png", 300, 300, false, false));
            System.err.println("Unknown image");
        }
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
