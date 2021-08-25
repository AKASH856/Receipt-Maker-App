package com.alternate.finalreceiptmaker;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class collection_fragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Mandal");

    Button oldPritnBtn,delete;
    EditText oldPrintEditText;
    Bitmap bmp;
    DataTable dataTable;
    DataTableHeader header;

    String datePattern = "dd-MM-yyyy";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat datePatternFormat = new SimpleDateFormat(datePattern);

    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    ArrayList<DataTableRow> rows = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_collection_details, container, false);

        oldPritnBtn = v.findViewById(R.id.oldPrintBtn);
        oldPrintEditText = v.findViewById(R.id.oldPrintEditText);
        delete = v.findViewById(R.id.delete);

        header = new DataTableHeader.Builder()
                .item("SR.No",5)
                .item("Name",5)
                .item("Amount",5)
                .item("Date",5)
                .item("Mandal",5)
                .build();


        loadTable();
        return  v;
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
                dataTable.setHeader(header);
                dataTable.setRows(rows);
//              dataTable.inflate(rows);
// ********************************** THE ABOVE LINE GIVES AN ERROR RELATED TO CONTEXT

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}


