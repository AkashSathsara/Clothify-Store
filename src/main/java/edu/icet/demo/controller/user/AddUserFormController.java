package edu.icet.demo.controller.user;

import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.Util.BoType;
import edu.icet.demo.bo.BoFactory;
import edu.icet.demo.bo.custom.UserBo;
import edu.icet.demo.controller.login.LoginFormController;
import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.User;
import edu.icet.demo.dto.tm.Table01TM;
import edu.icet.demo.entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUserFormController implements Initializable {
    public JFXTextField txtUserId;
    public ChoiceBox cmdTitle;
    public JFXTextField txtUserName;
    public JFXTextField txtEmail;
    public TableView tblUser;
    public TableColumn colCustomerId;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colEmail;
    public JFXTextField txtCompany;
    public ImageView imgUser;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);


    private void loadTable01() {
        ObservableList<Table01TM> table01Data = FXCollections.observableArrayList();
        ObservableList<User> allCustomer = UserController.getInstance().getAllUser();
        allCustomer.forEach(user -> {
            Table01TM table01 = new Table01TM(
                    user.getId(),
                    user.getEmail(),
                    user.getName(),
                    user.getCompany()

            );
            table01Data.add(table01);
        });

        tblUser.setItems(table01Data);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        User user = new User(
                txtUserId.getText(),
                txtUserName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );
        boolean b = userBo.save(user);
        System.out.println(b);
        loadTable01();
        clearText();
        generateOrderId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Added !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Added !").show();
        }
    }

    public  void generateOrderId() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM userentity");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);

            }
            if (count == 0) {
                txtUserId.setText("U001");
            }
            String lastUserId="";
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT id\n" +
                    "FROM userentity\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()){
                lastUserId = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastUserId);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    txtUserId.setText(String.format("U%03d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        User user = new User(
                txtUserId.getText(),
                txtUserName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );

        boolean b = userBo.update(user);
        System.out.println(b);
        loadTable01();
        clearText();
        generateOrderId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Updated !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        User user = new User(
                txtUserId.getText(),
                txtUserName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );

        boolean b = userBo.delete(user);
        System.out.println(b);
        loadTable01();
        clearText();
        generateOrderId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Deleted !").show();
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        //User user= UserController.getInstance().searchUser(txtUserId.getText());
        UserEntity userEntity = userBo.search(txtUserId.getText());
        System.out.println(userEntity);

        txtUserName.setText(userEntity.getName());
        txtCompany.setText(userEntity.getCompany());
        txtEmail.setText(userEntity.getEmail());
    }

    private void clearText(){
        txtUserId.setText(null);
        txtUserName.setText(null);
        txtCompany.setText(null);
        txtEmail.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable01();
        generateOrderId();
        Image image1 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img13.png");
        imgUser.setImage(image1);
    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
//        LoginFormController obj = new LoginFormController();//LoginFormController.getInstance();
//        if(((obj.userName).equalsIgnoreCase("user")) && ((obj.password).equalsIgnoreCase("1111"))){
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
