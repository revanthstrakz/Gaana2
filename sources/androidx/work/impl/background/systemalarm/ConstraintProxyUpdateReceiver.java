package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.work.f;
import androidx.work.impl.background.systemalarm.ConstraintProxy.BatteryChargingProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy.BatteryNotLowProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy.NetworkStateProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy.StorageNotLowProxy;
import androidx.work.impl.utils.d;

public class ConstraintProxyUpdateReceiver extends BroadcastReceiver {
    private static final String a = f.a("ConstrntProxyUpdtRecvr");

    public static Intent a(Context context, boolean z, boolean z2, boolean z3, boolean z4) {
        Intent intent = new Intent("androidx.work.impl.background.systemalarm.UpdateProxies");
        intent.setComponent(new ComponentName(context, ConstraintProxyUpdateReceiver.class));
        intent.putExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", z).putExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", z2).putExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", z3).putExtra("KEY_NETWORK_STATE_PROXY_ENABLED", z4);
        return intent;
    }

    public void onReceive(Context context, Intent intent) {
        if ("androidx.work.impl.background.systemalarm.UpdateProxies".equals(intent != null ? intent.getAction() : null)) {
            f.a().b(a, String.format("Updating proxies: BatteryNotLowProxy enabled (%s), BatteryChargingProxy enabled (%s), StorageNotLowProxy (%s), NetworkStateProxy enabled (%s)", new Object[]{Boolean.valueOf(intent.getBooleanExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", false)), Boolean.valueOf(intent.getBooleanExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", false)), Boolean.valueOf(intent.getBooleanExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", false)), Boolean.valueOf(intent.getBooleanExtra("KEY_NETWORK_STATE_PROXY_ENABLED", false))}), new Throwable[0]);
            d.a(context, BatteryNotLowProxy.class, r0);
            d.a(context, BatteryChargingProxy.class, r1);
            d.a(context, StorageNotLowProxy.class, r4);
            d.a(context, NetworkStateProxy.class, r12);
            return;
        }
        f.a().b(a, String.format("Ignoring unknown action %s", new Object[]{intent != null ? intent.getAction() : null}), new Throwable[0]);
    }
}
