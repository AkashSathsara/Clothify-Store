package edu.icet.demo.controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.Util.BoType;
import edu.icet.demo.bo.BoFactory;
import edu.icet.demo.bo.custom.UserBo;
import edu.icet.demo.controller.user.UserController;
import edu.icet.demo.dto.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OtpFormController implements Initializable {
    public JFXPasswordField txtOtp;
    public String otp;
    public JFXTextField txtemail;
    public Label lblSent;
    public JFXTextField txtP;
    public JFXTextField txtR;
    public JFXButton btnConfirm;
    public User user1;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);


    public void Random(){
        Random random= new Random();
        otp=(""+random.nextInt(10000)+1);
    }

    public void btnSubmitOnaction(ActionEvent event) throws IOException {
         if((txtOtp.getText()).equalsIgnoreCase(otp)){
             txtR.setVisible(true);
             txtP.setVisible(true);
             btnConfirm.setVisible(true);
         }
    }

    public void btnSendOnAction(ActionEvent event) throws MessagingException {
       String recipientEmail = txtemail.getText();
       sendEmail(recipientEmail);
       lblSent.setText("Your OTP will be sent to your Email ID ");
    }

    private void sendEmail(String recipientEmail) throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myEmail = "akashsathsara10@gmail.com";
        String password = "jiqd ncqm jncm rbxh";

        Session session= Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });
        Message message = prepareMessage(session,myEmail,recipientEmail,otp);
        if(message!=null){
            new Alert(Alert.AlertType.INFORMATION,"Send Email Successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Please Try Again").show();
        }
        Transport.send(message);
    }

    private Message prepareMessage(Session session,String myEmail,String recipientEmail,String msg){
       try {
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(myEmail));
           message.setRecipients(Message.RecipientType.TO,new InternetAddress[]{
                   new InternetAddress(recipientEmail)
           });

           message.setSubject("Messages");
           message.setText(msg);
           return message;

       }catch (Exception e){
           Logger.getLogger(OtpFormController.class.getName()).log(Level.SEVERE,null,e);
       }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random();
        txtP.setVisible(false);
        txtR.setVisible(false);
        btnConfirm.setVisible(false);
    }


    public void btnEnterOnAction(ActionEvent event) throws IOException {

        ObservableList<User> allUsers = UserController.getInstance().getAllUser();
        allUsers.forEach(user -> {
            if((txtemail.getText()).equalsIgnoreCase(user.getCompany())){
                user1=user;
            }
        });
            User user2 = new User(
                    user1.getId(),
                    user1.getEmail(),
                    txtP.getText(),
                    user1.getCompany()
            );

            boolean b = userBo.update(user2);
            System.out.println(b);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"New Password Updated !").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"New Password Not Updated !").show();
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
}
