package com.b;

import android.os.Build.VERSION;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.gaana.R;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class a {
    private static final Map<Class<? extends TextView>, Integer> a = new HashMap();
    private static a b;
    private final boolean c;
    private final String d;
    private final int e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final Map<Class<? extends TextView>, Integer> i;
    private final Set<Class<?>> j;

    public static class a {
        private boolean a;
        private boolean b;
        private boolean c;
        private int d;
        private boolean e;
        private String f;
        private Map<Class<? extends TextView>, Integer> g;
        private Set<Class<?>> h;

        public a() {
            this.a = VERSION.SDK_INT >= 11;
            this.b = true;
            this.c = false;
            this.d = R.attr.fontPath;
            this.e = false;
            this.f = null;
            this.g = new HashMap();
            this.h = new HashSet();
        }

        public a a(int i) {
            if (i == -1) {
                i = -1;
            }
            this.d = i;
            return this;
        }

        public a a(String str) {
            this.e = TextUtils.isEmpty(str) ^ 1;
            this.f = str;
            return this;
        }

        public a a() {
            this.e = TextUtils.isEmpty(this.f) ^ 1;
            return new a(this);
        }
    }

    static {
        a.put(TextView.class, Integer.valueOf(16842884));
        a.put(Button.class, Integer.valueOf(16842824));
        a.put(EditText.class, Integer.valueOf(16842862));
        a.put(AutoCompleteTextView.class, Integer.valueOf(16842859));
        a.put(MultiAutoCompleteTextView.class, Integer.valueOf(16842859));
        a.put(CheckBox.class, Integer.valueOf(16842860));
        a.put(RadioButton.class, Integer.valueOf(16842878));
        a.put(ToggleButton.class, Integer.valueOf(16842827));
        if (f.b()) {
            i();
        }
    }

    private static void i() {
        a.put(AppCompatTextView.class, Integer.valueOf(16842884));
        a.put(AppCompatButton.class, Integer.valueOf(16842824));
        a.put(AppCompatEditText.class, Integer.valueOf(16842862));
        a.put(AppCompatAutoCompleteTextView.class, Integer.valueOf(16842859));
        a.put(AppCompatMultiAutoCompleteTextView.class, Integer.valueOf(16842859));
        a.put(AppCompatCheckBox.class, Integer.valueOf(16842860));
        a.put(AppCompatRadioButton.class, Integer.valueOf(16842878));
        a.put(AppCompatCheckedTextView.class, Integer.valueOf(16843720));
    }

    public static void a(a aVar) {
        b = aVar;
    }

    public static a a() {
        if (b == null) {
            b = new a(new a());
        }
        return b;
    }

    protected a(a aVar) {
        this.c = aVar.e;
        this.d = aVar.f;
        this.e = aVar.d;
        this.f = aVar.a;
        this.g = aVar.b;
        this.h = aVar.c;
        HashMap hashMap = new HashMap(a);
        hashMap.putAll(aVar.g);
        this.i = Collections.unmodifiableMap(hashMap);
        this.j = Collections.unmodifiableSet(aVar.h);
    }

    public String b() {
        return this.d;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.f;
    }

    public boolean e() {
        return this.g;
    }

    public boolean f() {
        return this.h;
    }

    public boolean a(View view) {
        return this.j.contains(view.getClass());
    }

    /* Access modifiers changed, original: 0000 */
    public Map<Class<? extends TextView>, Integer> g() {
        return this.i;
    }

    public int h() {
        return this.e;
    }
}
