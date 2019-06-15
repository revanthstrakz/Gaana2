package com.managers;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.services.d;
import com.services.l.af;
import com.services.l.s;
import com.services.n;
import com.utilities.Util;
import com.utilities.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONObject;

public class w {
    private static w a;
    private GaanaApplication b;
    private d c = null;

    public interface a {
        void onLanguagesFetched(Languages languages);
    }

    public interface b {
        void onLanguageSavedOnServer(String str, boolean z);
    }

    public static w a(GaanaApplication gaanaApplication) {
        if (a == null) {
            a = new w();
        }
        a.b = gaanaApplication;
        a.c = d.a();
        return a;
    }

    public void a(Context context, final a aVar, boolean z) {
        if (!Util.j(context)) {
            ap.a().f(context);
            if (aVar != null) {
                aVar.onLanguagesFetched(null);
            }
        } else if (GaanaApplication.getInstance().isAppInOfflineMode()) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).displayFeatureNotAvailableOfflineDialog(context.getString(R.string.languauge));
                if (aVar != null) {
                    aVar.onLanguagesFetched(null);
                }
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/user.php?type=get_user_language_setting&user_device_lang=");
            stringBuilder.append(f.a());
            String stringBuilder2 = stringBuilder.toString();
            UserInfo currentUser = this.b.getCurrentUser();
            if (currentUser != null && currentUser.getLoginStatus()) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append("&token=");
                stringBuilder3.append(currentUser.getAuthToken());
                stringBuilder2 = stringBuilder3.toString();
            }
            URLManager uRLManager = new URLManager();
            uRLManager.a(Languages.class);
            uRLManager.a(stringBuilder2);
            uRLManager.a(Priority.HIGH);
            uRLManager.c(Boolean.valueOf(z));
            uRLManager.i(false);
            i.a().a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    Languages languages = businessObject != null ? (Languages) businessObject : null;
                    if (languages != null) {
                        ArrayList arrListBusinessObj = languages.getArrListBusinessObj();
                        if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                            StringBuilder stringBuilder = new StringBuilder();
                            Iterator it = arrListBusinessObj.iterator();
                            while (it.hasNext()) {
                                Language language = (Language) it.next();
                                if (language.isPrefered() == 1) {
                                    stringBuilder.append(language.getLanguage());
                                    stringBuilder.append(",");
                                }
                            }
                            String stringBuilder2 = stringBuilder.toString();
                            if (!TextUtils.isEmpty(stringBuilder2)) {
                                MoEngage.getInstance().reportLanguagesSelected(stringBuilder2.substring(0, stringBuilder2.length() - 1).substring(0));
                            }
                            w.this.a(languages);
                        }
                    }
                    if (aVar != null) {
                        aVar.onLanguagesFetched(languages);
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (aVar != null) {
                        aVar.onLanguagesFetched(null);
                    }
                }
            }, uRLManager);
        }
    }

    public void a(Context context, a aVar) {
        Object a = n.a(this.c.c("PREFERENCE_LANGUAGE_SETTINGS", false));
        if (a instanceof Languages) {
            Languages languages = (Languages) a;
            if (languages == null) {
                a(context, aVar, false);
            } else if (aVar != null) {
                aVar.onLanguagesFetched(languages);
            }
        }
    }

    public void a(final Context context, final af afVar) {
        com.i.d.a(new Runnable() {
            public void run() {
                Object a = n.a(w.this.c.c("PREFERENCE_LANGUAGE_SETTINGS", false));
                if (a instanceof Languages) {
                    Languages languages = (Languages) a;
                    if (languages != null) {
                        String a2 = w.this.b(languages.getArrListBusinessObj());
                        if (afVar != null) {
                            afVar.onRetreivalComplete(a2);
                        }
                    }
                } else if (a == null) {
                    w.this.a(context, new a() {
                        public void onLanguagesFetched(Languages languages) {
                            if (languages != null) {
                                String a = w.this.b(languages.getArrListBusinessObj());
                                if (afVar != null) {
                                    afVar.onRetreivalComplete(a);
                                }
                            }
                        }
                    }, false);
                }
            }
        });
    }

    public ArrayList<?> a(Context context) {
        Object a = n.a(this.c.c("PREFERENCE_LANGUAGE_SETTINGS", false));
        if (a instanceof Languages) {
            Languages languages = (Languages) a;
            if (languages != null && languages.getArrListBusinessObj().size() > 0) {
                return languages.getArrListBusinessObj();
            }
        }
        return null;
    }

    public void a(Context context, final ArrayList<Language> arrayList) {
        if (Util.j(context) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
            String str = "https://api.gaana.com/user.php?type=get_user_language_setting&user_device_lang=";
            UserInfo currentUser = this.b.getCurrentUser();
            if (currentUser != null && currentUser.getLoginStatus()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("&token=");
                stringBuilder.append(currentUser.getAuthToken());
                str = stringBuilder.toString();
            }
            URLManager uRLManager = new URLManager();
            uRLManager.a(Languages.class);
            uRLManager.a(str);
            uRLManager.a(Priority.HIGH);
            uRLManager.c(Boolean.valueOf(true));
            uRLManager.i(false);
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    Languages languages = businessObject != null ? (Languages) businessObject : null;
                    ArrayList arrayList = new ArrayList();
                    if (arrayList != null) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            Language language = (Language) it.next();
                            if (language.isPrefered() == 1) {
                                arrayList.add(language.getLanguage());
                            }
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    if (languages != null) {
                        ArrayList arrListBusinessObj = languages.getArrListBusinessObj();
                        if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                            StringBuilder stringBuilder = new StringBuilder();
                            Iterator it2 = arrListBusinessObj.iterator();
                            while (it2.hasNext()) {
                                Language language2 = (Language) it2.next();
                                if (language2.isPrefered() == 1) {
                                    stringBuilder.append(language2.getLanguage());
                                    stringBuilder.append(",");
                                    arrayList2.add(language2.getLanguage());
                                }
                            }
                            String stringBuilder2 = stringBuilder.toString();
                            if (!TextUtils.isEmpty(stringBuilder2)) {
                                MoEngage.getInstance().reportLanguagesSelected(stringBuilder2.substring(0, stringBuilder2.length() - 1).substring(0));
                            }
                            w.this.a(languages);
                        }
                    }
                    Collections.sort(arrayList2);
                    Collections.sort(arrayList);
                    if (!arrayList2.equals(arrayList)) {
                        w.this.c.a("ONBOARD_LANG_MISMATCH_FOR_LOGGED_IN_USER", true, false);
                    }
                }
            }, uRLManager);
        }
    }

    private void a(Languages languages) {
        this.c.a("PREFERENCE_LANGUAGE_SETTINGS", n.a((Serializable) languages), false);
        if (!TextUtils.isEmpty(languages.getbackPressedMessage())) {
            this.c.a("PREFERENCE_BACKPRESSED_MESSAGE", languages.getbackPressedMessage(), false);
        }
    }

    private void a(ArrayList<Language> arrayList) {
        Languages languages = (Languages) n.a(this.c.c("PREFERENCE_LANGUAGE_SETTINGS", false));
        if (languages instanceof Languages) {
            Serializable serializable = languages;
            if (serializable != null) {
                serializable.setArrList(arrayList);
                this.c.a("PREFERENCE_LANGUAGE_SETTINGS", n.a(serializable), false);
            }
        }
    }

    public void a(final Context context, final ArrayList<Language> arrayList, final b bVar) {
        if (!Util.j(context)) {
            ap.a().f(context);
        } else if (this.b.isAppInOfflineMode()) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).displayFeatureNotAvailableOfflineDialog(context.getResources().getString(R.string.languauge));
            }
        } else {
            String replace = "https://api.gaana.com/user.php?type=set_user_language_setting&language=<languages>".replace("<languages>", b(arrayList));
            UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
            if (currentUser != null && currentUser.getLoginStatus()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(replace);
                stringBuilder.append("&token=");
                stringBuilder.append(currentUser.getAuthToken());
                replace = stringBuilder.toString();
            }
            URLManager uRLManager = new URLManager();
            uRLManager.a(String.class);
            uRLManager.a(replace);
            uRLManager.a(Priority.HIGH);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    try {
                        JSONObject jSONObject = new JSONObject((String) obj);
                        String string = jSONObject.getString("message");
                        boolean z = true;
                        if (jSONObject.getInt("status") == 1) {
                            w.this.a(arrayList);
                            q.a().f();
                            if (arrayList != null && arrayList.size() > 0) {
                                StringBuilder stringBuilder = new StringBuilder();
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    Language language = (Language) it.next();
                                    if (language.isPrefered() == 1) {
                                        stringBuilder.append(language.getLanguage());
                                        stringBuilder.append(",");
                                    }
                                }
                                String stringBuilder2 = stringBuilder.toString();
                                if (!TextUtils.isEmpty(stringBuilder2)) {
                                    stringBuilder2 = stringBuilder2.substring(0, stringBuilder2.length() - 1);
                                    MoEngage.getInstance().reportLanguagesSelected(stringBuilder2);
                                    u.a().a("Settings", "Language Selected", stringBuilder2);
                                }
                            }
                        } else {
                            z = false;
                        }
                        if (bVar != null) {
                            bVar.onLanguageSavedOnServer(string, z);
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                        if (bVar != null) {
                            bVar.onLanguageSavedOnServer(context.getResources().getString(R.string.error_updating_languages), false);
                        }
                    }
                }
            }, uRLManager);
        }
    }

    private String b(ArrayList<Language> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Language language = (Language) it.next();
            if (language.isPrefered() == 1) {
                stringBuilder.append(language.getLanguage());
                stringBuilder.append(",");
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2 != null && stringBuilder2.length() > 2) {
            stringBuilder2 = stringBuilder2.substring(0, stringBuilder2.length() - 1);
        }
        return stringBuilder2;
    }
}
