package edu.icet.demo.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Table02TM{
    private String itemId;
    private String itemName;
    private String size;
    private Double price;
    private Integer qtyOnHand;

}
