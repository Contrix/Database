/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jirka
 */
public class List {    
    private ObservableList<Machine> machines = FXCollections.observableArrayList();
    JSONParser parser = new JSONParser();
    
    public List(){
        try{
            //JSONObject JsonObj = (JSONObject) parser.parse(new FileReader("soubor.json"));
            JSONArray JsonArray = (JSONArray)parser.parse(new FileReader("Data/soubor.json"));//(JSONArray) JsonObj.get("Jmachines");
            for (int i = 0; i < JsonArray.size(); i++){
                JSONObject o = (JSONObject)JsonArray.get(i);
                machines.add(new Machine(o.get("name").toString(), o.get("code").toString(), o.get("producer").toString(), o.get("dateOfBuying").toString(), o.get("placeOfBuying").toString(), Integer.parseInt(o.get("price").toString()), Integer.parseInt(o.get("guaranty").toString()), o.get("manual").toString(), Integer.parseInt(o.get("consumption").toString()), o.get("images").toString(), o.get("text").toString(), o.get("parametr").toString()));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public ObservableList<Machine> getMachines(){
        return machines;
    }
    
    public void addMachine(Machine machine){
        machines.add(machine);
        save();
    }
    
    public void removeMachine(Machine machine){
        machines.remove(machine);
        save();
    }
    
    /*public JSONArray getJmachines(){
        return Jmachines;
    }
    
    public void addJmachine(JSONObject o){
        Jmachines.add(o);
    }*/
    
    
    public void save(){
        JSONArray Jmachines = new JSONArray();
        for (Machine m : machines) {
            JSONObject o = new JSONObject();
            o.put("name", m.getName());
                o.put("code", m.getCode());
                o.put("producer", m.getProducer());
                o.put("dateOfBuying", m.getDateOfBuying());
                o.put("placeOfBuying", m.getPlaceOfBuying());
                o.put("price", m.getPrice());
                o.put("guaranty", m.getGuaranty());
                o.put("manual", m.getManual());
                o.put("consumption", m.getConsumption());
                o.put("images", m.getImages());
                o.put("text", m.getText());
                o.put("parametr", m.getParametr());
                Jmachines.add(o);
        }
        
        
        try (FileWriter file = new FileWriter("Data/soubor.json")) {
            //Jmachines.addAll(machines);
            //file.write(o.toJSONString());
            file.write(Jmachines.toJSONString());
        } catch(IOException exc) {
            System.err.println(exc);
        } 
    }
}
