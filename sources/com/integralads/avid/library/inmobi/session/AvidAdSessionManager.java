package com.integralads.avid.library.inmobi.session;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.AvidContext;
import com.integralads.avid.library.inmobi.AvidManager;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidDisplayAdSession;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidManagedDisplayAdSession;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidVideoAdSession;

public class AvidAdSessionManager {
    public static String getVersion() {
        return AvidContext.getInstance().getAvidVersion();
    }

    public static String getReleaseDate() {
        return AvidContext.getInstance().getAvidReleaseDate();
    }

    public static AvidDisplayAdSession startAvidDisplayAdSession(Context context, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidDisplayAdSession avidDisplayAdSession = new AvidDisplayAdSession();
        InternalAvidDisplayAdSession internalAvidDisplayAdSession = new InternalAvidDisplayAdSession(context, avidDisplayAdSession.getAvidAdSessionId(), externalAvidAdSessionContext);
        internalAvidDisplayAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidDisplayAdSession, internalAvidDisplayAdSession);
        return avidDisplayAdSession;
    }

    public static AvidVideoAdSession startAvidVideoAdSession(Context context, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidVideoAdSession avidVideoAdSession = new AvidVideoAdSession();
        InternalAvidVideoAdSession internalAvidVideoAdSession = new InternalAvidVideoAdSession(context, avidVideoAdSession.getAvidAdSessionId(), externalAvidAdSessionContext);
        internalAvidVideoAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidVideoAdSession, internalAvidVideoAdSession);
        return avidVideoAdSession;
    }

    public static AvidManagedVideoAdSession startAvidManagedVideoAdSession(Context context, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidManagedVideoAdSession avidManagedVideoAdSession = new AvidManagedVideoAdSession();
        InternalAvidManagedVideoAdSession internalAvidManagedVideoAdSession = new InternalAvidManagedVideoAdSession(context, avidManagedVideoAdSession.getAvidAdSessionId(), externalAvidAdSessionContext);
        internalAvidManagedVideoAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidManagedVideoAdSession, internalAvidManagedVideoAdSession);
        return avidManagedVideoAdSession;
    }

    public static AvidManagedDisplayAdSession startAvidManagedDisplayAdSession(Context context, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidManagedDisplayAdSession avidManagedDisplayAdSession = new AvidManagedDisplayAdSession();
        InternalAvidManagedDisplayAdSession internalAvidManagedDisplayAdSession = new InternalAvidManagedDisplayAdSession(context, avidManagedDisplayAdSession.getAvidAdSessionId(), externalAvidAdSessionContext);
        internalAvidManagedDisplayAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidManagedDisplayAdSession, internalAvidManagedDisplayAdSession);
        return avidManagedDisplayAdSession;
    }

    public static <T extends AbstractAvidAdSession> T findAvidAdSessionById(String str) {
        return AvidManager.getInstance().findAvidAdSessionById(str);
    }

    public static WebView webViewForView(View view) {
        WebView findWebView = findWebView(view);
        if (findWebView != null) {
            return findWebView;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            findWebView = webViewForView(viewGroup.getChildAt(i));
            if (findWebView != null) {
                break;
            }
        }
        return findWebView;
    }

    public static WebView webViewForSessionId(String str) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(str);
        return findInternalAvidAdSessionById != null ? findInternalAvidAdSessionById.getWebView() : null;
    }

    private static WebView findWebView(View view) {
        InternalAvidAdSession findInternalAvidAdSessionByView = AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionByView(view);
        return findInternalAvidAdSessionByView != null ? findInternalAvidAdSessionByView.getWebView() : null;
    }
}
