package com.generation.entities;

import static com.generation.util.DbUtil.*;

public class Menu {

    private int id;
    private String name;
    private double price;
    private String type;
    private String shift_availability;
    
    public int getId() {
        return id;
    }

    public boolean setId(int id) {
        
        if(id<1)
            return false;
        this.id = id;
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {

        if(name==null || name.isBlank())
            return false;
        this.name = name;
        return true;
    }

    public double getPrice() {
        return price;
    }

    public boolean setPrice(double price) {
        
        if(price<0)
            return false;
        this.price = price;
        return true;
    }

    public String getType() {
        return type;
    }

    public boolean setType(String type) {
        
        if(type!=null &&   ( type.equals("primo")    || 
                             type.equals("secondo")  ||
                             type.equals("contorno") ||
                             type.equals("dolce")    ||
                             type.equals("bevanda")
                            ))
        {
            this.type = type;
            return true;
        }
        return false;
    }

    public String getShift_availability() {
        return shift_availability;
    }

    public boolean setShift_availability(String shift_availability) {

        if(shift_availability!=null && (
                                        shift_availability.equals("breakfast") ||
                                        shift_availability.equals("lunch")      ||
                                        shift_availability.equals("dinner")
                                        ))
        {
            this.shift_availability = shift_availability;
            return true;
        }
        return false;
    }

    public String toInsertQuery(){

        return  "INSERT INTO menu (name, price, type, shift_availability) VALUES"+
                "("+
                quota(this.name)+","+
                +this.price+","+
                quota(this.type)+","+
                quota(this.shift_availability)+
                ")";
    }

    public String toDeleteQuery(){

        return "DELETE FROM menu WHERE id="+this.id;
    }

    public String toUpdateQuery(){

        return  "UPDATE menu set name="+quota(this.name)+","+
                "price="+this.price+","+
                "type="+quota(this.type)+","+
                "shift_availability="+quota(this.shift_availability)+ " "+       
                "WHERE id="+this.id;
    }

    public String toString() {
        return "Menu [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", shift_availability="
                + shift_availability + "]";
    }

    

}
