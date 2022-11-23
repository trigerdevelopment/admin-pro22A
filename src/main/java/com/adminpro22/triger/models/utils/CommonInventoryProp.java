package com.adminpro22.triger.models.utils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class CommonInventoryProp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false,length = 11)
    private Long id;
    @Column(name = "DATE", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @Column(name = "CODE", nullable = false, length = 20)
    public String code;
    @Column(name = "PRODUCT",nullable = false)
    public String product;
    @Column(name = "UNITS", nullable = false, length = 5)
    public String units;
    @Column(name = "UNIT_COST", nullable = false, length = 5)
    public Double unitCost;
    @Column(name = "PRICE",nullable = false, length = 5)
    private Double price;
    @Column(name = "CATEGORY", nullable = false,length = 4)
    private String category;
    @Column(name = "SUB_CATEGORY", nullable = false,length = 4)
    private String subCategory;
    @Column(name = "QUANTITY",nullable = false,length =10 )
    public Double quantity;
    @Column(name = "TOTAL",nullable = false,length =10 )
    public Double total;
}
