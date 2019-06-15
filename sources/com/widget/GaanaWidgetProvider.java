package com.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.gaana.BuildConfig;
import com.gaana.R;
import com.gaana.SplashScreenActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.an;
import com.managers.f;
import com.managers.n;
import com.managers.o;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerConstants.PlayerCommands;
import com.player_framework.PlayerStatus;
import com.player_framework.PlayerStatus.PlayerStates;
import com.services.d;
import com.services.l.r;
import com.utilities.Util;
import com.utilities.g;
import java.util.ArrayList;

public class GaanaWidgetProvider extends AppWidgetProvider {
    static boolean b = false;
    public static Track c;
    Bitmap a = null;
    PlayerManager d = null;
    private RemoteViews e;
    private Context f;
    private int g;

    private static int a(int i) {
        int i2 = 2;
        while ((70 * i2) - 30 < i) {
            i2++;
        }
        return i2 - 1;
    }

    private void a(boolean z) {
        d.a().a("PREFERENCE_DOES_WIDGET_EXIST", z, false);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        a(true);
        this.f = context;
        a(appWidgetManager, iArr);
    }

    public void onDisabled(Context context) {
        super.onDisabled(context);
        a(false);
    }

    public void onEnabled(Context context) {
        super.onEnabled(context);
        a(true);
        this.f = context;
        AppWidgetManager instance = AppWidgetManager.getInstance(this.f);
        a(instance, instance.getAppWidgetIds(new ComponentName(this.f, GaanaWidgetProvider.class)));
    }

    private void a(AppWidgetManager appWidgetManager, int[] iArr) {
        int length = iArr.length;
        this.e = new RemoteViews(this.f.getPackageName(), R.layout.gaana_widget_player_layout);
        for (int i = 0; i < length; i++) {
            this.g = i;
            a(this.f, this.e, iArr[i]);
            appWidgetManager.updateAppWidget(iArr[i], this.e);
        }
    }

