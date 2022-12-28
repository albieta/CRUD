package arg.crud;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session {
    void save(Object entity) throws SQLException;
    void close() throws SQLException;
    Object get(Class theClass, String attribute, String value) throws SQLException;
    void update(Object object) throws SQLException;
    void delete(Object object) throws SQLException;
    List<Object> findAll(Class theClass) throws SQLException;
    List<Object> findAll(Class theClass, HashMap<String, String> params)throws SQLException;
    void deleteRecords(Class theClass) throws SQLException;
    List<Object> query(String query, Class theClass, HashMap params);
}
