package com.moengage.pushbase.push;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.MoEEventManager;
import com.moengage.push.PushManager;
import com.moengage.push.PushManager.PushHandler;
import com.moengage.pushbase.PushActionMapperConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class MoEPushWorker extends IntentService {
    public static final String EXTRA_SERVICE_CAROUSEL = "DEAL_WITH_CAROUSEL";
    public static final String EXTRA_SERVICE_NOTIFY = "DEAL_WITH_NOTIFICATION";
    public static final String NOTIFICATION_CLEARED = "DEAL_WITH_NOTIFICATION_CLEARED";
    public static final String NOTIFICATION_DISMISS = "DEAL_WITH_NOTI_AUTODISMISS";
    public static final String PUSH_REG_FALLBACK = "PUSH_REG_FALLBACK";
    public static final String SHOW_NOTIFICATION = "SHOW_NOTIFICATION";
    public static final String TRACK_NOTIFICATION_RECEIVED = "DEAL_WITH_NOTIFI_TRACKING";
    private final int NAVIGATION_DIRECTION_LEFT = -1;
    private final int NAVIGATION_DIRECTION_RIGHT = 1;

    public MoEPushWorker() {
        super("RegistrationIntentService");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                if (!TextUtils.isEmpty(intent.getAction())) {
                    MoEHelperUtils.dumpIntentExtras(intent);
                    Logger.v("MoEPushWorker#onHandleIntent");
                    String action = intent.getAction();
                    Object obj = -1;
                    switch (action.hashCode()) {
                        case -1264339496:
                            if (action.equals(NOTIFICATION_DISMISS)) {
                                obj = 5;
                                break;
                            }
                            break;
                        case -1122176474:
                            if (action.equals(EXTRA_SERVICE_CAROUSEL)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -954625026:
                            if (action.equals(NOTIFICATION_CLEARED)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -528859471:
                            if (action.equals(EXTRA_SERVICE_NOTIFY)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -267438:
                            if (action.equals(PUSH_REG_FALLBACK)) {
                                obj = 8;
                                break;
                            }
                            break;
                        case 436702423:
                            if (action.equals(PushManager.REQ_REGISTRATION)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 651909495:
                            if (action.equals(TRACK_NOTIFICATION_RECEIVED)) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 901172608:
                            if (action.equals(PushManager.REQ_DELETE_TOKEN)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1164413677:
                            if (action.equals("SHOW_NOTIFICATION")) {
                                obj = 7;
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                    switch (obj) {
                        case null:
                            handlePushRegistration(intent);
                            break;
                        case 1:
                            handlePushTokenDeletion(intent);
                            break;
                        case 2:
                            handleCarousel(intent);
                            break;
                        case 3:
                            handleNotification(intent);
                            break;
                        case 4:
                            handleNotificationCleared(intent);
                            break;
                        case 5:
                            handleNotificationDismiss(intent);
                            break;
                        case 6:
                            handleNotificationReceived(intent);
                            break;
                        case 7:
                            handleShowNotification(intent);
                            break;
                        case 8:
                            handlePushRegistrationFallback();
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                Logger.f("MoEPushWorker#onHandleIntent: Handle payload", e);
            }
        }
    }

    private void handleShowNotification(Intent intent) {
        Logger.v("MoEPushWorker#onHandleIntent: handleShowNotification");
        PushHandler pushHandler = PushManager.getInstance().getPushHandler();
        if (pushHandler != null) {
            pushHandler.handlePushPayload(getApplicationContext(), intent.getExtras());
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void handleNotificationReceived(Intent intent) {
        Logger.v("MoEPushWorker#onHandleIntent: handleNotificationReceived");
        tryToShowNotification(intent.getExtras());
        MoEHelper.getInstance(getApplicationContext()).syncInteractionDataNow();
    }

    private void handleNotificationDismiss(Intent intent) {
        Logger.v("MoEPushWorker#onHandleIntent: handleNotificationDismiss");
        int i = intent.getExtras().getInt(NOTIFICATION_DISMISS);
        if (i != 0) {
            ((NotificationManager) getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).cancel(i);
        }
    }

    private void handleNotificationCleared(Intent intent) throws JSONException {
        Logger.v("MoEPushWorker#onHandleIntent: handleNotificationCleared");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MoEHelperConstants.GCM_EXTRA_CAMPAIGN_ID, MoEngageNotificationUtils.getCampaignIdIfAny(extras));
            MoEEventManager.getInstance(getApplicationContext()).trackEvent(MoEHelperConstants.EVENT_NOTIFICATION_CLEARED, jSONObject);
            MoEPushCallBacks.getInstance().onPushCleared(extras);
            MoEHelper.getInstance(getApplicationContext()).syncInteractionDataNow();
            MoEngageNotificationUtils.deleteImagesFromInternal(this, MoEngageNotificationUtils.getCampaignIdIfAny(extras));
        }
    }

    private void handleNotification(Intent intent) {
        Logger.v("MoEPushWorker#onHandleIntent: handleNotification");
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Logger.f("MoEPushWorker$handleNotification bundle is null");
        } else {
            tryToShowNotification(extras);
        }
    }

    private void handleCarousel(Intent intent) {
        Logger.v("MoEPushWorker#onHandleIntent: handleCarousel");
        Bundle extras = intent.getExtras();
        if (extras.containsKey(PushActionMapperConstants.KEY_ACTION_TAG)) {
            String string = extras.getString(PushActionMapperConstants.KEY_ACTION_TAG);
            try {
                if (!TextUtils.isEmpty(string) && string.equals(PushActionMapperConstants.IMG_ACTION_NEXT)) {
                    recreateCarouselNotification(extras, 1);
                } else if (!TextUtils.isEmpty(string) && string.equals(PushActionMapperConstants.IMG_ACTION_PREV)) {
                    recreateCarouselNotification(extras, -1);
                }
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MoEWorker$handleCarouselNav Exception occurred,");
                stringBuilder.append(e.getMessage());
                Logger.f(stringBuilder.toString());
            }
        }
    }

    private void handlePushTokenDeletion(Intent intent) {
        Logger.v("MoEPushWorker#onHandleIntent: GCM Token Deletion request");
        Bundle extras = intent.getExtras();
        PushManager.getInstance().getPushHandler().deleteToken(getApplicationContext(), extras.containsKey("SENDER_ID") ? extras.getString("SENDER_ID") : null);
    }

    private void handlePushRegistration(Intent intent) {
        Logger.v("MoEPushWorker#onHandleIntent: Registration request");
        ConfigurationProvider.getInstance(getApplicationContext());
        if (intent.hasExtra(MoEHelperConstants.EXTRA_REGISTRATION_ID)) {
            PushManager.getInstance().refreshTokenInternal(getApplicationContext(), intent.getStringExtra(MoEHelperConstants.EXTRA_REGISTRATION_ID), PushManager.TOKEN_BY_MOE);
        } else {
            registerForPush();
        }
    }

    private void registerForPush() {
        String registerForPushToken = PushManager.getInstance().getPushHandler().registerForPushToken(getApplicationContext());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MoEPushWorker#onHandleIntent: registerForPush ");
        stringBuilder.append(registerForPushToken);
        Logger.v(stringBuilder.toString());
    }

    private void recreateCarouselNotification(Bundle bundle, int i) {
        Logger.v("MoEPushWorker#onHandleIntent: recreateCarouselNotification");
        if (i == 1) {
            try {
                bundle.putBoolean(PushActionMapperConstants.IMG_ACTION_NEXT, true);
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MoEWorker$recreateCarouselNotification Exception ocurred ");
                stringBuilder.append(e);
                Logger.f(stringBuilder.toString());
                return;
            }
        }
        bundle.putBoolean(PushActionMapperConstants.IMG_ACTION_NEXT, false);
        i = MoEngageNotificationUtils.getNextImageIndex(bundle);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("MoEWorker$recreateCarouselNotification idx");
        stringBuilder2.append(i);
        Logger.v(stringBuilder2.toString());
        bundle.remove(PushActionMapperConstants.KEY_ACTION_TAG);
        bundle.remove(PushActionMapperConstants.KEY_ACTION_PAYLOAD);
        bundle.remove(PushActionMapperConstants.IMG_ACTION_NEXT);
        bundle.remove(PushActionMapperConstants.IMG_ACTION_PREV);
        bundle.putBoolean(PushActionMapperConstants.KEY_RENOTIFY, true);
        bundle.putInt(PushActionMapperConstants.IMG_INDEX, i);
        tryToShowNotification(bundle);
    }

    private void tryToShowNotification(Bundle bundle) {
        ((PushMessageListener) PushManager.getInstance().getPushHandler().getMessageListener()).onMessagereceived(getApplicationContext(), bundle);
    }

    private void handlePushRegistrationFallback() {
        Logger.v("MoEPushWorker#onHandleIntent: handlePushRegistrationFallback");
        ConfigurationProvider instance = ConfigurationProvider.getInstance(getApplicationContext());
        if (instance.isGCMRegistrationEnabled() && TextUtils.isEmpty(instance.getGCMToken())) {
            registerForPush();
        }
    }
}
