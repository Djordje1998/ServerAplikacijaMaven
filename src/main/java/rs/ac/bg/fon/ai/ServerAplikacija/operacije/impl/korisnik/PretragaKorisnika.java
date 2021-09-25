/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.korisnik;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import java.util.ArrayList;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 *
 * @author DarkForce
 */
public class PretragaKorisnika extends AbstractGenericOperation {

    private ArrayList<Korisnik> korisnici;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        korisnici = (ArrayList<Korisnik>) repository.getAll((Korisnik) param);
    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    @Override
    protected void postconditions(Object param) throws Exception {
        if (korisnici.isEmpty()) {
            throw new Exception("Nema unetih korisnika!");
        }
    }
}
