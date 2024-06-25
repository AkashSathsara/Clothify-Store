package edu.icet.demo.entity;

import edu.icet.demo.dto.OrderDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class OrderEntity{
    @Id
    private String orderId;
    private Date orderDate;
    private String userId;
}
