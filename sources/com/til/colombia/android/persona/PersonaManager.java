package com.til.colombia.android.persona;

import android.content.Context;
import com.til.colombia.android.internal.a;
import com.til.colombia.dmp.android.DmpManager;

@Deprecated
public final class PersonaManager {
    private static final String AUD_PREF_KEY = "audiences";
    private static PersonaManager personaManager;

    @Deprecated
    public final void createCC(Context context) {
    }

    @Deprecated
    public final void createCC(Context context, int i, Object obj) {
    }

    @Deprecated
    public final String getLotameAudiences() {
        return null;
    }

    @Deprecated
    public final void startSession() {
    }

    @Deprecated
    public final void updateLotameAuds() {
    }

    private PersonaManager() {
        DmpManager.initialize(a.a());
    }

    public static PersonaManager getInstance() {
        if (personaManager == null) {
            personaManager = new PersonaManager();
        }
        return personaManager;
    }

    public final void completeSession() {
        if (DmpManager.getInstance() != null) {
            DmpManager.getInstance().completeSession();
        }
    }

    public final void addEvents(String str, String str2) {
        if (DmpManager.getInstance() != null) {
            DmpManager.getInstance().addEvents(str, str2);
        }
    }

    @Deprecated
    public final String[] getLotameAudiencesArray() {
        return new String[0];
    }

    @Deprecated
    public final void updateAuds() {
        if (DmpManager.getInstance() != null) {
            DmpManager.getInstance().updateAuds();
        }
    }

    public final String getAuds() {
        return DmpManager.getInstance() != null ? DmpManager.getInstance().getAuds() : null;
    }

    public final String[] getAudsArray() {
        if (DmpManager.getInstance() != null) {
            return DmpManager.getInstance().getAudsArray();
        }
        return new String[0];
    }
}
