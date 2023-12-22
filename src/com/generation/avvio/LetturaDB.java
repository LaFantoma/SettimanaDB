package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import com.generation.library.Console;

public class LetturaDB {
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
        ResultSet risposta = foglio.executeQuery(query);    //mappe chiave-valore

        //6. STAMPA RISPOSTA
        while (risposta.next()) {

            Console.print("Sto analizzando un'altra riga della TABELLA");
            Console.print("Il valore della cella id di questa riga è "+risposta.getString("id"));
            Console.print("Il valore della cella name di questa riga è "+risposta.getString("name"));
            Console.print("Il valore della cella surname di questa riga è "+risposta.getString("surname"));
            Console.print("Il valore della cella dob di questa riga è "+risposta.getString("dob"));
            
        }
    }

}
