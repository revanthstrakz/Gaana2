package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.gaana.login.LoginManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import net.hockeyapp.android.c.f;
import net.hockeyapp.android.d.i;
import net.hockeyapp.android.i.b;
import net.hockeyapp.android.i.c;
import net.hockeyapp.android.i.d;

public class LoginActivity extends Activity {
    private String a;
    private String b;
    private int c;
    private f d;
    private Handler e;
    private Button f;

    private static class a extends Handler {
        private final WeakReference<Activity> a;

        public a(Activity activity) {
            this.a = new WeakReference(activity);
        }

        public void handleMessage(Message message) {
            Activity activity = (Activity) this.a.get();
            if (activity != null) {
                if (message.getData().getBoolean("success")) {
                    activity.finish();
                    if (g.b != null) {
                        g.b.a();
                    }
                } else {
                    Toast.makeText(activity, "Login failed. Check your credentials.", 1).show();
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(c.hockeyapp_activity_login);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            this.a = bundle.getString("url");
            this.b = bundle.getString("secret");
            this.c = bundle.getInt(InternalAvidAdSessionContext.CONTEXT_MODE);
        }
        a();
        b();
        Object lastNonConfigurationInstance = getLastNonConfigurationInstance();
        if (lastNonConfigurationInstance != null) {
            this.d = (f) lastNonConfigurationInstance;
            this.d.a((Context) this, this.e);
        }
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.d != null) {
            this.d.a();
        }
        return this.d;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (g.b != null) {
                g.b.b();
            } else {
                Intent intent = new Intent(this, g.a);
                intent.setFlags(67108864);
                intent.putExtra("net.hockeyapp.android.EXIT", true);
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void a() {
        if (this.c == 1) {
            ((EditText) findViewById(b.input_password)).setVisibility(4);
        }
        ((TextView) findViewById(b.text_headline)).setText(this.c == 1 ? d.hockeyapp_login_headline_text_email_only : d.hockeyapp_login_headline_text);
        this.f = (Button) findViewById(b.button_login);
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoginActivity.this.c();
            }
        });
    }

    private void b() {
        this.e = new a(this);
    }

    private void c() {
        if (i.a((Context) this)) {
            String obj = ((EditText) findViewById(b.input_email)).getText().toString();
            String obj2 = ((EditText) findViewById(b.input_password)).getText().toString();
            HashMap hashMap = new HashMap();
            int i = 0;
            if (this.c == 1) {
                i = TextUtils.isEmpty(obj) ^ 1;
                hashMap.put("email", obj);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.b);
                stringBuilder.append(obj);
                hashMap.put("authcode", a(stringBuilder.toString()));
            } else if (this.c == 2) {
                if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2))) {
                    i = 1;
                }
                hashMap.put("email", obj);
                hashMap.put(LoginManager.TAG_PASSWORD, obj2);
            }
            if (i != 0) {
                this.d = new f(this, this.e, this.a, this.c, hashMap);
                net.hockeyapp.android.d.a.a(this.d);
            } else {
                Toast.makeText(this, getString(d.hockeyapp_login_missing_credentials_toast), 1).show();
            }
            return;
        }
        Toast.makeText(this, d.hockeyapp_error_no_network_message, 1).show();
    }

    public String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                while (toHexString.length() < 2) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("0");
                    stringBuilder2.append(toHexString);
                    toHexString = stringBuilder2.toString();
                }
                stringBuilder.append(toHexString);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }
}
