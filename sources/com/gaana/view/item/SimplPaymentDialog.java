package com.gaana.view.item;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import com.gaana.R;

public class SimplPaymentDialog extends DialogFragment {
    Dialog dialog = null;
    private String payment_message = "";
    private String view_type = "";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.FullScreenDialogStyle);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
    public android.view.View onCreateView(android.view.LayoutInflater r7, android.view.ViewGroup r8, android.os.Bundle r9) {
        /*
        r6 = this;
        super.onCreateView(r7, r8, r9);
        r9 = 0;
        r0 = 2131493478; // 0x7f0c0266 float:1.8610437E38 double:1.053097702E-314;
        r7 = r7.inflate(r0, r8, r9);
        r8 = r6.view_type;
        r0 = r8.hashCode();
        r1 = -1867169789; // 0xffffffff90b54003 float:-7.149054E-29 double:NaN;
        if (r0 == r1) goto L_0x0035;
    L_0x0016:
        r1 = -1281977283; // 0xffffffffb396943d float:-7.011884E-8 double:NaN;
        if (r0 == r1) goto L_0x002b;
    L_0x001b:
        r1 = 422194963; // 0x192a2f13 float:8.7983006E-24 double:2.08592027E-315;
        if (r0 == r1) goto L_0x0021;
    L_0x0020:
        goto L_0x003f;
    L_0x0021:
        r0 = "processing";
        r8 = r8.equals(r0);
        if (r8 == 0) goto L_0x003f;
    L_0x0029:
        r8 = r9;
        goto L_0x0040;
    L_0x002b:
        r0 = "failed";
        r8 = r8.equals(r0);
        if (r8 == 0) goto L_0x003f;
    L_0x0033:
        r8 = 1;
        goto L_0x0040;
    L_0x0035:
        r0 = "success";
        r8 = r8.equals(r0);
        if (r8 == 0) goto L_0x003f;
    L_0x003d:
        r8 = 2;
        goto L_0x0040;
    L_0x003f:
        r8 = -1;
    L_0x0040:
        r0 = 2131297947; // 0x7f09069b float:1.8213853E38 double:1.0530010967E-314;
        r1 = 2131297953; // 0x7f0906a1 float:1.8213865E38 double:1.0530010996E-314;
        r2 = 2131297954; // 0x7f0906a2 float:1.8213867E38 double:1.0530011E-314;
        r3 = 2131297952; // 0x7f0906a0 float:1.8213863E38 double:1.053001099E-314;
        r4 = 8;
        r5 = 2131297950; // 0x7f09069e float:1.821386E38 double:1.053001098E-314;
        switch(r8) {
            case 0: goto L_0x00da;
            case 1: goto L_0x0098;
            case 2: goto L_0x0056;
            default: goto L_0x0054;
        };
    L_0x0054:
        goto L_0x00e8;
    L_0x0056:
        r8 = r7.findViewById(r2);
        r8 = (android.widget.TextView) r8;
        r2 = r6.payment_message;
        r8.setText(r2);
        r8 = r7.findViewById(r1);
        r8 = (android.widget.TextView) r8;
        r1 = r6.getResources();
        r2 = 2131822644; // 0x7f110834 float:1.9278065E38 double:1.0532603314E-314;
        r1 = r1.getText(r2);
        r8.setText(r1);
        r8 = r7.findViewById(r5);
        r8.setVisibility(r4);
        r8 = r7.findViewById(r3);
        r8.setVisibility(r9);
        r8 = r7.findViewById(r0);
        r8 = (android.widget.ImageView) r8;
        r9 = r6.getResources();
        r0 = 2131231654; // 0x7f0803a6 float:1.8079395E38 double:1.0529683436E-314;
        r9 = r9.getDrawable(r0);
        r8.setImageDrawable(r9);
        goto L_0x00e8;
    L_0x0098:
        r8 = r7.findViewById(r2);
        r8 = (android.widget.TextView) r8;
        r2 = r6.payment_message;
        r8.setText(r2);
        r8 = r7.findViewById(r1);
        r8 = (android.widget.TextView) r8;
        r1 = r6.getResources();
        r2 = 2131822643; // 0x7f110833 float:1.9278063E38 double:1.053260331E-314;
        r1 = r1.getText(r2);
        r8.setText(r1);
        r8 = r7.findViewById(r0);
        r8 = (android.widget.ImageView) r8;
        r0 = r6.getResources();
        r1 = 2131231652; // 0x7f0803a4 float:1.8079391E38 double:1.0529683426E-314;
        r0 = r0.getDrawable(r1);
        r8.setImageDrawable(r0);
        r8 = r7.findViewById(r5);
        r8.setVisibility(r4);
        r8 = r7.findViewById(r3);
        r8.setVisibility(r9);
        goto L_0x00e8;
    L_0x00da:
        r8 = r7.findViewById(r5);
        r8.setVisibility(r9);
        r8 = r7.findViewById(r3);
        r8.setVisibility(r4);
    L_0x00e8:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.SimplPaymentDialog.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }

    public void onStart() {
        super.onStart();
        this.dialog = getDialog();
        if (this.dialog != null) {
            this.dialog.getWindow().setLayout(-1, -1);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void setViewType(String str) {
        this.view_type = str;
    }

    public void setPaymentMessage(String str) {
        this.payment_message = str;
    }

    public void hide() {
        dismissAllowingStateLoss();
        if (this.dialog != null) {
            this.dialog.hide();
            this.dialog.dismiss();
        }
    }
}
