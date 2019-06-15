package com.actionbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.b.i;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.SearchEnchancedFragment;
import com.fragments.SearchFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.OnBoardLanguagePreferenceActivityNew;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.managers.aa;
import com.managers.al;
import com.managers.u;
import com.utilities.Util;

public class GenericBackActionBar extends BaseContextualActionBar implements OnMenuItemClickListener, OnClickListener {
    private boolean a;
    private Context b;
    private LayoutInflater c;
    private a d = null;
    private boolean e = false;
    private Toolbar f;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;

    public interface a {
        void onBackClicked();

        void onClearAllClicked();

        void onSubmitClicked();
    }

    public GenericBackActionBar(Context context, String str) {
        super(context);
        a(context, str, null);
    }

    public GenericBackActionBar(Context context, boolean z, String str) {
        super(context);
        this.a = z;
        this.i = true;
        a(context, str, null);
    }

    public GenericBackActionBar(Context context, String str, boolean z) {
        super(context);
        this.h = z;
        a(context, str, null);
    }

    public GenericBackActionBar(Context context, String str, a aVar) {
        super(context);
        a(context, str, aVar);
    }

    public GenericBackActionBar(Context context, String str, a aVar, boolean z) {
        super(context);
        this.g = z;
        a(context, str, aVar);
    }

    public void setTitle(String str) {
        if (this.a) {
            TextView textView = (TextView) findViewById(R.id.actionbar_title);
            textView.setVisibility(0);
            textView.setTypeface(i.a(this.b.getAssets(), "fonts/Roboto-Medium.ttf"));
            textView.setText(str);
        }
    }

    public void showContextMenu(boolean z) {
        Menu menu = this.f != null ? this.f.getMenu() : null;
        if (z) {
            if (menu != null) {
                menu.setGroupVisible(R.id.cast_menu_home, false);
            }
            findViewById(R.id.generic_back_actionbar).setVisibility(8);
        } else {
            if (menu != null) {
                menu.setGroupVisible(R.id.cast_menu_home, true);
            }
            findViewById(R.id.generic_back_actionbar).setVisibility(0);
        }
        super.showContextMenu(z);
    }

    public void hideContextMenu(boolean z) {
        Menu menu = this.f != null ? this.f.getMenu() : null;
        if (al.a) {
            super.hideContextMenu(z);
            if (z) {
                if (menu != null) {
                    menu.setGroupVisible(R.id.cast_menu_home, true);
                }
                findViewById(R.id.generic_back_actionbar).setVisibility(0);
                return;
            }
            if (menu != null) {
                menu.setGroupVisible(R.id.cast_menu_home, false);
            }
            findViewById(R.id.generic_back_actionbar).setVisibility(8);
        }
    }

    public void setToolbar(Toolbar toolbar) {
        this.f = toolbar;
        toolbar.setOnMenuItemClickListener(this);
    }

    public TextView getTitleTextView() {
        return (TextView) findViewById(R.id.actionbar_title);
    }

    public void a() {
        findViewById(R.id.accept_icon).setVisibility(4);
    }

