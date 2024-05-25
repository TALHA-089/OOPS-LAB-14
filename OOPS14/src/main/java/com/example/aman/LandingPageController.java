package com.example.aman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingPageController {

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnExit;


    @FXML
    protected void RegisterPage() throws IOException {
        Stage stage = (Stage) btnRegister.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("REGISTER A USER!");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.show();
    }

    @FXML
    protected void LoginPage() throws IOException {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("LOGIN USER!");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.show();
    }

    @FXML
    protected void Exit(){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}