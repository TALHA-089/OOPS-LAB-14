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

public class RegisterController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField tfUserID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPhoneNumber;

    @FXML
    private TextField tfAge;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfConfirmPassword;

    @FXML
    protected void Back() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LandingPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),320,450);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void RegisterUser(){
        String UserID = tfUserID.getText();
        String Name = tfName.getText();
        String Email = tfEmail.getText();
        String PhoneNumber = tfPhoneNumber.getText();
        String Age = tfAge.getText();
        String password = pfPassword.getText();
        String cpassword = pfConfirmPassword.getText();


        if(UserID.isEmpty() || Name.isEmpty() || Email.isEmpty() || PhoneNumber.isEmpty() || Age.isEmpty() || password.isEmpty() || cpassword.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("EMPTY FIELDS!");
            alert.setHeaderText(null);
            alert.setContentText("PLEASE FILL OUT ALL THE FIELDS!");
            alert.showAndWait();
            return;
        }

        if(!password.equals(cpassword)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INVALID PASSWORD!");
            alert.setHeaderText(null);
            alert.setContentText("Password and Confirm Password does not match!");
            alert.showAndWait();
            return;
        }

        final String DB_URL = "jdbc:mysql://localhost/LAB?serverTimeZone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "Talha@786";

        try(Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USER (UserID,Name,Email,PhoneNumber,Age,Password) VALUES(?,?,?,?,?,?)")){

            preparedStatement.setString(1,UserID);
            preparedStatement.setString(2,Name);
            preparedStatement.setString(3,Email);
            preparedStatement.setString(4,PhoneNumber);
            preparedStatement.setString(5,Age);
            preparedStatement.setString(6,password);

            int AddedRows = preparedStatement.executeUpdate();

            if(AddedRows > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS!");
                alert.setHeaderText(null);
                alert.setContentText("USER REGISTERED SUCCESSFULLY");
                alert.showAndWait();

                Back();
            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
