package com.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gaana.R;

public class GDPRCantUseAppFragment extends DialogFragment {
    private LayoutInflater a;
    private View b = null;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, 16973831);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.a = getActivity().getLayoutInflater();
            this.b = this.a.inflate(R.layout.gdpr_cantuse_app_layout, viewGroup);
            getDialog().requestWindowFeature(1);
            setCancelable(false);
        }
        return this.b;
    }

    public void onResume() {
        super.onResume();
        getDialog().setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                if (keyEvent.getAction() == 0) {
                    return true;
                }
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.setFlags(67108864);
                GDPRCantUseAppFragment.this.startActivity(intent);
                return true;
            }
        });
    }
}
