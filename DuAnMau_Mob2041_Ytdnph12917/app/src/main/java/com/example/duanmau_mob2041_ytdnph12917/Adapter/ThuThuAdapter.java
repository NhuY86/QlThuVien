package com.example.duanmau_mob2041_ytdnph12917.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_mob2041_ytdnph12917.Dao.ThuThuDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThuThu;
import com.example.duanmau_mob2041_ytdnph12917.R;

import java.util.List;

public class ThuThuAdapter extends BaseAdapter {
    private List<ThuThu> list;
    private Context context;
    private ThuThuDao thuThuDao;
    public ThuThuAdapter(List<ThuThu> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.custom_tai_khoan, null);

        } else {
            view = convertView;
        }
        ThuThu thuThu = list.get(position);
        TextView tv_user = (TextView) view.findViewById(R.id.tv_user_tt);
        TextView tv_pss = (TextView) view.findViewById(R.id.tv_pass_tt);
        TextView tv_acout = (TextView) view.findViewById(R.id.tv_id);
        tv_acout.setText("Họ và Tên: " + thuThu.getHoTen());
        tv_user.setText("USER: " + thuThu.getMaTT());
        tv_pss.setText("PASS: ******** " );
        ImageView img_dele = view.findViewById(R.id.img_delete);
        img_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete");
                builder.setIcon(R.drawable.racc);
                builder.setMessage("Bạn có chắc chắn muốn xoá không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        thuThuDao = new ThuThuDao(context);
                        thuThuDao.OPEN();
                        int kq = thuThuDao.DELETE(thuThu.getMaTT());
                        if (kq > 0) {
                            list.clear();
                            list.addAll(thuThuDao.GETTT());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Xoá Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                        thuThuDao.Close();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        return view;
    }
}
