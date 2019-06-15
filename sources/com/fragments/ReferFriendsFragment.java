package com.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.constants.Constants;
import com.facebook.GraphResponse;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.login.FbLoginErrorDialog;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.CustomInvite;
import com.gaana.models.CustomInvite.PromoText;
import com.gaana.models.CustomInvite.TextMsg;
import com.gaana.models.Referral;
import com.gaana.models.User.LoginType;
import com.i.i;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.ap;
import com.services.d;
import com.services.f;
import com.services.g;
import com.services.g.a;
import com.services.l.ad;
import com.services.l.af;
import com.services.l.s;
import com.services.n;
import com.utilities.Util;
import java.util.Calendar;
import java.util.List;

public class ReferFriendsFragment extends BaseGaanaFragment implements OnClickListener, a {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private View i = null;
    private com.services.a j;
    private long k;
    private CustomInvite l;
    private List<ResolveInfo> m;

    public String OnAuthrizationSuccess() {
        return null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.i == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.i = setContentView(R.layout.view_refer_friend, viewGroup);
            c();
            a();
            this.a = (TextView) this.i.findViewById(R.id.button_refer_facebook);
            this.b = (TextView) this.i.findViewById(R.id.button_refer_email);
            this.c = (TextView) this.i.findViewById(R.id.button_refer_facebook_invite);
            this.f = (TextView) this.i.findViewById(R.id.button_refer_copy);
            this.d = (TextView) this.i.findViewById(R.id.button_refer_whatsapp);
            this.e = (TextView) this.i.findViewById(R.id.button_refer_twitter);
            this.e = (TextView) this.i.findViewById(R.id.button_refer_twitter);
            this.g = (TextView) this.i.findViewById(R.id.button_refer_sms);
            this.h = (TextView) this.i.findViewById(R.id.button_refer_others);
            this.a.setOnClickListener(this);
            this.b.setOnClickListener(this);
            this.c.setOnClickListener(this);
            this.f.setOnClickListener(this);
            this.d.setOnClickListener(this);
            this.e.setOnClickListener(this);
            this.g.setOnClickListener(this);
            this.h.setOnClickListener(this);
            setActionBar(this.i, new GenericBackActionBar(this.mContext, getString(R.string.invite_friends)));
        }
        this.j = new com.services.a(this.mContext);
        updateView();
        setGAScreenName("ReferFriendScreen", "ReferFriendScreen");
        MoEngage.getInstance().reportSectionViewedEvent("InviteFriends");
        return this.i;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void a() {
        String c = d.a().c("PREFERENCE_REFER_FRIENDS_CAMPAIGN_TEXT", false);
        boolean z = true;
        if (!TextUtils.isEmpty(c)) {
            this.l = (CustomInvite) n.a(c);
            if (((int) ((System.currentTimeMillis() - this.l.getLastUpdatedTime()) / 1000)) <= 86400) {
                z = false;
            }
        }
        if (z) {
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/index.php?type=custom_message&subtype=inviteNote");
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a(CustomInvite.class);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    ReferFriendsFragment.this.l = (CustomInvite) obj;
                    if (ReferFriendsFragment.this.l != null) {
                        ReferFriendsFragment.this.l.setLastUpdatedTime(System.currentTimeMillis());
                        d.a().a("PREFERENCE_REFER_FRIENDS_CAMPAIGN_TEXT", n.a(ReferFriendsFragment.this.l), false);
                        ReferFriendsFragment.this.b();
                    }
                }
            }, uRLManager);
            return;
        }
        b();
    }

    private void b() {
        PromoText[] promoText = this.l.getPromoText();
        if (promoText != null) {
            TextMsg[] textMessage = promoText[0].getTextMessage();
            if (textMessage != null) {
                String text = textMessage[0].getText();
                String text2 = textMessage[1].getText();
                String text3 = promoText[1].getTextMessage()[0].getText();
                ((LinearLayout) this.i.findViewById(R.id.custom_invite)).setVisibility(0);
                ((TextView) this.i.findViewById(R.id.custom_invite_msg_1)).setText(text);
                ((TextView) this.i.findViewById(R.id.custom_invite_msg_2)).setText(text2);
                ((TextView) this.i.findViewById(R.id.custom_invite_msg_3)).setText(text3);
            }
        }
    }

    private void a(boolean z) {
        this.a.setClickable(z);
        this.b.setClickable(z);
        this.c.setClickable(z);
        this.f.setClickable(z);
        this.d.setClickable(z);
        this.e.setClickable(z);
        this.g.setClickable(z);
        this.h.setClickable(true);
    }

    public void onResume() {
        super.onResume();
        a(true);
        ((GaanaActivity) this.mContext).title = getString(R.string.invite_friends_to_gaana);
    }

    public void onClick(final View view) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("Refer friend");
        } else if (Util.j(this.mContext)) {
            this.k = Calendar.getInstance().getTimeInMillis();
            ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
                public void onLoginSuccess() {
                    ReferFriendsFragment.this.a(view.getId());
                }
            }, getResources().getString(R.string.LOGIN_LAUNCHED_TO_REFER_FRIEND));
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void c() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        this.m = this.mContext.getPackageManager().queryIntentActivities(intent, 0);
    }

    private ResolveInfo a(String str) {
        if (this.m != null) {
            for (ResolveInfo resolveInfo : this.m) {
                if (resolveInfo.activityInfo.packageName.equalsIgnoreCase(str)) {
                    return resolveInfo;
                }
            }
        }
        return null;
    }

    private void d() {
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            if (this.mContext instanceof BaseActivity) {
                ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), getString(R.string.getting_your_fb_friends));
            }
            g.a().a(getActivity(), (a) this);
            return;
        }
        LoginManager.getInstance().login((Activity) this.mContext, LoginType.FB, new IOnLoginCompleted() {
            public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
                if (login_status == LOGIN_STATUS.LOGIN_SUCCEDED) {
                    g.a().a((GaanaActivity) ReferFriendsFragment.this.mContext, ReferFriendsFragment.this);
                    ((GaanaActivity) ReferFriendsFragment.this.mContext).updateSidebarUserDetails();
                } else if (login_status == LOGIN_STATUS.LOGIN_EMAIL_MISSING_FB) {
                    if (ReferFriendsFragment.this.mContext != null && !((Activity) ReferFriendsFragment.this.mContext).isFinishing()) {
                        FbLoginErrorDialog fbLoginErrorDialog = new FbLoginErrorDialog(ReferFriendsFragment.this.mContext);
                        fbLoginErrorDialog.setOnLoginCompletedListener(this);
                        fbLoginErrorDialog.show();
                    }
                } else if (login_status == LOGIN_STATUS.LOGIN_MANDATORY_FIELD_MISSING && ReferFriendsFragment.this.mContext != null && !((Activity) ReferFriendsFragment.this.mContext).isFinishing()) {
                    new f(ReferFriendsFragment.this.mContext).a(ReferFriendsFragment.this.mContext.getResources().getString(R.string.mandatory_field_missing));
                }
            }
        });
    }

    private void a(final int i) {
        if (this.mAppState.getCurrentUser().getUserProfile() != null) {
            String str = "Referral";
            String str2 = "invite";
            String str3 = "";
            if (i == R.id.button_refer_facebook) {
                str3 = "Facebook Wall Post";
            } else if (i == R.id.button_refer_facebook_invite) {
                str3 = "Facebook Invite";
            } else if (i == R.id.button_refer_copy) {
                str3 = "Copy Link";
            } else if (i == R.id.button_refer_whatsapp) {
                str3 = "WhatsApp";
            } else if (i == R.id.button_refer_twitter) {
                str3 = "Twitter Post";
            } else if (i == R.id.button_refer_email) {
                str3 = "Email";
            } else if (i == R.id.button_refer_sms) {
                str3 = "SMS";
            } else if (i == R.id.button_refer_others) {
                str3 = "Others";
            }
            ((BaseActivity) this.mContext).sendGAEvent(str, str2, str3);
            com.managers.d.a().a(this.mContext, new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject == null || !(businessObject instanceof Referral)) {
                        aj.a().a(ReferFriendsFragment.this.mContext, ReferFriendsFragment.this.getString(R.string.error_getting_in_referral_link));
                        return;
                    }
                    ReferFriendsFragment.this.a(i, (Referral) businessObject);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis();
                    if (ReferFriendsFragment.this.k != 0) {
                        Constants.a("Referral", timeInMillis - ReferFriendsFragment.this.k, "Fetch referral link", null);
                    }
                }
            });
        }
    }

    private void a(int i, Referral referral) {
        ResolveInfo a;
        Intent intent;
        switch (i) {
            case R.id.button_refer_copy /*2131296578*/:
                ((ClipboardManager) this.mContext.getSystemService("clipboard")).setText(referral.getReferralUrl());
                aj.a().a(this.mContext, getString(R.string.copied_to_clipboard));
                UninstallIO.sendReferFriendEvent("Copy");
                MoEngage.getInstance().reportReferralSource("Copy");
                return;
            case R.id.button_refer_email /*2131296579*/:
                UninstallIO.sendReferFriendEvent("Email");
                MoEngage.getInstance().reportReferralSource("Email");
                intent = new Intent("android.intent.action.SEND");
                intent.setType("plain/text");
                intent.putExtra("android.intent.extra.SUBJECT", referral.getMessageSub());
                intent.putExtra("android.intent.extra.TEXT", referral.getMessage());
                startActivity(Intent.createChooser(intent, "Invite friends to Gaana"));
                return;
            case R.id.button_refer_facebook /*2131296580*/:
                ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), getString(R.string.posting_on_your_wall));
                g.a().a((Activity) this.mContext, referral.getMessage(), this.mContext, new a() {
                    public String OnAuthrizationSuccess() {
                        ((GaanaActivity) ReferFriendsFragment.this.mContext).hideProgressDialog();
                        UninstallIO.sendReferFriendEvent("Facebook");
                        MoEngage.getInstance().reportReferralSource("Facebook");
                        aj.a().a(ReferFriendsFragment.this.mContext, ReferFriendsFragment.this.getString(R.string.posted_successfully));
                        return null;
                    }

                    public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
                        ((GaanaActivity) ReferFriendsFragment.this.mContext).hideProgressDialog();
                        aj.a().a(ReferFriendsFragment.this.mContext, ReferFriendsFragment.this.getString(R.string.some_error_occurred));
                    }
                });
                return;
            case R.id.button_refer_facebook_invite /*2131296581*/:
                d();
                return;
            case R.id.button_refer_others /*2131296582*/:
                UninstallIO.sendReferFriendEvent("Others");
                MoEngage.getInstance().reportReferralSource("Others");
                intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", referral.getMessage());
                ((Activity) this.mContext).startActivity(Intent.createChooser(intent, "Share with..."));
                return;
            case R.id.button_refer_sms /*2131296583*/:
                try {
                    UninstallIO.sendReferFriendEvent("SMS");
                    MoEngage.getInstance().reportReferralSource("SMS");
                    intent = new Intent("android.intent.action.VIEW");
                    intent.putExtra("sms_body", referral.getMessageSms());
                    intent.setType("vnd.android-dir/mms-sms");
                    startActivity(intent);
                    return;
                } catch (Exception unused) {
                    a = a("com.bsb.hike");
                    if (a != null) {
                        this.j.a(a, "", referral.getMessageSms(), "", "", null, "");
                        return;
                    } else {
                        aj.a().a(this.mContext, getString(R.string.not_able_to_share_via_sms));
                        return;
                    }
                }
            case R.id.button_refer_twitter /*2131296584*/:
                a = a("com.twitter.android");
                if (a != null) {
                    UninstallIO.sendReferFriendEvent("Twitter");
                    MoEngage.getInstance().reportReferralSource("Twitter");
                    this.j.a(a, "", referral.getMessageSms(), "", "", null, "");
                    return;
                }
                aj.a().a(this.mContext, getString(R.string.twitter_not_installed));
                return;
            case R.id.button_refer_whatsapp /*2131296585*/:
                a = a("com.whatsapp");
                if (a != null) {
                    UninstallIO.sendReferFriendEvent("Whatsapp");
                    MoEngage.getInstance().reportReferralSource("Whatsapp");
                    this.j.a(a, "", referral.getMessage(), "", "", null, "");
                    return;
                }
                aj.a().a(this.mContext, getString(R.string.whatsapp_not_installed));
                return;
            default:
                return;
        }
    }

    public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
        aj.a().a(this.mContext, getString(R.string.error_while_connecting));
        a(true);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        if (this.i.getParent() != null) {
            ((ViewGroup) this.i.getParent()).removeView(this.i);
        }
        super.onDestroyView();
    }
}
