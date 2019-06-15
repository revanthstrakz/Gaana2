package com.managers;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.constants.Constants;
import com.facebook.places.model.PlaceFields;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.managers.TaskManager.TaskListner;
import com.library.util.Serializer;
import com.services.d;
import com.services.i;
import com.services.j;
import com.utilities.Util;
import com.utilities.h;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class y implements LocationListener {
    private static y a;
    private static int c;
    private LocationManager b;
    private d d;
    private Context e = GaanaApplication.getContext();
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private Location i = null;
    private String j = "";
    private String k = "";
    private String l = "";
    private String m = "";

    public interface a {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    private y(boolean z) {
        this.f = z;
        this.d = d.a();
        this.b = (LocationManager) this.e.getSystemService(PlaceFields.LOCATION);
        a(null);
    }

    public static y a() {
        if (a == null) {
            a = new y(false);
        }
        return a;
    }

    public static JSONObject a(String str) {
        InputStream content;
        String str2 = "";
        try {
            content = new DefaultHttpClient().execute(new HttpPost(str)).getEntity().getContent();
        } catch (Exception unused) {
            content = null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(readLine);
                stringBuilder2.append("\n");
                stringBuilder.append(stringBuilder2.toString());
            }
            content.close();
            str = stringBuilder.toString();
        } catch (Exception unused2) {
            str = str2;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused3) {
            return null;
        }
    }

    public void a(a aVar) {
        a(aVar, null);
    }

    private void a(final a aVar, String str) {
        if (this.i != null) {
            this.i = null;
        }
        if (!this.g && h.a(this.e)) {
            this.h = false;
            if (this.b.isProviderEnabled("network")) {
                this.h = true;
                this.b.requestLocationUpdates("network", 0, 0.0f, this);
                this.i = this.b.getLastKnownLocation("network");
            }
            if (this.i == null && this.b.isProviderEnabled("gps")) {
                this.h = true;
                this.b.requestLocationUpdates("gps", 0, 0.0f, this);
                this.i = this.b.getLastKnownLocation("gps");
            }
            com.services.h.a().a(new TaskListner() {
                public void onBackGroundTaskCompleted() {
                }

                public void doBackGroundTask() {
                    y.this.g = true;
                    y.this.a(aVar, y.this.i);
                }
            }, -1);
        }
    }

    private void a(a aVar, Location location) {
        this.m = this.d.c("PREF_CITY_NAME", false);
        if (location != null) {
            a(location, aVar);
            return;
        }
        this.g = false;
        this.j = "";
        this.k = "";
        this.d.b("PREF_COUNTRY_CODE", false);
        this.d.a("PREF_COUNTRY_CODE", "LOCATION_SERVICE_DISABLED", false);
        this.d.b("PREF_CITY_NAME", false);
        this.d.b("PREF_STATE_NAME", false);
        this.d.b("PREF_LAST_RETRIEVAL_DATE", false);
        Constants.cB = "";
        Constants.cC = "";
        Constants.cD = "0";
    }

    private void a(Location location, a aVar) {
        this.m = this.d.c("PREF_CITY_NAME", false);
        String a = a(location);
        if (a != null) {
            Constants.ct = a;
            Constants.cB = this.k;
            Constants.cC = this.j;
            Constants.cD = "1";
            this.d.b("PREF_COUNTRY_CODE", false);
            this.d.a("PREF_COUNTRY_CODE", a, false);
            this.d.b("PREF_CITY_NAME", false);
            this.d.a("PREF_CITY_NAME", this.j, false);
            this.d.b("PREF_STATE_NAME", false);
            this.d.a("PREF_STATE_NAME", this.k, false);
            this.d.b("PREF_LAST_RETRIEVAL_DATE", false);
            this.d.a("PREF_LAST_RETRIEVAL_DATE", Serializer.serialize(new Date()), false);
        }
        this.g = false;
        c();
    }

    private void c() {
        if (this.m == null) {
            this.m = "";
        }
        if (this.j == null) {
            this.j = "";
        }
        if (!TextUtils.isEmpty(this.m) || !TextUtils.isEmpty(this.j)) {
            if ((TextUtils.isEmpty(this.m) || TextUtils.isEmpty(this.j) || !this.m.equalsIgnoreCase(this.j)) && Util.j(this.e)) {
                try {
                    i b = new j().b("https://api.gaana.com/get_content_gps_status.php?gps_last_location=<gps_last_location>&gps_current_location=<gps_current_location>".replace("<gps_last_location>", this.m).replace("<gps_current_location>", this.j).replace(" ", "%20"));
                    if (b.b().booleanValue()) {
                        String a = b.a();
                        if (a != null) {
                            JSONObject jSONObject = new JSONObject(a);
                            if (jSONObject.has("status") && jSONObject.getBoolean("status") && jSONObject.has("content_gps")) {
                                if ("1".equals(jSONObject.getString("content_gps")) && jSONObject.has("content_changed")) {
                                    if ("1".equals(jSONObject.getString("content_changed"))) {
                                        o.a().b();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("exception: ");
                    stringBuilder.append(e.getMessage());
                    Log.e("LocManager", stringBuilder.toString());
                }
            }
        }
    }

    public String b() {
        String c = this.d.c("PREF_COUNTRY_CODE", false);
        if (!this.g && (TextUtils.isEmpty(c) || c.equals("LOCATION_SERVICE_DISABLED") || a(c))) {
            a(null);
        }
        return c;
    }

    public boolean a(int i) {
        if (this.f) {
            return true;
        }
        Date date = (Date) Serializer.deserialize(this.d.c("PREF_LAST_RETRIEVAL_DATE", false));
        if (date != null) {
            if (((int) ((new Date().getTime() - date.getTime()) / 1000)) > i * 60) {
                return true;
            }
            return false;
        } else if (i != 0) {
            return false;
        } else {
            return true;
        }
    }

    public void onLocationChanged(final Location location) {
        if (!this.g) {
            com.i.d.a(new Runnable() {
                public void run() {
                    y.this.a(location, null);
                }
            });
            this.b.removeUpdates(this);
        }
    }

    public void onProviderDisabled(String str) {
        a(null, str);
    }

    public void onProviderEnabled(String str) {
        a(null);
    }

    private String a(Location location) {
        Throwable e;
        String str = "";
        String str2 = "";
        String str3 = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
            stringBuilder.append(location.getLatitude());
            stringBuilder.append(",");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append("&sensor=true");
            JSONObject a = a(stringBuilder.toString());
            if (a.getString("status").equalsIgnoreCase(com.comscore.utils.Constants.RESPONSE_MASK)) {
                JSONArray jSONArray = a.getJSONArray("results").getJSONObject(0).getJSONArray("address_components");
                String str4 = str2;
                String str5 = str3;
                str2 = null;
                str3 = str;
                int i = 0;
                while (i < jSONArray.length()) {
                    try {
                        StringBuilder stringBuilder2;
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        String string = jSONObject.getString("long_name");
                        String string2 = jSONObject.getJSONArray("types").getString(0);
                        if (!(TextUtils.isEmpty(string) && string.equals(null) && string.length() <= 0 && string == "")) {
                            if (string2.equalsIgnoreCase("street_number")) {
                                StringBuilder stringBuilder3 = new StringBuilder();
                                stringBuilder3.append(string);
                                stringBuilder3.append(" ");
                                str3 = stringBuilder3.toString();
                            } else if (string2.equalsIgnoreCase("route")) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(str3);
                                stringBuilder2.append(string);
                                str3 = stringBuilder2.toString();
                            } else if (string2.equalsIgnoreCase("sublocality")) {
                                str4 = string;
                            } else if (string2.equalsIgnoreCase("locality")) {
                                this.j = string;
                            } else if (string2.equalsIgnoreCase("administrative_area_level_1")) {
                                this.k = string;
                            } else if (string2.equalsIgnoreCase("country")) {
                                this.l = string;
                                str2 = jSONObject.getString("short_name");
                            } else if (string2.equalsIgnoreCase("postal_code")) {
                                str5 = string;
                            }
                        }
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str3);
                        stringBuilder2.append(",");
                        stringBuilder2.append(str4);
                        stringBuilder2.append(",");
                        stringBuilder2.append(this.j);
                        stringBuilder2.append(",");
                        stringBuilder2.append(this.k);
                        stringBuilder2.append(",");
                        stringBuilder2.append(this.l);
                        stringBuilder2.append(",");
                        stringBuilder2.append(str5);
                        stringBuilder2.toString();
                        i++;
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            ThrowableExtension.printStackTrace(e);
                            this.f = false;
                            this.g = false;
                            return str2;
                        } catch (Throwable th) {
                            this.f = false;
                            this.g = false;
                        }
                    }
                }
            } else {
                str2 = null;
            }
        } catch (Exception e3) {
            e = e3;
            str2 = null;
            ThrowableExtension.printStackTrace(e);
            this.f = false;
            this.g = false;
            return str2;
        }
        this.f = false;
        this.g = false;
        return str2;
    }
}
