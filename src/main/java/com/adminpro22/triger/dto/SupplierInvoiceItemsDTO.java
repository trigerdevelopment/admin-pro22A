package com.adminpro22.triger.dto;

import java.util.Date;

public class SupplierInvoiceItemsDTO extends AbstractDTO<Long> {
    private Long id;
    private Date date;
    private String code;
    private String product;
    private String units;
    private Double unitCost;
    private Double price;
    private String category;
    private String subCategory;
    private Double quantity;
    private Double total;

    public SupplierInvoiceItemsDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return this.product;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getUnits() {
        return this.units;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getUnitCost() {
        return this.unitCost;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubCategory() {
        return this.subCategory;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return this.total;
    }
}