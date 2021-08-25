package com.alternate.finalreceiptmaker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Festivals");
    DataObj dataObj = new DataObj();

    private static final String FILE_NAME = "myFile";
    private DrawerLayout drawer;
    TextView username,userEmail;
    ImageView imageview;
    Button ProfileImage;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ProfileImage = findViewById(R.id.ProfileImage);
        imageview = findViewById(R.id.imageview);
//        username = findViewById(R.id.username);
//        userEmail = findViewById(R.id.userEmail);

//      To get images from gallery
//        if (ProfileImage != null) {
//            ProfileImage.setOnClickListener(v -> mGetContent.launch("image/*"));
//        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new home_fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//       ******************************  RETRIEVING THE DATA FROM FIREBASE USING REGISTER_ACTIVITY  *****************
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (username != null && userEmail != null) {
//                    String FullName = String.valueOf(snapshot.getValue(Boolean.parseBoolean(dataObj.FullName)));
//                    username.setText(FullName);
//                    String mEmail =  String.valueOf(snapshot.getValue(Boolean.parseBoolean(dataObj.mEmail)));
//                    userEmail.setText(mEmail);
//                }
//
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new home_fragment()).commit();
                break;
            case R.id.nav_collection:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new collection_fragment()).commit();
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new about_us_fragment()).commit();
                break;

            case R.id.nav_logout:

                SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
                String email = sharedPreferences.getString("email","Data Not found");
                Toast.makeText(Home_Activity.this,"Logged Out",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Home_Activity.this,login_activity.class);
                startActivity(intent);
                finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    ******************** To get images from gallery****************************
//    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
//           new ActivityResultContracts.GetContent(),
//           new ActivityResultCallback<Uri>() {
//                @Override
//                public void onActivityResult(Uri result) {
//                   if (result != null) {
//                        imageview.setImageURI(result);
//                    }
//                }
//            });




    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Are You Sure you want to Exit??")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Home_Activity.super.finish();
                        }
                    })
                    .setNegativeButton("no", null)
                    .show();
        }
    }
}

