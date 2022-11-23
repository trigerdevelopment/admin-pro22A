package com.adminpro22.triger.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "BANKS")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false,length = 11)
    private Long   id;

    @Column(name = "NAME",nullable = false,length = 25)
    private String name;

    @Column(name = "ACCOUNT",nullable = false,length = 20)
    private String account;

    @Column(name = "INITIAL_DATE",nullable = false)
    private Date initialDate;

    @Column(name = "BALANCE",nullable = false,length = 10)
    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "BANK_ID")
    private List<BankMovement> bankMovements = new ArrayList<>();


}
