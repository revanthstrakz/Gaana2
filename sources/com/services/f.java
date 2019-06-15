package com.services;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.gaana.R;

public class f {
    private Context a;
    private View b = null;
    private View c = null;
    private TextView d = null;
    private EditText e = null;
    private AlertDialog f;
    private AlertDialog g;
    private c h;
    private a i;
    private LayoutInflater j = null;
    private OnTimeSetListener k = new OnTimeSetListener() {
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            String str = "AM";
            if (i > 12) {
                str = "PM";
                i -= 12;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(f.b(i));
            stringBuilder.append(":");
            stringBuilder.append(f.b(i2));
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(" ");
            stringBuilder2.append(str);
            stringBuilder.append(stringBuilder2.toString());
            f.this.h.a(stringBuilder.toString());
        }
    };
    private OnClickListener l = new OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            f.this.i.a(i);
            dialogInterface.dismiss();
        }
    };

    public interface a {
        void a(int i);
    }

    public interface b {
        void onCancelListner();

        void onOkListner(String str);
    }

    public interface c {
        void a(String str);
    }

    public f(Context context) {
        this.a = context;
        this.j = LayoutInflater.from(this.a);
    }

    private static String b(int i) {
        if (i >= 10) {
            return String.valueOf(i);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0");
        stringBuilder.append(String.valueOf(i));
        return stringBuilder.toString();
    }

    public void a(String str, String str2, Boolean bool, final b bVar) {
        try {
            if (this.g == null || !this.g.isShowing()) {
                if (this.b == null) {
                    this.b = this.j.inflate(R.layout.dialog_single_text, null);
                }
                if (this.d == null) {
                    this.d = (TextView) this.b.findViewById(R.id.tvDialog);
                }
                this.d.setText(str2);
                Builder builder = new Builder(this.a);
                builder.setTitle(str);
                builder.setMessage(str2);
                builder.setPositiveButton("Ok", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (bVar != null) {
                            bVar.onOkListner("");
                        }
                    }
                });
                if (bool.booleanValue()) {
                    builder.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (bVar != null) {
                                bVar.onCancelListner();
                            }
                        }
                    });
                }
                this.g = builder.create();
                this.g.show();
            }
        } catch (Exception unused) {
        }
    }

    public void a(String str, String str2, Boolean bool, final b bVar, boolean z) {
        try {
            if (this.g == null || !this.g.isShowing()) {
                if (this.b == null) {
                    this.b = this.j.inflate(R.layout.dialog_single_text, null);
                }
                if (this.d == null) {
                    this.d = (TextView) this.b.findViewById(R.id.tvDialog);
                }
                this.d.setText(str2);
                Builder builder = new Builder(this.a);
                builder.setTitle(str);
                builder.setMessage(str2);
                builder.setPositiveButton("Ok", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        bVar.onOkListner("");
                    }
                });
                if (bool.booleanValue()) {
                    builder.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            bVar.onCancelListner();
                        }
                    });
                }
                builder.setCancelable(z);
                this.g = builder.create();
                this.g.show();
            }
        } catch (Exception unused) {
        }
    }

    public void a(String str, String str2, Boolean bool, String str3, String str4, final b bVar) {
        try {
            this.b = this.j.inflate(R.layout.dialog_single_text, null);
            this.d = (TextView) this.b.findViewById(R.id.tvDialog);
            this.d.setText(str2);
            Builder builder = new Builder(this.a);
            builder.setTitle(str);
            builder.setMessage(str2);
            builder.setPositiveButton(str3, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    bVar.onOkListner("");
                }
            });
            if (bool.booleanValue()) {
                builder.setNegativeButton(str4, new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        bVar.onCancelListner();
                    }
                });
            }
            final AlertDialog create = builder.create();
            create.setOnShowListener(new OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    create.getButton(-1).setTextColor(f.this.a.getResources().getColor(R.color.gaana_orange_text));
                }
            });
            create.show();
        } catch (Exception unused) {
        }
    }

    public void a(String str, String str2, Boolean bool, String str3, String str4, final b bVar, boolean z) {
        try {
            this.b = this.j.inflate(R.layout.dialog_single_text, null);
            this.d = (TextView) this.b.findViewById(R.id.tvDialog);
            this.d.setText(str2);
            Builder builder = new Builder(this.a);
            builder.setTitle(str);
            builder.setMessage(str2);
            builder.setPositiveButton(str3, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    bVar.onOkListner("");
                }
            });
            if (bool.booleanValue()) {
                builder.setNegativeButton(str4, new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        bVar.onCancelListner();
                    }
                });
            }
            builder.setCancelable(z);
            builder.create().show();
        } catch (Exception unused) {
        }
    }

    public void a(String str) {
        try {
            if (this.f == null || !this.f.isShowing()) {
                if (this.b == null) {
                    this.b = this.j.inflate(R.layout.dialog_single_text, null);
                }
                if (this.d == null) {
                    this.d = (TextView) this.b.findViewById(R.id.tvDialog);
                }
                this.d.setText(str);
                if (this.f == null) {
                    this.f = new Builder(this.a).setTitle("Gaana").setMessage(str).setPositiveButton("Ok", new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            f.this.f = null;
                        }
                    }).create();
                }
                if (!this.f.isShowing()) {
                    this.f.show();
                }
            }
        } catch (Exception unused) {
        }
    }
}
