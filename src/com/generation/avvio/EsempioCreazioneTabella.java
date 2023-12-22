package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.generation.library.Console;

public class EsempioCreazioneTabella 
{
    public static void main(String[] args) throws Exception
    {
        String versione = "org.sqlite.JDBC";
        Class.forName(versione);   

        String dbName = "anagrafe.sqlite";
        String connectionString= "jdbc:sqlite:"+dbName;//le informazioni
        Connection tubo = DriverManager.getConnection(connectionString);
        
        Statement foglioBianco = tubo.createStatement();    

        String query = "CREATE tae cuter"+
                        "id integer primary key autoincrement,"+
                        "model varchar(100),"+
                        "ramGb integer,"+
                        "ssdGb integer,"+
                        "cpu varchar(100),"+
                        "price integer"+
                        "";

        Console.print(query);

        //per eseguire DDL o DML non usiamo il metodo executeQuery, quello è solo per la lettura
        //utilizziamo execute
        foglioBianco.execute(query);
    }
}
