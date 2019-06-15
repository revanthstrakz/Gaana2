package com.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.custom_card_response.CustomCard;
import com.custom_card_response.a;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.GoogleIntroductoryPriceConfig.IntroConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.i.i;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.PurchaseGoogleManager.c;
import com.managers.URLManager;
import com.managers.ag;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;

public class CustomCardFragment extends DialogFragment implements OnClickListener {
    CustomCard a = null;
    a b = null;
    View c = null;
    Context d = null;
    String e = "";
    private ProductItem f;
    private TextView g;
    private TextView h;
    private TextView i;
    private Bitmap j = null;
    private c k = null;
    private boolean l = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.dialog_transparent_bg);
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.c = getActivity().getLayoutInflater().inflate(R.layout.fragment_custome_card_view, viewGroup, false);
        this.d = getActivity();
        a();
        return this.c;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(Bitmap bitmap) {
        this.j = bitmap;
    }

    public void a(c cVar) {
        this.k = cVar;
    }

    private void a() {
        if (!(this.b == null || this.c == null)) {
            this.g = (TextView) this.c.findViewById(R.id.suggestion_text);
            this.h = (TextView) this.c.findViewById(R.id.gaanaPlusButton);
            this.h.setTypeface(null, 1);
            this.i = (TextView) this.c.findViewById(R.id.learn_more_text);
            TextView textView = (TextView) this.c.findViewById(R.id.term_apply_text);
            this.f = this.b.i();
            this.h.setOnClickListener(this);
            this.i.setOnClickListener(this);
            RelativeLayout relativeLayout = (RelativeLayout) this.c.findViewById(R.id.custom_card_vw_background);
            if (this.j != null) {
                relativeLayout.setBackgroundDrawable(new BitmapDrawable(this.j));
            }
            textView.setText(this.b.c());
            textView.setOnClickListener(this);
            this.c.findViewById(R.id.dialogContainer).setOnClickListener(this);
            b(this.k);
            if (this.k != null && this.k.a()) {
                IntroConfig d = this.a.a().d();
                if (d != null) {
                    final CharSequence intro_tnc_link = d != null ? d.getIntro_tnc_link() : this.b.d();
                    if (!TextUtils.isEmpty(intro_tnc_link)) {
                        textView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Intent intent = new Intent(CustomCardFragment.this.d, WebViewActivity.class);
                                intent.putExtra("EXTRA_WEBVIEW_URL", intro_tnc_link);
                                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                                intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                                CustomCardFragment.this.d.startActivity(intent);
                            }
                        });
                    }
                }
            }
        }
        if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            b();
        }
        ag.a(this.d).a("In app type", "Custom in-app");
    }

    private void b(c cVar) {
        IntroConfig d = (cVar == null || !cVar.a() || this.a.a() == null || this.a.a().d() == null) ? null : this.a.a().d();
        if (cVar != null) {
            CharSequence intro_eligible_msg = d != null ? d.getIntro_eligible_msg() : this.b.a();
            if (!TextUtils.isEmpty(intro_eligible_msg)) {
                if (intro_eligible_msg.contains("&&&&")) {
                    intro_eligible_msg = intro_eligible_msg.replace("&&&&", cVar.b());
                }
                if (intro_eligible_msg.contains("####")) {
                    intro_eligible_msg = intro_eligible_msg.replace("####", cVar.c());
                }
                this.g.setText(intro_eligible_msg);
            }
        } else if (this.b.a() == null || !this.b.a().contains("&&&&")) {
            this.g.setText(this.b.a());
        } else {
            this.g.setText(this.b.a().replace("&&&&", this.f.getP_cost()));
        }
        if (cVar != null && cVar.a()) {
            int round = Math.round((((float) (cVar.d() - cVar.e())) / ((float) cVar.d())) * 100.0f);
            TextView textView = this.i;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.d.getString(R.string.you_save));
            stringBuilder.append(" ");
            stringBuilder.append(round);
            stringBuilder.append(" %");
            textView.setText(stringBuilder.toString());
        } else if (this.b.g() == null || !this.b.g().contains("&&&&")) {
            this.i.setText(this.b.g());
        } else {
            this.i.setText(this.b.g().replace("&&&&", this.f.getP_cost()));
        }
        if (cVar != null) {
            CharSequence intro_cta_text = cVar.a() ? d.getIntro_cta_text() : this.b.f();
            if (!TextUtils.isEmpty(intro_cta_text)) {
                if (intro_cta_text.contains("&&&&")) {
                    intro_cta_text = intro_cta_text.replace("&&&&", cVar.b());
                }
                if (intro_cta_text.contains("####")) {
                    intro_cta_text = intro_cta_text.replace("####", cVar.c());
                }
                this.h.setText(intro_cta_text);
            }
        } else if (this.b.f() == null || !this.b.f().contains("&&&&")) {
            this.h.setText(this.b.f());
        } else {
            this.h.setText(this.b.f().replace("&&&&", this.f.getP_cost()));
        }
    }

    private void b() {
        String str = "https://api.gaana.com/gplus_app_ad.php?type=gplus_ad_postbak";
        if (!(this.b == null || this.b.e() == null || TextUtils.isEmpty(this.b.e()))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&card_type=");
            stringBuilder.append(this.b.e());
            str = stringBuilder.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.a(CustomCard.class);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void a(CustomCard customCard) {
        this.a = customCard;
        this.b = customCard.b();
    }

    public void onClick(View view) {
        String str = "";
        StringBuilder stringBuilder;
        if (view.getId() == R.id.gaanaPlusButton) {
            if (!(this.b == null || this.b.h() == null)) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("CTA; payment mode=");
                stringBuilder2.append(this.b.h());
                stringBuilder2.append(";");
                str = stringBuilder2.toString();
            }
            c();
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.e);
            stringBuilder.append(" ;");
            stringBuilder.append(this.b.e());
            u.a().a("Custom in-app", stringBuilder.toString(), str);
        } else if (view.getId() == R.id.term_apply_text) {
            Intent intent = new Intent(this.d, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", this.b.d());
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
            this.d.startActivity(intent);
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.e);
            stringBuilder.append(" ;");
            stringBuilder.append(this.b.e());
            u.a().a("Custom in-app", stringBuilder.toString(), "TnC");
        } else if (view.getId() == R.id.dialogContainer) {
            dismiss();
        }
    }

    private void c() {
        final ProductItem i = this.b.i();
        if (i == null || TextUtils.isEmpty(i.getAction())) {
            ((GaanaActivity) this.d).changeFragment(R.id.upgradeButtonLayout, null, null);
        } else if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(i.getAction())) {
            u.a().a(i, i.getItem_id());
            ag.a(this.d).a(this.d, i, new ag.a() {
                public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                    ag.a(CustomCardFragment.this.d).a("", "", "success");
                    ((BaseActivity) CustomCardFragment.this.d).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            ((BaseActivity) CustomCardFragment.this.d).hideProgressDialog();
                            ap.a().a(CustomCardFragment.this.d);
                            Util.aa();
                            aj.a().a(CustomCardFragment.this.d, CustomCardFragment.this.d.getString(R.string.enjoy_using_gaana_plus));
                            if (Util.v(CustomCardFragment.this.d)) {
                                Intent intent = new Intent(CustomCardFragment.this.d, GaanaActivity.class);
                                intent.setFlags(71303168);
                                CustomCardFragment.this.d.startActivity(intent);
                            }
                        }
                    });
                    if (!TextUtils.isEmpty(i.getAction())) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(CustomCardFragment.this.e);
                        stringBuilder.append(" ;");
                        stringBuilder.append(CustomCardFragment.this.b.e());
                        String stringBuilder2 = stringBuilder.toString();
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Action;");
                        stringBuilder3.append(i.getAction());
                        stringBuilder3.append("SUCCESS");
                        u.a().a("Custom in-app", stringBuilder2, stringBuilder3.toString());
                    }
                }

                public void onFailure(String str, String str2) {
                    if (!TextUtils.isEmpty(str)) {
                        aj.a().a(CustomCardFragment.this.d, str);
                    }
                    ag.a(CustomCardFragment.this.d).a(str, "", str2);
                    if (i != null && !TextUtils.isEmpty(i.getAction())) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(CustomCardFragment.this.e);
                        stringBuilder.append(" ;");
                        stringBuilder.append(CustomCardFragment.this.b.e());
                        String stringBuilder2 = stringBuilder.toString();
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Action;");
                        stringBuilder3.append(i.getAction());
                        stringBuilder3.append("FAILURE");
                        u.a().a("Custom in-app", stringBuilder2, stringBuilder3.toString());
                    }
                }
            }, i.getItem_id(), i.getDesc());
        } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(i.getAction())) {
            BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
            paymentDetailFragment.a(i);
            ((GaanaActivity) this.d).displayFragment(paymentDetailFragment);
        } else if (NativeContentAd.ASSET_CALL_TO_ACTION.equalsIgnoreCase(i.getAction()) && !TextUtils.isEmpty(i.getWeb_url())) {
            Intent intent = new Intent(this.d, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", i.getWeb_url());
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
            this.d.startActivity(intent);
        } else if (NativeContentAd.ASSET_ADVERTISER.equalsIgnoreCase(i.getAction())) {
            u.a().a(i, i.getItem_id());
            u.a().a(i, i.getDesc(), i.getItem_id(), 0);
            ((GaanaActivity) this.d).displayFragment(new ReferFriendsFragment());
        }
        this.l = true;
        dismiss();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.j != null) {
            this.j.recycle();
            this.j = null;
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.l) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.e);
            stringBuilder.append(" ;");
            stringBuilder.append(this.b.e());
            u.a().a("Custom in-app", stringBuilder.toString(), "Skip");
        }
        super.onDismiss(dialogInterface);
    }
}
