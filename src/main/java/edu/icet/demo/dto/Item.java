package edu.icet.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String itemId;
    private String itemName;
    private Double price;
    private Integer qtyOnHand;
    private String size;

}
