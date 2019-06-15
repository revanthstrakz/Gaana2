package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.cast.zzeg;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "MediaMetadataCreator")
@Reserved({1})
public class MediaMetadata extends AbstractSafeParcelable {
    public static final Creator<MediaMetadata> CREATOR = new zzal();
    public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
    public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
    public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
    public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
    public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
    public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
    public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
    public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
    public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
    public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
    public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
    public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
    public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
    public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
    public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
    public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
    public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
    public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
    public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
    public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
    public static final int MEDIA_TYPE_GENERIC = 0;
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_USER = 100;
    private static final String[] zzdv = new String[]{null, "String", "int", "double", "ISO-8601 date String"};
    private static final zza zzdw = new zza().zza(KEY_CREATION_DATE, "creationDateTime", 4).zza(KEY_RELEASE_DATE, "releaseDate", 4).zza(KEY_BROADCAST_DATE, "originalAirdate", 4).zza(KEY_TITLE, "title", 1).zza(KEY_SUBTITLE, FacebookAdapter.KEY_SUBTITLE_ASSET, 1).zza(KEY_ARTIST, "artist", 1).zza(KEY_ALBUM_ARTIST, "albumArtist", 1).zza(KEY_ALBUM_TITLE, "albumName", 1).zza(KEY_COMPOSER, "composer", 1).zza(KEY_DISC_NUMBER, "discNumber", 2).zza(KEY_TRACK_NUMBER, "trackNumber", 2).zza(KEY_SEASON_NUMBER, "season", 2).zza(KEY_EPISODE_NUMBER, "episode", 2).zza(KEY_SERIES_TITLE, "seriesTitle", 1).zza(KEY_STUDIO, "studio", 1).zza(KEY_WIDTH, "width", 2).zza(KEY_HEIGHT, "height", 2).zza(KEY_LOCATION_NAME, PlaceFields.LOCATION, 1).zza(KEY_LOCATION_LATITUDE, "latitude", 3).zza(KEY_LOCATION_LONGITUDE, "longitude", 3).zza("com.google.android.gms.cast.metadata.SECTION_DURATION", "sectionDuration", 5).zza("com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA", "sectionStartTimeInMedia", 5).zza("com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME", "sectionStartAbsoluteTime", 5).zza("com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER", "sectionStartTimeInContainer", 5).zza("com.google.android.gms.cast.metadata.QUEUE_ITEM_ID", "queueItemId", 2);
    @Field(getter = "getImages", id = 2)
    private final List<WebImage> zzdx;
    @Field(id = 3)
    private final Bundle zzdy;
    @Field(getter = "getMediaType", id = 4)
    private int zzdz;

    private static class zza {
        private final Map<String, String> zzea = new HashMap();
        private final Map<String, String> zzeb = new HashMap();
        private final Map<String, Integer> zzec = new HashMap();

        public final zza zza(String str, String str2, int i) {
            this.zzea.put(str, str2);
            this.zzeb.put(str2, str);
            this.zzec.put(str, Integer.valueOf(i));
            return this;
        }

        public final String zzf(String str) {
            return (String) this.zzea.get(str);
        }

        public final String zzg(String str) {
            return (String) this.zzeb.get(str);
        }

        public final int zzh(String str) {
            Integer num = (Integer) this.zzec.get(str);
            return num != null ? num.intValue() : 0;
        }
    }

    @Constructor
    MediaMetadata(@Param(id = 2) List<WebImage> list, @Param(id = 3) Bundle bundle, @Param(id = 4) int i) {
        this.zzdx = list;
        this.zzdy = bundle;
        this.zzdz = i;
    }

    public MediaMetadata() {
        this(0);
    }

    public MediaMetadata(int i) {
        this(new ArrayList(), new Bundle(), i);
    }

    public int getMediaType() {
        return this.zzdz;
    }

    public void clear() {
        this.zzdy.clear();
        this.zzdx.clear();
    }

    public boolean containsKey(String str) {
        return this.zzdy.containsKey(str);
    }

    public Set<String> keySet() {
        return this.zzdy.keySet();
    }

    public void putString(String str, String str2) {
        zza(str, 1);
        this.zzdy.putString(str, str2);
    }

    public String getString(String str) {
        zza(str, 1);
        return this.zzdy.getString(str);
    }

    public void putInt(String str, int i) {
        zza(str, 2);
        this.zzdy.putInt(str, i);
    }

    public int getInt(String str) {
        zza(str, 2);
        return this.zzdy.getInt(str);
    }

    public void putDouble(String str, double d) {
        zza(str, 3);
        this.zzdy.putDouble(str, d);
    }

    public double getDouble(String str) {
        zza(str, 3);
        return this.zzdy.getDouble(str);
    }

    public void putDate(String str, Calendar calendar) {
        zza(str, 4);
        this.zzdy.putString(str, zzeg.zza(calendar));
    }

    public Calendar getDate(String str) {
        zza(str, 4);
        str = this.zzdy.getString(str);
        return str != null ? zzeg.zzv(str) : null;
    }

    public String getDateAsString(String str) {
        zza(str, 4);
        return this.zzdy.getString(str);
    }

    public final long zze(String str) {
        zza(str, 5);
        return this.zzdy.getLong(str);
    }

