/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza.pkg4;

import java.awt.Color;

/**
 *
 * @author Riccardo
 */
public class Casella {
    
    private boolean occupata;
    private Color colore; //definisco il colore della casella

    public Casella() {
        occupata=false;
        colore=new Color(255,255,255); 
    }
    
    public Casella(boolean occupata, Color colore) {
        this.occupata = occupata;
        this.colore = colore;
    }

    public boolean isOccupata() {
        return occupata;
    }

    public Color getColore() {
        return colore;
    }

    public void setOccupata(boolean occupata) {
        this.occupata = occupata;
    }

    public void setColore(Color colore) {
        this.colore = colore;
    }
    
   
}
