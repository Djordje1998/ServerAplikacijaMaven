/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.korisnik;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import java.util.ArrayList;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju pretragu objekta klase Korisnik u bazi.
 * Na kraju provera postuslove operacije pretrage korisnika u bazi.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class PretragaKorisnika extends AbstractGenericOperation {

	/**
	 * Lista objekata klase Korisnik kao rezultat pretrage.
	 */
    private ArrayList<Korisnik> korisnici;

	/**
	 * Proverava preduslove za pretragu objekta klase Korisnik u bazi.
	 * Ne postoje preduslovi za pretragu korisnika.
	 * 
	 * @throws java.lang.Exception Ako je neko dodje do greske prilikom pretrage u bazi.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Pretraga korisnika u bazu.
     * 
     * @param param Korisnik po cijim atributima se vrsi pretraga kao klasa Korisnik.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom pretrage korisnika u bazi.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        korisnici = (ArrayList<Korisnik>) repository.getAll((Korisnik) param);
    }

    /**
     * Vraca listu korisnika kao rezultat pretrage.
     * 
     * @return Lista korisnika.
     */
    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    /**
     * Proveravanje postuslova za pretragu objekta klase Korisnik u bazi.
     * 
     * @param param Korisnik po cijim atributima se vrsi pretraga kao klasa Korisnik.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova pretrage korisnika u bazu ili se vrati prazna lista.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
        if (korisnici.isEmpty()) {
            throw new Exception("Nema unetih korisnika!");
        }
    }
}