    private static void zza(String str, int i) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("null and empty keys are not allowed");
        }
        int zzh = zzdw.zzh(str);
        if (zzh != i && zzh != 0) {
            Object obj = zzdv[i];
            StringBuilder stringBuilder = new StringBuilder((21 + String.valueOf(str).length()) + String.valueOf(obj).length());
            stringBuilder.append("Value for ");
            stringBuilder.append(str);
            stringBuilder.append(" must be a ");
            stringBuilder.append(obj);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metadataType", this.zzdz);
        } catch (JSONException unused) {
        }
        JSONArray zze = zzeg.zze(this.zzdx);
        if (!(zze == null || zze.length() == 0)) {
            try {
                jSONObject.put("images", zze);
            } catch (JSONException unused2) {
            }
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        switch (this.zzdz) {
            case 0:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE});
                break;
            case 1:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE});
                break;
            case 2:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE});
                break;
            case 3:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ARTIST, KEY_ALBUM_TITLE, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE});
                break;
            case 4:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE});
                break;
        }
        Collections.addAll(arrayList, new String[]{"com.google.android.gms.cast.metadata.SECTION_DURATION", "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA", "com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME", "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER", "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID"});
        try {
            Object obj;
            arrayList = arrayList;
            int size = arrayList.size();
            while (i < size) {
                obj = arrayList.get(i);
                i++;
                String str = (String) obj;
                if (this.zzdy.containsKey(str)) {
                    switch (zzdw.zzh(str)) {
                        case 1:
                        case 4:
                            jSONObject.put(zzdw.zzf(str), this.zzdy.getString(str));
                            break;
                        case 2:
                            jSONObject.put(zzdw.zzf(str), this.zzdy.getInt(str));
                            break;
                        case 3:
                            jSONObject.put(zzdw.zzf(str), this.zzdy.getDouble(str));
                            break;
                        case 5:
                            jSONObject.put(zzdw.zzf(str), ((double) this.zzdy.getLong(str)) / 1000.0d);
                            break;
                        default:
                            break;
                    }
                }
            }
            for (String str2 : this.zzdy.keySet()) {
                if (!str2.startsWith("com.google.")) {
                    obj = this.zzdy.get(str2);
                    if (obj instanceof String) {
                        jSONObject.put(str2, obj);
                    } else if (obj instanceof Integer) {
                        jSONObject.put(str2, obj);
                    } else if (obj instanceof Double) {
                        jSONObject.put(str2, obj);
                    }
                }
            }
        } catch (JSONException unused3) {
        }
        return jSONObject;
    }

    public final void zze(JSONObject jSONObject) {
        clear();
        this.zzdz = 0;
        try {
            this.zzdz = jSONObject.getInt("metadataType");
        } catch (JSONException unused) {
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("images");
        if (optJSONArray != null) {
            zzeg.zza(this.zzdx, optJSONArray);
        }
        ArrayList arrayList = new ArrayList();
        switch (this.zzdz) {
            case 0:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE});
                break;
            case 1:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE});
                break;
            case 2:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE});
                break;
            case 3:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ALBUM_TITLE, KEY_ARTIST, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE});
                break;
            case 4:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE});
                break;
        }
        Collections.addAll(arrayList, new String[]{"com.google.android.gms.cast.metadata.QUEUE_ITEM_ID"});
        HashSet hashSet = new HashSet(arrayList);
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (!"metadataType".equals(str)) {
                    String zzg = zzdw.zzg(str);
                    if (zzg == null) {
                        Object obj = jSONObject.get(str);
                        if (obj instanceof String) {
                            this.zzdy.putString(str, (String) obj);
                        } else if (obj instanceof Integer) {
                            this.zzdy.putInt(str, ((Integer) obj).intValue());
                        } else if (obj instanceof Double) {
                            this.zzdy.putDouble(str, ((Double) obj).doubleValue());
                        }
                    } else if (hashSet.contains(zzg)) {
                        try {
                            Object obj2 = jSONObject.get(str);
                            if (obj2 != null) {
                                switch (zzdw.zzh(zzg)) {
                                    case 1:
                                        if (!(obj2 instanceof String)) {
                                            break;
                                        }
                                        this.zzdy.putString(zzg, (String) obj2);
                                        break;
                                    case 2:
                                        if (!(obj2 instanceof Integer)) {
                                            break;
                                        }
                                        this.zzdy.putInt(zzg, ((Integer) obj2).intValue());
                                        break;
                                    case 3:
                                        double optDouble = jSONObject.optDouble(str);
                                        if (!Double.isNaN(optDouble)) {
                                            this.zzdy.putDouble(zzg, optDouble);
                                            break;
                                        }
                                        break;
                                    case 4:
                                        if ((obj2 instanceof String) && zzeg.zzv((String) obj2) != null) {
                                            this.zzdy.putString(zzg, (String) obj2);
                                            break;
                                        }
                                    case 5:
                                        this.zzdy.putLong(zzg, (long) (((double) jSONObject.optLong(str)) * 1000.0d));
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } catch (JSONException unused2) {
                        }
                    }
                }
            }
        } catch (JSONException unused3) {
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return zza(this.zzdy, mediaMetadata.zzdy) && this.zzdx.equals(mediaMetadata.zzdx);
    }

    public int hashCode() {
        int i = 17;
        for (String str : this.zzdy.keySet()) {
            i = (i * 31) + this.zzdy.get(str).hashCode();
        }
        return (i * 31) + this.zzdx.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getImages(), false);
        SafeParcelWriter.writeBundle(parcel, 3, this.zzdy, false);
        SafeParcelWriter.writeInt(parcel, 4, getMediaType());
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public List<WebImage> getImages() {
        return this.zzdx;
    }

    public boolean hasImages() {
        return (this.zzdx == null || this.zzdx.isEmpty()) ? false : true;
    }

    public void clearImages() {
        this.zzdx.clear();
    }

    public void addImage(WebImage webImage) {
        this.zzdx.add(webImage);
    }

    private final boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !zza((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null) {
                if (obj2 != null || !bundle2.containsKey(str)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }
}
