package hwashin.summerproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    String search_text;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ActionBar action = getSupportActionBar();
        //action.setDisplayHomeAsUpEnabled(false);
        //action.setDisplayShowHomeEnabled(false);
        //action.setDisplayShowTitleEnabled(false);
        text1 = (TextView)findViewById(R.id.onquery);
    }
    /*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView search_view = (SearchView)search.getActionView();
        search_view  .setQueryHint("검색");

        SearchViewListener listener = new SearchViewListener();
        search_view.setOnQueryTextListener(listener);

        return true;
    }
    class SearchViewListener implements SearchView.OnQueryTextListener{
        @Override
        public boolean onQueryTextChange(String newText) {
            text1.setText(newText);
            //데이터베이스에서 관련 헬스장 목록 띄우기
            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            search_text = query;
            text1.setText(search_text);
            //데이터베이스에서 해당 헬스장 검색하여 조회
            return false;
        }
    }
    */
}