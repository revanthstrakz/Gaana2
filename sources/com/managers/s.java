package com.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaThemeModel;
import com.gaana.models.GaanaThemeModel.GaanaTheme;
import com.google.gson.Gson;
import com.i.d;
import com.i.i;
import com.services.l.af;
import com.services.l.r;
import java.util.Iterator;

public class s {
    private static s a;
    private GaanaThemeModel b;

    public static s a() {
        if (a == null) {
            a = new s();
        }
        return a;
    }

    public void a(Context context) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/home/theme");
        uRLManager.a(GaanaThemeModel.class);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                if (obj instanceof GaanaThemeModel) {
                    s.this.b = (GaanaThemeModel) obj;
                    s.this.d();
                    d.a(new Runnable() {
                        public void run() {
                            com.services.d.a().a("PREFERENCE_GAANA_THEME_DATA", new Gson().toJson(s.this.b), false);
                        }
                    });
                    return;
                }
                s.this.b = null;
            }

            public void onErrorResponse(BusinessObject businessObject) {
                s.this.b = null;
            }
        }, uRLManager);
    }

    private void d() {
        if (this.b == null || this.b.getThemeArrayList() == null || this.b.getThemeArrayList().size() <= 0) {
            Constants.du = false;
            return;
        }
        String b = com.services.d.a().b("PREFERENCE_GAANA_THEME_DATA_HASH_VALUE", null, false);
        if (b == null || !b.equalsIgnoreCase(this.b.getHashValue())) {
            b = com.services.d.a().b("PREFERENCE_USER_SELECTED_THEME", null, false);
            Constants.dt = null;
            Iterator it = this.b.getThemeArrayList().iterator();
            GaanaTheme gaanaTheme = null;
            GaanaTheme gaanaTheme2 = gaanaTheme;
            GaanaTheme gaanaTheme3 = gaanaTheme2;
            while (it.hasNext()) {
                GaanaTheme gaanaTheme4 = (GaanaTheme) it.next();
                if (!TextUtils.isEmpty(b) && b.equalsIgnoreCase(gaanaTheme4.getThemeName())) {
                    gaanaTheme2 = gaanaTheme4;
                }
                if (!TextUtils.isEmpty(gaanaTheme4.getThemeDefault()) && gaanaTheme4.getThemeDefault().equalsIgnoreCase("1")) {
                    gaanaTheme3 = gaanaTheme4;
                }
                if (gaanaTheme4.isSponsored()) {
                    gaanaTheme = gaanaTheme4;
                }
            }
            int accountType = GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData() != null ? GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() : 1;
            u a;
            StringBuilder stringBuilder;
            if (accountType == 0 || accountType == 1) {
                if (gaanaTheme != null) {
                    Constants.dt = gaanaTheme;
                    Constants.du = true;
                    a = u.a();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("sponsored - ");
                    stringBuilder.append(Constants.dt.getThemeName());
                    a.a(24, stringBuilder.toString());
                } else if (gaanaTheme2 != null) {
                    Constants.dt = gaanaTheme2;
                    Constants.du = true;
                    a = u.a();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("userselected - ");
                    stringBuilder.append(Constants.dt.getThemeName());
                    a.a(24, stringBuilder.toString());
                } else if (gaanaTheme3 != null) {
                    Constants.dt = gaanaTheme3;
                    Constants.du = true;
                    a = u.a();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("default - ");
                    stringBuilder.append(Constants.dt.getThemeName());
                    a.a(24, stringBuilder.toString());
                } else {
                    Constants.dt = null;
                    Constants.du = false;
                }
            } else if (accountType != 3 && accountType != 2) {
                Constants.dt = null;
                Constants.du = false;
            } else if (gaanaTheme2 != null && !gaanaTheme2.isSponsored()) {
                Constants.dt = gaanaTheme2;
                Constants.du = true;
                a = u.a();
                stringBuilder = new StringBuilder();
                stringBuilder.append("userselected - ");
                stringBuilder.append(Constants.dt.getThemeName());
                a.a(24, stringBuilder.toString());
            } else if (gaanaTheme3 == null) {
                Constants.dt = null;
                Constants.du = false;
            } else if (gaanaTheme == null || !(gaanaTheme == null || gaanaTheme3.getThemeName().equalsIgnoreCase(gaanaTheme.getThemeName()))) {
                Constants.dt = gaanaTheme3;
                Constants.du = true;
                a = u.a();
                stringBuilder = new StringBuilder();
                stringBuilder.append("default - ");
                stringBuilder.append(Constants.dt.getThemeName());
                a.a(24, stringBuilder.toString());
            } else {
                Constants.dt = null;
                Constants.du = false;
            }
            d.a(new Runnable() {
                public void run() {
                    com.services.d.a().a("PREFERENCE_CURRENT_THEME", Constants.dt != null ? new Gson().toJson(Constants.dt) : null, false);
                    com.services.d.a().a("PREFERENCE_THEME_MODE_ON_2", Constants.du, false);
                    com.services.d.a().a("PREFERENCE_GAANA_THEME_DATA_HASH_VALUE", s.this.b.getHashValue(), false);
                }
            });
            GaanaApplication.getInstance().setThemeRefreshRequired(true);
        } else {
            boolean b2 = com.services.d.a().b("PREFERENCE_THEME_MODE_ON_2", true, false);
            if (Constants.dt == null || !b2) {
                Constants.du = false;
            } else {
                Constants.du = true;
            }
            com.services.d.a().a("PREFERENCE_THEME_MODE_ON_2", Constants.du, false);
        }
    }

    public GaanaThemeModel b() {
        if (this.b == null) {
            String c = com.services.d.a().c("PREFERENCE_GAANA_THEME_DATA", false);
            if (!(c == null || c.isEmpty())) {
                this.b = (GaanaThemeModel) new Gson().fromJson(c, GaanaThemeModel.class);
            }
            d();
        }
        return this.b;
    }

    public void c() {
        com.services.d.a().a("PREFERENCE_GAANA_THEME_DATA_HASH_VALUE", null, false);
        if (this.b == null) {
            String c = com.services.d.a().c("PREFERENCE_GAANA_THEME_DATA", false);
            if (!(c == null || c.isEmpty())) {
                this.b = (GaanaThemeModel) new Gson().fromJson(c, GaanaThemeModel.class);
            }
        }
        d();
    }

    public void a(final ImageView imageView, boolean z) {
        if (imageView == null || !z) {
            if (imageView != null) {
                imageView.setVisibility(8);
            }
        } else if (a().b() == null || Constants.dt == null) {
            imageView.setVisibility(8);
        } else {
            GaanaTheme gaanaTheme = Constants.dt;
            if (!TextUtils.isEmpty(gaanaTheme.getOverlaySquareArtwork())) {
                i.a().a(gaanaTheme.getOverlaySquareArtwork(), new r() {
                    public void onSuccessfulResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                        imageView.setVisibility(0);
                    }

                    public void onErrorResponse(VolleyError volleyError) {
                        imageView.setVisibility(8);
                    }
                });
            }
        }
    }
}
