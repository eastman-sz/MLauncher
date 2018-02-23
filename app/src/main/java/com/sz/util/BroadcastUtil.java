package com.sz.util;

import android.content.Intent;

import com.sz.application.IApplication;

/**
 * Created by E on 2018/2/22.
 */
public class BroadcastUtil {

    public static void sendPkgSelectedChgBroadcast(int pageIndex , String pkgName , boolean added){
        Intent intent = new Intent();
        intent.setAction(BroadCastAction.PKG_SELECTED_CHG);
        intent.putExtra("pageIndex" , pageIndex);
        intent.putExtra("pkgName" , pkgName);
        intent.putExtra("added" , added);
        IApplication.getContext().sendBroadcast(intent);
    }

}
