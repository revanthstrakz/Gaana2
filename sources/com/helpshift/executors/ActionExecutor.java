package com.helpshift.executors;

import android.app.Activity;
import com.helpshift.enums.ACTION_TYPE;
import java.io.Serializable;

public interface ActionExecutor extends Serializable {
    public static final long serialVersionUID = -5804259965557523136L;

    void a(Activity activity, ACTION_TYPE action_type, String str);
}
