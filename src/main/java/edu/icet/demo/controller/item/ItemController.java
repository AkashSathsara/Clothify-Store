package edu.icet.demo.controller.item;

import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.Item;
import edu.icet.demo.dto.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemController{

        private static ItemController instance;
        private ItemController(){}

        public static ItemController getInstance(){
            if(instance==null){
                return instance = new ItemController();
            }
            return instance;
        }

        public ObservableList<Item> getAllItem() {
            ObservableList<Item> itemList = FXCollections.observableArrayList();
            try {
                ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM itementity");
                while(resultSet.next()){
                    Item item = new Item(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getInt(4),
                            resultSet.getString(5)
                    );
                    itemList.add(item);
                }
                return itemList;

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


