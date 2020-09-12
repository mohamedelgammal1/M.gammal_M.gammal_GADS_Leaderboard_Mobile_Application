package com.example.gadsleaderboardmobileapplication.ui.submit;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.gadsleaderboardmobileapplication.R;
import com.example.gadsleaderboardmobileapplication.databinding.ActivitySubmitBinding;
import com.example.gadsleaderboardmobileapplication.utils.ConnectivityManager;
import com.github.florent37.viewtooltip.ViewTooltip;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.gadsleaderboardmobileapplication.utils.Validation.isEmailValid;

public class SubmitActivity extends AppCompatActivity {
    ActivitySubmitBinding activitySubmitBinding;
    SubmitViewModel submitViewModel;
    private AlertDialog alertDialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vInit();
        vHandleOnClickActions();
        vHandleObservers();
    }

    private void vHandleObservers() {
        submitViewModel.mSubmitMutableLiveData.observe(this, this::vHandleSubmissionResponse);
    }

    private void vHandleSubmissionResponse(Integer integer) {
        activitySubmitBinding.progressBar.setVisibility(View.GONE);
        if (integer == 1) {
            ConnectivityManager.vShowSuccessFailDialogue(this, true);
        } else {
            ConnectivityManager.vShowSuccessFailDialogue(this, false);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 3000);
    }

    private void vHandleOnClickActions() {
        activitySubmitBinding.icBackIcon.setOnClickListener(view -> {
            onBackPressed();
            finish();
        });
        activitySubmitBinding.btnSubmit.setOnClickListener(view -> vHandleSubmitClick());
    }

    private void vHandleSubmitClick() {
        if (Objects.requireNonNull(activitySubmitBinding.editTxtFName.getText()).toString().equals("")) {
            setError(activitySubmitBinding.editTxtFName, "Please enter your first name");
        } else if (Objects.requireNonNull(activitySubmitBinding.editTxtLName.getText()).toString().equals("")) {
            setError(activitySubmitBinding.editTxtLName, "Please enter your last name");
        } else if (Objects.requireNonNull(activitySubmitBinding.editTxtEmail.getText()).toString().equals("")) {
            setError(activitySubmitBinding.editTxtEmail, "Please enter your email");
        } else if (!isEmailValid(Objects.requireNonNull(activitySubmitBinding.editTxtEmail.getText()).toString())) {
            setError(activitySubmitBinding.editTxtEmail, "Please enter valid email");
        } else if (Objects.requireNonNull(activitySubmitBinding.editTxtProjectLink.getText()).toString().equals("")) {
            setError(activitySubmitBinding.editTxtProjectLink, "Please enter your project link");
        } else {
            alertDialog = ConnectivityManager.vShowConfirmationDialogue(this, view -> {
                alertDialog.dismiss();
                vCallSubmitAPI();
            });
        }
    }

    private void setError(AppCompatEditText editText, String error) {
        ViewTooltip
                .on(editText)
                .corner(40)
                .color(getResources().getColor(R.color.errorTextColor, null))
                .position(ViewTooltip.Position.TOP)
                .autoHide(true, 2000)
                .text(error)
                .clickToHide(true)
                .show();
    }

    private void vCallSubmitAPI() {
        if (ConnectivityManager.CheckInternet(this)) {
            activitySubmitBinding.progressBar.setVisibility(View.VISIBLE);
            submitViewModel.sendUserData(Objects.requireNonNull(activitySubmitBinding.editTxtFName.getText()).toString(),
                    Objects.requireNonNull(activitySubmitBinding.editTxtLName.getText()).toString(),
                    Objects.requireNonNull(activitySubmitBinding.editTxtEmail.getText()).toString(),
                    Objects.requireNonNull(activitySubmitBinding.editTxtProjectLink.getText()).toString());
        }
    }

    private void vInit() {
        /* binding initialization*/
        activitySubmitBinding = DataBindingUtil.setContentView(this, R.layout.activity_submit);
        activitySubmitBinding.setLifecycleOwner(this);
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        submitViewModel = viewModelProvider.get(SubmitViewModel.class);
    }
}