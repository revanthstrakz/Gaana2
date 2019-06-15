package com.moengage.pushbase.push;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.share.internal.ShareConstants;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.moe.pushlibrary.providers.MoEDataContract.DatapointEntity;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.ActionMapperConstants;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.MoEUtils;
import com.moengage.pushbase.PushActionMapperConstants;
import com.moengage.pushbase.PushConstants;
import com.moengage.pushbase.R;
import com.moengage.pushbase.activities.SnoozeTracker;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MoEngageNotificationUtils {
    private static final String ASSIGN = "%3D";
    private static final long DEFAULT_TTL = 7776000000L;
    private static final String DIRECT_PUSH_INBOX = "gcm_push2inbox";
    private static final String EXTRA_KEY_NOTIFICATIONID = "MOE_NOTIFICATION_ID";
    private static final int FIXED_HEIGHT_DP = 192;
    private static final String GCM_ACTION_CLASSNAME = "cls";
    private static final String GCM_ACTION_DEEPLINK = "dl";
    private static final String GCM_ACTION_EXTRAS = "ex";
    private static final String GCM_ACTION_ICON = "ic";
    private static final String GCM_ACTION_LINK = "lk";
    private static final String GCM_ACTION_NAME = "name";
    private static final String GCM_CAROUSEL_NOTIFICATION = "gcm_carousel";
    private static String GCM_MESSAGE_IGNORE = "gcm_message_ignore";
    private static final String KEY_MOENGAGE_EXTRAS = "moextras";
    private static String MSG_AUTODISMISS = "gcm_dismiss";
    private static String MSG_TTL = "gcm_msgttl";
    private static final String NOTIFICATION_ACTION = "gcm_action_title";
    private static final String NOTIFICATION_ACTION_BUTTONS = "gcm_actions";
    private static final String NOTIFICATION_ACTIVITY_NAME = "gcm_activityName";
    private static final String NOTIFICATION_CAMPAIGN_ID = "gcm_campaign_id";
    private static final String NOTIFICATION_CATEGORY = "gcm_category";
    private static final String NOTIFICATION_CONTENT = "gcm_alert";
    private static final String NOTIFICATION_CONTENT_TYPE = "gcm_notificationType";
    private static final String NOTIFICATION_COUPON_CODE = "gcm_coupon_code";
    private static final String NOTIFICATION_CUSTOM_IMAGES = "gcm_images";
    private static final String NOTIFICATION_DISABLE_AUTO_CANCEL = "gcm_dnc";
    private static final String NOTIFICATION_DISABLE_VIBRATION = "gcm_no_vib";
    private static final String NOTIFICATION_DISP_TYPE_MULTIPLE = "gcm_show_multi";
    private static final String NOTIFICATION_DISP_TYPE_SINGLE = "gcm_show_single";
    private static final String NOTIFICATION_GEO_ID = "gcm_geo_id";
    private static final String NOTIFICATION_IMAGE_URL = "gcm_image_url";
    private static final String NOTIFICATION_LARGE_ICON = "gcm_l_ic";
    private static final String NOTIFICATION_LED_LIGHT_COLOR = "gcm_led";
    private static final String NOTIFICATION_MSG_TAG = "gcm_msg_tag";
    private static final String NOTIFICATION_PRIORITY = "gcm_priority";
    private static final String NOTIFICATION_PRIVACY = "gcm_privacy";
    private static final String NOTIFICATION_PUBLIC_VERSION = "gcm_pub_v";
    private static final String NOTIFICATION_SILENT_NOTIFICATION = "gcm_silentNotification";
    private static final String NOTIFICATION_SUB_TEXT = "gcm_subtext";
    private static final String NOTIFICATION_TICKER = "gcm_ticker";
    private static final String NOTIFICATION_TITLE = "gcm_title";
    private static final String NOTIFICATION_TONE = "gcm_tone";
    private static final String NOTIFICATION_TONE_DISABLED = "gcm_sound_disabled";
    private static final String NOTIFICATION_TONE_SYSTEM = "gcm_tone_system";
    private static final String NOTIFICATION_WEB_URL = "gcm_webUrl";
    private static final String RECIPIENT_USER_ID = "unique_id";
    private static final int REQ_CLEARED = 501;
    private static final String SEPARATOR = "%26";

    private MoEngageNotificationUtils() {
    }

    public static boolean isFromMoEngagePlatform(Intent intent) {
        return isFromMoEngagePlatform(intent.getExtras());
    }

    public static boolean isFromMoEngagePlatform(Bundle bundle) {
        if (bundle == null) {
            try {
                Logger.e("MoEngageNotificationUtils:No Intent extra available");
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: isFromMoEngagePlatform ", e);
            }
        } else if (bundle.containsKey(MoEHelperConstants.GCM_EXTRA_CONFIRMATION_KEY) && bundle.getString(MoEHelperConstants.GCM_EXTRA_CONFIRMATION_KEY).equals("moengage")) {
            return true;
        }
        return false;
    }

    public static boolean isSilentPush(Bundle bundle) {
        String string = bundle.getString("gcm_notificationType");
        return !TextUtils.isEmpty(string) && string.equals(NOTIFICATION_SILENT_NOTIFICATION);
    }

    public static boolean isPushToInbox(Bundle bundle) {
        return bundle.containsKey(DIRECT_PUSH_INBOX);
    }

    public static void stripMoEngageExtras(Bundle bundle) {
        bundle.remove(MoEHelperConstants.EXTRA_MSG_RECEIVED_TIME);
        bundle.remove(MoEHelperConstants.NOTIFICATION_RECEIVED_MOE);
        bundle.remove("gcm_campaign_id");
        bundle.remove(MoEHelperConstants.GCM_EXTRA_CONFIRMATION_KEY);
        bundle.remove("gcm_activityName");
        bundle.remove("gcm_geo_id");
        bundle.remove(MoEHelperConstants.GCM_EXTRA_UNIQUE_ID);
    }

    public static boolean isMoEngageCampaign(Bundle bundle) {
        if (bundle != null && bundle.containsKey("gcm_campaign_id")) {
            return true;
        }
        Logger.e("MoEngageNotificationUtils:isAMoEngageCampaign--> no campaign ID so skipping to show notification");
        return false;
    }

    public static void setNotificationId(Intent intent, int i) {
        intent.putExtra(EXTRA_KEY_NOTIFICATIONID, i);
    }

    public static int getNotificationIdIfAny(Bundle bundle) {
        return bundle.containsKey(EXTRA_KEY_NOTIFICATIONID) ? bundle.getInt(EXTRA_KEY_NOTIFICATIONID) : -1;
    }

    public static String getCampaignIdIfAny(Bundle bundle) {
        return bundle.containsKey("gcm_campaign_id") ? bundle.getString("gcm_campaign_id") : null;
    }

    public static String getNotificationTypeIfAny(Bundle bundle) {
        return bundle.getString("gcm_notificationType");
    }

    public static String getNotificationTitleIfAny(Bundle bundle) {
        return bundle.getString("gcm_title");
    }

    public static void setTitleIfPresent(Bundle bundle, Builder builder) {
        builder.setContentTitle(getNotificationTitleIfAny(bundle));
    }

    public static String getNotificationContentTextIfAny(Bundle bundle) {
        return bundle.getString("gcm_alert");
    }

    public static void setContentIfPresent(Bundle bundle, Builder builder) {
        builder.setContentText(getNotificationContentTextIfAny(bundle));
    }

    public static String getNotificationSubTextIfAny(Bundle bundle) {
        return bundle.getString(NOTIFICATION_SUB_TEXT);
    }

    public static void setSubTextIfAny(Bundle bundle, Builder builder) {
        builder.setSubText(getNotificationSubTextIfAny(bundle));
    }

    public static void setNotificationPriorityIfPresentAndSupported(Bundle bundle, Builder builder) {
        if (VERSION.SDK_INT >= 16) {
            try {
                if (bundle.containsKey(NOTIFICATION_PRIORITY)) {
                    int parseInt = Integer.parseInt(bundle.getString(NOTIFICATION_PRIORITY));
                    if (parseInt < -2 || parseInt > 2) {
                        builder.setPriority(0);
                    } else {
                        builder.setPriority(parseInt);
                    }
                }
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: setNotificationPriorityIfPresentAndSupported", e);
            }
        }
    }

    public static void setSmallIcon(Context context, Builder builder, ConfigurationProvider configurationProvider) {
        try {
            int notificationLargeIconIfAny;
            if (VERSION.SDK_INT < 21) {
                notificationLargeIconIfAny = configurationProvider.getNotificationLargeIconIfAny();
            } else {
                notificationLargeIconIfAny = configurationProvider.getNotificationSmallIcon();
            }
            builder.setSmallIcon(notificationLargeIconIfAny);
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils: setSmallIcon", e);
        }
    }

    public static void setColorOrLargeIconIfPresentAndSupported(Context context, Bundle bundle, Builder builder, ConfigurationProvider configurationProvider) {
        if (VERSION.SDK_INT >= 21) {
            try {
                int notificationLargeIconIfAny = configurationProvider.getNotificationLargeIconIfAny();
                int notificationColor = configurationProvider.getNotificationColor();
                if (-1 != notificationColor) {
                    builder.setColor(context.getResources().getColor(notificationColor));
                }
                Bitmap bitmap = null;
                if (bundle.containsKey(NOTIFICATION_LARGE_ICON)) {
                    Bitmap decodeResource;
                    String string = bundle.getString(NOTIFICATION_LARGE_ICON);
                    if (TextUtils.isEmpty(string)) {
                        decodeResource = BitmapFactory.decodeResource(context.getResources(), notificationLargeIconIfAny, null);
                    } else {
                        Bitmap downloadImageBitmap = MoEHelperUtils.downloadImageBitmap(string);
                        if (downloadImageBitmap != null || -1 == notificationColor) {
                            bitmap = downloadImageBitmap;
                        } else {
                            decodeResource = BitmapFactory.decodeResource(context.getResources(), notificationLargeIconIfAny, null);
                        }
                    }
                    bitmap = decodeResource;
                } else if (notificationLargeIconIfAny != 0 && -1 == notificationColor) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), notificationLargeIconIfAny, null);
                }
                if (bitmap != null) {
                    builder.setLargeIcon(bitmap);
                }
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: setColorOrLargeIconIfPresentAndSupported", e);
            }
        }
    }

    public static void setSoundIfPresentAndSupported(Context context, Bundle bundle, Builder builder, ConfigurationProvider configurationProvider) {
        if (configurationProvider.isNotificationSoundEnabled() && VERSION.SDK_INT >= 11) {
            Object obj = 1;
            try {
                Uri parse;
                String notificationToneIfAny = configurationProvider.getNotificationToneIfAny();
                if (notificationToneIfAny != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("android.resource://");
                    stringBuilder.append(context.getPackageName());
                    stringBuilder.append("/raw/");
                    stringBuilder.append(notificationToneIfAny);
                    parse = Uri.parse(stringBuilder.toString());
                } else {
                    parse = System.DEFAULT_NOTIFICATION_URI;
                }
                if (bundle.containsKey(NOTIFICATION_TONE_DISABLED)) {
                    obj = null;
                } else if (bundle.containsKey(NOTIFICATION_TONE_SYSTEM)) {
                    parse = System.DEFAULT_NOTIFICATION_URI;
                } else if (bundle.containsKey(NOTIFICATION_TONE)) {
                    String string = bundle.getString(NOTIFICATION_TONE);
                    if (TextUtils.isEmpty(string)) {
                        Logger.v("Notification tone is not required");
                    } else {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("android.resource://");
                        stringBuilder2.append(context.getPackageName());
                        stringBuilder2.append("/raw/");
                        stringBuilder2.append(string);
                        parse = Uri.parse(stringBuilder2.toString());
                    }
                }
                if (!(obj == null || parse == null)) {
                    builder.setSound(parse);
                }
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: setSoundIfPresentAndSupported", e);
            }
        }
    }

    public static void setVisibilityIfPresentAndSupported(Bundle bundle, Builder builder) {
        if (VERSION.SDK_INT >= 21) {
            try {
                if (bundle.containsKey(NOTIFICATION_PRIVACY)) {
                    builder.setVisibility(Integer.parseInt(bundle.getString(NOTIFICATION_PRIVACY)));
                }
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: setVisibilityIfPresentAndSupported", e);
            }
        }
    }

    public static void setTickerTextIfPresent(Bundle bundle, Builder builder) {
        if (VERSION.SDK_INT >= 11) {
            try {
                if (bundle.containsKey(NOTIFICATION_TICKER)) {
                    builder.setTicker(bundle.getCharSequence(NOTIFICATION_TICKER));
                }
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: setTickerTextIfPresent", e);
            }
        }
    }

    public static void setCategoryIfPresentAndSupported(Bundle bundle, Builder builder) {
        if (VERSION.SDK_INT >= 21) {
            try {
                if (bundle.containsKey(NOTIFICATION_CATEGORY)) {
                    builder.setCategory(bundle.getString(NOTIFICATION_CATEGORY));
                }
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: setCategoryIfPresentAndSupported", e);
            }
        }
    }

    public static int getNotificationDisplayType(Bundle bundle, Context context) {
        int notificationDisplayType = ConfigurationProvider.getInstance(context).getNotificationDisplayType();
        if (isShowOnlyOneNotification(bundle)) {
            return 1;
        }
        return isShowMultipleNotification(bundle) ? 2 : notificationDisplayType;
    }

    public static boolean isImageNotification(Bundle bundle) {
        return bundle.containsKey("gcm_image_url");
    }

    public static Bitmap scaleBitmapToDeviceSpecs(Bitmap bitmap, Context context) {
        if (bitmap == null) {
            Logger.e("MoEngageNotificationUtils: scaleBitmapToDeviceSpecs");
            return null;
        }
        if (bitmap.getWidth() > bitmap.getHeight()) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int pxFromDp = MoEHelperUtils.getPxFromDp(displayMetrics.densityDpi, 192);
            int i = 2 * pxFromDp;
            if (i > displayMetrics.widthPixels) {
                i = displayMetrics.widthPixels;
            }
            if (Build.MANUFACTURER.equals("OPPO")) {
                i -= MoEHelperUtils.getPxFromDp(displayMetrics.densityDpi, 16);
            }
            try {
                bitmap = Bitmap.createScaledBitmap(bitmap, i, pxFromDp, true);
            } catch (OutOfMemoryError e) {
                Logger.f("MoEngageNotificationUtils: scaleBitmapToDeviceSpecs", e);
            } catch (Exception e2) {
                Logger.f("MoEngageNotificationUtils: scaleBitmapToDeviceSpecs", e2);
            }
        }
        return bitmap;
    }

    public static boolean isAutoCancelEnabled(Bundle bundle) {
        return bundle.containsKey(NOTIFICATION_DISABLE_AUTO_CANCEL) ^ 1;
    }

    public static String convertBundletoJSONString(Bundle bundle) {
        Set<String> keySet = bundle.keySet();
        JSONObject jSONObject = new JSONObject();
        for (String str : keySet) {
            try {
                jSONObject.put(str, bundle.get(str));
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: convertBundletoJSONString", e);
            }
        }
        return jSONObject.toString();
    }

    public static String getNotificationCouponCode(Bundle bundle) {
        return bundle.getString("gcm_coupon_code");
    }

    public static boolean hasCouponCode(Bundle bundle) {
        return bundle.containsKey("gcm_coupon_code");
    }

    public static boolean isVibrationDisabled(Bundle bundle) {
        return bundle.containsKey(NOTIFICATION_DISABLE_VIBRATION);
    }

    public static int getNotificationLedLightColor(Bundle bundle) {
        try {
            return Integer.parseInt(bundle.getString(NOTIFICATION_LED_LIGHT_COLOR));
        } catch (Exception unused) {
            return -1;
        }
    }

    public static boolean isShowOnlyOneNotification(Bundle bundle) {
        return bundle.containsKey(NOTIFICATION_DISP_TYPE_SINGLE);
    }

    public static boolean isShowMultipleNotification(Bundle bundle) {
        return bundle.containsKey(NOTIFICATION_DISP_TYPE_MULTIPLE);
    }

    public static boolean isPublicVersionAvailable(Bundle bundle) {
        return bundle.containsKey(NOTIFICATION_PUBLIC_VERSION);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ea A:{Catch:{ Exception -> 0x0101 }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c0 A:{SYNTHETIC, Splitter:B:27:0x00c0} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f3 A:{Catch:{ Exception -> 0x0101 }} */
    public static void setActionButtonIfPresentAndSupported(android.content.Context r17, android.os.Bundle r18, android.support.v4.app.NotificationCompat.Builder r19, android.content.Intent r20, int r21) {
        /*
        r1 = r17;
        r2 = r18;
        r3 = r19;
        r4 = r21;
        r5 = android.os.Build.VERSION.SDK_INT;
        r6 = 16;
        if (r5 < r6) goto L_0x0108;
    L_0x000e:
        r5 = getNotificationDisplayType(r2, r1);	 Catch:{ Exception -> 0x0101 }
        r6 = 1;
        r7 = 0;
        if (r5 != r6) goto L_0x0017;
    L_0x0016:
        goto L_0x0018;
    L_0x0017:
        r6 = r7;
    L_0x0018:
        r5 = getActions(r18);	 Catch:{ Exception -> 0x0101 }
        if (r5 != 0) goto L_0x0037;
    L_0x001e:
        r5 = "gcm_action_title";
        r5 = r2.containsKey(r5);	 Catch:{ Exception -> 0x0101 }
        if (r5 == 0) goto L_0x0108;
    L_0x0026:
        r8 = r20;
        r1 = getContentIntent(r1, r8, r6, r4);	 Catch:{ Exception -> 0x0101 }
        r4 = "gcm_action_title";
        r2 = r2.getString(r4);	 Catch:{ Exception -> 0x0101 }
        r3.addAction(r7, r2, r1);	 Catch:{ Exception -> 0x0101 }
        goto L_0x0108;
    L_0x0037:
        r8 = r20;
        r6 = r5.length();	 Catch:{ Exception -> 0x0101 }
        r9 = r7;
    L_0x003e:
        if (r9 >= r6) goto L_0x0108;
    L_0x0040:
        r10 = r5.getJSONObject(r9);	 Catch:{ Exception -> 0x0101 }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0101 }
        r11.<init>();	 Catch:{ Exception -> 0x0101 }
        r12 = "MoEngageNotificationUtils : action button: ";
        r11.append(r12);	 Catch:{ Exception -> 0x0101 }
        r12 = r10.toString();	 Catch:{ Exception -> 0x0101 }
        r11.append(r12);	 Catch:{ Exception -> 0x0101 }
        r11 = r11.toString();	 Catch:{ Exception -> 0x0101 }
        com.moengage.core.Logger.v(r11);	 Catch:{ Exception -> 0x0101 }
        r11 = "action_tag";
        r11 = r10.getString(r11);	 Catch:{ Exception -> 0x0101 }
        r12 = "action_title";
        r12 = r10.getString(r12);	 Catch:{ Exception -> 0x0101 }
        r13 = "action_icon";
        r13 = r10.has(r13);	 Catch:{ Exception -> 0x0101 }
        if (r13 == 0) goto L_0x0077;
    L_0x0070:
        r13 = "action_icon";
        r13 = r10.getString(r13);	 Catch:{ Exception -> 0x0101 }
        goto L_0x0078;
    L_0x0077:
        r13 = 0;
    L_0x0078:
        r15 = "m_remind_exact";
        r15 = r11.equals(r15);	 Catch:{ Exception -> 0x0101 }
        if (r15 != 0) goto L_0x008b;
    L_0x0080:
        r15 = "m_remind_inexact";
        r15 = r11.equals(r15);	 Catch:{ Exception -> 0x0101 }
        if (r15 == 0) goto L_0x0089;
    L_0x0088:
        goto L_0x008b;
    L_0x0089:
        r15 = r8;
        goto L_0x0096;
    L_0x008b:
        r15 = getIntentForSnooze(r17);	 Catch:{ Exception -> 0x0101 }
        r7 = r20.getExtras();	 Catch:{ Exception -> 0x0101 }
        r15.putExtras(r7);	 Catch:{ Exception -> 0x0101 }
    L_0x0096:
        r7 = "action_tag";
        r15.putExtra(r7, r11);	 Catch:{ Exception -> 0x0101 }
        r7 = "action_payload";
        r14 = r10.toString();	 Catch:{ Exception -> 0x0101 }
        r15.putExtra(r7, r14);	 Catch:{ Exception -> 0x0101 }
        r7 = "action_id";
        r14 = "action_id";
        r10 = r10.getString(r14);	 Catch:{ Exception -> 0x0101 }
        r15.putExtra(r7, r10);	 Catch:{ Exception -> 0x0101 }
        r9 = r9 + 1;
        r7 = r9 * 1000;
        r7 = r7 + r4;
        r10 = 134217728; // 0x8000000 float:3.85186E-34 double:6.63123685E-316;
        r7 = android.app.PendingIntent.getActivity(r1, r7, r15, r10);	 Catch:{ Exception -> 0x0101 }
        r10 = android.text.TextUtils.isEmpty(r13);	 Catch:{ Exception -> 0x0101 }
        if (r10 != 0) goto L_0x00ea;
    L_0x00c0:
        r10 = r17.getResources();	 Catch:{ Exception -> 0x00e0 }
        r14 = "drawable";
        r15 = r17.getPackageName();	 Catch:{ Exception -> 0x00e0 }
        r10 = r10.getIdentifier(r13, r14, r15);	 Catch:{ Exception -> 0x00e0 }
        if (r10 != 0) goto L_0x00eb;
    L_0x00d0:
        r14 = android.R.drawable.class;
        r13 = r14.getField(r13);	 Catch:{ Exception -> 0x00dc }
        r14 = 0;
        r13 = r13.getInt(r14);	 Catch:{ Exception -> 0x00dc }
        goto L_0x00e8;
    L_0x00dc:
        r0 = move-exception;
        r13 = r10;
        r10 = r0;
        goto L_0x00e3;
    L_0x00e0:
        r0 = move-exception;
        r10 = r0;
        r13 = 0;
    L_0x00e3:
        r14 = "MoEngageNotificationUtils: setActionButtonIfPresentAndSupported";
        com.moengage.core.Logger.f(r14, r10);	 Catch:{ Exception -> 0x0101 }
    L_0x00e8:
        r10 = r13;
        goto L_0x00eb;
    L_0x00ea:
        r10 = 0;
    L_0x00eb:
        r13 = "re_notify";
        r13 = r2.containsKey(r13);	 Catch:{ Exception -> 0x0101 }
        if (r13 == 0) goto L_0x00fb;
    L_0x00f3:
        r13 = "m_remind_inexact";
        r11 = r11.equals(r13);	 Catch:{ Exception -> 0x0101 }
        if (r11 != 0) goto L_0x00fe;
    L_0x00fb:
        r3.addAction(r10, r12, r7);	 Catch:{ Exception -> 0x0101 }
    L_0x00fe:
        r7 = 0;
        goto L_0x003e;
    L_0x0101:
        r0 = move-exception;
        r1 = r0;
        r2 = "MoEngageNotificationUtils: setActionButtonIfPresentAndSupported";
        com.moengage.core.Logger.f(r2, r1);
    L_0x0108:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.pushbase.push.MoEngageNotificationUtils.setActionButtonIfPresentAndSupported(android.content.Context, android.os.Bundle, android.support.v4.app.NotificationCompat$Builder, android.content.Intent, int):void");
    }

    public static final PendingIntent getContentIntent(Context context, Intent intent, boolean z, int i) {
        return PendingIntent.getActivity(context, i, intent, 134217728);
    }

    public static JSONArray getActions(Bundle bundle) {
        String string = bundle.getString(NOTIFICATION_ACTION_BUTTONS);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return new JSONArray(string);
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils: getActions", e);
            return null;
        }
    }

    public static String getRedirectActivityNameIfAny(Bundle bundle) {
        return bundle.containsKey("gcm_activityName") ? bundle.getString("gcm_activityName") : null;
    }

    public static String getDeeplinkURIStringIfAny(Bundle bundle) {
        return bundle.containsKey("gcm_webUrl") ? bundle.getString("gcm_webUrl") : null;
    }

    public static String getCampaignGeoIDIfAny(Bundle bundle) {
        return bundle.containsKey("gcm_geo_id") ? bundle.getString("gcm_geo_id") : null;
    }

    public static long getNotificationTTL(Bundle bundle, long j) {
        if (bundle.containsKey(PushConstants.MOE_NOTIFICATION_EXPIRY)) {
            return Long.parseLong(bundle.getString(PushConstants.MOE_NOTIFICATION_EXPIRY)) * 1000;
        }
        return bundle.containsKey(MSG_TTL) ? convertTimeToLong(bundle.getString(MSG_TTL)) : j + DEFAULT_TTL;
    }

    public static String getMessageTagsIfAny(Bundle bundle) {
        return bundle.containsKey(NOTIFICATION_MSG_TAG) ? bundle.getString(NOTIFICATION_MSG_TAG) : null;
    }

    public static int getNotificationId(Context context, ConfigurationProvider configurationProvider, boolean z) {
        if (z) {
            return configurationProvider.getNotificationId();
        }
        int notificationId = configurationProvider.getNotificationId() + 1;
        configurationProvider.updateNotificationId(notificationId);
        return notificationId;
    }

    public static void setNotificationStyle(Context context, Bundle bundle, Builder builder) {
        BigTextStyle bigTextStyle;
        StringBuilder stringBuilder;
        if (isImageNotification(bundle)) {
            CharSequence notificationSubTextIfAny = getNotificationSubTextIfAny(bundle);
            if (TextUtils.isEmpty(notificationSubTextIfAny)) {
                notificationSubTextIfAny = getNotificationContentTextIfAny(bundle);
            }
            Bitmap downloadImageBitmap = MoEHelperUtils.downloadImageBitmap(bundle.getString("gcm_image_url"));
            if (downloadImageBitmap == null) {
                bigTextStyle = new BigTextStyle();
                stringBuilder = new StringBuilder();
                stringBuilder.append(getNotificationContentTextIfAny(bundle));
                stringBuilder.append(" ");
                builder.setStyle(bigTextStyle.bigText(stringBuilder.toString()));
                return;
            }
            Bitmap scaleBitmapToDeviceSpecs = scaleBitmapToDeviceSpecs(downloadImageBitmap, context);
            if (VERSION.SDK_INT < 24) {
                builder.setStyle(new BigPictureStyle().bigPicture(scaleBitmapToDeviceSpecs).setSummaryText(notificationSubTextIfAny));
                return;
            } else {
                builder.setStyle(new BigPictureStyle().bigPicture(scaleBitmapToDeviceSpecs).setSummaryText(getNotificationContentTextIfAny(bundle)));
                return;
            }
        }
        bigTextStyle = new BigTextStyle();
        stringBuilder = new StringBuilder();
        stringBuilder.append(getNotificationContentTextIfAny(bundle));
        stringBuilder.append(" ");
        builder.setStyle(bigTextStyle.bigText(stringBuilder.toString()));
    }

    public static String getRecipientUserId(Bundle bundle) {
        return bundle.getString(RECIPIENT_USER_ID);
    }

    public static void setNotificationAutoDismissIfAny(Context context, int i, Bundle bundle) {
        long notificationExpiryTime = getNotificationExpiryTime(bundle);
        if (notificationExpiryTime != -1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEngageNotificationUtils: setNotificationAutoDismissIfAny: setting an auto dismiss after: ");
            stringBuilder.append(notificationExpiryTime);
            Logger.v(stringBuilder.toString());
            Intent intent = new Intent(context, MoEPushWorker.class);
            intent.putExtra(MoEPushWorker.NOTIFICATION_DISMISS, i);
            intent.setAction(MoEPushWorker.NOTIFICATION_DISMISS);
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, notificationExpiryTime, PendingIntent.getService(context, i, intent, 134217728));
        }
    }

    public static void setNotificationClearedCallback(Context context, Builder builder, int i, Bundle bundle) {
        Intent intent = new Intent(context, MoEPushWorker.class);
        intent.putExtras(bundle);
        intent.setAction(MoEPushWorker.NOTIFICATION_CLEARED);
        builder.setDeleteIntent(PendingIntent.getService(context, i | REQ_CLEARED, intent, 134217728));
    }

    public static Bundle getMoEngageExtras(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(MoEHelperConstants.NOTIFICATION_RECEIVED_MOE, "true");
        bundle2.putString("gcm_campaign_id", getCampaignIdIfAny(bundle));
        bundle2.putString(MoEHelperConstants.GCM_EXTRA_CONFIRMATION_KEY, "moengage");
        bundle2.putString("gcm_activityName", bundle.getString("gcm_activityName"));
        bundle2.putString("gcm_geo_id", bundle.getString("gcm_geo_id"));
        bundle2.putString(MoEHelperConstants.GCM_EXTRA_UNIQUE_ID, bundle.getString(MoEHelperConstants.GCM_EXTRA_UNIQUE_ID));
        return bundle2;
    }

    public static void setMoEngageExtrastoUri(Bundle bundle, Uri.Builder builder) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Set<String> keySet = bundle.keySet();
                    int size = keySet.size();
                    for (String str : keySet) {
                        size--;
                        if (!TextUtils.isEmpty(str)) {
                            if (!"gcm_webUrl".equals(str)) {
                                stringBuilder.append(str);
                                stringBuilder.append(ASSIGN);
                                stringBuilder.append(bundle.get(str));
                                if (size > 0) {
                                    stringBuilder.append(SEPARATOR);
                                }
                            }
                        }
                    }
                    builder.appendQueryParameter(KEY_MOENGAGE_EXTRAS, stringBuilder.toString());
                }
            } catch (Exception e) {
                Logger.f("MoEHelperUtils: getMoEngageExtrasAsUriParam :", e);
            }
        }
    }

    public static void setMoEngageExtrasToBundleIfAny(Intent intent) {
        if (intent != null) {
            try {
                Uri data = intent.getData();
                if (data != null) {
                    Bundle extras = intent.getExtras();
                    if (extras == null) {
                        extras = new Bundle();
                    }
                    String queryParameter = data.getQueryParameter(KEY_MOENGAGE_EXTRAS);
                    if (!TextUtils.isEmpty(queryParameter)) {
                        if (queryParameter.contains(SEPARATOR)) {
                            for (String splitKVPairs : queryParameter.split(SEPARATOR)) {
                                String[] splitKVPairs2 = splitKVPairs(splitKVPairs);
                                if (splitKVPairs2.length == 2) {
                                    extras.putString(splitKVPairs2[0], splitKVPairs2[1]);
                                }
                            }
                        } else {
                            String[] splitKVPairs3 = splitKVPairs(queryParameter);
                            extras.putString(splitKVPairs3[0], splitKVPairs3[1]);
                        }
                        intent.putExtras(extras);
                    }
                }
            } catch (Exception e) {
                Logger.f("MoEHelperUtils: getMoEngageExtrasAsUriParam :", e);
            }
        }
    }

    private static String[] splitKVPairs(String str) {
        return str.split(ASSIGN);
    }

    public static boolean hasNotificationExpired(Bundle bundle) {
        long notificationExpiryTime = getNotificationExpiryTime(bundle);
        boolean z = false;
        if (notificationExpiryTime == -1) {
            Logger.v("MoEngageNotificationUtils#hasNotificationExpired : Notification does not have an expiry time");
            return false;
        }
        if (System.currentTimeMillis() > notificationExpiryTime) {
            z = true;
        }
        if (z) {
            Logger.v("MoEngageNotificationUtils#hasNotificationExpired : received notification has expired");
            bundle.putBoolean(MoEHelperConstants.EXTRA_CAMPAIGN_EXPIRED, true);
        } else {
            Logger.v("MoEngageNotificationUtils#hasNotificationExpired : received notification has not expired");
        }
        return z;
    }

    public static long getNotificationExpiryTime(Bundle bundle) {
        return convertTimeToLong(bundle.containsKey(MSG_AUTODISMISS) ? bundle.getString(MSG_AUTODISMISS) : null);
    }

    private static long convertTimeToLong(String str) {
        StringBuilder stringBuilder;
        try {
            if (!TextUtils.isEmpty(str)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getDefault());
                return simpleDateFormat.parse(str).getTime();
            }
        } catch (ParseException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("MoEngageNotificationUtils$hasNotificationExpired : exception while parsing date ");
            stringBuilder.append(e.getMessage());
            Logger.f(stringBuilder.toString());
        } catch (Exception e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("MoEngageNotificationUtils$hasNotificationExpired : exception while parsing date ");
            stringBuilder.append(e2.getMessage());
            Logger.f(stringBuilder.toString());
        }
        return -1;
    }

    public static boolean isSkipNotificationCenter(Bundle bundle) {
        return bundle.containsKey(GCM_MESSAGE_IGNORE) ? "true".equalsIgnoreCase(bundle.getString(GCM_MESSAGE_IGNORE).trim()) : false;
    }

    public static boolean isCarouselNotification(Bundle bundle) {
        return bundle.containsKey(GCM_CAROUSEL_NOTIFICATION);
    }

    public static String getCarouselTitle(JSONObject jSONObject, Bundle bundle) {
        return getVal(jSONObject, bundle, "gcm_title");
    }

    public static String getCarouselText(JSONObject jSONObject, Bundle bundle) {
        return getVal(jSONObject, bundle, "gcm_alert");
    }

    public static String getCarouselSubText(JSONObject jSONObject, Bundle bundle) {
        return getVal(jSONObject, bundle, NOTIFICATION_SUB_TEXT);
    }

    private static String getVal(JSONObject jSONObject, Bundle bundle, String str) {
        try {
            return jSONObject.has(str) ? jSONObject.getString(str) : bundle.getString(str);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEngageNotificationUtils : getVal : Exception Occurred");
            stringBuilder.append(e);
            Logger.f(stringBuilder.toString());
            return bundle.getString(str);
        }
    }

    public static int getCarouselSmallNotificationIcon(Context context) {
        try {
            return ConfigurationProvider.getInstance(context).getNotificationSmallIcon();
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils$getCarouselSmallNotificationIcon", e);
            return 0;
        }
    }

    public static int getCarouselLargeNotificationIcon(Context context) {
        try {
            return ConfigurationProvider.getInstance(context).getNotificationLargeIconIfAny();
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils$getCarouselLargeNotificationIcon", e);
            return 0;
        }
    }

    private static void saveToInternalStorage(Context context, String str, Bitmap bitmap) {
        Throwable e;
        if (context == null || str == null || bitmap == null) {
            Logger.v("MoEngageNotificationUtils$saveToInternalStorage : context/fileName/bitmapImage is null");
        }
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            try {
                bitmap.compress(CompressFormat.PNG, 100, openFileOutput);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = openFileOutput;
                try {
                    Logger.f("MoEngageNotificationUtils$saveToInternalStorage: Exception occurred ", e);
                    fileOutputStream.close();
                } catch (Throwable th) {
                    e = th;
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        Logger.f("MoEngageNotificationUtils$saveToInternalStorage: Exception occurred ", e3);
                    } catch (Exception e4) {
                        Logger.f("MoEngageNotificationUtils$saveToInternalStorage: Exception occurred ", e4);
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                fileOutputStream = openFileOutput;
                fileOutputStream.close();
                throw e;
            }
            try {
                openFileOutput.close();
            } catch (IOException e32) {
                Logger.f("MoEngageNotificationUtils$saveToInternalStorage: Exception occurred ", e32);
            } catch (Exception e42) {
                Logger.f("MoEngageNotificationUtils$saveToInternalStorage: Exception occurred ", e42);
            }
        } catch (Exception e5) {
            e = e5;
            Logger.f("MoEngageNotificationUtils$saveToInternalStorage: Exception occurred ", e);
            fileOutputStream.close();
        }
    }

    public static Bitmap loadImageFromStorage(Context context, String str) {
        if (context == null || str == null) {
            Logger.v("MoEngageNotificationUtils$loadImageFromStorage : context/fileName is null");
            return null;
        }
        try {
            if (context.getFileStreamPath(str).exists()) {
                return BitmapFactory.decodeStream(context.openFileInput(str));
            }
        } catch (FileNotFoundException e) {
            Logger.f("MoEngageNotificationUtils$loadImageFromStorage: Exception occurred ", e);
        } catch (Exception e2) {
            Logger.f("MoEngageNotificationUtils$loadImageFromStorage: Exception occurred ", e2);
        }
        return null;
    }

    public static JSONArray getImagesArray(JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray(NOTIFICATION_CUSTOM_IMAGES);
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils$getImagesArray : Exception Occurred", e);
            return null;
        }
    }

    public static void fetchAndSaveImages(Context context, JSONArray jSONArray, String str) {
        if (jSONArray != null && context != null) {
            try {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("MoEngageNotificationUtils$fetchAndSaveImages : carousel images: ");
                    stringBuilder.append(jSONObject.toString());
                    Logger.v(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(jSONObject.getString("id"));
                    String stringBuilder2 = stringBuilder.toString();
                    String string = jSONObject.getString("url");
                    Bitmap downloadImageBitmap = MoEHelperUtils.downloadImageBitmap(string);
                    if (downloadImageBitmap != null) {
                        downloadImageBitmap = scaleBitmapToDeviceSpecs(downloadImageBitmap, context);
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("MoEngageNotificationUtils$fetchAndSaveImages : save bitmap for ");
                        stringBuilder3.append(stringBuilder2);
                        stringBuilder3.append(" and url: ");
                        stringBuilder3.append(string);
                        Logger.v(stringBuilder3.toString());
                        saveToInternalStorage(context, stringBuilder2, downloadImageBitmap);
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("MoEngageNotificationUtils$fetchAndSaveImages : Failed to download image for ");
                        stringBuilder.append(string);
                        Logger.v(stringBuilder.toString());
                    }
                }
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils$fetchAndSaveImages : Exception Occurred ", e);
            }
        }
    }

    public static PendingIntent getImagePendingIntent(Context context, Intent intent, int i, JSONArray jSONArray) {
        try {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            intent.putExtra(PushActionMapperConstants.KEY_ACTION_TAG, ActionMapperConstants.ACTION_NAVIGATE);
            intent.putExtra("id", jSONObject.getString("id"));
            intent.putExtra(PushActionMapperConstants.KEY_ACTION_PAYLOAD, jSONObject.toString());
            return PendingIntent.getActivity(context, i, intent, 134217728);
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils$getImagePendingIntent : Exception Occurred ", e);
            return null;
        }
    }

    public static int getNextImageIndex(Bundle bundle) {
        try {
            JSONArray imagesArray = getImagesArray(getCarouselObject(bundle));
            int i = bundle.containsKey(PushActionMapperConstants.IMG_INDEX) ? bundle.getInt(PushActionMapperConstants.IMG_INDEX) : 0;
            if (bundle.containsKey(PushActionMapperConstants.IMG_ACTION_NEXT)) {
                if (bundle.getBoolean(PushActionMapperConstants.IMG_ACTION_NEXT)) {
                    int i2 = i + 1;
                    if (i2 == imagesArray.length()) {
                        i2 = 0;
                    }
                    return i2;
                }
                i--;
                if (i == -1) {
                    i = imagesArray.length() - 1;
                }
                return i;
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEngageNotificationUtils$getNextImageFileName : Exception Occured");
            stringBuilder.append(e);
            Logger.f(stringBuilder.toString());
        }
        return 0;
    }

    @Nullable
    public static JSONObject getCarouselObject(Bundle bundle) {
        String string = bundle.getString(GCM_CAROUSEL_NOTIFICATION);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils$getCarouselObject", e);
            return null;
        }
    }

    public static PendingIntent getNavPendingIntent(Context context, Intent intent, String str, int i, int i2) {
        intent.putExtra(PushActionMapperConstants.KEY_ACTION_TAG, str);
        intent.putExtra(str, true);
        intent.putExtra(PushActionMapperConstants.IMG_INDEX, i2);
        return PendingIntent.getService(context, i, intent, 134217728);
    }

    public static void deleteImagesFromInternal(Context context, String str) {
        if (context == null) {
            Logger.v("MoEngageNotificationUtils$deleteImagesFromInternal context is null");
            return;
        }
        try {
            for (String str2 : context.fileList()) {
                if (str2.contains(str)) {
                    context.deleteFile(str2);
                }
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEngageNotificationUtils$deleteImagesFromInternal Exception ocurred");
            stringBuilder.append(e);
            Logger.f(stringBuilder.toString());
        }
    }

    public static boolean isReNotification(Bundle bundle) {
        return bundle.containsKey(PushActionMapperConstants.KEY_RENOTIFY);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x009d A:{Catch:{ Exception -> 0x00e4 }} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009c A:{SYNTHETIC} */
    public static void addCarouselActionButton(android.content.Context r16, android.widget.RemoteViews r17, android.os.Bundle r18, android.content.Intent r19, int r20) {
        /*
        r7 = r17;
        r8 = getActions(r18);	 Catch:{ Exception -> 0x00e4 }
        if (r8 == 0) goto L_0x00eb;
    L_0x0008:
        r1 = r8.length();	 Catch:{ Exception -> 0x00e4 }
        r2 = 3;
        if (r1 > r2) goto L_0x0013;
    L_0x000f:
        r2 = r8.length();	 Catch:{ Exception -> 0x00e4 }
    L_0x0013:
        r9 = r2;
        r10 = 0;
        r11 = r10;
    L_0x0016:
        if (r11 >= r9) goto L_0x00eb;
    L_0x0018:
        r1 = r8.getJSONObject(r11);	 Catch:{ Exception -> 0x00e4 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e4 }
        r2.<init>();	 Catch:{ Exception -> 0x00e4 }
        r3 = "MoEngageNotificationUtils$addCarouselActionButton: ";
        r2.append(r3);	 Catch:{ Exception -> 0x00e4 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x00e4 }
        r2.append(r3);	 Catch:{ Exception -> 0x00e4 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x00e4 }
        com.moengage.core.Logger.v(r2);	 Catch:{ Exception -> 0x00e4 }
        r2 = "action_tag";
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x00e4 }
        r3 = "action_title";
        r3 = r1.getString(r3);	 Catch:{ Exception -> 0x00e4 }
        r4 = "action_icon";
        r4 = r1.has(r4);	 Catch:{ Exception -> 0x00e4 }
        r5 = 0;
        if (r4 == 0) goto L_0x0050;
    L_0x0049:
        r4 = "action_icon";
        r4 = r1.getString(r4);	 Catch:{ Exception -> 0x00e4 }
        goto L_0x0051;
    L_0x0050:
        r4 = r5;
    L_0x0051:
        r6 = "m_remind_exact";
        r6 = r2.equals(r6);	 Catch:{ Exception -> 0x00e4 }
        if (r6 != 0) goto L_0x0065;
    L_0x0059:
        r6 = "m_remind_inexact";
        r6 = r2.equals(r6);	 Catch:{ Exception -> 0x00e4 }
        if (r6 == 0) goto L_0x0062;
    L_0x0061:
        goto L_0x0065;
    L_0x0062:
        r6 = r19;
        goto L_0x0070;
    L_0x0065:
        r6 = getIntentForSnooze(r16);	 Catch:{ Exception -> 0x00e4 }
        r12 = r19.getExtras();	 Catch:{ Exception -> 0x00e4 }
        r6.putExtras(r12);	 Catch:{ Exception -> 0x00e4 }
    L_0x0070:
        r12 = "action_tag";
        r6.putExtra(r12, r2);	 Catch:{ Exception -> 0x00e4 }
        r2 = "action_payload";
        r12 = r1.toString();	 Catch:{ Exception -> 0x00e4 }
        r6.putExtra(r2, r12);	 Catch:{ Exception -> 0x00e4 }
        r2 = "action_id";
        r12 = "action_id";
        r1 = r1.getString(r12);	 Catch:{ Exception -> 0x00e4 }
        r6.putExtra(r2, r1);	 Catch:{ Exception -> 0x00e4 }
        r1 = r11 + 100;
        r1 = r1 * r20;
        r2 = 134217728; // 0x8000000 float:3.85186E-34 double:6.63123685E-316;
        r13 = r16;
        r1 = android.app.PendingIntent.getActivity(r13, r1, r6, r2);	 Catch:{ Exception -> 0x00e4 }
        r2 = getCarouselActionButtonId(r11);	 Catch:{ Exception -> 0x00e4 }
        r6 = -1;
        if (r2 != r6) goto L_0x009d;
    L_0x009c:
        return;
    L_0x009d:
        r6 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x00e4 }
        if (r6 != 0) goto L_0x00cb;
    L_0x00a3:
        r6 = r16.getResources();	 Catch:{ Exception -> 0x00c1 }
        r14 = "drawable";
        r15 = r16.getPackageName();	 Catch:{ Exception -> 0x00c1 }
        r6 = r6.getIdentifier(r4, r14, r15);	 Catch:{ Exception -> 0x00c1 }
        if (r6 != 0) goto L_0x00c9;
    L_0x00b3:
        r14 = android.R.drawable.class;
        r4 = r14.getField(r4);	 Catch:{ Exception -> 0x00be }
        r4 = r4.getInt(r5);	 Catch:{ Exception -> 0x00be }
        goto L_0x00cc;
    L_0x00be:
        r0 = move-exception;
        r4 = r0;
        goto L_0x00c4;
    L_0x00c1:
        r0 = move-exception;
        r4 = r0;
        r6 = r10;
    L_0x00c4:
        r5 = "MoEngageNotificationUtils$addCarouselActionButton";
        com.moengage.core.Logger.d(r5, r4);	 Catch:{ Exception -> 0x00e4 }
    L_0x00c9:
        r4 = r6;
        goto L_0x00cc;
    L_0x00cb:
        r4 = r10;
    L_0x00cc:
        r7.setTextViewText(r2, r3);	 Catch:{ Exception -> 0x00e4 }
        r7.setOnClickPendingIntent(r2, r1);	 Catch:{ Exception -> 0x00e4 }
        r7.setViewVisibility(r2, r10);	 Catch:{ Exception -> 0x00e4 }
        r5 = 0;
        r6 = 0;
        r14 = 0;
        r1 = r7;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r14;
        r1.setTextViewCompoundDrawablesRelative(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x00e4 }
        r11 = r11 + 1;
        goto L_0x0016;
    L_0x00e4:
        r0 = move-exception;
        r1 = r0;
        r2 = "MoEngageNotificationUtils$addCarouselActionButton : Exception";
        com.moengage.core.Logger.f(r2, r1);
    L_0x00eb:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.pushbase.push.MoEngageNotificationUtils.addCarouselActionButton(android.content.Context, android.widget.RemoteViews, android.os.Bundle, android.content.Intent, int):void");
    }

    private static int getCarouselActionButtonId(int i) {
        switch (i) {
            case 0:
                return R.id.action1;
            case 1:
                return R.id.action2;
            case 2:
                return R.id.action3;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MoEngageNotificationUtils$getCarouselActionButtionId Invalid Id ");
                stringBuilder.append(i);
                Logger.e(stringBuilder.toString());
                return -1;
        }
    }

    public static int calculateTopPadding(Context context, boolean z) {
        return context.getResources().getDimensionPixelSize(z ? R.dimen.notification_top_pad_narrow : R.dimen.notification_top_pad);
    }

    public static String getTime() {
        return new SimpleDateFormat("kk:mm").format(Calendar.getInstance().getTime());
    }

    public static void disableSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003d  */
    public static int getViewFlipperImageId(int r3, java.lang.String r4) {
        /*
        r0 = r4.hashCode();
        r1 = -87315416; // 0xfffffffffacbac28 float:-5.28764E35 double:NaN;
        r2 = -1;
        if (r0 == r1) goto L_0x001a;
    L_0x000a:
        r1 = 1553519760; // 0x5c98d490 float:3.44143291E17 double:7.675407436E-315;
        if (r0 == r1) goto L_0x0010;
    L_0x000f:
        goto L_0x0024;
    L_0x0010:
        r0 = "left_to_right";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0024;
    L_0x0018:
        r4 = 0;
        goto L_0x0025;
    L_0x001a:
        r0 = "right_to_left";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0024;
    L_0x0022:
        r4 = 1;
        goto L_0x0025;
    L_0x0024:
        r4 = r2;
    L_0x0025:
        switch(r4) {
            case 0: goto L_0x0042;
            case 1: goto L_0x003d;
            default: goto L_0x0028;
        };
    L_0x0028:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = "MoEngageNotificationUtils$getViewFlipperImageId Invalid Id ";
        r4.append(r0);
        r4.append(r3);
        r3 = r4.toString();
        com.moengage.core.Logger.e(r3);
        goto L_0x0046;
    L_0x003d:
        r2 = getViewIdForRightToLeft(r3);
        goto L_0x0046;
    L_0x0042:
        r2 = getViewIdForLeftToRight(r3);
    L_0x0046:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.pushbase.push.MoEngageNotificationUtils.getViewFlipperImageId(int, java.lang.String):int");
    }

    private static int getViewIdForLeftToRight(int i) {
        switch (i) {
            case 0:
                return R.id.flip_picture1_lr;
            case 1:
                return R.id.flip_picture2_lr;
            case 2:
                return R.id.flip_picture3_lr;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MoEngageNotificationUtils$getViewIdForLeftToRight Invalid Id ");
                stringBuilder.append(i);
                Logger.e(stringBuilder.toString());
                return -1;
        }
    }

    private static int getViewIdForRightToLeft(int i) {
        switch (i) {
            case 0:
                return R.id.flip_picture1_rl;
            case 1:
                return R.id.flip_picture2_rl;
            case 2:
                return R.id.flip_picture3_rl;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MoEngageNotificationUtils$getViewIdForRightToLeft Invalid Id ");
                stringBuilder.append(i);
                Logger.e(stringBuilder.toString());
                return -1;
        }
    }

    public static final Intent getIntentForSnooze(Context context) {
        Intent intent = new Intent(context, SnoozeTracker.class);
        intent.setFlags(268468224);
        return intent;
    }

    public static boolean isFromMoEngagePlatform(Map<String, String> map) {
        if (map == null) {
            try {
                Logger.e("MoEngageNotificationUtils:No Intent extra available");
            } catch (Exception e) {
                Logger.f("MoEngageNotificationUtils: isFromMoEngagePlatform ", e);
            }
        } else if (map.containsKey(MoEHelperConstants.GCM_EXTRA_CONFIRMATION_KEY)) {
            return ((String) map.get(MoEHelperConstants.GCM_EXTRA_CONFIRMATION_KEY)).equals("moengage");
        }
        return false;
    }

    public static boolean isFromMoEngagePlatform(String str) {
        try {
            Bundle jsonToBundle = MoEUtils.jsonToBundle(new JSONObject(str));
            if (jsonToBundle != null) {
                return isFromMoEngagePlatform(jsonToBundle);
            }
        } catch (Exception e) {
            Logger.f("MoEngageNotificationUtils: isFromMoEngagePlatform ", e);
        }
        return false;
    }

    public static final void logNotificationImpression(Context context, Bundle bundle) {
        try {
            if (isFromMoEngagePlatform(bundle) && bundle.containsKey("gcm_campaign_id")) {
                String string;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("gcm_campaign_id", bundle.getString("gcm_campaign_id"));
                if (bundle.containsKey("gcm_geo_id")) {
                    string = bundle.getString("gcm_geo_id");
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject.put("gcm_geo_id", string);
                    }
                }
                if (bundle.containsKey(MoEHelperConstants.GCM_EXTRA_UNIQUE_ID)) {
                    string = bundle.getString(MoEHelperConstants.GCM_EXTRA_UNIQUE_ID);
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject.put(MoEHelperConstants.GCM_EXTRA_UNIQUE_ID, string);
                    }
                }
                if (bundle.containsKey(MoEHelperConstants.EXTRA_CAMPAIGN_EXPIRED)) {
                    jSONObject.put(MoEHelperConstants.EXTRA_CAMPAIGN_EXPIRED, true);
                }
                if (bundle.containsKey("received_from")) {
                    jSONObject.put(ShareConstants.FEED_SOURCE_PARAM, bundle.getString("received_from"));
                }
                if (bundle.containsKey("from_appOpen")) {
                    jSONObject.put("from_appOpen", bundle.getBoolean("from_appOpen"));
                }
                if (bundle.containsKey(PushConstants.ATTR_PUSH_PROVIDER)) {
                    jSONObject.put(PushConstants.ATTR_PUSH_PROVIDER, bundle.getString(PushConstants.ATTR_PUSH_PROVIDER));
                }
                if (bundle.containsKey(PushConstants.ATTR_CAMPAIGN_ATTRIBUTES)) {
                    JSONObject jSONObject2 = new JSONObject(bundle.getString(PushConstants.ATTR_CAMPAIGN_ATTRIBUTES));
                    if (jSONObject2 != null) {
                        Iterator keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String str = (String) keys.next();
                            jSONObject.put(str, jSONObject2.getString(str));
                        }
                    }
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatapointColumns.DETAILS, MoEHelperUtils.getDatapointJSON(MoEHelperConstants.NOTIFICATION_RECEIVED_MOE, jSONObject).toString());
                contentValues.put(BaseColumns.GTIME, Long.toString(System.currentTimeMillis()));
                context.getContentResolver().insert(DatapointEntity.getContentUri(context), contentValues);
                MoEHelper.getInstance(context).syncInteractionDataNow();
            }
        } catch (Exception e) {
            Logger.f("PushMessageListener:trackNotification", e);
        }
    }

    public static final void logNotificationClick(Context context, Intent intent) {
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                if (extras != null && isFromMoEngagePlatform(extras)) {
                    boolean isFromInbox = MoEHelperUtils.isFromInbox(extras);
                    if (extras.containsKey(MoEHelperConstants.NOTIFICATION_RECEIVED_MOE) && !isFromInbox) {
                        Object string;
                        Object obj = null;
                        if (extras.containsKey("gcm_geo_id")) {
                            obj = extras.getString("gcm_geo_id");
                            string = extras.getString(MoEHelperConstants.GCM_EXTRA_UNIQUE_ID);
                        } else {
                            string = null;
                        }
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("gcm_campaign_id", getCampaignIdIfAny(extras));
                            if (obj != null) {
                                jSONObject.put("gcm_geo_id", obj);
                            }
                            if (string != null) {
                                jSONObject.put(MoEHelperConstants.GCM_EXTRA_UNIQUE_ID, string);
                            }
                            if (extras.containsKey("action_id")) {
                                jSONObject.put("gcm_action_id", extras.getString("action_id"));
                            }
                            if (extras.containsKey("received_from")) {
                                jSONObject.put(ShareConstants.FEED_SOURCE_PARAM, extras.getString("received_from"));
                                extras.remove("received_from");
                            }
                            if (extras.containsKey("from_appOpen")) {
                                jSONObject.put("from_appOpen", extras.getBoolean("from_appOpen"));
                                extras.remove("from_appOpen");
                            }
                            if (extras.containsKey(PushConstants.ATTR_PUSH_PROVIDER)) {
                                jSONObject.put(PushConstants.ATTR_PUSH_PROVIDER, extras.getString(PushConstants.ATTR_PUSH_PROVIDER));
                            }
                            if (extras.containsKey(PushConstants.ATTR_CAMPAIGN_ATTRIBUTES)) {
                                JSONObject jSONObject2 = new JSONObject(extras.getString(PushConstants.ATTR_CAMPAIGN_ATTRIBUTES));
                                if (jSONObject2 != null) {
                                    Iterator keys = jSONObject2.keys();
                                    while (keys.hasNext()) {
                                        String str = (String) keys.next();
                                        jSONObject.put(str, jSONObject2.getString(str));
                                    }
                                }
                                intent.removeExtra(PushConstants.ATTR_CAMPAIGN_ATTRIBUTES);
                            }
                            MoEHelper.getInstance(context).trackEvent(MoEHelperConstants.EVENT_NOTIFICATION_CLICKED, jSONObject);
                            if (extras.containsKey(MoEHelperConstants.EXTRA_MSG_RECEIVED_TIME)) {
                                MoEHelper.getInstance(context).trackNotificationClickedByTime(extras.getLong(MoEHelperConstants.EXTRA_MSG_RECEIVED_TIME));
                            }
                            intent.removeExtra(MoEHelperConstants.NOTIFICATION_RECEIVED_MOE);
                        } catch (JSONException e) {
                            Logger.f("PushMessageListener:logNotificationClicked", e);
                        }
                    }
                }
            } catch (Exception e2) {
                Logger.f("PushMessageListener:logNotificationClicked", e2);
            }
        }
    }
}
