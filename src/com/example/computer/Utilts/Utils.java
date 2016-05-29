package com.example.computer.Utilts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.example.computer.Fragment.AnswerFragment;
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
                    Random random = new Random();
                    int p = random.nextInt(30);
                    //Log.i(TAG, "getItem: " + i + p);
                    return AnswerFragment.newInstance(list, i+1, list.get(i + p).getMexam_type(), flag);
                } else {
                    return AnswerFragment.newInstance(list, i, list.get(i).getMexam_type(), flag);
                }

            }

            @Override
            public int getCount() {
                return list.size();
            }
        };

        return adapter;
    }

}
