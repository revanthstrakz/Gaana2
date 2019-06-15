package com.helpshift.support.conversations;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.conversation.c.f;
import com.helpshift.conversation.dto.c;
import com.helpshift.e;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.support.Faq;
import com.helpshift.support.fragments.HSMenuItemType;
import com.helpshift.support.fragments.ScreenshotPreviewFragment.LaunchSource;
import com.helpshift.support.fragments.ScreenshotPreviewFragment.ScreenshotAction;
import com.helpshift.support.util.AppSessionConstants.Screen;
import com.helpshift.util.o;
import java.util.ArrayList;

public class NewConversationFragment extends BaseConversationFragment implements e {
    f a;
    private d b;
    private TextInputEditText d;
    private c e;
    private boolean f;

    /* Access modifiers changed, original: protected */
    public int a() {
        return 1;
    }

    public static NewConversationFragment a(Bundle bundle) {
        NewConversationFragment newConversationFragment = new NewConversationFragment();
        newConversationFragment.setArguments(bundle);
        return newConversationFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__new_conversation_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        a(view);
        super.onViewCreated(view, bundle);
        b(view);
    }

    public void onStart() {
        super.onStart();
        if (!l()) {
            o.d().q().o();
        }
    }

    private void a(View view) {
        boolean z;
        View view2 = view;
        TextInputLayout textInputLayout = (TextInputLayout) view2.findViewById(e.f.hs__conversationDetailWrapper);
        textInputLayout.setHintEnabled(false);
        textInputLayout.setHintAnimationEnabled(false);
        this.d = (TextInputEditText) view2.findViewById(e.f.hs__conversationDetail);
        TextInputLayout textInputLayout2 = (TextInputLayout) view2.findViewById(e.f.hs__usernameWrapper);
        textInputLayout2.setHintEnabled(false);
        textInputLayout2.setHintAnimationEnabled(false);
        TextInputEditText textInputEditText = (TextInputEditText) view2.findViewById(e.f.hs__username);
        TextInputLayout textInputLayout3 = (TextInputLayout) view2.findViewById(e.f.hs__emailWrapper);
        textInputLayout3.setHintEnabled(false);
        textInputLayout3.setHintAnimationEnabled(false);
        TextInputEditText textInputEditText2 = (TextInputEditText) view2.findViewById(e.f.hs__email);
        ProgressBar progressBar = (ProgressBar) view2.findViewById(e.f.progress_bar);
        ImageView imageView = (ImageView) view2.findViewById(e.f.hs__screenshot);
        TextView textView = (TextView) view2.findViewById(e.f.attachment_file_name);
        TextView textView2 = (TextView) view2.findViewById(e.f.attachment_file_size);
        CardView cardView = (CardView) view2.findViewById(e.f.screenshot_view_container);
        ImageButton imageButton = (ImageButton) view2.findViewById(16908314);
        d dVar = r0;
        TextInputEditText textInputEditText3 = textInputEditText2;
        TextInputEditText textInputEditText4 = textInputEditText;
        d dVar2 = new d(getContext(), textInputLayout, this.d, textInputLayout2, textInputEditText, textInputLayout3, textInputEditText2, progressBar, imageView, textView, textView2, cardView, imageButton, getView(), this, e());
        this.b = dVar;
        this.a = o.d().a(this.b);
        if (this.f) {
            this.a.a(this.e);
            z = false;
            this.f = false;
        } else {
            z = false;
        }
        this.d.addTextChangedListener(new f() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NewConversationFragment.this.a.a(charSequence.toString());
            }
        });
        textInputEditText4.addTextChangedListener(new f() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NewConversationFragment.this.a.b(charSequence.toString());
            }
        });
        textInputEditText3.addTextChangedListener(new f() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NewConversationFragment.this.a.c(charSequence.toString());
            }
        });
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.a.d(arguments.getString("source_search_query"));
            this.a.b(arguments.getBoolean("dropMeta"));
            this.a.a(getArguments().getBoolean("search_performed", z));
        }
    }

    private void b(View view) {
        this.d = (TextInputEditText) view.findViewById(e.f.hs__conversationDetail);
        this.d.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getId() == e.f.hs__conversationDetail) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((motionEvent.getAction() & 255) == 1) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        ImageButton imageButton = (ImageButton) view.findViewById(16908314);
        imageButton.setVisibility(8);
        ImageView imageView = (ImageView) view.findViewById(e.f.hs__screenshot);
        imageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NewConversationFragment.this.a.e();
            }
        });
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NewConversationFragment.this.a.f();
            }
        });
    }

    public void onResume() {
        super.onResume();
        this.a.g();
        if (!l()) {
            o.d().f().a(AnalyticsEventType.REPORTED_ISSUE);
        }
        this.d.requestFocus();
        com.helpshift.support.util.e.b(getContext(), this.d);
        this.a.a(1);
    }

    /* Access modifiers changed, original: protected */
    public Screen c() {
        return Screen.NEW_CONVERSATION;
    }

    public void onPause() {
        super.onPause();
        com.helpshift.support.util.e.a(getContext(), this.d);
    }

    /* Access modifiers changed, original: protected */
    public String d() {
        return getString(k.hs__new_conversation_header);
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
        if (i == 2) {
            Bundle bundle = new Bundle();
            bundle.putInt("key_screenshot_mode", a());
            e().a(false, bundle);
        }
    }

    public void onDestroyView() {
        this.a.a(this.b);
        this.a.a(-1);
        super.onDestroyView();
    }

    public boolean a(ScreenshotAction screenshotAction, c cVar) {
        switch (screenshotAction) {
            case ADD:
                if (this.a == null) {
                    this.e = cVar;
                    this.f = true;
                } else {
                    this.a.a(cVar);
                }
                return true;
            case REMOVE:
                if (this.a == null) {
                    this.e = null;
                    this.f = true;
                } else {
                    this.a.a(null);
                }
                return true;
            default:
                return false;
        }
    }

    public void g() {
        this.a.b();
    }

    public void a(ArrayList<Faq> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("search_fragment_results", arrayList);
        f().b(bundle);
    }

    public void h() {
        e().a();
    }

    public void i() {
        if (isResumed()) {
            f().e();
        }
    }

    public void a(c cVar) {
        Bundle bundle = new Bundle();
        bundle.putInt("key_screenshot_mode", 2);
        f().a(cVar, bundle, LaunchSource.ATTACHMENT_DRAFT);
    }

    public void j() {
        this.a.g();
    }

    public void a(HSMenuItemType hSMenuItemType) {
        switch (hSMenuItemType) {
            case START_NEW_CONVERSATION:
                this.a.a();
                return;
            case SCREENSHOT_ATTACHMENT:
                Bundle bundle = new Bundle();
                bundle.putInt("key_screenshot_mode", a());
                bundle.putString("key_refers_id", null);
                e().a(true, bundle);
                return;
            default:
                return;
        }
    }
}
