/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.kontroler;

import rs.ac.bg.fon.ai.ServerAplikacija.forme.FrmKonfiguracijaServera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ai.ServerAplikacija.kordinator.MainCordinator;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.util.PropertyConst;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.util.PropertyRead;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.util.PropertyWrite;

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
//    	  STARA IMPLEMENTACIJA
//    	
//        PropertyRead read = new PropertyRead();
//        frmKonfiguracijaServera.getTxtUrl().setText(read.getString(PropertyConst.URL));
//        frmKonfiguracijaServera.getTxtUsername().setText(read.getString(PropertyConst.USER));
//        frmKonfiguracijaServera.getTxtPassword().setText(read.getString(PropertyConst.PASS));
//        frmKonfiguracijaServera.getTxtPort().setText(read.getString(PropertyConst.PORT));
        
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
                String port = frmKonfiguracijaServera.getTxtPort().getText().trim();
                
                if (url.isEmpty() || pass.isEmpty() || user.isEmpty() || port.isEmpty()) {
                    JOptionPane.showMessageDialog(frmKonfiguracijaServera, "Moraju sva polja biti popunjena", "Greska pri cuvanju podesavanja", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
//                STARA IMPLEMENTACIJA
//                
//                PropertyWrite write = new PropertyWrite();
//                write.setValues(PropertyConst.URL, url);
//                write.setValues(PropertyConst.PASS, pass);
//                write.setValues(PropertyConst.USER, user);
//                write.setValues(PropertyConst.PORT, port);
//                write.writeProperty();
                
                JOptionPane.showMessageDialog(frmKonfiguracijaServera, "Uspesno sacuvana podesavanja", "Uspesno sacuvano", JOptionPane.INFORMATION_MESSAGE);
                frmKonfiguracijaServera.dispose();
            }
        });
    }

}
