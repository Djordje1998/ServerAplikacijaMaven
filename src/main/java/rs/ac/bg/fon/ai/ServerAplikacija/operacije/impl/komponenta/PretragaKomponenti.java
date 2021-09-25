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
 *
 * @author DarkForce
 */
public class PretragaKomponenti extends AbstractGenericOperation {

    private ArrayList<Komponenta> komponente;

    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        komponente = (ArrayList<Komponenta>) repository.getAll((Komponenta) param);
    }

    @Override
    protected void postconditions(Object param) throws Exception {
        if (komponente.isEmpty()) {
            throw new Exception("Nema unetih komponenti!");
        }
    }

    public ArrayList<Komponenta> getKomponente() {
        return komponente;
    }

}
