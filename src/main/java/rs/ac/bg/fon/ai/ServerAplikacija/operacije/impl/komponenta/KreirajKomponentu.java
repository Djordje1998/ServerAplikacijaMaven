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
public class KreirajKomponentu extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        Komponenta komponenta = (Komponenta) param;

        if (komponenta.getNazivKomponente().isEmpty()) {
            throw new Exception("Naziv komponente je prazan!");
        }
        if (komponenta.getTipKomponente() == null) {
            throw new Exception("Nije izabran tip komponente!");
        }
        if (komponenta.getOpis().isEmpty()) {
            throw new Exception("Opis komponente je prazan!");
        }
        if (komponenta.getTakt() <= 0) {
            throw new Exception("Takt mora biti veci od 0!");
        }
        if (komponenta.getCena() <= 0) {
            throw new Exception("Cena mora biti veci od 0!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Komponenta) param);
    }

    @Override
    protected void postconditions(Object param) throws Exception {
    }

}
