/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.racunar;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Racunar;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StavkaRacunara;
import java.text.SimpleDateFormat;
import java.util.Date;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 * Klasa koja nasledjuje abstraktnu klasu AbstractGenericOperation i implementira abstratne metode.
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju dodavanja objekta klase Racunar u bazu.
 * Na kraju provera postuslove operacije dodavanja racunara u bazu.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class KreirajRacunar extends AbstractGenericOperation {

	/**
	 * Proverava preduslove za unos objekta klase Racunar u bazu.
	 * 
	 * @throws java.lang.Exception Ako je neko polje klase Racunar prazno (null), cena nula ili garancija datum u proslosti.
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
        Racunar racunar = (Racunar) param;
        if (racunar.getNazivRacunara().isEmpty()) {
            throw new Exception("Naziv racunara je obavezno polje!");
        }
        if (racunar.getNamena().isEmpty()) {
            throw new Exception("Namena racunara je obavezno polje!");
        }
        if (sdf.format(racunar.getGarancija()).isEmpty()) {
            throw new Exception("Garancija racunara je obavezno polje!");
        }
        if (racunar.getGarancija().before(new Date())) {
            throw new Exception("Garancija mora da bude datum u buducnosti!");
        }
        if (racunar.getUkupnaCena()==0.0) {
            throw new Exception("Racunar mora da ima stavke!");
        }
    }

    /**
     * Dodavanje racunara u bazu.
     * 
     * @param param Racunar koji se dodaje u bazu kao klasa Racunar.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom dodavanja racunara u bazu.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Racunar racunar = (Racunar) param;
        repository.add(racunar);
        for (StavkaRacunara stavka : racunar.getStavke()) {
            repository.add(stavka);
        }
    }

    /**
     * Proveravanje postuslova za unos objekta klase Racunar u bazu.
     * Ne postoje postuslov za operaciju dodavanja racunara.
     * 
     * @param param Komponenta koji se dodaje u bazu kao klasa Racunar.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova dodatne racunara u bazu.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
