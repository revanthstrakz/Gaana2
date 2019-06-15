package com.utilities;

import android.os.Build;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;

public class g {
    private static boolean a = false;
    private static boolean b = false;

    public static boolean a() {
        Constants.o = Build.MANUFACTURER;
        if (Constants.o.equalsIgnoreCase("YU") || Constants.o.equalsIgnoreCase("MICROMAX")) {
            b = true;
        }
        boolean z = Util.q(GaanaApplication.getContext()) && b;
        a = z;
        if (a) {
            Constants.bU = "GaanaMmxApp";
            Constants.l = true;
        }
        return a;
    }

    public static boolean b() {
        return a;
    }

    public static String c() {
        String str = "NA";
        if (b()) {
            return "MMX";
        }
        return Util.q(GaanaApplication.getContext()) ? Build.MANUFACTURER : str;
    }
}
