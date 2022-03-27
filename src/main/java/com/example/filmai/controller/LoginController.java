package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.UserDAO;
import com.example.filmai.model.UserSingleton;
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

public class LoginController {
    @FXML
    private Label loginStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        String username2=username.getText();
        String password2=password.getText();
        if(Validation.isValidUsername(username2)&&Validation.isValidPassword(password2)){
            //loginStatus.setText("Teisingas ivestas vardas");
            String passwordDB= UserDAO.getBCryptPasssword(username2);
            if(passwordDB.equals("")){
                loginStatus.setText("Klaidingai ivestas vardas");
            }else{
                boolean isValidPassword= BcryptPassword.checkPassword(password2,passwordDB);
                if(isValidPassword){
                    loginStatus.setText("Teissingai ivesti prisijungimo duomenys db");

                    UserSingleton userSingleton=UserSingleton.getInstance();
                    userSingleton.setUsername(username2);

                    ifCorrectLogin(event);
                }else {
                    loginStatus.setText("blogas slaptazodis");
                }
            }

        }else{
            loginStatus.setText("Klaidingas ivestas vardas");
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
    @FXML
    public void ifCorrectLogin(ActionEvent event) throws IOException {
        //vaizdo sukurimas
        Parent root = FXMLLoader.load(MainApplication.class.getResource("dashboard-view.fxml"));
        Stage registerStage=new Stage();
        //stage(langas) bus vienas, scenu gali buti daug
        registerStage.setScene(new Scene(root,800,550));
        registerStage.setTitle("Registracijos langas");
        //parodymas lango
        registerStage.show();
        //kodas reikalingas paslepti pries tai buvusi langa
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
}