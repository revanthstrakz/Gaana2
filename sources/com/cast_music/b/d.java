package com.cast_music.b;

import android.content.Context;
import android.graphics.Point;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.images.WebImage;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class d {
    public static final boolean a = (VERSION.SDK_INT >= 19);
    private static final String b = b.a(d.class);

    private d() {
    }

    public static void a(Context context, int i) {
        Toast.makeText(context, context.getString(i), 1).show();
    }

    public static Bundle a(MediaInfo mediaInfo) {
        if (mediaInfo == null) {
            return null;
        }
        MediaMetadata metadata = mediaInfo.getMetadata();
        Bundle bundle = new Bundle();
        bundle.putString(MediaMetadata.KEY_TITLE, metadata.getString(MediaMetadata.KEY_TITLE));
        bundle.putString(MediaMetadata.KEY_SUBTITLE, metadata.getString(MediaMetadata.KEY_SUBTITLE));
        bundle.putString(MediaMetadata.KEY_ALBUM_TITLE, metadata.getString(MediaMetadata.KEY_ALBUM_TITLE));
        bundle.putString(MediaMetadata.KEY_ALBUM_ARTIST, metadata.getString(MediaMetadata.KEY_ALBUM_ARTIST));
        bundle.putString(MediaMetadata.KEY_COMPOSER, metadata.getString(MediaMetadata.KEY_COMPOSER));
        bundle.putString(MediaMetadata.KEY_SERIES_TITLE, metadata.getString(MediaMetadata.KEY_SERIES_TITLE));
        bundle.putInt(MediaMetadata.KEY_SEASON_NUMBER, metadata.getInt(MediaMetadata.KEY_SEASON_NUMBER));
        bundle.putInt(MediaMetadata.KEY_EPISODE_NUMBER, metadata.getInt(MediaMetadata.KEY_EPISODE_NUMBER));
        Calendar date = metadata.getDate(MediaMetadata.KEY_RELEASE_DATE);
        if (date != null) {
            bundle.putLong(MediaMetadata.KEY_RELEASE_DATE, date.getTimeInMillis());
        }
        bundle.putInt("media-type", mediaInfo.getMetadata().getMediaType());
        bundle.putString("movie-urls", mediaInfo.getContentId());
        bundle.putString(MediaMetadata.KEY_STUDIO, metadata.getString(MediaMetadata.KEY_STUDIO));
        bundle.putString("content-type", mediaInfo.getContentType());
        bundle.putInt("stream-type", mediaInfo.getStreamType());
        bundle.putLong("stream-duration", mediaInfo.getStreamDuration());
        if (!metadata.getImages().isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (WebImage url : metadata.getImages()) {
                arrayList.add(url.getUrl().toString());
            }
            bundle.putStringArrayList("images", arrayList);
        }
        JSONObject customData = mediaInfo.getCustomData();
        if (customData != null) {
            bundle.putString("custom-data", customData.toString());
        }
        if (!(mediaInfo.getMediaTracks() == null || mediaInfo.getMediaTracks().isEmpty())) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (MediaTrack mediaTrack : mediaInfo.getMediaTracks()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("track-name", mediaTrack.getName());
                    jSONObject.put("track-custom-id", mediaTrack.getContentId());
                    jSONObject.put("track-id", mediaTrack.getId());
                    jSONObject.put("track-language", mediaTrack.getLanguage());
                    jSONObject.put("track-type", mediaTrack.getType());
                    jSONObject.put("track-content-type", mediaTrack.getContentType());
                    if (mediaTrack.getSubtype() != -1) {
                        jSONObject.put("track-subtype", mediaTrack.getSubtype());
                    }
                    if (mediaTrack.getCustomData() != null) {
                        jSONObject.put("track-custom-data", mediaTrack.getCustomData().toString());
                    }
                    jSONArray.put(jSONObject);
                }
                bundle.putString("track-data", jSONArray.toString());
            } catch (JSONException e) {
                b.a(b, "mediaInfoToBundle(): Failed to convert Tracks data to json", e);
            }
        }
        return bundle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e6 A:{SYNTHETIC, Splitter:B:23:0x00e6} */
    public static com.google.android.gms.cast.MediaInfo a(android.os.Bundle r12) {
        /*
        r0 = 0;
        if (r12 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = new com.google.android.gms.cast.MediaMetadata;
        r2 = "media-type";
        r2 = r12.getInt(r2);
        r1.<init>(r2);
        r2 = "com.google.android.gms.cast.metadata.SUBTITLE";
        r3 = "com.google.android.gms.cast.metadata.SUBTITLE";
        r3 = r12.getString(r3);
        r1.putString(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.TITLE";
        r3 = "com.google.android.gms.cast.metadata.TITLE";
        r3 = r12.getString(r3);
        r1.putString(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.STUDIO";
        r3 = "com.google.android.gms.cast.metadata.STUDIO";
        r3 = r12.getString(r3);
        r1.putString(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
        r3 = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
        r3 = r12.getString(r3);
        r1.putString(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
        r3 = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
        r3 = r12.getString(r3);
        r1.putString(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.COMPOSER";
        r3 = "com.google.android.gms.cast.metadata.COMPOSER";
        r3 = r12.getString(r3);
        r1.putString(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.SERIES_TITLE";
        r3 = "com.google.android.gms.cast.metadata.SERIES_TITLE";
        r3 = r12.getString(r3);
        r1.putString(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
        r3 = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
        r3 = r12.getInt(r3);
        r1.putInt(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
        r3 = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
        r3 = r12.getInt(r3);
        r1.putInt(r2, r3);
        r2 = "com.google.android.gms.cast.metadata.RELEASE_DATE";
        r3 = 0;
        r5 = r12.getLong(r2, r3);
        r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1));
        if (r2 <= 0) goto L_0x008a;
    L_0x007e:
        r2 = java.util.Calendar.getInstance();
        r2.setTimeInMillis(r5);
        r5 = "com.google.android.gms.cast.metadata.RELEASE_DATE";
        r1.putDate(r5, r2);
    L_0x008a:
        r2 = "images";
        r2 = r12.getStringArrayList(r2);
        if (r2 == 0) goto L_0x00b5;
    L_0x0092:
        r5 = r2.isEmpty();
        if (r5 != 0) goto L_0x00b5;
    L_0x0098:
        r2 = r2.iterator();
    L_0x009c:
        r5 = r2.hasNext();
        if (r5 == 0) goto L_0x00b5;
    L_0x00a2:
        r5 = r2.next();
        r5 = (java.lang.String) r5;
        r5 = android.net.Uri.parse(r5);
        r6 = new com.google.android.gms.common.images.WebImage;
        r6.<init>(r5);
        r1.addImage(r6);
        goto L_0x009c;
    L_0x00b5:
        r2 = "custom-data";
        r2 = r12.getString(r2);
        r5 = android.text.TextUtils.isEmpty(r2);
        if (r5 != 0) goto L_0x00dd;
    L_0x00c1:
        r5 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00c7 }
        r5.<init>(r2);	 Catch:{ JSONException -> 0x00c7 }
        goto L_0x00de;
    L_0x00c7:
        r5 = b;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "Failed to deserialize the custom data string: custom data= ";
        r6.append(r7);
        r6.append(r2);
        r2 = r6.toString();
        com.cast_music.b.b.b(r5, r2);
    L_0x00dd:
        r5 = r0;
    L_0x00de:
        r2 = "track-data";
        r2 = r12.getString(r2);
        if (r2 == 0) goto L_0x019d;
    L_0x00e6:
        r2 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x0192 }
        r6 = "track-data";
        r6 = r12.getString(r6);	 Catch:{ JSONException -> 0x0192 }
        r2.<init>(r6);	 Catch:{ JSONException -> 0x0192 }
        r6 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x0192 }
        r6.<init>();	 Catch:{ JSONException -> 0x0192 }
        r0 = r2.length();	 Catch:{ JSONException -> 0x0190 }
        if (r0 <= 0) goto L_0x019e;
    L_0x00fc:
        r0 = 0;
    L_0x00fd:
        r7 = r2.length();	 Catch:{ JSONException -> 0x0190 }
        if (r0 >= r7) goto L_0x019e;
    L_0x0103:
        r7 = r2.get(r0);	 Catch:{ JSONException -> 0x0190 }
        r7 = (org.json.JSONObject) r7;	 Catch:{ JSONException -> 0x0190 }
        r8 = new com.google.android.gms.cast.MediaTrack$Builder;	 Catch:{ JSONException -> 0x0190 }
        r9 = "track-id";
        r9 = r7.getLong(r9);	 Catch:{ JSONException -> 0x0190 }
        r11 = "track-type";
        r11 = r7.getInt(r11);	 Catch:{ JSONException -> 0x0190 }
        r8.<init>(r9, r11);	 Catch:{ JSONException -> 0x0190 }
        r9 = "track-name";
        r9 = r7.has(r9);	 Catch:{ JSONException -> 0x0190 }
        if (r9 == 0) goto L_0x012b;
    L_0x0122:
        r9 = "track-name";
        r9 = r7.getString(r9);	 Catch:{ JSONException -> 0x0190 }
        r8.setName(r9);	 Catch:{ JSONException -> 0x0190 }
    L_0x012b:
        r9 = "track-subtype";
        r9 = r7.has(r9);	 Catch:{ JSONException -> 0x0190 }
        if (r9 == 0) goto L_0x013c;
    L_0x0133:
        r9 = "track-subtype";
        r9 = r7.getInt(r9);	 Catch:{ JSONException -> 0x0190 }
        r8.setSubtype(r9);	 Catch:{ JSONException -> 0x0190 }
    L_0x013c:
        r9 = "track-custom-id";
        r9 = r7.has(r9);	 Catch:{ JSONException -> 0x0190 }
        if (r9 == 0) goto L_0x014d;
    L_0x0144:
        r9 = "track-custom-id";
        r9 = r7.getString(r9);	 Catch:{ JSONException -> 0x0190 }
        r8.setContentId(r9);	 Catch:{ JSONException -> 0x0190 }
    L_0x014d:
        r9 = "track-content-type";
        r9 = r7.has(r9);	 Catch:{ JSONException -> 0x0190 }
        if (r9 == 0) goto L_0x015e;
    L_0x0155:
        r9 = "track-content-type";
        r9 = r7.getString(r9);	 Catch:{ JSONException -> 0x0190 }
        r8.setContentType(r9);	 Catch:{ JSONException -> 0x0190 }
    L_0x015e:
        r9 = "track-language";
        r9 = r7.has(r9);	 Catch:{ JSONException -> 0x0190 }
        if (r9 == 0) goto L_0x016f;
    L_0x0166:
        r9 = "track-language";
        r9 = r7.getString(r9);	 Catch:{ JSONException -> 0x0190 }
        r8.setLanguage(r9);	 Catch:{ JSONException -> 0x0190 }
    L_0x016f:
        r9 = "track-data";
        r9 = r7.has(r9);	 Catch:{ JSONException -> 0x0190 }
        if (r9 == 0) goto L_0x0185;
    L_0x0177:
        r9 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0190 }
        r10 = "track-data";
        r7 = r7.getString(r10);	 Catch:{ JSONException -> 0x0190 }
        r9.<init>(r7);	 Catch:{ JSONException -> 0x0190 }
        r8.setCustomData(r9);	 Catch:{ JSONException -> 0x0190 }
    L_0x0185:
        r7 = r8.build();	 Catch:{ JSONException -> 0x0190 }
        r6.add(r7);	 Catch:{ JSONException -> 0x0190 }
        r0 = r0 + 1;
        goto L_0x00fd;
    L_0x0190:
        r0 = move-exception;
        goto L_0x0195;
    L_0x0192:
        r2 = move-exception;
        r6 = r0;
        r0 = r2;
    L_0x0195:
        r2 = b;
        r7 = "Failed to build media tracks from the wrapper bundle";
        com.cast_music.b.b.a(r2, r7, r0);
        goto L_0x019e;
    L_0x019d:
        r6 = r0;
    L_0x019e:
        r0 = new com.google.android.gms.cast.MediaInfo$Builder;
        r2 = "movie-urls";
        r2 = r12.getString(r2);
        r0.<init>(r2);
        r2 = "stream-type";
        r2 = r12.getInt(r2);
        r0 = r0.setStreamType(r2);
        r2 = "content-type";
        r2 = r12.getString(r2);
        r0 = r0.setContentType(r2);
        r0 = r0.setMetadata(r1);
        r0 = r0.setCustomData(r5);
        r0 = r0.setMediaTracks(r6);
        r1 = "stream-duration";
        r1 = r12.containsKey(r1);
        if (r1 == 0) goto L_0x01e4;
    L_0x01d1:
        r1 = "stream-duration";
        r1 = r12.getLong(r1);
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 < 0) goto L_0x01e4;
    L_0x01db:
        r1 = "stream-duration";
        r1 = r12.getLong(r1);
        r0.setStreamDuration(r1);
    L_0x01e4:
        r12 = r0.build();
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cast_music.b.d.a(android.os.Bundle):com.google.android.gms.cast.MediaInfo");
    }

    public static String a(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(e.ad)).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getSSID() : null;
    }

    public static int a(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static String a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(" cannot be null or empty");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public static Point b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }
}
