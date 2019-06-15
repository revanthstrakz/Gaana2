package bolts;

import android.content.Intent;
import android.os.Bundle;

public final class c {
    public static Bundle a(Intent intent) {
        return intent.getBundleExtra("al_applink_data");
    }

    public static Bundle b(Intent intent) {
        Bundle a = a(intent);
        if (a == null) {
            return null;
        }
        return a.getBundle("extras");
    }
}
