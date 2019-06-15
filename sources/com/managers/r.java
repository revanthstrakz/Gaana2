package com.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.gaana.view.item.RateUsDialog;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.ag.a;
import com.managers.w.b;
import com.moengage.inapp.InAppManager;
import com.moengage.inapp.InAppMessage;
import com.moengage.inapp.InAppTracker;
import com.services.d;
import com.services.l.ad;
import com.services.l.aq;
import com.services.l.au;
import com.services.n;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class r {
    private static r a;
    private InAppMessage b;
    private GaanaApplication c = GaanaApplication.getInstance();
    private ArrayList<Language> d;
    private boolean e = false;
    private aq f;

    public static r a() {
        if (a == null) {
            a = new r();
        }
        return a;
    }

    private r() {
    }

    public View a(Activity activity, aq aqVar, InAppMessage inAppMessage) {
        this.b = inAppMessage;
        if (this.b != null) {
            this.f = aqVar;
            try {
                JSONObject jSONObject = new JSONObject(this.b.content);
                String string = jSONObject.getString("template");
                if (string.equals("lang_pref")) {
                    GaanaApplication.getInstance().inAppShownList.put(string, Long.valueOf(System.currentTimeMillis()));
                    return a(activity);
                } else if (string.equals("free_trial")) {
                    GaanaApplication.getInstance().inAppShownList.put(string, Long.valueOf(System.currentTimeMillis()));
                    return a(activity, jSONObject);
                } else if (string.equals("rate_us")) {
                    GaanaApplication.getInstance().inAppShownList.put(string, Long.valueOf(System.currentTimeMillis()));
                    return b(activity);
                } else if (string.equals("user_feedback")) {
                    return Util.a((Context) activity, inAppMessage);
                }
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return null;
    }

    private View a(final Activity activity) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.inapp_language_preference, null);
        inflate.findViewById(R.id.inapp_lp_close).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                r.this.f.a();
                if (r.this.b != null) {
                    InAppTracker.getInstance(activity.getBaseContext()).trackInAppClicked(r.this.b);
                }
                r.this.b(view);
            }
        });
        inflate.findViewById(R.id.lp_save_changes).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (r.this.b != null) {
                    InAppTracker.getInstance(activity.getBaseContext()).trackInAppClicked(r.this.b);
                }
                r.this.b(view);
            }
        });
        a(inflate);
        this.e = true;
        return inflate;
    }

    private View a(final Activity activity, JSONObject jSONObject) {
        CharSequence charSequence = "";
        CharSequence charSequence2 = "";
        CharSequence charSequence3 = "";
        try {
            if (jSONObject.has(TtmlNode.TAG_HEAD)) {
                charSequence = jSONObject.getString(TtmlNode.TAG_HEAD);
            }
            if (jSONObject.has("body")) {
                charSequence2 = jSONObject.getString("body");
            }
            if (jSONObject.has("button")) {
                charSequence3 = jSONObject.getString("button");
            }
            View inflate = LayoutInflater.from(activity).inflate(R.layout.inapp_free_trial, null);
            if (!TextUtils.isEmpty(charSequence)) {
                ((TextView) inflate.findViewById(R.id.inapp_lp_head)).setText(charSequence);
            }
            if (!TextUtils.isEmpty(charSequence2)) {
                ((TextView) inflate.findViewById(R.id.inapp_lp_subhead1)).setText(charSequence2);
            }
            if (!TextUtils.isEmpty(charSequence3)) {
                ((TextView) inflate.findViewById(R.id.lp_save_changes)).setText(charSequence3);
            }
            inflate.findViewById(R.id.inapp_lp_close).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    r.this.f.a();
                    if (r.this.b != null) {
                        InAppTracker.getInstance(activity.getBaseContext()).trackInAppClicked(r.this.b);
                    }
                    r.this.c(view);
                }
            });
            inflate.findViewById(R.id.lp_save_changes).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    r.this.f.a();
                    if (r.this.b != null) {
                        InAppTracker.getInstance(activity.getBaseContext()).trackInAppClicked(r.this.b);
                    }
                    r.this.c(view);
                }
            });
            this.e = true;
            return inflate;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    private View b(Activity activity) {
        new RateUsDialog(activity, this.b).show();
        return null;
    }

    private void a(View view) {
        final Context context = view.getContext();
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.lang_pref_choices);
        View linearLayout2 = new LinearLayout(context);
        int i = -2;
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setOrientation(0);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int dimension = (int) this.c.getResources().getDimension(R.dimen.grid_spacing);
        int dimension2 = (int) this.c.getResources().getDimension(R.dimen.grid_spacing);
        int i2 = 2 * dimension;
        int i3 = (displayMetrics.widthPixels - i2) - i2;
        Languages languages = (Languages) n.a(d.a().c("PREFERENCE_LANGUAGE_SETTINGS", false));
        if (languages != null) {
            this.d = languages.getArrListBusinessObj();
            if (this.d != null && this.d.size() > 0) {
                Iterator it = this.d.iterator();
                View view2 = linearLayout2;
                int i4 = 0;
                int i5 = i3;
                while (it.hasNext()) {
                    int i6;
                    final Language language = (Language) it.next();
                    i4++;
                    final TextView textView = new TextView(context);
                    textView.setSingleLine(true);
                    textView.setEllipsize(TruncateAt.END);
                    LayoutParams layoutParams2 = new LayoutParams(i, i);
                    layoutParams2.gravity = 17;
                    i = dimension / 2;
                    layoutParams2.setMargins(dimension, i, dimension, i);
                    textView.setLayoutParams(layoutParams2);
                    textView.setTextSize(2, 16.0f);
                    textView.setTextColor(this.c.getResources().getColor(R.color.first_line_color));
                    textView.setText(language.getLanguage());
                    textView.setBackgroundResource(R.drawable.selector_language_preference_choice);
                    i = dimension2 / 2;
                    textView.setPadding(dimension2, i, dimension2, i);
                    if (language.isPrefered() == 1) {
                        textView.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.language_preference_green_tickmark), null, null, null);
                    }
                    textView.measure(0, 0);
                    textView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (language.isPrefered() != 1) {
                                textView.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.language_preference_green_tickmark), null, null, null);
                                language.setIsPrefered(1);
                                return;
                            }
                            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                            language.setIsPrefered(0);
                        }
                    });
                    i = textView.getMeasuredWidth();
                    textView.setBackgroundResource(R.drawable.selector_btn_search);
                    if (i5 < i) {
                        linearLayout.addView(view2, layoutParams);
                        View linearLayout3 = new LinearLayout(context);
                        linearLayout3.setLayoutParams(layoutParams);
                        i6 = 0;
                        linearLayout3.setOrientation(0);
                        view2 = linearLayout3;
                        i5 = i3;
                    } else {
                        i6 = 0;
                    }
                    view2.addView(textView);
                    if (i4 == this.d.size()) {
                        linearLayout.addView(view2, layoutParams);
                    } else {
                        i5 = (i5 - i) - i2;
                    }
                    int i7 = i6;
                    i = -2;
                }
            }
            MoEngage.getInstance().reportLanguagesScreenViewed();
        }
    }

    private void b(View view) {
        final Context context = view.getContext();
        if (view.getId() != R.id.lp_save_changes) {
            this.e = false;
            return;
        }
        boolean z;
        Iterator it = this.d.iterator();
        do {
            z = true;
            if (!it.hasNext()) {
                z = false;
                break;
            }
        } while (((Language) it.next()).isPrefered() != 1);
        if (z) {
            w.a(this.c).a(context, this.d, new b() {
                public void onLanguageSavedOnServer(String str, boolean z) {
                    ((GaanaActivity) context).hideProgressDialog();
                    if (z) {
                        r.this.f.a();
                        MoEngage.getInstance().reportLanguageSet(r.this.d);
                        o.a().b();
                        aj.a().a(context, str);
                        r.this.c.setSidebarActiveBtn(R.id.GaanaHome);
                        Intent intent = new Intent(context, GaanaActivity.class);
                        intent.setFlags(71303168);
                        context.startActivity(intent);
                        return;
                    }
                    ap.a().a(context, str);
                }
            });
            InAppManager.getInstance().trackInAppPrimaryClick(context, this.b);
        } else {
            aj.a().a(context, context.getString(R.string.select_one_language));
        }
        this.e = false;
    }

    private void c(View view) {
        final Context context = view.getContext();
        if (view.getId() != R.id.lp_save_changes) {
            this.e = false;
            return;
        }
        ((BaseActivity) context).checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                ag.a(context).a(context, "MOEngage", new a() {
                    public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                        ((BaseActivity) context).updateUserStatus(new au() {
                            public void onUserStatusUpdated() {
                                ((BaseActivity) context).hideProgressDialog();
                                ap.a().a(context);
                                Util.aa();
                                aj.a().a(context, context.getResources().getString(R.string.enjoy_using_gaana_plus));
                                Intent intent = new Intent(context, GaanaActivity.class);
                                intent.setFlags(71303168);
                                context.startActivity(intent);
                            }
                        });
                    }

                    public void onFailure(String str, String str2) {
                        if (!TextUtils.isEmpty(str)) {
                            aj.a().a(context, str);
                        }
                    }
                });
                InAppManager.getInstance().trackInAppPrimaryClick(context, r.this.b);
            }
        }, context.getResources().getString(R.string.LOGIN_LAUNCHED_FROM_GAANA_SUBSCRIBE));
        this.e = false;
    }
}
