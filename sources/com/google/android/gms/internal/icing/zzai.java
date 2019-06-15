package com.google.android.gms.internal.icing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.appindexing.AppIndexApi.ActionResult;
import com.google.android.gms.appindexing.AppIndexApi.AppIndexingLink;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import java.util.List;

public final class zzai implements AppIndexApi {
    private static final String TAG = "zzai";

    @Deprecated
    static final class zza implements ActionResult {
        private zzai zzau;
        private PendingResult<Status> zzav;
        private Action zzaw;

        zza(zzai zzai, PendingResult<Status> pendingResult, Action action) {
            this.zzau = zzai;
            this.zzav = pendingResult;
            this.zzaw = action;
        }

        public final PendingResult<Status> end(GoogleApiClient googleApiClient) {
            String packageName = googleApiClient.getContext().getPackageName();
            zzx zza = zzah.zza(this.zzaw, System.currentTimeMillis(), packageName, 2);
            return this.zzau.zza(googleApiClient, zza);
        }

        public final PendingResult<Status> getPendingResult() {
            return this.zzav;
        }
    }

    static abstract class zzb<T extends Result> extends ApiMethodImpl<T, zzag> {
        public zzb(GoogleApiClient googleApiClient) {
            super(zze.zzg, googleApiClient);
        }

        public abstract void zza(zzab zzab) throws RemoteException;

        /* Access modifiers changed, original: protected|synthetic */
        public /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
            zza((zzab) ((zzag) anyClient).getService());
        }
    }

    public static final class zzd extends zzaf<Status> {
        public zzd(ResultHolder<Status> resultHolder) {
            super(resultHolder);
        }

        public final void zza(Status status) {
            this.zzas.setResult(status);
        }
    }

    public static abstract class zzc<T extends Result> extends zzb<Status> {
        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* Access modifiers changed, original: protected|synthetic */
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    public final PendingResult<Status> zza(GoogleApiClient googleApiClient, zzx... zzxArr) {
        return googleApiClient.enqueue(new zzaj(this, googleApiClient, zzxArr));
    }

    public final PendingResult<Status> view(GoogleApiClient googleApiClient, Activity activity, Intent intent, String str, Uri uri, List<AppIndexingLink> list) {
        String packageName = googleApiClient.getContext().getPackageName();
        if (list != null) {
            for (AppIndexingLink appIndexingLink : list) {
                zzb(null, appIndexingLink.appIndexingUrl);
            }
        }
        return zza(googleApiClient, new zzx(packageName, intent, str, uri, null, list, 1));
    }

    public final PendingResult<Status> viewEnd(GoogleApiClient googleApiClient, Activity activity, Intent intent) {
        String packageName = googleApiClient.getContext().getPackageName();
        return zza(googleApiClient, new zzy().zza(zzx.zza(packageName, intent)).zza(System.currentTimeMillis()).zzb(0).zzc(2).zzd());
    }

    public static Intent zza(String str, Uri uri) {
        zzb(str, uri);
        if (zza(uri)) {
            return new Intent("android.intent.action.VIEW", uri);
        }
        if (zzb(uri)) {
            String str2 = "android.intent.action.VIEW";
            List pathSegments = uri.getPathSegments();
            String str3 = (String) pathSegments.get(0);
            Builder builder = new Builder();
            builder.scheme(str3);
            if (pathSegments.size() > 1) {
                builder.authority((String) pathSegments.get(1));
                for (int i = 2; i < pathSegments.size(); i++) {
                    builder.appendPath((String) pathSegments.get(i));
                }
            } else {
                String str4 = TAG;
                str3 = String.valueOf(uri);
                StringBuilder stringBuilder = new StringBuilder(88 + String.valueOf(str3).length());
                stringBuilder.append("The app URI must have the format: android-app://<package_name>/<scheme>/<path>. But got ");
                stringBuilder.append(str3);
                Log.e(str4, stringBuilder.toString());
            }
            builder.encodedQuery(uri.getEncodedQuery());
            builder.encodedFragment(uri.getEncodedFragment());
            return new Intent(str2, builder.build());
        }
        String valueOf = String.valueOf(uri);
        StringBuilder stringBuilder2 = new StringBuilder(70 + String.valueOf(valueOf).length());
        stringBuilder2.append("appIndexingUri is neither an HTTP(S) URL nor an \"android-app://\" URL: ");
        stringBuilder2.append(valueOf);
        throw new RuntimeException(stringBuilder2.toString());
    }

    private static void zzb(String str, Uri uri) {
        String valueOf;
        StringBuilder stringBuilder;
        if (zza(uri)) {
            if (uri.getHost().isEmpty()) {
                valueOf = String.valueOf(uri);
                stringBuilder = new StringBuilder(98 + String.valueOf(valueOf).length());
                stringBuilder.append("AppIndex: The web URL must have a host (follow the format http(s)://<host>/<path>). Provided URI: ");
                stringBuilder.append(valueOf);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        } else if (!zzb(uri)) {
            valueOf = String.valueOf(uri);
            stringBuilder = new StringBuilder(176 + String.valueOf(valueOf).length());
            stringBuilder.append("AppIndex: The URI scheme must either be 'http(s)' or 'android-app'. If the latter, it must follow the format 'android-app://<package_name>/<scheme>/<host_path>'. Provided URI: ");
            stringBuilder.append(valueOf);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (str == null || str.equals(uri.getHost())) {
            List pathSegments = uri.getPathSegments();
            if (pathSegments.isEmpty() || ((String) pathSegments.get(0)).isEmpty()) {
                valueOf = String.valueOf(uri);
                stringBuilder = new StringBuilder(128 + String.valueOf(valueOf).length());
                stringBuilder.append("AppIndex: The app URI scheme must exist and follow the format android-app://<package_name>/<scheme>/<host_path>). Provided URI: ");
                stringBuilder.append(valueOf);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        } else {
            valueOf = String.valueOf(uri);
            stringBuilder = new StringBuilder(150 + String.valueOf(valueOf).length());
            stringBuilder.append("AppIndex: The android-app URI host must match the package name and follow the format android-app://<package_name>/<scheme>/<host_path>. Provided URI: ");
            stringBuilder.append(valueOf);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static boolean zza(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equals(scheme) || "https".equals(scheme);
    }

    private static boolean zzb(Uri uri) {
        return "android-app".equals(uri.getScheme());
    }

    public final PendingResult<Status> view(GoogleApiClient googleApiClient, Activity activity, Uri uri, String str, Uri uri2, List<AppIndexingLink> list) {
        String packageName = googleApiClient.getContext().getPackageName();
        zzb(packageName, uri);
        return view(googleApiClient, activity, zza(packageName, uri), str, uri2, (List) list);
    }

    public final PendingResult<Status> viewEnd(GoogleApiClient googleApiClient, Activity activity, Uri uri) {
        return viewEnd(googleApiClient, activity, zza(googleApiClient.getContext().getPackageName(), uri));
    }

    public final ActionResult action(GoogleApiClient googleApiClient, Action action) {
        return new zza(this, zza(googleApiClient, action, 1), action);
    }

    public final PendingResult<Status> start(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 1);
    }

    public final PendingResult<Status> end(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 2);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, Action action, int i) {
        zzx zza = zzah.zza(action, System.currentTimeMillis(), googleApiClient.getContext().getPackageName(), i);
        return zza(googleApiClient, zza);
    }
}
