package edu.icet.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class OrderDetailEntity{
@Id
    private String detailId;
    private String orderId;
    private String itemCode;
    private String payment;
    private Integer qty;
}
