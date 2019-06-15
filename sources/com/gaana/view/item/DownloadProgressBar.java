package com.gaana.view.item;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.i.d;
import com.library.managers.TaskManager.TaskListner;
import com.managers.DownloadManager;
import com.managers.aj;
import com.managers.ap;
import com.services.FileDownloadService;
import com.services.l.ae;
import com.utilities.Util;
import java.lang.ref.WeakReference;

public class DownloadProgressBar extends BaseItemView {
    private static final String TAG = "DownloadProgressBar";
    private ImageView imgPauseResume = null;
    private LinearLayout llCancelDownload = null;
    private BaseGaanaFragment mFragment;
    private int mLayoutResourceId = R.layout.view_download_header;
    private View mView = null;
    private ProgressBar progressBar = null;
    private TextView tvPauseResume = null;
    private TextView tvTrackCountProgress = null;

    private static class FailedDownloadThread extends Thread {
        private WeakReference<TaskListner> failedDownloadTaskWeakReference;
        private Handler handler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                if (FailedDownloadThread.this.failedDownloadTaskWeakReference.get() != null) {
                    ((TaskListner) FailedDownloadThread.this.failedDownloadTaskWeakReference.get()).onBackGroundTaskCompleted();
                }
            }
        };

        FailedDownloadThread(TaskListner taskListner) {
            this.failedDownloadTaskWeakReference = new WeakReference(taskListner);
        }

        public void run() {
            if (this.failedDownloadTaskWeakReference.get() != null) {
                ((TaskListner) this.failedDownloadTaskWeakReference.get()).doBackGroundTask();
                Message obtain = Message.obtain();
                obtain.obj = "Task Performed";
                this.handler.sendMessage(obtain);
            }
        }
    }

    public DownloadProgressBar(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
    }

    public DownloadProgressBar(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
    }

    public View getView(ViewGroup viewGroup) {
        this.mView = super.getNewView(this.mLayoutResourceId, viewGroup);
        this.tvTrackCountProgress = (TextView) this.mView.findViewById(R.id.f46download.progress.tv.progress);
        this.tvPauseResume = (TextView) this.mView.findViewById(R.id.f47download.progress.tvPauseResume);
        this.imgPauseResume = (ImageView) this.mView.findViewById(R.id.f44download.progress.imgPauseResume);
        this.llCancelDownload = (LinearLayout) this.mView.findViewById(R.id.f57ll.download.progress.cancel);
        this.llCancelDownload.setOnClickListener(this);
        this.progressBar = (ProgressBar) this.mView.findViewById(R.id.f45download.progress.progress_bar);
        this.imgPauseResume.setOnClickListener(this);
        updateGlobalDownloadProgressbar(Boolean.valueOf(true));
        ((GaanaActivity) this.mContext).addDownloadReceiver();
        DownloadManager.c().d();
        return this.mView;
    }

    public void updateDownloadProgress(int i) {
        this.progressBar.setProgress(i);
    }

    public void updateTrackCount(int i, int i2) {
        TextView textView = this.tvTrackCountProgress;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i2);
        stringBuilder.append(" OF ");
        stringBuilder.append(i);
        stringBuilder.append(" Completed");
        textView.setText(stringBuilder.toString());
    }

    public void onClick(View view) {
        super.onClick(view);
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            int id = view.getId();
            if (id != R.id.f44download.progress.imgPauseResume) {
                if (id == R.id.f57ll.download.progress.cancel) {
                    new CustomDialogView(this.mContext, (int) R.string.dialog_canceldownload_text, new OnButtonClickListener() {
                        public void onNegativeButtonClick() {
                        }

                        public void onPositiveButtonClick() {
                            DownloadProgressBar.this.mView.setVisibility(8);
                            DownloadProgressBar.this.cancelDownload();
                        }
                    }).show();
                }
            } else if (this.tvPauseResume.getTag().toString().equals("pause")) {
                new CustomDialogView(this.mContext, (int) R.string.dialog_stopdownload_text, new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        DownloadManager.c().y();
                        DownloadManager.c().c(false);
                        DownloadManager.c().e();
                        DownloadProgressBar.this.updateGlobalDownloadProgressbar(Boolean.valueOf(true));
                    }
                }).show();
            } else {
                DownloadManager.c().z();
                DownloadManager.c().c(true);
                DownloadManager.c().d();
                updateGlobalDownloadProgressbar(Boolean.valueOf(true));
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void cancelDownload() {
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(false), this.mContext.getString(R.string.cancel_download_msg));
        d.a(new Runnable() {
            public void run() {
                DownloadManager.c().A();
                ((Activity) DownloadProgressBar.this.mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        if (DownloadProgressBar.this.mFragment instanceof DownloadDetailsFragment) {
                            ((DownloadDetailsFragment) DownloadProgressBar.this.mFragment).d();
                        }
                        ((BaseActivity) DownloadProgressBar.this.mContext).hideProgressDialog();
                    }
                });
            }
        });
    }

    public void updateGlobalDownloadProgressbar(Boolean bool) {
        if (this.mView != null) {
            if (GaanaApplication.getInstance().isAppInOfflineMode()) {
                this.mView.setVisibility(8);
            } else if (DownloadManager.c().q() == 0 || !bool.booleanValue()) {
                this.mView.setVisibility(8);
                if (DownloadManager.c().r() > 0) {
                    checkForFailedDownloadView((ViewGroup) this.mView.getParent());
                }
            } else if (bool.booleanValue()) {
                this.mView.setVisibility(0);
                int p = DownloadManager.c().p();
                int B = DownloadManager.c().B();
                if (DownloadManager.c().v()) {
                    TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.download_button_pause});
                    Drawable drawable = obtainStyledAttributes.getDrawable(0);
                    obtainStyledAttributes.recycle();
                    this.imgPauseResume.setImageDrawable(drawable);
                    this.tvPauseResume.setText(R.string.pause);
                    this.tvPauseResume.setTag("pause");
                    this.progressBar.setProgressDrawable(this.mContext.getResources().getDrawable(R.drawable.layer_download_progressbar));
                } else if (B == 0) {
                    this.mView.setVisibility(8);
                } else {
                    this.imgPauseResume.setImageResource(R.drawable.vector_download_retry);
                    this.tvPauseResume.setText(R.string.resume);
                    this.tvPauseResume.setTag("resume");
                    this.progressBar.setProgressDrawable(this.mContext.getResources().getDrawable(R.drawable.layer_download_progressbar_paused));
                }
                B += p;
                this.progressBar.setMax(B);
                this.progressBar.setProgress(p);
                TextView textView = this.tvTrackCountProgress;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(p);
                stringBuilder.append(" OF ");
                stringBuilder.append(B);
                stringBuilder.append(" Completed");
                textView.setText(stringBuilder.toString());
            }
        }
    }

    public void refreshProgressBar() {
        if (DownloadManager.c().k() == -1 && DownloadManager.c().v()) {
            updateGlobalDownloadProgressbar(Boolean.valueOf(false));
        } else {
            updateGlobalDownloadProgressbar(Boolean.valueOf(true));
        }
    }

    public void checkForFailedDownloadView(final ViewGroup viewGroup) {
        if (viewGroup != null) {
            View view = new FailedDownloadView(this.mContext, this.mFragment).getView();
            ImageView imageView = (ImageView) view.findViewById(R.id.cancel_button);
            ((Button) view.findViewById(R.id.retry_button)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    new FailedDownloadThread(new TaskListner() {
                        public void doBackGroundTask() {
                            DownloadManager.c().m();
                        }

                        public void onBackGroundTaskCompleted() {
                            if (!com.services.d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                                FileDownloadService.a(new ae() {
                                    public void OnNetworkChangeListener(boolean z) {
                                        aj.a().a(GaanaApplication.getContext(), GaanaApplication.getContext().getResources().getString(R.string.toast_download_on_data_disabled));
                                        FileDownloadService.a(null);
                                    }
                                });
                            }
                            DownloadManager.c().d();
                            if (viewGroup != null) {
                                viewGroup.removeAllViews();
                                View view = DownloadProgressBar.this.getView(null);
                                viewGroup.addView(view);
                                new LayoutParams(-1, -2).setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 8.0f, GaanaApplication.getContext().getResources().getDisplayMetrics()));
                                view.setVisibility(0);
                                viewGroup.setVisibility(0);
                                view.setOnClickListener(new OnClickListener() {
                                    public void onClick(View view) {
                                        ((GaanaActivity) DownloadProgressBar.this.mContext).displayFragment(new DownloadDetailsFragment());
                                    }
                                });
                            }
                        }
                    }).start();
                }
            });
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewGroup != null) {
                        viewGroup.removeAllViews();
                        viewGroup.setVisibility(8);
                    }
                }
            });
            viewGroup.removeAllViews();
            viewGroup.addView(view);
            viewGroup.setVisibility(0);
        }
    }
}
