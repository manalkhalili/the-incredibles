package sample.JAVA;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class ListTableManager {
    public SimpleStringProperty ManagerUserName = new SimpleStringProperty();
    public SimpleStringProperty FName = new SimpleStringProperty();
    public SimpleStringProperty LName = new SimpleStringProperty();
    public SimpleStringProperty ManagerAddress = new SimpleStringProperty();
    public SimpleIntegerProperty ManagerPhone = new SimpleIntegerProperty();
    public SimpleStringProperty Gender = new SimpleStringProperty();
    public SimpleStringProperty DateOfBirth = new SimpleStringProperty();
    public SimpleStringProperty RestName=new SimpleStringProperty();
    public SimpleStringProperty RestAddress=new SimpleStringProperty();
    public SimpleIntegerProperty RestID = new SimpleIntegerProperty();
    public SimpleIntegerProperty RestPhone = new SimpleIntegerProperty();

    public String getManagerUserName() {
        return ManagerUserName.get();
    }

    public SimpleStringProperty managerUserNameProperty() {
        return ManagerUserName;
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

    public String getManagerAddress() {
        return ManagerAddress.get();
    }

    public SimpleStringProperty managerAddressProperty() {
        return ManagerAddress;
    }

    public int getManagerPhone() {
        return ManagerPhone.get();
    }

    public SimpleIntegerProperty managerPhoneProperty() {
        return ManagerPhone;
    }

    public String getGender() {
        return Gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return Gender;
    }

    public String getDateOfBirth() {
        return DateOfBirth.get();
    }

    public SimpleStringProperty dateOfBirthProperty() {
        return DateOfBirth;
    }

    public String getRestName() {
        return RestName.get();
    }

    public SimpleStringProperty restNameProperty() {
        return RestName;
    }

    public String getRestAddress() {
        return RestAddress.get();
    }

    public SimpleStringProperty restAddressProperty() {
        return RestAddress;
    }

    public int getRestID() {
        return RestID.get();
    }

    public SimpleIntegerProperty restIDProperty() {
        return RestID;
    }

    public int getRestPhone() {
        return RestPhone.get();
    }

    public SimpleIntegerProperty restPhoneProperty() {
        return RestPhone;
    }
}

