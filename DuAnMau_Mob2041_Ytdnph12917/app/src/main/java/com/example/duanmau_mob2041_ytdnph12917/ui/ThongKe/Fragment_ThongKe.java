package com.example.duanmau_mob2041_ytdnph12917.ui.ThongKe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau_mob2041_ytdnph12917.Adapter.Top10Adapter;
import com.example.duanmau_mob2041_ytdnph12917.Dao.Top10Dao;
import com.example.duanmau_mob2041_ytdnph12917.Model.Top10;
import com.example.duanmau_mob2041_ytdnph12917.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ThongKe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ThongKe extends Fragment {
    ListView lv;
    ArrayList<Top10> list;
    Top10Adapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_ThongKe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_ThongKe.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_ThongKe newInstance(String param1, String param2) {
        Fragment_ThongKe fragment = new Fragment_ThongKe();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment__thong_ke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.lv_top);
        Top10Dao top10Dao = new Top10Dao(getActivity());
        list = (ArrayList<Top10>)top10Dao.getTop() ;
        adapter = new Top10Adapter(getActivity(),list);
        lv.setAdapter(adapter);
    }
}