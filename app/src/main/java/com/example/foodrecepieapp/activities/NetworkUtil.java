package com.example.foodrecepieapp.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.airbnb.lottie.animation.content.Content;

public class NetworkUtil {

    public static String getNetworkState(Context context){

        String status = null;

        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!= null){

            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE || networkInfo.getType()== ConnectivityManager.TYPE_WIFI){

                status = "Internet Available";
                return status;
            }

        }else{

            status = "No Internet";
            return status;
        }
        return  status;
    }
}
