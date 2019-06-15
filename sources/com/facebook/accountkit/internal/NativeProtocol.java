package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

final class NativeProtocol {
    static final String CONTENT_SCHEME = "content://";
    static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
    static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
    static final String EXTRA_SEAMLESS_LOGIN_TOKEN = "com.facebook.platform.extra.SEAMLESS_LOGIN_TOKEN";
    private static final String INTENT_ACTION_FBLITE_PLATFORM_SERVICE = "com.facebook.lite.platform.PLATFORM_SERVICE";
    private static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
    static final int MESSAGE_GET_AK_SEAMLESS_TOKEN_REPLY = 65545;
    static final int MESSAGE_GET_AK_SEAMLESS_TOKEN_REQUEST = 65544;
    static final String PLATFORM_PROVIDER = ".provider.PlatformProvider";
    static final String PLATFORM_PROVIDER_VERSIONS = ".provider.PlatformProvider/versions";
    static final String PLATFORM_PROVIDER_VERSION_COLUMN = "version";
    static final int PROTOCOL_VERSION_20161017 = 20161017;
    static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
    private static List<NativeAppInfo> facebookAppInfoList = Arrays.asList(new NativeAppInfo[]{new KatanaAppInfo(), new WakizashiAppInfo(), new FBLiteAppInfo()});
    private static AtomicBoolean protocolVersionsAsyncUpdating = new AtomicBoolean(false);

    private static class FBLiteAppInfo extends NativeAppInfo {
        private static final String FBLITE_PACKAGE = "com.facebook.lite";

        /* Access modifiers changed, original: protected */
        public String getPackage() {
            return FBLITE_PACKAGE;
        }

        private FBLiteAppInfo() {
        }

        /* synthetic */ FBLiteAppInfo(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* Access modifiers changed, original: protected */
        public Intent getPlatformServiceIntent() {
            return new Intent(NativeProtocol.INTENT_ACTION_FBLITE_PLATFORM_SERVICE).setPackage(getPackage());
        }
    }

    private static class KatanaAppInfo extends NativeAppInfo {
        private static final String KATANA_PACKAGE = "com.facebook.katana";

        /* Access modifiers changed, original: protected */
        public String getPackage() {
            return KATANA_PACKAGE;
        }

        private KatanaAppInfo() {
        }

        /* synthetic */ KatanaAppInfo(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* Access modifiers changed, original: protected */
        public Intent getPlatformServiceIntent() {
            return new Intent(NativeProtocol.INTENT_ACTION_PLATFORM_SERVICE).setPackage(getPackage());
        }
    }

    private static class WakizashiAppInfo extends NativeAppInfo {
        private static final String WAKIZASHI_PACKAGE = "com.facebook.wakizashi";

        /* Access modifiers changed, original: protected */
        public String getPackage() {
            return WAKIZASHI_PACKAGE;
        }

        private WakizashiAppInfo() {
        }

        /* synthetic */ WakizashiAppInfo(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* Access modifiers changed, original: protected */
        public Intent getPlatformServiceIntent() {
            return new Intent(NativeProtocol.INTENT_ACTION_PLATFORM_SERVICE).setPackage(getPackage());
        }
    }

    NativeProtocol() {
    }

    static boolean validateApplicationForService() {
        for (NativeAppInfo isAppInstalled : facebookAppInfoList) {
            if (isAppInstalled.isAppInstalled()) {
                return true;
            }
        }
        return false;
    }

    static boolean validateProtocolVersionForService(int i) {
        for (NativeAppInfo availableVersions : facebookAppInfoList) {
            if (availableVersions.getAvailableVersions().contains(Integer.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    static void updateAllAvailableProtocolVersionsAsync() {
        if (protocolVersionsAsyncUpdating.compareAndSet(false, true)) {
            Utility.getThreadPoolExecutor().execute(new Runnable() {
                public void run() {
                    try {
                        for (NativeAppInfo fetchAvailableVersions : NativeProtocol.facebookAppInfoList) {
                            fetchAvailableVersions.fetchAvailableVersions(true);
                        }
                    } finally {
                        NativeProtocol.protocolVersionsAsyncUpdating.set(false);
                    }
                }
            });
        }
    }

    static Intent createPlatformServiceIntent(Context context) {
        for (NativeAppInfo nativeAppInfo : facebookAppInfoList) {
            Intent validateServiceIntent = validateServiceIntent(context, nativeAppInfo.getPlatformServiceIntent().addCategory("android.intent.category.DEFAULT"), nativeAppInfo);
            if (validateServiceIntent != null) {
                return validateServiceIntent;
            }
        }
        return null;
    }

    private static Intent validateServiceIntent(Context context, Intent intent, NativeAppInfo nativeAppInfo) {
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService != null && nativeAppInfo.validateSignature(context, resolveService.serviceInfo.packageName)) {
            return intent;
        }
        return null;
    }
}
