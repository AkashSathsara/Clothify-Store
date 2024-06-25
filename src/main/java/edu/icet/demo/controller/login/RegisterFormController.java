package edu.icet.demo.controller.login;

import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.Util.BoType;
import edu.icet.demo.bo.BoFactory;
import edu.icet.demo.bo.custom.UserBo;
import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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


public class RegisterFormController implements Initializable {
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtPassword;

    public String uId;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

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
                uId = ("U001");
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
                   uId =(String.format("U%03d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void clearText(){
        txtName.setText(null);
        txtPassword.setText(null);
        txtEmail.setText(null);
    }


    public void btnRegisterOnAction(ActionEvent event) throws IOException {
        User user = new User(
                uId,
                txtName.getText(),
                txtPassword.getText(),
                txtEmail.getText()
        );
        boolean b = userBo.save(user);
        System.out.println(b);
        clearText();
        generateOrderId();
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Sign Up Successfull !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Sign Up UnSuccessfull !").show();
        }

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login-form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Form1");
        stage.show();
        Node n =(Node) event.getSource();
        Stage stage2 =(Stage) n.getScene().getWindow();
        stage2.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateOrderId();
    }
}
