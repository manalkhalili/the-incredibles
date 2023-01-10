package sample.JAVA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ManagerTable {
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

    public Button UserNameButton;

    public TableView<ListTableManager> ManagerTable;
    public TableColumn<ListTableManager, String> FName;
    public TableColumn<ListTableManager, String> LName;
    public TableColumn<ListTableManager,String> ManagerUserName;
    public TableColumn<ListTableManager,String> ManagerAddress;
    public TableColumn<ListTableManager,Integer> ManagerPhone;
    public TableColumn<ListTableManager,String> DateOfBirth;
    public TableColumn<ListTableManager,String> Gender;
    public TableColumn<ListTableManager,String> RestaurantName;
    public TableColumn<ListTableManager,String> RestaurantAddress;
    public TableColumn<ListTableManager,Integer> RestaurantID;
    public TableColumn<ListTableManager,Integer> RestaurantPhone;
    public Alert alert;

    private User user = new User();


    public void initialize(){
        UserNameButton.setText(UserInfo.getInstance().getUsername());

        System.out.println("1");

        FName.setCellValueFactory(new PropertyValueFactory<>("FName"));
        LName.setCellValueFactory(new PropertyValueFactory<>("LName"));
        ManagerUserName.setCellValueFactory(new PropertyValueFactory<>("ManagerUserName"));
        ManagerAddress.setCellValueFactory(new PropertyValueFactory<>("ManagerAddress"));
        ManagerPhone.setCellValueFactory(new PropertyValueFactory<>("ManagerPhone"));
        DateOfBirth.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        RestaurantName.setCellValueFactory(new PropertyValueFactory<>("RestName"));
        RestaurantAddress.setCellValueFactory(new PropertyValueFactory<>("RestAddress"));
        RestaurantPhone.setCellValueFactory(new PropertyValueFactory<>("RestPhone"));
        RestaurantID.setCellValueFactory(new PropertyValueFactory<>("RestID"));
        getManager();
        System.out.println("1111");
    }
    void initData(User user) {
        this.user.setUsername( user.getUsername());
        this.user.setManager(user.isManager());
    }
    public ObservableList<ListTableManager> getManager(){

        ObservableList<ListTableManager> ManagerItem= FXCollections.observableArrayList();
        try {
            System.out.println("2");
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from MANAGERSS");
            System.out.println("3");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                ListTableManager manager=new ListTableManager();
                manager.FName.set(resultSet.getString("MANAGERFNAME"));
                manager.LName.set(resultSet.getString("MANAGERLNAME"));
                manager.ManagerUserName.set(resultSet.getString("MANAGERUSERNAME"));
                manager.ManagerPhone.set(resultSet.getInt("MANAGERPHONENUM"));
                manager.ManagerAddress.set(resultSet.getString("MANAGERADDRESS"));
                manager.Gender.set(resultSet.getString("MANAGERGENDER"));
                manager.DateOfBirth.set(resultSet.getString("MANAGERBDATE"));
                manager.RestID.set(resultSet.getInt("RESTID"));
                manager.RestPhone.set(resultSet.getInt("RESTPHONE"));
                manager.RestAddress.set(resultSet.getString("RESTADDRESS"));
                manager.RestName.set(resultSet.getString("RESTNAME"));

                ManagerItem.add(manager);
                System.out.println("3");
            }
            System.out.println("4");
            System.out.println(ManagerItem.get(0).ManagerUserName);
            ManagerTable.setItems(ManagerItem);
            System.out.println("5");
            connection.close();
        }catch (Exception ex){
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText(ex.getMessage());
//            alert.showAndWait();
//            ex.printStackTrace();
            System.out.println(ex);
        }
        return ManagerItem;
    }
}
