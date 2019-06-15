package com.actionbar;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.PopupWindowView;
import com.managers.u;
import com.services.l.s;

public class JukeActionBar extends ConstraintLayout implements OnClickListener {
    private Context a;
    private LayoutInflater b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private BusinessObject g;
    private BaseGaanaFragment h;
    private s i;

    public static class a {
        private String a = "";
        private String b = "";
        private boolean c;
        private boolean d;
        private Context e;

        public a a(Context context) {
            this.e = context;
            return this;
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(boolean z) {
            this.c = z;
            return this;
        }

        public a b(boolean z) {
            this.d = z;
            return this;
        }

        public JukeActionBar a() {
            if (this.e != null) {
                return new JukeActionBar(this, null);
            }
            throw new IllegalStateException("Context must be provided");
        }
    }

    /* synthetic */ JukeActionBar(a aVar, AnonymousClass1 anonymousClass1) {
        this(aVar);
    }

    private JukeActionBar(a aVar) {
        super(aVar.e);
        this.i = new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (JukeActionBar.this.h.isAdded()) {
                    JukeActionBar.this.h.onResponse(businessObject);
                }
            }
        };
        this.c = aVar.a;
        this.d = aVar.b;
        this.e = aVar.c;
        this.f = aVar.d;
        a(aVar.e);
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.c = str;
            b(str, str2);
        }
    }

    public void a(boolean z) {
        this.e = z;
        if (this.e) {
            findViewById(R.id.action_refresh).setVisibility(0);
            findViewById(R.id.action_refresh).setOnClickListener(this);
            return;
        }
        findViewById(R.id.action_refresh).setVisibility(4);
    }

    private void a(Context context) {
        this.a = context;
        this.b = LayoutInflater.from(this.a);
        setLayoutParams(new LayoutParams(-1, -2));
        this.b.inflate(R.layout.ab_juke_action_bar, this);
        findViewById(R.id.menu_icon).setOnClickListener(this);
        if (this.e) {
            findViewById(R.id.action_refresh).setOnClickListener(this);
        } else {
            findViewById(R.id.action_refresh).setVisibility(4);
        }
        if (this.f) {
            findViewById(R.id.action_more_option).setOnClickListener(this);
        } else {
            findViewById(R.id.action_more_option).setVisibility(4);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00ad  */
    private void b(java.lang.String r10, java.lang.String r11) {
        /*
        r9 = this;
        r0 = new android.text.SpannableStringBuilder;
        r0.<init>(r10);
        r1 = r9.g;
        r1 = r1 instanceof com.gaana.juke.JukePlaylist;
        r2 = 1;
        r3 = 0;
        if (r1 == 0) goto L_0x0055;
    L_0x000d:
        r11 = r9.g;
        r11 = (com.gaana.juke.JukePlaylist) r11;
        r4 = r11.getPlStatus();
        r6 = 2;
        r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r11 == 0) goto L_0x0046;
    L_0x001b:
        r11 = r9.g;
        r11 = (com.gaana.juke.JukePlaylist) r11;
        r11 = r11.getOwnerName();
        r11 = android.text.TextUtils.isEmpty(r11);
        if (r11 != 0) goto L_0x0043;
    L_0x0029:
        r11 = r9.a;
        r11 = r11.getResources();
        r1 = 2131820717; // 0x7f1100ad float:1.9274157E38 double:1.0532593794E-314;
        r11 = r11.getString(r1);
        r1 = r9.g;
        r1 = (com.gaana.juke.JukePlaylist) r1;
        r1 = r1.getOwnerName();
        r11 = r11.concat(r1);
        goto L_0x0055;
    L_0x0043:
        r11 = "";
        goto L_0x0055;
    L_0x0046:
        r11 = r9.a;
        r11 = r11.getResources();
        r1 = 2131821407; // 0x7f11035f float:1.9275556E38 double:1.0532597203E-314;
        r11 = r11.getString(r1);
        r1 = r2;
        goto L_0x0056;
    L_0x0055:
        r1 = r3;
    L_0x0056:
        r4 = "\n";
        r5 = " ";
        r10 = r10.replace(r4, r5);
        r4 = new android.text.style.TextAppearanceSpan;
        r5 = r9.a;
        r6 = 2131886613; // 0x7f120215 float:1.940781E38 double:1.0532919363E-314;
        r4.<init>(r5, r6);
        r5 = android.text.TextUtils.isEmpty(r11);
        if (r5 == 0) goto L_0x0070;
    L_0x006e:
        r11 = "";
    L_0x0070:
        r5 = "\n";
        r5 = r0.append(r5);
        r5.append(r11);
        r5 = new android.text.style.TextAppearanceSpan;
        r6 = r9.a;
        r7 = 2131886615; // 0x7f120217 float:1.9407814E38 double:1.0532919373E-314;
        r5.<init>(r6, r7);
        r6 = new com.utilities.c;
        r6.<init>();
        r7 = r10.length();
        r8 = 17;
        r0.setSpan(r4, r3, r7, r8);
        r4 = r10.length();
        r0.setSpan(r6, r3, r4, r8);
        r3 = r10.length();
        r10 = r10.length();
        r11 = r11.length();
        r10 = r10 + r11;
        r10 = r10 + r2;
        r11 = 18;
        r0.setSpan(r5, r3, r10, r11);
        if (r1 == 0) goto L_0x00d5;
    L_0x00ad:
        r10 = new com.utilities.b;
        r1 = r9.a;
        r3 = 2131232136; // 0x7f080588 float:1.8080373E38 double:1.0529685817E-314;
        r4 = -12;
        r10.<init>(r1, r3, r4);
        r1 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r0.append(r1);
        r0.append(r1);
        r0.append(r1);
        r0.append(r1);
        r1 = r0.length();
        r1 = r1 + -2;
        r3 = r0.length();
        r3 = r3 - r2;
        r0.setSpan(r10, r1, r3, r11);
    L_0x00d5:
        r10 = 2131296389; // 0x7f090085 float:1.8210693E38 double:1.053000327E-314;
        r10 = r9.findViewById(r10);
        r10 = (android.widget.TextView) r10;
        r10.setText(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.actionbar.JukeActionBar.b(java.lang.String, java.lang.String):void");
    }

    public void setParams(BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject) {
        this.h = baseGaanaFragment;
        this.g = businessObject;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.action_more_option) {
            u.a().b("PartyHub", "Party_3Dot");
            PopupWindowView.getInstance(this.a, this.h).contextPopupWindow(this.g, false, false);
        } else if (id == R.id.action_refresh) {
            JukeSessionManager.getInstance().editPlaylist((JukePlaylist) this.g, this.i, false, true);
        } else if (id == R.id.menu_icon) {
            ((GaanaActivity) this.a).onBackPressedHandling();
        }
    }
}
