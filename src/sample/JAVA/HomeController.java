package sample.JAVA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button usernameButton;
@FXML
    private Button placeYourOrderButton;
@FXML
    private Button AboutUsButton;
    private User user = new User();
    protected URL location;
    protected ResourceBundle rb;
    boolean b=false;
    @FXML
    public void initialize(URL location, ResourceBundle  resources) {
        System.out.println(UserInfo.getInstance().getUsername());
        usernameButton.setText(UserInfo.getInstance().getUsername());
    }

@FXML
    public void PlaceYourOrder(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/rest1.fxml"));
        stage.setScene(root.getScene());
    }
    @FXML
    public void AboutUs(ActionEvent event)throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/AboutUs.fxml"));
        stage.setScene(root.getScene());
    }
    @FXML
    public void TheInc(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/AboutUs.fxml"));
        stage.setScene(root.getScene());

    }
    @FXML
    public void Username(ActionEvent event) throws IOException {
        System.out.println(UserInfo.getInstance().isManager());
        if(UserInfo.getInstance().isManager()){
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