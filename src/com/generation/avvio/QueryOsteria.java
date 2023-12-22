package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.generation.library.Console;
import com.generation.library.List;
import com.generation.library.Map;

public class QueryOsteria {
    public static void main(String[] args) throws Exception {
        
    

        String versione = "org.sqlite.JDBC";
        Class.forName(versione);

        String dbName = "osteriaJAITA.sqlite";
        String connectionString = "jdbc:sqlite:"+dbName;

        Connection tubo = DriverManager.getConnection(connectionString);

        Statement foglio = tubo.createStatement();

        //Scrivere SELECT che mostri solo nome e cognome dei rider
        String query1 = "SELECT name, surname FROM employee WHERE role='rider'";

        ResultSet risposta1 = foglio.executeQuery(query1);

        List<Map<String,String>> tabella1 = new List<Map<String,String>>();

        while(risposta1.next())
        {
            Map<String,String> unaRiga = new Map<String,String>();

            // unaRiga.put("id", risposta1.getString("id"));
            unaRiga.put("name", risposta1.getString("name"));
            unaRiga.put("surname", risposta1.getString("surname"));
            // unaRiga.put("dob", risposta1.getString("dob"));
            // unaRiga.put("role", risposta1.getString("role"));

            tabella1.add(unaRiga);
        }

        Console.print(tabella1);

        //SCRIVERE SELECT che mostri tutto dei piatti disponibili a colazione
        String query2 = "";

        //SCRIVERE SELECT che mostri tutto dei piatti disponibili a colazione  o a pranzo

        //mostrare solo i piatti disponibili a pranzo che costano meno di 5 euro

        //vedere il menù delle bevende disponibili a cena

        //vedere il menù delle bevende disponibili a cena che costano tra 50 euro e 100 euro

        //modificare lo stipendio dei cuochi con più di 42 anni e più di  15 anni di esperienza, ora prendono 9000 euro al mese




    }
}
