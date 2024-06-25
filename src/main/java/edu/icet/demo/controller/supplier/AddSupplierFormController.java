package edu.icet.demo.controller.supplier;

import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.Util.BoType;
import edu.icet.demo.bo.BoFactory;
import edu.icet.demo.bo.custom.SupplierBo;
import edu.icet.demo.bo.custom.UserBo;
import edu.icet.demo.controller.login.LoginFormController;
import edu.icet.demo.controller.user.UserController;
import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.Supplier;
import edu.icet.demo.dto.User;
import edu.icet.demo.dto.tm.Table01TM;
import edu.icet.demo.dto.tm.Table03TM;
import edu.icet.demo.entity.SupplierEntity;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSupplierFormController implements Initializable {
    public JFXTextField txtSupplierId;
    public ChoiceBox cmdTitle;
    public JFXTextField txtSupplierName;
    public JFXTextField txtEmail;
    public TableColumn colSupplierId;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colEmail;
    public TableView tblSupplier;
    public JFXTextField txtCompany;
    public ImageView imgSupplier;

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);


    private void loadTable03() {
        ObservableList<Table03TM> table03Data = FXCollections.observableArrayList();
        ObservableList<Supplier> allSupplier = SupplierController.getInstance().getAllSupplier();
        allSupplier.forEach(user -> {
            Table03TM table01 = new Table03TM(
                    user.getSupplierId(),
                    user.getSupplierName(),
                    user.getCompany(),
                    user.getEmail()
            );
            table03Data.add(table01);
        });

        tblSupplier.setItems(table03Data);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );
        boolean b = supplierBo.save(supplier);
        System.out.println(b);
        loadTable03();
        clearText();
        generateSupplierId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Added !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Added !").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );

        boolean b = supplierBo.update(supplier);
        System.out.println(b);
        loadTable03();
        clearText();
        generateSupplierId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Updated !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );

        boolean b = supplierBo.delete(supplier);
        System.out.println(b);
        loadTable03();
        clearText();
        generateSupplierId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Deleted !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Deleted !").show();
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        SupplierEntity supplierEntity = supplierBo.search(txtSupplierId.getText());
        System.out.println(supplierEntity);

        txtSupplierName.setText(supplierEntity.getSupplierName());
        txtCompany.setText(supplierEntity.getCompany());
        txtEmail.setText(supplierEntity.getEmail());
    }

    public  void generateSupplierId() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM supplierentity");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);

            }
            if (count == 0) {
                txtSupplierId.setText("S001");
            }
            String lastUserId="";
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT supplierId\n" +
                    "FROM supplierentity\n" +
                    "ORDER BY supplierId DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()){
                lastUserId = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastUserId);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    txtSupplierId.setText(String.format("S%03d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearText(){
        txtSupplierId.setText(null);
        txtSupplierName.setText(null);
        txtCompany.setText(null);
        txtEmail.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("company"));
        generateSupplierId();
        loadTable03();
        Image image = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img08.jpg");
        imgSupplier.setImage(image);
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
