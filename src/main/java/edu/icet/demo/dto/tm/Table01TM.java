package edu.icet.demo.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Table01TM{
    private String id;
    private String name;
    private String company;
    private String email;
}
