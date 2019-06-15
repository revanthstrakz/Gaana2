package com.payu.custombrowser;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.payu.custombrowser.bean.ReviewOrderData;
import com.payu.custombrowser.d.e;
import com.payu.custombrowser.d.f;
import java.util.ArrayList;
import java.util.Iterator;

public class c extends Fragment {
    private ArrayList<ReviewOrderData> a;
    private int b;
    private a c;
    private View d;
    private int e;

    public interface a {
        void c();
    }

    public static c a(ArrayList<ReviewOrderData> arrayList, @LayoutRes int i) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("review_order_detail_list", arrayList);
        bundle.putInt("layout_res", i);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.a = getArguments().getParcelableArrayList("review_order_detail_list");
            this.b = getArguments().getInt("layout_res");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(f.fragment_review_order, viewGroup, false);
        return this.d;
    }

    public void onViewCreated(final View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(e.payu_review_order_list);
        if (this.b != -1) {
            View inflate = getActivity().getLayoutInflater().inflate(this.b, null, false);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(e.r_payu_review_order);
            relativeLayout.removeAllViews();
            relativeLayout.addView(inflate);
        } else if (this.a != null) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ReviewOrderData reviewOrderData = (ReviewOrderData) it.next();
                View inflate2 = getActivity().getLayoutInflater().inflate(f.payu_review_order_list_row, null, false);
                TextView textView = (TextView) inflate2.findViewById(e.t_review_order_details_value);
                ((TextView) inflate2.findViewById(e.t_review_order_details_key)).setText(reviewOrderData.getKey());
                textView.setText(reviewOrderData.getValue());
                linearLayout.addView(inflate2);
            }
        }
        a(view);
        view.findViewById(e.i_payu_close_review).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                view.setEnabled(false);
                view.setClickable(false);
                c.this.c.c();
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) c.this.e);
                translateAnimation.setDuration(500);
                translateAnimation.setFillBefore(false);
                translateAnimation.setFillEnabled(true);
                translateAnimation.setZAdjustment(1);
                view.startAnimation(translateAnimation);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (c.this.getActivity() != null && !c.this.getActivity().isFinishing()) {
                            view.setVisibility(8);
                            FragmentTransaction beginTransaction = c.this.getActivity().getSupportFragmentManager().beginTransaction();
                            beginTransaction.remove(c.this);
                            beginTransaction.commit();
                        }
                    }
                }, 450);
            }
        });
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof a) {
            this.c = (a) context;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.toString());
        stringBuilder.append(" must implement OnReviewOrderDetailCloseListener");
        throw new RuntimeException(stringBuilder.toString());
    }

    public void onDetach() {
        super.onDetach();
        this.c = null;
    }

    private void a(View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        view.measure(0, 0);
        this.e = (int) (((double) i) * 0.45d);
        view.setLayoutParams(new LayoutParams(-1, this.e));
    }
}
