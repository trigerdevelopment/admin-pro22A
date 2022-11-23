package com.adminpro22.triger.dto;

import com.adminpro22.triger.models.CustomerInvoice;
import com.adminpro22.triger.models.SupplierInvoice;

import java.util.Date;
import java.util.List;

public class BankMovementDTO extends AbstractDTO<Long> {
    private Long id;
    private String accountNumber;
    private Date operationDate;
    private Date date;
    private String reference;
    private String description;
    private Double Deposit;
    private Double withdrawal;
    private String movement;
    private String details;
    private List<SupplierInvoice> supplierInvoices;
    private List<CustomerInvoice> customerInvoices;

    public BankMovementDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setOperationDate(java.util.Date operationDate) {
        this.operationDate = operationDate;
    }

    public java.util.Date getOperationDate() {
        return this.operationDate;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return this.reference;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDeposit(Double Deposit) {
        this.Deposit = Deposit;
    }

    public Double getDeposit() {
        return this.Deposit;
    }

    public void setWithdrawal(Double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Double getWithdrawal() {
        return this.withdrawal;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getMovement() {
        return this.movement;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return this.details;
    }

    public void setSupplierInvoices(java.util.List<com.adminpro22.triger.models.SupplierInvoice> supplierInvoices) {
        this.supplierInvoices = supplierInvoices;
    }

    public java.util.List<com.adminpro22.triger.models.SupplierInvoice> getSupplierInvoices() {
        return this.supplierInvoices;
    }

    public void setCustomerInvoices(java.util.List<com.adminpro22.triger.models.CustomerInvoice> customerInvoices) {
        this.customerInvoices = customerInvoices;
    }

    public java.util.List<com.adminpro22.triger.models.CustomerInvoice> getCustomerInvoices() {
        return this.customerInvoices;
    }
}