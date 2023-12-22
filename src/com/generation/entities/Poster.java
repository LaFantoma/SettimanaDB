package com.generation.entities;

public class Poster {

    public int id; 
    public String name;
    public String material;
    public int height;
    public int width;
    public double price;

    public int id_manifacturer;
    public Manifacturer myManifacturer;
   
    @Override
    public String toString() {
        return "Poster [id=" + id + ", name=" + name + ", material=" + material + ", height=" + height + ", width="
                + width + ", price=" + price + ", id_manifacturer=" + id_manifacturer + "]";
    }

    



}
