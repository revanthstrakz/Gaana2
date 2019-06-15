package com.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbar.CardsDetailsActionbar;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.RecyclerTouchListener;
import com.gaana.RecyclerTouchListener.ClickListener;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.gaana.cardoption.CardNumberEditText;
import com.gaana.cardoption.CardOption.CardScheme;
import com.gaana.cardoption.CardSchemeCallBack;
import com.gaana.models.PayUHash.SiEnabledBankName;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.ac;
import com.managers.aj;
import com.managers.u;
import com.models.CouponProducts.PaymentGateway;
import com.payu.india.Model.PayuResponse;
import com.payu.india.Model.StoredCard;
import com.payu.india.b.d;
import com.services.l.h;
import com.services.l.q;
import java.util.ArrayList;
import java.util.Iterator;

public class PayUCreditDebitFragment extends BaseGaanaFragment implements a, com.payu.india.b.a, com.payu.india.b.b, d, h, q {
    private String A = "";
    private boolean B;
    private boolean C = true;
    private ArrayList<SiEnabledBankName> D = null;
    View a = null;
    OnClickListener b = new OnClickListener() {
        public void onClick(View view) {
            if (PayUCreditDebitFragment.this.k.b() != 1 || PayUCreditDebitFragment.this.a() || PayUCreditDebitFragment.this.z == null || PayUCreditDebitFragment.this.z.equalsIgnoreCase("CC")) {
                String obj = PayUCreditDebitFragment.this.d.getText().toString();
                String obj2 = PayUCreditDebitFragment.this.s.getSelectedItem().toString();
                String obj3 = PayUCreditDebitFragment.this.t.getSelectedItem().toString();
                String obj4 = PayUCreditDebitFragment.this.e.getText().toString();
                String obj5 = PayUCreditDebitFragment.this.f.getText().toString();
                String replaceAll = obj.replaceAll("[\\s-]+", "");
                if (obj5.equalsIgnoreCase("") || obj5.length() <= 0) {
                    aj.a().a(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.mContext.getString(R.string.enter_card_holder_name));
                    return;
                } else if (obj2.equalsIgnoreCase("") || obj2.length() <= 0 || obj2.equalsIgnoreCase("MM")) {
                    aj.a().a(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.mContext.getString(R.string.enter_card_expiry_month));
                    return;
                } else if (obj3.equalsIgnoreCase("") || obj3.length() <= 0 || obj3.equalsIgnoreCase("YYYY")) {
                    aj.a().a(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.mContext.getString(R.string.enter_card_expiry_year));
                    return;
                } else if (obj4.equalsIgnoreCase("") || obj4.length() <= 0) {
                    aj.a().a(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.mContext.getString(R.string.enter_card_cvv));
                    return;
                } else if (replaceAll.equalsIgnoreCase("") || replaceAll.length() <= 0) {
                    aj.a().a(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.mContext.getString(R.string.enter_card_number));
                    return;
                } else {
                    if (!TextUtils.isEmpty(PayUCreditDebitFragment.this.z) && PayUCreditDebitFragment.this.z.equals("CC")) {
                        PayUCreditDebitFragment.this.a(replaceAll, obj5, obj2, obj3, obj4);
                    } else if (!TextUtils.isEmpty(PayUCreditDebitFragment.this.z) && PayUCreditDebitFragment.this.z.equals("DC") && PayUCreditDebitFragment.this.B) {
                        PayUCreditDebitFragment.this.a(replaceAll, obj5, obj2, obj3, obj4);
                    } else {
                        aj.a().a(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.mContext.getString(R.string.enter_valid_bank));
                    }
                    u.a().a("Subscription_Payments", "CC/DC", "Proceed");
                    return;
                }
            }
            Toast.makeText(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.getResources().getString(R.string.auto_renewal_message), 1).show();
        }
    };
    private View c = null;
    private CardNumberEditText d;
    private EditText e;
    private EditText f;
    private com.managers.ag.a g = null;
    private CustomDialogView h = null;
    private ProductItem i;
    private String j = "";
    private ac k;
    private ArrayList<StoredCard> l;
    private a m;
    private ImageView n;
    private RelativeLayout o;
    private LinearLayout p;
    private CheckBox q;
    private TextView r;
    private Spinner s;
    private Spinner t;
    private TextView u;
    private PaymentGateway v;
    private StoredCard w;
    private int x;
    private int y;
    private String z;

    private class a extends Adapter<b> {
        private ArrayList<StoredCard> b;

