package com.example.computer.Action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer.Action.*;
import com.example.computer.R;
import com.example.computer.Model.Questions;
import com.example.computer.DAO.DB_Helper;
import com.example.computer.Utilts.Utils;

/**
 * @author tmnt
 */
public class AgainActivity extends FragmentActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private ImageView imageView;
    private Button btn1;
    private Button btn2;

    public int QuestionsIndex;
    public Intent intent;
    public static DB_Helper db_Hleper;// 数据库
    public static ArrayList<Questions> arrayList;// 所有考题
    public List<Questions> list2;
    public Questions question;
    public int answer;
    public static AgainActivity instance;
    public int flag;
    public int count = 0;
    public int i = 0;
    public List<Integer> list;
    private ViewPager mViewPager;
    private ArrayList<Questions> wrongs;

//    public Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0) {
//                arrayList = (ArrayList<Questions>) msg.obj;
//
//                //QuestionsIndex=readQuestionIndex();
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentStatePagerAdapter adapter = Utils.getFragmentAdater(manager, arrayList, flag);
//                adapter.notifyDataSetChanged();
//                mViewPager.setAdapter(adapter);
//            }
//        }
//    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        db_Hleper = new DB_Helper(this);// new锟斤拷sqlite
        arrayList = new ArrayList<Questions>();
        //锟斤拷锟斤拷全锟斤拷
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.ViewPager);
        setContentView(mViewPager);

        intent = getIntent();
        flag = intent.getIntExtra("flag", -1);
        if (intent != null && intent.getIntExtra("who", 0) == 001) {
            wrongs = (ArrayList<Questions>) intent.getSerializableExtra("wrong");
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentStatePagerAdapter adapter = Utils.getFragmentAdater(manager, wrongs, flag);
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);


//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                List<Questions> list = new ArrayList<Questions>();
//                list = db_Hleper.queryAll();
//                Message message = Message.obtain();
//                message.what = 0;
//                message.obj = list;
//                handler.sendMessage(message);
//
//            }
//        }).start();
    }


}
