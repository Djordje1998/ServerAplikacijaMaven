/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.tipkomponente;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.TipKomponente;
import java.util.ArrayList;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko su ispunjeni izvrsava operaciju pretrag svih objekta klase TipKomponente u bazi.
 * Na kraju provera postuslove operacije pretrage tipova komponenti u bazi.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class VratiSveTipoveKomponenti extends AbstractGenericOperation {

	/**
	 * Lista objekata klase TipKomponente kao rezultat pretrage.
	 */
    ArrayList<TipKomponente> tipovi;

    /**
  	 * Proverava preduslove za pretragu objekta klase TipKomponente u bazi.
  	 * Ne postoje preduslovi za pretragu tipova komponenti.
  	 * 
  	 * @throws java.lang.Exception Ako je neko dodje do greske prilikom pretrage u bazi.
  	 */
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    /**
     * Pretraga tipova komponenti u bazi.
     * 
     * @param param Moze biti null, jer vraca sve tipova komponenti u bazi.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom pretrage tipova komponenti u bazi.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        tipovi = (ArrayList<TipKomponente>) repository.getAll((TipKomponente) param);
    }

    /**
     * Proveravanje postuslova za pretragu objekta klase TipKomponente u bazi.
     * 
     * @param param Moze biti null, jer vraca sve tipova komponenti u bazi.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova pretrage tipova komponenti u bazu ili se vrati prazna lista.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
        if (tipovi.isEmpty()) {
            throw new Exception("Nema unetih tipova komponenti!");
        }
    }

    /**
     * Vraca listu tipova komponenti kao rezultat pretrage.
     * 
     * @return Lista tipova komponenti.
     */
    public ArrayList<TipKomponente> getTipovi() {
        return tipovi;
    }

}
