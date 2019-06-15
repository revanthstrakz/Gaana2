package com.payu.custombrowser.util;

import android.text.TextUtils;
import java.util.HashMap;

public class SnoozeConfigMap extends HashMap {
    public Object get(Object obj) {
        return super.get(obj);
    }

    public int[] getPercentageAndTimeout(String str) {
        Object str2;
        if (get(str2) == null) {
            str2 = CBConstant.DEFAULT_PAYMENT_URLS;
        }
        String[] split = get(str2) == null ? new String[0] : get(str2).toString().split("\\|\\|");
        int[] iArr = new int[3];
        int i = 1;
        int parseInt = (split.length <= 0 || split[0].length() <= 0 || !TextUtils.isDigitsOnly(split[0])) ? 1 : Integer.parseInt(split[0]);
        iArr[0] = parseInt;
        int parseInt2 = (split.length <= 1 || split[1].length() <= 0 || !TextUtils.isDigitsOnly(split[1])) ? 1800000 : Integer.parseInt(split[1]);
        iArr[1] = parseInt2;
        if (split.length > 2 && split[2].length() > 0 && TextUtils.isDigitsOnly(split[2])) {
            i = Integer.parseInt(split[2]);
        }
        iArr[2] = i;
        return iArr;
    }
}
