package hwashin.summerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText text_id,text_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        text_id = (EditText)findViewById(R.id.text_id);
        text_password = (EditText)findViewById(R.id.text_password);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }


    public void Loginbtn(View view){
        // 로그인 정보 일치할 시 로그인 되게 설정하기


        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor c = db.query("JoinTable",null,null,null,null,null,null);

        /*
        String str_id = text_id.getText().toString();
        String str_password = text_password.getText().toString();*/

        while(c.moveToNext()){
            int id_pos = c.getColumnIndex("idx");
            int password_pos = c.getColumnIndex("textData");
            int name_pos = c.getColumnIndex("intData");
            int email_pos = c.getColumnIndex("floatData");
            int phone_pos = c.getColumnIndex("dateData");

            String id_data= c.getString(id_pos);
            String password_data = c.getString(password_pos);
            String name_data = c.getString(name_pos);
            String email_data = c.getString(email_pos);
            String phone_data = c.getString(phone_pos);


            /*
            if(str_id==id_data){
                Log.d("test", str_id+"와"+id_data + "의 내용입니다.");
            }
            */

            db.close();
        }


    }
    public void Joinbtn(View view){
        // 회원가입 프래그먼트로 이동하기
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
    }
}