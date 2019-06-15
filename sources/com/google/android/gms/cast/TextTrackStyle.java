package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.view.accessibility.CaptioningManager;
import android.view.accessibility.CaptioningManager.CaptionStyle;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzdk;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "TextTrackStyleCreator")
@Reserved({1})
public final class TextTrackStyle extends AbstractSafeParcelable {
    public static final int COLOR_UNSPECIFIED = 0;
    public static final Creator<TextTrackStyle> CREATOR = new zzbz();
    public static final float DEFAULT_FONT_SCALE = 1.0f;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    public static final int FONT_FAMILY_CASUAL = 4;
    public static final int FONT_FAMILY_CURSIVE = 5;
    public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
    public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
    public static final int FONT_FAMILY_SANS_SERIF = 0;
    public static final int FONT_FAMILY_SERIF = 2;
    public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
    public static final int FONT_FAMILY_UNSPECIFIED = -1;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_NORMAL = 0;
    public static final int FONT_STYLE_UNSPECIFIED = -1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_NORMAL = 1;
    public static final int WINDOW_TYPE_ROUNDED = 2;
    public static final int WINDOW_TYPE_UNSPECIFIED = -1;
    @Field(getter = "getBackgroundColor", id = 4)
    private int backgroundColor;
    @Field(getter = "getEdgeColor", id = 6)
    private int edgeColor;
    @Field(getter = "getEdgeType", id = 5)
    private int edgeType;
    @Field(getter = "getFontScale", id = 2)
    private float fontScale;
    @Field(getter = "getFontStyle", id = 12)
    private int fontStyle;
    @Field(getter = "getForegroundColor", id = 3)
    private int foregroundColor;
    @Field(getter = "getWindowColor", id = 8)
    private int windowColor;
    @Field(getter = "getWindowType", id = 7)
    private int zzgt;
    @Field(getter = "getWindowCornerRadius", id = 9)
    private int zzgu;
    @Field(getter = "getFontFamily", id = 10)
    private String zzgv;
    @Field(getter = "getFontGenericFamily", id = 11)
    private int zzgw;
    @Field(id = 13)
    private String zzj;
    private JSONObject zzp;

