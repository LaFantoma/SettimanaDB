package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.generation.entities.Employee;
import com.generation.library.Console;

public class EsercizioOsteria {
    public static void main(String[] args) throws Exception {
        
        String versione = "org.sqlite.JDBC";
        Class.forName(versione);

        String dbName = "osteriaJAITA.sqlite";
        String connectionString = "jdbc:sqlite:"+dbName;

        Connection tubo = DriverManager.getConnection(connectionString);

        Statement foglio = tubo.createStatement();

        // String query1 = "CREATE table menu"+
        //                 "("+
        //                 "id integer primary key autoincrement,"+
        //                 "name varchar(100),"+
        //                 "price numeric(5,2),"+
        //                 "type varchar(40),"+
        //                 "shift_availability varchar(40)"+
        //                 ")";

        // String query2 = "CREATE table employee"+
        //                 "("+
        //                 "id integer primary key autoincrement,"+
        //                 "name varchar(100),"+
        //                 "surname varchar(100),"+
        //                 "dob date,"+
        //                 "role varchar(100),"+
        //                 "salary integer,"+
        //                 "year_of_experience integer"+
        //                 ")";

        // foglio.execute(query1);
        // foglio.execute(query2);

        // Menu m = new Menu();

        // Console.print("CREAZIONE PIATTO");
        // Console.print("Inserisci nome piatto");
        // m.setName(Console.readString());
        // Console.print("Inserisci prezzo");
        // m.setPrice(Console.readDouble());
        // Console.print("Inserisci tipo (primo-secondo-contorno-dolce-bevanda)");
        // m.setType(Console.readString());
        // Console.print("Inserisci availability");
        // m.setShift_availability(Console.readString());

        // foglio.execute(m.toInsertQuery());


        Employee e = new Employee();

        Console.print("CREAZIONE IMPIEGATO");
        Console.print("Inserisci nome");
        e.setName(Console.readString());
        Console.print("Inserisci cognome");
        e.setSurname(Console.readString());
        Console.print("Inserisci data di nascita (YYYY-MM-DD)");
        e.setDob(Console.readString());
        Console.print("Inserisci ruolo");
        e.setRole(Console.readString());
        Console.print("Inserisci salario");
        e.setSalary(Console.readInt());
        Console.print("Inserisci anni di esperienza");
        e.setYear_of_experience(Console.readInt());

        foglio.execute(e.toInsertQuery());
    }

}
