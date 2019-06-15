package com.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.actionbar.SearchActionBar;
import com.comscore.measurement.MeasurementDispatcher;
import com.constants.Constants;
import com.constants.a.a;
import com.fragments.SearchTabFragment.b;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.NextGenAutoSuggestAdapter;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Languages;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.ACTION_TYPE;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.u;
import com.services.d;
import com.services.l.q;
import com.services.n;
import com.utilities.Util;

public class SearchEnchancedFragment extends BaseGaanaFragment implements AnimatorListener, a, b, GaanaSearchManager.b, q {
    private View a = null;
    private boolean b = false;
    private boolean c;
    private long d;
    private View e;
    private Toolbar f;
    private boolean g;
    private View h;
    private boolean i;
    private boolean j;
    private SearchActionBar k;
    private TypedValue l = new TypedValue();
    private SearchTabFragment m;
    private FragmentTransaction n;
    private FragmentManager o;

    public String getFragmentStackName() {
        return "search";
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public SearchActionBar a() {
        return this.k;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z;
        getActivity().getTheme().resolveAttribute(R.attr.first_line_color, this.l, true);
        if (this.a == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            setShouldShowKeyboard(false);
            GaanaSearchManager.a().a((Activity) this.mContext, "", "0");
            GaanaSearchManager.a().c();
            this.a = setContentView(R.layout.search_new, viewGroup);
            this.o = getChildFragmentManager();
            if (bundle == null) {
                this.m = new SearchTabFragment();
                if (getArguments() != null) {
                    z = getArguments().getBoolean("IS_TRENDING", false);
                    this.i = getArguments().getBoolean("isFromVoiceSearch", false);
                    if (z) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("IS_TRENDING", z);
                        this.m.setArguments(bundle2);
                    }
                } else {
                    z = false;
                }
                this.n = this.o.beginTransaction();
                this.n.replace(R.id.frame_container, this.m, "search_tab");
                this.n.addToBackStack(null);
                try {
                    this.n.commitAllowingStateLoss();
                } catch (IllegalStateException unused) {
                }
            } else {
                this.m = (SearchTabFragment) getChildFragmentManager().findFragmentByTag("search_tab");
                z = false;
            }
            this.k = new SearchActionBar(this.mContext, this.m);
            this.k.setSearchInterface(this);
            this.m.a(this.k);
            f();
        } else {
            z = false;
        }
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        this.d = d.a().b(0, "PREFERENCE_IS_VOICE_PROMINENT_TIME", false);
        updateView();
        setGAScreenName("Search", "Online-SearchScreen");
        Object a = n.a(d.a().c("PREFERENCE_LANGUAGE_SETTINGS", false));
        if (a != null && (a instanceof Languages)) {
            GaanaSearchManager.a().a(((Languages) a).getArrListBusinessObj());
        }
        this.currentUJPage = "SEARCH";
        MoEngage.getInstance().reportSectionViewedEvent("Search");
        if (d.a().b("PREFERENCE_VOICE_SEARCH_COACHMARK", false, true) || ((GaanaActivity) this.mContext).getCrossbuttonVisibility()) {
            ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        } else {
            ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, true);
        }
        if (Constants.ba) {
            if (this.e == null) {
                ((ViewStub) this.a.findViewById(R.id.search_new_voice_card)).inflate();
                this.e = this.a.findViewById(R.id.search_new_voice_card_container);
            }
            if (!(this.k == null || this.k.b == null || !TextUtils.isEmpty(this.k.b.getText()))) {
                this.e.setVisibility(0);
            }
            this.e.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().b("VoiceInteraction", "searchpagecard");
                    ((GaanaActivity) SearchEnchancedFragment.this.mContext).onBottomMenuLongClick();
                }
            });
        } else if (this.e != null) {
            this.e.setVisibility(8);
        }
        this.h = this.a.findViewById(R.id.back_menu);
        this.h.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((GaanaActivity) SearchEnchancedFragment.this.mContext).onBackPressed();
            }
        });
        if (z) {
            a(true);
        }
        return this.a;
    }

    private void f() {
        this.f = (Toolbar) this.a.findViewById(R.id.search_toolbar);
        this.f.setContentInsetsAbsolute(0, 0);
        this.f.addView(this.k);
        g();
        this.k.setOnSearchFocused(new SearchActionBar.b() {
            public void a() {
                if (SearchEnchancedFragment.this.j) {
                    if (!(SearchEnchancedFragment.this.m == null || SearchEnchancedFragment.this.i || !SearchEnchancedFragment.this.g)) {
                        SearchEnchancedFragment.this.i = true;
                        SearchEnchancedFragment.this.m.c();
                    }
                    SearchEnchancedFragment.this.a(true);
                }
            }
        });
        this.k.setSearchInnerActionBarVisibility(true);
        if (this.m != null) {
            this.m.a((b) this);
        }
    }

    private void a(boolean z) {
        if (!this.g) {
            if (this.h != null) {
                this.h.setVisibility(8);
            }
            this.f.animate().setListener(z ? this : null).translationY(0.0f).translationX(0.0f).scaleX(1.0f).setDuration(200).start();
            if (this.m.d() != null) {
                this.m.d().setVisibility(8);
            }
            if (this.k != null) {
                this.k.getSearchIcon().setVisibility(8);
                this.k.getBackIcon().setVisibility(0);
            }
            this.g = true;
        }
    }

    private void g() {
        if (this.h != null) {
            this.h.setVisibility(0);
        }
        float dimension = this.mContext.getResources().getDimension(R.dimen.actionbar_height);
        this.f.setTranslationY(2.0f * dimension);
        this.f.setScaleX(1.05f);
        this.f.setTranslationX(-(dimension * 0.3f));
        this.g = false;
        if (this.k != null) {
            this.k.getSearchIcon().setVisibility(0);
            this.k.getBackIcon().setVisibility(4);
        }
    }

    public void setGAScreenName(String str, String str2) {
        if (this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) {
            str2 = "Offline-SearchScreen";
        }
        sendGAScreenName(str, str2);
    }

    public void onResume() {
        this.j = true;
        super.onResume();
        this.k.setSearchInterface(this);
        GaanaSearchManager.a().a((GaanaSearchManager.b) this);
        GaanaSearchManager.a().a(SearchType.Generic);
        GaanaApplication.getInstance().setCurrentPageName(getPageName());
        if (!this.mContext.getSharedPreferences("VOICE_SEARCH_FIRST_TIME", 0).getBoolean("VOICE_SEARCH_FIRST_TIME", true)) {
            this.k.findViewById(R.id.search_voice_btn).setVisibility(0);
        }
        h();
    }

    private void h() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            if (!TextUtils.isEmpty(string) && !this.c) {
                this.c = true;
                this.k.b.setText(string);
            }
        }
    }

    public void onPause() {
        super.onPause();
        this.j = false;
    }

    public void onDestroy() {
        if (Constants.au) {
            GaanaSearchManager.a().a(ACTION_TYPE.VS_EXIT.ordinal(), 0, 0, "", 0, "");
            Constants.au = false;
        } else {
            GaanaSearchManager.a().a(ACTION_TYPE.SEARCH_EXIT.ordinal(), 0, 0, "", 0, "");
        }
        super.onDestroy();
    }

    public void onDestroyView() {
        GaanaSearchManager.a().a(null);
        ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        if (((ViewGroup) this.a.getParent()) != null) {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        }
        super.onDestroyView();
    }

    public void a(NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter) {
        if (this.m != null) {
            this.m.a(nextGenAutoSuggestAdapter);
        }
    }

    public void a(String str, String str2) {
        if (this.e != null) {
            if (TextUtils.isEmpty(str)) {
                this.e.setVisibility(0);
            } else {
                this.e.setVisibility(8);
            }
        }
        if (this.m != null && !TextUtils.isEmpty(str)) {
            if (str.length() < 3 || !str.equalsIgnoreCase(this.m.g()) || str2.equalsIgnoreCase("1")) {
                this.m.a(str);
                GaanaSearchManager.a().a((Activity) this.mContext, str, str2);
                if (str.length() > 2 && System.currentTimeMillis() > this.d + MeasurementDispatcher.MILLIS_PER_DAY) {
                    this.d = System.currentTimeMillis();
                    d.a().a(this.d, "PREFERENCE_IS_VOICE_PROMINENT_TIME", false);
                    d.a().a("PREFERENCE_IS_VOICE_PROMINENT", str.matches("[A-Za-z0-9_\\s]+") ^ 1, false);
                }
            }
        }
    }

    public void a(boolean z, boolean z2) {
        if (this.m != null) {
            this.m.b(z);
        }
    }

    public void a(Context context) {
        if (this.a != null && this.a.getWindowToken() != null) {
            Util.a(this.mContext, this.a);
        }
    }

    public void e() {
        if (this.m != null) {
            this.m.i();
        }
    }

    public void a(View view) {
        if (this.m != null) {
            this.m.b(view);
        }
    }

    public void refreshListView() {
        if (this.m != null) {
            this.m.h();
        }
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        refreshListView();
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        refreshListView();
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.SEARCH.name();
    }

    public void onFragmentScroll() {
        if (this.m != null) {
            this.m.j();
        }
        if (this.k != null) {
            this.k.setSearchText("");
        }
    }

    public void c() {
        a(false);
    }

    public void d() {
        this.i = false;
        g();
        this.k.setSearchInnerActionBarVisibility(true);
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.i && this.m != null) {
            this.i = true;
            this.m.c();
        }
    }

    public void b() {
        if (!this.i || this.m == null || this.m.b()) {
            ((GaanaActivity) this.mContext).onBackPressedHandling();
        } else {
            this.m.a();
        }
    }
}
