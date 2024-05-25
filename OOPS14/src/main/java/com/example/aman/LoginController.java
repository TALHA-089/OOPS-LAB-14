package com.example.aman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfUserID;

    @FXML
    private PasswordField pfPassword;

    @FXML
    protected void Back() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LandingPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 450);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void LoginUser() {
        String UserID = tfUserID.getText();
        String password = pfPassword.getText();

        if (UserID.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("EMPTY FIELDS!");
            alert.setHeaderText(null);
            alert.setContentText("PLEASE FILL OUT ALL THE FIELDS!");
            alert.showAndWait();
            return;
        }

        final String DB_URL = "jdbc:mysql://localhost/LAB?serverTimeZone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "Talha@786";

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT UserID, Password FROM USER WHERE UserID=? AND Password=?")) {

            preparedStatement.setString(1, UserID);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS!");
                alert.setHeaderText(null);
                alert.setContentText("Login was Successful");
                alert.showAndWait();

                Stage stage = (Stage) btnLogin.getScene().getWindow();
                FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("Home.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure!");
                alert.setHeaderText(null);
                alert.setContentText("Login was unsuccessful");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
