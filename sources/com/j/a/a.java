package com.j.a;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.moe.pushlibrary.MoEWorker;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.til.colombia.android.internal.e;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static final b A = new b(3, "APP_FOREGROUND", 2);
    private static final b B = new b(4, "OPEN_GLES_VERSION", 2);
    private static final b C = new b(5, "APP_DEBUGGABLE", 2);
    private static final b D = new b(6, "PROCESSORS", 1);
    private static final b E = new b(7, "ACTIVITIES", 1);
    private static final b F = new b(8, "PERSISTENT", 2);
    private static final b G = new b(9, "TOTAL_MEMORY", 2);
    private static final b H = new b(10, "MANIFEST_PERMISSIONS", 1);
    private static final b I = new b(11, "DEVICE_LOCKED", 2);
    private static final b J = new b(12, "KEYGUARD_LOCKED", 2);
    private static final b K = new b(13, "DEVICE_SECURE", 2);
    private static final b L = new b(14, "KEYGUARD_SECURE", 2);
    private static final b M = new b(15, "NETWORK_TYPE", 2);
    private static final b N = new b(16, "SIM_OPERATOR_NAME", 1);
    private static final b O = new b(17, "PHONE_TYPE", 1);
    private static final b P = new b(18, "NETWORK_OPERATOR", 2);
    private static final b Q = new b(19, "NETWORK_OPERATOR_NAME", 2);
    private static final b R = new b(20, "SIM_COUNTRY", 2);
    private static final b S = new b(21, "MOBILE_DATA_ENABLED", 2);
    private static final b T = new b(22, "ACCESSIBILITY_SERVICES_ENABLED", 2);
    private static final b U = new b(23, "ADB_ENABLED", 2);
    private static final b V = new b(24, "CURRENT_BRIGHTNESS", 2);
    private static final b W = new b(25, "SCREEN_OFF_TIMEOUT", 2);
    private static final b X = new b(26, "BOOT_COUNT", 1);
    private static final b Y = new b(28, "APP_INSTALL_DATE", 1);
    private static final b Z = new b(29, "TOUCHSCREEN_MULTITOUCH", 1);
    static String a = null;
    private static final b aA = new b(56, "STREAM_MUSIC", 2);
    private static final b aB = new b(57, "STREAM_RING", 2);
    private static final b aC = new b(58, "STREAM_DTMF", 2);
    private static final b aD = new b(59, "STREAM_VOICE_CALL", 2);
    private static final b aE = new b(60, "STREAM_ALARM", 2);
    private static final b aF = new b(61, "WIRED_HEADSET", 2);
    private static final b aG = new b(62, "MUSIC_ACTIVE", 2);
    private static final b aH = new b(63, "SPEAKERPHONE", 2);
    private static final b aI = new b(64, "AUDIO_DEVICES", 2);
    private static final b aJ = new b(65, "WIFI_SSID", 2);
    private static final b aK = new b(66, "WIFI_STATE", 2);
    private static final b aL = new b(68, "DISPLAY_INTERACTIVE", 2);
    private static final b aM = new b(69, "DEVICE_IDLE", 2);
    private static final b aN = new b(70, "POWER_SAFE_MODE", 2);
    private static final b aO = new b(71, "BUILD_SERIAL", 1);
    private static final b aP = new b(72, "BUILD_BRAND", 1);
    private static final b aQ = new b(73, "BUILD_BOARD", 1);
    private static final b aR = new b(74, "BUILD_BOOTLOADER", 1);
    private static final b aS = new b(75, "BUILD_MANUFACTURER", 1);
    private static final b aT = new b(76, "BUILD_MODEL", 1);
    private static final b aU = new b(77, "BUILD_HOST", 1);
    private static final b aV = new b(78, "BUILD_HARDWARE", 1);
    private static final b aW = new b(79, "BUILD_DEVICE", 1);
    private static final b aX = new b(80, "BUILD_PRINT", 1);
    private static final b aY = new b(81, "BUILD_DISPLAY", 1);
    private static final b aZ = new b(82, "BUILD_USER", 1);
    private static final b aa = new b(30, "TOUCHSCREEN_MULTITOUCH_DISTINCT", 1);
    private static final b ab = new b(31, "TOUCHSCREEN_MULTITOUCH_JAZZHAND", 1);
    private static final b ac = new b(32, "CAMERA_REAR", 1);
    private static final b ad = new b(33, "CAMERA_FRONT", 1);
    private static final b ae = new b(34, "CAMERA_FLASH", 1);
    private static final b af = new b(35, "SENSOR_ACCELEROMETER", 1);
    private static final b ag = new b(36, "SENSOR_STEP_COUNT", 1);
    private static final b ah = new b(37, "SENSOR_BAROMETER", 1);
    private static final b ai = new b(38, "SENSOR_GYRO", 1);
    private static final b aj = new b(39, "SENSOR_COMPASS", 1);
    private static final b ak = new b(40, "SENSOR_HEART", 1);
    private static final b al = new b(41, "SENSOR_STEP_DETECTOR", 1);
    private static final b am = new b(42, "SENSOR_PROXIMITY", 1);
    private static final b an = new b(43, "SENSOR_AMBIENT_TEMPERATURE", 1);
    private static final b ao = new b(44, "PLAYSTORE_APP_INSTALLATION", 1);
    private static final b ap = new b(45, "APP_INSTALLER_PACKAGE_NAME", 1);
    private static final b aq = new b(46, "BLUETOOTH_ENABLED", 2);
    private static final b ar = new b(47, "BLUETOOTH_PRESENT", 1);
    private static final b as = new b(48, "BLUETOOTH_LE_PRESENT", 1);
    private static final b at = new b(49, "DEVELOPER_MODE_ENABLED", 2);
    private static final b au = new b(50, "STAY_ON_WHILE_PLUGGEDIN", 2);
    private static final b av = new b(51, "WIFI_ENABLED", 2);
    private static final b aw = new b(52, "USB_MASS_STORAGE_ENABLED", 2);
    private static final b ax = new b(53, "INSTALL_NON_MARKET_APPS_ENABLED", 2);
    private static final b ay = new b(54, "STREAM_SYSTEM", 2);
    private static final b az = new b(55, "STREAM_NOTIFICATION", 2);
    static JSONObject b = null;
    private static final b bA = new b(122, "APP_PUBLIC_PATH", 1);
    private static final b bB = new b(MoEWorker.REQ_CODE_SEND_DATA, "BUILD_CODENAME", 1);
    private static final b bC = new b(124, "BUILD_RELEASE", 1);
    private static final b bD = new b(125, "BUILD_SDK_INT", 1);
    private static final b bE = new b(126, "BUILD_SECURITY_PATCH", 2);
    private static final b bF = new b(127, "BUILD_BASE_OS", 1);
    private static final b bG = new b(128, "SYSTEM_OS_ARCHITECTURE", 1);
    private static final b bH = new b(TsExtractor.TS_STREAM_TYPE_AC3, "SYSTEM_OS_NAME", 1);
    private static final b bI = new b(TsExtractor.TS_STREAM_TYPE_HDMV_DTS, "SYSTEM_OS_VERSION", 1);
    private static final b bJ = new b(131, "WIFI_PRESENT", 1);
    private static final b bK = new b(132, "TELEPHONY_PRESENT", 1);
    private static final b bL = new b(133, "TOUCHSCREEN", 1);
    private static final b bM = new b(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, "TOUCHSCREEN_FAKETOUCH_SUPPORT", 1);
    private static final b bN = new b(TsExtractor.TS_STREAM_TYPE_E_AC3, "TOUCHSCREEN_FAKETOUCH_MULTITOUCH_DISTINCT", 1);
    private static final b bO = new b(136, "TOUCHSCREEN_FAKETOUCH_MULTITOUCH_JAZZHAND", 1);
    private static final b bP = new b(137, "SENSOR_HIFI", 1);
    private static final b bQ = new b(TsExtractor.TS_STREAM_TYPE_DTS, "TELEPHONY_CDMA_PRESENT", 1);
    private static final b bR = new b(139, "TELEPHONY_GSM_PRESENT", 1);
    private static final b bS = new b(140, "TELEVISION", 1);
    private static final b bT = new b(141, "PRINTING", 1);
    private static final b bU = new b(142, "WATCH", 1);
    private static final b bV = new b(143, "GAMEPAD", 1);
    private static final b bW = new b(144, "LIVE_TV", 1);
    private static final b bX = new b(145, "AUTOMOTIVE", 1);
    private static final b bY = new b(146, "EMBEDDED", 1);
    private static final b bZ = new b(147, "SCREEN_PORTRAIT", 1);
    private static final b ba = new b(83, "BUILD_PRODUCT", 1);
    private static final b bb = new b(84, "BUILD_TAGS", 1);
    private static final b bc = new b(85, "BUILD_TIME", 1);
    private static final b bd = new b(86, "BUILD_RADIO", 1);
    private static final b be = new b(87, "BUILD_ID", 1);
    private static final b bf = new b(88, "BUILD_TYPE", 1);
    private static final b bg = new b(89, "UPTIME", 2);
    private static final b bh = new b(90, "EMULATOR_PROPERTIES", 1);
    private static final b bi = new b(91, "APP_PACKAGE_NAME", 1);
    private static final b bj = new b(105, "BATTERY_STATUS", 2);
    private static final b bk = new b(106, "BATTERY_PLUGGED", 2);
    private static final b bl = new b(107, "BATTERY_PERCENTAGE", 2);
    private static final b bm = new b(108, "BATTERY_PRESENT", 1);
    private static final b bn = new b(109, "BATTERY_HEALTH", 2);
    private static final b bo = new b(110, "BATTERY_TEMPERATURE", 2);
    private static final b bp = new b(111, "BATTERY_CURRENT_NOW", 2);
    private static final b bq = new b(112, "HTTP_PROXY_HOST", 2);
    private static final b br = new b(113, "HTTP_PROXY_PORT", 2);
    private static final b bs = new b(114, "HTTP_PROXY_USER", 2);
    private static final b bt = new b(115, "HTTP_PROXY_NON_PROXY_HOST", 2);
    private static final b bu = new b(116, "HTTP_USER_AGENT", 1);
    private static final b bv = new b(117, "SOCKS_PROXY_HOST", 2);
    private static final b bw = new b(118, "SOCKS_PROXY_PORT", 2);
    private static final b bx = new b(119, "SOCKS_PROXY_VERSION", 2);
    private static final b by = new b(120, "SOCKS_PROXY_USER", 2);
    private static final b bz = new b(121, "APP_SIZE", 1);
    static String c = "https://s.update.fbsbx.com/bridge.html";
    private static final b cA = new b(174, "DISPLAY_X_DPI", 1);
    private static final b cB = new b(175, "DISPLAY_Y_DPI", 1);
    private static final b cC = new b(176, "NETWORKS", 2);
    private static final b cD = new b(177, "LOCALE_LANGUAGE", 1);
    private static final b cE = new b(178, "LOCALE_COUNTRY", 1);
    private static final b cF = new b(179, "LOCALE_NAME", 1);
    private static final b cG = new b(180, "WO_SSID", 1);
    private static final b cH = new b(181, "WO_SDK_VERSION", 1);
    private static final b ca = new b(148, "SCREEN_LANDSCAPE", 1);
    private static final b cb = new b(149, "ACTIVITIES_ON_SECONDARY_DISPLAYS", 1);
    private static final b cc = new b(150, "NFC", 1);
    private static final b cd = new b(151, "USB_HOST", 1);
    private static final b ce = new b(152, "WIFI_DIRECT", 1);
    private static final b cf = new b(153, "ETHERNET", 1);
    private static final b cg = new b(154, "APP_WIDGETS", 1);
    private static final b ch = new b(155, "OPENGLES", 1);
    private static final b ci = new b(156, "MICROPHONE", 1);
    private static final b cj = new b(157, "DACTYLOGRAM", 1);
    private static final b ck = new b(158, "SIM_STATE", 1);
    private static final b cl = new b(159, "APP_FLAGS", 2);
    private static final b cm = new b(MoEHelperUtils.BASELINE_SCREEN_DPI, "APP_LAST_UPDATE", 1);
    private static final b cn = new b(161, "PACK_VERSION_CODE", 1);
    private static final b co = new b(162, "RINGER_MODE", 2);
    private static final b cp = new b(163, "TIME_ZONE", 2);
    private static final b cq = new b(164, "TIME_ZONE_NAME", 2);
    private static final b cr = new b(165, "NETWORK_INTERFACES", 2);
    private static final b cs = new b(166, "TOTAL_BYTES_RX", 2);
    private static final b ct = new b(167, "TOTAL_BYTES_TX", 2);
    private static final b cu = new b(168, "MOBILE_BYTES_RX", 2);
    private static final b cv = new b(169, "MOBILE_BYTES_TX", 2);
    private static final b cw = new b(170, "DISPLAY_DENSITY", 2);
    private static final b cx = new b(171, "DISPLAY_DENSITY_DPI", 1);
    private static final b cy = new b(172, "DISPLAY_HEIGHT_PIXELS", 1);
    private static final b cz = new b(173, "DISPLAY_WIDTH_PIXELS", 1);
    static String d = "";
    @SuppressLint({"StaticFieldLeak"})
    static Context e;
    static String f;
    static e g;
    private static final int h = VERSION.SDK_INT;
    private static d i = new d();
    private static boolean j;
    private static boolean k;
    private static boolean l;
    private static boolean m;
    private static boolean n;
    private static AudioManager o;
    private static PowerManager p;
    private static TelephonyManager q;
    private static WifiManager r;
    private static ApplicationInfo s;
    private static PackageManager t;
    private static KeyguardManager u;
    private static JSONObject v = new JSONObject();
    private static LinkedList<JSONObject> w = new LinkedList();
    private static JSONObject x = new JSONObject();
    private static final b y = new b(1, "APP_IMPORTANCE", 2);
    private static final b z = new b(2, "APP_VISIBLE", 2);

    static void a() {
        new Thread(new Runnable() {
            public final void run() {
                JSONObject jSONObject;
                a.b();
                try {
                    Iterator keys = a.x.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        JSONObject jSONObject2 = (JSONObject) a.x.get(str);
                        int i = jSONObject2.getInt("signalType");
                        jSONObject2.remove("signalType");
                        if ((i & 1) == 1) {
                            a.v.put(str, jSONObject2);
                        } else if ((i & 2) == 2) {
                            try {
                                jSONObject = new JSONObject();
                                jSONObject.put(str, jSONObject2);
                                a.w.add(jSONObject);
                            } catch (JSONException unused) {
                            }
                        }
                    }
                } catch (JSONException e) {
                    new StringBuilder(" JSONException: ").append(e.getMessage());
                }
                JSONObject jSONObject3 = new JSONObject();
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("TYPE", "AUTO");
                    jSONObject4.put("PROXY_ORIGIN", a.d);
                    jSONObject3.put("CI", a.a);
                    jSONObject3.put("ID", a.f);
                    jSONObject3.put("DECISION_EVENT", jSONObject4);
                    jSONObject3.put("CT", a.b);
                } catch (JSONException unused2) {
                }
                jSONObject4 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONObject = new JSONObject();
                try {
                    jSONObject4.put("TOUCH_SIG", new JSONArray());
                    jSONObject4.put("STATIC_SIG", a.v);
                    Iterator it = a.w.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(it.next());
                    }
                    jSONObject4.put("DYNAMIC_SIG", jSONArray);
                    Iterator keys2 = jSONObject3.keys();
                    while (keys2.hasNext()) {
                        String str2 = (String) keys2.next();
                        jSONObject.put(str2, jSONObject3.get(str2));
                    }
                    jSONObject.put("DATA", jSONObject4);
                    a.g.a(jSONObject.toString());
                } catch (JSONException unused3) {
                }
            }
        }).start();
    }

    public static void a(Context context) {
        if (context instanceof Application) {
            e = context;
            return;
        }
        throw new Exception("No Application Context given. SDK WILL NOT WORK");
    }

    private static void a(b bVar, Object obj) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("signalType", bVar.c);
            jSONObject.put("v", obj);
            jSONObject.put("t", System.currentTimeMillis());
            x.put(bVar.b, jSONObject);
        } catch (JSONException unused) {
        }
    }

    public static void a(String str) {
        if (a == null) {
            a = str;
        }
    }

    static void a(String str, String str2, String str3) {
        JSONObject jSONObject;
        if (str3 != null) {
            try {
                if (!str3.equals("")) {
                    jSONObject = new JSONObject(str3);
                    new Thread(new com.j.a.d.AnonymousClass1(str, str2, jSONObject)).start();
                }
            } catch (JSONException unused) {
                return;
            }
        }
        jSONObject = new JSONObject();
        new Thread(new com.j.a.d.AnonymousClass1(str, str2, jSONObject)).start();
    }

    public static void a(URL url) {
        d = url.toString();
    }

    public static void a(JSONObject jSONObject) {
        if (!j) {
            boolean z = true;
            j = true;
            if (h >= 21) {
                if (a == null) {
                    throw new Exception("No CI given. SDK WILL NOT WORK");
                } else if (e != null) {
                    byte[] bytes;
                    byte[] bArr;
                    b = jSONObject;
                    g = new e();
                    String string = e.getSharedPreferences("WOInspector", 0).getString("wossid", "");
                    f = string;
                    if (!string.equals("")) {
                        byte[] decode = Base64.decode(f, 0);
                        bytes = "wossid".getBytes();
                        bArr = new byte[decode.length];
                        for (int i = 0; i < decode.length; i++) {
                            bArr[i] = (byte) (decode[i] ^ bytes[i % bytes.length]);
                        }
                        f = new String(bArr);
                    }
                    if (f.equals("")) {
                        f = UUID.randomUUID().toString();
                        Editor edit = e.getSharedPreferences("WOInspector", 0).edit();
                        bytes = f.getBytes();
                        bArr = "wossid".getBytes();
                        byte[] bArr2 = new byte[bytes.length];
                        for (int i2 = 0; i2 < bytes.length; i2++) {
                            bArr2[i2] = (byte) (bytes[i2] ^ bArr[i2 % bArr.length]);
                        }
                        edit.putString("wossid", Base64.encodeToString(bArr2, 0));
                        edit.apply();
                    }
                    k = e.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
                    l = e.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0;
                    m = e.checkCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE") == 0;
                    if (e.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
                        z = false;
                    }
                    n = z;
                    if (k) {
                        o = (AudioManager) e.getSystemService("audio");
                        p = (PowerManager) e.getSystemService("power");
                        q = (TelephonyManager) e.getSystemService("phone");
                        r = (WifiManager) e.getSystemService(e.ad);
                        s = e.getApplicationInfo();
                        t = e.getPackageManager();
                        u = (KeyguardManager) e.getSystemService("keyguard");
                        g.a();
                        return;
                    }
                    throw new Exception("APP don't have internet access permission. SDK WILL NOT WORK");
                } else {
                    throw new Exception("No Application Context given. SDK WILL NOT WORK");
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:374:0x0f31  */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x0ef4  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0610  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x05b8  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x06d2 A:{Catch:{ SocketException | JSONException -> 0x06da }} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0639 A:{LOOP_START, LOOP:2: B:155:0x0639->B:380:0x0639, Catch:{ SocketException | JSONException -> 0x06da }, PHI: r1 } */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0706 A:{Catch:{ Exception -> 0x071b }} */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0744  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x072e  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x076f  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x074d  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x08dd  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x08cb  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0a5e  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0a48  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0a7f  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0a69  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x0aca  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0ab4  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0b2a  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0b14  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0b9f  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0b89  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0bb3  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0ba8  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0bdd  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0bc5  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x0bf8  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0be8  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x0c5d  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x0c42 A:{SYNTHETIC, Splitter:B:272:0x0c42} */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x0d00  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0cea  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0dec  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0ddc  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x0e01  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0df5  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0e1a  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0e0a  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x0e2f  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0e23  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0e44  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0e38  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0e59  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0e4d  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0e88  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0e64  */
    /* JADX WARNING: Removed duplicated region for block: B:339:0x0ea3  */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x0e93  */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x0ee9  */
    /* JADX WARNING: Removed duplicated region for block: B:342:0x0eac  */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x0ef4  */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x0f31  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0460 A:{Catch:{ Exception -> 0x04bc }} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x05b8  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0610  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0639 A:{LOOP_START, LOOP:2: B:155:0x0639->B:380:0x0639, Catch:{ SocketException | JSONException -> 0x06da }, PHI: r1 } */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x06d2 A:{Catch:{ SocketException | JSONException -> 0x06da }} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0706 A:{Catch:{ Exception -> 0x071b }} */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x072e  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0744  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x074d  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x076f  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x08cb  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x08dd  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0a48  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0a5e  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0a69  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0a7f  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0ab4  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x0aca  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0b14  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0b2a  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0b89  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0b9f  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0ba8  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0bb3  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0bc5  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0bdd  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0be8  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x0bf8  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x0c42 A:{SYNTHETIC, Splitter:B:272:0x0c42} */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x0c5d  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x0c8c A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0cea  */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x0d00  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0ddc  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0dec  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0df5  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x0e01  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0e0a  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0e1a  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0e23  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x0e2f  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0e38  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0e44  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0e4d  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0e59  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0e64  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0e88  */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x0e93  */
    /* JADX WARNING: Removed duplicated region for block: B:339:0x0ea3  */
    /* JADX WARNING: Removed duplicated region for block: B:342:0x0eac  */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x0ee9  */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x0f31  */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x0ef4  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0343  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0439  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x038f  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0460 A:{Catch:{ Exception -> 0x04bc }} */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0610  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x05b8  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x06d2 A:{Catch:{ SocketException | JSONException -> 0x06da }} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0639 A:{LOOP_START, LOOP:2: B:155:0x0639->B:380:0x0639, Catch:{ SocketException | JSONException -> 0x06da }, PHI: r1 } */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0706 A:{Catch:{ Exception -> 0x071b }} */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0744  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x072e  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x076f  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x074d  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x08dd  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x08cb  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0a5e  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0a48  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0a7f  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0a69  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x0aca  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0ab4  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0b2a  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0b14  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0b9f  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0b89  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0bb3  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0ba8  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0bdd  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0bc5  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x0bf8  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0be8  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x0c5d  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x0c42 A:{SYNTHETIC, Splitter:B:272:0x0c42} */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x0c8c A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x0d00  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0cea  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0dec  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0ddc  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x0e01  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0df5  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0e1a  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0e0a  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x0e2f  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0e23  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0e44  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0e38  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0e59  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0e4d  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0e88  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0e64  */
    /* JADX WARNING: Removed duplicated region for block: B:339:0x0ea3  */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x0e93  */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x0ee9  */
    /* JADX WARNING: Removed duplicated region for block: B:342:0x0eac  */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x0ef4  */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x0f31  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0343  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x038f  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0439  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0460 A:{Catch:{ Exception -> 0x04bc }} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x05b8  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0610  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0639 A:{LOOP_START, LOOP:2: B:155:0x0639->B:380:0x0639, Catch:{ SocketException | JSONException -> 0x06da }, PHI: r1 } */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x06d2 A:{Catch:{ SocketException | JSONException -> 0x06da }} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0706 A:{Catch:{ Exception -> 0x071b }} */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x072e  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0744  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x074d  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x076f  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x08cb  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x08dd  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0a48  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0a5e  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0a69  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0a7f  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0ab4  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x0aca  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0b14  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0b2a  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0b89  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0b9f  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0ba8  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0bb3  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0bc5  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0bdd  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0be8  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x0bf8  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x0c42 A:{SYNTHETIC, Splitter:B:272:0x0c42} */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x0c5d  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x0c8c A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0cea  */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x0d00  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0ddc  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0dec  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0df5  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x0e01  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0e0a  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0e1a  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0e23  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x0e2f  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0e38  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0e44  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0e4d  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0e59  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0e64  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0e88  */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x0e93  */
    /* JADX WARNING: Removed duplicated region for block: B:339:0x0ea3  */
    /* JADX WARNING: Removed duplicated region for block: B:342:0x0eac  */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x0ee9  */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x0f31  */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x0ef4  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0343  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0439  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x038f  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0460 A:{Catch:{ Exception -> 0x04bc }} */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0610  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x05b8  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x06d2 A:{Catch:{ SocketException | JSONException -> 0x06da }} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0639 A:{LOOP_START, LOOP:2: B:155:0x0639->B:380:0x0639, Catch:{ SocketException | JSONException -> 0x06da }, PHI: r1 } */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0706 A:{Catch:{ Exception -> 0x071b }} */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0744  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x072e  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x076f  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x074d  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x08dd  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x08cb  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0a5e  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0a48  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0a7f  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0a69  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x0aca  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0ab4  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0b2a  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0b14  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0b9f  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0b89  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0bb3  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0ba8  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0bdd  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0bc5  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x0bf8  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0be8  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x0c5d  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x0c42 A:{SYNTHETIC, Splitter:B:272:0x0c42} */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x0c8c A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x0d00  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0cea  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0dec  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0ddc  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x0e01  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0df5  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0e1a  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0e0a  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x0e2f  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0e23  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0e44  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0e38  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0e59  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0e4d  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0e88  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0e64  */
    /* JADX WARNING: Removed duplicated region for block: B:339:0x0ea3  */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x0e93  */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x0ee9  */
    /* JADX WARNING: Removed duplicated region for block: B:342:0x0eac  */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x0ef4  */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x0f31  */
    static /* synthetic */ void b() {
        /*
        r0 = new android.app.ActivityManager$RunningAppProcessInfo;
        r0.<init>();
        android.app.ActivityManager.getMyMemoryState(r0);
        r1 = y;
        r2 = r0.importance;
        r2 = java.lang.Integer.valueOf(r2);
        a(r1, r2);
        r1 = z;
        r2 = r0.importance;
        r3 = 100;
        r4 = 1;
        r5 = 0;
        if (r2 == r3) goto L_0x0026;
    L_0x001d:
        r2 = r0.importance;
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r6) goto L_0x0024;
    L_0x0023:
        goto L_0x0026;
    L_0x0024:
        r2 = r5;
        goto L_0x0027;
    L_0x0026:
        r2 = r4;
    L_0x0027:
        r2 = java.lang.Boolean.valueOf(r2);
        a(r1, r2);
        r1 = A;
        r0 = r0.importance;
        if (r0 != r3) goto L_0x0036;
    L_0x0034:
        r0 = r4;
        goto L_0x0037;
    L_0x0036:
        r0 = r5;
    L_0x0037:
        r0 = java.lang.Boolean.valueOf(r0);
        a(r1, r0);
        r0 = e;
        r1 = "activity";
        r0 = r0.getSystemService(r1);
        r0 = (android.app.ActivityManager) r0;
        if (r0 == 0) goto L_0x005a;
    L_0x004a:
        r0 = r0.getDeviceConfigurationInfo();
        if (r0 == 0) goto L_0x005a;
    L_0x0050:
        r1 = B;
        r0 = r0.getGlEsVersion();
        a(r1, r0);
        goto L_0x0061;
    L_0x005a:
        r0 = B;
        r1 = "ERROR";
        a(r0, r1);
    L_0x0061:
        r0 = C;
        r1 = s;
        r1 = r1.flags;
        r2 = 2;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x006d;
    L_0x006b:
        r1 = r4;
        goto L_0x006e;
    L_0x006d:
        r1 = r5;
    L_0x006e:
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = F;
        r1 = s;
        r1 = r1.flags;
        r6 = 8;
        r1 = r1 & r6;
        if (r1 == 0) goto L_0x0082;
    L_0x0080:
        r1 = r4;
        goto L_0x0083;
    L_0x0082:
        r1 = r5;
    L_0x0083:
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = cl;
        r1 = s;
        r1 = r1.flags;
        r1 = java.lang.Integer.valueOf(r1);
        a(r0, r1);
        r0 = o;
        r1 = 4;
        r7 = 3;
        r8 = 5;
        if (r0 == 0) goto L_0x0144;
    L_0x009e:
        r0 = ay;
        r9 = o;
        r9 = r9.getStreamVolume(r4);
        r9 = java.lang.Integer.valueOf(r9);
        a(r0, r9);
        r0 = az;
        r9 = o;
        r9 = r9.getStreamVolume(r8);
        r9 = java.lang.Integer.valueOf(r9);
        a(r0, r9);
        r0 = aA;
        r9 = o;
        r9 = r9.getStreamVolume(r7);
        r9 = java.lang.Integer.valueOf(r9);
        a(r0, r9);
        r0 = aB;
        r9 = o;
        r9 = r9.getStreamVolume(r2);
        r9 = java.lang.Integer.valueOf(r9);
        a(r0, r9);
        r0 = aC;
        r9 = o;
        r6 = r9.getStreamVolume(r6);
        r6 = java.lang.Integer.valueOf(r6);
        a(r0, r6);
        r0 = aD;
        r6 = o;
        r6 = r6.getStreamVolume(r5);
        r6 = java.lang.Integer.valueOf(r6);
        a(r0, r6);
        r0 = aE;
        r6 = o;
        r6 = r6.getStreamVolume(r1);
        r6 = java.lang.Integer.valueOf(r6);
        a(r0, r6);
        r0 = aF;
        r6 = o;
        r6 = r6.isWiredHeadsetOn();
        r6 = java.lang.Boolean.valueOf(r6);
        a(r0, r6);
        r0 = aG;
        r6 = o;
        r6 = r6.isMusicActive();
        r6 = java.lang.Boolean.valueOf(r6);
        a(r0, r6);
        r0 = aH;
        r6 = o;
        r6 = r6.isSpeakerphoneOn();
        r6 = java.lang.Boolean.valueOf(r6);
        a(r0, r6);
        r0 = co;
        r6 = o;
        r6 = r6.getRingerMode();
        r6 = java.lang.Integer.valueOf(r6);
    L_0x0140:
        a(r0, r6);
        goto L_0x018f;
    L_0x0144:
        r0 = ay;
        r6 = "ERROR";
        a(r0, r6);
        r0 = az;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aA;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aB;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aC;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aD;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aE;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aF;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aG;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aH;
        r6 = "ERROR";
        a(r0, r6);
        r0 = aI;
        r6 = "ERROR";
        goto L_0x0140;
    L_0x018f:
        r0 = new org.json.JSONArray;
        r0.<init>();
        r6 = h;
        r9 = 23;
        if (r6 < r9) goto L_0x01d6;
    L_0x019a:
        r6 = o;
        if (r6 == 0) goto L_0x01d1;
    L_0x019e:
        r6 = o;	 Catch:{ JSONException -> 0x01d1 }
        r6 = r6.getDevices(r4);	 Catch:{ JSONException -> 0x01d1 }
        r10 = r6.length;	 Catch:{ JSONException -> 0x01d1 }
        r11 = r5;
    L_0x01a6:
        if (r11 >= r10) goto L_0x01cb;
    L_0x01a8:
        r12 = r6[r11];	 Catch:{ JSONException -> 0x01d1 }
        r13 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x01d1 }
        r13.<init>();	 Catch:{ JSONException -> 0x01d1 }
        r14 = "ProductName";
        r15 = r12.getProductName();	 Catch:{ JSONException -> 0x01d1 }
        r15 = r15.toString();	 Catch:{ JSONException -> 0x01d1 }
        r13.put(r14, r15);	 Catch:{ JSONException -> 0x01d1 }
        r14 = "Type";
        r12 = r12.getType();	 Catch:{ JSONException -> 0x01d1 }
        r13.put(r14, r12);	 Catch:{ JSONException -> 0x01d1 }
        r0.put(r13);	 Catch:{ JSONException -> 0x01d1 }
        r11 = r11 + 1;
        goto L_0x01a6;
    L_0x01cb:
        r6 = aI;	 Catch:{ JSONException -> 0x01d1 }
        a(r6, r0);	 Catch:{ JSONException -> 0x01d1 }
        goto L_0x01dd;
    L_0x01d1:
        r0 = aI;
        r6 = "ERROR";
        goto L_0x01da;
    L_0x01d6:
        r0 = aI;
        r6 = "UNKNOWN";
    L_0x01da:
        a(r0, r6);
    L_0x01dd:
        r0 = e;
        r6 = new android.content.IntentFilter;
        r10 = "android.intent.action.BATTERY_CHANGED";
        r6.<init>(r10);
        r10 = 0;
        r0 = r0.registerReceiver(r10, r6);
        if (r0 == 0) goto L_0x01f4;
    L_0x01ed:
        r6 = "present";
        r6 = r0.getBooleanExtra(r6, r5);
        goto L_0x01f5;
    L_0x01f4:
        r6 = r5;
    L_0x01f5:
        r11 = bm;
        r12 = java.lang.Boolean.valueOf(r6);
        a(r11, r12);
        r11 = -1;
        if (r6 == 0) goto L_0x0288;
    L_0x0201:
        r6 = "level";
        r6 = r0.getIntExtra(r6, r11);
        r12 = "scale";
        r12 = r0.getIntExtra(r12, r11);
        if (r6 == r11) goto L_0x0222;
    L_0x020f:
        if (r12 <= 0) goto L_0x0222;
    L_0x0211:
        r13 = bl;
        r6 = (float) r6;
        r12 = (float) r12;
        r6 = r6 / r12;
        r12 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r6 = r6 * r12;
        r6 = (int) r6;
        r6 = java.lang.Integer.valueOf(r6);
        a(r13, r6);
        goto L_0x0229;
    L_0x0222:
        r6 = bl;
        r12 = "ERROR";
        a(r6, r12);
    L_0x0229:
        r6 = bk;
        r12 = "plugged";
        r12 = r0.getIntExtra(r12, r5);
        r12 = java.lang.String.valueOf(r12);
        a(r6, r12);
        r6 = bj;
        r12 = "status";
        r12 = r0.getIntExtra(r12, r5);
        r12 = java.lang.String.valueOf(r12);
        a(r6, r12);
        r6 = bn;
        r12 = "health";
        r12 = r0.getIntExtra(r12, r5);
        r12 = java.lang.String.valueOf(r12);
        a(r6, r12);
        r6 = "temperature";
        r0 = r0.getIntExtra(r6, r5);
        r0 = (float) r0;
        r6 = 1092616192; // 0x41200000 float:10.0 double:5.398241246E-315;
        r0 = r0 / r6;
        r6 = bo;
        r0 = java.lang.String.valueOf(r0);
        a(r6, r0);
        r0 = e;
        r6 = "batterymanager";
        r0 = r0.getSystemService(r6);
        r0 = (android.os.BatteryManager) r0;
        if (r0 == 0) goto L_0x0283;
    L_0x0275:
        r6 = bp;
        r0 = r0.getIntProperty(r2);
        r0 = java.lang.String.valueOf(r0);
        a(r6, r0);
        goto L_0x02b2;
    L_0x0283:
        r0 = bp;
        r6 = "ERROR";
        goto L_0x02af;
    L_0x0288:
        r0 = bl;
        r6 = "UNKNOWN";
        a(r0, r6);
        r0 = bk;
        r6 = "UNKNOWN";
        a(r0, r6);
        r0 = bj;
        r6 = "UNKNOWN";
        a(r0, r6);
        r0 = bn;
        r6 = "UNKNOWN";
        a(r0, r6);
        r0 = bo;
        r6 = "UNKNOWN";
        a(r0, r6);
        r0 = bp;
        r6 = "UNKNOWN";
    L_0x02af:
        a(r0, r6);
    L_0x02b2:
        r0 = aP;
        r6 = android.os.Build.BRAND;
        a(r0, r6);
        r0 = aQ;
        r6 = android.os.Build.BOARD;
        a(r0, r6);
        r0 = aR;
        r6 = android.os.Build.BOOTLOADER;
        a(r0, r6);
        r0 = aS;
        r6 = android.os.Build.MANUFACTURER;
        a(r0, r6);
        r0 = aT;
        r6 = android.os.Build.MODEL;
        a(r0, r6);
        r0 = aU;
        r6 = android.os.Build.HOST;
        a(r0, r6);
        r0 = aV;
        r6 = android.os.Build.HARDWARE;
        a(r0, r6);
        r0 = aW;
        r6 = android.os.Build.DEVICE;
        a(r0, r6);
        r0 = aX;
        r6 = android.os.Build.FINGERPRINT;
        a(r0, r6);
        r0 = aY;
        r6 = android.os.Build.DISPLAY;
        a(r0, r6);
        r0 = aZ;
        r6 = android.os.Build.USER;
        a(r0, r6);
        r0 = ba;
        r6 = android.os.Build.PRODUCT;
        a(r0, r6);
        r0 = bb;
        r6 = android.os.Build.TAGS;
        a(r0, r6);
        r0 = bc;
        r12 = android.os.Build.TIME;
        r6 = java.lang.Long.valueOf(r12);
        a(r0, r6);
        r0 = bf;
        r6 = android.os.Build.TYPE;
        a(r0, r6);
        r0 = be;
        r6 = android.os.Build.ID;
        a(r0, r6);
        r0 = bB;
        r6 = android.os.Build.VERSION.CODENAME;
        a(r0, r6);
        r0 = bC;
        r6 = android.os.Build.VERSION.RELEASE;
        a(r0, r6);
        r0 = bD;
        r6 = android.os.Build.VERSION.SDK_INT;
        r6 = java.lang.Integer.valueOf(r6);
        a(r0, r6);
        r0 = h;
        if (r0 < r9) goto L_0x035b;
    L_0x0343:
        r0 = bd;
        r6 = android.os.Build.getRadioVersion();
        a(r0, r6);
        r0 = bE;
        r6 = android.os.Build.VERSION.SECURITY_PATCH;
        a(r0, r6);
        r0 = bF;
        r6 = android.os.Build.VERSION.BASE_OS;
    L_0x0357:
        a(r0, r6);
        goto L_0x036e;
    L_0x035b:
        r0 = bd;
        r6 = "UNKNOWN";
        a(r0, r6);
        r0 = bE;
        r6 = "UNKNOWN";
        a(r0, r6);
        r0 = bF;
        r6 = "UNKNOWN";
        goto L_0x0357;
    L_0x036e:
        r0 = h;
        r6 = 26;
        if (r0 >= r6) goto L_0x037c;
    L_0x0374:
        r0 = aO;
        r12 = android.os.Build.SERIAL;
    L_0x0378:
        a(r0, r12);
        goto L_0x0381;
    L_0x037c:
        r0 = aO;
        r12 = "UNKNOWN";
        goto L_0x0378;
    L_0x0381:
        r0 = e;
        r12 = "connectivity";
        r0 = r0.getSystemService(r12);
        r0 = (android.net.ConnectivityManager) r0;
        r12 = l;
        if (r12 == 0) goto L_0x0439;
    L_0x038f:
        if (r0 == 0) goto L_0x0434;
    L_0x0391:
        r12 = r0.getAllNetworks();
        r13 = new org.json.JSONArray;
        r13.<init>();
        r14 = r12.length;
        r15 = r5;
    L_0x039c:
        if (r15 >= r14) goto L_0x042e;
    L_0x039e:
        r10 = r12[r15];
        r3 = new org.json.JSONObject;
        r3.<init>();
        r10 = r0.getNetworkCapabilities(r10);
        r9 = "CHK_NETWORK_CAPABILITIES_INFO";
        r6 = r10.toString();	 Catch:{ JSONException -> 0x041c }
        r3.put(r9, r6);	 Catch:{ JSONException -> 0x041c }
        r6 = "CHK_NETWORK_CAPABILITIES_INTERNET";
        r9 = 12;
        r9 = r10.hasCapability(r9);	 Catch:{ JSONException -> 0x041c }
        r3.put(r6, r9);	 Catch:{ JSONException -> 0x041c }
        r6 = "CHK_NETWORK_CAPABILITIES_NOT_VPN";
        r9 = 15;
        r9 = r10.hasCapability(r9);	 Catch:{ JSONException -> 0x041c }
        r3.put(r6, r9);	 Catch:{ JSONException -> 0x041c }
        r6 = java.lang.Integer.valueOf(r11);	 Catch:{ JSONException -> 0x041c }
        r9 = r10.hasTransport(r5);	 Catch:{ JSONException -> 0x041c }
        if (r9 == 0) goto L_0x03d7;
    L_0x03d2:
        r6 = java.lang.Integer.valueOf(r5);	 Catch:{ JSONException -> 0x041c }
        goto L_0x0413;
    L_0x03d7:
        r9 = r10.hasTransport(r4);	 Catch:{ JSONException -> 0x041c }
        if (r9 == 0) goto L_0x03e2;
    L_0x03dd:
        r6 = java.lang.Integer.valueOf(r4);	 Catch:{ JSONException -> 0x041c }
        goto L_0x0413;
    L_0x03e2:
        r9 = r10.hasTransport(r2);	 Catch:{ JSONException -> 0x041c }
        if (r9 == 0) goto L_0x03ed;
    L_0x03e8:
        r6 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x041c }
        goto L_0x0413;
    L_0x03ed:
        r9 = r10.hasTransport(r7);	 Catch:{ JSONException -> 0x041c }
        if (r9 == 0) goto L_0x03f8;
    L_0x03f3:
        r6 = java.lang.Integer.valueOf(r7);	 Catch:{ JSONException -> 0x041c }
        goto L_0x0413;
    L_0x03f8:
        r9 = r10.hasTransport(r1);	 Catch:{ JSONException -> 0x041c }
        if (r9 == 0) goto L_0x0403;
    L_0x03fe:
        r6 = java.lang.Integer.valueOf(r1);	 Catch:{ JSONException -> 0x041c }
        goto L_0x0413;
    L_0x0403:
        r9 = r10.hasTransport(r8);	 Catch:{ JSONException -> 0x041c }
        if (r9 == 0) goto L_0x0413;
    L_0x0409:
        r9 = h;	 Catch:{ JSONException -> 0x041c }
        r10 = 26;
        if (r9 < r10) goto L_0x0413;
    L_0x040f:
        r6 = java.lang.Integer.valueOf(r8);	 Catch:{ JSONException -> 0x041c }
    L_0x0413:
        r9 = "CHK_NETWORK_TRANSPORT_TYPE";
        r3.put(r9, r6);	 Catch:{ JSONException -> 0x041c }
        r13.put(r3);	 Catch:{ JSONException -> 0x041c }
        goto L_0x0423;
    L_0x041c:
        r3 = cC;
        r6 = "ERROR";
        a(r3, r6);
    L_0x0423:
        r15 = r15 + 1;
        r3 = 100;
        r6 = 26;
        r9 = 23;
        r10 = 0;
        goto L_0x039c;
    L_0x042e:
        r0 = cC;
        a(r0, r13);
        goto L_0x0440;
    L_0x0434:
        r0 = cC;
        r1 = "ERROR";
        goto L_0x043d;
    L_0x0439:
        r0 = cC;
        r1 = "NO_PERMISSION";
    L_0x043d:
        a(r0, r1);
    L_0x0440:
        r0 = bi;
        r1 = e;
        r1 = r1.getPackageName();
        a(r0, r1);
        r0 = bg;
        r6 = android.os.SystemClock.uptimeMillis();
        r1 = java.lang.Long.valueOf(r6);
        a(r0, r1);
        r0 = "android.os.SystemProperties";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x04bc }
        if (r0 == 0) goto L_0x04c3;
    L_0x0460:
        r1 = "get";
        r3 = new java.lang.Class[r4];	 Catch:{ Exception -> 0x04bc }
        r6 = java.lang.String.class;
        r3[r5] = r6;	 Catch:{ Exception -> 0x04bc }
        r1 = r0.getMethod(r1, r3);	 Catch:{ Exception -> 0x04bc }
        if (r1 == 0) goto L_0x04c3;
    L_0x046e:
        r3 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x04bc }
        r6 = "ro.hardware";
        r3[r5] = r6;	 Catch:{ Exception -> 0x04bc }
        r3 = r1.invoke(r0, r3);	 Catch:{ Exception -> 0x04bc }
        r6 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x04bc }
        r7 = "ro.kernel.qemu";
        r6[r5] = r7;	 Catch:{ Exception -> 0x04bc }
        r6 = r1.invoke(r0, r6);	 Catch:{ Exception -> 0x04bc }
        r7 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x04bc }
        r8 = "ro.product.model";
        r7[r5] = r8;	 Catch:{ Exception -> 0x04bc }
        r0 = r1.invoke(r0, r7);	 Catch:{ Exception -> 0x04bc }
        if (r3 == 0) goto L_0x0497;
    L_0x048e:
        r3 = (java.lang.String) r3;	 Catch:{ Exception -> 0x04bc }
        r1 = "goldfish";
        r1 = r3.contains(r1);	 Catch:{ Exception -> 0x04bc }
        goto L_0x0498;
    L_0x0497:
        r1 = r5;
    L_0x0498:
        if (r6 == 0) goto L_0x04aa;
    L_0x049a:
        r6 = (java.lang.String) r6;	 Catch:{ Exception -> 0x04bc }
        r3 = "1";
        r3 = r6.contains(r3);	 Catch:{ Exception -> 0x04bc }
        if (r3 != 0) goto L_0x04a9;
    L_0x04a4:
        if (r1 == 0) goto L_0x04a7;
    L_0x04a6:
        goto L_0x04a9;
    L_0x04a7:
        r1 = r5;
        goto L_0x04aa;
    L_0x04a9:
        r1 = r4;
    L_0x04aa:
        if (r0 == 0) goto L_0x04ba;
    L_0x04ac:
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x04bc }
        r3 = "sdk";
        r0 = r0.contains(r3);	 Catch:{ Exception -> 0x04bc }
        if (r0 != 0) goto L_0x04b8;
    L_0x04b6:
        if (r1 == 0) goto L_0x04c3;
    L_0x04b8:
        r0 = r4;
        goto L_0x04c4;
    L_0x04ba:
        r0 = r1;
        goto L_0x04c4;
    L_0x04bc:
        r0 = bh;
        r1 = "ERROR";
        a(r0, r1);
    L_0x04c3:
        r0 = r5;
    L_0x04c4:
        r1 = bh;
        r0 = java.lang.String.valueOf(r0);
        r0 = r0.toUpperCase();
        a(r1, r0);
        r0 = cq;
        r1 = java.util.TimeZone.getDefault();
        r1 = r1.getDisplayName();
        a(r0, r1);
        r0 = cp;
        r1 = java.util.TimeZone.getDefault();
        r1 = r1.getRawOffset();
        r1 = java.lang.Integer.valueOf(r1);
        a(r0, r1);
        r0 = e;
        r0 = r0.getResources();
        r0 = r0.getDisplayMetrics();
        r1 = cw;
        r3 = r0.density;
        r3 = java.lang.Float.valueOf(r3);
        a(r1, r3);
        r1 = cx;
        r3 = r0.densityDpi;
        r3 = java.lang.Integer.valueOf(r3);
        a(r1, r3);
        r1 = cz;
        r3 = r0.widthPixels;
        r3 = java.lang.Integer.valueOf(r3);
        a(r1, r3);
        r1 = cy;
        r3 = r0.heightPixels;
        r3 = java.lang.Integer.valueOf(r3);
        a(r1, r3);
        r1 = cA;
        r3 = r0.xdpi;
        r3 = java.lang.Float.valueOf(r3);
        a(r1, r3);
        r1 = cB;
        r0 = r0.ydpi;
        r0 = java.lang.Float.valueOf(r0);
        a(r1, r0);
        r0 = cD;	 Catch:{ MissingResourceException -> 0x0549 }
        r1 = java.util.Locale.getDefault();	 Catch:{ MissingResourceException -> 0x0549 }
        r1 = r1.getISO3Language();	 Catch:{ MissingResourceException -> 0x0549 }
        a(r0, r1);	 Catch:{ MissingResourceException -> 0x0549 }
        goto L_0x0550;
    L_0x0549:
        r0 = cD;
        r1 = "ERROR";
        a(r0, r1);
    L_0x0550:
        r0 = cE;	 Catch:{ MissingResourceException -> 0x055e }
        r1 = java.util.Locale.getDefault();	 Catch:{ MissingResourceException -> 0x055e }
        r1 = r1.getISO3Country();	 Catch:{ MissingResourceException -> 0x055e }
        a(r0, r1);	 Catch:{ MissingResourceException -> 0x055e }
        goto L_0x0565;
    L_0x055e:
        r0 = cE;
        r1 = "ERROR";
        a(r0, r1);
    L_0x0565:
        r0 = cF;
        r1 = java.util.Locale.getDefault();
        r1 = r1.getDisplayName();
        a(r0, r1);
        r0 = cs;
        r6 = android.net.TrafficStats.getTotalRxBytes();
        r1 = java.lang.Long.valueOf(r6);
        a(r0, r1);
        r0 = ct;
        r6 = android.net.TrafficStats.getTotalTxBytes();
        r1 = java.lang.Long.valueOf(r6);
        a(r0, r1);
        r0 = cu;
        r6 = android.net.TrafficStats.getMobileRxBytes();
        r1 = java.lang.Long.valueOf(r6);
        a(r0, r1);
        r0 = cv;
        r6 = android.net.TrafficStats.getMobileTxBytes();
        r1 = java.lang.Long.valueOf(r6);
        a(r0, r1);
        r0 = cG;
        r1 = f;
        a(r0, r1);
        r0 = cH;
        r1 = "B1.0.5.01";
        a(r0, r1);
        r0 = u;
        if (r0 == 0) goto L_0x0610;
    L_0x05b8:
        r0 = J;
        r1 = u;
        r1 = r1.isKeyguardLocked();
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = h;
        r1 = 22;
        if (r0 < r1) goto L_0x05dd;
    L_0x05cd:
        r0 = I;
        r1 = u;
        r1 = r1.isDeviceLocked();
        r1 = java.lang.String.valueOf(r1);
    L_0x05d9:
        a(r0, r1);
        goto L_0x05e2;
    L_0x05dd:
        r0 = I;
        r1 = "UNKNOWN";
        goto L_0x05d9;
    L_0x05e2:
        r0 = h;
        r1 = 23;
        if (r0 < r1) goto L_0x0604;
    L_0x05e8:
        r0 = K;
        r1 = u;
        r1 = r1.isDeviceSecure();
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = L;
        r1 = u;
        r1 = r1.isKeyguardSecure();
        r1 = java.lang.String.valueOf(r1);
        goto L_0x0629;
    L_0x0604:
        r0 = K;
        r1 = "UNKNOWN";
        a(r0, r1);
        r0 = L;
        r1 = "UNKNOWN";
        goto L_0x0629;
    L_0x0610:
        r0 = I;
        r1 = "ERROR";
        a(r0, r1);
        r0 = J;
        r1 = "ERROR";
        a(r0, r1);
        r0 = K;
        r1 = "ERROR";
        a(r0, r1);
        r0 = L;
        r1 = "ERROR";
    L_0x0629:
        a(r0, r1);
        r0 = new org.json.JSONArray;
        r0.<init>();
        r1 = "";
        r3 = java.net.NetworkInterface.getNetworkInterfaces();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        if (r3 == 0) goto L_0x06d2;
    L_0x0639:
        r6 = r3.hasMoreElements();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        if (r6 == 0) goto L_0x06cc;
    L_0x063f:
        r6 = r3.nextElement();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r6 = (java.net.NetworkInterface) r6;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r7 = r6.isUp();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        if (r7 == 0) goto L_0x0639;
    L_0x064b:
        r7 = new org.json.JSONObject;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r7.<init>();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r8 = r6.getHardwareAddress();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        if (r8 == 0) goto L_0x0684;
    L_0x0656:
        r1 = new java.lang.StringBuilder;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r1.<init>();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r9 = r5;
    L_0x065c:
        r10 = r8.length;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        if (r9 >= r10) goto L_0x0680;
    L_0x065f:
        r10 = "%02X%s";
        r12 = new java.lang.Object[r2];	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r13 = r8[r9];	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r13 = java.lang.Byte.valueOf(r13);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r12[r5] = r13;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r13 = r8.length;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r13 = r13 - r4;
        if (r9 >= r13) goto L_0x0672;
    L_0x066f:
        r13 = "-";
        goto L_0x0674;
    L_0x0672:
        r13 = "";
    L_0x0674:
        r12[r4] = r13;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r10 = java.lang.String.format(r10, r12);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r1.append(r10);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r9 = r9 + 1;
        goto L_0x065c;
    L_0x0680:
        r1 = r1.toString();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
    L_0x0684:
        r8 = "CHK_NETWORK_MAC";
        r7.put(r8, r1);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r8 = "CHK_NETWORK_VIRTUAL";
        r9 = r6.isVirtual();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r7.put(r8, r9);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r8 = new org.json.JSONArray;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r8.<init>();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r6 = r6.getInetAddresses();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
    L_0x069b:
        r9 = r6.hasMoreElements();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        if (r9 == 0) goto L_0x06c2;
    L_0x06a1:
        r9 = new org.json.JSONObject;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r9.<init>();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r10 = r6.nextElement();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r10 = (java.net.InetAddress) r10;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r12 = "CHK_NETWORK_IP";
        r13 = r10.getHostAddress();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r9.put(r12, r13);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r12 = "CHK_NETWORK_LOOPBACK";
        r10 = r10.isLoopbackAddress();	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r9.put(r12, r10);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r8.put(r9);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        goto L_0x069b;
    L_0x06c2:
        r6 = "CHK_INET_ADDRESSES";
        r7.put(r6, r8);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r0.put(r7);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        goto L_0x0639;
    L_0x06cc:
        r1 = cr;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        a(r1, r0);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        goto L_0x06e1;
    L_0x06d2:
        r0 = cr;	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        r1 = "";
        a(r0, r1);	 Catch:{ SocketException | JSONException -> 0x06da, SocketException | JSONException -> 0x06da }
        goto L_0x06e1;
    L_0x06da:
        r0 = cr;
        r1 = "ERROR";
        a(r0, r1);
    L_0x06e1:
        r0 = t;	 Catch:{ Exception -> 0x06f0 }
        r1 = e;	 Catch:{ Exception -> 0x06f0 }
        r1 = r1.getPackageName();	 Catch:{ Exception -> 0x06f0 }
        r3 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r10 = r0.getPackageInfo(r1, r3);	 Catch:{ Exception -> 0x06f0 }
        goto L_0x06f1;
    L_0x06f0:
        r10 = 0;
    L_0x06f1:
        r0 = new org.json.JSONArray;
        r0.<init>();
        r1 = t;	 Catch:{ Exception -> 0x071b }
        r3 = e;	 Catch:{ Exception -> 0x071b }
        r3 = r3.getPackageName();	 Catch:{ Exception -> 0x071b }
        r1 = r1.getPackageInfo(r3, r4);	 Catch:{ Exception -> 0x071b }
        r1 = r1.activities;	 Catch:{ Exception -> 0x071b }
        if (r1 == 0) goto L_0x0722;
    L_0x0706:
        r3 = r1.length;	 Catch:{ Exception -> 0x071b }
        r6 = 100;
        if (r3 >= r6) goto L_0x070d;
    L_0x070b:
        r3 = r1.length;	 Catch:{ Exception -> 0x071b }
        goto L_0x070e;
    L_0x070d:
        r3 = r6;
    L_0x070e:
        r6 = r5;
    L_0x070f:
        if (r6 >= r3) goto L_0x0722;
    L_0x0711:
        r7 = r1[r6];	 Catch:{ Exception -> 0x071b }
        r7 = r7.name;	 Catch:{ Exception -> 0x071b }
        r0.put(r7);	 Catch:{ Exception -> 0x071b }
        r6 = r6 + 1;
        goto L_0x070f;
    L_0x071b:
        r1 = E;
        r3 = "UNKNOWN";
        a(r1, r3);
    L_0x0722:
        r1 = E;
        a(r1, r0);
        r0 = new org.json.JSONArray;
        r0.<init>();
        if (r10 == 0) goto L_0x0744;
    L_0x072e:
        r1 = r10.requestedPermissions;
        if (r1 == 0) goto L_0x073e;
    L_0x0732:
        r3 = r1.length;
        r6 = r5;
    L_0x0734:
        if (r6 >= r3) goto L_0x073e;
    L_0x0736:
        r7 = r1[r6];
        r0.put(r7);
        r6 = r6 + 1;
        goto L_0x0734;
    L_0x073e:
        r1 = H;
        a(r1, r0);
        goto L_0x074b;
    L_0x0744:
        r0 = H;
        r1 = "ERROR";
        a(r0, r1);
    L_0x074b:
        if (r10 == 0) goto L_0x076f;
    L_0x074d:
        r0 = Y;
        r6 = r10.firstInstallTime;
        r1 = java.lang.Long.valueOf(r6);
        a(r0, r1);
        r0 = cm;
        r6 = r10.lastUpdateTime;
        r1 = java.lang.Long.valueOf(r6);
        a(r0, r1);
        r0 = cn;
        r1 = r10.versionCode;
        r1 = java.lang.Integer.valueOf(r1);
    L_0x076b:
        a(r0, r1);
        goto L_0x0782;
    L_0x076f:
        r0 = Y;
        r1 = "ERROR";
        a(r0, r1);
        r0 = cm;
        r1 = "ERROR";
        a(r0, r1);
        r0 = cn;
        r1 = "ERROR";
        goto L_0x076b;
    L_0x0782:
        r0 = Z;
        r1 = t;
        r3 = "android.hardware.touchscreen.multitouch";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = aa;
        r1 = t;
        r3 = "android.hardware.touchscreen.multitouch.distinct";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = ab;
        r1 = t;
        r3 = "android.hardware.touchscreen.multitouch.jazzhand";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = bL;
        r1 = t;
        r3 = "android.hardware.touchscreen";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = bM;
        r1 = t;
        r3 = "android.hardware.faketouch";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = bO;
        r1 = t;
        r3 = "android.hardware.faketouch.multitouch.jazzhand";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = bN;
        r1 = t;
        r3 = "android.hardware.faketouch.multitouch.distinct";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = ac;
        r1 = t;
        r3 = "android.hardware.camera";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = ad;
        r1 = t;
        r3 = "android.hardware.camera.front";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = ae;
        r1 = t;
        r3 = "android.hardware.camera.flash";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = af;
        r1 = t;
        r3 = "android.hardware.sensor.accelerometer";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = ah;
        r1 = t;
        r3 = "android.hardware.sensor.barometer";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = ai;
        r1 = t;
        r3 = "android.hardware.sensor.gyroscope";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = aj;
        r1 = t;
        r3 = "android.hardware.sensor.compass";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = am;
        r1 = t;
        r3 = "android.hardware.sensor.proximity";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = ag;
        r1 = t;
        r3 = "android.hardware.sensor.stepcounter";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = al;
        r1 = t;
        r3 = "android.hardware.sensor.stepdetector";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.Boolean.valueOf(r1);
        a(r0, r1);
        r0 = ak;
        r1 = t;
        r3 = "android.hardware.sensor.heartrate";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = an;
        r1 = t;
        r3 = "android.hardware.sensor.ambient_temperature";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = h;
        r1 = 23;
        if (r0 < r1) goto L_0x08dd;
    L_0x08cb:
        r0 = bP;
        r1 = t;
        r3 = "android.hardware.sensor.hifi_sensors";
        r1 = r1.hasSystemFeature(r3);
        r1 = java.lang.String.valueOf(r1);
    L_0x08d9:
        a(r0, r1);
        goto L_0x08e2;
    L_0x08dd:
        r0 = bP;
        r1 = "UNKNOWN";
        goto L_0x08d9;
    L_0x08e2:
        r0 = new java.util.ArrayList;
        r1 = new java.lang.String[r2];
        r2 = "com.android.vending";
        r1[r5] = r2;
        r2 = "com.google.android.feedback";
        r1[r4] = r2;
        r1 = java.util.Arrays.asList(r1);
        r0.<init>(r1);
        r1 = t;
        r2 = e;
        r2 = r2.getPackageName();
        r1 = r1.getInstallerPackageName(r2);
        r2 = ao;
        if (r1 == 0) goto L_0x090c;
    L_0x0905:
        r0 = r0.contains(r1);
        if (r0 == 0) goto L_0x090c;
    L_0x090b:
        goto L_0x090d;
    L_0x090c:
        r4 = r5;
    L_0x090d:
        r0 = java.lang.Boolean.valueOf(r4);
        a(r2, r0);
        r0 = ap;
        r1 = t;
        r2 = e;
        r2 = r2.getPackageName();
        r1 = r1.getInstallerPackageName(r2);
        r1 = java.lang.String.valueOf(r1);
        r1 = r1.toUpperCase();
        a(r0, r1);
        r0 = t;	 Catch:{ Exception -> 0x094e }
        r1 = e;	 Catch:{ Exception -> 0x094e }
        r1 = r1.getPackageName();	 Catch:{ Exception -> 0x094e }
        r0 = r0.getApplicationInfo(r1, r5);	 Catch:{ Exception -> 0x094e }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x094e }
        r0 = r0.publicSourceDir;	 Catch:{ Exception -> 0x094e }
        r1.<init>(r0);	 Catch:{ Exception -> 0x094e }
        r0 = bz;	 Catch:{ Exception -> 0x094e }
        r1 = r1.length();	 Catch:{ Exception -> 0x094e }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ Exception -> 0x094e }
        a(r0, r1);	 Catch:{ Exception -> 0x094e }
        goto L_0x0955;
    L_0x094e:
        r0 = bz;
        r1 = "ERROR";
        a(r0, r1);
    L_0x0955:
        r0 = t;	 Catch:{ Exception -> 0x0969 }
        r1 = e;	 Catch:{ Exception -> 0x0969 }
        r1 = r1.getPackageName();	 Catch:{ Exception -> 0x0969 }
        r0 = r0.getApplicationInfo(r1, r5);	 Catch:{ Exception -> 0x0969 }
        r1 = bA;	 Catch:{ Exception -> 0x0969 }
        r0 = r0.publicSourceDir;	 Catch:{ Exception -> 0x0969 }
        a(r1, r0);	 Catch:{ Exception -> 0x0969 }
        goto L_0x0970;
    L_0x0969:
        r0 = bA;
        r1 = "ERROR";
        a(r0, r1);
    L_0x0970:
        r0 = e;
        r0 = r0.getPackageManager();
        r1 = "android.hardware.bluetooth";
        r0 = r0.hasSystemFeature(r1);
        r1 = ar;
        r2 = java.lang.Boolean.valueOf(r0);
        a(r1, r2);
        r1 = e;
        r1 = r1.getPackageManager();
        r2 = "android.hardware.wifi";
        r1 = r1.hasSystemFeature(r2);
        r2 = bJ;
        r3 = java.lang.Boolean.valueOf(r1);
        a(r2, r3);
        r2 = bK;
        r3 = e;
        r3 = r3.getPackageManager();
        r4 = "android.hardware.telephony";
        r3 = r3.hasSystemFeature(r4);
        r3 = java.lang.Boolean.valueOf(r3);
        a(r2, r3);
        r2 = bQ;
        r3 = e;
        r3 = r3.getPackageManager();
        r4 = "android.hardware.telephony.cdma";
        r3 = r3.hasSystemFeature(r4);
        r3 = java.lang.Boolean.valueOf(r3);
        a(r2, r3);
        r2 = bR;
        r3 = e;
        r3 = r3.getPackageManager();
        r4 = "android.hardware.telephony.gsm";
        r3 = r3.hasSystemFeature(r4);
        r3 = java.lang.Boolean.valueOf(r3);
        a(r2, r3);
        r2 = e;
        r2 = r2.getPackageManager();
        r3 = "android.hardware.bluetooth";
        r2 = r2.hasSystemFeature(r3);
        r3 = as;
        r4 = java.lang.Boolean.valueOf(r2);
        a(r3, r4);
        r3 = bS;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.type.television";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.Boolean.valueOf(r4);
        a(r3, r4);
        r3 = bU;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.type.watch";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.String.valueOf(r4);
        a(r3, r4);
        r3 = bW;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.software.live_tv";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.String.valueOf(r4);
        a(r3, r4);
        r3 = bV;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.gamepad";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.String.valueOf(r4);
        a(r3, r4);
        r3 = h;
        r4 = 23;
        if (r3 < r4) goto L_0x0a5e;
    L_0x0a48:
        r3 = bX;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.type.automotive";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.String.valueOf(r4);
    L_0x0a5a:
        a(r3, r4);
        goto L_0x0a63;
    L_0x0a5e:
        r3 = bX;
        r4 = "UNKNOWN";
        goto L_0x0a5a;
    L_0x0a63:
        r3 = h;
        r4 = 26;
        if (r3 < r4) goto L_0x0a7f;
    L_0x0a69:
        r3 = bY;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.type.embedded";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.String.valueOf(r4);
    L_0x0a7b:
        a(r3, r4);
        goto L_0x0a84;
    L_0x0a7f:
        r3 = bY;
        r4 = "UNKNOWN";
        goto L_0x0a7b;
    L_0x0a84:
        r3 = ca;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.screen.landscape";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.Boolean.valueOf(r4);
        a(r3, r4);
        r3 = bZ;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.screen.portrait";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.Boolean.valueOf(r4);
        a(r3, r4);
        r3 = h;
        r4 = 26;
        if (r3 < r4) goto L_0x0aca;
    L_0x0ab4:
        r3 = cb;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.software.activities_on_secondary_displays";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.String.valueOf(r4);
    L_0x0ac6:
        a(r3, r4);
        goto L_0x0acf;
    L_0x0aca:
        r3 = cb;
        r4 = "UNKNOWN";
        goto L_0x0ac6;
    L_0x0acf:
        r3 = cc;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.nfc";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.Boolean.valueOf(r4);
        a(r3, r4);
        r3 = cd;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.usb.host";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.Boolean.valueOf(r4);
        a(r3, r4);
        r3 = ce;
        r4 = e;
        r4 = r4.getPackageManager();
        r6 = "android.hardware.wifi.direct";
        r4 = r4.hasSystemFeature(r6);
        r4 = java.lang.Boolean.valueOf(r4);
        a(r3, r4);
        r3 = h;
        r4 = 24;
        if (r3 < r4) goto L_0x0b2a;
    L_0x0b14:
        r3 = cf;
        r6 = e;
        r6 = r6.getPackageManager();
        r7 = "android.hardware.ethernet";
        r6 = r6.hasSystemFeature(r7);
        r6 = java.lang.String.valueOf(r6);
    L_0x0b26:
        a(r3, r6);
        goto L_0x0b2f;
    L_0x0b2a:
        r3 = cf;
        r6 = "UNKNOWN";
        goto L_0x0b26;
    L_0x0b2f:
        r3 = cg;
        r6 = e;
        r6 = r6.getPackageManager();
        r7 = "android.software.app_widgets";
        r6 = r6.hasSystemFeature(r7);
        r6 = java.lang.Boolean.valueOf(r6);
        a(r3, r6);
        r3 = ch;
        r6 = e;
        r6 = r6.getPackageManager();
        r7 = "android.hardware.opengles.aep";
        r6 = r6.hasSystemFeature(r7);
        r6 = java.lang.String.valueOf(r6);
        a(r3, r6);
        r3 = ci;
        r6 = e;
        r6 = r6.getPackageManager();
        r7 = "android.hardware.microphone";
        r6 = r6.hasSystemFeature(r7);
        r6 = java.lang.Boolean.valueOf(r6);
        a(r3, r6);
        r3 = bT;
        r6 = e;
        r6 = r6.getPackageManager();
        r7 = "android.software.print";
        r6 = r6.hasSystemFeature(r7);
        r6 = java.lang.String.valueOf(r6);
        a(r3, r6);
        r3 = h;
        r6 = 23;
        if (r3 < r6) goto L_0x0b9f;
    L_0x0b89:
        r3 = cj;
        r6 = e;
        r6 = r6.getPackageManager();
        r7 = "android.hardware.fingerprint";
        r6 = r6.hasSystemFeature(r7);
        r6 = java.lang.String.valueOf(r6);
    L_0x0b9b:
        a(r3, r6);
        goto L_0x0ba4;
    L_0x0b9f:
        r3 = cj;
        r6 = "UNKNOWN";
        goto L_0x0b9b;
    L_0x0ba4:
        r3 = p;
        if (r3 == 0) goto L_0x0bb3;
    L_0x0ba8:
        r3 = p;
        r3 = r3.isInteractive();
        r10 = java.lang.Boolean.valueOf(r3);
        goto L_0x0bb4;
    L_0x0bb3:
        r10 = 0;
    L_0x0bb4:
        r3 = aL;
        r6 = java.lang.String.valueOf(r10);
        r6 = r6.toUpperCase();
        a(r3, r6);
        r3 = p;
        if (r3 == 0) goto L_0x0bdd;
    L_0x0bc5:
        r3 = h;
        r6 = 23;
        if (r3 < r6) goto L_0x0bd8;
    L_0x0bcb:
        r3 = aM;
        r6 = p;
        r6 = r6.isDeviceIdleMode();
        r6 = java.lang.String.valueOf(r6);
        goto L_0x0be1;
    L_0x0bd8:
        r3 = aM;
        r6 = "UNKNOWN";
        goto L_0x0be1;
    L_0x0bdd:
        r3 = aM;
        r6 = "ERROR";
    L_0x0be1:
        a(r3, r6);
        r3 = p;
        if (r3 == 0) goto L_0x0bf8;
    L_0x0be8:
        r3 = aN;
        r6 = p;
        r6 = r6.isPowerSaveMode();
        r6 = java.lang.String.valueOf(r6);
    L_0x0bf4:
        a(r3, r6);
        goto L_0x0bfd;
    L_0x0bf8:
        r3 = aN;
        r6 = "ERROR";
        goto L_0x0bf4;
    L_0x0bfd:
        r3 = D;
        r6 = java.lang.Runtime.getRuntime();
        r6 = r6.availableProcessors();
        r6 = java.lang.Integer.valueOf(r6);
        a(r3, r6);
        r3 = G;
        r6 = java.lang.Runtime.getRuntime();
        r6 = r6.totalMemory();
        r6 = java.lang.Long.valueOf(r6);
        a(r3, r6);
        r3 = e;
        r3 = r3.getContentResolver();
        r6 = "adb_enabled";
        r3 = android.provider.Settings.Global.getString(r3, r6);
        r3 = java.lang.String.valueOf(r3);
        r6 = U;
        r7 = "1";
        r3 = r3.equals(r7);
        r3 = java.lang.Boolean.valueOf(r3);
        a(r6, r3);
        r3 = h;
        if (r3 < r4) goto L_0x0c5d;
    L_0x0c42:
        r3 = e;	 Catch:{ SettingNotFoundException -> 0x0c58 }
        r3 = r3.getContentResolver();	 Catch:{ SettingNotFoundException -> 0x0c58 }
        r4 = "boot_count";
        r3 = android.provider.Settings.Global.getInt(r3, r4);	 Catch:{ SettingNotFoundException -> 0x0c58 }
        r4 = X;	 Catch:{ SettingNotFoundException -> 0x0c58 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ SettingNotFoundException -> 0x0c58 }
        a(r4, r3);	 Catch:{ SettingNotFoundException -> 0x0c58 }
        goto L_0x0c64;
    L_0x0c58:
        r3 = X;
        r4 = "ERROR";
        goto L_0x0c61;
    L_0x0c5d:
        r3 = X;
        r4 = "UNKNOWN";
    L_0x0c61:
        a(r3, r4);
    L_0x0c64:
        r3 = au;
        r4 = e;
        r4 = r4.getContentResolver();
        r6 = "stay_on_while_plugged_in";
        r4 = android.provider.Settings.Global.getString(r4, r6);
        a(r3, r4);
        r3 = T;
        r4 = e;
        r4 = r4.getContentResolver();
        r6 = "accessibility_enabled";
        r4 = android.provider.Settings.Secure.getInt(r4, r6, r5);
        r4 = java.lang.Integer.valueOf(r4);
        a(r3, r4);
        if (r0 != 0) goto L_0x0c94;
    L_0x0c8c:
        if (r2 == 0) goto L_0x0c8f;
    L_0x0c8e:
        goto L_0x0c94;
    L_0x0c8f:
        r0 = aq;
        r2 = "FALSE";
        goto L_0x0ca6;
    L_0x0c94:
        r0 = aq;
        r2 = e;
        r2 = r2.getContentResolver();
        r3 = "bluetooth_on";
        r2 = android.provider.Settings.Secure.getInt(r2, r3, r5);
        r2 = java.lang.Integer.valueOf(r2);
    L_0x0ca6:
        a(r0, r2);
        r0 = ax;
        r2 = e;
        r2 = r2.getContentResolver();
        r3 = "install_non_market_apps";
        r2 = android.provider.Settings.Secure.getInt(r2, r3, r5);
        r2 = java.lang.Integer.valueOf(r2);
        a(r0, r2);
        r0 = aw;
        r2 = e;
        r2 = r2.getContentResolver();
        r3 = "usb_mass_storage_enabled";
        r2 = android.provider.Settings.Secure.getInt(r2, r3, r5);
        r2 = java.lang.Integer.valueOf(r2);
        a(r0, r2);
        r0 = at;
        r2 = e;
        r2 = r2.getContentResolver();
        r3 = "development_settings_enabled";
        r2 = android.provider.Settings.Secure.getInt(r2, r3, r5);
        r2 = java.lang.Integer.valueOf(r2);
        a(r0, r2);
        if (r1 == 0) goto L_0x0d00;
    L_0x0cea:
        r0 = av;
        r1 = e;
        r1 = r1.getContentResolver();
        r2 = "wifi_on";
        r1 = android.provider.Settings.Secure.getInt(r1, r2, r5);
        r1 = java.lang.Integer.valueOf(r1);
    L_0x0cfc:
        a(r0, r1);
        goto L_0x0d05;
    L_0x0d00:
        r0 = av;
        r1 = "FALSE";
        goto L_0x0cfc;
    L_0x0d05:
        r0 = java.lang.Integer.valueOf(r11);
        r1 = e;	 Catch:{ SettingNotFoundException -> 0x0d1a }
        r1 = r1.getContentResolver();	 Catch:{ SettingNotFoundException -> 0x0d1a }
        r2 = "screen_brightness";
        r1 = android.provider.Settings.System.getInt(r1, r2);	 Catch:{ SettingNotFoundException -> 0x0d1a }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SettingNotFoundException -> 0x0d1a }
        r0 = r1;
    L_0x0d1a:
        r1 = V;
        a(r1, r0);
        r0 = W;
        r1 = e;
        r1 = r1.getContentResolver();
        r2 = "screen_off_timeout";
        r1 = android.provider.Settings.System.getString(r1, r2);
        a(r0, r1);
        r0 = bq;
        r1 = "http.proxyHost";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = br;
        r1 = "http.proxyPort";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = bs;
        r1 = "http.proxyUser";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = bt;
        r1 = "http.nonProxyHosts";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = bu;
        r1 = "http.agent";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = bv;
        r1 = "socksProxyHost";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = bw;
        r1 = "socksProxyPort";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = bx;
        r1 = "socksProxyVersion";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = by;
        r1 = "java.net.socks.username";
        r1 = java.lang.System.getProperty(r1);
        r1 = java.lang.String.valueOf(r1);
        a(r0, r1);
        r0 = bG;
        r1 = "os.arch";
        r1 = java.lang.System.getProperty(r1);
        a(r0, r1);
        r0 = bH;
        r1 = "os.name";
        r1 = java.lang.System.getProperty(r1);
        a(r0, r1);
        r0 = bI;
        r1 = "os.version";
        r1 = java.lang.System.getProperty(r1);
        a(r0, r1);
        r0 = q;
        if (r0 == 0) goto L_0x0dec;
    L_0x0ddc:
        r0 = M;
        r1 = q;
        r1 = r1.getNetworkType();
        r1 = java.lang.Integer.valueOf(r1);
    L_0x0de8:
        a(r0, r1);
        goto L_0x0df1;
    L_0x0dec:
        r0 = M;
        r1 = "ERROR";
        goto L_0x0de8;
    L_0x0df1:
        r0 = q;
        if (r0 == 0) goto L_0x0e01;
    L_0x0df5:
        r0 = N;
        r1 = q;
        r1 = r1.getSimOperatorName();
    L_0x0dfd:
        a(r0, r1);
        goto L_0x0e06;
    L_0x0e01:
        r0 = N;
        r1 = "ERROR";
        goto L_0x0dfd;
    L_0x0e06:
        r0 = q;
        if (r0 == 0) goto L_0x0e1a;
    L_0x0e0a:
        r0 = O;
        r1 = q;
        r1 = r1.getPhoneType();
        r1 = java.lang.Integer.valueOf(r1);
    L_0x0e16:
        a(r0, r1);
        goto L_0x0e1f;
    L_0x0e1a:
        r0 = O;
        r1 = "ERROR";
        goto L_0x0e16;
    L_0x0e1f:
        r0 = q;
        if (r0 == 0) goto L_0x0e2f;
    L_0x0e23:
        r0 = Q;
        r1 = q;
        r1 = r1.getNetworkOperatorName();
    L_0x0e2b:
        a(r0, r1);
        goto L_0x0e34;
    L_0x0e2f:
        r0 = Q;
        r1 = "ERROR";
        goto L_0x0e2b;
    L_0x0e34:
        r0 = q;
        if (r0 == 0) goto L_0x0e44;
    L_0x0e38:
        r0 = R;
        r1 = q;
        r1 = r1.getSimCountryIso();
    L_0x0e40:
        a(r0, r1);
        goto L_0x0e49;
    L_0x0e44:
        r0 = R;
        r1 = "ERROR";
        goto L_0x0e40;
    L_0x0e49:
        r0 = q;
        if (r0 == 0) goto L_0x0e59;
    L_0x0e4d:
        r0 = P;
        r1 = q;
        r1 = r1.getNetworkOperator();
    L_0x0e55:
        a(r0, r1);
        goto L_0x0e5e;
    L_0x0e59:
        r0 = P;
        r1 = "ERROR";
        goto L_0x0e55;
    L_0x0e5e:
        r0 = h;
        r1 = 26;
        if (r0 < r1) goto L_0x0e88;
    L_0x0e64:
        r0 = q;
        if (r0 == 0) goto L_0x0e83;
    L_0x0e68:
        r0 = l;
        if (r0 != 0) goto L_0x0e76;
    L_0x0e6c:
        r0 = m;
        if (r0 == 0) goto L_0x0e71;
    L_0x0e70:
        goto L_0x0e76;
    L_0x0e71:
        r0 = S;
        r1 = "NO_PERMISSION";
        goto L_0x0e8c;
    L_0x0e76:
        r0 = S;
        r1 = q;
        r1 = r1.isDataEnabled();
        r1 = java.lang.Boolean.valueOf(r1);
        goto L_0x0e8c;
    L_0x0e83:
        r0 = S;
        r1 = "ERROR";
        goto L_0x0e8c;
    L_0x0e88:
        r0 = S;
        r1 = "UNKNOWN";
    L_0x0e8c:
        a(r0, r1);
        r0 = q;
        if (r0 == 0) goto L_0x0ea3;
    L_0x0e93:
        r0 = ck;
        r1 = q;
        r1 = r1.getSimState();
        r1 = java.lang.Integer.valueOf(r1);
    L_0x0e9f:
        a(r0, r1);
        goto L_0x0ea8;
    L_0x0ea3:
        r0 = ck;
        r1 = "ERROR";
        goto L_0x0e9f;
    L_0x0ea8:
        r0 = n;
        if (r0 == 0) goto L_0x0ee9;
    L_0x0eac:
        r0 = r;
        if (r0 == 0) goto L_0x0ee4;
    L_0x0eb0:
        r0 = r;
        r0 = r0.isWifiEnabled();
        if (r0 == 0) goto L_0x0edf;
    L_0x0eb8:
        r0 = r;
        r0 = r0.getConnectionInfo();
        if (r0 == 0) goto L_0x0eda;
    L_0x0ec0:
        r1 = r0.getSupplicantState();
        r1 = android.net.wifi.WifiInfo.getDetailedStateOf(r1);
        r2 = android.net.NetworkInfo.DetailedState.CONNECTED;
        if (r1 == r2) goto L_0x0ed0;
    L_0x0ecc:
        r2 = android.net.NetworkInfo.DetailedState.OBTAINING_IPADDR;
        if (r1 != r2) goto L_0x0ef0;
    L_0x0ed0:
        r1 = aJ;
        r0 = r0.getSSID();
        a(r1, r0);
        goto L_0x0ef0;
    L_0x0eda:
        r0 = aJ;
        r1 = "NULL";
        goto L_0x0eed;
    L_0x0edf:
        r0 = aJ;
        r1 = "UNKNOWN";
        goto L_0x0eed;
    L_0x0ee4:
        r0 = aJ;
        r1 = "ERROR";
        goto L_0x0eed;
    L_0x0ee9:
        r0 = aJ;
        r1 = "NO_PERMISSION";
    L_0x0eed:
        a(r0, r1);
    L_0x0ef0:
        r0 = n;
        if (r0 == 0) goto L_0x0f31;
    L_0x0ef4:
        r0 = r;
        if (r0 == 0) goto L_0x0f29;
    L_0x0ef8:
        r0 = r;
        r0 = r0.isWifiEnabled();
        if (r0 == 0) goto L_0x0f21;
    L_0x0f00:
        r0 = r;
        r0 = r0.getConnectionInfo();
        if (r0 == 0) goto L_0x0f1a;
    L_0x0f08:
        r0 = r0.getSupplicantState();
        r0 = android.net.wifi.WifiInfo.getDetailedStateOf(r0);
        r1 = aK;
        r0 = r0.toString();
        a(r1, r0);
        return;
    L_0x0f1a:
        r0 = aK;
        r1 = 0;
        a(r0, r1);
        return;
    L_0x0f21:
        r0 = aK;
        r1 = "UNKNOWN";
        a(r0, r1);
        return;
    L_0x0f29:
        r0 = aK;
        r1 = "ERROR";
        a(r0, r1);
        return;
    L_0x0f31:
        r0 = aK;
        r1 = "NO_PERMISSION";
        a(r0, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j.a.a.b():void");
    }
}
