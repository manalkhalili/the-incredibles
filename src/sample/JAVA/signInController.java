package sample.JAVA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signInController {

    @FXML
    private TextField ManagerUserName;
    @FXML
    private TextField ManagerPass;
    @FXML
    private Button managerSignInButton;
    @FXML
    private Button managerSignUpButton;
    @FXML
    private TextField CustomerUserName;
    @FXML
    private TextField CustomerPass;
    @FXML
    private Button customerSignUpButton;
    @FXML
    private Button CustomerSignInButton;

    @FXML
    public void ManagerSignInButton(ActionEvent e) throws IOException{
        if (ManagerUserName.getText().equals("") || ManagerPass.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure to enter both id and password ");
            alert.showAndWait();
            return;
        }
        else{
            try {
                String qry="select MANAGERUSERNAME,MANAGERPASSWORD from MANAGERSS";
                OracleDataSource oracleDataSource = new OracleDataSource();
                String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
                oracleDataSource.setURL(url);
                oracleDataSource.setUser("SYSTEM");
                oracleDataSource.setPassword("f123456789");
                Connection connection = oracleDataSource.getConnection();
                PreparedStatement preparedStatement1 = connection.prepareStatement(qry);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                System.out.println("execute");
                boolean user = false,pass=false;
                while(resultSet1.next()){
                    String username = resultSet1.getString("MANAGERUSERNAME");
                    String checkPassword = resultSet1.getString("MANAGERPASSWORD");
                    System.out.println("while");
                    if(username.equals(ManagerUserName.getText())&&checkPassword.equals(ManagerPass.getText())){
                        UserInfo tmp = UserInfo.getInstance( );
                        tmp.setUsername(ManagerUserName.getText());
                        tmp.setManager(true);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/HOME.fxml"));
                        stage.setScene(root.getScene());

                        user=true;
                        pass=true;
                    }
                    if(username.equals(ManagerUserName.getText())){
                        user=true;
                    }
                    else if(checkPassword.equals(ManagerPass.getText())){
                        pass=true;
                    }
                }
                if((!user&&!pass) ||(!user&&pass) || (user&&!pass)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("user or password is wrong");
                    alert.showAndWait();
                    return;
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null,e1);
                e1.printStackTrace();
            }
        }
    }

    @FXML
    public void CustomerSignInButton(ActionEvent e) throws IOException{
        if (CustomerUserName.getText().equals("") || CustomerPass.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure to enter both id and password ");
            alert.showAndWait();
            return;
        }
        else{
            try {
                String qry="select CUSTOMERUSERNAME,CUSTOMERPASSWORD from CUSTOMER";
                OracleDataSource oracleDataSource = new OracleDataSource();
                String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
                oracleDataSource.setURL(url);
                oracleDataSource.setUser("SYSTEM");
                oracleDataSource.setPassword("f123456789");
                Connection connection = oracleDataSource.getConnection();
                PreparedStatement preparedStatement1 = connection.prepareStatement(qry);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                System.out.println("execute");
                boolean user = false,pass=false;
                while(resultSet1.next()){
                    String username = resultSet1.getString("CUSTOMERUSERNAME");
                    String checkPassword = resultSet1.getString("CUSTOMERPASSWORD");
                    System.out.println("while");
                    if(username.equals(CustomerUserName.getText())&&checkPassword.equals(CustomerPass.getText())){

                        UserInfo tmp = UserInfo.getInstance( );
                        tmp.setUsername(CustomerUserName.getText());
                        tmp.setManager(false);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/HOME.fxml"));
                        stage.setScene(root.getScene());
//
                        //F@r@h123456
                        user=true;
                        pass=true;
                    }
                    if(username.equals(CustomerUserName.getText())){
                        user=true;
                    }
                    else if(checkPassword.equals(CustomerPass.getText())){
                        pass=true;
                    }
                }
                if((!user&&!pass) ||(!user&&pass) || (user&&!pass)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("user or password is wrong");
                    alert.showAndWait();
                    return;
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null,e1);
            }
        }
    }

    @FXML
    public void CustomerSignUpButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/Customers_SIGNUP.fxml"));
        stage.setScene(root.getScene());
    }

    public void ManagerSignUpButton(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/ManagerSignUp.fxml"));
        stage.setScene(root.getScene());
    }
}
