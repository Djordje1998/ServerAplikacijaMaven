/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.kontroler;

import rs.ac.bg.fon.ai.ServerAplikacija.forme.FrmKonfiguracijaServera;
import rs.ac.bg.fon.ai.ServerAplikacija.json.JsonConfigFormat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JOptionPane;
import rs.ac.bg.fon.ai.ServerAplikacija.kordinator.MainCordinator;

/**
 *
 * @author DarkForce
 */
public class KonfiguracijaServeraController {

    private final FrmKonfiguracijaServera frmKonfiguracijaServera;

    public KonfiguracijaServeraController(FrmKonfiguracijaServera frmKonfiguracijaServera) {
        this.frmKonfiguracijaServera = frmKonfiguracijaServera;
        addActionListeners();
    }

    public void openForm() {
        srediFormu();
        frmKonfiguracijaServera.setLocationRelativeTo(MainCordinator.getInstance().getMainServerController().getFrmMainServer());
        frmKonfiguracijaServera.setVisible(true);
    }

    private void srediFormu() {
    	try {
    		JsonConfigFormat read = JsonConfigFormat.readFromFile();
	        frmKonfiguracijaServera.getTxtUrl().setText(read.getUrl());
	        frmKonfiguracijaServera.getTxtUsername().setText(read.getUsername());
	        frmKonfiguracijaServera.getTxtPassword().setText(read.getPassword());
	        frmKonfiguracijaServera.getTxtPort().setText(String.valueOf(read.getPort()));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frmKonfiguracijaServera, e.getMessage(), "Greska citanju konfiguracionog fajla", JOptionPane.ERROR_MESSAGE);
            return;
		}
        
        frmKonfiguracijaServera.getTxtPort().grabFocus();
        frmKonfiguracijaServera.getTxtPort().setSelectionStart(0);
    }

    private void addActionListeners() {
        frmKonfiguracijaServera.getBtnSacuvajPodesavanja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajPodesavanja();
            }

            private void sacuvajPodesavanja() {
                String url = frmKonfiguracijaServera.getTxtUrl().getText().trim();
                String pass = frmKonfiguracijaServera.getTxtPassword().getText().trim();
                String user = frmKonfiguracijaServera.getTxtUsername().getText().trim();
                String portS = frmKonfiguracijaServera.getTxtPort().getText().trim();
                
                if (url.isEmpty() || pass.isEmpty() || user.isEmpty() || portS.isEmpty()) {
                    JOptionPane.showMessageDialog(frmKonfiguracijaServera, "Moraju sva polja biti popunjena", "Greska pri cuvanju podesavanja", JOptionPane.ERROR_MESSAGE);
                    return;
                }
  
                try {
                	int port = Integer.parseInt(portS);
                	
                	JsonConfigFormat write = new JsonConfigFormat();
                    write.setUrl( url);
                    write.setPassword(pass);
                    write.setUsername(user);
                    write.setPort(port);
                    write.writeToFile();
                    
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(frmKonfiguracijaServera, "Port mora biti broj", "Greska pri cuvanju podesavanja", JOptionPane.ERROR_MESSAGE);
                    return;
				}catch (Exception e) {
					JOptionPane.showMessageDialog(frmKonfiguracijaServera, e.getMessage(), "Greska pri cuvanju podesavanja", JOptionPane.ERROR_MESSAGE);
                    return;
				}
                
                JOptionPane.showMessageDialog(frmKonfiguracijaServera, "Uspesno sacuvana podesavanja", "Uspesno sacuvano", JOptionPane.INFORMATION_MESSAGE);
                frmKonfiguracijaServera.dispose();
            }
        });
    }

}
