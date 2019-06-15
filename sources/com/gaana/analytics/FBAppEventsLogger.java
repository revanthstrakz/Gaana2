package com.gaana.analytics;

import android.content.Context;
import android.os.Bundle;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.models.PlayerTrack;

public class FBAppEventsLogger {
    private static AppEventsLogger logger;

    public static void init(Context context) {
        if (logger == null) {
            logger = AppEventsLogger.newLogger(context);
        }
    }

    public static void reportPlay(PlayerTrack playerTrack) {
        init(GaanaApplication.getContext());
        Bundle bundle = new Bundle();
        bundle.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, "product");
        bundle.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, playerTrack.h());
        logger.logEvent(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT, bundle);
    }

    public static void reportFavorite(BusinessObject businessObject) {
        init(GaanaApplication.getContext());
        Bundle bundle = new Bundle();
        bundle.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, "product");
        bundle.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, businessObject.getBusinessObjId());
        logger.logEvent(AppEventsConstants.EVENT_NAME_ADDED_TO_CART, bundle);
    }
}
