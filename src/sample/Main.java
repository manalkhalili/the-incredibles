package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage root = FXMLLoader.load(getClass().getResource("FXML/sign_in.fxml"));
        root.setTitle("THE INCREDIBLES");
        root.show();
    }


    //delete bill button
    //delete bill_items where billid =
//keep bill flag in userinfo to change true when press back button on bill page and we change it false in rest1 if it was true else call genbillid
//remove show my info call method in initialize
//show bill id and date in bill page

    public static void main(String[] args) {
        launch(args);
    }
}
