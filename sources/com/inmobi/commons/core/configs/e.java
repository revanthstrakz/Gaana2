package com.inmobi.commons.core.configs;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse;
import java.util.Map;
import java.util.Map.Entry;

class e implements Runnable {
    private static final String a = "com.inmobi.commons.core.configs.e";
    private f b;
    private a c;
    private final f d;

    public interface a {
        void a();

        void a(ConfigResponse configResponse);
    }

    e(a aVar, f fVar, f fVar2) {
        this.c = aVar;
        this.b = fVar;
        this.d = fVar2;
    }

    private void a(f fVar, Map<String, ConfigResponse> map) {
        for (Entry entry : map.entrySet()) {
            ConfigResponse configResponse = (ConfigResponse) entry.getValue();
            String str = (String) entry.getKey();
            if (!configResponse.a()) {
                this.c.a(configResponse);
                fVar.c.remove(str);
            }
        }
    }

    @NonNull
    private static ConfigNetworkResponse a(f fVar) {
        com.inmobi.commons.core.network.e eVar = new com.inmobi.commons.core.network.e(fVar);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        return new ConfigNetworkResponse(fVar.c, eVar.a(), SystemClock.elapsedRealtime() - elapsedRealtime);
    }

    private boolean a(f fVar, int i, Map<String, ConfigResponse> map) throws InterruptedException {
        if (i > fVar.a) {
            for (Entry key : fVar.c.entrySet()) {
                String str = (String) key.getKey();
                if (map.containsKey(str)) {
                    this.c.a((ConfigResponse) map.get(str));
                }
            }
            return true;
        }
        Thread.sleep((long) (fVar.b * 1000));
        return false;
    }

    public void run() {
        int i = 0;
        int i2 = 0;
        Map map;
        do {
            try {
                if (i2 > this.b.a) {
                    break;
                }
                ConfigNetworkResponse a = a(this.b);
                map = a.a;
                int i3 = (!a.a() || this.d == null) ? 0 : 1;
                if (i3 == 0) {
                    a(this.b, map);
                    if (this.b.c.isEmpty()) {
                        break;
                    }
                    i2++;
                } else {
                    while (i <= this.d.a) {
                        ConfigNetworkResponse a2 = a(this.d);
                        Map map2 = a2.a;
                        if (!a2.a()) {
                            a(this.d, map2);
                            if (!this.d.c.isEmpty()) {
                                i++;
                                if (a(this.d, i, map2)) {
                                    break;
                                }
                            }
                            break;
                        }
                        break;
                    }
                    this.c.a();
                    return;
                }
            } catch (InterruptedException unused) {
                return;
            }
        } while (!a(this.b, i2, map));
        this.c.a();
    }
}
