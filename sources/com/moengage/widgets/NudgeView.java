package com.moengage.widgets;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.moengage.core.Logger;
import com.moengage.core.executor.TaskProcessor;
import com.moengage.inapp.InAppManager;
import com.moengage.inapp.InAppMessage;
import com.moengage.inapp.InAppMessage.ALIGN_TYPE;
import com.moengage.inapp.InAppMessage.TYPE;
import com.moengage.inapp.ViewEngine;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class NudgeView extends LinearLayout {
    private static final int ID_CLOSE = 10002;
    private static final int ID_CONTENT = 10001;
    boolean alreadyObserving;
    private InAppCacheObserver inAppCacheObserver;
    private boolean mAlreadyShowing;
    private InAppMessage mCampaign;
    private OnNudgeClickListener mClickListener;
    private OnNudgeClosedListener mCloseListener;
    private Context mContext;
    private TaskProcessor mTaskProcessor;
    private Activity nudgeView;
    private final Object queryLock;
    private AtomicBoolean wip;

    public class CreateAndShowNudge extends AsyncTask<String, Void, InAppMessage> {
        Context mContext;

        CreateAndShowNudge(Context context) {
            this.mContext = context;
        }

        /* Access modifiers changed, original: protected|varargs */
        public InAppMessage doInBackground(String... strArr) {
            try {
                NudgeView.this.wip.set(true);
                Logger.v("NudgeView: fetchAndShowNudge: Checking for Nudge");
                InAppMessage inAppMessageToShow = InAppManager.getInstance().getInAppMessageToShow(ALIGN_TYPE.EMBED, TYPE.GENERAL, this.mContext);
                if (inAppMessageToShow != null) {
                    if (InAppManager.getInstance().canShowInAppMessage(inAppMessageToShow.rules, System.currentTimeMillis() / 1000, NudgeView.this.nudgeView.getClass().getName())) {
                        Logger.v("NudgeView found");
                    }
                    inAppMessageToShow.theComposedView = ViewEngine.getInstance(this.mContext).createInApp(InAppManager.getInstance().getCurrentActivity(), inAppMessageToShow);
                }
                return inAppMessageToShow;
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("NudgeView$CreateAndShowNudgeTask Exception: ");
                stringBuilder.append(e.toString());
                Logger.f(stringBuilder.toString());
                return null;
            }
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(InAppMessage inAppMessage) {
            super.onPostExecute(inAppMessage);
            if (!(inAppMessage == null || inAppMessage.theComposedView == null)) {
                NudgeView.this.addNudge(inAppMessage);
                InAppManager.getInstance().trackInAppShown(this.mContext, inAppMessage);
            }
            NudgeView.this.wip.set(false);
        }
    }

    private class InAppCacheObserver implements Observer {
        private InAppCacheObserver() {
        }

        public void update(Observable observable, Object obj) {
            NudgeView.this.queryForNudge();
        }
    }

    public interface OnNudgeClickListener {
        void onClick(InAppMessage inAppMessage, int i);
    }

    public interface OnNudgeClosedListener {
        void onClosed(InAppMessage inAppMessage, int i);
    }

    public NudgeView(Context context) {
        this(context, null);
    }

    public NudgeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAlreadyShowing = false;
        this.inAppCacheObserver = new InAppCacheObserver();
        this.alreadyObserving = false;
        this.queryLock = new Object();
        this.wip = new AtomicBoolean(false);
        setLayoutParams(new LayoutParams(-2, -2));
        setVisibility(4);
        setOrientation(1);
        this.mContext = context;
        this.mTaskProcessor = TaskProcessor.getInstance();
    }

    public void setOnNudgeCloseListener(OnNudgeClosedListener onNudgeClosedListener) {
        this.mCloseListener = onNudgeClosedListener;
    }

    public void setOnNudgeClickListener(OnNudgeClickListener onNudgeClickListener) {
        this.mClickListener = onNudgeClickListener;
    }

    private int dpToPx(Context context, int i) {
        return Math.round(((float) i) * (context.getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void initialiseNudgeView(Activity activity) {
        Logger.v("NudgeView: inside initialiseNudgeView()");
        this.nudgeView = activity;
        queryForNudge();
    }

    /* Access modifiers changed, original: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            InAppManager.getInstance().setCacheObserver(this.inAppCacheObserver);
            this.alreadyObserving = true;
        } else if (this.alreadyObserving) {
            InAppManager.getInstance().removeObserver(this.inAppCacheObserver);
            this.alreadyObserving = false;
        }
    }

    public void addNudge(InAppMessage inAppMessage) {
        try {
            if (inAppMessage.theComposedView != null) {
                addView(inAppMessage.theComposedView);
                setVisibility(0);
            }
        } catch (Exception e) {
            Logger.f("NudgeView : addNudge ", e);
        }
    }

    /* JADX WARNING: Missing block: B:15:0x0038, code skipped:
            return;
     */
    private void queryForNudge() {
        /*
        r5 = this;
        r0 = r5.wip;
        r0 = r0.get();
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r0 = r5.queryLock;
        monitor-enter(r0);
        r1 = r5.nudgeView;	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x0037;
    L_0x0010:
        r1 = r5.getVisibility();	 Catch:{ all -> 0x0039 }
        if (r1 != 0) goto L_0x001d;
    L_0x0016:
        r1 = "NudgeView: Already showing an inapp";
        com.moengage.core.Logger.v(r1);	 Catch:{ all -> 0x0039 }
        monitor-exit(r0);	 Catch:{ all -> 0x0039 }
        return;
    L_0x001d:
        r1 = new com.moengage.widgets.NudgeView$CreateAndShowNudge;	 Catch:{ all -> 0x0039 }
        r2 = r5.mContext;	 Catch:{ all -> 0x0039 }
        r1.<init>(r2);	 Catch:{ all -> 0x0039 }
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ all -> 0x0039 }
        r3 = 0;
        r4 = r5.nudgeView;	 Catch:{ all -> 0x0039 }
        r4 = r4.getClass();	 Catch:{ all -> 0x0039 }
        r4 = r4.getName();	 Catch:{ all -> 0x0039 }
        r2[r3] = r4;	 Catch:{ all -> 0x0039 }
        r1.execute(r2);	 Catch:{ all -> 0x0039 }
    L_0x0037:
        monitor-exit(r0);	 Catch:{ all -> 0x0039 }
        return;
    L_0x0039:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0039 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.widgets.NudgeView.queryForNudge():void");
    }
}
