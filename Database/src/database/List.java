/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;
import javafx.util.converter.LocalDateStringConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jirka
 */
public class List {    
    //300
    private ObservableList<Machine> machines = FXCollections.observableArrayList();
    JSONParser parser = new JSONParser();
    
    public List(){
        try{
            //JSONObject JsonObj = (JSONObject) parser.parse(new FileReader("soubor.json"));
            JSONArray JsonArray = (JSONArray)parser.parse(new FileReader("Data/soubor.json"));//(JSONArray) JsonObj.get("Jmachines");
            for (int i = 0; i < JsonArray.size(); i++){
                JSONObject o = (JSONObject)JsonArray.get(i);
                machines.add(new Machine(o.get("name").toString(), 
                        o.get("code").toString(), 
                        o.get("producer").toString(), 
                        LocalDate.parse(o.get("dateOfBuying").toString().substring(1, o.get("dateOfBuying").toString().length())),
                        o.get("placeOfBuying").toString(), 
                        Integer.parseInt(o.get("price").toString()), 
                        Integer.parseInt(o.get("guaranty").toString()), 
                        o.get("manual").toString(), 
                        Integer.parseInt(o.get("consumption").toString()), 
                        o.get("images").toString(), 
                        o.get("text").toString(), 
                        o.get("parametr").toString()));
            }
        }
        catch (Exception e){
            System.out.println("300 " + e);
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
        try{
            Files.delete(Paths.get(machine.getImages()));
            Files.delete(Paths.get(machine.getManual()));
        }
        catch(Exception e){
            System.out.println("304 " + "Deleting failed" + e);
        }
        save();
    }
    
    public void moveUp(Machine machine){
        try{
            int index = machines.lastIndexOf(machine);
            Machine m = machines.get(index - 1);
            machines.set(index - 1, machine);
            machines.set(index, m);
        }
        catch (Exception e){
            System.out.println("301 " + "Invalid request " + e);
        }
        save();
    }
    
    public void moveDown(Machine machine){
        try{
            int index = machines.lastIndexOf(machine);
            Machine m = machines.get(index + 1);
            machines.set(index + 1, machine);
            machines.set(index, m);
            }
        catch (Exception e){
            System.out.println("302 " + "Invalid request " + e);
        }
        save();
    }
    
    public void save(){
        JSONObject jObject = new JSONObject();
        JSONArray Jmachines = new JSONArray();
        for (Machine m : machines) {
            JSONObject o = new JSONObject();
            o.put("name", m.getName());
                o.put("code", m.getCode());
                o.put("producer", m.getProducer());
                o.put("dateOfBuying", "d" + m.getDateOfBuying());
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
        jObject.put("machines", Jmachines);
        
        try (FileWriter file = new FileWriter("Data/soubor.json")) {
            //Jmachines.addAll(machines);
            //file.write(o.toJSONString());
            file.write(Jmachines.toJSONString());
        } catch(IOException exc) {
            System.out.println("303 " + exc);
        } 
    }
}
