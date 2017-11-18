package provagrafo;
/**
 *
 * @author florian.sabani
 */

/**
 *
 * @author Florian
 */
public class ProvaGrafo {
    public static void main(String args[]){
//                        0   1   2   3   4   5   6
        String[] nodi = {"A","B","C","D","E","F","G"};
//                        0   1   2   3   4   5   6

        Grafo<String> g = new Grafo(nodi,true);
        
        g.addArco("A", "B", 1);
        g.addArco("A", "C", 4);
        
        g.addArco("G", "E", 7);
        g.addArco("G", "F", 6);
        
        g.addArco("B", "E", 6);
        g.addArco("B", "A", 1);
        g.addArco("B", "D", 3);
        
        g.addArco("C", "F", 5);
        g.addArco("C", "A", 4);
        g.addArco("C", "D", 2);
        
        g.addArco("D", "E", 2);
        g.addArco("D", "F", 4);
        g.addArco("D", "B", 3);
        g.addArco("D", "C", 2);
        
        g.addArco("E", "F", 2);
        g.addArco("E", "D", 2);
        g.addArco("E", "B", 6);
        g.addArco("E", "G", 7);
        
        
        g.addArco("F", "E", 2);
        g.addArco("F", "G", 6);
        g.addArco("F", "D", 4);
        g.addArco("F", "C", 5);
        
        g.dfs();
        System.out.println();
        g.bfs();
        
        System.out.println(g.minCosto("A", "G"));
    }
    
}