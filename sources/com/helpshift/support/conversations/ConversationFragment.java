package com.helpshift.support.conversations;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.platform.Device.PermissionType;
import com.helpshift.conversation.activeconversation.message.AdminAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.AdminImageAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.c;
import com.helpshift.conversation.activeconversation.message.m;
import com.helpshift.conversation.c.b;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.support.conversations.messages.j;
import com.helpshift.support.fragments.HSMenuItemType;
import com.helpshift.support.fragments.ScreenshotPreviewFragment.ScreenshotAction;
import com.helpshift.support.fragments.a;
import com.helpshift.support.util.AppSessionConstants.Screen;
import com.helpshift.support.util.e;
import com.helpshift.support.util.g;
import com.helpshift.util.l;
import com.helpshift.util.o;

public class ConversationFragment extends BaseConversationFragment implements b, j, a {
    EditText a;
    b b;
    private String d;
    private String e;
    private c f;
    private a g;
    private int h;
    private int i;
    private boolean j;
    private com.helpshift.conversation.dto.c k;
    private String l;
    private boolean m;

    /* renamed from: com.helpshift.support.conversations.ConversationFragment$8 */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a = new int[ScreenshotAction.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|5|6|7|9|10|11|12|13|14|15|(3:17|18|20)) */
        static {
            /*
            r0 = com.helpshift.support.fragments.HSMenuItemType.values();
            r0 = r0.length;
            r0 = new int[r0];
            c = r0;
            r0 = 1;
            r1 = c;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = com.helpshift.support.fragments.HSMenuItemType.SCREENSHOT_ATTACHMENT;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = 2;
            r2 = c;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = com.helpshift.support.fragments.HSMenuItemType.CONVERSATION_INFO;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r2 = com.helpshift.common.platform.Device.PermissionState.values();
            r2 = r2.length;
            r2 = new int[r2];
            b = r2;
            r2 = b;	 Catch:{ NoSuchFieldError -> 0x0032 }
            r3 = com.helpshift.common.platform.Device.PermissionState.AVAILABLE;	 Catch:{ NoSuchFieldError -> 0x0032 }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x0032 }
            r2[r3] = r0;	 Catch:{ NoSuchFieldError -> 0x0032 }
        L_0x0032:
            r2 = b;	 Catch:{ NoSuchFieldError -> 0x003c }
            r3 = com.helpshift.common.platform.Device.PermissionState.UNAVAILABLE;	 Catch:{ NoSuchFieldError -> 0x003c }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x003c }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x003c }
        L_0x003c:
            r1 = b;	 Catch:{ NoSuchFieldError -> 0x0047 }
            r2 = com.helpshift.common.platform.Device.PermissionState.REQUESTABLE;	 Catch:{ NoSuchFieldError -> 0x0047 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0047 }
            r3 = 3;
            r1[r2] = r3;	 Catch:{ NoSuchFieldError -> 0x0047 }
        L_0x0047:
            r1 = com.helpshift.support.fragments.ScreenshotPreviewFragment.ScreenshotAction.values();
            r1 = r1.length;
            r1 = new int[r1];
            a = r1;
            r1 = a;	 Catch:{ NoSuchFieldError -> 0x005a }
            r2 = com.helpshift.support.fragments.ScreenshotPreviewFragment.ScreenshotAction.SEND;	 Catch:{ NoSuchFieldError -> 0x005a }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x005a }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x005a }
        L_0x005a:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.conversations.ConversationFragment$AnonymousClass8.<clinit>():void");
        }
    }

    /* Access modifiers changed, original: protected */
    public int a() {
        return 3;
    }

    public static ConversationFragment a(Bundle bundle) {
        ConversationFragment conversationFragment = new ConversationFragment();
        conversationFragment.setArguments(bundle);
        return conversationFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (l() && this.g != null) {
            this.j = this.g.l();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.i = getActivity().getWindow().getAttributes().flags;
        getActivity().getWindow().addFlags(2048);
        getActivity().getWindow().clearFlags(1024);
        View inflate = layoutInflater.inflate(h.hs__conversation_fragment, viewGroup, false);
        this.d = getArguments().getString("chatLaunchSource");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        a(view);
        super.onViewCreated(view, bundle);
        l.a("Helpshift_ConvFragment", "Now showing conversation screen");
    }

    private void a(View view) {
        Long valueOf = Long.valueOf(getArguments().getLong("issueId"));
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(f.hs__messagesList);
        this.a = (EditText) view.findViewById(f.hs__messageText);
        final ImageButton imageButton = (ImageButton) view.findViewById(f.hs__sendMessageBtn);
        RecyclerView recyclerView2 = recyclerView;
        ImageButton imageButton2 = imageButton;
        this.g = new a(getContext(), recyclerView2, this.a, imageButton2, getView(), view.findViewById(f.relativeLayout1), view.findViewById(f.hs__confirmation), this, e());
        this.b = o.d().a(valueOf, this.g, this.j);
        this.j = false;
        this.b.a(this.d);
        this.b.m();
        if (this.m) {
            this.b.a(this.k, this.l);
            this.m = false;
        }
        this.a.addTextChangedListener(new f() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ConversationFragment.this.b.b(charSequence.toString());
            }
        });
        this.a.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    imageButton.performClick();
                }
                return false;
            }
        });
        imageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ConversationFragment.this.b.c();
            }
        });
        view.findViewById(f.resolution_accepted_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ConversationFragment.this.b.c(true);
            }
        });
        view.findViewById(f.resolution_rejected_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ConversationFragment.this.a.requestFocus();
                e.b(ConversationFragment.this.getContext(), ConversationFragment.this.a);
                ConversationFragment.this.b.c(false);
            }
        });
        recyclerView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (i4 < i8) {
                    recyclerView.post(new Runnable() {
                        public void run() {
                            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                        }
                    });
                }
            }
        });
    }

    public void onResume() {
        super.onResume();
        this.b.a();
        this.b.g();
        this.h = getActivity().getWindow().getAttributes().softInputMode;
        getActivity().getWindow().setSoftInputMode(16);
        this.b.a(true);
        this.b.f();
        this.b.k();
        if (!l()) {
            this.b.a(AnalyticsEventType.OPEN_ISSUE, null);
            o.d().n().d();
        }
    }

    public void onPause() {
        getActivity().getWindow().setSoftInputMode(this.h);
        e.a(getContext(), this.a);
        this.b.a(false);
        this.b.f();
        this.b.k();
        this.b.l();
        super.onPause();
    }

    /* Access modifiers changed, original: protected */
    public Screen c() {
        return Screen.CONVERSATION;
    }

    public boolean a(ScreenshotAction screenshotAction, com.helpshift.conversation.dto.c cVar, @Nullable String str) {
        if (AnonymousClass8.a[screenshotAction.ordinal()] != 1) {
            return false;
        }
        if (this.b == null) {
            this.k = cVar;
            this.l = str;
            this.m = true;
        } else {
            this.b.a(cVar, str);
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public String d() {
        return getString(k.hs__conversation_header);
    }

    public void a(String str) {
        this.b.c(str);
    }

    public void a(ContextMenu contextMenu, View view) {
        if (view != null && (view instanceof TextView)) {
            final TextView textView = (TextView) view;
            contextMenu.add(0, view.getId(), 0, k.hs__copy).setOnMenuItemClickListener(new OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem menuItem) {
                    ConversationFragment.this.c(textView.getText().toString());
                    return true;
                }
            });
        }
    }

    public void a(com.helpshift.conversation.activeconversation.message.j jVar) {
        this.b.a(jVar);
    }

    public void a(m mVar) {
        this.e = mVar.i;
        this.b.i();
        Bundle bundle = new Bundle();
        bundle.putInt("key_screenshot_mode", a());
        bundle.putString("key_refers_id", this.e);
        e().a(true, bundle);
    }

    public void a(com.helpshift.conversation.activeconversation.message.k kVar) {
        this.b.a(kVar);
    }

    public void a(AdminAttachmentMessageDM adminAttachmentMessageDM) {
        a(adminAttachmentMessageDM.d(), (c) adminAttachmentMessageDM);
    }

    public void a(AdminImageAttachmentMessageDM adminImageAttachmentMessageDM) {
        a(true, (c) adminImageAttachmentMessageDM);
    }

    public void g() {
        this.b.j();
        e().c().e();
    }

    public void a(int i, String str) {
        this.b.a(i, str);
    }

    private void a(boolean z, c cVar) {
        this.f = null;
        if (z) {
            switch (o.c().d().a(PermissionType.WRITE_STORAGE)) {
                case AVAILABLE:
                    this.b.a(cVar);
                    return;
                case UNAVAILABLE:
                    d(cVar.e);
                    return;
                case REQUESTABLE:
                    this.f = cVar;
                    a(true);
                    return;
                default:
                    return;
            }
        }
        this.b.a(cVar);
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
        switch (i) {
            case 2:
                Bundle bundle = new Bundle();
                bundle.putInt("key_screenshot_mode", a());
                bundle.putString("key_refers_id", this.e);
                e().a(false, bundle);
                return;
            case 3:
                if (this.f != null) {
                    this.b.a(this.f);
                    this.f = null;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void d(String str) {
        DownloadManager downloadManager = (DownloadManager) getContext().getSystemService("download");
        Request request = new Request(Uri.parse(str));
        request.setNotificationVisibility(1);
        downloadManager.enqueue(request);
        if (!isDetached()) {
            g.a(getView(), k.hs__starting_download, -1);
        }
    }

    public void a(String str, String str2) {
        e().c().a(str, str2);
    }

    public void onDetach() {
        if (!l()) {
            o.d().n().c();
        }
        super.onDetach();
    }

    public void onDestroyView() {
        if (getActivity() != null) {
            getActivity().getWindow().clearFlags(2048);
            getActivity().getWindow().setFlags(this.i, this.i);
        }
        this.b.a(-1);
        this.b.b();
        super.onDestroyView();
    }

    public void h() {
        if (this.b != null) {
            this.b.m();
        }
    }

    public void i() {
        if (this.b != null) {
            this.b.n();
        }
    }

    public void j() {
        this.b.g();
    }

    public void a(HSMenuItemType hSMenuItemType) {
        switch (hSMenuItemType) {
            case SCREENSHOT_ATTACHMENT:
                this.e = null;
                this.b.i();
                Bundle bundle = new Bundle();
                bundle.putInt("key_screenshot_mode", a());
                bundle.putString("key_refers_id", null);
                e().a(true, bundle);
                return;
            case CONVERSATION_INFO:
                this.j = this.g.l();
                this.b.h();
                return;
            default:
                return;
        }
    }
}
