package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.api.client.http.HttpStatusCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.hockeyapp.android.c.g;
import net.hockeyapp.android.c.h;
import net.hockeyapp.android.d.i;
import net.hockeyapp.android.i.c;
import net.hockeyapp.android.i.d;
import net.hockeyapp.android.objects.ErrorObject;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.objects.FeedbackResponse;
import net.hockeyapp.android.objects.FeedbackUserDataElement;
import net.hockeyapp.android.views.AttachmentListView;
import net.hockeyapp.android.views.AttachmentView;

public class FeedbackActivity extends Activity implements OnClickListener {
    private String A;
    private String a;
    private String b;
    private Context c;
    private TextView d;
    private EditText e;
    private EditText f;
    private EditText g;
    private EditText h;
    private Button i;
    private Button j;
    private Button k;
    private Button l;
    private ScrollView m;
    private LinearLayout n;
    private ListView o;
    private h p;
    private Handler q;
    private g r;
    private Handler s;
    private List<Uri> t;
    private String u;
    private ErrorObject v;
    private net.hockeyapp.android.a.a w;
    private ArrayList<FeedbackMessage> x;
    private boolean y;
    private boolean z;

    private static class a extends Handler {
        private final WeakReference<FeedbackActivity> a;

        public a(FeedbackActivity feedbackActivity) {
            this.a = new WeakReference(feedbackActivity);
        }

