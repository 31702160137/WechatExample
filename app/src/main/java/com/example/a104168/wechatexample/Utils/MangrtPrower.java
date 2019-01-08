package com.example.a104168.wechatexample.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class MangrtPrower {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            //写
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            //读
            Manifest.permission.READ_EXTERNAL_STORAGE,
            //通讯录
            Manifest.permission.READ_CONTACTS,
            //通话记录
            Manifest.permission.READ_CALL_LOG
    };
    public static void verifyStoragePermissions(final Activity activity){
//        检查是否有写入权限
        int permission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED){
            // 我们没有权限提示用户并申请权限
            ActivityCompat.requestPermissions(activity,PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            // 提示用户手机更新状态

        }
    }
}
