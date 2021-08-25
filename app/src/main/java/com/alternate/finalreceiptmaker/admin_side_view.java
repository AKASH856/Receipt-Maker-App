package com.alternate.finalreceiptmaker;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class admin_side_view extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Mandal");

    Button oldPritnBtn, delete;
    EditText oldPrintEditText;
    Bitmap bmp;
    DataTable dataTable;
    DataTableHeader header;

    String datePattern = "dd-MM-yyyy";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat datePatternFormat = new SimpleDateFormat(datePattern);

    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    ArrayList<DataTableRow> rows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_side_view);
        oldPritnBtn = findViewById(R.id.oldPrintBtn);
        oldPrintEditText = findViewById(R.id.oldPrintEditText);
        delete = findViewById(R.id.delete);

        header = new DataTableHeader.Builder()
                .item("SR.No", 5)
                .item("Name", 5)
                .item("Amount", 5)
                .item("Date", 5)
                .item("Mandal", 5)
                .build();

        loadTable();
    }
    private void loadTable() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot myDataSnapshot : snapshot.getChildren()){
                    DataTableRow row = new DataTableRow.Builder()
                            .value(String.valueOf(myDataSnapshot.child("InvoiceNo").getValue()))
                            .value(String.valueOf(myDataSnapshot.child("Name").getValue()))
                            .value(String.valueOf(myDataSnapshot.child("Amount").getValue()))
                            .value(datePatternFormat.format(myDataSnapshot.child("Date").getValue()))
                            .value(String.valueOf(myDataSnapshot.child("Mandal").getValue()))
                            .build();
                    rows.add(row);
                }
                try {
                    dataTable.setHeader(header);
                    dataTable.setRows(rows);
                    dataTable.inflate(admin_side_view.this);
                }catch (NullPointerException ignore){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}