package com.comscore.utils;

import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;

public class API13 {
    public static Point getDisplaySize(Display display) {
        Point point = new Point();
        if (VERSION.SDK_INT >= 13) {
            display.getSize(point);
        }
        return point;
    }
}
