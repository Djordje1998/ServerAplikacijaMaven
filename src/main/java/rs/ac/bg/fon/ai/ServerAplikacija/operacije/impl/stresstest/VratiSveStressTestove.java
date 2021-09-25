/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije.impl.stresstest;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StressTest;
import java.util.ArrayList;
import rs.ac.bg.fon.ai.ServerAplikacija.operacije.AbstractGenericOperation;

/**
 *
 * @author DarkForce
 */
public class VratiSveStressTestove extends AbstractGenericOperation{

    private ArrayList<StressTest> testovi;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         testovi = (ArrayList<StressTest>) repository.getAll((StressTest)param);
    }

    @Override
    protected void postconditions(Object param) throws Exception {
        if (testovi.isEmpty()) {
            throw new Exception("Nema unetih stress testova!");
        }
    }

    public ArrayList<StressTest> getTestovi() {
        return testovi;
    }
    
}
