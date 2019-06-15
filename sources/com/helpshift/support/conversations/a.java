package com.helpshift.support.conversations;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import com.helpshift.common.exception.PlatformException;
import com.helpshift.conversation.activeconversation.b;
import com.helpshift.conversation.activeconversation.message.ConversationFooterState;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.e.k;
import com.helpshift.support.fragments.HSMenuItemType;
import com.helpshift.support.util.e;
import com.helpshift.support.util.g;
import com.helpshift.support.util.h;
import com.helpshift.util.o;
import com.helpshift.util.v;
import java.io.File;
import java.util.List;

class a implements b {
    RecyclerView a;
    c b;
    private Context c;
    private EditText d;
    private ImageButton e;
    private View f;
    private b g;
    private View h;
    private View i;
    private com.helpshift.support.fragments.b j;

    a(Context context, RecyclerView recyclerView, EditText editText, ImageButton imageButton, View view, View view2, View view3, b bVar, com.helpshift.support.fragments.b bVar2) {
        this.c = context;
        this.a = recyclerView;
        ItemAnimator itemAnimator = this.a.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        this.f = view;
        this.d = editText;
        this.e = imageButton;
        this.h = view2;
        this.i = view3;
        this.g = bVar;
        this.j = bVar2;
    }

    public void a(List<j> list) {
        if (this.b == null) {
            this.b = new c(this.c, list, this.g);
            this.a.setLayoutManager(new LinearLayoutManager(this.c));
            this.a.setAdapter(this.b);
            this.a.scrollToPosition(this.b.getItemCount() - 1);
        }
    }

    public void a(int i, int i2) {
        this.b.notifyItemRangeInserted(i, i2);
        if (i == this.b.b() - 1) {
            this.a.scrollToPosition(this.b.getItemCount() - 1);
        }
    }

    public void b(int i, int i2) {
        if (i == 0 && i2 == this.b.b()) {
            this.b.notifyDataSetChanged();
        } else {
            this.b.notifyItemRangeChanged(i, i2);
        }
    }

    public void a() {
        a(HSMenuItemType.CONVERSATION_INFO, true);
    }

    public void b() {
        a(HSMenuItemType.CONVERSATION_INFO, false);
    }

    public void c() {
        a(HSMenuItemType.SCREENSHOT_ATTACHMENT, true);
    }

    public void d() {
        a(HSMenuItemType.SCREENSHOT_ATTACHMENT, false);
    }

    public void e() {
        this.e.setEnabled(true);
        this.e.setAlpha(255);
        h.a(this.c, this.e.getDrawable(), true);
    }

    public void f() {
        this.e.setEnabled(false);
        this.e.setAlpha(64);
        h.a(this.c, this.e.getDrawable(), false);
    }

    public void a(String str) {
        this.d.setText(str);
    }

    public void g() {
        e.a(this.c, this.d);
        this.i.setVisibility(0);
    }

    public void h() {
        this.i.setVisibility(8);
    }

    public void i() {
        this.a.setPadding(0, 0, 0, (int) v.a(this.c, 12.0f));
        this.h.setVisibility(0);
    }

    public void j() {
        this.a.setPadding(0, 0, 0, 0);
        this.h.setVisibility(8);
    }

    public void a(String str, String str2) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                Uri uriForFile;
                Intent intent = new Intent("android.intent.action.VIEW");
                if (VERSION.SDK_INT >= 24) {
                    String packageName = this.c.getApplicationContext().getPackageName();
                    Context context = this.c;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(packageName);
                    stringBuilder.append(".helpshift.fileprovider");
                    uriForFile = FileProvider.getUriForFile(context, stringBuilder.toString(), file);
                    intent.setFlags(1);
                } else {
                    uriForFile = Uri.fromFile(file);
                }
                intent.setDataAndType(uriForFile, str2);
                if (intent.resolveActivity(this.c.getPackageManager()) != null) {
                    this.c.startActivity(intent);
                    return;
                } else if (o.d().h().d()) {
                    o.d().h().a(file);
                    return;
                } else {
                    a(PlatformException.NO_APPS_FOR_OPENING_ATTACHMENT);
                    return;
                }
            }
            a(PlatformException.FILE_NOT_FOUND);
            return;
        }
        a(PlatformException.FILE_NOT_FOUND);
    }

    public void a(com.helpshift.common.exception.a aVar) {
        g.a(aVar, this.f);
    }

    public void b(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        if (intent.resolveActivity(this.c.getPackageManager()) != null) {
            this.c.startActivity(intent);
        } else {
            a(PlatformException.NO_APPS_FOR_OPENING_ATTACHMENT);
        }
    }

    public void b(String str, String str2) {
        if (this.g != null) {
            this.g.a(str, str2);
        }
    }

    public void a(ConversationFooterState conversationFooterState) {
        if (this.b != null) {
            if (conversationFooterState != ConversationFooterState.NONE) {
                e.a(this.c, this.d);
            }
            this.b.a(conversationFooterState);
            if (conversationFooterState != ConversationFooterState.NONE) {
                this.a.post(new Runnable() {
                    public void run() {
                        a.this.a.scrollToPosition(a.this.b.getItemCount() - 1);
                    }
                });
            }
        }
    }

    public void k() {
        g.a(this.f, this.c.getResources().getString(k.hs__csat_submit_toast), 0);
    }

    public void a(boolean z) {
        if (this.b != null) {
            this.b.a(z);
            if (z) {
                int b = this.b.b() - 1;
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.a.getLayoutManager();
                int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (findLastVisibleItemPosition == b) {
                    if (linearLayoutManager.findViewByPosition(findLastVisibleItemPosition).getBottom() < this.a.getBottom()) {
                        this.a.scrollToPosition(this.b.getItemCount() - 1);
                    }
                } else if (findLastVisibleItemPosition == -1) {
                    this.a.scrollToPosition(this.b.getItemCount() - 1);
                }
            }
        }
    }

    public boolean l() {
        return this.h.getVisibility() == 0;
    }

    private void a(HSMenuItemType hSMenuItemType, boolean z) {
        if (this.j != null) {
            this.j.a(hSMenuItemType, z);
        }
    }
}
