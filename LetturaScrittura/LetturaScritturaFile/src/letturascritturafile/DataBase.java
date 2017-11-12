package letturascritturafile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author Florian
 */
class DataBase {

    private static File dataBase = new File("storage.txt");
    private static ArrayList<Utente> lista = new ArrayList<>();

    public static void addUser(Utente u) throws FileNotFoundException {
        lista.add(u);
        aggiornaFile();
    }

    public static String getUserPasswd(String nome) throws FileNotFoundException {
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getUtente().equals(nome)){
                return lista.get(i).getPasswd();
            }
        }
        return "";
    }

    public static void setUserPasswd(String nomeUtente, String passwd) throws FileNotFoundException {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getUtente().equals(nomeUtente)) {
                lista.get(i).passwd = passwd;
            }
        }
        aggiornaFile();
    }

    private static void aggiornaFile() throws FileNotFoundException {
        PrintWriter p = new PrintWriter(dataBase);
        for (int i = 0; i < lista.size(); i++) {
            p.println(lista.get(i).getUtente() + ";" + lista.get(i).getPasswd()+";"+lista.get(i).ultimoAccesso);
            if(lista.get(i).gruppi.listaGruppi.size()>1){
                p.println("Gruppi : ");
            }
            for (int j = 0; j < lista.get(i).gruppi.listaGruppi.size(); j++) {
                p.println("  \t\t   Gruppo "+j+" : "+lista.get(i).gruppi.listaGruppi.get(j).nomeGruppo+" Potere : "+lista.get(i).gruppi.poteri.get(j).potereCheHoIo +" Contenuto : "+lista.get(i).gruppi.listaGruppi.get(j).contenutoGruppo);
                for (int k = 0; k < lista.get(i).gruppi.listaGruppi.get(j).utenti.size(); k++) {
                    p.println("    \t\t\t\t\t\t         "+lista.get(i).gruppi.poteri.get(j).getPotereCheHoIo()+" n^"+k+" : "+lista.get(i).gruppi.listaGruppi.get(j).utenti.get(k).getUtente() +" Passwd : "+lista.get(i).gruppi.listaGruppi.get(j).utenti.get(k).getPasswd());
                }
            }
        }
        p.close();
    }
    
    static void setUtente(Utente daSettare) throws FileNotFoundException{
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getUtente().equals(daSettare.getUtente())){
                        lista.set(i, daSettare);
            }
        }
        aggiornaFile();
    }
    
    static void removeUser(Utente aThis) throws FileNotFoundException {
        lista.remove(aThis);
        aggiornaFile();
    }
    
}

