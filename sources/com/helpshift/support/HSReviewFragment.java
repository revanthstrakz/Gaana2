package com.helpshift.support;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.e.k;
import com.helpshift.support.activities.ParentActivity;
import com.helpshift.support.h.e;
import com.helpshift.support.util.AppSessionConstants.Screen;
import com.helpshift.util.a;
import com.helpshift.util.o;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashMap;
import java.util.Map;

public final class HSReviewFragment extends DialogFragment {
    private static a b;
    String a = "";
    private final String c = "Helpshift_ReviewFrag";
    private d d;
    private boolean e = true;

    public Dialog onCreateDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            this.e = extras.getBoolean("disableReview", true);
            this.a = extras.getString("rurl");
        }
        this.d = new d(activity);
        return a(activity);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str.trim()));
            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                getContext().startActivity(intent);
            }
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        b("later");
        a(2);
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.e) {
            o.d().m().a(true);
        }
        getActivity().finish();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i) {
        if (b != null) {
            b.a(i);
        }
        b = null;
    }

    private Dialog a(FragmentActivity fragmentActivity) {
        Builder builder = new Builder(fragmentActivity);
        builder.setMessage(k.hs__review_message);
        Dialog create = builder.create();
        create.setTitle(k.hs__review_title);
        create.setCanceledOnTouchOutside(false);
        create.setButton(-1, getResources().getString(k.hs__rate_button), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(HSReviewFragment.this.a)) {
                    HSReviewFragment.this.a = o.d().m().c("reviewUrl");
                }
                HSReviewFragment.this.a = HSReviewFragment.this.a.trim();
                if (!TextUtils.isEmpty(HSReviewFragment.this.a)) {
                    HSReviewFragment.this.a(HSReviewFragment.this.a);
                }
                HSReviewFragment.this.b("reviewed");
                HSReviewFragment.this.a(0);
            }
        });
        create.setButton(-3, getResources().getString(k.hs__feedback_button), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                HSReviewFragment.this.b("feedback");
                HSReviewFragment.this.a(1);
                Screen screen = (Screen) e.b().a("current_open_screen");
                if (screen != Screen.NEW_CONVERSATION && screen != Screen.CONVERSATION && screen != Screen.CONVERSATION_INFO && screen != Screen.SCREENSHOT_PREVIEW) {
                    Intent intent = new Intent(HSReviewFragment.this.getContext(), ParentActivity.class);
                    intent.putExtra("support_mode", 1);
                    intent.putExtra("decomp", true);
                    intent.putExtra("showInFullScreen", a.a(HSReviewFragment.this.getActivity()));
                    intent.putExtra("chatLaunchSource", "support");
                    intent.putExtra("isRoot", true);
                    intent.putExtra("search_performed", true);
                    HSReviewFragment.this.getActivity().startActivity(intent);
                }
            }
        });
        create.setButton(-2, getResources().getString(k.hs__review_close_button), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                HSReviewFragment.this.b("later");
                HSReviewFragment.this.a(2);
            }
        });
        com.helpshift.views.a.a(create);
        return create;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("type", "periodic");
        hashMap.put(CBConstant.RESPONSE, str);
        o.d().f().a(AnalyticsEventType.REVIEWED_APP, hashMap);
    }
}
