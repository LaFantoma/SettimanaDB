package com.generation.repositories;

import java.sql.Connection;

import com.generation.entities.Author;
import com.generation.entities.Book;
import com.generation.library.List;

public class Database 
{
    //Tutti i comandi che vogliamo poter dare al db
    //Leggi 1 libro tramite id
    //Leggi 1 autore tramite id

    //Per poter fare le letture ho bisogno delle repository
    //Per fare il Database servono le repository
    //Per fare le repository serve la connection

    //private Connection con;
    private BookRepository bRepo;
    private AuthorRepository aRepo;

    public Database(Connection con)
    {
        this.aRepo = new AuthorRepository(con);
        this.bRepo = new BookRepository(con);
    }

    /**
     * Arriva come parametro l'id di un libro
     * legge quel libro dal db
     * legge l'autore di quel libro dal db
     * li collega
     * restituisce il libro
     * @param bookId
     * @return
     */
    public Book readBookById(int bookId)throws Exception
    {
        Book res = bRepo.readOne(bookId);//abbiamo il libro senza autore
        Author a = aRepo.readOne(res.getId_author());
        res.setMyAuthor(a);//imposto l'autore come padre del libro

        List<Book> books = new List<Book>();//imposto il libro come figlio dell'autore
        books.add(res);
        a.myBooks = books;

        return res;
    }

    /**
     * Leggiamo un autore
     * Leggiamo tutti i libri che ha scritto
     * li colleghiamo
     * restituiamo
     * @param authorId
     * @return
     * @throws Exception
     */
    public Author readAuthorById(int authorId)throws Exception
    {
        Author res = aRepo.readOne(authorId);
        List<Book> books = bRepo.readBooksWroteByAuthorWithId(authorId);

        res.myBooks = books; //collego l'autore alla lista di libri che ha scritto

        for(int i=0;i<books.size();i++) //collego ogni libro all'autore
            books.get(i).setMyAuthor(res);

        return res;
    }
}
