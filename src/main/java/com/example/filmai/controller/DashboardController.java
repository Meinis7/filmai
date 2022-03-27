package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.Movie;
import com.example.filmai.model.MovieDao;
import com.example.filmai.model.UserDAO;
import com.example.filmai.model.UserSingleton;
import com.example.filmai.utills.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private TextField idSearch;
    @FXML
    private TableView filmaiTable;
    @FXML
    private TableColumn idTable;
    @FXML
    private TableColumn filmasTable;
    @FXML
    private TableColumn trukmeTable;
    @FXML
    private TableColumn aktoriaiTable;
    @FXML
    private TableColumn RezisieriusTable;
    @FXML
    private TableColumn metaiTable;
    @FXML
    private TableColumn vartotojasTable;
    @FXML
    private TextField filmoPav;
    @FXML
    private TextField filmoTruk;
    @FXML
    private CheckBox CheckBrad;
    @FXML
    private CheckBox CheckAngelina;
    @FXML
    private CheckBox CheckJim;
    @FXML
    private RadioButton RadioJames;
    @FXML
    private RadioButton radioSteven;
    @FXML
    private RadioButton radioTarant;
    @FXML
    private ChoiceBox choiseBoxYear;
    @FXML
    private Label tableStatus;
    @FXML
    private Label grupeLabel;
    @FXML
    private Label vardasLabel;

    ObservableList<Movie> list = FXCollections.observableArrayList();

    @FXML
    public void searchButtonClick(){
        list.clear();
        String titlefield=filmoPav.getText();

        List<Movie> movieList=MovieDao.searchByName(titlefield,grupeLabel.getText());
        for(Movie movie:movieList){
            list.add(new Movie(movie.getId(),movie.getTitle(),movie.getLength(),movie.getActors(),movie.getDirector(),movie.getYear(),movie.getUser_id()));

            idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
            filmasTable.setCellValueFactory(new PropertyValueFactory<>("title"));
            trukmeTable.setCellValueFactory(new PropertyValueFactory<>("length"));
            aktoriaiTable.setCellValueFactory(new PropertyValueFactory<>("actors"));
            RezisieriusTable.setCellValueFactory(new PropertyValueFactory<>("director"));
            metaiTable.setCellValueFactory(new PropertyValueFactory<>("year"));
            vartotojasTable.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            filmaiTable.setItems(list);
        }
        if(movieList.isEmpty()){
            tableStatus.setText("Nepavyko atlikti paieskos pagal filmu pavadinimus");
        }else {
            tableStatus.setText("Pavyko atlikti paieska pagal filmu pavadinimus");
        }
    }

    @FXML
    public void createOnClick(){
        String titleField=filmoPav.getText();
        String trukme=filmoTruk.getText();

        String actors="";
        if(CheckBrad.isSelected()){
            actors+=CheckBrad.getText()+",";
        }
        if(CheckAngelina.isSelected()){
            actors+=CheckAngelina.getText()+",";
        }
        if(CheckJim.isSelected()){
            actors+=CheckJim.getText()+",";
        }

        String director="";
        if(RadioJames.isSelected()){
            director=RadioJames.getText();
        }else if(radioSteven.isSelected()){
            director=radioSteven.getText();
        }else if(radioTarant.isSelected()){
            director=radioTarant.getText();
        }

        String year="";
        if(!choiseBoxYear.getSelectionModel().isEmpty()){
            year=choiseBoxYear.getSelectionModel().getSelectedItem().toString();
        }

        if(!Validation.isValidTitle(titleField)){
            tableStatus.setText("Neteisingas filmo pavadinimas");
        }else if(!Validation.isValidTime(trukme)){
            tableStatus.setText("Neteisinga filmo trukme");
        } else if(actors.isEmpty()){
            tableStatus.setText("Pasirinkite aktorius");
        }else {
            int year2=Integer.parseInt(year);
            int trume2=Integer.parseInt(filmoTruk.getText());

            int userID=UserDAO.searchByUsernameReturnId(vardasLabel.getText());
            Movie movies=new Movie(titleField,trume2,actors,director,year2,userID);
            MovieDao.create(movies);
            tableStatus.setText("Pavyko sukurti filmo irasa");
        }
    }

    @FXML
    public void onUpdateClick(){
        String titlefield=filmoPav.getText();
        String trukme=filmoTruk.getText();
        String idfield=idSearch.getText();

        if(grupeLabel.getText().equals("Administratorius")) {
            String actors = "";
            if (CheckBrad.isSelected()) {
                actors += CheckBrad.getText() + ",";
            }
            if (CheckAngelina.isSelected()) {
                actors += CheckAngelina.getText() + ",";
            }
            if (CheckJim.isSelected()) {
                actors += CheckJim.getText() + ",";
            }

            String director = "";
            if (RadioJames.isSelected()) {
                director = RadioJames.getText();
            } else if (radioSteven.isSelected()) {
                director = radioSteven.getText();
            } else if (radioTarant.isSelected()) {
                director = radioTarant.getText();
            }

            String year = "";
            if (!choiseBoxYear.getSelectionModel().isEmpty()) {
                year = choiseBoxYear.getSelectionModel().getSelectedItem().toString();
            }

            if (!Validation.isValidTime(idfield)) {
                tableStatus.setText("Neteisingas id");
            } else if (!Validation.isValidTitle(titlefield)) {
                tableStatus.setText("Neteisingas filmo pavadinimas");
            } else if (!Validation.isValidTime(trukme)) {
                tableStatus.setText("Neteisinga filmo trukme");
            } else if (actors.isEmpty()) {
                tableStatus.setText("Pasirinkite aktorius");
            } else {
                int year2 = Integer.parseInt(year);
                int trume2 = Integer.parseInt(filmoTruk.getText());
                int id = Integer.parseInt(idSearch.getText());

                Movie movies = new Movie(id, titlefield, trume2, actors, director, year2);
                MovieDao.update(movies);
                tableStatus.setText("Pavyko redaguoti");
            }
        }else {
            //tai bus vartotojas
            tableStatus.setText("Redaguoti irasa gali tik administratorius");
        }
    }

    @FXML
    public void onDeleteClick(){
        String idfield=idSearch.getText();
        if(grupeLabel.getText().equals("Administratorius")) {
            if (!Validation.isValidTime(idfield)) {
                tableStatus.setText("Neteisingas id");
            } else {
                int id = Integer.parseInt(idSearch.getText());
                MovieDao.deleteById(id);
                tableStatus.setText("Pavyko pasalinti irasa");
            }
        }else {
            tableStatus.setText("Trinti irasa gali tik administratorius");
        }
    }
    @FXML
    public void onLogOutClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage registerStage = new Stage();
        registerStage.setScene(new Scene(root, 600, 400));
        registerStage.setTitle("Prisijungimo langas");
        registerStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiseBoxYear.getItems().addAll("2005");
        choiseBoxYear.getItems().addAll("2000");
        choiseBoxYear.getItems().addAll("2022");

        CheckBrad.setSelected(true);
        RadioJames.setSelected(true);
        choiseBoxYear.getSelectionModel().selectFirst();

        //parodomas prissjunges vartotojas
        String username=UserSingleton.getInstance().getUsername();
        vardasLabel.setText(username);
        //pardooma prisijungusio vartotojo role

        String role=UserDAO.searchByUsername(username);
        grupeLabel.setText(role);
    }
}
