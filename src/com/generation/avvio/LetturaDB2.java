package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.generation.entities.Person;
import com.generation.library.Console;
import com.generation.library.List;

public class LetturaDB2 {
    public static void main(String[] args) throws Exception { 
        
        //1. REGISTRARE I DRIVER
        String versione = "org.sqlite.JDBC";
        Class.forName(versione);

        //2. CREARE CONNESSIONE COL DB
        String dbName = "anagrafe.sqlite";
        String connectionString = "jdbc:sqlite:"+dbName;

        Connection tubo = DriverManager.getConnection(connectionString);

        //3. CREARE UNO STATEMENT (scheda dove srivere query)
        Statement foglio = tubo.createStatement();

        //4. SCRIVERE QUERY
        String query = "SELECT * FROM person";

        //5. SALVARE RISPOSTA
        ResultSet risposta = foglio.executeQuery(query);
        
        List<Person> persone = new List<Person>();
                
        //6. COVERTIRE RIGHE DI TABELLA IN OGGETTO
        while (risposta.next()) {

            Person p = new Person();
            p.setId(risposta.getInt("id"));
            p.setName(risposta.getString("name"));
            p.setSurname(risposta.getString("surname"));
            p.setDate(risposta.getString("dob"));

            persone.add(p);
        }

        Console.print(persone);
    }

}
