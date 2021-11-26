package com.vaibhav.telementary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class appProperties {
    final Context context;
    final PackageManager pm;
    List<ApplicationInfo> appInfo = new ArrayList<>();
    List<PackageInfo> pkgInfo = new ArrayList<>();
    List<Drawable> appicons = new ArrayList<>();
    List<Intent> playstore = new ArrayList<>();
    List<String> appNames = new ArrayList<>();
    List<String> pkgNames = new ArrayList<>();
    List<Intent> infoIntent = new ArrayList<>();
    int packageSize;

    appProperties(Context ct) {
        this.context = ct;
        this.pm = ct.getPackageManager();
        this.appInfo = pm.getInstalledApplications(0);
        appInfo.sort(Comparator.comparing(o -> pm.getApplicationLabel(o).toString()));

        try {
            for (ApplicationInfo o : appInfo) {
                pkgInfo.add(pm.getPackageInfo(o.packageName, 0));
                appicons.add(pm.getApplicationIcon(o));
                playstore.add(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + o.packageName)));
                appNames.add((String) pm.getApplicationLabel(o));
                pkgNames.add(o.packageName);
                infoIntent.add(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + o.packageName)));
            }
            packageSize = pkgInfo.size();
//            pkgInfo.add(context.getPackageManager().getPackageInfo(context.getPackageName(),0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "ERROR While adding additional packageInfo", Toast.LENGTH_SHORT).show();
            Log.e("ERROR", "Error in Constructor of appProperties.java at Line 317 :( ");
        }
//        pkgInfo.sort(Comparator.comparing(o -> o.packageName));
    }

    public void releaser() {
        Toast.makeText(context, "Releaser Started", Toast.LENGTH_SHORT).show();
        appInfo.clear();
        pkgInfo.clear();
        appicons.clear();
        playstore = new ArrayList<>();
        appNames.clear();
        pkgNames.clear();
        infoIntent.clear();
        Toast.makeText(context, "Releaser finished", Toast.LENGTH_LONG).show();
    }

}
