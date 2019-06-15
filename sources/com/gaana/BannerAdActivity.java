package com.gaana;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.constants.Constants;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.api.client.http.HttpStatusCodes;
import com.library.controls.CrossFadeImageView;
import com.services.d;
import com.services.n;
import com.til.colombia.android.service.Item;
import com.views.CircularSolideProgressView;

public class BannerAdActivity extends AppCompatActivity {
    private CrossFadeImageView bannerImage;
    private ImageView crossButton = null;
    private CircularSolideProgressView progressBar;
    int second = 0;
    private CountDownTimer timer = null;
    private TextView timerText;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Constants.l) {
            setTheme(R.style.BannerAdThemeWhite);
        }
        setContentView((int) R.layout.activity_banner_ad);
        initUI();
    }

    private void initUI() {
        final Item item = (Item) n.a(d.a().c("PREFERENCE_KEY_AUDIO_AD_SERIALIZED_DATA", false));
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        this.bannerImage = new CrossFadeImageView(this);
        setBannerSize(item);
        this.crossButton = (ImageView) findViewById(R.id.adCrossButton);
        this.bannerImage.bindImage(item.getImageUrl());
        relativeLayout.addView(this.bannerImage);
        this.progressBar = (CircularSolideProgressView) findViewById(R.id.circularSolideProgressBar);
        this.timerText = (TextView) findViewById(R.id.timerText);
        this.crossButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BannerAdActivity.this.finish();
            }
        });
        this.bannerImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String adUrl = item.getAdUrl();
                if (adUrl != null) {
                    BannerAdActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(adUrl)));
                }
            }
        });
    }

    private void setBannerSize(Item item) {
        int mediaAdWidth = item.getMediaAdWidth();
        int mediaAdHeight = item.getMediaAdHeight();
        LayoutParams layoutParams = (mediaAdHeight <= 0 || mediaAdWidth <= 0) ? null : (mediaAdWidth == HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES && mediaAdHeight == HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES) ? new LayoutParams(getResources().getDimensionPixelSize(R.dimen.ad_width_300x300), getResources().getDimensionPixelSize(R.dimen.ad_height_300x300)) : (mediaAdWidth == HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES && mediaAdHeight == Callback.DEFAULT_SWIPE_ANIMATION_DURATION) ? new LayoutParams(getResources().getDimensionPixelSize(R.dimen.ad_width_300x250), getResources().getDimensionPixelSize(R.dimen.ad_height_300x250)) : (mediaAdWidth == ModuleDescriptor.MODULE_VERSION && mediaAdHeight == 480) ? new LayoutParams(getResources().getDimensionPixelSize(R.dimen.ad_width_320x480), getResources().getDimensionPixelSize(R.dimen.ad_height_320x480)) : new LayoutParams(com.cast_music.b.d.a((Context) this, (float) mediaAdWidth), com.cast_music.b.d.a((Context) this, (float) mediaAdHeight));
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-2, -2);
        }
        layoutParams.addRule(13);
        this.bannerImage.setLayoutParams(layoutParams);
    }

    private void showCircularAnimation() {
        this.second = 0;
        this.timer = new CountDownTimer(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, 100) {
            public void onTick(long j) {
                BannerAdActivity.this.second += 100;
                if (BannerAdActivity.this.second == 2000) {
                    BannerAdActivity.this.crossButton.setVisibility(0);
                }
                if (BannerAdActivity.this.second % 1000 == 0) {
                    TextView access$100 = BannerAdActivity.this.timerText;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(BannerAdActivity.this.getString(R.string.inText));
                    stringBuilder.append(" ");
                    stringBuilder.append((5000 - BannerAdActivity.this.second) / 1000);
                    stringBuilder.append("");
                    stringBuilder.append(BannerAdActivity.this.getString(R.string.seconds_text));
                    access$100.setText(stringBuilder.toString());
                }
                BannerAdActivity.this.progressBar.setProgress((int) j);
            }

            public void onFinish() {
                BannerAdActivity.this.finish();
            }
        };
        this.timer.start();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        showCircularAnimation();
        d.a().a("PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS", false, false);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }
}
