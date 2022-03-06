package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.User;
import com.example.filmai.model.UserDAO;
import com.example.filmai.utills.BcryptPassword;
import com.example.filmai.utills.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField registerUsername;
    @FXML
    private Label registerStatus;
    @FXML
    private TextField email;
    @FXML
    private PasswordField registerPassword;
    @FXML
    private PasswordField confirmPassword;

    public void onRegisterButton(ActionEvent event) throws IOException {
        String username=registerUsername.getText();
        String email2=email.getText();
        String password=registerPassword.getText();
        String confirm=confirmPassword.getText();

        if(Validation.isValidEmail(email2)&&Validation.isValidUsername(username)&&Validation.isValidPassword(password)&&Validation.isValidPassword(confirm)) {
            String bcryptpassword=BcryptPassword.hashPassword(password);
            User user= new User(username,bcryptpassword,email2);
           // UserDAO.insert(user);
            UserDAO.create(user);

            Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(root, 600, 400));
            registerStage.setTitle("Registracijos langas");
            registerStage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

        if(!Validation.isValidUsername(username)){
            registerStatus.setText("Neteisingas vardas");
        }
        if(!Validation.isValidPassword(password)){
            registerStatus.setText("Klaidingas slaptazodis");
        }
        if (!Validation.isValidPassword(password)==Validation.isValidPassword(confirm)){
            registerStatus.setText("Neteisingas slaptazodis, neatitinka patvirtinancio slaptazodzio");
        }

        if (!Validation.isValidEmail(email2)){
            registerStatus.setText("Klaidingas email");
        }
    }
}
