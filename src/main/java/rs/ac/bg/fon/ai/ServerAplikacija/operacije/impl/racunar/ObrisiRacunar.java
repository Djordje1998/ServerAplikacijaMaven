/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.racunar;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Racunar;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StavkaRacunara;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju brisanja objekta klase Racunar iz baze.
 * Na kraju provera postuslove operacije brisanja racunara iz baze.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class ObrisiRacunar extends AbstractGenericOperation{

	/**
	 * Proverava preduslove za brisanje objekta klase Racunar iz baze.
	 * Ne postoje preduslovi za operaciju brisanja racunara.
	 * 
	 * @throws java.lang.Exception Ako dodje do greske provere preduslova za brisanja racunara iz baze.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Brisanje racunara iz baze.
     * 
     * @param param Racunar koji se brise iz baze kao klasa Racunar.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom brisanja racunara iz baze.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Racunar racunar = (Racunar) param;
        repository.delete(racunar);
        for (StavkaRacunara stavkaRacunara : racunar.getStavke()) {
            repository.delete(stavkaRacunara);
        }
    }

    /**
     * Proveravanje postuslova za brisanje objekta klase Racunar iz baze.
     * Ne postoje postuslov za operaciju brisanja racunara.
     * 
     * @param param Komponenta koji se brise iz baze kao klasa Racunar.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova obrisanog racunara iz baze.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }
    
}
