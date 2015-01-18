package com.wxh.project.baiduNuomi.db;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	// ���ݿ������
	private static final String DB_NAME = "database.db";
	// ���ݿ�İ汾
	private static final int DB_VERSION = 2;
	
	// ʵ�ʲ������ݿ�Ķ���
	private SQLiteDatabase db;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		db = getWritableDatabase();
	}

	/**
	 * �������ݿ��
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// ɾ����
		String drop = "DROP TABLE IF EXISTS table_tuan";
		db.execSQL(drop);
		
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS table_tuan (       ");
		sql.append("       _id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append("       deal_id TEXT,                          ");
		sql.append("       image TEXT,                            ");
		sql.append("       brand_name TEXT,                       ");
		sql.append("       short_title TEXT,                      ");
		sql.append("       sale_count INTEGER,                    ");
		sql.append("       groupon_price INTEGER,                 ");
		sql.append("       market_price INTEGER                   ");
		sql.append(")                                             ");
		
		// ִ��SQL���
		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// �°汾�Ŵ��ھɰ汾�����ٴε���onCreate����
		if (newVersion > oldVersion) {
			onCreate(db);
		}
	}
	
	/**
	 * ִ��SQL��䣬��ִ�в��롢ɾ�����͸��²���
	 * @param sql SQL���
	 * @param bindArgs ���SQL�������ռλ�����������ò��������滻��������ֵ
	 */
	public void executeSQL(String sql, Object... bindArgs) {
		db.execSQL(sql, bindArgs);
	}
	
	/**
	 * ִ�����ݿ��ѯ����
	 * @param sql SQL���
	 * @param selectionArgs ���SQL�������ռλ�����������ò��������滻��������ֵ
	 * @return Cursor
	 */
	public Cursor executeQuery(String sql, String... selectionArgs) {
		return db.rawQuery(sql, selectionArgs);
	}

}

