package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.util.JsonWriter;
import com.facebook.internal.NativeProtocol;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.api.client.http.HttpStatusCodes;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

@zzark
public final class zzbax {
    private static Object sLock = new Object();
    private static Clock zzbrs = DefaultClock.getInstance();
    @GuardedBy("sLock")
    private static boolean zzeom = false;
    @GuardedBy("sLock")
    private static boolean zzeon = false;
    private static final Set<String> zzeoo = new HashSet(Arrays.asList(new String[0]));
    private final List<String> zzeop;

    public zzbax() {
        this(null);
    }

    public zzbax(@Nullable String str) {
        List asList;
        if (isEnabled()) {
            String uuid = UUID.randomUUID().toString();
            if (str == null) {
                String[] strArr = new String[1];
                String str2 = "network_request_";
                uuid = String.valueOf(uuid);
                strArr[0] = uuid.length() != 0 ? str2.concat(uuid) : new String(str2);
                asList = Arrays.asList(strArr);
            } else {
                String[] strArr2 = new String[2];
                String str3 = "ad_request_";
                str = String.valueOf(str);
                strArr2[0] = str.length() != 0 ? str3.concat(str) : new String(str3);
                str = "network_request_";
                uuid = String.valueOf(uuid);
                strArr2[1] = uuid.length() != 0 ? str.concat(uuid) : new String(str);
                asList = Arrays.asList(strArr2);
            }
        } else {
            asList = new ArrayList();
        }
        this.zzeop = asList;
    }

    public final void zza(HttpURLConnection httpURLConnection, @Nullable byte[] bArr) {
        if (isEnabled()) {
            Map map;
            if (httpURLConnection.getRequestProperties() == null) {
                map = null;
            } else {
                map = new HashMap(httpURLConnection.getRequestProperties());
            }
            zzb(new String(httpURLConnection.getURL().toString()), new String(httpURLConnection.getRequestMethod()), map, bArr);
        }
    }

    public final void zza(String str, String str2, @Nullable Map<String, ?> map, @Nullable byte[] bArr) {
        if (isEnabled()) {
            zzb(str, str2, map, bArr);
        }
    }

    private final void zzb(String str, String str2, @Nullable Map<String, ?> map, @Nullable byte[] bArr) {
        zza("onNetworkRequest", new zzbay(str, str2, map, bArr));
    }

    public final void zza(HttpURLConnection httpURLConnection, int i) {
        String responseMessage;
        if (isEnabled()) {
            zzb(httpURLConnection.getHeaderFields() == null ? null : new HashMap(httpURLConnection.getHeaderFields()), i);
            if (i < 200 || i >= HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES) {
                try {
                    responseMessage = httpURLConnection.getResponseMessage();
                } catch (IOException e) {
                    String str = "Can not get error message from error HttpURLConnection\n";
                    responseMessage = String.valueOf(e.getMessage());
                    zzbbd.zzeo(responseMessage.length() != 0 ? str.concat(responseMessage) : new String(str));
                    responseMessage = null;
                }
                zzel(responseMessage);
            }
        }
    }

    public final void zza(@Nullable Map<String, ?> map, int i) {
        if (isEnabled()) {
            zzb(map, i);
            if (i < 200 || i >= HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES) {
                zzel(null);
            }
        }
    }

    private final void zzb(@Nullable Map<String, ?> map, int i) {
        zza("onNetworkResponse", new zzbaz(i, map));
    }

    public final void zzek(@Nullable String str) {
        if (isEnabled() && str != null) {
            zzi(str.getBytes());
        }
    }

    public final void zzi(byte[] bArr) {
        zza("onNetworkResponseBody", new zzbba(bArr));
    }

    private final void zzel(@Nullable String str) {
        zza("onNetworkRequestError", new zzbbb(str));
    }

    private static void zza(JsonWriter jsonWriter, @Nullable Map<String, ?> map) throws IOException {
        if (map != null) {
            jsonWriter.name("headers").beginArray();
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (!zzeoo.contains(str)) {
                    if (!(entry.getValue() instanceof List)) {
                        if (!(entry.getValue() instanceof String)) {
                            zzbbd.e("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                            break;
                        }
                        jsonWriter.beginObject();
                        jsonWriter.name("name").value(str);
                        jsonWriter.name("value").value((String) entry.getValue());
                        jsonWriter.endObject();
                    } else {
                        for (String str2 : (List) entry.getValue()) {
                            jsonWriter.beginObject();
                            jsonWriter.name("name").value(str);
                            jsonWriter.name("value").value(str2);
                            jsonWriter.endObject();
                        }
                    }
                }
            }
            jsonWriter.endArray();
        }
    }

    private final void zza(String str, zzbbc zzbbc) {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            jsonWriter.beginObject();
            jsonWriter.name(AvidJSONUtil.KEY_TIMESTAMP).value(zzbrs.currentTimeMillis());
            jsonWriter.name("event").value(str);
            jsonWriter.name("components").beginArray();
            for (String value : this.zzeop) {
                jsonWriter.value(value);
            }
            jsonWriter.endArray();
            zzbbc.zza(jsonWriter);
            jsonWriter.endObject();
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            zzbbd.zzb("unable to log", e);
        }
        zzem(stringWriter.toString());
    }

    private static synchronized void zzem(String str) {
        synchronized (zzbax.class) {
            zzbbd.zzen("GMA Debug BEGIN");
            int i = 0;
            while (i < str.length()) {
                int i2 = i + SsoErrorCodes.SDK_NOT_INITIALIZED;
                String str2 = "GMA Debug CONTENT ";
                String valueOf = String.valueOf(str.substring(i, Math.min(i2, str.length())));
                zzbbd.zzen(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                i = i2;
            }
            zzbbd.zzen("GMA Debug FINISH");
        }
    }

    public static void zzaat() {
        synchronized (sLock) {
            zzeom = false;
            zzeon = false;
            zzbbd.zzeo("Ad debug logging enablement is out of date.");
        }
    }

    public static void zzaq(boolean z) {
        synchronized (sLock) {
            zzeom = true;
            zzeon = z;
        }
    }

    public static boolean zzaau() {
        boolean z;
        synchronized (sLock) {
            z = zzeom;
        }
        return z;
    }

    public static boolean isEnabled() {
        boolean z;
        synchronized (sLock) {
            z = zzeom && zzeon;
        }
        return z;
    }

    public static boolean zzbl(Context context) {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcsn)).booleanValue()) {
            return false;
        }
        try {
            if (Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            zzbbd.zzc("Fail to determine debug setting.", e);
            return false;
        }
    }

    static final /* synthetic */ void zza(int i, Map map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name(NativeProtocol.WEB_DIALOG_PARAMS).beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("code").value((long) i);
        jsonWriter.endObject();
        zza(jsonWriter, map);
        jsonWriter.endObject();
    }

    static final /* synthetic */ void zza(String str, String str2, Map map, byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name(NativeProtocol.WEB_DIALOG_PARAMS).beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("uri").value(str);
        jsonWriter.name("verb").value(str2);
        jsonWriter.endObject();
        zza(jsonWriter, map);
        if (bArr != null) {
            jsonWriter.name("body").value(Base64Utils.encode(bArr));
        }
        jsonWriter.endObject();
    }
}
