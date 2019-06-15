package com.moengage.core;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.widget.Toast;
import com.delight.pushlibrary.R;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.ActionMapperConstants;
import com.moengage.push.PushManager;
import org.json.JSONException;
import org.json.JSONObject;

public class ActionManagerBase {
    /* Access modifiers changed, original: protected */
    public void handleActionCopy(Activity activity, JSONObject jSONObject) throws JSONException {
        Logger.v("ActionManagerBase$handleActionCopy");
        String str = "";
        if (jSONObject.has("msg")) {
            str = jSONObject.getString("msg");
        }
        if (jSONObject.has("value")) {
            MoEHelperUtils.copyCouponCodeToClipboard(activity, jSONObject.getString("value"), str);
        }
    }

    /* Access modifiers changed, original: protected */
    public void handleActionCall(Activity activity, JSONObject jSONObject) throws JSONException {
        Logger.v("ActionManagerBase$handleActionCall");
        if (jSONObject.has("value")) {
            String string = jSONObject.getString("value");
            if (TextUtils.isEmpty(string) || !isPhoneNumberValid(string)) {
                Toast.makeText(activity, R.string.invalid_number, 1).show();
                return;
            }
            Intent intent = new Intent("android.intent.action.DIAL");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("tel:");
            stringBuilder.append(Uri.encode(string));
            intent.setData(Uri.parse(stringBuilder.toString()));
            activity.startActivity(intent);
        }
    }

    /* Access modifiers changed, original: protected */
    public void handleActionShare(Activity activity, JSONObject jSONObject) throws JSONException {
        Logger.v("ActionManagerBase$handleActionShare");
        Intent intent = new Intent("android.intent.action.SEND");
        if (jSONObject.has("content")) {
            String string = jSONObject.getString("content");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", string);
            activity.startActivity(Intent.createChooser(intent, "Share via"));
        }
    }

    /* Access modifiers changed, original: protected */
    public void handleActionNavigation(Activity activity, JSONObject jSONObject) throws JSONException, ClassNotFoundException {
        Logger.v("ActionManagerBase$handleActionNavigation");
        if (jSONObject.has(ActionMapperConstants.KEY_SCREEN)) {
            String string = jSONObject.getString(ActionMapperConstants.KEY_SCREEN);
            JSONObject jSONObject2 = null;
            if (jSONObject.has("extras")) {
                jSONObject2 = jSONObject.getJSONObject("extras");
            }
            Intent intent = new Intent(activity, Class.forName(string));
            if (jSONObject2 != null) {
                intent.putExtras(MoEHelperUtils.convertJSONObjecttoBundle(jSONObject2));
            }
            if (PushManager.getInstance().isBackStackBuilderOptedOut(activity)) {
                activity.startActivity(intent);
            } else {
                TaskStackBuilder.create(activity).addNextIntentWithParentStack(intent).startActivities();
            }
        } else if (jSONObject.has("uri")) {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(jSONObject.getString("uri")).buildUpon().build()));
        }
    }

    private boolean isPhoneNumberValid(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!PhoneNumberUtils.isDialable(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
