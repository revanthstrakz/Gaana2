package com.gaana.view.item;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.ap;
import com.managers.l;
import com.managers.u;
import com.models.DeviceList;
import com.models.DeviceList.Device;
import com.services.l.af;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class RadioButtonDeviceListView extends BaseItemView {
    private TextView currentDevice;
    private String jsonData;
    private CrossFadeImageView mImageIcon;
    private int mLayoutId;
    private LinearLayout mLayoutLanguageChooser;
    private ProgressBar mProgressBar;
    private CheckBox mRadioBtn;
    private View mView;
    private ScrollView scrollView;
    private Button sync;
    private ArrayList<Device> syncDevices;
    private TextView text;
    private TextView tvAlbumName;
    private TextView tvSongName;

    public RadioButtonDeviceListView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mView = null;
        this.mLayoutId = -1;
        this.mLayoutId = R.layout.view_item_devicelist_radiobtn;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(this.mLayoutId, null);
        }
        view.setOnClickListener(this);
        view.setTag(businessObject);
        return getDataFilledView(view, businessObject);
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        this.syncDevices = new ArrayList();
        this.mLayoutLanguageChooser = (LinearLayout) view.findViewById(R.id.llLanguageChooser);
        this.scrollView = (ScrollView) view.findViewById(R.id.scroll_view);
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        this.sync = (Button) view.findViewById(R.id.sync);
        this.text = (TextView) view.findViewById(R.id.headerText);
        this.mProgressBar.setVisibility(0);
        this.sync.setVisibility(8);
        this.scrollView.setVisibility(8);
        this.text.setVisibility(8);
        this.sync.setOnClickListener(this);
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/downloadsync.php?type=get_devices");
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.DeviceList);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        this.mLayoutLanguageChooser.removeAllViews();
        this.syncDevices.clear();
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                RadioButtonDeviceListView.this.mProgressBar.setVisibility(8);
                View inflate;
                BottomSheetDialog bottomSheetDialog;
                if (businessObject == null || !(businessObject instanceof DeviceList)) {
                    RadioButtonDeviceListView.this.scrollView.setVisibility(8);
                    inflate = ((LayoutInflater) RadioButtonDeviceListView.this.mContext.getSystemService("layout_inflater")).inflate(R.layout.device_not_found_layout, null);
                    bottomSheetDialog = new BottomSheetDialog(RadioButtonDeviceListView.this.mContext);
                    bottomSheetDialog.setContentView(inflate);
                    bottomSheetDialog.show();
                    return;
                }
                ArrayList a = ((DeviceList) businessObject).a();
                if (a == null || a.size() <= 0) {
                    RadioButtonDeviceListView.this.scrollView.setVisibility(8);
                    inflate = ((LayoutInflater) RadioButtonDeviceListView.this.mContext.getSystemService("layout_inflater")).inflate(R.layout.device_not_found_layout, null);
                    bottomSheetDialog = new BottomSheetDialog(RadioButtonDeviceListView.this.mContext);
                    bottomSheetDialog.setContentView(inflate);
                    bottomSheetDialog.show();
                    return;
                }
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    final Device device = (Device) it.next();
                    View inflate2 = RadioButtonDeviceListView.this.mInflater.inflate(R.layout.view_device_listitem_radiobtn, null, false);
                    final TextView textView = (TextView) inflate2.findViewById(R.id.headerText);
                    final CheckBox checkBox = (CheckBox) inflate2.findViewById(R.id.radioSong);
                    RadioButtonDeviceListView.this.currentDevice = (TextView) inflate2.findViewById(R.id.currentDevice);
                    TextView textView2 = (TextView) inflate2.findViewById(R.id.songCount);
                    textView.setText(device.c());
                    boolean a2 = device.a();
                    textView.setSelected(a2);
                    checkBox.setChecked(a2);
                    inflate2.setTag(Boolean.valueOf(a2));
                    checkBox.setId(-1);
                    checkBox.setSaveEnabled(true);
                    checkBox.setClickable(false);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("(");
                    stringBuilder.append(device.e());
                    stringBuilder.append(")");
                    textView2.setText(stringBuilder.toString());
                    if (device.d().equals("Y")) {
                        RadioButtonDeviceListView.this.currentDevice.setVisibility(0);
                    } else {
                        RadioButtonDeviceListView.this.currentDevice.setVisibility(8);
                    }
                    inflate2.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            boolean booleanValue = ((Boolean) view.getTag()).booleanValue();
                            checkBox.setChecked(booleanValue ^ 1);
                            textView.setSelected(booleanValue ^ 1);
                            view.setTag(Boolean.valueOf(booleanValue ^ 1));
                            device.a(booleanValue ^ 1);
                            if (!booleanValue) {
                                RadioButtonDeviceListView.this.syncDevices.add(device);
                            } else if (RadioButtonDeviceListView.this.syncDevices.contains(device)) {
                                RadioButtonDeviceListView.this.syncDevices.remove(device);
                            }
                        }
                    });
                    RadioButtonDeviceListView.this.mLayoutLanguageChooser.addView(inflate2);
                    RadioButtonDeviceListView.this.scrollView.setVisibility(0);
                    RadioButtonDeviceListView.this.sync.setVisibility(0);
                    RadioButtonDeviceListView.this.text.setVisibility(0);
                }
            }
        }, uRLManager);
        return view;
    }

    public void onClick(View view) {
        super.onClick(view);
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
        } else if (Util.j(this.mContext)) {
            if (view.getId() == R.id.sync) {
                u.a().a("Sync from other device", "Tap", "Tap to Sync");
                syncDevices();
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void syncDevices() {
        String str = "";
        String str2 = "";
        Iterator it = this.syncDevices.iterator();
        while (it.hasNext()) {
            Device device = (Device) it.next();
            StringBuilder stringBuilder;
            if (str.equals("")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(device.b());
                str = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(device.c());
                str2 = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(",");
                stringBuilder.append(device.b());
                str = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(",");
                stringBuilder.append(device.c());
                str2 = stringBuilder.toString();
            }
        }
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("https://api.gaana.com/downloadsync.php?type=sync_downloads&device_id=");
        stringBuilder2.append(str);
        uRLManager.a(stringBuilder2.toString());
        uRLManager.b(1);
        uRLManager.i(false);
        uRLManager.a(String.class);
        uRLManager.b(Boolean.valueOf(false));
        ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getResources().getString(R.string.fetching_details));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                RadioButtonDeviceListView.this.mProgressBar.setVisibility(8);
                ((GaanaActivity) RadioButtonDeviceListView.this.mContext).hideProgressDialog();
                RadioButtonDeviceListView.this.jsonData = (String) obj;
                try {
                    JSONObject jSONObject = new JSONObject(RadioButtonDeviceListView.this.jsonData);
                    if (jSONObject.has("status")) {
                        if ("1".equalsIgnoreCase(jSONObject.getString("status"))) {
                            l.a().c(true);
                            u.a().a("Sync from other device", "Sync initiates", str2);
                        }
                        aj.a().a(RadioButtonDeviceListView.this.getContext(), jSONObject.getString("msg"));
                    }
                } catch (Exception unused) {
                }
            }
        }, uRLManager);
    }
}
