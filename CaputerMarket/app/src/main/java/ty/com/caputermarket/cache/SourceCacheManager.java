package ty.com.caputermarket.cache;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Administrator on 2016/4/23.
 */
public class SourceCacheManager implements ISourceDatabaseImp

{
    private String mSourcePath = "/CaputerMarket";
    private String mImageSourceSuffix = ".dat";
    private Activity ac;
    private SQLiteDatabase db;


    //指定存储sd卡文件
    private String[] cacheFiles = {"file1", "file2", "file3", "file4"};

    public SourceCacheManager(Activity ac)
    {
        this.ac = ac;
        createCaputerDatabase();
    }

    /*
    创建数据库保存url（其实用文件存储会比较合适，量小，熟悉一遍数据库操作）
     */
    private void createCaputerDatabase()
    {
        db = ac.openOrCreateDatabase("caputer", Context.MODE_PRIVATE, null);
//        if(!exits("tbl_user"))
//        {
//            Log.i("tyty","create database");
//            db.execSQL("create table tbl_user(_id Integer primary key autoincrement,urlname varchar,link varchar)");
//        }
        // F NOT EXISTS 避免重复创建
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_user(_id Integer primary key autoincrement,urlname varchar,link varchar)");
        //备用手动删除 。。。

    }

    /*
    execSQL()方法可以执行insert、delete、update和CREATE TABLE之类有更改行为的SQL语句；
     */
    @Override
    public void addSource(Object object)
    {
        ContentValues contentValues  = new ContentValues();
        contentValues.put("urlname","yewen");
        contentValues.put("link","/data/data/pic.png");
        db.insert("tbl_user","urlname",contentValues);


    }

    @Override
    public void deleteSource(Object object)
    {

//        String temp = "SELECT * FROM tbl_user WHERE urlname = '"+object.toString()+"'";
//        Cursor cursor =  db.rawQuery(temp,null);
//        while (cursor.moveToNext())
//        {
//            int index = cursor.getInt(0);
//            String [] strs = {index+""};
//            Log.i("tyty","tyty id = "+index);
//            db.delete("tbl_user","_id =?",strs);
//        }
//        cursor.close();
        String [] strs = {"yewen"};
        db.delete("tbl_user","urlname =?",strs);
    }

    /*
    rawQuery()方法用于执行select语句。
     */
  @Override
    public Object findByURl(String url)
    {
            String temp = "SELECT * FROM tbl_user WHERE urlname = '"+url+"'";
       Cursor cursor =  db.rawQuery(temp,null);
        while (cursor.moveToNext())
        {
            int index = cursor.getInt(0);
            String urlname = cursor.getString(1);
            String link = cursor.getString(2);
            Log.i("tyty",link);
        }
        cursor.close();
        return "";
    }

    /**************************
     * 删除or增加sd卡缓存
     ********************************************/

    public void fileInSDCard()
    {

    }

    public void fileOutSDCard()
    {

    }

    private boolean exits(String table)
    {
        boolean exits = false;
        String sql = "select * from sqlite_master where name=" + "'" + table + "'";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() != 0)
        {
            exits = true;
        }
        return exits;
    }


}


