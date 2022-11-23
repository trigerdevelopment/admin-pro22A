package com.adminpro22.triger.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "BANK_MOVEMENT")
public class BankMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 11)
    private Long id;

    @Column(name = "ACCOUNT_NUMBER",nullable = false, length = 11)
    private String accountNumber;

    @Column(name = "OPERATION_DATE",nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date operationDate;

    @Column(name = "DATE",nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column(name = "REFERENCE", nullable = false, length = 5)
    private String reference;

    @Column(name = "DESCRIPTION",nullable = false)
    private String description;

    @Column(name = "DEPOSIT",nullable = false,length = 8)
    private Double Deposit;

    @Column(name = "WITHDRAWAL",length = 8)
    private Double withdrawal;

    @Column(name = "MOVEMENT", nullable = false,length = 8)
    private String movement;

    @Column(name = "DETAILS",nullable = false)
    private String details;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "BANK_SUPPLIER",
            joinColumns = @JoinColumn(name = "BANK_MOVEMENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SUPPLIER_INVOICE_ID", referencedColumnName = "ID"))
    @JsonManagedReference
    private List<SupplierInvoice> supplierInvoices = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "BANK_CUSTOMER",
            joinColumns = {@JoinColumn(name = "BANK_MOVEMENT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = @JoinColumn(name = "CUSTOMER_INVOICE_ID", referencedColumnName = "ID"))
    @JsonManagedReference
    private List<CustomerInvoice> customerInvoices = new ArrayList<>();
}
