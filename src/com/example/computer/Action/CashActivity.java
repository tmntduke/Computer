package com.example.computer.Action;

import java.util.ArrayList;

import com.example.computer.Action.*;

import com.example.computer.Model.Question;
import com.example.computer.Model.Questions;
import com.example.computer.DAO.DB_Helper;
import com.example.computer.Model.Psychological;
import com.example.computer.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.os.Handler;

/**
 * 预加载界面
 * @author tmnt
 *
 */
public class CashActivity extends Activity {


public static int getflag;// 传过来的值
public static DB_Helper db_Helper;// 数据库
public static ArrayList<Questions> arrayList;// 所有考题
public  Questions qwt;
public static ArrayList<Psychological> arrayListPsychological;
public static Handler handlerOrder;
public static int QuestionsIndex ;// 考题计数
public Intent intent;

    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
    		// TODO Auto-generated method stub
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_cash);
//    		this.getResources().openRawResource();
    		// 进入得到开启activity的flag , 根据值下载不同的数据
    		Intent getIntent = getIntent();
			getflag = getIntent.getIntExtra("flag", -1);// 取得  -1是标志位

			
			if (getflag != -1) { // 不同的页面上面显示不同的标题
				switch (getflag) {
				case 0:// 顺序练习		
					turnNext(OrderActivity.class, 0);
					break;
				case 1:
					turnNext(RangeActivity.class, 1);
					break;
				case 2:// 模拟考试
					turnNext(ModelActivity.class, 2);
					break;
				case 3:// 待加强
					turnNext(AgainActivity.class, 4);
					break;
				case 4://分类练习
					turnNext(SortActivity.class, 5);
	                break;
				}
			}
    	}

    	public void turnNext(Class class1,int flag){
    	  Intent intent = new Intent(CashActivity.this,class1);
			intent.putExtra("flag", flag);
            startActivity(intent);
			finish();
    	}
}
