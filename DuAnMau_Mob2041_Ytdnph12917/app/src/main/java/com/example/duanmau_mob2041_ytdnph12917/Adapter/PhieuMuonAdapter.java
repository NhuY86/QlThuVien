package com.example.duanmau_mob2041_ytdnph12917.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_mob2041_ytdnph12917.Dao.PhieuMuonDao;
import com.example.duanmau_mob2041_ytdnph12917.Dao.SachDao;
import com.example.duanmau_mob2041_ytdnph12917.Dao.ThanhVienDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.PhieuMuon;
import com.example.duanmau_mob2041_ytdnph12917.Model.Sach;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThanhVien;
import com.example.duanmau_mob2041_ytdnph12917.R;
import com.example.duanmau_mob2041_ytdnph12917.Spiner.SachSpiner;
import com.example.duanmau_mob2041_ytdnph12917.Spiner.ThanhVienSpiner;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonHoler> {
    Context context;
    List<PhieuMuon> lvPhieuMuon;
    PhieuMuonDao phieuMuonDao;
    SachSpiner sachSpiner;
    ThanhVienSpiner thanhVienSpiner;
    int maTV, maSach, Trasach, mst, mtvt;
    String maNV;
    ThanhVienDao thanhVienDao;
    ArrayList<ThanhVien> thanhVienArrayList;
    SachDao sachDao2;
    ArrayList<Sach> sachArrayList;

    public PhieuMuonAdapter(Context context, List<PhieuMuon> lvPhieuMuon) {
        this.context = context;
        this.lvPhieuMuon = lvPhieuMuon;
        phieuMuonDao = new PhieuMuonDao(context);
    }

    @NonNull
    @NotNull
    @Override
    public PhieuMuonHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_phieumuon, parent, false);
        return new PhieuMuonHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PhieuMuonHoler holder, int position) {
        PhieuMuon phieuMuon = lvPhieuMuon.get(position);
        if (phieuMuon == null) {
            return;
        }
        holder.tv_mapm.setText("Mã Phiếu: " + phieuMuon.getMaPM() + "");
        // lấy họ tên từ id
        ThanhVienDao tv_dao = new ThanhVienDao(context);
        ThanhVien tv = tv_dao.getId(phieuMuon.getMaTVpm() + "");
        holder.tv_matvpm.setText("Tên Thành Viên: " + tv.getHoTenTV() + "");
        // lấy tên sách
        SachDao sachDao = new SachDao(context);
        Sach sach = sachDao.getId(phieuMuon.getMaSpm() + "");
        holder.tv_maspm.setText("Tên Sách: " + sach.getTens() + "");
        Locale locale = new Locale("nv", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String tien = numberFormat.format(phieuMuon.getTienthue());
        holder.tv_giamuon.setText("Tiền Thuê: " + tien);
        holder.tv_ngaymuon.setText("Ngày Mượn: " + phieuMuon.getNgaymuon());
        if (phieuMuon.getTrasach() == 1) {
            holder.tv_trasach.setText("Đã Trả Sách");
            holder.tv_trasach.setTextColor(Color.BLUE);
        } else {
            holder.tv_trasach.setText("Chưa Trả Sách");
            holder.tv_trasach.setTextColor(Color.RED);
        }
        holder.img_xoa_pm.setOnClickListener(new View.OnClickListener() {
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

                        int kq = phieuMuonDao.DELETEPM(phieuMuon);
                        if (kq > 0) {
                            lvPhieuMuon.clear();
                            lvPhieuMuon.addAll(phieuMuonDao.GETPM());
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
        holder.img_sua_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.custom_sua_phieumuon, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(view);
                builder.setTitle("Sửa Phiếu Mượn");
                EditText ed_ngaymuoned = (EditText) view.findViewById(R.id.ed_ngaymuoned);
                EditText ed_tienthueed = (EditText) view.findViewById(R.id.ed_giamuoned);
                Spinner spn_tved = (Spinner) view.findViewById(R.id.spinnertved);
                Spinner spn_sached = (Spinner) view.findViewById(R.id.spinnersached);
                ImageView img_dateed = (ImageView) view.findViewById(R.id.img_liched);
                CheckBox chk_trasached = (CheckBox) view.findViewById(R.id.chk_sachtraed);
                ed_ngaymuoned.setText(phieuMuon.getNgaymuon());
                ed_tienthueed.setText(Integer.toString(phieuMuon.getTienthue()));
                // hiển thị
                if (phieuMuon.getTrasach() == 1) {
                    chk_trasached.setChecked(true);
                } else {
                    chk_trasached.setChecked(false);

                }
                thanhVienDao = new ThanhVienDao(view.getContext());
                thanhVienArrayList = new ArrayList<>();
                thanhVienArrayList = (ArrayList<ThanhVien>) thanhVienDao.GETTV();
                thanhVienSpiner = new ThanhVienSpiner(view.getContext(), thanhVienArrayList);
                spn_tved.setAdapter(thanhVienSpiner);
                Log.d("abs", phieuMuon.getMaPM() + "");
                spn_tved.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maTV = thanhVienArrayList.get(position).getIDTV();
                        Toast.makeText(view.getContext(), "Thành Viên: " + thanhVienArrayList.get(position).getHoTenTV(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // Spiner sach
                sachDao2 = new SachDao(view.getContext());
                sachArrayList = new ArrayList<>();
                sachArrayList = (ArrayList<Sach>) sachDao2.GETS();
                sachSpiner = new SachSpiner(view.getContext(), sachArrayList);
                spn_sached.setAdapter(sachSpiner);
                spn_sached.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maSach = sachArrayList.get(position).getMas();
                        Toast.makeText(view.getContext(), "Chọn sách: " + sachArrayList.get(position).getTens(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                mst = 0;
                for (int i = 0; i < sachArrayList.size(); i++) {
                    if (phieuMuon.getMaSpm() == sachArrayList.get(i).getMas()) {
                        mst = i;
                    }
                }
                spn_sached.setSelection(mst);
                mtvt = 0;
                for (int i = 0; i < thanhVienArrayList.size(); i++) {
                    if (phieuMuon.getMaTVpm() == thanhVienArrayList.get(i).getIDTV()) {
                        mtvt = i;
                    }
                }
                spn_tved.setSelection(mtvt);

                img_dateed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                calendar.set(year, month, dayOfMonth);
                                ed_ngaymuoned.setText(sdf.format(calendar.getTime()));
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.show();
                    }
                });

                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (chk_trasached.isChecked()) {
                            Trasach = 1;
                        } else {
                            Trasach = 0;
                        }
                        if (phieuMuon.getNgaymuon().equals(ed_ngaymuoned.getText().toString())
                                && phieuMuon.getTienthue() == Integer.parseInt(ed_tienthueed.getText().toString())
                                && phieuMuon.getMaTVpm() == mtvt
                                && phieuMuon.getMaSpm() == mst
                                && phieuMuon.getTrasach() == phieuMuon.getTrasach()) {

                            Toast.makeText(view.getContext(), "Không có thay đổi \n Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                        } else {
                            if (checkNgay(ed_ngaymuoned) && checkgiatien(ed_tienthueed)) {

                                phieuMuon.setMaTVpm(maTV);
                                phieuMuon.setMaSpm(maSach);
                                phieuMuon.setNgaymuon(ed_ngaymuoned.getText().toString());
                                phieuMuon.setTienthue(Integer.parseInt(ed_tienthueed.getText().toString()));
                                phieuMuon.setTrasach(Trasach);
                                //
                                long kq = phieuMuonDao.UPDATEPM(phieuMuon);
                                if (kq > 0) {
                                    lvPhieuMuon.clear();
                                    lvPhieuMuon.addAll(phieuMuonDao.GETPM());
                                    Toast.makeText(view.getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(view.getContext(), "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                                }
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
        if (lvPhieuMuon == null) {
            return 0;
        }
        return lvPhieuMuon.size();
    }
    public boolean checkNgay(EditText ed) {
        Date date = null;
        String value = ed.getText().toString();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
                Toast.makeText(context.getApplicationContext(), "Định dạng sai!", Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    public boolean checkgiatien(EditText ed) {
        boolean check = true;
        try {
            Integer.parseInt(ed.getText().toString());
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), "Tiền thuê phải là số", Toast.LENGTH_SHORT).show();
            check = false;
        }
        return check;
    }

    public class PhieuMuonHoler extends RecyclerView.ViewHolder{
        TextView tv_mapm, tv_matvpm, tv_maspm, tv_ngaymuon, tv_giamuon, tv_trasach;
        ImageView img_xoa_pm, img_sua_pm;
        public PhieuMuonHoler(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_mapm = itemView.findViewById(R.id.tv_maphieumuon);
            tv_matvpm = itemView.findViewById(R.id.tv_maTVmuon);
            tv_maspm = itemView.findViewById(R.id.tv_masachphieumuon);
            tv_ngaymuon = itemView.findViewById(R.id.tv_ngaymuon);
            tv_giamuon = itemView.findViewById(R.id.tv_giathuesach);
            tv_trasach = itemView.findViewById(R.id.tv_checktrasach);
            img_xoa_pm = itemView.findViewById(R.id.img_delete_pm);
            img_sua_pm = itemView.findViewById(R.id.img_edit_pm);
        }
    }
}
