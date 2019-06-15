package com.gaana;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.gaana.ads.interstitial.InterstitialAdRequest;
import com.gaana.ads.interstitial.LoadAlwaysInterstitialBehaviour;
import com.gaana.ads.interstitial.ShowAlwaysInterstitialBehaviour;
import com.gaana.view.item.SquareImageView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.managers.ap;
import com.managers.f;
import com.managers.f.b;
import com.player_framework.GaanaMusicService;
import com.utilities.Util;
import java.lang.ref.WeakReference;

public class AudioAdActivity extends AppCompatActivity implements OnClickListener, b {
    public static boolean LAUNCH_PURCHASE_SCREEN = false;
    public static boolean SHOW_FAKE_CHOTA_PLAYER = false;
    private static final int UPDATE_TIMER = 1001;
    private GaanaMusicService _GaanaMusicService;
    private boolean _isServiceBound = false;
    private ServiceConnection _myServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AudioAdActivity.this._GaanaMusicService = ((GaanaMusicService.b) iBinder).a();
            AudioAdActivity.this._isServiceBound = true;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            AudioAdActivity.this._isServiceBound = false;
        }
    };
    private f colombiaVideoAdManager = f.v();
    private long currentTime;
    private Handler handler;
    private boolean isActivityInForeground = false;
    private boolean isFinished = false;
    private boolean showChotaPlayer = false;
    private Thread thread;
    private TextView tvPlayerEndTimer;

    private static class TimeUpdateHandler extends Handler {
        private WeakReference<AudioAdActivity> activityWeakReference;

        public TimeUpdateHandler(AudioAdActivity audioAdActivity) {
            this.activityWeakReference = new WeakReference(audioAdActivity);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1001) {
                AudioAdActivity audioAdActivity = (AudioAdActivity) this.activityWeakReference.get();
                if (audioAdActivity != null) {
                    audioAdActivity.setTime();
                    audioAdActivity.startTimer(1000);
                }
            }
        }
    }

    public void onBackPressed() {
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private void setTime() {
        this.currentTime--;
        if (this.currentTime > 0) {
            TextView textView = this.tvPlayerEndTimer;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.currentTime);
            stringBuilder.append(getString(R.string.seconds_text));
            textView.setText(stringBuilder.toString());
            return;
        }
        this.tvPlayerEndTimer.setVisibility(8);
        if (f.v().u() || f.a) {
            ((ImageView) findViewById(R.id.playerBtnNext)).setOnClickListener(this);
            ((ImageView) findViewById(R.id.playerBtnNext)).setAlpha(255);
            return;
        }
        ((ImageView) findViewById(R.id.playerBtnNext)).setAlpha(64);
    }

    private void startTimer(long j) {
        if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(1001, j);
        }
    }

    private void stopTimer() {
        if (this.handler != null) {
            this.handler.removeMessages(1001);
        }
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(com.b.b.a(context));
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        Bitmap q;
        super.onCreate(bundle);
        setContentView((int) R.layout.audio_ad_activity);
        this.handler = new TimeUpdateHandler(this);
        SquareImageView squareImageView = (SquareImageView) findViewById(R.id.imgPlayerFullScreen);
        if (this.colombiaVideoAdManager.q() != null) {
            q = this.colombiaVideoAdManager.q();
        } else {
            q = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.placeholder_album_artwork_large);
        }
        squareImageView.setImageBitmap(q);
        squareImageView.setVisibility(0);
        TextView textView = (TextView) findViewById(R.id.player_bottom_main_text);
        if (!TextUtils.isEmpty(this.colombiaVideoAdManager.r())) {
            textView.setText(this.colombiaVideoAdManager.r());
        }
        textView = (TextView) findViewById(R.id.player_bottom_secondary_text);
        if (!TextUtils.isEmpty(this.colombiaVideoAdManager.r())) {
            textView.setText(R.string.sponsored_ad_text);
        }
        this.tvPlayerEndTimer = (TextView) findViewById(R.id.tvPlayerEndTimer);
        if (ap.a().b((Context) this) && !TextUtils.isEmpty(f.v().f())) {
            LoadAlwaysInterstitialBehaviour loadAlwaysInterstitialBehaviour = new LoadAlwaysInterstitialBehaviour();
            if (loadAlwaysInterstitialBehaviour.whenToLoad()) {
                new InterstitialAdRequest().buildPublisherInterstitialAd(new PublisherInterstitialAd(this)).buildInterstitialShowBehaviour(new ShowAlwaysInterstitialBehaviour()).buildInterstitialLoadBehaviour(loadAlwaysInterstitialBehaviour).buildAudioFollowUpCampaign(f.v().f()).build().loadAndShow();
            }
        }
        try {
            this.currentTime = ((long) (Double.parseDouble(f.b) / 1000.0d)) - ((System.currentTimeMillis() - f.c) / 1000);
            if (this.currentTime >= 0) {
                textView = this.tvPlayerEndTimer;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.currentTime);
                stringBuilder.append(getString(R.string.seconds_text));
                textView.setText(stringBuilder.toString());
            }
            startTimer(1000);
        } catch (Exception unused) {
        }
        ((ImageView) findViewById(R.id.playerbutton)).setAlpha(64);
        ((ImageView) findViewById(R.id.playerBtnRepeat)).setAlpha(64);
        ((ImageView) findViewById(R.id.playerBtnShuffle)).setAlpha(64);
        ((ImageView) findViewById(R.id.playerBtnPrev)).setAlpha(64);
        if (f.v().u() || f.a) {
            ((ImageView) findViewById(R.id.playerBtnNext)).setAlpha(255);
            findViewById(R.id.playerBtnNext).setOnClickListener(this);
        } else {
            ((ImageView) findViewById(R.id.playerBtnNext)).setAlpha(64);
        }
        ((ImageView) findViewById(R.id.playerQueue)).setAlpha(64);
        ((ImageView) findViewById(R.id.playerBtnInfo)).setAlpha(64);
        ((ImageView) findViewById(R.id.downloadTrackPlayer)).setAlpha(64);
        this.showChotaPlayer = false;
        findViewById(R.id.player_back_image).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AudioAdActivity.this.showChotaPlayer = true;
                if (!f.v().t()) {
                    AudioAdActivity.this.finish();
                }
            }
        });
        findViewById(R.id.upgrade_gaana).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AudioAdActivity.this.showChotaPlayer = true;
                if (!f.v().t()) {
                    AudioAdActivity.this.finish();
                }
                AudioAdActivity.LAUNCH_PURCHASE_SCREEN = true;
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        if (this.colombiaVideoAdManager.x()) {
            this.isActivityInForeground = true;
            this.colombiaVideoAdManager.a((b) this);
            Util.a(true);
            bindService(new Intent(this, GaanaMusicService.class), this._myServiceConnection, 1);
            return;
        }
        this.isActivityInForeground = true;
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        this.isActivityInForeground = false;
        if (!this.isFinished) {
            Util.a(false);
        }
        if (this._isServiceBound) {
            unbindService(this._myServiceConnection);
            this._isServiceBound = false;
        }
    }

    public void finish() {
        SHOW_FAKE_CHOTA_PLAYER = this.showChotaPlayer;
        this.isFinished = true;
        if (this.isActivityInForeground) {
            Intent intent = new Intent(this, GaanaActivity.class);
            intent.setFlags(603979776);
            startActivity(intent);
        }
        stopTimer();
        super.finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    public void updatePlayerOnAdStop() {
        this.showChotaPlayer = false;
        this.colombiaVideoAdManager.a(null);
        finish();
    }

    public void updatePlayerEnableSkip() {
        ((ImageView) findViewById(R.id.playerBtnNext)).setAlpha(255);
        findViewById(R.id.playerBtnNext).setOnClickListener(this);
    }

    public void onClick(View view) {
        f.v().b(true);
        this.colombiaVideoAdManager.k(this.colombiaVideoAdManager.D());
        this.colombiaVideoAdManager.A();
        this.showChotaPlayer = false;
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
