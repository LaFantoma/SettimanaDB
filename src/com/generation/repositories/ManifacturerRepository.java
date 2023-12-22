package com.generation.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.generation.entities.Manifacturer;
import com.generation.library.List;

public class ManifacturerRepository {

    private Connection con;

    public ManifacturerRepository(Connection con){

        this.con = con;
    }

    public List<Manifacturer> readAll() throws Exception{

        List<Manifacturer> res = new List<Manifacturer>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM manifacturer");

        while(rs.next())
            res.add(_rsToManifacturer(rs));

        s.close();
        return res;
    }

    private Manifacturer _rsToManifacturer(ResultSet rs) throws Exception {
        
        Manifacturer m = new Manifacturer();
        m.id = rs.getInt("id");
        m.name = rs.getString("name");
        m.city = rs.getString("city");
        m.address = rs.getString("address");
        m.n_of_employees = rs.getInt("n_of_employees");

        return m;
    }

    public Manifacturer readOne(int id) throws Exception
    {
        Manifacturer res = new Manifacturer();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM manifacturer WHERE id="+id);

        if(rs.next())
        {            
            res=_rsToManifacturer(rs);
            s.close();
            return res;
        }
        s.close();
        return null;
    }

    public List<Manifacturer> readFiltered(String condizione)throws Exception
    {
        List<Manifacturer> res = new List<Manifacturer>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM manifacturer WHERE "+condizione);
                                    
        while(rs.next())
            res.add(_rsToManifacturer(rs));

        s.close();
        return res;
    }
}
