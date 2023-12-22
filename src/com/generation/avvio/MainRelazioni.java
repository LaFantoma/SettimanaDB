package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

import com.generation.entities.Author;
import com.generation.entities.Book;
import com.generation.entities.Manifacturer;
import com.generation.entities.Poster;
import com.generation.library.Console;
import com.generation.repositories.Database;
import com.generation.repositories.GigaRepository;

public class MainRelazioni 
{
    public static void main(String[] args)  throws Exception
    {
        String versione = "com.mysql.cj.jdbc.Driver";
        Class.forName(versione);   
        //                       diciamo che vogliamo utilizzare un db mysql
        //                                      questo è il server
        //                                                   questo è il db (use primodbmysql)
        //                                                                mandiamo come parametri username e password
        String dbInformations = "jdbc:mysql://localhost:3306/primodbmysql?user=jaita&password=jaita107";
        Connection tubo = DriverManager.getConnection(dbInformations);
        
        Database db = new Database(tubo);
        //GigaRepository gr = new GigaRepository(tubo);

        // Author a = db.readAuthorById(1);

        // Console.print(a);
        // Console.print("\n\n----------------------------------------\n\n");
        // for(int i=0;i<a.myBooks.size();i++)
        //     Console.print(a.myBooks.get(i));

        // Book b = db.readBookById(6);

        // Console.print(b);
        // Console.print("\n----------------------------------------\n");
        // Console.print(b.myAuthor);

        // Manifacturer m = gr.readManifacturerById(2);
        // Console.print("\n"+m);
        // for(Poster p : m.myPoster)
        //     Console.print("\n"+p);

        Book book = null;
        boolean isValid = false;

        do{
            try {
                
                book = askBook();
                isValid = true;

            } catch (RuntimeException e) {
                
                Console.print(e.getMessage());
            }

        }while(!isValid);

        Console.print(book);
    }

    private static Book askBook() 
    {
        Book b = new Book();
        Console.print("Inserisci l'id del libro");
        b.setId(Console.readInt());
        Console.print("Inserisci il titolo del libro");
        b.setTitle(Console.readString());
        Console.print("Inserisci il genere del libro");
        b.setGenre(Console.readString());
        Console.print("Inserisci l'id dell'autore");
        b.setId_author(Console.readInt());
        Console.print("Inserisci la lingua del libro");
        b.setLanguage(Console.readString());
        Console.print("Inserisci il numero pagine del libro");
        b.setN_of_pages(Console.readInt());
        Console.print("Inserisci il numero di copie del libro");
        b.setN_copies(Console.readInt());
        Console.print("Inserisci il prezzo del libro");
        b.setPrice(Console.readDouble());
        Console.print("Inserisci la data di pubblicazione del libro");
        b.setPublished_on(LocalDate.parse(Console.readString()));
        Console.print("Inserisci il tag del libro");
        b.setTags(Console.readString());

        return b;
    }
}

