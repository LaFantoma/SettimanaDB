package com.generation.avvio;

import java.sql.Connection;

import com.generation.entities.Employee;
import com.generation.repositories.EmployeeRepository;
import com.generation.util.DbUtil;

import static com.generation.library.Console.*;
import static com.generation.util.DbUtil.*;

public class AvvioEmployee {

    private static Connection tubo;
    private static EmployeeRepository repo;

    public static void main(String[] args) throws Exception {
        
        tubo = DbUtil.connectToDB("osteriaJAITA.sqlite");

        repo = new EmployeeRepository(tubo);


        String cmd="";
        boolean goNext;
        String toPrint = "";

        do {
            print("Dammi comando");
            cmd = readString();

            switch (cmd) {

                case "C":
                    toPrint = _C();
                break;

                case "R":
                    toPrint = _R();
                break;

                case "U":
                    toPrint = _U();
                break;

                case "D":
                    toPrint = _D();
                break;

                case "Q":
                    toPrint = "Bye bye";
                break;

                default:
                    toPrint = "Comando non valido";
                break;
            }

            print(toPrint);
            goNext = !cmd.equals("Q");
            
        } while (goNext);

    }

    private static String _U() throws Exception {

        print("dammi id impiegato da modificare");
        Employee e = repo.readEmployeePerID(readInt());

        if(e==null)
            return "impiegato inesistente";

        print("modificare nome?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo nome");
            if(!_check(e.setName(readString())))
                return "NOOOO";
        }

        print("modificare cognome?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo cognome");
            if(!_check(e.setSurname(readString())))
                return "NOOOO";
        }

        print("modificare data di nascita?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo data di nascita");
            if(!_check(e.setYear_of_experience(readInt())))
                return "NOOOO";
        }

        print("modificare ruolo?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo ruolo");
            if(!_check(e.setRole(readString())))
                return "NOOOO";
        }

        print("modificare salario?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo salario");
            if(!_check(e.setSalary(readInt())))
                return "NOOOO";
        }

        repo.updateEmployee(e);
        return "Modifica completata";
    }

    private static String _D() throws Exception {

        print("dammi id impiegato da cancellare");
        Employee e = repo.readEmployeePerID(readInt());
        
        if(e==null)
            return "id non valido";

        print("Inserisci DESTROY per eliminare questo impiegato");
        print(e);

        if(readString().toUpperCase().equals("DESTROY")){

            repo.deleteEmployee(e);
            return "impiegato ucciso";
        }
        return "Impiegato risparmiato";

    }

    private static String _R() throws Exception {

        print("Scrivi T per lettura Totale, ID per lettura tramite ID, F per lettura filtrata (condizionale)");
        
        switch (readString().toUpperCase()){

            case "T"://Lettura TOTALE
                return _letturaTotale();
            
            case "ID"://Lettura per ID
                return _letturaPerID();
            
            case "F"://Lettura CONDIZIONALE
                return _letturaFiltrata();
        
            default:
                return "Comando non valido, muori";
        }
    }

    private static String _letturaTotale() throws Exception{

        return repo.readAll().toString();
        
    }

    private static String _letturaPerID() throws Exception{
        
        print("Dammi id impiegato");
        int id = readInt();

        Employee e = repo.readEmployeePerID(id);

        return e==null ? "id non valido" : e.toString();

    }

    private static String _letturaFiltrata()throws Exception{

        String condizione;

        print("Inserisci R per filtrare per ruolo, S per filtrare per salario");
        switch (readString().toUpperCase()) 
        {
            case "R":
                print("Che ruolo?");    
                condizione = "role="+quota(readString());
            break;
            case "S":

                print("Scrivi MAX per cercare per salario massimo,MIN per minimo, BET per salatro COMPRESO TRA");
                switch (readString().toUpperCase()) 
                {
                    case "MAX":
                        print("Inserisci il salario massimo");
                        condizione = "salary<" + readInt();    
                    break;

                    case "MIN":
                        print("Inserisci il salario minimo");
                        condizione = "salary>" + readInt();    
                    break;

                    case "BET":
                        print("Inserisci minimo");
                        condizione = "salary>=" + readInt();    
                        print("Inserisci massimo");
                        condizione += " and salary<=" + readInt();   
                    break;
                    
                    default:
                        print("una cosa ti avevo chiesto, non l'hai fatta, ora te li becchi TUTTI");
                        condizione = "1=1";
                    break;
                }
            break;

            default:
                print("una cosa ti avevo chiesto, non l'hai fatta, ora te li becchi TUTTI");
                condizione = "1=1";
            break;
        }

        return repo.filteredRead(condizione).toString();
    }

    private static String _C() throws Exception {
        
        Employee e = new Employee();

        print("inserisci nome");
        if(!_check(e.setName(readString())))
            return "riprova";
        
        print("inserisci cognome");
        if(!_check(e.setSurname(readString())))
            return "riprova";

        print("inserisci data di nascita (YYYY-MM-DD)");
        if(!_check(e.setDob(readString())))
            return "riprova";

        print("inserisci ruolo");
        if(!_check(e.setRole(readString())))
            return "riprova";

        print("inserisci salario");
        if(!_check(e.setSalary(readInt())))
            return "riprova";

        print("inserisci anni di esperienza");
        if(!_check(e.setYear_of_experience(readInt())))
            return "riprova";

        repo.insertEmployee(e);

        return "YEEE, impiegato inserito";
    }

    private static boolean _check(boolean resultSetter){

        if(!resultSetter)
            print("Proprietà non valida,termino inserimento");

        return resultSetter;
    }

}
