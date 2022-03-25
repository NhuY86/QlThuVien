package com.example.duanmau_mob2041_ytdnph12917.ui.PhieuMuon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.duanmau_mob2041_ytdnph12917.Dao.PhieuMuonDao;
import com.example.duanmau_mob2041_ytdnph12917.Model.PhieuMuon;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FragmentPhieuMuonViewModel extends AndroidViewModel {
    MutableLiveData<List<PhieuMuon>> liveData;
    PhieuMuonDao dao;

    public FragmentPhieuMuonViewModel(@NonNull @NotNull Application application) {
        super(application);
        liveData = new MutableLiveData<>();
        dao = new PhieuMuonDao(application);
    }

    public MutableLiveData<List<PhieuMuon>> getLiveData() {
        loadData();
        return liveData;
    }

    public void loadData() {
        List<PhieuMuon> list = new ArrayList<>();
        list = dao.GETPM();
        liveData.setValue(list);
    }
}