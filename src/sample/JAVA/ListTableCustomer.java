package sample.JAVA;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ListTableCustomer {
    public SimpleStringProperty CustomerUserName = new SimpleStringProperty();
    public SimpleStringProperty FName = new SimpleStringProperty();
    public SimpleStringProperty LName = new SimpleStringProperty();
    public SimpleStringProperty CustomerAddress = new SimpleStringProperty();
    public SimpleIntegerProperty CustomerPhone = new SimpleIntegerProperty();
    public SimpleStringProperty Gender = new SimpleStringProperty();
    public SimpleStringProperty Birth = new SimpleStringProperty();

    public String getCustomerUserName() {
        return CustomerUserName.get();
    }

    public SimpleStringProperty customerUserNameProperty() {
        return CustomerUserName;
    }


    public String getFName() {
        return FName.get();
    }

    public SimpleStringProperty FNameProperty() {
        return FName;
    }

    public String getLName() {
        return LName.get();
    }

    public SimpleStringProperty LNameProperty() {
        return LName;
    }

    public String getCustomerAddress() {
        return CustomerAddress.get();
    }

    public SimpleStringProperty customerAddressProperty() {
        return CustomerAddress;
    }

    public int getCustomerPhone() {
        return CustomerPhone.get();
    }

    public SimpleIntegerProperty customerPhoneProperty() {
        return CustomerPhone;
    }

    public String getGender() {
        return Gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return Gender;
    }

    public String getBirth() {
        return Birth.get();
    }

    public SimpleStringProperty birthProperty() {
        return Birth;
    }
}

