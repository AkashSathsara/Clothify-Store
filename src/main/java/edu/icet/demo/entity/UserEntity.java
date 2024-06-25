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
public class UserEntity {
    @Id
private String id;
private String name;
private String company;
private String email;
}
