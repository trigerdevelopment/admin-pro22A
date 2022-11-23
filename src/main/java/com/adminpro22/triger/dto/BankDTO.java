package com.adminpro22.triger.dto;

import com.adminpro22.triger.models.BankMovement;

import java.util.Date;
import java.util.List;

public class BankDTO extends AbstractDTO<Long> {
    private Long id;
    private String name;
    private String account;
    private Date initialDate;
    private String balance;
    private List<BankMovement> bankMovements;

    public BankDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return this.account;
    }

    public void setInitialDate(java.util.Date initialDate) {
        this.initialDate = initialDate;
    }

    public java.util.Date getInitialDate() {
        return this.initialDate;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBankMovements(java.util.List<com.adminpro22.triger.models.BankMovement> bankMovements) {
        this.bankMovements = bankMovements;
    }

    public java.util.List<com.adminpro22.triger.models.BankMovement> getBankMovements() {
        return this.bankMovements;
    }
}