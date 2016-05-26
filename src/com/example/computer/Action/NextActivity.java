package com.example.computer.Action;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.computer.Adapter.MainAdapter;
import com.example.computer.MainActivity;
import com.example.computer.R;
import com.example.computer.DAO.ReleaseDataBase;
import com.example.computer.Model.Questions;

/**
 *进入答题主界面
 * @author tmnt
 *
 */
public class NextActivity extends Activity {
    /** Called when the activity is first created. */
	
	private ImageButton icon_about;
	private ListView UIlist;
	private MainAdapter adapter;
	
	private int [] logo = { R.drawable.icon_shunxu, R.drawable.icon_suiji,
			R.drawable.icon_kaoshi,R.drawable.icon_cuowu,
			R.drawable.icon_gengxin};
	private String name[] = { "顺序练习", "随机练习", "模拟考试","待加强","分类练习"};
	private String introduce[] = { "按照题目顺序进行练习", "按照题目随机进行练习",
			"按照题目模拟真实进行联系","填出空缺的部分进行练习", "加强题集","按照题目类型进行练习"};
	private Intent intent;
	
	public static ArrayList<Questions> addArrayList;
	public static ArrayList<Questions> wrongArrayList;
	public static Handler handler;
	public static boolean con = false;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);
        
        ReleaseDataBase base = new ReleaseDataBase(this);// 实例化ReleaseDataBase类
		base.OpenDataBase();// 将数据库读到sd卡上
		
        icon_about = (ImageButton) findViewById(R.id.icon_about);
        UIlist = (ListView) findViewById(R.id.activity_listview);
        
        adapter = new MainAdapter(this, logo, name, introduce);
    	this.UIlist.setAdapter(adapter);//配置适配器
    	
    	addArrayList = new ArrayList<Questions>();
		wrongArrayList = new ArrayList<Questions>();
        
        icon_about.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				intent = new Intent(NextActivity.this,About_Activity.class);//跳转到详情界面
				startActivity(intent);
			}
		});  
        
        UIlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
             				turnCash(position);
			}
		} );
    } 
    
    public void turnCash(int flag){
    	Intent intent = new Intent(NextActivity.this,CashActivity.class);
    	intent.putExtra("flag", flag);
		startActivity(intent);
		finish();
    }
    

}