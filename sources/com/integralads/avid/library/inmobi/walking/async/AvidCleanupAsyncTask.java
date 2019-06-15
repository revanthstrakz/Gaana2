package com.integralads.avid.library.inmobi.walking.async;

import com.integralads.avid.library.inmobi.walking.async.AvidAsyncTask.StateProvider;

public class AvidCleanupAsyncTask extends AvidAsyncTask {
    public AvidCleanupAsyncTask(StateProvider stateProvider) {
        super(stateProvider);
    }

    /* Access modifiers changed, original: protected|varargs */
    public String doInBackground(Object... objArr) {
        this.stateProvider.setPreviousState(null);
        return null;
    }
}
