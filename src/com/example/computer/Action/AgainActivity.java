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

/**
 * 
 * @author tmnt
 *
 */
public class AgainActivity extends Activity implements android.view.View.OnClickListener{
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
	public int i =0;
	public List<Integer> list;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
		//设置全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.next_1_5);
		db_Hleper = new DB_Helper(this);// new出sqlite
		arrayList = new ArrayList<Questions>();
		intent = getIntent();
		flag = intent.getIntExtra("flag", -1);

		arrayList = db_Hleper.queryAll();
		list = readIndex();
		Log.e("", list.size()+"长度");

		QuestionsIndex = list.get(0);
		init();
		}
	private void init() {
	
		imageView = (ImageView) findViewById(R.id.order_practice_imageview_tu5);
		if (null != arrayList.get(QuestionsIndex).getBlob()) {// 如果有图片
			
			imageView.setVisibility(0);
			Bitmap bitmap = BitmapFactory.decodeByteArray(
					arrayList.get(QuestionsIndex).getBlob(), 0,
					arrayList.get(QuestionsIndex).getBlob().length);
			
			imageView.setImageBitmap(bitmap);
		} else {
			imageView.setVisibility(8);// 如果没有则控件隐藏
		}
		tv1 = (TextView) findViewById(R.id.tv17);
		tv1.setText(QuestionsIndex+arrayList.get(QuestionsIndex).getQuestion());
		Log.e("", arrayList.get(QuestionsIndex).getQuestion());
		tv2 = (TextView) findViewById(R.id.tv18);
		tv2.setText(arrayList.get(QuestionsIndex).getOptionA());
		tv3 = (TextView) findViewById(R.id.tv19);
		tv3.setText(arrayList.get(QuestionsIndex).getOptionB());
		tv4 = (TextView) findViewById(R.id.tv20);
		if(arrayList.get(QuestionsIndex).getOptionC() .equals("null")){

		tv4.setVisibility(8);
		}else{
	    tv4.setText(arrayList.get(QuestionsIndex).getOptionC());
	    tv4.setVisibility(0);
		}
		tv5 = (TextView) findViewById(R.id.tv21);
		if(arrayList.get(QuestionsIndex).getOptionD().equals("null")){
		
		tv5.setVisibility(8);
		}else{
		tv5.setText(arrayList.get(QuestionsIndex).getOptionD());
		tv5.setVisibility(0);
		}
		btn1 = (Button) findViewById(R.id.btn_14);
		btn2 = (Button) findViewById(R.id.btn_15);

        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);

	}

	public void onClick(View v) {
		if(v.getId() == R.id.tv18){
			answer = 1;
			Aanswer();
	        tv3.setOnClickListener(null);
	        tv4.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}		
		if(v.getId() == R.id.tv19){
			answer = 2;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv4.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}	  
		if(v.getId() == R.id.tv20){
			answer = 3;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv3.setOnClickListener(null);
	        tv5.setOnClickListener(null);
		}
		if(v.getId() == R.id.tv21){
			answer = 4;
			Aanswer();
	        tv2.setOnClickListener(null);
	        tv3.setOnClickListener(null);
	        tv4.setOnClickListener(null);
		}
		if(v.getId() == R.id.btn_14){
			if(i>0){
				i -=1;
				QuestionsIndex = list.get(i);
				Log.e("", QuestionsIndex+"");
			}else{
				QuestionsIndex  = list.get(0);
				
				showToast("已到最后一题");
			}
          init();
		}
		if(v.getId() == R.id.btn_15){
			if(i<list.size()-1){
				i += 1;
				QuestionsIndex = list.get(i);
				Log.e("", QuestionsIndex+"");
			}else{
				QuestionsIndex  = list.get(list.size()-1);
				
				showToast("已到最后一题");
			}
			init();
		}
	}
	private void Aanswer() {
		// TODO Auto-generated method stub
	    	 Log.e("", ""+arrayList.get(QuestionsIndex).getAnswer());
				if(arrayList.get(QuestionsIndex).getAnswer() == answer){
					
					showToast("答案正确");
					count +=1;
				}else{
					
					showToast("答案错误，正确答案是第"+arrayList.get(QuestionsIndex).getAnswer()
							+"个");
					}
	}

public void showToast(String string){
	Toast.makeText(AgainActivity.this, string, Toast.LENGTH_LONG).show();
}

	public List<Integer> readIndex()
	{
		List<Integer> list=new ArrayList<Integer>();
		int index;
		File file=new File(RangeActivity.FILENAME);
		Scanner scan=null;
		try {
			scan=new Scanner(new FileInputStream(file));
			while(scan.hasNext())
			{
				index=scan.nextInt();
				list.add(index);
			}
			
			scan.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//返回按键
    	// TODO Auto-generated method stub
    	
    	if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
            Intent intent = new Intent(AgainActivity.this,NextActivity.class);
            startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
    }

}
