package com.restaurant_vote.to;

import com.restaurant_vote.util.MenuUtil;
import com.restaurant_vote.model.MenuItem;

public class MenuItemTo extends BaseNamedTo{
    private Double price;

    public MenuItemTo() {
    }

    public MenuItemTo(MenuItem menuItem) {

        super(menuItem.getId(),menuItem.getName());
        this.price = MenuUtil.convertToMoney(menuItem.getPrice());
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
