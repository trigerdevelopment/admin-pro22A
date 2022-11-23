package com.adminpro22.triger.models;

import com.adminpro22.triger.models.utils.CommonInventoryProp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "RAW_MATERIALS")
public class RawMaterial {

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
    @Column(name = "CATEGORY", nullable = false,length = 4)
    private String category;
    @Column(name = "SUB_CATEGORY", nullable = false,length = 4)
    private String subCategory;
    @Column(name = "QUANTITY",nullable = false,length =10 )
    public Double quantity;
    @Column(name = "TOTAL",nullable = false,length =10 )
    public Double total;
    @Column(name = "ACTIVE",nullable = false,length = 1)
    private Boolean active;
    @Column(name = "FOR_SALE",nullable = false,length = 1)
    private Boolean forSale;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    InventoryMovement inventoryMovement;

}
