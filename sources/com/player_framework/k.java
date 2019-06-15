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
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.media.app.NotificationCompat.MediaStyle;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.gaana.BuildConfig;
import com.gaana.R;
import com.gaana.SplashScreenActivity;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.f;
import com.models.PlayerTrack;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerConstants.PlayerCommands;
import com.services.d;
import com.services.l.r;
import com.utilities.Util;
import com.utilities.g;

@SuppressLint({"NewApi"})
public class k extends a {
    private final NotificationManager a;
    private final GaanaMusicService b;
    private Notification c = null;
    private Builder d = null;
    private Bitmap e = null;
    private MediaStyle f;
    private boolean g = true;

    public k(GaanaMusicService gaanaMusicService) {
        this.b = gaanaMusicService;
        this.a = (NotificationManager) gaanaMusicService.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
        int w = Util.w();
        this.f = new MediaStyle().setMediaSession(new MediaSessionCompat(this.b, "TAG", new ComponentName(this.b, MediaButtonIntentReceiver.class.getName()), null).getSessionToken()).setShowCancelButton(true).setCancelButtonIntent(a(4));
        this.d = new Builder(this.b, "com.gaana.play").setStyle(this.f).setSmallIcon(w).setShowWhen(false).setVisibility(1).setContentIntent(c());
        this.c = this.d.build();
    }

    public Notification a() {
        return this.c;
    }

    public void b(String str, String str2, String str3, long j, Bitmap bitmap) {
        if (TextUtils.isEmpty(str2)) {
            a(str3, str, bitmap);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("-");
            stringBuilder.append(str2);
            a(str3, stringBuilder.toString(), bitmap);
        }
        this.b.startForeground(1000, this.c);
    }

    public void a(PlayerTrack playerTrack, long j) {
        String englishAlbumTitle = playerTrack.a(true).getEnglishAlbumTitle();
        String englishName = playerTrack.a(true).getEnglishName();
        CharSequence englishArtistNames = playerTrack.a(true).getEnglishArtistNames();
        this.e = BitmapFactory.decodeResource(this.b.getResources(), R.drawable.placeholder_album_artwork_large);
        String language = GaanaApplication.getLanguage(GaanaApplication.getContext());
        if (!TextUtils.isEmpty(language) && language.equalsIgnoreCase("hindi")) {
            englishAlbumTitle = playerTrack.a(true).getAlbumTitle();
            englishArtistNames = playerTrack.a(true).getArtistNames();
            englishName = playerTrack.a(true).getTrackTitle();
        }
        if (!TextUtils.isEmpty(englishArtistNames)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(englishAlbumTitle);
            stringBuilder.append("-");
            stringBuilder.append(englishArtistNames);
            englishAlbumTitle = stringBuilder.toString();
        }
        i.a().a(playerTrack.a(true).getArtwork(), new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                try {
                    k.this.e = bitmap;
                    if (k.this.e != null && !f.v().w() && !f.v().t()) {
                        try {
                            k.this.d.setLargeIcon(k.this.e);
                            k.this.c = k.this.d.build();
                            k.this.a.notify(1000, k.this.c);
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                } catch (OutOfMemoryError unused) {
                }
            }
        });
        if (playerTrack.b().isLocalMedia()) {
            try {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("content://media/external/audio/albumart/");
                stringBuilder2.append(playerTrack.b().getAlbumId());
                this.e = Media.getBitmap(this.b.getContentResolver(), Uri.parse(stringBuilder2.toString()));
            } catch (Exception unused) {
                this.e = BitmapFactory.decodeResource(this.b.getResources(), R.drawable.placeholder_album_artwork_large);
            }
        }
        a(englishName, englishAlbumTitle, this.e);
        this.b.startForeground(1000, this.c);
    }

    public void a(String str, String str2, String str3, long j, Bitmap bitmap) {
        this.g = false;
        b(str, str2, str3, j, bitmap);
        this.g = true;
    }

    public void a(boolean z) {
        if (this.c != null && this.a != null && this.d.mActions != null && this.d.mActions.size() != 0 && !f.v().t()) {
            ((Action) this.d.mActions.get(b())).icon = z ? R.drawable.ic_notif_play : R.drawable.ic_notif_pause;
            this.c = this.d.build();
            try {
                this.a.notify(1000, this.c);
            } catch (Exception | IllegalStateException unused) {
            }
        }
    }

    private int b() {
        return PlayerManager.a(this.b).m() == PlayerType.GAANA ? 2 : 1;
    }

