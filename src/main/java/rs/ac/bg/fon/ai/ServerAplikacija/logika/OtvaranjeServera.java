/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.logika;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.util.PropertyConst;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.util.PropertyRead;

/**
 *
 * @author DarkForce
 */
public class OtvaranjeServera extends Thread{
    
    private ServerSocket serverSocket;
    
    @Override
    public void run() {
        try {
            PropertyRead read = new PropertyRead();
            serverSocket = new ServerSocket(read.getInteger(PropertyConst.PORT));
            System.out.println("Server je pokrenut");
            while (true) {                
                Socket s = serverSocket.accept();
                KomunikacijaSaKlijentom ksk = new KomunikacijaSaKlijentom(s);
                ksk.start();
                System.out.println("Klijent se povezao");
            }
        } catch (IOException ex) {
            System.out.println("Server je ugasen");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    
    
}
