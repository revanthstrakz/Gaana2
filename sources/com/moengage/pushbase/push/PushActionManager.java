package com.moengage.pushbase.push;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.PayloadBuilder;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.ActionMapperConstants;
import com.moengage.core.ActionManagerBase;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import com.moengage.push.PushManager;
import com.moengage.pushbase.PushActionMapperConstants;
import com.moengage.pushbase.fragments.LaterDialogFragment;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

public class PushActionManager extends ActionManagerBase {
    private static PushActionManager _INSTANCE = null;
    public static boolean dialogShown = false;
    private IActionHandler actionHandlers;
    Context context;

    public interface IActionHandler {
        boolean onActionPerformed(Context context, String str, JSONObject jSONObject);
    }

    public static PushActionManager getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new PushActionManager();
        }
        return _INSTANCE;
    }

    public void registerActionHandler(IActionHandler iActionHandler) {
        this.actionHandlers = iActionHandler;
    }

    private PushActionManager() {
    }

    public boolean onActionPerformed(Activity activity, String str, JSONObject jSONObject) {
        this.context = activity.getApplicationContext();
        boolean z = true;
        try {
            switch (str.hashCode()) {
                case -1588643369:
                    if (str.equals(PushActionMapperConstants.ACTION_REMIND_EXACT)) {
                        z = true;
                        break;
                    }
                    break;
                case -1083609360:
                    if (str.equals(ActionMapperConstants.ACTION_CALL)) {
                        z = true;
                        break;
                    }
                    break;
                case -1083595769:
                    if (str.equals(ActionMapperConstants.ACTION_COPY)) {
                        z = true;
                        break;
                    }
                    break;
                case 103602769:
                    if (str.equals(ActionMapperConstants.ACTION_NAVIGATE)) {
                        z = false;
                        break;
                    }
                    break;
                case 103607696:
                    if (str.equals(ActionMapperConstants.ACTION_SET_ATTRIBUTE)) {
                        z = true;
                        break;
                    }
                    break;
                case 773766282:
                    if (str.equals(ActionMapperConstants.ACTION_OPEN_IN_APP)) {
                        z = true;
                        break;
                    }
                    break;
                case 782822797:
                    if (str.equals(ActionMapperConstants.ACTION_SHARE)) {
                        z = true;
                        break;
                    }
                    break;
                case 784043769:
                    if (str.equals(ActionMapperConstants.ACTION_TRACK_ATTR)) {
                        z = true;
                        break;
                    }
                    break;
                case 1295140306:
                    if (str.equals(PushActionMapperConstants.ACTION_REMIND_INEXACT)) {
                        z = true;
                        break;
                    }
                    break;
                default:
                    break;
            }
            switch (z) {
                case false:
                    handleActionNavigation(activity, jSONObject);
                    break;
                case true:
                    handleActionTrackEvent(activity, jSONObject);
                    break;
                case true:
                    handleActionShare(activity, jSONObject);
                    break;
                case true:
                    handleActionCall(activity, jSONObject);
                    break;
                case true:
                    handleActionCopy(activity, jSONObject);
                    break;
                case true:
                    handleActionSetAttribute(activity, jSONObject);
                    break;
                case true:
                    handleActionOpenInApp(activity, jSONObject);
                    break;
                case true:
                    handleActionRemindExact(activity, jSONObject);
                    break;
                case true:
                    handleActionRemindInExact(activity, jSONObject);
                    break;
                default:
                    if (this.actionHandlers != null) {
                        this.actionHandlers.onActionPerformed(activity, str, jSONObject);
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Not a valid action");
                        stringBuilder.append(str);
                        Logger.e(stringBuilder.toString());
                    }
                    return false;
            }
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("PushActionManager : exception occurred while performing notification action,");
            stringBuilder2.append(e.getMessage());
            Logger.f(stringBuilder2.toString());
        }
        return true;
    }

    private void handleActionOpenInApp(Activity activity, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(InAppConstants.LINKED_IN_APP)) {
            String string = jSONObject.getString(InAppConstants.LINKED_IN_APP);
            if (!TextUtils.isEmpty(string)) {
                MoEDispatcher.getInstance(activity).checkAndShowLinkedInApp(string);
            }
        }
    }

    private void handleActionSetAttribute(Context context, JSONObject jSONObject) {
        try {
            String string = jSONObject.has(ActionMapperConstants.KEY_SET) ? jSONObject.getString(ActionMapperConstants.KEY_SET) : null;
            if (jSONObject.has(ActionMapperConstants.KEY_VALUE_TYPE) && jSONObject.has("value") && !TextUtils.isEmpty(string)) {
                String string2 = jSONObject.getString(ActionMapperConstants.KEY_VALUE_TYPE);
                Object obj = -1;
                switch (string2.hashCode()) {
                    case -1808118735:
                        if (string2.equals("String")) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -1325958191:
                        if (string2.equals("double")) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 104431:
                        if (string2.equals("int")) {
                            obj = null;
                            break;
                        }
                        break;
                    case 3327612:
                        if (string2.equals("long")) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 64711720:
                        if (string2.equals("boolean")) {
                            obj = 3;
                            break;
                        }
                        break;
                    default:
                        break;
                }
                switch (obj) {
                    case null:
                        MoEHelper.getInstance(context).setUserAttribute(string, jSONObject.getInt("value"));
                        return;
                    case 1:
                        MoEHelper.getInstance(context).setUserAttribute(string, jSONObject.getDouble("value"));
                        return;
                    case 2:
                        MoEHelper.getInstance(context).setUserAttribute(string, jSONObject.getString("value"));
                        return;
                    case 3:
                        MoEHelper.getInstance(context).setUserAttribute(string, jSONObject.getBoolean("value"));
                        return;
                    case 4:
                        MoEHelper.getInstance(context).setUserAttribute(string, jSONObject.getLong("value"));
                        return;
                    default:
                        return;
                }
            } else if (jSONObject.has("value") && !TextUtils.isEmpty(string)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(string.trim(), jSONObject.getString("value"));
                MoEDispatcher.getInstance(context).setCustomUserAttribute(jSONObject2);
            }
        } catch (Exception e) {
            Logger.f("PushActionManager : handleActionSetAttribute ", e);
        }
    }

    private void handleActionTrackEvent(Context context, JSONObject jSONObject) {
        Logger.v("PushActionManager$handleActionTrackEvent(): action_track");
        if (jSONObject.has(ActionMapperConstants.KEY_TRACK)) {
            CharSequence charSequence = null;
            try {
                String string = jSONObject.getString(ActionMapperConstants.KEY_TRACK);
                PayloadBuilder payloadBuilder = new PayloadBuilder();
                if (jSONObject.has(ActionMapperConstants.KEY_VALUE_OF)) {
                    charSequence = jSONObject.getString(ActionMapperConstants.KEY_VALUE_OF);
                }
                if (!TextUtils.isEmpty(charSequence)) {
                    payloadBuilder.putAttrString(ActionMapperConstants.KEY_VALUE_OF, charSequence);
                }
                MoEHelper.getInstance(context).trackEvent(string, payloadBuilder.build());
            } catch (Exception e) {
                Logger.f("PushActionManger : handleActionTrackEvent()", e);
            }
        }
    }

    private void handleActionRemindExact(Activity activity, JSONObject jSONObject) throws Exception {
        Logger.v("PushActionManager$handleActionRemindExact()");
        Intent intent = activity.getIntent();
        if (intent == null) {
            Logger.v("PushActionManager$handleActionRemindExact() Intent is null");
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Logger.v("PushActionManager$handleActionRemindExact() Extras is null");
            return;
        }
        extras.remove(PushActionMapperConstants.KEY_ACTION_TAG);
        extras.remove(PushActionMapperConstants.KEY_ACTION_PAYLOAD);
        extras.putBoolean(PushActionMapperConstants.KEY_RENOTIFY, true);
        Intent intent2 = new Intent(activity, MoEPushWorker.class);
        intent2.putExtras(extras);
        intent2.setAction(MoEPushWorker.EXTRA_SERVICE_NOTIFY);
        PendingIntent service = PendingIntent.getService(activity, (int) System.currentTimeMillis(), intent2, 134217728);
        int snoozeVal = getSnoozeVal(jSONObject, "value");
        if (snoozeVal > 0) {
            Calendar instance = Calendar.getInstance();
            instance.add(11, snoozeVal);
            ((AlarmManager) activity.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, instance.getTimeInMillis(), service);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("PushActionManager$handleActionRemindExact(): Reminder set at :");
            stringBuilder.append(instance.getTime());
            Logger.v(stringBuilder.toString());
        }
    }

    private void handleActionRemindInExact(Activity activity, JSONObject jSONObject) throws Exception {
        Logger.v("PushActionManager$handleActionRemindInExact() : action_remind_inexact ");
        Intent intent = activity.getIntent();
        if (intent == null) {
            Logger.v("PushActionManager$handleActionRemindInExact() Intent is null");
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Logger.v("PushActionManager$handleActionRemindInExact() Extras is null");
            return;
        }
        dialogShown = true;
        extras.remove(PushActionMapperConstants.KEY_ACTION_TAG);
        extras.remove(PushActionMapperConstants.KEY_ACTION_PAYLOAD);
        int snoozeVal = getSnoozeVal(jSONObject, PushActionMapperConstants.ACTION_SNOOZE_TIME);
        int snoozeVal2 = getSnoozeVal(jSONObject, PushActionMapperConstants.ACTION_SNOOZE_TODAY);
        int snoozeVal3 = getSnoozeVal(jSONObject, PushActionMapperConstants.ACTION_SNOOZE_TOMORROW);
        if (Calendar.getInstance().get(11) + snoozeVal2 >= 25) {
            snoozeVal2 = -1;
        }
        extras.putInt(PushActionMapperConstants.ACTION_SNOOZE_TIME, snoozeVal);
        extras.putInt(PushActionMapperConstants.ACTION_SNOOZE_TODAY, snoozeVal2);
        extras.putInt(PushActionMapperConstants.ACTION_SNOOZE_TOMORROW, snoozeVal3);
        LaterDialogFragment laterDialogFragment = new LaterDialogFragment();
        laterDialogFragment.setArguments(extras);
        laterDialogFragment.show(((FragmentActivity) activity).getSupportFragmentManager(), "laterDialog");
    }

    private int getSnoozeVal(JSONObject jSONObject, String str) throws Exception {
        return jSONObject.has(str) ? Integer.parseInt(jSONObject.getString(str).trim()) : -1;
    }

    public static boolean isDialogShown() {
        return dialogShown;
    }

    /* Access modifiers changed, original: protected */
    public void handleActionNavigation(Activity activity, JSONObject jSONObject) throws JSONException, ClassNotFoundException {
        Bundle convertJSONObjecttoBundle;
        Uri uri;
        Logger.v("PushActionManager$handleActionNavigation");
        String str = null;
        if (jSONObject.has(ActionMapperConstants.KEY_SCREEN)) {
            String string = jSONObject.getString(ActionMapperConstants.KEY_SCREEN);
            convertJSONObjecttoBundle = MoEHelperUtils.convertJSONObjecttoBundle(jSONObject.has("extras") ? jSONObject.getJSONObject("extras") : null);
            str = string;
            uri = null;
        } else if (jSONObject.has("uri")) {
            uri = Uri.parse(jSONObject.getString("uri")).buildUpon().build();
            convertJSONObjecttoBundle = null;
        } else {
            convertJSONObjecttoBundle = null;
            uri = convertJSONObjecttoBundle;
        }
        if (!MoEPushCallBacks.getInstance().onPushNavigationAction(str, convertJSONObjecttoBundle, uri)) {
            if (!TextUtils.isEmpty(str)) {
                Intent intent = new Intent(activity, Class.forName(str));
                if (convertJSONObjecttoBundle != null) {
                    intent.putExtras(convertJSONObjecttoBundle);
                }
                if (PushManager.getInstance().isBackStackBuilderOptedOut(activity)) {
                    activity.startActivity(intent);
                } else {
                    TaskStackBuilder.create(activity).addNextIntentWithParentStack(intent).startActivities();
                }
            } else if (uri != null) {
                activity.startActivity(new Intent("android.intent.action.VIEW", uri));
            }
        }
    }
}
