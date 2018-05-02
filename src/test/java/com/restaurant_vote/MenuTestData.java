package com.restaurant_vote;

import com.restaurant_vote.model.MenuItem;

import java.util.Arrays;
import java.util.List;

import static com.restaurant_vote.RestaurantTestData.*;

public class MenuTestData {
    public static final MenuItem MENU_ITEM1=new MenuItem(100006,"Potato",12050,RESTAURANT1);
    public static final MenuItem MENU_ITEM2=new MenuItem(100007,"Soup",20000,RESTAURANT1);
    public static final MenuItem MENU_ITEM3=new MenuItem(100008,"Fry chicken",35000,RESTAURANT1);
    public static final MenuItem MENU_ITEM4=new MenuItem(100009,"Hamburger",10070,RESTAURANT2);
    public static final MenuItem MENU_ITEM5=new MenuItem(100010,"Potato",13000,RESTAURANT2);
    public static final MenuItem MENU_ITEM6=new MenuItem(100011,"Boiled eggs",15000,RESTAURANT2);
    public static final MenuItem MENU_ITEM7=new MenuItem(100012,"Beef stew",40000,RESTAURANT3);
    public static final MenuItem MENU_ITEM8=new MenuItem(100013,"Baked turkey",80000,RESTAURANT3);
    public static final MenuItem MENU_ITEM9=new MenuItem(100014,"Scrambled eggs",12000,RESTAURANT3);

    public static List<MenuItem> getMenuAsList(){
        return Arrays.asList(MENU_ITEM1,MENU_ITEM2,MENU_ITEM3,MENU_ITEM4,MENU_ITEM5,MENU_ITEM6,MENU_ITEM7,MENU_ITEM8,MENU_ITEM9);
    }

}
