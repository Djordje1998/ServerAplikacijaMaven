/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.racunar;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Racunar;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StavkaRacunara;
import java.util.ArrayList;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju pretragu objekta klase Racunar u bazi.
 * Na kraju provera postuslove operacije pretrage racunara u bazi.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class PretragaRacunara extends AbstractGenericOperation {

	/**
	 * Lista objekata klase Racunar kao rezultat pretrage.
	 */
    private ArrayList<Racunar> racunari;

    /**
	 * Proverava preduslove za pretragu objekta klase Racunar u bazi.
	 * Ne postoje preduslovi za pretragu racunara.
	 * 
	 * @throws java.lang.Exception Ako je neko dodje do greske prilikom pretrage u bazi.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Pretraga racunara u bazi.
     * 
     * @param param Racunar po cijim atributima se vrsi pretraga kao klasa Racunar.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom pretrage racunara u bazi.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        racunari = (ArrayList<Racunar>) repository.getAll((Racunar) param);
        for (Racunar racunar : racunari) {
            StavkaRacunara stavka = new StavkaRacunara();
            stavka.setRacunar(racunar);
            racunar.setStavke((ArrayList<StavkaRacunara>) repository.getAll((StavkaRacunara) stavka));
        }
    }

    /**
     * Proveravanje postuslova za pretragu objekta klase Racunar u bazi.
     * 
     * @param param Racunar po cijim atributima se vrsi pretraga kao klasa Racunar.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova pretrage racunara u bazu ili se vrati prazna lista.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
        if (racunari.isEmpty()) {
            throw new Exception("Nema unetih racunara!");
        }
    }

    /**
     * Vraca listu racunara kao rezultat pretrage.
     * 
     * @return Lista racunara.
     */
    public ArrayList<Racunar> getRacunari() {
        return racunari;
    }

}
