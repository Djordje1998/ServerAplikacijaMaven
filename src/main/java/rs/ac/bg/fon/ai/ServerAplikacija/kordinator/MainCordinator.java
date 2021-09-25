/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.kordinator;

import rs.ac.bg.fon.ai.ServerAplikacija.forme.FrmDodavanjeKorisnika;
import rs.ac.bg.fon.ai.ServerAplikacija.forme.FrmKonfiguracijaServera;
import rs.ac.bg.fon.ai.ServerAplikacija.forme.FrmMainServer;
import rs.ac.bg.fon.ai.ServerAplikacija.kontroler.DodavanjeKorisnikaController;
import rs.ac.bg.fon.ai.ServerAplikacija.kontroler.KonfiguracijaServeraController;
import rs.ac.bg.fon.ai.ServerAplikacija.kontroler.MainServerController;

/**
 *
 * @author DarkForce
 */
public class MainCordinator {
    
    private static MainCordinator instance;
    private final MainServerController mainServerController;

    private MainCordinator() {
        mainServerController = new MainServerController(new FrmMainServer());
    }

    public static MainCordinator getInstance() {
        if(instance==null){
            instance = new MainCordinator();
        }
        return instance;
    }
    
    public void otvoriFrmMainServer(){
        mainServerController.openForm();
    }

    public void otvoriFrmDodavanjeKorisnika() {
        DodavanjeKorisnikaController dodavanjeKorisnikaController = new DodavanjeKorisnikaController(new FrmDodavanjeKorisnika(mainServerController.getFrmMainServer(), true));
        dodavanjeKorisnikaController.openForm();
    }

    public void otvoriFrmKonfiguracijaServera() {
        KonfiguracijaServeraController konfiguracijaServeraController = new KonfiguracijaServeraController(new FrmKonfiguracijaServera(mainServerController.getFrmMainServer(), true));
        konfiguracijaServeraController.openForm();
    }

    public MainServerController getMainServerController() {
        return mainServerController;
    }
    
    
}
