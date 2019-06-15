package com.simpl.android.zeroClickSdk.internal;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.simpl.android.fingerprint.ExtraData;
import com.simpl.android.fingerprint.SimplDataCollection;
import com.simpl.android.fingerprint.commons.exception.ExceptionNotifier;
import com.simpl.android.fingerprint.commons.models.Attribute;
import com.simpl.android.zeroClickSdk.R;
import com.simpl.android.zeroClickSdk.internal.BaseSimplScreen.a;
import com.simpl.approvalsdk.SimplUser;

public class SimplZeroClickWebViewFragment extends Fragment implements a, d {
    public static final String a = "SimplZeroClickWebViewFragment";
    private boolean b = false;
    private ProgressDialog c;
    private e d;
    private String e;
    private SimplUser f;
    private String g;
    private WebView h;
    private SimplDataCollection i;
    private ExtraData j;
    private String k = "";
    private boolean l = false;

    public static SimplZeroClickWebViewFragment a(final String str, final SimplUser simplUser, final String str2) {
        return (SimplZeroClickWebViewFragment) c.a(new a<SimplZeroClickWebViewFragment>() {
            public final /* synthetic */ Object a() {
                SimplZeroClickWebViewFragment simplZeroClickWebViewFragment = new SimplZeroClickWebViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url", str2);
                bundle.putParcelable("transaction", simplUser);
                bundle.putString("url_type", str);
                simplZeroClickWebViewFragment.setArguments(bundle);
                return simplZeroClickWebViewFragment;
            }
        }, null);
    }

    @SuppressLint({"LongLogTag"})
    private static void b(WebView webView, String str, Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:try{");
        stringBuilder.append(str);
        stringBuilder.append("(");
        str = "";
        for (Object obj : objArr) {
            stringBuilder.append(str);
            str = ",";
            boolean z = obj instanceof String;
            if (z) {
                stringBuilder.append("'");
            }
            stringBuilder.append(obj);
            if (z) {
                stringBuilder.append("'");
            }
        }
        stringBuilder.append(")}catch(error){console.error(error.message);}");
        webView.loadUrl(stringBuilder.toString());
    }

    static /* synthetic */ boolean c(SimplZeroClickWebViewFragment simplZeroClickWebViewFragment, String str) {
        if (simplZeroClickWebViewFragment.getActivity().checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        if (VERSION.SDK_INT >= 23) {
            simplZeroClickWebViewFragment.getActivity().requestPermissions(new String[]{str}, 2003);
        }
        return false;
    }

    static /* synthetic */ void k(SimplZeroClickWebViewFragment simplZeroClickWebViewFragment) {
        Throwable e;
        if (simplZeroClickWebViewFragment.getActivity() != null) {
            try {
                simplZeroClickWebViewFragment.getActivity().unregisterReceiver(simplZeroClickWebViewFragment.d);
            } catch (Exception e2) {
                ExceptionNotifier.getSharedInstance().send(e2, new Attribute("user", simplZeroClickWebViewFragment.f.toString()));
            }
            if (!simplZeroClickWebViewFragment.b) {
                e2 = new Throwable("user_cancelled");
                ExceptionNotifier.getSharedInstance().send(e2, new Attribute("user", simplZeroClickWebViewFragment.f.toString()));
                if (simplZeroClickWebViewFragment.g.equals("verification_url")) {
                    ((i) i.a()).e.onFailure(e2);
                } else if (simplZeroClickWebViewFragment.g.equals("redirection_url")) {
                    ((i) i.a()).d.onError(e2);
                }
            }
            if (simplZeroClickWebViewFragment.c != null) {
                if (!(simplZeroClickWebViewFragment.getActivity() == null || simplZeroClickWebViewFragment.getActivity().isFinishing() || !simplZeroClickWebViewFragment.c.isShowing())) {
                    simplZeroClickWebViewFragment.c.dismiss();
                }
                simplZeroClickWebViewFragment.c = null;
            }
        }
    }

    public final void a() {
        Log.e(a, "OTP Timeout");
    }

    public final void a(final String str) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                if (SimplZeroClickWebViewFragment.this.l) {
                    SimplZeroClickWebViewFragment.this.k = str;
                } else {
                    SimplZeroClickWebViewFragment.b(SimplZeroClickWebViewFragment.this.h, "fillOTP", str);
                }
                return null;
            }
        });
    }

    public final void a(final boolean z, final String str) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                if (z) {
                    SimplZeroClickWebViewFragment.this.getActivity().runOnUiThread(new Runnable(SimplZeroClickWebViewFragment.this.i.getPermissionData(str, SimplZeroClickWebViewFragment.this.getActivity(), SimplZeroClickWebViewFragment.this.j.getStartTime(), SimplZeroClickWebViewFragment.this.j.getEndTime(), SimplZeroClickWebViewFragment.this.j.getSenderList())) {
                        public final void run() {
                            SimplZeroClickWebViewFragment.b(SimplZeroClickWebViewFragment.this.h, SimplZeroClickWebViewFragment.this.j.getCallBackKey(), r3);
                        }
                    });
                } else {
                    SimplZeroClickWebViewFragment.this.getActivity().runOnUiThread(/* anonymous class already generated */);
                }
                return null;
            }
        }, new a<Void>() {
            public final /* synthetic */ Object a() {
                SimplZeroClickWebViewFragment.this.getActivity().finish();
                return null;
            }
        });
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                if (!(SimplZeroClickWebViewFragment.this.getActivity() == null || SimplZeroClickWebViewFragment.this.getActivity().isFinishing())) {
                    BaseSimplScreen baseSimplScreen = (BaseSimplScreen) SimplZeroClickWebViewFragment.this.getActivity();
                    c.a(new com.simpl.android.zeroClickSdk.internal.BaseSimplScreen.AnonymousClass3(SimplZeroClickWebViewFragment.this), new a<Void>() {
                        public final /* synthetic */ Object a() {
                            BaseSimplScreen.this.finish();
                            return null;
                        }
                    });
                }
                return null;
            }
        }, new a<Void>() {
            public final /* synthetic */ Object a() {
                SimplZeroClickWebViewFragment.this.getActivity().finish();
                return null;
            }
        });
    }

    @SuppressLint({"LongLogTag"})
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, Bundle bundle) {
        return (View) c.a(new a<View>() {
            public final /* synthetic */ Object a() {
                View inflate = layoutInflater.inflate(R.layout.__fragment_simpl_web_view, viewGroup, false);
                SimplZeroClickWebViewFragment.this.e = SimplZeroClickWebViewFragment.this.getArguments().getString("url");
                SimplZeroClickWebViewFragment.this.f = (SimplUser) SimplZeroClickWebViewFragment.this.getArguments().getParcelable("transaction");
                SimplZeroClickWebViewFragment.this.g = SimplZeroClickWebViewFragment.this.getArguments().getString("url_type");
                SimplZeroClickWebViewFragment.a(SimplZeroClickWebViewFragment.this, inflate);
                String str = SimplZeroClickWebViewFragment.a;
                new StringBuilder("Package name =>").append(SimplZeroClickWebViewFragment.this.getActivity().getPackageName());
                return inflate;
            }
        }, new a<View>() {
            public final /* synthetic */ Object a() {
                SimplZeroClickWebViewFragment.this.getActivity().finish();
                return null;
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                SimplZeroClickWebViewFragment.k(SimplZeroClickWebViewFragment.this);
                return null;
            }
        });
    }
}
