package com.example.filmai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //vaizdo sukurimas
        FXMLLoader loginView = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
       //sukurimas scenos is vaizdo
        Scene scene = new Scene(loginView.load(), 500, 400);
        //stage(langas) bus vienas, scenu gali buti daug
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Prisijungimo langas");
        //langui prisskiriama scena
        stage.setScene(scene);
        //parodymas lango
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}