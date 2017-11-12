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
public class PaymentQueue extends List{
    private float total=0;
    
    
    public PaymentQueue(){
        super();
    }
    
    
    public void register(Torta p){
        if(this.head==null){
        this.addHead(p);
        }else{
        this.addTail(p);
        }
    }
    
    
    public Torta pay()throws ListEmptyException{
        Torta t=null;
        ListItem tmp=head;
        if (head == null) throw new ListEmptyException();
        while (tmp!=null){
            if(tmp.getData().getTime()==0){
                t=tmp.getData();
                tmp.getPrev().setNext(tmp.getNext());
                tmp.getNext().setPrev(tmp.getPrev());
                tmp.setNext(null);
                tmp.setPrev(null);
                total+=this.waitingPayment();
                tmp=tail;
            }
            tmp=tmp.getNext();
        }
        
        return t;
    }
    
    
    public Torta pay(String t) throws ListEmptyException{
        Torta s=null;
        ListItem tmp=head;
        if (head == null) throw new ListEmptyException();
        while (tmp.getData()!=null){
            if(tmp.getData().getNome()== t){
                s=tmp.getData();
                tmp.getPrev().setNext(tmp.getNext());
                tmp.getNext().setPrev(tmp.getPrev());
                tmp.setNext(null);
                tmp.setPrev(null);
                total+=this.waitingPayment(t);
                tmp=tail;
                
            }
            tmp=tmp.getNext();
        }
        return s;
    }
    
    
    public float waitingPayment()throws ListEmptyException{
        float t=0;
        ListItem tmp=head;
        if (head == null) throw new ListEmptyException();
        while(tmp.getData()== null){
            if(tmp.getData().getNome().equals("sacher")){
            t+=30;
            }else{
                if(tmp.getData().getNome().equals("millefoglie")){
                     t+=25; 
            }else{
                    if(tmp.getData().getNome().equals("tiramisù")){
                         t+=15;
                    }
                }
            }
            tmp=tmp.getNext();
            
        }
        
        return t;
    }
    
    
     public float waitingPayment(String c) throws ListEmptyException{
          float t=0;
        ListItem tmp=head;
        if (head == null) throw new ListEmptyException();
        while(tmp.getData()== null){
            if(tmp.getData().getNome().equals(c)){
                if(c.equals("sacher")){
                t+=30;
            }else{
                if(c.equals("millefoglie")){
                     t+=25; 
            }else{
                if(c.equals("tiramisù")){
                         t+=15;
                }
            }
            }
            }
        }
        return t;
     }
     
     
     public float getTotal(){
         return total;
     }
}
