package com.gaana.FastScrollRecyclerView;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build.VERSION;

public class Utils {
    public static int toPixels(Resources resources, float f) {
        return (int) (f * resources.getDisplayMetrics().density);
    }

    @TargetApi(17)
    public static boolean isRtl(Resources resources) {
        if (VERSION.SDK_INT < 17 || resources.getConfiguration().getLayoutDirection() != 1) {
            return false;
        }
        return true;
    }
}
