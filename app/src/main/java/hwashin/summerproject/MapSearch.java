package hwashin.summerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Hashtable;

public class MapSearch extends AppCompatActivity {
    ListView list_view;
    //헬스장 클래스인 Gym 클래스 만들어서 해쉬테이블에 저장하고 어댑터에 적용
    //ArrayAdapter<String, Hashtable<String,Gym>> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        list_view = (ListView)findViewById(R.id.list);

    }

}