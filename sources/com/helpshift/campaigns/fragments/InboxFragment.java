package com.helpshift.campaigns.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.helpshift.campaigns.g.a;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.k.b;

public class InboxFragment extends MainFragment implements a {
    private boolean a;
    private String b;
    private Toolbar c;

    public static InboxFragment a(Bundle bundle) {
        InboxFragment inboxFragment = new InboxFragment();
        inboxFragment.setArguments(bundle);
        return inboxFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(h.hs__campaign_inbox_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        int i;
        super.onViewCreated(view, bundle);
        this.c = (Toolbar) a((Fragment) this).findViewById(f.toolbar);
        bundle = getArguments();
        if (bundle != null) {
            i = bundle.getInt("launch_source", 0);
        } else {
            i = 0;
        }
        if (i == 1 || i == 3) {
            if (n()) {
                f();
            }
            this.b = bundle.getString("campaignId");
            c(false);
        } else {
            f();
            if (this.a) {
                c(true);
            }
        }
        b();
        Boolean bool = b.a().a.g;
        if (bool != null && bool.booleanValue()) {
            ((ImageView) view.findViewById(f.hs_logo)).setVisibility(8);
        }
    }

    private void f() {
        Fragment findFragmentById = k().findFragmentById(f.inbox_fragment_container);
        if (findFragmentById == null) {
            g();
        } else if (m() && !(findFragmentById instanceof CampaignListFragment)) {
            c();
            g();
        }
    }

    private void g() {
        String name = CampaignListFragment.class.getName();
        com.helpshift.campaigns.m.a.a(k(), f.inbox_fragment_container, CampaignListFragment.b(), name, null, false);
    }

    private void c(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("campaignId", this.b);
        String name = CampaignDetailFragment.class.getName();
        if (k().findFragmentByTag(name) == null || m()) {
            CampaignDetailFragment a = CampaignDetailFragment.a(bundle);
            if (n()) {
                com.helpshift.campaigns.m.a.a(k(), f.detail_fragment_container, a, name, null, false);
            } else {
                com.helpshift.campaigns.m.a.a(k(), f.inbox_fragment_container, a, name, z ? InboxFragment.class.getName() : null, false);
            }
        }
    }

    public void onResume() {
        super.onResume();
        b(true);
    }

    public void b() {
        View view = getView();
        view = view != null ? view.findViewById(f.select_campaign_view) : null;
        if (n() && view != null) {
            if (this.a) {
                a(false, view);
            } else {
                a(true, view);
            }
        }
    }

    public void a(boolean z, View view) {
        if (view == null) {
            return;
        }
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    public void a(String str) {
        if (this.c != null) {
            this.c.setTitle((CharSequence) str);
            return;
        }
        ActionBar supportActionBar = ((AppCompatActivity) a((Fragment) this)).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) str);
        }
    }

    public boolean c() {
        FragmentManager k = k();
        if (k.getBackStackEntryCount() <= 0) {
            return true;
        }
        k.popBackStack();
        return false;
    }

    public void b(String str) {
        this.a = true;
        this.b = str;
        c(true);
        b();
    }

    public void c(String str) {
        if (n() && !TextUtils.isEmpty(str) && str.equals(this.b)) {
            CampaignDetailFragment campaignDetailFragment = (CampaignDetailFragment) k().findFragmentById(f.detail_fragment_container);
            if (campaignDetailFragment != null) {
                com.helpshift.campaigns.m.a.a(k(), campaignDetailFragment);
                this.a = false;
                b();
            }
        }
    }

    public void a(boolean z) {
        this.a = z;
    }

    public boolean e() {
        return this.a;
    }

    public void b(Menu menu) {
        CampaignListFragment campaignListFragment = (CampaignListFragment) k().findFragmentById(f.inbox_fragment_container);
        if (campaignListFragment != null) {
            campaignListFragment.b(menu);
        }
    }
}
