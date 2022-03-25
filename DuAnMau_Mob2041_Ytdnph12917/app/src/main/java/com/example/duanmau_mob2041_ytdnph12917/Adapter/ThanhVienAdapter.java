package com.example.duanmau_mob2041_ytdnph12917.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_mob2041_ytdnph12917.Dao.ThanhVienDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThanhVien;
import com.example.duanmau_mob2041_ytdnph12917.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienHoler> {
    Context context;
    List<ThanhVien> lvThanhVien;
    ThanhVienDao thanhVienDao;
    public ThanhVienAdapter(Context context, List<ThanhVien> lvThanhVien) {
        this.context = context;
        this.lvThanhVien = lvThanhVien;
    }
    @NonNull
    @NotNull
    @Override
    public ThanhVienHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_thanhvien, parent, false);

        return new ThanhVienHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ThanhVienHoler holder, int position) {
        ThanhVien thanhVien = lvThanhVien.get(position);
        if (thanhVien == null) {
            return;
        } else {
            holder.tv_maTV.setText("Mã TV: " + thanhVien.getIDTV() + "");
            holder.tv_hoten.setText("Họ Và Tên: " + thanhVien.getHoTenTV());
            holder.tv_namsinh.setText("Năm Sinh: " + thanhVien.getNamsinhTV());
        }
        holder.img_xoa.setOnClickListener(new View.OnClickListener() {
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
                        thanhVienDao = new ThanhVienDao(context);
                        int kq = thanhVienDao.DELETETV(thanhVien);
                        if (kq > 0) {
                            lvThanhVien.clear();
                            lvThanhVien.addAll(thanhVienDao.GETTV());
                            // load lại dữ liệu
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
        holder.img_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.custom_sua_thanh_vien, null);
                AlertDialog dialog = new AlertDialog.Builder(view.getContext()).create();
                dialog.setView(view);
                dialog.setIcon(R.drawable.ruler);
                dialog.setTitle("         Sửa Thành Viên ");
                EditText ed_edhotentv = view.findViewById(R.id.ed_hoten_sua_tv);
                EditText ed_namsinhtv = view.findViewById(R.id.ed_date_sua_tv);
                ed_edhotentv.setText(thanhVien.getHoTenTV());
                ed_namsinhtv.setText(thanhVien.getNamsinhTV());
                AppCompatButton btn_save = view.findViewById(R.id.btn_them_sua_tv);
                AppCompatButton btn_huy = view.findViewById(R.id.btn_huy_sua_tv);
                dialog.setCancelable(true);
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (thanhVien.getHoTenTV().equals(ed_edhotentv.getText().toString()) && thanhVien.getNamsinhTV().equals(ed_namsinhtv.getText().toString())) {
                            Toast.makeText(context.getApplicationContext(), "Không Có Gì Thay Đổi \n Sửa Thất Bại!", Toast.LENGTH_SHORT).show();
                        } else {
                            thanhVienDao = new ThanhVienDao(context);
                            thanhVien.setHoTenTV(ed_edhotentv.getText().toString());
                            thanhVien.setNamsinhTV(ed_namsinhtv.getText().toString());
                            long kq = thanhVienDao.UPDATETV(thanhVien);
                            if (kq > 0) {
                                lvThanhVien.clear();
                                lvThanhVien.addAll(thanhVienDao.GETTV());
                                notifyDataSetChanged();
                                ed_edhotentv.setText("");
                                ed_namsinhtv.setText("");
                                dialog.dismiss();
                                Toast.makeText(context.getApplicationContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context.getApplicationContext(), "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        if(position== 0||position%2==0 ){
            holder.tv_maTV.setTextColor(Color.RED);
            holder.tv_hoten.setTextColor(Color.RED);
            holder.tv_namsinh.setTextColor(Color.RED);

        }
        else {
            holder.tv_maTV.setTextColor(Color.GREEN);
            holder.tv_hoten.setTextColor(Color.GREEN);
            holder.tv_namsinh.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        if (lvThanhVien != null) {
            return lvThanhVien.size();
        }
        return 0;
    }

    public class ThanhVienHoler extends RecyclerView.ViewHolder {
        TextView tv_maTV, tv_hoten, tv_namsinh;
        ImageView img_sua, img_xoa;
        public ThanhVienHoler(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_maTV = itemView.findViewById(R.id.tv_id_thanh_vien);
            tv_hoten = itemView.findViewById(R.id.tv_ho_ten_tv);
            tv_namsinh = itemView.findViewById(R.id.tv_nam_sinhtv);
            img_sua = itemView.findViewById(R.id.img_sua_tv);
            img_xoa = itemView.findViewById(R.id.img_xoa_tv);
        }
    }
}
