package com.alternate.finalreceiptmaker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ganpati_Activity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Festivals");
    DataObj dataObj = new DataObj();

    EditText textInputEditText1,textInputEditText2,textInputEditText3,textInputEditText4,textInputEditText5,textInputEditText6;
    Button Save,Share;
    long InvoiceNo = 0;
    Bitmap bmp;

    Date date = new Date();

    String datePattern = "dd-MM-yyyy";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat datePatternFormat = new SimpleDateFormat(datePattern);

    String timePattern = "hh:mm:ss";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat timePatternFormat = new SimpleDateFormat(timePattern);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganpati);
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.g1);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        callFindViewByIdes();
        createPDF();
        sharepdf();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InvoiceNo = snapshot.getChildrenCount();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//making and sharing PDF
    private void sharepdf() {
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textInputEditText1.getText().toString().length()==0 ||
                        textInputEditText2.getText().toString().length()==0 ||
                        textInputEditText3.getText().toString().length()==0 ||
                        textInputEditText4.getText().toString().length()==0 ||
                        textInputEditText5.getText().toString().length()==0 ||
                        textInputEditText6.getText().toString().length()==0 ) {
                    Toast.makeText(Ganpati_Activity.this,"Some places are empty",Toast.LENGTH_LONG).show();
                } else {
                    PdfDocument myPdfDocument = new PdfDocument();
                    Paint myPaint = new Paint();

                    PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(1750, 1200, 1).create();
                    PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);

                    Canvas canvas = myPage1.getCanvas();
                    canvas.drawBitmap(bmp,40,50,myPaint);

                    myPaint.setColor(Color.WHITE);
                    myPaint.setTextSize(80);
                    canvas.drawText(""+textInputEditText4.getText() +"MANDAL" ,70,150,myPaint);


                    myPaint.setTextSize(40);
                    myPaint.setColor(Color.WHITE);
                    canvas.drawText("Receipt to : "+textInputEditText1.getText()+","+"from : ____________" ,70,285,myPaint);


                    myPaint.setTextSize(40);
                    myPaint.setColor(Color.WHITE);
                    canvas.drawText("Name : "+textInputEditText1.getText(),90,450,myPaint);


                    myPaint.setColor(Color.WHITE);
                    canvas.drawText("PhoneNo : "+textInputEditText3.getText(),90,520,myPaint);


                    myPaint.setColor(Color.WHITE);
                    canvas.drawText("Amount : "+textInputEditText2.getText() +"/-",90,580,myPaint);


                    myPaint.setColor(Color.WHITE);
                    canvas.drawText("Mandal Name  : "+textInputEditText4.getText(),90,650,myPaint);


                    myPaint.setColor(Color.WHITE);
                    canvas.drawText("BLD.No : "+textInputEditText5.getText(),90,720,myPaint);


                    myPaint.setColor(Color.WHITE);
                    canvas.drawText("Room.No : "+textInputEditText6.getText(),90,780,myPaint);


                    myPaint.setTextSize(35);
                    canvas.drawText(" Venue : Aikya Vardhak Ground,Opposite to BLD.No.54", 70,1010,myPaint);

                    myPaint.setTextSize(35);
                    canvas.drawText("Do arrive the venue by 8 pm  for the occasion of Aarti ",75,1050,myPaint);
                    myPaint.setTypeface(Typeface.create(Typeface.MONOSPACE,Typeface.NORMAL));

                    myPaint.setColor(Color.rgb(168,131,186));
                    myPaint.setTextSize(45);
                    canvas.drawText(" HAPPY GANESH CHATURTHI ",60,1100,myPaint);


                    myPdfDocument.finishPage(myPage1);


                    File file = new File(Environment.getExternalStorageDirectory(),"_"+textInputEditText1.getText().toString()+"_"+textInputEditText3.getText().toString()+"GANPATI.pdf");
                    try {
                        myPdfDocument.writeTo(new FileOutputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    myPdfDocument.close();
                    Toast.makeText(Ganpati_Activity.this, "PDF Created Successfully ", Toast.LENGTH_LONG).show();

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

//Saving PDF values in database
    private void createPDF() {
        Save.setOnClickListener(view -> {
            if(textInputEditText1.getText().toString().length()==0 ||
                    textInputEditText2.getText().toString().length()==0 ||
                    textInputEditText3.getText().toString().length()==0 ||
                    textInputEditText4.getText().toString().length()==0 ||
                    textInputEditText5.getText().toString().length()==0 ||
                    textInputEditText6.getText().toString().length()==0 ) {
                Toast.makeText(Ganpati_Activity.this,"Some places are empty",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(Ganpati_Activity.this,"Data Saved Successfully",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(Ganpati_Activity.this, "Data Saved Successfully ", Toast.LENGTH_LONG).show();

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
        Share =findViewById(R.id.Share);
        textInputEditText1 = findViewById(R.id.textInputEditText1);
        textInputEditText2 = findViewById(R.id.textInputEditText2);
        textInputEditText3 = findViewById(R.id.textInputEditText3);
        textInputEditText4 = findViewById(R.id.textInputEditText4);
        textInputEditText5 = findViewById(R.id.textInputEditText5);
        textInputEditText6 = findViewById(R.id.textInputEditText6);
    }
}