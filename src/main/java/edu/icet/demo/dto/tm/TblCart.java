package edu.icet.demo.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TblCart{
    private String itemCode;
    private String name;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}
