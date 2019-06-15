package com.actionbar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.b.i;
import com.gaana.GaanaActivity;
import com.gaana.R;

public class MyMusicSearchResultActionBar extends LinearLayout implements OnClickListener {
    private Context a;
    private LayoutInflater b;

    public MyMusicSearchResultActionBar(Context context, String str, String str2) {
        super(context);
        a(context, str, str2);
    }

    public void setHeader(String str, String str2) {
        TextView textView = (TextView) findViewById(R.id.actionbar_title);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\"");
        stringBuilder.append(str);
        stringBuilder.append("\"");
        textView.setText(stringBuilder.toString());
        textView.setTypeface(i.a(this.a.getAssets(), "fonts/Roboto-Medium.ttf"));
        ((TextView) findViewById(R.id.actionbar_subtitle)).setText(str2);
    }

    private void a(Context context, String str, String str2) {
        this.a = context;
        this.b = LayoutInflater.from(this.a);
        setLayoutParams(new LayoutParams(-1, -2));
        this.b.inflate(R.layout.action_search_viewall, this);
        findViewById(R.id.menu_icon).setOnClickListener(this);
        setHeader(str, str2);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.menu_icon) {
            if (this.a instanceof GaanaActivity) {
                ((GaanaActivity) this.a).homeIconClick();
            } else {
                ((Activity) this.a).finish();
            }
        }
    }
}
