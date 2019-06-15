package com.player_framework;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import com.constants.Constants;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.f;
import com.player_framework.PlayerConstants.PauseReasons;
import java.util.Date;

public class MediaButtonIntentReceiver extends BroadcastReceiver {
    static long a;

    @TargetApi(9)
    public void onReceive(Context context, Intent intent) {
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent != null && keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 79) {
                switch (keyCode) {
                    case 85:
                        break;
                    case 86:
                        o.d(context);
                        break;
                    case 87:
                    case 90:
                        if (!f.v().t()) {
                            o.f(context);
                            break;
                        }
                        return;
                    case 88:
                    case 89:
                        if (!f.v().t()) {
                            if (PlayerManager.a(context).m() == PlayerType.GAANA) {
                                o.e(context);
                                break;
                            }
                        }
                        return;
                        break;
                    default:
                        switch (keyCode) {
                            case 126:
                                break;
                            case 127:
                                o.a(context, PauseReasons.MEDIA_BUTTON_TAP);
                                break;
                        }
                        break;
                }
            }
            if (!(f.v().w() || Constants.dc || f.v().t())) {
                if (a == 0) {
                    o.b(context, PauseReasons.MEDIA_BUTTON_TAP);
                } else if (new Date().getTime() - a > 500) {
                    o.b(context, PauseReasons.MEDIA_BUTTON_TAP);
                } else {
                    o.f(context);
                }
                a = new Date().getTime();
            }
            if (isOrderedBroadcast()) {
                abortBroadcast();
            }
        }
    }
}
