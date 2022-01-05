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

    private static int ROW = 6;
    private static int COL = 7;
    private static Casella[][] matrix;
    private boolean partitaFinita; // False= non è ancora finita True= partita finita
    private boolean statoInizio;
    private String ultimoTag;
    private int ultimoGiocatore; //ultimo player che ha fatto una giocata 1=player1 2=player2

    public ForzaQuattro() {

        matrix = new Casella[ROW][];
        for (int i = 0; i < ROW; i++) {
            matrix[i] = new Casella[COL];
            for (int j = 0; j < COL; j++) {
                matrix[i][j] = new Casella();
            }
        }
        partitaFinita = false;
        statoInizio = false; //false = non è iniziata la partita
        ultimoTag = "";
        ultimoGiocatore = 0;

    }

    public int rigaLibera(int colonna) {
        boolean libera = false; //se è false la colonna è tutta piena
        int i; //5 è la riga più bassa
        i = 5;
        while (i >= 0 && libera == false) {
            if (matrix[i][colonna].isOccupata() == false) {
                libera = true;

            } else {
                i--;
            }
        }
        return i;
    }

    public void occupaCasella(int colonna, Color colore) {
        int rigalibera = rigaLibera(colonna);
        if (rigalibera != -1) {
            matrix[rigalibera][colonna].setOccupata(true);
            matrix[rigalibera][colonna].setColore(colore);

        }

    }

    public void svuotaCampo() {
        matrix = new Casella[ROW][];
        for (int i = 0; i < ROW; i++) {
            matrix[i] = new Casella[COL];
            for (int j = 0; j < COL; j++) {
                matrix[i][j] = new Casella();
            }
        }
        partitaFinita = false;
        statoInizio = false; //false = non è iniziata la partita
        ultimoTag = "";
        ultimoGiocatore = 0;

    }

    public boolean checkRighe(Color colore) {
        int contatore = 4;
        int r = 5;
        int c = 0;

        while (r >= 0 && contatore > 0) {

            while (c < 7 && contatore < 0) {
                if (matrix[r][c].getColore() == colore) {

                    contatore--;

                } else {

                    contatore = 4;
                }

            }
            r--;
        }

        if (contatore == 0) {
            return true; //trovati 4 di fila
        }

        return false;

    }

    public boolean checkColonne(Color colore) {
        return false;
    }

    public boolean checkDiagonali(Color colore) {

        //diagonale sinistra a destra
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                Casella[][] temp = matrix;
                    //basso sx
                    if (j > 2 && i <= 3) {
                        if (temp.equals(matrix[j - 1][i + 1]) && temp.equals(matrix[j - 2][i + 2]) && temp.equals(matrix[j - 3][i + 3])) {
                               checkWin(colore);
                        }
                    }//alto sx
                    else if (j <= 2 && i <= 3) {
                        if (temp.equals(matrix[j + 1][i + 1]) && temp.equals(matrix[j + 2][i + 2])&& temp.equals(matrix[j + 3][i + 3])){
                             checkWin(colore);
                        }
                    }//alto dx
                    else if (j <= 2 && i >= 3) {
                        if (temp.equals(matrix[j + 1][i - 1]) && temp.equals(matrix[j + 2][i - 2]) && temp.equals(matrix[j + 3][i - 3])) {
                            checkWin(colore);
                        }
                    }//basso dx
                    else if (j > 2 && i >= 3) {
                        if (temp.equals(matrix[j - 1][i - 1]) && temp.equals(matrix[j - 2][i - 2])&& temp.equals(matrix[j - 3][i - 3])) {
                            checkWin(colore);
                        }
                    }
                    
                }
            }
            return false;
        }
    
    

    public boolean checkWin(Color colore) {

        return false;

    }

    public void setPartitaFinita(boolean partitaFinita) {
        this.partitaFinita = partitaFinita;
    }

    public void setStatoInizio(boolean statoInizio) {
        this.statoInizio = statoInizio;
    }

    public boolean isPartitaFinita() {
        return partitaFinita;
    }

    public boolean isStatoInizio() {
        return statoInizio;
    }

    public String getUltimoTag() {
        return ultimoTag;
    }

    public void setUltimoTag(String ultimoTag) {
        this.ultimoTag = ultimoTag;
    }

    public int getUltimoGiocatore() {
        return ultimoGiocatore;
    }

    public void setUltimoGiocatore(int ultimoGiocatore) {
        this.ultimoGiocatore = ultimoGiocatore;
    }

}
