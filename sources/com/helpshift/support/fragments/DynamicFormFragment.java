package com.helpshift.support.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.support.a.a;
import com.helpshift.support.d.c;
import com.helpshift.support.f.e;
import com.helpshift.support.f.g;
import com.helpshift.util.o;
import java.util.List;

public class DynamicFormFragment extends MainFragment implements OnClickListener {
    private c a;
    private RecyclerView b;
    private List<g> d;
    private boolean e = true;
    private String f;

    public boolean b() {
        return true;
    }

    public static DynamicFormFragment a(Bundle bundle, List<g> list, c cVar) {
        DynamicFormFragment dynamicFormFragment = new DynamicFormFragment();
        dynamicFormFragment.setArguments(bundle);
        dynamicFormFragment.d = list;
        dynamicFormFragment.a = cVar;
        return dynamicFormFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        bundle = getArguments();
        if (bundle != null) {
            this.f = bundle.getString("flow_title");
            if (TextUtils.isEmpty(this.f)) {
                this.f = getString(k.hs__help_header);
            }
        }
    }

    public void a(c cVar) {
        this.a = cVar;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__dynamic_form_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = (RecyclerView) view.findViewById(f.flow_list);
        this.b.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public void onResume() {
        super.onResume();
        b(this.f);
        a();
    }

    private void a() {
        if (this.d != null) {
            this.b.setAdapter(new a(this.d, this));
        }
    }

    public void onClick(View view) {
        g gVar = (g) this.d.get(((Integer) view.getTag()).intValue());
        this.e = false;
        a(gVar);
    }

    private void a(g gVar) {
        if (gVar instanceof com.helpshift.support.f.a) {
            ((com.helpshift.support.f.a) gVar).a(this.a);
        } else if (gVar instanceof e) {
            ((e) gVar).a(this.a);
        } else if (gVar instanceof com.helpshift.support.f.h) {
            ((com.helpshift.support.f.h) gVar).a(this.a);
        } else if (gVar instanceof com.helpshift.support.f.c) {
            ((com.helpshift.support.f.c) gVar).a(this.a);
        } else if (gVar instanceof com.helpshift.support.f.f) {
            ((com.helpshift.support.f.f) gVar).a(this.a);
        }
        gVar.c();
    }

    public void onStart() {
        super.onStart();
        if (!l() && this.e) {
            o.d().f().a(AnalyticsEventType.DYNAMIC_FORM_OPEN);
        }
        this.e = true;
    }

    public void onStop() {
        super.onStop();
        if (!l() && this.e) {
            o.d().f().a(AnalyticsEventType.DYNAMIC_FORM_CLOSE);
        }
    }
}
