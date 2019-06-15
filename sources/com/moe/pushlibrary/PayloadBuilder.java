package com.moe.pushlibrary;

import android.location.Location;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.moe.pushlibrary.models.GeoLocation;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class PayloadBuilder {
    private static final String ATTR_LOCATION = "location";
    private static final String ATTR_TIMESTAMP = "timestamp";
    public JSONObject customAttrs;
    public JSONObject generalAttrs;

    private void notNullCheck(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            throw new Exception("Action name cannot be empty");
        }
    }

    public PayloadBuilder putAttrInt(String str, int i) {
        try {
            notNullCheck(str);
            if (this.generalAttrs == null) {
                this.generalAttrs = new JSONObject();
            }
            this.generalAttrs.put(str.trim(), i);
        } catch (Exception e) {
            Logger.f("PayloadBuilder: putAttrInt", e);
        }
        return this;
    }

    public PayloadBuilder putAttrString(String str, String str2) {
        try {
            notNullCheck(str);
            if (this.generalAttrs == null) {
                this.generalAttrs = new JSONObject();
            }
            this.generalAttrs.put(str.trim(), str2);
        } catch (Exception e) {
            Logger.f("PayloadBuilder: putAttrString", e);
        }
        return this;
    }

    public PayloadBuilder putAttrBoolean(String str, boolean z) {
        try {
            notNullCheck(str);
            if (this.generalAttrs == null) {
                this.generalAttrs = new JSONObject();
            }
            this.generalAttrs.put(str.trim(), z);
        } catch (Exception e) {
            Logger.f("PayloadBuilder: putAttrBoolean", e);
        }
        return this;
    }

    public PayloadBuilder putAttrFloat(String str, float f) {
        try {
            notNullCheck(str);
            if (this.generalAttrs == null) {
                this.generalAttrs = new JSONObject();
            }
            this.generalAttrs.put(str.trim(), (double) f);
        } catch (Exception e) {
            Logger.f("PayloadBuilder: putAttrFloat", e);
        }
        return this;
    }

    public PayloadBuilder putAttrDouble(String str, double d) {
        try {
            notNullCheck(str);
            if (this.generalAttrs == null) {
                this.generalAttrs = new JSONObject();
            }
            this.generalAttrs.put(str.trim(), d);
        } catch (Exception e) {
            Logger.f("PayloadBuilder: putAttrDouble", e);
        }
        return this;
    }

    public PayloadBuilder putAttrLong(String str, long j) {
        try {
            notNullCheck(str);
            if (this.generalAttrs == null) {
                this.generalAttrs = new JSONObject();
            }
            this.generalAttrs.put(str.trim(), j);
        } catch (Exception e) {
            Logger.f("PayloadBuilder: putAttrLong", e);
        }
        return this;
    }

    public PayloadBuilder putAttrDate(String str, Date date) {
        try {
            JSONArray jSONArray;
            notNullCheck(str);
            if (this.customAttrs == null) {
                this.customAttrs = new JSONObject();
            }
            if (this.customAttrs.has("timestamp")) {
                jSONArray = this.customAttrs.getJSONArray("timestamp");
            } else {
                jSONArray = new JSONArray();
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str.trim(), date.getTime());
            jSONArray.put(jSONObject);
            this.customAttrs.put("timestamp", jSONArray);
        } catch (Exception e) {
            Logger.f("EventPayload: putAttrDate: ", e);
        }
        return this;
    }

    public PayloadBuilder putAttrDate(String str, String str2, String str3) {
        try {
            notNullCheck(str);
            return putAttrDate(str.trim(), new SimpleDateFormat(str3, Locale.ENGLISH).parse(str2));
        } catch (Exception e) {
            Logger.f("EventPayload: putAttrDate 2: ", e);
            return this;
        }
    }

    public PayloadBuilder putAttrLocation(String str, GeoLocation geoLocation) {
        try {
            JSONArray jSONArray;
            notNullCheck(str);
            if (this.customAttrs == null) {
                this.customAttrs = new JSONObject();
            }
            if (this.customAttrs.has("location")) {
                jSONArray = this.customAttrs.getJSONArray("location");
            } else {
                jSONArray = new JSONArray();
            }
            JSONObject jSONObject = new JSONObject();
            str = str.trim();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(geoLocation.latitude);
            stringBuilder.append(",");
            stringBuilder.append(geoLocation.longitude);
            jSONObject.put(str, stringBuilder.toString());
            jSONArray.put(jSONObject);
            this.customAttrs.put("location", jSONArray);
        } catch (Exception e) {
            Logger.f("EventPayload: putAttrLocation1: ", e);
        }
        return this;
    }

    public PayloadBuilder putAttrLocation(String str, Location location) {
        try {
            JSONArray jSONArray;
            notNullCheck(str);
            if (this.customAttrs == null) {
                this.customAttrs = new JSONObject();
            }
            if (this.customAttrs.has("location")) {
                jSONArray = this.customAttrs.getJSONArray("location");
            } else {
                jSONArray = new JSONArray();
            }
            JSONObject jSONObject = new JSONObject();
            str = str.trim();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(location.getLatitude());
            stringBuilder.append(",");
            stringBuilder.append(location.getLongitude());
            jSONObject.put(str, stringBuilder.toString());
            jSONArray.put(jSONObject);
            this.customAttrs.put("location", jSONArray);
        } catch (Exception e) {
            Logger.f("EventPayload: putAttrLocation2: ", e);
        }
        return this;
    }

    public PayloadBuilder putAttrLocation(String str, double d, double d2) {
        try {
            JSONArray jSONArray;
            notNullCheck(str);
            if (this.customAttrs == null) {
                this.customAttrs = new JSONObject();
            }
            if (this.customAttrs.has("location")) {
                jSONArray = this.customAttrs.getJSONArray("location");
            } else {
                jSONArray = new JSONArray();
            }
            JSONObject jSONObject = new JSONObject();
            str = str.trim();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(d);
            stringBuilder.append(",");
            stringBuilder.append(d2);
            jSONObject.put(str, stringBuilder.toString());
            jSONArray.put(jSONObject);
            this.customAttrs.put("location", jSONArray);
        } catch (Exception e) {
            Logger.f("EventPayload: putAttrLocation3: ", e);
        }
        return this;
    }

    public PayloadBuilder putAttrDateEpoch(String str, long j) {
        try {
            JSONArray jSONArray;
            notNullCheck(str);
            if (this.customAttrs == null) {
                this.customAttrs = new JSONObject();
            }
            if (this.customAttrs.has("timestamp")) {
                jSONArray = this.customAttrs.getJSONArray("timestamp");
            } else {
                jSONArray = new JSONArray();
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str.trim(), j);
            jSONArray.put(jSONObject);
            this.customAttrs.put("timestamp", jSONArray);
        } catch (Exception e) {
            Logger.f("EventPayload: putAttrDate: ", e);
        }
        return this;
    }

    public PayloadBuilder putAttrJSONArray(@NonNull String str, @NonNull JSONArray jSONArray) {
        try {
            notNullCheck(str);
            if (jSONArray == null) {
                return this;
            }
            if (this.generalAttrs == null) {
                this.generalAttrs = new JSONObject();
            }
            this.generalAttrs.put(str.trim(), jSONArray);
            return this;
        } catch (Exception e) {
            Logger.f("EventPayload: putAttrJSONArray: ", e);
        }
    }

    public PayloadBuilder putAttrJSONObject(@NonNull String str, @NonNull JSONObject jSONObject) {
        try {
            notNullCheck(str);
            if (jSONObject == null) {
                return this;
            }
            if (this.generalAttrs == null) {
                this.generalAttrs = new JSONObject();
            }
            this.generalAttrs.put(str.trim(), jSONObject);
            return this;
        } catch (Exception e) {
            Logger.f("EventPayload: putAttrJSONObject: ", e);
        }
    }

    public JSONObject build() {
        return MoEHelperUtils.getDatapointJSON(this.generalAttrs, this.customAttrs);
    }
}
