package com.adminpro22.triger.dto;

import com.adminpro22.triger.models.InventoryMovement;

public class RawMaterialDTO extends AbstractDTO<Double> {
    private Double taxes;
    private Boolean active;
    private Boolean forSale;
    private InventoryMovement inventoryMovement;

    public RawMaterialDTO() {
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getTaxes() {
        return this.taxes;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }

    public Boolean getForSale() {
        return this.forSale;
    }

    public void setInventoryMovement(InventoryMovement inventoryMovement) {
        this.inventoryMovement = inventoryMovement;
    }

    public InventoryMovement getInventoryMovement() {
        return this.inventoryMovement;
    }
}