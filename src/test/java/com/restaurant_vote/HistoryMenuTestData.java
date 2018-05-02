package com.restaurant_vote;

import com.restaurant_vote.model.HistoryMenuItem;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.restaurant_vote.MenuTestData.*;

public class HistoryMenuTestData {

    public static final HistoryMenuItem HISTORY_MENU_ITEM1;
    public static final HistoryMenuItem HISTORY_MENU_ITEM2;
    public static final HistoryMenuItem HISTORY_MENU_ITEM3;
    public static final HistoryMenuItem HISTORY_MENU_ITEM4;
    public static final HistoryMenuItem HISTORY_MENU_ITEM5;
    public static final HistoryMenuItem HISTORY_MENU_ITEM6;
    public static final HistoryMenuItem HISTORY_MENU_ITEM7;
    public static final HistoryMenuItem HISTORY_MENU_ITEM8;
    public static final HistoryMenuItem HISTORY_MENU_ITEM9;
    static {
        HISTORY_MENU_ITEM1=new HistoryMenuItem(MENU_ITEM1);
        HISTORY_MENU_ITEM1.setId(100017);
        HISTORY_MENU_ITEM1.setDate(LocalDate.of(2016,3,7));
        HISTORY_MENU_ITEM2=new HistoryMenuItem(MENU_ITEM2);
        HISTORY_MENU_ITEM2.setId(100018);
        HISTORY_MENU_ITEM2.setDate(LocalDate.of(2016,3,8));
        HISTORY_MENU_ITEM3=new HistoryMenuItem(MENU_ITEM3);
        HISTORY_MENU_ITEM3.setId(100019);
        HISTORY_MENU_ITEM3.setDate(LocalDate.of(2016,3,9));

        HISTORY_MENU_ITEM4=new HistoryMenuItem(MENU_ITEM4);
        HISTORY_MENU_ITEM4.setId(100020);
        HISTORY_MENU_ITEM4.setDate(LocalDate.of(2016,3,7));
        HISTORY_MENU_ITEM5=new HistoryMenuItem(MENU_ITEM5);
        HISTORY_MENU_ITEM5.setId(100021);
        HISTORY_MENU_ITEM5.setDate(LocalDate.of(2016,3,8));
        HISTORY_MENU_ITEM6=new HistoryMenuItem(MENU_ITEM6);
        HISTORY_MENU_ITEM6.setId(100022);
        HISTORY_MENU_ITEM6.setDate(LocalDate.of(2016,3,9));

        HISTORY_MENU_ITEM7=new HistoryMenuItem(MENU_ITEM7);
        HISTORY_MENU_ITEM7.setId(100023);
        HISTORY_MENU_ITEM7.setDate(LocalDate.of(2016,3,7));
        HISTORY_MENU_ITEM8=new HistoryMenuItem(MENU_ITEM8);
        HISTORY_MENU_ITEM8.setId(100024);
        HISTORY_MENU_ITEM8.setDate(LocalDate.of(2016,3,8));
        HISTORY_MENU_ITEM9=new HistoryMenuItem(MENU_ITEM9);
        HISTORY_MENU_ITEM9.setId(100025);
        HISTORY_MENU_ITEM9.setDate(LocalDate.of(2016,3,9));

    }

    public static List<HistoryMenuItem> getHistoryMenuAsList(){
        return Arrays.asList(HISTORY_MENU_ITEM1,HISTORY_MENU_ITEM2,HISTORY_MENU_ITEM3,HISTORY_MENU_ITEM4,HISTORY_MENU_ITEM5,HISTORY_MENU_ITEM6,HISTORY_MENU_ITEM7,HISTORY_MENU_ITEM8,HISTORY_MENU_ITEM9);
    }
}
