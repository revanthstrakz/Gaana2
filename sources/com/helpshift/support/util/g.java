package com.helpshift.support.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.helpshift.common.domain.network.j;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.PlatformException;
import com.helpshift.common.exception.a;
import com.helpshift.e.k;
import com.helpshift.util.o;
import com.helpshift.views.c;
import com.helpshift.views.d;
import java.util.WeakHashMap;

public class g {
    private static WeakHashMap<View, Snackbar> a = new WeakHashMap();

    public static void a(View view, String str, int i) {
        if (view != null) {
            Snackbar a = c.a(view, (CharSequence) str, i);
            a.show();
            a.put(view, a);
        } else if (o.b() != null) {
            d.a(o.b(), (CharSequence) str, i == -1 ? 0 : 1).show();
        }
    }

    public static void a(View view, int i, int i2) {
        if (view != null) {
            Snackbar a = c.a(view, i, i2);
            a.show();
            a.put(view, a);
        } else if (o.b() != null) {
            d.a(o.b(), i, i2 == -1 ? 0 : 1).show();
        }
    }

    public static void a(int i, View view) {
        if (i != -1) {
            Context b = o.b();
            if (view != null || b != null) {
                if (view != null) {
                    Snackbar a = c.a(view, a(i, view.getContext()), -1);
                    a.show();
                    a.put(view, a);
                } else {
                    d.a(b, a(i, b), 0).show();
                }
            }
        }
    }

    @NonNull
    private static String a(int i, Context context) {
        if (i == j.a.intValue()) {
            return context.getResources().getString(k.hs__network_unavailable_msg);
        }
        if (i == j.m.intValue()) {
            return context.getResources().getString(k.hs__data_not_found_msg);
        }
        if (i == j.c.intValue()) {
            return context.getResources().getString(k.hs__screenshot_upload_error_msg);
        }
        if (i == j.d.intValue()) {
            return context.getResources().getString(k.hs__could_not_reach_support_msg);
        }
        if (i == 100) {
            return context.getResources().getString(k.hs__could_not_open_attachment_msg);
        }
        if (i == 101) {
            return context.getResources().getString(k.hs__file_not_found_msg);
        }
        if (i == j.e.intValue()) {
            return context.getResources().getString(k.hs__ssl_peer_unverified_error);
        }
        if (i == j.f.intValue()) {
            return context.getResources().getString(k.hs__ssl_handshake_error);
        }
        if (i == 102) {
            return context.getResources().getString(k.hs__invalid_faq_publish_id_error);
        }
        if (i == 103) {
            return context.getResources().getString(k.hs__invalid_section_publish_id_error);
        }
        return context.getResources().getString(k.hs__network_error_msg);
    }

    public static void a(a aVar, View view) {
        Context b = o.b();
        if (view != null || b != null) {
            if (view != null) {
                Snackbar a = c.a(view, a(aVar, view.getContext()), -1);
                a.show();
                a.put(view, a);
            } else {
                d.a(b, a(aVar, b), 0).show();
            }
        }
    }

    private static String a(a aVar, Context context) {
        if (aVar == NetworkException.NO_CONNECTION) {
            return context.getResources().getString(k.hs__network_unavailable_msg);
        }
        if (aVar == NetworkException.UNKNOWN_HOST) {
            return context.getResources().getString(k.hs__could_not_reach_support_msg);
        }
        if (aVar == NetworkException.SSL_PEER_UNVERIFIED) {
            return context.getResources().getString(k.hs__ssl_peer_unverified_error);
        }
        if (aVar == NetworkException.SSL_HANDSHAKE) {
            return context.getResources().getString(k.hs__ssl_handshake_error);
        }
        if (aVar == NetworkException.CONTENT_NOT_FOUND) {
            return context.getResources().getString(k.hs__data_not_found_msg);
        }
        if (aVar == NetworkException.SCREENSHOT_UPLOAD_ERROR) {
            return context.getResources().getString(k.hs__screenshot_upload_error_msg);
        }
        if (aVar == PlatformException.NO_APPS_FOR_OPENING_ATTACHMENT) {
            return context.getResources().getString(k.hs__could_not_open_attachment_msg);
        }
        if (aVar == PlatformException.FILE_NOT_FOUND) {
            return context.getResources().getString(k.hs__file_not_found_msg);
        }
        return context.getResources().getString(k.hs__network_error_msg);
    }

    public static void a(View view) {
        if (view != null && a.containsKey(view)) {
            Snackbar snackbar = (Snackbar) a.get(view);
            if (snackbar != null && snackbar.isShown()) {
                snackbar.dismiss();
            }
            a.remove(view);
        }
    }
}
