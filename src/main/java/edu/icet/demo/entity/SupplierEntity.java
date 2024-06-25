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
public class SupplierEntity{
    @Id
    private String supplierId;
    private String supplierName;
    private String company;
    private String email;
}
