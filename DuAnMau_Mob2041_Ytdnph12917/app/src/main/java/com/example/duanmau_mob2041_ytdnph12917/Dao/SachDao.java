package com.example.duanmau_mob2041_ytdnph12917.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_mob2041_ytdnph12917.DataBase.CreateDatabase;
import com.example.duanmau_mob2041_ytdnph12917.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    SQLiteDatabase sqlite;
    CreateDatabase createData;
    public SachDao(Context context) {
        createData = new CreateDatabase(context);
        sqlite = createData.getWritableDatabase();
    }
    public long ADDS(Sach sach) {
        ContentValues values = new ContentValues();
        values.put(Sach.COL_NAME_MAL_SACH, sach.getMals());
        values.put(Sach.COL_NAME_TEN_SACH, sach.getTens());
        values.put(Sach.COL_NAME_GIA_SACH, sach.getGias());
        values.put(Sach.COL_TB_KM, sach.getKm());
        return sqlite.insert(Sach.TB_NAME, null, values);
    }

    public int DELETES(Sach sach) {
        return sqlite.delete(Sach.TB_NAME, "maSach=?", new String[]{String.valueOf(sach.getMas())});
    }

    public int UPDATES(Sach sach) {
        ContentValues values = new ContentValues();
        values.put(Sach.COL_NAME_MAL_SACH, sach.getMals());
        values.put(Sach.COL_NAME_TEN_SACH, sach.getTens());
        values.put(Sach.COL_NAME_GIA_SACH, sach.getGias());
        values.put(Sach.COL_TB_KM, sach.getKm());
        return sqlite.update(Sach.TB_NAME, values, "maSach=?", new String[]{String.valueOf(sach.getMas())});
    }

    public List<Sach> GETS() {
        String dl = "SELECT * FROM Sach";
        List<Sach> list = getdata(dl);
        return list;
    }

    public Sach getId(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getdata(sql, id);
        return list.get(0);
    }

    private List<Sach> getdata(String dl, String... Arays) {
        List<Sach> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            Sach sach = new Sach();
            sach.setMas(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Sach.COL_NAME_MA_SACH))));
            sach.setMals(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Sach.COL_NAME_MAL_SACH))));
            sach.setTens(cursor.getString(cursor.getColumnIndex(Sach.COL_NAME_TEN_SACH)));
            sach.setKm(cursor.getString(cursor.getColumnIndex(Sach.COL_TB_KM)));
            sach.setGias(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Sach.COL_NAME_GIA_SACH))));
            list.add(sach);
        }
        return list;
    }
}
