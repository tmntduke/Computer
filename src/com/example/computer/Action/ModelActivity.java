package com.example.computer.Action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import com.example.computer.Model.*;
import com.example.computer.DAO.*;

import com.example.computer.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ModelActivity extends Activity implements android.view.View.OnClickListener{
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
	public int i =0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
		//设置全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.next_1_3);
		db_Hleper = new DB_Helper(this);// new出sqlite
		arrayList = new ArrayList<Questions>();
		intent = getIntent();
		flag = intent.getIntExtra("flag", -1);

		arrayList = db_Hleper.queryAll();
		questions = new Questions[50];
		
		for(i=0;i<50;i++){
			int id = (int)(Math.random()*(arrayList.size()-1));
			questions[i] = db_Hleper.getQuestions(id);
//			for(int j = 0;j<i;j++){
//			questions[i] = db_Hleper.getQuestions(id);
////		    questions[j] = questions[i];
//		    questions[j] = db_Hleper.getQuestions(id);
//				if(questions[i].toString().equals(questions[j].toString()));
//				i--;
//			}
			
		}
//		for(i=0;i<50;i++){
//			Log.e("", questions[i].toString());
//		}
		QuestionsIndex = questions[0].get_id();
        init();
    }
	private void init() {
	

		tv1 = (TextView) findViewById(R.id.tv11);
		tv1.setText((i) +arrayList.get(QuestionsIndex).getQuestion());
		Log.e("", arrayList.get(QuestionsIndex).getQuestion());
		tv2 = (TextView) findViewById(R.id.tv12);
		tv2.setText(arrayList.get(QuestionsIndex).getOptionA());
		tv3 = (TextView) findViewById(R.id.tv13);
		tv3.setText(arrayList.get(QuestionsIndex).getOptionB());
		tv4 = (TextView) findViewById(R.id.tv14);
		if(arrayList.get(QuestionsIndex).getOptionC() .equals("null")){

		tv4.setVisibility(View.GONE);
		}else{
	    tv4.setText(arrayList.get(QuestionsIndex).getOptionC());

		}
		tv5 = (TextView) findViewById(R.id.tv15);
		if(arrayList.get(QuestionsIndex).getOptionD().equals("null")){
		

		}else{
		tv5.setText(arrayList.get(QuestionsIndex).getOptionD());

		}
		btn1 = (Button) findViewById(R.id.btn_6);
		btn2 = (Button) findViewById(R.id.btn_7);
		btn3 = (Button) findViewById(R.id.btn_8);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
	}

	public void onClick(View v) {
		if(v.getId() == R.id.tv12){
			answer = 1;
			Aanswer();
	        tv3.setOnClickListener(null);
	        tv4.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}		
		if(v.getId() == R.id.tv13){
			answer = 2;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv4.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}	  
		if(v.getId() == R.id.tv14){
			answer = 3;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv3.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}
		if(v.getId() == R.id.tv15){
			answer = 4;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv3.setOnClickListener(null);
	        tv4.setOnClickListener(null);
		}
		if(v.getId() == R.id.btn_6){
			if(i<49){
				i +=1;
				QuestionsIndex = questions[i].get_id();
				Log.e("", QuestionsIndex+"");
			}else{
				QuestionsIndex  = questions[49].get_id();
				Toast.makeText(ModelActivity.this, "已到最后一题", Toast.LENGTH_SHORT).show();
			}
          init();
		}
		if(v.getId() == R.id.btn_7){
			if(i>0){
				i -= 1;
				QuestionsIndex = questions[i].get_id() ;
				Log.e("", QuestionsIndex+"");
			}else{
				QuestionsIndex  = questions[0].get_id();
				Toast.makeText(ModelActivity.this, "已到最后一题", Toast.LENGTH_SHORT).show();
			}
			init();
		}
		if(v.getId() == R.id.btn_8){
			int m = count*2;
			if(m >= 90){
			Intent intent = new Intent(ModelActivity.this,PassActivity.class);
			startActivity(intent);
			}else{
				Intent intent = new Intent(ModelActivity.this,FailActivity.class);
				startActivity(intent);	
			}
		}
	}


	private void Aanswer() {
		// TODO Auto-generated method stub
	    	 Log.e("", ""+arrayList.get(QuestionsIndex).getAnswer());
				if(arrayList.get(QuestionsIndex).getAnswer() == answer){
					Toast.makeText(ModelActivity.this, "答案正确", Toast.LENGTH_SHORT).show();
					count +=1;
				}else{
					Toast.makeText(ModelActivity.this, "答案错误，正确答案是第"+arrayList.get(QuestionsIndex).getAnswer()
							+"个", Toast.LENGTH_SHORT).show();
					}
	}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//返回按键
    	// TODO Auto-generated method stub
    	
    	if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
            Intent intent = new Intent(ModelActivity.this,NextActivity.class);
            startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
    }

	}
