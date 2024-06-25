package edu.icet.demo.controller.user;

import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController{

    private static UserController instance;
    private UserController(){}

    public static UserController getInstance(){
        if(instance==null){
            return instance = new UserController();
        }
        return instance;
    }

    public User searchCustomer(String userId){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM userentity WHERE id='"+userId+"'");
            while (resultSet.next()) {
                return new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ObservableList<User> getAllUser() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM userentity");
            while(resultSet.next()){
                User user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                userList.add(user);
            }
            return userList;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
