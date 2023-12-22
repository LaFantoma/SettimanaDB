package com.generation.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

    public static String quota(String in){

        return "'"+in+"'";
    }

    public static Connection connectToDBLite(String dbName) throws Exception{

        String versione = "org.sqlite.JDBC";
        Class.forName(versione);

        
        String connectionString = "jdbc:sqlite:"+dbName;

        Connection tubo = DriverManager.getConnection(connectionString);

        return tubo;
    }

    public static Connection connectToDB(String dbName) throws Exception
    {
        String versione = "com.mysql.cj.jdbc.Driver";
        Class.forName(versione);   

        String dbInformations = "jdbc:mysql://localhost:3306/"+dbName+"?user=jaita&password=jaita107";
        Connection tubo = DriverManager.getConnection(dbInformations);
        return tubo;
    }
}
