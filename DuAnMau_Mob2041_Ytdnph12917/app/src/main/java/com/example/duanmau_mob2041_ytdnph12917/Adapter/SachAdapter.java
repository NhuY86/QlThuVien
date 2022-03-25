package com.example.duanmau_mob2041_ytdnph12917.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_mob2041_ytdnph12917.Dao.LoaiSachDao;
import com.example.duanmau_mob2041_ytdnph12917.Dao.SachDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.LoaiSach;
import com.example.duanmau_mob2041_ytdnph12917.Model.Sach;
import com.example.duanmau_mob2041_ytdnph12917.R;
import com.example.duanmau_mob2041_ytdnph12917.Spiner.LoaiSachSpiner;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachHoler> {
    Context context;
    List<Sach> lvsach;
    SachDao dao;
    int ms, mst;
    ArrayList<LoaiSach> loaiSachArrayList;
    LoaiSachDao loaiSachDao;

    public SachAdapter(Context context, List<Sach> lvsach, SachDao sachDao) {
        this.context = context;
        this.lvsach = lvsach;
        this.dao = dao;
    }
    @NonNull
    @NotNull
    @Override
    public SachHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_sach, parent, false);
        return new SachHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SachHoler holder, int position) {
        Sach sach = lvsach.get(position);
        if (sach == null) {
            return;
        } else {
            String tenLoai;
            try {
                LoaiSachDao loaiSachDao = new LoaiSachDao(context);
                LoaiSach loaiSach = loaiSachDao.getId(String.valueOf(sach.getMals()));
                tenLoai = loaiSach.getTenLS();
            } catch (Exception e) {
                tenLoai = "Đã xóa loại sách";
            }

            holder.tv_ma_sach.setText("Mã Sách: " + sach.getMas() + "");
            holder.tv_ma_loai_sach.setText("Loại Sách: " + tenLoai);
            holder.tv_ten_sach.setText("Tên Sách: " + sach.getTens());
            holder.tv_km.setText("Giá Km: "+ sach.getKm());
            Locale locale = new Locale("nv", "VN");
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
            String tien = numberFormat.format(sach.getGias());
            holder.tv_gia_sach.setText("Giá Sách: " + tien);
        }
        holder.img_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xoá");
                builder.setIcon(R.drawable.racc);
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setCancelable(true);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao = new SachDao(context);
                        long kq = dao.DELETES(sach);
                        if (kq > 0) {
                            lvsach.clear();
                            lvsach.addAll(dao.GETS());
                            Toast.makeText(context.getApplicationContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            dialog.cancel();
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
                View view = inflater.inflate(R.layout.custom_sua_sach, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(view);
                builder.setIcon(R.drawable.sua);
                builder.setTitle("Sửa Sách");
                EditText ed_ten_sach = (EditText) view.findViewById(R.id.ten_sach);
                Spinner spin_sach = (Spinner) view.findViewById(R.id.spin_lsach);
                EditText ed_gia_sach = (EditText) view.findViewById(R.id.giasach);
                EditText ed_km = (EditText)view.findViewById(R.id.sua_km) ;
                ed_ten_sach.setText(sach.getTens());
                ed_km.setText(sach.getKm());
                ed_gia_sach.setText(Integer.toString(sach.getGias()));
                loaiSachArrayList = new ArrayList<>();
                loaiSachDao = new LoaiSachDao(view.getContext());
                loaiSachArrayList = (ArrayList<LoaiSach>) loaiSachDao.GETLS();
                LoaiSachSpiner spiner = new LoaiSachSpiner(view.getContext(), loaiSachArrayList);
                spin_sach.setAdapter(spiner);
                spin_sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ms = loaiSachArrayList.get(position).getMaLS();
                        Toast.makeText(view.getContext(), "Chọn: " + loaiSachArrayList.get(position).getTenLS(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                mst = 0;
                for (int i = 0; i < loaiSachArrayList.size(); i++) {
                    if (sach.getMals() == loaiSachArrayList.get(i).getMaLS()) {
                        mst = i;
                    }
                }
                spin_sach.setSelection(mst);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (sach.getTens().equals(ed_ten_sach.getText().toString()) && sach.getGias() == Integer.parseInt(ed_gia_sach.getText().toString())
                                && sach.getMals() == mst) {
                            Toast.makeText(context.getApplicationContext(), "Không Có Gì Thay Đổi \n   Sửa Thất Bại!", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                int giaThue = Integer.parseInt(ed_gia_sach.getText().toString());
                                dao = new SachDao(context);
                                sach.setTens(ed_ten_sach.getText().toString());
                                sach.setGias(giaThue);
                                sach.setMals(ms);
                                long kq = dao.UPDATES(sach);
                                if (kq > 0) {
                                    lvsach.clear();
                                    lvsach.addAll(dao.GETS());
                                    Toast.makeText(view.getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                                    ed_ten_sach.setText("");
                                    ed_gia_sach.setText("");
                                    spin_sach.setSelection(0);
                                    notifyDataSetChanged();
                                    dialog.cancel();
                                } else {
                                    Toast.makeText(view.getContext(), "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Toast.makeText(view.getContext(), "Giá thuê phải là số", Toast.LENGTH_SHORT).show();
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
        if (lvsach != null) {
            return lvsach.size();
        }
        return 0;
    }

    public class SachHoler extends RecyclerView.ViewHolder {
        TextView tv_ma_sach, tv_ma_loai_sach, tv_ten_sach, tv_gia_sach,tv_km;
        ImageView img_sua, img_xoa;
        public SachHoler(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_ma_sach = itemView.findViewById(R.id.tv_ma_sach);
            tv_ma_loai_sach = itemView.findViewById(R.id.tv_ma_loai_s);
            tv_ten_sach = itemView.findViewById(R.id.tv_ten_sach);
            tv_gia_sach = itemView.findViewById(R.id.tv_gia_sach);
            img_xoa = itemView.findViewById(R.id.img_xoa_sach);
            img_sua = itemView.findViewById(R.id.img_sua_sach);
            tv_km = itemView.findViewById(R.id.tv_gia_km);
        }
    }
}
