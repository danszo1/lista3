package org.example.dane;

import java.util.List;

public class ŻądanieZamówienia {
    private Klient customerInfo;
    private List<Produkt> items;
    private String orderNumber;

    public Klient getCustomerInfo()
    {
        return customerInfo;
    }

    public void setCustomerInfo(Klient customerInfo)
    {
        this.customerInfo = customerInfo;
    }

    public List<Produkt> getItems()
    {
        return items;
    }

    public void setProducts(List<Produkt> items)
    {
        this.items = items;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }
}
