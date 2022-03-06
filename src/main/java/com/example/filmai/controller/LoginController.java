package com.example.filmai.controller;

import com.example.filmai.MainApplication;
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

public class LoginController {
    @FXML
    private Label loginStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onLoginButtonClick() {
        String username2=username.getText();
        String password2=password.getText();
        if(Validation.isValidUsername(username2)){
            loginStatus.setText("Teisingas ivestas vardas");
        }else{
            loginStatus.setText("Klaidingas ivestas vardas");
        }
        if(Validation.isValidPassword(password2)){
            loginStatus.setText("Teisingai ivestas slaptazodis");
        }else {
            loginStatus.setText("Klaidingas slaptazodis");
        }
        //loginStatus.setText(username2+" "+password2);
    }
    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        //vaizdo sukurimas
        Parent root = FXMLLoader.load(MainApplication.class.getResource("register-view.fxml"));
        Stage registerStage=new Stage();
        //stage(langas) bus vienas, scenu gali buti daug
        registerStage.setScene(new Scene(root,600,400));
        registerStage.setTitle("Registracijos langas");
        //parodymas lango
        registerStage.show();
        //kodas reikalingas paslepti pries tai buvusi langa
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}