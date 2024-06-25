package edu.icet.demo.controller.login;

import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.controller.item.ItemController;
import edu.icet.demo.controller.user.UserController;
import edu.icet.demo.dto.Item;
import edu.icet.demo.dto.User;
import edu.icet.demo.dto.tm.Table02TM;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public Label lblInvalid;
    public ImageView ImgView01;
    public ImageView ImgView02;
    public String userName = "user";
    public String password = "1111";

    public void btnLoginOnAction(ActionEvent event) throws IOException {
    if((userName.equalsIgnoreCase("user")) && password.equalsIgnoreCase("1111")){
        if ((txtUserName.getText()).equalsIgnoreCase(userName) && (txtPassword.getText()).equalsIgnoreCase(password)) {
            Parent rootNode = null;
            try {
                rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard2-form.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("DashBoard Form");
            stage.show();
            Node n = (Node) event.getSource();
            Stage stage2 = (Stage) n.getScene().getWindow();
            stage2.close();

           }else if (true){
            ObservableList<User> allUsers = UserController.getInstance().getAllUser();
            allUsers.forEach(user -> {
                if(((txtUserName.getText()).equalsIgnoreCase(user.getEmail())) & ((txtPassword.getText()).equalsIgnoreCase(user.getName()) )){
                    userName = (txtUserName.getText());
                    password = (txtPassword.getText());

                    Parent rootNode = null;
                    try {
                        rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard1-form.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(rootNode);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("DashBoard Form");
                    stage.show();

                }else {
                    lblInvalid.setText("Invali Username or Password !");
                }
            });
          }
        }
     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img03.png");
        ImgView01.setImage(image);
        Image image1 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img05.png");
        ImgView02.setImage(image1);
    }

    public void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/register-form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Register Form");
        stage.show();
        Node n =(Node) event.getSource();
        Stage stage2 =(Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void btnForgotOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/otp-form.fxml"));
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