    private void a(Context context, String str, a aVar) {
        this.b = context;
        this.d = aVar;
        this.c = LayoutInflater.from(this.b);
        setLayoutParams(new LayoutParams(-1, -2));
        this.c.inflate(R.layout.generic_back_actionbar, this);
        findViewById(R.id.menu_icon).setOnClickListener(this);
        if (this.h) {
            findViewById(R.id.menu_icon).setVisibility(4);
            findViewById(R.id.menu_icon).setOnClickListener(null);
            findViewById(R.id.txt_skip_intl).setVisibility(0);
            findViewById(R.id.txt_skip_intl).setOnClickListener(this);
        }
        if (this.i) {
            if (Constants.l) {
                findViewById(R.id.generic_back_actionbar).setBackgroundColor(this.b.getResources().getColor(R.color.view_foreground_light));
            } else {
                findViewById(R.id.generic_back_actionbar).setBackgroundColor(this.b.getResources().getColor(R.color.view_background_dark));
            }
        }
        if (!(this.b instanceof GaanaActivity) || (((GaanaActivity) this.b).getCurrentFragment() instanceof SearchFragment)) {
            findViewById(R.id.searchview_actionbar).setVisibility(8);
        } else {
            findViewById(R.id.searchview_actionbar).setOnClickListener(this);
        }
        if (this.d != null) {
            findViewById(R.id.accept_icon).setVisibility(0);
            findViewById(R.id.accept_icon).setOnClickListener(this);
        } else {
            findViewById(R.id.accept_icon).setVisibility(4);
        }
        findViewById(R.id.searchview_actionbar).setVisibility(8);
        if (this.g) {
            findViewById(R.id.searchview_actionbar).setVisibility(8);
            if (Constants.l) {
                if (this.a) {
                    findViewById(R.id.actionbar_title).setAlpha(0.6f);
                }
                ((ImageView) findViewById(R.id.menu_icon)).setImageResource(R.drawable.player_cross_white);
                ((ImageView) findViewById(R.id.clear_all_icon)).setImageResource(R.drawable.ic_read_all_white);
            } else {
                ((ImageView) findViewById(R.id.menu_icon)).setImageResource(R.drawable.player_cross);
                ((ImageView) findViewById(R.id.clear_all_icon)).setImageResource(R.drawable.ic_read_all);
            }
            findViewById(R.id.clear_all_icon).setVisibility(0);
            findViewById(R.id.clear_all_icon).setOnClickListener(this);
            findViewById(R.id.accept_icon).setVisibility(8);
        } else {
            findViewById(R.id.clear_all_icon).setVisibility(8);
        }
        setTitle(Constants.b(str));
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.accept_icon /*2131296346*/:
                if (this.d != null) {
                    this.d.onSubmitClicked();
                    return;
                }
                return;
            case R.id.clear_all_icon /*2131296691*/:
                aa.a().c();
                this.d.onClearAllClicked();
                return;
            case R.id.menu_icon /*2131297709*/:
                if (this.e) {
                    b();
                    return;
                }
                if (this.d != null) {
                    this.d.onBackClicked();
                }
                if (this.b instanceof WebViewActivity) {
                    if (this.d == null) {
                        ((Activity) this.b).finish();
                        return;
                    }
                    return;
                } else if (this.b instanceof GaanaActivity) {
                    ((GaanaActivity) this.b).homeIconClick();
                    return;
                } else {
                    ((Activity) this.b).finish();
                    return;
                }
            case R.id.searchview_actionbar /*2131298327*/:
                ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Action Bar Click", "Search");
                BaseGaanaFragment searchEnchancedFragment = new SearchEnchancedFragment();
                searchEnchancedFragment.setArguments(new Bundle());
                ((GaanaActivity) this.b).clearStackForSearch();
                ((GaanaActivity) this.b).displayFragment(searchEnchancedFragment);
                return;
            case R.id.txt_skip_intl /*2131298778*/:
                String str;
                if (Util.j(this.b)) {
                    str = "Skip-LanguagePreference";
                    Intent intent = new Intent(this.b, OnBoardLanguagePreferenceActivityNew.class);
                    intent.setFlags(603979776);
                    this.b.startActivity(intent);
                } else {
                    str = "Skip-Home";
                    if (((GaanaActivity) this.b).getCurrentFragment() instanceof SettingsDetailFragment) {
                        ((GaanaActivity) this.b).popBackStackImmediate();
                    }
                    ((GaanaActivity) this.b).changeFragment(R.id.LeftMenuMyMusic, null, null);
                }
                u.a().a("InternationalOnBoarding", str, "SubscriptionScreen");
                return;
            default:
                return;
        }
    }

    private void b() {
        int i = 0;
        this.e = false;
        findViewById(R.id.menu_icon).setVisibility(0);
        View findViewById = findViewById(R.id.actionbar_title);
        if (!this.a) {
            i = 8;
        }
        findViewById.setVisibility(i);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        a(menuItem.getItemId());
        return false;
    }

    public void a(int i) {
        if (i != R.id.accept_icon) {
            if (i == R.id.clear_all_icon) {
                aa.a().c();
                this.d.onClearAllClicked();
            } else if (i != R.id.menu_icon) {
                if (i == R.id.searchview_actionbar) {
                    ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Action Bar Click", "Search");
                    BaseGaanaFragment searchEnchancedFragment = new SearchEnchancedFragment();
                    searchEnchancedFragment.setArguments(new Bundle());
                    ((GaanaActivity) this.b).clearStackForSearch();
                    ((GaanaActivity) this.b).displayFragment(searchEnchancedFragment);
                }
            } else if (this.e) {
                b();
            } else {
                if (this.d != null) {
                    this.d.onBackClicked();
                }
                if (this.b instanceof WebViewActivity) {
                    if (this.d == null) {
                        ((Activity) this.b).finish();
                    }
                } else if (this.b instanceof GaanaActivity) {
                    ((GaanaActivity) this.b).homeIconClick();
                } else {
                    ((Activity) this.b).finish();
                }
            }
        } else if (this.d != null) {
            this.d.onSubmitClicked();
        }
    }
}
