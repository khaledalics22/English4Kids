package com.example.englishforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    public static String fileName="pref.com.example.englishforkids";
    public static int currLevel;
    public static String strCurrLevel="currLevel";
    public static SharedPreferences pref;
    public static final  String currWord="currWord";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView=findViewById(R.id.myNavBar);
        navigationView.setNavigationItemSelectedListener(this);
        drawer=findViewById(R.id.myDrawer);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        pref=getSharedPreferences(fileName,MODE_PRIVATE);
        currLevel=(pref.getInt(strCurrLevel,1));
        Button btnGoToLevel=(Button)findViewById(R.id.btn_go_to_level);
        if(currLevel!=1)
        {
            btnGoToLevel.setText(R.string.goToLevel);
            for(int i=0; i<currLevel;i++) {
                navigationView.getMenu().getItem(i).setIcon(R.drawable.ic_lock_open_black_24dp);
            }
        }
        btnGoToLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currLevel!=0)
                {
                    if(currLevel==1)
                    {
                        startActivity(new Intent(MainActivity.this, lesson_one_1.class));

                    }
                    else if(currLevel==2)
                    {
                        startActivity(new Intent(MainActivity.this, lesson_one_2.class));

                    }
                    else if(currLevel==3)
                    {
                        startActivity(new Intent(MainActivity.this, lesson_two_1.class));
                    }
                    else if(currLevel==4)
                    {
                        startActivity(new Intent(MainActivity.this, lesson_two_2.class));
                    }
                    else {}


                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.level_nav_1:
                startActivity(new Intent(MainActivity.this, lesson_one_1.class));
                Toast.makeText(MainActivity.this,R.string.level_1,Toast.LENGTH_SHORT).show();
                break;

            case R.id.level_nav_2:
                if(currLevel>=2) {
                    startActivity(new Intent(MainActivity.this, lesson_one_2.class));
                    Toast.makeText(MainActivity.this, R.string.level_2, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.notOpened, Toast.LENGTH_SHORT).show();}
                break;

            case R.id.level_nav_3:
                if(currLevel>=3) {
                    startActivity(new Intent(MainActivity.this, lesson_two_1.class));
                    Toast.makeText(MainActivity.this, R.string.level_3, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.notOpened, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.level_nav_4:
                if(currLevel>=4){
                startActivity(new Intent(MainActivity.this, lesson_two_2.class));
                Toast.makeText(MainActivity.this,R.string.level_4,Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(MainActivity.this, R.string.notOpened, Toast.LENGTH_SHORT).show();
                    }

                break;
        }
        return true;
    }
}
