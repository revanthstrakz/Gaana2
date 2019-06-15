package com.login.nativesso.d;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class b {
    private static b c = new b();
    GoogleSignInClient a;
    private Activity b;
    private int d;

    public static b a() {
        return c;
    }

    private b() {
    }

    public void a(String str, Activity activity, int i) {
        this.b = activity;
        this.d = i;
        this.a = GoogleSignIn.getClient(this.b, new Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken(str).build());
    }

    public void a(String str) {
        this.b.startActivityForResult(this.a.getSignInIntent(), this.d);
    }

    public void b() {
        this.a.signOut().addOnCompleteListener(this.b, new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
    }
}
