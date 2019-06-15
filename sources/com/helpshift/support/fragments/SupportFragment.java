package com.helpshift.support.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.conversation.activeconversation.c;
import com.helpshift.e;
import com.helpshift.e.h;
import com.helpshift.e.i;
import com.helpshift.e.k;
import com.helpshift.support.ContactUsFilter;
import com.helpshift.support.ContactUsFilter.LOCATION;
import com.helpshift.support.HSSearch;
import com.helpshift.support.activities.ParentActivity;
import com.helpshift.support.c.f;
import com.helpshift.support.compositions.FaqFragment;
import com.helpshift.support.compositions.SectionPagerFragment;
import com.helpshift.support.conversations.BaseConversationFragment;
import com.helpshift.support.conversations.ConversationFragment;
import com.helpshift.support.conversations.NewConversationFragment;
import com.helpshift.support.fragments.ScreenshotPreviewFragment.LaunchSource;
import com.helpshift.support.util.g;
import com.helpshift.support.widget.b;
import com.helpshift.support.widget.b.a;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.v;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SupportFragment extends MainFragment implements OnMenuItemClickListener, OnClickListener, c<Integer>, f, b, a {
    private static boolean b;
    MenuItem a;
    private final List<String> d = Collections.synchronizedList(new ArrayList());
    private com.helpshift.support.d.c e;
    private View f;
    private View g;
    private View h;
    private boolean i;
    private MenuItem j;
    private SearchView k;
    private MenuItem l;
    private MenuItem m;
    private MenuItem n;
    private MenuItem o;
    private boolean p;
    private int q = 0;
    private int r;
    private Toolbar s;
    private boolean t;
    private Bundle u;
    private List<Integer> v;
    private WeakReference<a> w;
    private b x;

    public boolean b() {
        return false;
    }

    public static SupportFragment a(Bundle bundle) {
        SupportFragment supportFragment = new SupportFragment();
        supportFragment.setArguments(bundle);
        return supportFragment;
    }

    public com.helpshift.support.d.c c() {
        return this.e;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        o.c().a(getContext());
        setRetainInstance(true);
        if (this.e == null) {
            this.e = new com.helpshift.support.d.c(getContext(), this, k(), getArguments());
        } else {
            this.e.a(k());
        }
        if (!l()) {
            o.d().n().c();
        }
    }

    public void onStart() {
        super.onStart();
        if (getArguments() == null) {
            y();
            return;
        }
        if (!l()) {
            AnalyticsEventType analyticsEventType;
            l.a("Helpshift_SupportFrag", "Helpshift session began.");
            HSSearch.a();
            if (getArguments().getInt("support_mode", 0) == 0) {
                analyticsEventType = AnalyticsEventType.LIBRARY_OPENED;
            } else {
                analyticsEventType = AnalyticsEventType.LIBRARY_OPENED_DECOMP;
            }
            o.d().f().a(analyticsEventType);
            if (this.t) {
                this.e.c(this.u);
                this.t = false;
            }
            o.d().b();
        }
        b = true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getArguments();
        if (bundle != null) {
            this.r = bundle.getInt("toolbarId");
        }
        if (this.r == 0) {
            setHasOptionsMenu(true);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__support_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f = view.findViewById(e.f.view_no_faqs);
        this.g = view.findViewById(e.f.view_faqs_loading);
        this.h = view.findViewById(e.f.view_faqs_load_error);
        ((Button) view.findViewById(e.f.button_retry)).setOnClickListener(this);
        if (o.d().m().c()) {
            ((ImageView) view.findViewById(e.f.hs_logo)).setVisibility(8);
        }
        if (this.r != 0) {
            int i;
            this.s = (Toolbar) a(this).findViewById(this.r);
            Menu menu = this.s.getMenu();
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (i = 0; i < menu.size(); i++) {
                arrayList.add(Integer.valueOf(menu.getItem(i).getItemId()));
            }
            this.s.inflateMenu(j());
            a(this.s.getMenu());
            menu = this.s.getMenu();
            this.v = new ArrayList();
            while (i2 < menu.size()) {
                i = menu.getItem(i2).getItemId();
                if (!arrayList.contains(Integer.valueOf(i))) {
                    this.v.add(Integer.valueOf(i));
                }
                i2++;
            }
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(j(), menu);
        a(menu);
        if (!(this.w == null || this.w.get() == null)) {
            ((a) this.w.get()).j();
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.e != null) {
            this.e.d(bundle);
        }
        B().c(bundle);
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            if (this.e != null) {
                this.e.e(bundle);
            }
            B().d(bundle);
        }
    }

    public void onResume() {
        super.onResume();
        this.e.d();
        b(getString(k.hs__help_header));
        c(true);
        o.d().q().f = new AtomicReference(this);
        z();
        b(Integer.valueOf(o.d().o()));
    }

    public void onPause() {
        if (!a(this).isChangingConfigurations()) {
            A();
        }
        super.onPause();
    }

    public void onDestroyView() {
        g.a(getView());
        if (this.s != null) {
            Menu menu = this.s.getMenu();
            for (Integer intValue : this.v) {
                menu.removeItem(intValue.intValue());
            }
        }
        super.onDestroyView();
    }

    public void onStop() {
        if (!l()) {
            l.a("Helpshift_SupportFrag", "Helpshift session ended.");
            com.helpshift.b d = o.d();
            HSSearch.b();
            d.f().a(AnalyticsEventType.LIBRARY_QUIT);
            b = false;
            d.e();
            d.c();
        }
        o.d().q().f = null;
        super.onStop();
    }

    public void onDetach() {
        o.c().a(null);
        com.helpshift.util.b.a();
        if (!l()) {
            o.d().n().b();
        }
        super.onDetach();
    }

    private int j() {
        return i.hs__support_fragment;
    }

    private void a(Menu menu) {
        this.j = menu.findItem(e.f.hs__search);
        this.k = (SearchView) com.helpshift.views.b.a(this.j);
        this.a = menu.findItem(e.f.hs__contact_us);
        this.a.setTitle(k.hs__contact_us_btn);
        this.a.setOnMenuItemClickListener(this);
        com.helpshift.views.b.a(this.a).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SupportFragment.this.onMenuItemClick(SupportFragment.this.a);
            }
        });
        this.l = menu.findItem(e.f.hs__action_done);
        this.l.setOnMenuItemClickListener(this);
        this.m = menu.findItem(e.f.hs__start_new_conversation);
        this.m.setOnMenuItemClickListener(this);
        this.n = menu.findItem(e.f.hs__attach_screenshot);
        this.n.setOnMenuItemClickListener(this);
        this.o = menu.findItem(e.f.hs__conversation_information);
        this.o.setOnMenuItemClickListener(this);
        this.i = true;
        a(null);
        f();
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == e.f.hs__contact_us) {
            this.e.a(null);
            return true;
        } else if (itemId == e.f.hs__action_done) {
            this.e.h();
            return true;
        } else if (itemId == e.f.hs__start_new_conversation) {
            a(HSMenuItemType.START_NEW_CONVERSATION);
            return true;
        } else if (itemId == e.f.hs__attach_screenshot) {
            a(HSMenuItemType.SCREENSHOT_ATTACHMENT);
            return true;
        } else if (itemId != e.f.hs__conversation_information) {
            return false;
        } else {
            a(HSMenuItemType.CONVERSATION_INFO);
            return true;
        }
    }

    private void a(HSMenuItemType hSMenuItemType) {
        if (this.w != null && this.w.get() != null) {
            ((a) this.w.get()).a(hSMenuItemType);
        }
    }

    public void d() {
        if (this.i) {
            com.helpshift.views.b.a(this.j, null);
            this.k.setOnQueryTextListener(null);
        }
    }

    public void a(com.helpshift.support.d.b bVar) {
        if (this.i) {
            OnActionExpandListener bVar2;
            if (bVar2 == null) {
                FaqFlowFragment c = com.helpshift.support.util.c.c(k());
                if (c != null) {
                    bVar2 = c.d();
                }
            }
            if (bVar2 != null) {
                com.helpshift.views.b.a(this.j, bVar2);
                this.k.setOnQueryTextListener(bVar2);
            }
        }
    }

    private void n() {
        Context context = getContext();
        v.a(context, this.j.getIcon());
        v.a(context, this.a.getIcon());
        v.a(context, ((TextView) com.helpshift.views.b.a(this.a).findViewById(e.f.hs__notification_badge)).getBackground());
        v.a(context, this.l.getIcon());
        v.a(context, this.m.getIcon());
        v.a(context, this.n.getIcon());
        v.a(context, this.o.getIcon());
    }

    private void o() {
        this.j.setVisible(false);
        this.a.setVisible(false);
        this.l.setVisible(false);
        this.m.setVisible(false);
        this.n.setVisible(false);
        this.o.setVisible(false);
    }

    public void a(String str) {
        this.d.add(str);
        f();
    }

    public void d(String str) {
        this.d.remove(str);
    }

    public void e() {
        this.p = true;
        if (!this.i) {
            return;
        }
        if (this.d.contains(FaqFragment.class.getName()) || this.d.contains(QuestionListFragment.class.getName())) {
            b(true);
        }
    }

    public void f() {
        if (this.i) {
            o();
            n();
            synchronized (this.d) {
                for (String str : this.d) {
                    if (str.equals(FaqFragment.class.getName())) {
                        v();
                    } else if (str.equals(SearchFragment.class.getName())) {
                        u();
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(SingleQuestionFragment.class.getName());
                        stringBuilder.append(1);
                        if (str.equals(stringBuilder.toString())) {
                            t();
                        } else if (str.equals(SectionPagerFragment.class.getName())) {
                            s();
                        } else if (str.equals(QuestionListFragment.class.getName())) {
                            r();
                        } else {
                            if (!str.equals(NewConversationFragment.class.getName())) {
                                if (!str.equals(ConversationFragment.class.getName())) {
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append(SingleQuestionFragment.class.getName());
                                    stringBuilder.append(2);
                                    if (str.equals(stringBuilder.toString())) {
                                        p();
                                    } else if (str.equals(DynamicFormFragment.class.getName())) {
                                        w();
                                    }
                                }
                            }
                            q();
                        }
                    }
                }
            }
        }
    }

    private void p() {
        this.l.setVisible(true);
    }

    private void q() {
        d(true);
        b(false);
        a(false);
        BaseConversationFragment baseConversationFragment = (BaseConversationFragment) k().findFragmentByTag("HSNewConversationFragment");
        if (baseConversationFragment == null) {
            baseConversationFragment = (BaseConversationFragment) k().findFragmentByTag("HSConversationFragment");
        }
        if (baseConversationFragment != null) {
            this.l.setVisible(false);
        }
    }

    private void r() {
        b(this.p);
        a(ContactUsFilter.a(LOCATION.ACTION_BAR));
    }

    private void s() {
        b(true);
        a(ContactUsFilter.a(LOCATION.ACTION_BAR));
    }

    private void t() {
        if (!m()) {
            d(true);
            b(false);
        }
        a(ContactUsFilter.a(LOCATION.QUESTION_ACTION_BAR));
    }

    private void d(boolean z) {
        FaqFlowFragment faqFlowFragment = (FaqFlowFragment) k().findFragmentByTag("Helpshift_FaqFlowFrag");
        if (faqFlowFragment != null && faqFlowFragment.d() != null) {
            faqFlowFragment.d().a(z);
        }
    }

    private void u() {
        FaqFlowFragment c = com.helpshift.support.util.c.c(k());
        if (c != null) {
            SearchFragment e = com.helpshift.support.util.c.e(c.k());
            if (e != null) {
                e(e.c());
            }
        }
        a(ContactUsFilter.a(LOCATION.ACTION_BAR));
        d(false);
    }

    private void v() {
        b(this.p);
        a(ContactUsFilter.a(LOCATION.ACTION_BAR));
    }

    private void w() {
        d(true);
        a(false);
        b(false);
    }

    public void a(boolean z) {
        if (com.helpshift.views.b.b(this.j)) {
            this.a.setVisible(false);
        } else {
            this.a.setVisible(z);
        }
        x();
    }

    public void b(boolean z) {
        if (com.helpshift.views.b.b(this.j) && !this.d.contains(SearchFragment.class.getName())) {
            com.helpshift.views.b.c(this.j);
        }
        this.j.setVisible(z);
    }

    public void e(String str) {
        if (!com.helpshift.views.b.b(this.j)) {
            com.helpshift.views.b.d(this.j);
        }
        if (!TextUtils.isEmpty(str)) {
            this.k.setQuery(str, false);
        }
    }

    private void x() {
        if (this.a != null && this.a.isVisible()) {
            View a = com.helpshift.views.b.a(this.a);
            if (a != null) {
                TextView textView = (TextView) a.findViewById(e.f.hs__notification_badge);
                a = a.findViewById(e.f.hs__notification_badge_padding);
                if (this.q != 0) {
                    textView.setText(String.valueOf(this.q));
                    a.setVisibility(8);
                    textView.setVisibility(0);
                    return;
                }
                textView.setVisibility(8);
                a.setVisibility(0);
            }
        }
    }

    public void g() {
        b(Integer.valueOf(0));
    }

    public void a(int i) {
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        switch (i) {
            case 0:
                this.g.setVisibility(0);
                return;
            case 2:
                this.f.setVisibility(0);
                return;
            case 3:
                this.h.setVisibility(0);
                return;
            default:
                return;
        }
    }

    private void y() {
        Activity a = a(this);
        if (a instanceof ParentActivity) {
            a.finish();
        } else {
            ((AppCompatActivity) a).getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    private void z() {
        ConversationFragment conversationFragment = (ConversationFragment) k().findFragmentByTag("HSConversationFragment");
        if (conversationFragment != null) {
            conversationFragment.h();
        }
    }

    private void A() {
        ConversationFragment conversationFragment = (ConversationFragment) k().findFragmentByTag("HSConversationFragment");
        if (conversationFragment != null) {
            conversationFragment.i();
        }
    }

    public boolean h() {
        List<Fragment> fragments = k().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible()) {
                    if ((fragment instanceof FaqFlowFragment) || (fragment instanceof BaseConversationFragment)) {
                        FragmentManager childFragmentManager = fragment.getChildFragmentManager();
                        if (childFragmentManager.getBackStackEntryCount() > 0) {
                            childFragmentManager.popBackStack();
                            return true;
                        } else if (fragment instanceof ConversationFragment) {
                            ((ConversationFragment) fragment).i();
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        List<Fragment> fragments = k().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible() && (fragment instanceof BaseConversationFragment)) {
                    fragment.onRequestPermissionsResult(i, strArr, iArr);
                    return;
                }
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onClick(View view) {
        if (view.getId() == e.f.button_retry) {
            FaqFlowFragment c = com.helpshift.support.util.c.c(k());
            if (c != null) {
                c.f();
            }
        }
    }

    public void f(String str) {
        if (this.s != null) {
            this.s.setTitle((CharSequence) str);
            return;
        }
        ActionBar supportActionBar = ((AppCompatActivity) a(this)).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) str);
        }
    }

    public void c(boolean z) {
        if (VERSION.SDK_INT >= 21) {
            e(z);
        } else {
            f(z);
        }
    }

    @TargetApi(21)
    private void e(boolean z) {
        if (this.s == null) {
            ActionBar supportActionBar = ((AppCompatActivity) a(this)).getSupportActionBar();
            if (supportActionBar == null) {
                return;
            }
            if (z) {
                supportActionBar.setElevation(v.a(getContext(), 4.0f));
            } else {
                supportActionBar.setElevation(0.0f);
            }
        } else if (z) {
            this.s.setElevation(v.a(getContext(), 4.0f));
        } else {
            this.s.setElevation(0.0f);
        }
    }

    private void f(boolean z) {
        FrameLayout frameLayout = (FrameLayout) a(this).findViewById(e.f.flow_fragment_container);
        if (frameLayout == null) {
            return;
        }
        if (z) {
            frameLayout.setForeground(getResources().getDrawable(e.e.hs__actionbar_compat_shadow));
        } else {
            frameLayout.setForeground(new ColorDrawable(0));
        }
    }

    public void a() {
        if (getActivity() instanceof ParentActivity) {
            getActivity().finish();
        } else {
            com.helpshift.support.util.c.a(getActivity().getSupportFragmentManager(), (Fragment) this);
        }
    }

    public void b(Bundle bundle) {
        if (b) {
            this.e.c(bundle);
        } else {
            this.u = bundle;
        }
        this.t = b ^ 1;
    }

    private void b(Integer num) {
        this.q = num.intValue();
        x();
    }

    public void a(Integer num) {
        b(num);
    }

    public void a(a aVar) {
        this.w = new WeakReference(aVar);
    }

    public void b(a aVar) {
        if (this.w != null && this.w.get() == aVar) {
            this.w = null;
        }
    }

    private b B() {
        if (this.x == null) {
            synchronized (this) {
                if (this.x == null) {
                    this.x = new b(this);
                }
            }
        }
        return this.x;
    }

    public void a(boolean z, Bundle bundle) {
        if (z) {
            B().a(bundle);
        } else {
            B().b(bundle);
        }
    }

    public void a(HSMenuItemType hSMenuItemType, boolean z) {
        switch (hSMenuItemType) {
            case START_NEW_CONVERSATION:
                if (this.m != null) {
                    this.m.setVisible(z);
                    return;
                }
                return;
            case SCREENSHOT_ATTACHMENT:
                if (this.n != null) {
                    this.n.setVisible(z);
                    return;
                }
                return;
            case CONVERSATION_INFO:
                if (this.o != null) {
                    this.o.setVisible(z);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if ((i == 1 || i == 2) && intent != null && i2 == -1) {
            B().a(i, intent);
        }
    }

    public void i() {
        BaseConversationFragment baseConversationFragment = (BaseConversationFragment) k().findFragmentByTag("HSConversationFragment");
        if (baseConversationFragment == null) {
            baseConversationFragment = (BaseConversationFragment) k().findFragmentByTag("HSNewConversationFragment");
        }
        if (baseConversationFragment != null) {
            baseConversationFragment.a(true, 2);
        }
    }

    public void a(com.helpshift.conversation.dto.c cVar, Bundle bundle) {
        c().a(cVar, bundle, LaunchSource.GALLERY_APP);
    }

    public void a(int i, Long l) {
        switch (i) {
            case -4:
                g.a(getView(), k.hs__network_error_msg, -1);
                return;
            case -3:
                g.a(getView(), String.format(getResources().getString(k.hs__screenshot_limit_error), new Object[]{Float.valueOf(((float) l.longValue()) / 1048576.0f)}), -1);
                return;
            case -2:
                g.a(getView(), k.hs__screenshot_upload_error_msg, -1);
                return;
            case -1:
                g.a(getView(), k.hs__screenshot_cloud_attach_error, -1);
                return;
            default:
                return;
        }
    }
}
