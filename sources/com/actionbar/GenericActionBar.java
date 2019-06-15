package com.actionbar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.SearchEnchancedFragment;
import com.fragments.SettingsFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.view.GaanaYourYearView.GAANA_ENTRY_PAGE;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.managers.an;
import com.managers.u;
import com.services.d;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;

public class GenericActionBar extends LinearLayout implements OnMenuItemClickListener, OnClickListener {
    private LayoutInflater a;
    private Context b;

    public GenericActionBar(Context context, String str, boolean z, BaseGaanaFragment baseGaanaFragment) {
        super(context);
        this.b = context;
        a(context, str, z);
    }

    private void a(Context context, String str, boolean z) {
        this.b = context;
        this.a = LayoutInflater.from(context);
        this.a.inflate(R.layout.actionbar_generic, this);
        findViewById(R.id.menu_pre_screen).setOnClickListener(this);
        if (!Constants.aZ) {
            return;
        }
        if (z) {
            findViewById(R.id.menu_pre_screen).setVisibility(0);
            View view = (TextView) findViewById(R.id.menu_pre_screen_text);
            view.setTypeface(Util.i(this.b));
            a(view, false, 4000);
            return;
        }
        findViewById(R.id.menu_pre_screen).setVisibility(8);
    }

    public void setToolbar(Toolbar toolbar) {
        toolbar.setOnMenuItemClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.media_route_menu_item) {
            u.a().b("Chromecast: Coach-mark", "Clicked on Chromecast icon");
        } else if (id == R.id.menu_icon) {
            ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Action Bar Click", "Navigation drawer");
            ((GaanaActivity) this.b).homeIconClick();
        } else if (id == R.id.menu_pre_screen) {
            ((GaanaActivity) this.b).openDrawers(false);
        } else if (id == R.id.settings_actionbar) {
            ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Action Bar Click", "Settings");
            ((GaanaActivity) this.b).displayFragment(new SettingsFragment());
        }
    }

    public void a(int i) {
        if (i == R.id.menu_icon) {
            ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Action Bar Click", "Navigation drawer");
            ((GaanaActivity) this.b).homeIconClick();
        } else if (i == R.id.searchview_actionbar) {
            ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Action Bar Click", "Search");
            BaseGaanaFragment searchEnchancedFragment = new SearchEnchancedFragment();
            Bundle bundle = new Bundle();
            if (Constants.bb) {
                bundle.putBoolean("IS_TRENDING", true);
            }
            searchEnchancedFragment.setArguments(bundle);
            ((GaanaActivity) this.b).clearStackForSearch();
            ((GaanaActivity) this.b).displayFragment(searchEnchancedFragment);
            an.a().a("click", "ac", "", "HOME", "", "SEARCH_EXP", "", "");
        } else if (i == R.id.settings_actionbar) {
            ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Action Bar Click", "Navigation drawer");
            ((GaanaActivity) this.b).openDrawers(true);
        } else if (i == R.id.video_feed_action_bar) {
            Util.a(this.b, new YouTubeVideo(), GAANA_ENTRY_PAGE.HOME_ACTIONBAR.name());
            u.a().a("Browse", "Action Bar Click", "Video Feed");
        }
    }

    private void a(final View view, final boolean z, long j) {
        if (GaanaApplication.sessionHistoryCount % 3 == 0 && GaanaApplication.sessionHistoryCount != d.a().b("PRESCREEN_ANIMATION_HISTORY", -1, false)) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (GaanaApplication.getInstance().isAppInForeground()) {
                        if (z) {
                            view.setVisibility(8);
                            d.a().a(GaanaApplication.sessionHistoryCount, "PRESCREEN_ANIMATION_HISTORY", false);
                            ((GaanaActivity) GenericActionBar.this.b).showPreScreenCoachmark();
                            return;
                        }
                        view.setVisibility(0);
                        GenericActionBar.this.a(view, true, d.a().b("PRESCREEN_COACHMARK", false, false) ? DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS : AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
                    } else if (view != null) {
                        view.setVisibility(8);
                    }
                }
            }, j);
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        a(menuItem.getItemId());
        return false;
    }
}
