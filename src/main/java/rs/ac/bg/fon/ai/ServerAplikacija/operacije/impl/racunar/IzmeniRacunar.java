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
 *
 * @author DarkForce
 */
public class IzmeniRacunar extends AbstractGenericOperation {

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

    @Override
    protected void executeOperation(Object param) throws Exception {
        Racunar racunar = (Racunar) param;
        repository.edit(racunar);
        for (StavkaRacunara stavkaRacunara : racunar.getStavke()) {
            repository.edit(stavkaRacunara);
        }
    }

    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