    @Constructor
    TextTrackStyle(@Param(id = 2) float f, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) int i3, @Param(id = 6) int i4, @Param(id = 7) int i5, @Param(id = 8) int i6, @Param(id = 9) int i7, @Param(id = 10) String str, @Param(id = 11) int i8, @Param(id = 12) int i9, @Param(id = 13) String str2) {
        this.fontScale = f;
        this.foregroundColor = i;
        this.backgroundColor = i2;
        this.edgeType = i3;
        this.edgeColor = i4;
        this.zzgt = i5;
        this.windowColor = i6;
        this.zzgu = i7;
        this.zzgv = str;
        this.zzgw = i8;
        this.fontStyle = i9;
        this.zzj = str2;
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

    public TextTrackStyle() {
        this(1.0f, 0, 0, -1, 0, -1, 0, 0, null, -1, -1, null);
    }

    public final void setFontScale(float f) {
        this.fontScale = f;
    }

    public final float getFontScale() {
        return this.fontScale;
    }

    public final void setForegroundColor(int i) {
        this.foregroundColor = i;
    }

    public final int getForegroundColor() {
        return this.foregroundColor;
    }

    public final void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setEdgeType(int i) {
        if (i < 0 || i > 4) {
            throw new IllegalArgumentException("invalid edgeType");
        }
        this.edgeType = i;
    }

    public final int getEdgeType() {
        return this.edgeType;
    }

    public final void setEdgeColor(int i) {
        this.edgeColor = i;
    }

    public final int getEdgeColor() {
        return this.edgeColor;
    }

    public final void setWindowType(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("invalid windowType");
        }
        this.zzgt = i;
    }

    public final int getWindowType() {
        return this.zzgt;
    }

    public final void setWindowColor(int i) {
        this.windowColor = i;
    }

    public final int getWindowColor() {
        return this.windowColor;
    }

    public final void setWindowCornerRadius(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("invalid windowCornerRadius");
        }
        this.zzgu = i;
    }

    public final int getWindowCornerRadius() {
        return this.zzgu;
    }

    public final void setFontFamily(String str) {
        this.zzgv = str;
    }

    public final String getFontFamily() {
        return this.zzgv;
    }

    public final void setFontGenericFamily(int i) {
        if (i < 0 || i > 6) {
            throw new IllegalArgumentException("invalid fontGenericFamily");
        }
        this.zzgw = i;
    }

    public final int getFontGenericFamily() {
        return this.zzgw;
    }

    public final void setFontStyle(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("invalid fontStyle");
        }
        this.fontStyle = i;
    }

    public final int getFontStyle() {
        return this.fontStyle;
    }

    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public final JSONObject getCustomData() {
        return this.zzp;
    }

    @TargetApi(19)
    public static TextTrackStyle fromSystemSettings(Context context) {
        TextTrackStyle textTrackStyle = new TextTrackStyle();
        if (!PlatformVersion.isAtLeastKitKat()) {
            return textTrackStyle;
        }
        CaptioningManager captioningManager = (CaptioningManager) context.getSystemService("captioning");
        textTrackStyle.setFontScale(captioningManager.getFontScale());
        CaptionStyle userStyle = captioningManager.getUserStyle();
        textTrackStyle.setBackgroundColor(userStyle.backgroundColor);
        textTrackStyle.setForegroundColor(userStyle.foregroundColor);
        switch (userStyle.edgeType) {
            case 1:
                textTrackStyle.setEdgeType(1);
                break;
            case 2:
                textTrackStyle.setEdgeType(2);
                break;
            default:
                textTrackStyle.setEdgeType(0);
                break;
        }
        textTrackStyle.setEdgeColor(userStyle.edgeColor);
        Typeface typeface = userStyle.getTypeface();
        if (typeface != null) {
            if (Typeface.MONOSPACE.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(1);
            } else if (Typeface.SANS_SERIF.equals(typeface) || !Typeface.SERIF.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(0);
            } else {
                textTrackStyle.setFontGenericFamily(2);
            }
            boolean isBold = typeface.isBold();
            boolean isItalic = typeface.isItalic();
            if (isBold && isItalic) {
                textTrackStyle.setFontStyle(3);
            } else if (isBold) {
                textTrackStyle.setFontStyle(1);
            } else if (isItalic) {
                textTrackStyle.setFontStyle(2);
            } else {
                textTrackStyle.setFontStyle(0);
            }
        }
        return textTrackStyle;
    }

    public final void zze(JSONObject jSONObject) throws JSONException {
        String string;
        this.fontScale = (float) jSONObject.optDouble("fontScale", 1.0d);
        this.foregroundColor = zzi(jSONObject.optString("foregroundColor"));
        this.backgroundColor = zzi(jSONObject.optString(TtmlNode.ATTR_TTS_BACKGROUND_COLOR));
        if (jSONObject.has("edgeType")) {
            string = jSONObject.getString("edgeType");
            if ("NONE".equals(string)) {
                this.edgeType = 0;
            } else if ("OUTLINE".equals(string)) {
                this.edgeType = 1;
            } else if ("DROP_SHADOW".equals(string)) {
                this.edgeType = 2;
            } else if ("RAISED".equals(string)) {
                this.edgeType = 3;
            } else if ("DEPRESSED".equals(string)) {
                this.edgeType = 4;
            }
        }
        this.edgeColor = zzi(jSONObject.optString("edgeColor"));
        if (jSONObject.has("windowType")) {
            string = jSONObject.getString("windowType");
            if ("NONE".equals(string)) {
                this.zzgt = 0;
            } else if ("NORMAL".equals(string)) {
                this.zzgt = 1;
            } else if ("ROUNDED_CORNERS".equals(string)) {
                this.zzgt = 2;
            }
        }
        this.windowColor = zzi(jSONObject.optString("windowColor"));
        if (this.zzgt == 2) {
            this.zzgu = jSONObject.optInt("windowRoundedCornerRadius", 0);
        }
        this.zzgv = jSONObject.optString(TtmlNode.ATTR_TTS_FONT_FAMILY, null);
        if (jSONObject.has("fontGenericFamily")) {
            string = jSONObject.getString("fontGenericFamily");
            if ("SANS_SERIF".equals(string)) {
                this.zzgw = 0;
            } else if ("MONOSPACED_SANS_SERIF".equals(string)) {
                this.zzgw = 1;
            } else if ("SERIF".equals(string)) {
                this.zzgw = 2;
            } else if ("MONOSPACED_SERIF".equals(string)) {
                this.zzgw = 3;
            } else if ("CASUAL".equals(string)) {
                this.zzgw = 4;
            } else if ("CURSIVE".equals(string)) {
                this.zzgw = 5;
            } else if ("SMALL_CAPITALS".equals(string)) {
                this.zzgw = 6;
            }
        }
        if (jSONObject.has(TtmlNode.ATTR_TTS_FONT_STYLE)) {
            string = jSONObject.getString(TtmlNode.ATTR_TTS_FONT_STYLE);
            if ("NORMAL".equals(string)) {
                this.fontStyle = 0;
            } else if ("BOLD".equals(string)) {
                this.fontStyle = 1;
            } else if ("ITALIC".equals(string)) {
                this.fontStyle = 2;
            } else if ("BOLD_ITALIC".equals(string)) {
                this.fontStyle = 3;
            }
        }
        this.zzp = jSONObject.optJSONObject("customData");
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fontScale", (double) this.fontScale);
            if (this.foregroundColor != 0) {
                jSONObject.put("foregroundColor", zzg(this.foregroundColor));
            }
            if (this.backgroundColor != 0) {
                jSONObject.put(TtmlNode.ATTR_TTS_BACKGROUND_COLOR, zzg(this.backgroundColor));
            }
            switch (this.edgeType) {
                case 0:
                    jSONObject.put("edgeType", "NONE");
                    break;
                case 1:
                    jSONObject.put("edgeType", "OUTLINE");
                    break;
                case 2:
                    jSONObject.put("edgeType", "DROP_SHADOW");
                    break;
                case 3:
                    jSONObject.put("edgeType", "RAISED");
                    break;
                case 4:
                    jSONObject.put("edgeType", "DEPRESSED");
                    break;
                default:
                    break;
            }
            if (this.edgeColor != 0) {
                jSONObject.put("edgeColor", zzg(this.edgeColor));
            }
            switch (this.zzgt) {
                case 0:
                    jSONObject.put("windowType", "NONE");
                    break;
                case 1:
                    jSONObject.put("windowType", "NORMAL");
                    break;
                case 2:
                    jSONObject.put("windowType", "ROUNDED_CORNERS");
                    break;
                default:
                    break;
            }
            if (this.windowColor != 0) {
                jSONObject.put("windowColor", zzg(this.windowColor));
            }
            if (this.zzgt == 2) {
                jSONObject.put("windowRoundedCornerRadius", this.zzgu);
            }
            if (this.zzgv != null) {
                jSONObject.put(TtmlNode.ATTR_TTS_FONT_FAMILY, this.zzgv);
            }
            switch (this.zzgw) {
                case 0:
                    jSONObject.put("fontGenericFamily", "SANS_SERIF");
                    break;
                case 1:
                    jSONObject.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");
                    break;
                case 2:
                    jSONObject.put("fontGenericFamily", "SERIF");
                    break;
                case 3:
                    jSONObject.put("fontGenericFamily", "MONOSPACED_SERIF");
                    break;
                case 4:
                    jSONObject.put("fontGenericFamily", "CASUAL");
                    break;
                case 5:
                    jSONObject.put("fontGenericFamily", "CURSIVE");
                    break;
                case 6:
                    jSONObject.put("fontGenericFamily", "SMALL_CAPITALS");
                    break;
                default:
                    break;
            }
            switch (this.fontStyle) {
                case 0:
                    jSONObject.put(TtmlNode.ATTR_TTS_FONT_STYLE, "NORMAL");
                    break;
                case 1:
                    jSONObject.put(TtmlNode.ATTR_TTS_FONT_STYLE, "BOLD");
                    break;
                case 2:
                    jSONObject.put(TtmlNode.ATTR_TTS_FONT_STYLE, "ITALIC");
                    break;
                case 3:
                    jSONObject.put(TtmlNode.ATTR_TTS_FONT_STYLE, "BOLD_ITALIC");
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

    private static String zzg(int i) {
        return String.format("#%02X%02X%02X%02X", new Object[]{Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.alpha(i))});
    }

    private static int zzi(String str) {
        if (str != null && str.length() == 9 && str.charAt(0) == '#') {
            try {
                return Color.argb(Integer.parseInt(str.substring(7, 9), 16), Integer.parseInt(str.substring(1, 3), 16), Integer.parseInt(str.substring(3, 5), 16), Integer.parseInt(str.substring(5, 7), 16));
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextTrackStyle)) {
            return false;
        }
        TextTrackStyle textTrackStyle = (TextTrackStyle) obj;
        if ((this.zzp == null) != (textTrackStyle.zzp == null)) {
            return false;
        }
        return (this.zzp == null || textTrackStyle.zzp == null || JsonUtils.areJsonValuesEquivalent(this.zzp, textTrackStyle.zzp)) && this.fontScale == textTrackStyle.fontScale && this.foregroundColor == textTrackStyle.foregroundColor && this.backgroundColor == textTrackStyle.backgroundColor && this.edgeType == textTrackStyle.edgeType && this.edgeColor == textTrackStyle.edgeColor && this.zzgt == textTrackStyle.zzgt && this.zzgu == textTrackStyle.zzgu && zzdk.zza(this.zzgv, textTrackStyle.zzgv) && this.zzgw == textTrackStyle.zzgw && this.fontStyle == textTrackStyle.fontStyle;
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.fontScale), Integer.valueOf(this.foregroundColor), Integer.valueOf(this.backgroundColor), Integer.valueOf(this.edgeType), Integer.valueOf(this.edgeColor), Integer.valueOf(this.zzgt), Integer.valueOf(this.windowColor), Integer.valueOf(this.zzgu), this.zzgv, Integer.valueOf(this.zzgw), Integer.valueOf(this.fontStyle), String.valueOf(this.zzp));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, getFontScale());
        SafeParcelWriter.writeInt(parcel, 3, getForegroundColor());
        SafeParcelWriter.writeInt(parcel, 4, getBackgroundColor());
        SafeParcelWriter.writeInt(parcel, 5, getEdgeType());
        SafeParcelWriter.writeInt(parcel, 6, getEdgeColor());
        SafeParcelWriter.writeInt(parcel, 7, getWindowType());
        SafeParcelWriter.writeInt(parcel, 8, getWindowColor());
        SafeParcelWriter.writeInt(parcel, 9, getWindowCornerRadius());
        SafeParcelWriter.writeString(parcel, 10, getFontFamily(), false);
        SafeParcelWriter.writeInt(parcel, 11, getFontGenericFamily());
        SafeParcelWriter.writeInt(parcel, 12, getFontStyle());
        SafeParcelWriter.writeString(parcel, 13, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
