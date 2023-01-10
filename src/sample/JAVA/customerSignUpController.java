package sample.JAVA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.*;
import oracle.jdbc.pool.OracleDataSource;
import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



    public class customerSignUpController {
        @FXML
        private TextField FNAME;
        @FXML
        private TextField LNAME;
        @FXML
        private PasswordField PASS;
        @FXML
        private TextField ADDRESS;
        @FXML
        private DatePicker BD;
        @FXML
        private TextField PHONENUM;
        @FXML
        private TextField CustomerUserName;
        @FXML
        private RadioButton M;
        @FXML
        private RadioButton F;
        @FXML
        private Button SignUpButton ;
        @FXML
        ToggleGroup group;
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
            matcher = pattern.matcher(PASS.getText());

            if(matcher.matches()){
                return true;
            }
            else return false;
        }
        public void SIGNUPBUTTON (ActionEvent e)throws IOException {
            Pattern TextPattern=Pattern.compile("[a-zA-Z]+");
            Matcher FNameMatcher=TextPattern.matcher(FNAME.getText());
            Matcher LNameMatcher=TextPattern.matcher(LNAME.getText());
            Matcher AddressMatcher=TextPattern.matcher(ADDRESS.getText());
            Pattern NumPattern=Pattern.compile("[0-9]+");
            Matcher PhoneNumMatcher=NumPattern.matcher(PHONENUM.getText());
            if(FNAME.getText().equals("")||LNAME.getText().equals("")||PASS.getText().equals("")||
                    ADDRESS.getText().equals("")||BD.equals("")||PHONENUM.getText().equals("")
                    ||!(M.isSelected())&&!(F.isSelected())){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Please make sure to enter all the fields ");
                alert.showAndWait();

            }
            if(!ValidPass()){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate password");
                alert.setHeaderText(null);
                alert.setContentText("Password must contain at least one (digit,lowercase,uppercase and special character)" +
                        " and length must be between 6-15");
                alert.showAndWait();
            }
            else if(PHONENUM.getText().length()<10 || PHONENUM.getText().length()>10) {
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Please make sure that your  phone number is 10 numbers");
                alert.showAndWait();
            }

            else   if(!FNameMatcher.matches()){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Please make sure that your  first name not contains numbers");
                alert.showAndWait();
            }
            else if(!LNameMatcher.matches()){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Please make sure that your  last name not contains numbers");
                alert.showAndWait();
            }

            else if(!AddressMatcher.matches()){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Please make sure that  address not contains numbers");
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
                    System.out.println(BD.getValue());

                    String query1 ="insert into CUSTOMER (CUSTOMERUSERNAME,CUSTOMERPASSWORD,CUSTOMERFNAME,CUSTOMERLNAME,CUSTOMERBDATE,CUSTOMERADDRESS,CUSTOMERPHONENUM,CUSTOMERGENDER) values(?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement=connection.prepareStatement(query1);
                    preparedStatement.setString(1,CustomerUserName.getText());
                    preparedStatement.setString(2,PASS.getText());
                    preparedStatement.setString(3,FNAME.getText());
                    preparedStatement.setString(4,LNAME.getText());
                    preparedStatement.setDate(5, Date.valueOf(BD.getValue()));
                    preparedStatement.setString(6,ADDRESS.getText());
                    preparedStatement.setInt(7,Integer.parseInt(PHONENUM.getText()));

                    if(M.isSelected()){
                        preparedStatement.setString(8,"M");
                        System.out.println("Male");

                    }
                    else {
                        preparedStatement.setString(8,"F");
                        System.out.println("Female");
                    }
                    int r= preparedStatement.executeUpdate();
                    connection.commit();
                    if(r>0)
                    {
                        JOptionPane.showMessageDialog(null,"User Created");
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/HOME.fxml"));
                        stage.setScene(root.getScene());
                    }

                    connection.close();
                } catch (Exception eve) {
                    //connection.rollback();
                    if (eve.getMessage().contains("unique constraint"))
                        JOptionPane.showMessageDialog(null,"Username already exists");
                    else
                        JOptionPane.showMessageDialog(null,eve);
                }

            }

        }

}
