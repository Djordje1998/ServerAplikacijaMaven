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
 *
 * @author DarkForce
 */
public class PretragaRacunara extends AbstractGenericOperation {

    private ArrayList<Racunar> racunari;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        racunari = (ArrayList<Racunar>) repository.getAll((Racunar) param);
        for (Racunar racunar : racunari) {
            StavkaRacunara stavka = new StavkaRacunara();
            stavka.setRacunar(racunar);
            racunar.setStavke((ArrayList<StavkaRacunara>) repository.getAll((StavkaRacunara) stavka));
        }
    }

    @Override
    protected void postconditions(Object param) throws Exception {
        if (racunari.isEmpty()) {
            throw new Exception("Nema unetih racunara!");
        }
    }

    public ArrayList<Racunar> getRacunari() {
        return racunari;
    }

}
