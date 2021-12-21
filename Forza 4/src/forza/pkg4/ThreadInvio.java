/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza.pkg4;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Riccardo
 */
public class ThreadInvio extends Thread {
    
    private ForzaQuattro condivisa;
    private InetAddress destinationIPAddress;
    private DatagramSocket udpClientSocket;
    private String nick;
    private Color color;
    private int IDplayer;
    private int portaTrasmissione;

    
     public ThreadInvio(int port, String ip,ForzaQuattro c,String n,Color col) throws SocketException {
        try {
            this.udpClientSocket=new DatagramSocket(port);
            portaTrasmissione = port;
            condivisa=c;
            nick=n;
            color=col;
            IDplayer=0;
            destinationIPAddress = InetAddress.getByName(ip);;
            
            // Creo DatagramSocket
            //this.udpClientSocket = new DatagramSocket(clientport);
            this.udpClientSocket.connect(destinationIPAddress, portaTrasmissione);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ThreadInvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public DatagramSocket getSocket() {
        return this.udpClientSocket;
    }

    public void run() {       
        try {         
            // Input stream da tastiera
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (true) 
            {
                if (condivisa.isStatoInizio()==false) {
                    
              
                String clientMessage="";
                if (condivisa.getUltimoTag().equals("")) {
                    
                    clientMessage="STR;"+nick+";"+color;
                    IDplayer=1;
                    System.out.println("sono nel primo if");
                    
                }
                if (condivisa.getUltimoTag().equals("STR") && condivisa.getUltimoGiocatore()!=IDplayer) {
                    System.out.println("Vuoi iniziare la partita? [y/n]");
                    clientMessage=userInput.readLine();
                     if (clientMessage.equals("y")) {
                        IDplayer=2;
                        
                        //invio all'altro peer
                    clientMessage= "YES;"+nick+";"+color;
                    }
                     else{
                         clientMessage="NON";
                     }
                }
                
                if (condivisa.getUltimoTag().equals("YES")&&condivisa.isStatoInizio()==false && condivisa.getUltimoGiocatore()!=IDplayer) {
                     System.out.println("sei sicuro di voler iniziare la partita [y/n]");
                    clientMessage=userInput.readLine();
                     if (clientMessage.equals("y")) {
                    
                    //invio all'altro peer
                    clientMessage= "YES";
                    condivisa.setStatoInizio(true);
                    }
                     else{
                         clientMessage="NON";
                         stoppa();
                     }
                    
                }
                
                if (condivisa.getUltimoTag().equals("YES")&&condivisa.isStatoInizio()==true && condivisa.getUltimoGiocatore()!=IDplayer) {
                
                    
                    
                }
                
                
                condivisa.setUltimoGiocatore(IDplayer);  
                byte[] sendData = new byte[1024];
                sendData = clientMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destinationIPAddress, portaTrasmissione);
                udpClientSocket.send(sendPacket);
                //condivisa.setCheckLetto(Boolean.TRUE);
                Thread.yield();
            }
           }
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    public void stoppa() {
    if (udpClientSocket != null) {
        udpClientSocket.close();
        udpClientSocket = null;
    }
}
}
