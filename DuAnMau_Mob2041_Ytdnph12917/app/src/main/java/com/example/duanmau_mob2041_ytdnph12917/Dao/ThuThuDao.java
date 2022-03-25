package com.example.duanmau_mob2041_ytdnph12917.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_mob2041_ytdnph12917.DataBase.CreateDatabase;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDao {
    SQLiteDatabase sqlite;
    CreateDatabase createData;
    public ThuThuDao(Context context) {
        createData = new CreateDatabase(context);
        sqlite = createData.getWritableDatabase();
    }

    public void OPEN() {
        sqlite = createData.getWritableDatabase();
    }

    public void Close() {
        createData.close();
    }
    public long ADDTT(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put(ThuThu.COL_MATT, thuThu.getMaTT());
        values.put(ThuThu.COL_TENTT, thuThu.getHoTen());
        values.put(ThuThu.COL_MK, thuThu.getMatKhau());
        return sqlite.insert(ThuThu.TB_NAME, null, values);
    }

    public int UPDATE(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put(ThuThu.COL_MATT, thuThu.getMaTT());
        values.put(ThuThu.COL_TENTT, thuThu.getHoTen());
        values.put(ThuThu.COL_MK, thuThu.getMatKhau());
        return sqlite.update(ThuThu.TB_NAME, values, "maTT=?", new String[]{thuThu.getMaTT()});
    }

    public int Thaypass(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put(ThuThu.COL_MK, thuThu.getMatKhau());
        return sqlite.update(ThuThu.TB_NAME, values, "maTT=?", new String[]{thuThu.getMaTT()});
    }

    public int DELETE(String mTT) {
        return sqlite.delete(ThuThu.TB_NAME, "maTT=?", new String[]{mTT});
    }

    public ThuThu getId(String mTT) {
        String selectId = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getdata(selectId, mTT);
        return list.get(0);
    }

    public List<ThuThu> GETTT() {
        String select = "SELECT* FROM ThuThu";
        return getdata(select);
    }

    private List<ThuThu> getdata(String sql, String... selection) {
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery(sql, selection);
        while (cursor.moveToNext()) {
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(cursor.getString(cursor.getColumnIndex(ThuThu.COL_MATT)));
            thuThu.setHoTen(cursor.getString(cursor.getColumnIndex(ThuThu.COL_TENTT)));
            thuThu.setMatKhau(cursor.getString(cursor.getColumnIndex(ThuThu.COL_MK)));
            list.add(thuThu);
        }
        return list;
    }

    public int getlogin(String user, String pass) {
        String dl = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getdata(dl, user, pass);
        if (list.size() == 0) {
            return -1;
        } else {
            return 1;
        }
    }

}
