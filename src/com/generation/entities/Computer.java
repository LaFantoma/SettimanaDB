package com.generation.entities;

public class Computer 
{
    private int id;
    private String model;
    private int ramGb,ssdGb;
    private String cpu;
    private int price;
   
    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getRamGb() {
        return ramGb;
    }

    public int getSsdGb() {
        return ssdGb;
    }

    public String getCpu() {
        return cpu;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        
        if(id<1)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.id = id;
    }

    public void setModel(String model) {

        if(model==null || model.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.model = model;

    }

    public void setRamGb(int ramGb) {
        
        if (ramGb<1)
            throw new RuntimeException("Impossibile inserire valore negativo");    
        this.ramGb = ramGb;
    }

    public void setSsdGb(int ssdGb) {
        
          if (ssdGb<1)
            throw new RuntimeException("Impossibile inserire valore negativo");  
        this.ssdGb = ssdGb;
    }

    public void setCpu(String cpu) {

         if(cpu==null || cpu.isBlank())
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.cpu = cpu;
    }

    public void setPrice(int price) {

        if (price<1)
            throw new RuntimeException("Impossibile inserire valore negativo");  
        this.price = price;
    }

    public String toInsertQuery(){

        return "INSERT INTO computer (model,ramGb,ssdGb,cpu,price) VALUES"+
                "("+
                    "'"+this.model+"',"+
                    +this.ramGb+","+
                    +this.ssdGb+","+
                    "'"+this.cpu+"',"+
                    this.price+
                ")";
    }
    
    public String toUpdateQuery(){
        
        return "UPDATE computer set model="+"'"+this.model+"',"+
                                   "ramGb="+this.ramGb+","+
                                   "ssdGb="+this.ssdGb+","+
                                   "cpu="+"'"+this.cpu+"',"+
                                    "price="+this.price +
                                    "WHERE id="+this.id;
    }

    public String toDeleteQuery(){

        return "DELETE FROM computer WHERE id="+this.id;
    }

}
