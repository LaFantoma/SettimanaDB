package com.generation.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.generation.entities.Menu;
import com.generation.library.List;

public class MenuRepository {

    private Connection con;

    public MenuRepository(Connection tubo){

        this.con = tubo;
    }

    public List<Menu> readAll() throws Exception
    {
        List<Menu> res = new List<Menu>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM menu");
        while(rs.next())
        {
            res.add(_rsToMenu(rs)); 
        }
       
        s.close();
        return res;
    }

    private Menu _rsToMenu(ResultSet rs) throws Exception {
        
        Menu m = new Menu();
        m.setId(rs.getInt("id"));
        m.setName(rs.getString("name"));
        m.setPrice(rs.getDouble("price"));
        m.setType(rs.getString("type"));
        m.setShift_availability(rs.getString("shift_availability"));

        return m;
    }

    public Menu readMenuPerID(int id) throws Exception{

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM menu WHERE id=" + id);

        if(rs.next()){

            Menu m = _rsToMenu(rs);
            s.close();
            return m;
        } 

        s.close();
        return null;
    }

    public List<Menu> filteredRead(String condizione) throws Exception {

        List<Menu> res = new List<Menu>();
        
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM menu WHERE " + condizione);
        
        while(rs.next())
            res.add(_rsToMenu(rs));
               
        s.close();
       
        return res;

    }

    public void insertMenu(Menu m) throws Exception {

        Statement s = con.createStatement();
        s.execute(m.toInsertQuery());
        s.close();
    }

    public void updateMenu(Menu m) throws Exception {

        Statement s = con.createStatement();
        s.execute(m.toUpdateQuery());
        s.close();
    }

    public void deleteMenu(Menu m) throws Exception {

        Statement s = con.createStatement();
        s.execute(m.toDeleteQuery());
        s.close();                
    }

}
