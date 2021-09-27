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
 * Prvo ispituje preduslove, ukoliko si ispunjeni izvrsava operaciju izmene objekta klase Racunar u bazu.
 * Na kraju provera postuslove operacije izmene racunara u bazi.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public class IzmeniRacunar extends AbstractGenericOperation {

	/**
	 * Proverava preduslove za izmenu objekta klase Racunar u bazu.
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
     * Izmena racunara u bazi.
     * 
     * @param param Racunar koji se menja u bazu kao klasa Racunar.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom izmene racunara u bazi.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Racunar racunar = (Racunar) param;
        repository.edit(racunar);
        for (StavkaRacunara stavkaRacunara : racunar.getStavke()) {
            repository.edit(stavkaRacunara);
        }
    }

    /**
     * Proveravanje postuslova za izmenu objekta klase Racunar u bazu.
     * Ne postoje postuslov za operaciju izmenu racunara.
     * 
     * @param param Racunar koja se menja u bazu kao klasa Racunar.
     * 
     * @throws java.lang.Exception Ako dodje do greske prilikom proveravanja postuslova ismene racunara u bazi.
     */
    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
