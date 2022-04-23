package com.example.foodrecepieapp.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.foodrecepieapp.R;

public class InternetCheckService extends BroadcastReceiver {


    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {

        String status = NetworkUtil.getNetworkState(context);

        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.cutom_dailog);
        Button retry_bt = dialog.findViewById(R.id.retry_bt);

        retry_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).finish();
                Intent intent1 = new Intent(context, MainActivity.class);
                context.startActivity(intent1);
            }
        });


        if (status.isEmpty() || status.equals("No Internet")){

            dialog.show();
        }
    }
}
