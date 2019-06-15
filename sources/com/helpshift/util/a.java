package com.helpshift.util;

import android.app.Activity;

public class a {
    public static Boolean a(Activity activity) {
        return Boolean.valueOf((activity.getWindow().getAttributes().flags & 1024) == 1024);
    }
}
