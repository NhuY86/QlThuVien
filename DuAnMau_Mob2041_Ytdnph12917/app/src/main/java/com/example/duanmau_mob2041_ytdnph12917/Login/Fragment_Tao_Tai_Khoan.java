package com.example.duanmau_mob2041_ytdnph12917.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau_mob2041_ytdnph12917.Adapter.ThuThuAdapter;
import com.example.duanmau_mob2041_ytdnph12917.Dao.ThuThuDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThuThu;
import com.example.duanmau_mob2041_ytdnph12917.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Tao_Tai_Khoan extends Fragment {
    ThuThuDao thuThuDao;
    EditText edUser, edPass, edEnPass, edhoten;
    ListView lv_nv;
    FloatingActionButton flb_nv;
    ThuThuAdapter adapter;
    List<ThuThu> list;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__tao__tai__khoan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_nv = view.findViewById(R.id.lisv_tao_id_tt);
        thuThuDao = new ThuThuDao(getActivity());
        thuThuDao.OPEN();
        list = new ArrayList<>();
        list = thuThuDao.GETTT();
        adapter = new ThuThuAdapter(list, getContext());
        lv_nv.setAdapter(adapter);

        flb_nv = view.findViewById(R.id.themtt);
        flb_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.custom_tao_tt, null);
                builder.setView(view);
                thuThuDao = new ThuThuDao(getActivity());
                edUser = view.findViewById(R.id.edUser);
                edhoten = view.findViewById(R.id.ed_HoTen);
                edPass = view.findViewById(R.id.edPass);
                edEnPass = view.findViewById(R.id.edRePass);
                builder.setTitle("          Tạo Tài Khoản");
                builder.setPositiveButton("Tạo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThuThu thuThu = new ThuThu();
                        thuThu.setMaTT(edUser.getText().toString());
                        thuThu.setHoTen(edhoten.getText().toString());
                        thuThu.setMatKhau(edPass.getText().toString());
                        if (checkrong() > 0) {
                            long kq = thuThuDao.ADDTT(thuThu);
                            if (kq > 0) {
                                Toast.makeText(getActivity(), "Tạo Tài khoản thành công", Toast.LENGTH_SHORT).show();
                                edUser.setText("");
                                edhoten.setText("");
                                edPass.setText("");
                                edEnPass.setText("");
                                list.clear();
                                list.addAll(thuThuDao.GETTT());
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getActivity(), "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });

    }
    public int checkrong() {
        int check = 1;
        if (edUser.getText().length() == 0 || edhoten.getText().length()==5||
                edPass.getText().length() == 0 || edEnPass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(),"Tên phải bé lớn hơn 5 và bé hơn 15 kí tự",Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edPass.getText().toString();
            String rePass = edEnPass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}