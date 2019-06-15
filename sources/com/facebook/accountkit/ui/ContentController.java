package com.facebook.accountkit.ui;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;

interface ContentController {
    ContentFragment getBottomFragment();

    ContentFragment getCenterFragment();

    @Nullable
    View getFocusView();

    TitleFragment getFooterFragment();

    TitleFragment getHeaderFragment();

    LoginFlowState getLoginFlowState();

    ContentFragment getTextFragment();

    ContentFragment getTopFragment();

    boolean isTransient();

    void onPause(Activity activity);

    void onResume(Activity activity);

    void setBottomFragment(@Nullable ContentFragment contentFragment);

    void setCenterFragment(@Nullable ContentFragment contentFragment);

    void setFooterFragment(@Nullable TitleFragment titleFragment);

    void setHeaderFragment(@Nullable TitleFragment titleFragment);

    void setTextFragment(@Nullable ContentFragment contentFragment);

    void setTopFragment(@Nullable ContentFragment contentFragment);
}
