package com.player_framework;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track;
import com.i.i;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.f;
import com.services.l.r;
import com.utilities.Util;
import com.utilities.d;

public class g {
    private RemoteControlClient a;
    private Track b;
    private MetadataEditor c;
    private Bitmap d;
    private f e = f.v();

    @TargetApi(14)
    public void a(Context context, Track track) {
        if (VERSION.SDK_INT >= 14) {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            this.b = track;
            if (this.a == null) {
                d.a(context);
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.setComponent(new ComponentName(context, MediaButtonIntentReceiver.class));
                this.a = new RemoteControlClient(PendingIntent.getBroadcast(context, 0, intent, 0));
                audioManager.registerRemoteControlClient(this.a);
            }
            try {
                this.d = BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder_album_artwork_large);
            } catch (OutOfMemoryError unused) {
                System.gc();
                this.d = BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder_album_artwork_large);
            }
            this.a.setPlaybackState(3);
            if (PlayerManager.a(context).m() != PlayerType.GAANA_RADIO) {
                this.a.setTransportControlFlags(169);
            } else if (ad.a(GaanaApplication.getInstance()).o().booleanValue()) {
                this.a.setTransportControlFlags(40);
            } else {
                this.a.setTransportControlFlags(168);
            }
            try {
                GaanaApplication.getInstance();
                String language = GaanaApplication.getLanguage(GaanaApplication.getContext());
                if (!TextUtils.isEmpty(language)) {
                    if (language.equalsIgnoreCase("hindi")) {
                        this.c = this.a.editMetadata(true).putString(2, track.getArtistNames()).putString(1, track.getAlbumTitle()).putString(7, track.getTrackTitle()).putLong(9, (long) ((int) Double.parseDouble(track.getDuration()))).putBitmap(100, this.d);
                        this.c.apply();
                        i.a().b(Util.s(this.b.getArtworkLarge()), new r() {
                            public void onErrorResponse(VolleyError volleyError) {
                            }

                            public void onSuccessfulResponse(Bitmap bitmap) {
                                try {
                                    g.this.d = bitmap;
                                    if (g.this.d == null || g.this.a == null) {
                                        i.a().a(g.this.b.getArtwork(), new r() {
                                            public void onErrorResponse(VolleyError volleyError) {
                                            }

                                            public void onSuccessfulResponse(Bitmap bitmap) {
                                                try {
                                                    g.this.d = bitmap;
                                                    if (g.this.d != null && g.this.a != null) {
                                                        g.this.c = g.this.a.editMetadata(false);
                                                        g.this.c.putBitmap(100, g.this.d);
                                                        g.this.c.apply();
                                                    }
                                                } catch (OutOfMemoryError unused) {
                                                }
                                            }
                                        });
                                        return;
                                    }
                                    g.this.c = g.this.a.editMetadata(false);
                                    g.this.c.putBitmap(100, g.this.d);
                                    g.this.c.apply();
                                } catch (OutOfMemoryError unused) {
                                }
                            }
                        });
                    }
                }
                this.c = this.a.editMetadata(true).putString(2, track.getEnglishArtistNames()).putString(1, track.getEnglishAlbumTitle()).putString(7, track.getEnglishName()).putLong(9, (long) ((int) Double.parseDouble(track.getDuration()))).putBitmap(100, this.d);
                this.c.apply();
                i.a().b(Util.s(this.b.getArtworkLarge()), /* anonymous class already generated */);
            } catch (Exception unused2) {
            }
        }
    }

    @TargetApi(14)
    public void b(Context context, Track track) {
        if (VERSION.SDK_INT >= 14) {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            d.a(context);
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(new ComponentName(context, MediaButtonIntentReceiver.class));
            this.a = new RemoteControlClient(PendingIntent.getBroadcast(context, 0, intent, 0));
            audioManager.registerRemoteControlClient(this.a);
            this.a.setPlaybackState(3);
            try {
                this.d = BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder_album_artwork_large);
            } catch (OutOfMemoryError unused) {
                System.gc();
                this.d = BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder_album_artwork_large);
            }
            try {
                GaanaApplication.getInstance();
                String language = GaanaApplication.getLanguage(GaanaApplication.getContext());
                if (!TextUtils.isEmpty(language)) {
                    if (language.equalsIgnoreCase("hindi")) {
                        this.c = this.a.editMetadata(true).putString(2, track.getArtistNames()).putString(1, track.getAlbumTitle()).putString(7, track.getTrackTitle()).putLong(9, (long) ((int) Double.parseDouble(track.getDuration()))).putBitmap(100, this.d);
                        i.a().b(this.b.getArtworkLarge(), new r() {
                            public void onErrorResponse(VolleyError volleyError) {
                            }

                            public void onSuccessfulResponse(Bitmap bitmap) {
                                g.this.d = bitmap;
                                if (g.this.d != null) {
                                    g.this.c.putBitmap(100, g.this.d);
                                } else {
                                    i.a().a(g.this.b.getArtwork(), new r() {
                                        public void onErrorResponse(VolleyError volleyError) {
                                        }

                                        public void onSuccessfulResponse(Bitmap bitmap) {
                                            g.this.d = bitmap;
                                            if (g.this.d != null) {
                                                g.this.c.putBitmap(100, g.this.d);
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        this.c.apply();
                    }
                }
                this.c = this.a.editMetadata(true).putString(2, track.getEnglishArtistNames()).putString(1, track.getEnglishAlbumTitle()).putString(7, track.getEnglishName()).putLong(9, (long) ((int) Double.parseDouble(track.getDuration()))).putBitmap(100, this.d);
                i.a().b(this.b.getArtworkLarge(), /* anonymous class already generated */);
                this.c.apply();
            } catch (Exception unused2) {
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00b2 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b2 A:{SYNTHETIC} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00b2 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x009f */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:17|18|(1:20)) */
    /* JADX WARNING: Missing block: B:18:?, code skipped:
            r4.d = r4.e.q();
     */
    /* JADX WARNING: Missing block: B:19:0x00a9, code skipped:
            if (r4.d != null) goto L_0x00ab;
     */
    /* JADX WARNING: Missing block: B:20:0x00ab, code skipped:
            r4.c.putBitmap(100, r4.d);
     */
    @android.annotation.TargetApi(14)
    public void a(android.content.Context r5, boolean r6) {
        /*
        r4 = this;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 14;
        if (r0 >= r1) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = "audio";
        r0 = r5.getSystemService(r0);
        r0 = (android.media.AudioManager) r0;
        com.utilities.d.a(r5);
        r1 = new android.content.Intent;
        r2 = "android.intent.action.MEDIA_BUTTON";
        r1.<init>(r2);
        r2 = new android.content.ComponentName;
        r3 = com.player_framework.MediaButtonIntentReceiver.class;
        r2.<init>(r5, r3);
        r1.setComponent(r2);
        r2 = new android.media.RemoteControlClient;
        r3 = 0;
        r1 = android.app.PendingIntent.getBroadcast(r5, r3, r1, r3);
        r2.<init>(r1);
        r4.a = r2;
        r1 = r4.a;
        r0.registerRemoteControlClient(r1);
        r0 = r4.a;
        r1 = 3;
        r0.setPlaybackState(r1);
        if (r6 == 0) goto L_0x0043;
    L_0x003c:
        r6 = r4.a;
        r0 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r6.setTransportControlFlags(r0);
    L_0x0043:
        r6 = 2131231660; // 0x7f0803ac float:1.8079407E38 double:1.0529683465E-314;
        r0 = r5.getResources();	 Catch:{ OutOfMemoryError -> 0x0051 }
        r0 = android.graphics.BitmapFactory.decodeResource(r0, r6);	 Catch:{ OutOfMemoryError -> 0x0051 }
        r4.d = r0;	 Catch:{ OutOfMemoryError -> 0x0051 }
        goto L_0x005e;
    L_0x0051:
        java.lang.System.gc();
        r5 = r5.getResources();
        r5 = android.graphics.BitmapFactory.decodeResource(r5, r6);
        r4.d = r5;
    L_0x005e:
        r5 = r4.a;	 Catch:{ Exception -> 0x00b7 }
        r6 = 1;
        r5 = r5.editMetadata(r6);	 Catch:{ Exception -> 0x00b7 }
        r0 = 2;
        r1 = r4.e;	 Catch:{ Exception -> 0x00b7 }
        r1 = r1.r();	 Catch:{ Exception -> 0x00b7 }
        r5 = r5.putString(r0, r1);	 Catch:{ Exception -> 0x00b7 }
        r0 = "Sponsored Ad";
        r5 = r5.putString(r6, r0);	 Catch:{ Exception -> 0x00b7 }
        r6 = 7;
        r0 = r4.e;	 Catch:{ Exception -> 0x00b7 }
        r0 = r0.r();	 Catch:{ Exception -> 0x00b7 }
        r5 = r5.putString(r6, r0);	 Catch:{ Exception -> 0x00b7 }
        r6 = r4.d;	 Catch:{ Exception -> 0x00b7 }
        r0 = 100;
        r5 = r5.putBitmap(r0, r6);	 Catch:{ Exception -> 0x00b7 }
        r4.c = r5;	 Catch:{ Exception -> 0x00b7 }
        r5 = r4.e;	 Catch:{ OutOfMemoryError -> 0x009f }
        r5 = r5.q();	 Catch:{ OutOfMemoryError -> 0x009f }
        r4.d = r5;	 Catch:{ OutOfMemoryError -> 0x009f }
        r5 = r4.d;	 Catch:{ OutOfMemoryError -> 0x009f }
        if (r5 == 0) goto L_0x00b2;
    L_0x0097:
        r5 = r4.c;	 Catch:{ OutOfMemoryError -> 0x009f }
        r6 = r4.d;	 Catch:{ OutOfMemoryError -> 0x009f }
        r5.putBitmap(r0, r6);	 Catch:{ OutOfMemoryError -> 0x009f }
        goto L_0x00b2;
    L_0x009f:
        r5 = r4.e;	 Catch:{ Exception -> 0x00b2, Exception -> 0x00b2 }
        r5 = r5.q();	 Catch:{ Exception -> 0x00b2, Exception -> 0x00b2 }
        r4.d = r5;	 Catch:{ Exception -> 0x00b2, Exception -> 0x00b2 }
        r5 = r4.d;	 Catch:{ Exception -> 0x00b2, Exception -> 0x00b2 }
        if (r5 == 0) goto L_0x00b2;
    L_0x00ab:
        r5 = r4.c;	 Catch:{ Exception -> 0x00b2, Exception -> 0x00b2 }
        r6 = r4.d;	 Catch:{ Exception -> 0x00b2, Exception -> 0x00b2 }
        r5.putBitmap(r0, r6);	 Catch:{ Exception -> 0x00b2, Exception -> 0x00b2 }
    L_0x00b2:
        r5 = r4.c;	 Catch:{ Exception -> 0x00b7 }
        r5.apply();	 Catch:{ Exception -> 0x00b7 }
    L_0x00b7:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.player_framework.g.a(android.content.Context, boolean):void");
    }

    @TargetApi(14)
    public void a() {
        if (this.a != null) {
            this.a.setPlaybackState(1);
            this.a = null;
        }
    }

    @TargetApi(14)
    public void b() {
        if (this.a != null) {
            this.a.setPlaybackState(2);
        }
    }
}
