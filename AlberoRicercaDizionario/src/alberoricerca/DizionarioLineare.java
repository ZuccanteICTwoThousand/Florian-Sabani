/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alberoricerca;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
class DizionarioLineare {
    
    ArrayList<Parola> listaConcatenata;
    
        DizionarioLineare(String file) throws FileNotFoundException {
        listaConcatenata = new ArrayList<Parola>();

        FileReader f = new FileReader(file);
        Scanner lettura = new Scanner(f);

        while (lettura.hasNextLine()) {
            String parola = lettura.nextLine();
            listaConcatenata.add(new Parola(parola,""));
        }
        
    }
        
    boolean trovaLineare(String parola){
        for (int i = 0; i < listaConcatenata.size(); i++) {
            if(parola.equals(listaConcatenata.get(i).parola)){
                return true;
            }
        }
        return false;
    }
        
    boolean trovaIterat(String parola){return false;}
    
    
    private class Parola{
        
        String parola,descrizione;
        
        public Parola(String parola, String descrizione) {
            this.parola = parola;
            this.descrizione = descrizione;
        }
    }
    
}
