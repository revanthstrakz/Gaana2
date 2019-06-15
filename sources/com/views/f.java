package com.views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.fragments.a;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.view.SpiralDrawingView;
import com.i.i;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.d;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.models.PlayerTrack;
import com.player_framework.PlayerStatus;
import com.player_framework.PlayerStatus.PlayerStates;
import com.player_framework.o;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class f {
    private Context a;
    private View b;
    private Activity c;
    private Drawable d;
    private boolean e;
    private boolean f;
    private Handler g;

    public f(Context context) {
        this.a = context;
        this.c = (GaanaActivity) context;
    }

    public void a() {
        boolean z = true;
        new int[1][0] = R.attr.miniplayer_play;
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.d = ContextCompat.getDrawable(this.a, obtainStyledAttributes.getResourceId(31, -1));
        obtainStyledAttributes.recycle();
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0);
        if (!(GaanaApplication.sessionHistoryCount == 0 && sharedPreferences.getBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", true))) {
            z = false;
        }
        this.e = z;
    }

    public void b() {
        if (Util.c() && Constants.dn) {
            if (this.e) {
                e();
            }
            return;
        }
        c();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(boolean z) {
        ((GaanaActivity) this.a).setCoachmarkViewHidden(z);
    }

    private void d() {
        if ((this.a instanceof GaanaActivity) && !(((GaanaActivity) this.a).getCurrentFragment() instanceof a) && !Constants.cY) {
            a(true);
            this.b = ((ViewStub) ((GaanaActivity) this.a).findViewById(R.id.onboard_player_stub)).inflate();
            ((GaanaActivity) this.a).getSlidingPanelLayout().a(2);
            this.b.setVisibility(0);
            TextView textView = (TextView) this.b.findViewById(R.id.onboard_player_timer_text);
            RelativeLayout relativeLayout = (RelativeLayout) this.b.findViewById(R.id.playerButtonLayout);
            final float dimensionPixelSize = (float) this.a.getResources().getDimensionPixelSize(R.dimen.radius_progress);
            float dimensionPixelSize2 = (float) this.a.getResources().getDimensionPixelSize(R.dimen.spiral_width);
            final long timeInMillis = Calendar.getInstance().getTimeInMillis();
            ImageView imageView = new ImageView(this.a);
            int i = (int) dimensionPixelSize2;
            int i2 = 2 * (((int) dimensionPixelSize) + i);
            imageView.setLayoutParams(new LayoutParams(i2, i2));
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    f.this.a(false);
                    long toSeconds = TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance().getTimeInMillis() - timeInMillis);
                    PlayerTrack i = PlayerManager.a(f.this.a).i();
                    if (!(i == null || i.b() == null)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(i.b().getBusinessObjId());
                        stringBuilder.append(" - ");
                        stringBuilder.append(toSeconds);
                        ((BaseActivity) f.this.a).sendGAEvent("Auto Player", "User driven play", stringBuilder.toString());
                    }
                    f.this.a(i);
                }
            });
            ((TextView) this.b.findViewById(R.id.gaana_playlist_title)).setText(Constants.dk);
            TextView textView2 = (TextView) this.b.findViewById(R.id.do_not_play_button);
            textView2.setText(Constants.dl);
            textView2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    f.this.a(false);
                    ((BaseActivity) f.this.a).sendGAEvent("Auto Player", "Stopped", "Auto Player-Stopped");
                    f.this.c();
                    PlayerManager.a(f.this.a).j(true);
                    f.this.b(false);
                }
            });
            imageView.setImageDrawable(this.d);
            imageView.setScaleType(ScaleType.CENTER_INSIDE);
            relativeLayout.addView(imageView);
            final SpiralDrawingView spiralDrawingView = new SpiralDrawingView(this.a, dimensionPixelSize, i);
            relativeLayout.addView(spiralDrawingView);
            spiralDrawingView.setVisibility(0);
            spiralDrawingView.ResetSpiral();
            ((TextView) this.b.findViewById(R.id.onboard_player_text)).setText(Constants.dj);
            int i3 = Constants.di;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a.getString(R.string.playing_in));
            stringBuilder.append(i3);
            stringBuilder.append(this.a.getString(R.string.sec));
            textView.setText(stringBuilder.toString());
            this.g = new Handler();
            this.g.postDelayed(new Runnable() {
                public void run() {
                    f.this.a(dimensionPixelSize, spiralDrawingView, 100);
                }
            }, 100);
        }
    }

    private void b(boolean z) {
        i slidingPanelLayout = ((GaanaActivity) this.a).getSlidingPanelLayout();
        if (slidingPanelLayout != null) {
            if (this.e && z && PlayerManager.a(GaanaApplication.getContext()).E() == null) {
                c();
            }
            if (!z) {
                slidingPanelLayout.b();
            } else if (slidingPanelLayout.a() == 2 && !com.managers.f.v().w()) {
                slidingPanelLayout.a(0);
            }
        }
    }

    private void a(final float f, final SpiralDrawingView spiralDrawingView, int i) {
        if (!this.e || this.c.isFinishing()) {
            a(false);
            return;
        }
        int i2 = Constants.di * 1000;
        spiralDrawingView.onResume(((float) TimeUnit.MILLISECONDS.toSeconds((long) i)) * ((float) (((long) (((double) (2.0f * f)) * 3.141592653589793d)) / TimeUnit.MILLISECONDS.toSeconds((long) i2))));
        TextView textView = (TextView) this.b.findViewById(R.id.onboard_player_timer_text);
        final int i3 = i + 100;
        int i4 = i2 - i;
        if (i4 % 1000 == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a.getString(R.string.playing_in));
            stringBuilder.append(TimeUnit.MILLISECONDS.toSeconds((long) i4));
            stringBuilder.append(this.a.getString(R.string.sec));
            textView.setText(stringBuilder.toString());
        }
        if (!Constants.dn) {
            a(false);
            c();
            PlayerManager.a(this.a).j(true);
            b(false);
        } else if (i >= i2) {
            a(false);
            if (Util.c()) {
                PlayerTrack i5 = PlayerManager.a(this.a).i();
                if (!(i5 == null || i5.b() == null)) {
                    ((BaseActivity) this.a).sendGAEvent("Auto Player", "Auto play", i5.b().getBusinessObjId());
                }
                a(i5);
                return;
            }
            c();
            PlayerManager.a(this.a).j(true);
            b(false);
        } else {
            this.g.postDelayed(new Runnable() {
                public void run() {
                    f.this.a(f, spiralDrawingView, i3);
                }
            }, 100);
        }
    }

    private void a(PlayerTrack playerTrack) {
        if (playerTrack != null && !Constants.cY) {
            playerTrack.d(PLAYOUT_SECTION_TYPE.AUTOPLAY.name());
            c();
            PlayerManager.a(this.a).j(false);
            PlayerManager.a(this.a).a(PlayerType.GAANA, this.a);
            ((GaanaActivity) this.a).setUpdatePlayerFragment();
        }
    }

    public void c() {
        Editor edit = GaanaApplication.getContext().getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0).edit();
        edit.putBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", false);
        edit.apply();
        Util.a(null);
        this.e = false;
        if (!this.c.isFinishing()) {
            if (this.b != null) {
                this.b.setVisibility(8);
            }
            i slidingPanelLayout = ((GaanaActivity) this.a).getSlidingPanelLayout();
            if (slidingPanelLayout != null) {
                slidingPanelLayout.a(0);
            }
        }
    }

    private void e() {
        if (Util.j(this.a)) {
            URLManager uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Tracks);
            uRLManager.a("https://api.gaana.com/index.php?type=song&subtype=most_popular_multilingual&order=week");
            uRLManager.a(Priority.HIGH);
            uRLManager.b(Boolean.valueOf(true));
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (!Constants.cY && businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0 && f.this.e && !f.this.c.isFinishing() && !f.this.f) {
                        ArrayList n = PlayerManager.a(f.this.a).n();
                        if (n == null || n.size() == 0) {
                            ArrayList a = d.a().a(((GaanaActivity) f.this.a).getCurrentFragment(), businessObject.getArrListBusinessObj());
                            PlayerManager.a(f.this.a).a(a, (PlayerTrack) a.get(0));
                            PlayerManager.a(f.this.a).b(a);
                            f.this.d();
                            f.this.f = true;
                            PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA, f.this.a);
                            PlayerStatus.a(f.this.a, PlayerStates.STOPPED);
                            o.d(f.this.a);
                            PlayerManager.a = false;
                        }
                    }
                }
            }, uRLManager);
        }
    }
}
