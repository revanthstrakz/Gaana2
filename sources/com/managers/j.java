package com.managers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.gaana.R;
import com.gaana.SplashScreenActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Notifications;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.view.DownloadClickAnimation;
import com.gaana.view.item.DownloadSongListingView;
import com.gaana.view.item.DownloadSongsItemView;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.services.FileDownloadService;
import com.services.d;
import com.utilities.Util;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    private static j d;
    public a a;
    public a b;
    public a c;
    private NotificationManager e;
    private Builder f;
    private Notification g;

    public interface a {
        void updateUiForCircularProgressBar(int i, int i2);
    }

    private j(Context context) {
        this.e = (NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
        this.f = new Builder(context, "com.gaana.download");
    }

    public void a(DownloadSongsItemView downloadSongsItemView) {
        this.a = downloadSongsItemView;
    }

    public void a(DownloadSongListingView downloadSongListingView) {
        this.b = downloadSongListingView;
    }

    public void a(DownloadClickAnimation downloadClickAnimation) {
        this.c = downloadClickAnimation;
    }

    public static j a(Context context) {
        if (d == null) {
            d = new j(context);
        }
        return d;
    }

    public Notification a() {
        return this.f.build();
    }

    public void a(Context context, String str, String str2) {
        this.f.setContentTitle(str).setContentText(str2).setSmallIcon(Util.w());
        if (VERSION.SDK_INT >= 21) {
            this.f.setColor(GaanaApplication.getContext().getResources().getColor(R.color.notification_color_filler));
        }
        this.f.setProgress(0, 0, true);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", "view/mymusic/downloads");
            str = jSONObject.toString();
            Intent intent = new Intent(context, SplashScreenActivity.class);
            intent.setFlags(603979776);
            intent.setAction("android.intent.action.VIEW");
            intent.putExtra("data", str);
            this.f.setContentIntent(PendingIntent.getActivity(context, 1002, intent, C.ENCODING_PCM_MU_LAW));
            this.g = this.f.build();
            if (context instanceof FileDownloadService) {
                ((FileDownloadService) context).startForeground(1002, this.g);
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void b() {
        if (this.e != null) {
            this.e.cancel(1002);
        }
    }

    public void a(int i, int i2, String str) {
        if (DownloadManager.c().v() && this.e != null) {
            this.f.setContentText(str);
            if (i == i2) {
                this.f.setProgress(0, 0, false);
            } else {
                this.f.setProgress(i, i2, false);
            }
            if (this.a != null) {
                this.a.updateUiForCircularProgressBar(i, i2);
            }
            if (this.b != null) {
                this.b.updateUiForCircularProgressBar(i, i2);
            }
            if (this.c != null) {
                this.c.updateUiForCircularProgressBar(i, i2);
            }
            this.e.notify(1002, this.f.build());
        }
    }

    public void a(Context context, ArrayList<BusinessObject> arrayList, Bitmap bitmap) {
        if (arrayList != null && arrayList.size() > 0) {
            RemoteViews remoteViews;
            if (arrayList.size() == 1) {
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_download_notification_single);
            } else {
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_download_notification);
            }
            Builder builder = new Builder(context);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
            builder.setContent(remoteViews).setSmallIcon(Util.w());
            remoteViews.setImageViewBitmap(R.id.image_noti_image, bitmap);
            String trackTitle = ((Track) arrayList.get(0)).getTrackTitle();
            if (trackTitle.length() > 22) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(trackTitle.substring(0, 19));
                stringBuilder.append("...");
                trackTitle = stringBuilder.toString();
            }
            if (arrayList.size() > 1) {
                remoteViews.setTextViewText(R.id.title, context.getString(R.string.download_noti_plural_title, new Object[]{Integer.valueOf(arrayList.size())}));
                Object[] objArr = new Object[1];
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("'");
                stringBuilder2.append(trackTitle);
                stringBuilder2.append("'");
                objArr[0] = stringBuilder2.toString();
                remoteViews.setTextViewText(R.id.subtitle, context.getString(R.string.download_noti_plural_subtitle, objArr));
            } else {
                remoteViews.setTextViewText(R.id.title, context.getString(R.string.download_noti_singular_title, new Object[]{trackTitle}));
                remoteViews.setTextViewText(R.id.subtitle, context.getString(R.string.download_noti_singular_subtitle));
            }
            if (VERSION.SDK_INT >= 21) {
                remoteViews.setTextColor(R.id.title, context.getResources().getColor(R.color.black));
                remoteViews.setTextColor(R.id.subtitle, context.getResources().getColor(R.color.black_alfa_50));
                if (arrayList.size() > 1) {
                    remoteViews.setInt(R.id.image_noti_bg, "setBackgroundResource", R.color.black_alfa_10);
                }
            } else {
                remoteViews.setTextColor(R.id.title, context.getResources().getColor(R.color.white));
                remoteViews.setTextColor(R.id.subtitle, context.getResources().getColor(R.color.white_alfa_50));
                if (arrayList.size() > 1) {
                    remoteViews.setInt(R.id.image_noti_bg, "setBackgroundResource", R.color.white_alfa_10);
                }
            }
            JSONObject jSONObject = new JSONObject();
            try {
                Gson create = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create();
                Object tracks = new Tracks();
                tracks.setArrListBusinessObj(arrayList);
                jSONObject.put("notificationTrackData", create.toJson(tracks));
                jSONObject.put("url", "view/mymusic/songs/1");
                String jSONObject2 = jSONObject.toString();
                Intent intent = new Intent(context, SplashScreenActivity.class);
                intent.setFlags(603979776);
                intent.setAction("android.intent.action.VIEW");
                intent.putExtra("data", jSONObject2);
                builder.setContentIntent(PendingIntent.getActivity(context, 1003, intent, C.ENCODING_PCM_MU_LAW));
                builder.setAutoCancel(true);
                notificationManager.notify(1003, builder.build());
                u.a().b("Download Notification", "DN_Impressions");
                d.a().a(System.currentTimeMillis(), "PREFERENCE_DOWNLOAD_NOTIFICATION_LAST_SHOWN", false);
                a(jSONObject2);
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Notifications.Notification notification = new Notifications.Notification();
            notification.setTimestamp(System.currentTimeMillis());
            notification.setNotificationId(NativeContentAd.ASSET_CALL_TO_ACTION);
            notification.setMessageDetails(str);
            notification.setType("offline_play_notif");
            aa.a().a(notification, true);
        }
    }
}
