/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preparazionecodepile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
class NumeriScarto {
    private File f;
    ArrayList<Integer> l = new ArrayList<>();
    
    public NumeriScarto(String nomeFile) throws FileNotFoundException {
        f = new File(nomeFile);
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            l.add(s.nextInt());
        }
    }
    
    public void stampa() throws FileNotFoundException{
        PrintWriter p = new PrintWriter(f);
        int base = l.get(0);
        p.print(base+" "+"1");
        for (int i = 1; i < l.size(); i++) {
            p.print(l.get(i)+" "+differenzaPercentuale(base,l.get(i)));
        }
        
    }

    private int differenzaPercentuale(int base, Integer get) {
        int differenza = base-get;
        return (100*differenza)/base;
    }
    
    
}
