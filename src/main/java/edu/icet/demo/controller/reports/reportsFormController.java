package edu.icet.demo.controller.reports;

import edu.icet.demo.controller.login.LoginFormController;
import edu.icet.demo.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class reportsFormController implements Initializable {
    public ImageView img1;
    public ImageView img2;
    public ImageView img6;
    public ImageView img4;
    public ImageView img5;
    public ImageView img3;


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
    }

    public void btnOrdersOnAction(ActionEvent event) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/Order1.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnItemOnAction(ActionEvent event) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/report03.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnOrderDetailOnAction(ActionEvent event) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/Orderdetail.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSupplierOnAction(ActionEvent event) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/Supplier.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUserOnAction(ActionEvent event) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/User.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSettingsOnAction(ActionEvent event) {
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
