/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.komponenta;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Komponenta;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju brisanja objekta klase Komponenta iz baze.
 * Na kraju provera postuslove operacije brisanja komponente iz baze.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class ObrisiKomponentu extends AbstractGenericOperation {

	/**
	 * Proverava preduslove za brisanje objekta klase Komponenta iz baze.
	 * Ne postoje preduslovi za operaciju brisanja korisnika.
	 * 
	 * @throws java.lang.Exception Ako dodje do greske provere preduslova za brisanja korisnika iz baze.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Brisanje komponente iz baze.
     * 
     * @param param Komponenta koji se brise iz baze kao klasa Komponenta.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom brisanja komponente iz baze.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Komponenta)param);
    }

    /**
     * Proveravanje postuslova za brisanje objekta klase Komponenta iz baze.
     * Ne postoje postuslov za operaciju brisanja korisnika.
     * 
     * @param param Komponenta koji se brise iz baze kao klasa Komponenta.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova obrisane komponente iz baze.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
