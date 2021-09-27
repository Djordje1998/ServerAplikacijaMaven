/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.kontroler;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import rs.ac.bg.fon.ai.ServerAplikacija.forme.FrmMainServer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ai.ServerAplikacija.kon.Kontroler;
import rs.ac.bg.fon.ai.ServerAplikacija.kordinator.MainCordinator;
import rs.ac.bg.fon.ai.ServerAplikacija.model.TabelModelKorisnici;

/**
 *
 * @author DarkForce
 */
public class MainServerController {

    private final FrmMainServer frmMainServer;

    public MainServerController(FrmMainServer frmMainServer) {
        this.frmMainServer = frmMainServer;
        addActionListeners();
    }

    public void openForm() {
        srediFormu();
        frmMainServer.setLocationRelativeTo(null);
        frmMainServer.setVisible(true);
    }

    private void srediFormu() {
        frmMainServer.getLblStatusServera().setText("Server je ugasen");
        frmMainServer.getLblStatusServera().setForeground(Color.red);
        frmMainServer.getTblKorisnici().setModel(new TabelModelKorisnici());
        frmMainServer.getBtnUgasiServer().setEnabled(false);
        frmMainServer.getBtnPokreniServer().setEnabled(true);
    }

    private void popuniTabelu() {
        TabelModelKorisnici tbk = (TabelModelKorisnici) frmMainServer.getTblKorisnici().getModel();
        try {
            Korisnik k = new Korisnik();
            k.setIme("");
            ArrayList<Korisnik> korisnici = Kontroler.getInstance().pretragaKorisnika(k);
            tbk.setKorisnici(korisnici);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmMainServer, ex.getMessage(), "Greska u pripremi forme", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListeners() {
        frmMainServer.getBtnPokreniServer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokreniServer();
            }

            private void pokreniServer() {
                Kontroler.getInstance().pokreniServer();

                frmMainServer.getLblStatusServera().setText("Server je pokrenut");
                frmMainServer.getLblStatusServera().setForeground(Color.green);
                frmMainServer.getBtnUgasiServer().setEnabled(true);
                frmMainServer.getBtnPokreniServer().setEnabled(false);

                popuniTabelu();
            }
        });

        frmMainServer.getBtnUgasiServer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ugasiServer();
            }

            private void ugasiServer() {
                try {
                    Kontroler.getInstance().ugasiServer();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frmMainServer, "Greska pri gasenju soketa", "Greska pri gasenju servera", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmMainServer, "Greska pri gasenju konekcije sa bazom", "Greska pri gasenju servera", JOptionPane.ERROR_MESSAGE);
                }

                frmMainServer.getLblStatusServera().setText("Server je ugasen");
                frmMainServer.getLblStatusServera().setForeground(Color.red);
                frmMainServer.getBtnUgasiServer().setEnabled(false);
                frmMainServer.getBtnPokreniServer().setEnabled(true);

                isprazniTabelu();
            }

            private void isprazniTabelu() {
                TabelModelKorisnici tmk = (TabelModelKorisnici) frmMainServer.getTblKorisnici().getModel();
                tmk.setKorisnici(new ArrayList<>());
            }
        });

        frmMainServer.getBtnObrisiKorisnika().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiKorisnika();
            }

            private void obrisiKorisnika() {
                int row = frmMainServer.getTblKorisnici().getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(frmMainServer, "Morate selektovati red za brisanje", "Greska pri brisanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int izbor = JOptionPane.showConfirmDialog(frmMainServer, "Da li zelite da obrisete selektovani korisnika iz tabele?", "Brisanje korisnika iz baze!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (izbor == JOptionPane.NO_OPTION) {
                    return;
                }
                if (izbor == JOptionPane.YES_OPTION) {
                    try {
                        TabelModelKorisnici tmk = (TabelModelKorisnici) frmMainServer.getTblKorisnici().getModel();
                        Korisnik korisnik = tmk.getKorisnikZaBrisanje(row);
                        Kontroler.getInstance().obrisiKorisnika(korisnik);
                        JOptionPane.showMessageDialog(frmMainServer, "Uspesno obrisan korisnik " + korisnik.toString() + "!", "Uspesno brisanje", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frmMainServer, ex.getMessage(), "Greska pri brisanju", JOptionPane.ERROR_MESSAGE);
                    }
                    popuniTabelu();
                }
            }
        });

        frmMainServer.getJmiDodajKorisnika().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiDodajKorisnika();
            }

            private void jmiDodajKorisnika() {
                MainCordinator.getInstance().otvoriFrmDodavanjeKorisnika();
            }
        });

        frmMainServer.getJmiPodesavanjaServera().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiPodesavanjaServera();
            }

            private void jmiPodesavanjaServera() {
                MainCordinator.getInstance().otvoriFrmKonfiguracijaServera();
            }
        });

    }

    public FrmMainServer getFrmMainServer() {
        return frmMainServer;
    }

}
