package com.adminpro22.triger.dto;

public class ProductDTO extends AbstractDTO<Long> {
    private Boolean active;
    private Boolean forSale;

    public ProductDTO() {
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
}