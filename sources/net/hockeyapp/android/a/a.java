package net.hockeyapp.android.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.views.FeedbackMessageView;

public class a extends BaseAdapter {
    private Context a;
    private ArrayList<FeedbackMessage> b;

    public long getItemId(int i) {
        return (long) i;
    }

    public a(Context context, ArrayList<FeedbackMessage> arrayList) {
        this.a = context;
        this.b = arrayList;
    }

    public int getCount() {
        return this.b.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        FeedbackMessageView feedbackMessageView;
        FeedbackMessage feedbackMessage = (FeedbackMessage) this.b.get(i);
        if (view == null) {
            feedbackMessageView = new FeedbackMessageView(this.a, null);
        } else {
            feedbackMessageView = (FeedbackMessageView) view;
        }
        if (feedbackMessage != null) {
            feedbackMessageView.setFeedbackMessage(feedbackMessage);
        }
        feedbackMessageView.setIndex(i);
        return feedbackMessageView;
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public void a() {
        if (this.b != null) {
            this.b.clear();
        }
    }

    public void a(FeedbackMessage feedbackMessage) {
        if (feedbackMessage != null && this.b != null) {
            this.b.add(feedbackMessage);
        }
    }
}
