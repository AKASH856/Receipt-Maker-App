package com.alternate.finalreceiptmaker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Holi_Activity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Festivals");
    DataObj dataObj = new DataObj();
    EditText textInputEditText1,textInputEditText2,textInputEditText3,textInputEditText4,textInputEditText5,textInputEditText6;
    Button Save,Share;
    Bitmap bmp;
    long InvoiceNo = 0;
    Date date = new Date();

    String datePattern = "dd-MM-yyyy";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat datePatternFormat = new SimpleDateFormat(datePattern);

    String timePattern = "hh:mm:ss";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat timePatternformat = new SimpleDateFormat(timePattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holi);
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.h1);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        callFindViewByIdes();
        createPDF();


    }

    private void createPDF() {
        Save.setOnClickListener(view -> {
            if(textInputEditText1.getText().toString().length()==0 ||
                    textInputEditText2.getText().toString().length()==0 ||
                    textInputEditText3.getText().toString().length()==0 ||
                    textInputEditText4.getText().toString().length()==0 ||
                    textInputEditText5.getText().toString().length()==0 ||
                    textInputEditText6.getText().toString().length()==0 ) {
                Toast.makeText(Holi_Activity.this,"Some places are empty",Toast.LENGTH_LONG).show();
            } else {
                if ("Aikyavardhak".equals(textInputEditText4.getText().toString())) {
                    dataObj.InvoiceNo = InvoiceNo + 1;
                    dataObj.date = new Date().getTime();
                    dataObj.Name = String.valueOf((textInputEditText1.getText()));
                    dataObj.Amount = Double.parseDouble(String.valueOf(textInputEditText2.getText()));
                    dataObj.PhoneNo = Double.parseDouble(String.valueOf(textInputEditText3.getText()));
                    dataObj.MandalName = String.valueOf((textInputEditText4.getText()));
                    dataObj.BldNo = Double.parseDouble(String.valueOf(textInputEditText5.getText()));
                    dataObj.RoomNo = Double.parseDouble(String.valueOf(textInputEditText6.getText()));

                    myRef.child("Ganesh chaturthi").child("Aikyavardhak Mandal").child("Volunteer").child(String.valueOf(InvoiceNo + 1)).setValue(dataObj);
                    Toast.makeText(Holi_Activity.this,"Data Saved Successfully",Toast.LENGTH_LONG).show();
                }else{
                    dataObj.InvoiceNo = InvoiceNo + 1;
                    dataObj.date = new Date().getTime();
                    dataObj.Name = String.valueOf((textInputEditText1.getText()));
                    dataObj.Amount = Double.parseDouble(String.valueOf(textInputEditText2.getText()));
                    dataObj.PhoneNo = Double.parseDouble(String.valueOf(textInputEditText3.getText()));
                    dataObj.MandalName = String.valueOf((textInputEditText4.getText()));
                    dataObj.BldNo = Double.parseDouble(String.valueOf(textInputEditText5.getText()));
                    dataObj.RoomNo = Double.parseDouble(String.valueOf(textInputEditText6.getText()));

                    myRef.child("Ganesh chaturthi").child("Lal Maidan Mandal").child("Volunteer").child(String.valueOf(InvoiceNo + 1)).setValue(dataObj);
                    Toast.makeText(Holi_Activity.this, "Data Saved Successfully ", Toast.LENGTH_LONG).show();

                    textInputEditText1.getText().clear();
                    textInputEditText2.getText().clear();
                    textInputEditText3.getText().clear();
                    textInputEditText4.getText().clear();
                    textInputEditText5.getText().clear();
                    textInputEditText6.getText().clear();
                }
            }
        });

    }

    private void callFindViewByIdes() {
        Save =findViewById(R.id.Save);
        textInputEditText1 = findViewById(R.id.textInputEditText1);
        textInputEditText2 = findViewById(R.id.textInputEditText2);
        textInputEditText3 = findViewById(R.id.textInputEditText3);
        textInputEditText4 = findViewById(R.id.textInputEditText4);
        textInputEditText5 = findViewById(R.id.textInputEditText5);
        textInputEditText6 = findViewById(R.id.textInputEditText6);
    }
}