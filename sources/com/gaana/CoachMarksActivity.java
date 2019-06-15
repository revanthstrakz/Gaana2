package com.gaana;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.b.b;
import com.constants.Constants;
import com.library.controls.CrossFadeImageView;
import com.utilities.Util;

public class CoachMarksActivity extends Activity {
    private View contentView;
    private LayoutInflater mLayoutInflater;

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(b.a(context));
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Constants.l) {
            setTheme(R.style.CoachMarkThemeWhite);
        }
        String string = getIntent().getExtras().getString("COACHMARK_VALUE");
        this.mLayoutInflater = LayoutInflater.from(this);
        if (string.equalsIgnoreCase("PLAYER_CREATED_FIRST_TIME")) {
            this.contentView = this.mLayoutInflater.inflate(R.layout.coachmarkssmallplayer, null);
            string = getIntent().getExtras().getString("MainText");
            String string2 = getIntent().getExtras().getString("SecondryText");
            String string3 = getIntent().getExtras().getString("ArtWork");
            if (string3 != null) {
                ((CrossFadeImageView) this.contentView.findViewById(R.id.player_bottom_image)).bindImage(string3, ScaleType.FIT_CENTER);
            }
            ((TextView) this.contentView.findViewById(R.id.player_bottom_main_text_bottom)).setText(string);
            ((TextView) this.contentView.findViewById(R.id.player_bottom_secondary_text_bottom)).setText(string2);
        } else if (string.equalsIgnoreCase("MYMUSIC_SECTION_OPENED_FIRST_TIME")) {
            this.contentView = this.mLayoutInflater.inflate(R.layout.coachmark_music_on_myphone, null);
        } else if (string.equalsIgnoreCase("SEARH_FIRST_TIME")) {
            this.contentView = this.mLayoutInflater.inflate(R.layout.coachmark_search, null);
        } else if (string.equalsIgnoreCase("DETAIL_SHARE_FIRST_TIME")) {
            this.contentView = this.mLayoutInflater.inflate(R.layout.coachmark_share, null);
        } else if (string.equalsIgnoreCase("HOME_CAST_FIRST_TIME")) {
            this.contentView = this.mLayoutInflater.inflate(R.layout.coachmark_cast, null);
        }
        setContentView(this.contentView);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        finish();
        return super.onTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        Util.a(true);
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        Util.a(false);
    }
}
