package com.utilities;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.b.a;
import com.constants.Constants;
import com.gaana.R;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.p;
import com.services.l.aa;
import java.util.Locale;

public class f {
    protected static Locale a = Locale.getDefault();
    static aa b;
    private static SharedPreferences c;

    public static void a(ContextWrapper contextWrapper) {
        c = PreferenceManager.getDefaultSharedPreferences(contextWrapper);
        a((Context) contextWrapper, "PREFERENCE_APP_DISPLAY_LANGUAGE", c);
    }

    public static void a(Context context, String str, SharedPreferences sharedPreferences) {
        a(context, sharedPreferences.getString("PREFERENCE_APP_DISPLAY_LANGUAGE", ""));
    }

    public static void a(Context context, String str) {
        a(context, str, false);
    }

    public static void a(Context context, String str, aa aaVar) {
        b = aaVar;
        a(context, str, false);
    }

    public static void a(final Context context, final String str, boolean z) {
        if (str != null || z) {
            try {
                Locale locale;
                if (str.equals("")) {
                    locale = new Locale("en", "US");
                    a.a(new a.a().a("fonts/Roboto-Regular.ttf").a((int) R.attr.fontPath).a());
                    a(context, str, locale);
                    if (b != null) {
                        b.onFontRetrieved(null);
                        b = null;
                        return;
                    }
                    return;
                }
                if (!(str.equals("hindi") || str.equals("Hindi"))) {
                    if (!str.equalsIgnoreCase("hi")) {
                        if (!(str.equals("english") || str.equals("English"))) {
                            if (!str.equalsIgnoreCase("en")) {
                                if (!(str.equals("Gujarati") || str.equals("gujarati"))) {
                                    if (!str.equalsIgnoreCase("gu ")) {
                                        if (!(str.equals("Bengali") || str.equals("bengali"))) {
                                            if (!str.equalsIgnoreCase("bn")) {
                                                if (!(str.equals("Kannada") || str.equals("kannada"))) {
                                                    if (!str.equalsIgnoreCase("kn")) {
                                                        if (!(str.equals("Malayalam") || str.equals("malayalam"))) {
                                                            if (!str.equalsIgnoreCase("ml")) {
                                                                if (!(str.equals("Oriya") || str.equals("oriya"))) {
                                                                    if (!str.equalsIgnoreCase("or")) {
                                                                        if (!(str.equals("Punjabi") || str.equals("punjabi"))) {
                                                                            if (!str.equalsIgnoreCase("pa")) {
                                                                                if (!(str.equals("Tamil") || str.equals("tamil"))) {
                                                                                    if (!str.equalsIgnoreCase("ta")) {
                                                                                        if (!(str.equals("Telugu") || str.equals("telugu"))) {
                                                                                            if (!str.equalsIgnoreCase("te")) {
                                                                                                if (!(str.equals("Marathi") || str.equals("marathi"))) {
                                                                                                    if (!str.equalsIgnoreCase("mr")) {
                                                                                                        if (!(str.equals("Bhojpuri") || str.equals("bhojpuri"))) {
                                                                                                            if (!str.equalsIgnoreCase("hi")) {
                                                                                                                locale = new Locale("en", "US");
                                                                                                                a.a(new a.a().a("fonts/Roboto-Regular.ttf").a((int) R.attr.fontPath).a());
                                                                                                                a(context, str, locale);
                                                                                                                if (b != null) {
                                                                                                                    b.onFontRetrieved(null);
                                                                                                                    b = null;
                                                                                                                    return;
                                                                                                                }
                                                                                                                return;
                                                                                                            }
                                                                                                        }
                                                                                                        p.a().a("Mangal", new aa() {
                                                                                                            public void onFontRetrieved(Typeface typeface) {
                                                                                                                Locale locale = new Locale("hi", "IN");
                                                                                                                a.a(new a.a().a("Mangal").a((int) R.attr.fontPath).a());
                                                                                                                f.a(context, str, locale);
                                                                                                                if (f.b != null) {
                                                                                                                    f.b.onFontRetrieved(typeface);
                                                                                                                    f.b = null;
                                                                                                                }
                                                                                                            }

                                                                                                            public void onError(String str) {
                                                                                                                if (f.b != null) {
                                                                                                                    f.b.onError(str);
                                                                                                                    f.b = null;
                                                                                                                }
                                                                                                            }
                                                                                                        }, new String[0]);
                                                                                                        return;
                                                                                                    }
                                                                                                }
                                                                                                p.a().a("Mangal", new aa() {
                                                                                                    public void onFontRetrieved(Typeface typeface) {
                                                                                                        Locale locale = new Locale("mr", "IN");
                                                                                                        a.a(new a.a().a("Mangal").a((int) R.attr.fontPath).a());
                                                                                                        f.a(context, str, locale);
                                                                                                        if (f.b != null) {
                                                                                                            f.b.onFontRetrieved(typeface);
                                                                                                            f.b = null;
                                                                                                        }
                                                                                                    }

                                                                                                    public void onError(String str) {
                                                                                                        if (f.b != null) {
                                                                                                            f.b.onError(str);
                                                                                                            f.b = null;
                                                                                                        }
                                                                                                    }
                                                                                                }, new String[0]);
                                                                                                return;
                                                                                            }
                                                                                        }
                                                                                        p.a().a("Telugu", new aa() {
                                                                                            public void onFontRetrieved(Typeface typeface) {
                                                                                                Locale locale = new Locale("te", "IN");
                                                                                                a.a(new a.a().a("Telugu").a((int) R.attr.fontPath).a());
                                                                                                f.a(context, str, locale);
                                                                                                if (f.b != null) {
                                                                                                    f.b.onFontRetrieved(typeface);
                                                                                                    f.b = null;
                                                                                                }
                                                                                            }

                                                                                            public void onError(String str) {
                                                                                                if (f.b != null) {
                                                                                                    f.b.onError(str);
                                                                                                    f.b = null;
                                                                                                }
                                                                                            }
                                                                                        }, new String[0]);
                                                                                        return;
                                                                                    }
                                                                                }
                                                                                p.a().a("Tamil", new aa() {
                                                                                    public void onFontRetrieved(Typeface typeface) {
                                                                                        Locale locale = new Locale("ta", "IN");
                                                                                        a.a(new a.a().a("Tamil").a((int) R.attr.fontPath).a());
                                                                                        f.a(context, str, locale);
                                                                                        if (f.b != null) {
                                                                                            f.b.onFontRetrieved(typeface);
                                                                                            f.b = null;
                                                                                        }
                                                                                    }

                                                                                    public void onError(String str) {
                                                                                        if (f.b != null) {
                                                                                            f.b.onError(str);
                                                                                            f.b = null;
                                                                                        }
                                                                                    }
                                                                                }, new String[0]);
                                                                                return;
                                                                            }
                                                                        }
                                                                        p.a().a("Punjabi", new aa() {
                                                                            public void onFontRetrieved(Typeface typeface) {
                                                                                Locale locale = new Locale("pa", "IN");
                                                                                a.a(new a.a().a("Punjabi").a((int) R.attr.fontPath).a());
                                                                                f.a(context, str, locale);
                                                                                if (f.b != null) {
                                                                                    f.b.onFontRetrieved(typeface);
                                                                                    f.b = null;
                                                                                }
                                                                            }

                                                                            public void onError(String str) {
                                                                                if (f.b != null) {
                                                                                    f.b.onError(str);
                                                                                    f.b = null;
                                                                                }
                                                                            }
                                                                        }, new String[0]);
                                                                        return;
                                                                    }
                                                                }
                                                                p.a().a("Oriya", new aa() {
                                                                    public void onFontRetrieved(Typeface typeface) {
                                                                        Locale locale = new Locale("or", "IN");
                                                                        a.a(new a.a().a("Oriya").a((int) R.attr.fontPath).a());
                                                                        f.a(context, str, locale);
                                                                        if (f.b != null) {
                                                                            f.b.onFontRetrieved(typeface);
                                                                            f.b = null;
                                                                        }
                                                                    }

                                                                    public void onError(String str) {
                                                                        if (f.b != null) {
                                                                            f.b.onError(str);
                                                                            f.b = null;
                                                                        }
                                                                    }
                                                                }, new String[0]);
                                                                return;
                                                            }
                                                        }
                                                        p.a().a("Malayalam", new aa() {
                                                            public void onFontRetrieved(Typeface typeface) {
                                                                Locale locale = new Locale("ml", "IN");
                                                                a.a(new a.a().a("Malayalam").a((int) R.attr.fontPath).a());
                                                                f.a(context, str, locale);
                                                                if (f.b != null) {
                                                                    f.b.onFontRetrieved(typeface);
                                                                    f.b = null;
                                                                }
                                                            }

                                                            public void onError(String str) {
                                                                if (f.b != null) {
                                                                    f.b.onError(str);
                                                                    f.b = null;
                                                                }
                                                            }
                                                        }, new String[0]);
                                                        return;
                                                    }
                                                }
                                                p.a().a("Kannada", new aa() {
                                                    public void onFontRetrieved(Typeface typeface) {
                                                        Locale locale = new Locale("kn", "IN");
                                                        a.a(new a.a().a("Kannada").a((int) R.attr.fontPath).a());
                                                        f.a(context, str, locale);
                                                        if (f.b != null) {
                                                            f.b.onFontRetrieved(typeface);
                                                            f.b = null;
                                                        }
                                                    }

                                                    public void onError(String str) {
                                                        if (f.b != null) {
                                                            f.b.onError(str);
                                                            f.b = null;
                                                        }
                                                    }
                                                }, new String[0]);
                                                return;
                                            }
                                        }
                                        p.a().a("Mangal", new aa() {
                                            public void onFontRetrieved(Typeface typeface) {
                                                Locale locale = new Locale("bn", "IN");
                                                a.a(new a.a().a("Mangal").a((int) R.attr.fontPath).a());
                                                f.a(context, str, locale);
                                                if (f.b != null) {
                                                    f.b.onFontRetrieved(typeface);
                                                    f.b = null;
                                                }
                                            }

                                            public void onError(String str) {
                                                if (f.b != null) {
                                                    f.b.onError(str);
                                                    f.b = null;
                                                }
                                            }
                                        }, new String[0]);
                                        return;
                                    }
                                }
                                p.a().a("Gujarati", new aa() {
                                    public void onFontRetrieved(Typeface typeface) {
                                        Locale locale = new Locale("gu", "US");
                                        a.a(new a.a().a("Gujarati").a((int) R.attr.fontPath).a());
                                        f.a(context, str, locale);
                                        if (f.b != null) {
                                            f.b.onFontRetrieved(typeface);
                                            f.b = null;
                                        }
                                    }

                                    public void onError(String str) {
                                        if (f.b != null) {
                                            f.b.onError(str);
                                            f.b = null;
                                        }
                                    }
                                }, new String[0]);
                                return;
                            }
                        }
                        locale = new Locale("en", "US");
                        a.a(new a.a().a("fonts/Roboto-Regular.ttf").a((int) R.attr.fontPath).a());
                        a(context, str, locale);
                        if (b != null) {
                            b.onFontRetrieved(null);
                            b = null;
                            return;
                        }
                        return;
                    }
                }
                p.a().a("Mangal", new aa() {
                    public void onFontRetrieved(Typeface typeface) {
                        Locale locale = new Locale("hi", "IN");
                        a.a(new a.a().a("Mangal").a((int) R.attr.fontPath).a());
                        f.a(context, str, locale);
                        if (f.b != null) {
                            f.b.onFontRetrieved(typeface);
                            f.b = null;
                        }
                    }

                    public void onError(String str) {
                        if (f.b != null) {
                            f.b.onError(str);
                            f.b = null;
                        }
                    }
                }, new String[0]);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static void a(Context context, String str, Locale locale) {
        Constants.bV = str;
        try {
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();
            if (d.f()) {
                configuration.setLocales(new LocaleList(new Locale[]{locale}));
            } else {
                configuration.locale = locale;
            }
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            if (c == null) {
                c = PreferenceManager.getDefaultSharedPreferences(context);
            }
            Editor edit = c.edit();
            edit.putString("PREFERENCE_APP_DISPLAY_LANGUAGE", str);
            edit.putString("PREFERENCE_APP_DISPLAY_LANGUAGE_CODE", locale.getLanguage());
            edit.apply();
            if (!TextUtils.isEmpty(str)) {
                Locale.setDefault(locale);
                Util.e = str;
                Util.f = locale;
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void a(Context context) {
        try {
            if (c == null) {
                c = PreferenceManager.getDefaultSharedPreferences(context);
            }
            String string = c.getString("PREFERENCE_APP_DISPLAY_LANGUAGE_CODE", "en");
            if (!TextUtils.isEmpty(string)) {
                Locale locale = new Locale(string);
                Resources resources = context.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                Configuration configuration = resources.getConfiguration();
                if (VERSION.SDK_INT >= 24) {
                    configuration.setLocale(locale);
                } else {
                    configuration.locale = locale;
                }
                resources.updateConfiguration(configuration, displayMetrics);
                if (!TextUtils.isEmpty(string)) {
                    Locale.setDefault(locale);
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static String b(Context context) {
        if (c == null) {
            c = PreferenceManager.getDefaultSharedPreferences(context);
        }
        String string = c.getString("PREFERENCE_APP_DISPLAY_LANGUAGE", "");
        return TextUtils.isEmpty(string) ? a() : string;
    }

    public static String a() {
        String language;
        if (d.f()) {
            language = LocaleList.getDefault().get(0).getLanguage();
        } else {
            language = Locale.getDefault().getLanguage();
        }
        if (language.equals("hindi") || language.equals("Hindi") || language.equalsIgnoreCase("hi")) {
            return "Hindi";
        }
        if (language.equals("english") || language.equals("English") || language.equalsIgnoreCase("en")) {
            return "English";
        }
        if (language.equals("Gujarati") || language.equals("gujarati") || language.equalsIgnoreCase("gu ")) {
            return "Gujrati";
        }
        if (language.equals("Bengali") || language.equals("bengali") || language.equalsIgnoreCase("bn")) {
            return "Bengali";
        }
        if (language.equals("Kannada") || language.equals("kannada") || language.equalsIgnoreCase("kn")) {
            return "Kannada";
        }
        if (language.equals("Malayalam") || language.equals("malayalam") || language.equalsIgnoreCase("ml")) {
            return "Malayalam";
        }
        if (language.equals("Oriya") || language.equals("oriya") || language.equalsIgnoreCase("or")) {
            return "Oriya";
        }
        if (language.equals("Punjabi") || language.equals("punjabi") || language.equalsIgnoreCase("pa")) {
            return "Punjabi";
        }
        if (language.equals("Tamil") || language.equals("tamil") || language.equalsIgnoreCase("ta")) {
            return "Tamil";
        }
        if (language.equals("Telugu") || language.equals("telugu") || language.equalsIgnoreCase("te")) {
            return "Telugu";
        }
        if (language.equals("Marathi") || language.equals("marathi") || language.equalsIgnoreCase("mr")) {
            return "Marathi";
        }
        return (language.equals("Bhojpuri") || language.equals("bhojpuri") || language.equalsIgnoreCase("hi")) ? "Bhojpuri" : "English";
    }

    public static Locale a(String str) {
        if (str.equals("hindi") || str.equals("Hindi") || str.equalsIgnoreCase("hi")) {
            return new Locale("hi", "IN");
        }
        if (str.equals("english") || str.equals("English") || str.equalsIgnoreCase("en")) {
            return new Locale("en", "US");
        }
        if (str.equals("Gujarati") || str.equals("gujarati") || str.equalsIgnoreCase("gu ")) {
            return new Locale("gu", "IN");
        }
        if (str.equals("Bengali") || str.equals("bengali") || str.equalsIgnoreCase("bn")) {
            return new Locale("bn", "IN");
        }
        if (str.equals("Kannada") || str.equals("kannada") || str.equalsIgnoreCase("kn")) {
            return new Locale("kn", "IN");
        }
        if (str.equals("Malayalam") || str.equals("malayalam") || str.equalsIgnoreCase("ml")) {
            return new Locale("ml", "IN");
        }
        if (str.equals("Oriya") || str.equals("oriya") || str.equalsIgnoreCase("or")) {
            return new Locale("or", "IN");
        }
        if (str.equals("Punjabi") || str.equals("punjabi") || str.equalsIgnoreCase("pa")) {
            return new Locale("pa", "IN");
        }
        if (str.equals("Tamil") || str.equals("tamil") || str.equalsIgnoreCase("ta")) {
            return new Locale("ta", "IN");
        }
        if (str.equals("Telugu") || str.equals("telugu") || str.equalsIgnoreCase("te")) {
            return new Locale("te", "IN");
        }
        if (str.equals("Marathi") || str.equals("marathi") || str.equalsIgnoreCase("mr")) {
            return new Locale("mr", "IN");
        }
        if (str.equals("Bhojpuri") || str.equals("bhojpuri") || str.equalsIgnoreCase("hi")) {
            return new Locale("hi", "IN");
        }
        return null;
    }
}
