package com.gaana.view.item;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.GraphResponse;
import com.facebook.messenger.MessengerUtils;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.models.BusinessObject;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.u;
import com.services.b;
import com.services.g;
import com.services.g.a;
import com.services.l.s;
import com.utilities.Util;
import java.util.List;

public class PopupShareitemView extends BottomSheetDialog implements OnClickListener {
    private static final String DEFAULT_MESSAGING_PACKAGE_NAME = "com.android.mms";
    private static final String FACEBOOK_MESSENGER_PACKAGE_NAME = "com.facebook.orca";
    private static final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";
    private static final String GMAIL_PACKAGE_NAME = "com.google.android.gm";
    private static final String GOOGLEPLUS_PACKAGE_NAME = "com.google.android.apps.plus";
    private static final String MESSANGER_PACKAGE_NAME = "com.facebook.orca";
    private static final String TWITTER_PACKAGE_NAME = "com.twitter.android";
    private static final String WHATSAPP_PACKAGE_NAME = "com.whatsapp";
    private List<ResolveInfo> availableShareActivities;
    private CoordinatorLayout coordinatorLayout;
    private String mAddUrl = null;
    private GaanaApplication mAppState;
    private String mArtwork;
    private Context mContext;
    private b mDeepLinkingListAdapter;
    private LayoutInflater mInflater;
    private String mItem;
    private String mSubject;
    private String mUrlForShare;
    private View mView;
    private ResolveInfo resolveInfo;
    private String subTitleName;
    private String titleName;

    private void callDedicatedApi() {
    }

    private void callShareToFollowerApi() {
    }

    public PopupShareitemView(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        super(context);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mSubject = str;
        this.mUrlForShare = str2;
        this.mArtwork = str3;
        this.mItem = str4;
        this.titleName = str5;
        this.subTitleName = str6;
        this.mAppState = GaanaApplication.getInstance();
        this.mAddUrl = str7;
        requestWindowFeature(1);
        init(context);
    }

    public PopupShareitemView(Context context, String str) {
        super(context);
        this.mContext = context;
        this.mUrlForShare = str;
        requestWindowFeature(1);
    }

    private void init(Context context) {
        this.mView = this.mInflater.inflate(R.layout.popup_share_item_view_layout, null);
        this.coordinatorLayout = (CoordinatorLayout) this.mView.findViewById(R.id.coordinator_layout);
        setContentView(this.mView);
        BottomSheetBehavior.from((RelativeLayout) this.mView.findViewById(R.id.layout)).setState(3);
        ((TextView) this.mView.findViewById(R.id.shareInSideGaanaText)).setOnClickListener(this);
        ((TextView) this.mView.findViewById(R.id.shareToFollowerText)).setOnClickListener(this);
        ((TextView) this.mView.findViewById(R.id.dedicateText)).setOnClickListener(this);
        ((ImageView) this.mView.findViewById(R.id.shareInSideGaanaImage)).setOnClickListener(this);
        ((ImageView) this.mView.findViewById(R.id.shareToFollowerImage)).setOnClickListener(this);
        ((ImageView) this.mView.findViewById(R.id.dedicateImage)).setOnClickListener(this);
        ((ImageView) this.mView.findViewById(R.id.shareFacebook)).setOnClickListener(this);
        ((ImageView) this.mView.findViewById(R.id.shareTwitter)).setOnClickListener(this);
        ((ImageView) this.mView.findViewById(R.id.shareWhatsApp)).setOnClickListener(this);
        ((ImageView) this.mView.findViewById(R.id.shareCopyLink)).setOnClickListener(this);
        ((ImageView) this.mView.findViewById(R.id.shareMoreOption)).setOnClickListener(this);
        ((CrossFadeImageView) this.mView.findViewById(R.id.headerShareImage)).bindImage(this.mArtwork, this.mAppState.isAppInOfflineMode());
        ((TextView) this.mView.findViewById(R.id.headerTitleNameText)).setText(this.titleName);
        ((TextView) this.mView.findViewById(R.id.headerTitleSubNameText)).setText(this.subTitleName);
        checkAvailabilityIntent();
    }

