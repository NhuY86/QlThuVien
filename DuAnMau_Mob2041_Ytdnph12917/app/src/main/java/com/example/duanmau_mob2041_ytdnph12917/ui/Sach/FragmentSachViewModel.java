package com.example.duanmau_mob2041_ytdnph12917.ui.Sach;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.duanmau_mob2041_ytdnph12917.Dao.SachDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.Sach;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FragmentSachViewModel extends AndroidViewModel {
    SachDao dao;
    MutableLiveData<List<Sach>> liveData;
    public FragmentSachViewModel(@NonNull @NotNull Application application) {
        super(application);
        liveData = new MutableLiveData<>();
        dao = new SachDao(application);
    }
    public MutableLiveData<List<Sach>> getLiveData() {
        loads();
        return liveData;
    }
    private void loads() {
        List<Sach> list = new ArrayList<>();
        list = dao.GETS();
        liveData.setValue(list);
    }
}