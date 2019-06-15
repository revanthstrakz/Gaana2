package com.moengage.core.integrationVerification;

import android.content.Context;
import android.os.Bundle;
import com.delight.pushlibrary.R;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.IntegrationVerificationNetworkCallTask;
import com.moengage.core.IntegrationVerificationNetworkCallTask.TASK_TYPE;
import com.moengage.core.MoEDispatcher;
import com.moengage.core.MoEUtils;
import com.moengage.core.executor.OnTaskCompleteListener;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskProcessor;
import com.moengage.core.executor.TaskResult;

public class IntegrationVerificationPresenter implements OnTaskCompleteListener, Presenter {
    private Context context;
    private View view;

    public IntegrationVerificationPresenter(Context context, View view) {
        this.context = context;
        this.view = view;
        TaskProcessor.getInstance().setOnTaskCompleteListener(this);
    }

    public void registerDevice() {
        MoEDispatcher.getInstance(this.context).addTaskToQueueBeginning(new IntegrationVerificationNetworkCallTask(this.context, TASK_TYPE.REGISTER_DEVICE));
        this.view.showLoadingDialog("Registering Device for Integration Verification");
    }

    public void unregisterDevice() {
        MoEDispatcher.getInstance(this.context).addTaskToQueueBeginning(new IntegrationVerificationNetworkCallTask(this.context, TASK_TYPE.UNREGISTER_DEVICE));
        this.view.showLoadingDialog("Un-registering Device from Integration Verification");
    }

    public void checkAndRestoreState() {
        MoEUtils.updateTestDeviceState(this.context);
        if (ConfigurationProvider.getInstance(this.context).isDeviceRegisteredForVerification()) {
            this.view.messageAndButton("Device is registered for Integration verification. Click on the button to un-register your device.", R.id.unregisterButton);
        } else {
            registerDevice();
        }
    }

    public void onTaskComplete(String str, TaskResult taskResult) {
        Object obj = (str.hashCode() == -1615285777 && str.equals(SDKTask.TAG_INTEGRATION_VERIFICATION_NETWORK_TASK)) ? null : -1;
        if (obj == null) {
            Bundle bundle = (Bundle) taskResult.getPayload();
            this.view.dismissLoadingDialog();
            this.view.messageAndButton(bundle.getString("message"), bundle.getInt("button_id"));
        }
    }
}
