package com.adminpro22.triger.models.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class CommonInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false,length = 11)
    private Long   id;

    @Column(name = "INVOICE_NUMBER",nullable = false,length = 11)
    private String invoiceNumber;

    @Column(name = "VERSION",nullable = false,length = 5)
    private String version;

    @Column(name = "SERIE",nullable = false,length = 10)
    private String serie;

    @Column(name = "STORE_NUM",nullable = false,length = 2)
    private String storeNum;

    @Column(name = "FILE_NAME",nullable = false,length = 11)
    private String fileName;

    @Column(name = "COMPANY",nullable = false,length = 10)
    private String company;

    @Column(name = "TAX_REGISTER",nullable = false,length = 10)
    private String taxRegister;

    @Column(name = "DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column(name = "SUBTOTAL",nullable = false, length = 10)
    private Double subtotal;

    @Column(name = "TOTAL",nullable = false,length = 10)
    private BigDecimal total;
}
