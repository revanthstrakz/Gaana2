package com.helpshift.support.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.support.h.e;
import com.helpshift.support.util.AppSessionConstants.Screen;
import com.helpshift.util.o;
import com.helpshift.util.w;

public class ConversationInfoFragment extends MainFragment {
    private static final Screen a = Screen.CONVERSATION_INFO;

    public boolean b() {
        return true;
    }

    public static ConversationInfoFragment a(Bundle bundle) {
        ConversationInfoFragment conversationInfoFragment = new ConversationInfoFragment();
        conversationInfoFragment.setArguments(bundle);
        return conversationInfoFragment;
    }

    public void onStart() {
        super.onStart();
        e.b().a("current_open_screen", a);
    }

    public void onResume() {
        super.onResume();
        b(getString(k.hs__conversation_info_header));
        if (!l()) {
            o.d().f().a(AnalyticsEventType.CONVERSATION_INFORMATION_OPENED);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(h.hs__conversation_info_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        final String string = getArguments().getString("issue_publish_id");
        TextView textView = (TextView) view.findViewById(f.issue_publish_id);
        final ImageButton imageButton = (ImageButton) view.findViewById(f.issue_id_copy_btn);
        com.helpshift.support.util.h.c(getContext(), imageButton.getDrawable());
        if (!w.a(string)) {
            textView.setText(getString(k.hs__conversation_info_id_format, string));
        }
        imageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ConversationInfoFragment.this.c(string);
            }
        });
        imageButton.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                new com.helpshift.views.e(imageButton, ConversationInfoFragment.this.getString(k.hs__copy_to_clipboard_tooltip)).a();
                return true;
            }
        });
    }

    public void onStop() {
        super.onStop();
        Screen screen = (Screen) e.b().a("current_open_screen");
        if (screen != null && screen.equals(a)) {
            e.b().b("current_open_screen");
        }
    }
}
