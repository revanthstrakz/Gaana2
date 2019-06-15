package com.g.a;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.CallLog.Calls;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import com.g.a.b.a;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class e {
    Context a = null;
    Map<String, String> b = new LinkedHashMap();
    private SharedPreferences c;

    public e(Context context) {
        this.a = context;
    }

    public boolean a(String str) {
        try {
            if (this.a.getPackageManager().checkPermission(str, this.a.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            j.a("Exception in checkPerms");
            return false;
        }
    }

    public String a() {
        try {
            if (!a("android.permission.ACCESS_NETWORK_STATE")) {
                j.a("MFilterItDataPoints :: MF_NP_0701 ");
                return "NPERM";
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_CT_NP_0701 ");
            ThrowableExtension.printStackTrace(e);
        }
        try {
            for (NetworkInfo networkInfo : ((ConnectivityManager) this.a.getSystemService("connectivity")).getAllNetworkInfo()) {
                if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
                    return "Wifi";
                }
                if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                    return "Mobile";
                }
            }
        } catch (Exception e2) {
            j.a("MFilterItDataPoints ::MF_CT_0701 ");
            ThrowableExtension.printStackTrace(e2);
        }
        return "UnAvailable";
    }

    public String b() {
        try {
            if (!a("android.permission.ACCESS_NETWORK_STATE")) {
                j.a("MFilterItDataPoints ::MF_NP_0701 ");
                return "NPERM";
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_0701 ");
            ThrowableExtension.printStackTrace(e);
        }
        String str = InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService("phone");
            if (telephonyManager.getPhoneType() != 1 || telephonyManager.getSimState() == 1 || telephonyManager.getSimState() == 0) {
                return str;
            }
            return str;
        } catch (Exception e2) {
            j.a("MFilterItDataPoints ::MF_IW_0701 ");
            ThrowableExtension.printStackTrace(e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0086 A:{SYNTHETIC, Splitter:B:25:0x0086} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x007a */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public boolean c() {
        /*
        r8 = this;
        r0 = 8;
        r1 = 0;
        r2 = 1;
        r0 = new java.lang.String[r0];	 Catch:{ Exception -> 0x0052 }
        r3 = "/sbin/";
        r0[r1] = r3;	 Catch:{ Exception -> 0x0052 }
        r3 = "/system/bin/";
        r0[r2] = r3;	 Catch:{ Exception -> 0x0052 }
        r3 = 2;
        r4 = "/system/xbin/";
        r0[r3] = r4;	 Catch:{ Exception -> 0x0052 }
        r3 = 3;
        r4 = "/data/local/xbin/";
        r0[r3] = r4;	 Catch:{ Exception -> 0x0052 }
        r3 = 4;
        r4 = "/data/local/bin/";
        r0[r3] = r4;	 Catch:{ Exception -> 0x0052 }
        r3 = 5;
        r4 = "/system/sd/xbin/";
        r0[r3] = r4;	 Catch:{ Exception -> 0x0052 }
        r3 = 6;
        r4 = "/system/bin/failsafe/";
        r0[r3] = r4;	 Catch:{ Exception -> 0x0052 }
        r3 = 7;
        r4 = "/data/local/";
        r0[r3] = r4;	 Catch:{ Exception -> 0x0052 }
        r3 = r0.length;	 Catch:{ Exception -> 0x0052 }
        r4 = r1;
    L_0x002e:
        if (r4 >= r3) goto L_0x005b;
    L_0x0030:
        r5 = r0[r4];	 Catch:{ Exception -> 0x0052 }
        r6 = new java.io.File;	 Catch:{ Exception -> 0x0052 }
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0052 }
        r7.<init>();	 Catch:{ Exception -> 0x0052 }
        r7.append(r5);	 Catch:{ Exception -> 0x0052 }
        r5 = "su";
        r7.append(r5);	 Catch:{ Exception -> 0x0052 }
        r5 = r7.toString();	 Catch:{ Exception -> 0x0052 }
        r6.<init>(r5);	 Catch:{ Exception -> 0x0052 }
        r5 = r6.exists();	 Catch:{ Exception -> 0x0052 }
        if (r5 == 0) goto L_0x004f;
    L_0x004e:
        return r2;
    L_0x004f:
        r4 = r4 + 1;
        goto L_0x002e;
    L_0x0052:
        r0 = move-exception;
        r3 = "MFilterItDataPoints ::MF_IR_0702 ";
        com.g.a.j.a(r3);
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x005b:
        r0 = android.os.Build.TAGS;	 Catch:{ Exception -> 0x0068 }
        if (r0 == 0) goto L_0x006c;
    L_0x005f:
        r3 = "test-keys";
        r0 = r0.contains(r3);	 Catch:{ Exception -> 0x0068 }
        if (r0 == 0) goto L_0x006c;
    L_0x0067:
        return r2;
    L_0x0068:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x006c:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x007a }
        r3 = "/system/app/Superuser.apk";
        r0.<init>(r3);	 Catch:{ Exception -> 0x007a }
        r0 = r0.exists();	 Catch:{ Exception -> 0x007a }
        if (r0 == 0) goto L_0x007a;
    L_0x0079:
        return r2;
    L_0x007a:
        r0 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r3 = "su";
        r0 = r0.exec(r3);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        if (r0 == 0) goto L_0x008f;
    L_0x0086:
        r0.destroy();	 Catch:{ Exception -> 0x008a }
        goto L_0x008f;
    L_0x008a:
        r0 = "MFilterItDataPoints ::MF_IR_0702 ";
        com.g.a.j.a(r0);
    L_0x008f:
        return r2;
    L_0x0090:
        r0 = move-exception;
        throw r0;
    L_0x0092:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.g.a.e.c():boolean");
    }

    public String d() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(new String[]{"de.robv.android.xposed.installer", "com.vivek.imeichanger", "com.kingouser.com", "com.VTechno.androididchanger", "com.phoneinfo.changer", "com.redphx.deviceid", "com.makeinfo.androididchanger", "eu.donkeyguard", "com.repodroid.app", "com.phoneinfo.changerpro", "com.bocharov.xposed.fsbi", "ma.wanam.xposedlollipop", "com.vivek.imeichangerpro", "com.device.emulator", "com.liamw.root.androididchanger", "biz.bokhorst.xprivacy.installer", "net.digitalfeed.pdroidalternative", "com.privacy.pdroid", "com.makeinfo.androididchanger", "com.vivek.imeichangerpro", "com.phoneinfo.changer", "com.magic.imeichanger", "com.gojolo.realimeichanger", "com.spazedog.xposed.additionsgb", "com.makeinfo.imeichanger", "com.lekeope.universalgenerator", "com.evozi.deviceid", "com.devicefaker.free", "com.devicefaker.plus", "com.liamw.root.androididchanger", "com.unique.mobilefaker", "com.pro.imeichanger", "com.xamtax.imeicontrol", "com.unique.mobilefaker.plus", "com.noshufou.android.su", "com.toptools.rootmaster360", "com.advanced.rootchecker", "myapp.check.device.superuser", "com.koushikdutta.superuser", "com.scn.rootandrowithoutpc", "com.sheikhbacha.simplerootchecker", "com.jrummyapps.rootchecker", "org.namelessrom.devicecontrol", "eu.chainfire.flash", "com.nsstudio.rootkingalldevice", "com.yellowes.su", "com.superthomaslab.rootessentials", "com.toptools.rootmasterpro", "name.dohkoos.rootuninstaller", "com.rootuninstaller.free", "zsj.android.systemappremover"}));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(Arrays.asList(new String[]{"com.devadvance.rootcloak", "com.felixheller.sharedprefseditor.pro", "com.amphoras.hidemyroot", "eu.chainfire.supersu"}));
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(Arrays.asList(new String[]{"com.greatbytes.fastreboot", "com.apkinstaller.ApkInstaller", "net.segv11.bootunlocker", "com.speedsoftware.rootexplorer", "com.ext.ui", "stericson.busybox", "com.ceco.lollipop.gravitybox"}));
            int i = 0;
            int i2 = i;
            int i3 = i2;
            int i4 = i3;
            for (ApplicationInfo applicationInfo : this.a.getPackageManager().getInstalledApplications(128)) {
                if ((applicationInfo.flags & 1) != 1) {
                    if (i != 1 && arrayList.contains(applicationInfo.packageName)) {
                        stringBuilder.append("High Risk");
                        stringBuilder.append(";");
                        i = 1;
                    }
                    if (i2 != 1 && arrayList2.contains(applicationInfo.packageName)) {
                        stringBuilder.append("Medium Risk");
                        stringBuilder.append(";");
                        i2 = 1;
                    }
                    if (i3 != 1 && arrayList3.contains(applicationInfo.packageName)) {
                        stringBuilder.append("Low Risk");
                        stringBuilder.append(";");
                        i3 = 1;
                    }
                    if (i4 != 1 && applicationInfo.packageName.contains("vpn")) {
                        stringBuilder.append("vpn");
                        stringBuilder.append(";");
                        i4 = 1;
                    }
                }
            }
            return stringBuilder.length() > 0 ? stringBuilder.substring(0, stringBuilder.length() - 1) : "";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_PL_0702 ");
            ThrowableExtension.printStackTrace(e);
            return "UnAvailable";
        }
    }

    public boolean e() {
        try {
            if (VERSION.SDK_INT < 23) {
                Secure.getInt(this.a.getContentResolver(), "adb_enabled", 0);
                if (Secure.getInt(this.a.getContentResolver(), "adb_enabled", 0) != 1) {
                    return false;
                }
            } else if (Global.getInt(this.a.getContentResolver(), "adb_enabled", 0) != 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_AD_0702 ");
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public String f() {
        try {
            a a = b.a(this.a);
            if (a != null) {
                return a.a();
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_GAI_0702 ");
            ThrowableExtension.printStackTrace(e);
        }
        return "UnAvailable";
    }

    public String g() {
        try {
            if (h()) {
                try {
                    if (AdvertisingIdClient.getAdvertisingIdInfo(this.a) == null) {
                        return "";
                    }
                    String id = AdvertisingIdClient.getAdvertisingIdInfo(this.a).getId();
                    if (id != null && id.length() > 0) {
                        return id;
                    }
                } catch (Exception e) {
                    j.a("MFilterItDataPoints ::MF_GAI_0702 ");
                    ThrowableExtension.printStackTrace(e);
                }
            }
        } catch (Exception e2) {
            j.a("MFilterItDataPoints ::MF_GAI_0702 ");
            ThrowableExtension.printStackTrace(e2);
        }
        return "UnAvailable";
    }

    public boolean h() {
        int isGooglePlayServicesAvailable;
        try {
            isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.a);
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_GPSA_0702 ");
            ThrowableExtension.printStackTrace(e);
            isGooglePlayServicesAvailable = 0;
        }
        if (isGooglePlayServicesAvailable == 0) {
            return true;
        }
        return false;
    }

    public String i() {
        try {
            if (b().equalsIgnoreCase(InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
                if (a("android.permission.READ_PHONE_STATE")) {
                    TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService("phone");
                    if (telephonyManager.getDeviceId() != null) {
                        return telephonyManager.getDeviceId().substring(0, 8);
                    }
                    return Secure.getString(this.a.getContentResolver(), "android_id").substring(0, 7);
                }
                j.a("MFilterItDataPoints ::MF_NP_0702 ");
                return "NPERM";
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_TC_0702 ");
            ThrowableExtension.printStackTrace(e);
        }
        j.a("MFilterItDataPoints ::MF_TC_0702 ");
        return "UnAvailable";
    }

    public String j() {
        try {
            if (a("android.permission.READ_PHONE_STATE")) {
                if (b().equalsIgnoreCase(InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
                    return ((TelephonyManager) this.a.getSystemService("phone")).getSimOperator().substring(0, 3);
                }
                return "UnAvailable";
            }
            j.a("MFilterItDataPoints ::MF_NP_0702 ");
            return "NPERM";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_MC_0702 ");
            ThrowableExtension.printStackTrace(e);
        }
    }

    public String k() {
        try {
            if (a("android.permission.READ_PHONE_STATE")) {
                if (b().equalsIgnoreCase(InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
                    return ((TelephonyManager) this.a.getSystemService("phone")).getSimOperator().substring(3);
                }
                return "UnAvailable";
            }
            j.a("MFilterItDataPoints ::MF_NP_0702 ");
            return "NPERM";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_MN_0702 ");
            ThrowableExtension.printStackTrace(e);
        }
    }

    public String l() {
        try {
            return this.a.getPackageName();
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_PN_0702 ");
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public String m() {
        try {
            if (!a("android.permission.READ_SMS")) {
                j.a("MFilterItDataPoints ::MF_NP_0703 ");
                return "NPERM";
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_0703 ");
            ThrowableExtension.printStackTrace(e);
        }
        try {
            Cursor query = this.a.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
            int i = 0;
            int count = query.getCount() > 0 ? query.getCount() : 0;
            query.close();
            query = this.a.getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
            if (query.getCount() > 0) {
                i = query.getCount();
            }
            query.close();
            return String.valueOf(i + count);
        } catch (Exception e2) {
            j.a("MFilterItDataPoints ::MF_SC_0703 ");
            ThrowableExtension.printStackTrace(e2);
            return "UnAvailable";
        }
    }

    public String n() throws SecurityException {
        try {
            if (!a("android.permission.READ_CALL_LOG")) {
                return "NPERM";
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_NP_0705 ");
            ThrowableExtension.printStackTrace(e);
        }
        String str = "";
        try {
            String string;
            Cursor query = this.a.getContentResolver().query(Calls.CONTENT_URI, null, "type = 2", null, "date DESC");
            query.moveToFirst();
            String str2 = str;
            int i = 1;
            while (i <= 5) {
                string = query.getString(query.getColumnIndex("number"));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(string);
                stringBuilder.append(";");
                str2 = stringBuilder.toString();
                i++;
                if (!query.moveToNext()) {
                    break;
                }
            }
            byte[] digest = MessageDigest.getInstance("MD5").digest(str2.getBytes("UTF-8"));
            StringBuilder stringBuilder2 = new StringBuilder();
            for (byte b : digest) {
                string = Integer.toHexString(255 & b);
                if (string.length() == 1) {
                    stringBuilder2.append('0');
                }
                stringBuilder2.append(string);
            }
            return stringBuilder2.toString();
        } catch (Exception e2) {
            j.a("MFilterItDataPoints ::MF_LFD_0705 ");
            ThrowableExtension.printStackTrace(e2);
            return "UnAvailable";
        }
    }

    public String o() {
        try {
            if (!a("android.permission.READ_CALL_LOG")) {
                j.a("MFilterItDataPoints ::MF_NP_0705 ");
                return "NPERM";
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_0705 ");
            ThrowableExtension.printStackTrace(e);
        }
        try {
            if (Calls.getLastOutgoingCall(this.a).length() > 0) {
                byte[] digest = MessageDigest.getInstance("MD5").digest(Calls.getLastOutgoingCall(this.a).getBytes("UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                for (byte b : digest) {
                    String toHexString = Integer.toHexString(255 & b);
                    if (toHexString.length() == 1) {
                        stringBuilder.append('0');
                    }
                    stringBuilder.append(toHexString);
                }
                return stringBuilder.toString();
            }
        } catch (Exception e2) {
            j.a("MFilterItDataPoints ::MF_LD_0705 ");
            ThrowableExtension.printStackTrace(e2);
        }
        return "UnAvailable";
    }

    public boolean p() {
        boolean z = false;
        try {
            List asList = Arrays.asList(new String[]{"de.robv.android.xposed.installer", "eu.chainfire.supersu"});
            for (ApplicationInfo applicationInfo : this.a.getPackageManager().getInstalledApplications(128)) {
                if (asList.contains(applicationInfo.packageName)) {
                    z = true;
                }
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_HRA_0706 ");
            ThrowableExtension.printStackTrace(e);
        }
        return z;
    }

    public String q() {
        try {
            if (a("android.permission.ACCESS_NETWORK_STATE")) {
                switch (((TelephonyManager) this.a.getSystemService("phone")).getNetworkType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2g";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3g";
                    case 13:
                        return "4g";
                    default:
                        return "UnAvailable";
                }
            }
            j.a("MFilterItDataPoints ::MF_NP_0701 ");
            return "NPERM";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_NT_0701 ");
            ThrowableExtension.printStackTrace(e);
            return "UnAvailable";
        }
    }

    public String r() {
        try {
            String str = Build.MANUFACTURER;
            String str2 = Build.MODEL;
            if (str2.startsWith(str)) {
                return b(str2);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b(str));
            stringBuilder.append("_");
            stringBuilder.append(str2);
            return stringBuilder.toString();
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_MM_0701 ");
            ThrowableExtension.printStackTrace(e);
            return "UnAvailable";
        }
    }

    public String s() {
        try {
            if (a("android.permission.READ_PHONE_STATE")) {
                TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService("phone");
                if (telephonyManager.getNetworkOperatorName() != null && telephonyManager.getNetworkOperatorName().length() > 0) {
                    return telephonyManager.getNetworkOperatorName();
                }
                return "UnAvailable";
            }
            j.a("MFilterItDataPoints ::MF_NP_0702 ");
            return "NPERM";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_OP_0702 ");
            ThrowableExtension.printStackTrace(e);
        }
    }

    private String b(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    char charAt = str.charAt(0);
                    if (Character.isUpperCase(charAt)) {
                        return str;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Character.toUpperCase(charAt));
                    stringBuilder.append(str.substring(1));
                    return stringBuilder.toString();
                }
            } catch (Exception e) {
                j.a("MFilterItDataPoints ::MF_CL_0702 ");
                ThrowableExtension.printStackTrace(e);
                return "UnAvailable";
            }
        }
        return "";
    }

    public String t() {
        try {
            return this.a.getPackageManager().getInstallerPackageName(l());
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_SN_0702 ");
            ThrowableExtension.printStackTrace(e);
            return "UnAvailable";
        }
    }

    @TargetApi(22)
    public String u() {
        try {
            if (!a("android.permission.READ_PHONE_STATE")) {
                j.a("MFilterItDataPoints ::MF_NP_0702 ");
                return "NPERM";
            }
        } catch (Exception unused) {
            j.a("MFilterItDataPoints ::MF_0702 ");
        }
        try {
            String str = "";
            if (VERSION.SDK_INT < 22) {
                return "UnAvailable";
            }
            SubscriptionManager subscriptionManager = (SubscriptionManager) this.a.getSystemService("telephony_subscription_service");
            subscriptionManager.toString();
            int i = 0;
            for (SubscriptionInfo subscriptionInfo : subscriptionManager.getActiveSubscriptionInfoList()) {
                i++;
                if (i == 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(subscriptionInfo.getMcc());
                    stringBuilder.append("_");
                    stringBuilder.append(subscriptionInfo.getMnc());
                    str = stringBuilder.toString();
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str);
                    stringBuilder2.append(":");
                    stringBuilder2.append(subscriptionInfo.getMcc());
                    stringBuilder2.append("_");
                    stringBuilder2.append(subscriptionInfo.getMnc());
                    str = stringBuilder2.toString();
                }
            }
            return str;
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_DSI_0702 ");
            ThrowableExtension.printStackTrace(e);
            return "Exception";
        }
    }

    public int v() {
        try {
            return VERSION.SDK_INT;
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_OV_0702 ");
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    public String w() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!a("android.permission.BLUETOOTH")) {
                j.a("MFilterItDataPoints ::MF_NP_0708 ");
                return "NPERM";
            } else if (defaultAdapter != null) {
                return defaultAdapter.getName();
            } else {
                j.a("MFilterItDataPoints ::MF_BN_0708 ");
                return null;
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public String x() {
        try {
            if (!a("android.permission.READ_EXTERNAL_STORAGE")) {
                j.a("MFilterItDataPoints ::MF_NP_0709 ");
                return "NPERM";
            }
        } catch (Exception unused) {
            j.a("MFilterItDataPoints ::MF_0709 ");
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(new String[]{"de.robv.android.xposed.installer", "com.vivek.imeichanger", "com.kingouser.com", "com.VTechno.androididchanger", "com.phoneinfo.changer", "com.redphx.deviceid", "com.makeinfo.androididchanger", "eu.donkeyguard", "com.repodroid.app", "com.phoneinfo.changerpro", "com.bocharov.xposed.fsbi", "ma.wanam.xposedlollipop", "com.vivek.imeichangerpro", "com.device.emulator", "com.liamw.root.androididchanger", "biz.bokhorst.xprivacy.installer"}));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(Arrays.asList(new String[]{"com.devadvance.rootcloak", "com.felixheller.sharedprefseditor.pro", "com.amphoras.hidemyroot", "eu.chainfire.supersu"}));
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(Arrays.asList(new String[]{"com.greatbytes.fastreboot", "com.apkinstaller.ApkInstaller", "net.segv11.bootunlocker", "com.speedsoftware.rootexplorer", "com.ext.ui", "stericson.busybox", "com.ceco.lollipop.gravitybox"}));
            StringBuilder stringBuilder2 = new StringBuilder();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(Environment.getExternalStorageDirectory());
            stringBuilder3.append("/Android/data/");
            File[] listFiles = new File(stringBuilder3.toString()).listFiles();
            int i = 0;
            int i2 = i;
            int i3 = i2;
            int i4 = i3;
            int i5 = i4;
            while (i < listFiles.length) {
                if (listFiles[i].isDirectory()) {
                    if (i2 != 1 && arrayList.contains(listFiles[i].getName())) {
                        stringBuilder.append("High Risk");
                        stringBuilder.append(";");
                        i2 = 1;
                    }
                    if (i3 != 1 && arrayList2.contains(listFiles[i].getName())) {
                        stringBuilder.append("Medium Risk");
                        stringBuilder.append(";");
                        i3 = 1;
                    }
                    if (i4 != 1 && arrayList3.contains(listFiles[i].getName())) {
                        stringBuilder.append("Low Risk");
                        stringBuilder.append(";");
                        i4 = 1;
                    }
                    if (i5 != 1 && listFiles[i].getName().contains("vpn")) {
                        stringBuilder.append("vpn");
                        stringBuilder.append(";");
                        i5 = 1;
                    }
                }
                i++;
            }
            return stringBuilder.length() > 0 ? stringBuilder.substring(0, stringBuilder.length() - 1) : "";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_DF_0702 ");
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public String y() {
        try {
            File file = new File("/sdcard/Android/data");
            if (file.isDirectory()) {
                for (String str : file.list()) {
                    if (str.equalsIgnoreCase("com.bluestacks.home")) {
                        return "true";
                    }
                    if (str.equalsIgnoreCase("com.bluestacks.appsettings")) {
                        return "true";
                    }
                    if (str.equalsIgnoreCase("com.bluestacks.settings")) {
                        return "true";
                    }
                }
            }
        } catch (Exception unused) {
            j.a("MFilterItDataPoints ::MF_BS_0702 ");
        }
        return InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
    }

    public String z() {
        String str = "";
        try {
            return Build.MANUFACTURER;
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_MF_0702 ");
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public String A() {
        String str = "";
        try {
            return Build.DEVICE;
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_DN_0702 ");
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public String B() {
        String str = "unavailable";
        try {
            return Build.FINGERPRINT;
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_FP_0702 ");
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public String C() {
        String str = "";
        try {
            if (a("android.permission.ACCESS_WIFI_STATE")) {
                str = ((WifiManager) this.a.getSystemService(com.til.colombia.android.internal.e.ad)).getConnectionInfo().getMacAddress();
                return str;
            }
            j.a("MFilterItDataPoints ::MF_NP_0707 ");
            return "NPERM";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_WM_0707 ");
            ThrowableExtension.printStackTrace(e);
        }
    }

    public String D() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!a("android.permission.BLUETOOTH")) {
                j.a("MFilterItDataPoints ::MF_NP_0708 ");
                return "NPERM";
            } else if (defaultAdapter == null) {
                return null;
            } else {
                return defaultAdapter.getAddress();
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_BM_0708 ");
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public String E() {
        String str = "unavailable";
        try {
            if (!a("android.permission.READ_EXTERNAL_STORAGE")) {
                j.a("MFilterItDataPoints ::MF_NP_0709 ");
                return "NPERM";
            } else if (a("android.permission.WRITE_EXTERNAL_STORAGE")) {
                File file = new File(Environment.getExternalStorageDirectory(), "com.android.device.check");
                if (!file.exists()) {
                    file.mkdirs();
                }
                file = new File("/sdcard/com.android.device.check/trans.txt");
                if (file.exists()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                        str = stringBuffer.toString();
                    }
                } else {
                    file.createNewFile();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                    bufferedWriter.write("unavailable");
                    bufferedWriter.close();
                }
                return str;
            } else {
                j.a("MFilterItDataPoints ::MF_NP_0710 ");
                return "NPERM";
            }
        } catch (FileNotFoundException e) {
            j.a("MFilterItDataPoints ::MF_FM_0710 ");
            ThrowableExtension.printStackTrace(e);
        } catch (Exception e2) {
            j.a("MFilterItDataPoints ::MF_FM_0710 ");
            ThrowableExtension.printStackTrace(e2);
        }
    }

    public String F() {
        String str = "off";
        try {
            if (a("android.permission.ACCESS_WIFI_STATE")) {
                WifiInfo connectionInfo = ((WifiManager) this.a.getSystemService(com.til.colombia.android.internal.e.ad)).getConnectionInfo();
                if (connectionInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                    str = connectionInfo.getSSID();
                }
                return str;
            }
            j.a("MFilterItDataPoints ::MF_NP_0707 ");
            return "NPERM";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_WS_0707 ");
            ThrowableExtension.printStackTrace(e);
        }
    }

    public String G() {
        String str = "off";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (a("android.permission.ACCESS_WIFI_STATE")) {
                List configuredNetworks = ((WifiManager) this.a.getSystemService(com.til.colombia.android.internal.e.ad)).getConfiguredNetworks();
                if (configuredNetworks == null) {
                    return str;
                }
                for (int i = 0; i < configuredNetworks.size(); i++) {
                    stringBuilder.append(((WifiConfiguration) configuredNetworks.get(i)).SSID);
                    stringBuilder.append(";");
                }
                return stringBuilder.toString();
            }
            j.a("MFilterItDataPoints ::MF_NP_0707 ");
            return "NPERM";
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_0707 ");
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public String H() {
        String str = InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp()) {
                    if (networkInterface.getInterfaceAddresses().size() != 0) {
                        if ("tun0".equals(networkInterface.getName())) {
                            return "true";
                        }
                    }
                }
            }
            return str;
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_ET_0702 ");
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public String I() {
        String str = "";
        try {
            return System.getProperty("http.agent");
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_WV_0702 ");
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public static long a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
        } catch (Exception unused) {
            j.a("MFilterItDataPoints ::MF_WV_0719 ");
            return 0;
        }
    }

    public static long b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_WV_0729 ");
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    public Map<String, String> a(int i) {
        try {
            j.a("extended Data Points: 0");
            this.b.put("sdkVersion", "2.7.1");
            this.b.put("packageName", l());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(i);
            this.b.put("extDataPoints", stringBuilder.toString());
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Exception in minKpi ");
            stringBuilder2.append(e);
            j.a(stringBuilder2.toString());
        }
        return this.b;
    }

    public Map<String, String> a(String str, String str2, int i) {
        try {
            j.a("extended Data Points: 1");
            this.c = this.a.getSharedPreferences("mFilterItData", 0);
            Editor edit = this.c.edit();
            this.c = this.a.getSharedPreferences("mFilterItData", 0);
            this.b.put("sdk_version", "2.7.1");
            this.b.put("sdk_connection_type", a());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(c());
            this.b.put("sdk_rooted_device", stringBuilder.toString());
            this.b.put("sdk_package_list", d());
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(e());
            this.b.put("sdk_adb_active", stringBuilder.toString());
            this.b.put("sdk_gaid", f());
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(b());
            this.b.put("sdk_wifi_only", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(j());
            this.b.put("sdk_mcc", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(k());
            this.b.put("sdk_mnc", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(m());
            this.b.put("sdk_sms_count", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(l());
            this.b.put("sdk_package_name", stringBuilder.toString());
            this.b.put("sdk_tac", i());
            this.b.put("sdk_app_data", str);
            this.b.put("sdk_vendor_id", str2);
            this.b.put("sdk_last_dialed", o());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(p());
            this.b.put("sdk_rooted_apps", stringBuilder2.toString());
            this.b.put("sdk_network_type", q());
            this.b.put("sdk_make_model", r());
            this.b.put("sdk_operator", s());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(t());
            this.b.put("sdk_store_name", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(u());
            this.b.put("sdk_dualsim_info", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(v());
            this.b.put("sdk_os_version", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(H());
            this.b.put("sdk_vpn_info", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(y());
            this.b.put("sdk_bluestack", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(w());
            this.b.put("sdk_bluetooth_name", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(x());
            this.b.put("sdk_data_folder", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(z());
            this.b.put("sdk_manufacturer", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(A());
            this.b.put("sdk_device_name", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(B());
            this.b.put("sdk_fingerprint", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(I());
            this.b.put("sdk_webview", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(C());
            this.b.put("sdk_wifi_mac", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(D());
            this.b.put("sdk_bluetooth_mac", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(F());
            this.b.put("sdk_wifi_ssid", stringBuilder2.toString());
            str = Base64.encodeToString(G().getBytes("UTF-8"), 0);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("");
            stringBuilder3.append(str);
            this.b.put("sdk_wifissid_configure", stringBuilder3.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(E());
            this.b.put("sdk_filemarker", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(n());
            this.b.put("sdk_last5dialed", stringBuilder2.toString());
            this.b.put("sdk_conversion_time", this.c.getString("mf_conversiontime", ""));
            this.b.put("sdk_gaid_api", g());
            this.b.put("sdk_transaction_id", this.c.getString("mf_transactionid", ""));
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(a(this.a));
            this.b.put("sdk_installtime", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(i);
            this.b.put("sdk_ext_datapoints", stringBuilder2.toString());
            edit.apply();
        } catch (Exception e) {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("Exception in maxKpi ");
            stringBuilder4.append(e);
            j.a(stringBuilder4.toString());
        }
        return this.b;
    }
}
