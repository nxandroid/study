package com.example.sj.listview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    // CData안에 받은 값을 직접 할당
    class CData {

        private int m_szData;

        public CData(Context context, int p_szData) {
            m_szData = p_szData;
        }

        public int getData() {
            return m_szData;
        }
    }

    private class DataAdapter extends ArrayAdapter<CData> {
        // 레이아웃 XML을 읽어들이기 위한 객체
        private LayoutInflater mInflater;

        public DataAdapter(Context context, ArrayList<CData> object) {
            // 상위 클래스의 초기화 과정
            // context, 0, 자료구조
            super(context, 0, object);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        // 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
        @Override
        public View getView(int position, View v, ViewGroup parent) {
            View view = null;
            // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
            if (v == null) {
                // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
                view = mInflater.inflate(R.layout.listview_item, null);
            } else {
                view = v;
            }
            // 자료를 받는다.
            final CData data = this.getItem(position);
            if (data != null) {
                // 화면 출력
                ImageView iv = (ImageView) view.findViewById(R.id.imageView1);
                // 이미지뷰에 뿌려질 해당 이미지값을 연결 즉 세번째 인수값
                iv.setImageResource(data.getData());

            }
            return view;
        }
    }

    // 리스트뷰 선언
    private ListView listview;
    // 데이터를 연결할 Adapter
    DataAdapter adapter;
    // 데이터를 담을 자료구조
    ArrayList<CData> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 선언한 리스트뷰에 사용할 리스뷰연결
        listview = (ListView) findViewById(R.id.listView);
        // 객체를 생성합니다
        list = new ArrayList<CData>();

        // 데이터를 받기위해 데이터어댑터 객체 선언
        adapter = new DataAdapter(this, list);

        // 리스트뷰에 어댑터 연결
        listview.setAdapter(adapter);
        // ArrayAdapter를 통해서 ArrayList에 자료 저장
        // 하나의 String값을 저장하던 것을 CData클래스의 객체를 저장하던것으로 변경
        // CData클래스를 만들때의 순서대로 해당 인수값을 입력
        // 한줄 한줄이 리스트뷰에 뿌려질 한줄 한줄이라고 생각하면 됩니다.
        adapter.add(new CData(getApplicationContext(), R.drawable.pic1));
        adapter.add(new CData(getApplicationContext(), R.drawable.pic2));


        // onclick event

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast temp;
                switch (position)
                {
                    case 0:
                        temp = Toast.makeText(getApplicationContext(), R.string.flower, Toast.LENGTH_LONG);
                        temp.show();
                        break;
                    case 1:
                        temp = Toast.makeText(getApplicationContext(), R.string.bird, Toast.LENGTH_LONG);
                        temp.show();
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