    private ComponentName a(Context context, RemoteViews remoteViews, final int i) {
        this.e = remoteViews;
        this.e.setOnClickPendingIntent(R.id.notification_base, b(false));
        a();
        if (b) {
            this.e.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_play);
        } else {
            this.e.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
        }
        if (c == null) {
            this.e.setViewVisibility(R.id.track_info, 8);
            this.e.setViewVisibility(R.id.empty_track, 0);
            this.e.setViewVisibility(R.id.tv_bottom_line, 0);
        } else {
            this.e.setViewVisibility(R.id.track_info, 0);
            this.e.setViewVisibility(R.id.empty_track, 8);
            this.e.setViewVisibility(R.id.tv_bottom_line, 8);
            String albumTitle = c.getAlbumTitle();
            String artistNames = c.getArtistNames();
            this.e.setTextViewText(R.id.track_title, c.getTrackTitle());
            if (TextUtils.isEmpty(artistNames)) {
                this.e.setTextViewText(R.id.track_details, albumTitle);
            } else {
                RemoteViews remoteViews2 = this.e;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(albumTitle);
                stringBuilder.append(" - ");
                stringBuilder.append(artistNames);
                remoteViews2.setTextViewText(R.id.track_details, stringBuilder.toString());
            }
            i.a().a(c.getArtworkLarge(), new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    GaanaWidgetProvider.this.a = bitmap;
                    if (GaanaWidgetProvider.this.a == null) {
                        i.a().a(GaanaWidgetProvider.c.getArtwork(), new r() {
                            public void onErrorResponse(VolleyError volleyError) {
                            }

                            public void onSuccessfulResponse(Bitmap bitmap) {
                                GaanaWidgetProvider.this.a = bitmap;
                                if (!(GaanaWidgetProvider.this.a == null || f.v().w() || GaanaWidgetProvider.c == null)) {
                                    try {
                                        AppWidgetManager instance = AppWidgetManager.getInstance(GaanaWidgetProvider.this.f);
                                        GaanaWidgetProvider.this.e.setImageViewBitmap(R.id.notification_base_image, GaanaWidgetProvider.this.a);
                                        instance.updateAppWidget(i, GaanaWidgetProvider.this.e);
                                    } catch (Exception e) {
                                        ThrowableExtension.printStackTrace(e);
                                    }
                                }
                            }
                        });
                    } else if (!(f.v().w() || GaanaWidgetProvider.c == null)) {
                        try {
                            AppWidgetManager instance = AppWidgetManager.getInstance(GaanaWidgetProvider.this.f);
                            GaanaWidgetProvider.this.e.setImageViewBitmap(R.id.notification_base_image, GaanaWidgetProvider.this.a);
                            instance.updateAppWidget(i, GaanaWidgetProvider.this.e);
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                }
            });
            if (this.a != null) {
                this.e.setImageViewBitmap(R.id.notification_base_image, this.a);
            } else {
                this.e.setImageViewResource(R.id.notification_base_image, R.drawable.placeholder_album_artwork_large);
            }
            if (c.isLocalMedia() || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                this.e.setImageViewResource(R.id.notification_base_favorite, R.drawable.widget_not_favoritable);
            } else if (c.isFavorite().booleanValue()) {
                this.e.setImageViewResource(R.id.notification_base_favorite, R.drawable.favorited_track);
            } else {
                this.e.setImageViewResource(R.id.notification_base_favorite, R.drawable.unfavorited_track);
            }
        }
        return new ComponentName(context, GaanaWidgetProvider.class);
    }

    private RemoteViews a(Context context, int i, int i2) {
        a(i2);
        a(i);
        return new RemoteViews(context.getPackageName(), R.layout.gaana_widget_player_layout);
    }

    private void a() {
        this.e.setOnClickPendingIntent(R.id.track_controls, b(-1));
        this.e.setOnClickPendingIntent(R.id.notification_base_play, b(1));
        this.e.setOnClickPendingIntent(R.id.notification_base_next, b(2));
        this.e.setOnClickPendingIntent(R.id.notification_base_previous, b(3));
        if ((c != null && c.isLocalMedia()) || !Util.j(this.f)) {
            this.e.setOnClickPendingIntent(R.id.notification_base_favorite, b(-1));
        } else if (c != null && !GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            this.e.setOnClickPendingIntent(R.id.notification_base_favorite, b(true));
        } else if (c != null) {
            this.e.setOnClickPendingIntent(R.id.notification_base_favorite, a("APP_WIDGET_FAV_STATE_CHANGE"));
        }
        if (f.v().w()) {
            this.e.setImageViewResource(R.id.notification_base_previous, R.drawable.previous_track);
            this.e.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
            this.e.setOnClickPendingIntent(R.id.notification_base_play, b(-1));
            this.e.setOnClickPendingIntent(R.id.notification_base_favorite, b(-1));
            this.e.setOnClickPendingIntent(R.id.notification_base_previous, b(-1));
            if (f.a) {
                this.e.setOnClickPendingIntent(R.id.notification_base_next, b(2));
                this.e.setImageViewResource(R.id.notification_base_next, R.drawable.next_track);
                return;
            }
            this.e.setOnClickPendingIntent(R.id.notification_base_next, b(-1));
            this.e.setImageViewResource(R.id.notification_base_next, R.drawable.next_track);
        } else if (PlayerManager.a(this.f).m() == PlayerType.GAANA_RADIO) {
            this.e.setImageViewResource(R.id.notification_base_previous, R.drawable.previous_track);
            this.e.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
            this.e.setImageViewResource(R.id.notification_base_next, R.drawable.next_track);
            this.e.setViewVisibility(R.id.notification_base_previous, 4);
            this.e.setViewVisibility(R.id.notification_base_play, 0);
            if (ad.a(GaanaApplication.getContext()).o().booleanValue()) {
                this.e.setViewVisibility(R.id.notification_base_next, 4);
            } else {
                this.e.setViewVisibility(R.id.notification_base_next, 0);
            }
            this.e.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
            if (c == null || !c.isFavorite().booleanValue()) {
                this.e.setImageViewResource(R.id.notification_base_favorite, R.drawable.unfavorited_track);
            } else {
                this.e.setImageViewResource(R.id.notification_base_favorite, R.drawable.favorited_track);
            }
        } else {
            this.e.setViewVisibility(R.id.notification_base_play, 0);
            this.e.setViewVisibility(R.id.notification_base_previous, 0);
            this.e.setViewVisibility(R.id.notification_base_next, 0);
            this.e.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
            this.e.setImageViewResource(R.id.notification_base_previous, R.drawable.previous_track);
            this.e.setImageViewResource(R.id.notification_base_next, R.drawable.next_track);
            if (c == null || !c.isFavorite().booleanValue()) {
                this.e.setImageViewResource(R.id.notification_base_favorite, R.drawable.unfavorited_track);
            } else {
                this.e.setImageViewResource(R.id.notification_base_favorite, R.drawable.favorited_track);
            }
        }
    }

    private final PendingIntent b(int i) {
        Intent intent;
        switch (i) {
            case 1:
                intent = new Intent(this.f, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_PAUSE.toInt());
                intent.putExtra("EXTRA_PLAYER_COMMAND_ARG", PauseReasons.MEDIA_BUTTON_TAP.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", false);
                intent.putExtra("IS_FROM_WIDGET", true);
                intent.setData(Uri.parse("WIDGET"));
                return PendingIntent.getService(this.f, 1, intent, 0);
            case 2:
                intent = new Intent(this.f, GaanaMusicService.class);
                intent.setData(Uri.parse("WIDGET"));
                intent.putExtra("IS_FROM_WIDGET", true);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_NEXT.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", false);
                return PendingIntent.getService(this.f, 2, intent, 0);
            case 3:
                intent = new Intent(this.f, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_PREVIOUS.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", false);
                intent.putExtra("IS_FROM_WIDGET", true);
                intent.setData(Uri.parse("WIDGET"));
                return PendingIntent.getService(this.f, 3, intent, 0);
            default:
                return null;
        }
    }

    private PendingIntent b(boolean z) {
        Intent intent = new Intent(this.f, SplashScreenActivity.class);
        if (g.b()) {
            ComponentName componentName = new ComponentName(BuildConfig.APPLICATION_ID, "com.gaana.SplashScreenActivityMMX");
            Intent intent2 = new Intent();
            intent2.setComponent(componentName);
            intent = intent2;
        }
        intent.putExtra("IS_FROM_NOTIFICATION", false);
        intent.putExtra("IS_FROM_WIDGET", true);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        if (z) {
            if (!Util.j(this.f) || d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false) <= 1) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("gaana://view/addtofavorite/");
            stringBuilder.append(c.getBusinessObjId());
            intent.setData(Uri.parse(stringBuilder.toString()));
        }
        return PendingIntent.getActivity(this.f.getApplicationContext(), 0, intent, 134217728);
    }

    private PendingIntent a(String str) {
        Intent intent = new Intent(this.f, GaanaWidgetProvider.class);
        intent.putExtra("IS_FROM_NOTIFICATION", false);
        intent.putExtra("IS_FROM_WIDGET", true);
        intent.setAction(str);
        return PendingIntent.getBroadcast(this.f, this.g, intent, 134217728);
    }

    public void onReceive(Context context, Intent intent) {
        this.f = context;
        AppWidgetManager instance;
        int[] appWidgetIds;
        AppWidgetManager instance2;
        if (intent.getAction().equals("APP_WIDGET_UPDATE_Q_EMPTY")) {
            c = null;
            instance = AppWidgetManager.getInstance(context);
            appWidgetIds = instance.getAppWidgetIds(new ComponentName(context, GaanaWidgetProvider.class));
            if (appWidgetIds.length > 0) {
                a(instance, appWidgetIds);
            } else {
                a(false);
            }
        } else if (intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE") || intent.getAction().equals("android.appwidget.action.APP_WIDGET_ENABLED")) {
            a(true);
            instance2 = AppWidgetManager.getInstance(this.f);
            a(instance2, instance2.getAppWidgetIds(new ComponentName(this.f, GaanaWidgetProvider.class)));
        } else if (intent.getAction().equals("APP_WIDGET_UPDATE_ACTION")) {
            if (intent.hasExtra("isPaused")) {
                b = intent.getBooleanExtra("isPaused", false);
            } else if (PlayerStatus.a(this.f).d() || PlayerStatus.a(this.f).e()) {
                b = true;
            }
            if (intent.hasExtra("currentTrack")) {
                c = (Track) intent.getSerializableExtra("currentTrack");
            }
            if (c == null) {
                com.i.d.a(new Runnable() {
                    public void run() {
                        GaanaWidgetProvider.this.b();
                    }
                });
            }
            instance = AppWidgetManager.getInstance(context);
            appWidgetIds = instance.getAppWidgetIds(new ComponentName(context, GaanaWidgetProvider.class));
            if (appWidgetIds.length > 0) {
                a(instance, appWidgetIds);
            } else {
                a(false);
            }
        } else if (intent.getAction().contentEquals("com.sec.android.widgetapp.APPWIDGET_RESIZE")) {
            b(context, intent);
        } else if (intent.getAction().contentEquals("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS")) {
            a(context, intent);
        } else if (intent.getAction().equalsIgnoreCase("APP_WIDGET_FAV_STATE_CHANGE")) {
            if (!(c == null || c.isLocalMedia())) {
                if (!intent.hasExtra("trackID")) {
                    d();
                } else if (!intent.getStringExtra("trackID").equalsIgnoreCase(c.getBusinessObjId())) {
                    return;
                }
                instance2 = AppWidgetManager.getInstance(this.f);
                int[] appWidgetIds2 = instance2.getAppWidgetIds(new ComponentName(this.f, GaanaWidgetProvider.class));
                if (appWidgetIds2.length > 0) {
                    a(instance2, appWidgetIds2);
                } else {
                    a(false);
                }
            }
        } else if (intent.getAction().equalsIgnoreCase("android.appwidget.action.APPWIDGET_DISABLED")) {
            a(false);
        }
    }

    private void b() {
        if (this.d == null) {
            this.d = PlayerManager.a(this.f);
        }
        if (this.d.n() == null) {
            ArrayList e = o.a().e();
            int b = d.a().b("PREFERENCE_KEY_LAST_PLAYED_TRACK_INDEX", 0, true);
            if (e != null && e.size() > 0) {
                if (b < 0 || b > e.size() - 1 || b > Constants.dg - 1) {
                    b = 0;
                }
                PlayerManager.a(this.f).a(e, (PlayerTrack) e.get(b));
                c = PlayerManager.a(this.f).i().a(true);
                c();
                this.d.a(PlayerType.GAANA, this.f);
                PlayerStatus.a(this.f, PlayerStates.STOPPED);
                PlayerManager.a = false;
            }
        } else if (PlayerManager.a(this.f).i() != null) {
            c = PlayerManager.a(this.f).i().a(true);
        } else {
            c = null;
        }
    }

    private void c() {
        d a = d.a();
        if (a.b("PREFERENCE_KEY_SHUFFLE_STATUS", false, true)) {
            ArrayList f = o.a().f();
            if (f == null || f.size() <= 0) {
                a.a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
            } else {
                PlayerManager.a(this.f).a(f);
            }
        }
        int b = a.b("PREFERENCE_KEY_REPEAT_STATUS", 2, true);
        if (b == 1) {
            PlayerManager.a(this.f).b(true);
        } else if (b == 2) {
            PlayerManager.a(this.f).c(true);
        }
    }

    private void a(Context context, Intent intent) {
        AppWidgetManager instance = AppWidgetManager.getInstance(context);
        int intExtra = intent.getIntExtra("appWidgetId", 0);
        Bundle bundle = new Bundle();
        if (VERSION.SDK_INT >= 16) {
            bundle = instance.getAppWidgetOptions(intExtra);
        } else {
            bundle.putInt("appWidgetMinHeight", 0);
            bundle.putInt("appWidgetMinWidth", 0);
        }
        onAppWidgetOptionsChanged(context, instance, intExtra, bundle);
    }

    private void b(Context context, Intent intent) {
        AppWidgetManager instance = AppWidgetManager.getInstance(context);
        int intExtra = intent.getIntExtra("widgetId", 0);
        int intExtra2 = intent.getIntExtra("widgetspanx", 0);
        int intExtra3 = intent.getIntExtra("widgetspany", 0);
        if (intExtra > 0 && intExtra2 > 0 && intExtra3 > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("appWidgetMinHeight", intExtra3 * 74);
            bundle.putInt("appWidgetMinWidth", intExtra2 * 74);
            onAppWidgetOptionsChanged(context, instance, intExtra, bundle);
        }
    }

    private void d() {
        if (c.isFavorite().booleanValue()) {
            c.setFavorite(Boolean.valueOf(false));
            n.a().c(c);
            n.a().b("Widget", c.getBusinessObjId());
        } else {
            c.setFavorite(Boolean.valueOf(true));
            n.a().b(c);
            n.a().a("Widget", c.getBusinessObjId());
        }
        an.a().a("click", "ac", "", "Widget", "", "fav", "", "");
    }

    @TargetApi(16)
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        RemoteViews a = a(context, bundle.getInt("appWidgetMinWidth"), bundle.getInt("appWidgetMinHeight"));
        a(context, a, i);
        appWidgetManager.updateAppWidget(i, a);
        super.onAppWidgetOptionsChanged(context, appWidgetManager, i, bundle);
    }
}
