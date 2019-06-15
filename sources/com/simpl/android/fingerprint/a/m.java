package com.simpl.android.fingerprint.a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.Secure;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.simpl.android.fingerprint.commons.utils.VersionUtil;
import com.til.colombia.android.internal.e;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

@SuppressLint({"MissingPermission"})
public class m {
    private String getAccounts(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0) {
            for (Account account : ((AccountManager) context.getSystemService("account")).getAccounts()) {
                if ("com.google".equalsIgnoreCase(account.type)) {
                    stringBuilder.append(account.name);
                    stringBuilder.append(",");
                }
            }
        }
        return stringBuilder.toString();
    }

    private String getAvailableMemory(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED));
        stringBuilder.append("MB");
        return stringBuilder.toString();
    }

    private ArrayList<String> getCarrierNetwork(TelephonyManager telephonyManager, Context context) {
        ArrayList arrayList = new ArrayList();
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
            if (VERSION.SDK_INT >= 22) {
                List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
                if (activeSubscriptionInfoList == null) {
                    arrayList.add(telephonyManager.getNetworkOperatorName());
                    return arrayList;
                }
                for (SubscriptionInfo carrierName : activeSubscriptionInfoList) {
                    arrayList.add(String.valueOf(carrierName.getCarrierName()));
                }
            } else {
                arrayList.add(telephonyManager.getNetworkOperatorName());
            }
        }
        return arrayList;
    }

    private ArrayList<String> getDeviceId(Context context, TelephonyManager telephonyManager) {
        ArrayList arrayList = new ArrayList();
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return arrayList;
        }
        Object deviceId;
        if (VERSION.SDK_INT >= 23) {
            arrayList.add(telephonyManager.getDeviceId(0));
            deviceId = telephonyManager.getDeviceId(1);
        } else {
            deviceId = telephonyManager.getDeviceId();
        }
        arrayList.add(deviceId);
        return arrayList;
    }

    private ArrayList<n> getDeviceInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        n nVar = new n();
        nVar.a = Boolean.valueOf(isDeviceRooted());
        boolean z = telephonyManager != null && telephonyManager.isNetworkRoaming();
        nVar.b = Boolean.valueOf(z);
        nVar.c = getCarrierNetwork(telephonyManager, context);
        nVar.d = Build.SERIAL;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(SystemClock.elapsedRealtime()));
        stringBuilder.append("ns");
        nVar.e = stringBuilder.toString();
        nVar.f = Secure.getString(context.getContentResolver(), "android_id");
        nVar.g = VersionUtil.getSdkVersion();
        nVar.h = getAvailableMemory(context);
        nVar.i = getIpAddress();
        nVar.j = getDeviceId(context, telephonyManager);
        nVar.k = getSimSerialNumber(telephonyManager);
        nVar.l = Build.MANUFACTURER;
        nVar.m = Build.MODEL;
        ArrayList arrayList = new ArrayList();
        arrayList.add(nVar);
        return arrayList;
    }

    private ArrayList<String> getInstalledApps(Context context) {
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
        ArrayList arrayList = new ArrayList();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if ((applicationInfo.flags & 1) == 0) {
                arrayList.add(applicationInfo.packageName);
            }
        }
        return arrayList;
    }

    private String getIpAddress() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    stringBuilder.append(((InetAddress) inetAddresses.nextElement()).getHostAddress());
                    stringBuilder.append(" ");
                }
            }
        } catch (SocketException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return stringBuilder.toString();
    }

    private String getLastKnownLocation(Context context) {
        Location a = new h(context).a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(a.getLatitude()));
        stringBuilder.append(", ");
        stringBuilder.append(String.valueOf(a.getLongitude()));
        return stringBuilder.toString();
    }

    private String getSimSerialNumber(TelephonyManager telephonyManager) {
        return telephonyManager.getSimSerialNumber();
    }

    private ArrayList<l> getSimplContacts(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context.checkCallingOrSelfPermission("android.permission.READ_CONTACTS") == 0) {
            Cursor query = context.getContentResolver().query(Contacts.CONTENT_URI, null, null, null, null);
            while (query != null && query.moveToNext()) {
                l lVar = new l();
                String string = query.getString(query.getColumnIndex(BaseColumns._ID));
                lVar.a = string;
                lVar.b = query.getString(query.getColumnIndex("display_name"));
                if (query.getString(query.getColumnIndex("has_phone_number")).equalsIgnoreCase("1")) {
                    Cursor query2 = context.getContentResolver().query(Phone.CONTENT_URI, null, "contact_id = ".concat(String.valueOf(string)), null, null);
                    if (query2 != null) {
                        ArrayList arrayList2 = new ArrayList();
                        while (query2.moveToNext()) {
                            arrayList2.add(query2.getString(query2.getColumnIndex("data1")));
                        }
                        lVar.c = arrayList2;
                        query2.close();
                    }
                }
                Cursor query3 = context.getContentResolver().query(Email.CONTENT_URI, null, "contact_id = ?", new String[]{string}, null);
                if (query3 != null) {
                    ArrayList arrayList3 = new ArrayList();
                    while (query3.moveToNext()) {
                        arrayList3.add(query3.getString(query3.getColumnIndex("data1")));
                    }
                    lVar.d = arrayList3;
                    query3.close();
                }
                arrayList.add(lVar);
            }
            if (query != null) {
                query.close();
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d6 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0148  */
    private java.util.ArrayList<com.simpl.android.fingerprint.a.q> getSimplSmses(android.content.Context r19, java.lang.String r20, java.lang.String r21, java.lang.String[] r22) {
        /*
        r18 = this;
        r0 = r18;
        r1 = r20;
        r2 = r21;
        r3 = r22;
        r4 = new java.util.ArrayList;
        r4.<init>();
        r5 = "android.permission.READ_SMS";
        r6 = r19;
        r5 = r6.checkCallingOrSelfPermission(r5);
        if (r5 != 0) goto L_0x014b;
    L_0x0017:
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = 5;
        r8 = 0;
        r9 = 1;
        r10 = 2;
        if (r1 == 0) goto L_0x0066;
    L_0x0022:
        if (r2 == 0) goto L_0x0066;
    L_0x0024:
        if (r3 == 0) goto L_0x0066;
    L_0x0026:
        r11 = r3.length;
        r11 = r11 + r10;
        r11 = new java.lang.String[r11];
        r1 = r0.getTimestamp(r1);
        r11[r8] = r1;
        r1 = r0.getTimestamp(r2);
        r11[r9] = r1;
        r1 = "date BETWEEN? AND? AND (";
        r5.append(r1);
        r1 = r10;
    L_0x003c:
        r2 = r11.length;
        if (r1 >= r2) goto L_0x0063;
    L_0x003f:
        r2 = new java.lang.StringBuilder;
        r12 = "%";
        r2.<init>(r12);
        r12 = r1 + -2;
        r12 = r3[r12];
        r2.append(r12);
        r2 = r2.toString();
        r11[r1] = r2;
        r2 = "address LIKE?";
        r5.append(r2);
        r1 = r1 + 1;
        r2 = r11.length;
        if (r1 == r2) goto L_0x003c;
    L_0x005d:
        r2 = " OR ";
        r5.append(r2);
        goto L_0x003c;
    L_0x0063:
        r1 = ")";
        goto L_0x007c;
    L_0x0066:
        if (r1 == 0) goto L_0x0080;
    L_0x0068:
        if (r2 == 0) goto L_0x0080;
    L_0x006a:
        if (r3 != 0) goto L_0x0080;
    L_0x006c:
        r11 = new java.lang.String[r10];
        r1 = r0.getTimestamp(r1);
        r11[r8] = r1;
        r1 = r0.getTimestamp(r2);
        r11[r9] = r1;
        r1 = "date BETWEEN? AND?";
    L_0x007c:
        r5.append(r1);
        goto L_0x0099;
    L_0x0080:
        r1 = "date>=?";
        r5.append(r1);
        r11 = new java.lang.String[r9];
        r1 = java.util.Calendar.getInstance();
        r2 = -3;
        r1.add(r7, r2);
        r1 = r1.getTimeInMillis();
        r1 = java.lang.String.valueOf(r1);
        r11[r8] = r1;
    L_0x0099:
        r16 = r11;
        r12 = r19.getContentResolver();
        r1 = "content://sms/inbox";
        r13 = android.net.Uri.parse(r1);
        r1 = 6;
        r14 = new java.lang.String[r1];
        r1 = "_id";
        r14[r8] = r1;
        r1 = "address";
        r14[r9] = r1;
        r1 = "date";
        r14[r10] = r1;
        r1 = 3;
        r2 = "body";
        r14[r1] = r2;
        r1 = 4;
        r2 = "creator";
        r14[r1] = r2;
        r1 = "read";
        r14[r7] = r1;
        r1 = r5.length();
        if (r1 == 0) goto L_0x00ce;
    L_0x00c8:
        r1 = r5.toString();
    L_0x00cc:
        r15 = r1;
        goto L_0x00d0;
    L_0x00ce:
        r1 = 0;
        goto L_0x00cc;
    L_0x00d0:
        r17 = 0;
        r1 = r12.query(r13, r14, r15, r16, r17);
    L_0x00d6:
        if (r1 == 0) goto L_0x0146;
    L_0x00d8:
        r2 = r1.moveToNext();
        if (r2 == 0) goto L_0x0146;
    L_0x00de:
        r2 = "^([A-Z][A-Z])?(-[A-Z,0-9]\\w)";
        r2 = java.util.regex.Pattern.compile(r2);
        r3 = "address";
        r3 = r1.getColumnIndex(r3);
        r3 = r1.getString(r3);
        r2 = r2.matcher(r3);
        r2 = r2.find();
        if (r2 == 0) goto L_0x00d6;
    L_0x00f8:
        r2 = new com.simpl.android.fingerprint.a.q;
        r3 = "address";
        r3 = r1.getColumnIndex(r3);
        r6 = r1.getString(r3);
        r3 = "_id";
        r3 = r1.getColumnIndex(r3);
        r7 = r1.getString(r3);
        r3 = "date";
        r3 = r1.getColumnIndex(r3);
        r8 = r1.getString(r3);
        r3 = "body";
        r3 = r1.getColumnIndex(r3);
        r9 = r1.getString(r3);
        r3 = "creator";
        r3 = r1.getColumnIndex(r3);
        r10 = r1.getString(r3);
        r3 = "read";
        r3 = r1.getColumnIndex(r3);
        r3 = r1.getString(r3);
        r3 = java.lang.Boolean.parseBoolean(r3);
        r11 = java.lang.Boolean.valueOf(r3);
        r5 = r2;
        r5.<init>(r6, r7, r8, r9, r10, r11);
        r4.add(r2);
        goto L_0x00d6;
    L_0x0146:
        if (r1 == 0) goto L_0x014b;
    L_0x0148:
        r1.close();
    L_0x014b:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.simpl.android.fingerprint.a.m.getSimplSmses(android.content.Context, java.lang.String, java.lang.String, java.lang.String[]):java.util.ArrayList");
    }

    private String getTimestamp(String str) {
        long time;
        try {
            time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(str).getTime();
        } catch (ParseException unused) {
            time = 0;
        }
        return String.valueOf(time);
    }

    private String getWifi(Context context) {
        return ((WifiManager) context.getApplicationContext().getSystemService(e.ad)).getConnectionInfo().getSSID();
    }

    private boolean isDeviceRooted() {
        return isRootByTag() || isRootBySuFile() || isRootBySuProcess();
    }

    private boolean isRootBySuFile() {
        String[] strArr = new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (int i = 0; i < 10; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039  */
    private boolean isRootBySuProcess() {
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = java.lang.Runtime.getRuntime();	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r3 = 2;
        r3 = new java.lang.String[r3];	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r4 = "/system/xbin/which";
        r3[r0] = r4;	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r4 = "su";
        r5 = 1;
        r3[r5] = r4;	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r2 = r2.exec(r3);	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r3 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r4 = r2.getInputStream();	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r3.<init>(r4);	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r1.<init>(r3);	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r1 = r1.readLine();	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        if (r1 == 0) goto L_0x002b;
    L_0x002a:
        r0 = r5;
    L_0x002b:
        if (r2 == 0) goto L_0x0030;
    L_0x002d:
        r2.destroy();
    L_0x0030:
        return r0;
    L_0x0031:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0037;
    L_0x0034:
        r1 = r2;
        goto L_0x003d;
    L_0x0036:
        r0 = move-exception;
    L_0x0037:
        if (r1 == 0) goto L_0x003c;
    L_0x0039:
        r1.destroy();
    L_0x003c:
        throw r0;
    L_0x003d:
        if (r1 == 0) goto L_0x0042;
    L_0x003f:
        r1.destroy();
    L_0x0042:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.simpl.android.fingerprint.a.m.isRootBySuProcess():boolean");
    }

    private boolean isRootByTag() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    /* Access modifiers changed, original: protected */
    public java.lang.String getPermissionData(java.lang.String r2, android.content.Context r3, java.lang.String r4, java.lang.String r5, java.lang.String[] r6) {
        /*
        r1 = this;
        r0 = r2.hashCode();
        switch(r0) {
            case -2062386608: goto L_0x0044;
            case -1888586689: goto L_0x003a;
            case -5573545: goto L_0x0030;
            case 2015858: goto L_0x0026;
            case 1271781903: goto L_0x001c;
            case 1675316546: goto L_0x0012;
            case 1977429404: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x004e;
    L_0x0008:
        r0 = "android.permission.READ_CONTACTS";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x004e;
    L_0x0010:
        r2 = 0;
        goto L_0x004f;
    L_0x0012:
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x004e;
    L_0x001a:
        r2 = 5;
        goto L_0x004f;
    L_0x001c:
        r0 = "android.permission.GET_ACCOUNTS";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x004e;
    L_0x0024:
        r2 = 2;
        goto L_0x004f;
    L_0x0026:
        r0 = "APPS";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x004e;
    L_0x002e:
        r2 = 6;
        goto L_0x004f;
    L_0x0030:
        r0 = "android.permission.READ_PHONE_STATE";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x004e;
    L_0x0038:
        r2 = 3;
        goto L_0x004f;
    L_0x003a:
        r0 = "android.permission.ACCESS_FINE_LOCATION";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x004e;
    L_0x0042:
        r2 = 4;
        goto L_0x004f;
    L_0x0044:
        r0 = "android.permission.READ_SMS";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x004e;
    L_0x004c:
        r2 = 1;
        goto L_0x004f;
    L_0x004e:
        r2 = -1;
    L_0x004f:
        switch(r2) {
            case 0: goto L_0x0080;
            case 1: goto L_0x007b;
            case 2: goto L_0x0076;
            case 3: goto L_0x006d;
            case 4: goto L_0x0068;
            case 5: goto L_0x0063;
            case 6: goto L_0x0055;
            default: goto L_0x0052;
        };
    L_0x0052:
        r2 = "";
        return r2;
    L_0x0055:
        r2 = new org.json.JSONArray;
        r3 = r1.getInstalledApps(r3);
        r2.<init>(r3);
    L_0x005e:
        r2 = r2.toString();
        return r2;
    L_0x0063:
        r2 = r1.getWifi(r3);
        return r2;
    L_0x0068:
        r2 = r1.getLastKnownLocation(r3);
        return r2;
    L_0x006d:
        r2 = r1.getDeviceInfo(r3);
    L_0x0071:
        r2 = com.simpl.android.fingerprint.commons.utils.JSONUtils.getJSONArrayFromJsonable(r2);
        goto L_0x005e;
    L_0x0076:
        r2 = r1.getAccounts(r3);
        return r2;
    L_0x007b:
        r2 = r1.getSimplSmses(r3, r4, r5, r6);
        goto L_0x0071;
    L_0x0080:
        r2 = r1.getSimplContacts(r3);
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.simpl.android.fingerprint.a.m.getPermissionData(java.lang.String, android.content.Context, java.lang.String, java.lang.String, java.lang.String[]):java.lang.String");
    }
}
