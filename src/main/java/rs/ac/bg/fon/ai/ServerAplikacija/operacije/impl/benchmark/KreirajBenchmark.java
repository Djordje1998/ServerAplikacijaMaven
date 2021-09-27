/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.benchmark;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Benchmark;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju dodavanja objekta klase Benchmark u bazu.
 * Na kraju provera postuslove operacije dodavanja benchmarka u bazu.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class KreirajBenchmark extends AbstractGenericOperation{

	/**
	 * Proverava preduslove za unos objekta klase Benchmark u bazu.
	 * 
	 * @throws java.lang.Exception Ako dodje do greske prilikom dodavanja benchmarka u bazu.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Dodavanje benchmarka u bazu.
     * 
     * @param param Benchmark koji se dodaje u bazu kao klasa Benchmark.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom dodavanja benchmarka u bazu.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Benchmark)param);
    }

    /**
     * Proveravanje postuslova za unos objekta klase Benchmark u bazu.
     * Ne postoje postuslov za operaciju dodavanja benchmarka.
     * 
     * @param param Benchmark koji se dodaje u bazu kao klasa Benchmark.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova dodatog benchmarka u bazu.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }
    
}
