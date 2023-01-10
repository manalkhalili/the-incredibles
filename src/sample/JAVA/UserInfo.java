package sample.JAVA;

public class UserInfo {

        private static UserInfo user = new UserInfo( );
        private int billId = 0 ;
        private  String username = "";
    private boolean isManager = false;


    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

        private UserInfo() { }
        public static UserInfo getInstance( ) {
            return user;
        }


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void SignOut(){
        int billId = 0 ;
        String username = "";
        boolean isManager = false;
    }
}


