module com.example.aman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.aman to javafx.fxml;
    exports com.example.aman;
}