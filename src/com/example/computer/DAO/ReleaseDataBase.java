package com.example.computer.DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.example.computer.R;
import com.example.computer.FeildUilts.Finallay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class ReleaseDataBase extends SQLiteOpenHelper {
    private static final String NAME = "My.db";
    private static final int VERSION = 1;
    private Context context;

    /**
     * 创建数据库
     *
     * @param context
     */
    public ReleaseDataBase(Context context) {
        super(context, NAME, null, VERSION);
        // TODO Auto-generated constructor stub]\
        this.context = context;
    }


    private static final String TAG = "RelaseDataBase";


    /**
     * 把raw下的sq写进sd卡
     */
    public void OpenDataBase() {
        if (backEnvironment()) {// 判断sd卡是否挂载
            File file = new File(Finallay.FILE_PAPER_PATH);// 建立sd卡路径
            if (!file.exists()) {
                file.mkdirs();
                //System.out.println("sd卡路径创建文件夹");
                Log.i(TAG, "sd卡路径创建文件夹");
            } else {
                //System.out.println("sd已有");
                Log.i(TAG, "sd已有");
            }
            File filePath = new File(Finallay.FILE_PATH);// 建立sd卡下 的db文件
            if (!filePath.exists()) {
                InputStream in = context.getResources().openRawResource(
                        R.raw.examofcomputer);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            Finallay.FILE_PATH);// 得到输出流 文件夹下文件路径
                    byte[] buffer = new byte[8192];
                    int t = 0;
                    while ((t = in.read(buffer)) != -1) {// 半读边写
                        fileOutputStream.write(buffer, 0, t);
                    }
                    in.close();
                    fileOutputStream.close();
                    Log.e(TAG, "cpot");
                } catch (Exception e) {
                    //System.out.print("write error");
                    Log.i(TAG, "write error");
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(context, "您的sd卡没有挂载", Toast.LENGTH_LONG).show();
        }

    }

    public boolean backEnvironment() {// 判断sd卡是否挂载
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub

        arg0.execSQL("create table T_User (uid integer PRIMARY KEY AUTOINCREMENT, username varchar (10),password varchar (20))");
        arg0.execSQL("create table T_Wrong (wid integer primary key AUTOINCREMENT,question varchar(200),answerA varchar(25)," +
                "answerB varchar(25),answerC varchar(25),answerD varchar(25),answer integer,kind integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}
