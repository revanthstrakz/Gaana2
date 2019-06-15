package com.helpshift.campaigns.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpshift.campaigns.a.a;
import com.helpshift.campaigns.i.e;
import com.helpshift.campaigns.k.b;
import com.helpshift.campaigns.l.m;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.i;
import com.helpshift.e.k;
import com.helpshift.util.l;
import com.helpshift.util.v;
import com.helpshift.views.c;

public class CampaignListFragment extends MainFragment implements e {
    a a;
    b b;
    MenuItem c;
    private OnClickListener d;
    private TextView e;
    private Snackbar f;
    private SearchView g;
    private boolean h = false;

    public static CampaignListFragment b() {
        return new CampaignListFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = new b(new com.helpshift.campaigns.f.b(m.a().c));
        InboxFragment a = com.helpshift.campaigns.m.a.a(this);
        if (n() || !(a == null || a.e())) {
            this.b.j();
            this.b.a((e) this);
        }
        this.h = true;
        this.d = new OnClickListener() {
            public void onClick(View view) {
                String str = (String) view.getTag();
                if (com.helpshift.views.b.b(CampaignListFragment.this.c)) {
                    CampaignListFragment.this.b.b(true);
                    CampaignListFragment.this.b.a(true);
                }
                CampaignListFragment.this.j().b(str);
            }
        };
        return layoutInflater.inflate(h.hs__campaign_list_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(f.inbox_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.a = new a(this.b, this.d);
        recyclerView.setAdapter(this.a);
        new ItemTouchHelper(new com.helpshift.campaigns.b.a(getContext(), this)).attachToRecyclerView(recyclerView);
        this.e = (TextView) view.findViewById(f.view_no_campaigns);
        c();
        l.a("Helpshift_CampaignList", "Showing Campaigns list fragment");
    }

    public void onResume() {
        super.onResume();
        d(getString(k.hs__cam_inbox));
        if (!this.h) {
            this.b.j();
            this.b.a((e) this);
        }
        this.b.l();
        f();
    }

    public void onPause() {
        super.onPause();
        p();
        this.b.k();
        this.b.b((e) this);
        this.h = false;
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        if (this.b.e() == 0) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void a(final int i, boolean z) {
        j().c(this.b.b(i));
        if (z) {
            this.f = c.a(getView(), k.hs__cam_message_deleted, 0).setAction(k.hs__cam_undo, new OnClickListener() {
                public void onClick(View view) {
                    CampaignListFragment.this.b.g();
                    CampaignListFragment.this.a.notifyItemInserted(i);
                    CampaignListFragment.this.c();
                }
            }).setCallback(new Callback() {
                public void onDismissed(Snackbar snackbar, int i) {
                    super.onDismissed(snackbar, i);
                    if (i != 1 && i != 4) {
                        CampaignListFragment.this.b.f();
                    }
                }
            });
            this.f.show();
        }
        this.a.a(i, z);
        c();
    }

    /* Access modifiers changed, original: protected */
    public int d() {
        return i.hs__campaign_list_menu;
    }

    /* Access modifiers changed, original: protected */
    public void a(Menu menu) {
        this.c = menu.findItem(f.hs__search);
        this.g = (SearchView) com.helpshift.views.b.a(this.c);
        this.g.setOnQueryTextListener(this.b);
        com.helpshift.views.b.a(this.c, this.b);
        v.a(getContext(), this.c.getIcon());
        o();
    }

    private void o() {
        if (this.b.h()) {
            a(this.b.i());
            this.b.a(false);
        }
    }

    public void a(String str) {
        if (!com.helpshift.views.b.b(this.c)) {
            com.helpshift.views.b.d(this.c);
        }
        if (!TextUtils.isEmpty(str)) {
            this.g.setQuery(str, false);
        }
    }

    public void onStop() {
        super.onStop();
        if (l() && this.c != null && com.helpshift.views.b.b(this.c)) {
            this.b.a(true);
        } else if (n() && !l()) {
            this.b.a(false);
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        try {
            int a = this.a.a();
            int itemId = menuItem.getItemId();
            if (itemId == f.delete_campaign) {
                a(a, false);
            } else if (itemId == f.mark_campaign_as_read) {
                this.a.a(a);
            }
            this.a.b(-1);
            return super.onContextItemSelected(menuItem);
        } catch (Exception unused) {
            return super.onContextItemSelected(menuItem);
        }
    }

    public void b(Menu menu) {
        this.a.b(-1);
    }

    public int e() {
        return this.a.a();
    }

    public void onDetach() {
        super.onDetach();
    }

    public void f() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                CampaignListFragment.this.a.notifyDataSetChanged();
                CampaignListFragment.this.c();
            }
        });
    }

    public void g() {
        p();
    }

    public void h() {
        p();
    }

    public void i() {
        p();
    }

    private void p() {
        if (this.f != null && this.f.isShown()) {
            this.f.dismiss();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public com.helpshift.campaigns.g.a j() {
        return (com.helpshift.campaigns.g.a) getParentFragment();
    }
}
