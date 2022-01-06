package com.vaibhav.telementary;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

public class ManagerAdmin extends DeviceAdminReceiver {
    ComponentName mDevice_admin;

    @Override
    public void onEnabled(@NonNull Context context, @NonNull Intent intent) {
        super.onEnabled(context, intent);
        Log.d("LEAF", " Device Owner Enabled");
        mDevice_admin = new ComponentName(context, ManagerAdmin.class);

        DevicePolicyManager policyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        policyManager.setCameraDisabled(mDevice_admin, true);
        policyManager.getCameraDisabled(mDevice_admin);
    }
}
