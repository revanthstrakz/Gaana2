package com.til.colombia.dmp.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public class PersonaListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            if (Utils.getIntPreferences(context, Utils.DMP_PREF, Utils.PERSONA_DISABLE) != 1 && !Utils.getBooleanPreferences(context, Utils.DMP_PREF, Utils.PERSONA_SERVER_DISABLE) && intent != null) {
                Bundle extras = intent.getExtras();
                String str = null;
                if (intent.getData() != null) {
                    str = intent.getData().getEncodedSchemeSpecificPart();
                }
                if (str != null && Utils.getIntPreferences(context, Utils.DMP_PREF, Utils.IS_FIRST_PERSONA_EVENT_REPORTED) == 1) {
                    String str2;
                    String str3;
                    StringBuilder stringBuilder;
                    if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                        if (extras != null && extras.containsKey("android.intent.extra.REPLACING") && extras.getBoolean("android.intent.extra.REPLACING")) {
                            if (TextUtils.isEmpty(Utils.getPreferences(context, Utils.DMP_PREF, Utils.UPDATED_APPS))) {
                                Utils.setPreferences(context, Utils.DMP_PREF, Utils.UPDATED_APPS, str);
                            } else {
                                str2 = Utils.DMP_PREF;
                                str3 = Utils.UPDATED_APPS;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(Utils.getPreferences(context, Utils.DMP_PREF, Utils.UPDATED_APPS));
                                stringBuilder.append(",");
                                stringBuilder.append(str);
                                Utils.setPreferences(context, str2, str3, stringBuilder.toString());
                            }
                        } else if (TextUtils.isEmpty(Utils.getPreferences(context, Utils.DMP_PREF, Utils.INSTALLED_APPS))) {
                            Utils.setPreferences(context, Utils.DMP_PREF, Utils.INSTALLED_APPS, str);
                        } else {
                            str2 = Utils.DMP_PREF;
                            str3 = Utils.INSTALLED_APPS;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(Utils.getPreferences(context, Utils.DMP_PREF, Utils.INSTALLED_APPS));
                            stringBuilder.append(",");
                            stringBuilder.append(str);
                            Utils.setPreferences(context, str2, str3, stringBuilder.toString());
                        }
                    } else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                        if (extras == null || !extras.containsKey("android.intent.extra.REPLACING") || !extras.getBoolean("android.intent.extra.REPLACING")) {
                            if (TextUtils.isEmpty(Utils.getPreferences(context, Utils.DMP_PREF, Utils.UNINSTALLED_APPS))) {
                                Utils.setPreferences(context, Utils.DMP_PREF, Utils.UNINSTALLED_APPS, str);
                            } else {
                                str2 = Utils.DMP_PREF;
                                str3 = Utils.UNINSTALLED_APPS;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(Utils.getPreferences(context, Utils.DMP_PREF, Utils.UNINSTALLED_APPS));
                                stringBuilder.append(",");
                                stringBuilder.append(str);
                                Utils.setPreferences(context, str2, str3, stringBuilder.toString());
                            }
                        } else {
                            return;
                        }
                    }
                }
                DmpManager.initialize(context);
                if (DmpManager.getInstance() != null) {
                    DmpManager.getInstance().process(context);
                }
            }
        } catch (Exception unused) {
        }
    }
}
