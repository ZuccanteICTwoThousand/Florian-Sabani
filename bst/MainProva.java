package bst;
/**
 *
 * @author Florian
 */
public class MainProva {
    public static void main(String[] s){
        
        String[] array = {"questa","e'","una","prova","queste","parole","verrano","ordinate","secondo","il","criterio","alfabetico"};
        
        BST<String> v = new BST(array);
        
        v.add("ciao");
        v.add("aaa");
        v.add("come");
        v.add("va");
        
        v.simmetricaCrescente();
    }
}
