package edu.icet.demo.controller.dashboard;

import edu.icet.demo.controller.item.ItemController;
import edu.icet.demo.controller.order.OrderController;
import edu.icet.demo.controller.supplier.SupplierController;
import edu.icet.demo.controller.user.UserController;
import edu.icet.demo.dto.Item;
import edu.icet.demo.dto.Order;
import edu.icet.demo.dto.Supplier;
import edu.icet.demo.dto.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;


public class DashBoard2Controller implements Initializable {

    public Label Menu;
    public Label MenuBack;
    public AnchorPane slider;
    public ImageView img06;
    public ImageView Img01;
    public ImageView img02;

    public ImageView img07;
    public ImageView img08;
    public ImageView img09;
    public ImageView img12;
    public ImageView img10;
    public ImageView img11;
    public ImageView img03;
    public ImageView img04;
    public ImageView img05;
    public ImageView img13;
    public Label lblDate;
    public Label lblTime;
    public ImageView img;
    public Label lblnew;
    public Label lblOrders;
    public Label lblUsers;
    public Label lblItems;
    public Label lblSuppliers;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Integer x=0;
    public static Integer y=0;
    public static Integer z=0;
    public static Integer v=0;

    private void loadDateAndTime() {
        Date date =new Date();
        SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(f.format(date));
        lblnew.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime time = LocalTime.now();
            lblTime.setText(time.getHour()+" : "+ time.getMinute()+" : "+ time.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void resetLabel(){
        x=0;
        y=0;
        z=0;
        v=0;
    }

    private void loadLabels(){
        ObservableList<Item> allItems = ItemController.getInstance().getAllItem();
        allItems.forEach(item -> {
            x++;
        });
        ObservableList<User> allUsers = UserController.getInstance().getAllUser();
        allUsers.forEach(item -> {
            y++;
        });
        ObservableList<Supplier> allSupplier = SupplierController.getInstance().getAllSupplier();
        allSupplier.forEach(item -> {
            z++;
        });
        ObservableList<Order> allOrders = OrderController.getInstance().getAllOrders();
        allOrders.forEach(item -> {
            v++;
        });
        lblSuppliers.setText(""+z);
        lblUsers.setText(""+y);
        lblOrders.setText(""+v);
        lblItems.setText(""+x);
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

    public void itemsOnAction(ActionEvent event) throws IOException {
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

    public void suppliersOnAction(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetLabel();
        loadLabels();
        loadDateAndTime();

        Image image1 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img15.jpg");
        Img01.setImage(image1);
        Image image2 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img16.jpg");
        img02.setImage(image2);
        Image image3 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img08.jpg");
        img03.setImage(image3);
        Image image4 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img11.png");
        img04.setImage(image4);
        Image image5 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img12.jpg");
        img05.setImage(image5);
        Image image6 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img15.jpg");
        img06.setImage(image6);
        Image image7 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img16.jpg");
        img07.setImage(image7);
        Image image8 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img08.jpg");
        img08.setImage(image8);
        Image image9 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img11.png");
        img09.setImage(image9);
        Image image10 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img14.jpg");
        img10.setImage(image10);
        Image image11 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img06.jpg");
        img11.setImage(image11);
        Image image12 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img14.jpg");
        img12.setImage(image12);
        Image image13 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img13.png");
        img13.setImage(image13);
        Image image14 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\dashboard.png");
        img.setImage(image14);

//        slider.setTranslateX(-176);
//        Menu.setOnMouseClicked(event ->{
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(slider);
//            slide.setToX(0);
//            slide.play();
//            slider.setTranslateX(-176);
//
//            slide.setOnFinished((ActionEvent e)->{
//                Menu.setVisible(false);
//                MenuBack.setVisible(true);
//            });
//
//        });
//
//        MenuBack.setOnMouseClicked(event ->{
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(slider);
//            slide.setToX(-176);
//            slide.play();
//            slider.setTranslateX(0);
//
//            slide.setOnFinished((ActionEvent e)->{
//                Menu.setVisible(true);
//                MenuBack.setVisible(false);
//            });
//
//        });

    }

    public void UsersOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/add-user-form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Form1");
        stage.show();
        Node n =(Node) event.getSource();
        Stage stage2 =(Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void viewOrdersOnAction(ActionEvent event) throws IOException {
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

    public void btnBackOnAction(ActionEvent event) throws IOException {
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
