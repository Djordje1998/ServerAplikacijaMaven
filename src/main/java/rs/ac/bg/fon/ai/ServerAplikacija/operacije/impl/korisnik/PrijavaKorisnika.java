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
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju pretrage trazenog korisnika u bazi.
 * Na kraju provera postuslove operacije pretrage trazenog korisnika.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class PrijavaKorisnika extends AbstractGenericOperation {

	/**
	 * Lista objekata klase Korisnik kao rezultat prijave.
	 */
    private ArrayList<Korisnik> korisnici;

	/**
	 * Proverava preduslove za pretragu objekta klase Korisnik u bazi.
	 * 
	 * @throws java.lang.Exception Ako je neko parametar klase Korisnik sa praznim String-om korisnickog imena ili sifre.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getKorisnickoIme().isEmpty()) {
            throw new Exception("Morate uneti vrednost u polje Korisnicko ime.");
        }
        if (korisnik.getSifra().isEmpty()) {
            throw new Exception("Morate uneti vrednost u polje Lozinka.");
        }
    }

    /**
     * Proveravanje da li postoji u bazi korisnik kao onaj koji se prosledjuje metodi.
     * 
     * @param param Korisnik koji trazi pristup sistemu klasa Korisnik.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom pretrage korisnika u bazi ili je sifra ili koriscniko ime prazan String.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        korisnici = (ArrayList<Korisnik>) repository.getAll((Korisnik) param);
    }

    /**
     * Vraca prvog korisnika iz lsite.
     * 
     * @return Prvi korisnik iz liste kao klasa Korisnik.
     */
    public Korisnik getKorisnik() {
        return korisnici.get(0);
    }

    /**
     * Proveravanje postuslova za pretragu objekta klase Korisnik u bazi.
     * 
     * @param param Korisnik koji se trazi u bazu kao klasa Korisnik.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova pretrage korisnika u bazu ili se vrati prazna lista.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
        if (korisnici.isEmpty()) {
            throw new Exception("Ne postoji uneti korisnik, pokusajte ponovo!");
        }
    }

}
