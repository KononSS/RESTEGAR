package ru.edu.egar.mvcdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String lastName;
    private String firstName;
    private String phone;
    private Integer age;
}
