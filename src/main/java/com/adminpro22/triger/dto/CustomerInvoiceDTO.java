package com.adminpro22.triger.dto;

import com.adminpro22.triger.models.InvoiceItems;
import com.adminpro22.triger.models.Vendor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerInvoiceDTO extends AbstractDTO<Long> {
    private Long id;
    private String invoiceNumber;
    private String version;
    private String serie;
    private String storeNum;
    private String fileName;
    private String company;
    private String taxRegister;
    private Date date;
    private Double subtotal;
    private BigDecimal total;
    private List<InvoiceItems> invoiceItemsList;
    private Vendor vendor;

    public CustomerInvoiceDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSerie() {
        return this.serie;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getStoreNum() {
        return this.storeNum;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
    }

    public void setTaxRegister(String taxRegister) {
        this.taxRegister = taxRegister;
    }

    public String getTaxRegister() {
        return this.taxRegister;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getSubtotal() {
        return this.subtotal;
    }

    public void setTotal(java.math.BigDecimal total) {
        this.total = total;
    }

    public java.math.BigDecimal getTotal() {
        return this.total;
    }

    public void setInvoiceItemsList(java.util.List<com.adminpro22.triger.models.InvoiceItems> invoiceItemsList) {
        this.invoiceItemsList = invoiceItemsList;
    }

    public java.util.List<com.adminpro22.triger.models.InvoiceItems> getInvoiceItemsList() {
        return this.invoiceItemsList;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Vendor getVendor() {
        return this.vendor;
    }
}