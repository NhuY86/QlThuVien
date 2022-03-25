package com.example.duanmau_mob2041_ytdnph12917.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_mob2041_ytdnph12917.Dao.ThuThuDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThuThu;
import com.example.duanmau_mob2041_ytdnph12917.R;

import org.jetbrains.annotations.NotNull;

public class DoiMatKhau extends Fragment {
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doi_mat_khau,null,false);
    }
    ThuThuDao thuThuDao;
    EditText ed_passnew, ed_passold, ed_passen;
    Button btn_save, btn_clear;
    ThuThu thuThu;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ed_passold = view.findViewById(R.id.ed_pass_old);
        ed_passnew = view.findViewById(R.id.ed_Pass_new);
        ed_passen = view.findViewById(R.id.ed_Enter_pass);
        btn_clear = view.findViewById(R.id.btn_clear);
        btn_save = view.findViewById(R.id.btn_save);
        thuThuDao = new ThuThuDao(getActivity());
        String user = getActivity().getIntent().getStringExtra("admintion");
        thuThu = thuThuDao.getId(user);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_passold.setText("");
                ed_passnew.setText("");
                ed_passen.setText("");
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkn() > 0) {
                    thuThu.setMatKhau(ed_passnew.getText().toString());
                    int kq = thuThuDao.Thaypass(thuThu);
                    if (kq > 0) {
                        Toast.makeText(getActivity(), "Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                        ed_passold.setText("");
                        ed_passnew.setText("");
                        ed_passen.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Thay Đổi Mật Khẩu Thất Bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public int checkn() {
        int check = 1;
        if (ed_passold.getText().length() == 0 || ed_passnew.getText().length() == 0 || ed_passen.getText().length() == 0) {
            Toast.makeText(getActivity(), "Thông Tin Trống!", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass_old = thuThu.getMatKhau();
            String pass_new = ed_passnew.getText().toString();
            String passent = ed_passen.getText().toString();
            if (!pass_old.equals(ed_passold.getText().toString())) {
                Toast.makeText(getActivity(), "Nhập Mật Khẩu Cũ Sai", Toast.LENGTH_SHORT).show();
                return check = -1;
            }
            if (!pass_new.equals(passent)) {
                Toast.makeText(getActivity(), "Mật Khẩu Mới Nhập Lại Không Chính Xác", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}