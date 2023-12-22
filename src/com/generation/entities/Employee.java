package com.generation.entities;

import java.time.LocalDate;
import static com.generation.util.DbUtil.*;

public class Employee {

    private int id;
    private String name;
    private String surname;
    private LocalDate dob;
    private String role;
    private int salary;
    private int year_of_experience;
    
    public int getId() {
        return id;
    }

    public boolean setId(int id) {
        if(id<1)
            return false;
        this.id = id;
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if(name==null || name.isBlank())
            return false;
        this.name = name;
        return true;
    }

    public String getSurname() {
        return surname;
    }

    public boolean setSurname(String surname) {
        if(surname==null || surname.isBlank())
            return false;
        this.surname = surname;
        return true;
    }

    public LocalDate getDob() {
        return dob;
    }
    
    public String dobToString(){
        return dob.getYear() + "-" + dob.getMonthValue() + "-" + dob.getDayOfMonth();
    }

    public boolean setDob(String dob) {
        
        String[]parts = dob.split("-");
        if(parts.length < 3)
            return false;
        this.dob = LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        return true;
    }

    public String getRole() {
        return role;
    }

    public boolean setRole(String role) {
        if(role==null || role.isBlank())
            return false;
        this.role = role;
        return true;
    }

    public int getSalary() {
        return salary;
    }

    public boolean setSalary(int salary) {
        if(salary<0)
            return false;
        this.salary = salary;
        return true;
    }

    public int getYear_of_experience() {
        return year_of_experience;
    }
    
    public boolean setYear_of_experience(int year_of_experience) {
        
        if(year_of_experience<0)
            return false;
        this.year_of_experience = year_of_experience;
        return true;
    }

    public String toInsertQuery() {
        return  "INSERT INTO employee (name, surname, dob, role, salary, year_of_experience) VALUES"+
                "("+
                quota(this.name)+","+
                quota(this.surname)+","+
                quota(dobToString())+","+
                quota(this.role)+","+
                this.salary+","+
                this.year_of_experience+
                ")";
    }

    public String toDeleteQuery(){

        return "DELETE FROM employee WHERE id="+this.id;
    }

    public String toUpdateQuery(){

        return  "UPDATE employee set name="+quota(this.name)+","+
                "surname="+quota(this.surname)+","+
                "dob="+quota(dobToString())+","+
                "role="+quota(this.role)+","+  
                "salary="+this.salary+","+
                "year_of_experience="+this.year_of_experience+" "+       
                "WHERE id="+this.id;
    }

    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + ", role=" + role
                + ", salary=" + salary + ", year_of_experience=" + year_of_experience + "]";
    }
    

}
