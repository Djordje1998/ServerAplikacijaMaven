/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.kontroler;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import rs.ac.bg.fon.ai.ServerAplikacija.forme.FrmDodavanjeKorisnika;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ai.ServerAplikacija.kon.Kontroler;
import rs.ac.bg.fon.ai.ServerAplikacija.kordinator.MainCordinator;
import rs.ac.bg.fon.ai.ServerAplikacija.model.TabelModelKorisnici;

/**
 *
 * @author DarkForce
 */

public class DodavanjeKorisnikaController {

    private final FrmDodavanjeKorisnika frmDodavanjeKorisnika;

    public DodavanjeKorisnikaController(FrmDodavanjeKorisnika frmDodavanjeKorisnika) {
        this.frmDodavanjeKorisnika = frmDodavanjeKorisnika;
        addActionListeners();
    }

    public void openForm() {
        frmDodavanjeKorisnika.setLocationRelativeTo(MainCordinator.getInstance().getMainServerController().getFrmMainServer());
        frmDodavanjeKorisnika.setVisible(true);
        frmDodavanjeKorisnika.getTxtIme().grabFocus();
    }

    private void addActionListeners() {
        frmDodavanjeKorisnika.getBtnDodajKorisnika().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKorisnika();
            }

            private void dodajKorisnika() {
                String ime = frmDodavanjeKorisnika.getTxtIme().getText().trim();
                String prezime = frmDodavanjeKorisnika.getTxtPrezime().getText().trim();
                String korisnickoIme = frmDodavanjeKorisnika.getTxtKorisnickoIme().getText().trim();
                String lozinka = frmDodavanjeKorisnika.getTxtLozinka().getText().trim();
                String potvrdaLozinke = frmDodavanjeKorisnika.getTxtPotvrdiLozinku().getText().trim();

                Korisnik korisnik = new Korisnik(ime, prezime, korisnickoIme, lozinka,potvrdaLozinke);

                try {
                    Kontroler.getInstance().kreirajKorisnika(korisnik);
                    JOptionPane.showMessageDialog(frmDodavanjeKorisnika, "Uspesno dodat korisnik " + ime + " " + prezime + "!", "Uspesno dodat korisnik", JOptionPane.INFORMATION_MESSAGE);
                    isprazniPolja();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmDodavanjeKorisnika, ex.getMessage(), "Greska pri unosu", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void isprazniPolja() {
                frmDodavanjeKorisnika.getTxtIme().setText("");
                frmDodavanjeKorisnika.getTxtPrezime().setText("");
                frmDodavanjeKorisnika.getTxtKorisnickoIme().setText("");
                frmDodavanjeKorisnika.getTxtLozinka().setText("");
                frmDodavanjeKorisnika.getTxtPotvrdiLozinku().setText("");
                frmDodavanjeKorisnika.getTxtIme().grabFocus();
            }
        });
        
        frmDodavanjeKorisnika.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (MainCordinator.getInstance().getMainServerController().getFrmMainServer().getLblStatusServera().getText().equals("Server je pokrenut")) {
                    System.out.println("Aktiviran windows acitivated");
                    popuniTabelu();
                }
            }
            
        });
        
    }
     private void popuniTabelu() {
        TabelModelKorisnici tbk = (TabelModelKorisnici) MainCordinator.getInstance().getMainServerController().getFrmMainServer().getTblKorisnici().getModel();
        try {
            Korisnik k = new Korisnik();
            ArrayList<Korisnik> korisnici = Kontroler.getInstance().pretragaKorisnika(k);
            tbk.setKorisnici(korisnici);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MainCordinator.getInstance().getMainServerController().getFrmMainServer(), ex.getMessage(), "Greska u pripremi forme", JOptionPane.ERROR_MESSAGE);
        }
    }
}