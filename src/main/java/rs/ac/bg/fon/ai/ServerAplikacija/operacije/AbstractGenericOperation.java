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
 *
 * @author DarkForce
 */
public abstract class AbstractGenericOperation {

    protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDbGeneric();
    }

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

    protected abstract void preconditions(Object param) throws Exception;

    protected abstract void executeOperation(Object param) throws Exception;

    protected abstract void postconditions(Object param) throws Exception;

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}
