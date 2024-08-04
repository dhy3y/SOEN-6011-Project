module com.example.d2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.d2 to javafx.fxml;
    exports com.example.d2;
}