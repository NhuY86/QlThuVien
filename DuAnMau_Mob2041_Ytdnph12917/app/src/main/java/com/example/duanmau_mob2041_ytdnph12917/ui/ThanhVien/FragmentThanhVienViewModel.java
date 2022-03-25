package com.example.duanmau_mob2041_ytdnph12917.ui.ThanhVien;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_mob2041_ytdnph12917.Adapter.ThanhVienAdapter;
import com.example.duanmau_mob2041_ytdnph12917.Dao.ThanhVienDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FragmentThanhVienViewModel extends AndroidViewModel {
    ThanhVienDao thanhVienDao;
    MutableLiveData<List<ThanhVien>> liveData;
    public FragmentThanhVienViewModel(@NonNull @NotNull Application application) {
        super(application);
        liveData = new MutableLiveData<>();
        thanhVienDao = new ThanhVienDao(application);
    }
    public MutableLiveData<List<ThanhVien>> getLiveData() {
        loaddl();
        return liveData;
    }

    public void loaddl() {
        List<ThanhVien> list = new ArrayList<>();
        list = thanhVienDao.GETTV();
        liveData.setValue(list);
    }
}