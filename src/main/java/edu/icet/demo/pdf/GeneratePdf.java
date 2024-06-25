package edu.icet.demo.pdf;


import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import edu.icet.demo.db.DBConnection;
import edu.icet.demo.dto.Product;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.util.Duration;


import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratePdf {
    public static String dated;
    public static String timein;
    public static String id;

    public static void generateOrderId() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM orderentity");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);

            }
            if (count == 0) {
                id=("INV001");
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
                    id=(String.format("INV%03d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static void loadDateAndTime() {
        Date date =new Date();
        SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(f.format(date));
        dated =(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime time = LocalTime.now();
            timein =(time.getHour()+" : "+ time.getMinute()+" : "+ time.getSecond());

        }),
                new KeyFrame(Duration.seconds(1))

        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public static void generate1(List<Product> productList) throws FileNotFoundException {
        String path ="invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        float threecol=190f;
        float twocol= 285f;
        float twocol150=twocol+150f;
        float twocolumnWidth[]={twocol150,twocol};
        float threeColumnWidth[]={threecol,threecol,threecol};
        float fullwidth[]={threecol*3};
        Paragraph onesp=new Paragraph("   ");



        loadDateAndTime();
        generateOrderId();

        Table table=new Table(twocolumnWidth);
        table.addCell(new Cell().add("Invoice").setFontSize(20f).setBold().setBorder(Border.NO_BORDER).setBold());
        Table nestedtabel=new Table(new float[]{twocol/2,twocol/2});
        nestedtabel.addCell(getHeaderTextCell("Invoice No :"));
        nestedtabel.addCell(getHeaderTextCellValue(""+id+""));
        nestedtabel.addCell(getHeaderTextCell("Invoice Date :"));
        nestedtabel.addCell(getHeaderTextCellValue(""+dated+""));

        table.addCell(new Cell().add(nestedtabel).setBorder(Border.NO_BORDER));

        Border gb= new SolidBorder(Color.GRAY,1f/2f);
        Table divider= new Table(fullwidth);
        divider.setBorder(gb);
        document.add(table);
        document.add(onesp);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);


        Table twoColTable=new Table(twocolumnWidth);
        twoColTable.addCell(getBillingCell("Billing Information"));
        twoColTable.addCell(getBillingCell("Shipping Information"));
        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2=new Table(twocolumnWidth);
        twoColTable2.addCell(getCellOfLeft("Company",true));
        twoColTable2.addCell(getCellOfLeft("Name",true));
        twoColTable2.addCell(getCellOfLeft("Clothify Store (PVT) Ltd. ",false));
        twoColTable2.addCell(getCellOfLeft("Mr. Sheshan Thilakshana ",false));
        document.add(twoColTable2);

        Table twoColTable3=new Table(twocolumnWidth);
        twoColTable3.addCell(getCellOfLeft("Name",true));
        twoColTable3.addCell(getCellOfLeft("Address",true));
        twoColTable3.addCell(getCellOfLeft("Clothify Store",false));
        twoColTable3.addCell(getCellOfLeft("No 10,\nMain Road,\nGalle.",false));
        document.add(twoColTable3);

        float oneColumnwidth[]={twocol150};

       Table oneColTable1=new Table(oneColumnwidth);
       oneColTable1.addCell(getCellOfLeft("Address",true));
       oneColTable1.addCell(getCellOfLeft("27/2,\nThotupola Road\nPoramba\nPanadura.",false));
       oneColTable1.addCell(getCellOfLeft("Email",true));
       oneColTable1.addCell(getCellOfLeft("clothify@sample.com",false));
       document.add(oneColTable1.setMarginBottom(10f));

       Table tableDivider2=new Table(fullwidth);
       Border dgb=new DashedBorder(Color.GRAY,0.5f);
       document.add(tableDivider2.setBorder(dgb));

       Paragraph produce=new Paragraph("Products");
       document.add(produce.setBold());
       Table threeColTable1=new Table(threeColumnWidth);
       threeColTable1.setBackgroundColor(Color.BLACK,0.7f);

       threeColTable1.addCell(new Cell().add("Description").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
       threeColTable1.addCell(new Cell().add("Quantity").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
       threeColTable1.addCell(new Cell().add("Price").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);
       document.add(threeColTable1);


        Table threeColTable2= new Table(threeColumnWidth);

        double totalSum=0f;
        for(Product product:productList){

            double total=product.getQuantity()*product.getPrice();
            totalSum+=total;
            threeColTable2.addCell(new Cell().add(product.getName()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
            threeColTable2.addCell(new Cell().add(String.valueOf(product.getQuantity())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColTable2.addCell(new Cell().add(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);

        }
       document.add(threeColTable2.setMarginBottom(10f));

        float onetwo[]={threecol+125f,threecol*2};
        Table threeColTable4=new Table(onetwo);
        threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(tableDivider2).setBorder(Border.NO_BORDER);
        document.add(threeColTable4);

        Table threeColTable3=new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f);
        threeColTable3.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable3.addCell(new Cell().add(String.valueOf(totalSum)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);
        document.add(threeColTable3);

        document.add(tableDivider2);
        document.add(new Paragraph("\n"));
        document.add(divider.setBorder(new SolidBorder(Color.GRAY,1)).setMarginBottom(15f));

        document.close();

    }

    static Cell getHeaderTextCell(String textValue){
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }
    static Cell getHeaderTextCellValue(String textValue){
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    static Cell getBillingCell(String textValue){
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    static Cell getCellOfLeft(String textValue,Boolean isBold){
        Cell myCell=new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ?myCell.setBold():myCell;
    }

}

