package arg.crud;

import arg.crud.util.ObjectHelper;
import arg.crud.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Object entity) throws SQLException {
        try{
            String insertQuery = QueryHelper.createQueryINSERT(entity);

            PreparedStatement statement = conn.prepareStatement(insertQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(entity)) {
                statement.setObject(i++, ObjectHelper.getter(entity, field));
            }
            statement.executeQuery();
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

    @Override
    public Object get(Class theClass, String attribute, String value) throws SQLException {
        Object entity;

        try {
            entity = theClass.newInstance();
            ObjectHelper.setter(entity, ObjectHelper.getAttributeName(theClass, attribute), value);
            String selectQuery = QueryHelper.createQuerySELECT(entity, attribute);

            PreparedStatement statement = conn.prepareStatement(selectQuery);
            statement.setObject(1, value);
            entity = ObjectHelper.createObjects(statement.executeQuery(), theClass).get(0);
            assert entity != null;

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

    @Override
    public void update(Object object) throws SQLException {
        try{
            String updateQuery = QueryHelper.createQueryUPDATE(object);
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(object)) {
                statement.setObject(i++, ObjectHelper.getter(object, field));
            }
            statement.setObject(i, ObjectHelper.getter(object, ObjectHelper.getAttributeName(object.getClass(), "id")));

            statement.executeQuery();
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Object object) throws SQLException {
        try{
            String updateQuery = QueryHelper.createQueryDELETE(object);
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setObject(1, ObjectHelper.getter(object, ObjectHelper.getAttributeName(object.getClass(), "id")));

            statement.executeQuery();
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> findAll(Class theClass) throws SQLException {
        String selectQuery = QueryHelper.createQuerySelectAll(theClass);

        PreparedStatement statement;
        List<Object> objects = null;

        try{
            statement = conn.prepareStatement(selectQuery);
            objects = ObjectHelper.createObjects(statement.executeQuery(), theClass);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchFieldException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public List<Object> findAll(Class theClass, HashMap<String, String> params) throws SQLException{
        String selectQuery;
        try{
            selectQuery = QueryHelper.createQuerySELECTMultipleParams(theClass.newInstance(), params);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement statement;
        List<Object> objects = null;
        int i = 1;

        try {
            statement = conn.prepareStatement(selectQuery);
            for (String value : params.values()) {
                statement.setObject(i++, value);
            }
            objects = ObjectHelper.createObjects(statement.executeQuery(), theClass);
        } catch (NoSuchFieldException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }

    @Override
    public void deleteRecords(Class theClass) throws SQLException {
        String selectQuery = QueryHelper.createQueryDeleteRecords(theClass);

        PreparedStatement statement = conn.prepareStatement(selectQuery);
        statement.executeQuery();

    }
}
