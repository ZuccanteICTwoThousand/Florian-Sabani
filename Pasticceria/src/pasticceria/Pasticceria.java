/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasticceria;

/**
 *
 * @author Studente
 */
public class Pasticceria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ListEmptyException {
       ProductionQueue p= new ProductionQueue();
       PaymentQueue pa= new PaymentQueue();
       pa.register(p.register("sacher", 12));
       pa.register(p.register("millefoglie", 12));
       pa.register(p.register("tiramisù", 12));
       //p.update();
       System.out.println(pa.getTotal());
       pa.pay();
   }
    
}
