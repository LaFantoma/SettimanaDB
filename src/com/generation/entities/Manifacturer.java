package com.generation.entities;

import com.generation.library.List;

public class Manifacturer {

    public int id;
    public String name;
    public String city;
    public String address;
    public int n_of_employees;

    public List<Poster> myPoster;

    @Override
    public String toString() {
        return "Manifacturer [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address
                + ", n_of_employees=" + n_of_employees + "]";
    }

    

}
