package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    hospital selectedhospital;

    private EditText date01;    // 날짜 입력칸
    private EditText time01;    // 시간 입력칸
    private EditText about; // 방문 목적란
    private EditText name;
    private Button sdbutton01;  // 예약 버튼
    private TextView hosname;   // 병원 이름
    private String username="wjd";

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSelectedhospital();
        setValues();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        date01 = (EditText) findViewById(R.id.editTextTextPersonName);
        time01 = (EditText) findViewById(R.id.editTextTextPersonName12);
        about = (EditText) findViewById(R.id.editTextTextPersonName13) ;
        name = (EditText) findViewById(R.id.editTextTextPersonName15);
        sdbutton01 = (Button) findViewById(R.id.button3);
        TextView hosname = findViewById(R.id.hospital_detail_name);

        String date = date01.getText().toString().trim();
        String time = time01.getText().toString().trim();

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

            sdbutton01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (currentUser != null) {
                        username = currentUser.getDisplayName();


                        Map<String, Object> resv = new HashMap<>();
                        resv.put("hospital", hosname.getText().toString().trim());
                        resv.put("name", name.getText().toString().trim());
                        resv.put("date", date01.getText().toString().trim());
                        resv.put("time", time01.getText().toString().trim());
                        resv.put("about", about.getText().toString().trim());

                        db.collection("reservation")
                                .add(resv)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {    // 성공했을 경우
                                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                                        builder.setTitle("");
                                        builder.setMessage("예약이 완료되었습니다.");
                                        builder.setPositiveButton("ok", null);
                                        builder.create().show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {   // 실패했을 경우
                                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                                        builder.setTitle("");
                                        builder.setMessage("예약에 실패했습니다.");
                                        builder.setPositiveButton("ok", null);
                                        builder.create().show();
                                    }
                                });
                    }
                }
            });
    }
    private void setValues(){
        TextView tv = findViewById(R.id.hospital_detail_name);
        TextView tv1 = findViewById(R.id.hospital_detail_address);
        TextView tv2 = findViewById(R.id.hospital_detail_tell);
        TextView tv3 = findViewById(R.id.hospital_detail_info);

        tv.setText(selectedhospital.getName());
        tv1.setText(selectedhospital.getAddress());
        tv2.setText(selectedhospital.getTel());
        tv3.setText(selectedhospital.getInfo());
    }
    private void getSelectedhospital(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        selectedhospital = SearchHospital.hospitalList.get(Integer.valueOf(id));
    }
    public void gotoHosResv01(View view) {
        Intent intent=new Intent(DetailActivity.this,HosResv01.class);
        startActivity(intent);
    }
}