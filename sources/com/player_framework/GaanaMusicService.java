package com.player_framework;

import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass.Device;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest.Builder;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.cast_music.VideoCastManager;
import com.cast_music.a.d;
import com.comscore.analytics.comScore;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.constants.Constants.VoiceActivatedAdsFormat;
import com.constants.c.c;
import com.fragments.MiniPlayerFragment;
import com.fragments.PlayerRadioFragmentV4;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.login.LoginManager;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.api.client.http.HttpStatusCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.internal.LinkedTreeMap;
import com.i.i;
import com.i.j;
import com.logging.GaanaLogger;
import com.logging.GaanaLogger.CONTENT_TYPE;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.PLAYOUT_SOURCE;
import com.logging.GaanaLogger.PLAYOUT_SOURCE_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.logging.TrackLog;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaManager;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.VoiceRecognition;
import com.managers.VoiceRecognition.VoiceCommand;
import com.managers.ad;
import com.managers.ai;
import com.managers.an;
import com.managers.ap;
import com.managers.aq;
import com.managers.f;
import com.managers.n;
import com.managers.o;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerConstants.PlayerCommands;
import com.player_framework.PlayerStatus.PlayerStates;
import com.playercache.TrackCacheQueueManager;
import com.playercache.TrackCacheQueueManager.CACHING_BEHAVIOUR;
import com.playercache.TrackCacheQueueManager.INSERTION_ORDER;
import com.services.l.ac;
import com.services.l.ak;
import com.services.l.ay;
import com.services.l.bc;
import com.services.l.r;
import com.services.l.s;
import com.til.colombia.android.internal.e;
import com.utilities.Util;
import com.utilities.h;
import com.widget.GaanaWidgetProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GaanaMusicService extends Service implements com.managers.ColombiaManager.b, com.managers.VoiceRecognition.a, ak {
    private static boolean A = false;
    private static f B = null;
    private static boolean C = false;
    private static boolean D = false;
    private static int E = 0;
    private static boolean H = false;
    private static PlayerTrack Q = null;
    private static PlayerTrack R = null;
    private static SimpleExoPlayerView ag = null;
    private static ay ah = null;
    private static boolean ai = false;
    private static boolean aj = false;
    private static String ak = null;
    public static boolean c = false;
    private static f f = null;
    private static boolean g = false;
    private static boolean l = false;
    private static int o = 0;
    private static int p = 30000;
    private static boolean u = false;
    private static boolean v = false;
    private static int w = -1;
    private static f z;
    private final IBinder F = new b();
    private g G = null;
    private boolean I = false;
    private BroadcastReceiver J = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equalsIgnoreCase("broadcast_crossfade_status_changed")) {
                GaanaMusicService.this.U();
            } else if (action.equalsIgnoreCase("broadcast_videoautoplay_status_changed")) {
                o.c(GaanaMusicService.this, 1);
            }
        }
    };
    private BroadcastReceiver K = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (PlayerStatus.a(context).c() || PlayerStatus.a(context).b()) {
                o.a(GaanaMusicService.this, PauseReasons.MEDIA_BUTTON_TAP);
            }
        }
    };
    private BroadcastReceiver L = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras().getInt("android.bluetooth.adapter.extra.CONNECTION_STATE") == 2) {
                Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
                if (bondedDevices.size() > 0) {
                    HashMap a = Util.a(Device.class);
                    for (BluetoothDevice bluetoothDevice : bondedDevices) {
                        String name = bluetoothDevice.getName();
                        String str = "UNKNOWN_DEVICE_CLASS";
                        if (a.get(Integer.valueOf(bluetoothDevice.getBluetoothClass().getDeviceClass())) != null) {
                            str = (String) a.get(Integer.valueOf(bluetoothDevice.getBluetoothClass().getDeviceClass()));
                        }
                        u.a().a("Bluetooth", str, name);
                    }
                }
            }
        }
    };
    private a M;
    private PlayerManager N;
    private GaanaApplication O;
    private WifiLock P;
    private boolean S = true;
    private boolean T = true;
    private boolean U = true;
    private boolean V = true;
    private boolean W = false;
    private boolean X = false;
    private boolean Y = false;
    private boolean Z = true;
    Timer a = null;
    private boolean aa = false;
    private boolean ab = true;
    private CountDownTimer ac;
    private CountDownTimer ad;
    private boolean ae = false;
    private String af = "";
    private m al = new m() {
        public void onBufferingUpdate(f fVar, int i) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (!Constants.cY) {
                GaanaMusicService.this.K();
                GaanaMusicService.this.k(true);
            }
            if (GaanaMusicService.this.q) {
                GaanaMusicService.this.q = false;
                GaanaMusicService.B();
            }
            GaanaMusicService.ak = fVar != null ? fVar.s() : null;
            GaanaMusicService.this.ae = false;
            if (!(GaanaMusicService.f == null || GaanaMusicService.f.l())) {
                PlayerStatus.a(GaanaMusicService.this, PlayerStates.PLAYING);
            }
            GaanaMusicService.this.n = 0;
            if (GaanaMusicService.f instanceof b) {
                GaanaMusicService.D = false;
                GaanaMusicService.A = false;
                if (GaanaMusicService.z != null) {
                    GaanaMusicService.z.w();
                    GaanaMusicService.z = null;
                }
            } else {
                GaanaMusicService.this.U();
            }
            if (fVar instanceof c) {
                if (((c) fVar).b() != null) {
                    GaanaMusicService.i();
                } else {
                    GaanaMusicService.aj = true;
                    if ((GaanaMusicService.f instanceof c) && GaanaMusicService.ag != null) {
                        if (GaanaMusicService.ah != null) {
                            GaanaMusicService.ah.videoStateChanged(1);
                        }
                        ((c) GaanaMusicService.f).a(GaanaMusicService.ag);
                        GaanaMusicService.ag = null;
                        GaanaMusicService.ah = null;
                    }
                }
            }
            GaanaMusicService.this.N();
            if (GaanaMusicService.this.Y) {
                GaanaMusicService.this.O.sendPlayLoadTimeEventFromSecdndaryPlayer(GaanaMusicService.this.T);
            } else {
                GaanaMusicService.this.O.sendPlayLoadTimeEvent(GaanaMusicService.this.T);
            }
            if (Constants.F == 1 || GaanaMusicService.A) {
                GaanaMusicService.ai = false;
                GaanaMusicService.this.V();
            }
            TrackCacheQueueManager.a().a(5, CACHING_BEHAVIOUR.FULL_CACHE.ordinal(), INSERTION_ORDER.FIRST.ordinal());
        }

        public void onError(f fVar, int i, int i2) {
            if (fVar == GaanaMusicService.f) {
                if (i == HttpStatusCodes.STATUS_CODE_FOUND) {
                    GaanaMusicService.this.m(false);
                    u.a().a("StreamingFailure", "Buffer not fetched - Server-302", Util.P());
                } else if (i == 403 || i == 9876) {
                    a(fVar, i);
                } else if (i != SsoErrorCodes.NO_INTERNET_CONNECTION) {
                    GaanaMusicService.this.n(false);
                } else if (Util.j(GaanaMusicService.this)) {
                    GaanaMusicService.this.n(false);
                } else {
                    GaanaMusicService.this.ae = true;
                }
            } else if (fVar == GaanaMusicService.z && GaanaMusicService.z != null) {
                GaanaMusicService.z.w();
                GaanaMusicService.z = null;
            }
        }

        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
            final Ad ad = adEvent.getAd();
            switch (adEvent.getType()) {
                case CONTENT_RESUME_REQUESTED:
                    if (GaanaMusicService.A || !h.b(GaanaApplication.getContext()) || f.v().c() || !f.v().t()) {
                        GaanaMusicService.this.b(false);
                    } else {
                        com.models.a a = f.v().a();
                        if (a == null || TextUtils.isEmpty(a.f())) {
                            GaanaMusicService.this.b(false);
                        } else {
                            long parseLong = Long.parseLong(a.f()) * 1000;
                            if (parseLong >= 1000) {
                                GaanaMusicService.this.ad = new CountDownTimer(parseLong, parseLong) {
                                    public void onTick(long j) {
                                    }

                                    public void onFinish() {
                                        GaanaMusicService.this.b(false);
                                    }
                                };
                                o.a(GaanaMusicService.this.O, PauseReasons.MEDIA_BUTTON_TAP);
                                GaanaMusicService.this.ad.start();
                                GaanaMusicService.this.t = false;
                                GaanaMusicService.this.r = new VoiceRecognition(GaanaMusicService.this.O);
                                GaanaMusicService.this.r.a(GaanaMusicService.this);
                                GaanaMusicService.this.r.a();
                                GaanaMusicService.this.z();
                            } else {
                                GaanaMusicService.this.b(false);
                            }
                        }
                    }
                    if ((GaanaMusicService.f instanceof c) && GaanaMusicService.ag != null) {
                        if (GaanaMusicService.ah != null) {
                            GaanaMusicService.ah.videoStateChanged(1);
                        }
                        ((c) GaanaMusicService.f).a(GaanaMusicService.ag);
                        GaanaMusicService.ag = null;
                        GaanaMusicService.ah = null;
                    }
                    if ((fVar instanceof c) && fVar == GaanaMusicService.z) {
                        GaanaMusicService.ai = true;
                    }
                    GaanaMusicService.aj = true;
                    return;
                case STARTED:
                    a();
                    a(ad);
                    f.v().a(System.currentTimeMillis() / 1000);
                    f.c = System.currentTimeMillis();
                    GaanaMusicService.C = true;
                    f.v().b(false);
                    GaanaMusicService.this.a(ad);
                    if (GaanaMusicService.A && (fVar instanceof c)) {
                        if (fVar.equals(GaanaMusicService.f) && GaanaMusicService.z != null && GaanaMusicService.z.isPlaying()) {
                            GaanaMusicService.B = GaanaMusicService.z;
                            GaanaMusicService.z.r();
                            GaanaMusicService.this.Y();
                            GaanaMusicService.this.a(GaanaMusicService.f, 1.0f, 1.0f);
                        } else if (fVar.equals(GaanaMusicService.z) && GaanaMusicService.f != null && GaanaMusicService.f.isPlaying()) {
                            GaanaMusicService.B = GaanaMusicService.f;
                            GaanaMusicService.f.r();
                            GaanaMusicService.this.Y();
                            GaanaMusicService.this.a(GaanaMusicService.z, 1.0f, 1.0f);
                        }
                    }
                    if (adEvent.getAd() != null) {
                        long skipTimeOffset = (long) (adEvent.getAd().getSkipTimeOffset() * 1000.0d);
                        if (skipTimeOffset > 0 && adEvent.getAd().isSkippable()) {
                            f.b = String.valueOf(skipTimeOffset);
                            GaanaMusicService.this.ac = new CountDownTimer(skipTimeOffset, skipTimeOffset) {
                                public void onTick(long j) {
                                }

                                public void onFinish() {
                                    f.v().g(true);
                                    GaanaMusicService.this.a(ad);
                                    if (GaanaMusicService.this.ac != null) {
                                        GaanaMusicService.this.ac.cancel();
                                    }
                                }
                            };
                            GaanaMusicService.this.ac.start();
                            return;
                        }
                        return;
                    }
                    return;
                case ALL_ADS_COMPLETED:
                    GaanaMusicService.C = false;
                    f.v().b(false);
                    return;
                default:
                    return;
            }
        }

        private void a(Ad ad) {
            try {
                String traffickingParameters = ad.getTraffickingParameters();
                if (!TextUtils.isEmpty("traffickingParameters")) {
                    com.models.a aVar = new com.models.a();
                    String[] split = traffickingParameters.split(",");
                    for (String split2 : split) {
                        String split22;
                        String[] split3 = split22.split("=");
                        if (split3.length >= 2 && split3[0] != null) {
                            if (split3[1] != null) {
                                String trim = split3[0].trim();
                                split22 = split3[1].trim();
                                if (trim.equalsIgnoreCase("campaign")) {
                                    f.v().a(split22);
                                }
                                if (trim.equalsIgnoreCase("timeoffset")) {
                                    f.v().a(Integer.parseInt(split22));
                                }
                                if (trim.equalsIgnoreCase("followup")) {
                                    f.v().b(split22);
                                }
                                if (trim.equalsIgnoreCase("Format")) {
                                    aVar.c(split22);
                                }
                                if (trim.equalsIgnoreCase("Keyword")) {
                                    aVar.b(split22);
                                }
                                if (trim.equalsIgnoreCase("Voice_flag")) {
                                    aVar.d(split22);
                                }
                                if (trim.equalsIgnoreCase("LP_open_flag")) {
                                    aVar.e(split22);
                                }
                                if (trim.equalsIgnoreCase("Timeout_interval")) {
                                    aVar.g(split22);
                                }
                                if (trim.equalsIgnoreCase("LP")) {
                                    aVar.f(split22.replaceAll("~!", "="));
                                }
                                if (trim.equalsIgnoreCase("AT")) {
                                    aVar.a(split22);
                                }
                            }
                        }
                    }
                    f.v().a(aVar);
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }

        private void a() {
            try {
                f.v().a("");
                f.v().a(-1);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }

        private void a(f fVar, int i) {
            if (fVar != null && fVar.s() != null && GaanaMusicService.f != null && fVar.s().equalsIgnoreCase(GaanaMusicService.f.s())) {
                if (i == 9876) {
                    u.a().a("StreamingFailure", "Buffer not fetched - Cache Read Failure - 9876", Util.P());
                    o.a().b(PlayerManager.a(GaanaMusicService.this.O).i().h());
                }
                if (GaanaMusicService.this.ab) {
                    final PlayerTrack i2 = PlayerManager.a(GaanaMusicService.this.O).i();
                    GaanaMusicService.this.ab = false;
                    GaanaMusicService.this.a(i2, new com.i.e.b() {
                        public void onDataRetrieved(Object obj, int i, boolean z) {
                            GaanaMusicService.this.h = false;
                            GaanaMusicService.u = false;
                            GaanaMusicService.this.b(i2, obj.toString(), i, true);
                        }
                    });
                    return;
                }
                u.a().a("StreamingFailure", "Buffer not fetched - Server-403", Util.P());
                GaanaMusicService.this.ab = true;
                GaanaMusicService.this.n(false);
            }
        }

        public void onCompletion(f fVar) {
            if (GaanaMusicService.this.aa) {
                GaanaMusicService.this.aa = false;
            } else {
                GaanaMusicService.this.n(false);
            }
        }
    };
    private BroadcastReceiver am = new BroadcastReceiver() {
        /* JADX WARNING: Missing block: B:17:0x0041, code skipped:
            return;
     */
        public void onReceive(android.content.Context r1, android.content.Intent r2) {
            /*
            r0 = this;
            if (r2 == 0) goto L_0x0041;
        L_0x0002:
            r1 = r2.getAction();
            if (r1 == 0) goto L_0x0041;
        L_0x0008:
            r1 = com.player_framework.GaanaMusicService.this;
            r1 = r1.ae;
            if (r1 != 0) goto L_0x0011;
        L_0x0010:
            goto L_0x0041;
        L_0x0011:
            r1 = r2.getAction();
            r2 = "android.net.conn.CONNECTIVITY_CHANGE";
            r1 = r1.equalsIgnoreCase(r2);
            if (r1 == 0) goto L_0x0040;
        L_0x001d:
            r1 = com.utilities.Util.d();
            r2 = com.utilities.Util.NETWORK_TYPE.NETWORK_NO_CONNECTION;
            if (r1 == r2) goto L_0x0040;
        L_0x0025:
            r2 = com.utilities.Util.NETWORK_TYPE.NETWORK_UNKNOWN;
            if (r1 == r2) goto L_0x0040;
        L_0x0029:
            r1 = com.player_framework.GaanaMusicService.f;
            if (r1 == 0) goto L_0x0040;
        L_0x002f:
            r1 = com.player_framework.GaanaMusicService.f;
            r1 = r1 instanceof com.player_framework.c;
            if (r1 == 0) goto L_0x0040;
        L_0x0037:
            r1 = com.player_framework.GaanaMusicService.f;
            r1 = (com.player_framework.c) r1;
            r1.z();
        L_0x0040:
            return;
        L_0x0041:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.player_framework.GaanaMusicService$AnonymousClass22.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    private Track an = null;
    private String ao = null;
    private PLAY_TYPE ap = null;
    private boolean aq = false;
    private boolean[] ar = new boolean[]{false, false, false};
    private boolean as = false;
    private final d at = new d() {
        public void onDisconnectionReason(int i) {
        }

        public void onApplicationConnected(ApplicationMetadata applicationMetadata, String str, boolean z) {
            u.a().b("Chromecast", "Casting");
            u.a().b("Chromecast: Coach-mark", "Casting successful");
            Constants.aG = true;
            Constants.aH = VideoCastManager.y().i();
            LocalBroadcastManager.getInstance(GaanaMusicService.this.O).sendBroadcast(new Intent("UPDATE_UI_CHROMECAST_CONNECTED"));
            if (GaanaMusicService.f != null && !(GaanaMusicService.f instanceof b)) {
                String s = GaanaMusicService.f.s();
                int v = GaanaMusicService.f.v();
                z = GaanaMusicService.f.isPlaying();
                GaanaMusicService.f.p();
                GaanaMusicService.f.w();
                GaanaMusicService.f = new b();
                if (z && s != null) {
                    ((b) GaanaMusicService.f).a(v);
                    GaanaMusicService.this.d(s);
                }
            }
        }

        public void onDisconnected() {
            Constants.aG = false;
            Constants.aH = "";
            LocalBroadcastManager.getInstance(GaanaMusicService.this.O).sendBroadcast(new Intent("UPDATE_UI_CHROMECAST_CONNECTED"));
            if (GaanaMusicService.f != null && (GaanaMusicService.f instanceof b)) {
                String s = GaanaMusicService.f.s();
                int v = GaanaMusicService.f.v();
                boolean f = ((b) GaanaMusicService.f).f();
                if (GaanaMusicService.this.O != null) {
                    o.d(GaanaMusicService.this.O);
                }
                GaanaMusicService.f.p();
                GaanaMusicService.f.w();
                if (com.utilities.d.g()) {
                    GaanaMusicService.f = new c();
                } else {
                    GaanaMusicService.f = new e();
                }
                if (s != null && f) {
                    GaanaMusicService.this.d(s);
                    GaanaMusicService.f.b(v);
                }
            }
        }
    };
    f b = f.v();
    PlayerManager d = null;
    bc e = new bc() {
        public void a(PLAYOUT_SOURCE playout_source, boolean z) {
            if (z) {
                com.services.d.a().a("PREFERENCE_KEY_LAST_TRACK_PLAYOUT_SOURCE", String.valueOf(playout_source.ordinal()), false);
            } else {
                com.services.d.a().a("PREFERENCE_KEY_LAST_TRACK_PLAYOUT_SOURCE_SECONDARY", String.valueOf(playout_source.ordinal()), false);
            }
        }
    };
    private boolean h = true;
    private boolean i = true;
    private boolean j = false;
    private boolean k = false;
    private boolean m;
    private int n = 0;
    private boolean q = false;
    private VoiceRecognition r = null;
    private TextToSpeech s = null;
    private boolean t = false;
    private a x;
    private final OnAudioFocusChangeListener y = new OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            switch (i) {
                case -3:
                    if (PlayerStatus.a(GaanaMusicService.this).c()) {
                        GaanaMusicService.this.a(GaanaMusicService.f, 0.1f, 0.1f);
                        return;
                    }
                    return;
                case -2:
                    if (PlayerStatus.a(GaanaMusicService.this).c() || PlayerStatus.a(GaanaMusicService.this).b()) {
                        o.a(GaanaMusicService.this, PauseReasons.AUDIO_FOCUS_LOSS_TRANSIENT);
                        return;
                    }
                    return;
                case -1:
                    if ((PlayerStatus.a(GaanaMusicService.this).c() || PlayerStatus.a(GaanaMusicService.this).b()) && !f.v().w()) {
                        o.a(GaanaMusicService.this, PauseReasons.AUDIO_FOCUS_LOSS);
                        return;
                    }
                    return;
                case 1:
                    if (PlayerStatus.a(GaanaMusicService.this).d() && GaanaMusicService.this.ar[PauseReasons.AUDIO_FOCUS_LOSS_TRANSIENT.ordinal() - 1]) {
                        GaanaMusicService.this.a(GaanaMusicService.f, 1.0f, 1.0f);
                        o.c(GaanaMusicService.this, PauseReasons.AUDIO_FOCUS_LOSS_TRANSIENT);
                        return;
                    } else if (PlayerStatus.a(GaanaMusicService.this).c()) {
                        GaanaMusicService.this.a(GaanaMusicService.f, 1.0f, 1.0f);
                        return;
                    } else {
                        return;
                    }
                case 2:
                    if (PlayerStatus.a(GaanaMusicService.this).d()) {
                        GaanaMusicService.this.a(GaanaMusicService.f, 1.0f, 1.0f);
                        o.c(GaanaMusicService.this, PauseReasons.AUDIO_FOCUS_LOSS_TRANSIENT);
                        return;
                    } else if (PlayerStatus.a(GaanaMusicService.this).c()) {
                        GaanaMusicService.this.a(GaanaMusicService.f, 1.0f, 1.0f);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };

    public enum PLAY_TYPE {
        ONLINE,
        OFFLINE,
        LOCAL
    }

    private static class a extends Handler {
        WeakReference<GaanaMusicService> a;

        public a(GaanaMusicService gaanaMusicService) {
            this.a = new WeakReference(gaanaMusicService);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1001) {
                ((GaanaMusicService) this.a.get()).Z();
            }
        }
    }

    public class b extends Binder {
        public GaanaMusicService a() {
            return GaanaMusicService.this;
        }
    }

    public void a(VoiceCommand voiceCommand, String str) {
    }

    static /* synthetic */ int B() {
        int i = o;
        o = i + 1;
        return i;
    }

    public void f() {
        if (this.J != null && !this.I) {
            IntentFilter intentFilter = new IntentFilter("broadcast_crossfade_status_changed");
            intentFilter.addAction("broadcast_videoautoplay_status_changed");
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).registerReceiver(this.J, intentFilter);
            this.I = true;
        }
    }

    public void g() {
        if (this.J != null && this.I) {
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).unregisterReceiver(this.J);
        }
        this.I = false;
    }

    public static int h() {
        return o;
    }

    public static void i() {
        o = 0;
    }

    public static void j() {
        o = f.v().o();
    }

    public void b(boolean z) {
        if (this.ad != null) {
            this.ad.cancel();
            this.ad = null;
        }
        if (this.r != null && this.r.c()) {
            this.r.b();
            this.r = null;
        }
        PlayerStatus.a(this, PlayerStates.PLAYING);
        if (!A && f.v().t()) {
            f.v().e(false);
            if (z) {
                o.f(this.O);
            } else {
                o.c(this.O, PauseReasons.MEDIA_BUTTON_TAP);
            }
        }
        C = false;
        f.v().b(false);
        f.v().e(false);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                GaanaMusicService.this.c();
            }
        });
        if (A && B != null) {
            if (B.equals(z)) {
                aa();
                a(z, 1.0f, 1.0f);
            } else if (B.equals(f)) {
                f.q();
            }
            B = null;
        }
        if (this.ac != null) {
            this.ac.cancel();
        }
    }

    public static boolean k() {
        return A;
    }

    public static boolean l() {
        return C;
    }

    private void I() {
        J();
        this.a = new Timer();
        this.a.schedule(new TimerTask() {
            public void run() {
                int v = GaanaMusicService.s().v();
                if (!GaanaApplication.getInstance().isEndlessPlayback() || Constants.cY) {
                    if (v >= Constants.cK) {
                        GaanaMusicService.this.J();
                    }
                } else if (v >= Constants.cK) {
                    GaanaMusicService.this.b(GaanaMusicService.Q.h());
                    GaanaMusicService.this.J();
                }
            }
        }, 0, 1000);
    }

    private void J() {
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
    }

    private void K() {
        if (Q.e() == SOURCE_TYPE.CF_TRACK.ordinal() && GaanaApplication.getInstance().getShowCFSongsToastFlag()) {
            Toast.makeText(this.O, R.string.autoplay_recommended_songs, 1).show();
        }
        GaanaApplication.getInstance().setShowCFSongsToastFlag(false);
    }

    /* JADX WARNING: Missing block: B:23:0x0067, code skipped:
            return;
     */
    private void k(boolean r2) {
        /*
        r1 = this;
        r0 = com.constants.Constants.cY;
        if (r0 != 0) goto L_0x0067;
    L_0x0004:
        r0 = Q;
        if (r0 == 0) goto L_0x0067;
    L_0x0008:
        r0 = Q;
        r0 = r0.h();
        if (r0 != 0) goto L_0x0011;
    L_0x0010:
        goto L_0x0067;
    L_0x0011:
        r0 = r1.O;
        r0 = com.managers.PlayerManager.a(r0);
        r0 = r0.t();
        if (r0 == 0) goto L_0x0066;
    L_0x001d:
        r0 = r1.O;
        r0 = com.managers.PlayerManager.a(r0);
        r0 = r0.e();
        if (r0 != 0) goto L_0x0066;
    L_0x0029:
        r0 = r1.O;
        r0 = com.managers.PlayerManager.a(r0);
        r0 = r0.d();
        if (r0 != 0) goto L_0x0066;
    L_0x0035:
        r0 = Q;
        r0 = r0.b();
        r0 = r0.isLocalMedia();
        if (r0 != 0) goto L_0x0066;
    L_0x0041:
        if (r2 == 0) goto L_0x0047;
    L_0x0043:
        r1.I();
        goto L_0x005e;
    L_0x0047:
        r2 = com.gaana.application.GaanaApplication.getInstance();
        r2 = r2.isEndlessPlayback();
        if (r2 == 0) goto L_0x005e;
    L_0x0051:
        r2 = com.constants.Constants.cY;
        if (r2 != 0) goto L_0x005e;
    L_0x0055:
        r2 = Q;
        r2 = r2.h();
        r1.b(r2);
    L_0x005e:
        r2 = com.gaana.application.GaanaApplication.getInstance();
        r0 = 1;
        r2.setShowCFSongsToastFlag(r0);
    L_0x0066:
        return;
    L_0x0067:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.player_framework.GaanaMusicService.k(boolean):void");
    }

    private void b(final String str) {
        j.a().a((Object) "CF_API");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://rec.gaana.com/recommendation/recommendedTracks/");
        stringBuilder.append(str);
        com.i.b bVar = new com.i.b(stringBuilder.toString(), Tracks.class, new com.i.e.a() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, boolean z) {
                ArrayList arrListBusinessObj = ((BusinessObject) obj).getArrListBusinessObj();
                PlayerTrack i = PlayerManager.a(GaanaApplication.getContext()).i();
                if (i != null && i.h().equalsIgnoreCase(str)) {
                    if (arrListBusinessObj == null) {
                        u.a().a("CF TRACK", "CF Track Null", GaanaMusicService.this.N.i().h());
                        return;
                    }
                    Iterator it = arrListBusinessObj.iterator();
                    while (it.hasNext()) {
                        Track track = (Track) it.next();
                        track.setBusinessObjType(BusinessObjectType.Tracks);
                        track.setSeedTrackId(str);
                    }
                    PlayerManager.a(GaanaApplication.getContext()).c(PlayerManager.a(GaanaApplication.getContext()).i());
                    PlayerManager.a(GaanaApplication.getContext()).a(arrListBusinessObj, null, GaanaApplication.getContext(), false, false);
                }
            }
        });
        bVar.a("CF_API");
        i.a().a(bVar);
    }

    public void a(ArrayList<String> arrayList) {
        if (f.v().t()) {
            boolean z;
            final com.models.a a = f.v().a();
            String[] split = (a == null || TextUtils.isEmpty(a.b())) ? null : a.b().split("/");
            HashMap hashMap = new HashMap();
            if (!(split == null || arrayList == null)) {
                for (int i = 0; i < split.length; i++) {
                    if (!TextUtils.isEmpty(split[i])) {
                        hashMap.put(split[i].trim().toLowerCase(), Boolean.valueOf(true));
                    }
                }
                int i2 = 0;
                while (i2 < arrayList.size()) {
                    if (!TextUtils.isEmpty((CharSequence) arrayList.get(i2)) && hashMap.get(((String) arrayList.get(i2)).toLowerCase()) != null) {
                        z = true;
                        break;
                    }
                    i2++;
                }
            }
            z = false;
            if (z) {
                if (this.ad != null) {
                    this.ad.cancel();
                    this.ad = null;
                }
                if (this.r != null && this.r.c()) {
                    this.r.b();
                    this.r = null;
                }
                if (VoiceActivatedAdsFormat.Play.name().equalsIgnoreCase(a.c())) {
                    String[] split2 = a.e().split("/");
                    if (split2.length > 0) {
                        String str = split2[split2.length - 1];
                        int lastIndexOf = str.lastIndexOf("I");
                        if (lastIndexOf > 0) {
                            BusinessObject businessObject = new BusinessObject();
                            String substring = str.substring(1, lastIndexOf);
                            str = str.substring(lastIndexOf + 1);
                            businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                            businessObject.setName(substring);
                            businessObject.setBusinessObjId(str);
                            URLManager uRLManager = new URLManager();
                            HashMap hashMap2 = new HashMap();
                            uRLManager.a(BusinessObjectType.Tracks);
                            hashMap2.put("type", "song");
                            hashMap2.put(LoginManager.TAG_SUBTYPE, "song_detail");
                            hashMap2.put("track_id", str);
                            uRLManager.a(hashMap2);
                            i.a().a(new s() {
                                public void onErrorResponse(BusinessObject businessObject) {
                                }

                                public void onRetreivalComplete(BusinessObject businessObject) {
                                    if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0 && businessObject.getArrListBusinessObj().get(0) != null) {
                                        Track track = (Track) ((BusinessObject) businessObject.getArrListBusinessObj().get(0));
                                        PlayerTrack playerTrack = new PlayerTrack(track, track.getAlbumId(), SOURCE_TYPE.OTHER.ordinal(), track.getEnglishAlbumTitle());
                                        if (!GaanaMusicService.this.t) {
                                            GaanaMusicService.this.t = true;
                                            GaanaMusicService.this.a(playerTrack);
                                        }
                                    }
                                }
                            }, uRLManager);
                        }
                    }
                } else if (VoiceActivatedAdsFormat.Page_open.name().equalsIgnoreCase(a.c())) {
                    f.v().a(true);
                    if (TextUtils.isEmpty(a.a())) {
                        b(false);
                    } else {
                        this.s = new TextToSpeech(this.O, new OnInitListener() {
                            public void onInit(int i) {
                                if (i == 0) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("utteranceId", LoginManager.TAG_SUBTYPE_GAANA);
                                    GaanaMusicService.this.L();
                                    if (!GaanaMusicService.this.s.isSpeaking()) {
                                        GaanaMusicService.this.s.speak(a.a(), 0, hashMap);
                                        return;
                                    }
                                    return;
                                }
                                GaanaMusicService.this.b(false);
                            }
                        });
                    }
                } else {
                    b(false);
                }
            }
        }
    }

    private void L() {
        this.s.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            public void onError(String str) {
            }

            public void onStart(String str) {
            }

            public void onDone(String str) {
                GaanaMusicService.this.b(false);
            }
        });
    }

    private void a(PlayerTrack playerTrack) {
        if (PlayerManager.a(this.O).a(playerTrack, this.O) || !GaanaApplication.getInstance().getPlayerStatus()) {
            PlayerManager.a(this.O).a(PlayerType.GAANA);
            PlayerManager.a(this.O).a(null, playerTrack, 999999);
            PlayerManager.a(this.O).e(true);
        }
        b(true);
    }

    private void a(PlayerTrack playerTrack, PLAY_TYPE play_type) {
        this.an = playerTrack.b();
        this.ao = playerTrack.f();
        this.ap = play_type;
    }

    private void M() {
        this.an = null;
        this.ao = null;
        this.ap = null;
    }

    private void N() {
        PlayerTrack i = this.N.i();
        if (i != null) {
            String name;
            PLAY_TYPE play_type;
            String g = i.g();
            if (TextUtils.isEmpty(g)) {
                g = PLAYOUT_SECTION_TYPE.OTHERS.name();
            } else if (g.equals(PLAYOUT_SECTION_TYPE.LOCAL.name())) {
                g = PLAYOUT_SECTION_TYPE.OTHERS.name();
            }
            PLAYOUT_SOURCE_TYPE.OTHER.name();
            if (Q == null || !Q.k()) {
                name = PLAYOUT_SOURCE_TYPE.USER_INITIATED.name();
            } else {
                name = PLAYOUT_SOURCE_TYPE.SYSTEM_INITIATED.name();
            }
            String str = name;
            name = "URL ";
            StringBuilder stringBuilder;
            if (this.T) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(name);
                stringBuilder.append("Fetched");
                name = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(name);
                stringBuilder.append("Cached");
                name = stringBuilder.toString();
            }
            PLAY_TYPE play_type2 = PLAY_TYPE.ONLINE;
            StringBuilder stringBuilder2;
            if (this.Z) {
                play_type = PLAY_TYPE.ONLINE;
                u a;
                StringBuilder stringBuilder3;
                if (u) {
                    a = u.a();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(name);
                    stringBuilder3.append("-");
                    stringBuilder3.append(Util.S());
                    stringBuilder3.append("-");
                    stringBuilder3.append(i.b().getVideoId());
                    a.a("Player Events", "Video Played Online", stringBuilder3.toString(), Util.a(i, g), g, str);
                } else {
                    a = u.a();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(name);
                    stringBuilder3.append("-");
                    stringBuilder3.append(Util.S());
                    stringBuilder3.append("-");
                    stringBuilder3.append(i.h());
                    a.a("Player Events", "Track Played Online", stringBuilder3.toString(), Util.a(i, g), g, str);
                }
            } else if (i.a(true).isLocalMedia()) {
                play_type = PLAY_TYPE.LOCAL;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("-");
                stringBuilder2.append(i.a(true).getName());
                u.a().a("Player Events", "Track Played Local", stringBuilder2.toString(), Util.a(i, g), g, str);
            } else {
                play_type = PLAY_TYPE.OFFLINE;
                if (i.a() == 1) {
                    u.a().a("Smart Download", "Download", i.h());
                }
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("-");
                stringBuilder2.append(i.h());
                u.a().a("Player Events", "Track Played Offline", stringBuilder2.toString(), Util.a(i, g), g, str);
            }
            this.ab = true;
            a(PlayerManager.a(this.O).i(), play_type);
            AppsFlyer.getInstance().reportPlayEvent(PlayerManager.a(this.O).i(), this.Z);
            if (g.contains(PLAYOUT_SECTION_TYPE.SONG_RADIO.name()) && !TextUtils.isEmpty(this.O.getPlayoutSectionNamePrevForSongradio())) {
                this.O.setPlayoutSectionName(this.O.getPlayoutSectionNamePrevForSongradio());
                this.O.setPlayoutSectionNamePrevForSongradio(null);
            }
            if ((this.Z || !this.N.i().a(true).isLocalMedia()) && PlayerManager.a(this.O).m() == PlayerType.GAANA) {
                Item item = new Item();
                Track a2 = this.N.i().a(true);
                item.setEntityId(a2.getBusinessObjId());
                item.setName(a2.getRawName());
                item.setLanguage(a2.getLanguage());
                item.setEntityType(c.c);
                item.setArtwork(a2.getArtwork());
                item.setAtw(a2.getArtwork());
                item.setLanguage(a2.getLanguage());
                item.setSeokey(a2.getSeokey());
                item.setPremiumContent(a2.getPremiumContent());
                ArrayList arrayList = new ArrayList();
                EntityInfo entityInfo = new EntityInfo();
                entityInfo.setKey("artist");
                entityInfo.setValue(a2.getArtists());
                arrayList.add(entityInfo);
                entityInfo = new EntityInfo();
                entityInfo.setKey("download_enabled");
                entityInfo.setValue(Integer.valueOf(a2.isFreeDownloadEnabled()));
                arrayList.add(entityInfo);
                entityInfo = new EntityInfo();
                entityInfo.setKey("album");
                ArrayList arrayList2 = new ArrayList();
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                linkedTreeMap.put("album_id", a2.getAlbumId());
                linkedTreeMap.put("name", a2.getRawAlbumTitle());
                arrayList2.add(linkedTreeMap);
                entityInfo.setValue(arrayList2);
                arrayList.add(entityInfo);
                entityInfo = new EntityInfo();
                entityInfo.setKey("artwork_large");
                entityInfo.setValue(a2.getArtworkLarge());
                arrayList.add(entityInfo);
                if (!TextUtils.isEmpty(a2.getLyricsUrl())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("lyrics_url");
                    entityInfo.setValue(a2.getLyricsUrl());
                    arrayList.add(entityInfo);
                }
                entityInfo = new EntityInfo();
                entityInfo.setKey("parental_warning");
                entityInfo.setValue(Double.valueOf(a2.isParentalWarningEnabled() ? 1.0d : 0.0d));
                arrayList.add(entityInfo);
                if (!TextUtils.isEmpty(a2.getYoutubeId())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("youtube_id");
                    entityInfo.setValue(a2.getYoutubeId());
                    arrayList.add(entityInfo);
                }
                if (a2.getStreamUrls() != null) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("stream_url");
                    entityInfo.setValue(a2.getStreamUrls());
                    arrayList.add(entityInfo);
                }
                if (!TextUtils.isEmpty(a2.getLyricsType())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("lyrics_type");
                    entityInfo.setValue(a2.getLyricsType());
                    arrayList.add(entityInfo);
                }
                if (!TextUtils.isEmpty(a2.getPremiumContent())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("premium_content");
                    entityInfo.setValue(a2.getPremiumContent());
                    arrayList.add(entityInfo);
                }
                if (!TextUtils.isEmpty(a2.getVideoId())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("video_id");
                    entityInfo.setValue(a2.getVideoId());
                    arrayList.add(entityInfo);
                }
                if (!TextUtils.isEmpty(a2.getVerticalUrl())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("vert_vd");
                    entityInfo.setValue(a2.getVerticalUrl());
                    arrayList.add(entityInfo);
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("vd_expiry");
                    entityInfo.setValue(Long.toString(a2.getVideoExpiryTime()));
                    arrayList.add(entityInfo);
                }
                if (!TextUtils.isEmpty(a2.getClipVideoUrl())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("clip_vd");
                    entityInfo.setValue(a2.getClipVideoUrl());
                    arrayList.add(entityInfo);
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("vd_expiry");
                    entityInfo.setValue(Long.toString(a2.getVideoExpiryTime()));
                    arrayList.add(entityInfo);
                }
                if (!TextUtils.isEmpty(a2.getHorizontalUrl())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("horz_vd");
                    entityInfo.setValue(a2.getHorizontalUrl());
                    arrayList.add(entityInfo);
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("vd_expiry");
                    entityInfo.setValue(Long.toString(a2.getVideoExpiryTime()));
                    arrayList.add(entityInfo);
                }
                if (!TextUtils.isEmpty(a2.getDuration())) {
                    entityInfo = new EntityInfo();
                    entityInfo.setKey("duration");
                    entityInfo.setValue(a2.getDuration());
                    arrayList.add(entityInfo);
                }
                item.setEntityInfo(arrayList);
                aq.a().a(item);
            }
            if (PlayerManager.a(this.O).m() == PlayerType.GAANA) {
                PlayerManager.a(this.O).k(true);
            }
        }
    }

    public static f m() {
        if (VideoCastManager.y().f()) {
            return new b();
        }
        if (com.utilities.d.g()) {
            return new c();
        }
        return new e();
    }

    public static com.exoplayer2.a.a.a n() {
        return (f == null || !(f instanceof c)) ? null : ((c) f).b();
    }

    public static com.exoplayer2.a.a.a o() {
        return (z == null || !(z instanceof c)) ? null : ((c) z).b();
    }

    public static f p() {
        return B;
    }

    public static void q() {
        B = null;
    }

    public static f r() {
        return z;
    }

    public static f s() {
        if (f == null) {
            f = m();
        }
        return f;
    }

    public static boolean t() {
        try {
            return s().isPlaying();
        } catch (IllegalStateException unused) {
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return false;
    }

    public void onCreate() {
        super.onCreate();
        this.O = (GaanaApplication) getApplicationContext();
        if (!TextUtils.isEmpty(com.f.c.c()) && com.f.c.d()) {
            com.f.c.a();
        }
        ColombiaManager.b().a((com.managers.ColombiaManager.b) this);
        this.N = PlayerManager.a((Context) this);
        this.M = j.a(this);
        this.P = ((WifiManager) GaanaApplication.getContext().getApplicationContext().getSystemService(e.ad)).createWifiLock(1, "mylock");
        if (this.x == null) {
            this.x = new a(this);
        }
        O();
        a(this.K, "android.media.AUDIO_BECOMING_NOISY");
        a(this.L, "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        a(this.am, com.til.colombia.android.internal.d.a);
        f();
        VideoCastManager.y().a(this.at);
        GaanaApplication.getInstance().setIsEndlessPlayback(com.services.d.a().b("PREFERENCE_KEY_ENDLESS_PLAYBACK", true, false));
        M();
    }

    public IBinder onBind(Intent intent) {
        return this.F;
    }

    public void onDestroy() {
        super.onDestroy();
        ((AudioManager) getSystemService("audio")).abandonAudioFocus(this.y);
        a(this.K);
        a(this.L);
        a(this.am);
        g();
        o.d("LISTENER_KEY_MUSIC_SERVICE");
        if (f != null) {
            f.w();
        }
        if (z != null) {
            z.w();
        }
        PlayerStatus.a(this, PlayerStates.STOPPED);
        comScore.onUxInactive();
        M();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (com.utilities.d.d()) {
            startForeground(1000, this.M.a());
            v();
        }
        a(intent);
        return 1;
    }

    /* JADX WARNING: Missing block: B:191:0x0418, code skipped:
            return;
     */
    private void a(android.content.Intent r9) {
        /*
        r8 = this;
        if (r9 == 0) goto L_0x0418;
    L_0x0002:
        r0 = r9.getExtras();
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        goto L_0x0418;
    L_0x000a:
        r0 = r9.getExtras();
        r1 = "EXTRA_PLAYER_COMMAND";
        r0 = r0.containsKey(r1);
        if (r0 != 0) goto L_0x0017;
    L_0x0016:
        return;
    L_0x0017:
        r0 = r9.getExtras();
        r1 = "Connected";
        r0.containsKey(r1);
        r0 = 0;
        r8.m = r0;
        r1 = r9.getExtras();
        r2 = "IS_TRIGGERED_FROM_NOTIFICATION";
        r1 = r1.getBoolean(r2);
        r2 = 1;
        if (r1 == 0) goto L_0x0032;
    L_0x0030:
        r8.m = r2;
    L_0x0032:
        r8.O();
        r1 = "EXTRA_PLAYER_COMMAND_ARG";
        r3 = -1;
        r1 = r9.getIntExtra(r1, r3);
        com.cast_music.VideoCastManager.y();
        r3 = "EXTRA_PLAYER_COMMAND";
        r4 = com.player_framework.PlayerConstants.PlayerCommands.NONE;
        r4 = r4.toInt();
        r3 = r9.getIntExtra(r3, r4);
        r3 = com.player_framework.PlayerConstants.PlayerCommands.fromInt(r3);
        r4 = "EXTRA_CAST_PLAYER_COMMAND";
        r5 = com.player_framework.PlayerConstants.PlayerCommands.NONE;
        r5 = r5.toInt();
        r4 = r9.getIntExtra(r4, r5);
        r5 = "IS_FROM_NOTIFICATION";
        r5 = r9.getBooleanExtra(r5, r0);
        r6 = 1213; // 0x4bd float:1.7E-42 double:5.993E-321;
        if (r4 == r6) goto L_0x006c;
    L_0x0065:
        r6 = 1212; // 0x4bc float:1.698E-42 double:5.99E-321;
        if (r4 != r6) goto L_0x006a;
    L_0x0069:
        goto L_0x006c;
    L_0x006a:
        r4 = r0;
        goto L_0x006d;
    L_0x006c:
        r4 = r2;
    L_0x006d:
        r6 = "IS_FROM_WIDGET";
        r6 = r9.getBooleanExtra(r6, r0);
        r7 = r3.name();
        r8.a(r7, r5, r6);
        r5 = com.player_framework.GaanaMusicService.AnonymousClass17.b;
        r6 = r3.ordinal();
        r5 = r5[r6];
        r6 = 2131822872; // 0x7f110918 float:1.9278528E38 double:1.053260444E-314;
        switch(r5) {
            case 1: goto L_0x0385;
            case 2: goto L_0x031e;
            case 3: goto L_0x0315;
            case 4: goto L_0x02ca;
            case 5: goto L_0x02c1;
            case 6: goto L_0x0254;
            case 7: goto L_0x0205;
            case 8: goto L_0x0109;
            case 9: goto L_0x00fe;
            case 10: goto L_0x00c4;
            case 11: goto L_0x00bf;
            case 12: goto L_0x00ba;
            case 13: goto L_0x00af;
            case 14: goto L_0x00aa;
            case 15: goto L_0x0094;
            case 16: goto L_0x008f;
            case 17: goto L_0x008a;
            default: goto L_0x0088;
        };
    L_0x0088:
        goto L_0x03fa;
    L_0x008a:
        r8.P();
        goto L_0x03fa;
    L_0x008f:
        r8.J();
        goto L_0x03fa;
    L_0x0094:
        r9 = s();
        r9 = r9.v();
        r1 = com.constants.Constants.cK;
        if (r9 < r1) goto L_0x00a5;
    L_0x00a0:
        r8.k(r0);
        goto L_0x03fa;
    L_0x00a5:
        r8.k(r2);
        goto L_0x03fa;
    L_0x00aa:
        r8.aj();
        goto L_0x03fa;
    L_0x00af:
        r1 = "EXTRA_PLAYER_COMMAND_ARG";
        r9 = r9.getIntExtra(r1, r0);
        r8.e(r9);
        goto L_0x03fa;
    L_0x00ba:
        r8.x();
        goto L_0x03fa;
    L_0x00bf:
        r8.v();
        goto L_0x03fa;
    L_0x00c4:
        r1 = "EXTRA_ERROR_MSG";
        r1 = r9.getStringExtra(r1);
        r4 = "EXTRA_ERROR_TYPE";
        r9 = r9.getSerializableExtra(r4);
        r9 = (com.constants.Constants.ErrorType) r9;
        r4 = com.player_framework.o.d();
        r4 = r4.values();
        r4 = r4.iterator();
    L_0x00de:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x00f0;
    L_0x00e4:
        r5 = r4.next();
        r5 = (com.player_framework.n) r5;
        if (r5 == 0) goto L_0x00de;
    L_0x00ec:
        r5.displayErrorDialog(r1, r9);
        goto L_0x00de;
    L_0x00f0:
        r1 = com.constants.Constants.ErrorType.TEMPORARY_NETWORK_ERROR;
        if (r9 != r1) goto L_0x00f9;
    L_0x00f4:
        r8.n(r0);
        goto L_0x03fa;
    L_0x00f9:
        r8.m(r0);
        goto L_0x03fa;
    L_0x00fe:
        r1 = "EXTRA_PLAYER_COMMAND_ARG";
        r9 = r9.getIntExtra(r1, r0);
        r8.c(r9);
        goto L_0x03fa;
    L_0x0109:
        r9 = r8.b;
        r9 = r9.w();
        if (r9 == 0) goto L_0x012f;
    L_0x0111:
        r9 = com.managers.f.a;
        if (r9 == 0) goto L_0x012f;
    L_0x0115:
        r9 = r8.b;
        r9.E();
        r9 = r8.b;
        r9.C();
        r9 = r8.b;
        r9.A();
        r9 = com.services.d.a();
        r1 = "PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS";
        r9.a(r1, r0, r0);
        goto L_0x03fa;
    L_0x012f:
        r9 = com.managers.f.v();
        r9 = r9.s();
        if (r9 != 0) goto L_0x0143;
    L_0x0139:
        r9 = com.managers.f.v();
        r9 = r9.t();
        if (r9 == 0) goto L_0x01b6;
    L_0x0143:
        r9 = com.managers.f.v();
        r9 = r9.u();
        if (r9 == 0) goto L_0x01b6;
    L_0x014d:
        C = r0;
        r9 = com.managers.f.v();
        r9.b(r2);
        r9 = A;
        if (r9 == 0) goto L_0x0199;
    L_0x015a:
        r9 = B;
        if (r9 == 0) goto L_0x0199;
    L_0x015e:
        r9 = B;
        r1 = z;
        r9 = r9.equals(r1);
        if (r9 == 0) goto L_0x017a;
    L_0x0168:
        r8.aa();
        r9 = z;
        r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r8.a(r9, r1, r1);
        r9 = f;
        r9 = (com.player_framework.c) r9;
        r9.A();
        goto L_0x0195;
    L_0x017a:
        r9 = B;
        r1 = f;
        r9 = r9.equals(r1);
        if (r9 == 0) goto L_0x0195;
    L_0x0184:
        r9 = f;
        r9.q();
        r9 = com.player_framework.PlayerStatus.PlayerStates.PLAYING;
        com.player_framework.PlayerStatus.a(r8, r9);
        r9 = z;
        r9 = (com.player_framework.c) r9;
        r9.B();
    L_0x0195:
        r9 = 0;
        B = r9;
        goto L_0x01aa;
    L_0x0199:
        r9 = s();
        r9 = r9 instanceof com.player_framework.c;
        if (r9 == 0) goto L_0x01aa;
    L_0x01a1:
        r9 = s();
        r9 = (com.player_framework.c) r9;
        r9.B();
    L_0x01aa:
        r9 = com.managers.f.v();
        r9.g(r0);
        r8.c();
        goto L_0x03fa;
    L_0x01b6:
        r9 = com.constants.Constants.aa;
        if (r9 == 0) goto L_0x0200;
    L_0x01ba:
        r9 = com.constants.Constants.h;
        if (r9 > 0) goto L_0x0200;
    L_0x01be:
        r9 = com.utilities.Util.c();
        if (r9 != 0) goto L_0x0200;
    L_0x01c4:
        r9 = com.managers.PlayerManager.a(r8);
        r9 = r9.m();
        r1 = com.managers.PlayerManager.PlayerType.GAANA;
        if (r9 != r1) goto L_0x01f0;
    L_0x01d0:
        r9 = com.managers.PlayerManager.a(r8);
        r9 = r9.x();
        if (r9 == 0) goto L_0x01f0;
    L_0x01da:
        r9 = com.managers.PlayerManager.a(r8);
        r9 = r9.x();
        r9 = r9.b();
        r9 = r9.isLocalMedia();
        if (r9 == 0) goto L_0x01f0;
    L_0x01ec:
        r8.n(r2);
        goto L_0x01ff;
    L_0x01f0:
        r9 = r8.getResources();
        r9 = r9.getString(r6);
        r9 = android.widget.Toast.makeText(r8, r9, r0);
        r9.show();
    L_0x01ff:
        return;
    L_0x0200:
        r8.n(r2);
        goto L_0x03fa;
    L_0x0205:
        r9 = com.constants.Constants.aa;
        if (r9 == 0) goto L_0x024f;
    L_0x0209:
        r9 = com.constants.Constants.h;
        if (r9 > 0) goto L_0x024f;
    L_0x020d:
        r9 = com.utilities.Util.c();
        if (r9 != 0) goto L_0x024f;
    L_0x0213:
        r9 = com.managers.PlayerManager.a(r8);
        r9 = r9.m();
        r1 = com.managers.PlayerManager.PlayerType.GAANA;
        if (r9 != r1) goto L_0x023f;
    L_0x021f:
        r9 = com.managers.PlayerManager.a(r8);
        r9 = r9.w();
        if (r9 == 0) goto L_0x023f;
    L_0x0229:
        r9 = com.managers.PlayerManager.a(r8);
        r9 = r9.w();
        r9 = r9.b();
        r9 = r9.isLocalMedia();
        if (r9 == 0) goto L_0x023f;
    L_0x023b:
        r8.o(r2);
        goto L_0x024e;
    L_0x023f:
        r9 = r8.getResources();
        r9 = r9.getString(r6);
        r9 = android.widget.Toast.makeText(r8, r9, r0);
        r9.show();
    L_0x024e:
        return;
    L_0x024f:
        r8.o(r2);
        goto L_0x03fa;
    L_0x0254:
        r9 = com.constants.Constants.dc;
        if (r9 == 0) goto L_0x025a;
    L_0x0258:
        com.constants.Constants.dc = r0;
    L_0x025a:
        r9 = r8.O;
        if (r9 == 0) goto L_0x0277;
    L_0x025e:
        r9 = r8.O;
        r9 = com.managers.ad.a(r9);
        r9 = r9.o();
        r9 = r9.booleanValue();
        if (r9 == 0) goto L_0x0277;
    L_0x026e:
        r9 = r8.O;
        r9 = com.managers.ad.a(r9);
        r9.k();
    L_0x0277:
        if (r4 == 0) goto L_0x02a9;
    L_0x0279:
        r9 = f;
        if (r9 == 0) goto L_0x02a9;
    L_0x027d:
        r9 = f;
        if (r9 == 0) goto L_0x02a9;
    L_0x0281:
        r9 = r8.O;
        if (r9 == 0) goto L_0x028a;
    L_0x0285:
        r9 = r8.O;
        com.player_framework.o.d(r9);
    L_0x028a:
        r9 = f;
        r9.p();
        r9 = f;
        r9.w();
        r9 = com.utilities.d.g();
        if (r9 == 0) goto L_0x02a2;
    L_0x029a:
        r9 = new com.player_framework.c;
        r9.<init>();
        f = r9;
        goto L_0x02a9;
    L_0x02a2:
        r9 = new com.player_framework.e;
        r9.<init>();
        f = r9;
    L_0x02a9:
        if (r4 == 0) goto L_0x02bc;
    L_0x02ab:
        r9 = f;
        if (r9 == 0) goto L_0x02bc;
    L_0x02af:
        r9 = f;
        r9 = r9 instanceof com.player_framework.b;
        if (r9 == 0) goto L_0x02bc;
    L_0x02b5:
        r9 = com.cast_music.VideoCastManager.y();
        r9.h();
    L_0x02bc:
        r8.m(r2);
        goto L_0x03fa;
    L_0x02c1:
        r9 = com.player_framework.PlayerConstants.PauseReasons.fromInt(r1);
        r8.b(r9);
        goto L_0x03fa;
    L_0x02ca:
        r9 = r8.O;
        r9 = com.managers.ad.a(r9);
        r9 = r9.o();
        r9 = r9.booleanValue();
        if (r9 == 0) goto L_0x02ee;
    L_0x02da:
        r9 = t();
        if (r9 == 0) goto L_0x02e3;
    L_0x02e0:
        g = r2;
        goto L_0x02ee;
    L_0x02e3:
        r9 = Q;
        if (r9 == 0) goto L_0x02ee;
    L_0x02e7:
        r9 = com.managers.ad.a(r8);
        r9.j();
    L_0x02ee:
        r9 = com.player_framework.PlayerStatus.a(r8);
        r9 = r9.c();
        if (r9 != 0) goto L_0x030c;
    L_0x02f8:
        r9 = com.player_framework.PlayerStatus.a(r8);
        r9 = r9.b();
        if (r9 == 0) goto L_0x0303;
    L_0x0302:
        goto L_0x030c;
    L_0x0303:
        r9 = com.player_framework.PlayerConstants.PauseReasons.fromInt(r1);
        r8.b(r9);
        goto L_0x03fa;
    L_0x030c:
        r9 = com.player_framework.PlayerConstants.PauseReasons.fromInt(r1);
        r8.a(r9);
        goto L_0x03fa;
    L_0x0315:
        r9 = com.player_framework.PlayerConstants.PauseReasons.fromInt(r1);
        r8.a(r9);
        goto L_0x03fa;
    L_0x031e:
        r0 = r8.N;
        r1 = com.managers.PlayerManager.PlaySequenceType.CURRENT;
        r0 = r0.a(r1);
        r1 = Q;
        if (r1 == 0) goto L_0x035a;
    L_0x032a:
        if (r0 == 0) goto L_0x035a;
    L_0x032c:
        r0 = r0.h();
        r1 = Q;
        r1 = r1.h();
        r0 = r0.equalsIgnoreCase(r1);
        if (r0 != 0) goto L_0x035a;
    L_0x033c:
        r0 = com.player_framework.o.d();
        r0 = r0.values();
        r0 = r0.iterator();
    L_0x0348:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x035a;
    L_0x034e:
        r1 = r0.next();
        r1 = (com.player_framework.n) r1;
        if (r1 == 0) goto L_0x0348;
    L_0x0356:
        r1.onPlayerRepeatReset(r2);
        goto L_0x0348;
    L_0x035a:
        r0 = "EXTRA_TRACK_OBJ";
        r9 = r9.getSerializableExtra(r0);
        r9 = (com.models.PlayerTrack) r9;
        Q = r9;
        r9 = com.player_framework.PlayerStatus.PlayerStates.LOADING;
        com.player_framework.PlayerStatus.a(r8, r9);
        r9 = f;
        if (r9 == 0) goto L_0x0379;
    L_0x036d:
        r9 = f;
        r9 = r9.v();
        r0 = p;
        if (r9 < r0) goto L_0x0379;
    L_0x0377:
        r8.q = r2;
    L_0x0379:
        r9 = com.managers.f.v();
        r9.y();
        r8.ad();
        goto L_0x03fa;
    L_0x0385:
        r9 = r8.N;
        r0 = com.managers.PlayerManager.PlaySequenceType.CURRENT;
        r9 = r9.a(r0);
        if (r9 == 0) goto L_0x03c1;
    L_0x038f:
        r0 = Q;
        if (r0 == 0) goto L_0x03c1;
    L_0x0393:
        r9 = r9.h();
        r0 = Q;
        r0 = r0.h();
        r9 = r9.equalsIgnoreCase(r0);
        if (r9 != 0) goto L_0x03c1;
    L_0x03a3:
        r9 = com.player_framework.o.d();
        r9 = r9.values();
        r9 = r9.iterator();
    L_0x03af:
        r0 = r9.hasNext();
        if (r0 == 0) goto L_0x03c1;
    L_0x03b5:
        r0 = r9.next();
        r0 = (com.player_framework.n) r0;
        if (r0 == 0) goto L_0x03af;
    L_0x03bd:
        r0.onPlayerRepeatReset(r2);
        goto L_0x03af;
    L_0x03c1:
        r9 = r8.N;
        r0 = com.managers.PlayerManager.PlaySequenceType.CURRENT;
        r9 = r9.a(r0);
        Q = r9;
        r9 = Q;
        if (r9 == 0) goto L_0x03f7;
    L_0x03cf:
        r9 = Q;
        r9 = r9.a(r2);
        if (r9 == 0) goto L_0x03f7;
    L_0x03d7:
        r9 = com.player_framework.PlayerStatus.PlayerStates.LOADING;
        com.player_framework.PlayerStatus.a(r8, r9);
        r9 = f;
        if (r9 == 0) goto L_0x03ec;
    L_0x03e0:
        r9 = f;
        r9 = r9.v();
        r0 = p;
        if (r9 < r0) goto L_0x03ec;
    L_0x03ea:
        r8.q = r2;
    L_0x03ec:
        r9 = com.managers.f.v();
        r9.y();
        r8.ad();
        goto L_0x03fa;
    L_0x03f7:
        com.player_framework.o.f(r8);
    L_0x03fa:
        r9 = com.utilities.d.d();
        if (r9 == 0) goto L_0x0417;
    L_0x0400:
        r9 = com.player_framework.PlayerConstants.PlayerCommands.CANCEL_CF_SCHEDULER;
        if (r3 == r9) goto L_0x0408;
    L_0x0404:
        r9 = com.player_framework.PlayerConstants.PlayerCommands.FETCH_CF_TRACKS;
        if (r3 != r9) goto L_0x0417;
    L_0x0408:
        r9 = Q;
        if (r9 == 0) goto L_0x0414;
    L_0x040c:
        r9 = Q;
        r9 = r9.b();
        if (r9 != 0) goto L_0x0417;
    L_0x0414:
        r8.stopForeground(r2);
    L_0x0417:
        return;
    L_0x0418:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.player_framework.GaanaMusicService.a(android.content.Intent):void");
    }

    private void a(String str, boolean z, boolean z2) {
        if (z || z2) {
            String str2 = "";
            if (z) {
                str2 = "notif";
            } else if (z2) {
                str2 = "widget";
            }
            String str3 = str2;
            if (str.equals("PLAY_PAUSE")) {
                str = s().isPlaying() ? "PAUSE" : "PLAY";
            }
            Object obj = -1;
            switch (str.hashCode()) {
                case 2458420:
                    if (str.equals("PLAY")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 2555906:
                    if (str.equals("STOP")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 75902422:
                    if (str.equals("PAUSE")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 938449182:
                    if (str.equals("PLAY_NEXT")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 1611100360:
                    if (str.equals("FAVORITE_TRACK")) {
                        obj = null;
                        break;
                    }
                    break;
                case 1969730466:
                    if (str.equals("PLAY_PREVIOUS")) {
                        obj = 3;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    an.a().a("click", "ac", "", str3, "", "fav", "", "");
                    break;
                case 1:
                    an.a().a("click", "ac", "", str3, "", "pause", "", "");
                    break;
                case 2:
                    an.a().a("click", "ac", "", str3, "", "play", "", "");
                    break;
                case 3:
                    an.a().a("click", "ac", "", str3, "", "prev", "", "");
                    break;
                case 4:
                    an.a().a("click", "ac", "", str3, "", "nxt", "", "");
                    break;
                case 5:
                    an.a().a("click", "ac", "", str3, "", "close", "", "");
                    break;
            }
        }
    }

    private void O() {
        if (f == null) {
            f = m();
        }
        if (this.G == null) {
            this.G = new g();
        }
    }

    public void onTaskRemoved(Intent intent) {
        if (f.v().w()) {
            f.v().E();
        }
        if (!(f instanceof b)) {
            m(true);
            Constants.aG = false;
            Constants.aH = "";
        }
    }

    private void a(BroadcastReceiver broadcastReceiver, String str) {
        a(broadcastReceiver);
        registerReceiver(broadcastReceiver, new IntentFilter(str));
    }

    private void a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public static boolean u() {
        return l;
    }

    private void P() {
        Q = PlayerManager.a((Context) this).a(PlaySequenceType.CURRENT);
        if (Q == null) {
            x();
            this.G.a();
            return;
        }
        if (Q.b().isFavorite().booleanValue()) {
            Q.b().setFavorite(Boolean.valueOf(false));
            n.a().c(Q.b());
            n.a().b("Notification Player", Q.b().getBusinessObjId());
        } else {
            Q.b().setFavorite(Boolean.valueOf(true));
            n.a().b(Q.b());
            n.a().a("Notification Player", Q.b().getBusinessObjId());
        }
        if (!(ai.a() == null || ((Activity) ai.a()).isFinishing())) {
            ((GaanaActivity) ai.a()).refreshForFavorite();
        }
        this.M.a(Q, 1234);
        l = true;
        if (w()) {
            T();
        }
        Q();
    }

    public void v() {
        Q = PlayerManager.a((Context) this).a(PlaySequenceType.CURRENT);
        if (Q == null) {
            x();
            this.G.a();
            return;
        }
        if (!f.v().t()) {
            ad a = ad.a(GaanaApplication.getContext());
            if (a.c() == null || a.d() == null || !a.o().booleanValue()) {
                this.M.a(Q, 1234);
            } else {
                PlayerTrack playerTrack = Q;
                if (!(ai.a() == null || ((Activity) ai.a()).isFinishing())) {
                    Fragment miniPlayer = ((GaanaActivity) ai.a()).getMiniPlayer();
                    BaseFragment baseFragment = ((GaanaActivity) ai.a()).getmCurrentPlayerFragment();
                    if (baseFragment != null && (baseFragment instanceof PlayerRadioFragmentV4)) {
                        ((PlayerRadioFragmentV4) baseFragment).h();
                    }
                    if ((miniPlayer instanceof MiniPlayerFragment) && !a.h().equals("")) {
                        ((MiniPlayerFragment) miniPlayer).b(a.h());
                    }
                }
                playerTrack.b().setAlbumName(a.l());
                playerTrack.b().setTracktitle(a.h());
                playerTrack.b().setArtist(null);
                this.M.a(playerTrack, 1234);
            }
            l = true;
            this.G = new g();
            this.G.a((Context) this, Q.a(true));
        }
        if (f == null) {
            f = m();
        }
        if (PlayerStatus.a(this).d() || f.n() || f.l()) {
            T();
        }
        Q();
    }

    private void Q() {
        if (com.services.d.a().b("PREFERENCE_DOES_WIDGET_EXIST", true, false)) {
            Intent intent;
            if (this.k && PlayerManager.a(this.O).l() == 0) {
                intent = new Intent(this, GaanaWidgetProvider.class);
                intent.setAction("APP_WIDGET_UPDATE_Q_EMPTY");
                intent.putExtra("appWidgetIds", new int[]{R.xml.gaana_widget_player});
                sendBroadcast(intent);
                this.k = false;
            } else {
                if (Q == null) {
                    R();
                }
                intent = new Intent(this, GaanaWidgetProvider.class);
                intent.setAction("APP_WIDGET_UPDATE_ACTION");
                intent.putExtra("isPaused", w());
                if (Q != null) {
                    intent.putExtra("currentTrack", Q.a(true));
                }
                intent.putExtra("appWidgetIds", new int[]{R.xml.gaana_widget_player});
                sendBroadcast(intent);
            }
        }
    }

    private void R() {
        Context context = this.O;
        if (this.d == null) {
            this.d = PlayerManager.a(context);
        }
        if (this.d.n() == null) {
            ArrayList e = o.a().e();
            int b = com.services.d.a().b("PREFERENCE_KEY_LAST_PLAYED_TRACK_INDEX", 0, true);
            if (e != null && e.size() > 0) {
                if (b < 0 || b > e.size() - 1 || b > Constants.dg - 1) {
                    b = 0;
                }
                PlayerManager.a(context).a(e, (PlayerTrack) e.get(b));
                Q = PlayerManager.a(context).i();
                S();
                this.d.a(PlayerType.GAANA, context);
                PlayerStatus.a(context, PlayerStates.STOPPED);
                PlayerManager.a = false;
            }
        }
    }

    private void S() {
        Context context = this.O;
        com.services.d a = com.services.d.a();
        if (a.b("PREFERENCE_KEY_SHUFFLE_STATUS", false, true)) {
            ArrayList f = o.a().f();
            if (f == null || f.size() <= 0) {
                a.a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
            } else {
                PlayerManager.a(context).a(f);
            }
        }
        int b = a.b("PREFERENCE_KEY_REPEAT_STATUS", 2, true);
        if (b == 1) {
            PlayerManager.a(context).b(true);
        } else if (b == 2) {
            PlayerManager.a(context).c(true);
        }
    }

    public boolean w() {
        return PlayerStatus.a(this).d() || f == null || f.n() || f.l();
    }

    private void T() {
        this.M.a(true);
        this.G.b();
    }

    public void x() {
        if (this.b.w()) {
            this.b.E();
            this.b.C();
        }
        stopForeground(true);
        Q();
        l = false;
    }

    private void U() {
        com.services.d a = com.services.d.a();
        D = a.b("PREFERENCE_KEY_GAPLESS_PLAYBACK", false, true);
        int b = a.b("PREFERENCE_KEY_CROSSFADE_VALUE", 0, true);
        if (D && b > 0 && Constants.C == 1) {
            A = true;
            E = b * 1000;
            Constants.D = 1;
            return;
        }
        A = false;
        E = 0;
        Constants.D = 0;
    }

    /* JADX WARNING: Missing block: B:11:0x0023, code skipped:
            return;
     */
    private void V() {
        /*
        r1 = this;
        r0 = f;
        r0 = r0 instanceof com.player_framework.b;
        if (r0 != 0) goto L_0x0023;
    L_0x0006:
        r0 = f;
        r0 = r0 instanceof com.player_framework.e;
        if (r0 == 0) goto L_0x000d;
    L_0x000c:
        goto L_0x0023;
    L_0x000d:
        r0 = r1.N;
        r0 = r0.t();
        if (r0 != 0) goto L_0x0022;
    L_0x0015:
        r0 = z;
        if (r0 != 0) goto L_0x001f;
    L_0x0019:
        r0 = m();
        z = r0;
    L_0x001f:
        r1.ab();
    L_0x0022:
        return;
    L_0x0023:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.player_framework.GaanaMusicService.V():void");
    }

    private void W() {
        if (this.x != null) {
            this.x.removeMessages(1001);
            this.x.sendEmptyMessageDelayed(1001, 1000);
        }
    }

    private void X() {
        if (this.x != null) {
            this.x.removeMessages(1001);
            this.x.sendEmptyMessage(1001);
        }
    }

    private void Y() {
        if (this.x != null) {
            this.x.removeMessages(1001);
        }
    }

    private void Z() {
        if (f != null && f.isPlaying() && !C) {
            int u = f.u() - f.v();
            if (u <= E + 2000 && u >= 0 && A && !this.N.d()) {
                aa();
            }
            if (u <= E && u >= 0 && A && !this.N.d()) {
                a(u);
            }
            if (u < 0) {
                Y();
            } else {
                W();
            }
        }
    }

    private void aa() {
        if (z == null) {
            Y();
        } else if (!z.m() && !z.isPlaying()) {
            ((c) z).A();
            z.a(this.W);
            z.q();
            a(z, 0.0f, 0.0f);
            z.b(false);
        }
    }

    private void a(int i) {
        float f = ((float) i) / 15000.0f;
        float f2 = (float) (1.0d - ((double) f));
        if (z != null) {
            a(f, f, f);
            if (z.isPlaying()) {
                a(z, f2, f2);
            }
        }
    }

    /* JADX WARNING: Missing block: B:19:0x003b, code skipped:
            return false;
     */
    private boolean b(com.models.PlayerTrack r3) {
        /*
        r2 = this;
        r0 = 0;
        if (r3 == 0) goto L_0x003b;
    L_0x0003:
        r1 = r3.b();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x003b;
    L_0x000a:
        r3 = r3.b();
        r3 = r3.getVerticalUrl();
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x003a;
    L_0x0018:
        r3 = com.gaana.application.GaanaApplication.getInstance();
        r3 = r3.isAppInForeground();
        if (r3 == 0) goto L_0x003a;
    L_0x0022:
        r3 = com.constants.Constants.cH;
        if (r3 == 0) goto L_0x003a;
    L_0x0026:
        r3 = com.constants.Constants.cN;
        if (r3 != 0) goto L_0x003a;
    L_0x002a:
        r3 = com.utilities.d.g();
        if (r3 == 0) goto L_0x003a;
    L_0x0030:
        r3 = r2.O;
        r3 = r3.isVideoAutoplay();
        if (r3 == 0) goto L_0x003a;
    L_0x0038:
        r3 = 1;
        return r3;
    L_0x003a:
        return r0;
    L_0x003b:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.player_framework.GaanaMusicService.b(com.models.PlayerTrack):boolean");
    }

    private void ab() {
        if (!this.N.d()) {
            if (!this.N.d() || this.N.m() == PlayerType.GAANA_RADIO) {
                R = this.N.b(PlaySequenceType.NEXT);
            } else {
                R = this.N.b(PlaySequenceType.CURRENT);
            }
            this.O.setInitialPlayTimeForSecondaryTrack(Calendar.getInstance().getTimeInMillis());
            if (R == null || R.a(true) == null) {
                try {
                    z.w();
                    z = null;
                } catch (IllegalStateException unused) {
                }
            } else {
                String a;
                d(R);
                R.b(false);
                if (!this.S) {
                    a = new l().a(R.a(true), false);
                    if (TextUtils.isEmpty(a)) {
                        b(Integer.parseInt(R.a(true).getBusinessObjId()));
                        if (!this.O.isAppInOfflineMode() && Util.j(this.O)) {
                            this.S = true;
                        }
                    } else {
                        this.i = true;
                        a(a, null, -1, false);
                        return;
                    }
                }
                if (this.S) {
                    boolean z = b(R) && this.S;
                    v = z;
                    if (v) {
                        a = new d(this).a(R.b());
                        if (z instanceof c) {
                            ((c) z).f(true);
                        }
                    } else {
                        a = new d(this).a(R);
                        if (z instanceof c) {
                            ((c) z).f(false);
                        }
                    }
                    if (!TextUtils.isEmpty(a)) {
                        this.i = true;
                        if (!(a.startsWith("http:") || a.startsWith("https:"))) {
                            if (v) {
                                a = Util.k(a);
                            } else {
                                a = Util.l(a);
                            }
                        }
                        a(a, R, R.b().getAvAd(), false);
                    } else if (v) {
                        new d(this).b(R.b(), new com.i.e.b() {
                            public void onDataRetrieved(Object obj, int i, boolean z) {
                                GaanaMusicService.this.i = false;
                                String str = null;
                                if (obj != null && (obj instanceof LinkedTreeMap)) {
                                    String str2 = (String) ((LinkedTreeMap) obj).get("data");
                                    if (!TextUtils.isEmpty(str2)) {
                                        str = Util.k(str2);
                                    }
                                }
                                GaanaMusicService.this.a(str, GaanaMusicService.R, i, true);
                            }
                        });
                    } else {
                        a(R, new com.i.e.b() {
                            public void onDataRetrieved(Object obj, int i, boolean z) {
                                if (obj != null) {
                                    GaanaMusicService.this.i = false;
                                    String str = null;
                                    if (obj != null && (obj instanceof LinkedTreeMap)) {
                                        String str2 = (String) ((LinkedTreeMap) obj).get("data");
                                        if (!TextUtils.isEmpty(str2)) {
                                            str = Util.k(str2);
                                        }
                                    }
                                    GaanaMusicService.this.a(str, GaanaMusicService.R, i, true);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private void a(String str, PlayerTrack playerTrack, int i, boolean z) {
        if (z != null) {
            if (str != null) {
                if (this.S) {
                    this.U = true;
                    this.O.sendUrlFetchTimeEventForSecondaryTrack(this.i);
                }
                try {
                    z.c(true);
                    z.d(false);
                    z.a(this, str, playerTrack, i, z);
                    X();
                } catch (IllegalArgumentException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (SecurityException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            } else {
                try {
                    z.w();
                    z = null;
                } catch (IllegalStateException unused) {
                }
            }
        }
    }

    private void ac() {
        if (GaanaLogger.a().b() != null) {
            try {
                GaanaLogger.a().a(com.logging.b.a());
                GaanaLogger.a().b().i(String.valueOf(s().v()));
                GaanaLogger.a().c().a(this);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private void c(PlayerTrack playerTrack) {
        boolean z = false;
        if (f instanceof b) {
            this.Z = playerTrack.a(true).isLocalMedia() ^ 1;
        } else if (this.O.isAppInOfflineMode() || !(PlayerManager.a(this.O).m() == PlayerType.GAANA_RADIO || TextUtils.isEmpty(playerTrack.h()) || !DownloadManager.c().l(Integer.parseInt(playerTrack.h())).booleanValue())) {
            this.Z = false;
        } else {
            boolean z2 = (Constants.Q && playerTrack.a(true).isLocalMedia()) ? false : true;
            this.Z = z2;
        }
        if (DownloadManager.c().a(Q.b()).booleanValue()) {
            this.Z = false;
        }
        if (b(playerTrack) && this.Z) {
            z = true;
        }
        u = z;
    }

    private void d(PlayerTrack playerTrack) {
        if (f instanceof b) {
            this.S = playerTrack.a(true).isLocalMedia() ^ 1;
            return;
        }
        boolean z = false;
        boolean booleanValue;
        try {
            booleanValue = DownloadManager.c().l(Integer.parseInt(playerTrack.h())).booleanValue();
        } catch (NumberFormatException unused) {
            booleanValue = false;
        }
        if (this.O.isAppInOfflineMode() || !(PlayerManager.a(this.O).m() == PlayerType.GAANA_RADIO || TextUtils.isEmpty(playerTrack.h()) || !booleanValue)) {
            this.S = false;
            return;
        }
        if (!(Constants.Q && playerTrack.a(true).isLocalMedia())) {
            z = true;
        }
        this.S = z;
    }

    private void l(boolean z) {
        this.X = z;
        a(R, false);
    }

    private void a(n[] nVarArr, boolean z) {
        if (z != null) {
            this.aa = false;
            aj = ai;
            if (f != null && f.v() >= p) {
                this.q = true;
            }
            this.O.setInitialPlayTimeForSecondaryTrack(Calendar.getInstance().getTimeInMillis());
            this.Y = true;
            this.N.a(null, R);
            Q = R;
            this.Z = this.S;
            u = v;
            this.T = this.U;
            this.h = this.i;
            if (!ap.a().b(Q.a(true))) {
                c(getString(R.string.region_song_not_available));
            } else if (ap.a().c(Q.a(true))) {
                c(getString(R.string.error_msg_explicit_content_track));
            } else {
                try {
                    d(this.Z);
                    ak();
                    com.services.d.a().a("PREFERENCE_KEY_LAST_TRACK_PLAYOUT_SOURCE", com.services.d.a().c("PREFERENCE_KEY_LAST_TRACK_PLAYOUT_SOURCE_SECONDARY", false), false);
                    com.services.d.a().a("PREFERENCE_KEY_LAST_TRACK_PLAYOUT_SOURCE_SECONDARY", String.valueOf(PLAYOUT_SOURCE.NETWORK.ordinal()), false);
                    f.w();
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
                f = z;
                f.x();
                f.d(true);
                PlayerStatus.a(this, PlayerStates.PLAYING);
                try {
                    this.n = 0;
                    a(f, 1.0f, 1.0f);
                    z = null;
                } catch (IllegalStateException unused) {
                }
                f.c(false);
                f.b(false);
                if (this.N.m() == PlayerType.GAANA_RADIO) {
                    this.N.f(true);
                }
                v();
                try {
                    if (this.P.isHeld()) {
                        this.P.release();
                    }
                    this.P.acquire();
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                String h = this.N.a(PlaySequenceType.CURRENT).h();
                if (h != null) {
                    com.services.d.a().a("PREFERENCE_KEY_LAST_ONE_TOUCH_RADIO_TRACK_PLAYED", h, true);
                }
                for (int i = 0; i < nVarArr.length; i++) {
                    if (nVarArr[i] != null) {
                        nVarArr[i].onPlayNext(z, false);
                        nVarArr[i].onPlayerPlay();
                    }
                }
                for (m mVar : o.b().values()) {
                    if (mVar != null) {
                        mVar.onPrepared(f);
                    }
                }
            }
        }
    }

    private void ad() {
        if (al() && this != null) {
            this.V = true;
            for (int i = 0; i < 2; i++) {
                this.ar[i] = false;
            }
            if (PlayerStatus.a(this).d()) {
                if (f.m()) {
                    PlayerStatus.a(this, PlayerStates.LOADING);
                } else {
                    try {
                        this.n = 0;
                        f.a(false);
                        f.q();
                    } catch (IllegalStateException unused) {
                    }
                    PlayerStatus.a(this, PlayerStates.PLAYING);
                }
                f.b(false);
                try {
                    if (this.P.isHeld()) {
                        this.P.release();
                    }
                    this.P.acquire();
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
                v();
                for (n nVar : o.d().values()) {
                    if (nVar != null) {
                        nVar.onPlayerResume();
                    }
                }
                return;
            }
            if (Q == null) {
                Q = this.N.a(PlaySequenceType.CURRENT);
            }
            a(Q, true);
        }
    }

    private void ae() {
        this.aa = false;
        if (Q == null) {
            Q = this.N.a(PlaySequenceType.CURRENT);
        }
        if (Q == null) {
            ag();
        } else if (!ap.a().b(Q.a(true))) {
            c(getString(R.string.region_song_not_available));
        } else if (ap.a().c(Q.a(true))) {
            c(getString(R.string.error_msg_explicit_content_track));
        } else if (f != null) {
            try {
                if (!PlayerStatus.a(this).e() && f.isPlaying()) {
                    f.p();
                }
                if (z != null) {
                    z.p();
                    z.w();
                    z = null;
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
            f.b(false);
            f.c(true);
            PlayerStatus.a(this, PlayerStates.LOADING);
            if (Q != null && Q.h() != null) {
                if (Q.b().isLocalMedia() || ((!this.O.isAppInOfflineMode() && Util.j((Context) this)) || DownloadManager.c().l(Integer.parseInt(Q.h())).booleanValue() || DownloadManager.c().a(Q.b()).booleanValue())) {
                    if (this.N.m() != PlayerType.GAANA_RADIO) {
                        ad.a(this.O).b(Boolean.valueOf(false));
                        ad.a(this.O).k();
                        ad.a(this.O).c(Boolean.valueOf(false));
                    }
                    c(Q);
                    Q.b(false);
                    af();
                    return;
                }
                ai();
            }
        }
    }

    public static boolean c(boolean z) {
        PlayerTrack playerTrack;
        if (z) {
            playerTrack = Q;
        } else {
            playerTrack = R;
        }
        if (c || !ap.a().q() || playerTrack == null || playerTrack.a(true).isLocalMedia() || playerTrack.e() == SOURCE_TYPE.RADIO_MIRCHI.ordinal()) {
            return false;
        }
        return true;
    }

    private void a(PlayerTrack playerTrack, boolean z) {
        if (z && Q == null) {
            ag();
            return;
        }
        this.V = z;
        this.W = false;
        if (c || !ap.a().q() || playerTrack.a(true).isLocalMedia() || playerTrack.e() == SOURCE_TYPE.RADIO_MIRCHI.ordinal()) {
            if (f.v().w()) {
                f.v().B();
            }
            b();
        } else {
            ColombiaManager.b().a((com.managers.ColombiaManager.b) this);
            ColombiaManager.b().a((Context) this, playerTrack);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            long parseLong = Long.parseLong(com.services.d.a().b("PREFERENCE_KEY_FOR_REFRESH_AD_ON_GAANA_CHANGE", "0", false));
            if (this.j && Util.c() && (valueOf.longValue() >= parseLong + 30000 || parseLong == 0)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(System.currentTimeMillis());
                com.services.d.a().a("PREFERENCE_KEY_FOR_REFRESH_AD_ON_GAANA_CHANGE", stringBuilder.toString(), false);
                ColombiaAdViewManager.a().c();
            }
            this.j = true;
        }
    }

    private void af() {
        if (!ad.a(this.O).o().booleanValue()) {
            this.O.setInitialPlayTime(Calendar.getInstance().getTimeInMillis());
        }
        if (this.Z) {
            e(Q);
            return;
        }
        final PlayerTrack playerTrack = Q;
        a(playerTrack, playerTrack.i(), new ac() {
            public void a(String str) {
                if (!TextUtils.isEmpty(str)) {
                    GaanaMusicService.this.v();
                    int parseInt = Integer.parseInt(playerTrack.b().getBusinessObjId());
                    if (ap.a().d() && DownloadManager.c().e(parseInt) == DownloadStatus.DOWNLOADED) {
                        DownloadManager.c().t(parseInt);
                    }
                    GaanaMusicService.this.a(playerTrack, str, -1, false);
                } else if (playerTrack.b().isLocalMedia()) {
                    GaanaMusicService.this.ah();
                } else {
                    GaanaMusicService.this.e(playerTrack);
                }
            }
        });
    }

    private void e(final PlayerTrack playerTrack) {
        try {
            if (this.P.isHeld()) {
                this.P.release();
            }
            this.P.acquire();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        try {
            d(this.Z);
        } catch (NumberFormatException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        this.h = true;
        if (ad.a(this.O).o().booleanValue()) {
            this.h = true;
            b(playerTrack, ad.a(this.O).r(), -1, false);
            return;
        }
        String a;
        if (u) {
            a = new d(this).a(playerTrack.b());
            if (f instanceof c) {
                ((c) f).f(true);
            }
        } else {
            a = new d(this).a(playerTrack);
            if (f instanceof c) {
                ((c) f).f(false);
            }
        }
        if (!TextUtils.isEmpty(a)) {
            this.h = true;
            b(playerTrack, a, playerTrack.b().getAvAd(), false);
        } else if (u) {
            new d(this).b(playerTrack.b(), new com.i.e.b() {
                public void onDataRetrieved(Object obj, int i, boolean z) {
                    GaanaMusicService.this.h = false;
                    String str = null;
                    if (obj != null && (obj instanceof LinkedTreeMap)) {
                        String str2 = (String) ((LinkedTreeMap) obj).get("data");
                        if (!TextUtils.isEmpty(str2)) {
                            str = Util.k(str2);
                        }
                    }
                    GaanaMusicService.this.b(playerTrack, str, i, true);
                }
            });
        } else {
            a(playerTrack, new com.i.e.b() {
                public void onDataRetrieved(Object obj, int i, boolean z) {
                    GaanaMusicService.this.h = false;
                    GaanaMusicService.this.b(playerTrack, obj.toString(), i, true);
                }
            });
        }
    }

    private void a(PlayerTrack playerTrack, String str, int i, boolean z) {
        if (a(playerTrack.a(true))) {
            o.a("LISTENER_KEY_MUSIC_SERVICE", this.al);
            o.a(this.e);
            if (ad.a(this.O).o().booleanValue() && !TextUtils.isEmpty(ad.a(this.O).r())) {
                str = ad.a(this.O).r();
            }
            b(str, playerTrack, i, z);
        }
    }

    private boolean a(Track track) {
        PlayerTrack i = PlayerManager.a((Context) this).i();
        if (i != null) {
            Track b = i.b();
            if (b != null && b.getBusinessObjId().equalsIgnoreCase(track.getBusinessObjId())) {
                return true;
            }
        }
        return false;
    }

    private void ag() {
        x();
    }

    private void c(String str) {
        for (n nVar : o.d().values()) {
            if (nVar != null) {
                nVar.displayErrorToast(str, 1);
            }
        }
        this.n++;
        x();
        if (this.N.d()) {
            n(true);
        } else {
            n(false);
        }
    }

    private void ah() {
        if (!this.aq) {
            for (n nVar : o.d().values()) {
                if (nVar != null) {
                    PlayerManager.a(this.O).a(Q.b(), true, (Context) this);
                    nVar.displayErrorToast(getString(R.string.device_song_not_available), 1);
                }
            }
            this.aq = true;
        }
        this.n++;
        x();
        if (this.N.d()) {
            n(true);
        } else {
            n(false);
        }
    }

    private void ai() {
        n[] nVarArr = (n[]) o.d().values().toArray(new n[o.d().size()]);
        int i;
        if (!Util.j((Context) this) || this.N.m() == PlayerType.GAANA_RADIO) {
            for (i = 0; i < nVarArr.length; i++) {
                int i2;
                if (nVarArr[i] != null) {
                    nVarArr[i].displayErrorToast(getResources().getString(R.string.error_msg_no_connection), 1);
                    i2 = 1;
                } else {
                    i2 = false;
                }
                if (i2 > 0) {
                    m(true);
                }
            }
            return;
        }
        this.n++;
        if (!this.aq) {
            for (i = 0; i < nVarArr.length; i++) {
                if (nVarArr[i] != null) {
                    n nVar = nVarArr[i];
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Song: '");
                    stringBuilder.append(Q.b().getName());
                    stringBuilder.append("' is not available offline, moving to next available song in queue..");
                    nVar.displayErrorToast(stringBuilder.toString(), 1);
                }
            }
            this.aq = true;
        }
        x();
        if (this.N.d()) {
            n(true);
        } else {
            n(false);
        }
    }

    private String a(PlayerTrack playerTrack, com.i.e.b bVar) {
        new d(this).a(playerTrack, bVar);
        return null;
    }

    private void a(final PlayerTrack playerTrack, final boolean z, final ac acVar) {
        com.i.d.a(new Runnable() {
            public void run() {
                PlayerTrack playerTrack = playerTrack;
                if (!GaanaMusicService.this.Z) {
                    final String a = new l().a(playerTrack.a(true), z);
                    if (a == null) {
                        GaanaMusicService.this.b(Integer.parseInt(playerTrack.a(true).getBusinessObjId()));
                        if (!GaanaMusicService.this.O.isAppInOfflineMode() && Util.j(GaanaMusicService.this.O)) {
                            GaanaMusicService.this.Z = true;
                        }
                    }
                    if (GaanaMusicService.this.a(playerTrack.a(true))) {
                        if (!GaanaMusicService.this.Z) {
                            try {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    public void run() {
                                        GaanaMusicService.this.d(GaanaMusicService.this.Z);
                                    }
                                });
                            } catch (NumberFormatException e) {
                                ThrowableExtension.printStackTrace(e);
                            }
                        }
                        new Handler(GaanaMusicService.this.getMainLooper()).post(new Runnable() {
                            public void run() {
                                acVar.a(a);
                            }
                        });
                    }
                }
            }
        });
    }

    private void b(int i) {
        if (DownloadManager.c().l(i).booleanValue()) {
            for (n nVar : o.d().values()) {
                if (nVar != null) {
                    nVar.displayErrorDialog(getString(R.string.download_once_online_error), ErrorType.OTHER);
                }
            }
            DownloadManager.c().d(i, -2);
        }
    }

    private f a(boolean z, f fVar) {
        b fVar2;
        if (VideoCastManager.y().f() && !z && !(fVar2 instanceof b)) {
            fVar2 = new b();
        } else if (com.utilities.d.g()) {
            f eVar;
            if (z && (fVar2 instanceof c)) {
                eVar = new e();
                fVar2.w();
                return eVar;
            } else if (!z && (fVar2 instanceof e)) {
                eVar = new c();
                fVar2.w();
                return eVar;
            }
        }
        return fVar2;
    }

    private void b(String str, PlayerTrack playerTrack, int i, boolean z) {
        StringBuilder stringBuilder;
        int i2 = 0;
        aj = false;
        n[] nVarArr = (n[]) o.d().values().toArray(new n[o.d().size()]);
        if (str == null || str.length() == 0) {
            while (i2 < nVarArr.length) {
                if (nVarArr[i2] != null) {
                    nVarArr[i2].displayErrorDialog(getResources().getString(R.string.UnableToPlay), ErrorType.OTHER);
                }
                i2++;
            }
        } else {
            this.Y = false;
            if (!ad.a(this.O).o().booleanValue()) {
                this.O.sendUrlFetchTimeEvent(this.h);
            }
            try {
                PlayerTrack a = this.N.a(PlaySequenceType.CURRENT);
                int i3;
                if (a == null) {
                    for (i3 = 0; i3 < nVarArr.length; i3++) {
                        if (nVarArr[i3] != null) {
                            nVarArr[i3].displayErrorDialog(getResources().getString(R.string.UnableToPlay), ErrorType.OTHER);
                        }
                    }
                    return;
                }
                com.services.d.a().a("PREFERENCE_KEY_LAST_ONE_TOUCH_RADIO_TRACK_PLAYED", a.h(), true);
                if (f == null) {
                    for (i3 = 0; i3 < nVarArr.length; i3++) {
                        if (nVarArr[i3] != null) {
                            nVarArr[i3].displayErrorDialog(getResources().getString(R.string.UnableToPlay), ErrorType.OTHER);
                        }
                    }
                    return;
                }
                if (c) {
                    c = false;
                } else {
                    ak();
                }
                f = a(a.b().isLocalMedia(), f);
                f.x();
                f.d(true);
                if (this.Z) {
                    if (str.startsWith("http:") || str.startsWith("https:")) {
                        this.T = true;
                    } else {
                        str = Util.l(str);
                        Util.m("akamai");
                        this.T = false;
                    }
                } else if (f instanceof b) {
                    for (i3 = 0; i3 < nVarArr.length; i3++) {
                        if (nVarArr[i3] != null) {
                            nVarArr[i3].displayErrorDialog(getResources().getString(R.string.not_media_for_cast), ErrorType.OTHER);
                        }
                    }
                    n(true);
                    return;
                } else {
                    Util.o("");
                    Util.m("");
                }
                String str2 = str;
                comScore.onUxActive();
                if (this.W) {
                    f.a(this, str2, playerTrack, -1, z);
                } else {
                    f.a(this, str2, playerTrack, i, z);
                }
                if (w != -1) {
                    f.b(w);
                    w = -1;
                }
                v();
            } catch (IllegalArgumentException e) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Media Player-");
                stringBuilder.append(e.getMessage());
                u.a().a("StreamingFailure", stringBuilder.toString(), Util.P());
                while (i2 < nVarArr.length) {
                    if (nVarArr[i2] != null) {
                        nVarArr[i2].displayErrorDialog(getResources().getString(R.string.UnableToPlay), ErrorType.OTHER);
                    }
                    i2++;
                }
            } catch (SecurityException e2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Media Player-");
                stringBuilder.append(e2.getMessage());
                u.a().a("StreamingFailure", stringBuilder.toString(), Util.P());
                while (i2 < nVarArr.length) {
                    if (nVarArr[i2] != null) {
                        nVarArr[i2].displayErrorDialog(getResources().getString(R.string.UnableToPlay), ErrorType.OTHER);
                    }
                    i2++;
                }
            } catch (Exception e3) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Media Player-");
                stringBuilder.append(e3.getMessage());
                u.a().a("StreamingFailure", stringBuilder.toString(), Util.P());
                while (i2 < nVarArr.length) {
                    if (nVarArr[i2] != null) {
                        nVarArr[i2].displayErrorDialog(getResources().getString(R.string.UnableToPlay), ErrorType.OTHER);
                    }
                    i2++;
                }
            }
        }
    }

    private void d(String str) {
        b(str, null, -1, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:? A:{SYNTHETIC, ExcHandler: IllegalStateException (unused java.lang.IllegalStateException), Splitter:B:10:0x0020} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:34:0x00b9, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:35:0x00ba, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);
     */
    private void a(com.player_framework.PlayerConstants.PauseReasons r8) {
        /*
        r7 = this;
        com.comscore.analytics.comScore.onUxInactive();
        r0 = com.player_framework.PlayerConstants.PauseReasons.INVALID;
        if (r8 != r0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        r7.Y();
        r0 = com.player_framework.PlayerConstants.PauseReasons.AUDIO_FOCUS_LOSS;
        r1 = 1;
        if (r8 == r0) goto L_0x0019;
    L_0x0010:
        r0 = r7.ar;
        r2 = r8.toInt();
        r2 = r2 - r1;
        r0[r2] = r1;
    L_0x0019:
        r0 = com.player_framework.PlayerConstants.PauseReasons.MEDIA_BUTTON_TAP;
        if (r8 != r0) goto L_0x001f;
    L_0x001d:
        r7.aa = r1;
    L_0x001f:
        r0 = 0;
        r7.an();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2 = r7.Z;	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        if (r2 == 0) goto L_0x00a2;
    L_0x0027:
        r2 = r7.O;	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2 = com.managers.ad.a(r2);	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2 = r2.o();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2 = r2.booleanValue();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        if (r2 == 0) goto L_0x008b;
    L_0x0037:
        r2 = f;	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r2 = r2.v();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r2 = r2 / 1000;
        r3 = r7.O;	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r3 = com.managers.ad.a(r3);	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r3 = r3.p();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r3 = r3.booleanValue();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        if (r3 == 0) goto L_0x0051;
    L_0x004f:
        r2 = r0;
        goto L_0x0081;
    L_0x0051:
        r3 = Q;	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r3 = r3.b();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r3 = r3.getDuration();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r3 = r3.trim();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r3 = java.lang.Long.parseLong(r3);	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r3 = (int) r3;	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        if (r2 <= r3) goto L_0x0081;
    L_0x0066:
        r2 = Q;	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r2 = r2.b();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r2 = r2.getDuration();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r2 = r2.trim();	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r2 = java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x007a, IllegalStateException -> 0x00bd }
        r2 = (int) r2;
        goto L_0x0081;
    L_0x007a:
        r2 = move-exception;
        r3 = 30;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2 = r3;
    L_0x0081:
        r3 = com.services.d.a();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r4 = "PREFERENCE_KEY_LAST_PLAYED_DURATION";
        r3.a(r4, r2, r0);	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        goto L_0x00a2;
    L_0x008b:
        r2 = com.services.d.a();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r3 = "PREFERENCE_KEY_LAST_PLAYED_DURATION";
        r4 = f;	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r4 = r4.v();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r5 = com.utilities.Util.y();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r5 = (int) r5;	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r4 = r4 + r5;
        r4 = r4 / 1000;
        r2.a(r3, r4, r0);	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
    L_0x00a2:
        r2 = f;	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2.r();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2 = z;	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        if (r2 == 0) goto L_0x00bd;
    L_0x00ab:
        r2 = z;	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2 = r2.isPlaying();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        if (r2 == 0) goto L_0x00bd;
    L_0x00b3:
        r2 = z;	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        r2.r();	 Catch:{ IllegalStateException -> 0x00bd, Exception -> 0x00b9 }
        goto L_0x00bd;
    L_0x00b9:
        r2 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);
    L_0x00bd:
        r2 = f;
        if (r2 == 0) goto L_0x00c6;
    L_0x00c1:
        r2 = f;
        r2.b(r1);
    L_0x00c6:
        r1 = com.player_framework.PlayerStatus.PlayerStates.PAUSED;
        com.player_framework.PlayerStatus.a(r7, r1);
        r1 = r7.P;	 Catch:{ Exception -> 0x00d9 }
        r1 = r1.isHeld();	 Catch:{ Exception -> 0x00d9 }
        if (r1 == 0) goto L_0x00dd;
    L_0x00d3:
        r1 = r7.P;	 Catch:{ Exception -> 0x00d9 }
        r1.release();	 Catch:{ Exception -> 0x00d9 }
        goto L_0x00dd;
    L_0x00d9:
        r1 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);
    L_0x00dd:
        r1 = com.utilities.d.a();
        if (r1 == 0) goto L_0x0116;
    L_0x00e3:
        r1 = android.os.Build.VERSION.RELEASE;
        if (r1 == 0) goto L_0x00fb;
    L_0x00e7:
        r2 = "4.0.3";
        r2 = r1.equalsIgnoreCase(r2);
        if (r2 != 0) goto L_0x00f7;
    L_0x00ef:
        r2 = "4.0.4";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00fb;
    L_0x00f7:
        r7.x();
        goto L_0x0119;
    L_0x00fb:
        r7.v();
        r1 = com.player_framework.PlayerConstants.PauseReasons.MEDIA_BUTTON_TAP;
        if (r8 != r1) goto L_0x0119;
    L_0x0102:
        r8 = new android.os.Handler;
        r1 = r7.getMainLooper();
        r8.<init>(r1);
        r1 = new com.player_framework.GaanaMusicService$13;
        r1.<init>();
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r8.postDelayed(r1, r2);
        goto L_0x0119;
    L_0x0116:
        r7.x();
    L_0x0119:
        r8 = com.player_framework.o.d();
        r8 = r8.values();
        r8 = r8.iterator();
    L_0x0125:
        r1 = r8.hasNext();
        if (r1 == 0) goto L_0x013b;
    L_0x012b:
        r1 = r8.next();
        r1 = (com.player_framework.n) r1;
        if (r1 == 0) goto L_0x0125;
    L_0x0133:
        r2 = Q;
        if (r2 == 0) goto L_0x0125;
    L_0x0137:
        r1.onPlayerPause();
        goto L_0x0125;
    L_0x013b:
        r8 = g;
        if (r8 == 0) goto L_0x0146;
    L_0x013f:
        r8 = com.player_framework.PlayerStatus.PlayerStates.STOPPED;
        com.player_framework.PlayerStatus.a(r7, r8);
        g = r0;
    L_0x0146:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.player_framework.GaanaMusicService.a(com.player_framework.PlayerConstants$PauseReasons):void");
    }

    /* JADX WARNING: Missing block: B:21:0x0040, code skipped:
            return;
     */
    private void b(com.player_framework.PlayerConstants.PauseReasons r3) {
        /*
        r2 = this;
        com.comscore.analytics.comScore.onUxActive();
        r0 = com.player_framework.PlayerConstants.PauseReasons.INVALID;
        if (r3 == r0) goto L_0x0040;
    L_0x0007:
        r0 = s();
        r0 = r0.isPlaying();
        if (r0 != 0) goto L_0x0040;
    L_0x0011:
        r0 = com.player_framework.PlayerConstants.PauseReasons.AUDIO_FOCUS_LOSS;
        if (r3 != r0) goto L_0x0016;
    L_0x0015:
        goto L_0x0040;
    L_0x0016:
        r0 = f;
        if (r0 != 0) goto L_0x001b;
    L_0x001a:
        return;
    L_0x001b:
        r0 = r2.aa;
        if (r0 == 0) goto L_0x0022;
    L_0x001f:
        r2.X();
    L_0x0022:
        r0 = 0;
        r2.aa = r0;
        r1 = r2.ar;
        r3 = r3.toInt();
        r3 = r3 + -1;
        r1[r3] = r0;
    L_0x002f:
        r3 = 2;
        if (r0 >= r3) goto L_0x003c;
    L_0x0032:
        r3 = r2.ar;
        r3 = r3[r0];
        if (r3 == 0) goto L_0x0039;
    L_0x0038:
        return;
    L_0x0039:
        r0 = r0 + 1;
        goto L_0x002f;
    L_0x003c:
        r2.ad();
        return;
    L_0x0040:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.player_framework.GaanaMusicService.b(com.player_framework.PlayerConstants$PauseReasons):void");
    }

    private void m(boolean z) {
        ((AudioManager) getSystemService("audio")).abandonAudioFocus(this.y);
        comScore.onUxInactive();
        f.v().z();
        this.k = z;
        if (this.ad != null) {
            this.ad.cancel();
            this.ad = null;
        }
        if (this.r != null && this.r.c()) {
            this.r.b();
            this.r = null;
        }
        if (this.ac != null) {
            this.ac.cancel();
        }
        try {
            if (f != null && f.isPlaying()) {
                if (!this.Z) {
                    ac();
                } else if (ad.a(this.O).o().booleanValue()) {
                    int v;
                    try {
                        v = f.v() / 1000;
                        if (ad.a(this.O).p().booleanValue()) {
                            v = 0;
                        } else if (v > ((int) Long.parseLong(Q.b().getDuration().trim()))) {
                            v = (int) Long.parseLong(Q.b().getDuration().trim());
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                        v = 30;
                    }
                    com.services.d.a().a("PREFERENCE_KEY_LAST_PLAYED_DURATION", v, false);
                } else {
                    com.services.d.a().a("PREFERENCE_KEY_LAST_PLAYED_DURATION", (f.v() + ((int) Util.y())) / 1000, false);
                }
                f.p();
                if (z != null && z.isPlaying()) {
                    z.p();
                }
            }
            H = false;
            Util.z();
            Util.A();
        } catch (IllegalStateException unused) {
        }
        if (f != null) {
            f.c(false);
            f.b(true);
            try {
                f.y();
                f.w();
                f = null;
            } catch (IllegalStateException unused2) {
            }
        }
        if (z != null) {
            z.c(false);
            z.b(false);
            try {
                z.y();
                z.w();
                z = null;
            } catch (IllegalStateException unused3) {
            }
        }
        this.O.setPlayerStatus(false);
        PlayerStatus.a(this, PlayerStates.STOPPED);
        x();
        this.G.a();
        try {
            if (this.P.isHeld()) {
                this.P.release();
            }
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        for (n nVar : o.d().values()) {
            if (nVar != null && z) {
                nVar.onPlayerStop();
            }
        }
        o.d("LISTENER_KEY_MUSIC_SERVICE");
        stopSelf();
    }

    private void c(int i) {
        d(i);
        try {
            f.b(i);
            for (n nVar : o.d().values()) {
                if (nVar instanceof com.player_framework.n.b) {
                    ((com.player_framework.n.b) nVar).seekTo(i);
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void d(int i) {
        if (D && z != null && z.isPlaying()) {
            int u = f.u() - i;
            if (u <= E) {
                z.b(u);
                return;
            }
            z.p();
            try {
                z.w();
                z = null;
            } catch (IllegalStateException unused) {
            }
            a(f, 1.0f, 1.0f);
        }
    }

    private void n(boolean z) {
        GaanaLogger.a().a(this, z, this.m);
        if (Constants.cY || this.N.n() == null || this.N.n().size() <= 0 || this.N.n().size() > this.n) {
            this.V = true;
            if (f == null) {
                f = m();
            }
            if (this.N.n() != null && this.N.n().size() != 0) {
                if (Constants.cY && o.a() != null) {
                    o.a().onPlayNext(z, false);
                    return;
                } else if (this.N.t() && this.N.v() && this.N.m() != PlayerType.GAANA_RADIO) {
                    a(z, PlayerCommands.PLAY_NEXT);
                    return;
                } else {
                    n[] nVarArr = (n[]) o.d().values().toArray(new n[o.d().size()]);
                    PlayerTrack a;
                    int i;
                    if (this.N.m() == PlayerType.GAANA_RADIO) {
                        if (this.N.t()) {
                            if (this.N.m() != PlayerType.GAANA_RADIO || this.N.s() == 0) {
                                this.N.f(true);
                                a(z, PlayerCommands.PLAY_NEXT);
                            } else {
                                ad.a(this.O).e();
                            }
                        } else if (this.N.f()) {
                            if (this.N.h()) {
                                a = this.N.a(PlaySequenceType.NEXT);
                            } else {
                                this.N.k();
                                a = this.N.a(PlaySequenceType.CURRENT);
                            }
                            if (a != null) {
                                a.c(z ^ 1);
                                Y();
                                if (z == null || TextUtils.isEmpty(z.s()) || z.t() <= 0) {
                                    if (z != null) {
                                        if (z.isPlaying()) {
                                            z.p();
                                        }
                                        try {
                                            z.w();
                                            z = null;
                                        } catch (IllegalStateException unused) {
                                        }
                                    }
                                    for (i = 0; i < nVarArr.length; i++) {
                                        if (nVarArr[i] != null) {
                                            nVarArr[i].onPlayNext(z, false);
                                        }
                                    }
                                    this.N.f(true);
                                    f.b(false);
                                    o.a((Context) this);
                                } else {
                                    l(z);
                                    U();
                                }
                            } else {
                                this.N.f(true);
                                a(z, PlayerCommands.PLAY_NEXT);
                            }
                        } else {
                            for (int i2 = 0; i2 < nVarArr.length; i2++) {
                                boolean z2;
                                if (nVarArr[i2] != null) {
                                    nVarArr[i2].onPlayNext(z, false);
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (!z2) {
                                    this.as = true;
                                    this.N.a((ak) this);
                                }
                            }
                        }
                        a = null;
                    } else {
                        if (z || !this.N.d()) {
                            a = this.N.a(PlaySequenceType.NEXT);
                            i = 0;
                        } else {
                            a = this.N.a(PlaySequenceType.CURRENT);
                            i = 1;
                        }
                        if (a != null) {
                            a.c(z ^ 1);
                            Y();
                            if (this.N.d() || R == null || !a.h().equalsIgnoreCase(R.h()) || z == null || TextUtils.isEmpty(z.s()) || z.t() <= 0) {
                                Y();
                                if (z != null) {
                                    if (z.isPlaying()) {
                                        z.p();
                                    }
                                    try {
                                        z.w();
                                        z = null;
                                    } catch (IllegalStateException unused2) {
                                    }
                                }
                                for (int i3 = 0; i3 < nVarArr.length; i3++) {
                                    if (nVarArr[i3] != null) {
                                        nVarArr[i3].onPlayNext(z, false);
                                        nVarArr[i3].onPlayerRepeatReset(i ^ 1);
                                    }
                                }
                                f.b(false);
                                if (t()) {
                                    a(f, 1.0f, 1.0f);
                                }
                                if (a.a(true) != null) {
                                    o.a((Context) this);
                                } else {
                                    o.f((Context) this);
                                }
                            } else {
                                l(z);
                                U();
                            }
                        } else {
                            a(z, PlayerCommands.PLAY_NEXT);
                        }
                    }
                    if (Constants.aa && z && r3 != null && !r3.b().isLocalMedia()) {
                        Constants.h--;
                    }
                    return;
                }
            }
            return;
        }
        for (n nVar : o.d().values()) {
            if (nVar != null) {
                nVar.onPlayerStop();
            }
        }
        this.n = 0;
        m(false);
    }

    private void o(boolean z) {
        try {
            long toSeconds = TimeUnit.MILLISECONDS.toSeconds((long) f.v());
            if (!(f instanceof b) && toSeconds >= 3 && f != null && toSeconds < ((long) f.u())) {
                if (Q == null) {
                    Q = this.N.a(PlaySequenceType.CURRENT);
                }
                ad();
                return;
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        if (Constants.cY && o.a() != null) {
            o.a().onPlayPrevious(z, false);
        } else if (this.N.s() == 0 && this.N.v()) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
        } else {
            PlayerTrack a = this.N.a(PlaySequenceType.PREV);
            if (a != null) {
                for (n nVar : o.d().values()) {
                    if (nVar != null) {
                        nVar.onPlayPrevious(z, false);
                    }
                }
                f.b(false);
                if (a == null || a.a(true) == null) {
                    o.e((Context) this);
                } else {
                    o.a((Context) this);
                }
            } else {
                a(z, PlayerCommands.PLAY_PREVIOUS);
            }
            if (Constants.aa && z && a != null && !a.b().isLocalMedia()) {
                Constants.h--;
            }
        }
    }

    private void e(int i) {
        int i2 = 0;
        c = i > 0;
        if (f == null) {
            f = m();
        }
        n[] nVarArr = (n[]) o.d().values().toArray(new n[o.d().size()]);
        PlayerTrack i3 = this.N.i();
        if (this.N.m() == PlayerType.GAANA_RADIO) {
            if (i3 != null) {
                Y();
                U();
                if (z != null) {
                    if (z.isPlaying()) {
                        z.p();
                    }
                    try {
                        z.w();
                        z = null;
                    } catch (IllegalStateException unused) {
                    }
                }
                for (int i4 = 0; i4 < nVarArr.length; i4++) {
                    if (nVarArr[i4] != null) {
                        nVarArr[i4].onStreamingQualityChanged(i);
                    }
                }
                this.N.f(true);
                f.b(false);
                o.a((Context) this);
            }
        } else if (i3 != null) {
            Y();
            U();
            if (z != null) {
                if (z.isPlaying()) {
                    z.p();
                }
                try {
                    z.w();
                    z = null;
                } catch (IllegalStateException unused2) {
                }
            }
            f.b(false);
            if (t()) {
                a(f, 1.0f, 1.0f);
            }
            o.a((Context) this);
            while (i2 < nVarArr.length) {
                if (nVarArr[i2] != null) {
                    nVarArr[i2].onStreamingQualityChanged(i);
                }
                i2++;
            }
        }
    }

    private void aj() {
        if (Q != null && Q.b() != null && f != null && f.isPlaying() && Q.b().getVerticalUrl() != null && !u && this.Z && b(Q) && !this.b.t()) {
            c = true;
            this.b.c(true);
            w = f.v();
            if (f == null) {
                f = m();
            }
            n[] nVarArr = (n[]) o.d().values().toArray(new n[o.d().size()]);
            PlayerTrack i = this.N.i();
            if (this.N.m() == PlayerType.GAANA_RADIO) {
                if (i != null) {
                    Y();
                    U();
                    if (z != null) {
                        if (z.isPlaying()) {
                            z.p();
                        }
                        try {
                            z.w();
                            z = null;
                        } catch (IllegalStateException unused) {
                        }
                    }
                    this.N.f(true);
                    f.b(false);
                    o.a((Context) this);
                }
            } else if (i != null) {
                Y();
                U();
                if (z != null) {
                    if (z.isPlaying()) {
                        z.p();
                    }
                    try {
                        z.w();
                        z = null;
                    } catch (IllegalStateException unused2) {
                    }
                }
                f.b(false);
                if (t()) {
                    a(f, 1.0f, 1.0f);
                }
                o.a((Context) this);
            }
        }
    }

    private void a(PlayerTrack playerTrack, String str) {
        PlayerTrack i = this.N.i();
        if (playerTrack.b().getBusinessObjId().equals(i.h()) || !this.N.z()) {
            this.N.h(false);
        } else {
            this.N.h(true);
        }
        if (!playerTrack.b().isLocalMedia && !i.b().isLocalMedia) {
            String valueOf = String.valueOf(s().v());
            String h = playerTrack.h();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i.e());
            stringBuilder.append("##");
            stringBuilder.append(i.c());
            an.a().b("playout", "ac", h, stringBuilder.toString(), i.h(), "play", str, valueOf);
        }
    }

    private void ak() {
        if (GaanaLogger.a().b() != null) {
            try {
                if (s().v() / 1000 >= 30) {
                    MoEngage.getInstance().reportItemPlayed(this.an, this.ao, this.ap);
                }
                M();
                TrackLog b = GaanaLogger.a().b();
                b.i(String.valueOf(s().v()));
                String b2 = com.services.d.a().b("PREFERENCE_KEY_LAST_TRACK_PLAYOUT_SOURCE", String.valueOf(PLAYOUT_SOURCE.NETWORK.ordinal()), false);
                if (TextUtils.isEmpty(b2)) {
                    b2 = String.valueOf(PLAYOUT_SOURCE.NETWORK.ordinal());
                }
                b.h(b2);
                if (!H) {
                    if (this.Z) {
                        GaanaLogger.a().b().i("0");
                        Util.z();
                    }
                    if (com.utilities.d.b()) {
                        PlayerTrack w = this.N.w();
                        if (!(w == null || w.h().equalsIgnoreCase(Q.h()))) {
                            String h = w.h();
                            if (!TextUtils.isEmpty(h) && DownloadManager.c().l(Integer.parseInt(h)).booleanValue()) {
                                DownloadManager.c().k(h);
                            }
                        }
                    }
                } else if (!this.Z) {
                    com.services.d.a().a("PREFERENCE_KEY_LAST_PLAYED_DURATION", (f.v() + ((int) Util.y())) / 1000, false);
                    Util.z();
                }
                if (this.Z) {
                    GaanaLogger.a().c().a(this);
                } else {
                    ac();
                }
                H = this.Z;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        n[] nVarArr = (n[]) o.d().values().toArray(new n[o.d().size()]);
        int i = 0;
        switch (playerCommands) {
            case PLAY_PREVIOUS:
                break;
            case PLAY_NEXT:
                if (!z) {
                    f.c(false);
                    PlayerStatus.a(this, PlayerStates.STOPPED);
                    d(this.Z);
                    if (GaanaLogger.a().b() != null) {
                        if (GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j(GaanaApplication.getContext())) {
                            GaanaLogger.a().a(com.logging.b.a());
                        }
                        TrackLog b = GaanaLogger.a().b();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("");
                        stringBuilder.append(s().u());
                        b.i(stringBuilder.toString());
                        GaanaLogger.a().c().a(GaanaApplication.getContext());
                    }
                    if (this.Z) {
                        com.services.d.a().a("PREFERENCE_KEY_LAST_PLAYED_DURATION", (f.v() + ((int) Util.y())) / 1000, false);
                    }
                    m(false);
                }
                while (i < nVarArr.length) {
                    if (nVarArr[i] != null) {
                        nVarArr[i].onPlayNext(z, true);
                    }
                    i++;
                }
                return;
            default:
                return;
        }
        while (i < nVarArr.length) {
            if (nVarArr[i] != null) {
                nVarArr[i].onPlayPrevious(z, true);
            }
            i++;
        }
    }

    public void a(Boolean bool) {
        if (this.as) {
            this.as = false;
            this.N.k();
            this.N.f(true);
            o.a((Context) this);
        }
    }

    private boolean al() {
        int requestAudioFocus;
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (com.utilities.d.d()) {
            requestAudioFocus = audioManager.requestAudioFocus(new Builder(1).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).build()).setOnAudioFocusChangeListener(this.y).build());
        } else {
            requestAudioFocus = audioManager.requestAudioFocus(this.y, 3, 1);
        }
        if (requestAudioFocus == 0) {
            for (n nVar : o.d().values()) {
                if (nVar != null) {
                    nVar.displayErrorToast(getString(R.string.error_ongoing_call_during_music_play), 1);
                }
            }
            m(true);
            return false;
        }
        com.gaanavideo.e.a();
        return true;
    }

    private void a(f fVar, float f, float f2) {
        try {
            fVar.setVolume(f, f2);
        } catch (IllegalStateException unused) {
        }
    }

    public void d(boolean z) {
        if (f == null) {
            f = m();
        }
        TrackLog trackLog = new TrackLog();
        if (Q != null && Q.b() != null) {
            Track b = Q.b();
            trackLog.f(Q.c());
            trackLog.d(String.valueOf(Q.e()));
            trackLog.a(b.isLocalMedia());
            trackLog.j(b.getName());
            trackLog.k(b.getLanguage());
            trackLog.e(b.getAlbumTitle());
            trackLog.a(b);
            trackLog.m(Q.g());
            trackLog.l(Q.j());
            trackLog.n(Q.l() ? "1" : "0");
            trackLog.c(Q.d());
            trackLog.h(String.valueOf(PLAYOUT_SOURCE.NETWORK.ordinal()));
            if (u) {
                trackLog.g(String.valueOf(CONTENT_TYPE.VIDEO_TRACK.ordinal()));
            } else if (!TextUtils.isEmpty(Q.b().getClipVideoUrl()) && Util.c() && Constants.cH && GaanaApplication.getInstance().isVideoAutoplay()) {
                trackLog.g(String.valueOf(CONTENT_TYPE.AUDIO_VIDEO_CLIP.ordinal()));
            } else {
                trackLog.g(String.valueOf(CONTENT_TYPE.AUDIO_TRACK.ordinal()));
            }
            try {
                trackLog.i(String.valueOf(f.u()));
                trackLog.b(Q.h());
                trackLog.a(b.getDuration());
                trackLog.a(System.currentTimeMillis());
            } catch (IllegalStateException unused) {
                trackLog.i(String.valueOf(Double.parseDouble(b.getDuration()) * 1000.0d));
                trackLog.b(Q.h());
                trackLog.a(b.getDuration());
                trackLog.a(System.currentTimeMillis());
            }
            GaanaLogger.a().a(trackLog, this);
            if (z) {
                GaanaLogger.a().a(com.logging.c.a());
            } else {
                GaanaLogger.a().a(com.logging.b.a());
            }
        }
    }

    public static boolean y() {
        return u;
    }

    private void b(PlayerTrack playerTrack, String str, int i, boolean z) {
        a(playerTrack, str, i, z);
    }

    public void a() {
        a(PauseReasons.MEDIA_BUTTON_TAP);
        ((AudioManager) getSystemService("audio")).abandonAudioFocus(this.y);
    }

    public void b() {
        if (al()) {
            an();
            if (this.V) {
                am();
                ae();
            } else {
                n[] nVarArr = (n[]) o.d().values().toArray(new n[o.d().size()]);
                aa();
                a(nVarArr, this.X);
            }
        }
    }

    private void am() {
        PlayerTrack a = this.N.a();
        PlayerTrack i = this.N.i();
        if (a == null || i == null || !a.h().equalsIgnoreCase(i.h())) {
            int b;
            if (f == null || f.v() == 0) {
                b = com.services.d.a().b("PREFERENCE_KEY_LAST_PLAYED_DURATION", 0, false) * 1000;
            } else {
                b = f.v();
            }
            if (a != null && a.e() == SOURCE_TYPE.CF_TRACK.ordinal() && b < Constants.cK) {
                u.a().a("CF TRACK", "Track Skipped", a.h());
            }
            this.N.a(i);
        }
    }

    private void an() {
        this.N.a();
        PlayerTrack i = this.N.i();
        if (this.N.a() == null) {
            a(i, String.valueOf(s().u()));
        } else {
            a(this.N.a(), String.valueOf(s().u()));
        }
    }

    public void a(Ad ad) {
        Bitmap p = this.b.p();
        this.af = ad.getTitle();
        if (p != null) {
            this.M.a("", "Sponsored Ad", ad.getTitle(), 1234, p);
        } else {
            this.M.a("", "Sponsored Ad", ad.getTitle(), 1234, BitmapFactory.decodeResource(getResources(), Util.w()));
        }
        this.G = new g();
        this.G.a(GaanaApplication.getContext(), ad.isSkippable());
    }

    public void z() {
        if (!TextUtils.isEmpty(this.af)) {
            f.v().g(false);
            Bitmap p = this.b.p();
            if (p != null) {
                this.M.a("", "Sponsored Ad", this.af, 1234, p);
            } else {
                this.M.a("", "Sponsored Ad", this.af, 1234, BitmapFactory.decodeResource(getResources(), Util.w()));
            }
            this.G = new g();
            this.G.a(GaanaApplication.getContext(), false);
        }
    }

    public void c() {
        if (Q != null) {
            i.a().b(Q.a(true).getArtworkLarge(), new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    if (bitmap != null) {
                        String language = GaanaApplication.getLanguage(GaanaMusicService.this.O);
                        if (TextUtils.isEmpty(language) || !language.equalsIgnoreCase("hindi")) {
                            GaanaMusicService.this.M.b(GaanaMusicService.Q.a(true).getEnglishAlbumTitle(), GaanaMusicService.Q.a(true).getEnglishArtistNames(), GaanaMusicService.Q.a(true).getEnglishName(), 1234, bitmap);
                            return;
                        } else {
                            GaanaMusicService.this.M.b(GaanaMusicService.Q.a(true).getAlbumTitle(), GaanaMusicService.Q.a(true).getArtistNames(), GaanaMusicService.Q.a(true).getTrackTitle(), 1234, bitmap);
                            return;
                        }
                    }
                    i.a().a(GaanaMusicService.Q.a(true).getArtwork(), new r() {
                        public void onErrorResponse(VolleyError volleyError) {
                        }

                        public void onSuccessfulResponse(Bitmap bitmap) {
                            if (bitmap != null) {
                                String language = GaanaApplication.getLanguage(GaanaMusicService.this.O);
                                if (TextUtils.isEmpty(language) || !language.equalsIgnoreCase("hindi")) {
                                    GaanaMusicService.this.M.b(GaanaMusicService.Q.a(true).getEnglishAlbumTitle(), GaanaMusicService.Q.a(true).getEnglishArtistNames(), GaanaMusicService.Q.a(true).getEnglishName(), 1234, bitmap);
                                    return;
                                }
                                GaanaMusicService.this.M.b(GaanaMusicService.Q.a(true).getAlbumTitle(), GaanaMusicService.Q.a(true).getArtistNames(), GaanaMusicService.Q.a(true).getTrackTitle(), 1234, bitmap);
                            }
                        }
                    });
                }
            });
        }
    }

    public void d() {
        if (Q != null) {
            i.a().b(Q.a(true).getArtworkLarge(), new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    if (bitmap != null) {
                        GaanaMusicService.this.M.a(GaanaMusicService.Q.a(true).getAlbumTitle(), GaanaMusicService.Q.a(true).getArtistNames(), GaanaMusicService.Q.a(true).getTrackTitle(), 1234, bitmap);
                        GaanaMusicService.this.G = new g();
                        GaanaMusicService.this.G.b(GaanaApplication.getContext(), GaanaMusicService.Q.a(true));
                        return;
                    }
                    i.a().a(GaanaMusicService.Q.a(true).getArtwork(), new r() {
                        public void onErrorResponse(VolleyError volleyError) {
                        }

                        public void onSuccessfulResponse(Bitmap bitmap) {
                            if (bitmap != null) {
                                GaanaMusicService.this.M.a(GaanaMusicService.Q.a(true).getAlbumTitle(), GaanaMusicService.Q.a(true).getArtistNames(), GaanaMusicService.Q.a(true).getTrackTitle(), 1234, bitmap);
                                GaanaMusicService.this.G = new g();
                                GaanaMusicService.this.G.b(GaanaApplication.getContext(), GaanaMusicService.Q.a(true));
                            }
                        }
                    });
                }
            });
        }
    }

    public void e() {
        Bitmap p = this.b.p();
        if (p != null) {
            this.M.b("", "Sponsored Ad", this.b.r(), 1234, p);
        } else {
            this.M.b("", "Sponsored Ad", this.b.r(), 1234, BitmapFactory.decodeResource(getResources(), Util.w()));
        }
        this.G = new g();
        this.G.a(GaanaApplication.getContext(), f.a);
    }

    public void a(boolean z) {
        if (z) {
            i();
        }
        this.W = z;
    }

    public static void a(SimpleExoPlayerView simpleExoPlayerView, ay ayVar) {
        if (!aj) {
            ag = simpleExoPlayerView;
            ah = ayVar;
        } else if (f instanceof c) {
            if (ayVar != null && u && f.s() != null && f.s().equals(ak)) {
                ayVar.videoStateChanged(1);
            }
            ((c) f).a(simpleExoPlayerView);
        }
    }
}
