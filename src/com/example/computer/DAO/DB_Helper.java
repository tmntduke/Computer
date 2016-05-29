package com.example.computer.DAO;

import java.util.ArrayList;
import java.util.Timer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.computer.FeildUilts.Finallay;
import com.example.computer.Model.Questions;

public class DB_Helper {

	private static SQLiteDatabase db,db2;// 数据库
	private Context context;
	private ArrayList<Questions> arrayList = new ArrayList<Questions>();
    private ReleaseDataBase releasedatabase ;
	
	public DB_Helper(Context context) {
		this.context = context;
		releasedatabase= new ReleaseDataBase(context);
		releasedatabase.OpenDataBase();
		db = SQLiteDatabase.openOrCreateDatabase(Finallay.FILE_PATH, null);
	}

	// 开启连接
	public void openConn() {
		if (db == null) {


		}
	}

	// 关闭连接
	private void closeConn() {
		if (db != null) {
			db.close();
			db = null;
		}
	}

	

	

	/**
	 * 得到考题 id
	 * @param _id
	 * @return
	 */
	public Questions getQuestions(int _id) {
		if (db == null) {
			openConn();
		}
		String where = Finallay.T01_COLUMN_ID + "=" + _id;
		Cursor cursor = db.query(Finallay.TABLE_NAME, null, where, null, null,
				null, null);
		Questions questions = null;
		if (cursor.moveToNext()) {
			questions = new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4), cursor.getString(5),
					cursor.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9));
		}
		cursor.close();
		closeConn();
		return questions;
	}

	

	/**
	 * 恶劣天安全气行驶试题
	 * @return
	 */
	public ArrayList<Questions> queryBadWeather() {
		if (null == db) {
			openConn();
		}
		String where = "question like " + "'%雨%'";
		Cursor cursor = db.query(Finallay.TABLE_NAME, new String[] { "_id",
				"q_type", "question", "optionA", "optionB", "optionC",
				"optionD", "answer", "mexam_type", "image" }, where, null,
				null, null, null);
		while (cursor.moveToNext()) {
			arrayList.add(new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9)));
		}
		cursor.close();
		closeConn();
		return arrayList;
	}

	/**
	 * 车辆安全行驶试题
	 * @return
	 */
	public ArrayList<Questions> queryCheLinagAnQuan() {
		if (null == db) {
			openConn();
		}
		String where = "question like " + "'驾驶车辆%'";
		Cursor cursor = db.query(Finallay.TABLE_NAME, new String[] { "_id",
				"q_type", "question", "optionA", "optionB", "optionC",
				"optionD", "answer", "mexam_type", "image" }, where, null,
				null, null, null);
		while (cursor.moveToNext()) {
			arrayList.add(new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9)));
		}
		cursor.close();
		closeConn();
		return arrayList;
	}

	
	
	/**
	 * 行车之中遇到突发事件试题
	 * @return
	 */
	public ArrayList<Questions> queryXingCheZhiZhong() {
		if (null == db) {
			openConn();
		}
		String where = "question like " + "'在%'";
		Cursor cursor = db.query(Finallay.TABLE_NAME, new String[] { "_id",
				"q_type", "question", "optionA", "optionB", "optionC",
				"optionD", "answer", "mexam_type", "image" }, where, null,
				null, null, null);
		while (cursor.moveToNext()) {
			arrayList.add(new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9)));
		}
		cursor.close();
		closeConn();
		return arrayList;
	}

	/**
	 * 夜间行车注意事项
	 * @return
	 */
	public ArrayList<Questions> queryYeJianXingChe() {
		if (null == db) {
			openConn();
		}
		String where = "question like " + "'夜间%'";
		Cursor cursor = db.query(Finallay.TABLE_NAME, new String[] { "_id",
				"q_type", "question", "optionA", "optionB", "optionC",
				"optionD", "answer", "mexam_type", "image" }, where, null,
				null, null, null);
		while (cursor.moveToNext()) {
			arrayList.add(new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9)));
		}
		cursor.close();
		closeConn();
		return arrayList;
	}

	/**
	 * 在特殊道路上安全行驶试题
	 * @return
	 */
	public ArrayList<Questions> queryXingCheZhong() {
		if (null == db) {
			openConn();
		}
		String where = "question like " + "'行车中%'";
		Cursor cursor = db.query(Finallay.TABLE_NAME, new String[] { "_id",
				"q_type", "question", "optionA", "optionB", "optionC",
				"optionD", "answer", "mexam_type", "image" }, where, null,
				null, null, null);
		while (cursor.moveToNext()) {
			arrayList.add(new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9)));
		}
		cursor.close();
		closeConn();
		return arrayList;
	}

	/**
	 * 自动挡
	 * @return
	 */
	public ArrayList<Questions> queryZiDongDang() {
		if (null == db) {
			openConn();
		}
		String where = "question like " + "'自动挡%'";
		Cursor cursor = db.query(Finallay.TABLE_NAME, new String[] { "_id",
				"q_type", "question", "optionA", "optionB", "optionC",
				"optionD", "answer", "mexam_type", "image" }, where, null,
				null, null, null);
		while (cursor.moveToNext()) {
			arrayList.add(new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9)));
		}
		cursor.close();
		closeConn();
		return arrayList;
	}

	/**
	 * 机动车
	 * @return
	 */
	public ArrayList<Questions> queryCar() {
		if (null == db) {
			openConn();
		}
		String where = "question like " + "'机动%'";
		Cursor cursor = db.query(Finallay.TABLE_NAME, new String[] { "_id",
				"q_type", "question", "optionA", "optionB", "optionC",
				"optionD", "answer", "mexam_type", "image" }, where, null,
				null, null, null);
		while (cursor.moveToNext()) {
			arrayList.add(new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9)));
		}
		cursor.close();
		closeConn();
		return arrayList;
	}

	/**
	 * 全部考题
	 * @return
	 */
	public ArrayList<Questions> queryAll() {
		ArrayList<Questions> arrayList = new ArrayList<Questions>();
		if (db == null) {
			openConn();
		}
		Cursor cursor = db.query(Finallay.TABLE_NAME, null, null, null, null,
				null, null);
		while (cursor.moveToNext()) {
			arrayList.add(new Questions(cursor.getInt(0), cursor.getInt(1),
					cursor.getString(2), cursor.getString(3), cursor
							.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getInt(7), cursor.getInt(8),
					cursor.getBlob(9)));
		}
		cursor.close();
		closeConn();
		return arrayList;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @param password
	 */
	public void add(String user,String password){

        db2=releasedatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",user);
        values.put("password",password);
        db2.insert("T_User","uid",values);
    }
	
	/**
	 * 查找用户
	 * @param user
	 * @param password
	 * @return
	 */
    public boolean query(String user,String password){
        db2=releasedatabase.getReadableDatabase();
        Cursor cursor= db2.query("T_User", new String[]{"uid"}, "username=? and password=?", new String[]{user, password}, null, null, null);
        if (cursor.moveToNext()){
            return true;
        }
        else {
            return false;
        }
    }



}
