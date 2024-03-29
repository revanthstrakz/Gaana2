package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.mediarouter.R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

final class MediaRouterThemeHelper {
    static final int COLOR_DARK_ON_LIGHT_BACKGROUND = -570425344;
    static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;
    private static final float MIN_CONTRAST = 3.0f;

    @Retention(RetentionPolicy.SOURCE)
    private @interface ControllerColorType {
    }

    private MediaRouterThemeHelper() {
    }

    static Context createThemedButtonContext(Context context) {
        Context contextThemeWrapper = new ContextThemeWrapper(context, getRouterThemeId(context));
        int themeResource = getThemeResource(contextThemeWrapper, R.attr.mediaRouteTheme);
        return themeResource != 0 ? new ContextThemeWrapper(contextThemeWrapper, themeResource) : contextThemeWrapper;
    }

    static Context createThemedDialogContext(Context context, int i, boolean z) {
        if (i == 0) {
            i = getThemeResource(context, !z ? android.support.v7.appcompat.R.attr.dialogTheme : android.support.v7.appcompat.R.attr.alertDialogTheme);
        }
        Context contextThemeWrapper = new ContextThemeWrapper(context, i);
        return getThemeResource(contextThemeWrapper, R.attr.mediaRouteTheme) != 0 ? new ContextThemeWrapper(contextThemeWrapper, getRouterThemeId(contextThemeWrapper)) : contextThemeWrapper;
    }

    static int createThemedDialogStyle(Context context) {
        int themeResource = getThemeResource(context, R.attr.mediaRouteTheme);
        return themeResource == 0 ? getRouterThemeId(context) : themeResource;
    }

    static int getThemeResource(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(i, typedValue, true) ? typedValue.resourceId : 0;
    }

    static float getDisabledAlpha(Context context) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(16842803, typedValue, true) ? typedValue.getFloat() : 0.5f;
    }

    static int getControllerColor(Context context, int i) {
        if (ColorUtils.calculateContrast(-1, getThemeColor(context, i, android.support.v7.appcompat.R.attr.colorPrimary)) >= 3.0d) {
            return -1;
        }
        return COLOR_DARK_ON_LIGHT_BACKGROUND;
    }

    static int getButtonTextColor(Context context) {
        int themeColor = getThemeColor(context, 0, android.support.v7.appcompat.R.attr.colorPrimary);
        return ColorUtils.calculateContrast(themeColor, getThemeColor(context, 0, 16842801)) < 3.0d ? getThemeColor(context, 0, android.support.v7.appcompat.R.attr.colorAccent) : themeColor;
    }

    static void setMediaControlsBackgroundColor(Context context, View view, View view2, boolean z) {
        int i;
        int themeColor = getThemeColor(context, 0, android.support.v7.appcompat.R.attr.colorPrimary);
        int themeColor2 = getThemeColor(context, 0, android.support.v7.appcompat.R.attr.colorPrimaryDark);
        if (z && getControllerColor(context, 0) == COLOR_DARK_ON_LIGHT_BACKGROUND) {
            i = -1;
        } else {
            i = themeColor;
            themeColor = themeColor2;
        }
        view.setBackgroundColor(i);
        view2.setBackgroundColor(themeColor);
        view.setTag(Integer.valueOf(i));
        view2.setTag(Integer.valueOf(themeColor));
    }

    static void setVolumeSliderColor(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider, View view) {
        int controllerColor = getControllerColor(context, 0);
        if (Color.alpha(controllerColor) != 255) {
            controllerColor = ColorUtils.compositeColors(controllerColor, ((Integer) view.getTag()).intValue());
        }
        mediaRouteVolumeSlider.setColor(controllerColor);
    }

    private static boolean isLightTheme(Context context) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.isLightTheme, typedValue, true) && typedValue.data != 0;
    }

    private static int getThemeColor(Context context, int i, int i2) {
        if (i != 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{i2});
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            if (color != 0) {
                return color;
            }
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        if (typedValue.resourceId != 0) {
            return context.getResources().getColor(typedValue.resourceId);
        }
        return typedValue.data;
    }

    private static int getRouterThemeId(Context context) {
        if (isLightTheme(context)) {
            if (getControllerColor(context, 0) == COLOR_DARK_ON_LIGHT_BACKGROUND) {
                return R.style.Theme_MediaRouter_Light;
            }
            return R.style.Theme_MediaRouter_Light_DarkControlPanel;
        } else if (getControllerColor(context, 0) == COLOR_DARK_ON_LIGHT_BACKGROUND) {
            return R.style.Theme_MediaRouter_LightControlPanel;
        } else {
            return R.style.Theme_MediaRouter;
        }
    }
}
