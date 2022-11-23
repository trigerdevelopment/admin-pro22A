package com.adminpro22.triger.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false)
    private Long   id;

    @Column(name = "COMPANY",nullable = false,length = 100,unique = true)
    private String company;
    @Column(name = "RFC",nullable = false,length = 12,unique = true)
    private String rfc;
    @Column(name = "STREET",length = 20)
    private String street;
    @Column(name = "NUMBER",length = 5)
    private String number;

    @Column(name = "NUMBER2",length = 5)
    private String number2;
    @Column(name = "POSTAL_CODE",length = 5)
    private String postalCode;
    @Column(name = "PHONE",length = 5,unique = true)
    private String phone;

    @Column(name = "DISTRICT",length = 20)
    private String district;
    @Column(name = "DELEGACION",length = 20)
    private String delegacion;

    @Column(name = "MUNICIPIO",length = 15)
    private String municipio;
    @Column(name = "STATE",length = 15)
    private String state;
    @Column(name = "COUNTRY",length = 15)
    private String country;

    @Column(name = "PAYMENT_DAYS",length = 10)
    private String paymentDays;

    @Column(name = "IS_ACTIVE",nullable = false,length = 5,columnDefinition = "true")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CUSTOMER_INVOICES_ID")
    private List<CustomerInvoice> customerInvoices = new ArrayList<>();

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", rfc='" + rfc + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", number2='" + number2 + '\'' +
                ", code='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", district='" + district + '\'' +
                ", delegacion='" + delegacion + '\'' +
                ", municipio='" + municipio + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", paymentDays='" + paymentDays + '\'' +
//                ", vendor=" + vendor +
                '}';
    }
}