        public void handleMessage(Message message) {
            ErrorObject errorObject = new ErrorObject();
            final FeedbackActivity feedbackActivity = (FeedbackActivity) this.a.get();
            if (feedbackActivity != null) {
                boolean z = false;
                if (message == null || message.getData() == null) {
                    errorObject.a(feedbackActivity.getString(d.hockeyapp_feedback_send_generic_error));
                } else {
                    Bundle data = message.getData();
                    String string = data.getString("feedback_response");
                    String string2 = data.getString("feedback_status");
                    String string3 = data.getString("request_type");
                    if (!string3.equals("send") || (string != null && Integer.parseInt(string2) == HttpStatusCodes.STATUS_CODE_CREATED)) {
                        if (string3.equals(InAppConstants.API_ENDPOINT_INAPPS) && string2 != null && (Integer.parseInt(string2) == 404 || Integer.parseInt(string2) == HttpStatusCodes.STATUS_CODE_UNPROCESSABLE_ENTITY)) {
                            feedbackActivity.f();
                        } else if (string != null) {
                            feedbackActivity.b(string, string3);
                        } else {
                            errorObject.a(feedbackActivity.getString(d.hockeyapp_feedback_send_network_error));
                        }
                        z = true;
                    } else {
                        errorObject.a(feedbackActivity.getString(d.hockeyapp_feedback_send_generic_error));
                    }
                }
                feedbackActivity.v = errorObject;
                if (!z) {
                    feedbackActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            feedbackActivity.a(true);
                            feedbackActivity.showDialog(0);
                        }
                    });
                }
                feedbackActivity.c(z);
            }
        }
    }

    private static class b extends Handler {
        private final WeakReference<FeedbackActivity> a;

        public b(FeedbackActivity feedbackActivity) {
            this.a = new WeakReference(feedbackActivity);
        }

        public void handleMessage(Message message) {
            final FeedbackActivity feedbackActivity = (FeedbackActivity) this.a.get();
            if (feedbackActivity != null) {
                feedbackActivity.v = new ErrorObject();
                boolean z = false;
                if (!(message == null || message.getData() == null)) {
                    FeedbackResponse feedbackResponse = (FeedbackResponse) message.getData().getSerializable("parse_feedback_response");
                    if (feedbackResponse != null && feedbackResponse.a().equalsIgnoreCase("success")) {
                        if (feedbackResponse.c() != null) {
                            net.hockeyapp.android.d.g.a().a(feedbackActivity, feedbackResponse.c());
                            feedbackActivity.a(feedbackResponse);
                            feedbackActivity.y = false;
                        }
                        z = true;
                    }
                }
                if (!z) {
                    feedbackActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            feedbackActivity.showDialog(0);
                        }
                    });
                }
                feedbackActivity.a(true);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void c(boolean z) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(a());
        setTitle(getString(d.hockeyapp_feedback_title));
        this.c = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.u = extras.getString("url");
            this.a = extras.getString("initialUserName");
            this.b = extras.getString("initialUserEmail");
            Parcelable[] parcelableArray = extras.getParcelableArray("initialAttachments");
            if (parcelableArray != null) {
                this.t = new ArrayList();
                for (Parcelable parcelable : parcelableArray) {
                    this.t.add((Uri) parcelable);
                }
            }
        }
        if (bundle != null) {
            this.z = bundle.getBoolean("feedbackViewInitialized");
            this.y = bundle.getBoolean("inSendFeedback");
        } else {
            this.y = false;
            this.z = false;
        }
        ((NotificationManager) getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).cancel(2);
        d();
        e();
        b();
    }

    /* Access modifiers changed, original: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            ViewGroup viewGroup = (ViewGroup) findViewById(net.hockeyapp.android.i.b.wrapper_attachments);
            Iterator it = bundle.getParcelableArrayList("attachments").iterator();
            while (it.hasNext()) {
                Uri uri = (Uri) it.next();
                if (!this.t.contains(uri)) {
                    viewGroup.addView(new AttachmentView((Context) this, viewGroup, uri, true));
                }
            }
            this.z = bundle.getBoolean("feedbackViewInitialized");
        }
        super.onRestoreInstanceState(bundle);
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList("attachments", ((AttachmentListView) findViewById(net.hockeyapp.android.i.b.wrapper_attachments)).getAttachments());
        bundle.putBoolean("feedbackViewInitialized", this.z);
        bundle.putBoolean("inSendFeedback", this.y);
        super.onSaveInstanceState(bundle);
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        if (this.p != null) {
            this.p.a();
        }
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.p != null) {
            this.p.a();
        }
        return this.p;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.y) {
            this.y = false;
            b();
        } else {
            finish();
        }
        return true;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == net.hockeyapp.android.i.b.button_send) {
            g();
        } else if (id == net.hockeyapp.android.i.b.button_attachment) {
            if (((ViewGroup) findViewById(net.hockeyapp.android.i.b.wrapper_attachments)).getChildCount() >= 3) {
                Toast.makeText(this, String.valueOf(3), 0).show();
            } else {
                openContextMenu(view);
            }
        } else if (id == net.hockeyapp.android.i.b.button_add_response) {
            b(false);
            this.y = true;
        } else if (id == net.hockeyapp.android.i.b.button_refresh) {
            a(this.u, null, null, null, null, null, net.hockeyapp.android.d.g.a().a(this.c), this.q, true);
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        contextMenu.add(0, 2, 0, getString(d.hockeyapp_feedback_attach_file));
        contextMenu.add(0, 1, 0, getString(d.hockeyapp_feedback_attach_picture));
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
            case 2:
                return a(menuItem.getItemId());
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 0) {
            return null;
        }
        return new Builder(this).setMessage(getString(d.hockeyapp_dialog_error_message)).setCancelable(false).setTitle(getString(d.hockeyapp_dialog_error_title)).setIcon(17301543).setPositiveButton(getString(d.hockeyapp_dialog_positive_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FeedbackActivity.this.v = null;
                dialogInterface.cancel();
            }
        }).create();
    }

    /* Access modifiers changed, original: protected */
    public void onPrepareDialog(int i, Dialog dialog) {
        if (i == 0) {
            AlertDialog alertDialog = (AlertDialog) dialog;
            if (this.v != null) {
                alertDialog.setMessage(this.v.a());
            } else {
                alertDialog.setMessage(getString(d.hockeyapp_feedback_generic_error));
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            Uri data;
            ViewGroup viewGroup;
            if (i == 2) {
                data = intent.getData();
                if (data != null) {
                    viewGroup = (ViewGroup) findViewById(net.hockeyapp.android.i.b.wrapper_attachments);
                    viewGroup.addView(new AttachmentView((Context) this, viewGroup, data, true));
                }
            } else if (i == 1) {
                data = intent.getData();
                if (data != null) {
                    try {
                        intent = new Intent(this, PaintActivity.class);
                        intent.putExtra("imageUri", data);
                        startActivityForResult(intent, 3);
                    } catch (ActivityNotFoundException e) {
                        net.hockeyapp.android.d.d.a("HockeyApp", "Paint activity not declared!", e);
                    }
                }
            } else if (i == 3) {
                data = (Uri) intent.getParcelableExtra("imageUri");
                if (data != null) {
                    viewGroup = (ViewGroup) findViewById(net.hockeyapp.android.i.b.wrapper_attachments);
                    viewGroup.addView(new AttachmentView((Context) this, viewGroup, data, true));
                }
            }
        }
    }

    @SuppressLint({"InflateParams"})
    public View a() {
        return getLayoutInflater().inflate(c.hockeyapp_activity_feedback, null);
    }

    public void a(boolean z) {
        if (this.i != null) {
            this.i.setEnabled(z);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(boolean z) {
        this.m = (ScrollView) findViewById(net.hockeyapp.android.i.b.wrapper_feedback_scroll);
        this.n = (LinearLayout) findViewById(net.hockeyapp.android.i.b.wrapper_messages);
        this.o = (ListView) findViewById(net.hockeyapp.android.i.b.list_feedback_messages);
        if (z) {
            this.n.setVisibility(0);
            this.m.setVisibility(8);
            this.d = (TextView) findViewById(net.hockeyapp.android.i.b.label_last_updated);
            this.d.setVisibility(4);
            this.k = (Button) findViewById(net.hockeyapp.android.i.b.button_add_response);
            this.k.setOnClickListener(this);
            this.l = (Button) findViewById(net.hockeyapp.android.i.b.button_refresh);
            this.l.setOnClickListener(this);
            return;
        }
        this.n.setVisibility(8);
        this.m.setVisibility(0);
        this.e = (EditText) findViewById(net.hockeyapp.android.i.b.input_name);
        this.f = (EditText) findViewById(net.hockeyapp.android.i.b.input_email);
        this.g = (EditText) findViewById(net.hockeyapp.android.i.b.input_subject);
        this.h = (EditText) findViewById(net.hockeyapp.android.i.b.input_message);
        if (!this.z) {
            String b = net.hockeyapp.android.d.g.a().b(this.c);
            if (b != null) {
                String[] split = b.split("\\|");
                if (split != null && split.length >= 2) {
                    this.e.setText(split[0]);
                    this.f.setText(split[1]);
                    if (split.length >= 3) {
                        this.g.setText(split[2]);
                        this.h.requestFocus();
                    } else {
                        this.g.requestFocus();
                    }
                }
            } else {
                this.e.setText(this.a);
                this.f.setText(this.b);
                this.g.setText("");
                if (TextUtils.isEmpty(this.a)) {
                    this.e.requestFocus();
                } else if (TextUtils.isEmpty(this.b)) {
                    this.f.requestFocus();
                } else {
                    this.g.requestFocus();
                }
            }
            this.z = true;
        }
        this.h.setText("");
        if (net.hockeyapp.android.d.g.a().a(this.c) != null) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(net.hockeyapp.android.i.b.wrapper_attachments);
        viewGroup.removeAllViews();
        if (this.t != null) {
            for (Uri attachmentView : this.t) {
                viewGroup.addView(new AttachmentView((Context) this, viewGroup, attachmentView, true));
            }
        }
        this.j = (Button) findViewById(net.hockeyapp.android.i.b.button_attachment);
        this.j.setOnClickListener(this);
        registerForContextMenu(this.j);
        this.i = (Button) findViewById(net.hockeyapp.android.i.b.button_send);
        this.i.setOnClickListener(this);
    }

    private boolean a(int i) {
        Intent intent;
        if (i == 2) {
            intent = new Intent();
            intent.setType("*/*");
            intent.setAction("android.intent.action.GET_CONTENT");
            startActivityForResult(Intent.createChooser(intent, getString(d.hockeyapp_feedback_select_file)), 2);
            return true;
        } else if (i != 1) {
            return false;
        } else {
            intent = new Intent();
            intent.setType("image/*");
            intent.setAction("android.intent.action.GET_CONTENT");
            startActivityForResult(Intent.createChooser(intent, getString(d.hockeyapp_feedback_select_picture)), 1);
            return true;
        }
    }

    private void b() {
        this.A = net.hockeyapp.android.d.g.a().a(this);
        if (this.A == null || this.y) {
            b(false);
            return;
        }
        b(true);
        a(this.u, null, null, null, null, null, this.A, this.q, true);
    }

    private void a(String str, String str2) {
        this.r = new g(this, str, this.s, str2);
    }

    private void c() {
        if (this.h != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.h.getWindowToken(), 0);
        }
    }

    private void d() {
        this.q = new a(this);
    }

    private void e() {
        this.s = new b(this);
    }

    @SuppressLint({"SimpleDateFormat"})
    private void a(final FeedbackResponse feedbackResponse) {
        runOnUiThread(new Runnable() {
            public void run() {
                FeedbackActivity.this.b(true);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("d MMM h:mm a");
                if (feedbackResponse != null && feedbackResponse.b() != null && feedbackResponse.b().a() != null && feedbackResponse.b().a().size() > 0) {
                    FeedbackActivity.this.x = feedbackResponse.b().a();
                    Collections.reverse(FeedbackActivity.this.x);
                    try {
                        Date parse = simpleDateFormat.parse(((FeedbackMessage) FeedbackActivity.this.x.get(0)).b());
                        FeedbackActivity.this.d.setText(String.format(FeedbackActivity.this.getString(d.hockeyapp_feedback_last_updated_text), new Object[]{simpleDateFormat2.format(parse)}));
                        FeedbackActivity.this.d.setVisibility(0);
                    } catch (ParseException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                    if (FeedbackActivity.this.w == null) {
                        FeedbackActivity.this.w = new net.hockeyapp.android.a.a(FeedbackActivity.this.c, FeedbackActivity.this.x);
                    } else {
                        FeedbackActivity.this.w.a();
                        Iterator it = FeedbackActivity.this.x.iterator();
                        while (it.hasNext()) {
                            FeedbackActivity.this.w.a((FeedbackMessage) it.next());
                        }
                        FeedbackActivity.this.w.notifyDataSetChanged();
                    }
                    FeedbackActivity.this.o.setAdapter(FeedbackActivity.this.w);
                }
            }
        });
    }

    private void f() {
        runOnUiThread(new Runnable() {
            public void run() {
                net.hockeyapp.android.d.g.a().a(FeedbackActivity.this, null);
                FeedbackActivity.this.getSharedPreferences("net.hockeyapp.android.feedback", 0).edit().remove("idLastMessageSend").remove("idLastMessageProcessed").apply();
                FeedbackActivity.this.b(false);
            }
        });
    }

    private void g() {
        if (i.a((Context) this)) {
            a(false);
            c();
            String a = net.hockeyapp.android.d.g.a().a(this.c);
            String trim = this.e.getText().toString().trim();
            String trim2 = this.f.getText().toString().trim();
            String trim3 = this.g.getText().toString().trim();
            String trim4 = this.h.getText().toString().trim();
            if (TextUtils.isEmpty(trim3)) {
                this.g.setVisibility(0);
                a(this.g, d.hockeyapp_feedback_validate_subject_error);
            } else if (e.b() == FeedbackUserDataElement.REQUIRED && TextUtils.isEmpty(trim)) {
                a(this.e, d.hockeyapp_feedback_validate_name_error);
            } else if (e.c() == FeedbackUserDataElement.REQUIRED && TextUtils.isEmpty(trim2)) {
                a(this.f, d.hockeyapp_feedback_validate_email_empty);
            } else if (TextUtils.isEmpty(trim4)) {
                a(this.h, d.hockeyapp_feedback_validate_text_error);
            } else if (e.c() != FeedbackUserDataElement.REQUIRED || i.b(trim2)) {
                net.hockeyapp.android.d.g.a().a(this.c, trim, trim2, trim3);
                a(this.u, trim, trim2, trim3, trim4, ((AttachmentListView) findViewById(net.hockeyapp.android.i.b.wrapper_attachments)).getAttachments(), a, this.q, false);
            } else {
                a(this.f, d.hockeyapp_feedback_validate_email_error);
            }
            return;
        }
        Toast.makeText(this, d.hockeyapp_error_no_network_message, 1).show();
    }

    private void a(EditText editText, int i) {
        editText.setError(getString(i));
        a(true);
    }

    private void a(String str, String str2, String str3, String str4, String str5, List<Uri> list, String str6, Handler handler, boolean z) {
        this.p = new h(this.c, str, str2, str3, str4, str5, list, str6, handler, z);
        net.hockeyapp.android.d.a.a(this.p);
    }

    private void b(String str, String str2) {
        a(str, str2);
        net.hockeyapp.android.d.a.a(this.r);
    }
}
