package arg.crud.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectHelper {
    public static String[] getFields(Object entity) {
        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] fieldsStringNames = new String[fields.length];

        int i = 0;
        for(Field field : fields) {
            fieldsStringNames[i++] = field.getName();
        }

        return fieldsStringNames;
    }

    public static void setter(Object object, String property, Object value) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        List<Method> methods = Arrays.stream(object.getClass().getMethods()).toList();
        Method method = methods.stream()
                .filter(x-> x.getName().matches("(?i).*"+"set"+property+".*"))
                .findFirst()
                .orElse(null);
        assert method != null;
        method.invoke(object,value);
    }

    public static Object getter(Object object, String property) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        List<Method> methods = Arrays.stream(object.getClass().getMethods()).toList();
        Method method = methods.stream()
                .filter(x-> x.getName().matches("(?i).*"+"get"+property+".*"))
                .findFirst()
                .orElse(null);
        assert method != null;
        return method.invoke(object,null);
    }

    public static String getIdAttributeName(Class theClass) {
        Field field = Arrays.stream(theClass.getDeclaredFields())
                .filter(x->x.getName().matches("(?i).*id.*"))
                .findFirst()
                .orElse(null);
        assert field != null;
        return field.getName();
    }

    public static List<Object> createObjects(ResultSet resultSet, Class theClass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        List<Object> objects = new ArrayList<>();

        String[] fields = getFields(theClass.newInstance());

        while(resultSet.next()) {
            Object object = theClass.newInstance();
            for(String field : fields) {
                setter(object, field, resultSet.getObject(field));
            }
            objects.add(object);
        }
        return objects;
    }

    public static boolean assertEqual(Object object1, Object object2) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        String[] getFields1 = getFields(object1);
        String[] getFields2 = getFields(object2);

        if(!Arrays.equals(getFields1, getFields2)) return false;

        for(String field : getFields1) {
            if(!getter(object1, field).equals(getter(object2, field))) return false;
        }
        return true;
    }
}
