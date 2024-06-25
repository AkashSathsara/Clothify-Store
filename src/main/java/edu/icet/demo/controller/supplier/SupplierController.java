package edu.icet.demo.controller.supplier;

import edu.icet.demo.controller.user.UserController;
import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.Supplier;
import edu.icet.demo.dto.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierController {
    private static SupplierController instance;
    private SupplierController(){}

    public static SupplierController getInstance(){
        if(instance==null){
            return instance = new SupplierController();
        }
        return instance;
    }

    public ObservableList<Supplier> getAllSupplier() {
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM supplierentity");
            while(resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                supplierList.add(supplier);
            }
            return supplierList;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
