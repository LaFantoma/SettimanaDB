package com.generation.avvio;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnessioneMySql {

    public static void main(String[] args) throws Exception {
        
        String versione = "org.mysql.jdbc.Driver";
        Class.forName(versione);

        String dbInformations = "jdbc:mysql://localhost:3306/primodbmysql?user=jaita&password=jaita107";
        
        Connection tubo = DriverManager.getConnection(dbInformations);

        
    }

}
