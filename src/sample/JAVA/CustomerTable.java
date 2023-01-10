package sample.JAVA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerTable {
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

    public TableView<ListTableCustomer> CustomerTable;
    public TableColumn<ListTableCustomer,String> FName;
    public TableColumn<ListTableCustomer,String> LName;
    public TableColumn<ListTableCustomer,String> CustomerUserName;
    public TableColumn<ListTableCustomer,String> CustomerAddress;
    public TableColumn<ListTableCustomer,Integer> CustomerPhone;
    public TableColumn<ListTableCustomer,String> Birth;
    public TableColumn<ListTableCustomer,String> Gender;
    public Alert alert;

    public void initialize(){
        System.out.println("1");
        FName.setCellValueFactory(new PropertyValueFactory<ListTableCustomer,String>("FName"));
        LName.setCellValueFactory(new PropertyValueFactory<ListTableCustomer,String>("LName"));
        CustomerUserName.setCellValueFactory(new PropertyValueFactory<ListTableCustomer,String>("CustomerUserName"));
        CustomerAddress.setCellValueFactory(new PropertyValueFactory<ListTableCustomer,String>("CustomerAddress"));
        CustomerPhone.setCellValueFactory(new PropertyValueFactory<ListTableCustomer,Integer>("CustomerPhone"));
        Gender.setCellValueFactory(new PropertyValueFactory<ListTableCustomer,String>("Gender"));
        Birth.setCellValueFactory(new PropertyValueFactory<ListTableCustomer,String>("Birth"));

        getCustomer();
        System.out.println("1111");
    }

    public ObservableList<ListTableCustomer> getCustomer(){

        ObservableList<ListTableCustomer> CustomerItem= FXCollections.observableArrayList();
        try {
            System.out.println("2");
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from CUSTOMER");
            System.out.println("3");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("3");
                ListTableCustomer customer=new ListTableCustomer();
                customer.FName.set(resultSet.getString("CUSTOMERFNAME"));
                customer.LName.set(resultSet.getString("CUSTOMERLNAME"));
                customer.CustomerUserName.set(resultSet.getString("CUSTOMERUSERNAME"));
                customer.CustomerPhone.set(resultSet.getInt("CUSTOMERPHONENUM"));
                customer.CustomerAddress.set(resultSet.getString("CUSTOMERADDRESS"));
                customer.Gender.set(resultSet.getString("CUSTOMERGENDER"));
                customer.Birth.set(resultSet.getString("CUSTOMERBDATE"));

                CustomerItem.add(customer);
            }
            System.out.println("4");
            CustomerTable.setItems(CustomerItem);
            connection.close();
        }catch (Exception ex){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }
        return CustomerItem;
    }
}
