/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasticceria;

import java.util.EmptyStackException;

/**
 *
 * @author Studente
 */
public class ProductionQueue extends List{
    
    
    public ProductionQueue(){
        super();
    }
    
    
    public int count (String t){
        ListItem tmp= head;
        int cont=0;
        
        while(tmp.getNext().getData()== null){
            if(tmp.getData().getNome() == t){
                cont+=1;
            }
            tmp=tmp.getNext();
        }
        return cont;
    }
    
    
    public Torta register(String nom, int t){
        Torta torta= new Torta(nom, t);
        this.addTail(torta);
        return torta;
    }
    
    
    public Torta ready() throws EmptyStackException{
        ListItem tmp = head;
        Torta torta=null;
        while(tmp.getData().getTime()==0){
            tmp=tmp.getNext();
        }
        if(tmp.getData()!= null){
            torta = tmp.getData();
        }else{
           throw new EmptyStackException();
        }
        return torta;
    }
    
    
    public void update(){
        ListItem tmp = head;
        while(tmp.getData()==null){
            tmp.getData().update();
        }
    }
}
