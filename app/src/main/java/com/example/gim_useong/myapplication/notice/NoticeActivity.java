package com.example.gim_useong.myapplication.notice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gim_useong.myapplication.R;
import com.example.gim_useong.myapplication.models.Notice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {
    private ListView listView;
    List noticeDate = new ArrayList<>();
    List noticeTitle = new ArrayList<>();
    List noticeDetail = new ArrayList<>();
    ArrayAdapter adapter;
    static boolean calledAlready = false;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.menu_notice);

        listView = (ListView) findViewById(R.id.notice_list);

        adapter = new ArrayAdapter<String>(this, R.layout.notice_row_list);

        listView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("notice");

        reference.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fireSnapshot : dataSnapshot.getChildren()) {
                    String title = fireSnapshot.child("title").getValue(String.class);
                    noticeTitle.add(title);
                    String date = fireSnapshot.child("date").getValue(String.class);
                    noticeDate.add(date);
                    String detail = fireSnapshot.child("detail").getValue(String.class);
                    noticeDetail.add(detail);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
/*
    private void dataSetting(){
        NoticeAdapter noticeAdapter = new NoticeAdapter();
        String[][] notice ;
        int size = 5;

        listView = (ListView)findViewById(R.id.notice_list);

        notice = new String[][] {{"베타테스터 오픈입니다. ", "05/22", "오늘부터 베타테스트 버전을 시작합니다. 이용에 불편하신점은 문의를 해주세요.^^"},
                {"현재 접속자가 많아 일부 기능 장애", "05/24", "현재 베타테스터 중 접속자들에게 일시적 오류가 발생하여, 현재 수정중에 있습니다. 수정 완료시 공지사항에 다시 공지가 되겠습니다."},
                {"현재 접속자가 많아 일부 기능 장애(점검완료)", "05/24", "현재 베타테스터 중 접속자들에게 일시적 오류로 구동이 원할하지 못한 점 사과드립니다. 현재 오류는 수정되었고 원활한 사용이 가능하게 패치되었습니다."},
                {"오늘의 메뉴 사용시 주의사항", "05/25", "오늘의 메뉴는 사용자가 원하는 음식이 아닌 보관중인 물품에서 가장 최적화되어 높은 확률로 제조할 수 있는 것을 나타내는 것이므로 확신이 아닌 참고를 이용하여 활용하시기 바랍니다."},
                {"오류나 버그 발견시 문의 방법", "05/25", "오류나 버그를 발견 시 사용자께서는 핸드폰 캡처기능을 통해 캡처 혹은 글을 이용하여 관리자에게 문의 메일을 보내주시면 소정의 상품을 드립니다. ( e-mail : aaa@aaa.com"}};

        for(int i = size; i > 0; i--){
            noticeAdapter.addItem(String.valueOf(i), notice[i][0], notice[1][1], notice[1][2]);
        }

        listView.setAdapter(noticeAdapter);
    }
}
*/