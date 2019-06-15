package com.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.managers.ap;
import com.managers.u;
import com.utilities.Util;

public class EducativeHDStreamFragment extends BaseGaanaFragment implements a {
    private boolean a = true;

    public void setGAScreenName(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.educative_hd_quality, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.edu_hd_switch);
        switchCompat.setChecked(true);
        switchCompat.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EducativeHDStreamFragment.this.a = z;
            }
        });
        view.findViewById(R.id.fragment_edu_back).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((GaanaActivity) EducativeHDStreamFragment.this.mContext).onBackPressed();
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.a && ap.a().s()) {
            Util.o("extreme");
            u.a().a("HD Settings", "Settings", "Enable");
            this.mDeviceResManager.a("PREFERENCE_KEY_STREAMING_QUALITY", 10003, false);
            return;
        }
        u.a().a("HD Settings", "Settings", "Disable");
    }
}
