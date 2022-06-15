package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.widget.EditText;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;
import android.widget.TextView;

public class ResvConfirm extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private Button confirmbtn;
    private TextView resv;
    String resvdata="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resv_confirm);

        confirmbtn = (Button) findViewById(R.id.button7);
        resv = (TextView) findViewById(R.id.textView9);

        // 파이어스토어에 접근할 객체 생성
        final FirebaseFirestore db = FirebaseFirestore.getInstance();


       confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
               db.collection("reservation")
                       .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        resvdata = "=>" + document.getData();
                                        resv.setText(resvdata);
                                    }
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ResvConfirm.this);
                                    builder.setTitle("");
                                    builder.setMessage("예약 조회에 실패했습니다.");
                                    builder.setPositiveButton("ok", null);
                                    builder.create().show();
                                }
                            }

                        });
             }
        });
    }
}