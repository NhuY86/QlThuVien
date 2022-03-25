package com.example.duanmau_mob2041_ytdnph12917.ui.PhieuMuon;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau_mob2041_ytdnph12917.Adapter.PhieuMuonAdapter;
import com.example.duanmau_mob2041_ytdnph12917.Dao.PhieuMuonDao;
import com.example.duanmau_mob2041_ytdnph12917.Dao.SachDao;
import com.example.duanmau_mob2041_ytdnph12917.Dao.ThanhVienDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.PhieuMuon;
import com.example.duanmau_mob2041_ytdnph12917.Model.Sach;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThanhVien;
import com.example.duanmau_mob2041_ytdnph12917.R;
import com.example.duanmau_mob2041_ytdnph12917.Spiner.SachSpiner;
import com.example.duanmau_mob2041_ytdnph12917.Spiner.ThanhVienSpiner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Fragment_Phieu_Muon extends Fragment {
    List<PhieuMuon> lvPhieuMuon;
    FragmentPhieuMuonViewModel phieuMuonViewModel;
    RecyclerView rcl_pm;
    FloatingActionButton fbl_add_pm;
    PhieuMuonAdapter phieuMuonAdapter;
    SachSpiner sachSpiner;
    ThanhVienSpiner thanhVienSpiner;
    int maTV, maSach, Trasach;
    String maNV;
    ThanhVienDao thanhVienDao;
    ArrayList<ThanhVien> thanhVienArrayList;
    SachDao sachDao;
    ArrayList<Sach> sachArrayList;
    PhieuMuonDao phieuMuonDao;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__phieu__muon_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcl_pm = view.findViewById(R.id.recyle_pm);
        fbl_add_pm = view.findViewById(R.id.fbl_phieumuon);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcl_pm.setLayoutManager(layoutManager);
        phieuMuonViewModel = new ViewModelProvider(this).get(FragmentPhieuMuonViewModel.class);
        phieuMuonDao = new PhieuMuonDao(getActivity());
        phieuMuonViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<PhieuMuon>>() {
            @Override
            public void onChanged(List<PhieuMuon> phieuMuons) {
                phieuMuonAdapter = new PhieuMuonAdapter(getActivity(), phieuMuons);
                rcl_pm.setAdapter(phieuMuonAdapter);
            }
        });
        Intent intent = getActivity().getIntent();
        maNV = intent.getStringExtra("admintion");

        fbl_add_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view1 = inflater.inflate(R.layout.custom_add_phieumuon, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(view1);
                EditText ed_ngaymuon = (EditText) view1.findViewById(R.id.ed_ngaymuon);
                EditText ed_tienthue = (EditText) view1.findViewById(R.id.ed_giamuon);
                Spinner spn_tv = (Spinner) view1.findViewById(R.id.spinnertv);
                Spinner spn_sach = (Spinner) view1.findViewById(R.id.spinnersach);
                ImageView img_date = (ImageView) view1.findViewById(R.id.img_lich);
                CheckBox chk_trasach = (CheckBox) view1.findViewById(R.id.chk_sachtra);
                builder.setIcon(R.drawable.ruler);
                builder.setTitle("         Thêm Phiếu Mượn");
                // Spinner tv
                thanhVienDao = new ThanhVienDao(getContext());
                thanhVienArrayList = new ArrayList<>();
                thanhVienArrayList = (ArrayList<ThanhVien>) thanhVienDao.GETTV();
                thanhVienSpiner = new ThanhVienSpiner(getContext(), thanhVienArrayList);
                spn_tv.setAdapter(thanhVienSpiner);
                spn_tv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maTV = thanhVienArrayList.get(position).getIDTV();
                        Toast.makeText(getActivity(), "Thành Viên: " + thanhVienArrayList.get(position).getHoTenTV(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                // Spiner sach
                sachDao = new SachDao(getContext());
                sachArrayList = new ArrayList<>();
                sachArrayList = (ArrayList<Sach>) sachDao.GETS();
                sachSpiner = new SachSpiner(getContext(), sachArrayList);
                spn_sach.setAdapter(sachSpiner);
                spn_sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maSach = sachArrayList.get(position).getMas();
                        Toast.makeText(getActivity(), "Chọn sách: " + sachArrayList.get(position).getTens(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                // date pikerdialog
                img_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                calendar.set(year, month, dayOfMonth);
                                ed_ngaymuon.setText(sdf.format(calendar.getTime()));
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.show();
                    }
                });
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (chk_trasach.isChecked()) {
                            Trasach = 1;
                        } else {
                            Trasach = 0;
                        }
                        if (ed_ngaymuon.getText().length() == 0 || ed_tienthue.getText().length() == 0) {
                            Toast.makeText(getContext(), "Bạn cần phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            if (checkNgay(ed_ngaymuon) && checkgiatien(ed_tienthue)) {

                                PhieuMuon phieuMuon = new PhieuMuon();
                                phieuMuon.setMaNVpm(maNV);
                                phieuMuon.setMaTVpm(maTV);
                                phieuMuon.setMaSpm(maSach);
                                phieuMuon.setNgaymuon(ed_ngaymuon.getText().toString());
                                phieuMuon.setTienthue(Integer.parseInt(ed_tienthue.getText().toString()));
                                phieuMuon.setTrasach(Trasach);
                                long kq = phieuMuonDao.ADDPM(phieuMuon);
                                if (kq > 0) {

                                    Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                                    phieuMuonViewModel.getLiveData();
                                    phieuMuonAdapter.notifyDataSetChanged();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
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

    public boolean checkNgay(EditText ed) {
        Date date = null;
        String value = ed.getText().toString();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
                Toast.makeText(getActivity(), "Sai dịnh dạng ngày!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity(), "Tiền thuê phải là số", Toast.LENGTH_SHORT).show();
            check = false;
        }
        return check;
    }
    }