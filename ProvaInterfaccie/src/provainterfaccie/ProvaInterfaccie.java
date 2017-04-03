/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provainterfaccie;

import java.awt.Point;

/**
 *
 * @author florian
 */
public class ProvaInterfaccie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        Figure rettangolo = new Rettangolo(new Point(2,4), 300 , 400);
        
        System.out.println("Rettangolo 1 : "+rettangolo.getArea());
        
        rettangolo.sposta(10, 10);
        
        Figure rettangolo2 = new Rettangolo(new Point(2,4), 600 , 600);

        Figure rettangolo3 = (Figure) new Rettangolo().prendiLaPiuGrande(rettangolo, rettangolo2);
        
        System.out.println(rettangolo3.getNome()+"\n"+rettangolo3.getArea());
        
        Figure triangolo = new Triangolo(new Point(1,1), 400/*altezza*/ ,300/*base*/);
        
        System.out.println(triangolo.getNome());
    }
    
}
