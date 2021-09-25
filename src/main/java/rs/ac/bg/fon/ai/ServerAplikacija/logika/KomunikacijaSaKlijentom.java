/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.logika;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Benchmark;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Komponenta;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Ocena;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Racunar;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StressTest;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.TipKomponente;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.communication.Receiver;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.communication.Sender;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.communication.object.Request;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.communication.object.Response;
import rs.ac.bg.fon.ai.ServerAplikacija.kon.Kontroler;

/**
 *
 * @author DarkForce
 */
public class KomunikacijaSaKlijentom extends Thread {

    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;
    private boolean kraj = false;

    public KomunikacijaSaKlijentom(Socket s) {
        this.socket = s;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Request zahtev = (Request) receiver.recieve();
                Response odgovor = new Response();
                try {
                    switch (zahtev.getOperation()) {
                        case LOGOVANJE:
                            String[] imeILozinka = (String[]) zahtev.getPaket();
                            Korisnik korisnik = new Korisnik();
                            korisnik.setKorisnickoIme(imeILozinka[0]);
                            korisnik.setSifra(imeILozinka[1]);
                            korisnik = Kontroler.getInstance().prijavaKorisnika(korisnik);
                            odgovor.setResult(korisnik);
                            break;

                        case UGASI:
                            kraj = true;
                            socket.close();
                            break;

                        case VRATI_TIPOVE_KOMPONENTI:
                            TipKomponente vratiTipove = new TipKomponente();
                            ArrayList<TipKomponente> tipovi = Kontroler.getInstance().vratiSveTipoveKomponenti(vratiTipove);
                            odgovor.setResult(tipovi);
                            break;

                        case VRATI_STRESS_TESTOVE:
                            StressTest vratiTestove = new StressTest();
                            ArrayList<StressTest> testovi = Kontroler.getInstance().vratiStressTestove(vratiTestove);
                            odgovor.setResult(testovi);
                            break;

                        case KREIRAJ_KOMPONENTU:
                            Komponenta komponenta = (Komponenta) zahtev.getPaket();
                            Kontroler.getInstance().kreirajKomponentu(komponenta);
                            break;

                        case PRETRAZI_KOMPONENTU:
                            Komponenta komponentaZaPretragu = new Komponenta();
                            komponentaZaPretragu.setNazivKomponente((String) zahtev.getPaket());
                            ArrayList<Komponenta> komponente = Kontroler.getInstance().pretragaKomponenti(komponentaZaPretragu);
                            odgovor.setResult(komponente);
                            break;

                        case IZMENI_KOMPONENTU:
                            Komponenta zaIzmenu = (Komponenta) zahtev.getPaket();
                            Kontroler.getInstance().izmeniKomponentu(zaIzmenu);
                            break;

                        case OBRISI_KOMPONENTU:
                            Komponenta zaBrisanje = (Komponenta) zahtev.getPaket();
                            Kontroler.getInstance().obrisiKomponentu(zaBrisanje);
                            break;

                        case KREIRAJ_RACUNAR:
                            Racunar racunar = (Racunar) zahtev.getPaket();
                            Kontroler.getInstance().kreirajRacunar(racunar);
                            break;

                        case IZMENI_RACUNAR:
                            Racunar racunarIzmena = (Racunar) zahtev.getPaket();
                            Kontroler.getInstance().izmeniRacunar(racunarIzmena);
                            break;

                        case OBRISI_RACUNAR:
                            Racunar racunarBrisanje = (Racunar) zahtev.getPaket();
                            Kontroler.getInstance().obrisiRacunar(racunarBrisanje);
                            break;

                        case PRETRAZI_RACUNAR:
                            Racunar pretragaRacunara = new Racunar();
                            pretragaRacunara.setNazivRacunara((String) zahtev.getPaket());
                            ArrayList<Racunar> trazeniRacunari = Kontroler.getInstance().pretragaRacunara(pretragaRacunara);
                            odgovor.setResult(trazeniRacunari);
                            break;

                        case KREIRAJ_BENCHMARK:
                            Benchmark benchmark = (Benchmark) zahtev.getPaket();
                            Kontroler.getInstance().kreirajBenchmark(benchmark);
                            break;

                        case KREIRAJ_OCENU:
                            Ocena ocenaCuvanje = (Ocena) zahtev.getPaket();
                            Kontroler.getInstance().kreirajOcenu(ocenaCuvanje);
                            break;

                        case OCENI_KOMPONENTU:
                            Object[] komponentaTest = (Object[]) zahtev.getPaket();
                            int ocena = Kontroler.getInstance().oceniKomponentu((Komponenta) komponentaTest[0], (StressTest) komponentaTest[1]);
                            odgovor.setResult(ocena);
                            break;

                        case OCENI_RACUNAR:
                            Object[] racunarTest = (Object[]) zahtev.getPaket();
                            int ocenaRacunara = Kontroler.getInstance().oceniRacunar((Racunar) racunarTest[0], (StressTest) racunarTest[1]);
                            odgovor.setResult(ocenaRacunara);
                            break;
                            
                        case VRATI_ZAKLJUCAK_KOMPONENTI:
                            int[] ocene = (int[]) zahtev.getPaket();
                            String zakljucak = Kontroler.getInstance().vratiZakljucakKomponente(ocene[0], ocene[1]);
                            odgovor.setResult(zakljucak);
                            break;

                        case VRATI_ZAKLJUCAK_RACUNARA:
                            int[] oceneZakljucak = (int[]) zahtev.getPaket();
                            String zakljucakRacunara = Kontroler.getInstance().vratiZakljucakRacunara(oceneZakljucak[0], oceneZakljucak[1]);
                            odgovor.setResult(zakljucakRacunara);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    odgovor.setException(e);
                }
                sender.send(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(KomunikacijaSaKlijentom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
