package arg.crud;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session {
    void save(Object entity);
    void close() throws SQLException;
    Object get(Class theClass, String attribute, String value);
    void update(Object object);
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String, String> params);
    void deleteRecords(Class theClass);
    List<Object> query(String query, Class theClass, HashMap params);
}
