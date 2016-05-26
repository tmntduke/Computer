package com.example.computer.Action;

import java.util.ArrayList;

import com.example.computer.Model.Questions;
import com.example.computer.DAO.DB_Helper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.computer.R;

public class AnswerActivity extends Activity{
	private TextView textView;
	public int QuestionsIndex;
	public Intent intent;
	public static DB_Helper db_Hleper;// ?????
	public static ArrayList<Questions> arrayList;// ???п???
	public int answer;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        instance = this;
//		//???????
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.answerview);
		db_Hleper = new DB_Helper(this);// new??sqlite
		arrayList = new ArrayList<Questions>();
		intent = getIntent();
		QuestionsIndex = intent.getIntExtra("QuestionsIndex", -1);
        Log.e("", QuestionsIndex+"");
		arrayList = db_Hleper.queryAll();
		init();
    }
	private void init() {
		// TODO Auto-generated method stub
		textView = (TextView) findViewById(R.id.textView1);
		answer = arrayList.get(QuestionsIndex).getAnswer();
		switch(answer){
		case 1:
			textView.setText(arrayList.get(QuestionsIndex).getOptionA());
			break;
		case 2:
			textView.setText(arrayList.get(QuestionsIndex).getOptionB());
			break;
		case 3:
			textView.setText(arrayList.get(QuestionsIndex).getOptionC());
			break;
		case 4:
			textView.setText(arrayList.get(QuestionsIndex).getOptionD());
			break;
		}
	}
}
