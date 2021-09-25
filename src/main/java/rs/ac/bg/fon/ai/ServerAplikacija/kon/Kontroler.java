/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.kon;

import rs.ac.bg.fon.ai.ServerAplikacija.db.DbConnectionFactory;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Benchmark;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Komponenta;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Ocena;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Racunar;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StressTest;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.TipKomponente;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;
import rs.ac.bg.fon.ai.ServerAplikacija.logika.OtvaranjeServera;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.benchmark.KreirajBenchmark;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.komponenta.IzmeniKomponentu;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.komponenta.KreirajKomponentu;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.komponenta.ObrisiKomponentu;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.komponenta.PretragaKomponenti;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.korisnik.ObrisiKorisnika;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.korisnik.PretragaKorisnika;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.korisnik.PrijavaKorisnika;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.korisnik.KreirajKorisnika;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.ocena.KreirajOcenu;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.stresstest.VratiSveStressTestove;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.racunar.IzmeniRacunar;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.racunar.KreirajRacunar;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.racunar.ObrisiRacunar;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.racunar.PretragaRacunara;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.tipkomponente.VratiSveTipoveKomponenti;

/**
 *
 * @author DarkForce
 */
public class Kontroler {

    private static Kontroler instance;
    private OtvaranjeServera os;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public void pokreniServer() {
        os = new OtvaranjeServera();
        os.start();
    }

    public void ugasiServer() throws IOException, SQLException {
        ServerSocket serverSocket = os.getServerSocket();
        serverSocket.close();
        DbConnectionFactory.getInstance().getConnection().close();
    }

    public void kreirajKorisnika(Korisnik param) throws Exception {
        AbstractGenericOperation operation = new KreirajKorisnika();
        operation.execute(param);
    }

    public ArrayList<Korisnik> pretragaKorisnika(Korisnik param) throws Exception {
        AbstractGenericOperation operation = new PretragaKorisnika();
        operation.execute(param);
        return ((PretragaKorisnika) operation).getKorisnici();
    }

    public void obrisiKorisnika(Korisnik param) throws Exception {
        AbstractGenericOperation operation = new ObrisiKorisnika();
        operation.execute(param);
    }

    public Korisnik prijavaKorisnika(Korisnik param) throws Exception {
        AbstractGenericOperation operation = new PrijavaKorisnika();
        operation.execute(param);
        return ((PrijavaKorisnika) operation).getKorisnik();
    }

    public ArrayList<TipKomponente> vratiSveTipoveKomponenti(TipKomponente param) throws Exception {
        AbstractGenericOperation operation = new VratiSveTipoveKomponenti();
        operation.execute(param);
        return ((VratiSveTipoveKomponenti) operation).getTipovi();
    }

    public ArrayList<StressTest> vratiStressTestove(StressTest param) throws Exception {
        AbstractGenericOperation operation = new VratiSveStressTestove();
        operation.execute(param);
        return ((VratiSveStressTestove) operation).getTestovi();
    }

    public void kreirajKomponentu(Komponenta param) throws Exception {
        AbstractGenericOperation operation = new KreirajKomponentu();
        operation.execute(param);
    }

    public ArrayList<Komponenta> pretragaKomponenti(Komponenta param) throws Exception {
        AbstractGenericOperation operation = new PretragaKomponenti();
        operation.execute(param);
        return ((PretragaKomponenti) operation).getKomponente();
    }

    public void izmeniKomponentu(Komponenta param) throws Exception {
        AbstractGenericOperation operation = new IzmeniKomponentu();
        operation.execute(param);
    }

    public void obrisiKomponentu(Komponenta param) throws Exception {
        AbstractGenericOperation operation = new ObrisiKomponentu();
        operation.execute(param);
    }

    public void kreirajRacunar(Racunar param) throws Exception {
        AbstractGenericOperation operation = new KreirajRacunar();
        operation.execute(param);
    }

    public void izmeniRacunar(Racunar param) throws Exception {
        AbstractGenericOperation operation = new IzmeniRacunar();
        operation.execute(param);
    }

    public void obrisiRacunar(Racunar param) throws Exception {
        AbstractGenericOperation operation = new ObrisiRacunar();
        operation.execute(param);
    }

    public ArrayList<Racunar> pretragaRacunara(Racunar param) throws Exception {
        AbstractGenericOperation operation = new PretragaRacunara();
        operation.execute(param);
        return ((PretragaRacunara) operation).getRacunari();
    }

    public void kreirajBenchmark(Benchmark param) throws Exception {
        AbstractGenericOperation operation = new KreirajBenchmark();
        operation.execute(param);
    }

    public void kreirajOcenu(Ocena param) throws Exception {
        AbstractGenericOperation operation = new KreirajOcenu();
        operation.execute(param);
    }

    public int oceniKomponentu(Komponenta komponenta, StressTest test) {
        return komponenta.oceni(test);
    }

    public int oceniRacunar(Racunar racunar, StressTest test) {
        return racunar.oceni(test);
    }
    
    public String vratiZakljucakKomponente(int prva, int druga) {
        return Komponenta.vratiZakljucak(prva, druga);
    }

    public String vratiZakljucakRacunara(int prvi, int drugi) {
        return Racunar.vratiZakljucak(prvi, drugi);
    }

}
