package edu.icet.demo.controller.order;

import edu.icet.demo.controller.login.LoginFormController;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.Util.BoType;
import edu.icet.demo.bo.BoFactory;
import edu.icet.demo.bo.custom.ItemBo;
import edu.icet.demo.bo.custom.OrderBo;
import edu.icet.demo.bo.custom.OrderDetailBo;
import edu.icet.demo.bo.custom.UserBo;
import edu.icet.demo.controller.item.ItemController;
import edu.icet.demo.controller.login.OtpFormController;
import edu.icet.demo.controller.user.UserController;
import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.*;
import edu.icet.demo.dto.tm.TblCart;
import edu.icet.demo.entity.ItemEntity;
import edu.icet.demo.entity.UserEntity;
import edu.icet.demo.pdf.GeneratePdf;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PlaceOrderFormController implements Initializable{
    public ComboBox cmbUserID;
    public ComboBox cmbItemID;
    public Label lblDate;
    public Label lblTime;
    public Label lblOrderId;
    public Label lblName;
    public Label lblItemName;
    public Label lblItemSize;
    public Label lblUnitPrice;
    public Label lblQty;
    public TableView tblCart;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Button btnAdd;
    public Button btnClear;
    public JFXTextField txtQty;
    public Button btnPlaceOrder;
    public Label lblTotal;
    public TableColumn colItemName;
    public TableColumn colItemCode;
    public JFXTextField txtPayment1;
    public JFXTextField txtOrderId;
    public String detaiId;
    public ImageView imgPlace;
    public JFXTextField txtEmail;

    private ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);
    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    private OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);
    private OrderDetailBo orderDetailBo = BoFactory.getInstance().getBo(BoType.ORDERDETAIL);

    private void setItemDataForLbl(String ItemCode) {
        ItemEntity item = itemBo.search(ItemCode);

        lblItemName.setText(item.getItemName());
        lblItemSize.setText(item.getSize());
        lblUnitPrice.setText(String.valueOf(item.getPrice()));
        lblQty.setText(String.valueOf(item.getQtyOnHand()));

    }

    private void setCustomerDataForLbl(String customerId) {
       UserEntity user = userBo.search(customerId);

        lblName.setText(user.getName());

    }

    private void loadItemCodes() {
        ObservableList<Item> allItem = ItemController.getInstance().getAllItem();

        ObservableList code = FXCollections.observableArrayList();

        allItem.forEach(item -> {
            code.add(item.getItemId());
        });
        System.out.println(code);
        cmbItemID.setItems(code);
    }

    private void loadCustomerIDs() {
        ObservableList<User> allCustomer = UserController.getInstance().getAllUser();

        ObservableList ids = FXCollections.observableArrayList();

        allCustomer.forEach(customer -> {
            ids.add(customer.getId());
        });
        System.out.println(ids);
        cmbUserID.setItems(ids);
    }

    private void loadDateAndTime() {
        Date date =new Date();
        SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(f.format(date));
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime time = LocalTime.now();
            lblTime.setText(time.getHour()+" : "+ time.getMinute()+" : "+ time.getSecond());

        }),
                new KeyFrame(Duration.seconds(1))

        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public  void generateOrderId() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM orderentity");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);

            }
            if (count == 0) {
                txtOrderId.setText("D001");
            }
            String lastUserId="";
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT orderId\n" +
                    "FROM orderentity\n" +
                    "ORDER BY orderId DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()){
                lastUserId = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastUserId);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    txtOrderId.setText(String.format("D%03d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public  void generateOrderId1() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM orderdetailentity ");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);

            }
            if (count == 0) {
                detaiId="O001";
            }
            String lastUserId="";
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT detailId\n" +
                    "FROM orderdetailentity\n" +
                    "ORDER BY detailId DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()){
                lastUserId = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastUserId);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    detaiId = (String.format("O%03d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    ObservableList<TblCart> cartList = FXCollections.observableArrayList();
    List<Product> productList = new ArrayList<>();

    public void btnAddOnAction(ActionEvent actionEvent) {
        String itemCode = (String) cmbItemID.getValue();
        String name = lblItemName.getText();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.valueOf(lblUnitPrice.getText());
        Double total = qty*unitPrice;

        TblCart cartTbl = new TblCart(itemCode,name,qty,unitPrice,total);
        System.out.println(cartTbl);

        int qtyStock = Integer.parseInt(lblQty.getText());
        if(qtyStock<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }
        cartList.add(cartTbl);
        tblCart.setItems(cartList);
        calcNetTotal();
    }
    public void calcNetTotal(){
        double ttl=0;
        for (TblCart cartObj : cartList){
            ttl+= cartObj.getTotal();
        }
        lblTotal.setText(String.valueOf(ttl)+"0");
    }


    public void btnClearOnAction(ActionEvent actionEvent) {

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

        try {
            String orderId = txtOrderId.getText();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = format.parse(lblDate.getText());
            String userId = cmbUserID.getValue().toString();

            String itemCode = null;
            Integer Qty1 = null;
            OrderDetail orderDetail = null;
            for (TblCart tblCart : cartList ){
                generateOrderId1();
                String oId = txtOrderId.getText();
                itemCode = tblCart.getItemCode();
                Qty1 = tblCart.getQty();
                String payment = txtPayment1.getText();
                orderDetail = new OrderDetail(detaiId,oId,itemCode,payment,Qty1);
                orderDetailBo.save(orderDetail);
            }
            for (TblCart tblCart : cartList ) {
                ItemEntity item = itemBo.search(itemCode);
                Item item1 = new Item(
                        item.getItemId(),
                        item.getItemName(),
                        item.getPrice(),
                        ((item.getQtyOnHand()) - Qty1),
                        item.getSize()
                );
                 itemBo.update(item1);
            }
            Order order = new Order(orderId,orderDate,userId);
            Boolean isOrderPlace = orderBo.save(order);
                    if(isOrderPlace){
                        new Alert(Alert.AlertType.CONFIRMATION,"Order Place !").show();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Order Not Place !").show();
                    }

            //tblCart.setItems(null);
            cartList.removeAll(cartList);
            generateOrderId();

        } catch (ParseException  e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadCustomerIDs();
        loadItemCodes();
        generateOrderId();

        Image image1 = new Image("C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\src\\main\\java\\edu\\icet\\demo\\img\\img15.jpg");
        imgPlace.setImage(image1);

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        cmbUserID.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> {
            System.out.println(newValue);
            setCustomerDataForLbl((String)newValue);
        });

        cmbItemID.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{
            System.out.println(newValue);
            setItemDataForLbl((String) newValue);
        });
    }



    public void btnbillOnAction(ActionEvent event) throws FileNotFoundException {
      // System.out.println(getText());

        for(TblCart tblCart : cartList){
            String itemCode1 = tblCart.getItemCode();
            Integer Qty = tblCart.getQty();
            double price= tblCart.getUnitPrice();
            productList.add(new Product(itemCode1,Qty,price));
        }
        GeneratePdf.generate1(productList);
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

        Message message = prepareMessage(session,myEmail,recipientEmail);
        if(message!=null){
            new Alert(Alert.AlertType.INFORMATION,"Send Email Successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Please Try Again").show();
        }
        Transport.send(message);
    }

    private Message prepareMessage(Session session,String myEmail,String recipientEmail){
        String path = "C:\\Users\\HP\\IdeaProjects\\Clothy-Store\\invoice.pdf";

        MimeMultipart mimeMultipart = new MimeMultipart();
        MimeBodyPart fileMime = new MimeBodyPart();

        try {
            File file = new File(path);
            fileMime.attachFile(file);

            mimeMultipart.addBodyPart(fileMime);

        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }

//===================================================================================
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO,new InternetAddress[]{
                    new InternetAddress(recipientEmail)
            });

            message.setSubject("Messages");
            message.setContent(mimeMultipart);
            return message;

        }catch (Exception e){
            Logger.getLogger(OtpFormController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }

    public void btnSendOnAction(ActionEvent event) throws MessagingException {
        String recipientEmail = txtEmail.getText();
        sendEmail(recipientEmail);
    }
}
