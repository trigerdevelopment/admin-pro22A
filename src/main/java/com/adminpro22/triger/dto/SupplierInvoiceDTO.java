package com.adminpro22.triger.dto;

import com.adminpro22.triger.models.SupplierInvoiceItems;

import java.util.List;

public class SupplierInvoiceDTO extends AbstractDTO<Double> {
    private Double taxes;
    private List<SupplierInvoiceItems> supplierInvoiceItems;

    public SupplierInvoiceDTO() {
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getTaxes() {
        return this.taxes;
    }

    public void setSupplierInvoiceItems(java.util.List<com.adminpro22.triger.models.SupplierInvoiceItems> supplierInvoiceItems) {
        this.supplierInvoiceItems = supplierInvoiceItems;
    }

    public java.util.List<com.adminpro22.triger.models.SupplierInvoiceItems> getSupplierInvoiceItems() {
        return this.supplierInvoiceItems;
    }
}