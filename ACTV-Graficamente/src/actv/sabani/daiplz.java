/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actv.sabani;

import java.io.FileNotFoundException;

/**
 *
 * @author Florian
 */
public class daiplz {
    public static void main (String[] args) throws FileNotFoundException{
        Mappa mappa = new Mappa("stops.csv");
        System.out.println(mappa.search(45.24,12.05));
    }
}
