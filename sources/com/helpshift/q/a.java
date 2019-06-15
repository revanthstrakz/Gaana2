package com.helpshift.q;

import com.helpshift.util.l;
import java.io.Serializable;

public abstract class a implements d {
    protected d a;

    public abstract void b();

    public synchronized boolean a(String str, Serializable serializable) {
        int i = 0;
        do {
            try {
            } catch (Exception e) {
                StringBuilder stringBuilder;
                if (i == 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in setting value for key : ");
                    stringBuilder.append(str);
                    stringBuilder.append(", retry count : ");
                    stringBuilder.append(i);
                    l.c("Helpshift_RetryKeyValue", stringBuilder.toString(), e);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in setting value for key : ");
                    stringBuilder.append(str);
                    stringBuilder.append(", retry count : ");
                    stringBuilder.append(i);
                    l.d("Helpshift_RetryKeyValue", stringBuilder.toString(), e, new com.helpshift.j.c.a[0]);
                }
                b();
                i++;
                if (i > 1) {
                    return false;
                }
            }
        } while (i > 1);
        return false;
        return this.a.a(str, serializable);
    }

    public synchronized void b(String str, Serializable serializable) {
        int i = 0;
        do {
            try {
                this.a.b(str, serializable);
            } catch (Exception e) {
                StringBuilder stringBuilder;
                if (i == 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in setWithBackup value for key : ");
                    stringBuilder.append(str);
                    stringBuilder.append(", retry count : ");
                    stringBuilder.append(i);
                    l.c("Helpshift_RetryKeyValue", stringBuilder.toString(), e);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in setWithBackup value for key : ");
                    stringBuilder.append(str);
                    stringBuilder.append(", retry count : ");
                    stringBuilder.append(i);
                    l.d("Helpshift_RetryKeyValue", stringBuilder.toString(), e, new com.helpshift.j.c.a[0]);
                }
                b();
                i++;
                if (i > 1) {
                }
            }
        } while (i > 1);
    }

    public synchronized Object a(String str) {
        int i = 0;
        do {
            try {
            } catch (Exception e) {
                StringBuilder stringBuilder;
                if (i == 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception getting value for : ");
                    stringBuilder.append(str);
                    stringBuilder.append(", retry count : ");
                    stringBuilder.append(i);
                    l.c("Helpshift_RetryKeyValue", stringBuilder.toString(), e);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception getting value for : ");
                    stringBuilder.append(str);
                    stringBuilder.append(", retry count : ");
                    stringBuilder.append(i);
                    l.d("Helpshift_RetryKeyValue", stringBuilder.toString(), e, new com.helpshift.j.c.a[0]);
                }
                b();
                i++;
                if (i > 1) {
                    return null;
                }
            }
        } while (i > 1);
        return null;
        return this.a.a(str);
    }

    public synchronized void b(String str) {
        int i = 0;
        do {
            try {
                this.a.b(str);
            } catch (Exception e) {
                StringBuilder stringBuilder;
                if (i == 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception removing key : ");
                    stringBuilder.append(str);
                    stringBuilder.append(", retry count : ");
                    stringBuilder.append(i);
                    l.c("Helpshift_RetryKeyValue", stringBuilder.toString(), e);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception removing key : ");
                    stringBuilder.append(str);
                    stringBuilder.append(", retry count : ");
                    stringBuilder.append(i);
                    l.d("Helpshift_RetryKeyValue", stringBuilder.toString(), e, new com.helpshift.j.c.a[0]);
                }
                b();
                i++;
                if (i > 1) {
                }
            }
        } while (i > 1);
    }

    public synchronized void a() {
        int i = 0;
        do {
            try {
                this.a.a();
            } catch (Exception e) {
                StringBuilder stringBuilder;
                if (i == 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception removing all keys, retry count : ");
                    stringBuilder.append(i);
                    l.c("Helpshift_RetryKeyValue", stringBuilder.toString(), e);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception removing all keys, retry count : ");
                    stringBuilder.append(i);
                    l.d("Helpshift_RetryKeyValue", stringBuilder.toString(), e, new com.helpshift.j.c.a[0]);
                }
                b();
                i++;
                if (i > 1) {
                }
            }
        } while (i > 1);
    }
}
