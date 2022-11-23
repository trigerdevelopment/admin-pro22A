package com.adminpro22.triger.models;


import com.adminpro22.triger.models.utils.CommonInvoice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER_INVOICES")
public class CustomerInvoice  {

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

    @Column(name = "TAX_REGISTER",nullable = false,length = 10)
    private String taxRegister;

    @Column(name = "DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column(name = "SUBTOTAL",nullable = false, length = 10)
    private Double subtotal;

    @Column(name = "TOTAL",nullable = false,length = 10)
    private BigDecimal total;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CUSTOMER_INVOICES_ID")
    private List<InvoiceItems> invoiceItemsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;

    @ManyToMany(mappedBy = "customerInvoices",cascade = CascadeType.ALL)
    private List<BankMovement> bankMovements;

}
