package com.generation.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.generation.entities.Book;
import com.generation.library.List;

public class BookRepository 
{
    private Connection con;

    //per ora legge solo un libro tramite id
    //o tutti i libri
    public BookRepository(Connection con)
    {
        this.con=con;
    }

    public List<Book> readAll()throws Exception
    {
        List<Book> res = new List<Book>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM book");

        while(rs.next())
            res.add(_rsToBook(rs));

        s.close();
        return res;
    }

    public Book readOne(int id) throws Exception
    {
        Book res = new Book();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM book WHERE id="+id);

        if(rs.next())
        {            
            res=_rsToBook(rs);
            s.close();
            return res;
        }
        s.close();
        return null;
    }
                                                        
    public List<Book> readBooksWroteByAuthorWithId(int id_author)throws Exception
    {
        return readFiltered("id_author="+id_author);//id_author=1
    }

    public List<Book> readFiltered(String condizione)throws Exception
    {
        List<Book> res = new List<Book>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM book WHERE "+condizione);
                                    //SELECT * FROM book WHERE id_author=1
        while(rs.next())
            res.add(_rsToBook(rs));

        s.close();
        return res;
    }

    private Book _rsToBook(ResultSet rs) throws Exception
    {
        Book b = new Book();
        b.setId(rs.getInt("id"));
        b.setTitle(rs.getString("title"));
        b.setGenre(rs.getString("genre"));
        b.setId_author(rs.getInt("id_author"));
        b.setLanguage(rs.getString("language"));
        b.setN_of_pages(rs.getInt("n_of_pages"));
        b.setN_copies(rs.getInt("n_copies"));
        b.setPrice(rs.getDouble("price"));
        b.setPublished_on(LocalDate.parse(rs.getString("published_on")));
        b.setTags(rs.getString("tags"));

        return b;
    }
}
