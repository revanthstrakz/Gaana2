package com.services;

import android.app.Activity;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gaana.R;

public class b extends ArrayAdapter<Object> {
    private Activity a;
    private Object[] b;

    public b(Activity activity, Object[] objArr) {
        super(activity, R.layout.view_row_shareoptions, objArr);
        this.a = activity;
        this.b = objArr;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        view = this.a.getLayoutInflater().inflate(R.layout.view_row_shareoptions, null);
        ((TextView) view.findViewById(R.id.txShareOptionName)).setText(((ResolveInfo) this.b[i]).loadLabel(this.a.getPackageManager()).toString());
        ((ImageView) view.findViewById(R.id.imgShareOptionLogo)).setImageDrawable(((ResolveInfo) this.b[i]).loadIcon(this.a.getPackageManager()));
        return view;
    }
}
