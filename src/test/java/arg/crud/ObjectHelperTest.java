package arg.crud;

import arg.crud.entity.User;
import arg.crud.util.ObjectHelper;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ObjectHelperTest {
    @Test
    public void testObjectGetFields() {
        User user = new User();
        String[] strings = ObjectHelper.getFields(user);
        Assert.assertEquals("userId", strings[0]);
        Assert.assertEquals("userName", strings[1]);
        Assert.assertEquals(6, strings.length);
    }

    @Test
    public void testObjectSetter() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        User user = new User();
        ObjectHelper.setter(user, "userName", "Alba");
        Assert.assertEquals("Alba", user.getUserName());
    }

    @Test
    public void testObjectGetter() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        User user = new User("1A", "Alba", "Roma", "albaromagomez@gmail.com", "Test123");
        Object userName = ObjectHelper.getter(user, "userName");
        Assert.assertEquals("Alba", userName);
    }

    @Test
    public void testGetAttributeName() {
        String attributeName = ObjectHelper.getAttributeName(User.class, "id");
        Assert.assertEquals("userId", attributeName);
    }

    @Test
    public void testAssertEqual() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        User user1 = new User("1A", "Alba", "Roma", "albaromagomez@gmail.com", "Test123");
        User user2 = new User("1A", "Alba", "Roma", "albaromagomez@gmail.com", "Test123");

        Assert.assertTrue(ObjectHelper.assertEqual(user1, user2));
    }
}
