package org.example.dane;

public class Produkt {
    private String name;
    private String productCode;
    private String quantity;
    private String unitOfMeasure;

    public String getName()
    {
        return name;
    }

    public void setName(String Name)
    {
        this.name = name;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure()
    {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure)
    {
        this.unitOfMeasure = unitOfMeasure;
    }
}
