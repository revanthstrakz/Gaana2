package com.gaana.login;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.credentials.IdentityProviders;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService.Builder;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.people.v1.model.Birthday;
import com.google.api.services.people.v1.model.Person;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.d;
import com.library.managers.TaskManager.TaskListner;
import com.managers.an;
import com.managers.u;
import com.models.b;
import com.services.g;
import com.services.h;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class GooglePlusLogin implements ConnectionCallbacks, OnConnectionFailedListener {
    public static final int GOOGLE_PLUS_REQUEST_CODE = 703;
    private static HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    public static final int OAUTH_REQUEST_CODE = 704;
    public static final int RC_CREDENTIALS_READ = 210;
    public static final int RC_CREDENTIALS_SAVE = 211;
    public static final int RC_HINT = 212;
    public static final int RC_SIGN_IN = 209;
    private static final String TAG = "GooglePlusLogin";
    private static GooglePlusLogin mInstance;
    private Runnable accessTokenFound = new Runnable() {
        public void run() {
            if (GooglePlusLogin.this.mAccessToken != null) {
                GooglePlusLogin.this.handleGoogleSignIn(GooglePlusLogin.this.gsr);
                return;
            }
            if (GooglePlusLogin.this.onGooglePlusLoginListner != null) {
                GooglePlusLogin.this.onGooglePlusLoginListner.onLoginFailed(null);
            }
            u.a().a("Auto_SignUp", "Login", "Failure");
        }
    };
    private GoogleSignInResult gsr;
    private String mAccessToken;
    private Reference<Activity> mActivityRef;
    private Credential mCredentialToSave;
    private GoogleApiClient mGoogleApiClient;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsResolving = false;
    private Person meProfile = null;
    private OnEmailAutoSignInGoogle onEmailAutoSignInGoogle;
    private OnGooglePlusLoginListner onGooglePlusLoginListner;
    Runnable profileFound = new Runnable() {
        public void run() {
            if (GooglePlusLogin.this.meProfile != null) {
                List birthdays = GooglePlusLogin.this.meProfile.getBirthdays();
                if (!(birthdays == null || birthdays.size() <= 0 || ((Birthday) birthdays.get(0)).getDate() == null || ((Birthday) birthdays.get(0)).getDate().getDay() == null || ((Birthday) birthdays.get(0)).getDate().getMonth() == null || ((Birthday) birthdays.get(0)).getDate().getYear() == null)) {
                    b access$500 = GooglePlusLogin.this.user;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(((Birthday) birthdays.get(0)).getDate().getDay());
                    stringBuilder.append("-");
                    stringBuilder.append(((Birthday) birthdays.get(0)).getDate().getMonth());
                    stringBuilder.append("-");
                    stringBuilder.append(((Birthday) birthdays.get(0)).getDate().getYear());
                    access$500.c(stringBuilder.toString());
                }
            }
            GooglePlusLogin.this.setUserDetails(GooglePlusLogin.this.gsr);
        }
    };
    private b user;

    public interface OnEmailAutoSignInGoogle {
        void onEmailSignIn(String str, String str2);
    }

    public interface OnGooglePlusLoginListner {
        void onLoginFailed(String str);

        void onLoginSuccess(b bVar);
    }

    private void getProfileInfo(final String str) {
        d.a(new Runnable() {
            public void run() {
                Activity activity = (Activity) GooglePlusLogin.this.mActivityRef.get();
                if (activity != null) {
                    GoogleAccountCredential usingOAuth2 = GoogleAccountCredential.usingOAuth2(activity, Collections.singleton(Scopes.PROFILE));
                    usingOAuth2.setSelectedAccount(new Account(str, "com.google"));
                    try {
                        GooglePlusLogin.this.meProfile = (Person) new Builder(GooglePlusLogin.HTTP_TRANSPORT, GooglePlusLogin.JSON_FACTORY, usingOAuth2).setApplicationName("Gaana").build().people().get("people/me").execute();
                        GooglePlusLogin.this.mHandler.post(GooglePlusLogin.this.profileFound);
                        return;
                    } catch (IOException e) {
                        GooglePlusLogin.this.mHandler.post(GooglePlusLogin.this.profileFound);
                        ThrowableExtension.printStackTrace(e);
                        return;
                    }
                }
                GooglePlusLogin.this.mHandler.post(GooglePlusLogin.this.profileFound);
            }
        });
    }

    public static GooglePlusLogin getInstance() {
        if (mInstance == null) {
            mInstance = new GooglePlusLogin();
        }
        return mInstance;
    }

    public void login(Activity activity, OnGooglePlusLoginListner onGooglePlusLoginListner) {
        this.mActivityRef = new WeakReference(activity);
        this.onGooglePlusLoginListner = onGooglePlusLoginListner;
        GoogleSignInOptions.Builder requestIdToken = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile().requestIdToken(Constants.bD);
        if (Constants.bE) {
            requestIdToken.requestScopes(new Scope(PeopleServiceScopes.USER_BIRTHDAY_READ), new Scope[0]);
        }
        this.mGoogleApiClient = new GoogleApiClient.Builder(GaanaApplication.getContext()).addConnectionCallbacks(this).addConnectionCallbacks(new ConnectionCallbacks() {
            public void onConnected(@Nullable Bundle bundle) {
                Activity activity = (Activity) GooglePlusLogin.this.mActivityRef.get();
                if (activity != null) {
                    activity.startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(GooglePlusLogin.this.mGoogleApiClient), GooglePlusLogin.RC_SIGN_IN);
                }
            }

            public void onConnectionSuspended(int i) {
                if (GooglePlusLogin.this.onGooglePlusLoginListner != null) {
                    GooglePlusLogin.this.onGooglePlusLoginListner.onLoginFailed(null);
                }
            }
        }).addApi(Auth.CREDENTIALS_API).addApi(Auth.GOOGLE_SIGN_IN_API, requestIdToken.build()).build();
        this.mGoogleApiClient.connect();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.onGooglePlusLoginListner.onLoginFailed(getLoginFailureReason(connectionResult));
        disconnect();
    }

    public void onConnected(Bundle bundle) {
        if (this.mGoogleApiClient.isConnected()) {
            Auth.CredentialsApi.disableAutoSignIn(this.mGoogleApiClient);
            saveCredentialIfConnected(this.mCredentialToSave);
            return;
        }
        this.mGoogleApiClient.connect();
    }

    public void buildGoogleApiClient(Activity activity, String str, OnGooglePlusLoginListner onGooglePlusLoginListner, OnEmailAutoSignInGoogle onEmailAutoSignInGoogle) {
        this.mActivityRef = new WeakReference(activity);
        this.onGooglePlusLoginListner = onGooglePlusLoginListner;
        this.onEmailAutoSignInGoogle = onEmailAutoSignInGoogle;
        buildGoogleClient(str);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            this.mAccessToken = ((GoogleSignInAccount) task.getResult(ApiException.class)).getIdToken();
            this.mHandler.post(this.accessTokenFound);
        } catch (ApiException unused) {
            this.mAccessToken = "";
            this.mHandler.post(this.accessTokenFound);
        }
    }

    public void authorizeCallBack(int i, int i2, Intent intent) {
        switch (i) {
            case RC_SIGN_IN /*209*/:
                Task signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(intent);
                this.gsr = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
                handleSignInResult(signedInAccountFromIntent);
                return;
            case RC_CREDENTIALS_READ /*210*/:
            case RC_HINT /*212*/:
                this.mIsResolving = false;
                if (i2 == -1) {
                    handleCredential((Credential) intent.getParcelableExtra(Credential.EXTRA_KEY));
                    return;
                }
                return;
            case RC_CREDENTIALS_SAVE /*211*/:
                this.mIsResolving = false;
                if (i2 == -1) {
                    Toast.makeText(GaanaApplication.getContext(), "Saved", 0).show();
                    return;
                } else {
                    Log.w(TAG, "Credential save failed.");
                    return;
                }
            default:
                if (this.onGooglePlusLoginListner != null) {
                    this.onGooglePlusLoginListner.onLoginFailed("Unknown Error");
                    return;
                }
                return;
        }
    }

    public void onConnectionSuspended(int i) {
        if (this.mGoogleApiClient != null) {
            this.mGoogleApiClient.connect();
        }
    }

    public void disconnect() {
        if (this.mGoogleApiClient != null) {
            Auth.CredentialsApi.disableAutoSignIn(this.mGoogleApiClient);
            Auth.GoogleSignInApi.signOut(this.mGoogleApiClient);
            this.mHandler.removeCallbacksAndMessages(null);
            this.mAccessToken = "";
        }
    }

    private String getLoginFailureReason(ConnectionResult connectionResult) {
        Resources resources = GaanaApplication.getContext().getResources();
        String string = resources.getString(R.string.unable_process_request);
        int errorCode = connectionResult.getErrorCode();
        if (errorCode == 9) {
            return resources.getString(R.string.reinstall_google_play_service);
        }
        switch (errorCode) {
            case 1:
                return resources.getString(R.string.missing_google_play_service);
            case 2:
                return resources.getString(R.string.update_google_play_service);
            case 3:
                return resources.getString(R.string.google_play_service_disabled);
            default:
                return string;
        }
    }

    public void requestCredentials(final boolean z, boolean z2) {
        CredentialRequest.Builder passwordLoginSupported = new CredentialRequest.Builder().setPasswordLoginSupported(true);
        if (!z2) {
            passwordLoginSupported.setAccountTypes(IdentityProviders.GOOGLE, IdentityProviders.FACEBOOK);
        }
        Auth.CredentialsApi.request(this.mGoogleApiClient, passwordLoginSupported.build()).setResultCallback(new ResultCallback<CredentialRequestResult>() {
            public void onResult(CredentialRequestResult credentialRequestResult) {
                Status status = credentialRequestResult.getStatus();
                if (status.isSuccess()) {
                    GooglePlusLogin.this.handleCredential(credentialRequestResult.getCredential());
                } else if (status.getStatusCode() == 6 && z) {
                    GooglePlusLogin.this.resolveResult(status, GooglePlusLogin.RC_CREDENTIALS_READ);
                } else if (status.getStatusCode() == 4) {
                    GooglePlusLogin.this.showHints();
                }
            }
        });
    }

    private void resolveResult(Status status, int i) {
        Activity activity = (Activity) this.mActivityRef.get();
        if (!this.mIsResolving && activity != null) {
            try {
                status.startResolutionForResult(activity, i);
                this.mIsResolving = true;
            } catch (SendIntentException unused) {
                this.mIsResolving = false;
            }
        }
    }

    private void handleCredential(Credential credential) {
        if (IdentityProviders.GOOGLE.equals(credential.getAccountType())) {
            Activity activity = (Activity) this.mActivityRef.get();
            if (activity != null) {
                buildGoogleApiClient(activity, credential.getId(), this.onGooglePlusLoginListner, this.onEmailAutoSignInGoogle);
                googleSilentSignIn();
            }
        } else if (IdentityProviders.FACEBOOK.equals(credential.getAccountType())) {
            h.a().a(new TaskListner() {
                public void onBackGroundTaskCompleted() {
                    LoginManager.getInstance().loginWithFacebook();
                }

                public void doBackGroundTask() {
                    g.a().h();
                }
            }, -1);
        } else if (this.onEmailAutoSignInGoogle != null) {
            this.onEmailAutoSignInGoogle.onEmailSignIn(credential.getId(), credential.getPassword());
        }
    }

    private void googleSilentSignIn() {
        OptionalPendingResult silentSignIn = Auth.GoogleSignInApi.silentSignIn(this.mGoogleApiClient);
        if (silentSignIn.isDone()) {
            this.gsr = (GoogleSignInResult) silentSignIn.get();
            if (!this.gsr.isSuccess() || this.gsr.getSignInAccount() == null) {
                this.mAccessToken = "";
            } else {
                this.mAccessToken = this.gsr.getSignInAccount().getIdToken();
            }
            this.mHandler.post(this.accessTokenFound);
            return;
        }
        silentSignIn.setResultCallback(new ResultCallback<GoogleSignInResult>() {
            public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                GooglePlusLogin.this.gsr = googleSignInResult;
                if (!GooglePlusLogin.this.gsr.isSuccess() || GooglePlusLogin.this.gsr.getSignInAccount() == null) {
                    GooglePlusLogin.this.mAccessToken = "";
                } else {
                    GooglePlusLogin.this.mAccessToken = GooglePlusLogin.this.gsr.getSignInAccount().getIdToken();
                }
                GooglePlusLogin.this.mHandler.post(GooglePlusLogin.this.accessTokenFound);
            }
        });
    }

    private void handleGoogleSignIn(GoogleSignInResult googleSignInResult) {
        Object obj = (googleSignInResult == null || !googleSignInResult.isSuccess()) ? null : 1;
        if (obj != null) {
            this.user = new b();
            if (Constants.bE) {
                getProfileInfo(googleSignInResult.getSignInAccount().getEmail());
                return;
            } else {
                setUserDetails(googleSignInResult);
                return;
            }
        }
        u.a().a("Auto_SignUp", "Login", "Failure");
        an.a().d("s2s", "ac", "", "GOOGLE", "", "FAIL", "", "");
        this.onGooglePlusLoginListner.onLoginFailed(null);
    }

    private void setUserDetails(GoogleSignInResult googleSignInResult) {
        GoogleSignInAccount signInAccount = googleSignInResult.getSignInAccount();
        if (signInAccount.getPhotoUrl() != null) {
            this.user.f(signInAccount.getPhotoUrl().toString());
        }
        this.user.a(signInAccount.getEmail());
        this.user.d(this.mAccessToken);
        this.user.b(signInAccount.getDisplayName());
        this.user.e(signInAccount.getId());
        saveCredentialIfConnected(new Credential.Builder(signInAccount.getEmail()).setAccountType(IdentityProviders.GOOGLE).setName(signInAccount.getDisplayName()).setProfilePictureUri(signInAccount.getPhotoUrl()).build());
        u.a().a("Auto_SignUp", "Login", "Success");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.user.b());
        stringBuilder.append("##");
        stringBuilder.append(this.user.a());
        stringBuilder.append("##");
        stringBuilder.append(this.user.e());
        stringBuilder.append("##");
        stringBuilder.append(this.user.f());
        String stringBuilder2 = stringBuilder.toString();
        an.a().d("s2s", "ac", "", "GOOGLE", stringBuilder2, "SUCCESS", "", "");
        this.onGooglePlusLoginListner.onLoginSuccess(this.user);
    }

    private void saveCredentialIfConnected(Credential credential) {
        if (credential != null && this.mActivityRef != null && this.mActivityRef.get() != null) {
            Activity activity = (Activity) this.mActivityRef.get();
            this.mCredentialToSave = credential;
            if (this.mGoogleApiClient.isConnected()) {
                Auth.CredentialsApi.save(this.mGoogleApiClient, this.mCredentialToSave).setResultCallback(new ResolvingResultCallbacks<Status>(activity, RC_CREDENTIALS_SAVE) {
                    public void onSuccess(Status status) {
                        GooglePlusLogin.this.mCredentialToSave = null;
                    }

                    public void onUnresolvableFailure(Status status) {
                        GooglePlusLogin.this.mCredentialToSave = null;
                    }
                });
            }
        }
    }

    public void resetCredentials() {
        this.mCredentialToSave = null;
    }

    public void onEmailSaveClicked(String str, String str2, Activity activity) {
        if (!TextUtils.isEmpty(str2)) {
            Credential build = new Credential.Builder(str).setPassword(str2).build();
            this.mActivityRef = new WeakReference(activity);
            saveCredentialIfConnected(build);
        }
    }

    public void onFacebookClicked(String str, String str2, Activity activity) {
        Credential build = new Credential.Builder(str).setAccountType(IdentityProviders.FACEBOOK).setName(str2).build();
        this.mActivityRef = new WeakReference(activity);
        saveCredentialIfConnected(build);
    }

    public void buildGoogleClient(String str) {
        GoogleSignInOptions.Builder requestIdToken = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile().requestIdToken(Constants.bD);
        if (Constants.bE) {
            requestIdToken.requestScopes(new Scope(PeopleServiceScopes.USER_BIRTHDAY_READ), new Scope[0]);
        }
        if (str != null) {
            requestIdToken.setAccountName(str);
        }
        this.mGoogleApiClient = new GoogleApiClient.Builder(GaanaApplication.getContext()).addConnectionCallbacks(this).addApi(Auth.CREDENTIALS_API).addApi(Auth.GOOGLE_SIGN_IN_API, requestIdToken.build()).build();
        this.mGoogleApiClient.connect();
    }

    private void showHints() {
        Activity activity = (Activity) this.mActivityRef.get();
        if (activity != null) {
            try {
                activity.startIntentSenderForResult(Auth.CredentialsApi.getHintPickerIntent(this.mGoogleApiClient, new HintRequest.Builder().setHintPickerConfig(new CredentialPickerConfig.Builder().setShowCancelButton(true).build()).setEmailAddressIdentifierSupported(true).setAccountTypes(IdentityProviders.GOOGLE).build()).getIntentSender(), RC_HINT, null, 0, 0, 0);
                this.mIsResolving = true;
            } catch (SendIntentException unused) {
                this.mIsResolving = false;
            }
        }
    }
}
