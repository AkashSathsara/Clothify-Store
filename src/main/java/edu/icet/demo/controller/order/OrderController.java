package edu.icet.demo.controller.order;

import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.Order;
import edu.icet.demo.dto.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController {
    private static OrderController instance;
    private OrderController(){}

    public static OrderController getInstance(){
        if(instance==null){
            return instance = new OrderController();
        }
        return instance;
    }

    public ObservableList<Order> getAllOrders() {
        ObservableList<Order> itemList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM orderentity");
            while(resultSet.next()){
                Order order = new Order(
                        resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getString(3)
                );
                itemList.add(order);
            }
            return itemList;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<OrderDetail> getAllOrderDetails() {
        ObservableList<OrderDetail> itemList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM orderdetailentity");
            while(resultSet.next()){
                OrderDetail order = new OrderDetail(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                itemList.add(order);
            }
            return itemList;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
