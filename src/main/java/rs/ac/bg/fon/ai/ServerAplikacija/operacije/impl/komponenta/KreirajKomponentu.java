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
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju dodavanja objekta klase Komponenta u bazu.
 * Na kraju provera postuslove operacije dodavanja komponente u bazu.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class KreirajKomponentu extends AbstractGenericOperation {

	/**
	 * Proverava preduslove za unos objekta klase Komponenta u bazu.
	 * 
	 * @throws java.lang.Exception Ako je neko polje klase Komponenta prazno (null) ili brojevni atributi negativni.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
        Komponenta komponenta = (Komponenta) param;

        if (komponenta.getNazivKomponente().isEmpty()) {
            throw new Exception("Naziv komponente je prazan!");
        }
        if (komponenta.getTipKomponente() == null) {
            throw new Exception("Nije izabran tip komponente!");
        }
        if (komponenta.getOpis().isEmpty()) {
            throw new Exception("Opis komponente je prazan!");
        }
        if (komponenta.getTakt() <= 0) {
            throw new Exception("Takt mora biti veci od 0!");
        }
        if (komponenta.getCena() <= 0) {
            throw new Exception("Cena mora biti veci od 0!");
        }
    }

    /**
     * Dodavanje komponente u bazu.
     * 
     * @param param Komponenta koji se dodaje u bazu kao klasa Komponenta.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom dodavanja komponente u bazu.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Komponenta) param);
    }

    /**
     * Proveravanje postuslova za unos objekta klase Komponenta u bazu.
     * Ne postoje postuslov za operaciju dodavanja komponente.
     * 
     * @param param Komponenta koji se dodaje u bazu kao klasa Komponenta.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova dodate komponente u bazu.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
