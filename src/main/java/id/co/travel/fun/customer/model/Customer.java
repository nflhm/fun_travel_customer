package id.co.travel.fun.customer.model;

public class Customer {
    private int customerId;
    private String username;
    private String name;
    private String password;
    private String email;
    private int deleteFlag;
    public Customer() {
    }

    public Customer(int customerId, String username, String name, String password, String email, int deleteFlag) {
        this.customerId = customerId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.deleteFlag = deleteFlag;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
