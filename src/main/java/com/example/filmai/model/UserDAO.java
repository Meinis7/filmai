package com.example.filmai.model;

import com.example.filmai.model.User;
import com.example.filmai.utills.HibernateUtil;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

   /* public static void insert(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }*/
   public static void create(User user){
       //prisijungimai prie db
       String jdbcUrl = "jdbc:mysql://localhost:3306/db";
       String querry = "INSERT INTO `users`(`username`, `password`, `email`) VALUES (?,?,?)";
       //vykdomi prisijungimai prie db ir atliekama/ivykdoma uzklausa
       try {
           Connection connection = DriverManager.getConnection(jdbcUrl,"root","");
           //siekiant isvengti sql injekciju, kiekviena laukeli surasom i uzklausa atskirai-ignoruojami specialus simboliai
           PreparedStatement preparedStatement = connection.prepareStatement(querry);
           preparedStatement.setString(1,user.getUsername());
           preparedStatement.setString(2,user.getPassword());
           preparedStatement.setString(3,user.getEmail());

           //naujo sukurimui,esamo iraso redagavimui ir trinimui naudojame excuteUpdate metodda
           //esamo iraso paieskai naudojame querry metoda
           preparedStatement.executeUpdate();

           //ivykdzius uzklausas gera praktika uzdaryti prisijungimus
           preparedStatement.close();
           connection.close();

           System.out.println("Sekmingai sukurem nauja irasa");
       } catch (SQLException e) {
           e.printStackTrace();
           System.out.println("Ivyko klaida kuriant nauja iraso");
       }
   }

   public static String getBCryptPasssword(String username){
       List<User> list= new ArrayList<>();
       String jdbcUrl="jdbc:mysql://localhost:3306/db";
       String querry="SELECT password FROM users WHERE username=?";
       String passwordBcrypt="";
       try {
           Connection connection=DriverManager.getConnection(jdbcUrl,"root","");
           PreparedStatement preparedStatement = connection.prepareStatement(querry);
           preparedStatement.setString(1,username);
           ResultSet resultSet=preparedStatement.executeQuery();
           while (resultSet.next()){
               passwordBcrypt=resultSet.getString("password");
           }
           preparedStatement.close();
           connection.close();
           System.out.println("Pavyko rasti pavadinima ");
       } catch (SQLException e) {
           e.printStackTrace();
           System.out.println("Nepavyko rasti pavadinimo");
       }

       return passwordBcrypt;
   }
}
