package com.nina.invoicesstripeapp.model;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;

}
