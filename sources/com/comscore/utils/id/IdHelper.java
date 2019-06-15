package com.comscore.utils.id;

import android.annotation.SuppressLint;
import android.content.Context;
import com.comscore.analytics.Core;
import com.comscore.android.id.DeviceId;
import com.comscore.android.id.IdHelperAndroid;
import com.comscore.applications.EventType;
import com.comscore.utils.Constants;
import com.comscore.utils.Storage;
import com.comscore.utils.Utils;
import java.util.HashMap;

@SuppressLint({"NewApi"})
public class IdHelper {
    private Core a;
    private String b;
    private String c;
    private Storage d;
    private Context e;
    private String f;
    private String g;
    private String h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private Boolean l;
    private IdChecker m;

    public IdHelper(Context context, Storage storage, Core core) {
        this.e = context;
        this.d = storage;
        this.a = core;
        this.m = new IdChecker(this.e, storage);
    }

    private void a() {
        DeviceId advertisingDeviceId = IdHelperAndroid.getAdvertisingDeviceId(this.e);
        if (advertisingDeviceId == null) {
            this.i = true;
            a(null, false);
        } else if (advertisingDeviceId.getCommonness() == 0 && advertisingDeviceId.getPersistency() == 0) {
            a(advertisingDeviceId.getId(), true);
        } else {
            if (!this.m.checkCrossPublisherId(this.d.get(Constants.MD5_RAW_CROSSPUBLISHER_ID_KEY))) {
                this.d.remove(Constants.MD5_RAW_CROSSPUBLISHER_ID_KEY);
                this.d.remove(Constants.CROSS_PUBLISHER_ID_KEY);
                this.b = null;
                this.h = null;
            }
            a(advertisingDeviceId.getId(), false);
        }
    }

    private void a(String str, boolean z) {
        if (str != null) {
            this.i = z;
            String str2 = this.d.get(Constants.MD5_RAW_CROSSPUBLISHER_ID_KEY);
            boolean z2 = this.h == null;
            this.h = Utils.md5(str);
            if (a(this.h)) {
                this.b = this.d.get(Constants.CROSS_PUBLISHER_ID_KEY);
                return;
            }
            if (!(!this.i || str2 == null || str2.isEmpty() || str2 == this.h)) {
                this.j = true;
                this.k = z2;
            }
            this.b = b(str);
            this.d.set(Constants.CROSS_PUBLISHER_ID_KEY, this.b);
            this.d.set(Constants.MD5_RAW_CROSSPUBLISHER_ID_KEY, this.h);
            return;
        }
        this.b = null;
        this.h = null;
    }

    private boolean a(String str) {
        String str2 = this.d.get(Constants.MD5_RAW_CROSSPUBLISHER_ID_KEY);
        return str2 != null && str2.equals(str);
    }

    private String b(String str) {
        if (str != null && str.length() > 0) {
            try {
                return Utils.encrypt(str);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private void b() {
        if (this.d.has(Constants.VID_KEY).booleanValue()) {
            this.c = this.d.get(Constants.VID_KEY);
            if (!this.m.checkVisitorId()) {
                HashMap hashMap = new HashMap();
                hashMap.put("cs_c12u", this.d.get(Constants.VID_KEY));
                this.a.notify(EventType.AGGREGATE, hashMap, true);
                this.d.remove(Constants.VID_KEY);
                this.c = null;
            } else {
                return;
            }
        }
        DeviceId deviceId = IdHelperAndroid.getDeviceId(this.e);
        String id = deviceId.getId();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-cs");
        stringBuilder.append(deviceId.getSuffix());
        String stringBuilder2 = stringBuilder.toString();
        if (this.c == null) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder = new StringBuilder();
            stringBuilder.append(id);
            stringBuilder.append(getPublisherSecret());
            stringBuilder3.append(Utils.md5(stringBuilder.toString()));
            stringBuilder3.append(stringBuilder2);
            this.c = stringBuilder3.toString();
            this.d.set(Constants.VID_KEY, this.c);
        }
    }

    private boolean c() {
        boolean isAdvertisingIdEnabled = IdHelperAndroid.isAdvertisingIdEnabled(this.e);
        if (this.l == null) {
            this.l = Boolean.valueOf(isAdvertisingIdEnabled);
            return isAdvertisingIdEnabled;
        }
        if (this.l.booleanValue() != isAdvertisingIdEnabled) {
            a();
        }
        return isAdvertisingIdEnabled;
    }

    public void generateIds() {
        if (!isPublisherSecretEmpty()) {
            this.g = IdHelperAndroid.getDeviceId(this.e).getId();
            b();
            a();
        }
    }

    public String getAndroidId() {
        return this.g;
    }

    public String getCrossPublisherId() {
        if (!this.i) {
            return this.b;
        }
        if (!c()) {
            return IdHelperAndroid.NO_ID_AVAILABLE;
        }
        a();
        return (!this.j || this.k) ? this.b : IdHelperAndroid.NO_ID_AVAILABLE;
    }

    public String getMD5AdvertisingId() {
        return (this.i && c()) ? this.h : null;
    }

    public String getPublisherSecret() {
        if (this.f == null) {
            this.f = "";
        }
        return this.f;
    }

    public String getVisitorId() {
        return this.c;
    }

    public boolean isIdChanged() {
        return this.j;
    }

    public boolean isPublisherSecretEmpty() {
        return this.f == null || this.f.length() == 0;
    }

    public void resetVisitorId() {
        this.c = null;
        if (this.d.has(Constants.VID_KEY).booleanValue()) {
            this.d.remove(Constants.VID_KEY);
            generateIds();
        }
    }

    public void setPublisherSecret(String str) {
        this.f = str;
    }
}
