package net.hockeyapp.android.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;
import net.hockeyapp.android.j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class k {
    private ArrayList<JSONObject> a;
    private JSONObject b;
    private j c;
    private int d;

    private Object e() {
        return "<hr style='border-top: 1px solid #c8c8c8; border-bottom: 0px; margin: 40px 10px 0px 10px;' />";
    }

    public k(Context context, String str, j jVar) {
        this.c = jVar;
        a(context, str);
        d();
    }

    private void a(Context context, String str) {
        this.b = new JSONObject();
        this.a = new ArrayList();
        this.d = this.c.getCurrentVersionCode();
        try {
            JSONArray jSONArray = new JSONArray(str);
            int currentVersionCode = this.c.getCurrentVersionCode();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Object obj = 1;
                Object obj2 = jSONObject.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION) > currentVersionCode ? 1 : null;
                if (jSONObject.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION) != currentVersionCode || !a(context, jSONObject.getLong(AvidJSONUtil.KEY_TIMESTAMP))) {
                    obj = null;
                }
                if (!(obj2 == null && obj == null)) {
                    this.b = jSONObject;
                    currentVersionCode = jSONObject.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
                }
                this.a.add(jSONObject);
            }
        } catch (NullPointerException | JSONException unused) {
        }
    }

    private void d() {
        Collections.sort(this.a, new Comparator<JSONObject>() {
            /* renamed from: a */
            public int compare(JSONObject jSONObject, JSONObject jSONObject2) {
                try {
                    return jSONObject.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION) > jSONObject2.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION) ? 0 : 0;
                } catch (NullPointerException | JSONException unused) {
                }
            }
        });
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a(this.b, "shortversion", ""));
        stringBuilder.append(" (");
        stringBuilder.append(a(this.b, ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, ""));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @SuppressLint({"SimpleDateFormat"})
    public String b() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(a(this.b, AvidJSONUtil.KEY_TIMESTAMP, 0) * 1000));
    }

    public long c() {
        boolean booleanValue = Boolean.valueOf(a(this.b, "external", InternalLogger.EVENT_PARAM_EXTRAS_FALSE)).booleanValue();
        long a = a(this.b, "appsize", 0);
        return (booleanValue && a == 0) ? -1 : a;
    }

    private static String a(JSONObject jSONObject, String str, String str2) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return str2;
        }
    }

    private static long a(JSONObject jSONObject, String str, long j) {
        try {
            return jSONObject.getLong(str);
        } catch (JSONException unused) {
            return j;
        }
    }

    public String a(boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<body style='padding: 0px 0px 20px 0px'>");
        Iterator it = this.a.iterator();
        int i = 0;
        while (it.hasNext()) {
            JSONObject jSONObject = (JSONObject) it.next();
            if (i > 0) {
                stringBuilder.append(e());
                if (z) {
                    stringBuilder.append(a(i, jSONObject));
                }
            }
            stringBuilder.append(b(i, jSONObject));
            stringBuilder.append(c(i, jSONObject));
            i++;
        }
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }

    private String a(int i, JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        String a = a(jSONObject);
        if (!TextUtils.isEmpty(a)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("<a href='restore:");
            stringBuilder2.append(a);
            stringBuilder2.append("'  style='background: #c8c8c8; color: #000; display: block; float: right; padding: 7px; margin: 0px 10px 10px; text-decoration: none;'>Restore</a>");
            stringBuilder.append(stringBuilder2.toString());
        }
        return stringBuilder.toString();
    }

    private String a(JSONObject jSONObject) {
        try {
            return jSONObject.getString("id");
        } catch (JSONException unused) {
            return "";
        }
    }

    private String b(int i, JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        int b = b(this.b);
        int b2 = b(jSONObject);
        String c = c(jSONObject);
        stringBuilder.append("<div style='padding: 20px 10px 10px;'><strong>");
        if (i == 0) {
            stringBuilder.append("Newest version:");
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Version ");
            stringBuilder2.append(c);
            stringBuilder2.append(" (");
            stringBuilder2.append(b2);
            stringBuilder2.append("): ");
            stringBuilder.append(stringBuilder2.toString());
            if (b2 != b && b2 == this.d) {
                this.d = -1;
                stringBuilder.append("[INSTALLED]");
            }
        }
        stringBuilder.append("</strong></div>");
        return stringBuilder.toString();
    }

    private int b(JSONObject jSONObject) {
        try {
            return jSONObject.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
        } catch (JSONException unused) {
            return 0;
        }
    }

    private String c(JSONObject jSONObject) {
        try {
            return jSONObject.getString("shortversion");
        } catch (JSONException unused) {
            return "";
        }
    }

    private String c(int i, JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        String a = a(jSONObject, "notes", "");
        stringBuilder.append("<div style='padding: 0px 10px;'>");
        if (a.trim().length() == 0) {
            stringBuilder.append("<em>No information.</em>");
        } else {
            stringBuilder.append(a);
        }
        stringBuilder.append("</div>");
        return stringBuilder.toString();
    }

    public static int a(String str, String str2) {
        if (str == null || str2 == null) {
            return 0;
        }
        try {
            Scanner scanner = new Scanner(str.replaceAll("\\-.*", ""));
            Scanner scanner2 = new Scanner(str2.replaceAll("\\-.*", ""));
            scanner.useDelimiter("\\.");
            scanner2.useDelimiter("\\.");
            while (scanner.hasNextInt() && scanner2.hasNextInt()) {
                int nextInt = scanner.nextInt();
                int nextInt2 = scanner2.nextInt();
                if (nextInt < nextInt2) {
                    return -1;
                }
                if (nextInt > nextInt2) {
                    return 1;
                }
            }
            if (scanner.hasNextInt()) {
                return 1;
            }
            if (scanner2.hasNextInt()) {
                return -1;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static boolean a(Context context, long j) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        try {
            if (j > (new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified() / 1000) + 1800) {
                z = true;
            }
            return z;
        } catch (NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static String a(String str) {
        if (str == null || str.equalsIgnoreCase("L")) {
            return "5.0";
        }
        if (str.equalsIgnoreCase("M")) {
            return "6.0";
        }
        if (str.equalsIgnoreCase("N")) {
            return "7.0";
        }
        return Pattern.matches("^[a-zA-Z]+", str) ? "99.0" : str;
    }
}
