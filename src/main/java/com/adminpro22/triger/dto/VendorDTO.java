package com.adminpro22.triger.dto;

public class VendorDTO extends AbstractDTO<Long> {
    private Long id;
    private String name;
    private String lastName;
    private int vendorCode;
    private String email;
    private String phone;
    private String Address;

    public VendorDTO() {
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setVendorCode(int vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getVendorCode() {
        return this.vendorCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAddress() {
        return this.Address;
    }
}