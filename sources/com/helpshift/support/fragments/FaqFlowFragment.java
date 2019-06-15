package com.helpshift.support.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.support.c.a;
import com.helpshift.support.c.c;
import com.helpshift.support.compositions.FaqFragment;
import com.helpshift.support.d.b;
import com.helpshift.support.f.g;
import java.util.List;

public class FaqFlowFragment extends MainFragment implements a {
    private b a;
    private View b;
    private View d;
    private List<g> e;

    public boolean b() {
        return false;
    }

    public static FaqFlowFragment a(Bundle bundle, List<g> list) {
        FaqFlowFragment faqFlowFragment = new FaqFlowFragment();
        faqFlowFragment.setArguments(bundle);
        faqFlowFragment.e = list;
        return faqFlowFragment;
    }

    public b d() {
        return this.a;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.a == null) {
            this.a = new b(this, context, k(), getArguments());
        } else {
            this.a.a(k());
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__faq_flow_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = view.findViewById(f.vertical_divider);
        this.d = view.findViewById(f.select_question_view);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.a != null) {
            this.a.b(bundle);
        }
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null && this.a != null) {
            this.a.c(bundle);
        }
    }

    public void onResume() {
        super.onResume();
        com.helpshift.support.f.b.a(this.e);
        c().a(this.a);
        this.a.a();
        e();
    }

    public void e() {
        if (m() && this.d != null) {
            if (k().findFragmentById(f.details_fragment_container) == null) {
                a(true);
            } else {
                a(false);
            }
        }
    }

    public void a(boolean z) {
        if (this.d == null) {
            return;
        }
        if (z) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        this.a = null;
        c().d();
    }

    public SupportFragment c() {
        return (SupportFragment) getParentFragment();
    }

    public c a() {
        return d();
    }

    public void b(boolean z) {
        if (this.b == null) {
            return;
        }
        if (z) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
    }

    public void f() {
        FaqFragment d = com.helpshift.support.util.c.d(k());
        if (d != null) {
            d.c();
        }
    }

    public List<g> g() {
        return this.e;
    }
}
