package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.gaana.login.LoginManager;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "MediaTrackCreator")
@Reserved({1})
public final class MediaTrack extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<MediaTrack> CREATOR = new zzaw();
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    @Field(getter = "getId", id = 2)
    private long id;
    @Field(getter = "getName", id = 6)
    private String name;
    @Field(getter = "getType", id = 3)
    private int type;
    @Field(getter = "getLanguage", id = 7)
    private String zzdc;
    @Field(getter = "getContentType", id = 5)
    private String zzde;
    @Field(getter = "getSubtype", id = 8)
    private int zzfd;
    @Field(id = 9)
    private String zzj;
    @Field(getter = "getContentId", id = 4)
    private String zzk;
    private JSONObject zzp;

    @VisibleForTesting
    public static class Builder {
        private final MediaTrack zzfe;

        public Builder(long j, int i) throws IllegalArgumentException {
            this.zzfe = new MediaTrack(j, i);
        }

        public Builder setContentId(String str) {
            this.zzfe.setContentId(str);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzfe.setContentType(str);
            return this;
        }

        public Builder setName(String str) {
            this.zzfe.setName(str);
            return this;
        }

        public Builder setLanguage(String str) {
            this.zzfe.setLanguage(str);
            return this;
        }

        public Builder setLanguage(Locale locale) {
            this.zzfe.setLanguage(zzdk.zza(locale));
            return this;
        }

        public Builder setSubtype(int i) throws IllegalArgumentException {
            this.zzfe.zze(i);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzfe.setCustomData(jSONObject);
            return this;
        }

        public MediaTrack build() {
            return this.zzfe;
        }
    }

    @Constructor
    MediaTrack(@Param(id = 2) long j, @Param(id = 3) int i, @Param(id = 4) String str, @Param(id = 5) String str2, @Param(id = 6) String str3, @Param(id = 7) String str4, @Param(id = 8) int i2, @Param(id = 9) String str5) {
        this.id = j;
        this.type = i;
        this.zzk = str;
        this.zzde = str2;
        this.name = str3;
        this.zzdc = str4;
        this.zzfd = i2;
        this.zzj = str5;
        if (this.zzj != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
                return;
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
                return;
            }
        }
        this.zzp = null;
    }

    MediaTrack(JSONObject jSONObject) throws JSONException {
        this(0, 0, null, null, null, null, -1, null);
        this.id = jSONObject.getLong("trackId");
        String string = jSONObject.getString("type");
        if ("TEXT".equals(string)) {
            this.type = 1;
        } else if ("AUDIO".equals(string)) {
            this.type = 2;
        } else if (ShareConstants.VIDEO_URL.equals(string)) {
            this.type = 3;
        } else {
            String str = "invalid type: ";
            string = String.valueOf(string);
            throw new JSONException(string.length() != 0 ? str.concat(string) : new String(str));
        }
        this.zzk = jSONObject.optString("trackContentId", null);
        this.zzde = jSONObject.optString("trackContentType", null);
        this.name = jSONObject.optString("name", null);
        this.zzdc = jSONObject.optString("language", null);
        if (jSONObject.has(LoginManager.TAG_SUBTYPE)) {
            string = jSONObject.getString(LoginManager.TAG_SUBTYPE);
            if ("SUBTITLES".equals(string)) {
                this.zzfd = 1;
            } else if ("CAPTIONS".equals(string)) {
                this.zzfd = 2;
            } else if ("DESCRIPTIONS".equals(string)) {
                this.zzfd = 3;
            } else if ("CHAPTERS".equals(string)) {
                this.zzfd = 4;
            } else if ("METADATA".equals(string)) {
                this.zzfd = 5;
            } else {
                this.zzfd = -1;
            }
        } else {
            this.zzfd = 0;
        }
        this.zzp = jSONObject.optJSONObject("customData");
    }

    MediaTrack(long j, int i) throws IllegalArgumentException {
        this(0, 0, null, null, null, null, -1, null);
        this.id = j;
        if (i <= 0 || i > 3) {
            StringBuilder stringBuilder = new StringBuilder(24);
            stringBuilder.append("invalid type ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.type = i;
    }

    public final long getId() {
        return this.id;
    }

    public final int getType() {
        return this.type;
    }

    public final String getContentId() {
        return this.zzk;
    }

    public final void setContentId(String str) {
        this.zzk = str;
    }

    public final String getContentType() {
        return this.zzde;
    }

    public final void setContentType(String str) {
        this.zzde = str;
    }

    public final String getName() {
        return this.name;
    }

    /* Access modifiers changed, original: final */
    public final void setName(String str) {
        this.name = str;
    }

    public final String getLanguage() {
        return this.zzdc;
    }

    /* Access modifiers changed, original: final */
    public final void setLanguage(String str) {
        this.zzdc = str;
    }

    public final int getSubtype() {
        return this.zzfd;
    }

    /* Access modifiers changed, original: final */
    public final void zze(int i) throws IllegalArgumentException {
        if (i < 0 || i > 5) {
            StringBuilder stringBuilder = new StringBuilder(27);
            stringBuilder.append("invalid subtype ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i == 0 || this.type == 1) {
            this.zzfd = i;
        } else {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
        }
    }

    public final JSONObject getCustomData() {
        return this.zzp;
    }

    /* Access modifiers changed, original: final */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackId", this.id);
            switch (this.type) {
                case 1:
                    jSONObject.put("type", "TEXT");
                    break;
                case 2:
                    jSONObject.put("type", "AUDIO");
                    break;
                case 3:
                    jSONObject.put("type", ShareConstants.VIDEO_URL);
                    break;
                default:
                    break;
            }
            if (this.zzk != null) {
                jSONObject.put("trackContentId", this.zzk);
            }
            if (this.zzde != null) {
                jSONObject.put("trackContentType", this.zzde);
            }
            if (this.name != null) {
                jSONObject.put("name", this.name);
            }
            if (!TextUtils.isEmpty(this.zzdc)) {
                jSONObject.put("language", this.zzdc);
            }
            switch (this.zzfd) {
                case 1:
                    jSONObject.put(LoginManager.TAG_SUBTYPE, "SUBTITLES");
                    break;
                case 2:
                    jSONObject.put(LoginManager.TAG_SUBTYPE, "CAPTIONS");
                    break;
                case 3:
                    jSONObject.put(LoginManager.TAG_SUBTYPE, "DESCRIPTIONS");
                    break;
                case 4:
                    jSONObject.put(LoginManager.TAG_SUBTYPE, "CHAPTERS");
                    break;
                case 5:
                    jSONObject.put(LoginManager.TAG_SUBTYPE, "METADATA");
                    break;
                default:
                    break;
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) obj;
        if ((this.zzp == null) != (mediaTrack.zzp == null)) {
            return false;
        }
        return (this.zzp == null || mediaTrack.zzp == null || JsonUtils.areJsonValuesEquivalent(this.zzp, mediaTrack.zzp)) && this.id == mediaTrack.id && this.type == mediaTrack.type && zzdk.zza(this.zzk, mediaTrack.zzk) && zzdk.zza(this.zzde, mediaTrack.zzde) && zzdk.zza(this.name, mediaTrack.name) && zzdk.zza(this.zzdc, mediaTrack.zzdc) && this.zzfd == mediaTrack.zzfd;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.id), Integer.valueOf(this.type), this.zzk, this.zzde, this.name, this.zzdc, Integer.valueOf(this.zzfd), String.valueOf(this.zzp));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getId());
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeString(parcel, 4, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 5, getContentType(), false);
        SafeParcelWriter.writeString(parcel, 6, getName(), false);
        SafeParcelWriter.writeString(parcel, 7, getLanguage(), false);
        SafeParcelWriter.writeInt(parcel, 8, getSubtype());
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
