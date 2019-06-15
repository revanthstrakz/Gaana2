package com.helpshift.support.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.helpshift.support.FaqTagFilter;
import com.helpshift.support.Section;
import com.helpshift.support.c.c;
import com.helpshift.support.d;
import com.helpshift.support.util.g;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionListFragment extends MainFragment {
    private d a;
    private FaqTagFilter b;
    private String d;
    private String e;
    private RecyclerView f;
    private OnClickListener g;
    private boolean h = false;
    private boolean i = false;

    private static class a extends Handler {
        private final WeakReference<QuestionListFragment> a;

        public a(QuestionListFragment questionListFragment) {
            this.a = new WeakReference(questionListFragment);
        }

        public void handleMessage(Message message) {
            QuestionListFragment questionListFragment = (QuestionListFragment) this.a.get();
            if (questionListFragment != null && !questionListFragment.isDetached()) {
                Integer valueOf = Integer.valueOf(103);
                if (message.obj instanceof HashMap) {
                    valueOf = (Integer) ((HashMap) message.obj).get("status");
                }
                g.a(valueOf.intValue(), questionListFragment.getView());
            }
        }
    }

    private static class b extends Handler {
        private final WeakReference<QuestionListFragment> a;

        public b(QuestionListFragment questionListFragment) {
            this.a = new WeakReference(questionListFragment);
        }

        public void handleMessage(Message message) {
            QuestionListFragment questionListFragment = (QuestionListFragment) this.a.get();
            if (questionListFragment != null && !questionListFragment.isDetached()) {
                if (message.obj != null) {
                    Section section = (Section) message.obj;
                    questionListFragment.a(section);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("FAQ section loaded : SectionSuccessHandler : ");
                    stringBuilder.append(section.b());
                    l.a("Helpshift_QstnListFrag", stringBuilder.toString());
                    return;
                }
                g.a(103, questionListFragment.getView());
            }
        }
    }

    public static QuestionListFragment a(Bundle bundle) {
        QuestionListFragment questionListFragment = new QuestionListFragment();
        questionListFragment.setArguments(bundle);
        return questionListFragment;
    }

    public c a() {
        return ((com.helpshift.support.c.b) getParentFragment()).a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getArguments();
        if (bundle != null) {
            this.b = (FaqTagFilter) bundle.getSerializable("withTagsMatching");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__question_list_fragment, viewGroup, false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.a = new d(context);
        this.d = getString(k.hs__help_header);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f = (RecyclerView) view.findViewById(f.question_list);
        this.f.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.g = new OnClickListener() {
            public void onClick(View view) {
                QuestionListFragment.this.a().a((String) view.getTag(), null);
            }
        };
        String string = getArguments().getString("sectionPublishId");
        if (m()) {
            String a = a(string);
            if (!TextUtils.isEmpty(a)) {
                this.d = a;
            }
        }
        Handler bVar = new b(this);
        Handler aVar = new a(this);
        if (getArguments().getInt("support_mode", 0) != 2) {
            this.a.a(string, bVar, aVar);
        } else {
            this.a.a(string, bVar, aVar, this.b);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FAQ section loaded : Name : ");
        stringBuilder.append(this.d);
        l.a("Helpshift_QstnListFrag", stringBuilder.toString());
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Section section) {
        ArrayList a = this.a.a(section.a(), this.b);
        if (a != null && !a.isEmpty()) {
            this.f.setAdapter(new com.helpshift.support.a.b(a, this.g));
            SupportFragment a2 = com.helpshift.support.util.c.a((Fragment) this);
            if (a2 != null) {
                a2.e();
            }
            if (TextUtils.isEmpty(this.e)) {
                d(getArguments().getString("sectionPublishId"));
            }
            c();
        } else if (!isDetached()) {
            g.a(103, getView());
        }
    }

    private String a(String str) {
        Section c = this.a.c(str);
        return c != null ? c.b() : null;
    }

    private void d(String str) {
        Section c = this.a.c(str);
        if (c != null) {
            this.e = c.c();
        }
    }

    public void onResume() {
        super.onResume();
        b(getString(k.hs__help_header));
        if (m()) {
            b(this.d);
            Fragment parentFragment = getParentFragment();
            if (parentFragment instanceof FaqFlowFragment) {
                ((FaqFlowFragment) parentFragment).b(true);
            }
        }
        c();
    }

    public void onStart() {
        super.onStart();
        this.i = l();
        this.h = false;
    }

    public boolean b() {
        return getParentFragment() instanceof FaqFlowFragment;
    }

    public void onDestroyView() {
        g.a(getView());
        super.onDestroyView();
    }

    public void onStop() {
        if (m()) {
            b(getString(k.hs__help_header));
        }
        super.onStop();
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        c();
    }

    private void c() {
        if (getUserVisibleHint() && !this.h && !this.i && !TextUtils.isEmpty(this.e)) {
            o.d().f().a(AnalyticsEventType.BROWSED_FAQ_LIST, this.e);
            this.h = true;
        }
    }
}
