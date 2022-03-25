package com.example.duanmau_mob2041_ytdnph12917.ui.LoaiSach;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.duanmau_mob2041_ytdnph12917.Adapter.LoaiSachAdapter;
import com.example.duanmau_mob2041_ytdnph12917.Dao.LoaiSachDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.LoaiSach;
import com.example.duanmau_mob2041_ytdnph12917.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Fragment_Loai_Sach extends Fragment {
    RecyclerView rcl_ls;
    EditText ed_tenls;
    LoaiSachDao loaiSachDao;
    LoaiSachAdapter adapter;
    FloatingActionButton flb_addls;
    FragmentLoaiSachViewModel model;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__loai__sach_fragment,null);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcl_ls = view.findViewById(R.id.rcl_loaisach);
        flb_addls = view.findViewById(R.id.flb_loaisach);
        loaiSachDao = new LoaiSachDao(getActivity());
        RecyclerView.LayoutManager lymanage = new LinearLayoutManager(getActivity());
        rcl_ls.setLayoutManager(lymanage);
        model = new ViewModelProvider(this).get(FragmentLoaiSachViewModel.class);
        model.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<LoaiSach>>() {
            @Override
            public void onChanged(List<LoaiSach> loaiSaches) {
                adapter = new LoaiSachAdapter(getActivity(), loaiSaches, loaiSachDao);
                rcl_ls.setAdapter(adapter);
            }
        });
        flb_addls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View view1 = inflater.inflate(R.layout.custom_add_loaisach, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(view1);
                EditText ed_ls = (EditText) view1.findViewById(R.id.ed_sua_ls);
                builder.setIcon(R.drawable.ruler);
                builder.setTitle("         Thêm Loại Sách");
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiSach loaiSach = new LoaiSach();
                        loaiSach.setTenLS(ed_ls.getText().toString());
                        long kq = loaiSachDao.ADDLS(loaiSach);
                        if (kq > 0) {
                            ed_ls.setText("");
                            Toast.makeText(getActivity(), "Thêm Loại Sách Thành Công", Toast.LENGTH_SHORT).show();
                            model.getLiveData();
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "Tên Loại Sách Trùng Lặp \n Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}