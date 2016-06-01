package com.example.computer.Utilts;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.example.computer.Fragment.AnswerFragment;
import com.example.computer.Model.AnotherAnswer;
import com.example.computer.Model.Questions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tmnt on 2016/5/26.
 */
public class Utils {

    private static final String TAG = "Utils";

    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    public static void showToast(Context context, String title) {
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
    }

    public static FragmentStatePagerAdapter getFragmentAdater(FragmentManager manager, ArrayList<Questions> list, int flag) {
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int i) {
                if (flag == 1) {
                    Random random = new Random();
                    int p = random.nextInt(150);
                    //Log.i(TAG, "getItem: " + i + p);
                    return AnswerFragment.newInstance(list, i + p, list.get(i + p).getMexam_type(), flag);
                } else if (flag == 2) {
//                    Random random = new Random();
//                    int p = random.nextInt(20);
//                    //Log.i(TAG, "getItem: " + i + p);
                    return AnswerFragment.newInstance(list, i, list.get(i).getMexam_type(), flag);
                } else {
                    return AnswerFragment.newInstance(list, i, list.get(i).getMexam_type(), flag);
                }

            }

            @Override
            public int getCount() {
                {
                    return list.size();
                }

            }
        };

        return adapter;
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    public static int getNetWorkStatus(Context context) {
        int netWorkType = Constants.NETWORK_CLASS_UNKNOWN;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (type == ConnectivityManager.TYPE_WIFI) {
                netWorkType = Constants.NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                netWorkType = Constants.getNetWorkClass(context);
            }
        }
        return netWorkType;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Context context) {

        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        context.startActivity(intent);
    }

    public static boolean isMobileConnected(Context context) {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobileNetworkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return mMobileNetworkInfo.isAvailable();


    }
}
