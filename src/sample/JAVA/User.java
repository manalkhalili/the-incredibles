package sample.JAVA;

public class User {
    String username = "";
    boolean isManager = false;
    public User(String username,boolean isManager) {
        this.username = username;
        this.isManager = isManager;
    }
    public User(){
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
