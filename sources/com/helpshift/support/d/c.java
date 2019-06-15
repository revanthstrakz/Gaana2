package com.helpshift.support.d;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.text.TextUtils;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.conversation.activeconversation.a;
import com.helpshift.support.c.d;
import com.helpshift.support.c.e;
import com.helpshift.support.c.f;
import com.helpshift.support.conversations.ConversationFragment;
import com.helpshift.support.conversations.NewConversationFragment;
import com.helpshift.support.f.b;
import com.helpshift.support.f.g;
import com.helpshift.support.fragments.ConversationInfoFragment;
import com.helpshift.support.fragments.DynamicFormFragment;
import com.helpshift.support.fragments.FaqFlowFragment;
import com.helpshift.support.fragments.ScreenshotPreviewFragment;
import com.helpshift.support.fragments.ScreenshotPreviewFragment.LaunchSource;
import com.helpshift.support.fragments.ScreenshotPreviewFragment.ScreenshotAction;
import com.helpshift.support.fragments.SearchResultFragment;
import com.helpshift.support.fragments.SingleQuestionFragment;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c implements d, e {
    private final String a = "key_support_controller_started";
    private final String b = "key_conversation_bundle";
    private final String c = "key_conversation_add_to_back_stack";
    private final Context d;
    private final f e;
    private FragmentManager f;
    private final Bundle g;
    private Bundle h;
    private boolean i;
    private int j;
    private boolean k = false;
    private boolean l;
    private String m;

    public c(Context context, f fVar, FragmentManager fragmentManager, Bundle bundle) {
        this.d = context;
        this.e = fVar;
        this.f = fragmentManager;
        this.g = bundle;
    }

    public void a(FragmentManager fragmentManager) {
        this.f = fragmentManager;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void d() {
        if (!this.i) {
            this.j = this.g.getInt("support_mode", 0);
            int i = this.j;
            if (i == 1) {
                a(this.g, false);
            } else if (i != 4) {
                a(this.g, false, b.a());
            } else {
                a(com.helpshift.support.f.d.a(), false);
            }
        }
        this.i = true;
    }

    /* JADX WARNING: Missing block: B:35:0x00c1, code skipped:
            if ((r0 instanceof com.helpshift.support.conversations.BaseConversationFragment) == false) goto L_0x00c5;
     */
    private void f(android.os.Bundle r7) {
        /*
        r6 = this;
        r0 = "conversationIdInPush";
        r0 = r7.getLong(r0);
        r0 = java.lang.Long.valueOf(r0);
        r1 = r6.h;
        if (r1 == 0) goto L_0x001b;
    L_0x000e:
        r1 = r6.h;
        r2 = "issueId";
        r1 = r1.getLong(r2);
        r1 = java.lang.Long.valueOf(r1);
        goto L_0x001c;
    L_0x001b:
        r1 = 0;
    L_0x001c:
        r0 = r0.equals(r1);
        r1 = 1;
        r0 = r0 ^ r1;
        r2 = r6.f;
        r2 = r2.getFragments();
        r3 = 0;
        if (r0 == 0) goto L_0x0092;
    L_0x002b:
        r0 = r2.size();
        r0 = r0 - r1;
    L_0x0030:
        if (r0 < 0) goto L_0x005c;
    L_0x0032:
        r4 = r2.get(r0);
        r4 = (android.support.v4.app.Fragment) r4;
        r5 = r4 instanceof com.helpshift.support.fragments.ScreenshotPreviewFragment;
        if (r5 != 0) goto L_0x0044;
    L_0x003c:
        r5 = r4 instanceof com.helpshift.support.conversations.BaseConversationFragment;
        if (r5 != 0) goto L_0x0044;
    L_0x0040:
        r5 = r4 instanceof com.helpshift.support.fragments.ConversationInfoFragment;
        if (r5 == 0) goto L_0x0059;
    L_0x0044:
        if (r0 != 0) goto L_0x004c;
    L_0x0046:
        r5 = r6.f;
        com.helpshift.support.util.c.a(r5, r4);
        goto L_0x0059;
    L_0x004c:
        r5 = r6.f;
        r4 = r4.getClass();
        r4 = r4.getName();
        com.helpshift.support.util.c.b(r5, r4);
    L_0x0059:
        r0 = r0 + -1;
        goto L_0x0030;
    L_0x005c:
        r0 = r6.f;
        r2 = "HSConversationFragment";
        r0 = r0.findFragmentByTag(r2);
        if (r0 == 0) goto L_0x0075;
    L_0x0066:
        r2 = r6.f;
        r0 = r0.getClass();
        r0 = r0.getName();
        com.helpshift.support.util.c.b(r2, r0);
        r3 = r1;
        goto L_0x005c;
    L_0x0075:
        r0 = r6.f;
        r2 = "HSConversationInfoFragment";
        r0 = r0.findFragmentByTag(r2);
        if (r0 == 0) goto L_0x008d;
    L_0x007f:
        r2 = r6.f;
        r0 = r0.getClass();
        r0 = r0.getName();
        com.helpshift.support.util.c.b(r2, r0);
        goto L_0x0075;
    L_0x008d:
        if (r3 != 0) goto L_0x00c5;
    L_0x008f:
        r6.l = r1;
        goto L_0x00c5;
    L_0x0092:
        r0 = r2.size();
        if (r0 <= 0) goto L_0x00c5;
    L_0x0098:
        r0 = r2.size();
        r0 = r0 - r1;
        r0 = r2.get(r0);
        r0 = (android.support.v4.app.Fragment) r0;
        r2 = r0 instanceof com.helpshift.support.fragments.ScreenshotPreviewFragment;
        if (r2 == 0) goto L_0x00a8;
    L_0x00a7:
        return;
    L_0x00a8:
        r2 = r0 instanceof com.helpshift.support.fragments.ConversationInfoFragment;
        if (r2 == 0) goto L_0x00bf;
    L_0x00ac:
        r1 = r6.f;
        com.helpshift.support.util.c.a(r1, r0);
        r1 = r6.f;
        r0 = r0.getClass();
        r0 = r0.getName();
        com.helpshift.support.util.c.b(r1, r0);
        goto L_0x00c4;
    L_0x00bf:
        r0 = r0 instanceof com.helpshift.support.conversations.BaseConversationFragment;
        if (r0 != 0) goto L_0x00c4;
    L_0x00c3:
        goto L_0x00c5;
    L_0x00c4:
        r1 = r3;
    L_0x00c5:
        if (r1 == 0) goto L_0x00cc;
    L_0x00c7:
        r6.h = r7;
        r6.e();
    L_0x00cc:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.d.c.f(android.os.Bundle):void");
    }

    public void a(Bundle bundle, boolean z) {
        this.l = z;
        this.h = bundle;
        e();
    }

    public void e() {
        if (this.h == null) {
            this.h = this.g;
        }
        long j = this.h.getLong("conversationIdInPush", 0);
        if (j != 0) {
            this.h.remove("conversationIdInPush");
            if (o.d().q().a(j)) {
                a(Long.valueOf(j));
                return;
            }
        }
        a a = o.d().a();
        Long l = null;
        if (a != null) {
            l = a.a;
        }
        if (l == null) {
            List a2 = b.a();
            if (a2 == null || a2.isEmpty()) {
                i();
            } else {
                BackStackEntry backStackEntryAt = g().getBackStackEntryAt(this.f.getBackStackEntryCount() - 1);
                if (backStackEntryAt != null) {
                    String name = backStackEntryAt.getName();
                    if (name != null && name.equals(ConversationFragment.class.getName())) {
                        com.helpshift.support.util.c.b(this.f, name);
                    }
                }
                a(a2, true);
            }
        } else {
            a(l);
        }
    }

    public void a(Bundle bundle, boolean z, List<g> list) {
        com.helpshift.support.util.c.a(this.f, com.helpshift.e.f.flow_fragment_container, FaqFlowFragment.a(bundle, list), "Helpshift_FaqFlowFrag", z ? FaqFlowFragment.class.getName() : null, false, false);
    }

    private void i() {
        String name;
        l.a("Helpshift_SupportContr", "Starting new conversation fragment");
        this.h.putBoolean("search_performed", this.k);
        this.h.putString("source_search_query", this.m);
        NewConversationFragment a = NewConversationFragment.a(this.h);
        if (this.l) {
            name = a.getClass().getName();
            com.helpshift.support.util.c.b(this.f, ConversationFragment.class.getName());
        } else {
            name = null;
        }
        com.helpshift.support.util.c.a(this.f, com.helpshift.e.f.flow_fragment_container, a, "HSNewConversationFragment", name, false, false);
    }

    private void a(Long l) {
        String name;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Starting conversation fragment: ");
        stringBuilder.append(l);
        l.a("Helpshift_SupportContr", stringBuilder.toString());
        this.h.putLong("issueId", l.longValue());
        ConversationFragment a = ConversationFragment.a(this.h);
        if (this.l) {
            name = a.getClass().getName();
            com.helpshift.support.util.c.b(this.f, NewConversationFragment.class.getName());
        } else {
            name = null;
        }
        com.helpshift.support.util.c.a(this.f, com.helpshift.e.f.flow_fragment_container, a, "HSConversationFragment", name, false, false);
    }

    public void b(Bundle bundle) {
        com.helpshift.support.util.c.a(this.f, com.helpshift.e.f.flow_fragment_container, SearchResultFragment.a(bundle, this), "HSSearchResultFragment", false);
    }

    public void a(com.helpshift.conversation.dto.c cVar, Bundle bundle, LaunchSource launchSource) {
        ScreenshotPreviewFragment b = com.helpshift.support.util.c.b(g());
        if (b == null) {
            b = ScreenshotPreviewFragment.a((d) this);
            com.helpshift.support.util.c.a(g(), com.helpshift.e.f.flow_fragment_container, b, "ScreenshotPreviewFragment", false);
        }
        b.a(bundle, cVar, launchSource);
    }

    public void a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("issue_publish_id", str2);
        com.helpshift.support.util.c.a(this.f, com.helpshift.e.f.flow_fragment_container, ConversationInfoFragment.a(bundle), "HSConversationInfoFragment", false);
    }

    public void a(List<g> list, boolean z) {
        com.helpshift.support.util.c.a(this.f, com.helpshift.e.f.flow_fragment_container, DynamicFormFragment.a(this.g, list, this), "HSDynamicFormFragment", z ? DynamicFormFragment.class.getName() : null, false, false);
    }

    public void a(String str, List<g> list, boolean z) {
        if (this.g != null) {
            this.g.putString("flow_title", str);
        }
        a((List) list, z);
    }

    public void a(int i, List<g> list, boolean z) {
        if (!(this.g == null || i == 0)) {
            this.g.putString("flow_title", this.d.getResources().getString(i));
        }
        a((List) list, z);
    }

    public int f() {
        return this.j;
    }

    public FragmentManager g() {
        return this.f;
    }

    public void a(String str) {
        if (!j()) {
            if (!TextUtils.isEmpty(str)) {
                this.m = str;
            }
            this.g.putString("chatLaunchSource", "support");
            a(this.g, true);
        }
    }

    private boolean j() {
        if (o.d().a() == null) {
            FaqFlowFragment c = com.helpshift.support.util.c.c(this.f);
            if (c != null) {
                List g = c.g();
                if (!(g == null || g.isEmpty())) {
                    a(g, true);
                    return true;
                }
            }
        }
        return false;
    }

    public void h() {
        k();
        Long l = o.d().j().a().a;
        o.c().e().a(l.longValue(), new com.helpshift.conversation.dto.a("", System.nanoTime(), 0));
        o.c().e().a(l.longValue(), null);
        if (f() == 1) {
            this.e.a();
        } else {
            com.helpshift.support.util.c.b(g(), NewConversationFragment.class.getName());
        }
    }

    private void k() {
        SingleQuestionFragment a = com.helpshift.support.util.c.a(this.f);
        if (a != null) {
            String e = a.e();
            if (!TextUtils.isEmpty(e)) {
                Map hashMap = new HashMap();
                hashMap.put("id", e);
                hashMap.put("str", o.c().e().a(o.d().j().a().a.longValue()).a);
                o.d().f().a(AnalyticsEventType.TICKET_AVOIDED, hashMap);
            }
        }
    }

    public void a(String str, ArrayList<String> arrayList) {
        boolean z = o.b().getResources().getBoolean(com.helpshift.e.c.is_screen_large);
        Bundle bundle = new Bundle();
        bundle.putString("questionPublishId", str);
        if (arrayList != null) {
            bundle.putStringArrayList("searchTerms", arrayList);
        }
        com.helpshift.support.util.c.a(this.f, com.helpshift.e.f.flow_fragment_container, SingleQuestionFragment.a(bundle, 2, z), null, false);
    }

    public void c() {
        o.d().f().a(AnalyticsEventType.TICKET_AVOIDANCE_FAILED);
        com.helpshift.support.util.c.b(g(), SearchResultFragment.class.getName());
        NewConversationFragment newConversationFragment = (NewConversationFragment) this.f.findFragmentByTag("HSNewConversationFragment");
        if (newConversationFragment != null) {
            newConversationFragment.g();
        }
    }

    public void a(com.helpshift.conversation.dto.c cVar) {
        com.helpshift.support.util.c.a(this.f, ScreenshotPreviewFragment.class.getName());
        NewConversationFragment newConversationFragment = (NewConversationFragment) this.f.findFragmentByTag("HSNewConversationFragment");
        if (newConversationFragment != null) {
            newConversationFragment.a(ScreenshotAction.ADD, cVar);
        }
    }

    public void a(com.helpshift.conversation.dto.c cVar, @Nullable String str) {
        com.helpshift.support.util.c.a(this.f, ScreenshotPreviewFragment.class.getName());
        ConversationFragment conversationFragment = (ConversationFragment) this.f.findFragmentByTag("HSConversationFragment");
        if (conversationFragment != null) {
            conversationFragment.a(ScreenshotAction.SEND, cVar, str);
        }
    }

    public void a() {
        com.helpshift.support.util.c.a(this.f, ScreenshotPreviewFragment.class.getName());
        NewConversationFragment newConversationFragment = (NewConversationFragment) this.f.findFragmentByTag("HSNewConversationFragment");
        if (newConversationFragment != null) {
            newConversationFragment.a(ScreenshotAction.REMOVE, null);
        }
    }

    public void a(Bundle bundle) {
        this.e.a(true, bundle);
    }

    public void b() {
        com.helpshift.support.util.c.a(this.f, ScreenshotPreviewFragment.class.getName());
    }

    public void c(Bundle bundle) {
        int i = bundle.getInt("support_mode");
        if (i == 1) {
            f(bundle);
        } else if (i != 4) {
            a(bundle, true, b.a());
        } else {
            a(bundle.getString("flow_title"), com.helpshift.support.f.d.a(), true);
        }
    }

    public void d(Bundle bundle) {
        bundle.putBoolean("key_support_controller_started", this.i);
        bundle.putBundle("key_conversation_bundle", this.h);
        bundle.putBoolean("key_conversation_add_to_back_stack", this.l);
    }

    public void e(Bundle bundle) {
        if (!this.i) {
            if (bundle.containsKey("key_support_controller_started")) {
                this.i = bundle.containsKey("key_support_controller_started");
                this.j = this.g.getInt("support_mode", 0);
                if (this.f != null) {
                    ScreenshotPreviewFragment screenshotPreviewFragment = (ScreenshotPreviewFragment) this.f.findFragmentByTag("ScreenshotPreviewFragment");
                    if (screenshotPreviewFragment != null) {
                        screenshotPreviewFragment.b(this);
                    }
                    SearchResultFragment searchResultFragment = (SearchResultFragment) this.f.findFragmentByTag("HSSearchResultFragment");
                    if (searchResultFragment != null) {
                        searchResultFragment.a(this);
                    }
                    DynamicFormFragment dynamicFormFragment = (DynamicFormFragment) this.f.findFragmentByTag("HSDynamicFormFragment");
                    if (dynamicFormFragment != null) {
                        dynamicFormFragment.a(this);
                    }
                }
            }
            if (bundle.containsKey("key_conversation_bundle") && bundle.containsKey("key_conversation_add_to_back_stack")) {
                this.h = bundle.getBundle("key_conversation_bundle");
                this.l = bundle.getBoolean("key_conversation_add_to_back_stack");
            }
        }
    }
}
