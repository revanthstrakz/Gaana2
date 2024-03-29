package com.facebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.FragmentWrapper;

public abstract class FacebookButtonBase extends Button {
    private String analyticsButtonCreatedEventName;
    private String analyticsButtonTappedEventName;
    private OnClickListener externalOnClickListener;
    private OnClickListener internalOnClickListener;
    private boolean overrideCompoundPadding;
    private int overrideCompoundPaddingLeft;
    private int overrideCompoundPaddingRight;
    private FragmentWrapper parentFragment;

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r5_3 ?) in PHI: PHI: (r5_6 ?) = (r5_3 ?), (r5_0 android.content.Context) binds: {(r5_0 android.content.Context)=B:11:0x0069}
        	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
        	at jadx.core.dex.visitors.ssa.SSATransform.inlinePhiInsn(SSATransform.java:392)
        	at jadx.core.dex.visitors.ssa.SSATransform.replacePhiWithMove(SSATransform.java:360)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixPhiWithSameArgs(SSATransform.java:300)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixUselessPhi(SSATransform.java:275)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:61)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    private void parseTextAttributes(android.content.Context r5, android.util.AttributeSet r6, int r7, int r8) {
        /*
        r4 = this;
        r0 = 1;
        r1 = new int[r0];
        r2 = 0;
        r3 = 16842904; // 0x1010098 float:2.3693984E-38 double:8.3215E-317;
        r1[r2] = r3;
        r3 = r5.getTheme();
        r1 = r3.obtainStyledAttributes(r6, r1, r7, r8);
        r3 = r1.getColorStateList(r2);	 Catch:{ all -> 0x006d }
        setTextColor(r3);	 Catch:{ all -> 0x006d }
        r1.recycle();
        r1 = new int[r0];
        r3 = 16842927; // 0x10100af float:2.3694048E-38 double:8.3215116E-317;
        r1[r2] = r3;
        r3 = r5.getTheme();
        r1 = r3.obtainStyledAttributes(r6, r1, r7, r8);
        r3 = 17;
        r3 = r1.getInt(r2, r3);	 Catch:{ all -> 0x0068 }
        setGravity(r3);	 Catch:{ all -> 0x0068 }
        r1.recycle();
        r1 = 3;
        r1 = new int[r1];
        r1 = {16842901, 16842903, 16843087};
        r5 = r5.getTheme();
        r5 = r5.obtainStyledAttributes(r6, r1, r7, r8);
        r6 = r5.getDimensionPixelSize(r2, r2);	 Catch:{ all -> 0x0063 }
        r6 = (float) r6;	 Catch:{ all -> 0x0063 }
        setTextSize(r2, r6);	 Catch:{ all -> 0x0063 }
        r6 = r5.getInt(r0, r0);	 Catch:{ all -> 0x0063 }
        r6 = android.graphics.Typeface.defaultFromStyle(r6);	 Catch:{ all -> 0x0063 }
        setTypeface(r6);	 Catch:{ all -> 0x0063 }
        r6 = 2;	 Catch:{ all -> 0x0063 }
        r6 = r5.getString(r6);	 Catch:{ all -> 0x0063 }
        setText(r6);	 Catch:{ all -> 0x0063 }
        r5.recycle();
        return;
        r6 = move-exception;
        r5.recycle();
        throw r6;
        r5 = move-exception;
        r1.recycle();
        throw r5;
        r5 = move-exception;
        r1.recycle();
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookButtonBase.parseTextAttributes(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    public abstract int getDefaultRequestCode();

    /* Access modifiers changed, original: protected */
    public int getDefaultStyleResource() {
        return 0;
    }

    protected FacebookButtonBase(Context context, AttributeSet attributeSet, int i, int i2, String str, String str2) {
        super(context, attributeSet, 0);
        if (i2 == 0) {
            i2 = getDefaultStyleResource();
        }
        if (i2 == 0) {
            i2 = R.style.com_facebook_button;
        }
        configureButton(context, attributeSet, i, i2);
        this.analyticsButtonCreatedEventName = str;
        this.analyticsButtonTappedEventName = str2;
        setClickable(true);
        setFocusable(true);
    }

    public void setFragment(Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }

    public void setFragment(android.app.Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }

    public Fragment getFragment() {
        return this.parentFragment != null ? this.parentFragment.getSupportFragment() : null;
    }

    public android.app.Fragment getNativeFragment() {
        return this.parentFragment != null ? this.parentFragment.getNativeFragment() : null;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.externalOnClickListener = onClickListener;
    }

    public int getRequestCode() {
        return getDefaultRequestCode();
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            logButtonCreated(getContext());
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (((getGravity() & 1) != 0 ? 1 : false) != 0) {
            int compoundPaddingLeft = getCompoundPaddingLeft();
            int compoundPaddingRight = getCompoundPaddingRight();
            int min = Math.min((((getWidth() - (getCompoundDrawablePadding() + compoundPaddingLeft)) - compoundPaddingRight) - measureTextWidth(getText().toString())) / 2, (compoundPaddingLeft - getPaddingLeft()) / 2);
            this.overrideCompoundPaddingLeft = compoundPaddingLeft - min;
            this.overrideCompoundPaddingRight = compoundPaddingRight + min;
            this.overrideCompoundPadding = true;
        }
        super.onDraw(canvas);
        this.overrideCompoundPadding = false;
    }

    public int getCompoundPaddingLeft() {
        if (this.overrideCompoundPadding) {
            return this.overrideCompoundPaddingLeft;
        }
        return super.getCompoundPaddingLeft();
    }

    public int getCompoundPaddingRight() {
        if (this.overrideCompoundPadding) {
            return this.overrideCompoundPaddingRight;
        }
        return super.getCompoundPaddingRight();
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0015  */
    public android.app.Activity getActivity() {
        /*
        r3 = this;
        r0 = r3.getContext();
    L_0x0004:
        r1 = r0 instanceof android.app.Activity;
        if (r1 != 0) goto L_0x0013;
    L_0x0008:
        r2 = r0 instanceof android.content.ContextWrapper;
        if (r2 == 0) goto L_0x0013;
    L_0x000c:
        r0 = (android.content.ContextWrapper) r0;
        r0 = r0.getBaseContext();
        goto L_0x0004;
    L_0x0013:
        if (r1 == 0) goto L_0x0018;
    L_0x0015:
        r0 = (android.app.Activity) r0;
        return r0;
    L_0x0018:
        r0 = new com.facebook.FacebookException;
        r1 = "Unable to get Activity.";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookButtonBase.getActivity():android.app.Activity");
    }

    /* Access modifiers changed, original: protected */
    public int measureTextWidth(String str) {
        return (int) Math.ceil((double) getPaint().measureText(str));
    }

    /* Access modifiers changed, original: protected */
    public void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        parseBackgroundAttributes(context, attributeSet, i, i2);
        parseCompoundDrawableAttributes(context, attributeSet, i, i2);
        parseContentAttributes(context, attributeSet, i, i2);
        parseTextAttributes(context, attributeSet, i, i2);
        setupOnClickListener();
    }

    /* Access modifiers changed, original: protected */
    public void callExternalOnClickListener(View view) {
        if (this.externalOnClickListener != null) {
            this.externalOnClickListener.onClick(view);
        }
    }

    /* Access modifiers changed, original: protected */
    public void setInternalOnClickListener(OnClickListener onClickListener) {
        this.internalOnClickListener = onClickListener;
    }

    private void logButtonCreated(Context context) {
        AppEventsLogger.newLogger(context).logSdkEvent(this.analyticsButtonCreatedEventName, null, null);
    }

    private void logButtonTapped(Context context) {
        AppEventsLogger.newLogger(context).logSdkEvent(this.analyticsButtonTappedEventName, null, null);
    }

    private void parseBackgroundAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842964}, i, i2);
            try {
                if (obtainStyledAttributes.hasValue(0)) {
                    int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        setBackgroundResource(resourceId);
                    } else {
                        setBackgroundColor(obtainStyledAttributes.getColor(0, 0));
                    }
                } else {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.com_facebook_blue));
                }
                obtainStyledAttributes.recycle();
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
            }
        }
    }

    @SuppressLint({"ResourceType"})
    private void parseCompoundDrawableAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16843119, 16843117, 16843120, 16843118, 16843121}, i, i2);
        try {
            setCompoundDrawablesWithIntrinsicBounds(obtainStyledAttributes.getResourceId(0, 0), obtainStyledAttributes.getResourceId(1, 0), obtainStyledAttributes.getResourceId(2, 0), obtainStyledAttributes.getResourceId(3, 0));
            setCompoundDrawablePadding(obtainStyledAttributes.getDimensionPixelSize(4, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void parseContentAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842966, 16842967, 16842968, 16842969}, i, i2);
        try {
            setPadding(obtainStyledAttributes.getDimensionPixelSize(0, 0), obtainStyledAttributes.getDimensionPixelSize(1, 0), obtainStyledAttributes.getDimensionPixelSize(2, 0), obtainStyledAttributes.getDimensionPixelSize(3, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void setupOnClickListener() {
        super.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FacebookButtonBase.this.logButtonTapped(FacebookButtonBase.this.getContext());
                if (FacebookButtonBase.this.internalOnClickListener != null) {
                    FacebookButtonBase.this.internalOnClickListener.onClick(view);
                } else if (FacebookButtonBase.this.externalOnClickListener != null) {
                    FacebookButtonBase.this.externalOnClickListener.onClick(view);
                }
            }
        });
    }
}
