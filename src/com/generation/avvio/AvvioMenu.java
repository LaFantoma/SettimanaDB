package com.generation.avvio;

import static com.generation.library.Console.*;
import static com.generation.util.DbUtil.quota;

import java.sql.Connection;

import com.generation.entities.Menu;
import com.generation.repositories.MenuRepository;
import com.generation.util.DbUtil;

public class AvvioMenu {

    private static Connection tubo;
    private static MenuRepository repo;

    public static void main(String[] args) throws Exception {
        
        tubo = DbUtil.connectToDB("osteriaJAITA.sqlite");

        repo = new MenuRepository(tubo);

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

    private static String _D() throws Exception {
        
        print("dammi id piatto da cancellare");
        Menu m = repo.readMenuPerID(readInt());
        
        if(m==null)
            return "id non valido";

        print("Inserisci DESTROY per eliminare questo piatto");
        print(m);

        if(readString().toUpperCase().equals("DESTROY")){

            repo.deleteMenu(m);
            return "piatto eliminato";
        }
        return "piatto non eliminato";
    }

    private static String _U() throws Exception {
        
        print("dammi id piatto da modificare");
        Menu m = repo.readMenuPerID(readInt());

        if(m==null)
            return "piatto inesistente";

        print("modificare nome?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo nome");
            if(!_check(m.setName(readString())))
                return "NOOOO";
        }

        print("modificare prezzo?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo prezzo");
            if(!_check(m.setPrice(readDouble())))
                return "NOOOO";
        }

        print("modificare tipo?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo tipo (primo-secondo-contorno-dolce-bevanda)");
            if(!_check(m.setType(readString())))
                return "NOOOO";
        }

        print("modificare availability?(y/n)");
        if(readString().toLowerCase().equals("y")){

            print("Inserisci nuovo availability (breakfast-lunch-dinner)");
            if(!_check(m.setShift_availability(readString())))
                return "NOOOO";
        }

        repo.updateMenu(m);
        return "Modifica completata";
    }

    private static String _R() throws Exception {
        
        print("T.Lettura Totale   ID.Lettura tramite ID   F.Lettura filtrata");
        
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

    private static String _letturaPerID() throws Exception {
        
        print("Inserisci ID menu");
        int id = readInt();

        Menu m = repo.readMenuPerID(id);

        return m==null ? "id non valido" : m.toString();
    }

    private static String _letturaTotale() throws Exception {
        
        return repo.readAll().toString();
    }

     private static String _letturaFiltrata()throws Exception{

        String condizione;

        print("Inserisci P per filtrare per prezzo, A per filtrare per availalibity");
        switch (readString().toUpperCase()) 
        {
            case "A":
                print("Inserisci availability? (breakfast-lunch-dinner)");    
                condizione = "shift_availability="+quota(readString());
            break;
            case "S":

                print("Scrivi MAX per cercare per prezzo massimo, MIN per minimo, BET per prezzo COMPRESO TRA");
                switch (readString().toUpperCase()) 
                {
                    case "MAX":
                        print("Inserisci il prezzo massimo");
                        condizione = "price<" + readInt();    
                    break;

                    case "MIN":
                        print("Inserisci il salario minimo");
                        condizione = "salary>" + readDouble();    
                    break;

                    case "BET":
                        print("Inserisci minimo");
                        condizione = "price>=" + readDouble();    
                        print("Inserisci massimo");
                        condizione += " and price<=" + readDouble();   
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
        
        Menu m = new Menu();

        print("inserisci nome piatto");
        if(!_check(m.setName(readString())))
            return "riprova";
        
        print("inserisci prezzo");
        if(!_check(m.setPrice(readDouble())))
            return "riprova";

        print("inserisci tipo (primo-secondo-contorno-dolce)");
        if(!_check(m.setType(readString())))
            return "riprova";

        print("inserisci availability (breakfast-lunch-dinner)");
        if(!_check(m.setShift_availability(readString())))
            return "riprova";

        repo.insertMenu(m);

        return "Menu inserito con successo";
    }

    private static boolean _check(boolean resultSetter) {
        
        if(!resultSetter)
            print("Proprietà non valida,termino inserimento");

        return resultSetter;
    }

}
