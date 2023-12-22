package com.generation.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.generation.entities.Author;
import com.generation.library.List;

public class AuthorRepository 
{
    private Connection con;

    //leggo autore per id
    //leggo tutti autori
    //leggo autori con condizione
    public AuthorRepository(Connection con)
    {
        this.con=con;
    }

    public List<Author> readAll()throws Exception
    {
        List<Author> res = new List<Author>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM author");

        while(rs.next())
            res.add(_rsToAuthor(rs));

        s.close();
        return res;
    }

    public Author readOne(int id) throws Exception
    {
        Author res = new Author();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM author WHERE id="+id);

        if(rs.next())
        {            
            res=_rsToAuthor(rs);
            s.close();
            return res;
        }
        s.close();
        return null;
    }
                                                        
    public List<Author> readFiltered(String condizione)throws Exception
    {
        List<Author> res = new List<Author>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM author WHERE "+condizione);
                                   
        while(rs.next())
            res.add(_rsToAuthor(rs));

        s.close();
        return res;
    }

    private Author _rsToAuthor(ResultSet rs) throws Exception
    {
        Author a = new Author();
        a.id = rs.getInt("id");
        a.name = rs.getString("name");
        a.dob = LocalDate.parse(rs.getString("dob"));
        a.nation = rs.getString("nation");

        return a;
    }
}
