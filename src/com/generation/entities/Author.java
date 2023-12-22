package com.generation.entities;

import java.time.LocalDate;

import com.generation.library.List;

public class Author 
{
    public int id;
    public String name;
    public LocalDate dob;
    public String nation;

    public List<Book> myBooks;//ha una lista di figli
    //è il lato con cardinalità 1 della relazione
    //1 autore tanti libri
    //ogni autore conterrà la lista dei libri che ha scritto

    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + ", dob=" + dob + ", nation=" + nation + "]";
    }

    
}
