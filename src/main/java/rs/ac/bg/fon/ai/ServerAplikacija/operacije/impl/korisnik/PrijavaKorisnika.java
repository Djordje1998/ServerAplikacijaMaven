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
public class PrijavaKorisnika extends AbstractGenericOperation {

    private ArrayList<Korisnik> korisnici;

    @Override
    protected void preconditions(Object param) throws Exception {
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getKorisnickoIme().isEmpty()) {
            throw new Exception("Morate uneti vrednost u polje Korisnicko ime.");
        }
        if (korisnik.getSifra().isEmpty()) {
            throw new Exception("Morate uneti vrednost u polje Lozinka.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        korisnici = (ArrayList<Korisnik>) repository.getAll((Korisnik) param);
    }

    public Korisnik getKorisnik() {
        return korisnici.get(0);
    }

    @Override
    protected void postconditions(Object param) throws Exception {
        if (korisnici.isEmpty()) {
            throw new Exception("Ne postoji uneti korisnik, pokusajte ponovo!");
        }
    }

}
