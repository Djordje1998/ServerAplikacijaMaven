/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.ocena;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Ocena;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju dodavanja objekta klase Ocena u bazu.
 * Na kraju provera postuslove operacije dodavanja ocene u bazu.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class KreirajOcenu extends AbstractGenericOperation {

	/**
	 * Proverava preduslove za unos objekta klase Ocena u bazu.
	 * 
	 * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja preduslova ocene u bazu.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Dodavanje ocene u bazu.
     * 
     * @param param Ocena koja se dodaje u bazu kao klasa Ocena.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom dodavanja ocene u bazu.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Ocena)param);
    }

    /**
     * Proveravanje postuslova za unos objekta klase Ocenav u bazu.
     * Ne postoje postuslov za operaciju dodavanja ocene.
     * 
     * @param param Ocena koja se dodaje u bazu kao klasa Ocena.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova dodatne ocene u bazu.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
