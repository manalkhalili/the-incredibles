package sample.JAVA;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class managerProfileController {
    @FXML
    private TextField Managertextcity;
    @FXML
    private TextField ManagerPhoneNumText;
    @FXML
    private Button username;
    @FXML
    private TextField ManagerFName;
    @FXML
    private TextField ManagerLName;
    @FXML
    private TextField ManagerUserNameText;
    @FXML
    private TextField ManagerBD;
    @FXML
    private TextField ManagerGender;
    @FXML
    private PasswordField ManagerPasswordText;
    @FXML
    private TextField ManagerRestNameText;
    @FXML
    private TextField ManagerRestIDText;
    @FXML
    private TextField RestPhoneText;
    @FXML
    private TextField RestAddressText;
    @FXML
    private Button HomeButton;
    @FXML
    private Button Savebutton;


    public void GetInfo(){
        try{
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            Statement statement=connection.createStatement();
            String stmtqry="select * from MANAGERSS";
            ResultSet resultSet=statement.executeQuery(stmtqry);
            System.out.println("GGGGG");

            while(resultSet.next()){

                if(UserInfo.getInstance().getUsername().equals(resultSet.getString("MANAGERUSERNAME"))){
                    ManagerUserNameText.setText(resultSet.getString("MANAGERUSERNAME"));
                    ManagerPasswordText.setText(resultSet.getString("MANAGERPASSWORD"));
                    System.out.println("1");
                    ManagerFName.setText(resultSet.getString("MANAGERFNAME"));
                    ManagerLName.setText(resultSet.getString("MANAGERLNAME"));
                    Date date=resultSet.getDate("MANAGERBDATE");
                    ManagerBD.setText(date.toString());
                    Managertextcity.setText(resultSet.getString("MANAGERADDRESS"));
                    ManagerPhoneNumText.setText(resultSet.getString("MANAGERPHONENUM"));
                    ManagerRestIDText.setText(resultSet.getString("RESTID"));
                    if(resultSet.getString("MANAGERGENDER").equals("M")){
                        ManagerGender.setText("M");
                    }
                    else ManagerGender.setText("F");
                    ManagerRestNameText.setText(resultSet.getString("RESTNAME"));
                    RestAddressText.setText(resultSet.getString("RESTADDRESS"));
                    RestPhoneText.setText(resultSet.getString("RESTPHONE"));
                }
            }
            connection.setAutoCommit(false);
            connection.commit();
            connection.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
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
    public void CustomersTable(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/CustomerTable.fxml"));
        stage.setScene(root.getScene());

    }
    @FXML
    public void ManagersTable(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/ManagerTable.fxml"));
        stage.setScene(root.getScene());

    }
    public void UpdateInfo(){
        try{
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            Statement statement=connection.createStatement();
            connection.setAutoCommit(false);
            String stmtqry="update MANAGERSS set MANAGERPASSWORD=?,MANAGERFNAME=?,MANAGERLNAME=?,MANAGERBDATE=?,MANAGERADDRESS=?,MANAGERPHONENUM=?,RESTID=?,MANAGERGENDER=?, RESTNAME=?,RESTADDRESS=?,RESTPHONE=? where MANAGERUSERNAME=?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(stmtqry);

            preparedStatement1.setString(1,ManagerPasswordText.getText());
            preparedStatement1.setString(2,ManagerFName.getText());
            preparedStatement1.setString(3,ManagerLName.getText());
            preparedStatement1.setDate(4,Date.valueOf(ManagerBD.getText()));
            preparedStatement1.setString(5, Managertextcity.getText());
            preparedStatement1.setInt(6,Integer.parseInt(ManagerPhoneNumText.getText()));
            preparedStatement1.setInt(7,Integer.parseInt(ManagerRestIDText.getText()));
            preparedStatement1.setString(8,ManagerGender.getText());
            preparedStatement1.setString(9,ManagerRestNameText.getText());
            preparedStatement1.setString(10,RestAddressText.getText());
            preparedStatement1.setInt(11,Integer.parseInt(RestPhoneText.getText()));
            preparedStatement1.setString(12,ManagerUserNameText.getText());
            preparedStatement1.executeUpdate();

            connection.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }


}