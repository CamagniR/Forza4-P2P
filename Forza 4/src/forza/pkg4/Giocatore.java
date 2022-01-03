/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza.pkg4;

import java.awt.Color;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Riccardo
 */
public class Giocatore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, SocketException, Exception {
        // TODO code application logic here
        
     ForzaQuattro condivisa= new ForzaQuattro();
     Color colore=Color.BLACK;
     String nickName;
     InetAddress address ;
     DatagramSocket udpClientSocket;
        
     Scanner scanner = new Scanner(System.in);
     System.out.println("Inserisci IP del peer del destinatario:");
     String ip= scanner.nextLine();
        
        System.out.println("Inserisci nickname:");
        nickName= scanner.nextLine();
        System.out.println("Inserisci colore [R-G-B-V]:");
        
        String codColore= scanner.nextLine();
        
        switch(codColore){
                case "R":
                    colore=Color.RED;
                    break;
                case "G":
                    colore=Color.YELLOW;
                    break;
                case "B":
                    colore=Color.BLUE;
                    break;
                case "V":
                    colore=Color.GREEN;
                    break;            
        }
                    
        
        
        
        
        ThreadRicevi threadRiceve = new ThreadRicevi(2003,condivisa);
        threadRiceve.start();
        
        ThreadInvio threadInvio = new ThreadInvio(2004,ip,condivisa,nickName,colore);
        threadInvio.start();
                
        
        
    }
    
}  
     
        
        
        
        
        
        
        
        
    
    

