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
import rs.ac.bg.fon.ai.ServerAplikacija.json.JsonConfigFormat;

/**
 *
 * @author DarkForce
 */
public class OtvaranjeServera extends Thread{
    
    private ServerSocket serverSocket;
    
    @Override
    public void run() {
        try {
            JsonConfigFormat read = JsonConfigFormat.readFromFile();
            int port = read.getPort();
            serverSocket = new ServerSocket(port);
            System.out.println("Server je pokrenut na portu: " + port);
            while (true) {                
                Socket s = serverSocket.accept();
                KomunikacijaSaKlijentom ksk = new KomunikacijaSaKlijentom(s);
                ksk.start();
                System.out.println("Klijent se povezao");
            }
        } catch (IOException ex) {
            System.out.println("Server je ugasen");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    
    
}
