package com.example.gadsleaderboardmobileapplication.ui.submit;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.gadsleaderboardmobileapplication.R;
import com.example.gadsleaderboardmobileapplication.databinding.ActivitySubmitBinding;
import com.example.gadsleaderboardmobileapplication.utils.ConnectivityManager;

import java.util.HashMap;
import java.util.Objects;

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
        if (integer == 1) {
            ConnectivityManager.vShowSuccessFailDialogue(this, true);
        } else {
            ConnectivityManager.vShowSuccessFailDialogue(this, false);
        }
    }

    private void vHandleOnClickActions() {
        activitySubmitBinding.icBackIcon.setOnClickListener(view -> {
            onBackPressed();
            finish();
        });
        activitySubmitBinding.btnSubmit.setOnClickListener(view -> vHandleSubmitClick());
    }

    private void vHandleSubmitClick() {
        if (!Objects.requireNonNull(activitySubmitBinding.editTxtFName.getText()).toString().equals("") &&
                !Objects.requireNonNull(activitySubmitBinding.editTxtLName.getText()).toString().equals("") &&
                !Objects.requireNonNull(activitySubmitBinding.editTxtEmail.getText()).toString().equals("") &&
                !Objects.requireNonNull(activitySubmitBinding.editTxtProjectLink.getText()).toString().equals("") &&
                isEmailValid(Objects.requireNonNull(activitySubmitBinding.editTxtEmail.getText()).toString())) {
            alertDialog = ConnectivityManager.vShowConfirmationDialogue(this, view -> {
                alertDialog.dismiss();
                vCallSubmitAPI();
            });

        } else {
            Toast.makeText(this, "Please complete cradentials ",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void vCallSubmitAPI() {
        if (ConnectivityManager.CheckInternet(this)) {
            activitySubmitBinding.progressBar.setVisibility(View.VISIBLE);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("entry.1877115667", Objects.requireNonNull(activitySubmitBinding.editTxtFName.getText()).toString());
            hashMap.put("entry.2006916086", Objects.requireNonNull(activitySubmitBinding.editTxtLName.getText()).toString());
            hashMap.put("entry.1824927963", Objects.requireNonNull(activitySubmitBinding.editTxtEmail.getText()).toString());
            hashMap.put("entry.284483984", Objects.requireNonNull(activitySubmitBinding.editTxtProjectLink.getText()).toString());


            submitViewModel.sendUserData(hashMap);
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