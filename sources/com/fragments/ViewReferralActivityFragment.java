package com.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.facebook.GraphResponse;
import com.facebook.messenger.MessengerUtils;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.ListAdapter;
import com.gaana.adapter.ListAdapter.IAddListItemView;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.models.BusinessObject;
import com.gaana.models.ReferralResponse;
import com.gaana.models.ReferralUserActivities;
import com.gaana.models.ReferralUserActivities.ReferralUserActivity;
import com.gaana.view.ReferralActivityItemView;
import com.gaana.view.item.BaseItemView.ReferralActivityHolder;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.aj;
import com.services.a;
import com.services.g;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.List;

public class ViewReferralActivityFragment extends BaseGaanaFragment implements OnClickListener {
    private View a = null;
    private boolean b = false;
    private ListAdapter c;
    private GenericBackActionBar d;
    private Button e;
    private RecyclerView f;
    private ReferralResponse g;
    private a h;
    private List<ResolveInfo> i;
    private Drawable j;
    private TypedArray k;
    private int[] l;
    private ProgressBar m;
    private s n = new s() {
        public void onRetreivalComplete(BusinessObject businessObject) {
            ViewReferralActivityFragment.this.m.setVisibility(8);
            if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                ViewReferralActivityFragment.this.e.setText(R.string.invite_friends_caps);
                LayoutParams layoutParams = (LayoutParams) ViewReferralActivityFragment.this.e.getLayoutParams();
                layoutParams.addRule(12, 0);
                layoutParams.addRule(13, -1);
                ViewReferralActivityFragment.this.e.setLayoutParams(layoutParams);
                ViewReferralActivityFragment.this.e.setVisibility(0);
                ViewReferralActivityFragment.this.b();
                return;
            }
            ViewReferralActivityFragment.this.e.setText(ViewReferralActivityFragment.this.getString(R.string.invite_more_friends));
            ViewReferralActivityFragment.this.e.setLayoutParams((LayoutParams) ViewReferralActivityFragment.this.e.getLayoutParams());
            ViewReferralActivityFragment.this.e.setVisibility(0);
            ViewReferralActivityFragment.this.a();
            ViewReferralActivityFragment.this.c.setAdapterArrayList(businessObject.getArrListBusinessObj());
        }

