package com.classichu.classichu2.helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.classichu.classichu2.tool.AppTool;
import com.classichu.classichu2.tool.BaseTool;
import com.classichu.classichu2.tool.DeviceTool;
import com.classichu.classichu2.tool.ToastTool;

import java.util.List;


/**
 * Created by louisgeek on 2017/2/21.
 *
 * 引导用户到系统设置页面，或者应用信息（有些厂家会直接在应用信息提供权限管理入口）
 */
public class GoToSysConfigHelper {

    public static  void  goToNormalEnterWithManufacturer(){
        try {
        String manufacturer= DeviceTool.getDeviceManufacturer();

        switch (manufacturer.toLowerCase()){
            case "huawei":
                goToSysConfigAppPermissionsHuaWei();
                break;
            case "xiaomi":
                goToSysConfigAppPermissionsXiaoMi();
                break;
            case "meizu":
                goToSysConfigAppPermissionsMeizu();
                break;
            case "oppo":
                goToSysConfigAppPermissionsOppo();
                break;
            //VIVO X6S Plus 系统一直处理  弹出是否允许和引导
            case "vivo":
                goToSysConfigAppPermissionsVivo();
                break;
            case "samsung":
                goToSysConfigAppPermissionsSamsung();
                break;
            case "coolpad":
                goToSysConfigAppPermissionsCoolpad();
                break;
            case "lenovo":
            case "gionee":
            default:
                goToSysConfigAppInfoSettings();
                break;
        }
        }catch (Exception e){
            Log.e("kkk 1", "e: "+e.getMessage());
            ToastTool.showLong("kkk 1 e: "+e.getMessage());
           try {
               goToSysConfigAppInfoSettings2();
           }catch (Exception e1)
           {
               Log.e("kkk 2", "e1: "+e1.getMessage());
               ToastTool.showLong("kkk 2 e1: "+e1.getMessage());
               goToSysConfigSettings();
           }
        }
    }


    /**
     * 应用信息（有些厂家会直接在应用信息提供权限管理入口）
     *
     * Meizu note2 测试通过
     */
    @Deprecated
    public static  void  goToSysConfigAppInfoSettings2(){
        Context appContext = BaseTool.getAppContext();
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", AppTool.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", AppTool.getPackageName());
        }
        appContext.startActivity(intent);
    }
    /**
     * 跳转系统的蓝牙设置界面
     */
    public static    void  goToSysConfigBluetooth(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
    }
    /**
     * 跳转到设置界面
     */
    public static    void  goToSysConfigSettings(){
        Context appContext =BaseTool.getAppContext();
        Intent intent =  new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
    }
    /**
     * 根据包名跳转到系统自带的应用程序信息界面
     */
    public static void goToSysConfigAppInfoSettings(){
        try {
            Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromParts("package", AppTool.getPackageName(), null);
        intent.setData(uri);
        appContext.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static  void  goToSysConfigAppPermissionsHuaWei(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", AppTool.getPackageName());
        ComponentName comp = new ComponentName("com.huawei.systemmanager",
                "com.huawei.permissionmanager.ui.MainActivity");
        intent.setComponent(comp);
        appContext.startActivity(intent);
    }
    /**
     * redmi 3s （包括6.0以上和以下）测试通过
     */
    private static  void  goToSysConfigAppPermissionsXiaoMi(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("extra_pkgname", AppTool.getPackageName());
        appContext.startActivity(intent);
    }
    /**
     * Meizu note2
     */
    private  static void  goToSysConfigAppPermissionsMeizu(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", AppTool.getPackageName());
        appContext.startActivity(intent);
    }

    /**
     * oppo a33测试通过
     */
    private  static void  goToSysConfigAppPermissionsOppo(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", AppTool.getPackageName());
        ComponentName comp = new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
        intent.setComponent(comp);
        appContext.startActivity(intent);
    }

    /**
     * doStartApplicationWithPackageName("")
     * 和Intent open = getPackageManager().getLaunchIntentForPackage("");
     startActivity(open);
     本质上没有什么区别，通过Intent open...打开比调用doStartApplicationWithPackageName方法更快，也是android本身提供的方法
     */
    @Deprecated //单纯的跳转到oppo测试
    private static void goToSysConfigAppPermissionsOppo2(){
        Context appContext =BaseTool.getAppContext();
        //#### doStartApplicationWithPackageName(context,"com.coloros.safecenter");
        Intent openIntent = appContext.getPackageManager().getLaunchIntentForPackage("com.color.safecenter");
        openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(openIntent);
    }
    private static void goToSysConfigAppPermissionsVivo(){
        Context appContext =BaseTool.getAppContext();
        //####  doStartApplicationWithPackageName(context,"com.vivo.securedaemonservice");
        Intent openIntent = appContext.getPackageManager().getLaunchIntentForPackage("com.vivo.securedaemonservice");
        openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(openIntent);
    }

    private static void goToSysConfigAppPermissionsCoolpad(){
        Context appContext =BaseTool.getAppContext();
       //#### doStartApplicationWithPackageName(context,"com.yulong.android.security:remote");
        Intent openIntent = appContext.getPackageManager().getLaunchIntentForPackage("com.yulong.android.security:remote");
        openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(openIntent);
    }
    private static void goToSysConfigAppPermissionsSamsung(){
        //三星4.3可以直接跳转
        goToSysConfigAppInfoSettings();
    }

    private  static void  goToSysConfigAppPermissionsSony(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName",AppTool.getPackageName());
        ComponentName comp = new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity");
        intent.setComponent(comp);
        appContext.startActivity(intent);
    }
    private  static void  goToSysConfigAppPermissionsLetv(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", AppTool.getPackageName());
        ComponentName comp = new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps");
        intent.setComponent(comp);
        appContext.startActivity(intent);
    }
    private  static void  goToSysConfigAppPermissionsLG(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", AppTool.getPackageName());
        ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
        intent.setComponent(comp);
        appContext.startActivity(intent);
    }
    private  static void  goToSysConfigAppPermissions360(){
        Context appContext =BaseTool.getAppContext();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", AppTool.getPackageName());
        ComponentName comp = new ComponentName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
        intent.setComponent(comp);
        appContext.startActivity(intent);
    }

    @Deprecated
    private void doStartApplicationWithPackageName(String packageName) {
        Context appContext =BaseTool.getAppContext();
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = appContext.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }
        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//待测试 是否需要
        resolveIntent.setPackage(packageinfo.packageName);
        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = appContext.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        Log.i("MainActivity","resolveinfoList"+resolveinfoList.size());
        for (int i = 0; i < resolveinfoList.size(); i++) {
            Log.i("MainActivity",resolveinfoList.get(i).activityInfo.packageName+resolveinfoList.get(i).activityInfo.name);
        }
        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packagename = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packagename, className);
            intent.setComponent(cn);
            try {
                appContext.startActivity(intent);
            }catch (Exception e){
                //!!!
                goToSysConfigAppInfoSettings();
                e.printStackTrace();
            }
        }
    }
}
