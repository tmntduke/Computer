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
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import com.example.computer.Model.*;
import com.example.computer.DAO.*;

import com.example.computer.R;

public class FenleiActivity extends Activity implements  OnClickListener{
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
	public int answer;
	public static FenleiActivity instance;
	public int flag1;
	public static final String FILENAME="/mnt/sdcard/enhance/enhace.txt";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
		//设置全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fenlei_1);
		db_Hleper = new DB_Helper(this);// new出sqlite
		arrayList = new ArrayList<Questions>();
		intent = getIntent();
		flag1 = intent.getIntExtra("flag1", -1);
		if(flag1 != -1){
			switch(flag1){
			case 6:
				arrayList = db_Hleper.queryAll();
				break;
			case 7:
				arrayList = db_Hleper.queryCar();
				break;
			case 8:
				arrayList = db_Hleper.queryZiDongDang();
				break;
			case 9:
				arrayList = db_Hleper.queryXingCheZhong();
				break;
			case 10:
				arrayList = db_Hleper.queryYeJianXingChe();
				break;
			case 11:
				arrayList = db_Hleper.queryXingCheZhiZhong();
				break;
			case 12:
				arrayList = db_Hleper.queryBadWeather();
				
				break;
			case 13:
				arrayList = db_Hleper.queryCheLinagAnQuan();
				break;
			}
		}
			
        
	    QuestionsIndex = arrayList.size()-1;
	    Log.e("", arrayList.size()+"");
        init();
    }

	private void init() {
		
		imageView = (ImageView) findViewById(R.id.order_practice_imageview_tu11);
		if (null != arrayList.get(QuestionsIndex).getBlob()) {// 如果有图片
			
			imageView.setVisibility(0);
			Bitmap bitmap = BitmapFactory.decodeByteArray(
					arrayList.get(QuestionsIndex).getBlob(), 0,
					arrayList.get(QuestionsIndex).getBlob().length);
			System.out.println(bitmap);
			imageView.setImageBitmap(bitmap);
		} else {
			imageView.setVisibility(8);// 如果没有则控件隐藏
		}
		tv1 = (TextView) findViewById(R.id.tv111);
		tv1.setText(QuestionsIndex+arrayList.get(QuestionsIndex).getQuestion());
		Log.e("", arrayList.get(QuestionsIndex).getQuestion());
		tv2 = (TextView) findViewById(R.id.tv112);
		tv2.setText(arrayList.get(QuestionsIndex).getOptionA());
		tv3 = (TextView) findViewById(R.id.tv113);
		tv3.setText(arrayList.get(QuestionsIndex).getOptionB());
		tv4 = (TextView) findViewById(R.id.tv114);
		if(arrayList.get(QuestionsIndex).getOptionC() .equals("null")){

		tv4.setVisibility(8);
		}else{
	    tv4.setText(arrayList.get(QuestionsIndex).getOptionC());
	    tv4.setVisibility(0);
		}
		tv5 = (TextView) findViewById(R.id.tv115);
		if(arrayList.get(QuestionsIndex).getOptionD().equals("null")){
		
		tv5.setVisibility(8);
		}else{
		tv5.setText(arrayList.get(QuestionsIndex).getOptionD());
		tv5.setVisibility(0);
		}
		btn1 = (Button) findViewById(R.id.btn_111);
		btn2 = (Button) findViewById(R.id.btn_211);
		btn3 = (Button) findViewById(R.id.btn_311);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.tv112){
			answer = 1;
			Aanswer();
	        tv3.setOnClickListener(null);
	        tv4.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}		
		if(v.getId() == R.id.tv113){
			answer = 2;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv4.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}	  
		if(v.getId() == R.id.tv114){
			answer = 3;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv3.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}
		if(v.getId() == R.id.tv115){
			answer = 4;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv3.setOnClickListener(null);
	        tv4.setOnClickListener(null);
		}
		if(v.getId() == R.id.btn_111){
			if(QuestionsIndex < arrayList.size()-1){
				QuestionsIndex += 1;
				Log.e("", QuestionsIndex+"");
			}else{
				QuestionsIndex  = arrayList.size()-1;
				Toast.makeText(FenleiActivity.this, "已到最后一题", 200).show();
			}
          init();
		}
		if(v.getId() == R.id.btn_211){
			if(QuestionsIndex >0){
				QuestionsIndex -= 1;
				Log.e("", QuestionsIndex+"");
			}else{
				QuestionsIndex  = 0;
				Toast.makeText(FenleiActivity.this, "已到最后一题", Toast.LENGTH_SHORT).show();
			}
			init();
		}
		if(v.getId() == R.id.btn_311){
			File file=new File(FILENAME);
			if(!file.getParentFile().exists())
			{
				file.getParentFile().mkdirs();
			}
			PrintStream output=null;
			try{
			output=new PrintStream(new FileOutputStream(file,true));
			output.println(QuestionsIndex+"\r\n");
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally
			{
				try {
					if(output!=null)
					{
					output.close();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	private void Aanswer() {
		// TODO Auto-generated method stub
	    	 Log.e("", ""+arrayList.get(QuestionsIndex).getAnswer());
				if(arrayList.get(QuestionsIndex).getAnswer() == answer){
					Toast.makeText(FenleiActivity.this, "答案正确", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(FenleiActivity.this, "答案错误，正确答案是第"+arrayList.get(QuestionsIndex).getAnswer()
							+"个", Toast.LENGTH_SHORT).show();
					}
	}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//返回按键
    	// TODO Auto-generated method stub
    	
    	if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
            Intent intent = new Intent(FenleiActivity.this,NextActivity.class);
            startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
    }


}

