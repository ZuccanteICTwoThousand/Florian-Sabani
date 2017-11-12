/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preparazionecodepile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
public class Scaffale {
    
    private File storage;
    private ArrayList<Prodotto> lista;
    
    public Scaffale(String nomeFile) throws FileNotFoundException {
        storage = new File(nomeFile);
        lista = new ArrayList<>();
        Scanner s = new Scanner(storage);
        while(s.hasNext()){
            String riga = s.nextLine();
            lista.add(new Prodotto(riga.substring(0,riga.indexOf("#")),Integer.parseInt(riga.substring(riga.indexOf("#")+1,riga.indexOf("@"))),Integer.parseInt(riga.substring(riga.indexOf("@")+1,riga.length()))));
        }
    }
    
    public void visitaScaffale(){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
    }
    
    
    
}
