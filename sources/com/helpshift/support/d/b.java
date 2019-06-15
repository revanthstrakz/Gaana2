package com.helpshift.support.d;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.view.MenuItem;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.e;
import com.helpshift.e.f;
import com.helpshift.support.c.a;
import com.helpshift.support.c.c;
import com.helpshift.support.compositions.FaqFragment;
import com.helpshift.support.compositions.SectionPagerFragment;
import com.helpshift.support.fragments.QuestionListFragment;
import com.helpshift.support.fragments.SearchFragment;
import com.helpshift.support.fragments.SingleQuestionFragment;
import com.helpshift.util.n;
import com.helpshift.util.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class b implements OnActionExpandListener, OnQueryTextListener, MenuItem.OnActionExpandListener, c {
    private final String a = "key_faq_controller_state";
    private final a b;
    private FragmentManager c;
    private final boolean d;
    private final Bundle e;
    private boolean f;
    private boolean g;
    private String h = "";
    private String i = "";

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public b(a aVar, Context context, FragmentManager fragmentManager, Bundle bundle) {
        this.b = aVar;
        this.d = context.getResources().getBoolean(e.c.is_screen_large);
        this.c = fragmentManager;
        this.e = bundle;
    }

    public void a(FragmentManager fragmentManager) {
        this.c = fragmentManager;
    }

    public void a() {
        if (!this.f) {
            switch (this.e.getInt("support_mode", 0)) {
                case 2:
                    d();
                    break;
                case 3:
                    e();
                    break;
                default:
                    c();
                    break;
            }
        }
        this.f = true;
    }

    private void c() {
        com.helpshift.support.util.c.b(this.c, f.list_fragment_container, FaqFragment.a(this.e), null, true);
    }

    private void d() {
        com.helpshift.support.util.c.b(this.c, f.list_fragment_container, QuestionListFragment.a(this.e), null, false);
    }

    private void e() {
        int i = f.list_fragment_container;
        if (this.d) {
            i = f.single_question_container;
        }
        this.b.c().c().a(true);
        com.helpshift.support.util.c.b(this.c, i, SingleQuestionFragment.a(this.e, 1, this.d), null, false);
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void a(Bundle bundle) {
        if (this.d) {
            com.helpshift.support.util.c.a(this.c, f.list_fragment_container, QuestionListFragment.a(bundle), null, false);
            return;
        }
        com.helpshift.support.util.c.a(this.c, f.list_fragment_container, SectionPagerFragment.a(bundle), null, false);
    }

    public void a(String str, ArrayList<String> arrayList) {
        b();
        this.b.c().c().a(true);
        Bundle bundle = new Bundle();
        bundle.putString("questionPublishId", str);
        bundle.putStringArrayList("searchTerms", arrayList);
        if (this.d) {
            com.helpshift.support.util.c.b(this.c, f.details_fragment_container, SingleQuestionFragment.a(bundle, 1, false), null, false);
            return;
        }
        com.helpshift.support.util.c.a(this.c, f.list_fragment_container, SingleQuestionFragment.a(bundle, 1, false), null, false);
    }

    public void a(String str) {
        a(true);
        b();
        this.b.c().c().a(str);
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        if (((SearchFragment) this.c.findFragmentByTag("Helpshift_SearchFrag")) == null) {
            com.helpshift.support.util.c.a(this.c, f.list_fragment_container, SearchFragment.a(this.e), "Helpshift_SearchFrag", false);
        }
        return true;
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        b();
        if (!this.g) {
            String str = "";
            this.i = str;
            this.h = str;
            com.helpshift.support.util.c.a(this.c, SearchFragment.class.getName());
        }
        return true;
    }

    public boolean onQueryTextChange(String str) {
        if (TextUtils.isEmpty(str) && this.h.length() > 2) {
            b();
        }
        this.h = str;
        return b(str);
    }

    private boolean b(String str) {
        if (!this.g) {
            SearchFragment searchFragment = (SearchFragment) this.c.findFragmentByTag("Helpshift_SearchFrag");
            if (searchFragment != null) {
                searchFragment.a(str, this.e.getString("sectionPublishId"));
                return true;
            }
        }
        return false;
    }

    public void b() {
        if (!TextUtils.isEmpty(this.h.trim()) && !this.i.equals(this.h)) {
            this.b.c().c().a(true);
            this.e.putBoolean("search_performed", true);
            SearchFragment searchFragment = (SearchFragment) this.c.findFragmentByTag("Helpshift_SearchFrag");
            if (searchFragment != null) {
                int d = searchFragment.d();
                if (d >= 0) {
                    Map hashMap = new HashMap();
                    hashMap.put("s", this.h);
                    hashMap.put("n", Integer.valueOf(d));
                    hashMap.put("nt", Boolean.valueOf(n.a(o.b())));
                    o.d().f().a(AnalyticsEventType.PERFORMED_SEARCH, hashMap);
                    this.i = this.h;
                }
            }
        }
    }

    public void b(Bundle bundle) {
        bundle.putBoolean("key_faq_controller_state", this.f);
    }

    public void c(Bundle bundle) {
        if (!this.f && bundle.containsKey("key_faq_controller_state")) {
            this.f = bundle.getBoolean("key_faq_controller_state");
        }
    }
}
