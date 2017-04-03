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
public class Triangolo implements Figure {

    private final String nome = "Triangolo";
    public int base = 0;
    public int altezza = 0;
    public Point coordinate;    //Point. restituisce x ed y
    
 // Alcuni costruttori
    public Triangolo() {
        coordinate = new Point(0, 0);
    }
    public Triangolo(Point p) {
        coordinate = p;
    }
    public Triangolo(int w, int h) {
        coordinate = new Point(0, 0);
        base = w;
        altezza = h;
    }
    public Triangolo(Point p, int base, int h) {
        coordinate = p;
        this.base = base;
        altezza = h;
    }
    
  @Override
    public int isPiuLargo(final Figure altraFigura) {
        Triangolo altroTriangolo = (Triangolo) altraFigura;  // Castato
        if (this.getArea() < altroTriangolo.getArea()) {
            return -1;  // Se il tuo triangolo e piu piccolo di quello confrontato
        } else if (this.getArea() > altroTriangolo.getArea()) {
            return 1;   // Se la tua figura e piu grande
        } else {
            return 0;   // Se sono uguali
        }
    }
    // Confronta due figure
    @Override
    public Object prendiLaPiuGrande(final Object object1,final Object object2) {
        Triangolo rett1 = (Triangolo) object1;
        Triangolo rett2 = (Triangolo) object2;
        if ((rett1).isPiuLargo(rett2) > 0) {
            return object1;
        } else {
            return object2;
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getNome(){
        return this.nome;
    }
    
    @Override
    public void disegna(){
        // Disegnera
    }
    @Override
    public int getArea() {
        return this.base * this.altezza;
    }

    @Override
    public void sposta(int x, int y) {
        this.coordinate.x = x;
        this.coordinate.y = y;
    }
    
}
