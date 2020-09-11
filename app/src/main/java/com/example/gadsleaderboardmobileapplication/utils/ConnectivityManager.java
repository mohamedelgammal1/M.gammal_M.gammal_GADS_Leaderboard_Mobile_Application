package com.example.gadsleaderboardmobileapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import com.example.gadsleaderboardmobileapplication.R;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectivityManager {
    public static boolean CheckInternet(Activity activity) {
        if (activity != null) {
            android.net.ConnectivityManager cm = (android.net.ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities caps = null;
            Network activeNetwork;
            if (cm != null) {
                activeNetwork = cm.getActiveNetwork();
                caps = cm.getNetworkCapabilities(activeNetwork);
            }
            boolean isConnected = false;

            if (caps != null) {
                if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    isConnected = true;
                }
            }
            if (!isConnected) {
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
                View dialogView = View.inflate(activity, R.layout.connection_dialogue, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog alertDialog = dialogBuilder.create();
                Objects.requireNonNull(alertDialog.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT);
                Objects.requireNonNull(alertDialog.getWindow()).setDimAmount((float) 0.5);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.show();
                dialogView.findViewById(R.id.dismiss).setOnClickListener(v -> alertDialog.dismiss());
            }
            return isConnected;
        }
        return false;
    }

    public static AlertDialog vShowConfirmationDialogue(Activity activity,
                                                        View.OnClickListener yesListener) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        View dialogView = View.inflate(activity, R.layout.alert_dialogue, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        Objects.requireNonNull(alertDialog.getWindow()).setDimAmount((float) 0.5);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setCancelable(true);
        alertDialog.show();
        AppCompatButton yesButton = dialogView.findViewById(R.id.yes);
        AppCompatButton noButton = dialogView.findViewById(R.id.ic_close);
        yesButton.setOnClickListener(yesListener);
        noButton.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        return alertDialog;
    }

    public static AlertDialog vShowSuccessFailDialogue(Activity activity, boolean success) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        View dialogView;
        if (success) {
            dialogView = View.inflate(activity, R.layout.success_dialogue, null);
        } else {
            dialogView = View.inflate(activity, R.layout.fail_dialogue, null);
        }
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        Objects.requireNonNull(alertDialog.getWindow()).setDimAmount((float) 0.5);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setCancelable(true);
        alertDialog.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                alertDialog.dismiss();
            }
        }, 2000);
        return alertDialog;
    }
}
