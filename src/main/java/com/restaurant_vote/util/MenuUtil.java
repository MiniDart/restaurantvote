package com.restaurant_vote.util;

import com.restaurant_vote.model.MenuItem;
import com.restaurant_vote.to.MenuItemTo;

public class MenuUtil {
    private static final Integer CONVERT_COEFFICIENT=100;

    public static Double convertToMoney(Integer sum){
        return (double)sum/CONVERT_COEFFICIENT;
    }

    public static Integer convertToInnerRepresentation(Double sum){
        if (sum==null) return null;
        return new Double(sum*CONVERT_COEFFICIENT).intValue();
    }

    public static MenuItem menuItemTOIntoMenuItem(MenuItemTo menuItemTo){
        MenuItem menuItem=new MenuItem();
        if (menuItemTo.getId()!=null) menuItem.setId(menuItemTo.getId());
        menuItem.setName(menuItemTo.getName());
        menuItem.setPrice(convertToInnerRepresentation(menuItemTo.getPrice()));
        return menuItem;
    }
}
