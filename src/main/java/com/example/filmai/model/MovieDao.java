package com.example.filmai.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    public static void create(Movie movie) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "INSERT INTO `filmai`(`title`, `length`, `actors`, `director`, `year`,user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setInt(2, movie.getLength());
            preparedStatement.setString(3, movie.getActors());
            preparedStatement.setString(4, movie.getDirector());
            preparedStatement.setInt(5, movie.getYear());
            preparedStatement.setInt(6,movie.getUser_id());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Movie> searchByName(String name, String role) {
        //prisijungimai prie db
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "";
        //administratorius iesko tarp visu irasu
        if(role.equals("Administratorius")){
            if(name.isEmpty()){
                querry="SELECT * FROM `filmai`";
            }else {
                querry= "SELECT * FROM `filmai` WHERE `title` LIKE '%"+ name +"%'";
            }
        }else{
            //vartotjas iesko tik tarp savo irasu
            int userID=UserDAO.searchByUsernameReturnId(UserSingleton.getInstance().getUsername());
                if(name.isEmpty()){
                    querry="SELECT * FROM `filmai` WHERE user_id="+userID;
                }else {
                    querry= "SELECT * FROM `filmai` WHERE `title` LIKE '%"+ name +"%'"+" AND user_id="+userID;
                }
        }
        //vykdomi prisijungimai prie db ir atliekama/ivykdoma uzklausa
        ArrayList<Movie> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            //siekiant isvengti sql injekciju, kiekviena laukeli surasom i uzklausa atskirai-ignoruojami specialus simboliai
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            /*if(!name.isEmpty()){
                preparedStatement.setString(1,name);
            }*/

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("length"),
                        resultSet.getString("actors"),
                        resultSet.getString("director"),
                        resultSet.getInt("year"),
                        resultSet.getInt("user_id")
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
    public static void update(Movie movie) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String update = "UPDATE `filmai` SET `title`= ?,`length`= ?,`actors`= ?,`director`= ?,`year`= ? WHERE `id` = ?";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setInt(2, movie.getLength());
            preparedStatement.setString(3, movie.getActors());
            preparedStatement.setString(4, movie.getDirector());
            preparedStatement.setInt(5, movie.getYear());
            preparedStatement.setInt(6, movie.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko atnaujinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti įrašą");
        }
    }
    public static void deleteById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String delete = "DELETE FROM filmai WHERE id = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko ištrinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("Įrašo ištrinti nepavyko");
        }
    }
}
