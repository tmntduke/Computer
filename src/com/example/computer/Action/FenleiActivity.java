package com.example.computer.Action;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.computer.Fragment.AnotherFragment;
import com.example.computer.Model.*;

import com.example.computer.R;
import com.example.computer.Utilts.Utils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class FenleiActivity extends FragmentActivity {


    private ViewPager mViewPager;

    private static final String TAG = "FenleiActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.ViewPager);
        setContentView(mViewPager);

        Intent intent = getIntent();

        Bmob.initialize(this, "5b5167d530b5db1c3696b59f02b904bb");

        switch (intent.getIntExtra("flag1", 0)) {
            case 6:
                getyAnotherAnswer("java", 6);
                break;
            case 7:
                getyAnotherAnswer("python", 7);
                break;
            case 8:
                getyAnotherAnswer("c#", 8);
                break;
            case 9:
                getyAnotherAnswer("other", 9);
                break;

        }


    }

    public void getAnotherAdapter(ArrayList<AnotherAnswer> l, int flag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int i) {
                return AnotherFragment.newInstance(l, i, 0, flag);
            }

            @Override
            public int getCount() {
                return l.size();
            }
        };
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
    }


    public void getyAnotherAnswer(String kind, int flag) {
        BmobQuery<AnotherAnswer> mAnotherQuestionBmobQuery = new BmobQuery<>();

        mAnotherQuestionBmobQuery.addWhereEqualTo("kind", kind);
        mAnotherQuestionBmobQuery.findObjects(this, new FindListener<AnotherAnswer>() {

            @Override
            public void onSuccess(List<AnotherAnswer> list) {

                getAnotherAdapter((ArrayList<AnotherAnswer>) list, flag);
            }


            @Override
            public void onError(int i, String s) {
                Utils.showToast(FenleiActivity.this, "wrong");
            }
        });

    }


}

