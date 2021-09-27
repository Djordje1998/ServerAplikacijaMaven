/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.operacije;

import rs.ac.bg.fon.ai.ServerAplikacija.db.DbRepository;
import rs.ac.bg.fon.ai.ServerAplikacija.db.Repository;
import rs.ac.bg.fon.ai.ServerAplikacija.db.impl.RepositoryDbGeneric;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Abstraktnu klasu AbstractGenericOperation koju implemetiraju sve sistemske operacije.
 * Prvo ispituje preduslove, otvara transakciju, izvrsava sistemsku operaciju.
 * Na kraju provera postuslove sistemske operacije i potvrdjuje transakciju ako se proces zavrsi bez greske.
 * U slucaju greske radi se rollback transakcije.
 *
 * @author Djordje Novakovic
 * @version 1.0
 */
public abstract class AbstractGenericOperation {

	/**
	 * Predstavlja interfejs brokeru baze.
	 */
    protected final Repository repository;

    /**
     * Konstruktor bez parametra koji inicijalizuje interfejs broker baze konkretnom implementacijom.
     */
    public AbstractGenericOperation() {
        this.repository = new RepositoryDbGeneric();
    }

    /**
     * Generican proces izvrsavanja sistemske operacije.
     * 
     * @param param Neka domenska klasa koja je potrebna za isvrsavanje operacije.
     * @throws Exception Ako objekat ne moze biti izmenjen u bazi zbog postojecih relacija.
     */
    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            postconditions(param);
            commitTransaction();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            rollbackTransaction();
            throw new Exception("Objekat ne moze biti obrisan zbog postojecih relacija!");
        } 
        catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } finally {
            disconnect();
        }

    }

    /**
     * Preduslovi konkretne operacije.
     * 
     * @param param Neka domenska klasa nad kojom se vrsi provera preduslova.
     * @throws Exception Ako dodje do neocekivane greske pri komunikaciji sa bazom.
     */
    protected abstract void preconditions(Object param) throws Exception;

    /**
     * Izvrsavanje konkretne operacije.
     * 
     * @param param Neka domenska klasa koja je potrebna za isvrsavanje operacije.
     * @throws Exception Ako dodje do neocekivane greske pri komunikaciji sa bazom.
     */
    protected abstract void executeOperation(Object param) throws Exception;

    /**
     * Postuslovi konkretne operacije.
     * 
     * @param param Neka domenska klasa nad kojom se vrsi provera postuslova.
     * @throws Exception Ako dodje do neocekivane greske pri komunikaciji sa bazom.
     */
    protected abstract void postconditions(Object param) throws Exception;

    /**
     * Uspostavljanje konekcije za bazom.
     * 
     * @throws Exception Ako dodje do neocekivane greske pri komunikaciji sa bazom.
     */
    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    /**
     * Potvrdjivanje transakcije.
     * 
     * @throws Exception Ako dodje do neocekivane greske pri komunikaciji sa bazom.
     */
    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    /**
     * Ponistavanje transakcije.
     * 
     * @throws Exception Ako dodje do neocekivane greske pri komunikaciji sa bazom.
     */
    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    /**
     * Gasenje konekcije sa bazom.
     * 
     * @throws Exception Ako dodje do neocekivane greske pri komunikaciji sa bazom.
     */
    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}
