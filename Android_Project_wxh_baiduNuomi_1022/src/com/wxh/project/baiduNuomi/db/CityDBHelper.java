package com.wxh.project.baiduNuomi.db;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CityDBHelper extends SQLiteOpenHelper {
	// 数据库的名称
	private static final String DB_NAME = "nuomi.db";
	// 数据库的版本
	private static final int DB_VERSION = 2;
	
	// 实际操作数据库的对象
	private SQLiteDatabase db;

	public CityDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		db = getWritableDatabase();
	}

	/**
	 * 创建数据库表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String drop = "DROP TABLE IF EXISTS city";
		db.execSQL(drop);
		
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS city (       ");
		sql.append("       _id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append("       city_id TEXT,                          ");
		sql.append("       city_url TEXT,                            ");
		sql.append("       city_name TEXT,                       ");
		sql.append("       short_name TEXT                     ");
		sql.append(");                                             ");
		
		// 执行SQL语句
		db.execSQL(sql.toString());
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 新版本号大于旧版本号则再次调用onCreate方法
		if (newVersion > oldVersion) {
			onCreate(db);
		}
	}
	
	/**
	 * 执行SQL语句，可执行插入、删除、和更新操作
	 * @param sql SQL语句
	 * @param bindArgs 如果SQL语句中有占位符“？”，该参数用于替换“？”的值
	 */
	public void executeSQL(String sql, Object... bindArgs) {
		
		db.execSQL(sql, bindArgs);
	}
	
	/**
	 * 执行数据库查询操作
	 * @param sql SQL语句
	 * @param selectionArgs 如果SQL语句中有占位符“？”，该参数用于替换“？”的值
	 * @return Cursor
	 */
	public Cursor executeQuery(String sql, String... selectionArgs) {
		return db.rawQuery(sql, selectionArgs);
	}


}

