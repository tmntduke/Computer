package com.example.computer.Action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.computer.Fragment.AnswerFragment;
import com.example.computer.Model.*;
import com.example.computer.DAO.*;

import com.example.computer.R;
import com.example.computer.Utilts.Utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
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

public class ModelActivity extends FragmentActivity {
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
    public static DB_Helper db_Hleper;// 数据库
    public static ArrayList<Questions> arrayList;// 所有考题
    public Questions[] questions;
    public Questions question;
    public int answer;
    public static ModelActivity instance;
    public int flag;
    public int count = 0;
    public int i = 0;
    private ArrayList<Questions> turn;

    private static final String TAG = "ModelActivity";

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                arrayList = (ArrayList<Questions>) msg.obj;

                for (i = 0; i <= 19; i++) {
                    Random random = new Random();
                    int p = random.nextInt(19);
                    turn.add(arrayList.get(p));
                }
                Log.i(TAG, "handleMessage: " + turn.size());
                //QuestionsIndex=readQuestionIndex();
                FragmentManager manager = getSupportFragmentManager();
                FragmentStatePagerAdapter adapter = Utils.getFragmentAdater(manager, turn, flag);
                adapter.notifyDataSetChanged();
                mViewPager.setAdapter(adapter);

            }
        }
    };
    private ViewPager mViewPager;

    @Override
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

        turn = new ArrayList<>();

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
