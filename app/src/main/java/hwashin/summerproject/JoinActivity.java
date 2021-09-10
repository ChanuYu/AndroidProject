package hwashin.summerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JoinActivity extends AppCompatActivity {

    EditText edit_id,edit_password,edit_name,edit_email,edit_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_password = (EditText)findViewById(R.id.edit_password);
        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_email = (EditText)findViewById(R.id.edit_email);
        edit_phone = (EditText)findViewById(R.id.edit_phone);


    }
    public void JoinMethod(View view){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String str_id = edit_id.getText().toString();
        String str_password = edit_password.getText().toString();
        String str_name = edit_name.getText().toString();
        String str_email = edit_email.getText().toString();
        String str_phone = edit_phone.getText().toString();

        ContentValues cv1 = new ContentValues();

        cv1.put("id", str_id);
        cv1.put("password", str_password);
        cv1.put("name", str_name);
        cv1.put("email", str_email);
        cv1.put("phone", str_phone);

        db.insert("JoinTable",null,cv1);


        db.close();
        //Toast.makeText(getApplicationContext(),"아이디가 "+str_id+"로 회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
        onBackPressed();
    }


}