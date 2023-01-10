package sample.JAVA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutUsController {
    @FXML
    private Button HOMEButton;
    @FXML
    private Button OrderNowButton;
//    @FXML
//    private Button AboutUsButton;
//    private User user = new User();
//    protected URL location;
//    protected ResourceBundle rb;
//    boolean b=false;
//    @FXML
//    public void initialize(URL location, ResourceBundle  resources) {
//        System.out.println(UserInfo.getInstance().getUsername());
//        usernameButton.setText(UserInfo.getInstance().getUsername());
//    }

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
}