package arg.crud;

import arg.crud.entity.User;
import arg.crud.util.QueryHelper;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class QueryHelperTest {
    @Test
    public void testCreateQueryINSERT() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        User user = new User("1A", "Alba", "Roma", "albaromagomez@gmail.com","Test123");
        String query = QueryHelper.createQueryINSERT(user);
        Assert.assertEquals("INSERT INTO User (userId, userName, userSurname, userEmail, userPassword, processedOrders) VALUES (?, ?, ?, ?, ?, ?)", query);
    }

    @Test
    public void testCreateQuerySELECT() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        user.setUserId("1A");
        String query = QueryHelper.createQuerySELECT(user, "id");
        Assert.assertEquals("SELECT * FROM User WHERE userId = ?", query);
    }

    @Test
    public void testCreateQuerySELECTMultipleParams() {
        User user = new User();
        user.setUserEmail("albaromagomez@gmail.com");
        user.setUserPassword("Test123");
        HashMap<String, String> fields = new HashMap<>();
        fields.put("password", "Test123");
        fields.put("email", "albaromagomez@gmail.com");


        String query = QueryHelper.createQuerySELECTMultipleParams(user, fields);
        Assert.assertEquals("SELECT * FROM User WHERE userPassword = ? AND userEmail = ?", query);
    }

    @Test
    public void testCreateQueryUPDATE() {
        User user = new User("1A", "Alba", "Roma", "albaromagomez@gmail.com", "Test123");
        String query = QueryHelper.createQueryUPDATE(user);
        Assert.assertEquals("UPDATE User SET userId = ?, userName = ?, userSurname = ?, userEmail = ?, userPassword = ?, processedOrders = ? WHERE userId = ?", query);
    }

    @Test
    public void testCreateQueryDELETE() {
        User user = new User("1A", "Alba", "Roma", "albaromagomez@gmail.com", "Test123");
        String query = QueryHelper.createQueryDELETE(user);
        Assert.assertEquals("DELETE FROM User WHERE userId = ?", query);
    }

    @Test
    public void testCreateQueryDeleteRecords() {
        String query = QueryHelper.createQueryDeleteRecords(User.class);
        Assert.assertEquals("DELETE FROM User", query);
    }

    @Test
    public void testCreateQuerySelectAll() {
        String query = QueryHelper.createQuerySelectAll(User.class);
        Assert.assertEquals("SELECT * FROM User", query);
    }
}
