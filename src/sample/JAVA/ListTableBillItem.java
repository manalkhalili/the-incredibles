package sample.JAVA;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ListTableBillItem {
    public SimpleStringProperty Items = new SimpleStringProperty();
    public SimpleIntegerProperty Quantity = new SimpleIntegerProperty();
    public SimpleIntegerProperty Price = new SimpleIntegerProperty();
    public SimpleIntegerProperty BillId =new SimpleIntegerProperty();

    public String getItems() {
        return Items.get();
    }

    public SimpleStringProperty itemsProperty() {
        return Items;
    }

    public int getQuantity() {
        return Quantity.get();
    }
    public void setQuantity(Integer quantity) {
         this.Quantity.set(quantity);
    }
    public SimpleIntegerProperty quantityProperty() {
        return Quantity;
    }

    public int getPrice() {
        return Price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return Price;
    }
}
