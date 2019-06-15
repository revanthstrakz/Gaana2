package com.simpl.android.fingerprint.a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.WallpaperManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.annotation.NonNull;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.DisplayMetrics;
import com.facebook.internal.AnalyticsEvents;
import com.simpl.android.fingerprint.commons.models.Attribute;
import com.simpl.android.fingerprint.commons.utils.VersionUtil;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONObject;

class j {
    private static final String c = "j";
    String a;
    EnumSet<k> b = EnumSet.noneOf(k.class);
    private Context d;
    private String e;
    private d f;

    public interface a {
        void a(JSONObject jSONObject);
    }

    j(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        this.d = context;
        this.a = str;
        this.e = str2;
        this.f = new d(context);
    }

    static /* synthetic */ List C(j jVar) {
        final i iVar = new i(jVar.d);
        a anonymousClass11 = new a<List<Attribute>>() {
            public final /* synthetic */ Object a() {
                ArrayList arrayList;
                if (j.this.b.contains(k.DISABLE_LOCATION)) {
                    arrayList = new ArrayList();
                    arrayList.add(new Attribute("SIMPL-Ltln", "disabled by merchant"));
                    arrayList.add(new Attribute("SIMPL-isMock", "disabled by merchant"));
                    return arrayList;
                }
                Object attribute;
                d b = j.this.f;
                Location a = new h(b.a).a();
                i iVar = new i(b.a);
                arrayList = new ArrayList();
                if (a != null) {
                    boolean isFromMockProvider = VERSION.SDK_INT >= 18 ? a.isFromMockProvider() : Secure.getString(iVar.a.getContentResolver(), "mock_location").equals("0");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(String.valueOf(a.getLatitude()));
                    stringBuilder.append(", ");
                    stringBuilder.append(String.valueOf(a.getLongitude()));
                    arrayList.add(new Attribute("SIMPL-Ltln", stringBuilder.toString()));
                    attribute = new Attribute("SIMPL-isMock", String.valueOf(isFromMockProvider));
                } else {
                    attribute = new Attribute("SIMPL-Ltln", "p_disabled");
                }
                arrayList.add(attribute);
                return arrayList;
            }
        };
        Object arrayList = new ArrayList();
        arrayList.add(new Attribute("SIMPL-Ltln", "error"));
        arrayList.add(new Attribute("SIMPL-isMock", "error"));
        return (List) e.a(anonymousClass11, arrayList);
    }

