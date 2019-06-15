package com.moengage.inapp;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RatingBar;
import com.facebook.internal.NativeProtocol;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.PayloadBuilder;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.ActionMapperConstants;
import com.moengage.core.ActionManagerBase;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppActionManager extends ActionManagerBase {
    private static InAppActionManager _INSTANCE;
    private IActionHandler actionHandlers;
    Context context;

    public interface IActionHandler {
        boolean onActionPerformed(Context context, String str, JSONObject jSONObject, View view, InAppMessage inAppMessage);
    }

    public static InAppActionManager getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new InAppActionManager();
        }
        return _INSTANCE;
    }

    public void registerActionHandler(IActionHandler iActionHandler) {
        this.actionHandlers = iActionHandler;
    }

    public boolean onActionPerformed(Activity activity, String str, JSONObject jSONObject, View view, InAppMessage inAppMessage) {
        this.context = activity.getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Inside InAppActionManager#onActionPerformed, Action : ");
        stringBuilder.append(str);
        Logger.v(stringBuilder.toString());
        boolean z = true;
        try {
            switch (str.hashCode()) {
                case -1083609360:
                    if (str.equals(ActionMapperConstants.ACTION_CALL)) {
                        z = true;
                        break;
                    }
                    break;
                case -1083595852:
                    if (str.equals(ActionMapperConstants.ACTION_CONDITIONAL)) {
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
                        z = true;
                        break;
                    }
                    break;
                case 103607696:
                    if (str.equals(ActionMapperConstants.ACTION_SET_ATTRIBUTE)) {
                        z = true;
                        break;
                    }
                    break;
                case 290888888:
                    if (str.equals(ActionMapperConstants.ACTION_DISMISS)) {
                        z = false;
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
                default:
                    break;
            }
            switch (z) {
                case false:
                    handleActionDismiss(view, inAppMessage);
                    break;
                case true:
                    handleActionNavigation(activity, jSONObject);
                    break;
                case true:
                    handleActionTrackEvent(activity, jSONObject, inAppMessage);
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
                    handleActionSetAttribute(activity, jSONObject, view, inAppMessage);
                    break;
                case true:
                    handleActionLinkedInApp(activity, jSONObject);
                    break;
                case true:
                    handleActionCondition(activity, jSONObject, view, inAppMessage);
                    break;
                default:
                    if (this.actionHandlers != null) {
                        this.actionHandlers.onActionPerformed(activity, str, jSONObject, view, inAppMessage);
                    } else {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Not a valid action ");
                        stringBuilder2.append(str);
                        Logger.f(stringBuilder2.toString());
                    }
                    return false;
            }
        } catch (Exception e) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("ActionManager : exception occurred while performing notification/in-app action,");
            stringBuilder3.append(e.getMessage());
            Logger.f(stringBuilder3.toString());
        }
        return true;
    }

    private void handleActionLinkedInApp(Activity activity, JSONObject jSONObject) throws JSONException {
        Logger.v("InAppActionManager#handleActionLinkedInApp");
        if (jSONObject.has(InAppConstants.LINKED_IN_APP)) {
            String string = jSONObject.getString(InAppConstants.LINKED_IN_APP);
            if (!TextUtils.isEmpty(string)) {
                InAppMessage checkAndReturnInApp = InAppManager.getInstance().checkAndReturnInApp(activity, string);
                if (checkAndReturnInApp == null) {
                    InAppController.getInstance().getInAppHandler().fetchLinkedInApp(activity.getApplicationContext(), string);
                } else {
                    new InAppHandlerImpl().buildAndShowInApp(activity.getApplicationContext(), checkAndReturnInApp);
                }
            }
        }
    }

    private void handleActionSetAttribute(Activity activity, JSONObject jSONObject, View view, InAppMessage inAppMessage) throws JSONException {
        Logger.v("InAppActionManager#handleActionSetAttribute");
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
            }
            switch (obj) {
                case null:
                    MoEHelper.getInstance(activity).setUserAttribute(string, jSONObject.getInt("value"));
                    break;
                case 1:
                    MoEHelper.getInstance(activity).setUserAttribute(string, jSONObject.getDouble("value"));
                    break;
                case 2:
                    MoEHelper.getInstance(activity).setUserAttribute(string, jSONObject.getString("value"));
                    break;
                case 3:
                    MoEHelper.getInstance(activity).setUserAttribute(string, jSONObject.getBoolean("value"));
                    break;
                case 4:
                    MoEHelper.getInstance(activity).setUserAttribute(string, jSONObject.getLong("value"));
                    break;
            }
        } else if (jSONObject.has("value") && !TextUtils.isEmpty(string)) {
            MoEHelper.getInstance(activity).setUserAttribute(string, jSONObject.getString("value"));
        }
        if (jSONObject.has(ActionMapperConstants.KEY_VALUE_OF)) {
            View findViewById = inAppMessage.theComposedView.findViewById(2000 + jSONObject.getInt(ActionMapperConstants.KEY_VALUE_OF));
            if (findViewById == null) {
                return;
            }
            JSONObject jSONObject2;
            if (findViewById instanceof RatingBar) {
                int rating = (int) ((RatingBar) findViewById).getRating();
                if (!TextUtils.isEmpty(string)) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put(string.trim(), rating);
                    MoEDispatcher.getInstance(activity).setCustomUserAttribute(jSONObject2);
                }
            } else if (findViewById instanceof EditText) {
                String obj2 = ((EditText) findViewById).getText().toString();
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(obj2)) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put(string.trim(), obj2);
                    MoEDispatcher.getInstance(activity).setCustomUserAttribute(jSONObject2);
                }
            }
        }
    }

    private void handleActionTrackEvent(Activity activity, JSONObject jSONObject, InAppMessage inAppMessage) throws JSONException {
        Logger.v("InAppActionManager#handleActionTrackEvent");
        if (jSONObject.has(ActionMapperConstants.KEY_TRACK)) {
            String str = "";
            String string = jSONObject.getString(ActionMapperConstants.KEY_TRACK);
            if (jSONObject.has("campaign_id")) {
                str = jSONObject.getString("campaign_id");
            } else if (inAppMessage != null) {
                str = inAppMessage.rules.campaignId;
            }
            PayloadBuilder payloadBuilder = new PayloadBuilder();
            payloadBuilder.putAttrString(MoEHelperConstants.GCM_EXTRA_CAMPAIGN_ID, str);
            if (string.equals(InAppConstants.EVENT_IN_APP_CLICKED)) {
                payloadBuilder.putAttrInt("widget_id", jSONObject.getInt("id"));
            }
            if (string.equals(InAppConstants.APP_RATED_EVENT) && jSONObject.has(ActionMapperConstants.KEY_VALUE_OF)) {
                payloadBuilder.putAttrInt("rating", getRatingValueForWidget(jSONObject.getInt(ActionMapperConstants.KEY_VALUE_OF), inAppMessage));
            }
            MoEHelper.getInstance(activity).trackEvent(string, payloadBuilder.build());
        }
    }

    private void handleActionDismiss(View view, InAppMessage inAppMessage) {
        Logger.v("InAppActionManager$handleActionDismiss");
        if (inAppMessage != null) {
            View findViewById = view.getRootView().findViewById(10001);
            if (findViewById != null) {
                seAnimation(findViewById, inAppMessage);
                ((ViewGroup) findViewById.getParent()).removeView(findViewById);
            } else {
                view = view.getRootView().findViewById(ViewEngine.INAPP_WRAPPER_ID);
                if (view != null) {
                    seAnimation(view, inAppMessage);
                    ((ViewGroup) view.getParent()).removeView(view);
                }
            }
        }
        InAppManager.getInstance().handleDismiss();
    }

    private void seAnimation(View view, InAppMessage inAppMessage) {
        if (inAppMessage.rules.exitAnimation != 0) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.context, inAppMessage.rules.exitAnimation);
            loadAnimation.setFillAfter(true);
            view.setAnimation(loadAnimation);
        }
    }

    private void handleActionCondition(Activity activity, JSONObject jSONObject, View view, InAppMessage inAppMessage) throws JSONException {
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2.has("widget_id")) {
            InAppMessage inAppMessage2 = inAppMessage;
            int ratingValueForWidget = getRatingValueForWidget(jSONObject2.getInt("widget_id"), inAppMessage2);
            if (ratingValueForWidget != -1 && jSONObject2.has(ActionMapperConstants.KEY_CONDITIONS)) {
                JSONArray jSONArray = jSONObject2.getJSONArray(ActionMapperConstants.KEY_CONDITIONS);
                for (int i = 0; i < jSONArray.length(); i++) {
                    jSONObject2 = jSONArray.getJSONObject(i);
                    if (shouldExecuteAction(ratingValueForWidget, jSONObject2)) {
                        JSONArray jSONArray2 = jSONObject2.getJSONArray(NativeProtocol.WEB_DIALOG_ACTION);
                        int i2 = 0;
                        while (i2 < jSONArray2.length()) {
                            JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                            String str = null;
                            if (jSONObject3.has("tag")) {
                                str = jSONObject3.getString("tag");
                            }
                            String str2 = str;
                            if (str2 != null && str2.equals(ActionMapperConstants.ACTION_CONDITIONAL)) {
                                Logger.e("InAppActionManager:handleActionCondition() cannot have nested conditional");
                                break;
                            } else {
                                onActionPerformed(activity, str2, jSONObject3, view, inAppMessage2);
                                i2++;
                            }
                        }
                    }
                }
            }
        }
    }

    private int getRatingValueForWidget(int i, InAppMessage inAppMessage) {
        View view = inAppMessage.theComposedView;
        if (view != null) {
            View findViewById = view.findViewById(2000 + i);
            if (findViewById != null && (findViewById instanceof RatingBar)) {
                return (int) ((RatingBar) findViewById).getRating();
            }
        }
        return 0;
    }

    private boolean shouldExecuteAction(int i, JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("type");
        boolean z = true;
        int i2 = jSONObject.has("value") ? jSONObject.getInt("value") : -1;
        int hashCode = string.hashCode();
        boolean z2 = true;
        if (hashCode != -1184391093) {
            if (hashCode != 3244) {
                if (hashCode != 102044) {
                    if (hashCode == 106849 && string.equals(ActionMapperConstants.CONDITION_TYPE_LESS_THAN)) {
                        z = true;
                    }
                } else if (string.equals(ActionMapperConstants.CONDITION_TYPE_GREATER_THAN)) {
                    z = true;
                }
            } else if (string.equals(ActionMapperConstants.CONDITION_TYPE_EQUAL)) {
                z = false;
            }
        } else if (string.equals(ActionMapperConstants.CONDITION_TYPE_BETWEEN)) {
            z = true;
        }
        switch (z) {
            case false:
                if (i != i2) {
                    z2 = false;
                }
                return z2;
            case true:
                int i3 = jSONObject.getInt(ActionMapperConstants.CONDITION_UPPER_BOUND);
                if (i <= jSONObject.getInt(ActionMapperConstants.CONDITION_LOWER_BOUND) || i >= i3) {
                    z2 = false;
                }
                return z2;
            case true:
                if (i <= i2) {
                    z2 = false;
                }
                return z2;
            case true:
                if (i >= i2) {
                    z2 = false;
                }
                return z2;
            default:
                Logger.e("Not a valid condition");
                return false;
        }
    }
}
