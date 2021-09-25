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
public class KreirajKorisnika extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getIme().isEmpty()) {
            throw new Exception("Ime je obavezno polje!");
        }
        if (korisnik.getPrezime().isEmpty()) {
            throw new Exception("Prezime je obavezno polje!");
        }
        if (korisnik.getKorisnickoIme().isEmpty()) {
            throw new Exception("Korisnicko ime je obavezno polje!");
        }
        if ( korisnik.getSifra().isEmpty()) {
            throw new Exception("Lozinka je obavezno polje!");
        }
        if (korisnik.getSifraPotvrda().isEmpty()) {
            throw new Exception("Potvrdi lozinku je obavezno polje!");
        }

        if (!korisnik.getSifra().equals(korisnik.getSifraPotvrda())) {
            throw new Exception("Lozinka i potvrda lozinke nisu jednake");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Korisnik)param);
    }

    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
