package com.gaana;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.FavouriteSyncManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.BasicResponse;
import com.gaana.models.BusinessObject;
import com.gaana.models.TrialProductFeature;
import com.i.i;
import com.library.controls.CircularImageView;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ag;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.managers.y;
import com.services.d;
import com.services.l.af;
import com.services.l.g;
import com.services.l.s;
import com.utilities.Util;
import java.util.Date;

public class NavigationHeaderMenu extends LinearLayout {
    private GaanaApplication mAppState;
    private Context mContext;
    d mDeviceResManager;
    OnCheckedChangeListener offlineSwitchListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            NavigationHeaderMenu.this.mDeviceResManager.a("PREFERENCE_KEY_OFFLINE_MODE", z, false);
            if (z) {
                NavigationHeaderMenu.this.mDeviceResManager.a(System.currentTimeMillis(), "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                NavigationHeaderMenu.this.mDeviceResManager.a(System.currentTimeMillis(), "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                NavigationHeaderMenu.this.mAppState.setAppInOfflineMode(true);
                DownloadManager.c().e();
            } else {
                NavigationHeaderMenu.this.mDeviceResManager.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                NavigationHeaderMenu.this.mDeviceResManager.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                NavigationHeaderMenu.this.mAppState.setAppInOfflineMode(false);
                DownloadManager.c().d();
                PlaylistSyncManager.getInstance().performSync();
                FavouriteSyncManager.getInstance().performSync(new g() {
                    public void favouriteSyncCompleted() {
                    }
                });
            }
            NavigationHeaderMenu.this.refreshOfflineModeExpiryText(NavigationHeaderMenu.this.tvOfflineModeExpiryDays);
        }
    };
    private View parentView;
    SwitchCompat switchOfflineMode;
    TextView tvOfflineModeExpiryDays;
    private int userStatus = 0;

    private void refreshOfflineModeExpiryText(TextView textView) {
    }

    public NavigationHeaderMenu(Context context) {
        super(context);
        init(context);
    }

    public NavigationHeaderMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            init(context);
        }
    }

    public NavigationHeaderMenu(Context context, int i, int i2) {
        super(context);
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.nav_header_menu, null);
        addView(relativeLayout);
        relativeLayout.removeAllViews();
        View view = new View(context);
        view.setLayoutParams(new LayoutParams(i, i2));
        relativeLayout.addView(view);
    }

    public boolean isInEditMode() {
        return super.isInEditMode();
    }

    private void init(Context context) {
        this.mContext = context;
        this.mAppState = (GaanaApplication) context.getApplicationContext();
        this.mDeviceResManager = d.a();
        this.parentView = LayoutInflater.from(context).inflate(R.layout.nav_header_menu, null);
        this.tvOfflineModeExpiryDays = (TextView) this.parentView.findViewById(R.id.subheaderText);
        addView(this.parentView);
        ((ImageView) this.parentView.findViewById(R.id.img_back_settings)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((GaanaActivity) NavigationHeaderMenu.this.mContext).closeDrawer();
            }
        });
        this.parentView.findViewById(R.id.rlProfileSideBar).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NavigationHeaderMenu.this.mAppState.setSidebarActiveBtn(view.getId());
                if (NavigationHeaderMenu.this.userStatus != 0) {
                    ((BaseActivity) NavigationHeaderMenu.this.mContext).sendGAEvent("LeftNav", "User Profile", " LeftNav - User Profile");
                } else {
                    ((BaseActivity) NavigationHeaderMenu.this.mContext).sendGAEvent("LeftNav", "Login", "LeftNav - Login");
                    NavigationHeaderMenu.this.mAppState.setSidebarActiveBtn(R.id.LeftMenuLogin);
                }
                ((GaanaActivity) NavigationHeaderMenu.this.mContext).closeDrawer();
            }
        });
        ((RelativeLayout) this.parentView.findViewById(R.id.header_lower_layout_info)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (NavigationHeaderMenu.this.userStatus == 0) {
                    NavigationHeaderMenu.this.mAppState.setSidebarActiveBtn(R.id.LeftMenuLogin);
                    ((BaseActivity) NavigationHeaderMenu.this.mContext).sendGAEvent("LeftNav", "Login", "LeftNav - Login");
                    ((GaanaActivity) NavigationHeaderMenu.this.mContext).closeDrawer();
                } else if (NavigationHeaderMenu.this.userStatus == 1) {
                    ag.a(NavigationHeaderMenu.this.mContext).a("Left Nav", "Gaana Plus");
                    NavigationHeaderMenu.this.mAppState.setSidebarActiveBtn(R.id.upgradeButtonLayout);
                    ((BaseActivity) NavigationHeaderMenu.this.mContext).sendGAEvent("LeftNav", "Upgrade Gaana+", "LeftNav - Upgrade Gaana+");
                    ((GaanaActivity) NavigationHeaderMenu.this.mContext).closeDrawer();
                    an.a().e("click", "ac", "", "LEFT_NV", "", "PYMT_PLAN", "", "");
                } else {
                    NavigationHeaderMenu.this.mAppState.setSidebarActiveBtn(R.id.LeftMenuReferFriend);
                    ((BaseActivity) NavigationHeaderMenu.this.mContext).sendGAEvent("LeftNav", "Share your joy", "LeftNav - Share your joy");
                    ((GaanaActivity) NavigationHeaderMenu.this.mContext).closeDrawer();
                }
            }
        });
        updateLoginBar();
    }

    public void updateLoginBar() {
        UserInfo currentUser = this.mAppState.getCurrentUser();
        TextView textView = (TextView) this.parentView.findViewById(R.id.userSubscription);
        TextView textView2 = (TextView) this.parentView.findViewById(R.id.userSubsMoreinfo);
        TextView textView3 = (TextView) this.parentView.findViewById(R.id.userName);
        final TextView textView4 = (TextView) this.parentView.findViewById(R.id.login_msg);
        if (currentUser == null || !currentUser.getLoginStatus() || currentUser.getUserProfile() == null) {
            textView3.setText(R.string.nav_feature_text_1);
            textView.setText(R.string.login_register_camel);
            textView.setTextColor(Color.parseColor("#fa2200"));
            textView4.setText(R.string.login_msg);
            this.userStatus = 0;
        } else {
            String string;
            ((CircularImageView) this.parentView.findViewById(R.id.imgUser)).setScaleType(ScaleType.CENTER);
            ((CircularImageView) this.parentView.findViewById(R.id.imgUser)).bindImage(currentUser.getUserProfile().getImg());
            textView3.setText(currentUser.getUserProfile().getFullname());
            if (currentUser.getUserSubscriptionData() != null) {
                int accountType = currentUser.getUserSubscriptionData().getAccountType();
                CharSequence string2 = getContext().getString(R.string.FREE_USER);
                this.userStatus = 1;
                if (accountType == 3) {
                    if (ap.a().r()) {
                        string2 = getContext().getString(R.string.NO_ADS_USER);
                    } else if (this.mAppState.getCurrentUser().getUserSubscriptionData().isDeviceLinked()) {
                        string = getContext().getString(R.string.GAANA_PLUS_USER);
                        if (ap.a().f()) {
                            string = getContext().getString(R.string.GAANA_PLUS_MINI_USER);
                        }
                        string2 = string;
                        this.userStatus = 2;
                        this.parentView.findViewById(R.id.header_lower_layout_info).setVisibility(8);
                        this.parentView.findViewById(R.id.header_lower_layout_offline).setVisibility(8);
                    }
                } else if (accountType == 2 && currentUser.getUserSubscriptionData().isDeviceLinked()) {
                    double time = ((double) (this.mAppState.getCurrentUser().getUserSubscriptionData().getExpiryDate().getTime() - new Date().getTime())) / 8.64E7d;
                    string = getContext().getString(R.string.TRIAL_USER);
                    textView2.setVisibility(0);
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(" (");
                        stringBuilder.append((int) time);
                        stringBuilder.append(" Days left)");
                        textView2.setText(stringBuilder.toString());
                        textView2.setTypeface(Typeface.DEFAULT_BOLD);
                    } catch (Exception unused) {
                    }
                    string2 = string;
                } else {
                    textView2.setVisibility(8);
                }
                if (ap.a().m()) {
                    string2 = getContext().getString(R.string.GAANA_PLUS_MINI);
                }
                if (ap.a().l()) {
                    string2 = getContext().getString(R.string.FREEDOM_USER);
                }
                textView.setText(string2);
            } else {
                this.userStatus = 0;
            }
            textView4.setText(R.string.nav_gaana_user_text_new);
            if (this.userStatus == 1 && Util.j(this.mContext)) {
                if (isInternationalUser() || Constants.aA) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_gtrial&source=left_nav&no_downloads=");
                    stringBuilder2.append(DownloadManager.c().B());
                    stringBuilder2.append(DownloadManager.c().K());
                    stringBuilder2.append(ag.a(this.mContext).d());
                    string = stringBuilder2.toString();
                    URLManager uRLManager = new URLManager();
                    uRLManager.a(string);
                    uRLManager.b(1);
                    uRLManager.a(BusinessObjectType.TrialProductFeature);
                    uRLManager.i(false);
                    i.a().a(new af() {
                        public void onErrorResponse(BusinessObject businessObject) {
                        }

                        public void onRetreivalComplete(Object obj) {
                            if (obj instanceof TrialProductFeature) {
                                final TrialProductFeature trialProductFeature = (TrialProductFeature) obj;
                                textView4.setText(trialProductFeature.getSourceMessage());
                                if (TextUtils.isEmpty(trialProductFeature.getCard_identifier())) {
                                    u.a().b("A/B Testing", "Generic");
                                } else {
                                    u.a().b("A/B Testing", trialProductFeature.getCard_identifier());
                                }
                                NavigationHeaderMenu.this.parentView.findViewById(R.id.header_lower_layout_info).setOnClickListener(new OnClickListener() {
                                    public void onClick(View view) {
                                        Util.a(NavigationHeaderMenu.this.mContext, trialProductFeature, null, null);
                                        BaseActivity baseActivity = (BaseActivity) NavigationHeaderMenu.this.mContext;
                                        String str = "LeftNav";
                                        String str2 = "FreeTrial";
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("LeftNav - FreeTrial_");
                                        stringBuilder.append(NavigationHeaderMenu.this.isInternationalUser() ? "International" : "India");
                                        baseActivity.sendGAEvent(str, str2, stringBuilder.toString());
                                        ((GaanaActivity) NavigationHeaderMenu.this.mContext).closeDrawer();
                                    }
                                });
                            }
                        }
                    }, uRLManager);
                } else if (Constants.az) {
                    LoginManager.getInstance().checkTrialAvailability(this.mContext, new s() {
                        public void onErrorResponse(BusinessObject businessObject) {
                        }

                        public void onRetreivalComplete(BusinessObject businessObject) {
                            if (((businessObject != null ? 1 : 0) & (businessObject instanceof BasicResponse)) != 0) {
                                BasicResponse basicResponse = (BasicResponse) businessObject;
                                if (basicResponse.getResult() != null && basicResponse.getResult().equalsIgnoreCase("Yes")) {
                                    textView4.setText(R.string.indian_user_trial_mssg);
                                    NavigationHeaderMenu.this.parentView.findViewById(R.id.header_lower_layout_info).setOnClickListener(new OnClickListener() {
                                        public void onClick(View view) {
                                            NavigationHeaderMenu.this.mAppState.setSidebarActiveBtn(R.id.LeftMenuPurchase);
                                            ((BaseActivity) NavigationHeaderMenu.this.mContext).sendGAEvent("LeftNav", "FreeTrial", "LeftNav - FreeTrial_India");
                                            ((GaanaActivity) NavigationHeaderMenu.this.mContext).closeDrawer();
                                            an.a().e("click", "ac", "", "LEFT_NV", "", "PYMT_PLAN", "", "");
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        }
        boolean b = this.mDeviceResManager.b("PREFERENCE_KEY_OFFLINE_MODE", false, false);
        this.switchOfflineMode = (SwitchCompat) this.parentView.findViewById(R.id.switchButton);
        this.parentView.findViewById(R.id.head_text).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NavigationHeaderMenu.this.switchOfflineMode.setChecked(NavigationHeaderMenu.this.switchOfflineMode.isChecked() ^ 1);
            }
        });
        this.switchOfflineMode.setChecked(b);
        refreshOfflineModeExpiryText(this.tvOfflineModeExpiryDays);
        this.switchOfflineMode.setOnCheckedChangeListener(this.offlineSwitchListener);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isInternationalUser() {
        try {
            return !y.a().b().equalsIgnoreCase("IN");
        } catch (Exception unused) {
            return false;
        }
    }
}
