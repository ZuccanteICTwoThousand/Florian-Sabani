/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impllementazionegrafo;

/**
 *
 * @author Florian
 */
public class ImpllementazioneGrafo {
    public static void main(String args[]){
//                          0      1     2      3     4
        String[] nodi = {"ciao","come","va","tutto","bene"};
//                          0      1     2      3     4

        Graph<String> g = new Graph(nodi);
 
        g.addArco(0, 3);
        g.addArco(0, 2);
        g.addArco(1, 2);
        g.addArco(2, 0);
        g.addArco(2, 3);
        g.addArco(3, 3);
 
        /*
                       --
                      V  |
        ciao ----> tutto-|
           ^         ^
            \        |
             va<----come
        */
        
        
        g.dfs();
    }
    
}
