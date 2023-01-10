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

public class CustomerProfileController {
    @FXML
    private TextField ADDRESS;
    @FXML
    private TextField PhoneNum;
    @FXML
    private TextField birthDate;
    @FXML
    public TextField Gender;
    @FXML
    public PasswordField password;
    @FXML
    public TextField FName;
    @FXML
    public TextField LName;
    @FXML
    public TextField userName;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button UserNameButton;
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
    private User user = new User();
    protected URL location;
    protected ResourceBundle rb;
    boolean b=false;

    @FXML
    public void GetInfo(ActionEvent event) throws IOException {
        try{
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            Statement statement=connection.createStatement();
            String stmtqry="select * from CUSTOMER";
            ResultSet resultSet=statement.executeQuery(stmtqry);
            System.out.println("GGGGG");

            while(resultSet.next()){
                if(UserInfo.getInstance().getUsername().equals(resultSet.getString("CUSTOMERUSERNAME"))){
                    userName.setText(resultSet.getString("CUSTOMERUSERNAME"));
                    password.setText(resultSet.getString("CUSTOMERPASSWORD"));
                    FName.setText(resultSet.getString("CUSTOMERFNAME"));
                    LName.setText(resultSet.getString("CUSTOMERLNAME"));
                    Date date=resultSet.getDate("CUSTOMERBDATE");
                    birthDate.setText(date.toString());
                    ADDRESS.setText(resultSet.getString("CUSTOMERADDRESS"));
                    PhoneNum.setText(resultSet.getString("CUSTOMERPHONENUM"));
                    if(resultSet.getString("CUSTOMERGENDER").equals("M")){
                        Gender.setText("M");
                    }
                    else Gender.setText("F");
                }
            }
            connection.setAutoCommit(false);
            connection.commit();
            connection.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void UpdateInfo(ActionEvent event) throws IOException {
        try{
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            Statement statement=connection.createStatement();
            connection.setAutoCommit(false);
            System.out.println("hi1");
            String stmtqry="update CUSTOMER set CUSTOMERPASSWORD=?,CUSTOMERFNAME=?,CUSTOMERLNAME=?,CUSTOMERBDATE=?,CUSTOMERADDRESS=?,CUSTOMERPHONENUM=?,CUSTOMERGENDER=? where CUSTOMERUSERNAME=?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(stmtqry);
            System.out.println("hi2");
            preparedStatement1.setString(1,password.getText());System.out.println("hi3");
            preparedStatement1.setString(2,FName.getText());System.out.println("hi3");
            preparedStatement1.setString(3,LName.getText());System.out.println("hi3");
            preparedStatement1.setString(5, ADDRESS.getText());System.out.println("hi3");
            preparedStatement1.setInt(6,Integer.parseInt(PhoneNum.getText()));System.out.println("hi3");
            preparedStatement1.setString(7,Gender.getText());System.out.println("hi3");
            preparedStatement1.setString(8,userName.getText());System.out.println("hi3");
            preparedStatement1.setDate(4,Date.valueOf(birthDate.getText()));System.out.println("hi3");


            preparedStatement1.executeUpdate();
            System.out.println("hi4");
            connection.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

}
