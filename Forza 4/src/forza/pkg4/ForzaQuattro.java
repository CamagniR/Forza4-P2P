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



public class ForzaQuattro {
    
    private static int ROW=6;
    private static int COL=7;
    private static Casella[][] matrix; 
    private boolean partitaFinita; // False= non è ancora finita True= partita finita
    
    public ForzaQuattro() {
        
        for(int i=0;i<ROW;i++)
        {
            for(int j=0;j<COL;j++)
            {
                matrix[i][j]=new Casella();
            }
        }
        partitaFinita=false;
    
    }
    
    public int rigaLibera(int colonna)
    {
        boolean libera=false; //se è false la colonna è tutta piena
        int i; //5 è la riga più bassa
        i = 5;
        while(i>=0 && libera==false)
        {
            if (matrix[i][colonna].isOccupata()==false) {
                libera=true;
               
            }else
            {
                i--;
            }
        }
        return i;
    }
   
    public boolean occupaCasella(int colonna,Color colore)
    {
        int rigalibera=rigaLibera(colonna);
        if (rigalibera!=-1) {
            matrix[rigalibera][colonna].setOccupata(true);
            matrix[rigalibera][colonna].setColore(colore);
            return true;
        }
        
        return false;
    }
    
    
}