    private void checkAvailabilityIntent() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        if (this.availableShareActivities == null) {
            this.availableShareActivities = this.mContext.getPackageManager().queryIntentActivities(intent, 0);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dedicateImage /*2131296868*/:
            case R.id.dedicateText /*2131296869*/:
                u.a().b("SocialShare", "Dedicate");
                callDedicatedApi();
                return;
            case R.id.shareCopyLink /*2131298378*/:
                u.a().a("SocialShare", "Other", "ShareCopyLink");
                shareOnCopyLink();
                return;
            case R.id.shareFacebook /*2131298379*/:
                u.a().a("SocialShare", "Other", "Facebook");
                shareOnFacebook();
                return;
            case R.id.shareInSideGaanaImage /*2131298381*/:
            case R.id.shareInSideGaanaText /*2131298382*/:
                callShareInSideGaanaApi();
                return;
            case R.id.shareMoreOption /*2131298384*/:
                u.a().a("SocialShare", "Other", "Others");
                shareOnOther();
                return;
            case R.id.shareToFollowerImage /*2131298386*/:
            case R.id.shareToFollowerText /*2131298387*/:
                u.a().b("SocialShare", "ShareWithFollower");
                callShareToFollowerApi();
                return;
            case R.id.shareTwitter /*2131298388*/:
                u.a().a("SocialShare", "Other", "Twitter");
                shareOnTwitter();
                return;
            case R.id.shareWhatsApp /*2131298389*/:
                u.a().a("SocialShare", "Other", "WhatsApp");
                shareOnWhatsApp();
                return;
            default:
                return;
        }
    }

    private void callShareInSideGaanaApi() {
        Util.a(this.mContext, getContext().getString(R.string.loading));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://social.gaana.com/event/user/share/");
        stringBuilder.append(this.mAddUrl);
        String stringBuilder2 = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.a(BusinessObjectType.BasicResponse);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                Util.b();
                aj.a().a(PopupShareitemView.this.mContext, PopupShareitemView.this.getContext().getResources().getString(R.string.success));
            }

            public void onErrorResponse(BusinessObject businessObject) {
                Util.b();
                aj.a().a(PopupShareitemView.this.mContext, PopupShareitemView.this.getContext().getString(R.string.request_not_completed));
            }
        }, uRLManager);
        dismiss();
    }

    public void shareOnOther() {
        UninstallIO.sendReferFriendEvent("Others");
        MoEngage.getInstance().reportReferralSource("Others");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", this.mUrlForShare);
        ((Activity) this.mContext).startActivity(Intent.createChooser(intent, "Share with..."));
        dismiss();
    }

    private void shareOnCopyLink() {
        ((ClipboardManager) this.mContext.getSystemService("clipboard")).setText(this.mUrlForShare);
        aj.a().a(this.mContext, this.mContext.getString(R.string.copied_to_clipboard));
        UninstallIO.sendReferFriendEvent("Copy");
        MoEngage.getInstance().reportReferralSource("Copy");
        dismiss();
    }

    private void shareOnWhatsApp() {
        this.resolveInfo = getResolveInfo(WHATSAPP_PACKAGE_NAME);
        if (this.resolveInfo != null) {
            UninstallIO.sendReferFriendEvent("Whatsapp");
            MoEngage.getInstance().reportReferralSource("Whatsapp");
            share(this.resolveInfo, "", this.mUrlForShare, "", null);
        } else {
            aj.a().a(this.mContext, this.mContext.getString(R.string.whatsapp_not_installed));
        }
        dismiss();
    }

    private void shareOnTwitter() {
        this.resolveInfo = getResolveInfo(TWITTER_PACKAGE_NAME);
        if (this.resolveInfo != null) {
            UninstallIO.sendReferFriendEvent("Twitter");
            MoEngage.getInstance().reportReferralSource("Twitter");
            share(this.resolveInfo, "", this.mUrlForShare, "", null);
        } else {
            aj.a().a(this.mContext, this.mContext.getString(R.string.twitter_not_installed));
        }
        dismiss();
    }

    private void shareOnFacebook() {
        ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.posting_on_your_wall));
        g.a().a((Activity) this.mContext, this.mUrlForShare, this.mContext, new a() {
            public String OnAuthrizationSuccess() {
                ((GaanaActivity) PopupShareitemView.this.mContext).hideProgressDialog();
                UninstallIO.sendReferFriendEvent("Facebook");
                MoEngage.getInstance().reportReferralSource("Facebook");
                aj.a().a(PopupShareitemView.this.mContext, PopupShareitemView.this.mContext.getString(R.string.posted_successfully));
                PopupShareitemView.this.dismiss();
                return null;
            }

            public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
                ((GaanaActivity) PopupShareitemView.this.mContext).hideProgressDialog();
                aj.a().a(PopupShareitemView.this.mContext, PopupShareitemView.this.mContext.getString(R.string.some_error_occurred));
                PopupShareitemView.this.dismiss();
            }
        });
    }

    private ResolveInfo getResolveInfo(String str) {
        if (this.availableShareActivities != null) {
            for (ResolveInfo resolveInfo : this.availableShareActivities) {
                if (resolveInfo.activityInfo.packageName.equalsIgnoreCase(str)) {
                    return resolveInfo;
                }
            }
        }
        return null;
    }

    public void share(ResolveInfo resolveInfo, String str, String str2, String str3, String str4) {
        this.mContext.getString(R.string.mode_other);
        str3 = "Other";
        Intent intent;
        if (resolveInfo.activityInfo.packageName.endsWith(TWITTER_PACKAGE_NAME)) {
            this.mContext.getString(R.string.mode_twitter);
            str3 = "Twitter";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(" @gaana");
            str2 = stringBuilder.toString();
            intent = new Intent("android.intent.action.SEND");
            intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", str);
            intent.putExtra("android.intent.extra.TEXT", str2);
            ((Activity) this.mContext).startActivity(intent);
        } else {
            intent = new Intent("android.intent.action.SEND");
            intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            intent.setType("text/plain");
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("android.intent.extra.SUBJECT", str);
            }
            if (resolveInfo.activityInfo.packageName.equals(MessengerUtils.PACKAGE_NAME)) {
                this.mContext.getString(R.string.mode_fb_messenger);
                str3 = "Fb Messenger";
            } else if (resolveInfo.activityInfo.packageName.equals(DEFAULT_MESSAGING_PACKAGE_NAME)) {
                this.mContext.getString(R.string.mode_sms);
                str3 = "SMS";
            } else if (resolveInfo.activityInfo.packageName.equals(WHATSAPP_PACKAGE_NAME)) {
                this.mContext.getString(R.string.mode_whatsapp);
                str3 = "Whatsapp";
            } else if (resolveInfo.activityInfo.packageName.equals(MessengerUtils.PACKAGE_NAME)) {
                this.mContext.getString(R.string.mode_messenger);
                str3 = "Messanger";
            } else if (resolveInfo.activityInfo.packageName.equals(GMAIL_PACKAGE_NAME)) {
                this.mContext.getString(R.string.mode_gmail);
                str3 = "Gmail";
            }
            intent.putExtra("android.intent.extra.TEXT", str2);
            ((Activity) this.mContext).startActivity(intent);
        }
        if (str4 != null && !TextUtils.isEmpty(str4)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str4);
            stringBuilder2.append(" shared");
            u.a().a("Share widget", stringBuilder2.toString(), str3);
        }
    }
}