        public void onErrorResponse(BusinessObject businessObject) {
            ViewReferralActivityFragment.this.m.setVisibility(8);
            ViewReferralActivityFragment.this.e.setText(R.string.invite_friends_caps);
            LayoutParams layoutParams = (LayoutParams) ViewReferralActivityFragment.this.e.getLayoutParams();
            layoutParams.addRule(12, 0);
            layoutParams.addRule(13, -1);
            ViewReferralActivityFragment.this.e.setLayoutParams(layoutParams);
            ViewReferralActivityFragment.this.b();
        }
    };

    private void a() {
        ((FrameLayout) this.containerView.findViewById(R.id.first_child)).setVisibility(8);
    }

    private void b() {
        this.f.setVisibility(8);
        ((FrameLayout) this.containerView.findViewById(R.id.first_child)).setVisibility(0);
    }

    public void a(ReferralResponse referralResponse) {
        this.g = referralResponse;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.containerView == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.containerView = setContentView(R.layout.view_referral_activity_fragment, viewGroup);
            c();
            a(new ArrayList());
            a(this.containerView);
            a(getString(R.string.referral_activity));
        }
        this.h = new a(this.mContext);
        d();
        setGAScreenName("ReferralViewActivityScreen", "ReferralViewActivityScreen");
        return this.containerView;
    }

    private void a(String str) {
        ((GaanaActivity) this.mContext).title = str;
        if (this.d == null) {
            this.d = new GenericBackActionBar(this.mContext, Util.f(str));
        }
        setActionBar(this.containerView, this.d);
    }

    private void a(ArrayList<BusinessObject> arrayList) {
        this.c = new ListAdapter(this.mContext);
        final ReferralActivityItemView referralActivityItemView = new ReferralActivityItemView(this.mContext, this);
        this.c.setParamaters(arrayList, new IAddListItemView() {
            public int getItemViewType(int i) {
                return 1;
            }

            public void showHideEmtpyView(boolean z) {
            }

            public View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup) {
                if (obj instanceof ReferralUserActivity) {
                    return referralActivityItemView.getPoplatedView(viewHolder, (BusinessObject) obj, viewGroup);
                }
                return viewHolder.itemView;
            }

            public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                return new ReferralActivityHolder(referralActivityItemView.createViewHolder(viewGroup, i));
            }
        });
        if (this.f != null) {
            this.f.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
            this.f.setAdapter(this.c);
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void c() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        this.i = this.mContext.getPackageManager().queryIntentActivities(intent, 0);
    }

    private void a(View view) {
        this.f = (RecyclerView) view.findViewById(R.id.activity_list);
        this.e = (Button) view.findViewById(R.id.invite_more_friends);
        this.f.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        this.f.setAdapter(this.c);
        this.e.setOnClickListener(this);
        this.m = (ProgressBar) view.findViewById(R.id.progressbar);
        int[] iArr = new int[]{R.attr.selector_btn_global_bg_transparent, R.attr.streaming_quality_layout_drawable};
        this.j = ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(86, -1));
        this.l = new int[]{81, 78, 76, 80, 77, 79, 75};
        this.k = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
    }

    private void d() {
        this.m.setVisibility(0);
        i.a().a(this.n, a(this.b));
    }

    private URLManager a(boolean z) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/index.php?type=referral&subtype=get_referral_activity_log");
        uRLManager.a(ReferralUserActivities.class);
        uRLManager.b(Boolean.valueOf(false));
        return uRLManager;
    }

    public void onClick(View view) {
        if (view == this.e) {
            b(view);
        }
    }

    private void b(View view) {
        final LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        final View inflate = layoutInflater.inflate(R.layout.referral_share_menu_layout, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.mContext);
        bottomSheetDialog.setContentView(inflate);
        Behavior behavior = ((CoordinatorLayout.LayoutParams) ((View) inflate.getParent()).getLayoutParams()).getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            final BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetCallback() {
                public void onSlide(@NonNull View view, float f) {
                }

                public void onStateChanged(@NonNull View view, int i) {
                }
            });
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    inflate.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    bottomSheetBehavior.setPeekHeight(inflate.getMeasuredHeight());
                }
            });
        }
        ListView listView = (ListView) inflate.findViewById(R.id.share_options_list);
        final String[] strArr = new String[]{getString(R.string.whatsapp), getString(R.string.messenger), getString(R.string.facebook), getString(R.string.twitter), getString(R.string.gmail), getString(R.string.mode_sms), getString(R.string.copy_to_clipboard)};
        listView.setSelector(this.j);
        listView.setAdapter(new BaseAdapter() {
            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return 0;
            }

            public int getCount() {
                return strArr.length;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = layoutInflater.inflate(R.layout.referral_share_option_item, viewGroup, false);
                }
                TextView textView = (TextView) view.findViewById(R.id.share_option_name);
                ((CrossFadeImageView) view.findViewById(R.id.share_option_img)).setImageDrawable(ContextCompat.getDrawable(ViewReferralActivityFragment.this.getContext(), ViewReferralActivityFragment.this.k.getResourceId(ViewReferralActivityFragment.this.l[i], -1)));
                textView.setText(strArr[i]);
                return view;
            }
        });
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                bottomSheetDialog.hide();
                if (ViewReferralActivityFragment.this.g == null) {
                    aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.sorry_some_thing_went_wrong));
                    return;
                }
                ResolveInfo a;
                switch (i) {
                    case 0:
                        a = ViewReferralActivityFragment.this.b("com.whatsapp");
                        if (a == null) {
                            aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.whatsapp_not_installed));
                            break;
                        }
                        UninstallIO.sendReferFriendEvent("Whatsapp");
                        MoEngage.getInstance().reportReferralSource("Whatsapp");
                        ((BaseActivity) ViewReferralActivityFragment.this.mContext).sendGAEvent(((BaseActivity) ViewReferralActivityFragment.this.mContext).currentScreen, "Invite", "Whatsapp");
                        ViewReferralActivityFragment.this.h.a(a, "", ViewReferralActivityFragment.this.g.getMessage(), "", "", null, "");
                        break;
                    case 1:
                        a = ViewReferralActivityFragment.this.b(MessengerUtils.PACKAGE_NAME);
                        if (a == null) {
                            aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.messenger_not_installed));
                            break;
                        }
                        UninstallIO.sendReferFriendEvent("Messenger");
                        MoEngage.getInstance().reportReferralSource("Messenger");
                        ((BaseActivity) ViewReferralActivityFragment.this.mContext).sendGAEvent(((BaseActivity) ViewReferralActivityFragment.this.mContext).currentScreen, "Invite", "Messenger");
                        ViewReferralActivityFragment.this.h.a(a, "", ViewReferralActivityFragment.this.g.getMessage(), "", "", null, "");
                        break;
                    case 2:
                        ((GaanaActivity) ViewReferralActivityFragment.this.mContext).showProgressDialog(Boolean.valueOf(true), ViewReferralActivityFragment.this.getString(R.string.posting_on_wall));
                        g.a().a((Activity) ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.g.getMessage(), ViewReferralActivityFragment.this.mContext, new g.a() {
                            public String OnAuthrizationSuccess() {
                                ((GaanaActivity) ViewReferralActivityFragment.this.mContext).hideProgressDialog();
                                UninstallIO.sendReferFriendEvent("Facebook");
                                MoEngage.getInstance().reportReferralSource("Facebook");
                                ((BaseActivity) ViewReferralActivityFragment.this.mContext).sendGAEvent(((BaseActivity) ViewReferralActivityFragment.this.mContext).currentScreen, "Invite", "Facebook");
                                aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.posted_successfully));
                                return null;
                            }

                            public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
                                ((GaanaActivity) ViewReferralActivityFragment.this.mContext).hideProgressDialog();
                                aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.some_error_occurred));
                            }
                        });
                        break;
                    case 3:
                        a = ViewReferralActivityFragment.this.b("com.twitter.android");
                        if (a == null) {
                            aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.twitter_not_installed));
                            break;
                        }
                        UninstallIO.sendReferFriendEvent("Twitter");
                        MoEngage.getInstance().reportReferralSource("Twitter");
                        ((BaseActivity) ViewReferralActivityFragment.this.mContext).sendGAEvent(((BaseActivity) ViewReferralActivityFragment.this.mContext).currentScreen, "Invite", "Twitter");
                        ViewReferralActivityFragment.this.h.a(a, "", ViewReferralActivityFragment.this.g.getMessageSMS(), "", "", null, "");
                        break;
                    case 4:
                        a = ViewReferralActivityFragment.this.b("com.google.android.gm");
                        if (a == null) {
                            aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.gmai_not_installed));
                            break;
                        }
                        UninstallIO.sendReferFriendEvent("Gmail");
                        MoEngage.getInstance().reportReferralSource("Gmail");
                        ((BaseActivity) ViewReferralActivityFragment.this.mContext).sendGAEvent(((BaseActivity) ViewReferralActivityFragment.this.mContext).currentScreen, "Invite", "Gmail");
                        ViewReferralActivityFragment.this.h.a(a, ViewReferralActivityFragment.this.g.getMessageSubject(), ViewReferralActivityFragment.this.g.getMessage(), "", "", null, "");
                        break;
                    case 5:
                        try {
                            UninstallIO.sendReferFriendEvent("SMS");
                            MoEngage.getInstance().reportReferralSource("SMS");
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.putExtra("sms_body", ViewReferralActivityFragment.this.g.getMessageSMS());
                            intent.setType("vnd.android-dir/mms-sms");
                            ((BaseActivity) ViewReferralActivityFragment.this.mContext).sendGAEvent(((BaseActivity) ViewReferralActivityFragment.this.mContext).currentScreen, "Invite", "SMS");
                            ViewReferralActivityFragment.this.startActivity(intent);
                            break;
                        } catch (Exception unused) {
                            aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.not_able_to_share_via_sms));
                            break;
                        }
                    case 6:
                        ((ClipboardManager) ViewReferralActivityFragment.this.mContext.getSystemService("clipboard")).setText(ViewReferralActivityFragment.this.g.getReferralUrl());
                        aj.a().a(ViewReferralActivityFragment.this.mContext, ViewReferralActivityFragment.this.getString(R.string.copied_to_clipboard));
                        UninstallIO.sendReferFriendEvent("Copy");
                        MoEngage.getInstance().reportReferralSource("Copy");
                        ((BaseActivity) ViewReferralActivityFragment.this.mContext).sendGAEvent(((BaseActivity) ViewReferralActivityFragment.this.mContext).currentScreen, "Invite", "Copy");
                        break;
                }
            }
        });
        bottomSheetDialog.show();
    }

    private ResolveInfo b(String str) {
        if (this.i != null) {
            for (ResolveInfo resolveInfo : this.i) {
                if (resolveInfo.activityInfo.packageName.equalsIgnoreCase(str)) {
                    return resolveInfo;
                }
            }
        }
        return null;
    }

    public void onDestroyView() {
        if (this.containerView.getParent() != null) {
            ((ViewGroup) this.containerView.getParent()).removeView(this.containerView);
        }
        super.onDestroyView();
    }
}
