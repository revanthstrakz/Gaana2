package com.moengage.core;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.delight.pushlibrary.R;
import com.moe.pushlibrary.models.GeoLocation;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import java.util.HashMap;

public class IntegrationVerificationNetworkCallTask extends SDKTask {
    private static final String RESULT_EXTRA_BUTTON_ID = "button_id";
    private static final String RESULT_EXTRA_MESSAGE = "message";
    private TASK_TYPE task_type;

    public enum TASK_TYPE {
        REGISTER_DEVICE,
        UNREGISTER_DEVICE
    }

    public String getTaskTag() {
        return SDKTask.TAG_INTEGRATION_VERIFICATION_NETWORK_TASK;
    }

    public boolean isSynchronous() {
        return true;
    }

    public IntegrationVerificationNetworkCallTask(Context context, TASK_TYPE task_type) {
        super(context);
        this.task_type = task_type;
    }

    public TaskResult execute() {
        try {
            switch (this.task_type) {
                case REGISTER_DEVICE:
                    registerDevice();
                    break;
                case UNREGISTER_DEVICE:
                    unregisterDevice();
                    break;
                default:
                    Logger.e("IntegrationVerificationNetworkCallTask: invalid case");
                    break;
            }
        } catch (Exception e) {
            Logger.f("IntegrationVerificationNetworkCallTask: Exception ", e);
        }
        return this.mTaskResult;
    }

    private void unregisterDevice() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MoEUtils.getAPIRoute(this.mContext));
        stringBuilder.append("/integration/unregister_device");
        if (APIManager.registerUnregisterDeviceForIntegrationVerification(this.mContext, stringBuilder.toString(), null)) {
            ConfigurationProvider.getInstance(this.mContext).setVerificationRegistration(false);
            createResult(true, "Device unregistered successfully. Click on the button to re-register.", R.id.retryButton);
            return;
        }
        createResult(false, "Device could not be unregistered. Click on the button to retry.", R.id.unregisterButton);
    }

    private void registerDevice() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MoEUtils.getAPIRoute(this.mContext));
        stringBuilder.append("/integration/register_device");
        String stringBuilder2 = stringBuilder.toString();
        GeoLocation lastKnownUserLocation = ConfigurationProvider.getInstance(this.mContext).getLastKnownUserLocation();
        if (lastKnownUserLocation == null) {
            lastKnownUserLocation = new GeoLocation(0.0d, 0.0d);
        }
        HashMap hashMap = new HashMap();
        hashMap.put(LocationConstants.PARAM_LAT, String.valueOf(lastKnownUserLocation.latitude));
        hashMap.put(LocationConstants.PARAM_LNG, String.valueOf(lastKnownUserLocation.longitude));
        hashMap.put("manufacturer", Build.MANUFACTURER);
        if (APIManager.registerUnregisterDeviceForIntegrationVerification(this.mContext, stringBuilder2, hashMap)) {
            ConfigurationProvider.getInstance(this.mContext).setVerificationRegistrationTime(System.currentTimeMillis());
            ConfigurationProvider.getInstance(this.mContext).setVerificationRegistration(true);
            createResult(true, "Device registered successfully. Click on the button to unregister.", R.id.unregisterButton);
            return;
        }
        createResult(false, "Device could not be registered. Click on the button to retry.", R.id.retryButton);
    }

    private void createResult(boolean z, String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        bundle.putInt(RESULT_EXTRA_BUTTON_ID, i);
        this.mTaskResult.setIsSuccess(z);
        this.mTaskResult.setPayload(bundle);
    }
}
