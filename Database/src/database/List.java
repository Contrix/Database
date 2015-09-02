/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jirka
 */
public class List {
    
    private ObservableList<Machine> machines = FXCollections.observableArrayList();
    
    public ObservableList<Machine> getMachines(){
        return machines;
    }
    
    public void addMachine(Machine machine){
        machines.add(machine);
    }
    
    public void removeMachine(Machine machine){
        machines.remove(machine);
    }
}
