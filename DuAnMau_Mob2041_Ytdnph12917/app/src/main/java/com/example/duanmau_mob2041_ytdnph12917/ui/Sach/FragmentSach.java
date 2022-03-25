package com.example.duanmau_mob2041_ytdnph12917.ui.Sach;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_mob2041_ytdnph12917.Adapter.SachAdapter;
import com.example.duanmau_mob2041_ytdnph12917.Dao.LoaiSachDao;
import com.example.duanmau_mob2041_ytdnph12917.Dao.SachDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.LoaiSach;
import com.example.duanmau_mob2041_ytdnph12917.Model.Sach;
import com.example.duanmau_mob2041_ytdnph12917.R;
import com.example.duanmau_mob2041_ytdnph12917.Spiner.LoaiSachSpiner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FragmentSach extends Fragment {
    RecyclerView rcl_sach;
    FloatingActionButton flb_sach;
    ArrayList<LoaiSach> loaiSach;
    int mals;
    LoaiSachDao loaiSachDao;
    LoaiSachSpiner loaiSachSpiner;
    SachDao dao;
    FragmentSachViewModel model;
    SachAdapter adapter;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sach_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcl_sach = (RecyclerView) view.findViewById(R.id.rcl_sach);
        flb_sach = (FloatingActionButton) view.findViewById(R.id.them_sach);
        dao = new SachDao(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcl_sach.setLayoutManager(layoutManager);
        model = new ViewModelProvider(this).get(FragmentSachViewModel.class);
        model.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<Sach>>() {
            @Override
            public void onChanged(List<Sach> sach) {
                adapter = new SachAdapter(getActivity(), sach, dao);
                rcl_sach.setAdapter(adapter);
            }
        });
        flb_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = inflater.inflate(R.layout.custom_add_sach, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(view);
                EditText ed_tens = (EditText) view.findViewById(R.id.tensach);
                EditText ed_km = (EditText) view.findViewById(R.id.km);
                Spinner spns = (Spinner) view.findViewById(R.id.spin_lsach);
                EditText ed_gias = (EditText) view.findViewById(R.id.giasach);
                builder.setIcon(R.drawable.ruler);
                builder.setTitle("          Thêm Sách");
                loaiSach = new ArrayList<>();
                loaiSachDao = new LoaiSachDao(getContext());
                loaiSach = (ArrayList<LoaiSach>) loaiSachDao.GETLS();
                loaiSachSpiner = new LoaiSachSpiner(getActivity(), loaiSach);
                spns.setAdapter(loaiSachSpiner);
                spns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mals = loaiSach.get(position).getMaLS();
                        Toast.makeText(getContext(), "Chọn" + loaiSach.get(position).getTenLS(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ed_tens.getText().length() == 0 || ed_gias.getText().length() == 0 || ed_km.getText().length() == 0){
                            Toast.makeText(getContext(), "Bạn cần phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                int giaThue = Integer.parseInt(ed_gias.getText().toString());
                                Sach sach = new Sach();
                                sach.setTens(ed_tens.getText().toString());
                                sach.setGias(giaThue);
                                int km = Integer.parseInt(ed_km.getText().toString());
                                sach.setKm(ed_km.getText().toString());
                                sach.setMals(mals);
                                long kq = dao.ADDS(sach);
                                if (kq > 0) {
                                    Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                                    ed_tens.setText("");
                                    ed_gias.setText("");
                                    ed_km.setText("");
                                    spns.setSelection(0);
                                    model.getLiveData();
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Toast.makeText(getContext(), "Giá thuê phải là số", Toast.LENGTH_SHORT).show();
                            }
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