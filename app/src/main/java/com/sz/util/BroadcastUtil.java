package com.sz.util;

import android.content.Intent;

import com.sz.application.IApplication;

/**
 * Created by E on 2018/2/22.
 */
public class BroadcastUtil {

    //应用选择
    public static void sendPkgSelectedChgBroadcast(int pageIndex , String pkgName , boolean added){
        Intent intent = new Intent();
        intent.setAction(BroadCastAction.PKG_SELECTED_CHG);
        intent.putExtra("pageIndex" , pageIndex);
        intent.putExtra("pkgName" , pkgName);
        intent.putExtra("added" , added);
        IApplication.getContext().sendBroadcast(intent);
    }

    //选择新的背景图
    public static void sendMainBgImgChgBroadcast(){
        Intent intent = new Intent();
        intent.setAction(BroadCastAction.MAIN_PAGE_BG_IMG_CHG);
        IApplication.getContext().sendBroadcast(intent);
    }

}
