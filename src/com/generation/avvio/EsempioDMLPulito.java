package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.generation.entities.Computer;
import com.generation.library.Console;

public class EsempioDMLPulito 
{
     public static void main(String[] args) throws Exception
    {
        String versione = "org.sqlite.JDBC";
        Class.forName(versione);   

        String dbName = "anagrafe.sqlite";
        String connectionString= "jdbc:sqlite:"+dbName;//le informazioni
        Connection tubo = DriverManager.getConnection(connectionString);
        
        Statement foglioBianco = tubo.createStatement();    

        Computer c = new Computer();

        Console.print("Dammi modello");
        c.setModel(Console.readString());
        Console.print("Dammi gb di ram");
        c.setRamGb(Console.readInt());
        Console.print("Dammi gb di ssd");
        c.setSsdGb(Console.readInt());
        Console.print("Dammi cpu");
        c.setCpu(Console.readString());
        Console.print("Dammi prezzo");
        c.setPrice(Console.readInt());

        Console.print(c.toInsertQuery());
        foglioBianco.execute(c.toInsertQuery());

        // Computer c = new Computer();
        // c.setId(3);

        // foglioBianco.execute(c.toDeleteQuery());
    }
}
