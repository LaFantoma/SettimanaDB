package com.generation.entities;

import java.time.LocalDate;

public class Book 
{
    private int id;
    private String title;
    private String genre,language;
    private int n_of_pages,n_copies;
    private double price;
    private LocalDate published_on;
    private String tags;

    public int id_author;
    public Author myAuthor;
    
    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", genre=" + genre + ", language=" + language + ", n_of_pages="
                + n_of_pages + ", n_copies=" + n_copies + ", price=" + price + ", published_on=" + published_on
                + ", tags=" + tags + ", id_author=" + id_author + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        
        if(id<1)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
    
        if(title==null || title.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        
        if(genre==null || genre.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        
        if(language==null || language.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.language = language;
    }

    public int getN_of_pages() {
        return n_of_pages;
    }

    public void setN_of_pages(int n_of_pages) {
        
        if(n_of_pages<1)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.n_of_pages = n_of_pages;
    }

    public int getN_copies() {
        return n_copies;
    }

    public void setN_copies(int n_copies) {
        
        if(n_copies<1)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.n_copies = n_copies;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        
        if(price<1)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.price = price;
    }

    public LocalDate getPublished_on() {
        return published_on;
    }

    public void setPublished_on(LocalDate published_on) {
        
        if(published_on.isAfter(LocalDate.now()))
            throw new RuntimeException("Data non valida");
        this.published_on = published_on;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        
        if(tags==null || tags.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.tags = tags;

    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        
        if(id_author<1)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.id_author = id_author;
    }

    public Author getMyAuthor() {
        return myAuthor;
    }

    public void setMyAuthor(Author myAuthor) {
        
        if(myAuthor==null)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.myAuthor = myAuthor;
        this.id_author = myAuthor.id;
    }

    
    
}
