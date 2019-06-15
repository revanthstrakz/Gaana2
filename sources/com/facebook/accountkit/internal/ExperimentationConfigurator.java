package com.facebook.accountkit.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ExperimentationConfigurator {
    private static final String FEATURE_PARAMETER_KEY = "key";
    private static final String FEATURE_PARAMETER_VALUE = "value";
    private static final String GRAPH_PATH_GET_CONFIGURATION = "experimentation_configuration";
    private static final String PARAMETER_UNIT_IDENTIFIER = "unit_id";
    private static final String RESPONSE_PARAMETER_CREATE_TIME = "create_time";
    private static final String RESPONSE_PARAMETER_DATA = "data";
    private static final String RESPONSE_PARAMETER_FEATURE_SET = "feature_set";
    private static final String RESPONSE_PARAMETER_TTL = "ttl";
    private static final String RESPONSE_PARAMETER_UNIT_ID = "unit_id";
    private Context applicationContext;

    ExperimentationConfigurator() {
    }

    /* Access modifiers changed, original: 0000 */
    public void initialize(@NonNull Context context) {
        Validate.checkInternetPermissionAndThrow(context);
        this.applicationContext = context.getApplicationContext();
        Utility.getThreadPoolExecutor().execute(new Runnable() {
            public void run() {
                ExperimentationConfiguration experimentationConfiguration = ExperimentationConfigurator.this.getExperimentationConfiguration();
                if (!experimentationConfiguration.exists() || experimentationConfiguration.isStale()) {
                    ExperimentationConfigurator.this.downloadExperimentationConfiguration(experimentationConfiguration.getUnitID());
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public ExperimentationConfiguration getExperimentationConfiguration() {
        return new ExperimentationConfiguration(this.applicationContext);
    }

    private void downloadExperimentationConfiguration(@Nullable final String str) {
        Utility.getThreadPoolExecutor().execute(new Runnable() {
            public void run() {
                AccountKitGraphRequest access$100 = ExperimentationConfigurator.this.buildGraphRequest(ExperimentationConfigurator.GRAPH_PATH_GET_CONFIGURATION, str);
                AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
                AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(access$100, new Callback() {
                    public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                        if (accountKitGraphResponse.getError() == null) {
                            try {
                                int i = 0;
                                JSONObject jSONObject = accountKitGraphResponse.getResponseObject().getJSONArray("data").getJSONObject(0);
                                Long l = null;
                                Long valueOf = jSONObject.has(ExperimentationConfigurator.RESPONSE_PARAMETER_CREATE_TIME) ? Long.valueOf(jSONObject.getLong(ExperimentationConfigurator.RESPONSE_PARAMETER_CREATE_TIME)) : null;
                                String string = jSONObject.has("unit_id") ? jSONObject.getString("unit_id") : null;
                                if (jSONObject.has("ttl")) {
                                    l = Long.valueOf(jSONObject.getLong("ttl"));
                                }
                                JSONArray jSONArray = jSONObject.getJSONArray(ExperimentationConfigurator.RESPONSE_PARAMETER_FEATURE_SET);
                                HashMap hashMap = new HashMap(jSONArray.length());
                                while (i < jSONArray.length()) {
                                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                    hashMap.put(Integer.valueOf(jSONObject2.getInt("key")), Integer.valueOf(jSONObject2.getInt("value")));
                                    i++;
                                }
                                ExperimentationConfiguration.load(ExperimentationConfigurator.this.applicationContext, string, valueOf, l, hashMap);
                            } catch (JSONException unused) {
                            }
                        }
                    }
                }));
            }
        });
    }

    private AccountKitGraphRequest buildGraphRequest(String str, @Nullable String str2) {
        Bundle bundle = new Bundle();
        Utility.putNonNullString(bundle, "unit_id", str2);
        return new AccountKitGraphRequest(null, str, bundle, false, HttpMethod.GET);
    }
}
