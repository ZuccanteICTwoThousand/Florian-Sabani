package provainterfaccie;

import java.awt.Point;

/**
 *
 * @author florian
 */
public class Rettangolo implements Figure {

    
    private final String nome = "Rettangolo";
    public int larghezza = 0;
    public int altezza = 0;
    public Point coordinate;    //Point. restituisce x ed y

    // Alcuni costruttori
    public Rettangolo() {
        coordinate = new Point(0, 0);
    }
    public Rettangolo(Point p) {
        coordinate = p;
    }
    public Rettangolo(int w, int h) {
        coordinate = new Point(0, 0);
        larghezza = w;
        altezza = h;
    }
    public Rettangolo(Point p, int w, int h) {
        coordinate = p;
        larghezza = w;
        altezza = h;
    }
    // muove il rettangolo nelle nuove coordinate
    @Override
    public void sposta(int x, int y) {
        coordinate.x = x;
        coordinate.y = y;
    }
    // returna l'area del rettangolo
    public int getArea() {
        return larghezza * altezza;
    }
        // 1 se piu grande , 0 se uguali , -1 se piu piccolo.
    @Override
    public int isPiuLargo(final Figure altraFigura) {
        Rettangolo altroRettangolo = (Rettangolo) altraFigura;  // Castato
        if (this.getArea() < altroRettangolo.getArea()) {
            return -1;
        } else if (this.getArea() > altroRettangolo.getArea()) {
            return 1;
        } else {
            return 0;
        }
    }
    // Confronta due figure
    @Override
    public Object prendiLaPiuGrande(final Object object1,final Object object2) {
        Rettangolo rett1 = (Rettangolo) object1;
        Rettangolo rett2 = (Rettangolo) object2;
        if ((rett1).isPiuLargo(rett2) > 0) {
            return object1;
        } else {
            return object2;
        }
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void disegna(){
        // Disegnera
    }
}
