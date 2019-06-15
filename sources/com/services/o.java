package com.services;

import android.util.Patterns;
import android.widget.EditText;

public class o {
    public static Boolean a(EditText... editTextArr) {
        for (EditText editText : editTextArr) {
            String trim = editText.getText().toString().trim();
            String str = "";
            if (editText.getHint() != null) {
                str = editText.getHint().toString();
            }
            if (trim == null || trim.length() == 0 || trim.compareTo(str) == 0) {
                return Boolean.valueOf(true);
            }
        }
        return Boolean.valueOf(false);
    }

    public static final boolean a(String str) {
        return str.matches("[A-Za-z_\\s]+");
    }

    public static Boolean b(String str) {
        return Boolean.valueOf(Patterns.EMAIL_ADDRESS.matcher(str).matches());
    }

    public static boolean c(String str) {
        return str.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=\\S+$).{6,14}");
    }
}
