/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.model;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DarkForce
 */
public class TabelModelKorisnici extends AbstractTableModel {

    private ArrayList<Korisnik> korisnici;
    private final String[] kolone = {"Korisnicko ime", "Ime", "Prezime"};

    public TabelModelKorisnici() {
        korisnici = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik korisnik = korisnici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return korisnik.getKorisnickoIme();
            case 1:
                return korisnik.getIme();
            case 2:
                return korisnik.getPrezime();
            default:
                return "Error";
        }
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        this.korisnici = korisnici;
        fireTableDataChanged();
    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public Korisnik getKorisnikZaBrisanje(int row) {
        Korisnik korisnik = korisnici.get(row);
        korisnici.remove(row);
        fireTableDataChanged();
        return korisnik;
    }

    public void dodajKorisnika(Korisnik korisnik) {
        korisnici.add(korisnik);
        //fireTableDataChanged();
    }

}
