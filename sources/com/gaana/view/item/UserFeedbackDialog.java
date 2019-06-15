package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.User.LoginType;
import com.helpshift.a;
import com.helpshift.support.h;
import com.helpshift.support.i;
import com.helpshift.support.l;
import com.managers.ap;
import com.moengage.inapp.InAppMessage;
import com.moengage.inapp.InAppTracker;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class UserFeedbackDialog extends Dialog {
    public UserFeedbackDialog(Context context, InAppMessage inAppMessage, int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        final Context context2 = context;
        final InAppMessage inAppMessage2 = inAppMessage;
        int i2 = i;
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        super(context);
        requestWindowFeature(1);
        setContentView(R.layout.popup_user_feedback);
        ConcurrentHashMap concurrentHashMap = GaanaApplication.getInstance().inAppShownList;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_feedback");
        stringBuilder.append(i2);
        concurrentHashMap.put(stringBuilder.toString(), Long.valueOf(System.currentTimeMillis()));
        TextView textView = (TextView) findViewById(R.id.txt_subtitle);
        TextView textView2 = (TextView) findViewById(R.id.txt_question);
        Button button = (Button) findViewById(R.id.btn_ok);
        Button button2 = (Button) findViewById(R.id.btn_not_ok);
        ((ImageView) findViewById(R.id.img_header)).setImageDrawable(context.getResources().getDrawable(new Integer[]{Integer.valueOf(R.drawable.heart_popup), Integer.valueOf(R.drawable.star_popup), Integer.valueOf(R.drawable.feedback_popup), Integer.valueOf(R.drawable.trophy_popup)}[i2].intValue()));
        if (str5 == null) {
            textView2.setText(context.getResources().getStringArray(R.array.mo_popup_question)[i2]);
        } else {
            textView2.setText(str5);
        }
        if (str6 == null) {
            button.setText(context.getResources().getStringArray(R.array.mo_popup_ok)[i2]);
        } else {
            button.setText(str6);
        }
        if (str7 == null) {
            button2.setText(context.getResources().getStringArray(R.array.mo_popup_not_ok)[i2]);
        } else {
            button2.setText(str7);
        }
        if (str8 != null) {
            textView.setText(str8);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        switch (i2) {
            case 0:
            case 3:
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        MoEngage.getInstance().reportEnjoyingGaanaAction("Yes");
                        if (inAppMessage2 != null) {
                            InAppTracker.getInstance(context2).trackInAppClicked(inAppMessage2);
                        }
                        UserFeedbackDialog.this.dismiss();
                        new UserFeedbackDialog(context2, null, 1, null, null, null, null).show();
                    }
                });
                button2.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        MoEngage.getInstance().reportEnjoyingGaanaAction("No");
                        if (inAppMessage2 != null) {
                            InAppTracker.getInstance(context2).trackInAppClicked(inAppMessage2);
                        }
                        UserFeedbackDialog.this.dismiss();
                        new UserFeedbackDialog(context2, null, 2, null, null, null, null).show();
                    }
                });
                break;
            case 1:
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        UserFeedbackDialog.this.dismiss();
                        MoEngage.getInstance().reportWhetherAppRated(true);
                        MoEngage.getInstance().reportRateUsAction("Rated");
                        if (inAppMessage2 != null) {
                            InAppTracker.getInstance(context2).trackInAppClicked(inAppMessage2);
                        }
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("market://details?id=com.gaana"));
                        context2.startActivity(intent);
                    }
                });
                button2.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        MoEngage.getInstance().reportRateUsAction("NoThanks");
                        if (inAppMessage2 != null) {
                            InAppTracker.getInstance(context2).trackInAppClicked(inAppMessage2);
                        }
                        UserFeedbackDialog.this.dismiss();
                    }
                });
                break;
            case 2:
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        UserFeedbackDialog.this.dismiss();
                        MoEngage.getInstance().reportFeedbackAction("FeedbackGiven");
                        if (inAppMessage2 != null) {
                            InAppTracker.getInstance(context2).trackInAppClicked(inAppMessage2);
                        }
                        UserFeedbackDialog.this.startHelpShiftActivity(context2, GaanaApplication.getInstance());
                    }
                });
                button2.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        MoEngage.getInstance().reportFeedbackAction("NoThanks");
                        if (inAppMessage2 != null) {
                            InAppTracker.getInstance(context2).trackInAppClicked(inAppMessage2);
                        }
                        UserFeedbackDialog.this.dismiss();
                    }
                });
                break;
            default:
                dismiss();
                break;
        }
        setCanceledOnTouchOutside(false);
    }

    private void startHelpShiftActivity(Context context, GaanaApplication gaanaApplication) {
        if (!gaanaApplication.isAppInOfflineMode() && Util.j(context)) {
            setUserDetailsToHelpShift(gaanaApplication, context);
            l.a((GaanaActivity) context);
        }
    }

    public void setUserDetailsToHelpShift(GaanaApplication gaanaApplication, Context context) {
        UserInfo currentUser = gaanaApplication.getCurrentUser();
        ArrayList arrayList = new ArrayList();
        if (!(currentUser == null || !currentUser.getLoginStatus() || currentUser.getUserProfile() == null)) {
            String fullname = currentUser.getUserProfile().getFullname();
            String email = currentUser.getUserProfile().getEmail();
            a.a(fullname, email);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("user-id-");
            stringBuilder.append(currentUser.getUserProfile().getUserId());
            l.a(stringBuilder.toString());
            arrayList.add(fullname);
            arrayList.add(email);
            if (currentUser.getLoginType() == LoginType.PHONENUMBER) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("PHONE:");
                stringBuilder2.append(currentUser.getUserProfile().getPhoneNumber());
                arrayList.add(stringBuilder2.toString());
            }
            if (gaanaApplication.getCurrentUser().getUserSubscriptionData() != null) {
                arrayList.add(gaanaApplication.getCurrentUser().getUserSubscriptionData().getServerAccountType());
                if (ap.a().r()) {
                    arrayList.add("no_ads");
                }
            }
        }
        HashMap hashMap = new HashMap();
        if (arrayList.size() == 0) {
            arrayList = new ArrayList();
            arrayList.add("logged_out");
        }
        arrayList.add("userfeedbackinapp");
        hashMap.put("hs-tags", arrayList.toArray(new String[arrayList.size()]));
        final h hVar = new h(hashMap);
        l.a(new i() {
            public h call() {
                return hVar;
            }
        });
    }
}
