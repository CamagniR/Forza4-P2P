/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza.pkg4;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Riccardo
 */
public class ThreadRicevi extends Thread{
    
    private ForzaQuattro condivisa;
    private DatagramSocket udpClientSocket;
    private int IDplayer;
    private Color coloreAvversario;
    private static int portaRicezione=2003;
    

    public ThreadRicevi(int port, ForzaQuattro c) throws Exception {
        this.udpClientSocket=new DatagramSocket(port);
        portaRicezione = port;
        condivisa=c;
        IDplayer=0;
        coloreAvversario=Color.BLACK;
    }


    public void run() {
        
        byte[] receiveData = new byte[1024];
        
        while (true) {            
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                
                udpClientSocket.receive(receivePacket);
            } catch (IOException ex) {
                Logger.getLogger(ThreadRicevi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                String messaggioRicevuto =  new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] campi= messaggioRicevuto.split(";");
                if (condivisa.getUltimoTag()=="STR" && campi[0]=="YES" ) {
                
                    condivisa.setStatoInizio(true);
                    
                }
                
                if (campi[0]== "STR" || campi[0]=="YES" && campi.length==3) {
                
                    //coloreAvversario=campi[2].;
                            
                }
                
                if (campi[0]=="INV" && condivisa.getUltimoGiocatore()!=IDplayer) {
                      
                    condivisa.occupaCasella(Integer.parseInt(campi[1]), coloreAvversario);
                    if (campi[2]=="true") {
                        condivisa.setPartitaFinita(true);
                    }
                    
                }
                
                if (campi[0]=="RIY") {
                    
                    condivisa.svuotaCampo();
                    
                }
                
                if (campi[0]=="RIN" || campi[0]=="CLS") {
                
                   stoppa();
                   // manca comando per chiudere la grafica 
                }
              
                
                condivisa.setUltimoTag(campi[0]);
                
                
               
                //condivisa.setCheckLetto(Boolean.FALSE);
                
                System.out.println(messaggioRicevuto + "\"\n");
                
                Thread.yield();
            } 
           
         
        }
    
    
    public void stoppa() {
    if (udpClientSocket != null) {
        udpClientSocket.close();
        udpClientSocket = null;
    }
    }
}
