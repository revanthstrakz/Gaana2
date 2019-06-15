package com.helpshift.support.conversations;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.helpshift.conversation.c.e;
import com.helpshift.conversation.dto.c;
import com.helpshift.e.k;
import com.helpshift.support.fragments.HSMenuItemType;
import com.helpshift.support.fragments.b;
import com.helpshift.support.util.a;
import com.helpshift.support.util.g;
import java.util.ArrayList;

public class d implements e {
    private final Context a;
    private final TextInputLayout b;
    private final TextInputEditText c;
    private final TextInputLayout d;
    private final TextInputEditText e;
    private final TextInputLayout f;
    private final TextInputEditText g;
    private final ProgressBar h;
    private final ImageView i;
    private final TextView j;
    private final TextView k;
    private final CardView l;
    private final ImageButton m;
    private final e n;
    private final View o;
    private final b p;

    public void w() {
    }

    public void x() {
    }

    d(Context context, TextInputLayout textInputLayout, TextInputEditText textInputEditText, TextInputLayout textInputLayout2, TextInputEditText textInputEditText2, TextInputLayout textInputLayout3, TextInputEditText textInputEditText3, ProgressBar progressBar, ImageView imageView, TextView textView, TextView textView2, CardView cardView, ImageButton imageButton, View view, e eVar, b bVar) {
        this.a = context;
        this.b = textInputLayout;
        this.c = textInputEditText;
        this.d = textInputLayout2;
        this.e = textInputEditText2;
        this.f = textInputLayout3;
        this.g = textInputEditText3;
        this.h = progressBar;
        this.i = imageView;
        this.j = textView;
        this.k = textView2;
        this.l = cardView;
        this.m = imageButton;
        this.o = view;
        this.n = eVar;
        this.p = bVar;
    }

    private String a(int i) {
        return this.a.getText(i).toString();
    }

    public void a() {
        a(this.b, a(k.hs__conversation_detail_error));
    }

    public void b() {
        a(this.b, a(k.hs__description_invalid_length_error));
    }

    public void c() {
        a(this.b, a(k.hs__invalid_description_error));
    }

    public void d() {
        a(this.b, null);
    }

    public void e() {
        a(this.d, a(k.hs__username_blank_error));
    }

    public void f() {
        a(this.d, a(k.hs__username_blank_error));
    }

    public void g() {
        a(this.d, null);
    }

    public void h() {
        a(this.f, a(k.hs__invalid_email_error));
    }

    public void i() {
        a(this.f, a(k.hs__invalid_email_error));
    }

    public void j() {
        a(this.f, null);
    }

    public void k() {
        a(HSMenuItemType.SCREENSHOT_ATTACHMENT, false);
    }

    public void l() {
        a(HSMenuItemType.SCREENSHOT_ATTACHMENT, true);
    }

    public void a(@NonNull String str, String str2, Long l) {
        Bitmap a = a.a(str, -1);
        if (a != null) {
            CharSequence str22;
            this.i.setImageBitmap(a);
            TextView textView = this.j;
            if (str22 == null) {
                str22 = "";
            }
            textView.setText(str22);
            CharSequence charSequence = "";
            if (l != null) {
                charSequence = new com.helpshift.support.model.a((double) l.longValue()).a();
            }
            this.k.setText(charSequence);
            this.i.setVisibility(0);
            this.m.setVisibility(0);
            this.l.setVisibility(0);
        }
    }

    public void m() {
        this.l.setVisibility(8);
        this.i.setVisibility(8);
        this.m.setVisibility(8);
    }

    public void a(String str) {
        this.c.setText(str);
        this.c.setSelection(this.c.getText().length());
    }

    public void b(String str) {
        this.e.setText(str);
        this.e.setSelection(this.e.getText().length());
    }

    public void c(String str) {
        this.g.setText(str);
        this.g.setSelection(this.g.getText().length());
    }

    public void n() {
        this.e.setVisibility(0);
        this.g.setVisibility(0);
    }

    public void o() {
        this.e.setVisibility(8);
        this.g.setVisibility(8);
    }

    public void p() {
        this.g.setHint(a(k.hs__email_required_hint));
    }

    public void q() {
        a(HSMenuItemType.START_NEW_CONVERSATION, true);
    }

    public void r() {
        a(HSMenuItemType.START_NEW_CONVERSATION, false);
    }

    public void a(long j) {
        this.n.i();
    }

    public void s() {
        this.n.h();
    }

    public void a(c cVar) {
        this.n.a(cVar);
    }

    public void t() {
        this.h.setVisibility(0);
    }

    public void u() {
        this.h.setVisibility(8);
    }

    public void v() {
        Toast a = com.helpshift.views.d.a(this.a, k.hs__conversation_started_message, 0);
        a.setGravity(16, 0, 0);
        a.show();
    }

    public void a(ArrayList arrayList) {
        this.n.a(arrayList);
    }

    public void a(com.helpshift.common.exception.a aVar) {
        g.a(aVar, this.o);
    }

    private void a(TextInputLayout textInputLayout, CharSequence charSequence) {
        textInputLayout.setErrorEnabled(TextUtils.isEmpty(charSequence) ^ 1);
        textInputLayout.setError(charSequence);
    }

    private void a(HSMenuItemType hSMenuItemType, boolean z) {
        if (this.p != null) {
            this.p.a(hSMenuItemType, z);
        }
    }
}
