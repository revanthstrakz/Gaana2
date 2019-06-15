package com.facebook.accountkit.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountPreferences;
import com.facebook.accountkit.AccountPreferences.OnDeletePreferenceListener;
import com.facebook.accountkit.AccountPreferences.OnLoadPreferenceListener;
import com.facebook.accountkit.AccountPreferences.OnLoadPreferencesListener;
import com.facebook.accountkit.AccountPreferences.OnSetPreferenceListener;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class AccountPreferencesImpl implements AccountPreferences {
    private static final String PREFERENCES_API_PATH = "me/preferences";
    private static final String REQUEST_PARAMETER_NAME = "name";
    private static final String REQUEST_PARAMETER_VALUE = "value";
    private static final String RESPONSE_PARAMETER_DATA = "data";
    private static final String RESPONSE_PARAMETER_NAME = "name";
    private static final String RESPONSE_PARAMETER_SUCCESS = "success";
    private static final String RESPONSE_PARAMETER_VALUE = "value";
    private final AccessToken accessToken;

    AccountPreferencesImpl(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void deletePreference(@NonNull final String str, @Nullable final OnDeletePreferenceListener onDeletePreferenceListener) {
        Callback callback;
        Bundle bundle = new Bundle();
        Utility.putNonNullString(bundle, "name", str);
        AccountKitGraphRequest accountKitGraphRequest = new AccountKitGraphRequest(this.accessToken, PREFERENCES_API_PATH, bundle, false, HttpMethod.DELETE);
        if (onDeletePreferenceListener == null) {
            callback = null;
        } else {
            callback = new Callback() {
                public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                    AccountKitRequestError error = accountKitGraphResponse.getError();
                    if (error != null) {
                        onDeletePreferenceListener.onDeletePreference(str, (AccountKitError) Utility.createErrorFromServerError(error).first);
                        return;
                    }
                    try {
                        if (accountKitGraphResponse.getResponseObject().getBoolean("success")) {
                            onDeletePreferenceListener.onDeletePreference(str, null);
                            return;
                        }
                        onDeletePreferenceListener.onDeletePreference(str, new AccountKitError(Type.INTERNAL_ERROR, InternalAccountKitError.OPERATION_NOT_SUCCESSFUL));
                    } catch (JSONException unused) {
                        onDeletePreferenceListener.onDeletePreference(str, new AccountKitError(Type.SERVER_ERROR, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                    }
                }
            };
        }
        AccountKitGraphRequest.executeAsync(accountKitGraphRequest, callback);
    }

    public void loadPreference(@NonNull final String str, @Nullable final OnLoadPreferenceListener onLoadPreferenceListener) {
        Callback callback;
        Bundle bundle = new Bundle();
        Utility.putNonNullString(bundle, "name", str);
        AccountKitGraphRequest accountKitGraphRequest = new AccountKitGraphRequest(this.accessToken, PREFERENCES_API_PATH, bundle, false, HttpMethod.GET);
        if (onLoadPreferenceListener == null) {
            callback = null;
        } else {
            callback = new Callback() {
                public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                    AccountKitRequestError error = accountKitGraphResponse.getError();
                    if (error != null) {
                        onLoadPreferenceListener.onLoadPreference(str, null, (AccountKitError) Utility.createErrorFromServerError(error).first);
                        return;
                    }
                    try {
                        String string;
                        JSONArray jSONArray = accountKitGraphResponse.getResponseObject().getJSONArray("data");
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            if (jSONObject != null) {
                                String string2 = jSONObject.getString("name");
                                if (string2 != null) {
                                    if (string2.equals(str)) {
                                        string = jSONObject.getString("value");
                                        break;
                                    }
                                }
                            }
                        }
                        string = null;
                        onLoadPreferenceListener.onLoadPreference(str, string, null);
                    } catch (JSONException unused) {
                        onLoadPreferenceListener.onLoadPreference(str, null, new AccountKitError(Type.SERVER_ERROR, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                    }
                }
            };
        }
        AccountKitGraphRequest.executeAsync(accountKitGraphRequest, callback);
    }

    public void loadPreferences(@Nullable final OnLoadPreferencesListener onLoadPreferencesListener) {
        Callback callback;
        AccountKitGraphRequest accountKitGraphRequest = new AccountKitGraphRequest(this.accessToken, PREFERENCES_API_PATH, new Bundle(), false, HttpMethod.GET);
        if (onLoadPreferencesListener == null) {
            callback = null;
        } else {
            callback = new Callback() {
                public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                    AccountKitRequestError error = accountKitGraphResponse.getError();
                    if (error != null) {
                        onLoadPreferencesListener.onLoadPreferences(null, (AccountKitError) Utility.createErrorFromServerError(error).first);
                        return;
                    }
                    JSONObject responseObject = accountKitGraphResponse.getResponseObject();
                    HashMap hashMap = new HashMap();
                    try {
                        JSONArray jSONArray = responseObject.getJSONArray("data");
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            if (jSONObject != null) {
                                String string = jSONObject.getString("name");
                                if (string != null) {
                                    String string2 = jSONObject.getString("value");
                                    if (string2 != null) {
                                        hashMap.put(string, string2);
                                    }
                                }
                            }
                        }
                        onLoadPreferencesListener.onLoadPreferences(hashMap, null);
                    } catch (JSONException unused) {
                        onLoadPreferencesListener.onLoadPreferences(null, new AccountKitError(Type.SERVER_ERROR, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                    }
                }
            };
        }
        AccountKitGraphRequest.executeAsync(accountKitGraphRequest, callback);
    }

    public void setPreference(@NonNull final String str, @NonNull final String str2, @Nullable final OnSetPreferenceListener onSetPreferenceListener) {
        Callback callback;
        Bundle bundle = new Bundle();
        Utility.putNonNullString(bundle, "name", str);
        Utility.putNonNullString(bundle, "value", str2);
        AccountKitGraphRequest accountKitGraphRequest = new AccountKitGraphRequest(this.accessToken, PREFERENCES_API_PATH, bundle, false, HttpMethod.POST);
        if (onSetPreferenceListener == null) {
            callback = null;
        } else {
            callback = new Callback() {
                public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                    AccountKitRequestError error = accountKitGraphResponse.getError();
                    if (error != null) {
                        onSetPreferenceListener.onSetPreference(str, str2, (AccountKitError) Utility.createErrorFromServerError(error).first);
                        return;
                    }
                    try {
                        if (accountKitGraphResponse.getResponseObject().getBoolean("success")) {
                            onSetPreferenceListener.onSetPreference(str, str2, null);
                            return;
                        }
                        onSetPreferenceListener.onSetPreference(str, str2, new AccountKitError(Type.INTERNAL_ERROR, InternalAccountKitError.OPERATION_NOT_SUCCESSFUL));
                    } catch (JSONException unused) {
                        onSetPreferenceListener.onSetPreference(str, str2, new AccountKitError(Type.SERVER_ERROR, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                    }
                }
            };
        }
        AccountKitGraphRequest.executeAsync(accountKitGraphRequest, callback);
    }
}
