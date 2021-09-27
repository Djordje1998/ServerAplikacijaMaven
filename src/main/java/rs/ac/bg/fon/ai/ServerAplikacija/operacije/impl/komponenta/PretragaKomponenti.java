/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.komponenta;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Komponenta;
import java.util.ArrayList;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju pretragu objekta klase Komponenta u bazi.
 * Na kraju provera postuslove operacije pretrage korisnika u bazi.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class PretragaKomponenti extends AbstractGenericOperation {

	/**
	 * Lista objekata klase Komponenta kao rezultat pretrage.
	 */
    private ArrayList<Komponenta> komponente;

    /**
	 * Proverava preduslove za pretragu objekta klase Komponenta u bazi.
	 * Ne postoje preduslovi za pretragu komponente.
	 * 
	 * @throws java.lang.Exception Ako je neko dodje do greske prilikom pretrage u bazi.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    /**
     * Pretraga komponente u bazi.
     * 
     * @param param Komponenta po cijim atributima se vrsi pretraga kao klasa Komponenta.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom pretrage komponente u bazi.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        komponente = (ArrayList<Komponenta>) repository.getAll((Komponenta) param);
    }

    /**
     * Proveravanje postuslova za pretragu objekta klase Komponenta u bazi.
     * 
     * @param param Komponenta po cijim atributima se vrsi pretraga kao klasa Komponenta.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova pretrage komponente u bazu ili se vrati prazna lista.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
        if (komponente.isEmpty()) {
            throw new Exception("Nema unetih komponenti!");
        }
    }

    /**
     * Vraca listu komponenti kao rezultat pretrage.
     * 
     * @return Lista komponenti.
     */
    public ArrayList<Komponenta> getKomponente() {
        return komponente;
    }

}
