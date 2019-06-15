package com.helpshift.support.compositions;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.support.FaqTagFilter;
import com.helpshift.support.Section;
import com.helpshift.support.c.c;
import com.helpshift.support.d;
import com.helpshift.support.fragments.FaqFlowFragment;
import com.helpshift.support.fragments.MainFragment;
import com.helpshift.support.fragments.QuestionListFragment;
import com.helpshift.support.fragments.SectionListFragment;
import com.helpshift.support.fragments.SupportFragment;
import com.helpshift.support.util.g;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FaqFragment extends MainFragment implements com.helpshift.support.c.b {
    int a = 0;
    boolean b;
    private FaqTagFilter d;
    private d e;

    private static class a extends Handler {
        private final WeakReference<FaqFragment> a;
        private final int b = 42;

        public a(FaqFragment faqFragment) {
            this.a = new WeakReference(faqFragment);
        }

        public void handleMessage(Message message) {
            FaqFragment faqFragment = (FaqFragment) this.a.get();
            if (faqFragment != null && faqFragment.getHost() != null && !faqFragment.isDetached()) {
                int i = this.b;
                if (message.obj instanceof HashMap) {
                    Object obj = ((HashMap) message.obj).get("status");
                    if (obj instanceof Integer) {
                        i = ((Integer) obj).intValue();
                    }
                }
                g.a(i, faqFragment.getView());
                if (faqFragment.a == 0) {
                    faqFragment.a(3);
                } else {
                    faqFragment.a(1);
                }
            }
        }
    }

    private static class b extends Handler {
        private final WeakReference<FaqFragment> a;

        public b(FaqFragment faqFragment) {
            this.a = new WeakReference(faqFragment);
        }

        public void handleMessage(Message message) {
            FaqFragment faqFragment = (FaqFragment) this.a.get();
            if (faqFragment != null && faqFragment.getHost() != null && !faqFragment.isDetached()) {
                ArrayList arrayList = (ArrayList) message.obj;
                int i = message.what;
                if (arrayList != null) {
                    arrayList = faqFragment.a(arrayList);
                    faqFragment.a = arrayList.size();
                }
                if (i == com.helpshift.support.b.a.a) {
                    if (faqFragment.a != 0) {
                        faqFragment.a(1);
                        faqFragment.a(faqFragment, arrayList);
                    }
                } else if (i == com.helpshift.support.b.a.d) {
                    if (faqFragment.a == 0) {
                        faqFragment.a(2);
                    } else {
                        faqFragment.b = true;
                        faqFragment.a(1);
                        faqFragment.a(faqFragment, arrayList);
                    }
                } else if (i == com.helpshift.support.b.a.c && faqFragment.a == 0) {
                    faqFragment.a(2);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Faq loaded with ");
                stringBuilder.append(faqFragment.a);
                stringBuilder.append(" sections");
                l.a("Helpshift_FaqFragment", stringBuilder.toString());
            }
        }
    }

    public boolean b() {
        return true;
    }

    public static FaqFragment a(Bundle bundle) {
        FaqFragment faqFragment = new FaqFragment();
        faqFragment.setArguments(bundle);
        return faqFragment;
    }

    public c a() {
        return ((com.helpshift.support.c.b) getParentFragment()).a();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(FaqFragment faqFragment, ArrayList<Section> arrayList) {
        if (faqFragment.k().findFragmentById(f.faq_fragment_container) == null || this.b) {
            ArrayList a = faqFragment.e.a((ArrayList) arrayList, faqFragment.d);
            Bundle bundle;
            if (a.size() == 1) {
                bundle = new Bundle();
                bundle.putString("sectionPublishId", ((Section) a.get(0)).a());
                bundle.putSerializable("withTagsMatching", getArguments().getSerializable("withTagsMatching"));
                try {
                    com.helpshift.support.util.c.a(faqFragment.k(), f.faq_fragment_container, QuestionListFragment.a(bundle), null, null, false, this.b);
                    this.b = false;
                } catch (IllegalStateException unused) {
                }
            } else {
                bundle = new Bundle();
                bundle.putParcelableArrayList("sections", a);
                bundle.putSerializable("withTagsMatching", getArguments().getSerializable("withTagsMatching"));
                com.helpshift.support.util.c.a(faqFragment.k(), f.faq_fragment_container, SectionListFragment.a(bundle), null, null, false, this.b);
                this.b = false;
            }
            d();
        }
    }

    private void d() {
        SupportFragment a = com.helpshift.support.util.c.a((Fragment) this);
        if (a != null) {
            a.e();
        }
    }

    public void a(int i) {
        FaqFlowFragment faqFlowFragment = (FaqFlowFragment) getParentFragment();
        SupportFragment supportFragment = faqFlowFragment != null ? (SupportFragment) faqFlowFragment.getParentFragment() : null;
        if (supportFragment != null) {
            if (i == 1) {
                faqFlowFragment.b(true);
                faqFlowFragment.e();
            } else {
                faqFlowFragment.b(false);
                faqFlowFragment.a(false);
            }
            supportFragment.a(i);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getArguments();
        if (bundle != null) {
            this.d = (FaqTagFilter) bundle.getSerializable("withTagsMatching");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__faq_fragment, viewGroup, false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.e = new d(context);
    }

    public void onResume() {
        super.onResume();
        b(getString(k.hs__help_header));
        if (this.a == 0) {
            a(0);
        }
        this.e.a(new b(this), new a(this), this.d);
        if (!l()) {
            o.d().f().a(AnalyticsEventType.SUPPORT_LAUNCH);
        }
    }

    public void onDestroyView() {
        g.a(getView());
        super.onDestroyView();
    }

    public void onStop() {
        super.onStop();
        a(1);
    }

    public void c() {
        if (this.a == 0) {
            a(0);
        }
        this.e.a(new b(this), new a(this), this.d);
    }

    /* Access modifiers changed, original: 0000 */
    public ArrayList<Section> a(ArrayList<Section> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Section section = (Section) it.next();
            ArrayList a = this.e.a(section.a(), this.d);
            if (!(a == null || a.isEmpty())) {
                arrayList2.add(section);
            }
        }
        return arrayList2;
    }
}