        public a(ArrayList<StoredCard> arrayList) {
            this.b = arrayList;
        }

        /* renamed from: a */
        public b onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(PayUCreditDebitFragment.this.mContext).inflate(R.layout.saved_card, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(final b bVar, int i) {
            final StoredCard storedCard = (StoredCard) this.b.get(i);
            if (storedCard.g().equals("MASTERCARD")) {
                bVar.c.setImageResource(R.drawable.mastercard);
            } else if (storedCard.g().equals("VISA")) {
                bVar.c.setImageResource(R.drawable.visa);
            } else if (storedCard.a().equals(CARD.AMEX)) {
                bVar.c.setImageResource(R.drawable.amex);
            }
            bVar.d.setText(storedCard.f());
            TextView a = bVar.f;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(storedCard.c());
            stringBuilder.append("/");
            stringBuilder.append(storedCard.b());
            a.setText(stringBuilder.toString());
            bVar.a.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (TextUtils.isEmpty(bVar.g.getText().toString())) {
                        aj.a().a(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.mContext.getString(R.string.enter_card_cvv));
                    } else if (storedCard.e().equals("DC")) {
                        boolean z = false;
                        if (PayUCreditDebitFragment.this.D != null) {
                            int i = 0;
                            while (i < PayUCreditDebitFragment.this.D.size()) {
                                if (PayUCreditDebitFragment.this.D.get(i) != null && !TextUtils.isEmpty(((SiEnabledBankName) PayUCreditDebitFragment.this.D.get(i)).getBank_name()) && ((SiEnabledBankName) PayUCreditDebitFragment.this.D.get(i)).getBank_name().equalsIgnoreCase(storedCard.h())) {
                                    z = true;
                                    break;
                                }
                                i++;
                            }
                        }
                        if (z) {
                            ((BaseActivity) PayUCreditDebitFragment.this.mContext).showProgressDialog(Boolean.valueOf(true));
                            PayUCreditDebitFragment.this.k.b(PayUCreditDebitFragment.this.x);
                            PayUCreditDebitFragment.this.k.a(storedCard.d(), bVar.g.getText().toString(), storedCard.c(), storedCard.b());
                            return;
                        }
                        aj.a().a(PayUCreditDebitFragment.this.mContext, PayUCreditDebitFragment.this.mContext.getString(R.string.enter_valid_bank));
                    } else {
                        ((BaseActivity) PayUCreditDebitFragment.this.mContext).showProgressDialog(Boolean.valueOf(true));
                        PayUCreditDebitFragment.this.k.b(PayUCreditDebitFragment.this.x);
                        PayUCreditDebitFragment.this.k.a(storedCard.d(), bVar.g.getText().toString(), storedCard.c(), storedCard.b());
                    }
                }
            });
        }

        public int getItemCount() {
            return this.b.size();
        }
    }

    public class b extends ViewHolder {
        public Button a = ((Button) this.b.findViewById(R.id.pay_now_store_card));
        View b;
        ImageView c = ((ImageView) this.b.findViewById(R.id.card_logo));
        TextView d = ((TextView) this.b.findViewById(R.id.card_number));
        private final TextView f = ((TextView) this.b.findViewById(R.id.expiry_date));
        private final EditText g = ((EditText) this.b.findViewById(R.id.cvv_number));

        public b(View view) {
            super(view);
            this.b = view;
        }
    }

    public void a(com.managers.ag.a aVar) {
        this.g = aVar;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.v = (PaymentGateway) getArguments().getSerializable("COUPON_PRODUCT");
        this.i = (ProductItem) getArguments().getSerializable("PRODUCT");
        this.j = getArguments().getString("COUPONCODE");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.c = setContentView(R.layout.fragment_payu_details, viewGroup);
            this.k = new ac(this.mContext);
            this.k.b(this.i);
            setActionBar(this.c, new CardsDetailsActionbar(this.mContext, this.mContext.getString(R.string.card_detail), "", true), false);
            if (getActivity() != null) {
                getActivity().getWindow().setSoftInputMode(34);
            }
        }
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
        if (c()) {
            j();
        }
        u.a().a("CC/DC");
        this.k.a(d(), this, this);
        return this.c;
    }

    private boolean c() {
        return !TextUtils.isEmpty(this.j);
    }

    private String d() {
        StringBuilder stringBuilder;
        if (c()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("https://pay.gaana.com/payu/index.php?type=get_order_detail&prd_id=");
            stringBuilder.append(this.i.getP_id());
            stringBuilder.append("&prd_cost=");
            stringBuilder.append(this.i.getP_cost());
            stringBuilder.append("&source=coupon_redeem");
            stringBuilder.append("&p_code=");
            stringBuilder.append(this.j);
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/payu/index.php?type=get_order_detail&prd_id=");
        stringBuilder.append(this.i.getP_id());
        stringBuilder.append("&prd_cost=");
        stringBuilder.append(this.i.getP_cost());
        stringBuilder.append("&source=payment");
        stringBuilder.append("&p_code=");
        stringBuilder.append(this.i.getP_code());
        return stringBuilder.toString();
    }

    private void e() {
        this.p = (LinearLayout) this.c.findViewById(R.id.new_card_container);
        this.o = (RelativeLayout) this.c.findViewById(R.id.card_layout);
        this.r = (TextView) this.c.findViewById(R.id.proceed_note);
        this.u = (TextView) this.c.findViewById(R.id.total_amount);
        this.r = (TextView) this.c.findViewById(R.id.proceed_note);
        boolean z = false;
        if (TextUtils.isEmpty(h())) {
            this.r.setVisibility(8);
        } else {
            this.r.setVisibility(0);
            TextView textView = this.r;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<b>Note: </b>");
            stringBuilder.append(h());
            textView.setText(Html.fromHtml(stringBuilder.toString()));
        }
        this.o.setVisibility(8);
        View findViewById = this.c.findViewById(R.id.card_details);
        findViewById.findViewById(R.id.pay_now_button).setOnClickListener(this.b);
        this.d = (CardNumberEditText) findViewById.findViewById(R.id.card_number_edit_text);
        this.s = (Spinner) findViewById.findViewById(R.id.month_spinner);
        this.t = (Spinner) findViewById.findViewById(R.id.year_spinner);
        this.e = (EditText) findViewById.findViewById(R.id.cvv_number_edit_text);
        this.n = (ImageView) findViewById.findViewById(R.id.card_logo);
        this.f = (EditText) findViewById.findViewById(R.id.card_holder_name_edit_text);
        this.q = (CheckBox) this.c.findViewById(R.id.save_card_message);
        this.q.setPadding(10, 0, 0, 0);
        this.r.setText(h());
        if (!TextUtils.isEmpty(f())) {
            this.q.setVisibility(0);
            this.q.setText(f());
            this.q.setClickable(this.k.b() != 1);
            this.q.setEnabled(this.k.b() != 1);
            CheckBox checkBox = this.q;
            if (this.k.a() != 0) {
                z = true;
            }
            checkBox.setChecked(z);
            this.q.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        PayUCreditDebitFragment.this.k.b(1);
                        PayUCreditDebitFragment.this.k.a(1);
                        PayUCreditDebitFragment.this.q.setChecked(true);
                        u.a().a("Subscription_Payments", "CC/DC", "Save card_Checked");
                        return;
                    }
                    PayUCreditDebitFragment.this.k.b(0);
                    PayUCreditDebitFragment.this.k.a(0);
                    PayUCreditDebitFragment.this.q.setChecked(false);
                    u.a().a("Subscription_Payments", "CC/DC", "Save card_Unchecked");
                }
            });
        }
        this.x = this.k.b();
        this.y = this.k.a();
        this.d.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                PayUCreditDebitFragment.this.d.getCardScheme(new CardSchemeCallBack() {
                    public void onCardSchemeReceived(CardScheme cardScheme) {
                        if (cardScheme.getName().equals("mcrd")) {
                            PayUCreditDebitFragment.this.n.setImageResource(R.drawable.mastercard);
                            if (!(TextUtils.isEmpty(PayUCreditDebitFragment.this.z) || PayUCreditDebitFragment.this.z.equals("DC"))) {
                                if (PayUCreditDebitFragment.this.q.isChecked()) {
                                    PayUCreditDebitFragment.this.k.a(1);
                                    PayUCreditDebitFragment.this.k.b(1);
                                } else {
                                    PayUCreditDebitFragment.this.k.a(0);
                                    PayUCreditDebitFragment.this.k.b(0);
                                }
                                PayUCreditDebitFragment.this.q.setVisibility(0);
                                PayUCreditDebitFragment.this.r.setVisibility(0);
                            }
                            PayUCreditDebitFragment.this.a(3);
                        } else if (cardScheme.getName().equals("visa")) {
                            PayUCreditDebitFragment.this.n.setImageResource(R.drawable.visa);
                            PayUCreditDebitFragment.this.a(3);
                            if (!(TextUtils.isEmpty(PayUCreditDebitFragment.this.z) || PayUCreditDebitFragment.this.z.equals("DC"))) {
                                if (PayUCreditDebitFragment.this.q.isChecked()) {
                                    PayUCreditDebitFragment.this.k.a(1);
                                    PayUCreditDebitFragment.this.k.b(1);
                                } else {
                                    PayUCreditDebitFragment.this.k.a(0);
                                    PayUCreditDebitFragment.this.k.b(0);
                                }
                                PayUCreditDebitFragment.this.q.setVisibility(0);
                                PayUCreditDebitFragment.this.r.setVisibility(0);
                            }
                        } else if (cardScheme.getName().equals("amex")) {
                            PayUCreditDebitFragment.this.q.setVisibility(8);
                            PayUCreditDebitFragment.this.r.setVisibility(8);
                            PayUCreditDebitFragment.this.n.setImageResource(R.drawable.amex);
                            PayUCreditDebitFragment.this.a(4);
                            PayUCreditDebitFragment.this.k.a(0);
                            PayUCreditDebitFragment.this.k.b(0);
                        } else if (cardScheme.getName().equals(CARD.UNKNOWN)) {
                            PayUCreditDebitFragment.this.n.setImageDrawable(null);
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onCardSchemeReceived: ");
                        stringBuilder.append(cardScheme.getName());
                        stringBuilder.append(" ");
                        stringBuilder.append(cardScheme.getIconName());
                        Log.d("pal", stringBuilder.toString());
                    }
                });
            }

            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 7 && PayUCreditDebitFragment.this.C) {
                    PayUCreditDebitFragment.this.k.a(editable.toString().replaceAll("[\\s-]+", "").substring(0, 6), PayUCreditDebitFragment.this);
                }
            }
        });
        i();
    }

    private String f() {
        return this.i.getSaved_card_msg();
    }

    private String g() {
        return this.i.getP_cost_curr();
    }

    private String h() {
        return this.i.getIs_si_msg();
    }

    private void i() {
        final CheckBox checkBox = (CheckBox) this.c.findViewById(R.id.new_card_checkbox);
        this.p.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    ((CheckBox) PayUCreditDebitFragment.this.c.findViewById(R.id.new_card_checkbox)).setChecked(false);
                    PayUCreditDebitFragment.this.c.findViewById(R.id.card_details).setVisibility(8);
                    PayUCreditDebitFragment.this.c.findViewById(R.id.save_card_message).setVisibility(8);
                    return;
                }
                PayUCreditDebitFragment.this.k();
                if (PayUCreditDebitFragment.this.a != null) {
                    PayUCreditDebitFragment.this.n();
                }
            }
        });
    }

    private void j() {
        this.c.findViewById(R.id.total_amount_label).setVisibility(8);
        this.c.findViewById(R.id.total_amount).setVisibility(8);
        this.c.findViewById(R.id.divider).setVisibility(8);
    }

    private void k() {
        if (TextUtils.isEmpty(f())) {
            this.q.setVisibility(8);
        } else {
            this.q.setVisibility(0);
            this.q.setEnabled(this.k.b() != 1);
            this.q.setClickable(this.k.b() != 1);
            this.q.setChecked(this.k.a() != 0);
        }
        this.c.findViewById(R.id.card_details).setVisibility(0);
        ((CheckBox) this.c.findViewById(R.id.new_card_checkbox)).setChecked(true);
    }

    private void l() {
        this.c.findViewById(R.id.card_details).setVisibility(8);
        ((CheckBox) this.c.findViewById(R.id.new_card_checkbox)).setChecked(false);
    }

    private void m() {
        RecyclerView recyclerView = (RecyclerView) this.c.findViewById(R.id.saved_cards);
        this.m = new a(this.l);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(this.m);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this.mContext, recyclerView, new ClickListener() {
            public void onLongClick(View view, int i) {
            }

            public void onClick(View view, final int i) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.card_detail_checkbox);
                View findViewById = view.findViewById(R.id.cvv_details);
                LinearLayout linearLayout = (LinearLayout) findViewById.findViewById(R.id.remove_card_container);
                PayUCreditDebitFragment.this.w = null;
                linearLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        PayUCreditDebitFragment.this.w = (StoredCard) PayUCreditDebitFragment.this.l.get(i);
                        PayUCreditDebitFragment.this.k.a(((StoredCard) PayUCreditDebitFragment.this.l.get(i)).d(), PayUCreditDebitFragment.this);
                    }
                });
                if (!checkBox.isChecked()) {
                    checkBox.setChecked(true);
                    findViewById.setVisibility(0);
                    if (!(PayUCreditDebitFragment.this.a == null || view == PayUCreditDebitFragment.this.a)) {
                        PayUCreditDebitFragment.this.n();
                    }
                    PayUCreditDebitFragment.this.l();
                    PayUCreditDebitFragment.this.a = view;
                }
            }
        }));
    }

    private void n() {
        this.a.findViewById(R.id.cvv_details).setVisibility(8);
        ((CheckBox) this.a.findViewById(R.id.card_detail_checkbox)).setChecked(false);
    }

    public boolean a() {
        Iterator it = this.D.iterator();
        while (it.hasNext()) {
            if (((SiEnabledBankName) it.next()).getBank_name().equalsIgnoreCase(this.A)) {
                return true;
            }
        }
        return false;
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        try {
            ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true));
            this.k.a(str, str2, str3, str4, str5, 1, "");
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroyView() {
        if (!(this.c == null || this.c.getParent() == null)) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        super.onDestroyView();
        getActivity().getWindow().setSoftInputMode(52);
    }

    public void b() {
        this.h = new CustomDialogView(this.mContext, this.mContext.getString(R.string.exit_confirmation_msg), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new OnButtonClickListener() {
            public void onPositiveButtonClick() {
                PayUCreditDebitFragment.this.h.dismiss();
                ((GaanaActivity) PayUCreditDebitFragment.this.mContext).homeIconClick();
            }

            public void onNegativeButtonClick() {
                PayUCreditDebitFragment.this.h.dismiss();
            }
        });
        this.h.show();
    }

    public void a(PayuResponse payuResponse) {
        ((BaseActivity) this.mContext).hideProgressDialog();
        this.l = payuResponse.a();
        if (this.l != null) {
            a(0, 0);
            m();
            return;
        }
        a(8, 8);
    }

    public void a(String str, ArrayList<SiEnabledBankName> arrayList) {
        e();
        this.D = arrayList;
        TextView textView = this.u;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(g());
        stringBuilder.append(" ");
        stringBuilder.append(str);
        textView.setText(stringBuilder.toString());
    }

    public void b(PayuResponse payuResponse) {
        if (payuResponse.b().a().equals("SUCCESS")) {
            Iterator it = this.l.iterator();
            while (it.hasNext()) {
                StoredCard storedCard = (StoredCard) it.next();
                if (this.w.equals(storedCard)) {
                    this.l.remove(storedCard);
                    break;
                }
            }
            if (this.l == null || this.l.size() <= 0) {
                a(8, 8);
                return;
            }
            a(0, 0);
            m();
        }
    }

    public void c(PayuResponse payuResponse) {
        if (payuResponse.c() != null) {
            String a = payuResponse.c().a();
            this.A = a;
            this.z = payuResponse.c().b();
            if (this.z.equals("CC") || this.z.equals("DC")) {
                this.C = false;
            } else {
                this.C = true;
            }
            if (this.z.equals("DC")) {
                boolean z;
                if (this.D != null) {
                    int i = 0;
                    while (i < this.D.size()) {
                        if (this.D.get(i) != null && !TextUtils.isEmpty(((SiEnabledBankName) this.D.get(i)).getBank_name()) && ((SiEnabledBankName) this.D.get(i)).getBank_name().equalsIgnoreCase(a)) {
                            z = true;
                            break;
                        }
                        i++;
                    }
                }
                z = false;
                if (z) {
                    this.B = true;
                    this.k.a(this.y);
                    if (this.y == 0 || !this.q.isChecked()) {
                        this.k.b(0);
                    } else {
                        this.k.b(1);
                    }
                    this.q.setVisibility(0);
                    this.r.setVisibility(0);
                    return;
                }
                if (c()) {
                    this.B = false;
                } else {
                    this.B = true;
                }
                this.q.setVisibility(8);
                this.r.setVisibility(8);
                this.k.a(0);
                this.k.b(0);
            }
        }
    }

    public void a(int i, int i2) {
        this.o.setVisibility(i);
        this.p.setVisibility(i2);
        if (i == 0) {
            this.c.findViewById(R.id.card_details).setVisibility(8);
        } else {
            this.c.findViewById(R.id.card_details).setVisibility(0);
        }
    }

    public void a(int i) {
        this.e.setFilters(new InputFilter[]{new LengthFilter(i)});
    }
}
