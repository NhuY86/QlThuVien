package com.example.duanmau_mob2041_ytdnph12917.ui.LoaiSach;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.duanmau_mob2041_ytdnph12917.Dao.LoaiSachDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.LoaiSach;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FragmentLoaiSachViewModel extends AndroidViewModel {
    LoaiSachDao loaiSachDao;
    MutableLiveData<List<LoaiSach>> liveData;

    public FragmentLoaiSachViewModel(@NonNull @NotNull Application application) {
        super(application);
        liveData = new MutableLiveData<>();
        loaiSachDao = new LoaiSachDao(application);
    }

    public MutableLiveData<List<LoaiSach>> getLiveData() {
        loadls();
        return liveData;

    }

    public void loadls() {
        List<LoaiSach> list = new ArrayList<>();
        list = loaiSachDao.GETLS();
        liveData.setValue(list);
    }
}