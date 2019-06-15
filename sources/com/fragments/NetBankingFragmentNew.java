package com.fragments;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.actionbar.CardsDetailsActionbar;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ac;
import com.managers.ag.a;
import com.managers.aj;
import com.managers.u;
import com.models.BankCodeList;
import com.models.BankCodeList.BankCode;
import com.models.BankCodeList.PopularBankCode;
import com.services.l.s;
import java.util.ArrayList;

public class NetBankingFragmentNew extends BaseGaanaFragment implements OnClickListener, a {
    ProductItem a = null;
    a b = null;
    private View c = null;
    private ProgressBar d = null;
    private String e;
    private Button f;
    private ArrayList<PopularBankCode> g;

    public void a(a aVar) {
        this.b = aVar;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.c = setContentView(R.layout.netbanking_layout, viewGroup);
            this.a = (ProductItem) getArguments().getSerializable("PRODUCT");
            this.d = (ProgressBar) this.c.findViewById(R.id.progressBar);
            this.d.setVisibility(0);
            b();
            d();
            u.a().a("Netbanking");
            setActionBar(this.c, new CardsDetailsActionbar(this.mContext, "Netbanking", "", false), false);
        }
        return this.c;
    }

    private void a() {
        if (this.e.equals("-1")) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.select_bank_dropdown));
            return;
        }
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true));
        new ac(this.mContext).a(this.a, this.e);
    }

    private void b() {
        if (TextUtils.isEmpty(this.a.getP_discounted_cost())) {
            ((TextView) this.c.findViewById(R.id.total_amount)).setText(this.a.getP_cost());
        } else {
            ((TextView) this.c.findViewById(R.id.total_amount)).setText(this.a.getP_discounted_cost());
        }
    }

    private void c() {
        int size = this.g.size();
        if (size == 0) {
            this.c.findViewById(R.id.popuplar_banks_container).setVisibility(8);
            ((TextView) this.c.findViewById(R.id.other_banks_list_container).findViewById(R.id.other_banks_title)).setText("Bank List");
            return;
        }
        if (size <= 0 || this.g.get(0) == null) {
            this.c.findViewById(R.id.state_bank).setVisibility(8);
        } else {
            this.c.findViewById(R.id.state_bank).setVisibility(0);
            ((TextView) this.c.findViewById(R.id.state_bank).findViewById(R.id.bank_name)).setText(((PopularBankCode) this.g.get(0)).b());
            ((CrossFadeImageView) ((ImageView) this.c.findViewById(R.id.state_bank).findViewById(R.id.bank_logo))).bindImage(((PopularBankCode) this.g.get(0)).c());
        }
        if (1 >= size || this.g.get(1) == null) {
            this.c.findViewById(R.id.pnb_bank).setVisibility(8);
        } else {
            this.c.findViewById(R.id.pnb_bank).setVisibility(0);
            ((TextView) this.c.findViewById(R.id.pnb_bank).findViewById(R.id.bank_name)).setText(((PopularBankCode) this.g.get(1)).b());
            ((CrossFadeImageView) ((ImageView) this.c.findViewById(R.id.pnb_bank).findViewById(R.id.bank_logo))).bindImage(((PopularBankCode) this.g.get(1)).c());
        }
        if (2 >= size || this.g.get(2) == null) {
            this.c.findViewById(R.id.icici_bank).setVisibility(8);
        } else {
            this.c.findViewById(R.id.icici_bank).setVisibility(0);
            ((TextView) this.c.findViewById(R.id.icici_bank).findViewById(R.id.bank_name)).setText(((PopularBankCode) this.g.get(2)).b());
            ((CrossFadeImageView) ((ImageView) this.c.findViewById(R.id.icici_bank).findViewById(R.id.bank_logo))).bindImage(((PopularBankCode) this.g.get(2)).c());
        }
        if (3 >= size || this.g.get(3) == null) {
            this.c.findViewById(R.id.hdfc_bank).setVisibility(8);
        } else {
            this.c.findViewById(R.id.hdfc_bank).setVisibility(0);
            ((TextView) this.c.findViewById(R.id.hdfc_bank).findViewById(R.id.bank_name)).setText(((PopularBankCode) this.g.get(3)).b());
            ((CrossFadeImageView) ((ImageView) this.c.findViewById(R.id.hdfc_bank).findViewById(R.id.bank_logo))).bindImage(((PopularBankCode) this.g.get(3)).c());
        }
        this.c.findViewById(R.id.state_bank).setOnClickListener(this);
        this.c.findViewById(R.id.pnb_bank).setOnClickListener(this);
        this.c.findViewById(R.id.hdfc_bank).setOnClickListener(this);
        this.c.findViewById(R.id.icici_bank).setOnClickListener(this);
    }

    public void onClick(View view) {
        BankCode bankCode = new BankCode();
        int id = view.getId();
        if (id == R.id.hdfc_bank) {
            this.e = ((PopularBankCode) this.g.get(3)).a();
            a(((PopularBankCode) this.g.get(3)).b());
            a();
        } else if (id == R.id.icici_bank) {
            this.e = ((PopularBankCode) this.g.get(2)).a();
            a(((PopularBankCode) this.g.get(2)).b());
            a();
        } else if (id == R.id.pnb_bank) {
            this.e = ((PopularBankCode) this.g.get(1)).a();
            a(((PopularBankCode) this.g.get(1)).b());
            a();
        } else if (id == R.id.state_bank) {
            this.e = ((PopularBankCode) this.g.get(0)).a();
            a(((PopularBankCode) this.g.get(0)).b());
            a();
        }
    }

    private void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Popular ");
        stringBuilder.append(str);
        u.a().a("Subscription_Payments", "Netbanking", stringBuilder.toString());
    }

    private void d() {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/payu/index.php?type=get_nb_codes&token=");
        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
        uRLManager.a(stringBuilder.toString());
        uRLManager.a(BusinessObjectType.BankCodes);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject != null) {
                    BankCodeList bankCodeList = (BankCodeList) businessObject;
                    if (bankCodeList.b() != null) {
                        NetBankingFragmentNew.this.g = bankCodeList.b();
                        NetBankingFragmentNew.this.c.findViewById(R.id.popuplar_banks_container).setVisibility(0);
                        NetBankingFragmentNew.this.c();
                    }
                    NetBankingFragmentNew.this.c.findViewById(R.id.other_banks_list_container).setVisibility(0);
                    NetBankingFragmentNew.this.a(bankCodeList.a());
                    NetBankingFragmentNew.this.d.setVisibility(8);
                }
            }
        }, uRLManager);
    }

    private void a(final ArrayList<BankCode> arrayList) {
        this.f = (Button) this.c.findViewById(R.id.pay_now_button);
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                u.a().a("Subscription_Payments", "Netbanking", "Proceed");
                NetBankingFragmentNew.this.a();
            }
        });
        arrayList.add(0, e());
        Spinner spinner = (Spinner) this.c.findViewById(R.id.other_banks_list);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    NetBankingFragmentNew.this.e = ((BankCode) arrayList.get(i)).a();
                } catch (Exception unused) {
                }
            }
        });
        spinner.setAdapter(new SpinnerAdapter() {
            public long getItemId(int i) {
                return (long) i;
            }

            public int getItemViewType(int i) {
                return 1;
            }

            public int getViewTypeCount() {
                return 1;
            }

            public boolean hasStableIds() {
                return false;
            }

            public boolean isEmpty() {
                return false;
            }

            public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            }

            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            }

            public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                return NetBankingFragmentNew.this.b(((BankCode) arrayList.get(i)).b());
            }

            public int getCount() {
                return arrayList.size();
            }

            public Object getItem(int i) {
                return arrayList.get(i);
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                return NetBankingFragmentNew.this.b(((BankCode) arrayList.get(i)).b());
            }
        });
    }

    private BankCode e() {
        BankCode bankCode = new BankCode();
        bankCode.b(getResources().getString(R.string.select_bank));
        bankCode.a("-1");
        return bankCode;
    }

    private ViewGroup b(String str) {
        RelativeLayout relativeLayout = (RelativeLayout) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.bank_code_item, null);
        ((TextView) relativeLayout.findViewById(R.id.bank_code_item)).setText(str);
        return relativeLayout;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            this.b.onFailure(this.mContext.getString(R.string.purchase_error), "failed");
        }
    }

    public void onDestroyView() {
        if (!(this.c == null || this.c.getParent() == null)) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        super.onDestroyView();
    }
}
