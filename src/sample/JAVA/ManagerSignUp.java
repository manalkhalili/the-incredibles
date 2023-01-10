package sample.JAVA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.pool.OracleDataSource;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ManagerSignUp{
    @FXML
    private TextField MaanagerFN;
    @FXML
    private TextField ManagerLn;
    @FXML
    private TextField ManagerUserName;
    @FXML
    private TextField ManagerPhone;
    @FXML
    private TextField ManagerAddress;
    @FXML
    private DatePicker ManagerDb;
    @FXML
    public RadioButton M;
    @FXML
    public RadioButton F;
    @FXML
    ToggleGroup group;
    @FXML
    private PasswordField ManagerPass;
    @FXML
    private TextField RetaurantId;
    @FXML
    private TextField RestaurantName;
    @FXML
    private TextField RestaurantAdrdess;
    @FXML
    private TextField RestaurantPhone;
    @FXML
    private Button SignupButton;

    @FXML
    public void MaleOrFemale() {
        group = new ToggleGroup();
        M.setToggleGroup(group);
        F.setToggleGroup(group);
    }
    private boolean ValidPass(){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,15})";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(ManagerPass.getText());

        if(matcher.matches()){
            return true;
        }
        else return false;
    }
    @FXML
    public void SignUpButton(ActionEvent e){
        Pattern TextPattern=Pattern.compile("[a-zA-Z]+");
        Matcher FNameMatcher=TextPattern.matcher(MaanagerFN.getText());
        Matcher LNameMatcher=TextPattern.matcher(ManagerLn.getText());
        Matcher RestaurantNameMatcher=TextPattern.matcher(RestaurantName.getText());
        Matcher ManagerAddressMatcher=TextPattern.matcher(ManagerAddress.getText());
        Pattern NumPattern=Pattern.compile("[0-9]+");
        Matcher ManagerPhoneMatcher=NumPattern.matcher(ManagerPhone.getText());
        Matcher RestaurantPhoneMatcher=NumPattern.matcher(RestaurantPhone.getText());


        if(ManagerAddress.getText().equals("") || ManagerPhone.getText().equals("") || ManagerUserName.getText().equals("") ||
                ManagerDb.equals("") || ManagerPass.getText().equals("") || RestaurantName.getText().equals("") ||
                RestaurantPhone.getText().equals("")||RestaurantAdrdess.getText().equals("")||!(M.isSelected())&&!(F.isSelected())) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure to enter all the fields ");
            alert.showAndWait();
            return;
        }
         if(!ValidPass()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one (digit,lowercase,uppercase and special character)" +
                    " and length must be between 6-15");
            alert.showAndWait();

        }
        else if(ManagerPhone.getText().length()<10 || ManagerPhone.getText().length()>10) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that your  manager phone number is 10 numbers");
            alert.showAndWait();
        }
        else if (RestaurantPhone.getText().length()<10||RestaurantPhone.getText().length()>10){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that your phone restaurant number is 10 numbers");
            alert.showAndWait();
        }
        else if(!ManagerPhoneMatcher.matches()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that your manager phone number not contains characters");
            alert.showAndWait();
        }
        else if(!RestaurantPhoneMatcher.matches()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that your restaurant phone number not contains characters");
            alert.showAndWait();
        }
        else if(!FNameMatcher.matches()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that your  manager first name not contains numbers");
            alert.showAndWait();
        }
        else if(!LNameMatcher.matches()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that your  manager last name not contains numbers");
            alert.showAndWait();
        }
        else if(!ManagerAddressMatcher.matches()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that manager  address not contains numbers");
            alert.showAndWait();
        }
        else if(!RestaurantNameMatcher.matches()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that restaurant name not contains numbers");
            alert.showAndWait();
        }
        else{

            System.out.println("hielse");
            try {
                OracleDataSource oracleDataSource = new OracleDataSource();
                String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
                oracleDataSource.setURL(url);
                oracleDataSource.setUser("SYSTEM");
                oracleDataSource.setPassword("f123456789");
                Connection connection = oracleDataSource.getConnection();
                connection.setAutoCommit(false);
                System.out.println("GGGGG");
                System.out.println(ManagerDb.getValue());
                // if(connection.isValid(1000)){
                // JOptionPane.showMessageDialog(null,"hello");
                //}
                String generatedColumns[] = { "RESTID" };
                String query = "insert into RESTAURANT (RESTPHONE,RESTADDRESS,RESTNAME) values(?,?,?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query,generatedColumns);
                preparedStatement1.setString(1, (RestaurantPhone.getText()));
                preparedStatement1.setString(2, RestaurantAdrdess.getText());
                preparedStatement1.setString(3, RestaurantName.getText());
                String query1 = "insert into MANAGERSS (MANAGERPASSWORD,MANAGERFNAME,MANAGERLNAME,MANAGERBDATE,MANAGERADDRESS,MANAGERPHONENUM,RESTID,MANAGERGENDER,RESTNAME,RESTADDRESS,RESTPHONE,MANAGERUSERNAME) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query1);
                preparedStatement.setString(1, ManagerPass.getText());
                preparedStatement.setString(2, MaanagerFN.getText());
                preparedStatement.setString(3, ManagerLn.getText());
                preparedStatement.setDate(4, Date.valueOf(ManagerDb.getValue()));
                preparedStatement.setString(5, ManagerAddress.getText());
                preparedStatement.setInt(6, Integer.parseInt(ManagerPhone.getText()));
                preparedStatement.setString(9, (RestaurantName.getText()));
                preparedStatement.setString(10, (RestaurantAdrdess.getText()));
                preparedStatement.setString(11, (RestaurantPhone.getText()));
                preparedStatement.setString(12, ManagerUserName.getText());


                if (M.isSelected()) {
                    preparedStatement.setString(8, "M");
                    System.out.println("Male");
                } else if (F.isSelected()) {
                    preparedStatement.setString(8, "F");

                //preparedStatement.setString(10,RestaurantName.getText());
                System.out.println("Female");
            }
                try {
                    int r1 = preparedStatement1.executeUpdate();
                    Long restID =1L ;
                    try (ResultSet generatedKeys = preparedStatement1.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            restID = generatedKeys.getLong(1);
                        }
                        else {
                            throw new SQLException("Creating user failed, no ID obtained.");
                        }
                    }
                    preparedStatement.setLong(7, restID);

                    int r= preparedStatement.executeUpdate();
                    connection.commit();
                    if(r>0 || r1>0)
                    {
                        JOptionPane.showMessageDialog(null,"User Created");
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/HOME.fxml"));
                        stage.setScene(root.getScene());
                    }
                }
                catch (Exception exp1)
                {
                    connection.rollback();
                    if (exp1.getMessage().contains("unique constraint"))
                    JOptionPane.showMessageDialog(null,"Username already exists");
                    else
                        JOptionPane.showMessageDialog(null,exp1);

                }
                finally{
                    connection.close();
                }

            } catch (Exception eve) {
                eve.printStackTrace();
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
}