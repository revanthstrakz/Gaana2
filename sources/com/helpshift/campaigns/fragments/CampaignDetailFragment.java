package com.helpshift.campaigns.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.helpshift.campaigns.i.b;
import com.helpshift.campaigns.k.a;
import com.helpshift.campaigns.l.m;
import com.helpshift.campaigns.views.AdjustableImageView;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.util.l;
import com.helpshift.views.c;
import com.payu.custombrowser.util.CBConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CampaignDetailFragment extends MainFragment implements b {
    a a;
    private String b;
    private AdjustableImageView c;
    private ProgressBar d;
    private TextView e;
    private TextView f;
    private List<Button> g;
    private ProgressBar h;
    private ViewStub i;
    private LinearLayout j;
    private ScrollView k;

    public static CampaignDetailFragment a(Bundle bundle) {
        CampaignDetailFragment campaignDetailFragment = new CampaignDetailFragment();
        campaignDetailFragment.setArguments(bundle);
        return campaignDetailFragment;
    }

    /* Access modifiers changed, original: protected */
    public boolean a() {
        return m() ^ 1;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.b = getArguments().getString("campaignId");
        com.helpshift.campaigns.f.a a = com.helpshift.campaigns.f.a.a(this.b, m.a().c, m.a().d);
        if (a != null) {
            this.a = new a(a);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a != null) {
            this.a.m();
            this.a.a((b) this);
        }
        return layoutInflater.inflate(h.hs__campaign_detail_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.c = (AdjustableImageView) view.findViewById(f.campaign_cover_image);
        this.d = (ProgressBar) view.findViewById(f.campaign_cover_image_progress);
        this.e = (TextView) view.findViewById(f.campaign_title);
        this.f = (TextView) view.findViewById(f.campaign_body);
        this.g = new ArrayList();
        this.g.add((Button) view.findViewById(f.action1_button));
        this.g.add((Button) view.findViewById(f.action2_button));
        this.g.add((Button) view.findViewById(f.action3_button));
        this.g.add((Button) view.findViewById(f.action4_button));
        this.h = (ProgressBar) view.findViewById(f.progress_bar);
        this.k = (ScrollView) view.findViewById(f.campaign_detail_view_container);
        this.i = (ViewStub) view.findViewById(f.hs__campaign_expired_view_stub);
        l.a("Helpshift_CampDetails", "Showing Campaign details");
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        if (this.a != null) {
            View view = getView();
            if (this.a.j()) {
                if (this.j == null) {
                    this.j = (LinearLayout) this.i.inflate();
                }
                this.j.setVisibility(0);
                this.k.setVisibility(8);
                if (view != null) {
                    view.setBackgroundColor(0);
                    return;
                }
                return;
            }
            if (this.j != null) {
                this.j.setVisibility(8);
            }
            this.k.setVisibility(0);
            if (TextUtils.isEmpty(this.a.e())) {
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(8);
            }
            HashMap d = this.a.d();
            Bitmap bitmap = (Bitmap) d.get("bitmap");
            if (bitmap != null) {
                this.c.setImageBitmap(bitmap);
                if (d.containsKey(CBConstant.DEFAULT_VALUE)) {
                    this.d.setVisibility(0);
                } else {
                    this.d.setVisibility(8);
                }
            }
            this.e.setText(this.a.e());
            if (!TextUtils.isEmpty(this.a.f())) {
                try {
                    this.e.setTextColor(Color.parseColor(this.a.f()));
                } catch (IllegalArgumentException e) {
                    l.a("Helpshift_CampDetails", "Error while parsing title color", e);
                }
            }
            this.f.setText(this.a.g());
            if (!TextUtils.isEmpty(this.a.h())) {
                try {
                    this.f.setTextColor(Color.parseColor(this.a.h()));
                } catch (IllegalArgumentException e2) {
                    l.a("Helpshift_CampDetails", "Error while parsing body color", e2);
                }
            }
            if (!(view == null || TextUtils.isEmpty(this.a.i()))) {
                try {
                    view.setBackgroundColor(Color.parseColor(this.a.i()));
                } catch (IllegalArgumentException e3) {
                    l.a("Helpshift_CampDetails", "Error while parsing background color", e3);
                }
            }
            for (int i = 0; i < this.a.k(); i++) {
                Button button = (Button) this.g.get(i);
                button.setText(this.a.a(i));
                button.setTextColor(Color.parseColor(this.a.b(i)));
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        CampaignDetailFragment.this.a.a(i, CampaignDetailFragment.this.getActivity());
                    }
                });
                button.setVisibility(0);
            }
            return;
        }
        c.a(getView(), k.hs__data_not_found_msg, 0).show();
    }

    public void onResume() {
        super.onResume();
        d(getString(k.hs__cam_message));
        b();
        if (this.a != null) {
            this.a.l();
            com.helpshift.util.b.a(this.b);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Campaign title : ");
            stringBuilder.append(this.a.e());
            l.a("Helpshift_CampDetails", stringBuilder.toString());
        }
    }

    public void onPause() {
        super.onPause();
        if (this.a != null) {
            this.a.n();
            this.a.b((b) this);
        }
    }

    public void onStop() {
        super.onStop();
        if (!l() && !n()) {
            InboxFragment a = com.helpshift.campaigns.m.a.a(this);
            if (a != null) {
                a.a(false);
            }
        }
    }

    public void c() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                CampaignDetailFragment.this.b();
            }
        });
    }
}
