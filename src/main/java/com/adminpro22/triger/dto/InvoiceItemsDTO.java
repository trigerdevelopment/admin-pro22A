package com.adminpro22.triger.dto;

import com.adminpro22.triger.models.InventoryMovement;

public class InvoiceItemsDTO extends AbstractDTO<Long> {
    private InventoryMovement inventoryMovement;

    public InvoiceItemsDTO() {
    }

    public void setInventoryMovement(InventoryMovement inventoryMovement) {
        this.inventoryMovement = inventoryMovement;
    }

    public InventoryMovement getInventoryMovement() {
        return this.inventoryMovement;
    }
}