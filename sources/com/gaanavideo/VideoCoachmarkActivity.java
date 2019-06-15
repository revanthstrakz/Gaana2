package com.gaanavideo;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.SearchEnchancedFragment;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.firebase.appindexing.Indexable;
import com.managers.GaanaSearchManager;
import com.managers.p;
import com.managers.u;
import com.services.d;
import com.utilities.Util;
import com.views.RateTextCircularProgressBar;

public class VideoCoachmarkActivity extends AppCompatActivity {
    public static String a;
    public CountDownTimer b;
    private String c = "VideoCoachmarkFragment";
    private TextView d;
    private TextView e;
    private MutedVideoView f;
    private LayoutInflater g;
    private View h;
    private long i;
    private long j;
    private String k = "COACHMARK_SLIDE_LEFT";
    private String l = "COACHMARK_HOLD_DRAG";
    private String m = "COACHMARK_LONG_PRESS";
    private String n = "COACHMARK_CHROME_CAST";
    private String o = "COACHMARK_VOICE_SEARCH";
    private String p = "COACHMARK_VOICE_HOME";
    private String q = "COACHMARK_PLAYER_HOME";
    private String r = "COACHMARK_DOWNLOAD_TRACK";
    private String s = "COACHMARK_DOWNLOAD_PLAYER_TRACK";
    private String t = "COACHMARK_DOWNLOADED_TRACKS";
    private String u = "COACHMARK_PLAYER_VIEW_PAGER";
    private String v = "MINI_PLAYER_OVERLAY";
    private String w = "PRESCREEN_COACHMARK";
    private RateTextCircularProgressBar x;
    private final int y = Indexable.MAX_STRING_LENGTH;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Constants.l) {
            setTheme(R.style.CoachMarkThemeWhite);
        }
        String string = getIntent().getExtras().getString("COACHMARK_VALUE");
        this.g = LayoutInflater.from(this);
        this.h = this.g.inflate(R.layout.activity_coachmark_videoscreen, null);
        this.f = (MutedVideoView) this.h.findViewById(R.id.full_screen_video_player);
        this.e = (TextView) this.h.findViewById(R.id.coachmarkTxt);
        this.d = (TextView) this.h.findViewById(R.id.gotItTxt);
        this.x = (RateTextCircularProgressBar) this.h.findViewById(R.id.rate_progress_bar);
        b();
        this.i = System.currentTimeMillis();
        int i;
        TextView textView;
        p a;
        int parseInt;
        if (string.equalsIgnoreCase("SWIPE_LEFT_TO_REMOVE_TRACK")) {
            i = Constants.l ? R.raw.slide_left_white : R.raw.slide_left;
            this.e.setText(R.string.slide_left_remove);
            a = this.k;
            a(i);
        } else if (string.equalsIgnoreCase("MOVE_TRACK_UP_DOWN")) {
            i = Constants.l ? R.raw.hold_drag_white : R.raw.hold_drag;
            this.e.setText(R.string.hold_drag_queue);
            a = this.l;
            a(i);
        } else if (string.equalsIgnoreCase("LONG_PRESS_ON_TRACK")) {
            i = Constants.l ? R.raw.long_press_white : R.raw.long_press;
            this.e.setText(R.string.long_press_track);
            a = this.m;
            a(i);
        } else if (string.equalsIgnoreCase("HOME_CAST_FIRST_TIME")) {
            this.h = this.g.inflate(R.layout.coachmark_cast, null);
            this.e = (TextView) this.h.findViewById(R.id.castTxt);
            this.d = (TextView) this.h.findViewById(R.id.gotItTxt);
            a = this.n;
            this.x = (RateTextCircularProgressBar) this.h.findViewById(R.id.rate_progress_bar);
            b();
            d();
            i = Constants.cb ? (int) TypedValue.applyDimension(1, (float) 60, getResources().getDisplayMetrics()) : Constants.cc ? (int) TypedValue.applyDimension(1, (float) 60, getResources().getDisplayMetrics()) : 0;
            if (Constants.ce == 1) {
                i = (int) TypedValue.applyDimension(1, (float) 30, getResources().getDisplayMetrics());
            } else if (Constants.ce == 2) {
                i = (int) TypedValue.applyDimension(1, (float) 30, getResources().getDisplayMetrics());
            } else if (Constants.ce == 3) {
                i = (int) TypedValue.applyDimension(1, (float) 90, getResources().getDisplayMetrics());
            } else if (Constants.ce == 4) {
                i = (int) TypedValue.applyDimension(1, (float) 120, getResources().getDisplayMetrics());
            }
            if (this.e != null) {
                this.e.setPadding(0, 0, i, 0);
            }
        } else if (string.equalsIgnoreCase("VOICE_SEARCH_FIRST_TIME")) {
            this.h = this.g.inflate(R.layout.voice_search_coachmarks, null);
            a = this.o;
            ImageView imageView = (ImageView) this.h.findViewById(R.id.voice_icon);
            if (Constants.l) {
                imageView.setImageResource(R.drawable.vector_icon_voice_red);
            } else {
                imageView.setImageResource(R.drawable.vector_icon_voice_white);
            }
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                    if (GaanaSearchManager.a().g() instanceof SearchEnchancedFragment) {
                        VideoCoachmarkActivity.this.getSharedPreferences("VOICE_SEARCH_FIRST_TIME", 0).edit().putBoolean("VOICE_SEARCH_FIRST_TIME", false).apply();
                        ((SearchEnchancedFragment) GaanaSearchManager.a().g()).a().b();
                    }
                }
            });
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.getSharedPreferences("VOICE_SEARCH_FIRST_TIME", 0).edit().putBoolean("VOICE_SEARCH_FIRST_TIME", false).apply();
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
        } else if (string.equalsIgnoreCase("VOICE_SEARCH_HOME")) {
            this.h = this.g.inflate(R.layout.voice_search_home, null);
            a = this.p;
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.getSharedPreferences("VOICE_SEARCH_HOME", 0).edit().putBoolean("VOICE_SEARCH_HOME", false).apply();
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
            ((ImageView) this.h.findViewById(R.id.search)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("search", 1);
                    VideoCoachmarkActivity.this.setResult(-1, intent);
                    VideoCoachmarkActivity.this.finish();
                }
            });
            ((ImageView) this.h.findViewById(R.id.home)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("home", 0);
                    VideoCoachmarkActivity.this.setResult(-1, intent);
                    VideoCoachmarkActivity.this.finish();
                }
            });
            ((ImageView) this.h.findViewById(R.id.radio)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("radio", 2);
                    VideoCoachmarkActivity.this.setResult(-1, intent);
                    VideoCoachmarkActivity.this.finish();
                }
            });
            ((ImageView) this.h.findViewById(R.id.my_music)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("my_music", 3);
                    VideoCoachmarkActivity.this.setResult(-1, intent);
                    VideoCoachmarkActivity.this.finish();
                }
            });
        } else if (string.equalsIgnoreCase("PLAYER_SWIPE_COACHMARK_FIRSTTIME")) {
            this.h = this.g.inflate(R.layout.player_coachmark, null);
            a = this.q;
            textView = (TextView) this.h.findViewById(R.id.player_coachmark_text);
            a = p.a();
            GaanaApplication.getInstance();
            textView.setTypeface(a.a(GaanaApplication.getLanguage(this)));
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.getSharedPreferences("PLAYER_SWIPE_COACHMARK_FIRSTTIME", 0).edit().putBoolean("PLAYER_SWIPE_COACHMARK_FIRSTTIME", false).apply();
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
        } else if (string.equalsIgnoreCase("DOWNLOAD_TRACK_COACHMARK")) {
            this.h = this.g.inflate(R.layout.download_coachmark, null);
            a = this.r;
            string = getIntent().getExtras().getString("TargetCoords");
            parseInt = Integer.parseInt(string.split(",")[0]);
            i = Integer.parseInt(string.split(",")[1]);
            View findViewById = this.h.findViewById(R.id.download_track);
            findViewById.setVisibility(0);
            findViewById.setX((float) (parseInt - Util.a(getApplicationContext(), 175)));
            findViewById.setY((float) (i - Util.a(getApplicationContext(), 22)));
            textView = (TextView) this.h.findViewById(R.id.player_coachmark_text);
            a = p.a();
            GaanaApplication.getInstance();
            textView.setTypeface(a.a(GaanaApplication.getLanguage(this)));
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.getSharedPreferences("DOWNLOAD_TRACK_COACHMARK", 0).edit().putBoolean("DOWNLOAD_TRACK_COACHMARK", false).apply();
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
        } else if (string.equalsIgnoreCase("DOWNLOADED_TRACKS_COACHMARK")) {
            this.h = this.g.inflate(R.layout.download_coachmark, null);
            a = this.t;
            this.h.findViewById(R.id.downloaded_songs).setVisibility(0);
            textView = (TextView) this.h.findViewById(R.id.player_coachmark_text);
            a = p.a();
            GaanaApplication.getInstance();
            textView.setTypeface(a.a(GaanaApplication.getLanguage(this)));
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.getSharedPreferences("DOWNLOADED_TRACKS_COACHMARK", 0).edit().putBoolean("DOWNLOADED_TRACKS_COACHMARK", false).apply();
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                    d.a().a("DOWNLOADED_TRACKS_COACHMARK", false, false);
                }
            });
        } else if (string.equalsIgnoreCase("DOWNLOAD_PLAYER_TRACK_COACHMARK")) {
            this.h = this.g.inflate(R.layout.download_coachmark, null);
            a = this.s;
            ViewGroup viewGroup = (ViewGroup) this.h.findViewById(R.id.download_track);
            String string2 = getIntent().getExtras().getString("TargetCoords");
            int parseInt2 = Integer.parseInt(string2.split(",")[0]);
            parseInt = Integer.parseInt(string2.split(",")[1]);
            viewGroup.setVisibility(0);
            viewGroup.setX((float) (parseInt2 - Util.a(getApplicationContext(), 184)));
            viewGroup.setY((float) (parseInt - Util.a(getApplicationContext(), 30)));
            textView = (TextView) this.h.findViewById(R.id.player_coachmark_text);
            a = p.a();
            GaanaApplication.getInstance();
            textView.setTypeface(a.a(GaanaApplication.getLanguage(this)));
            d.a().a("DOWNLOAD_PLAYER_TRACK_COACHMARK", false, false);
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.getSharedPreferences("DOWNLOAD_PLAYER_TRACK_COACHMARK", 0).edit().putBoolean("DOWNLOAD_PLAYER_TRACK_COACHMARK", false).apply();
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                    d.a().a("DOWNLOAD_PLAYER_TRACK_COACHMARK", false, false);
                }
            });
        } else if (string.equalsIgnoreCase("PLAYER_VIEW_PAGER_COACHMARK_FIRSTTIME")) {
            this.h = this.g.inflate(R.layout.view_pager_coachmarks, null);
            a = this.u;
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
        } else if (string.equalsIgnoreCase("MINI_PLAYER_OVERLAY_COACHMARK_FIRSTTIME")) {
            this.h = this.g.inflate(R.layout.mini_player_overlay_coachmark, null);
            this.e = (TextView) this.h.findViewById(R.id.coachmarkText);
            this.e.setTypeface(Util.i((Context) this));
            a = this.v;
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
        } else if (string.equalsIgnoreCase("PRESCREEN_COACHMARK")) {
            this.h = this.g.inflate(R.layout.prescreen_coachmark, null);
            this.e = (TextView) this.h.findViewById(R.id.coachmarkText);
            this.e.setTypeface(Util.i((Context) this));
            a = this.w;
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
        } else if (string.equalsIgnoreCase("create_party")) {
            i = (int) getIntent().getFloatExtra("extra_y", 0.0f);
            this.h = this.g.inflate(R.layout.invite_friends_coachmark, null);
            View findViewById2 = this.h.findViewById(R.id.ll_coachmark);
            LayoutParams layoutParams = (LayoutParams) findViewById2.getLayoutParams();
            layoutParams.topMargin = i;
            layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.page_left_right_margin);
            findViewById2.requestLayout();
            this.e = (TextView) this.h.findViewById(R.id.coachmarkText);
            this.e.setText(R.string.tap_create_party);
            this.e.setTypeface(Util.i((Context) this));
            a = "create_party";
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
        } else if (string.equalsIgnoreCase("inv_friends")) {
            this.h = this.g.inflate(R.layout.invite_friends_coachmark, null);
            this.e = (TextView) this.h.findViewById(R.id.coachmarkText);
            this.e.setTypeface(Util.i((Context) this));
            View findViewById3 = this.h.findViewById(R.id.ll_coachmark);
            LayoutParams layoutParams2 = new LayoutParams(-1, -2);
            layoutParams2.topMargin = getResources().getDimensionPixelSize(R.dimen.dp300);
            findViewById3.setLayoutParams(layoutParams2);
            a = "inv_friends";
            this.h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VideoCoachmarkActivity.this.finish();
                    VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                }
            });
        }
        setContentView(this.h);
        c();
    }

    private void c() {
        if (VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        if (VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(0);
        }
        if (Constants.l && VERSION.SDK_INT >= 23) {
            a(true);
            b(true);
            getWindow().setNavigationBarColor(-1);
        } else if (!Constants.l && VERSION.SDK_INT >= 23) {
            b(false);
            getWindow().setNavigationBarColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    public void a(boolean z) {
        a(z, 8192);
    }

    public void b(boolean z) {
        a(z, 16);
    }

    private void a(boolean z, int i) {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
        getWindow().getDecorView().setSystemUiVisibility(z ? systemUiVisibility | i : (i ^ -1) & systemUiVisibility);
    }

    public void a(int i) {
        this.x.setMax(Indexable.MAX_STRING_LENGTH);
        this.x.setTextSize(0.0f);
        b(i);
        this.b = new CountDownTimer(20000, 100) {
            int a = 1;

            public void onTick(long j) {
                this.a = 20000 - ((int) j);
                VideoCoachmarkActivity.this.x.setProgress(this.a);
            }

            public void onFinish() {
                Constants.cf = false;
                VideoCoachmarkActivity.this.f.a();
                VideoCoachmarkActivity.this.j = System.currentTimeMillis();
                VideoCoachmarkActivity.this.a();
                VideoCoachmarkActivity.this.finish();
                VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                VideoCoachmarkActivity.this.b.cancel();
            }
        }.start();
    }

    private void d() {
        this.x.setMax(Indexable.MAX_STRING_LENGTH);
        this.x.setTextSize(0.0f);
        this.b = new CountDownTimer(20000, 100) {
            int a = 1;

            public void onTick(long j) {
                this.a = 20000 - ((int) j);
                VideoCoachmarkActivity.this.x.setProgress(this.a);
            }

            public void onFinish() {
                VideoCoachmarkActivity.this.j = System.currentTimeMillis();
                VideoCoachmarkActivity.this.a();
                VideoCoachmarkActivity.this.finish();
                VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
                VideoCoachmarkActivity.this.b.cancel();
            }
        }.start();
    }

    private void b(int i) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("android.resource://");
            stringBuilder.append(getPackageName());
            stringBuilder.append("/");
            stringBuilder.append(i);
            this.f.setVideoURI(Uri.parse(stringBuilder.toString()));
            this.f.start();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        this.f.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                VideoCoachmarkActivity.this.f.start();
            }
        });
        this.f.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        Util.a(false);
        a = "";
    }

    public void onResume() {
        super.onResume();
        Util.a(true);
    }

    public void a() {
        float f = (float) ((this.j - this.i) / 1000);
        StringBuilder stringBuilder;
        if (a.equalsIgnoreCase(this.l)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Duration: ");
            stringBuilder.append(f);
            u.a().a("Move a track up or down: Coach-mark", stringBuilder.toString(), "");
        } else if (a.equalsIgnoreCase(this.m)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Duration: ");
            stringBuilder.append(f);
            u.a().a("Long Press: Coach-mark", stringBuilder.toString(), "");
        } else if (a.equalsIgnoreCase(this.k)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Duration: ");
            stringBuilder.append(f);
            u.a().a("Swipe left: Coach-mark", stringBuilder.toString(), "");
        } else if (a.equalsIgnoreCase(this.n)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Duration: ");
            stringBuilder.append(f);
            u.a().a("Chromecast: Coach-mark", stringBuilder.toString(), "");
        }
    }

    private void e() {
        if (a.equalsIgnoreCase(this.l)) {
            u.a().a("Move a track up or down: Coach-mark", "Explicitly closed", "");
        } else if (a.equalsIgnoreCase(this.m)) {
            u.a().a("Long Press: Coach-mark", "Explicitly closed", "");
        } else if (a.equalsIgnoreCase(this.k)) {
            u.a().a("Swipe left: Coach-mark", "Explicitly closed", "");
        } else if (a.equalsIgnoreCase(this.n)) {
            u.a().a("Chromecast: Coach-mark", "Explicitly closed", "");
        }
    }

    public void b() {
        this.d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (VideoCoachmarkActivity.this.f.isPlaying()) {
                    VideoCoachmarkActivity.this.f.a();
                }
                Constants.cf = false;
                VideoCoachmarkActivity.this.finish();
                VideoCoachmarkActivity.this.e();
                VideoCoachmarkActivity.this.j = System.currentTimeMillis();
                VideoCoachmarkActivity.this.a();
                VideoCoachmarkActivity.this.overridePendingTransition(17432576, 17432577);
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.f.isPlaying()) {
            this.f.a();
        }
        this.j = System.currentTimeMillis();
        a();
        Constants.cf = false;
        overridePendingTransition(17432576, 17432577);
        if (a == this.o) {
            getSharedPreferences("VOICE_SEARCH_FIRST_TIME", 0).edit().putBoolean("VOICE_SEARCH_FIRST_TIME", false).apply();
        } else if (a == this.p) {
            getSharedPreferences("VOICE_SEARCH_HOME", 0).edit().putBoolean("VOICE_SEARCH_HOME", false).apply();
        }
    }
}
