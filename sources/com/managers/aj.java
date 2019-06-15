package com.managers;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.comscore.utils.Constants;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.services.d;

public class aj {
    private static aj a;

    public interface a {
        void onDismiss();

        void onSet();
    }

    public interface b {
        void undoSnackBar();
    }

    private aj() {
    }

    public static aj a() {
        if (a == null) {
            a = new aj();
        }
        return a;
    }

    public void a(Context context, String str) {
        a(context, str, false);
    }

    public void a(Context context, final String str, boolean z) {
        if (context instanceof Activity) {
            b(context, str, z);
        } else if (ai.a() != null) {
            b(ai.a(), str, z);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Toast.makeText(GaanaApplication.getContext(), str, 1).show();
                }
            });
        }
    }

    private void b(Context context, String str, boolean z) {
        Snackbar make;
        View findViewById;
        StringBuilder stringBuilder;
        if (z) {
            findViewById = ((Activity) context).findViewById(16908290);
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(str);
            make = Snackbar.make(findViewById, stringBuilder.toString(), 0);
        } else {
            findViewById = ((Activity) context).findViewById(16908290);
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(str);
            make = Snackbar.make(findViewById, stringBuilder.toString(), -1);
        }
        make.getView().setBackgroundColor(context.getResources().getColor(R.color.snack_bar_background_color));
        make.show();
    }

    public void a(Context context, String str, int i, String str2) {
        View findViewById = ((Activity) context).findViewById(16908290);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(str);
        Snackbar snackbar = (Snackbar) Snackbar.make(findViewById, stringBuilder.toString(), 0).setActionTextColor(context.getResources().getColor(R.color.gaana_orange_text)).setAction((CharSequence) str2, new OnClickListener() {
            public void onClick(View view) {
            }
        }).setDuration(i);
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setMaxLines(5);
        view.setBackgroundColor(context.getResources().getColor(R.color.snack_bar_background_color));
        snackbar.show();
    }

    public void a(Context context, String str, final b bVar) {
        View findViewById = ((Activity) context).findViewById(16908290);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(str);
        Snackbar action = Snackbar.make(findViewById, stringBuilder.toString(), 0).setActionTextColor(context.getResources().getColor(R.color.gaana_orange_text)).setAction((int) R.string.undo, new OnClickListener() {
            public void onClick(View view) {
                bVar.undoSnackBar();
            }
        });
        action.getView().setBackgroundColor(context.getResources().getColor(R.color.snack_bar_background_color));
        action.show();
    }

    public void a(Context context, String str, String str2, final b bVar) {
        View findViewById = ((Activity) context).findViewById(16908290);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(str2);
        Snackbar action = Snackbar.make(findViewById, stringBuilder.toString(), 0).setActionTextColor(context.getResources().getColor(R.color.gaana_orange_text)).setAction((CharSequence) str, new OnClickListener() {
            public void onClick(View view) {
                bVar.undoSnackBar();
            }
        });
        action.getView().setBackgroundColor(context.getResources().getColor(R.color.snack_bar_background_color));
        action.show();
    }

    public void a(Context context, String str, String str2, final a aVar) {
        View findViewById = ((Activity) context).findViewById(16908290);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(str2);
        Snackbar snackbar = (Snackbar) ((Snackbar) Snackbar.make(findViewById, stringBuilder.toString(), 0).setActionTextColor(context.getResources().getColor(R.color.gaana_orange_text)).setAction((CharSequence) str, new OnClickListener() {
            public void onClick(View view) {
                aVar.onSet();
            }
        }).addCallback(new Callback() {
            public void onShown(Snackbar snackbar) {
            }

            public void onDismissed(Snackbar snackbar, int i) {
                aVar.onDismiss();
            }
        })).setDuration(Constants.EVENTS_LIMIT_PER_DAY);
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.snack_bar_background_color));
        snackbar.show();
    }

    public void a(final Context context, String str, String str2, final OnClickListener onClickListener) {
        int b = d.a().b("pref_schd_count", 0, false);
        if (b < 3) {
            View findViewById = ((Activity) context).findViewById(16908290);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(str2);
            Snackbar snackbar = (Snackbar) Snackbar.make(findViewById, stringBuilder.toString(), 0).setActionTextColor(context.getResources().getColor(R.color.gaana_orange_text)).setAction((CharSequence) str, new OnClickListener() {
                public void onClick(View view) {
                    ((BaseActivity) context).sendGAEvent("Download Settings", "Schedule Downloads Toast", "Settings");
                    onClickListener.onClick(view);
                }
            }).setDuration(5000);
            snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.snack_bar_background_color));
            snackbar.show();
            d.a().a("pref_schd_count", b + 1, false);
        }
    }
}
