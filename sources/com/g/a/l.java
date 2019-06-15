package com.g.a;

import android.content.Context;
import android.os.SystemClock;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.LinkedHashMap;
import java.util.Map;

public class l {
    public static String b;
    int a = 0;
    Context c;
    e d = new e(this.c);
    String e;
    String f;
    int g;
    int h;
    Map<String, String> i = new LinkedHashMap();
    private double j;
    private int k = 1;
    private int l = 1;

    public l(Context context) {
        b = "MF_START";
        this.c = context;
        this.d = new e(this.c);
    }

    public int a() {
        if (this.l <= 5) {
            this.l++;
            return ((int) 1.0d) * 1000;
        }
        double random;
        this.l = 1;
        try {
            random = ((double) 1) + (Math.random() * ((double) 99));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            random = 5.0d;
        }
        return ((int) random) * 1000;
    }

    private void f() {
        try {
            if (this.d.a().equalsIgnoreCase("mobile") && this.g == 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("extdp : ");
                stringBuilder.append(this.g);
                j.a(stringBuilder.toString());
                this.i = this.d.a(this.g);
            }
            if (this.g != 0) {
                this.i = this.d.a(this.e, this.f, this.g);
                this.i = new f(this.c).a(this.i);
                this.i = new g(this.c).a(this.i);
                this.i = new h(this.c).a(this.i);
                this.i = new i(this.c).a(this.i);
            }
        } catch (Exception e) {
            j.a("Exception : MF_SM_100");
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void b() {
        while (true) {
            try {
                String str = b;
                Object obj = -1;
                int hashCode = str.hashCode();
                if (hashCode != -1398769399) {
                    if (hashCode != 748527225) {
                        if (hashCode != 1552146972) {
                            if (hashCode == 1712637672) {
                                if (str.equals("MF_STOP")) {
                                    obj = 3;
                                }
                            }
                        } else if (str.equals("MF_START")) {
                            obj = null;
                        }
                    } else if (str.equals("MF_DATAPOINTS_TO_UPLOAD")) {
                        obj = 1;
                    }
                } else if (str.equals("MF_EXCEPTION")) {
                    obj = 2;
                }
                switch (obj) {
                    case null:
                        c();
                        break;
                    case 1:
                        d();
                        break;
                    case 2:
                        e();
                        break;
                    case 3:
                        j.a("Transaction Complete");
                        return;
                    default:
                        break;
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                j.a("Exception : MF_SM_101");
            }
        }
    }

    public void c() {
        try {
            d dVar = new d(this.c);
            j.a("Going to initialize data points");
            this.e = dVar.b("mf_app_data", "mf_app_data");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("after getting ");
            stringBuilder.append(this.e);
            j.a(stringBuilder.toString());
            this.f = dVar.b("mf_vendor_id", "mf_vendor_id");
            this.g = Integer.parseInt(dVar.b("mf_ex_datapoints", "0"));
            this.h = Integer.parseInt(dVar.b("mf_total_retrycount", "0"));
            boolean equalsIgnoreCase = dVar.b("mf_upload_datapoints", "true").equalsIgnoreCase("true");
            dVar.b("mf_is_install", "true").equalsIgnoreCase("true");
            if (equalsIgnoreCase) {
                f();
                j.a("All datapoints collected");
                b = "MF_DATAPOINTS_TO_UPLOAD";
                return;
            }
            j.a("Avoiding Repeat Connection");
            b = "MF_STOP";
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_SM_102");
            b = "MF_START";
        }
    }

    public void d() {
        try {
            d dVar = new d(this.c);
            this.h++;
            dVar.a("mf_total_retrycount", Integer.toString(this.h));
            dVar.a("mf_current_retrycount", Integer.toString(this.k));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(this.h);
            this.i.put("sdk_totalretry_count", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(this.k);
            this.i.put("sdk_currentretry_count", stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Total tries : ");
            stringBuilder2.append(this.h);
            j.a(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Current try : ");
            stringBuilder2.append(this.k);
            j.a(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("AppData is : ");
            stringBuilder2.append(this.e);
            j.a(stringBuilder2.toString());
            if (new c(this.c).a(this.i)) {
                dVar.a("mf_upload_datapoints", InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
                b = "MF_STOP";
            } else {
                b = "MF_EXCEPTION";
            }
        } catch (Exception e) {
            b = "MF_EXCEPTION";
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_SM_104");
        }
    }

    public void e() {
        try {
            this.j = (double) a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Retrying after ");
            stringBuilder.append(this.j / 1000.0d);
            stringBuilder.append(" seconds duration");
            j.a(stringBuilder.toString());
            SystemClock.sleep((long) ((int) this.j));
            this.k++;
            b = "MF_DATAPOINTS_TO_UPLOAD";
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_SM_105");
            b = "MF_EXCEPTION";
        }
    }
}
