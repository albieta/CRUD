package arg.crud.entity;

import java.util.LinkedList;
import java.util.List;

public class User {
    String userId;

    String userName;

    String userSurname;

    String userEmail;

    String userPassword;

    String processedOrders;

    public User(){}

    public User(String userId, String userName, String userSurname, String userEmail, String userPassword){
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.processedOrders = "[]";
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getProcessedOrders() {
        return this.processedOrders;
    }

    public void setProcessedOrders(String processedOrders) {
        this.processedOrders = processedOrders;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
