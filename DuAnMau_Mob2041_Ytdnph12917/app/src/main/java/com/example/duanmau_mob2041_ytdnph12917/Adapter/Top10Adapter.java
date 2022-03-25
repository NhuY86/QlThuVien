package com.example.duanmau_mob2041_ytdnph12917.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau_mob2041_ytdnph12917.Model.Top10;
import com.example.duanmau_mob2041_ytdnph12917.R;

import java.util.ArrayList;

public class Top10Adapter extends ArrayAdapter<Top10> {
    private Context context;
    private ArrayList<Top10> lists;
    TextView tvSach,tvSoLuong;

    public Top10Adapter(@NonNull Context context, ArrayList<Top10> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top10 item = lists.get(position);
        if (item!=null){
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sach: "+item.tens);
            tvSoLuong = v.findViewById(R.id.tv_soLuong);
            tvSoLuong.setText("số Lượng: "+item.soLuong);
        }
        return v;
    }
}
