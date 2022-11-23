package com.adminpro22.triger.dto;

import com.adminpro22.triger.models.CustomerInvoice;
import com.adminpro22.triger.models.Vendor;

import java.util.List;

public class CustomerDTO extends AbstractDTO<Long> {
    private Long id;
    private String company;
    private String rfc;
    private String street;
    private String number;
    private String number2;
    private String postalCode;
    private String phone;
    private String district;
    private String delegacion;
    private String municipio;
    private String state;
    private String country;
    private String paymentDays;
    private Boolean isActive;
    private Vendor vendor;
    private List<CustomerInvoice> customerInvoices;

    public CustomerDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return this.street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getNumber2() {
        return this.number2;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getDelegacion() {
        return this.delegacion;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setPaymentDays(String paymentDays) {
        this.paymentDays = paymentDays;
    }

    public String getPaymentDays() {
        return this.paymentDays;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Vendor getVendor() {
        return this.vendor;
    }

    public void setCustomerInvoices(java.util.List<com.adminpro22.triger.models.CustomerInvoice> customerInvoices) {
        this.customerInvoices = customerInvoices;
    }

    public java.util.List<com.adminpro22.triger.models.CustomerInvoice> getCustomerInvoices() {
        return this.customerInvoices;
    }
}