package com.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.content.ContextCompat;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.facebook.GraphResponse;
import com.facebook.messenger.MessengerUtils;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.models.ReferralResponse;
import com.google.gson.GsonBuilder;
import com.i.i;
import com.i.j;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.aj;
import com.services.a;
import com.services.d;
import com.services.f;
import com.services.g;
import com.utilities.Util;
import java.util.List;

public class ReferralScreenFragment extends BaseGaanaFragment implements OnClickListener, a {
    int[] a = new int[]{81, 78, 76, 80, 77, 79, 75};
    private LayoutInflater b;
    private View c = null;
    private List<ResolveInfo> d;
    private Drawable e;
    private GenericBackActionBar f;
    private ReferralResponse g;
    private TextView h;
    private TextView i;
    private a j;
    private f k;
    private TypedArray l;
    private boolean m = false;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.m = false;
        if (this.c == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.b = getActivity().getLayoutInflater();
            this.c = setContentView(R.layout.view_referral_screen_fragment, viewGroup);
            a(this.c);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.free_gaana));
            stringBuilder.append("+");
            a(stringBuilder.toString());
            a();
        }
        this.j = new a(this.mContext);
        setGAScreenName("ReferralScreen", "ReferralScreen");
        return this.c;
    }

    private void a(String str) {
        ((GaanaActivity) this.mContext).title = str;
        if (this.f == null) {
            this.f = new GenericBackActionBar(this.mContext, Util.f(str));
        }
        setActionBar(this.c, this.f);
    }

    private void a(View view) {
        TextView textView = (TextView) view.findViewById(R.id.more_share_options);
        TextView textView2 = (TextView) view.findViewById(R.id.view_terms_and_conditions);
        TextView textView3 = (TextView) view.findViewById(R.id.view_activity_title);
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.whtsapp_share);
        CrossFadeImageView crossFadeImageView2 = (CrossFadeImageView) view.findViewById(R.id.facebook_share);
        CrossFadeImageView crossFadeImageView3 = (CrossFadeImageView) view.findViewById(R.id.messenger_share);
        this.h = (TextView) view.findViewById(R.id.invite_code);
        this.i = (TextView) view.findViewById(R.id.free_gaanaplus_earned_period);
        TextView textView4 = (TextView) view.findViewById(R.id.view_get_referred);
        textView2.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        crossFadeImageView.setOnClickListener(this);
        crossFadeImageView2.setOnClickListener(this);
        crossFadeImageView3.setOnClickListener(this);
        this.k = new f(this.mContext);
        int[] iArr = new int[]{R.attr.selector_btn_global_bg_transparent, R.attr.streaming_quality_layout_drawable};
        this.e = ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(86, -1));
        this.l = this.mContext.obtainStyledAttributes(this.a);
    }

    private void a() {
        this.g = (ReferralResponse) new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(d.a().b("PREFERENCE_REFERRAL_SHARE_INFO", null, true), ReferralResponse.class);
        if (this.g != null) {
            b(true);
            a(false);
        } else {
            a(true);
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        this.d = this.mContext.getPackageManager().queryIntentActivities(intent, 0);
    }

    private void a(boolean z) {
        if (z) {
            ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.getting_referral_code));
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/index.php?type=referral&subtype=get_referral_code");
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(ReferralResponse.class);
        i.a().a(uRLManager, toString(), (b) this, (com.android.volley.i.a) this);
    }

    public void onResponse(Object obj) {
        if (!this.m) {
            super.onResponse(obj);
            if (obj != null) {
                this.g = (ReferralResponse) obj;
                ((BaseActivity) this.mContext).hideProgressDialog();
                b(false);
            }
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        ((BaseActivity) this.mContext).hideProgressDialog();
    }

    public void onDestroyView() {
        if (this.c.getParent() != null) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        super.onDestroyView();
        this.m = true;
        j.a().a(toString());
    }

    private void b(boolean z) {
        if (!z) {
            d.a().a("PREFERENCE_REFERRAL_SHARE_INFO", new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.g), true);
        }
        this.h.setText(this.g.getReferralCode());
        this.h.setTextSize(2, 28.0f);
        this.h.setTextColor(getResources().getColor(R.color.f17gaana.red));
        TextView textView = this.i;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.g.getGaanaPlusDaysEarned());
        stringBuilder.append("");
        textView.setText(stringBuilder.toString());
        textView = (TextView) this.c.findViewById(R.id.view_activity_title);
        TextView textView2 = (TextView) this.c.findViewById(R.id.view_get_referred);
        if (!this.g.getIsEligble()) {
            textView2.setVisibility(8);
            LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
            layoutParams.addRule(9, 0);
            layoutParams.addRule(13, -1);
            textView.setLayoutParams(layoutParams);
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void b(View view) {
        final LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        final View inflate = layoutInflater.inflate(R.layout.referral_share_menu_layout, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.mContext);
        bottomSheetDialog.setContentView(inflate);
        Behavior behavior = ((CoordinatorLayout.LayoutParams) ((View) inflate.getParent()).getLayoutParams()).getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            final BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetCallback() {
                public void onSlide(@NonNull View view, float f) {
                }

                public void onStateChanged(@NonNull View view, int i) {
                }
            });
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    inflate.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    bottomSheetBehavior.setPeekHeight(inflate.getMeasuredHeight());
                }
            });
        }
        ListView listView = (ListView) inflate.findViewById(R.id.share_options_list);
        final String[] strArr = new String[]{getString(R.string.whatsapp), getString(R.string.messenger), getString(R.string.facebook), getString(R.string.twitter), getString(R.string.gmail), getString(R.string.message), getString(R.string.copy_to_clipboard)};
        listView.setSelector(this.e);
        listView.setAdapter(new BaseAdapter() {
            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return 0;
            }

            public int getCount() {
                return strArr.length;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = layoutInflater.inflate(R.layout.referral_share_option_item, viewGroup, false);
                }
                CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.share_option_img);
                TextView textView = (TextView) view.findViewById(R.id.share_option_name);
                TypedArray obtainStyledAttributes = ReferralScreenFragment.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                Drawable drawable = ContextCompat.getDrawable(ReferralScreenFragment.this.getContext(), obtainStyledAttributes.getResourceId(ReferralScreenFragment.this.a[i], -1));
                obtainStyledAttributes.recycle();
                crossFadeImageView.setImageDrawable(drawable);
                textView.setText(strArr[i]);
                return view;
            }
        });
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                bottomSheetDialog.hide();
                if (ReferralScreenFragment.this.g == null) {
                    aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.sorry_some_thing_went_wrong));
                    return;
                }
                ResolveInfo a;
                switch (i) {
                    case 0:
                        a = ReferralScreenFragment.this.b("com.whatsapp");
                        if (a == null) {
                            aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.whatsapp_not_installed));
                            break;
                        }
                        UninstallIO.sendReferFriendEvent("Whatsapp");
                        MoEngage.getInstance().reportReferralSource("Whatsapp");
                        ((BaseActivity) ReferralScreenFragment.this.mContext).sendGAEvent(((BaseActivity) ReferralScreenFragment.this.mContext).currentScreen, "Invite", "Whatsapp");
                        ReferralScreenFragment.this.j.a(a, "", ReferralScreenFragment.this.g.getMessage(), "", "", null, "");
                        break;
                    case 1:
                        a = ReferralScreenFragment.this.b(MessengerUtils.PACKAGE_NAME);
                        if (a == null) {
                            aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.messenger_not_installed));
                            break;
                        }
                        UninstallIO.sendReferFriendEvent("Messenger");
                        MoEngage.getInstance().reportReferralSource("Messenger");
                        ((BaseActivity) ReferralScreenFragment.this.mContext).sendGAEvent(((BaseActivity) ReferralScreenFragment.this.mContext).currentScreen, "Invite", "Messenger");
                        ReferralScreenFragment.this.j.a(a, "", ReferralScreenFragment.this.g.getMessage(), "", "", null, "");
                        break;
                    case 2:
                        ((GaanaActivity) ReferralScreenFragment.this.mContext).showProgressDialog(Boolean.valueOf(true), ReferralScreenFragment.this.getString(R.string.posting_on_wall));
                        g.a().a((Activity) ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.g.getMessage(), ReferralScreenFragment.this.mContext, new g.a() {
                            public String OnAuthrizationSuccess() {
                                ((GaanaActivity) ReferralScreenFragment.this.mContext).hideProgressDialog();
                                UninstallIO.sendReferFriendEvent("Facebook");
                                MoEngage.getInstance().reportReferralSource("Facebook");
                                ((BaseActivity) ReferralScreenFragment.this.mContext).sendGAEvent(((BaseActivity) ReferralScreenFragment.this.mContext).currentScreen, "Invite", "Facebook");
                                aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.posted_successfully));
                                return null;
                            }

                            public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
                                ((GaanaActivity) ReferralScreenFragment.this.mContext).hideProgressDialog();
                                aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.some_error_occurred));
                            }
                        });
                        break;
                    case 3:
                        a = ReferralScreenFragment.this.b("com.twitter.android");
                        if (a == null) {
                            aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.twitter_not_installed));
                            break;
                        }
                        UninstallIO.sendReferFriendEvent("Twitter");
                        MoEngage.getInstance().reportReferralSource("Twitter");
                        ((BaseActivity) ReferralScreenFragment.this.mContext).sendGAEvent(((BaseActivity) ReferralScreenFragment.this.mContext).currentScreen, "Invite", "Twitter");
                        ReferralScreenFragment.this.j.a(a, "", ReferralScreenFragment.this.g.getMessageSMS(), "", "", null, "");
                        break;
                    case 4:
                        a = ReferralScreenFragment.this.b("com.google.android.gm");
                        if (a == null) {
                            aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.gmai_not_installed));
                            break;
                        }
                        UninstallIO.sendReferFriendEvent("Gmail");
                        MoEngage.getInstance().reportReferralSource("Gmail");
                        ((BaseActivity) ReferralScreenFragment.this.mContext).sendGAEvent(((BaseActivity) ReferralScreenFragment.this.mContext).currentScreen, "Invite", "Gmail");
                        ReferralScreenFragment.this.j.a(a, ReferralScreenFragment.this.g.getMessageSubject(), ReferralScreenFragment.this.g.getMessage(), "", "", null, "");
                        break;
                    case 5:
                        try {
                            UninstallIO.sendReferFriendEvent("SMS");
                            MoEngage.getInstance().reportReferralSource("SMS");
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.putExtra("sms_body", ReferralScreenFragment.this.g.getMessageSMS());
                            intent.setType("vnd.android-dir/mms-sms");
                            ((BaseActivity) ReferralScreenFragment.this.mContext).sendGAEvent(((BaseActivity) ReferralScreenFragment.this.mContext).currentScreen, "Invite", "SMS");
                            ReferralScreenFragment.this.startActivity(intent);
                            break;
                        } catch (Exception unused) {
                            aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.not_able_to_share_via_sms));
                            break;
                        }
                    case 6:
                        ((ClipboardManager) ReferralScreenFragment.this.mContext.getSystemService("clipboard")).setText(ReferralScreenFragment.this.g.getReferralUrl());
                        aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.copied_to_clipboard));
                        UninstallIO.sendReferFriendEvent("Copy");
                        MoEngage.getInstance().reportReferralSource("Copy");
                        ((BaseActivity) ReferralScreenFragment.this.mContext).sendGAEvent(((BaseActivity) ReferralScreenFragment.this.mContext).currentScreen, "Invite", "Copy");
                        break;
                }
            }
        });
        bottomSheetDialog.show();
    }

    public void onClick(View view) {
        ResolveInfo b;
        switch (view.getId()) {
            case R.id.facebook_share /*2131297107*/:
                ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), getString(R.string.posting_on_your_wall));
                if (this.g != null) {
                    g.a().a((Activity) this.mContext, this.g.getMessage(), this.mContext, new g.a() {
                        public String OnAuthrizationSuccess() {
                            ((GaanaActivity) ReferralScreenFragment.this.mContext).hideProgressDialog();
                            UninstallIO.sendReferFriendEvent("Facebook");
                            MoEngage.getInstance().reportReferralSource("Facebook");
                            ((BaseActivity) ReferralScreenFragment.this.mContext).sendGAEvent(((BaseActivity) ReferralScreenFragment.this.mContext).currentScreen, "Invite", "Facebook");
                            aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.getString(R.string.posted_successfully));
                            return null;
                        }

                        public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
                            ((GaanaActivity) ReferralScreenFragment.this.mContext).hideProgressDialog();
                            aj.a().a(ReferralScreenFragment.this.mContext, ReferralScreenFragment.this.mContext.getString(R.string.some_error_occurred));
                        }
                    });
                    return;
                }
                ((GaanaActivity) this.mContext).hideProgressDialog();
                aj.a().a(this.mContext, this.mContext.getString(R.string.sorry_some_thing_went_wrong));
                return;
            case R.id.messenger_share /*2131297725*/:
                b = b(MessengerUtils.PACKAGE_NAME);
                if (b != null) {
                    UninstallIO.sendReferFriendEvent("Messenger");
                    MoEngage.getInstance().reportReferralSource("Messenger");
                    ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Invite", "Messenger");
                    if (this.g != null) {
                        this.j.a(b, "", this.g.getMessage(), "", "", null, "");
                        return;
                    } else {
                        aj.a().a(this.mContext, this.mContext.getString(R.string.sorry_some_thing_went_wrong));
                        return;
                    }
                }
                aj.a().a(this.mContext, this.mContext.getString(R.string.messenger_not_installed));
                return;
            case R.id.more_share_options /*2131297744*/:
                b(view);
                return;
            case R.id.view_activity_title /*2131298861*/:
                BaseGaanaFragment viewReferralActivityFragment = new ViewReferralActivityFragment();
                viewReferralActivityFragment.a(this.g);
                ((GaanaActivity) this.mContext).displayFragment(viewReferralActivityFragment);
                return;
            case R.id.view_get_referred /*2131298868*/:
                b();
                return;
            case R.id.view_terms_and_conditions /*2131298879*/:
                ((GaanaActivity) this.mContext).startHelpShiftActivitySection("18");
                return;
            case R.id.whtsapp_share /*2131298936*/:
                b = b("com.whatsapp");
                if (b != null) {
                    UninstallIO.sendReferFriendEvent("Whatsapp");
                    MoEngage.getInstance().reportReferralSource("Whatsapp");
                    ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Invite", "Whatsapp");
                    if (this.g != null) {
                        this.j.a(b, "", this.g.getMessage(), "", "", null, "");
                        return;
                    } else {
                        aj.a().a(this.mContext, getString(R.string.sorry_some_thing_went_wrong));
                        return;
                    }
                }
                aj.a().a(this.mContext, getString(R.string.whatsapp_not_installed));
                return;
            default:
                return;
        }
    }

    private ResolveInfo b(String str) {
        if (this.d != null) {
            for (ResolveInfo resolveInfo : this.d) {
                if (resolveInfo.activityInfo.packageName.equalsIgnoreCase(str)) {
                    return resolveInfo;
                }
            }
        }
        return null;
    }

    private void b() {
        ((GaanaActivity) this.mContext).displayFragment(new GetReferredFragment());
    }
}
