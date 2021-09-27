/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.stresstest;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StressTest;
import java.util.ArrayList;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko su ispunjeni izvrsava operaciju pretrag svih objekta klase StressTest u bazi.
 * Na kraju provera postuslove operacije pretrage stress testa u bazi.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class VratiSveStressTestove extends AbstractGenericOperation{

	/**
	 * Lista objekata klase StressTest kao rezultat pretrage.
	 */
    private ArrayList<StressTest> testovi;
    
    /**
	 * Proverava preduslove za pretragu objekta klase StressTest u bazi.
	 * Ne postoje preduslovi za pretragu testova.
	 * 
	 * @throws java.lang.Exception Ako je neko dodje do greske prilikom pretrage u bazi.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Pretraga stress testova u bazi.
     * 
     * @param param Moze biti null, jer vraca sve stress testove u bazi.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom pretrage stress testova u bazi.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
         testovi = (ArrayList<StressTest>) repository.getAll((StressTest)param);
    }

    /**
     * Proveravanje postuslova za pretragu objekta klase StressTest u bazi.
     * 
     * @param param Moze biti null, jer vraca sve stress testove u bazi.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova pretrage stress testova u bazu ili se vrati prazna lista.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
        if (testovi.isEmpty()) {
            throw new Exception("Nema unetih stress testova!");
        }
    }

    /**
     * Vraca listu stress testova kao rezultat pretrage.
     * 
     * @return Lista stress testova.
     */
    public ArrayList<StressTest> getTestovi() {
        return testovi;
    }
    
}
