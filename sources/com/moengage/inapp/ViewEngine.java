package com.moengage.inapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.ActionMapperConstants;
import com.moengage.core.Logger;
import com.moengage.inapp.InAppManager.InAppMessageListener;
import com.moengage.inapp.InAppMessage.ALIGN_TYPE;
import java.net.URL;
import java.util.WeakHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ViewEngine {
    private static final String ATTR_BOTTOM = "bottom";
    private static final String ATTR_LEFT = "left";
    private static final String ATTR_RIGHT = "right";
    private static final String ATTR_TOP = "top";
    private static final int CONTENT_TYPE_BUTTON = 4;
    private static final int CONTENT_TYPE_BUTTON_ARRAY = 9;
    private static final int CONTENT_TYPE_CLOSE = 7;
    private static final int CONTENT_TYPE_GIF = 6;
    private static final int CONTENT_TYPE_IMAGE = 3;
    private static final int CONTENT_TYPE_RATING = 8;
    private static final int CONTENT_TYPE_TEXT = 2;
    private static final int CONTENT_TYPE_VIEW = 5;
    static final String EVENT_IN_APP_CLICKED = "IN_APP_CLICKED";
    private static final String IMAGE_TYPE_LOCAL_PREFIX = "resources://";
    private static final String INAPP_ALIGNMENT = "alignment";
    public static final int INAPP_BLUR_WRAPPER_ID = 10001;
    static final String INAPP_CAMPAIGN_WIDGET_DATA = "widgets";
    private static final String INAPP_TEXT_STYLE_BOLD = "bold";
    private static final String INAPP_TEXT_STYLE_FONTNAME = "typeface";
    private static final String INAPP_TEXT_STYLE_ITALICS = "italics";
    private static final String INAPP_TEXT_STYLE_UNDERLINE = "underline";
    private static final String INAPP_TYPE = "type";
    static final String INAPP_WIDGET_ACTION_LIST = "actionV2";
    static final String INAPP_WIDGET_ACTION_TAG = "tag";
    private static final String INAPP_WIDGET_CONTENT = "content";
    protected static final String INAPP_WIDGET_ID = "id";
    public static final int INAPP_WIDGET_ID_BASE = 2000;
    private static final String INAPP_WIDGET_LAYOUT = "layout";
    public static final String INAPP_WIDGET_LAYOUT_ALIGN_LEFT = "left";
    public static final String INAPP_WIDGET_LAYOUT_ALIGN_RIGHT = "right";
    private static final String INAPP_WIDGET_LAYOUT_ANIMATION = "animate";
    private static final String INAPP_WIDGET_LAYOUT_CENTER_HORIZONTAL = "center_h";
    private static final String INAPP_WIDGET_LAYOUT_CENTER_VERTICAL = "center_v";
    private static final String INAPP_WIDGET_LAYOUT_GRAVITY = "gravity";
    private static final String INAPP_WIDGET_LAYOUT_HEIGHT = "height";
    private static final String INAPP_WIDGET_LAYOUT_MARGIN = "margin";
    private static final String INAPP_WIDGET_LAYOUT_PADDING = "padding";
    private static final String INAPP_WIDGET_LAYOUT_WIDTH = "width";
    static final String INAPP_WIDGET_PRIMARY_ACTION = "primary";
    private static final String INAPP_WIDGET_PROPERTIES = "properties";
    private static final String INAPP_WIDGET_PROP_BACKGROUND = "background";
    private static final String INAPP_WIDGET_PROP_BORDER = "border";
    private static final String INAPP_WIDGET_PROP_COLOR = "color";
    private static final String INAPP_WIDGET_PROP_IMAGE = "image";
    private static final String INAPP_WIDGET_PROP_OPACITY = "opacity";
    private static final String INAPP_WIDGET_PROP_RADIUS = "radius";
    private static final String INAPP_WIDGET_PROP_WEIGHT = "weight";
    private static final String INAPP_WIDGET_REAL_HEIGHT = "realHeight";
    private static final String INAPP_WIDGET_REAL_WIDTH = "realWidth";
    private static final String INAPP_WIDGET_TEXT_PROPERTY_SIZE = "size";
    private static final String INAPP_WIDGET_TYPE_BUTTON = "button";
    private static final String INAPP_WIDGET_TYPE_BUTTON_ARRAY = "buttonarray";
    private static final String INAPP_WIDGET_TYPE_CLOSE = "closebutton";
    private static final String INAPP_WIDGET_TYPE_CONTAINER = "container";
    private static final String INAPP_WIDGET_TYPE_IMAGE = "image";
    private static final String INAPP_WIDGET_TYPE_RATING = "rating";
    private static final String INAPP_WIDGET_TYPE_TEXT = "text";
    private static final String INAPP_WIDGET_TYPE_VIEW = "view";
    public static final int INAPP_WRAPPER_ID = 20002;
    static final String IN_APP_ANIMANTION_FADE_IN = "fade_in";
    static final String IN_APP_ANIMANTION_FADE_OUT = "fade_out";
    static final String IN_APP_ANIMANTION_SLIDE_DOWN = "slide_down";
    static final String IN_APP_ANIMANTION_SLIDE_LEFT = "slide_left";
    static final String IN_APP_ANIMANTION_SLIDE_RIGHT = "slide_right";
    static final String IN_APP_ANIMANTION_SLIDE_UP = "slide_up";
    static final String IN_APP_ANIMANTION_TYPE_ENTRY = "entry";
    static final String IN_APP_ANIMANTION_TYPE_EXIT = "exit";
    public static ViewEngine _INSTANCE;
    private static final Object superLock = new Object();
    private final String[] USED_FRESCO_CLASSES = new String[]{"com.facebook.drawee.backends.pipeline.Fresco", "com.facebook.drawee.interfaces.DraweeController", "com.facebook.drawee.view.SimpleDraweeView"};
    private Context appContext;
    private int containerBorderWeight;
    private boolean isLand;
    private boolean isTablet;
    private Activity mActivity;
    private InAppMessage mInAppMessage;
    int[] margin = new int[]{0, 0, 0, 0};
    private WeakHashMap<String, Typeface> sTypefaceCache = new WeakHashMap();

    private ViewEngine(Context context) {
        this.appContext = context;
    }

    public static ViewEngine getInstance(Context context) {
        synchronized (superLock) {
            if (_INSTANCE == null) {
                _INSTANCE = new ViewEngine(context);
            }
        }
        return _INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x011e A:{Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8, all -> 0x0022 }} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x011a A:{Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8, all -> 0x0022 }} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x011a A:{Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8, all -> 0x0022 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x011e A:{Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8, all -> 0x0022 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x011e A:{Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8, all -> 0x0022 }} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x011a A:{Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8, all -> 0x0022 }} */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x0006, B:19:0x00b8] */
    /* JADX WARNING: Missing block: B:32:0x00d8, code skipped:
            r14 = e;
     */
    /* JADX WARNING: Missing block: B:35:0x00ee, code skipped:
            r14 = e;
     */
    public android.widget.ImageView parseToCreateCloseButton(org.json.JSONObject r12, int[] r13, int[] r14, float r15) throws java.lang.Exception {
        /*
        r11 = this;
        r0 = superLock;
        monitor-enter(r0);
        r1 = 0;
        if (r12 != 0) goto L_0x0025;
    L_0x0006:
        r12 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0022 }
        r12.<init>();	 Catch:{ all -> 0x0022 }
        r13 = "ViewEngine: createWidget : widgetData is null, Campaign Id";
        r12.append(r13);	 Catch:{ all -> 0x0022 }
        r13 = r11.mInAppMessage;	 Catch:{ all -> 0x0022 }
        r13 = r13.rules;	 Catch:{ all -> 0x0022 }
        r13 = r13.campaignId;	 Catch:{ all -> 0x0022 }
        r12.append(r13);	 Catch:{ all -> 0x0022 }
        r12 = r12.toString();	 Catch:{ all -> 0x0022 }
        com.moengage.core.Logger.f(r12);	 Catch:{ all -> 0x0022 }
        monitor-exit(r0);	 Catch:{ all -> 0x0022 }
        return r1;
    L_0x0022:
        r12 = move-exception;
        goto L_0x013e;
    L_0x0025:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0022 }
        r2.<init>();	 Catch:{ all -> 0x0022 }
        r3 = "ViewEngine: parseToCreateCloseButton :-----> {";
        r2.append(r3);	 Catch:{ all -> 0x0022 }
        r3 = r12.toString();	 Catch:{ all -> 0x0022 }
        r2.append(r3);	 Catch:{ all -> 0x0022 }
        r3 = "}";
        r2.append(r3);	 Catch:{ all -> 0x0022 }
        r2 = r2.toString();	 Catch:{ all -> 0x0022 }
        com.moengage.core.Logger.v(r2);	 Catch:{ all -> 0x0022 }
        r2 = new android.widget.ImageView;	 Catch:{ all -> 0x0022 }
        r3 = r11.appContext;	 Catch:{ all -> 0x0022 }
        r2.<init>(r3);	 Catch:{ all -> 0x0022 }
        r3 = "id";
        r3 = r12.has(r3);	 Catch:{ all -> 0x0022 }
        if (r3 == 0) goto L_0x005d;
    L_0x0051:
        r3 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r4 = "id";
        r4 = r12.getInt(r4);	 Catch:{ all -> 0x0022 }
        r3 = r3 + r4;
        r2.setId(r3);	 Catch:{ all -> 0x0022 }
    L_0x005d:
        r3 = 1109917696; // 0x42280000 float:42.0 double:5.483722033E-315;
        r3 = r3 * r15;
        r3 = (int) r3;	 Catch:{ all -> 0x0022 }
        r4 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r4 = r4 * r15;
        r4 = (int) r4;	 Catch:{ all -> 0x0022 }
        r5 = 2;
        r6 = new int[r5];	 Catch:{ all -> 0x0022 }
        r7 = 0;
        r6[r7] = r3;	 Catch:{ all -> 0x0022 }
        r8 = 1;
        r6[r8] = r3;	 Catch:{ all -> 0x0022 }
        r9 = 4;
        r10 = new int[r9];	 Catch:{ all -> 0x0022 }
        r10 = {0, 0, 0, 0};	 Catch:{ all -> 0x0022 }
        r9 = new int[r9];	 Catch:{ all -> 0x0022 }
        r9 = {0, 0, 0, 0};	 Catch:{ all -> 0x0022 }
        r9 = "layout";
        r9 = r12.has(r9);	 Catch:{ all -> 0x0022 }
        if (r9 == 0) goto L_0x008e;
    L_0x0081:
        r9 = "layout";
        r9 = r12.getJSONObject(r9);	 Catch:{ all -> 0x0022 }
        r11.getMargin(r9, r13, r14, r7);	 Catch:{ all -> 0x0022 }
        r10 = r11.getPadding(r9, r13, r14, r7);	 Catch:{ all -> 0x0022 }
    L_0x008e:
        r13 = new android.widget.RelativeLayout$LayoutParams;	 Catch:{ all -> 0x0022 }
        r14 = r6[r7];	 Catch:{ all -> 0x0022 }
        r9 = r6[r8];	 Catch:{ all -> 0x0022 }
        r13.<init>(r14, r9);	 Catch:{ all -> 0x0022 }
        r2.setLayoutParams(r13);	 Catch:{ all -> 0x0022 }
        r13 = r10[r7];	 Catch:{ all -> 0x0022 }
        r13 = r13 + r4;
        r14 = r10[r8];	 Catch:{ all -> 0x0022 }
        r14 = r14 + r4;
        r5 = r10[r5];	 Catch:{ all -> 0x0022 }
        r5 = r5 + r4;
        r9 = 3;
        r9 = r10[r9];	 Catch:{ all -> 0x0022 }
        r9 = r9 + r4;
        r2.setPadding(r13, r14, r5, r9);	 Catch:{ all -> 0x0022 }
        r13 = "content";
        r13 = r12.has(r13);	 Catch:{ all -> 0x0022 }
        if (r13 == 0) goto L_0x0118;
    L_0x00b2:
        r13 = "content";
        r13 = r12.getString(r13);	 Catch:{ all -> 0x0022 }
        r14 = r11.appContext;	 Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8 }
        r13 = r11.downloadImageBitmap(r13, r14);	 Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8 }
        if (r13 != 0) goto L_0x00ce;
    L_0x00c0:
        r14 = new java.lang.Exception;	 Catch:{ Exception -> 0x00cb, OutOfMemoryError -> 0x00c8 }
        r1 = "Failed to set image";
        r14.<init>(r1);	 Catch:{ Exception -> 0x00cb, OutOfMemoryError -> 0x00c8 }
        throw r14;	 Catch:{ Exception -> 0x00cb, OutOfMemoryError -> 0x00c8 }
    L_0x00c8:
        r14 = move-exception;
        r1 = r13;
        goto L_0x00d9;
    L_0x00cb:
        r14 = move-exception;
        r1 = r13;
        goto L_0x00ef;
    L_0x00ce:
        r1 = android.graphics.Bitmap.createScaledBitmap(r13, r3, r3, r8);	 Catch:{ Exception -> 0x00cb, OutOfMemoryError -> 0x00c8 }
        r13 = "ViewEngine: setContentImage: setting content Image";
        com.moengage.core.Logger.v(r13);	 Catch:{ Exception -> 0x00ee, OutOfMemoryError -> 0x00d8 }
        goto L_0x0118;
    L_0x00d8:
        r14 = move-exception;
    L_0x00d9:
        r13 = "ViewEngine: parseToCreateCloseButton";
        com.moengage.core.Logger.f(r13, r14);	 Catch:{ all -> 0x0022 }
        r13 = com.moengage.inapp.MoEInAppFailureLogger.getInstance();	 Catch:{ all -> 0x0022 }
        r14 = r11.mInAppMessage;	 Catch:{ all -> 0x0022 }
        r14 = r14.rules;	 Catch:{ all -> 0x0022 }
        r14 = r14.campaignId;	 Catch:{ all -> 0x0022 }
        r3 = com.moengage.inapp.MoEInAppFailureLogger.FAILURE_REASON_CLOSE_BUTTON_DOWNLOAD_FAILURE;	 Catch:{ all -> 0x0022 }
        r13.updateCampaignFailureCounter(r14, r3);	 Catch:{ all -> 0x0022 }
        goto L_0x0118;
    L_0x00ee:
        r14 = move-exception;
    L_0x00ef:
        r13 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0022 }
        r13.<init>();	 Catch:{ all -> 0x0022 }
        r3 = "ViewEngine: setContentImage: Campaign Id";
        r13.append(r3);	 Catch:{ all -> 0x0022 }
        r3 = r11.mInAppMessage;	 Catch:{ all -> 0x0022 }
        r3 = r3.rules;	 Catch:{ all -> 0x0022 }
        r3 = r3.campaignId;	 Catch:{ all -> 0x0022 }
        r13.append(r3);	 Catch:{ all -> 0x0022 }
        r13 = r13.toString();	 Catch:{ all -> 0x0022 }
        com.moengage.core.Logger.f(r13, r14);	 Catch:{ all -> 0x0022 }
        r13 = com.moengage.inapp.MoEInAppFailureLogger.getInstance();	 Catch:{ all -> 0x0022 }
        r14 = r11.mInAppMessage;	 Catch:{ all -> 0x0022 }
        r14 = r14.rules;	 Catch:{ all -> 0x0022 }
        r14 = r14.campaignId;	 Catch:{ all -> 0x0022 }
        r3 = com.moengage.inapp.MoEInAppFailureLogger.FAILURE_REASON_CLOSE_BUTTON_DOWNLOAD_FAILURE;	 Catch:{ all -> 0x0022 }
        r13.updateCampaignFailureCounter(r14, r3);	 Catch:{ all -> 0x0022 }
    L_0x0118:
        if (r1 == 0) goto L_0x011e;
    L_0x011a:
        r2.setImageBitmap(r1);	 Catch:{ all -> 0x0022 }
        goto L_0x012d;
    L_0x011e:
        r13 = r11.appContext;	 Catch:{ all -> 0x0022 }
        r13 = r13.getResources();	 Catch:{ all -> 0x0022 }
        r14 = com.moengage.inapp.R.drawable.moe_close;	 Catch:{ all -> 0x0022 }
        r13 = android.graphics.BitmapFactory.decodeResource(r13, r14);	 Catch:{ all -> 0x0022 }
        r2.setImageBitmap(r13);	 Catch:{ all -> 0x0022 }
    L_0x012d:
        r13 = r11.appContext;	 Catch:{ all -> 0x0022 }
        r14 = r6[r7];	 Catch:{ all -> 0x0022 }
        r9 = r6[r8];	 Catch:{ all -> 0x0022 }
        r3 = r11;
        r4 = r2;
        r5 = r12;
        r6 = r13;
        r7 = r15;
        r8 = r14;
        r3.styleWidgetBackground(r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x0022 }
        monitor-exit(r0);	 Catch:{ all -> 0x0022 }
        return r2;
    L_0x013e:
        monitor-exit(r0);	 Catch:{ all -> 0x0022 }
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.inapp.ViewEngine.parseToCreateCloseButton(org.json.JSONObject, int[], int[], float):android.widget.ImageView");
    }

    /* JADX WARNING: Removed duplicated region for block: B:95:0x026b A:{Catch:{ all -> 0x0015 }} */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0232 A:{Catch:{ all -> 0x0015 }} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0250 A:{Catch:{ all -> 0x0015 }} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x026b A:{Catch:{ all -> 0x0015 }} */
    /* JADX WARNING: Missing block: B:99:0x0282, code skipped:
            return r14;
     */
    public android.view.View parseToCreateView(org.json.JSONObject r18, int[] r19, int[] r20, float r21) throws java.lang.Exception {
        /*
        r17 = this;
        r8 = r17;
        r9 = r18;
        r1 = r19;
        r2 = r20;
        r10 = superLock;
        monitor-enter(r10);
        r3 = 0;
        if (r9 != 0) goto L_0x0019;
    L_0x000e:
        r1 = "ViewEngine: createWidget : widgetData is null";
        com.moengage.core.Logger.v(r1);	 Catch:{ all -> 0x0015 }
        monitor-exit(r10);	 Catch:{ all -> 0x0015 }
        return r3;
    L_0x0015:
        r0 = move-exception;
        r1 = r0;
        goto L_0x02c1;
    L_0x0019:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0015 }
        r4.<init>();	 Catch:{ all -> 0x0015 }
        r5 = "ViewEngine: createWidget :-----> {";
        r4.append(r5);	 Catch:{ all -> 0x0015 }
        r5 = r18.toString();	 Catch:{ all -> 0x0015 }
        r4.append(r5);	 Catch:{ all -> 0x0015 }
        r5 = "}";
        r4.append(r5);	 Catch:{ all -> 0x0015 }
        r4 = r4.toString();	 Catch:{ all -> 0x0015 }
        com.moengage.core.Logger.v(r4);	 Catch:{ all -> 0x0015 }
        r4 = "type";
        r4 = r9.has(r4);	 Catch:{ all -> 0x0015 }
        if (r4 != 0) goto L_0x005a;
    L_0x003e:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0015 }
        r1.<init>();	 Catch:{ all -> 0x0015 }
        r2 = "ViewEngine: createWidget : no widget type available, Campaign Id";
        r1.append(r2);	 Catch:{ all -> 0x0015 }
        r2 = r8.mInAppMessage;	 Catch:{ all -> 0x0015 }
        r2 = r2.rules;	 Catch:{ all -> 0x0015 }
        r2 = r2.campaignId;	 Catch:{ all -> 0x0015 }
        r1.append(r2);	 Catch:{ all -> 0x0015 }
        r1 = r1.toString();	 Catch:{ all -> 0x0015 }
        com.moengage.core.Logger.f(r1);	 Catch:{ all -> 0x0015 }
        monitor-exit(r10);	 Catch:{ all -> 0x0015 }
        return r3;
    L_0x005a:
        r3 = "type";
        r11 = r9.getString(r3);	 Catch:{ all -> 0x0015 }
        r3 = "image";
        r3 = r3.equals(r11);	 Catch:{ all -> 0x0015 }
        r4 = 4;
        r5 = 2;
        if (r3 == 0) goto L_0x0096;
    L_0x006a:
        r3 = r17.isGIF(r18);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x0086;
    L_0x0070:
        r3 = r17.isFrescoSupported();	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x0086;
    L_0x0076:
        r3 = new com.facebook.drawee.view.SimpleDraweeView;	 Catch:{ all -> 0x0015 }
        r6 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r3.<init>(r6);	 Catch:{ all -> 0x0015 }
        r6 = "ViewEngine: createWidget : widget type is GIF image ";
        com.moengage.core.Logger.v(r6);	 Catch:{ all -> 0x0015 }
        r14 = r3;
        r15 = 6;
        goto L_0x011e;
    L_0x0086:
        r3 = new android.widget.ImageView;	 Catch:{ all -> 0x0015 }
        r6 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r3.<init>(r6);	 Catch:{ all -> 0x0015 }
        r6 = "ViewEngine: createWidget : widget type is image ";
        com.moengage.core.Logger.v(r6);	 Catch:{ all -> 0x0015 }
        r14 = r3;
        r15 = 3;
        goto L_0x011e;
    L_0x0096:
        r3 = "text";
        r3 = r3.equals(r11);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x00ae;
    L_0x009e:
        r3 = new android.widget.TextView;	 Catch:{ all -> 0x0015 }
        r6 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r3.<init>(r6);	 Catch:{ all -> 0x0015 }
        r6 = "ViewEngine: createWidget : widget type is text ";
        com.moengage.core.Logger.v(r6);	 Catch:{ all -> 0x0015 }
        r14 = r3;
        r15 = r5;
        goto L_0x011e;
    L_0x00ae:
        r3 = "button";
        r3 = r3.equals(r11);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x00c5;
    L_0x00b6:
        r3 = new android.widget.Button;	 Catch:{ all -> 0x0015 }
        r6 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r3.<init>(r6);	 Catch:{ all -> 0x0015 }
        r6 = "ViewEngine: createWidget : widget type is button ";
        com.moengage.core.Logger.v(r6);	 Catch:{ all -> 0x0015 }
        r14 = r3;
        r15 = r4;
        goto L_0x011e;
    L_0x00c5:
        r3 = "view";
        r3 = r3.equals(r11);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x00dd;
    L_0x00cd:
        r3 = new android.view.View;	 Catch:{ all -> 0x0015 }
        r6 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r3.<init>(r6);	 Catch:{ all -> 0x0015 }
        r6 = "ViewEngine: createWidget : widget type is view ";
        com.moengage.core.Logger.v(r6);	 Catch:{ all -> 0x0015 }
        r6 = 5;
    L_0x00da:
        r14 = r3;
        r15 = r6;
        goto L_0x011e;
    L_0x00dd:
        r3 = "closebutton";
        r3 = r3.equals(r11);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x00f3;
    L_0x00e5:
        r3 = new android.widget.ImageView;	 Catch:{ all -> 0x0015 }
        r6 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r3.<init>(r6);	 Catch:{ all -> 0x0015 }
        r6 = "ViewEngine: createWidget : widget type is close button ";
        com.moengage.core.Logger.v(r6);	 Catch:{ all -> 0x0015 }
        r6 = 7;
        goto L_0x00da;
    L_0x00f3:
        r3 = "rating";
        r3 = r3.equals(r11);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x010a;
    L_0x00fb:
        r3 = new com.moengage.widgets.MoERatingBar;	 Catch:{ all -> 0x0015 }
        r6 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r3.<init>(r6);	 Catch:{ all -> 0x0015 }
        r6 = "ViewEngine: createWidget : widget type is rating ";
        com.moengage.core.Logger.v(r6);	 Catch:{ all -> 0x0015 }
        r6 = 8;
        goto L_0x00da;
    L_0x010a:
        r3 = "buttonarray";
        r3 = r3.equals(r11);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x0283;
    L_0x0112:
        r3 = r17.parseToCreateButtonArray(r18, r19, r20, r21);	 Catch:{ all -> 0x0015 }
        r6 = "ViewEngine: createWidget : widget type is button array ";
        com.moengage.core.Logger.v(r6);	 Catch:{ all -> 0x0015 }
        r6 = 9;
        goto L_0x00da;
    L_0x011e:
        r3 = "id";
        r3 = r9.has(r3);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x0132;
    L_0x0126:
        r3 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r6 = "id";
        r6 = r9.getInt(r6);	 Catch:{ all -> 0x0015 }
        r3 = r3 + r6;
        r14.setId(r3);	 Catch:{ all -> 0x0015 }
    L_0x0132:
        r3 = new int[r5];	 Catch:{ all -> 0x0015 }
        r3 = {-2, -2};	 Catch:{ all -> 0x0015 }
        r6 = new int[r4];	 Catch:{ all -> 0x0015 }
        r6 = {0, 0, 0, 0};	 Catch:{ all -> 0x0015 }
        r7 = new int[r4];	 Catch:{ all -> 0x0015 }
        r7 = {0, 0, 0, 0};	 Catch:{ all -> 0x0015 }
        r4 = "layout";
        r4 = r9.has(r4);	 Catch:{ all -> 0x0015 }
        r13 = 1;
        r12 = 0;
        if (r4 == 0) goto L_0x01a1;
    L_0x014b:
        r3 = "layout";
        r3 = r9.getJSONObject(r3);	 Catch:{ all -> 0x0015 }
        r4 = new int[r5];	 Catch:{ all -> 0x0015 }
        r6 = r2[r12];	 Catch:{ all -> 0x0015 }
        r4[r12] = r6;	 Catch:{ all -> 0x0015 }
        r6 = r1[r13];	 Catch:{ all -> 0x0015 }
        r4[r13] = r6;	 Catch:{ all -> 0x0015 }
        r4 = r8.getViewDimensions(r3, r1, r4, r12);	 Catch:{ all -> 0x0015 }
        r7 = r8.getMargin(r3, r1, r2, r12);	 Catch:{ all -> 0x0015 }
        r6 = r8.getPadding(r3, r1, r2, r12);	 Catch:{ all -> 0x0015 }
        r1 = 6;
        if (r15 != r1) goto L_0x019a;
    L_0x016a:
        r1 = "realHeight";
        r1 = r3.has(r1);	 Catch:{ all -> 0x0015 }
        r2 = 0;
        if (r1 == 0) goto L_0x017d;
    L_0x0173:
        r1 = "realHeight";
        r16 = r6;
        r5 = r3.getDouble(r1);	 Catch:{ all -> 0x0015 }
        r1 = (float) r5;	 Catch:{ all -> 0x0015 }
        goto L_0x0180;
    L_0x017d:
        r16 = r6;
        r1 = r2;
    L_0x0180:
        r5 = "realWidth";
        r5 = r3.has(r5);	 Catch:{ all -> 0x0015 }
        if (r5 == 0) goto L_0x018f;
    L_0x0188:
        r2 = "realWidth";
        r2 = r3.getDouble(r2);	 Catch:{ all -> 0x0015 }
        r2 = (float) r2;	 Catch:{ all -> 0x0015 }
    L_0x018f:
        r3 = r4[r12];	 Catch:{ all -> 0x0015 }
        r3 = (float) r3;	 Catch:{ all -> 0x0015 }
        r3 = r3 * r1;
        r1 = (int) r3;	 Catch:{ all -> 0x0015 }
        r1 = (float) r1;	 Catch:{ all -> 0x0015 }
        r1 = r1 / r2;
        r1 = (int) r1;	 Catch:{ all -> 0x0015 }
        r4[r13] = r1;	 Catch:{ all -> 0x0015 }
        goto L_0x019c;
    L_0x019a:
        r16 = r6;
    L_0x019c:
        r6 = r16;
        r16 = r4;
        goto L_0x01a3;
    L_0x01a1:
        r16 = r3;
    L_0x01a3:
        r1 = "rating";
        r1 = r1.equals(r11);	 Catch:{ all -> 0x0015 }
        if (r1 == 0) goto L_0x01b2;
    L_0x01ab:
        r1 = new android.widget.LinearLayout$LayoutParams;	 Catch:{ all -> 0x0015 }
        r2 = -2;
        r1.<init>(r2, r2);	 Catch:{ all -> 0x0015 }
        goto L_0x01bb;
    L_0x01b2:
        r1 = new android.widget.LinearLayout$LayoutParams;	 Catch:{ all -> 0x0015 }
        r2 = r16[r12];	 Catch:{ all -> 0x0015 }
        r3 = r16[r13];	 Catch:{ all -> 0x0015 }
        r1.<init>(r2, r3);	 Catch:{ all -> 0x0015 }
    L_0x01bb:
        r2 = r7[r12];	 Catch:{ all -> 0x0015 }
        r3 = r7[r13];	 Catch:{ all -> 0x0015 }
        r4 = r8.containerBorderWeight;	 Catch:{ all -> 0x0015 }
        r3 = r3 + r4;
        r4 = 2;
        r5 = r7[r4];	 Catch:{ all -> 0x0015 }
        r4 = 3;
        r7 = r7[r4];	 Catch:{ all -> 0x0015 }
        r4 = r8.containerBorderWeight;	 Catch:{ all -> 0x0015 }
        r7 = r7 + r4;
        r1.setMargins(r2, r3, r5, r7);	 Catch:{ all -> 0x0015 }
        r1.gravity = r13;	 Catch:{ all -> 0x0015 }
        r14.setLayoutParams(r1);	 Catch:{ all -> 0x0015 }
        r1 = r6[r12];	 Catch:{ all -> 0x0015 }
        r2 = r6[r13];	 Catch:{ all -> 0x0015 }
        r3 = 2;
        r4 = r6[r3];	 Catch:{ all -> 0x0015 }
        r5 = 3;
        r6 = r6[r5];	 Catch:{ all -> 0x0015 }
        r14.setPadding(r1, r2, r4, r6);	 Catch:{ all -> 0x0015 }
        if (r15 == r3) goto L_0x0220;
    L_0x01e2:
        r1 = 4;
        if (r15 != r1) goto L_0x01e6;
    L_0x01e5:
        goto L_0x0220;
    L_0x01e6:
        if (r15 != r5) goto L_0x0203;
    L_0x01e8:
        r2 = r14;
        r2 = (android.widget.ImageView) r2;	 Catch:{ all -> 0x0015 }
        r4 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r5 = r16[r12];	 Catch:{ all -> 0x0015 }
        r6 = r16[r13];	 Catch:{ all -> 0x0015 }
        r1 = r8;
        r3 = r9;
        r7 = r21;
        r1 = r1.setContentImage(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0015 }
        if (r1 != 0) goto L_0x021d;
    L_0x01fb:
        r1 = new java.lang.Exception;	 Catch:{ all -> 0x0015 }
        r2 = "Image Download failed";
        r1.<init>(r2);	 Catch:{ all -> 0x0015 }
        throw r1;	 Catch:{ all -> 0x0015 }
    L_0x0203:
        r1 = 6;
        if (r15 != r1) goto L_0x021d;
    L_0x0206:
        r1 = r17.isFrescoSupported();	 Catch:{ all -> 0x0015 }
        if (r1 == 0) goto L_0x021d;
    L_0x020c:
        r1 = r17.getImageUrl(r18);	 Catch:{ all -> 0x0015 }
        r2 = r14;
        r2 = (com.facebook.drawee.view.SimpleDraweeView) r2;	 Catch:{ all -> 0x0015 }
        r3 = r16[r12];	 Catch:{ all -> 0x0015 }
        r3 = (float) r3;	 Catch:{ all -> 0x0015 }
        r4 = r16[r13];	 Catch:{ all -> 0x0015 }
        r4 = (float) r4;	 Catch:{ all -> 0x0015 }
        r3 = r3 / r4;
        r8.setGIFContent(r1, r2, r3);	 Catch:{ all -> 0x0015 }
    L_0x021d:
        r5 = r21;
        goto L_0x022a;
    L_0x0220:
        r1 = r14;
        r1 = (android.widget.TextView) r1;	 Catch:{ all -> 0x0015 }
        r2 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r5 = r21;
        r8.setContentText(r1, r9, r2, r5);	 Catch:{ all -> 0x0015 }
    L_0x022a:
        r1 = "properties";
        r1 = r9.has(r1);	 Catch:{ all -> 0x0015 }
        if (r1 == 0) goto L_0x024c;
    L_0x0232:
        r1 = "properties";
        r1 = r9.getJSONObject(r1);	 Catch:{ all -> 0x0015 }
        r2 = "color";
        r2 = r1.has(r2);	 Catch:{ all -> 0x0015 }
        if (r2 == 0) goto L_0x024c;
    L_0x0240:
        r2 = "color";
        r1 = r1.getString(r2);	 Catch:{ all -> 0x0015 }
        r1 = android.graphics.Color.parseColor(r1);	 Catch:{ all -> 0x0015 }
        r7 = r1;
        goto L_0x024d;
    L_0x024c:
        r7 = r12;
    L_0x024d:
        r1 = 3;
        if (r15 == r1) goto L_0x0262;
    L_0x0250:
        r1 = 6;
        if (r15 == r1) goto L_0x0262;
    L_0x0253:
        r4 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r6 = r16[r12];	 Catch:{ all -> 0x0015 }
        r13 = r16[r13];	 Catch:{ all -> 0x0015 }
        r1 = r8;
        r2 = r14;
        r3 = r9;
        r9 = r7;
        r7 = r13;
        r1.styleWidgetBackground(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0015 }
        goto L_0x0263;
    L_0x0262:
        r9 = r7;
    L_0x0263:
        r1 = "rating";
        r1 = r1.equals(r11);	 Catch:{ all -> 0x0015 }
        if (r1 == 0) goto L_0x0281;
    L_0x026b:
        r1 = r14;
        r1 = (com.moengage.widgets.MoERatingBar) r1;	 Catch:{ all -> 0x0015 }
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r1.setStepSize(r2);	 Catch:{ all -> 0x0015 }
        r1 = r14;
        r1 = (com.moengage.widgets.MoERatingBar) r1;	 Catch:{ all -> 0x0015 }
        r1.setIsIndicator(r12);	 Catch:{ all -> 0x0015 }
        if (r9 == 0) goto L_0x0281;
    L_0x027b:
        r1 = r14;
        r1 = (com.moengage.widgets.MoERatingBar) r1;	 Catch:{ all -> 0x0015 }
        r1.setColor(r9);	 Catch:{ all -> 0x0015 }
    L_0x0281:
        monitor-exit(r10);	 Catch:{ all -> 0x0015 }
        return r14;
    L_0x0283:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0015 }
        r1.<init>();	 Catch:{ all -> 0x0015 }
        r2 = "ViewEngine: createWidget : widget type is UNKNOWN ^^^^^^ ";
        r1.append(r2);	 Catch:{ all -> 0x0015 }
        r1.append(r11);	 Catch:{ all -> 0x0015 }
        r2 = " ,Campaign id";
        r1.append(r2);	 Catch:{ all -> 0x0015 }
        r2 = r8.mInAppMessage;	 Catch:{ all -> 0x0015 }
        r2 = r2.rules;	 Catch:{ all -> 0x0015 }
        r2 = r2.campaignId;	 Catch:{ all -> 0x0015 }
        r1.append(r2);	 Catch:{ all -> 0x0015 }
        r1 = r1.toString();	 Catch:{ all -> 0x0015 }
        com.moengage.core.Logger.f(r1);	 Catch:{ all -> 0x0015 }
        r1 = new java.lang.Exception;	 Catch:{ all -> 0x0015 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0015 }
        r2.<init>();	 Catch:{ all -> 0x0015 }
        r3 = "Unknown widget ";
        r2.append(r3);	 Catch:{ all -> 0x0015 }
        r2.append(r11);	 Catch:{ all -> 0x0015 }
        r3 = "! InApp should not be created";
        r2.append(r3);	 Catch:{ all -> 0x0015 }
        r2 = r2.toString();	 Catch:{ all -> 0x0015 }
        r1.<init>(r2);	 Catch:{ all -> 0x0015 }
        throw r1;	 Catch:{ all -> 0x0015 }
    L_0x02c1:
        monitor-exit(r10);	 Catch:{ all -> 0x0015 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.inapp.ViewEngine.parseToCreateView(org.json.JSONObject, int[], int[], float):android.view.View");
    }

    /* JADX WARNING: Missing block: B:23:0x00fd, code skipped:
            return r13;
     */
    public android.view.View parseToCreateButtonArray(org.json.JSONObject r23, int[] r24, int[] r25, float r26) throws java.lang.Exception {
        /*
        r22 = this;
        r8 = r22;
        r1 = r23;
        r9 = r24;
        r10 = r25;
        r11 = superLock;
        monitor-enter(r11);
        r2 = 0;
        if (r1 != 0) goto L_0x0019;
    L_0x000e:
        r1 = "ViewEngine: parseToCreateButtonArray : widgetData is null";
        com.moengage.core.Logger.v(r1);	 Catch:{ all -> 0x0015 }
        monitor-exit(r11);	 Catch:{ all -> 0x0015 }
        return r2;
    L_0x0015:
        r0 = move-exception;
        r1 = r0;
        goto L_0x00fe;
    L_0x0019:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0015 }
        r3.<init>();	 Catch:{ all -> 0x0015 }
        r4 = "ViewEngine: parseToCreateButtonArray : {";
        r3.append(r4);	 Catch:{ all -> 0x0015 }
        r4 = r23.toString();	 Catch:{ all -> 0x0015 }
        r3.append(r4);	 Catch:{ all -> 0x0015 }
        r4 = "}";
        r3.append(r4);	 Catch:{ all -> 0x0015 }
        r3 = r3.toString();	 Catch:{ all -> 0x0015 }
        com.moengage.core.Logger.v(r3);	 Catch:{ all -> 0x0015 }
        r3 = "buttonarray";
        r3 = r1.has(r3);	 Catch:{ all -> 0x0015 }
        if (r3 == 0) goto L_0x0044;
    L_0x003e:
        r2 = "buttonarray";
        r2 = r1.getJSONArray(r2);	 Catch:{ all -> 0x0015 }
    L_0x0044:
        r12 = r2;
        r13 = new android.widget.LinearLayout;	 Catch:{ all -> 0x0015 }
        r1 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r13.<init>(r1);	 Catch:{ all -> 0x0015 }
        r14 = 0;
        r13.setOrientation(r14);	 Catch:{ all -> 0x0015 }
        if (r12 == 0) goto L_0x00fc;
    L_0x0052:
        r15 = r12.length();	 Catch:{ all -> 0x0015 }
        r7 = r14;
    L_0x0057:
        if (r7 >= r15) goto L_0x00fc;
    L_0x0059:
        r6 = r12.getJSONObject(r7);	 Catch:{ all -> 0x0015 }
        r5 = new android.widget.Button;	 Catch:{ all -> 0x0015 }
        r1 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r5.<init>(r1);	 Catch:{ all -> 0x0015 }
        r1 = 2;
        r2 = new int[r1];	 Catch:{ all -> 0x0015 }
        r2 = {-2, -2};	 Catch:{ all -> 0x0015 }
        r3 = 4;
        r4 = new int[r3];	 Catch:{ all -> 0x0015 }
        r4 = {0, 0, 0, 0};	 Catch:{ all -> 0x0015 }
        r3 = new int[r3];	 Catch:{ all -> 0x0015 }
        r3 = {0, 0, 0, 0};	 Catch:{ all -> 0x0015 }
        r14 = "layout";
        r14 = r6.has(r14);	 Catch:{ all -> 0x0015 }
        r17 = 1;
        if (r14 == 0) goto L_0x009f;
    L_0x007f:
        r2 = "layout";
        r2 = r6.getJSONObject(r2);	 Catch:{ all -> 0x0015 }
        r3 = new int[r1];	 Catch:{ all -> 0x0015 }
        r4 = 0;
        r14 = r10[r4];	 Catch:{ all -> 0x0015 }
        r3[r4] = r14;	 Catch:{ all -> 0x0015 }
        r14 = r9[r17];	 Catch:{ all -> 0x0015 }
        r3[r17] = r14;	 Catch:{ all -> 0x0015 }
        r3 = r8.getViewDimensions(r2, r10, r3, r4);	 Catch:{ all -> 0x0015 }
        r14 = r8.getMargin(r2, r9, r10, r4);	 Catch:{ all -> 0x0015 }
        r2 = r8.getPadding(r2, r9, r10, r4);	 Catch:{ all -> 0x0015 }
        r4 = r2;
        r2 = r3;
        goto L_0x00a0;
    L_0x009f:
        r14 = r3;
    L_0x00a0:
        r3 = new android.widget.LinearLayout$LayoutParams;	 Catch:{ all -> 0x0015 }
        r16 = 0;
        r1 = r2[r16];	 Catch:{ all -> 0x0015 }
        r19 = r7;
        r7 = r2[r17];	 Catch:{ all -> 0x0015 }
        r3.<init>(r1, r7);	 Catch:{ all -> 0x0015 }
        r1 = r14[r16];	 Catch:{ all -> 0x0015 }
        r7 = r14[r17];	 Catch:{ all -> 0x0015 }
        r18 = 2;
        r9 = r14[r18];	 Catch:{ all -> 0x0015 }
        r20 = 3;
        r14 = r14[r20];	 Catch:{ all -> 0x0015 }
        r3.setMargins(r1, r7, r9, r14);	 Catch:{ all -> 0x0015 }
        r5.setLayoutParams(r3);	 Catch:{ all -> 0x0015 }
        r1 = 0;
        r3 = r4[r1];	 Catch:{ all -> 0x0015 }
        r1 = r4[r17];	 Catch:{ all -> 0x0015 }
        r7 = 2;
        r7 = r4[r7];	 Catch:{ all -> 0x0015 }
        r4 = r4[r20];	 Catch:{ all -> 0x0015 }
        r5.setPadding(r3, r1, r7, r4);	 Catch:{ all -> 0x0015 }
        r1 = r5;
        r1 = (android.widget.TextView) r1;	 Catch:{ all -> 0x0015 }
        r3 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r9 = r26;
        r8.setContentText(r1, r6, r3, r9);	 Catch:{ all -> 0x0015 }
        r4 = r8.appContext;	 Catch:{ all -> 0x0015 }
        r14 = 0;
        r7 = r2[r14];	 Catch:{ all -> 0x0015 }
        r16 = r2[r17];	 Catch:{ all -> 0x0015 }
        r1 = r8;
        r2 = r5;
        r3 = r6;
        r14 = r5;
        r5 = r9;
        r9 = r6;
        r6 = r7;
        r17 = r19;
        r7 = r16;
        r1.styleWidgetBackground(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0015 }
        r13.addView(r14);	 Catch:{ all -> 0x0015 }
        r1 = r8.mActivity;	 Catch:{ all -> 0x0015 }
        r2 = r8.mInAppMessage;	 Catch:{ all -> 0x0015 }
        r8.addAction(r9, r14, r1, r2);	 Catch:{ all -> 0x0015 }
        r7 = r17 + 1;
        r9 = r24;
        r14 = 0;
        goto L_0x0057;
    L_0x00fc:
        monitor-exit(r11);	 Catch:{ all -> 0x0015 }
        return r13;
    L_0x00fe:
        monitor-exit(r11);	 Catch:{ all -> 0x0015 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.inapp.ViewEngine.parseToCreateButtonArray(org.json.JSONObject, int[], int[], float):android.view.View");
    }

    private void setContentText(TextView textView, JSONObject jSONObject, Context context, float f) throws JSONException {
        if (jSONObject.has("content")) {
            String string = jSONObject.getString("content");
            if (!TextUtils.isEmpty(string)) {
                textView.setText(string);
                if (VERSION.SDK_INT >= 14) {
                    textView.setAllCaps(false);
                }
                styleTextView(textView, jSONObject, context, f);
            }
        }
    }

    public void setGIFContent(String str, SimpleDraweeView simpleDraweeView, float f) {
        if (simpleDraweeView != null) {
            AbstractDraweeController build = ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true)).setUri(Uri.parse(str)).build();
            simpleDraweeView.setAspectRatio(f);
            simpleDraweeView.setController(build);
        }
    }

    private boolean isGIF(JSONObject jSONObject) {
        try {
            if (jSONObject.has("content")) {
                String string = jSONObject.getString("content");
                if (!TextUtils.isEmpty(string) && (string.contains(".gif") || string.contains(".GIF"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ViewEngine: isGIF, Campaign id ");
            stringBuilder.append(this.mInAppMessage.rules.campaignId);
            Logger.f(stringBuilder.toString(), e);
        }
        return false;
    }

    private String getImageUrl(JSONObject jSONObject) throws JSONException {
        return jSONObject.has("content") ? jSONObject.getString("content") : null;
    }

    private boolean setContentImage(ImageView imageView, JSONObject jSONObject, Context context, int i, int i2, float f) throws Exception {
        if (jSONObject.has("content")) {
            try {
                Bitmap downloadImageBitmap = downloadImageBitmap(jSONObject.getString("content"), context);
                if (downloadImageBitmap == null) {
                    throw new Exception("Failed to set image");
                }
                i2 = (downloadImageBitmap.getHeight() * i) / downloadImageBitmap.getWidth();
                if (jSONObject.has("properties")) {
                    jSONObject = jSONObject.getJSONObject("properties");
                }
                if (downloadImageBitmap == null) {
                    throw new Exception("Failed to set image");
                }
                downloadImageBitmap = Bitmap.createScaledBitmap(downloadImageBitmap, i, i2, true);
                if (jSONObject.has(INAPP_WIDGET_PROP_BORDER)) {
                    int[] borderData = getBorderData(jSONObject.getJSONObject(INAPP_WIDGET_PROP_BORDER), this.appContext, f);
                    if (borderData[1] != 0) {
                        setViewBackground(imageView, getBitmapDrawable(this.appContext, addBorderToBitmap(downloadImageBitmap, borderData[0], borderData[1], borderData[2])));
                        return true;
                    }
                }
                Logger.v("ViewEngine: setContentImage: setting content Image");
                imageView.setImageBitmap(downloadImageBitmap);
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ViewEngine: setContentImage : Campaign Id");
                stringBuilder.append(this.mInAppMessage.rules.campaignId);
                Logger.f(stringBuilder.toString(), e);
                MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_IMAGE_DOWNLOAD_FAILURE);
                return false;
            } catch (OutOfMemoryError e2) {
                Logger.f("ViewEngine: setContentImage", e2);
                MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_IMAGE_DOWNLOAD_FAILURE);
                return false;
            }
        }
        return true;
    }

    private Bitmap scaleToDimension(Bitmap bitmap, int i) {
        if (bitmap == null || i <= 0) {
            return bitmap;
        }
        int round = Math.round((((float) bitmap.getHeight()) / ((float) bitmap.getWidth())) * ((float) i));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ViewEngine: scaleToDimension scaled height: ");
        stringBuilder.append(round);
        Logger.v(stringBuilder.toString());
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, round, false);
        bitmap.recycle();
        return createScaledBitmap;
    }

    private LinearLayout parseToCreateContainer(JSONObject jSONObject, int[] iArr, int[] iArr2, float f) {
        try {
            LayoutParams layoutParams;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ViewEngine: parseToCreateContainer --> ");
            stringBuilder.append(jSONObject.toString());
            stringBuilder.append(" \n with dimension: ");
            stringBuilder.append(iArr2[0]);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(iArr2[1]);
            Logger.v(stringBuilder.toString());
            LinearLayout linearLayout = new LinearLayout(this.appContext);
            linearLayout.setOrientation(1);
            if (jSONObject.has("id")) {
                linearLayout.setId(2000 + jSONObject.getInt("id"));
            }
            int[] iArr3 = new int[]{0, 0, 0, 0};
            if (this.mInAppMessage.rules.alignType != ALIGN_TYPE.EMBED) {
                layoutParams = new LayoutParams(iArr2[0], iArr2[1]);
            } else {
                layoutParams = new LayoutParams(iArr2[0], -2);
            }
            if (jSONObject.has("layout")) {
                jSONObject = jSONObject.getJSONObject("layout");
                this.margin = getMargin(jSONObject, iArr, iArr2, true);
                iArr3 = getPadding(jSONObject, iArr, iArr2, true);
            }
            this.margin[0] = (int) TypedValue.applyDimension(0, (float) this.margin[0], this.appContext.getResources().getDisplayMetrics());
            this.margin[1] = (int) TypedValue.applyDimension(0, (float) this.margin[1], this.appContext.getResources().getDisplayMetrics());
            this.margin[2] = (int) TypedValue.applyDimension(0, (float) this.margin[2], this.appContext.getResources().getDisplayMetrics());
            this.margin[3] = (int) TypedValue.applyDimension(0, (float) this.margin[3], this.appContext.getResources().getDisplayMetrics());
            layoutParams.setMargins(this.margin[0], this.margin[1], this.margin[2], this.margin[3]);
            linearLayout.setPadding(iArr3[0], iArr3[1], iArr3[2], iArr3[3]);
            linearLayout.setLayoutParams(layoutParams);
            return linearLayout;
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ViewEngine: parseToCreateContainer : campaignId ");
            stringBuilder2.append(this.mInAppMessage.rules.campaignId);
            Logger.f(stringBuilder2.toString(), e);
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE);
            return null;
        }
    }

    public View createInApp(Activity activity, InAppMessage inAppMessage) {
        Activity activity2 = activity;
        InAppMessage inAppMessage2 = inAppMessage;
        if (!(inAppMessage2 == null || activity2 == null)) {
            try {
                if (!TextUtils.isEmpty(inAppMessage2.content)) {
                    JSONObject jSONObject;
                    this.mInAppMessage = inAppMessage2;
                    this.mActivity = activity2;
                    DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
                    int[] iArr = new int[2];
                    this.isTablet = activity.getResources().getBoolean(R.bool.isTablet);
                    this.isLand = activity.getResources().getBoolean(R.bool.isLand);
                    activity.getWindow().getDecorView().findViewById(16908290).getRootView();
                    iArr[0] = displayMetrics.widthPixels;
                    iArr[1] = displayMetrics.heightPixels;
                    int[] iArr2 = new int[]{iArr[0], iArr[1]};
                    float f = displayMetrics.scaledDensity;
                    if (this.isLand || this.isTablet) {
                        iArr[0] = (int) (((double) iArr[0]) * 0.6d);
                        iArr[1] = (int) (((double) iArr[1]) * 0.6d);
                    }
                    JSONArray jSONArray = new JSONArray(inAppMessage2.content);
                    int length = jSONArray.length();
                    int[] iArr3 = new int[]{-2, -2};
                    RelativeLayout relativeLayout = new RelativeLayout(activity.getApplicationContext());
                    relativeLayout.setId(INAPP_WRAPPER_ID);
                    LayoutParams layoutParams = new LayoutParams(-2, -2);
                    layoutParams.addRule(14);
                    relativeLayout.setLayoutParams(layoutParams);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("View Engine : createInApp : viewableAreaDimens[0] : ");
                    stringBuilder.append(iArr[0]);
                    stringBuilder.append("viewableAreaDimens[1] : ");
                    stringBuilder.append(iArr[1]);
                    Logger.v(stringBuilder.toString());
                    int[] iArr4 = iArr3;
                    int i = 0;
                    View view = null;
                    View view2 = null;
                    Object obj = null;
                    JSONObject jSONObject2 = null;
                    JSONArray jSONArray2 = null;
                    while (i < length) {
                        jSONObject = jSONArray.getJSONObject(i);
                        JSONArray jSONArray3 = jSONArray;
                        String string = jSONObject.getString("type");
                        int i2 = length;
                        if (INAPP_WIDGET_TYPE_CONTAINER.equalsIgnoreCase(string)) {
                            int[] containerDimens = getContainerDimens(jSONObject, iArr);
                            view2 = parseToCreateContainer(jSONObject, iArr, containerDimens, f);
                            if (jSONObject.has("properties")) {
                                containerDimens = adjustContainerRef(containerDimens, f, jSONObject.getJSONObject("properties"), this.appContext);
                            }
                            setAnimationProperties(inAppMessage2, jSONObject);
                            addAction(jSONObject, view2, activity2, inAppMessage2);
                            iArr4 = containerDimens;
                            jSONArray2 = jSONObject.getJSONArray("widgets");
                            jSONObject2 = jSONObject;
                        } else if (INAPP_WIDGET_TYPE_CLOSE.equals(string) && jSONObject.has("layout")) {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("layout");
                            if (jSONObject3.has(INAPP_ALIGNMENT)) {
                                obj = jSONObject3.getString(INAPP_ALIGNMENT);
                            }
                            View parseToCreateCloseButton = parseToCreateCloseButton(jSONObject, iArr, iArr4, f);
                            if (parseToCreateCloseButton != null) {
                                addAction(jSONObject, parseToCreateCloseButton, activity2, inAppMessage2);
                            }
                            view = parseToCreateCloseButton;
                        }
                        i++;
                        jSONArray = jSONArray3;
                        length = i2;
                    }
                    if (view2 == null) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("ViewEngine: createInApp could not create container, Campaign Id : ");
                        stringBuilder2.append(this.mInAppMessage.rules.campaignId);
                        Logger.f(stringBuilder2.toString());
                        return null;
                    }
                    LayoutParams layoutParams2;
                    Animation loadAnimation;
                    if (view != null) {
                        if (inAppMessage2.rules.alignType == ALIGN_TYPE.EMBED || inAppMessage2.rules.alignType == ALIGN_TYPE.FULL) {
                            layoutParams2 = (LayoutParams) view.getLayoutParams();
                            if ("right".equals(obj)) {
                                layoutParams2.addRule(11, view2.getId());
                            } else if ("left".equals(obj)) {
                                layoutParams2.addRule(9, view2.getId());
                            }
                        } else {
                            layoutParams2 = (LayoutParams) view.getLayoutParams();
                            if ("right".equals(obj)) {
                                layoutParams2.addRule(7, relativeLayout.getId());
                                layoutParams2.rightMargin = (int) (((float) layoutParams2.rightMargin) + (((float) this.margin[2]) - (21.0f * f)));
                            }
                            if ("left".equals(obj)) {
                                layoutParams2.leftMargin = (int) (((float) layoutParams2.leftMargin) + (((float) this.margin[0]) - (21.0f * f)));
                            }
                            view.setLayoutParams(layoutParams2);
                            layoutParams2 = (LayoutParams) view2.getLayoutParams();
                            layoutParams2.topMargin = (int) (((float) layoutParams2.topMargin) + (21.0f * f));
                        }
                    }
                    jSONArray = jSONArray2;
                    if (jSONArray != null) {
                        i = jSONArray.length();
                        for (length = 0; length < i; length++) {
                            jSONObject = jSONArray.getJSONObject(length);
                            View parseToCreateView = parseToCreateView(jSONObject, iArr, iArr4, f);
                            view2.addView(parseToCreateView);
                            addAction(jSONObject, parseToCreateView, activity2, inAppMessage2);
                        }
                    } else {
                        Logger.v("ViewEngine: createInApp container has no content");
                    }
                    view2.measure(0, 0);
                    int measuredWidth = view2.getMeasuredWidth();
                    int measuredHeight = view2.getMeasuredHeight();
                    View view3 = view;
                    View view4 = view2;
                    int i3 = measuredWidth;
                    RelativeLayout relativeLayout2 = relativeLayout;
                    styleWidgetBackground(view2, jSONObject2, this.appContext, f, i3, measuredHeight);
                    if (inAppMessage2.rules.alignType != ALIGN_TYPE.FULL) {
                        if (inAppMessage2.rules.alignType != ALIGN_TYPE.EMBED) {
                            RelativeLayout relativeLayout3 = new RelativeLayout(activity.getApplicationContext());
                            relativeLayout3.setId(INAPP_WRAPPER_ID);
                            layoutParams2 = new LayoutParams(-2, -2);
                            layoutParams2.addRule(14);
                            relativeLayout3.setLayoutParams(layoutParams2);
                            relativeLayout2.addView(view4);
                            relativeLayout3.addView(relativeLayout2);
                            View relativeLayout4 = new RelativeLayout(activity.getApplicationContext());
                            relativeLayout4.setId(10001);
                            relativeLayout4.setGravity(17);
                            LayoutParams layoutParams3 = new LayoutParams(iArr2[0], iArr2[1]);
                            if (!TextUtils.isEmpty(inAppMessage2.dimStyle)) {
                                Logger.v("ViewEngine: generateInAppMessage : styling blur wrapper");
                                styleWidgetBackground(relativeLayout4, new JSONObject(inAppMessage2.dimStyle), this.appContext, f, iArr2[0], iArr2[1]);
                            }
                            view = view3;
                            if (view != null) {
                                relativeLayout3.addView(view);
                            }
                            relativeLayout4.setLayoutParams(layoutParams3);
                            relativeLayout4.addView(relativeLayout3);
                            relativeLayout4.setClickable(true);
                            if (inAppMessage2.rules.cancelable) {
                                handleBackPress(relativeLayout4);
                            }
                            if (inAppMessage2.rules.entryAnimation != 0) {
                                loadAnimation = AnimationUtils.loadAnimation(this.appContext, inAppMessage2.rules.entryAnimation);
                                loadAnimation.setFillAfter(true);
                                relativeLayout4.setAnimation(loadAnimation);
                            }
                            this.mInAppMessage.theComposedView = relativeLayout4;
                            return relativeLayout4;
                        }
                    }
                    view = view3;
                    view2 = view4;
                    if (inAppMessage2.rules.cancelable && inAppMessage2.rules.alignType == ALIGN_TYPE.FULL) {
                        handleBackPress(relativeLayout2);
                    }
                    view2.setClickable(true);
                    relativeLayout2.setId(INAPP_WRAPPER_ID);
                    relativeLayout2.addView(view2);
                    if (view != null) {
                        if (inAppMessage2.rules.alignType == ALIGN_TYPE.EMBED) {
                            layoutParams2 = (LayoutParams) view.getLayoutParams();
                            i = (int) (16.0f * f);
                            layoutParams2.height = i;
                            layoutParams2.width = i;
                            view.setLayoutParams(layoutParams2);
                        }
                        relativeLayout2.addView(view);
                    }
                    if (inAppMessage2.rules.entryAnimation != 0) {
                        loadAnimation = AnimationUtils.loadAnimation(this.appContext, inAppMessage2.rules.entryAnimation);
                        loadAnimation.setFillAfter(true);
                        relativeLayout2.setAnimation(loadAnimation);
                    }
                    return relativeLayout2;
                }
            } catch (Exception e) {
                Throwable th = e;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("ViewEngine: createInApp, Campaign Id ");
                stringBuilder3.append(this.mInAppMessage.rules.campaignId);
                Logger.f(stringBuilder3.toString(), th);
                MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE);
                return null;
            }
        }
        Logger.v("ViewEngine: createInApp: will not create In-App");
        return null;
    }

    private void setAnimationProperties(InAppMessage inAppMessage, JSONObject jSONObject) throws Exception {
        if (jSONObject.has(INAPP_WIDGET_LAYOUT_ANIMATION)) {
            jSONObject = jSONObject.getJSONObject(INAPP_WIDGET_LAYOUT_ANIMATION);
            if (jSONObject.has(IN_APP_ANIMANTION_TYPE_ENTRY)) {
                inAppMessage.rules.entryAnimation = setAnimationEntry(jSONObject.getString(IN_APP_ANIMANTION_TYPE_ENTRY));
            }
            if (jSONObject.has("exit")) {
                inAppMessage.rules.exitAnimation = setAnimationExit(jSONObject.getString("exit"));
            }
        }
    }

    private int setAnimationEntry(java.lang.String r4) {
        /*
        r3 = this;
        r0 = android.text.TextUtils.isEmpty(r4);
        r1 = 0;
        if (r0 != 0) goto L_0x0076;
    L_0x0007:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "View Engine : setAnimationEntry : ";
        r0.append(r2);
        r0.append(r4);
        r0 = r0.toString();
        com.moengage.core.Logger.v(r0);
        r0 = -1;
        r2 = r4.hashCode();
        switch(r2) {
            case -1272607767: goto L_0x0056;
            case -1091421752: goto L_0x004c;
            case -584541682: goto L_0x0042;
            case 525670155: goto L_0x0038;
            case 1089111664: goto L_0x002e;
            case 1089339861: goto L_0x0024;
            default: goto L_0x0023;
        };
    L_0x0023:
        goto L_0x0060;
    L_0x0024:
        r2 = "slide_left";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x002c:
        r4 = 2;
        goto L_0x0061;
    L_0x002e:
        r2 = "slide_down";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x0036:
        r4 = 1;
        goto L_0x0061;
    L_0x0038:
        r2 = "fade_out";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x0040:
        r4 = 5;
        goto L_0x0061;
    L_0x0042:
        r2 = "slide_right";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x004a:
        r4 = 3;
        goto L_0x0061;
    L_0x004c:
        r2 = "fade_in";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x0054:
        r4 = 4;
        goto L_0x0061;
    L_0x0056:
        r2 = "slide_up";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x005e:
        r4 = r1;
        goto L_0x0061;
    L_0x0060:
        r4 = r0;
    L_0x0061:
        switch(r4) {
            case 0: goto L_0x0074;
            case 1: goto L_0x0071;
            case 2: goto L_0x006e;
            case 3: goto L_0x006b;
            case 4: goto L_0x0068;
            case 5: goto L_0x0065;
            default: goto L_0x0064;
        };
    L_0x0064:
        goto L_0x0076;
    L_0x0065:
        r1 = com.moengage.inapp.R.anim.fade_out;
        goto L_0x0076;
    L_0x0068:
        r1 = com.moengage.inapp.R.anim.fade_in;
        goto L_0x0076;
    L_0x006b:
        r1 = com.moengage.inapp.R.anim.slide_left_in;
        goto L_0x0076;
    L_0x006e:
        r1 = com.moengage.inapp.R.anim.slide_right_in;
        goto L_0x0076;
    L_0x0071:
        r1 = com.moengage.inapp.R.anim.slide_down_in;
        goto L_0x0076;
    L_0x0074:
        r1 = com.moengage.inapp.R.anim.slide_up_in;
    L_0x0076:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.inapp.ViewEngine.setAnimationEntry(java.lang.String):int");
    }

    private int setAnimationExit(java.lang.String r4) {
        /*
        r3 = this;
        r0 = android.text.TextUtils.isEmpty(r4);
        r1 = 0;
        if (r0 != 0) goto L_0x0076;
    L_0x0007:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "View Engine : setAnimationExit : ";
        r0.append(r2);
        r0.append(r4);
        r0 = r0.toString();
        com.moengage.core.Logger.v(r0);
        r0 = -1;
        r2 = r4.hashCode();
        switch(r2) {
            case -1272607767: goto L_0x0056;
            case -1091421752: goto L_0x004c;
            case -584541682: goto L_0x0042;
            case 525670155: goto L_0x0038;
            case 1089111664: goto L_0x002e;
            case 1089339861: goto L_0x0024;
            default: goto L_0x0023;
        };
    L_0x0023:
        goto L_0x0060;
    L_0x0024:
        r2 = "slide_left";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x002c:
        r4 = 2;
        goto L_0x0061;
    L_0x002e:
        r2 = "slide_down";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x0036:
        r4 = 1;
        goto L_0x0061;
    L_0x0038:
        r2 = "fade_out";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x0040:
        r4 = 5;
        goto L_0x0061;
    L_0x0042:
        r2 = "slide_right";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x004a:
        r4 = 3;
        goto L_0x0061;
    L_0x004c:
        r2 = "fade_in";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x0054:
        r4 = 4;
        goto L_0x0061;
    L_0x0056:
        r2 = "slide_up";
        r4 = r4.equals(r2);
        if (r4 == 0) goto L_0x0060;
    L_0x005e:
        r4 = r1;
        goto L_0x0061;
    L_0x0060:
        r4 = r0;
    L_0x0061:
        switch(r4) {
            case 0: goto L_0x0074;
            case 1: goto L_0x0071;
            case 2: goto L_0x006e;
            case 3: goto L_0x006b;
            case 4: goto L_0x0068;
            case 5: goto L_0x0065;
            default: goto L_0x0064;
        };
    L_0x0064:
        goto L_0x0076;
    L_0x0065:
        r1 = com.moengage.inapp.R.anim.fade_out;
        goto L_0x0076;
    L_0x0068:
        r1 = com.moengage.inapp.R.anim.fade_in;
        goto L_0x0076;
    L_0x006b:
        r1 = com.moengage.inapp.R.anim.slide_right_out;
        goto L_0x0076;
    L_0x006e:
        r1 = com.moengage.inapp.R.anim.slide_left_out;
        goto L_0x0076;
    L_0x0071:
        r1 = com.moengage.inapp.R.anim.slide_down_out;
        goto L_0x0076;
    L_0x0074:
        r1 = com.moengage.inapp.R.anim.slide_up_out;
    L_0x0076:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.inapp.ViewEngine.setAnimationExit(java.lang.String):int");
    }

    private void addAction(final JSONObject jSONObject, View view, final Activity activity, final InAppMessage inAppMessage) {
        if (view != null) {
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    try {
                        if (jSONObject.has(ViewEngine.INAPP_WIDGET_ACTION_LIST)) {
                            JSONArray jSONArray = jSONObject.getJSONArray(ViewEngine.INAPP_WIDGET_ACTION_LIST);
                            int length = jSONArray.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i);
                                String str = null;
                                String string = jSONObject.has(ViewEngine.INAPP_WIDGET_ACTION_TAG) ? jSONObject.getString(ViewEngine.INAPP_WIDGET_ACTION_TAG) : null;
                                jSONObject.put("id", jSONObject.has("id") ? jSONObject.getInt("id") : 0);
                                InAppMessageListener inAppMessageListener = InAppManager.getInstance().getInAppMessageListener();
                                Bundle convertJSONObjecttoBundle;
                                Uri uri;
                                if (jSONObject.has(ActionMapperConstants.KEY_SCREEN)) {
                                    String string2 = jSONObject.getString(ActionMapperConstants.KEY_SCREEN);
                                    convertJSONObjecttoBundle = jSONObject.has("extras") ? MoEHelperUtils.convertJSONObjecttoBundle(jSONObject.getJSONObject("extras")) : null;
                                    String str2 = string2;
                                    uri = null;
                                    str = str2;
                                } else if (jSONObject.has("uri")) {
                                    uri = Uri.parse(jSONObject.getString("uri"));
                                    convertJSONObjecttoBundle = null;
                                } else {
                                    uri = null;
                                    convertJSONObjecttoBundle = uri;
                                }
                                if (inAppMessageListener == null || string == null || !string.equals(ActionMapperConstants.ACTION_NAVIGATE) || !inAppMessageListener.onInAppClick(str, convertJSONObjecttoBundle, uri)) {
                                    InAppActionManager.getInstance().onActionPerformed(activity, string, jSONObject, view, inAppMessage);
                                    if (jSONObject.has(ViewEngine.INAPP_WIDGET_PRIMARY_ACTION) && jSONObject.getBoolean(ViewEngine.INAPP_WIDGET_PRIMARY_ACTION)) {
                                        InAppManager.getInstance().trackInAppPrimaryClick(activity.getApplicationContext(), inAppMessage);
                                    }
                                } else {
                                    Logger.v("InAppMessageClick is overriden");
                                }
                            }
                        }
                    } catch (Exception e) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("ViewEngine: addAction, Campaign Id");
                        stringBuilder.append(ViewEngine.this.mInAppMessage.rules.campaignId);
                        Logger.f(stringBuilder.toString(), e);
                        MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(ViewEngine.this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE);
                    }
                }
            });
        }
    }

    private void styleWidgetBackground(View view, JSONObject jSONObject, Context context, float f, int i, int i2) throws Exception {
        if (jSONObject.has("properties")) {
            jSONObject = jSONObject.getJSONObject("properties");
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        CharSequence charSequence = null;
        if (jSONObject.has("background")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("background");
            if (jSONObject2.has("color")) {
                Logger.v("ViewEngine: styleBitmap: has background color");
                charSequence = jSONObject2.getString("color");
                if (!TextUtils.isEmpty(charSequence)) {
                    gradientDrawable.setColor(Color.parseColor(charSequence));
                }
            }
            if (jSONObject2.has(TtmlNode.TAG_IMAGE)) {
                String optString = jSONObject2.optString(TtmlNode.TAG_IMAGE);
                if (!TextUtils.isEmpty(optString)) {
                    Bitmap downloadImageBitmap = downloadImageBitmap(optString, context);
                    if (downloadImageBitmap != null) {
                        try {
                            downloadImageBitmap = Bitmap.createScaledBitmap(downloadImageBitmap, i, i2, true);
                        } catch (OutOfMemoryError e) {
                            Logger.f("ViewEngine: styleWidgetBackground", e);
                            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE);
                        }
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        downloadImageBitmap = addBackgroundToImage(downloadImageBitmap, charSequence, i, i2);
                    }
                    if (jSONObject.has(INAPP_WIDGET_PROP_BORDER)) {
                        int[] borderData = getBorderData(jSONObject.getJSONObject(INAPP_WIDGET_PROP_BORDER), this.appContext, f);
                        if (borderData[1] != 0) {
                            downloadImageBitmap = addBorderToBitmap(downloadImageBitmap, borderData[0], borderData[1], borderData[2]);
                        }
                        setViewBackground(view, getBitmapDrawable(this.appContext, downloadImageBitmap));
                        return;
                    }
                }
            }
        }
        if (jSONObject.has(INAPP_WIDGET_PROP_BORDER)) {
            setWidgetBorder(jSONObject, context, gradientDrawable, f);
        }
        setViewBackground(view, gradientDrawable);
    }

    private Bitmap addBackgroundToImage(Bitmap bitmap, String str, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(Color.parseColor(str));
        float f = (float) i2;
        canvas.drawBitmap(bitmap, 0.0f, f, null);
        Paint paint = new Paint();
        RectF rectF = new RectF(0.0f, 0.0f, (float) i, f);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor(str));
        paint.setStyle(Style.FILL);
        canvas.drawRoundRect(rectF, 0.0f, 0.0f, paint);
        canvas.drawBitmap(bitmap, null, rectF, paint);
        return createBitmap;
    }

    private Drawable getBitmapDrawable(Context context, Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    @Nullable
    private Bitmap downloadImageBitmap(String str, Context context) throws Exception {
        Throwable e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(IMAGE_TYPE_LOCAL_PREFIX)) {
            int identifier = context.getResources().getIdentifier(str.substring(12), "drawable", context.getPackageName());
            if (identifier == 0) {
                return null;
            }
            Logger.v("ViewEngine: downloadImageBitmap: using a local resource");
            return BitmapFactory.decodeResource(context.getResources(), identifier);
        }
        Bitmap decodeStream;
        try {
            decodeStream = BitmapFactory.decodeStream(new URL(str).openStream());
            if (decodeStream != null) {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("ViewEngine: downloadImageBitmap: Bitmap dimensions: width: ");
                    stringBuilder.append(decodeStream.getWidth());
                    stringBuilder.append(" height: ");
                    stringBuilder.append(decodeStream.getHeight());
                    Logger.v(stringBuilder.toString());
                } catch (Exception e2) {
                    e = e2;
                    Logger.e("ViewEngine:downloadImageBitmap : Image download Exception ", e);
                    return decodeStream;
                }
            }
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_IMAGE_DOWNLOAD_FAILURE);
        } catch (Exception e3) {
            e = e3;
            decodeStream = null;
            Logger.e("ViewEngine:downloadImageBitmap : Image download Exception ", e);
            return decodeStream;
        }
        return decodeStream;
    }

    private void setWidgetBorder(JSONObject jSONObject, Context context, GradientDrawable gradientDrawable, float f) throws JSONException {
        int[] borderData = getBorderData(jSONObject.getJSONObject(INAPP_WIDGET_PROP_BORDER), context, f);
        gradientDrawable.setCornerRadius((float) borderData[0]);
        if (borderData[1] != 0) {
            gradientDrawable.setStroke(borderData[1], borderData[2]);
        }
    }

    private void setViewBackground(View view, Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            setBackground(view, drawable);
        } else {
            setBackgroundDrawable(view, drawable);
        }
    }

    @TargetApi(16)
    private void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    private void setBackgroundDrawable(View view, Drawable drawable) {
        view.setBackgroundDrawable(drawable);
    }

    private int[] adjustContainerRef(int[] iArr, float f, JSONObject jSONObject, Context context) {
        int[] borderData = getBorderData(jSONObject, context, f);
        if (this.isLand || this.isTablet) {
            iArr[0] = iArr[0] - (borderData[1] * 2);
        } else {
            iArr[0] = iArr[0] - (((borderData[1] * 2) + this.margin[0]) + this.margin[2]);
        }
        this.containerBorderWeight = borderData[1];
        return iArr;
    }

    @NonNull
    public int[] getBorderData(JSONObject jSONObject, Context context, float f) {
        int[] iArr = new int[]{0, 0, 0};
        try {
            if (jSONObject.has(INAPP_WIDGET_PROP_BORDER)) {
                jSONObject = jSONObject.getJSONObject(INAPP_WIDGET_PROP_BORDER);
            }
            if (jSONObject.has(INAPP_WIDGET_PROP_RADIUS)) {
                iArr[0] = (int) (jSONObject.getDouble(INAPP_WIDGET_PROP_RADIUS) * ((double) f));
            }
            if (jSONObject.has(INAPP_WIDGET_PROP_WEIGHT)) {
                iArr[1] = (int) (jSONObject.getDouble(INAPP_WIDGET_PROP_WEIGHT) * ((double) f));
            } else {
                iArr[1] = 1;
            }
            if (jSONObject.has("color")) {
                String string = jSONObject.getString("color");
                if (!TextUtils.isEmpty(string)) {
                    iArr[2] = Color.parseColor(string);
                }
            }
            iArr[1] = (int) TypedValue.applyDimension(0, (float) iArr[1], context.getResources().getDisplayMetrics());
            iArr[0] = (int) TypedValue.applyDimension(0, (float) iArr[0], context.getResources().getDisplayMetrics());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ViewEngine: getBorderData: radius: ");
            stringBuilder.append(iArr[0]);
            stringBuilder.append(" weight: ");
            stringBuilder.append(iArr[1]);
            stringBuilder.append(" color: ");
            stringBuilder.append(iArr[2]);
            Logger.v(stringBuilder.toString());
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ViewEngine: getBorderData:, Campaign Id");
            stringBuilder2.append(this.mInAppMessage.rules.campaignId);
            Logger.f(stringBuilder2.toString(), e);
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE);
        }
        return iArr;
    }

    private int[] getContainerDimens(JSONObject jSONObject, int[] iArr) {
        Throwable e;
        int[] viewDimensions;
        try {
            if (!INAPP_WIDGET_TYPE_CONTAINER.equals(jSONObject.getString("type")) || !jSONObject.has("layout")) {
                return iArr;
            }
            viewDimensions = getViewDimensions(jSONObject.getJSONObject("layout"), iArr, iArr, true);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ViewEngine: getContainerDimens found container with dimensions: ");
                stringBuilder.append(viewDimensions[0]);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(viewDimensions[1]);
                Logger.v(stringBuilder.toString());
                return viewDimensions;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            int[] iArr2 = iArr;
            e = e3;
            viewDimensions = iArr2;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ViewEngine: getContainerDimens, Campaign Id");
            stringBuilder2.append(this.mInAppMessage.rules.campaignId);
            Logger.f(stringBuilder2.toString(), e);
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE);
            return viewDimensions;
        }
    }

    private int[] getMargin(JSONObject jSONObject, int[] iArr, int[] iArr2, boolean z) {
        int[] extras = getExtras(jSONObject, iArr, iArr2, z, INAPP_WIDGET_LAYOUT_MARGIN);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ViewEngine: getMargin : left: ");
        stringBuilder.append(extras[0]);
        stringBuilder.append(" ,top ");
        stringBuilder.append(extras[1]);
        stringBuilder.append(" ,right: ");
        stringBuilder.append(extras[2]);
        stringBuilder.append(" ,bottom: ");
        stringBuilder.append(extras[3]);
        Logger.v(stringBuilder.toString());
        return extras;
    }

    private int[] getExtras(JSONObject jSONObject, int[] iArr, int[] iArr2, boolean z, String str) {
        int[] iArr3 = new int[]{0, 0, 0, 0};
        if (jSONObject == null) {
            return iArr3;
        }
        try {
            if (jSONObject.has("layout")) {
                jSONObject = jSONObject.getJSONObject("layout");
            }
            if (jSONObject.has(str)) {
                jSONObject = jSONObject.getJSONObject(str);
                if (jSONObject.has("left")) {
                    iArr3[0] = transformDimension(jSONObject.getDouble("left"), iArr[0], iArr2[0], z);
                }
                if (jSONObject.has("top")) {
                    iArr3[1] = transformDimension(jSONObject.getDouble("top"), iArr[1], iArr[1], z);
                }
                if (jSONObject.has("right")) {
                    iArr3[2] = transformDimension(jSONObject.getDouble("right"), iArr[0], iArr2[0], z);
                }
                if (jSONObject.has("bottom")) {
                    iArr3[3] = transformDimension(jSONObject.getDouble("bottom"), iArr[1], iArr[1], z);
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ViewEngine: getExtras: NO value found for ");
                stringBuilder.append(str);
                Logger.v(stringBuilder.toString());
            }
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ViewEngine: getExtras Campaign Id ");
            stringBuilder2.append(this.mInAppMessage.rules.campaignId);
            Logger.f(stringBuilder2.toString(), e);
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(this.mInAppMessage.rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE);
        }
        return iArr3;
    }

    private int[] getPadding(JSONObject jSONObject, int[] iArr, int[] iArr2, boolean z) {
        int[] extras = getExtras(jSONObject, iArr, iArr2, z, INAPP_WIDGET_LAYOUT_PADDING);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ViewEngine: getPadding: left: ");
        stringBuilder.append(extras[0]);
        stringBuilder.append(" ,top ");
        stringBuilder.append(extras[1]);
        stringBuilder.append(" ,right: ");
        stringBuilder.append(extras[2]);
        stringBuilder.append(" ,bottom: ");
        stringBuilder.append(extras[3]);
        Logger.v(stringBuilder.toString());
        return extras;
    }

    private int[] getViewDimensions(JSONObject jSONObject, int[] iArr, int[] iArr2, boolean z) throws JSONException {
        double d;
        JSONObject jSONObject2 = jSONObject;
        int[] iArr3 = new int[]{iArr2[0], -2};
        if (jSONObject2.has("width")) {
            d = jSONObject2.getDouble("width");
            iArr3[0] = transformDimension(d, iArr[0], iArr2[0], z);
            if (iArr3[0] <= 0) {
                iArr3[0] = (int) d;
            }
        }
        if (jSONObject2.has("height")) {
            d = jSONObject2.getDouble("height");
            iArr3[1] = transformDimension(d, iArr[1], iArr2[1], z);
            if (iArr3[1] <= 0) {
                iArr3[1] = (int) d;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ViewEngine: getViewDimensions: Width: ");
        stringBuilder.append(iArr3[0]);
        stringBuilder.append(" height: ");
        stringBuilder.append(iArr3[1]);
        Logger.v(stringBuilder.toString());
        return iArr3;
    }

    private int transformDimension(double d, int i, int i2, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ViewEngine: transformDimension : dimension : ");
        stringBuilder.append(d);
        stringBuilder.append("screenRef :");
        stringBuilder.append(i);
        stringBuilder.append("containerRef : ");
        stringBuilder.append(i2);
        Logger.v(stringBuilder.toString());
        return z ? (int) ((d * ((double) i)) / 100.0d) : (int) ((d * ((double) i2)) / 100.0d);
    }

    private void handleBackPress(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || i != 4) {
                    return false;
                }
                if (ViewEngine.this.mInAppMessage.rules.exitAnimation != 0) {
                    Animation loadAnimation = AnimationUtils.loadAnimation(ViewEngine.this.appContext, ViewEngine.this.mInAppMessage.rules.exitAnimation);
                    loadAnimation.setFillAfter(true);
                    view.setAnimation(loadAnimation);
                }
                ((ViewGroup) view.getParent()).removeView(view);
                InAppManager.getInstance().handleDismiss();
                Logger.v("ViewEngine: handleBackPress : on back button pressed");
                return true;
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0094 A:{Catch:{ Exception -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a9 A:{Catch:{ Exception -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0 A:{Catch:{ Exception -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e8 A:{Catch:{ Exception -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00df A:{Catch:{ Exception -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x015e A:{Catch:{ Exception -> 0x0164 }} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0120 A:{Catch:{ Exception -> 0x0164 }} */
    private void styleTextView(android.widget.TextView r4, org.json.JSONObject r5, android.content.Context r6, float r7) {
        /*
        r3 = this;
        r0 = "properties";
        r0 = r5.has(r0);	 Catch:{ Exception -> 0x0164 }
        if (r0 == 0) goto L_0x018e;
    L_0x0008:
        r0 = "properties";
        r5 = r5.getJSONObject(r0);	 Catch:{ Exception -> 0x0164 }
        r0 = "typeface";
        r0 = r5.has(r0);	 Catch:{ Exception -> 0x0164 }
        if (r0 == 0) goto L_0x0067;
    L_0x0016:
        r0 = "typeface";
        r0 = r5.getString(r0);	 Catch:{ Exception -> 0x003d }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x003d }
        if (r1 != 0) goto L_0x0067;
    L_0x0022:
        r1 = r3.sTypefaceCache;	 Catch:{ Exception -> 0x003d }
        r1 = r1.get(r0);	 Catch:{ Exception -> 0x003d }
        r1 = (android.graphics.Typeface) r1;	 Catch:{ Exception -> 0x003d }
        if (r1 != 0) goto L_0x0067;
    L_0x002c:
        r6 = r6.getAssets();	 Catch:{ Exception -> 0x003d }
        r6 = android.graphics.Typeface.createFromAsset(r6, r0);	 Catch:{ Exception -> 0x003d }
        r1 = r3.sTypefaceCache;	 Catch:{ Exception -> 0x003d }
        r1.put(r0, r6);	 Catch:{ Exception -> 0x003d }
        r4.setTypeface(r6);	 Catch:{ Exception -> 0x003d }
        goto L_0x0067;
    L_0x003d:
        r6 = move-exception;
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0164 }
        r0.<init>();	 Catch:{ Exception -> 0x0164 }
        r1 = "ViewEngine: styleTextView: Campaign Id";
        r0.append(r1);	 Catch:{ Exception -> 0x0164 }
        r1 = r3.mInAppMessage;	 Catch:{ Exception -> 0x0164 }
        r1 = r1.rules;	 Catch:{ Exception -> 0x0164 }
        r1 = r1.campaignId;	 Catch:{ Exception -> 0x0164 }
        r0.append(r1);	 Catch:{ Exception -> 0x0164 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0164 }
        com.moengage.core.Logger.f(r0, r6);	 Catch:{ Exception -> 0x0164 }
        r6 = com.moengage.inapp.MoEInAppFailureLogger.getInstance();	 Catch:{ Exception -> 0x0164 }
        r0 = r3.mInAppMessage;	 Catch:{ Exception -> 0x0164 }
        r0 = r0.rules;	 Catch:{ Exception -> 0x0164 }
        r0 = r0.campaignId;	 Catch:{ Exception -> 0x0164 }
        r1 = com.moengage.inapp.MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE;	 Catch:{ Exception -> 0x0164 }
        r6.updateCampaignFailureCounter(r0, r1);	 Catch:{ Exception -> 0x0164 }
    L_0x0067:
        r6 = "size";
        r6 = r5.has(r6);	 Catch:{ Exception -> 0x0164 }
        r0 = 0;
        if (r6 == 0) goto L_0x0077;
    L_0x0070:
        r6 = "size";
        r6 = r5.getInt(r6);	 Catch:{ Exception -> 0x0164 }
        goto L_0x0078;
    L_0x0077:
        r6 = r0;
    L_0x0078:
        r1 = "color";
        r1 = r5.has(r1);	 Catch:{ Exception -> 0x0164 }
        if (r1 == 0) goto L_0x0091;
    L_0x0080:
        r1 = "color";
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x0164 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x0164 }
        if (r2 != 0) goto L_0x0091;
    L_0x008c:
        r1 = android.graphics.Color.parseColor(r1);	 Catch:{ Exception -> 0x0164 }
        goto L_0x0092;
    L_0x0091:
        r1 = r0;
    L_0x0092:
        if (r6 == 0) goto L_0x009e;
    L_0x0094:
        r2 = "ViewEngine: styleWidget: setting text size";
        com.moengage.core.Logger.v(r2);	 Catch:{ Exception -> 0x0164 }
        r6 = (float) r6;	 Catch:{ Exception -> 0x0164 }
        r6 = r6 * r7;
        r4.setTextSize(r0, r6);	 Catch:{ Exception -> 0x0164 }
    L_0x009e:
        if (r1 == 0) goto L_0x00a9;
    L_0x00a0:
        r6 = "ViewEngine: styleWidget: setting text color";
        com.moengage.core.Logger.v(r6);	 Catch:{ Exception -> 0x0164 }
        r4.setTextColor(r1);	 Catch:{ Exception -> 0x0164 }
        goto L_0x00b2;
    L_0x00a9:
        r6 = "#000000";
        r6 = android.graphics.Color.parseColor(r6);	 Catch:{ Exception -> 0x0164 }
        r4.setTextColor(r6);	 Catch:{ Exception -> 0x0164 }
    L_0x00b2:
        r6 = "bold";
        r6 = r5.has(r6);	 Catch:{ Exception -> 0x0164 }
        r7 = 1;
        if (r6 == 0) goto L_0x00cc;
    L_0x00bb:
        r6 = "bold";
        r6 = r5.getBoolean(r6);	 Catch:{ Exception -> 0x0164 }
        if (r6 == 0) goto L_0x00cc;
    L_0x00c3:
        r6 = r4.getTypeface();	 Catch:{ Exception -> 0x0164 }
        r4.setTypeface(r6, r7);	 Catch:{ Exception -> 0x0164 }
        r6 = r7;
        goto L_0x00cd;
    L_0x00cc:
        r6 = r0;
    L_0x00cd:
        r1 = "italics";
        r1 = r5.has(r1);	 Catch:{ Exception -> 0x0164 }
        if (r1 == 0) goto L_0x00f0;
    L_0x00d5:
        r1 = "italics";
        r1 = r5.getBoolean(r1);	 Catch:{ Exception -> 0x0164 }
        if (r1 == 0) goto L_0x00f0;
    L_0x00dd:
        if (r6 == 0) goto L_0x00e8;
    L_0x00df:
        r6 = r4.getTypeface();	 Catch:{ Exception -> 0x0164 }
        r1 = 3;
        r4.setTypeface(r6, r1);	 Catch:{ Exception -> 0x0164 }
        goto L_0x00f0;
    L_0x00e8:
        r6 = r4.getTypeface();	 Catch:{ Exception -> 0x0164 }
        r1 = 2;
        r4.setTypeface(r6, r1);	 Catch:{ Exception -> 0x0164 }
    L_0x00f0:
        r6 = "underline";
        r6 = r5.has(r6);	 Catch:{ Exception -> 0x0164 }
        if (r6 == 0) goto L_0x0118;
    L_0x00f8:
        r6 = "underline";
        r6 = r5.getBoolean(r6);	 Catch:{ Exception -> 0x0164 }
        if (r6 == 0) goto L_0x0118;
    L_0x0100:
        r6 = new android.text.SpannableString;	 Catch:{ Exception -> 0x0164 }
        r1 = r4.getText();	 Catch:{ Exception -> 0x0164 }
        r6.<init>(r1);	 Catch:{ Exception -> 0x0164 }
        r1 = new android.text.style.UnderlineSpan;	 Catch:{ Exception -> 0x0164 }
        r1.<init>();	 Catch:{ Exception -> 0x0164 }
        r2 = r6.length();	 Catch:{ Exception -> 0x0164 }
        r6.setSpan(r1, r0, r2, r0);	 Catch:{ Exception -> 0x0164 }
        r4.setText(r6);	 Catch:{ Exception -> 0x0164 }
    L_0x0118:
        r6 = "gravity";
        r6 = r5.has(r6);	 Catch:{ Exception -> 0x0164 }
        if (r6 == 0) goto L_0x015e;
    L_0x0120:
        r6 = "gravity";
        r5 = r5.getString(r6);	 Catch:{ Exception -> 0x0164 }
        r6 = android.text.TextUtils.isEmpty(r5);	 Catch:{ Exception -> 0x0164 }
        if (r6 != 0) goto L_0x018e;
    L_0x012c:
        r6 = "center_h";
        r6 = r5.contains(r6);	 Catch:{ Exception -> 0x0164 }
        if (r6 == 0) goto L_0x0135;
    L_0x0134:
        goto L_0x0136;
    L_0x0135:
        r7 = r0;
    L_0x0136:
        r6 = "center_v";
        r6 = r5.contains(r6);	 Catch:{ Exception -> 0x0164 }
        if (r6 == 0) goto L_0x0140;
    L_0x013e:
        r7 = r7 | 16;
    L_0x0140:
        r6 = "right";
        r6 = r5.contains(r6);	 Catch:{ Exception -> 0x0164 }
        if (r6 == 0) goto L_0x014c;
    L_0x0148:
        r6 = 8388613; // 0x800005 float:1.175495E-38 double:4.1445255E-317;
        r7 = r7 | r6;
    L_0x014c:
        r6 = "left";
        r5 = r5.contains(r6);	 Catch:{ Exception -> 0x0164 }
        if (r5 == 0) goto L_0x0158;
    L_0x0154:
        r5 = 8388611; // 0x800003 float:1.1754948E-38 double:4.1445245E-317;
        r7 = r7 | r5;
    L_0x0158:
        if (r7 == 0) goto L_0x018e;
    L_0x015a:
        r4.setGravity(r7);	 Catch:{ Exception -> 0x0164 }
        goto L_0x018e;
    L_0x015e:
        r5 = 17;
        r4.setGravity(r5);	 Catch:{ Exception -> 0x0164 }
        goto L_0x018e;
    L_0x0164:
        r4 = move-exception;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "ViewEngine: styleTextView: Campaign Id";
        r5.append(r6);
        r6 = r3.mInAppMessage;
        r6 = r6.rules;
        r6 = r6.campaignId;
        r5.append(r6);
        r5 = r5.toString();
        com.moengage.core.Logger.f(r5, r4);
        r4 = com.moengage.inapp.MoEInAppFailureLogger.getInstance();
        r5 = r3.mInAppMessage;
        r5 = r5.rules;
        r5 = r5.campaignId;
        r6 = com.moengage.inapp.MoEInAppFailureLogger.FAILURE_REASON_VIEW_CREATION_FAILURE;
        r4.updateCampaignFailureCounter(r5, r6);
    L_0x018e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.inapp.ViewEngine.styleTextView(android.widget.TextView, org.json.JSONObject, android.content.Context, float):void");
    }

    private Bitmap addBorderToBitmap(Bitmap bitmap, int i, int i2, int i3) {
        int i4 = i2 * 2;
        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() - i4, bitmap.getHeight() - i4, true).copy(Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(i3);
        paint.setStrokeWidth((float) i2);
        float f = (float) i;
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight()), f, f, paint);
        return bitmap;
    }

    private boolean isFrescoSupported() {
        boolean z = false;
        try {
            ClassLoader classLoader = MoEHelperUtils.class.getClassLoader();
            for (String cls : this.USED_FRESCO_CLASSES) {
                if (Class.forName(cls, false, classLoader) == null) {
                    break;
                }
            }
            z = true;
            return z;
        } catch (Exception e) {
            Logger.e("MoEHelperUtils: isFrescoSupported ", e);
            return false;
        } catch (NoClassDefFoundError e2) {
            Logger.e("MoEHelperUtils: isFrescoSupported ", e2);
            return false;
        } catch (Throwable th) {
            Logger.e("MoEHelperUtils: isFrescoSupported ", th);
            return false;
        }
    }
}
