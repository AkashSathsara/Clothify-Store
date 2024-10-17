package edu.icet.demo.controller.item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.Util.BoType;
import edu.icet.demo.bo.BoFactory;
import edu.icet.demo.bo.custom.ItemBo;
import edu.icet.demo.controller.login.LoginFormController;
import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.Item;
import edu.icet.demo.dto.tm.Table02TM;
import edu.icet.demo.entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminItemFormController implements Initializable {
    public JFXTextField txtItemID;
    public JFXTextField txtItemName;
    public JFXTextField txtPrice;
    public TableColumn colItemID;
    public TableColumn colItemName;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQtyOnHand;
    public JFXTextField txtSize;
    public ImageView imgItem;
    public JFXTextField txtQtyOnHand;
    public TableView tblItem;
    public JFXButton btnClear;
    public ImageView imgItem1;

    private ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    private void loadTable02() {
        ObservableList<Table02TM> table02Data = FXCollections.observableArrayList();
        ObservableList<Item> allItems = ItemController.getInstance().getAllItem();
        allItems.forEach(item -> {
            Table02TM table02 = new Table02TM(
                    item.getItemId(),
                    item.getItemName(),
                    item.getSize(),
                    item.getPrice(),
                    item.getQtyOnHand()
            );
            table02Data.add(table02);
        });

        tblItem.setItems(table02Data);
    }


    public void btnAddOnAction(ActionEvent actionEvent){
        Item item = new Item(
                txtItemID.getText(),
                txtItemName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                txtSize.getText()

        );
        boolean b = itemBo.save(item);
        System.out.println(b);
        loadTable02();
        clearText();
        generateOrderId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Added !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Added !").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Item item = new Item(
                txtItemID.getText(),
                txtItemName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                txtSize.getText()
        );

        boolean b = itemBo.update(item);
        System.out.println(b);
        loadTable02();
        clearText();
        generateOrderId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Updated !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Item user = new Item(
                txtItemID.getText(),
                txtItemName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                txtSize.getText()
        );

        boolean b = itemBo.delete(user);
        System.out.println(b);
        loadTable02();
        clearText();
        generateOrderId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Deleted !").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        ItemEntity itemEntity = itemBo.search(txtItemID.getText());
        System.out.println(itemEntity);

        txtItemName.setText(itemEntity.getItemName());
        txtSize.setText(itemEntity.getSize());
        txtPrice.setText(String.valueOf(itemEntity.getPrice()));
        txtQtyOnHand.setText(String.valueOf(itemEntity.getQtyOnHand()));

        Image image = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\"+txtItemID.getText()+".jpg");
        imgItem.setImage(image);
    }

    public  void generateOrderId() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM itementity");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);

            }
            if (count == 0) {
                txtItemID.setText("I001");
            }
            String lastUserId="";
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT itemId\n" +
                    "FROM itementity\n" +
                    "ORDER BY itemId DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()){
                lastUserId = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastUserId);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    txtItemID.setText(String.format("I%03d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearText(){
        txtItemID.setText(null);
        txtItemName.setText(null);
        txtSize.setText(null);
        txtPrice.setText(null);
        txtQtyOnHand.setText(null);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        loadTable02();
        generateOrderId();
        Image image1 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img16.jpg");
        imgItem1.setImage(image1);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        imgItem.setImage(null);
        txtItemID.setText(null);
        txtItemName.setText(null);
        txtSize.setText(null);
        txtPrice.setText(null);
        txtQtyOnHand.setText(null);
        generateOrderId();

    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard2-form.fxml"));
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
