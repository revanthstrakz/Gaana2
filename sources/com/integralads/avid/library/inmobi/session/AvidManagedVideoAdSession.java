package com.integralads.avid.library.inmobi.session;

import com.integralads.avid.library.inmobi.AvidManager;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener;

public class AvidManagedVideoAdSession extends AbstractAvidManagedAdSession {
    public AvidVideoPlaybackListener getAvidVideoPlaybackListener() {
        InternalAvidManagedVideoAdSession internalAvidManagedVideoAdSession = (InternalAvidManagedVideoAdSession) AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        return internalAvidManagedVideoAdSession != null ? internalAvidManagedVideoAdSession.getAvidVideoPlaybackListener() : null;
    }
}
