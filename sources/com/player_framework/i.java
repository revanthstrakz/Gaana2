package com.player_framework;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.gaana.BuildConfig;
import com.gaana.R;
import com.gaana.SplashScreenActivity;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.f;
import com.models.PlayerTrack;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerConstants.PlayerCommands;
import com.services.l.r;
import com.utilities.Util;
import com.utilities.d;
import com.utilities.g;

@SuppressLint({"NewApi"})
public class i extends a {
    Bitmap a = null;
    private final NotificationManager b;
    private final GaanaMusicService c;
    private RemoteViews d;
    private Notification e = null;
    private RemoteViews f;
    private boolean g = true;

    public i(GaanaMusicService gaanaMusicService) {
        this.c = gaanaMusicService;
        this.b = (NotificationManager) gaanaMusicService.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
        this.d = new RemoteViews(this.c.getPackageName(), R.layout.notification_template_base);
        String str = VERSION.RELEASE;
        int w = Util.w();
        if (str == null || !(str.equalsIgnoreCase("4.0.3") || str.equals("4.0.4"))) {
            this.e = new Builder(this.c, "com.gaana.play").setSmallIcon(w).setContentIntent(b()).setPriority(0).setVisibility(1).setContent(this.d).build();
            return;
        }
        this.d = new RemoteViews(this.c.getPackageName(), R.layout.notification_template_v404);
        this.e = new Notification();
        this.e.contentView = this.d;
        Notification notification = this.e;
        notification.flags |= 2;
        this.e.icon = w;
        this.e.contentIntent = b();
    }

    public Notification a() {
        return this.e;
    }

    public void b(String str, String str2, String str3, long j, Bitmap bitmap) {
        a(str3, str2, bitmap);
        String str4 = VERSION.RELEASE;
        if (str4 == null || !(str4.equalsIgnoreCase("4.0.3") || str4.equals("4.0.4"))) {
            d();
            if (d.g()) {
                this.f = new RemoteViews(this.c.getPackageName(), R.layout.notification_template_expanded_base);
                this.e.bigContentView = this.f;
                c();
                a(str3, str, str2, bitmap);
            }
            this.c.startForeground(1000, this.e);
            return;
        }
        a(str3, str2, bitmap);
        this.c.startForeground(1000, this.e);
    }

