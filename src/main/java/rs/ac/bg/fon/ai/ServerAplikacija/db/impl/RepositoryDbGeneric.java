/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.db.impl;

import rs.ac.bg.fon.ai.ServerAplikacija.db.DbConnectionFactory;
import rs.ac.bg.fon.ai.ServerAplikacija.db.DbRepository;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.GenericEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DarkForce
 */
public class RepositoryDbGeneric implements DbRepository<GenericEntity> {

    @Override
    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        ArrayList<GenericEntity> gens = new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(entity.getTableName())
                .append(entity.getJoinTables())
                .append(entity.getWhereCriteria())
                .append(entity.getLikeCriteria());
        String query = sb.toString();

        System.out.println(query);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            GenericEntity gen = entity.getResponse(rs);
            gens.add(gen);
        }
        rs.close();
        statement.close();
        return gens;
    }

    @Override
    public void add(GenericEntity entity) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(entity.getTableName())
                .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                .append(" VALUES (").append(entity.getInsertValues()).append(")");
        String query = sb.toString();

        System.out.println(query);

        Statement statement = connection.createStatement();
        statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            entity.setId(id);
        }
        rs.close();
        statement.close();
    }

    @Override
    public void edit(GenericEntity entity) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(entity.getTableName())
                .append(" SET ").append(entity.getEditValues())
                .append(" WHERE ").append(entity.getEditCriteria());
        String query = sb.toString();

        System.out.println(query);

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    @Override
    public void delete(GenericEntity entity) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(entity.getTableName())
                .append(" WHERE ").append(entity.getDeleteCriteria());
        String query = sb.toString();

        System.out.println(query);

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

}
