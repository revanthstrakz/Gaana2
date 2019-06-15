package com.helpshift.views;

import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import com.helpshift.util.o;

public class b {
    public static <T extends OnActionExpandListener & MenuItemCompat.OnActionExpandListener> void a(MenuItem menuItem, T t) {
        if (com.helpshift.util.b.a(o.b(), 26)) {
            menuItem.setOnActionExpandListener(t);
        } else {
            MenuItemCompat.setOnActionExpandListener(menuItem, (MenuItemCompat.OnActionExpandListener) t);
        }
    }

    public static View a(MenuItem menuItem) {
        if (com.helpshift.util.b.a(o.b(), 26)) {
            return menuItem.getActionView();
        }
        return MenuItemCompat.getActionView(menuItem);
    }

    public static boolean b(MenuItem menuItem) {
        if (com.helpshift.util.b.a(o.b(), 26)) {
            return menuItem.isActionViewExpanded();
        }
        return MenuItemCompat.isActionViewExpanded(menuItem);
    }

    public static void c(MenuItem menuItem) {
        if (com.helpshift.util.b.a(o.b(), 26)) {
            menuItem.collapseActionView();
        } else {
            MenuItemCompat.collapseActionView(menuItem);
        }
    }

    public static void d(MenuItem menuItem) {
        if (com.helpshift.util.b.a(o.b(), 26)) {
            menuItem.expandActionView();
        } else {
            MenuItemCompat.expandActionView(menuItem);
        }
    }
}
