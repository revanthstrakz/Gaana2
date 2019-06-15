package com.facebook.accountkit.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.accountkit.R;
import com.facebook.accountkit.ui.SkinManager.Skin;

final class ViewUtility {
    private static final double TEXT_COLOR_CONTRAST_THRESHOLD = 1.5d;

    ViewUtility() {
    }

    static void applyThemeAttributes(Context context, UIManager uIManager, View view) {
        if (context != null && view != null) {
            if (view instanceof Button) {
                applyButtonThemeAttributes(context, uIManager, (Button) view);
            } else if (view instanceof EditText) {
                applyInputThemeAttributes(context, uIManager, (EditText) view);
            } else if (view instanceof ProgressBar) {
                applyProgressBarThemeAttributes(context, uIManager, (ProgressBar) view);
            } else if (view instanceof CountryCodeSpinner) {
                applySpinnerThemeAttributes(context, uIManager, (CountryCodeSpinner) view);
            } else if (view instanceof TextView) {
                applyTextViewThemeAttributes(context, uIManager, (TextView) view);
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    applyThemeAttributes(context, uIManager, viewGroup.getChildAt(i));
                }
            }
        }
    }

    static void applyThemeBackground(Context context, UIManager uIManager, View view) {
        if (context != null && view != null) {
            if (uIManager instanceof SkinManager) {
                applySkinThemedBackground(context, (SkinManager) uIManager, view);
            } else {
                applyLegacyThemedBackground(context, view);
            }
        }
    }

    private static void applySkinThemedBackground(Context context, SkinManager skinManager, View view) {
        Drawable drawable;
        if (skinManager.hasBackgroundImage()) {
            drawable = getDrawable(context.getResources(), skinManager.getBackgroundImageResId());
        } else {
            drawable = new ColorDrawable(ContextCompat.getColor(context, R.color.com_accountkit_default_skin_background));
        }
        if (skinManager.hasBackgroundImage()) {
            if (view instanceof AspectFrameLayout) {
                AspectFrameLayout aspectFrameLayout = (AspectFrameLayout) view;
                aspectFrameLayout.setAspectWidth(drawable.getIntrinsicWidth());
                aspectFrameLayout.setAspectHeight(drawable.getIntrinsicHeight());
            }
            drawable.setColorFilter(skinManager.getTintColor(), Mode.SRC_ATOP);
        }
        setBackground(view, drawable);
    }

    private static Drawable getLegacyThemedBackground(Context context, View view) {
        Drawable colorDrawable;
        Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.com_accountkit_background, typedValue, true);
        if (typedValue.resourceId == 0) {
            colorDrawable = new ColorDrawable(getColor(context, R.attr.com_accountkit_background_color, -1));
        } else {
            colorDrawable = getDrawable(context.getResources(), typedValue.resourceId);
        }
        if (typedValue.resourceId > 0) {
            if (view instanceof AspectFrameLayout) {
                AspectFrameLayout aspectFrameLayout = (AspectFrameLayout) view;
                aspectFrameLayout.setAspectWidth(colorDrawable.getIntrinsicWidth());
                aspectFrameLayout.setAspectHeight(colorDrawable.getIntrinsicHeight());
            }
            applyThemeColor(context, colorDrawable, getColor(context, R.attr.com_accountkit_background_color, -1));
        }
        return colorDrawable;
    }

    private static void applyLegacyThemedBackground(Context context, View view) {
        setBackground(view, getLegacyThemedBackground(context, view));
    }

    static void applyThemeColor(Context context, Drawable drawable, @ColorInt int i) {
        if (context != null && drawable != null) {
            drawable.setColorFilter(i, Mode.SRC_ATOP);
        }
    }

    static void applyThemeColor(Context context, ImageView imageView, @ColorInt int i) {
        if (context != null && imageView != null) {
            imageView.setColorFilter(i, Mode.SRC_ATOP);
        }
    }

    static int getDimensionPixelSize(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    static boolean isTablet(Context context) {
        boolean z = (context.getResources().getConfiguration().screenLayout & 15) == 4;
        boolean z2 = (context.getResources().getConfiguration().screenLayout & 15) == 3;
        if (z || z2) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Missing block: B:7:0x0020, code skipped:
            return;
     */
    static void showKeyboard(android.view.View r2) {
        /*
        if (r2 == 0) goto L_0x0020;
    L_0x0002:
        r0 = r2.getContext();
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        goto L_0x0020;
    L_0x0009:
        r0 = r2.requestFocus();
        if (r0 == 0) goto L_0x001f;
    L_0x000f:
        r0 = r2.getContext();
        r1 = "input_method";
        r0 = r0.getSystemService(r1);
        r0 = (android.view.inputmethod.InputMethodManager) r0;
        r1 = 1;
        r0.showSoftInput(r2, r1);
    L_0x001f:
        return;
    L_0x0020:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.ui.ViewUtility.showKeyboard(android.view.View):void");
    }

    static void hideKeyboard(Activity activity) {
        View findViewById = activity.findViewById(16908290);
        if (findViewById != null) {
            View findFocus = findViewById.findFocus();
            if (findFocus != null) {
                findFocus.clearFocus();
            }
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(findViewById.getWindowToken(), 1);
        }
    }

    static boolean useLegacy(UIManager uIManager) {
        return !(uIManager instanceof SkinManager);
    }

    static boolean isSkin(UIManager uIManager, Skin skin) {
        return (uIManager instanceof SkinManager) && ((SkinManager) uIManager).getSkin() == skin;
    }

    private static void applyButtonThemeAttributes(Context context, UIManager uIManager, Button button) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int color;
        int buttonColor;
        int color2;
        int color3;
        int color4;
        if (useLegacy(uIManager)) {
            buttonColor = getButtonColor(context, uIManager);
            color2 = getColor(context, R.attr.com_accountkit_button_border_color, buttonColor);
            color3 = getColor(context, R.attr.com_accountkit_button_pressed_background_color, -3355444);
            int color5 = getColor(context, R.attr.com_accountkit_button_pressed_border_color, color3);
            color4 = getColor(context, R.attr.com_accountkit_button_disabled_background_color, -3355444);
            i = buttonColor;
            i2 = color2;
            i3 = color3;
            i4 = color4;
            i5 = color5;
            color = getColor(context, R.attr.com_accountkit_button_disabled_border_color, color4);
        } else {
            buttonColor = getPrimaryColor(context, uIManager);
            color2 = ((SkinManager) uIManager).getDisabledColor(buttonColor);
            color4 = 0;
            color3 = isSkin(uIManager, Skin.TRANSLUCENT) ? 0 : buttonColor;
            if (!isSkin(uIManager, Skin.TRANSLUCENT)) {
                color4 = color2;
            }
            if (isSkin(uIManager, Skin.TRANSLUCENT)) {
                color2 = buttonColor;
            }
            i = buttonColor;
            i2 = i;
            i5 = i2;
            color = color2;
            i3 = color3;
            i4 = color4;
        }
        setBackground(button, getButtonBackgroundDrawable(context, i, i2, i3, i5, i4, color));
        button.setTextColor(getButtonTextColorStateList(context, uIManager));
    }

    private static Drawable getButtonBackgroundDrawable(Context context, int i, int i2, int i3, int i4, int i5, int i6) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (VERSION.SDK_INT >= 21) {
            stateListDrawable.addState(new int[]{-16842910}, new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{i}), new ColorDrawable(i5), null));
            stateListDrawable.addState(new int[0], new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{i5}), new ColorDrawable(i), null));
        } else {
            stateListDrawable.addState(new int[]{-16842910}, getInputBackgroundDrawable(context, i5, i6));
            stateListDrawable.addState(new int[]{16842919}, getInputBackgroundDrawable(context, i3, i4));
            stateListDrawable.addState(new int[0], getInputBackgroundDrawable(context, i, i2));
        }
        return stateListDrawable;
    }

    @ColorInt
    static int getButtonTextColor(Context context, UIManager uIManager) {
        if (useLegacy(uIManager)) {
            return getColor(context, R.attr.com_accountkit_button_text_color, (int) ViewCompat.MEASURED_STATE_MASK);
        }
        return ((SkinManager) uIManager).getTextColor();
    }

    private static ColorStateList getButtonTextColorStateList(Context context, UIManager uIManager) {
        int[] iArr;
        r1 = new int[3][];
        r1[0] = new int[]{-16842910};
        r1[1] = new int[]{16842919};
        r1[2] = new int[0];
        if (useLegacy(uIManager)) {
            iArr = new int[]{getColor(context, R.attr.com_accountkit_button_disabled_text_color, -3355444), getColor(context, R.attr.com_accountkit_button_pressed_text_color, -12303292), getColor(context, R.attr.com_accountkit_button_text_color, (int) ViewCompat.MEASURED_STATE_MASK)};
        } else {
            int textColor = ((SkinManager) uIManager).getTextColor();
            iArr = new int[]{textColor, textColor, textColor};
        }
        return new ColorStateList(r1, iArr);
    }

    private static void applyTextViewThemeAttributes(Context context, UIManager uIManager, TextView textView) {
        int color;
        if (useLegacy(uIManager)) {
            color = getColor(context, R.attr.com_accountkit_text_color, ContextCompat.getColor(context, 17170433));
        } else {
            color = ((SkinManager) uIManager).getTextColor();
        }
        textView.setTextColor(color);
        textView.setLinkTextColor(color);
    }

    static boolean doesTextColorContrast(Context context, UIManager uIManager) {
        Theme newTheme;
        int color;
        int color2;
        if (uIManager.getThemeId() != -1) {
            newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.applyStyle(uIManager.getThemeId(), true);
        } else {
            newTheme = context.getTheme();
        }
        if (useLegacy(uIManager)) {
            color = getColor(newTheme, R.attr.com_accountkit_text_color, ContextCompat.getColor(context, 17170433));
        } else {
            color = ((SkinManager) uIManager).getTextColor();
        }
        if (useLegacy(uIManager)) {
            color2 = getColor(newTheme, R.attr.com_accountkit_background_color, -1);
        } else {
            color2 = ((SkinManager) uIManager).getTintColor();
        }
        if (ColorUtils.calculateContrast(color | ViewCompat.MEASURED_STATE_MASK, color2 | ViewCompat.MEASURED_STATE_MASK) >= 1.5d) {
            return true;
        }
        return false;
    }

    private static void applyInputThemeAttributes(Context context, UIManager uIManager, EditText editText) {
        if (!useLegacy(uIManager)) {
            editText.setTextColor(((SkinManager) uIManager).getTextColor());
        }
        if (isSkin(uIManager, Skin.CONTEMPORARY)) {
            int primaryColor = getPrimaryColor(context, uIManager);
            Drawable mutate = DrawableCompat.wrap(editText.getBackground()).mutate();
            DrawableCompat.setTint(mutate, primaryColor);
            setBackground(editText, mutate);
            editText.setTextColor(((SkinManager) uIManager).getTextColor());
            return;
        }
        applyInputThemeAttributes(context, uIManager, (View) editText);
    }

    private static void applyInputThemeAttributes(Context context, UIManager uIManager, View view) {
        int color;
        if (useLegacy(uIManager)) {
            color = getColor(context, R.attr.com_accountkit_input_background_color, -3355444);
            setBackground(view, getInputBackgroundDrawable(context, color, getColor(context, R.attr.com_accountkit_input_border_color, color)));
        } else if (isSkin(uIManager, Skin.TRANSLUCENT)) {
            setBackground(view, getInputBackgroundDrawable(context, 0, getPrimaryColor(context, uIManager)));
        } else {
            color = ((SkinManager) uIManager).getDisabledColor(getPrimaryColor(context, uIManager));
            setBackground(view, getInputBackgroundDrawable(context, color, color));
        }
    }

    private static void applyProgressBarThemeAttributes(Context context, UIManager uIManager, ProgressBar progressBar) {
        int color;
        Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
        if (useLegacy(uIManager)) {
            color = getColor(context, R.attr.com_accountkit_icon_color, (int) ViewCompat.MEASURED_STATE_MASK);
        } else {
            color = getPrimaryColor(context, uIManager);
        }
        applyThemeColor(context, indeterminateDrawable, color);
    }

    private static void applySpinnerThemeAttributes(Context context, UIManager uIManager, CountryCodeSpinner countryCodeSpinner) {
        View view = (ViewGroup) countryCodeSpinner.getParent();
        ImageView imageView = (ImageView) view.getChildAt(1);
        View childAt = view.getChildAt(2);
        Drawable mutate = DrawableCompat.wrap(imageView.getDrawable()).mutate();
        if (isSkin(uIManager, Skin.CONTEMPORARY)) {
            childAt.setVisibility(0);
            setBackground(childAt, new ColorDrawable(getPrimaryColor(context, uIManager)));
            DrawableCompat.setTint(mutate, getPrimaryColor(context, uIManager));
        } else if (isSkin(uIManager, Skin.TRANSLUCENT) || isSkin(uIManager, Skin.CLASSIC)) {
            childAt.setVisibility(8);
            DrawableCompat.setTint(mutate, ((SkinManager) uIManager).getTextColor());
            applyInputThemeAttributes(context, uIManager, view);
        } else {
            childAt.setVisibility(8);
            DrawableCompat.setTint(mutate, getColor(context, R.attr.com_accountkit_input_accent_color, (int) ViewCompat.MEASURED_STATE_MASK));
            applyInputThemeAttributes(context, uIManager, view);
        }
    }

    @ColorInt
    static int getButtonColor(Context context, UIManager uIManager) {
        if (uIManager instanceof SkinManager) {
            return ((SkinManager) uIManager).getPrimaryColor();
        }
        return getColor(context, R.attr.com_accountkit_button_background_color, -3355444);
    }

    @ColorInt
    static int getPrimaryColor(Context context, UIManager uIManager) {
        if (uIManager instanceof SkinManager) {
            return ((SkinManager) uIManager).getPrimaryColor();
        }
        return getColor(context, R.attr.com_accountkit_primary_color, -3355444);
    }

    @ColorInt
    static int getColor(Context context, @AttrRes int i, int i2) {
        return getColor(context.getTheme(), i, i2);
    }

    @ColorInt
    static int getColor(Theme theme, @AttrRes int i, int i2) {
        TypedValue typedValue = new TypedValue();
        return theme.resolveAttribute(i, typedValue, true) ? typedValue.data : i2;
    }

    private static Drawable getDrawable(Resources resources, int i) {
        if (VERSION.SDK_INT < 22) {
            return resources.getDrawable(i);
        }
        return resources.getDrawable(i, null);
    }

    private static Drawable getInputBackgroundDrawable(Context context, @ColorInt int i, @ColorInt int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        Resources resources = context.getResources();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(resources.getDimension(R.dimen.com_accountkit_input_corner_radius));
        gradientDrawable.setStroke(resources.getDimensionPixelSize(R.dimen.com_accountkit_input_border), i2);
        return gradientDrawable;
    }

    static void setBackground(View view, Drawable drawable) {
        if (VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
