package sample.JAVA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class billController {
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
    public void confirmYourOrder(ActionEvent event) throws IOException {
        //String itemName = BillTable.getSelectionModel().getSelectedItem().getItems();
        int bId = UserInfo.getInstance().getBillId();
        //Integer newQty = Integer.valueOf(qty.getText());
        try {
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            connection.setAutoCommit(false);
//            String stmtqry="delete from BILL_ITEMS where BILLID=?  ";
//            connection.commit();
//            PreparedStatement preparedStatement1 = connection.prepareStatement(stmtqry);
//            System.out.println(bId);
//            preparedStatement1.setInt(1,bId);
//            preparedStatement1.executeQuery();

            connection.close();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage root = FXMLLoader.load(getClass().getResource("/sample/FXML/finish.fxml"));
            stage.setScene(root.getScene());
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    private TextField qty;
    @FXML
    public void DeleteButton() throws IOException{
        String itemName = BillTable.getSelectionModel().getSelectedItem().getItems();
        int bId = UserInfo.getInstance().getBillId();

        try{
            Integer newQty = Integer.valueOf(qty.getText());
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            connection.setAutoCommit(false);
            String stmtqry="delete from BILL_ITEMS where BILLID=? and ITEMNAME=?";
            connection.commit();
            PreparedStatement preparedStatement1 = connection.prepareStatement(stmtqry);
            System.out.println(bId);
            preparedStatement1.setInt(1,bId);
            preparedStatement1.setString(2,itemName);
            preparedStatement1.executeQuery();
            connection.close();
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,"please enter a new quantity value");
            JOptionPane.showMessageDialog(null,e);
        }
        initialize();
    }

    @FXML
    public void updateQty() throws IOException {
        String itemName = BillTable.getSelectionModel().getSelectedItem().getItems();
        int bId = UserInfo.getInstance().getBillId();
        try{
            Integer newQty = Integer.valueOf(qty.getText());
            System.out.println(itemName + bId +""+ newQty);
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();
            connection.setAutoCommit(false);
            String stmtqry="update bill_items set QTY=? where BILLID=? and ITEMNAME=?";
            connection.commit();
            PreparedStatement preparedStatement1 = connection.prepareStatement(stmtqry);
            preparedStatement1.setInt(1,newQty);
            preparedStatement1.setInt(2,bId);
            preparedStatement1.setString(3,itemName);
            preparedStatement1.executeUpdate();

            connection.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"please enter a new quantity value");
            JOptionPane.showMessageDialog(null,e);
        }
        initialize();
    }

    public TextField TotalPrice;
    public TableView<ListTableBillItem> BillTable;
    public TableColumn<ListTableBillItem,String> Items;
    public TableColumn<ListTableBillItem,Integer> Quantity;
    public TableColumn<ListTableBillItem,Integer> Price;
    Alert alert=null;

    public void initialize(){
        System.out.println("1");
        Items.setCellValueFactory(new PropertyValueFactory<ListTableBillItem,String>("Items"));
        Quantity.setCellValueFactory(new PropertyValueFactory<ListTableBillItem,Integer>("Quantity"));
        Price.setCellValueFactory(new PropertyValueFactory<ListTableBillItem,Integer>("Price"));
        Quantity.setOnEditCommit(event -> {
            System.out.println( event.getRowValue());
            //user.setType(event.getNewValue());
            //updateData("type", event.getNewValue(), user.getId());
        });
        Quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Quantity.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ListTableBillItem, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ListTableBillItem, Integer> t) {
                        ((ListTableBillItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setQuantity(t.getNewValue());
                    }
                }
        );
        BillTable.setEditable(true);
        getBill();
        System.out.println("1111");
    }
    public ObservableList<ListTableBillItem> getBill(){

        ObservableList<ListTableBillItem> BillItem= FXCollections.observableArrayList();
        try {
            int bId = UserInfo.getInstance().getBillId();
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("SYSTEM");
            oracleDataSource.setPassword("f123456789");
            Connection connection = oracleDataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from BILL_ITEMS where BILLID=?");
            preparedStatement.setInt(1,bId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                ListTableBillItem bill=new ListTableBillItem();
                bill.Items.set(resultSet.getString("ITEMNAME"));
                bill.Price.set(resultSet.getInt("PRICE"));
                bill.Quantity.set(resultSet.getInt("QTY"));

                BillItem.add(bill);
            }

            BillTable.setItems(BillItem);

            int sum = 0;
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT sum(PRICE*QTY) from BILL_ITEMS where BILLID=?");
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while(resultSet1.next()){
                int c=resultSet1.getInt(3);
                int c1=resultSet1.getInt(4);
                sum+=(c*c1);
            }
            TotalPrice.setText(Integer.toString(sum));

            connection.close();
        }catch (Exception ex){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }
        return BillItem;
    }


}
