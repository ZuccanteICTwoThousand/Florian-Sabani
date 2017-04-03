/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provainterfaccie;

/**
 *
 * @author florian
 */
public interface Figure{
    
    // this (object obj isPiuLargo)
    public int isPiuLargo(Figure other);
    
    public void sposta(int x, int y);
    
    public int getArea();
    
    public void disegna();
    
    public String getNome();
    
    public Object prendiLaPiuGrande(final Object object1,final Object object2);
}