    private void a(List<Callable<JSONObject>> list, final a aVar) {
        final JSONObject jSONObject = new JSONObject();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        for (Future future : newFixedThreadPool.invokeAll(list)) {
            JSONObject jSONObject2 = (JSONObject) future.get();
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                jSONObject.put(str, jSONObject2.getString(str));
            }
        }
        newFixedThreadPool.shutdownNow();
        new b(this.d, new c() {
            public final void a(String str) {
                try {
                    jSONObject.put("SIMPL-AdID", str);
                } catch (Throwable unused) {
                }
                aVar.a(jSONObject);
            }
        }).execute(new Void[0]);
    }

    static /* synthetic */ List t(j jVar) {
        Object arrayList = new ArrayList();
        arrayList.add(new Attribute("SIMPL-DEVICE-MODEL", "error"));
        return (List) e.a(new a<List<Attribute>>() {
            public final /* synthetic */ Object a() {
                if (j.this.b.contains(k.DISABLE_DEVICE_ID)) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new Attribute("SIMPL-DEVICE-MODEL", "disabled by merchant"));
                    return arrayList;
                }
                d b = j.this.f;
                ArrayList arrayList2 = new ArrayList();
                if (b.a.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
                    arrayList2.add(new Attribute("SIMPL-DevId", b.b.getDeviceId()));
                    arrayList2.add(new Attribute("SIMPL-SSN", b.b.getSimSerialNumber()));
                    if (VERSION.SDK_INT >= 28) {
                        arrayList2.add(new Attribute("SIMPL-CarId", String.valueOf(b.b.getSimCarrierId())));
                        arrayList2.add(new Attribute("SIMPL-CarName", String.valueOf(b.b.getSimCarrierIdName())));
                    }
                } else {
                    arrayList2.add(new Attribute("SIMPL-DevId", "p_disabled/p_not_avail"));
                    arrayList2.add(new Attribute("SIMPL-SSN", "p_disabled/p_not_avail"));
                }
                arrayList2.add(new Attribute("SIMPL-DEVICE-MANUFACTURER", Build.MANUFACTURER));
                arrayList2.add(new Attribute("SIMPL-DEVICE-MODEL", Build.MODEL));
                return arrayList2;
            }
        }, arrayList);
    }

    /* Access modifiers changed, original: final */
    public final void a(final HashMap<String, String> hashMap, final a aVar) {
        List arrayList = new ArrayList();
        arrayList.add(new Callable<JSONObject>() {
            public final /* synthetic */ Object call() {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("SIMPL-IPA", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        j.this.f;
                        return f.a();
                    }
                }, (Object) "error")));
                return jSONObject;
            }
        });
        arrayList.add(new Callable<JSONObject>() {
            public final /* synthetic */ Object call() {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("SIMPL-Rt", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        d b = j.this.f;
                        return d.a(RingtoneManager.getRingtone(b.a, RingtoneManager.getActualDefaultRingtoneUri(b.a, 1)).getTitle(b.a));
                    }
                }, (Object) "error")));
                return jSONObject;
            }
        });
        arrayList.add(new Callable<JSONObject>() {
            public final /* synthetic */ Object call() {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("SIMPL-isR", ((String) e.a(new a<String>() {
                    /* JADX WARNING: Missing block: B:16:0x006a, code skipped:
            if (com.simpl.android.fingerprint.a.d.a() == false) goto L_0x006d;
     */
                    public final /* synthetic */ java.lang.Object a() {
                        /*
                        r7 = this;
                        r0 = com.simpl.android.fingerprint.a.j.this;
                        r0.f;
                        r0 = android.os.Build.TAGS;
                        r1 = 0;
                        r2 = 1;
                        if (r0 == 0) goto L_0x0015;
                    L_0x000b:
                        r3 = "test-keys";
                        r0 = r0.contains(r3);
                        if (r0 == 0) goto L_0x0015;
                    L_0x0013:
                        r0 = r2;
                        goto L_0x0016;
                    L_0x0015:
                        r0 = r1;
                    L_0x0016:
                        if (r0 != 0) goto L_0x006c;
                    L_0x0018:
                        r0 = 10;
                        r3 = new java.lang.String[r0];
                        r4 = "/system/app/Superuser.apk";
                        r3[r1] = r4;
                        r4 = "/sbin/su";
                        r3[r2] = r4;
                        r4 = 2;
                        r5 = "/system/bin/su";
                        r3[r4] = r5;
                        r4 = 3;
                        r5 = "/system/xbin/su";
                        r3[r4] = r5;
                        r4 = 4;
                        r5 = "/data/local/xbin/su";
                        r3[r4] = r5;
                        r4 = 5;
                        r5 = "/data/local/bin/su";
                        r3[r4] = r5;
                        r4 = 6;
                        r5 = "/system/sd/xbin/su";
                        r3[r4] = r5;
                        r4 = 7;
                        r5 = "/system/bin/failsafe/su";
                        r3[r4] = r5;
                        r4 = 8;
                        r5 = "/data/local/su";
                        r3[r4] = r5;
                        r4 = 9;
                        r5 = "/su/bin/su";
                        r3[r4] = r5;
                        r4 = r1;
                    L_0x004f:
                        if (r4 >= r0) goto L_0x0063;
                    L_0x0051:
                        r5 = r3[r4];
                        r6 = new java.io.File;
                        r6.<init>(r5);
                        r5 = r6.exists();
                        if (r5 == 0) goto L_0x0060;
                    L_0x005e:
                        r0 = r2;
                        goto L_0x0064;
                    L_0x0060:
                        r4 = r4 + 1;
                        goto L_0x004f;
                    L_0x0063:
                        r0 = r1;
                    L_0x0064:
                        if (r0 != 0) goto L_0x006c;
                    L_0x0066:
                        r0 = com.simpl.android.fingerprint.a.d.a();
                        if (r0 == 0) goto L_0x006d;
                    L_0x006c:
                        r1 = r2;
                    L_0x006d:
                        r0 = java.lang.String.valueOf(r1);
                        return r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.simpl.android.fingerprint.a.j$AnonymousClass33.a():java.lang.Object");
                    }
                }, (Object) "error")));
                j.this.f;
                jSONObject.put("SIMPL-SeN", Build.SERIAL);
                jSONObject.put("SIMPL-Up", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        j.this.f;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(String.valueOf(SystemClock.elapsedRealtime()));
                        stringBuilder.append("ms");
                        return String.valueOf(stringBuilder.toString());
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-AndId", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return Secure.getString(j.this.f.a.getContentResolver(), "android_id");
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-Amem", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        d b = j.this.f;
                        MemoryInfo memoryInfo = new MemoryInfo();
                        ((ActivityManager) b.a.getSystemService("activity")).getMemoryInfo(memoryInfo);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(String.valueOf(memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED));
                        stringBuilder.append("MB");
                        return stringBuilder.toString();
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-BAT", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        if (j.this.b.contains(k.DISABLE_BATTERY)) {
                            return "disabled by merchant";
                        }
                        Intent registerReceiver = j.this.f.a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                        if (registerReceiver == null) {
                            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                        }
                        return String.valueOf((((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1))) * 100.0f);
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-DRes", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        DisplayMetrics displayMetrics = j.this.f.a.getResources().getDisplayMetrics();
                        return String.format("%dx%dx%d", new Object[]{Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels), Integer.valueOf(displayMetrics.densityDpi)});
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-ScrOff", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        String string = System.getString(j.this.f.a.getContentResolver(), "screen_off_timeout");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(string);
                        stringBuilder.append("ms");
                        return stringBuilder.toString();
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-FontSize", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return String.valueOf(j.this.f.a.getResources().getConfiguration().fontScale);
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-ScrBrtMode", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return System.getString(j.this.f.a.getContentResolver(), "screen_brightness_mode");
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-ScrBrt", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return System.getString(j.this.f.a.getContentResolver(), "screen_brightness");
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-CaR", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return String.valueOf(j.this.f.b.isNetworkRoaming());
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-ADisk", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        j.this.f;
                        if (VERSION.SDK_INT < 18) {
                            return "p_disabled/p_not_avail";
                        }
                        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                        return String.valueOf(statFs.getBlockCountLong() * statFs.getBlockSizeLong());
                    }
                }, (Object) "error")));
                for (Attribute attribute : j.t(j.this)) {
                    jSONObject.put(attribute.getKey(), attribute.getValue());
                }
                return jSONObject;
            }
        });
        arrayList.add(new Callable<JSONObject>() {
            public final /* synthetic */ Object call() {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("SIMPL-Blu", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        if (j.this.b.contains(k.DISABLE_BLUETOOTH)) {
                            return "disabled by merchant";
                        }
                        if (j.this.f.a.checkCallingOrSelfPermission("android.permission.BLUETOOTH") != 0) {
                            return "p_disabled/p_not_avail";
                        }
                        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (!defaultAdapter.isEnabled()) {
                            return "off";
                        }
                        Set bondedDevices = defaultAdapter.getBondedDevices();
                        StringBuilder stringBuilder = new StringBuilder();
                        Iterator it = bondedDevices.iterator();
                        while (it.hasNext()) {
                            stringBuilder.append(((BluetoothDevice) it.next()).getAddress());
                            if (it.hasNext()) {
                                stringBuilder.append(",");
                            }
                        }
                        return stringBuilder.toString();
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-InApp", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return !j.this.b.contains(k.DISABLE_APPS) ? j.this.f.b() : "disabled by merchant";
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-WIFI-SSID", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        if (j.this.b.contains(k.DISABLE_WIFI)) {
                            return "disabled by merchant";
                        }
                        d b = j.this.f;
                        return b.a.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0 ? ((WifiManager) b.a.getApplicationContext().getSystemService(e.ad)).getConnectionInfo().getSSID() : "p_disabled/p_not_avail";
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-CaN", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return j.this.f.b.getNetworkOperatorName();
                    }
                }, (Object) "error")));
                jSONObject.put("phone_number", j.this.a);
                jSONObject.put("email", j.this.e);
                jSONObject.put("SIMPL-AccEm", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        if (j.this.b.contains(k.DISABLE_ACCOUNTS)) {
                            return "disabled by merchant";
                        }
                        d b = j.this.f;
                        if (b.a.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") != 0) {
                            return "p_disabled/p_not_avail";
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Account account : ((AccountManager) b.a.getSystemService("account")).getAccounts()) {
                            stringBuilder.append(account.name);
                            stringBuilder.append(",");
                        }
                        return stringBuilder.toString();
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-WpId", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        d b = j.this.f;
                        if (VERSION.SDK_INT < 24) {
                            return "p_disabled/p_not_avail";
                        }
                        int wallpaperId = WallpaperManager.getInstance(b.a).getWallpaperId(1);
                        return String.format("%d-%d", new Object[]{Integer.valueOf(r0.getWallpaperId(2)), Integer.valueOf(wallpaperId)});
                    }
                }, (Object) "error")));
                for (Attribute attribute : j.C(j.this)) {
                    jSONObject.put(attribute.getKey(), attribute.getValue());
                }
                return jSONObject;
            }
        });
        arrayList.add(new Callable<JSONObject>() {
            public final /* synthetic */ Object call() {
                JSONObject jSONObject = new JSONObject();
                if (hashMap != null) {
                    for (Entry entry : hashMap.entrySet()) {
                        jSONObject.put((String) entry.getKey(), entry.getValue());
                    }
                }
                jSONObject.put("sdk-version", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        j.this.f;
                        return VersionUtil.getSdkVersion();
                    }
                }, (Object) "error")));
                jSONObject.put("sdk", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        j.this.f;
                        return VersionUtil.getSdkType();
                    }
                }, (Object) "error")));
                j.this.f;
                jSONObject.put("platform", "android");
                jSONObject.put("SIMPL-PAN", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return j.this.f.a.getPackageName();
                    }
                }, (Object) "error")));
                jSONObject.put("SIMPL-PAV", ((String) e.a(new a<String>() {
                    public final /* synthetic */ Object a() {
                        return j.this.f.c();
                    }
                }, (Object) "error")));
                return jSONObject;
            }
        });
        final long currentTimeMillis = System.currentTimeMillis();
        a(arrayList, new a() {
            public final void a(JSONObject jSONObject) {
                try {
                    jSONObject.put("SIMPL-GenTimeMs", String.valueOf(System.currentTimeMillis() - currentTimeMillis));
                } catch (Exception unused) {
                }
                aVar.a(jSONObject);
            }
        });
    }
}
