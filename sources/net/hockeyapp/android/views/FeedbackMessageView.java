package net.hockeyapp.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.hockeyapp.android.c.a;
import net.hockeyapp.android.i;
import net.hockeyapp.android.i.b;
import net.hockeyapp.android.i.c;
import net.hockeyapp.android.objects.FeedbackAttachment;
import net.hockeyapp.android.objects.FeedbackMessage;

public class FeedbackMessageView extends LinearLayout {
    @SuppressLint({"SimpleDateFormat"})
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    @SuppressLint({"SimpleDateFormat"})
    private static final SimpleDateFormat b = new SimpleDateFormat("d MMM h:mm a");
    private TextView c = ((TextView) findViewById(b.label_author));
    private TextView d = ((TextView) findViewById(b.label_date));
    private TextView e = ((TextView) findViewById(b.label_text));
    private AttachmentListView f = ((AttachmentListView) findViewById(b.list_attachments));
    private FeedbackMessage g;
    private final Context h;

    public FeedbackMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = context;
        LayoutInflater.from(context).inflate(c.hockeyapp_view_feedback_message, this);
    }

    public void setFeedbackMessage(FeedbackMessage feedbackMessage) {
        this.g = feedbackMessage;
        try {
            this.d.setText(b.format(a.parse(this.g.b())));
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
        }
        this.c.setText(this.g.d());
        this.e.setText(this.g.a());
        this.f.removeAllViews();
        for (FeedbackAttachment feedbackAttachment : this.g.e()) {
            AttachmentView attachmentView = new AttachmentView(this.h, this.f, feedbackAttachment, false);
            a.a().a(feedbackAttachment, attachmentView);
            this.f.addView(attachmentView);
        }
    }

    public void setIndex(int i) {
        if (i % 2 == 0) {
            setBackgroundColor(getResources().getColor(i.a.hockeyapp_background_light));
            this.c.setTextColor(getResources().getColor(i.a.hockeyapp_text_white));
            this.d.setTextColor(getResources().getColor(i.a.hockeyapp_text_white));
        } else {
            setBackgroundColor(getResources().getColor(i.a.hockeyapp_background_white));
            this.c.setTextColor(getResources().getColor(i.a.hockeyapp_text_light));
            this.d.setTextColor(getResources().getColor(i.a.hockeyapp_text_light));
        }
        this.e.setTextColor(getResources().getColor(i.a.hockeyapp_text_black));
    }
}
