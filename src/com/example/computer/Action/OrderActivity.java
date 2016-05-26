package com.example.computer.Action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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

import com.example.computer.Action.*;
import com.example.computer.Fragment.AnswerFragment;
import com.example.computer.R;
import com.example.computer.Model.Questions;
import com.example.computer.DAO.DB_Helper;
import com.example.computer.Utilts.Utils;


public class OrderActivity extends FragmentActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private ImageView imageView;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    public int QuestionsIndex;
    public Intent intent;
    public static DB_Helper db_Hleper;// 锟斤拷锟捷匡拷
    public static ArrayList<Questions> arrayList;// 锟斤拷锟叫匡拷锟斤拷
    public int answer;

    private ViewPager mViewPager;
    private int type;

    public int flag;
    public static final String FILENAME = "/mnt/sdcard/progress/progress.txt";

    /**
     *
     */
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                arrayList = (ArrayList<Questions>) msg.obj;

                //QuestionsIndex=readQuestionIndex();
                FragmentManager manager = getSupportFragmentManager();
                FragmentStatePagerAdapter adapter = Utils.getFragmentAdater(manager, arrayList,0);
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
