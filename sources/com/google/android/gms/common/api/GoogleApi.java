package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.GoogleApiManager.zaa;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zaae;
import com.google.android.gms.common.api.internal.zabp;
import com.google.android.gms.common.api.internal.zace;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class GoogleApi<O extends ApiOptions> {
    private final Api<O> mApi;
    private final Context mContext;
    private final int mId;
    private final O zabh;
    private final zai<O> zabi;
    private final Looper zabj;
    private final GoogleApiClient zabk;
    private final StatusExceptionMapper zabl;
    protected final GoogleApiManager zabm;

    @KeepForSdk
    public static class Settings {
        @KeepForSdk
        public static final Settings DEFAULT_SETTINGS = new Builder().build();
        public final StatusExceptionMapper zabn;
        public final Looper zabo;

        @KeepForSdk
        public static class Builder {
            private Looper zabj;
            private StatusExceptionMapper zabl;

            @KeepForSdk
            public Builder setMapper(StatusExceptionMapper statusExceptionMapper) {
                Preconditions.checkNotNull(statusExceptionMapper, "StatusExceptionMapper must not be null.");
                this.zabl = statusExceptionMapper;
                return this;
            }

            @KeepForSdk
            public Builder setLooper(Looper looper) {
                Preconditions.checkNotNull(looper, "Looper must not be null.");
                this.zabj = looper;
                return this;
            }

            @KeepForSdk
            public Settings build() {
                if (this.zabl == null) {
                    this.zabl = new ApiExceptionMapper();
                }
                if (this.zabj == null) {
                    this.zabj = Looper.getMainLooper();
                }
                return new Settings(this.zabl, null, this.zabj, null);
            }
        }

        @KeepForSdk
        private Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            this.zabn = statusExceptionMapper;
            this.zabo = looper;
        }

        /* synthetic */ Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper, zab zab) {
            this(statusExceptionMapper, null, looper);
        }
    }

    @KeepForSdk
    protected GoogleApi(@NonNull Context context, Api<O> api, Looper looper) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(looper, "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.mApi = api;
        this.zabh = null;
        this.zabj = looper;
        this.zabi = zai.zaa(api);
        this.zabk = new zabp(this);
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = new ApiExceptionMapper();
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(@NonNull Context context, Api<O> api, O o, Looper looper, StatusExceptionMapper statusExceptionMapper) {
        this(context, (Api) api, (ApiOptions) o, new Builder().setLooper(looper).setMapper(statusExceptionMapper).build());
    }

    @KeepForSdk
    @MainThread
    public GoogleApi(@NonNull Activity activity, Api<O> api, O o, Settings settings) {
        Preconditions.checkNotNull(activity, "Null activity is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = activity.getApplicationContext();
        this.mApi = api;
        this.zabh = o;
        this.zabj = settings.zabo;
        this.zabi = zai.zaa(this.mApi, this.zabh);
        this.zabk = new zabp(this);
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = settings.zabn;
        if (!(activity instanceof GoogleApiActivity)) {
            zaae.zaa(activity, this.zabm, this.zabi);
        }
        this.zabm.zaa(this);
    }

    @KeepForSdk
    public GoogleApi(@NonNull Context context, Api<O> api, O o, Settings settings) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = context.getApplicationContext();
        this.mApi = api;
        this.zabh = o;
        this.zabj = settings.zabo;
        this.zabi = zai.zaa(this.mApi, this.zabh);
        this.zabk = new zabp(this);
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = settings.zabn;
        this.zabm.zaa(this);
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(@NonNull Activity activity, Api<O> api, O o, StatusExceptionMapper statusExceptionMapper) {
        this(activity, (Api) api, (ApiOptions) o, new Builder().setMapper(statusExceptionMapper).setLooper(activity.getMainLooper()).build());
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(@NonNull Context context, Api<O> api, O o, StatusExceptionMapper statusExceptionMapper) {
        this(context, (Api) api, (ApiOptions) o, new Builder().setMapper(statusExceptionMapper).build());
    }

    private final <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T zaa(int i, @NonNull T t) {
        t.zau();
        this.zabm.zaa(this, i, (ApiMethodImpl) t);
        return t;
    }

    private final <TResult, A extends AnyClient> Task<TResult> zaa(int i, @NonNull TaskApiCall<A, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zabm.zaa(this, i, taskApiCall, taskCompletionSource, this.zabl);
        return taskCompletionSource.getTask();
    }

    @KeepForSdk
    public <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T doRead(@NonNull T t) {
        return zaa(0, (ApiMethodImpl) t);
    }

    @KeepForSdk
    public <TResult, A extends AnyClient> Task<TResult> doRead(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(0, (TaskApiCall) taskApiCall);
    }

    @KeepForSdk
    public <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T doWrite(@NonNull T t) {
        return zaa(1, (ApiMethodImpl) t);
    }

    @KeepForSdk
    public <TResult, A extends AnyClient> Task<TResult> doWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(1, (TaskApiCall) taskApiCall);
    }

    @KeepForSdk
    public <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(@NonNull T t) {
        return zaa(2, (ApiMethodImpl) t);
    }

    @KeepForSdk
    public <TResult, A extends AnyClient> Task<TResult> doBestEffortWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(2, (TaskApiCall) taskApiCall);
    }

    @KeepForSdk
    @Deprecated
    public <A extends AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(@NonNull T t, U u) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(u);
        Preconditions.checkNotNull(t.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(u.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(t.getListenerKey().equals(u.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zabm.zaa(this, (RegisterListenerMethod) t, (UnregisterListenerMethod) u);
    }

    @KeepForSdk
    public <A extends AnyClient> Task<Void> doRegisterEventListener(@NonNull RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull(registrationMethods);
        Preconditions.checkNotNull(registrationMethods.zajy.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(registrationMethods.zajz.getListenerKey(), "Listener has already been released.");
        return this.zabm.zaa(this, registrationMethods.zajy, registrationMethods.zajz);
    }

    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@NonNull ListenerKey<?> listenerKey) {
        Preconditions.checkNotNull(listenerKey, "Listener key cannot be null.");
        return this.zabm.zaa(this, (ListenerKey) listenerKey);
    }

    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull L l, String str) {
        return ListenerHolders.createListenerHolder(l, this.zabj, str);
    }

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    public Task<Boolean> disconnectService() {
        return this.zabm.zac(this);
    }

    @WorkerThread
    public Client zaa(Looper looper, zaa<O> zaa) {
        return this.mApi.zai().buildClient(this.mContext, looper, createClientSettingsBuilder().build(), this.zabh, zaa, zaa);
    }

    public final Api<O> getApi() {
        return this.mApi;
    }

    @KeepForSdk
    public O getApiOptions() {
        return this.zabh;
    }

    public final zai<O> zak() {
        return this.zabi;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    @KeepForSdk
    public GoogleApiClient asGoogleApiClient() {
        return this.zabk;
    }

    @KeepForSdk
    public Looper getLooper() {
        return this.zabj;
    }

    @KeepForSdk
    public Context getApplicationContext() {
        return this.mContext;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0034  */
    @com.google.android.gms.common.annotation.KeepForSdk
    public com.google.android.gms.common.internal.ClientSettings.Builder createClientSettingsBuilder() {
        /*
        r2 = this;
        r0 = new com.google.android.gms.common.internal.ClientSettings$Builder;
        r0.<init>();
        r1 = r2.zabh;
        r1 = r1 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions;
        if (r1 == 0) goto L_0x001a;
    L_0x000b:
        r1 = r2.zabh;
        r1 = (com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions) r1;
        r1 = r1.getGoogleSignInAccount();
        if (r1 == 0) goto L_0x001a;
    L_0x0015:
        r1 = r1.getAccount();
        goto L_0x002a;
    L_0x001a:
        r1 = r2.zabh;
        r1 = r1 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions;
        if (r1 == 0) goto L_0x0029;
    L_0x0020:
        r1 = r2.zabh;
        r1 = (com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions) r1;
        r1 = r1.getAccount();
        goto L_0x002a;
    L_0x0029:
        r1 = 0;
    L_0x002a:
        r0 = r0.setAccount(r1);
        r1 = r2.zabh;
        r1 = r1 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions;
        if (r1 == 0) goto L_0x0043;
    L_0x0034:
        r1 = r2.zabh;
        r1 = (com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions) r1;
        r1 = r1.getGoogleSignInAccount();
        if (r1 == 0) goto L_0x0043;
    L_0x003e:
        r1 = r1.getRequestedScopes();
        goto L_0x0047;
    L_0x0043:
        r1 = java.util.Collections.emptySet();
    L_0x0047:
        r0 = r0.addAllRequiredScopes(r1);
        r1 = r2.mContext;
        r1 = r1.getClass();
        r1 = r1.getName();
        r0 = r0.setRealClientClassName(r1);
        r1 = r2.mContext;
        r1 = r1.getPackageName();
        r0 = r0.setRealClientPackageName(r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApi.createClientSettingsBuilder():com.google.android.gms.common.internal.ClientSettings$Builder");
    }

    public zace zaa(Context context, Handler handler) {
        return new zace(context, handler, createClientSettingsBuilder().build());
    }
}
