package com.example.capstone;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import android.util.Log;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import android.widget.EditText;
import android.view.View;

import java.util.HashMap;
import java.util.Map;



public class HosResv01 extends AppCompatActivity {
    private EditText date01;    // 날짜 입력칸
    private EditText time01;    // 시간 입력칸
    private Button sdbutton01;  // 예약 버튼

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.hospital01);    // 성공회 동물병원
            mAuth = FirebaseAuth.getInstance();

            date01 = (EditText) findViewById(R.id.editTextTextPersonName2);
            time01 = (EditText) findViewById(R.id.editTextTextPersonName3);
            sdbutton01 = (Button) findViewById(R.id.button3);

            String date = date01.getText().toString().trim();
            String time = time01.getText().toString().trim();

            final FirebaseFirestore db = FirebaseFirestore.getInstance();

            sdbutton01.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Map<String, Object> resv = new HashMap<>();
                    resv.put("hospital", "성공회 동물병원");
                    resv.put("name", "김지원");
                    resv.put("date", date01.getText().toString().trim());
                    resv.put("time", time01.getText().toString().trim());

                    db.collection("reservation")
                            .add(resv)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {    // 성공했을 경우
                                    AlertDialog.Builder builder = new AlertDialog.Builder(HosResv01.this);
                                    builder.setTitle("");
                                    builder.setMessage("예약이 완료되었습니다.");
                                    builder.setPositiveButton("ok", null);
                                    builder.create().show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {   // 실패했을 경우
                                    AlertDialog.Builder builder = new AlertDialog.Builder(HosResv01.this);
                                    builder.setTitle("");
                                    builder.setMessage("예약에 실패했습니다.");
                                    builder.setPositiveButton("ok", null);
                                    builder.create().show();
                                }
                            });
                }
            });


        }
    }


