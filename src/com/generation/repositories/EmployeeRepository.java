package com.generation.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.generation.entities.Employee;
import com.generation.library.List;

public class EmployeeRepository {

    private Connection con;

    public EmployeeRepository(Connection tubo)
    {
        this.con=tubo;
    }

    public List<Employee> readAll() throws Exception
    {
        List<Employee> res = new List<Employee>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM employee");
        while(rs.next())
        {
            res.add(_rsToEmployee(rs)); 
        }
       
        s.close();
        return res;
    }

    private Employee _rsToEmployee(ResultSet rs) throws Exception {
        
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setSurname(rs.getString("surname"));
        e.setDob(rs.getString("dob"));
        e.setRole(rs.getString("role"));
        e.setSalary(rs.getInt("salary"));
        e.setYear_of_experience(rs.getInt("year_of_experience"));

        return e;
    }

    public Employee readEmployeePerID(int id) throws Exception{

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM employee WHERE id=" +id);

        if(rs.next()){

            Employee e = _rsToEmployee(rs);
            s.close();
            return e;
        } 

        s.close();
        return null;
    }

    public List<Employee> filteredRead(String condizione) throws Exception {

        List<Employee> res = new List<Employee>();
        
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM employee WHERE " + condizione);
        
        while(rs.next())
            res.add(_rsToEmployee(rs));
               
        s.close();
       
        return res;

    }

    public void insertEmployee(Employee e) throws Exception {

        Statement s = con.createStatement();
        s.execute(e.toInsertQuery());
        s.close();
    }

    public void updateEmployee(Employee e) throws Exception {

        Statement s = con.createStatement();
        s.execute(e.toUpdateQuery());
        s.close();
    }

    public void deleteEmployee(Employee e) throws Exception {

        Statement s = con.createStatement();
        s.execute(e.toDeleteQuery());
        s.close();                
    }

}
