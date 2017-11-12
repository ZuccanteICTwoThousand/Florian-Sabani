package actv.sabani;

public class NodoConc <T> {

  public T info;     //campo informazione
  public NodoConc <T> next;   //campo link

  //costruttore

  public NodoConc (T  info, NodoConc <T>  next){
    this.info = info;
    this.next = next;
  }

}
