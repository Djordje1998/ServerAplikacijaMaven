/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.korisnik;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 *
 * @author DarkForce
 */
public class ObrisiKorisnika extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Korisnik)param);
    }

    @Override
    protected void postconditions(Object param) throws Exception {
    }
    
}