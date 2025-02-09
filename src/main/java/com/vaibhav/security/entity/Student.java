package com.vaibhav.security.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students", schema = "public")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long marks;
}
