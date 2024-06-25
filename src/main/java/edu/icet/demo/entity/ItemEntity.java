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
public class ItemEntity {
        @Id
        private String itemId;
        private String itemName;
        private String size;
        private Double price;
        private Integer qtyOnHand;
    }

