package com.moengage.core.integrationVerification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.delight.pushlibrary.R;

public class IntegrationVerificationActivity extends AppCompatActivity implements View {
    private ProgressDialog dialog;
    private TextView messageWidget;
    private Presenter presenter;
    private TextView retryButtonWidget;
    private TextView unregisterButtonWidget;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_integration_verification);
        init();
        this.presenter = new IntegrationVerificationPresenter(getApplicationContext(), this);
        this.presenter.checkAndRestoreState();
    }

    private void init() {
        this.messageWidget = (TextView) findViewById(R.id.message);
        this.retryButtonWidget = (TextView) findViewById(R.id.retryButton);
        this.retryButtonWidget.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                IntegrationVerificationActivity.this.presenter.registerDevice();
            }
        });
        this.unregisterButtonWidget = (TextView) findViewById(R.id.unregisterButton);
        this.unregisterButtonWidget.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                IntegrationVerificationActivity.this.presenter.unregisterDevice();
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        dismissLoadingDialog();
    }

    public void showLoadingDialog(String str) {
        this.dialog = ProgressDialog.show(this, "", str, true);
    }

    public void dismissLoadingDialog() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (IntegrationVerificationActivity.this.dialog != null) {
                    IntegrationVerificationActivity.this.dialog.dismiss();
                }
            }
        });
    }

    public void messageAndButton(final String str, final int i) {
        runOnUiThread(new Runnable() {
            public void run() {
                IntegrationVerificationActivity.this.messageWidget.setText(str);
                IntegrationVerificationActivity.this.messageWidget.setVisibility(0);
                if (i == R.id.unregisterButton) {
                    IntegrationVerificationActivity.this.unregisterButtonWidget.setVisibility(0);
                    IntegrationVerificationActivity.this.retryButtonWidget.setVisibility(8);
                }
                if (i == R.id.retryButton) {
                    IntegrationVerificationActivity.this.retryButtonWidget.setVisibility(0);
                    IntegrationVerificationActivity.this.unregisterButtonWidget.setVisibility(8);
                }
            }
        });
    }
}
