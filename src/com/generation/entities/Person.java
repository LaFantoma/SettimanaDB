package com.generation.entities;

import java.time.LocalDate;

public class Person 
{
    private int id;
    private String name,surname;
    private LocalDate dob;

    public int getId() 
    {
        return id;
    }

    public boolean setId(int id)
    {
        if(id<1)
            return false;
        
        this.id=id;
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name)
    {
        if(name==null || name.isBlank())
            return false;

        this.name=name;
        return true;
    }

    public String getSurname() {
        return surname;
    }

    public boolean setSurname(String surname)
    {
        if(surname==null || surname.isBlank())
            return false;

        this.surname=surname;
        return true;
    }

    public LocalDate getDob() {
        return dob;
    }

    public boolean setDate(LocalDate dob)
    {
        if(dob==null)
            return false;

        this.dob = dob;
        return true;
    }

     public boolean setDate(String dobString)
    {
        String[] parts = dobString.split("-");
        if(parts.length!=3)
           return false;
        
        this.dob = LocalDate.of
                    (
                        Integer.parseInt(parts[0]), 
                        Integer.parseInt(parts[1]),  
                        Integer.parseInt(parts[2])
                    );
        return true;
    }

    public int getAge()
    {
        return 2023-dob.getYear();
    }

    public String toString()
    {
        return  "Ciao sono un oggetto Person con id "+this.id+
                ", mi chiamo "+this.name+" "+this.surname +
                " e ho "+this.getAge()+" anni";
    }

}
