package arg.crud.util;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static arg.crud.util.ObjectHelper.getAttributeName;

public class QueryHelper {
    public static String createQueryINSERT(Object entity) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        StringBuffer buffer = new StringBuffer("INSERT INTO ");
        buffer.append(entity.getClass().getSimpleName()).append(" (");

        String[] fields = ObjectHelper.getFields(entity);

        for(String field : fields) {
            buffer.append(field).append(", ");
        }
        buffer.setLength(buffer.length()-2);

        buffer.append(") VALUES (");
        for(String ignored : fields) {
            buffer.append("?, ");
        }
        buffer.setLength(buffer.length()-2);
        buffer.append(")");

        return buffer.toString();
    }

    public static String createQuerySELECT(Object entity, String attribute) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        String field = getAttributeName(entity.getClass(), attribute);

        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        buffer.append(" WHERE ").append(field);
        buffer.append(" = ?");

        return buffer.toString();
    }

    public static String createQuerySELECTMultipleParams(Object entity, HashMap<String, String> attributes) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        buffer.append(" WHERE ");

        for (String attribute : attributes.keySet()) {
            buffer.append(getAttributeName(entity.getClass(), attribute));
            buffer.append(" = ? AND ");
        }
        buffer.setLength(buffer.length()-5);

        return buffer.toString();
    }

    public static String createQueryUPDATE(Object entity) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE ").append(entity.getClass().getSimpleName());
        buffer.append(" SET ");

        String[] fields = ObjectHelper.getFields(entity);
        for (String field : fields) {
            buffer.append(field).append(" = ?, ");
        }
        buffer.setLength(buffer.length()-2);
        buffer.append(" WHERE ").append(getAttributeName(entity.getClass(), "id")).append(" = ?");

        return buffer.toString();
    }

    public static String createQueryDELETE(Object entity) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("DELETE FROM ").append(entity.getClass().getSimpleName());
        buffer.append(" WHERE ").append(getAttributeName(entity.getClass(), "id")).append(" = ?");

        return buffer.toString();
    }

    public static String createQueryDeleteRecords(Class theClass) {
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM ").append(theClass.getSimpleName());
        return query.toString();
    }

    public static String createQuerySelectAll(Class theClass) {
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM ").append(theClass.getSimpleName());
        return query.toString();
    }
}
