package com.example.computer.Action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.computer.Model.*;
import com.example.computer.DAO.*;

import com.example.computer.*;
import com.example.computer.Utilts.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RangeActivity extends FragmentActivity {

    public int QuestionsIndex;
    public Intent intent;
    public static DB_Helper db_Hleper;// 数据库
    public static ArrayList<Questions> arrayList;// 所有考题
    public int answer;
    public int flag;
    public static final String FILENAME = "/mnt/sdcard/enhance/enhace.txt";
    private ViewPager mViewPager;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                arrayList = (ArrayList<Questions>) msg.obj;

                //QuestionsIndex=readQuestionIndex();
                FragmentManager manager = getSupportFragmentManager();
                FragmentStatePagerAdapter adapter = Utils.getFragmentAdater(manager, arrayList,  flag);
                adapter.notifyDataSetChanged();
                mViewPager.setAdapter(adapter);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        db_Hleper = new DB_Helper(this);// new锟斤拷sqlite
        arrayList = new ArrayList<Questions>();
        //锟斤拷锟斤拷全锟斤拷
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.ViewPager);
        setContentView(mViewPager);

        intent = getIntent();
        flag = intent.getIntExtra("flag", -1);


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                List<Questions> list = new ArrayList<Questions>();
                list = db_Hleper.queryAll();
                Message message = Message.obtain();
                message.what = 0;
                message.obj = list;
                handler.sendMessage(message);

            }
        }).start();
    }



}
