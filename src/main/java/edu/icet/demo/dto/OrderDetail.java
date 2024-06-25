package edu.icet.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {
    private String detailId;
    private String orderId;
    private String itemCode;
    private String payment;
    private Integer qty;

}
