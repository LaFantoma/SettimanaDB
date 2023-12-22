package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.generation.entities.Person;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.library.Map;

public class GiochinoPolimorfico 
{
    public static void main(String[] args) throws Exception
    {
         String versione = "org.sqlite.JDBC";
        Class.forName(versione);   

        String dbName = "anagrafe.sqlite";
        String connectionString= "jdbc:sqlite:"+dbName;//le informazioni
        Connection tubo = DriverManager.getConnection(connectionString);
        
        Statement foglioBianco = tubo.createStatement();

        String query = "SELECT * FROM person";

        ResultSet rispostaDB = foglioBianco.executeQuery(query);//sto premendo EXECUTE

        //Le informazioni in un DB hanno forma di tabella con righe e colonne

        //Java le leggerà sempre sotto forma di ResultSet, uno schifo del passato
        //che scorre riga per riga in maniera indecente e indegna

        //Quello che è spiritualmente è una Lista di mappe

        List<Map<String,String>> tabella = new List<Map<String,String>>();

        while(rispostaDB.next())
        {
            Map<String,String> unaRiga = new Map<String,String>();

            unaRiga.put("id", rispostaDB.getString("id"));
            unaRiga.put("name", rispostaDB.getString("name"));
            unaRiga.put("surname", rispostaDB.getString("surname"));
            unaRiga.put("dob", rispostaDB.getString("dob"));

            tabella.add(unaRiga);
        }

        Console.print(tabella);

        //ora lo converto in una lista di persone

        List<Person> convertitiInOggetti = new List<Person>();

        for(int i=0;i<tabella.size();i++)
        {
            Person p = new Person();
            p.setId(Integer.parseInt(tabella.get(i).get("id")));
            p.setName(tabella.get(i).get("name"));
            p.setSurname(tabella.get(i).get("surname"));
            p.setDate(tabella.get(i).get("dob"));
            convertitiInOggetti.add(p);
        }

        Console.print("------------------------------------------------------------------------\n");
        Console.print(convertitiInOggetti);


    }
}
