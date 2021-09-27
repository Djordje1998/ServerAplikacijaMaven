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
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju brisanja objekta klase Korisnik iz baze.
 * Na kraju provera postuslove operacije brisanja korisnika iz baze.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class ObrisiKorisnika extends AbstractGenericOperation{

	/**
	 * Proverava preduslove za brisanje objekta klase Korisnik iz baze.
	 * Ne postoje preduslovi za operaciju brisanja korisnika.
	 * 
	 * @throws java.lang.Exception Ako dodje do greske provere preduslova za brisanja korisnika iz baze.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Brisanje korisnika iz baze.
     * 
     * @param param Korisnik koji se brise iz baze kao klasa Korisnik.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom brisanja korisnika iz baze.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Korisnik)param);
    }

    /**
     * Proveravanje postuslova za brisanje objekta klase Korisnik iz baze.
     * Ne postoje postuslov za operaciju brisanja korisnika.
     * 
     * @param param Korisnik koji se brise iz baze kao klasa Korisnik.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova obrisanog korisnika iz baze.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }
    
}
