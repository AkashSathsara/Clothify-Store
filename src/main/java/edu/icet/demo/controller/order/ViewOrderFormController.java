package edu.icet.demo.controller.order;

import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.controller.login.LoginFormController;
import edu.icet.demo.dto.Order;
import edu.icet.demo.dto.OrderDetail;
import edu.icet.demo.dto.tm.Table04TM;
import edu.icet.demo.dto.tm.TblCart2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewOrderFormController implements Initializable {

    public TableColumn colOrderId;
    public TableView tblOrders;
    public JFXTextField txtOrderId;
    public TableColumn colDate;
    public TableColumn colUserId;
    public TableColumn colItemCode;
    public TableColumn colPayment;
    public TableColumn colQty;
    public TableView tblCart;
    public ImageView imgView;

    ObservableList<TblCart2> cartList = FXCollections.observableArrayList();

    private void loadTable02() {
        ObservableList<Table04TM> table02Data = FXCollections.observableArrayList();
        ObservableList<Order> allItems = OrderController.getInstance().getAllOrders();
        allItems.forEach(order -> {
            Table04TM table02 = new Table04TM(
                    order.getOrderId(),
                    order.getOrderDate(),
                    order.getUserId()


            );
            table02Data.add(table02);
        });

        tblOrders.setItems(table02Data);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("Oid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("Uid"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadTable02();
        Image image1 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img11.png");
        imgView.setImage(image1);
    }

    public void btnSearchOnAction(ActionEvent event) {
        ObservableList<OrderDetail> allItems = OrderController.getInstance().getAllOrderDetails();
        allItems.forEach(orderDetail -> {
       if((txtOrderId.getText()).equals(orderDetail.getItemCode())){
           TblCart2 cartTbl = new TblCart2(
                   orderDetail.getOrderId(),
                   orderDetail.getPayment(),
                   orderDetail.getQty()
           );
           cartList.add(cartTbl);
           }
        });
        tblCart.setItems(cartList);
    }


    public void btnDeleteOnAction(ActionEvent event) {
    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        LoginFormController obj = new LoginFormController();
        if(((obj.userName).equalsIgnoreCase("user")) && ((obj.password).equalsIgnoreCase("1111"))){
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard2-form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("DashBoard Form");
            stage.show();
            Node n =(Node) event.getSource();
            Stage stage2 =(Stage) n.getScene().getWindow();
            stage2.close();
        }else{
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard1-form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("DashBoard Form");
            stage.show();
            Node n =(Node) event.getSource();
            Stage stage2 =(Stage) n.getScene().getWindow();
            stage2.close();
        }
    }
}
