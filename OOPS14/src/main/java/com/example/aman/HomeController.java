package com.example.aman;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HomeController {

    @FXML
    private Button btnShow;

    @FXML
    private Button btnBack;

    @FXML
    private TableView<User> UserTable;

    @FXML
    private TableColumn<User, String> NameColumn;

    @FXML
    private TableColumn<User, String> EmailColumn;

    @FXML
    private TableColumn<User, String> PhoneColumn;

    @FXML
    private TableColumn<User, String> AgeColumn;

    @FXML
    protected void Back() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LandingPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 450);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void ShowDetails() {
        final String DB_URL = "jdbc:mysql://localhost/LAB?serverTimeZone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "Talha@786";

        ObservableList<User> data = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT Name, Email, PhoneNumber, Age FROM USER")) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Age")
                );
                data.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        AgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        UserTable.setItems(data);
    }
}
