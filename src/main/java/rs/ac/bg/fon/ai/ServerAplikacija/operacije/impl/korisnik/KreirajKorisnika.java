/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.korisnik;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju dodavanja objekta klase Korisnik u bazu.
 * Na kraju provera postuslove operacije dodavanja korisnika u bazu.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class KreirajKorisnika extends AbstractGenericOperation {

	/**
	 * Proverava preduslove za unos objekta klase Korisnik u bazu.
	 * 
	 * @throws java.lang.Exception Ako je neko polje korisnika prazno ili ako nisu jednaka polja sifra i sifraPotvrda.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getIme().isEmpty()) {
            throw new Exception("Ime je obavezno polje!");
        }
        if (korisnik.getPrezime().isEmpty()) {
            throw new Exception("Prezime je obavezno polje!");
        }
        if (korisnik.getKorisnickoIme().isEmpty()) {
            throw new Exception("Korisnicko ime je obavezno polje!");
        }
        if ( korisnik.getSifra().isEmpty()) {
            throw new Exception("Lozinka je obavezno polje!");
        }
        if (korisnik.getSifraPotvrda().isEmpty()) {
            throw new Exception("Potvrdi lozinku je obavezno polje!");
        }

        if (!korisnik.getSifra().equals(korisnik.getSifraPotvrda())) {
            throw new Exception("Lozinka i potvrda lozinke nisu jednake");
        }
    }

    /**
     * Dodavanje korisnika u bazu.
     * 
     * @param param Korisnik koji se dodaje u bazu kao klasa Korisnik.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom dodavanja korisnika u bazu.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Korisnik)param);
    }

    /**
     * Proveravanje postuslova za unos objekta klase Korisnik u bazu.
     * Ne postoje postuslov za operaciju dodavanja korisnika.
     * 
     * @param param Korisnik koji se dodaje u bazu kao klasa Korisnik.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova dodatnog korisnika u bazu.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
