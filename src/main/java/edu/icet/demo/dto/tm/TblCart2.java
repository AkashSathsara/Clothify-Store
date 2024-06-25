package edu.icet.demo.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TblCart2 {
    private String itemCode;
    private String payment;
    private Integer qty;
}
