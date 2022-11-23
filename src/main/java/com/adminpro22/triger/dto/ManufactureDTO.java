package com.adminpro22.triger.dto;

import com.adminpro22.triger.models.InventoryMovement;
import com.adminpro22.triger.models.RawMaterial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ManufactureDTO extends AbstractDTO<Long> {
    private Long id;
    private String number;
    private String fileName;
    private String codeProduct;
    private String product;
    private Date date;
    private BigDecimal total;
    private List<RawMaterial> rawMaterials;
    private InventoryMovement inventoryMovement;

    public ManufactureDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getCodeProduct() {
        return this.codeProduct;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return this.product;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setTotal(java.math.BigDecimal total) {
        this.total = total;
    }

    public java.math.BigDecimal getTotal() {
        return this.total;
    }

    public void setRawMaterials(java.util.List<com.adminpro22.triger.models.RawMaterial> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

    public java.util.List<com.adminpro22.triger.models.RawMaterial> getRawMaterials() {
        return this.rawMaterials;
    }

    public void setInventoryMovement(InventoryMovement inventoryMovement) {
        this.inventoryMovement = inventoryMovement;
    }

    public InventoryMovement getInventoryMovement() {
        return this.inventoryMovement;
    }
}