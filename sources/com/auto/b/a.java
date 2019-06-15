package com.auto.b;

import android.os.Bundle;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.utilities.Util;

public class a {
    public static void a(Bundle bundle, boolean z, boolean z2, boolean z3) {
        if (z) {
            bundle.putBoolean("com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_QUEUE", true);
        } else {
            bundle.remove("com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_QUEUE");
        }
        if (z3) {
            bundle.putBoolean("com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", true);
        } else {
            bundle.remove("com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS");
        }
        if (z2) {
            bundle.putBoolean("com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true);
        } else {
            bundle.remove("com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT");
        }
    }

    public static BusinessObject a(Item item) {
        return Util.g(item);
    }

    public static BusinessObject b(Item item) {
        return Util.d(item);
    }

    public static BusinessObject c(Item item) {
        return Util.c(item);
    }

    public static BusinessObject d(Item item) {
        return Util.b(item);
    }
}
