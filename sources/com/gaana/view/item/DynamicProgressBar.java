package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.gaana.R;

public class DynamicProgressBar extends Dialog {
    private static final int layoutResID = 2131493627;
    private Context mContext;
    private TextView mProTextView = ((TextView) findViewById(R.id.f64tv.progress));
    private ProgressBar mProgressBar = ((ProgressBar) findViewById(R.id.progress_bar));
    private TextView mTitleTextView;
    private int max;

    public DynamicProgressBar(Context context, String str) {
        super(context);
        requestWindowFeature(1);
        setContentView(R.layout.view_progress_bar);
        this.mContext = context;
        this.mProgressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.layer_download_progressbar));
        this.mTitleTextView = (TextView) findViewById(R.id.f65tv.title);
        this.mTitleTextView.setText(str);
    }

    public void show() {
        this.mProgressBar.setMax(this.max);
        TextView textView = this.mProTextView;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0 of ");
        stringBuilder.append(this.max);
        textView.setText(stringBuilder.toString());
        super.show();
    }

    public void setMax(int i) {
        this.max = i;
    }

    public void setProgress(int i) {
        this.mProgressBar.setProgressDrawable(this.mContext.getResources().getDrawable(R.drawable.layer_download_progressbar));
        this.mProgressBar.setMax(this.max);
        this.mProgressBar.setProgress(i);
        TextView textView = this.mProTextView;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(" of ");
        stringBuilder.append(this.max);
        textView.setText(stringBuilder.toString());
    }
}
