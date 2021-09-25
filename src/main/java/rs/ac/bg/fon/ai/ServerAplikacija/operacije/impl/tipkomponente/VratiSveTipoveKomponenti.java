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
 *
 * @author DarkForce
 */
public class VratiSveTipoveKomponenti extends AbstractGenericOperation {

    ArrayList<TipKomponente> tipovi;

    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        tipovi = (ArrayList<TipKomponente>) repository.getAll((TipKomponente) param);
    }

    @Override
    protected void postconditions(Object param) throws Exception {
        if (tipovi.isEmpty()) {
            throw new Exception("Nema unetih tipova komponenti!");
        }
    }

    public ArrayList<TipKomponente> getTipovi() {
        return tipovi;
    }

}
