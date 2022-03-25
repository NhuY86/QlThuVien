package com.example.duanmau_mob2041_ytdnph12917.ui.ThanhVien;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_mob2041_ytdnph12917.Adapter.ThanhVienAdapter;
import com.example.duanmau_mob2041_ytdnph12917.Dao.ThanhVienDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThanhVien;
import com.example.duanmau_mob2041_ytdnph12917.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Fragment_Thanh_Vien extends Fragment {
    RecyclerView rcl_thanhvien;
    FragmentThanhVienViewModel thanhVienViewModel;
    ThanhVienDao thanhVienDao;
    FloatingActionButton flb_addtv;
    ThanhVienAdapter thanhVienAdapter;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flb_addtv = view.findViewById(R.id.flb_addtv);
        rcl_thanhvien = view.findViewById(R.id.recy_thanhvien);
        thanhVienDao = new ThanhVienDao(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcl_thanhvien.setLayoutManager(layoutManager);
        thanhVienViewModel = new ViewModelProvider(this).get(FragmentThanhVienViewModel.class);
        thanhVienViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<ThanhVien>>() {
            @Override
            public void onChanged(List<ThanhVien> mThanhVien) {
                thanhVienAdapter = new ThanhVienAdapter(getContext(),mThanhVien);
                rcl_thanhvien.setAdapter(thanhVienAdapter);
            }
        });
        flb_addtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view1 = layoutInflater.inflate(R.layout.custom_add_thanhvien, null);
                AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                builder.setView(view1);
                builder.setIcon(R.drawable.ruler);
                builder.setTitle("        Thêm Thành Viên");
                EditText ed_hoten = (EditText) view1.findViewById(R.id.ed_ten_thanh_vien);
                EditText ed_namsinh = (EditText) view1.findViewById(R.id.ed_nam_sinh_thanh_vien);
                AppCompatButton btn_them = (AppCompatButton) view1.findViewById(R.id.btn_them_tv_add);
                AppCompatButton btn_cle = (AppCompatButton) view1.findViewById(R.id.btn_huy_tv_add);
                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ThanhVien thanhVien = new ThanhVien();
                        thanhVien.setHoTenTV(ed_hoten.getText().toString());
                        thanhVien.setNamsinhTV(ed_namsinh.getText().toString());
                        long kq = thanhVienDao.ADDTV(thanhVien);
                        if (kq > 0) {
                            Toast.makeText(getActivity(), "Đã Thêm Thành viên", Toast.LENGTH_SHORT).show();
                            ed_hoten.setText("");
                            ed_namsinh.setText("");
                            thanhVienViewModel.getLiveData();
                            thanhVienAdapter.notifyDataSetChanged();
                            builder.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Thêm Thành viên Thất Bại", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                btn_cle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__thanh__vien_fragment, null, false);
    }
}