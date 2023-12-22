package com.generation.repositories;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.ResultSet;

import com.generation.entities.Poster;
import com.generation.library.List;

public class PosterRepository {

    private Connection con;

    public PosterRepository(Connection con){

        this.con = con;
    }

    public List<Poster> readAll() throws Exception{

        List<Poster> res = new List<Poster>();

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM poster");

    while (rs.next()) 
        res.add(_rsToPoster(rs));

    s.close();;
    return res;

    }

    private Poster _rsToPoster(ResultSet rs) throws Exception {
        
        Poster p = new Poster();
        p.id = rs.getInt("id");
        p.id_manifacturer = rs.getInt("id_manifacturer");
        p.name = rs.getString("name");
        p.material = rs.getString("material");
        p.height = rs.getInt("height");
        p.width = rs.getInt("width");
        p.price = rs.getDouble("price");

        return p;
    }

    public Poster readOne(int id) throws Exception{

        Poster res = new Poster();

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM poster WHERE id=" +id);

        if(rs.next()){

            res = _rsToPoster(rs);
            s.close();
            return res;
        }
        s.close();
        return null;
    }

    public List<Poster> madeBy(int id_manifacturer) throws Exception {

        return readFiltered("id_manifacturer="+id_manifacturer);
    }

    private List<Poster> readFiltered(String condizione) throws Exception {
        
        List<Poster> res = new List<Poster>();

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM poster WHERE " + condizione);

        while (rs.next())
            res.add(_rsToPoster(rs));

        s.close();
        return res;
    }



}
