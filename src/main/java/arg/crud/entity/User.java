package arg.crud.entity;

import java.util.LinkedList;
import java.util.List;

public class User {
    String userId;

    String userName;

    String userSurname;

    List<String> processedOrders;

    public User(){}

    public User(String userId, String userName, String userSurname){
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.processedOrders = new LinkedList<>();
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

    public void addProcessedOrder(String order) {
        this.processedOrders.add(order);
    }

    public List<String> getProcessedOrders() {
        return this.processedOrders;
    }

    public void setProcessedOrders(List<String> processedOrders) {
        this.processedOrders = processedOrders;
    }
}
