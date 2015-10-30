/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.time.LocalDate;

/**
 *
 * @author Jirka
 */
public class Machine {
    private String name;
    private String code;
    private String producer;
    private String dateOfBuying;//LocalDate
    private String placeOfBuying;
    private int price;
    private int guaranty;
    private String manual;
    private int consumption;
    
    private String images;
    private String text;
    private String parametr;
    
    //private String id;
    
    public Machine(String name, String code, String producer, String dateOfBuying, String placeOfBuying, int price, int guaranty, String manual, int consumption, String images, String text, String parametr){
        this.name = name;
        this.code = code;
        this.producer = producer;
        this.dateOfBuying = dateOfBuying;
        this.placeOfBuying = placeOfBuying;
        this.price = price;
        this.guaranty = guaranty;
        this.manual = manual;
        this.consumption = consumption;
        this.images = images;
        this.text = text;
        this.parametr = parametr;
    }
    
    public String getName(){
        return name;
    }
    
    public String getCode(){
        return code;
    }
    
    public String getProducer(){
        return producer;
    }
    
    public String getDateOfBuying(){
        return dateOfBuying;
    }
    
    public String getPlaceOfBuying(){
        return placeOfBuying;
    }
    
    public int getPrice(){
        return price;
    }
    
    public int getGuaranty(){
        return guaranty;
    }
    
    public String getManual(){
        return manual;
    }
    
    public int getConsumption(){
        return consumption;
    }
    
    public String getImages(){
        return images;
    }
    
    public String getText(){
        return text;
    }
    
    public String getParametr(){
        return parametr;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
