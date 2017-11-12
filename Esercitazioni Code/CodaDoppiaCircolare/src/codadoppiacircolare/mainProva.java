/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codadoppiacircolare;

import java.util.Random;

/**
 *
 * @author Florian
 */
public class mainProva {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CodaDoppiaCircolare<Integer> c = new CodaDoppiaCircolare();
        Random r = new Random();
        int max = 100;  // sia valore che n Elementi
        int valore = r.nextInt(max);
        for (int i = 0; i < valore; i++) {
            c.enQueueDown(valore);
            valore = r.nextInt(max);
        }
        System.out.println(c);
        int a = c.nElementi-c.nElementi/2,da =  c.nElementi-r.nextInt(c.nElementi);
        System.out.println("Sotto sequenza da "+da +" a "+a +" "+c.subsequence(da,a));
        
    }
    
}