    private PendingIntent c() {
        Intent intent = new Intent(this.b, SplashScreenActivity.class);
        if (g.b()) {
            ComponentName componentName = new ComponentName(BuildConfig.APPLICATION_ID, "com.gaana.SplashScreenActivityMMX");
            Intent intent2 = new Intent();
            intent2.setComponent(componentName);
            intent = intent2;
        }
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("gaana://view/player/"));
        return PendingIntent.getActivity(this.b.getApplicationContext(), 0, intent, 134217728);
    }

    private PendingIntent a(int i) {
        Intent intent;
        switch (i) {
            case 1:
                intent = new Intent(this.b, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_PAUSE.toInt());
                intent.putExtra("EXTRA_PLAYER_COMMAND_ARG", PauseReasons.MEDIA_BUTTON_TAP.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.b, 1, intent, 0);
            case 2:
                intent = new Intent(this.b, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_NEXT.toInt());
                intent.putExtra("IS_TRIGGERED_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.b, 2, intent, 0);
            case 3:
                intent = new Intent(this.b, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.PLAY_PREVIOUS.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.b, 3, intent, 0);
            case 4:
                intent = new Intent(this.b, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.STOP.toInt());
                intent.putExtra("EXTRA_CAST_PLAYER_COMMAND", 1213);
                intent.putExtra("EXTRA_CAST_PLAYER_COMMAND", 1212);
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.b, 4, intent, 0);
            case 5:
                intent = new Intent(this.b, GaanaMusicService.class);
                intent.putExtra("EXTRA_PLAYER_COMMAND", PlayerCommands.FAVORITE_TRACK.toInt());
                intent.putExtra("IS_FROM_NOTIFICATION", true);
                intent.putExtra("IS_FROM_WIDGET", false);
                return PendingIntent.getService(this.b, 5, intent, 0);
            case 6:
                intent = new Intent(this.b, SplashScreenActivity.class);
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
                if (!Util.j(this.b) || d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false) <= 1) {
                    return null;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("gaana://view/addtofavorite/");
                stringBuilder.append(PlayerManager.a(this.b).i().b().getBusinessObjId());
                intent.setData(Uri.parse(stringBuilder.toString()));
                return PendingIntent.getActivity(this.b.getApplicationContext(), 0, intent, 134217728);
            default:
                return null;
        }
    }

    private void a(String str, String str2, Bitmap bitmap) {
        CharSequence str22;
        this.d.mActions.clear();
        if (f.v().w() || f.v().t() || !this.g) {
            this.d.addAction(new Action(R.drawable.widget_not_favoritable, "FavoriteNotAllowed", a(-1)));
            this.d.addAction(new Action(R.drawable.ic_notif_previous_greyed, "PreviousNotAllowed", a(-1)));
            this.d.addAction(new Action(R.drawable.ic_notif_pause_greyed, "PlayNotAllowed", a(-1)));
            if (f.a || f.v().u()) {
                this.d.addAction(new Action(R.drawable.next_track, "Next", a(2)));
            } else {
                this.d.addAction(new Action(R.drawable.ic_notif_next_greyed, "NextNotAllowed", a(-1)));
            }
        } else {
            if (PlayerManager.a(this.b).i().b().isLocalMedia()) {
                this.d.addAction(new Action(R.drawable.widget_not_favoritable, "FavoriteNotAllowed", a(-1)));
            } else if (!GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                this.d.addAction(new Action(R.drawable.widget_not_favoritable, "FavoriteAfterLogin", a(6)));
            } else if (PlayerManager.a(this.b).i().b().isFavorite().booleanValue()) {
                this.d.addAction(new Action(R.drawable.favorited_track, "Favorite", a(5)));
            } else {
                this.d.addAction(new Action(R.drawable.unfavorited_track, "UnFavorite", a(5)));
            }
            if (PlayerManager.a(this.b).m() == PlayerType.GAANA) {
                this.d.addAction(new Action(R.drawable.previous_track, "Previous", a(3)));
            }
            if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                this.d.addAction(new Action(R.drawable.ic_notif_pause, "PlayPause", a(1)));
            } else {
                this.d.addAction(new Action(R.drawable.ic_notif_play, "PlayPause", a(1)));
            }
            if (!ad.a(GaanaApplication.getContext()).o().booleanValue()) {
                this.d.addAction(new Action(R.drawable.next_track, "Next", a(2)));
            }
        }
        this.f.setShowActionsInCompactView(d());
        this.d.setLargeIcon(bitmap).setContentTitle(str);
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.b.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            str22 = stringBuilder.toString();
        }
        this.d.setContentText(str22);
        this.c = this.d.build();
        try {
            this.a.notify(1000, this.c);
        } catch (Exception | IllegalStateException unused) {
        }
    }

    private int[] d() {
        if (PlayerManager.a(this.b).m() == PlayerType.GAANA) {
            return new int[]{1, 2, 3};
        }
        if (!ad.a(this.b).o().booleanValue()) {
            return new int[]{1, 2};
        }
        return new int[]{1};
    }
}