    public void a(final PlayerTrack playerTrack, long j) {
        String englishAlbumTitle = playerTrack.a(true).getEnglishAlbumTitle();
        String englishArtistNames = playerTrack.a(true).getEnglishArtistNames();
        String englishName = playerTrack.a(true).getEnglishName();
        GaanaApplication.getInstance();
        String language = GaanaApplication.getLanguage(GaanaApplication.getContext());
        if (!TextUtils.isEmpty(language) && language.equalsIgnoreCase("hindi")) {
            englishAlbumTitle = playerTrack.a(true).getAlbumTitle();
            englishArtistNames = playerTrack.a(true).getArtistNames();
            englishName = playerTrack.a(true).getTrackTitle();
        }
        com.i.i.a().a(playerTrack.a(true).getArtworkLarge(), new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                try {
                    i.this.a = bitmap;
                    if (!(i.this.a == null || f.v().w())) {
                        if (!f.v().t()) {
                            if (playerTrack != null && playerTrack.h().equalsIgnoreCase(playerTrack.h())) {
                                try {
                                    i.this.d.setImageViewBitmap(R.id.notification_base_image, i.this.a);
                                    i.this.f.setImageViewBitmap(R.id.notification_expanded_base_image, i.this.a);
                                    i.this.f.setImageViewResource(R.id.notification_expanded_bg, R.drawable.bg_notification);
                                    i.this.b.notify(1000, i.this.e);
                                } catch (Exception e) {
                                    ThrowableExtension.printStackTrace(e);
                                }
                            }
                        }
                    }
                } catch (OutOfMemoryError unused) {
                }
            }
        });
        if (playerTrack.b().isLocalMedia()) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("content://media/external/audio/albumart/");
                stringBuilder.append(playerTrack.b().getAlbumId());
                this.a = Media.getBitmap(this.c.getContentResolver(), Uri.parse(stringBuilder.toString()));
            } catch (Exception unused) {
                this.a = BitmapFactory.decodeResource(this.c.getResources(), R.drawable.placeholder_album_artwork_large);
            }
        }
        String str = VERSION.RELEASE;
        a(englishName, englishArtistNames, this.a);
        if (str == null || !(str.equalsIgnoreCase("4.0.3") || str.equals("4.0.4"))) {
            d();
            if (d.g()) {
                this.f = new RemoteViews(this.c.getPackageName(), R.layout.notification_template_expanded_base);
                this.e.bigContentView = this.f;
                c();
                a(englishName, englishAlbumTitle, englishArtistNames, this.a);
            }
            this.c.startForeground(1000, this.e);
            return;
        }
        a(englishName, englishArtistNames, this.a);
        this.c.startForeground(1000, this.e);
    }

    public void a(String str, String str2, String str3, long j, Bitmap bitmap) {
        this.g = false;
        b(str, str2, str3, j, bitmap);
        this.g = true;
    }

    public void a(boolean z) {
        if (this.e != null && this.b != null && !f.v().t()) {
            boolean a = d.a();
            int i = R.drawable.ic_notif_pause;
            if (a && this.d != null) {
                this.d.setImageViewResource(R.id.notification_base_play, z ? R.drawable.ic_notif_play : R.drawable.ic_notif_pause);
            }
            if (d.g() && this.f != null && d.g()) {
                RemoteViews remoteViews = this.f;
                if (z) {
                    i = R.drawable.ic_notif_play;
                }
                remoteViews.setImageViewResource(R.id.notification_expanded_base_play, i);
            }
            try {
                this.b.notify(1000, this.e);
            } catch (Exception | IllegalStateException unused) {
            }
        }
    }

    private PendingIntent b() {
        Intent intent = new Intent(this.c, SplashScreenActivity.class);
        if (g.b()) {
            ComponentName componentName = new ComponentName(BuildConfig.APPLICATION_ID, "com.gaana.SplashScreenActivityMMX");
            Intent intent2 = new Intent();
            intent2.setComponent(componentName);
            intent = intent2;
        }
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("gaana://view/player/"));
        return PendingIntent.getActivity(this.c.getApplicationContext(), 0, intent, 134217728);
    }

    private void c() {
        this.f.setOnClickPendingIntent(R.id.notification_expanded_base_play, a(1));
        this.f.setOnClickPendingIntent(R.id.notification_expanded_base_next, a(2));
        this.f.setOnClickPendingIntent(R.id.notification_expanded_base_previous, a(3));
        this.f.setOnClickPendingIntent(R.id.notification_expanded_base_collapse, a(4));
        this.f.setOnClickPendingIntent(R.id.notification_expanded_base_favorite, a(5));
        if (!this.g) {
            this.f.setViewVisibility(R.id.notification_expanded_base_favorite, 8);
            this.f.setViewVisibility(R.id.notification_expanded_separator_fav_prev, 8);
            this.f.setViewVisibility(R.id.notification_expanded_base_previous, 8);
            this.f.setViewVisibility(R.id.notification_expanded_separator_prev_play, 8);
            this.f.setViewVisibility(R.id.notification_expanded_base_play, 8);
            this.f.setViewVisibility(R.id.notification_expanded_separator_play_next, 8);
            this.f.setViewVisibility(R.id.notification_expanded_base_next, 8);
        } else if (f.v().w() || f.v().t()) {
            this.f.setImageViewResource(R.id.notification_expanded_base_previous, R.drawable.previous_track);
            this.f.setImageViewResource(R.id.notification_expanded_base_play, R.drawable.ic_notif_pause);
            this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.widget_not_favoritable);
            this.f.setOnClickPendingIntent(R.id.notification_expanded_base_play, a(-1));
            this.f.setOnClickPendingIntent(R.id.notification_expanded_base_previous, a(-1));
            this.f.setOnClickPendingIntent(R.id.notification_expanded_base_favorite, a(-1));
            if (f.a) {
                this.f.setOnClickPendingIntent(R.id.notification_expanded_base_next, a(2));
                this.f.setImageViewResource(R.id.notification_expanded_base_next, R.drawable.next_track);
                return;
            }
            this.f.setOnClickPendingIntent(R.id.notification_expanded_base_next, a(-1));
            this.f.setImageViewResource(R.id.notification_expanded_base_next, R.drawable.ic_notif_next_greyed);
        } else if (PlayerManager.a(this.c).m() == PlayerType.GAANA_RADIO) {
            this.f.setImageViewResource(R.id.notification_expanded_base_previous, R.drawable.previous_track);
            this.f.setImageViewResource(R.id.notification_expanded_base_play, R.drawable.ic_notif_pause);
            this.f.setImageViewResource(R.id.notification_expanded_base_next, R.drawable.next_track);
            if (PlayerManager.a(this.c).i().b().isLocalMedia()) {
                this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.widget_not_favoritable);
                this.f.setOnClickPendingIntent(R.id.notification_expanded_base_favorite, a(-1));
            } else if (!GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.widget_not_favoritable);
                this.f.setOnClickPendingIntent(R.id.notification_expanded_base_favorite, a(6));
            } else if (PlayerManager.a(this.c).i().b().isFavorite().booleanValue()) {
                this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.favorited_track);
            } else {
                this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.unfavorited_track);
            }
            this.f.setViewVisibility(R.id.notification_expanded_base_favorite, 0);
            this.f.setViewVisibility(R.id.notification_expanded_separator_fav_prev, 0);
            this.f.setViewVisibility(R.id.notification_expanded_base_previous, 8);
            this.f.setViewVisibility(R.id.notification_expanded_separator_prev_play, 8);
            this.f.setViewVisibility(R.id.notification_expanded_base_play, 0);
            this.f.setViewVisibility(R.id.notification_expanded_separator_play_next, 0);
            this.f.setViewVisibility(R.id.notification_expanded_base_next, 0);
            if (ad.a(GaanaApplication.getContext()).o().booleanValue()) {
                this.f.setViewVisibility(R.id.notification_expanded_separator_play_next, 8);
                this.f.setViewVisibility(R.id.notification_expanded_base_next, 8);
            }
        } else {
            this.f.setImageViewResource(R.id.notification_expanded_base_previous, R.drawable.previous_track);
            this.f.setImageViewResource(R.id.notification_expanded_base_play, R.drawable.ic_notif_pause);
            this.f.setImageViewResource(R.id.notification_expanded_base_next, R.drawable.next_track);
            if (PlayerManager.a(this.c).i().b().isLocalMedia()) {
                this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.widget_not_favoritable);
                this.f.setOnClickPendingIntent(R.id.notification_expanded_base_favorite, a(-1));
            } else if (!GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.widget_not_favoritable);
                this.f.setOnClickPendingIntent(R.id.notification_expanded_base_favorite, a(6));
            } else if (PlayerManager.a(this.c).i().b().isFavorite().booleanValue()) {
                this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.favorited_track);
            } else {
                this.f.setImageViewResource(R.id.notification_expanded_base_favorite, R.drawable.unfavorited_track);
            }
            this.f.setViewVisibility(R.id.notification_expanded_base_favorite, 0);
            this.f.setViewVisibility(R.id.notification_expanded_separator_fav_prev, 0);
            this.f.setViewVisibility(R.id.notification_expanded_base_previous, 0);
            this.f.setViewVisibility(R.id.notification_expanded_separator_prev_play, 0);
            this.f.setViewVisibility(R.id.notification_expanded_base_play, 0);
            this.f.setViewVisibility(R.id.notification_expanded_separator_play_next, 0);
            this.f.setViewVisibility(R.id.notification_expanded_base_next, 0);
        }
    }

    private void d() {
        this.d.setOnClickPendingIntent(R.id.notification_base_play, a(1));
        this.d.setOnClickPendingIntent(R.id.notification_base_next, a(2));
        this.d.setOnClickPendingIntent(R.id.notification_base_previous, a(3));
        if (!this.g) {
            this.d.setImageViewResource(R.id.notification_base_previous, R.drawable.previous_track);
            this.d.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
            this.d.setImageViewResource(R.id.notification_base_next, R.drawable.next_track);
            this.d.setViewVisibility(R.id.notification_base_play, 8);
            this.d.setViewVisibility(R.id.notification_base_previous, 8);
            this.d.setViewVisibility(R.id.notification_base_next, 8);
            this.d.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
        } else if (f.v().w() || f.v().t()) {
            this.d.setImageViewResource(R.id.notification_base_previous, R.drawable.ic_notif_previous_greyed);
            this.d.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause_greyed);
            this.d.setOnClickPendingIntent(R.id.notification_base_play, a(-1));
            this.d.setOnClickPendingIntent(R.id.notification_base_previous, a(-1));
            if (f.a) {
                this.d.setOnClickPendingIntent(R.id.notification_base_next, a(2));
                this.d.setImageViewResource(R.id.notification_base_next, R.drawable.next_track);
            } else {
                this.d.setOnClickPendingIntent(R.id.notification_base_next, a(-1));
                this.d.setImageViewResource(R.id.notification_base_next, R.drawable.ic_notif_next_greyed);
            }
        } else if (PlayerManager.a(this.c).m() == PlayerType.GAANA_RADIO) {
            this.d.setImageViewResource(R.id.notification_base_previous, R.drawable.previous_track);
            this.d.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
            this.d.setImageViewResource(R.id.notification_base_next, R.drawable.next_track);
            this.d.setViewVisibility(R.id.notification_base_previous, 8);
            this.d.setViewVisibility(R.id.notification_base_play, 0);
            if (ad.a(GaanaApplication.getContext()).o().booleanValue()) {
                this.d.setViewVisibility(R.id.notification_base_next, 8);
            } else {
                this.d.setViewVisibility(R.id.notification_base_next, 0);
            }
            this.d.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
        } else {
            this.d.setViewVisibility(R.id.notification_base_play, 0);
            this.d.setViewVisibility(R.id.notification_base_previous, 0);
            this.d.setViewVisibility(R.id.notification_base_next, 0);
            this.d.setImageViewResource(R.id.notification_base_play, R.drawable.ic_notif_pause);
            this.d.setImageViewResource(R.id.notification_base_previous, R.drawable.previous_track);
            this.d.setImageViewResource(R.id.notification_base_next, R.drawable.next_track);
        }
        this.d.setOnClickPendingIntent(R.id.notification_base_collapse, a(4));
    }

    private final PendingIntent a(int i) {
        Intent intent;
        switch (i) {
            case 1:
                intent = new Intent(this.c, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_PAUSE.toInt());
                intent.putExtra("EXTRA_PLAYER_COMMAND_ARG", PauseReasons.MEDIA_BUTTON_TAP.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.c, 1, intent, 0);
            case 2:
                intent = new Intent(this.c, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_NEXT.toInt());
                intent.putExtra("IS_TRIGGERED_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.c, 2, intent, 0);
            case 3:
                intent = new Intent(this.c, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_PREVIOUS.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.c, 3, intent, 0);
            case 4:
                intent = new Intent(this.c, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.STOP.toInt());
                intent.putExtra("EXTRA_CAST_PLAYER_COMMAND", 1213);
                intent.putExtra("EXTRA_CAST_PLAYER_COMMAND", 1212);
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.c, 4, intent, 0);
            case 5:
                intent = new Intent(this.c, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.FAVORITE_TRACK.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.c, 5, intent, 0);
            case 6:
                intent = new Intent(this.c, SplashScreenActivity.class);
                if (g.b()) {
                    ComponentName componentName = new ComponentName(BuildConfig.APPLICATION_ID, "com.gaana.SplashScreenActivityMMX");
                    Intent intent2 = new Intent();
                    intent2.setComponent(componentName);
                    intent = intent2;
                }
                intent.setAction("android.intent.action.MAIN");
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                intent.addCategory("android.intent.category.LAUNCHER");
                if (!Util.j(this.c) || com.services.d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false) <= 1) {
                    return null;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("gaana://view/addtofavorite/");
                stringBuilder.append(PlayerManager.a(this.c).i().b().getBusinessObjId());
                intent.setData(Uri.parse(stringBuilder.toString()));
                return PendingIntent.getActivity(this.c.getApplicationContext(), 0, intent, 134217728);
            default:
                return null;
        }
    }

    private void a(String str, String str2, Bitmap bitmap) {
        this.d.setTextViewText(R.id.notification_base_line_one, str);
        this.d.setTextViewText(R.id.notification_base_line_two, str2);
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.c.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            this.d.setTextViewText(R.id.notification_base_line_two, stringBuilder.toString());
        } else {
            this.d.setTextViewText(R.id.notification_base_line_two, str2);
        }
        if (bitmap != null) {
            this.d.setImageViewBitmap(R.id.notification_base_image, bitmap);
        } else {
            this.d.setImageViewResource(R.id.notification_base_image, R.mipmap.gaana_logo);
        }
    }

    private void a(String str, String str2, String str3, Bitmap bitmap) {
        this.f.setTextViewText(R.id.notification_expanded_base_line_one, str);
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.c.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            this.f.setTextViewText(R.id.notification_expanded_base_line_two, stringBuilder.toString());
        } else if (TextUtils.isEmpty(str2)) {
            this.f.setTextViewText(R.id.notification_expanded_base_line_two, str3);
        } else {
            RemoteViews remoteViews = this.f;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str3);
            stringBuilder2.append(" - ");
            stringBuilder2.append(str2);
            remoteViews.setTextViewText(R.id.notification_expanded_base_line_two, stringBuilder2.toString());
        }
        if (bitmap != null) {
            this.f.setImageViewBitmap(R.id.notification_expanded_base_image, bitmap);
            this.f.setImageViewResource(R.id.notification_expanded_bg, R.drawable.bg_notification);
            return;
        }
        this.f.setImageViewResource(R.id.notification_expanded_base_image, R.drawable.placeholder_album_artwork_large);
        this.f.setImageViewResource(R.id.notification_expanded_bg, R.drawable.bg_notification);
    }
}
