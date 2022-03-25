package com.example.duanmau_mob2041_ytdnph12917;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.duanmau_mob2041_ytdnph12917.Login.DangNhap;
import com.example.duanmau_mob2041_ytdnph12917.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    View mHeaderview;
    TextView tv_user, tv_email;
    ImageView img_user;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_PhieuMuon, R.id.nav_LoaiSach, R.id.nav_Sach,R.id.nav_ThanhVien,R.id.nav_ThongKe,R.id.nav_DoanhThu,R.id.nav_TaoTaiKhoan,R.id.nav_DoiMatKhau)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        mHeaderview = navigationView.getHeaderView(0);
        tv_user = mHeaderview.findViewById(R.id.tv_user);
        img_user = mHeaderview.findViewById(R.id.img_user);
        tv_email = mHeaderview.findViewById(R.id.tv_email);
        Intent intent = getIntent();
        String user = intent.getStringExtra("admintion");
//        String email = intent.getStringExtra("admintion");
//        tv_email.setText(email +"@gmail.com");
        tv_user.setText("Wellcome " + user + "!");
        if (user.equals("Admin")) {
//            img_user.setImageResource(R.drawable.sach);
            navigationView.getMenu().findItem(R.id.nav_TaoTaiKhoan).setVisible(true);
        } else {
            navigationView.getMenu().findItem(R.id.nav_TaoTaiKhoan).setVisible(false);
        }
//        if (email.equals("@gmail.com")) {
//            img_user.setImageResource(R.drawable.launcher);
//            navigationView.getMenu().findItem(R.id.nav_TaoTaiKhoan).setVisible(true);
//        } else {
//            navigationView.getMenu().findItem(R.id.nav_TaoTaiKhoan).setVisible(false);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }
    public void Thoat(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_baseline_campaign_24);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), DangNhap.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}