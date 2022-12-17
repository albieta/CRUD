package arg.crud;

import arg.crud.entity.User;
import arg.crud.entity.Product;
import arg.crud.util.ObjectHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

public class SessionImplTest {
    Session session;

    @Before
    public void setUp() {
        this.session = FactorySession.openSession("jdbc:mariadb://localhost:3306/crud", "crud", "crud");
        this.session.deleteRecords(User.class);
        this.session.deleteRecords(Product.class);
    }

    @Test
    public void testSaveObject() {
        Product product = new Product("1A", "coses", 2.0, 3);
        this.session.save(product);
        List<Object> products = this.session.findAll(Product.class);
        Product prod = (Product) products.get(0);
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(Product.class, products.get(0).getClass());
        Assert.assertEquals(2.0,prod.getPrice(), Math.abs(2.0-prod.getPrice()));
    }

    @Test
    public void testGetObject() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Product p = new Product("1A", "coses", 2.0, 3);
        this.session.save(p);
        Product product = (Product) this.session.get(Product.class,"id", "1A");
        List<Object> products = this.session.findAll(Product.class);
        Assert.assertTrue(ObjectHelper.assertEqual(product, products.get(0)));
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        user.setUserEmail("alba@gmail.com");
        this.session.save(user);
        User userFound = (User) this.session.get(User.class, "email", "alba@gmail.com");
        Assert.assertEquals(userFound.getUserEmail(), "alba@gmail.com");
    }

    @Test
    public void testUpdateObject() {
        Product p = new Product("1A", "coses", 2.0, 3);
        this.session.save(p);
        Product product1 = (Product) this.session.get(Product.class, "id", "1A");
        p.setPrice(3.0);
        this.session.update(p);
        Product product2 = (Product) this.session.get(Product.class, "id", "1A");
        Assert.assertEquals(3.0, product2.getPrice(), Math.abs(product2.getPrice() - 3.0));
        Assert.assertEquals(2.0, product1.getPrice(), Math.abs(product2.getPrice() - 2.0));
    }

    @Test
    public void testFindAllByHashmap() {
        User userSaved = new User("1A", "Alba", "Roma", "albaromagomez@gmail.com", "Test123");
        this.session.save(userSaved);
        HashMap<String, String> fields = new HashMap<>();
        fields.put("email", "albaromagomez@gmail.com");
        fields.put("password", "Test123");
        List<Object> users = this.session.findAll(User.class, fields);
        User user = (User) users.get(0);

        Assert.assertEquals(1, users.size());
        Assert.assertEquals(user.getUserEmail(), "albaromagomez@gmail.com");
        Assert.assertEquals(user.getUserPassword(), "Test123");
    }

    @Test
    public void testDeleteObject() {
        Product p = new Product("1A", "coses", 2.0, 3);
        this.session.save(p);
        this.session.delete(p);
        List<Object> products = this.session.findAll(Product.class);
        Assert.assertEquals(0, products.size());
    }
}
