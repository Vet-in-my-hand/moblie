package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchHospital extends AppCompatActivity {

    public static ArrayList<hospital> hospitalList = new ArrayList<hospital>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchHospital();

        setUpData();
        setUpList();
        setUpOnClickListener();
    }

    private void searchHospital(){
        SearchView searchView = findViewById(R.id.hospital_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<hospital> filterHospital = new ArrayList<>();
                for(int i = 0; i < hospitalList.size(); i++){
                    hospital hospital = hospitalList.get(i);
                    if(hospital.getName().toLowerCase().contains(newText.toLowerCase())){
                        filterHospital.add(hospital);
                    }
                }
                HospitalAdapter adapter = new HospitalAdapter(getApplicationContext(), 0, filterHospital);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    private void setUpData() {
        hospital hospital01 = new hospital("0", "충북 청주시 서원구 1순환로 627", "성공회동물병원", "010-1234-5678", "영업시간: 10:00 ~ 18:00, 진료동물/수술: 강아지, 추가정보: 내시경 가능");
        hospitalList.add(hospital01);
        hospital hospital02 = new hospital("1", "서울시 구로구 오류동 1순환로 627", "온수역동물병원", "010-4779-8699", "영업시간: 09:30 ~ 18:00, 진료동물/수술: 고양이, 추가정보: 호텔 서비스");
        hospitalList.add(hospital02);
        hospital hospital03 = new hospital("2", "경기도 부천시 1순환로 627", "부천역동물병원", "010-9876-5432", "영업시간: 11:00 ~ 19:30, 진료동물/수술: 햄스터, 추가정보: 초음파 가능");
        hospitalList.add(hospital03);
        hospital hospital04 = new hospital("3", "여수시 관문동 1순환로 627", "관문동동물병원", "010-1588-1588", "영업시간: 10:30 ~ 17:00, 진료동물/수술: 토끼, 추가정보: 용품 판매");
        hospitalList.add(hospital04);
        hospital hospital05 = new hospital("4", "서울 광진구 천호대로 644", "애플동물병원", "010-2276-0903", "영업시간: 10:00 ~ 18:00, 진료동물/수술: 파충류, 추가정보: 산란장 운영");
        hospitalList.add(hospital05);
        hospital hospital06 = new hospital("5", "서울특별시 구로구 오류동 166-9", "캥거루동물병원", "02-456-7897", "영업시간: 09:00 ~ 18:30, 진료동물/수술: 조류, 추가정보: 날개골절 수술 가능");
        hospitalList.add(hospital06);
        hospital hospital07 = new hospital("6", "서울특별시 영등포구 영등포로 184", "뉴욕동물병원", "031-654-7894", "영업시간: 08:30 ~ 17:30, 진료동물/수술: 강아지/고양이, 추가정보: 없음");
        hospitalList.add(hospital07);
        hospital hospital08 = new hospital("7", "경기도 광명시 오리로 923", "참 좋은 동물병원", "061-578-1564", "영업시간: 연중무휴/24시간, 진료동물/수술: 원숭이, 추가정보: 응급실 운영");
        hospitalList.add(hospital08);
    }

    private void setUpList(){
        listView = findViewById(R.id.hospital_listView);
        HospitalAdapter adapter = new HospitalAdapter(getApplicationContext(), 0, hospitalList);
        listView.setAdapter(adapter);
    }
    private void setUpOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                 hospital selectHospital = (hospital)listView.getItemAtPosition(position);

                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id", selectHospital.getId());
                startActivity(showDetail);
            }
        });
    }
}
