package com.example.computer.Action;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.computer.MainActivity;
import com.example.computer.R;
import com.example.computer.DAO.DB_Helper;
import com.example.computer.DAO.ReleaseDataBase;

import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends Activity {
    private Button zhuCe, tuiChu, fanHui;
    private EditText user, pass, sPass;
    private RadioGroup radioGroup;
    private CheckBox checkBox1, checkBox2, checkBox3;
    private RadioButton male, female;
    private List<String> list;
    private Spinner spinner;
    private CheckBox savle;
    private SharedPreferences per;
    public static String USER="user";
    public static String PASS="pass";
    private static final int RESULT_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        sPass = (EditText) findViewById(R.id.checkPass);
       
        zhuCe = (Button) findViewById(R.id.zhuce);
        zhuCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regex = "([a-zA-Z]((\\w+|\\d+)){2,10})";
                if (user.getText().toString().isEmpty() && pass.getText().toString().isEmpty() && sPass.getText().toString().isEmpty()) {
                    tiShi("用户名和密码不能为空");

                } else if (!(user.getText().toString().matches(regex) && pass.getText().toString().matches(regex))) {
                    tiShi("用户名和密码格式不正确");
                } else if (!sPass.getText().toString().equals(pass.getText().toString())) {
                    // tiShi("密码不一致");
                    sPass.setError("密码不一致");
                } 

                   
                    //tiShi(person.toString());
                    ReleaseDataBase releaseDataBase  = new ReleaseDataBase(getApplicationContext());
  
                    DB_Helper helper=new DB_Helper(getApplicationContext());
                    helper.openConn();
                    helper.add(user.getText().toString(), pass.getText().toString());
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
//                    intent.putExtra("", "注册成功");
//                    intent.putExtra("salve", true);
//                    per=getSharedPreferences("zhuCe",MODE_PRIVATE);
//                    SharedPreferences.Editor editor=per.edit();
//                    editor.putString(USER,user.getText().toString());
//                    editor.putString(PASS,pass.getText().toString());
//                    //editor.putBoolean("salve",true);
//                    editor.commit();
                    startActivity(intent);
                    finish();
                }


           
        });
        tuiChu = (Button) findViewById(R.id.exitMain);
        tuiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

      
           fanHui=(Button) findViewById(R.id.returnMain);
           fanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

       
      
        

      
    }

    public void tiShi(String string) {
        Toast.makeText(RegisterActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
