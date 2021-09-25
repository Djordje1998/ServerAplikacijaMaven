/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.komponenta;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Komponenta;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 *
 * @author DarkForce
 */
public class ObrisiKomponentu extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Komponenta)param);
    }

    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
