package com.example.duanmau_mob2041_ytdnph12917.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_mob2041_ytdnph12917.Dao.LoaiSachDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.LoaiSach;
import com.example.duanmau_mob2041_ytdnph12917.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachHoler> {
    Context context;
    List<LoaiSach> lvSach;
    LoaiSachDao loaiSachDao;

    public LoaiSachAdapter(Context context, List<LoaiSach> lvSach, LoaiSachDao loaiSachDao) {
        this.context = context;
        this.lvSach = lvSach;
        this.loaiSachDao = loaiSachDao;
    }

    @NonNull
    @NotNull
    @Override
    public LoaiSachHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_loaisach, parent, false);
        return new LoaiSachHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LoaiSachHoler holder, int position) {
        LoaiSach loaiSach = lvSach.get(position);
        if (loaiSach == null) {
            return;
        } else {
            holder.tv_ID_ls.setText("Mã Loại Sách: " + loaiSach.getMaLS() + "");
            holder.tv_ten_ls.setText("Tên Loại Sách:" + loaiSach.getTenLS());
        }
        holder.imgxoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xoá");
                builder.setIcon(R.drawable.racc);
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loaiSachDao = new LoaiSachDao(context);
                        long kq = loaiSachDao.DELETELS(loaiSach);
                        if (kq > 0) {
                            lvSach.clear();
                            lvSach.addAll(loaiSachDao.GETLS());
                            // load dữ liệu
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context.getApplicationContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context.getApplicationContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                        }
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
        holder.imgsuals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View view = layoutInflater.inflate(R.layout.custom_sua_loaisach, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(view);
                builder.setIcon(R.drawable.sua);
                builder.setTitle("Sửa Loại Sách");
                EditText ed_sua = (EditText) view.findViewById(R.id.ed_edit_ls);
                ed_sua.setText(loaiSach.getTenLS());
                builder.setCancelable(true);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (loaiSach.getTenLS().equals(ed_sua.getText().toString())) {
                            Toast.makeText(context.getApplicationContext(), "Không Có Gì Thay Đổi \n   Sửa Thất Bại!", Toast.LENGTH_SHORT).show();

                        } else {
                            loaiSachDao = new LoaiSachDao(context);
                            loaiSach.setTenLS(ed_sua.getText().toString());
                            long kq = loaiSachDao.UPDATELS(loaiSach);
                            if (kq > 0) {
                                lvSach.clear();
                                lvSach.addAll(loaiSachDao.GETLS());
                                notifyDataSetChanged();
                                ed_sua.setText("");
                                dialog.dismiss();
                                Toast.makeText(context.getApplicationContext(), "Sửa Thành Công!!!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context.getApplicationContext(), "Sửa Thất Bại!!!!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (lvSach != null) {
            return lvSach.size();
        }
        return 0;
    }

    public class LoaiSachHoler extends RecyclerView.ViewHolder {
        TextView tv_ID_ls, tv_ten_ls;
        ImageView imgxoals, imgsuals;
        public LoaiSachHoler(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_ID_ls = (TextView) itemView.findViewById(R.id.tv_id_loaisach);
            tv_ten_ls = (TextView) itemView.findViewById(R.id.tv_ten_loaisach);
            imgxoals = (ImageView) itemView.findViewById(R.id.img_xoa_ls);
            imgsuals = (ImageView) itemView.findViewById(R.id.img_sua_ls);
        }
    }
}
