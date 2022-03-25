package com.example.duanmau_mob2041_ytdnph12917.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_mob2041_ytdnph12917.DataBase.CreateDatabase;
import com.example.duanmau_mob2041_ytdnph12917.Model.Sach;
import com.example.duanmau_mob2041_ytdnph12917.Model.Top10;

import java.util.ArrayList;
import java.util.List;

public class Top10Dao {
    SQLiteDatabase sqlite;
    CreateDatabase createData;
    private Context context;
    public Top10Dao(Context context) {
        this.context =context;
        createData = new CreateDatabase(context);
        sqlite = createData.getWritableDatabase();
    }
    public List<Top10>getTop(){
        String sqlTop ="SELECT maSach,count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top10> list = new ArrayList<>();
        SachDao sachDao = new SachDao(context);
        Cursor c = sqlite.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            Top10 top = new Top10();
            Sach sach = sachDao.getId(c.getString(c.getColumnIndex("maSach")));
            top.tens = sach.getTens();
            top.soLuong = Integer.parseInt(c.getString(c.getColumnIndex("soLuong")));
            list.add(top);
        }
        return list;
    }
}
