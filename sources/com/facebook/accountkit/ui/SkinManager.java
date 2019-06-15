package com.facebook.accountkit.ui;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;

public final class SkinManager extends BaseUIManager {
    public static final Creator<SkinManager> CREATOR = new Creator<SkinManager>() {
        public SkinManager createFromParcel(Parcel parcel) {
            return new SkinManager(parcel, null);
        }

        public SkinManager[] newArray(int i) {
            return new SkinManager[i];
        }
    };
    private static final double DISABLED_COLOR_ALPHA = 0.25d;
    private static final double MAXIMUM_TINT_INTENSITY = 0.85d;
    private static final double MINIMUM_TINT_INTENSITY = 0.55d;
    @DrawableRes
    private final int backgroundImage;
    @ColorInt
    private final int primaryColor;
    private final Skin skin;
    private final Tint tint;
    private final double tintIntensity;

    /* renamed from: com.facebook.accountkit.ui.SkinManager$2 */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$accountkit$ui$SkinManager$Tint = new int[Tint.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Missing block: B:7:?, code skipped:
            return;
     */
        static {
            /*
            r0 = com.facebook.accountkit.ui.SkinManager.Tint.values();
            r0 = r0.length;
            r0 = new int[r0];
            $SwitchMap$com$facebook$accountkit$ui$SkinManager$Tint = r0;
            r0 = $SwitchMap$com$facebook$accountkit$ui$SkinManager$Tint;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.facebook.accountkit.ui.SkinManager.Tint.WHITE;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = $SwitchMap$com$facebook$accountkit$ui$SkinManager$Tint;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = com.facebook.accountkit.ui.SkinManager.Tint.BLACK;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.ui.SkinManager$AnonymousClass2.<clinit>():void");
        }
    }

    public enum Skin {
        NONE,
        CLASSIC,
        CONTEMPORARY,
        TRANSLUCENT
    }

    public enum Tint {
        WHITE,
        BLACK
    }

    public int describeContents() {
        return 0;
    }

    public SkinManager(Skin skin, @ColorInt int i, @DrawableRes int i2, Tint tint, double d) {
        super(-1);
        this.skin = skin;
        this.primaryColor = i;
        this.backgroundImage = i2;
        if (hasBackgroundImage()) {
            this.tint = tint;
            this.tintIntensity = Math.min(MAXIMUM_TINT_INTENSITY, Math.max(MINIMUM_TINT_INTENSITY, d));
            return;
        }
        this.tint = Tint.WHITE;
        this.tintIntensity = MINIMUM_TINT_INTENSITY;
    }

    public SkinManager(Skin skin, @ColorInt int i) {
        this(skin, i, -1, Tint.WHITE, MINIMUM_TINT_INTENSITY);
    }

    private SkinManager(Parcel parcel) {
        super(parcel);
        this.skin = Skin.values()[parcel.readInt()];
        this.primaryColor = parcel.readInt();
        this.backgroundImage = parcel.readInt();
        this.tint = Tint.values()[parcel.readInt()];
        this.tintIntensity = parcel.readDouble();
    }

    public Skin getSkin() {
        return this.skin;
    }

    public boolean hasBackgroundImage() {
        return this.backgroundImage >= 0;
    }

    /* Access modifiers changed, original: 0000 */
    @DrawableRes
    public int getBackgroundImageResId() {
        return this.backgroundImage;
    }

    public Tint getTint() {
        return this.tint;
    }

    public double getTintIntensity() {
        return this.tintIntensity;
    }

    /* Access modifiers changed, original: 0000 */
    @ColorInt
    public int getDisabledColor(@ColorInt int i) {
        int i2 = AnonymousClass2.$SwitchMap$com$facebook$accountkit$ui$SkinManager$Tint[this.tint.ordinal()] != 1 ? ViewCompat.MEASURED_STATE_MASK : -1;
        return Color.rgb((int) ((((double) Color.red(i)) * DISABLED_COLOR_ALPHA) + (((double) Color.red(i2)) * 0.75d)), (int) ((((double) Color.green(i)) * DISABLED_COLOR_ALPHA) + (((double) Color.green(i2)) * 0.75d)), (int) ((DISABLED_COLOR_ALPHA * ((double) Color.blue(i))) + (0.75d * ((double) Color.blue(i2)))));
    }

    @ColorInt
    public int getPrimaryColor() {
        return this.primaryColor;
    }

    /* Access modifiers changed, original: 0000 */
    @ColorInt
    public int getTintColor() {
        return AnonymousClass2.$SwitchMap$com$facebook$accountkit$ui$SkinManager$Tint[this.tint.ordinal()] != 1 ? Color.argb((int) (255.0d * this.tintIntensity), 0, 0, 0) : Color.argb((int) (255.0d * this.tintIntensity), 255, 255, 255);
    }

    /* Access modifiers changed, original: 0000 */
    @ColorInt
    public int getTextColor() {
        return AnonymousClass2.$SwitchMap$com$facebook$accountkit$ui$SkinManager$Tint[getTint().ordinal()] != 2 ? ViewCompat.MEASURED_STATE_MASK : -1;
    }

    @Nullable
    public Fragment getBodyFragment(LoginFlowState loginFlowState) {
        return super.getBodyFragment(loginFlowState);
    }

    @Nullable
    public ButtonType getButtonType(LoginFlowState loginFlowState) {
        return super.getButtonType(loginFlowState);
    }

    @Nullable
    public Fragment getFooterFragment(LoginFlowState loginFlowState) {
        return super.getFooterFragment(loginFlowState);
    }

    @Nullable
    public Fragment getHeaderFragment(LoginFlowState loginFlowState) {
        return super.getHeaderFragment(loginFlowState);
    }

    @Nullable
    public TextPosition getTextPosition(LoginFlowState loginFlowState) {
        return super.getTextPosition(loginFlowState);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.skin.ordinal());
        parcel.writeInt(this.primaryColor);
        parcel.writeInt(this.backgroundImage);
        parcel.writeInt(this.tint.ordinal());
        parcel.writeDouble(this.tintIntensity);
    }
}
