package sample.JAVA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class rest1Controller implements Initializable {
    @FXML
    public void Home(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/HOME.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    public void OrderNow(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/rest1.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    public void TheInc(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/AboutUs.fxml"));
        stage.setScene(root.getScene());

    }

    @FXML
    public void GoToBill(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/bill.fxml"));
        stage.setScene(root.getScene());
    }

    @FXML
    public void CROSSIANT(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"CROSSIANT");
    }
    @FXML
    public void GARLIC_BREAD(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"GARLIC BREAD");
    }
    @FXML
    public void CREPE(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"CREPE");
    }
    @FXML
    public void TEA(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"TEA");
    }
    @FXML
    public void COFFEE(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"COFFEE");
    }
    @FXML
    public void FreshStrawberry(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"FRESH STRAWBERRY");
    }
    @FXML
    public void APPLEJUICE(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"APPLE JUICE");
    }
    @FXML
    public void COCKTAIL(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"COCKTAIL");
    }
    @FXML
    public void PizzaMarg(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"PIZZA MARGHERITA");
    }
    @FXML
    public void Salami(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"SALAMI");
    }
    @FXML
    public void PastaPologneses(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"PASTA POLOGNESES");
    }
    @FXML
    public void LASAGNA(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"LASAGNA");
    }
    @FXML
    public void ChickenBurger(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"CHICKEN BURGER");
    }
    @FXML
    public void CheeseBurger(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"CHEESE BURGER");
    }
    @FXML
    public void CheeseHotDog(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"CHEESE HOT DOG");
    }
    @FXML
    public void ChiliDog(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"CHILI DOG");
    }
    @FXML
    public void Doughnut(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"DOUGHNUT");
    }
    @FXML
    public void CheeseCake(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"CHEESECAKE");
    }
    @FXML
    public void EGG_AND_CHEESE(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"EGG AND CHEESE");
    }
    @FXML
    public void GRILLED_CHEESE(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"GRILLED CHEESE");
    }
    @FXML
    public void SteakWithWhiteSauce(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"STEAK WITH WHITE SAUCE ");
    }
    @FXML
    public void BANHMI(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"BANHMI");
    }
    @FXML
    public void CHIPS(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"CHIPS");
    }
    @FXML
    public void TOAST(ActionEvent e) throws IOException {
        addItemToBill(UserInfo.getInstance().getBillId(),"TOAST");
    }
    public Button usernameButton;
    private User user = new User();
    protected URL location;
    protected ResourceBundle rb;
    boolean b=false;



    public boolean addItemToBill(int BillId, String ItemName) {
        int r1 = 0;
        System.out.println("HelloFarah");
        try {
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            connection.setAutoCommit(false);
            System.out.println("1");

            String query = "insert into BILL_ITEMS (billid,itemname,price,qty) values (?,?,(select price from ITEM where itemname = ?),?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);

            preparedStatement1.setInt(1, BillId);
            preparedStatement1.setString(2, ItemName);
            preparedStatement1.setString(3, ItemName);
            preparedStatement1.setInt(4, 1);
            System.out.println(ItemName);
            connection.commit();

            r1 = preparedStatement1.executeUpdate();
            System.out.println("3");
            connection.close();

        } catch (SQLException throwables) {
            if (throwables.getMessage().contains("BILLITEMS_PK")) {
            }
            else
                throwables.printStackTrace();
        }
        if (r1 > 0)
            return true;
        else
            return false;
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("1");

        generateBillId();
        usernameButton.setText(UserInfo.getInstance().getUsername());
        System.out.println(UserInfo.getInstance().getBillId());
        System.out.println("2");
    }
    void initData(User user)  {
        this.user.setUsername( user.getUsername());
        this.user.setManager(user.isManager());
    }
    private static int generateBillId (){
        Long billId =0L ;
        System.out.println("3");
        try {
            int r2 = 0;
                OracleDataSource oracleDataSource = new OracleDataSource();
                String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
                oracleDataSource.setURL(url);
                oracleDataSource.setUser("SYSTEM");
                oracleDataSource.setPassword("f123456789");
                Connection connection = oracleDataSource.getConnection();
                connection.setAutoCommit(false);
            String s="insert into BILL(BILLDATE,CUSTOMERUSERNAME) values(sysdate, ?)";
            String generatedColumns[] = { "BILLID" };
            PreparedStatement preparedStatement2 = connection.prepareStatement(s,generatedColumns);
            preparedStatement2.setString(1,UserInfo.getInstance().getUsername());
            r2 = preparedStatement2.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement2.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    billId = generatedKeys.getLong(1);
                    UserInfo.getInstance().setBillId(billId.intValue());
                } else {
                    throw new SQLException("Creating bill failed, no ID obtained.");
                }
            }
            connection.commit();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("4");

        return billId.intValue();

    }
    @FXML
    public void Username(ActionEvent event) throws IOException {
        System.out.println("hi");
        if(user.isManager){
            System.out.println("111");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/manager_Profile.fxml"));
            stage.setScene(root.getScene());
        }else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/CUSTOMER_PROFILE.fxml"));
            stage.setScene(root.getScene());
        }

    }

}

