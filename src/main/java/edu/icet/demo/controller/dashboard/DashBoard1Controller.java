package edu.icet.demo.controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoard1Controller implements Initializable {
    public ImageView img1;
    public ImageView img2;
    public ImageView img6;
    public ImageView img4;
    public ImageView img5;
    public ImageView img3;
    public ImageView imgDash;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img15.jpg");
        img1.setImage(image1);
        Image image2 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img16.jpg");
        img2.setImage(image2);
        Image image3 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img13.png");
        img3.setImage(image3);
        Image image4 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img11.png");
        img4.setImage(image4);
        Image image5 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img08.jpg");
        img5.setImage(image5);
        Image image6 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img12.jpg");
        img6.setImage(image6);
        Image image7 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img06.jpg");
        imgDash.setImage(image7);
    }

    public void placeOrderOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/place-order-form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Form1");
        stage.show();
        Node n =(Node) event.getSource();
        Stage stage2 =(Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void addItemOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/add-item-form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Form1");
        stage.show();
        Node n =(Node) event.getSource();
        Stage stage2 =(Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void settingsOnAction(ActionEvent event) {
    }

    public void viewOrderOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/vieworder-form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Form1");
        stage.show();
        Node n =(Node) event.getSource();
        Stage stage2 =(Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void addSupplierOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/add-supplier-form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Form1");
        stage.show();
        Node n =(Node) event.getSource();
        Stage stage2 =(Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void reportsOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/reports-form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Form1");
        stage.show();
        Node n =(Node) event.getSource();
        Stage stage2 =(Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void backOnAction(ActionEvent event) throws IOException {
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

}

